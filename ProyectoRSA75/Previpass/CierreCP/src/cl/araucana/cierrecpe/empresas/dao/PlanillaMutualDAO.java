

/*
 * @(#) planillaAFPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualCotizante;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualPaginaDetalle;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 10-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class PlanillaMutualDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public PlanillaMutualDAO(ConectaDB2 db2, String esquema) {
		this.db2= db2;
		this.esquema= esquema;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		PlanillaMutualDocumentModel planilla= (PlanillaMutualDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf3000 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getNombreEntidad());
		db2.setStatement(2, planilla.getFolio());
		db2.setStatement(3, planilla.getDatosEmpleador().getRutEmpresa(9));
		db2.setStatement(4, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(5, planilla.getSecuenciaFolio());
		db2.setStatement(6, planilla.getTipoProceso());
		db2.setStatement(7, planilla.getPeriodo());
		db2.setStatement(8, planilla.getTipoRemuneracion());
		db2.setStatement(9, String.valueOf(planilla.getPeriodo()).substring(4, 6));
		db2.setStatement(10, String.valueOf(planilla.getPeriodo()).substring(0, 4));
		db2.setStatement(11, planilla.getTotalRemuneracionImponible());
		db2.setStatement(12, planilla.getNumeroAfiliadosInformados());
		db2.setStatement(13, planilla.getNumeroAdherente());
		db2.setStatement(14, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(15, planilla.getDatosEmpleador().getCodActEconomica());
		db2.setStatement(16, planilla.getDatosSucursal().getDireccion());
		db2.setStatement(17, planilla.getDatosSucursal().getEmail(30));
		db2.setStatement(18, planilla.getDatosSucursal().getFax(12));
		db2.setStatement(19, planilla.getDatosSucursal().getTelefono(12));
		db2.setStatement(20, planilla.getDatosEmpleador().getApellidosRepLegal());
		db2.setStatement(21, planilla.getDatosEmpleador().getNombresRepLegal());
		db2.setStatement(22, planilla.getDatosEmpleador().getRutRepLegal(9));
		db2.setStatement(23, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		//db2.setStatement(24, planilla.getPorcTasaCotizacion());
		db2.setStatement(24, planilla.getTasaMutual());
		db2.setStatement(25, planilla.getTotalCotizacion());
		db2.setStatement(26, planilla.getTotalAPagar());
		db2.setStatement(27, planilla.getNumeroHojasAnexas());
		db2.setStatement(28, planilla.getFechaPago());
		db2.setStatement(29, planilla.getConvenio());
		db2.setStatement(30, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(31, planilla.getGrupoConvenio());
		db2.setStatement(32, planilla.getFilter()); //DYP(1), DYNP(0)
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaMutualPaginaDetalle pagina = (PlanillaMutualPaginaDetalle) iter.next();
			insertDetalle(pagina);
			if(pagina.getCabeceraPlanilla().getTipoProceso().equals("R")){
				insertPWF0000(pagina);
			}
		}
		return result;
	}
	
	private int insertDetalle(Object obj) throws SQLException {
		PlanillaMutualPaginaDetalle pagina= (PlanillaMutualPaginaDetalle) obj;
		logger.finest("Nro cotizantes pagina=" + pagina.getCotizantes().size());
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf3100 values( ?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaMutualCotizante cotizante = (PlanillaMutualCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(3, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(4, pagina.getSecuenciaFolio());
			db2.setStatement(5, cotizante.getRutCotizante(9));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(7, cotizante.getRutCotizante().getDV());
			db2.setStatement(8, cotizante.getApellidosCotizante());
			db2.setStatement(9, cotizante.getNombresCotizante());
			db2.setStatement(10, cotizante.getRemuneracionImponible());
			db2.setStatement(11, cotizante.getMontoCotizacion());
			db2.setStatement(12, cotizante.getCodigoSexo());
			db2.setStatement(13, cotizante.getNumeroDiasTrabajados());
			db2.executeUpdate();
		}
		
		return 0;
	}
	
	private int insertPWF0000(Object obj) {
		int resultado=0;
		try {
			PlanillaMutualPaginaDetalle pagina= (PlanillaMutualPaginaDetalle) obj;
			StringBuffer query= new StringBuffer();
			query.append("INSERT INTO " + getEsquema() + ".pwf0000 values( ?,?,?,?,?,?,?,?,? ) ");
			//logger.finest("Query=" + query.toString());
			db2.prepareQuery(query.toString());
			
			for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
				PlanillaMutualCotizante cotizante = (PlanillaMutualCotizante) iterator.next();
				System.out.println("rutempresa=" + pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber());
				db2.setStatement(1, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber());
				System.out.println("cotizante=" + cotizante.getRutCotizante().getNumber());
				db2.setStatement(2, cotizante.getRutCotizante().getNumber());
				db2.setStatement(3, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
				db2.setStatement(4, cotizante.getRutCotizante().getDV());
				db2.setStatement(5, pagina.getCabeceraPlanilla().getPeriodo());
				db2.setStatement(6, pagina.getCabeceraPlanilla().getConvenio());
				db2.setStatement(7, pagina.getCabeceraPlanilla().getGrupoConvenio());
				System.out.println("dias trab.=" + cotizante.getDiasTrabajados());
				db2.setStatement(8, cotizante.getDiasTrabajados());
				db2.setStatement(9, cotizante.getGeneroCotizante());
				resultado= db2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			logger.warning("Error al insertar PWF0000, mensaje:" + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/**
	 * @return el esquema
	 */
	public String getEsquema() {
		return esquema;
	}

}

