/**
 * 
 */
package cl.araucana.cr.pdfservice.nc.dao.as400;

/**
 * @author J-Factory (Claudio Lillo)
 *
 */
import cl.araucana.core.util.*;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.cr.pdfservice.nc.*;
import cl.araucana.fpg.DocumentModel;
import cl.araucana.pdfservice.common.dao.as400.AS400DocumentModelList;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class AS400NCDocumentModelList extends AS400DocumentModelList
implements Constants
{
	private static Logger logger = LogManager.getLogger();
	private int filter;
	private String periodo;
	private String nombrePeriodo;
	private String sFechaTopePago;
	public AS400NCDocumentModelList(int documentCount, PreparedStatement documentHeaderStmt, PreparedStatement documentDetailStmt, int filter, AbsoluteDate period, String sFechaTopePago)
	{
		super(documentCount, documentHeaderStmt, documentDetailStmt);
		nombrePeriodo = period.getMonthName().toUpperCase() + " del " + period.getYear();
		periodo = (new StringBuffer(String.valueOf(period.getPeriod()))).toString();
		this.sFechaTopePago = sFechaTopePago;
		this.filter = filter;
	}

	protected DocumentModel newDocumentModel()
	{
		if(filter == 0)
			return new NC_ADocumentModel();
		else
			return new NC_PDocumentModel();
	}

	protected void setDocumentModelHeader(DocumentModel docModel)
	throws SQLException
	{
		NCDocumentModel ncDocModel = (NCDocumentModel)docModel;
		ncDocModel.setPeriodo(periodo);
		ncDocModel.setNombrePeriodo(nombrePeriodo);
		ncDocModel.setFechaTopePago(sFechaTopePago);
		ncDocModel.setFolio(rsDocumentHeader.getString(1));
		ncDocModel.setCodigoBarraTesoreria(rsDocumentHeader.getString(2));
		ncDocModel.setOficina(Padder.lpad(rsDocumentHeader.getInt(3), 3, '0'));
		String razonSocial = truncate(rsDocumentHeader.getString(4).replaceAll("/", " "), 40);
		ncDocModel.setRazonSocial(razonSocial);
		ncDocModel.setNombreNomina(truncate(rsDocumentHeader.getString(5), 15));
		ncDocModel.setRutEmpresa((new Rut(rsDocumentHeader.getString(6), false)).toString());
		if(razonSocial.equals("NO ENCONTRADA"))
			logger.warning(ncDocModel.getFolio() + "/" + ncDocModel.getRutEmpresa() + ": Razon social de empresa no encontrada.");
		ncDocModel.setNumeroNomina(Padder.lpad(rsDocumentHeader.getInt(7), 3, '0'));
		long montoTotal = rsDocumentHeader.getLong(8);
		ncDocModel.setMontoTotal(montoTotal);
		ncDocModel.setTotalAPagar(Padder.lpad(Padder.padSeparators(montoTotal), 13));
		ncDocModel.setNCuotas(rsDocumentHeader.getInt(9));
	}

	protected void setDocumentModelDetail(DocumentModel docModel)
	throws SQLException
	{
		NCDocumentModel ncDocModel = (NCDocumentModel)docModel;
		int nCuotas = ncDocModel.getNCuotas();
		List detalleCuotasList = new ArrayList(nCuotas);
		for(int i = 0; i < nCuotas; i++)
		{
			rsDocumentDetail.next();
			NCDetalleCuota detalleCuota = new NCDetalleCuota();
			String rutDeudor = rsDocumentDetail.getString(2);
			if(!rsDocumentDetail.wasNull())
				rutDeudor = (new Rut(rutDeudor, false)).toString();
			else
				rutDeudor = "";
			detalleCuota.setRutDeudor(Padder.lpad(rutDeudor, 12));
			String nombreDeudor = rsDocumentDetail.getString(3);	
			detalleCuota.setNombreDeudor(truncate(nombreDeudor, 37));
			if(nombreDeudor.equals("NO ENCONTRADO"))
				logger.warning(ncDocModel.getFolio() + "/" + rutDeudor + ": Nombre de deudor no encontrado.");
			detalleCuota.setCredito(Padder.lpad(rsDocumentDetail.getInt(4) + "-" + rsDocumentDetail.getInt(5), 12));
			int noCuota = rsDocumentDetail.getInt(6);
			int nCuotasCredito = rsDocumentDetail.getInt(7);
			if(nCuotasCredito > 0)
				detalleCuota.setCuotas(Padder.lpad(noCuota, 3) + "/" + nCuotasCredito);
			else
				detalleCuota.setCuotas(Padder.lpad(noCuota, 3));
			detalleCuota.setValorCuotaMes(Padder.lpad(Padder.padSeparators(rsDocumentDetail.getInt(8)), 11));
			detalleCuotasList.add(detalleCuota);
		}

		Collections.sort(detalleCuotasList);
		ncDocModel.setDetalleCuotas(detalleCuotasList);
		ncDocModel.setNPaginas((nCuotas - 1) / 19 + 1);
	}

}
