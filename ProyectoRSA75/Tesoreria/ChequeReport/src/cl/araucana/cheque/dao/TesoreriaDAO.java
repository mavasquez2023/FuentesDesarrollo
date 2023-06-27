/*
 * Creado el 24-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cheque.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import cl.recursos.*;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TesoreriaDAO implements DAO_Interface{
	private ConectaDB2 db2teso;
	private static Logger log = Logger.getLogger(TesoreriaDAO.class);
	
	public TesoreriaDAO(ConectaDB2 db2) throws SQLException{
		this.db2teso= db2;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		Map resultado= new HashMap();
		Integer folio= (Integer)pk;
		StringBuffer query= new StringBuffer();
		query.append("select tea7a as codigobarra, te42a as rut, te43a as dvrut, te44a as identificacion, te45a as rut2, te46a as dvrut2, te47a as identificacion2, te7na as monto, a.sajkm92 as usercreate, cmca as oficina, sajkky1 as area, te49a as observacion ");
		query.append("from tedta.te07f1 a join cmdta.cm01f1 b " );
		query.append("on a.cmba= b.cmba ");
		query.append("join tedta.te02f1 c ");
		query.append("on a.teqa=c.teqa ");
		query.append("where a.te3wa= ? ");
		db2teso.prepareQuery(query.toString());
		log.debug("Query Cabecera Teso:" + query.toString());
		log.debug("Parametro:" + folio);
		db2teso.setStatement(1, folio.intValue());
		db2teso.executeQuery();
		if (db2teso.next()){
			String codigobarra= db2teso.getString("codigobarra");
			String identificacion= db2teso.getString("identificacion");
			String nombrecheque= identificacion;
			if(identificacion.length()>28){
				identificacion= identificacion.substring(0, 28);
			}
			int rut= (int)db2teso.getDouble("rut");
			String dvrut= db2teso.getString("dvrut");
			String identificacion2= db2teso.getString("identificacion2");
			if(identificacion2.length()>28){
				identificacion2= identificacion2.substring(0, 28);
			}
			int rut2= (int)db2teso.getDouble("rut2");
			String dvrut2= db2teso.getString("dvrut2");
			int monto= (int)db2teso.getDouble("monto");
			String usercreate= db2teso.getString("usercreate");
			String oficina= db2teso.getString("oficina");
			String area= db2teso.getString("area");
			String observacion= db2teso.getString("observacion");
			
			resultado.put("codigobarra", codigobarra);
			resultado.put("identificacion", identificacion);
			String rut1dv= Formato.numEntero(rut) + "-" + dvrut;
			resultado.put("rut", rut1dv);
			//resultado.put("dvrut", dvrut);
			resultado.put("identificacion2", identificacion2);
			String rut2dv="";
			if(rut2>0){
				rut2dv= Formato.numEntero(rut2) + "-" + dvrut2;
			}
			resultado.put("rut2", rut2dv);
			resultado.put("nombrecheque", nombrecheque);
			//resultado.put("dvrut2", dvrut2);
			resultado.put("monto", new Integer(monto));
			resultado.put("usercreate", usercreate);
			resultado.put("oficina", oficina);
			resultado.put("area", area);
			resultado.put("observacion", observacion);
			resultado.put("folio", new BigDecimal(String.valueOf(folio)));
			Map detalle= (HashMap)selectDetalle(folio.intValue());
			resultado.put("detalle", detalle.get("detalle"));
			resultado.put("pagos", detalle.get("pagos"));
		}
		return resultado;
	}

	public Object selectDetalle (int folio) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select (trim(a.te4ta) || case when b.te23a='.' then '' else  trim(b.te23a) end) as detalle, a.te4sa as montodetalle ");
		query.append("from tedta.te07f2 a ");
		query.append("join tedta.te04f1 b ");
		query.append("on a.te1ya=b.te1ya ");
		query.append("where te3wa=? ");
		log.debug("Query Detalle:" + query.toString());
		db2teso.prepareQuery(query.toString());
		db2teso.setStatement(1, folio);
		db2teso.executeQuery();
		StringBuffer detalle= new StringBuffer();
		StringBuffer pagos= new StringBuffer();
		int i=1;
		while (db2teso.next()){
			String texto= db2teso.getString("detalle");
			int monto= db2teso.getInt("montodetalle");
			if(i<=16){
				detalle.append(texto + "\n");
				if(monto==0){
					pagos.append("\n");
				}else{
					pagos.append(Formato.numEntero(monto) + "\n");
				}
			}
			i++;
		}
		Map resultado= new HashMap();
		resultado.put("detalle", detalle.toString());
		resultado.put("pagos", pagos.toString());
		return resultado;
	}
	
	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}


	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
