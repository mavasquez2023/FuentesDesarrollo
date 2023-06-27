package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.ComprobanteDAO;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.DetalleReporteDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.MapeoTesoreriaDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.util.reporte.DetalleSabanaPDF;

/*
 * @(#) ParametroMgr.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cllanos
 * @author cmeli
 * 
 * @version 1.4
 */
public class SabanaMgr
{
	private ComprobanteDAO comprobanteDao;
	private EmpresaDAO empresaDao;
	private MapeoTesoreriaDAO mapeoTesoreriaDao;
	private NominaDAO nominaDao;
	private DetalleReporteDAO detalleReporteDao;
	private ParametrosDAO parametrosDao;

	private ConvenioDAO convenio;

	public SabanaMgr(Session session)
	{
		this.comprobanteDao = new ComprobanteDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.mapeoTesoreriaDao = new MapeoTesoreriaDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.detalleReporteDao = new DetalleReporteDAO(session);
		this.parametrosDao = new ParametrosDAO(session);
		this.convenio = new ConvenioDAO(session);
	}

	/**
	 * detalle reporte
	 * 
	 * @param idTipo
	 * @return
	 * @throws DaoException
	 */
	public List getDetalleReporte(int idTipo) throws DaoException
	{
		return this.detalleReporteDao.getLista(idTipo);
	}

	/**
	 * tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getTiposProceso() throws DaoException
	{
		return this.nominaDao.getTiposNominas();
	}

	/**
	 * generar pdf
	 * 
	 * @param codigoBarra
	 * @param rutEmpresa
	 * @return
	 * @throws Exception
	 */
	public String generarPdf(String codigoBarra, String rutEmpresa, String tipoAdmin) throws Exception
	{
		long idCodigoBarra = Long.parseLong(codigoBarra);
		ComprobanteVO comprobante = this.comprobanteDao.getComprobante(idCodigoBarra);
		this.comprobanteDao.loadConfigPDF(comprobante.getSecciones());
		List listaTipoSeccion = this.mapeoTesoreriaDao.getTipoSeccion();
		List listaDetReporte = getDetalleReporte(Constants.DET_REPORTE_SABANA);
		HashMap listaLeyendas = this.detalleReporteDao.getPropertiesMapeo(Constants.PROP_MAPEO_MX_LEYENDA);
		HashMap tipoNominas = new HashMap();

		Collection tmpList = getTiposProceso();
		for (Iterator it = tmpList.iterator(); it.hasNext();)
		{
			TipoNominaVO tn = (TipoNominaVO) it.next();
			tipoNominas.put(tn.getIdTipoNomina(), tn.getDescripcion().trim());
		}

		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_TASA_FIJA));
		listaParams.add(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE));
		listaParams.add(new Integer(Constants.PARAM_PERIODO));
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
		String nameFile = "";
		int rutEmp = Integer.parseInt(rutEmpresa);
		NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
		EmpresaVO empresa = this.empresaDao.getEmpresaCasaMatriz(rutEmp);
		ConvenioVO cn = this.convenio.getConvenio(rutEmp, nomina.getIdConvenio());
		GrupoConvenioVO gc = this.convenio.getGrupoConvenio(cn.getIdGrupoConvenio());
		List listaAPVs = this.comprobanteDao.getApvs(empresa.getIdEmpresa(), nomina.getIdConvenio());
		DetalleSabanaPDF sabana = new DetalleSabanaPDF(paramHash, tipoNominas, listaDetReporte, listaTipoSeccion, listaLeyendas, listaAPVs);
//TODO Quitar comentario jlandero
		nameFile = /*Constants.RUTA_CMPS +*/ codigoBarra + ".pdf";

		boolean result = sabana.createPDFtoPago(nameFile, comprobante, empresa, nomina, gc.getNombre().trim(), cn , tipoAdmin);
		if (!result)
			throw new Exception("ERROR en la generacion de PDF para pago por caja::");
		return nameFile;
	}
}
