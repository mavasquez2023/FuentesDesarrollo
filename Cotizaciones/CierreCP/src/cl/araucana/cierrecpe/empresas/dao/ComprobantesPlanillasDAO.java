

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;

import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Detalle;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Totales;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.IdentificacionSucursal;
import cl.recursos.ConectaDB2;
import cl.recursos.Formato;

import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

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
public class ComprobantesPlanillasDAO implements DAO_Interface {
private ConectaDB2 db2;
private String esquema;
private static Logger logger = LogManager.getLogger();	
	
	public ComprobantesPlanillasDAO(ConectaDB2 db2, String esquema){
		this.db2= db2;
		this.esquema= esquema;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		Comprobante_Encabezado comprobante= (Comprobante_Encabezado) pk;

		StringBuffer query= new StringBuffer();
		query.append("delete from  PWF5000");
		query.append(" where pwccrutem= ? ");
		query.append(" and pwcccopro= ? ");
		query.append(" and pwcccdhol= ? ");
		query.append(" and pwccconv= ? ");
		query.append(" and pwcctipro= ? ");
		
		
		//logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, comprobante.getDatosEmpleador().getRutEmpresa().getNumber());
		db2.setStatement(2, comprobante.getPeriodo());
		db2.setStatement(3, comprobante.getGrupoConvenio());
		db2.setStatement(4, comprobante.getConvenio());
		db2.setStatement(5, comprobante.getTipoProceso());

		db2.executeUpdate();

	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj, IdentificacionSucursal sucursal) throws SQLException {
		Comprobante_Encabezado comprobante= (Comprobante_Encabezado) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO PWF5000 ");
		query.append(" (PWCCNUMCO, PWCCTIPRO, PWCCCOPRO, PWCCRUTEM, PWCCDIGEM, PWCCRAZSO, PWCCCONV, PWCCCIUEM, PWCCREGEM, PWCCTELEM, PWCCTOTTR, PWCCTOTPA, PWCCNOMRE, PWCCCOFIR, PWCCFECHA, PWCCFEPAG, PWCCCDHOL, PWCCCIERRE, PWCCREMIM, PWCCFOLTES, PWCCDOMEM, PWCCCOMEM, PWCCFORPAG ) ");
		query.append(" values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, String.valueOf(comprobante.getId_codigo_barra()).trim() );
		db2.setStatement(2, comprobante.getTipoProceso());
		db2.setStatement(3, comprobante.getPeriodo());
		db2.setStatement(4, comprobante.getDatosEmpleador().getRutEmpresa().getNumber());
		db2.setStatement(5, comprobante.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(6, comprobante.getDatosEmpleador().getRazonSocial());
		db2.setStatement(7, comprobante.getConvenio());
		db2.setStatement(8, sucursal.getCiudad());
		db2.setStatement(9, sucursal.getRegion());
		db2.setStatement(10, sucursal.getTelefono());
		db2.setStatement(11, comprobante.getNumTrabajadores());
		db2.setStatement(12, comprobante.getMontoTotal());
		String replegal= comprobante.getDatosEmpleador().getNombresRepLegal() + " " + comprobante.getDatosEmpleador().getApellidosRepLegal();
		if(replegal.length()>50){
			replegal.substring(0, 50);
		}
		db2.setStatement(13, replegal);
		db2.setStatement(14, "");
		db2.setStatement(15, comprobante.getFechaEmision());
		String fechapago= comprobante.getFechaPago().getYear() + Formato.paddingLeft(String.valueOf(comprobante.getFechaPago().getMonth()), 2, '0') + Formato.paddingLeft(String.valueOf(comprobante.getFechaPago().getDay()), 2, '0');
		db2.setStatement(16, fechapago);
		db2.setStatement(17, comprobante.getGrupoConvenio());
		db2.setStatement(18, comprobante.getCierre());
		db2.setStatement(19, comprobante.getTotalRemuneraciones());
		db2.setStatement(20, comprobante.getFolioTesoreria());
		db2.setStatement(21, sucursal.getDireccion());
		db2.setStatement(22, sucursal.getComuna());
		db2.setStatement(23, comprobante.getFormaPago());
		
		int result= db2.executeUpdate();
		return result;
	}
	
	public int insertDetalle(Object obj) throws SQLException {
		Comprobante_Detalle detalle= (Comprobante_Detalle) obj;
		if(!detalle.getEncabezado().getTipoProceso().equals("R")){
			return insertDetalle_GratiReliq(obj);
		}
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO PWF5100 " );
		query.append(" (PWDCNUMCO, PWDCTIPEM, PWDCNOMEM, PWDCCONCE, PWDCMONTO, PWDCMONPEN, PWDCMONSIS, PWDCMONAHO, PWDCMONCES, PWDCMONTP, PWDCNUMTR) ");
		query.append(" values( ?,?,?,?,?,?,?,?,?,?,? ) ");
		//logger.finest("Query=" + query.toString());
		int result=0;
		if(detalle.getNombreSeccion().equals(Constants.SECCION_INP)){
			//Pensión
			if(detalle.getM2() + detalle.getM5() - detalle.getM8()>0){
				long monto= detalle.getM2() + detalle.getM5() - detalle.getM8();
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_INP_PENSION);
				db2.setStatement(5, monto);
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Fonasa
			if(detalle.getM3()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, "FONASA");
				db2.setStatement(4, Constants.CONCEPTO_INP_FONASA);
				db2.setStatement(5, detalle.getM3());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Accidente del Trabajo
			if(detalle.getM4()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_INP_ACCIDENTE);
				db2.setStatement(5, detalle.getM4());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Insertando Asfam
			if(detalle.getM7()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_INP_ASFAM);
				db2.setStatement(5, detalle.getM7());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
		}
		else if(detalle.getNombreSeccion().equals(Constants.SECCION_CAJA)){
			//Asfam
			if(detalle.getM2()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_ASFAM);
				db2.setStatement(5, detalle.getM2());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Aporte
			if(detalle.getM1()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_APORTE);
				db2.setStatement(5, detalle.getM1());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//SFI
			if(detalle.getM3()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_SFI);
				db2.setStatement(5, detalle.getM3());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Credito
			if(detalle.getM4()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_CREDITO);
				db2.setStatement(5, detalle.getM4());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Leasing
			if(detalle.getM5()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_LEASING);
				db2.setStatement(5, detalle.getM5());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Seguro Vida
			if(detalle.getM6()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_VIDA);
				db2.setStatement(5, detalle.getM6());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Convenio Dental
			if(detalle.getM7()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_DENTAL);
				db2.setStatement(5, detalle.getM7());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
		}else if(detalle.getNombreSeccion().equals(Constants.SECCION_AFP)){
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "");
			db2.setStatement(5, detalle.getMtotal());
			db2.setStatement(6, detalle.getM2());
			db2.setStatement(7, detalle.getM12());
			db2.setStatement(8, detalle.getM3());
			db2.setStatement(9, detalle.getM8());
			db2.setStatement(10, detalle.getM9());
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}else if(detalle.getNombreSeccion().equals(Constants.SECCION_MUTUAL)){
			//Tasa Fija
			long tasafija=95;
			long tasaadi=detalle.getM1() - tasafija;
			if(tasaadi<0){
				tasaadi=0;
			}
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "Tasa Fija");
			db2.setStatement(5, tasafija);
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
			//Tasa Adicional
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "Tasa Adicional");
			db2.setStatement(5, tasaadi);
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}else{
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "");
			db2.setStatement(5, detalle.getMtotal());
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}
		return result;
	}	
	
	public int insertDetalle_GratiReliq(Object obj) throws SQLException {
		Comprobante_Detalle detalle= (Comprobante_Detalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO PWF5100 " );
		query.append(" (PWDCNUMCO, PWDCTIPEM, PWDCNOMEM, PWDCCONCE, PWDCMONTO, PWDCMONPEN, PWDCMONSIS, PWDCMONAHO, PWDCMONCES, PWDCMONTP, PWDCNUMTR) ");
		query.append(" values( ?,?,?,?,?,?,?,?,?,?,? ) ");
		//logger.finest("Query=" + query.toString());
		int result=0;
		if(detalle.getNombreSeccion().equals(Constants.SECCION_INP)){
			//Pensión
			if(detalle.getM1()>0){
				long monto= detalle.getM1();
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_INP_PENSION);
				db2.setStatement(5, monto);
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Fonasa
			if(detalle.getM2()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, "FONASA");
				db2.setStatement(4, Constants.CONCEPTO_INP_FONASA);
				db2.setStatement(5, detalle.getM2());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//Accidente del Trabajo
			if(detalle.getM3()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_INP_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_INP_ACCIDENTE);
				db2.setStatement(5, detalle.getM3());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			
		}
		else if(detalle.getNombreSeccion().equals(Constants.SECCION_CAJA)){
			
			//Aporte
			if(detalle.getM1()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_APORTE);
				db2.setStatement(5, detalle.getM1());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
			//SFI
			if(detalle.getM1()>0){
				db2.prepareQuery(query.toString());
				db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
				db2.setStatement(2, Constants.SECCION_CAJA_PWF5000);
				db2.setStatement(3, detalle.getNombreEntidad());
				db2.setStatement(4, Constants.CONCEPTO_CAJA_SFI);
				db2.setStatement(5, detalle.getM1());
				db2.setStatement(6, 0);
				db2.setStatement(7, 0);
				db2.setStatement(8, 0);
				db2.setStatement(9, 0);
				db2.setStatement(10, 0);
				db2.setStatement(11, detalle.getN_trabajadores());
				result+= db2.executeUpdate();
			}
		}else if(detalle.getNombreSeccion().equals(Constants.SECCION_AFP)){
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "");
			db2.setStatement(5, detalle.getMtotal());
			db2.setStatement(6, detalle.getM1());
			db2.setStatement(7, detalle.getM7());
			db2.setStatement(8, 0);
			db2.setStatement(9, detalle.getM4());
			db2.setStatement(10, detalle.getM5());
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}else if(detalle.getNombreSeccion().equals(Constants.SECCION_MUTUAL)){
			//Tasa Fija
			long tasafija=95;
			long tasaadi=detalle.getM1() - tasafija;
			if(tasaadi<0){
				tasaadi=0;
			}
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "Tasa Fija");
			db2.setStatement(5, tasafija);
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
			//Tasa Adicional
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "Tasa Adicional");
			db2.setStatement(5, tasaadi);
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}else{
			db2.prepareQuery(query.toString());
			db2.setStatement(1, String.valueOf(detalle.getEncabezado().getId_codigo_barra()).trim());
			db2.setStatement(2, detalle.getNombreSeccion());
			db2.setStatement(3, detalle.getNombreEntidad());
			db2.setStatement(4, "");
			db2.setStatement(5, detalle.getMtotal());
			db2.setStatement(6, 0);
			db2.setStatement(7, 0);
			db2.setStatement(8, 0);
			db2.setStatement(9, 0);
			db2.setStatement(10, 0);
			db2.setStatement(11, detalle.getN_trabajadores());
			result+= db2.executeUpdate();
		}
		return result;
	}	
	
	public int insertTotalesxSeccion(Object obj) throws SQLException {
		Comprobante_Totales totales= (Comprobante_Totales) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO  PWF5200 values( ?,?,?,? ) ");
		//System.out.println("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1,  String.valueOf(totales.getEncabezado().getId_codigo_barra()).trim());
		String seccion= totales.getNombreSeccion();
		if(seccion.equals("INP")){
			seccion= Constants.SECCION_INP_PWF5000;
		}
		if(seccion.equals("CAJA")){
			seccion= Constants.SECCION_CAJA_PWF5000;
		}
		db2.setStatement(2, seccion);
		db2.setStatement(3, totales.getMtotal());
		db2.setStatement(4, totales.getN_trabajadores());
		int result= db2.executeUpdate();
		return result;
	}	
	
	
	//Solo para cerrar conexión por detalle comprobantes
	public void close(){
		try {
			db2.desconectaDB2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

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

	/**
	 * @param esquema el esquema a establecer
	 */
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}

