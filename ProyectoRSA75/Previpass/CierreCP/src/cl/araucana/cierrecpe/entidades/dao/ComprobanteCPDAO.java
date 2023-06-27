/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.ComprobanteCPTO;
import cl.araucana.cierrecpe.entidades.to.FormasPagoTO;
import cl.araucana.cierrecpe.entidades.to.TipoDetalleTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;
import cl.recursos.ConectaDB2;
import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ComprobanteCPDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public ComprobanteCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk, String formapago, char tipoNomina) throws SQLException {
		TipoDetalleTO detalleTO= (TipoDetalleTO) pk;
		String colmontos="";
		for (Iterator iter = detalleTO.getMontos().iterator(); iter.hasNext();) {
			String idmonto = (String) iter.next();
			colmontos += "t3.M" + idmonto + " + ";
		}
		colmontos= colmontos.substring(0, colmontos.length()-2);
		StringBuffer query= new StringBuffer();
		query.append("select t1.ID_CODIGO_BARRA, t1.FOLIO_TESORERIA , t4.ID_EMPRESA, (" + colmontos + ") as Monto ");
		query.append("from COMPROBANTE_PAGO t1, ");
		query.append("SECCION t2, ");
		query.append("DETALLE_SECCION t3, ");
		switch (tipoNomina) {
		case 'R':
			query.append("NOMINARE t4 ");
			break;
		case 'G':
			query.append("NOMINAGR t4 ");
			break;
		case 'D':
			query.append("NOMINADC t4 ");
			break;
		case 'A':
			query.append("NOMINARA t4 ");
			break;
		}
		query.append("WHERE t1.ID_CODIGO_BARRA= t2.ID_CODIGO_BARRA ");
		query.append("AND t2.ID_CODIGO_BARRA= t3.ID_CODIGO_BARRA ");
		query.append("AND t2.ID_TIPO_SECCION= t3.ID_TIPO_SECCION ");
		query.append("AND t1.ID_ESTADO IN ( 4, 5 ) ");
		query.append("AND t1.ID_CODIGO_BARRA= t4.ID_CODIGO_BARRA ");

		//Se incluye solo secciones pagadas (t2.tipo_pago=1) o detalles parcialmente pagados (t2.tipo_pago= 0 AND t3.tipo_pago=1)
		query.append("AND (t2.tipo_pago=1 OR (t2.tipo_pago= 0 AND t3.tipo_pago=1)) ");
		query.append("AND t1.CIERRE= 0 ");
		query.append("AND t3.ID_TIPO_SECCION= ? ");
		query.append("AND t3.ID_DETALLE_SECCION= ? ");
		query.append("AND t1.FORMA_PAGO IN(" + formapago + ")");
		if(detalleTO.getTipoSeccion()== 5){
			//SI seccion es CCAF se forzará que aparezca Propuesta de Pago para Caja y comprobante aparezca en detalle.
			query.append("AND (" + colmontos + ")>=0");
		}else{
			query.append("AND (" + colmontos + ")>0");
		}
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, detalleTO.getTipoSeccion());
		db2.setStatement(2, detalleTO.getTipoDetalle());
		
		//Se ejecuta la query
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while (db2.next()) {
			long codigoBarra= db2.getLong(1);
			int folio= db2.getInt(2);
			int rutempresa= db2.getInt(3);
			long monto= db2.getLong(4);
																				
			ComprobanteCPTO comprobante= new ComprobanteCPTO();
			comprobante.setCodigoBarra(codigoBarra);
			comprobante.setFolioIngreso(folio);
			comprobante.setRutEmpresa(new Rut(rutempresa));
			comprobante.setMonto(monto);
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}

	public Collection selectNumComprobantes() throws SQLException{
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("select forma_pago, count(*) as cantidad, id_estado, sum(total) as total ");
		query.append("from COMPROBANTE_PAGO ");
		query.append("WHERE ID_ESTADO IN (4, 5) ");
		query.append("and CIERRE=0 ");
		query.append("GROUP BY forma_pago, id_estado ");
		query.append("ORDER BY forma_pago, id_estado DESC");
		logger.finest("Query=" + query.toString());
		List formas= new ArrayList();
		db2.executeQuery(query.toString());
		int formaPagoOld=-1;
		FormasPagoTO formaTO=null;
		while (db2.next()){
			int formaPago= db2.getInt(1);
			int numComprobantes= db2.getInt(2);
			int id_estado= db2.getInt(3);
			long totalComprobantes= db2.getLong(4);
			//System.out.println("Forma Pago old=" + formaPagoOld + ", formapago=" + formaPago);
			if(formaPago!= formaPagoOld){
				formaTO = new FormasPagoTO();
				formaTO.setFormaPago(formaPago);
				formaTO.setNumComprobantes(numComprobantes);
				formaTO.setTotalComprobantes(totalComprobantes);
				if(id_estado== 4){
					formaTO.setNumDYNP(numComprobantes);
				}
				formas.add(formaTO);
			}else{
				formaTO.setNumComprobantes(formaTO.getNumComprobantes()+ numComprobantes);
				formaTO.setTotalComprobantes(formaTO.getTotalComprobantes() + totalComprobantes);
				formaTO.setNumDYNP(numComprobantes);
			}
			formaPagoOld= formaPago;
		}
		
		return formas;
	}
	
	public Date selectMinPagadoCierre(int formapago1, int formapago2) throws SQLException{
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("SELECT MIN(DATE(EMITIDO)) ");
		query.append("FROM COMPROBANTE_PAGO ");
		query.append("WHERE CIERRE=0 ");
		query.append("AND ID_ESTADO IN(4, 5, 8) ");
		query.append("AND FORMA_PAGO in (0, ?, ?) ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, formapago1);
		db2.setStatement(1, formapago2);
		db2.executeQuery();
		
		if (db2.next()){
			Date fecha= db2.getDate(1);
			return fecha;
		}
		db2.closeStatement();
		return null;
	}
	
	public Date selectMaxPagado() throws SQLException{
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("SELECT current_date ");
		query.append("FROM sysibm.sysdummy1 ");

		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, formaPago);
		db2.executeQuery();
		
		if (db2.next()){
			Date fecha= db2.getDate(1);
			return fecha;
		}
		db2.closeStatement();
		return null;
	}
	
	
	public boolean selectIsComprobanteValido(int formaPago, int folio) throws SQLException{
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("SELECT 1 ");
		query.append("FROM COMPROBANTE_PAGO ");
		query.append("WHERE ((FORMA_PAGO != ?  AND CIERRE=0) ");
		query.append("OR  CIERRE>0) ");
		query.append("AND FOLIO_TESORERIA = ? ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, formaPago);
		db2.setStatement(2, folio);
		db2.executeQuery();
		
		if (db2.next()){
			int existe= db2.getInt(1);
			return false;
		}
		db2.closeStatement();
		return true;
	}
	
	public String selectTipoEmpresa(int rut) throws SQLException{
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("SELECT TIPO ");
		query.append("FROM EMPRESA ");
		query.append("WHERE ID_EMPRESA= ? ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, rut);
		db2.executeQuery();
		
		if (db2.next()){
			String tipo= db2.getString(1);
			return tipo;
		}
		db2.closeStatement();
		return null;
	}
	
	public Collection selectComprobantesCuadraturaCP(String formaPago, boolean allCierre) throws SQLException{
		
		
		//Se guardan en única lista los descuadres en CP
		List comprobantes= new ArrayList();
		
		//rescatando comprobantes que están en CP
		StringBuffer query= new StringBuffer();
		query.append("SELECT 'R', A.ID_CODIGO_BARRA, A.FOLIO_TESORERIA, B.ID_EMPRESA, B.ID_CONVENIO, A.TOTAL, A.N_TRABAJADORES, A.PAGADO, TIME(EMITIDO), A.ID_ESTADO ");
		query.append("FROM COMPROBANTE_PAGO A , NOMINARE B ");
		query.append("WHERE A.ID_CODIGO_BARRA= B.ID_CODIGO_BARRA ");
		if(allCierre){
			query.append("AND A.CIERRE>=0 ");
		}else{
			query.append("AND A.CIERRE=0 ");
		}
		query.append("AND A.ID_ESTADO IN(4, 5) ");
		query.append("AND A.FORMA_PAGO in( " + formaPago + " ) ");
		query.append("UNION ");
		query.append("SELECT 'G', A.ID_CODIGO_BARRA, A.FOLIO_TESORERIA, B.ID_EMPRESA, B.ID_CONVENIO, A.TOTAL, A.N_TRABAJADORES, A.PAGADO, TIME(EMITIDO), A.ID_ESTADO ");
		query.append("FROM COMPROBANTE_PAGO A , NOMINAGR B ");
		query.append("WHERE A.ID_CODIGO_BARRA= B.ID_CODIGO_BARRA ");
		if(allCierre){
			query.append("AND A.CIERRE>=0 ");
		}else{
			query.append("AND A.CIERRE=0 ");
		}
		query.append("AND A.ID_ESTADO IN(4, 5) ");
		query.append("AND A.FORMA_PAGO in ( " + formaPago + " ) ");
		query.append("UNION ");
		query.append("SELECT 'D', A.ID_CODIGO_BARRA, A.FOLIO_TESORERIA, B.ID_EMPRESA, B.ID_CONVENIO, A.TOTAL, A.N_TRABAJADORES, A.PAGADO, TIME(EMITIDO), A.ID_ESTADO ");
		query.append("FROM COMPROBANTE_PAGO A , NOMINADC B ");
		query.append("WHERE A.ID_CODIGO_BARRA= B.ID_CODIGO_BARRA ");
		if(allCierre){
			query.append("AND A.CIERRE>=0 ");
		}else{
			query.append("AND A.CIERRE=0 ");
		}
		query.append("AND A.ID_ESTADO IN(4, 5) ");
		query.append("AND A.FORMA_PAGO in ( " + formaPago + " ) ");
		query.append("UNION ");
		query.append("SELECT 'A', A.ID_CODIGO_BARRA, A.FOLIO_TESORERIA, B.ID_EMPRESA, B.ID_CONVENIO, A.TOTAL, A.N_TRABAJADORES, A.PAGADO, TIME(EMITIDO), A.ID_ESTADO ");
		query.append("FROM COMPROBANTE_PAGO A , NOMINARA B ");
		query.append("WHERE A.ID_CODIGO_BARRA= B.ID_CODIGO_BARRA ");
		if(allCierre){
			query.append("AND A.CIERRE>=0 ");
		}else{
			query.append("AND A.CIERRE=0 ");
		}
		query.append("AND A.ID_ESTADO IN(4, 5) ");
		query.append("AND A.FORMA_PAGO in ( " + formaPago + " ) ");
		query.append("ORDER BY PAGADO ");
		logger.finest("Query=" + query.toString());
		
		db2.prepareQuery(query.toString());
		db2.executeQuery();
		
		while (db2.next()){
			char tipoNomina= db2.getString(1).charAt(0);
			long codigoBarra= db2.getLong(2);
			int folioIngreso= db2.getInt(3);
			int rutEmpresa= db2.getInt(4);
			int convenio= db2.getInt(5);
			long monto= db2.getLong(6);
			int n_trabajadores= db2.getInt(7);
			AbsoluteDate pagado= new AbsoluteDate(db2.getDate(8));
			Time time= db2.getTime(9);
			int id_estado= db2.getInt(10);
			ComprobanteCPTO comprobante= new ComprobanteCPTO();
			comprobante.setProceso("CP");
			comprobante.setTipoNomina(tipoNomina);
			comprobante.setCodigoBarra(codigoBarra);
			comprobante.setFolioIngreso(folioIngreso);
			comprobante.setRutEmpresa(new Rut(rutEmpresa));
			comprobante.setConvenio(convenio);
			comprobante.setMonto(monto);
			comprobante.setN_trabajadores(n_trabajadores);
			comprobante.setFecha(pagado);
			comprobante.setTime(time.toString());
			comprobante.setId_estado(id_estado);
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj, int cierre) {
		int ndetalle=0;
		try {
			Set codigo_barras= (TreeSet) obj;
			for (Iterator iter = codigo_barras.iterator(); iter.hasNext();) {
				Long id_codigo_barra = (Long) iter.next();
				String query= "update COMPROBANTE_PAGO SET CIERRE= ? WHERE ID_CODIGO_BARRA= ?";
				logger.finest("Query=" + query);
				db2.prepareQuery(query);
				db2.setStatement(1, cierre);
				db2.setStatement(2, id_codigo_barra.longValue());
				ndetalle+= db2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		return ndetalle;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#updateCierre(java.lang.Object)
	 */
	public int updateCierre(int cierre_new, int cierre_old) throws SQLException {
		int ndetalle=0;
		String query= "update COMPROBANTE_PAGO SET CIERRE= ? WHERE CIERRE= ?";
		logger.finest("Query=" + query);
		db2.prepareQuery(query);
		db2.setStatement(1, cierre_new);
		db2.setStatement(2, cierre_old);
		ndetalle= db2.executeUpdate();		
		return ndetalle;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}
	

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	
}
