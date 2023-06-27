/*
 * Creado el 24-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cheque.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import cl.araucana.cheque.to.EstadisticasTO;
import cl.araucana.cheque.to.ParamURL;
import cl.recursos.*;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EstadisticasDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger log = Logger.getLogger(EstadisticasDAO.class);
	
	public EstadisticasDAO(ConectaDB2 db2) throws SQLException{
		this.db2= db2;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		return null;
	}
	
//	Query para obtener datos por ID
	public Object selectID(int id) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select  folio, cola, dispositivo, usuario ");
		query.append("from pubdta.bitcheque " );
		query.append("where id= ? " );

		db2.prepareQuery(query.toString());
		log.info("Query:" + query.toString());
		db2.setStatement(1, id);
		db2.executeQuery();
		ParamURL param=null;
		if (db2.next()){
			param= new ParamURL();
			int folio= db2.getInt("folio");
			String cola= db2.getString("cola");
			String dispositivo= db2.getString("dispositivo");
			String usuario= db2.getString("usuario");
			param.setFolio(folio);
			param.setCola(cola);
			param.setDispositivo(dispositivo);
			param.setCajero(usuario);
		}
		return param;
	}
	
	//Query para pantalla inicial
	public Object selectCountbyMes() throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select  YEAR(fecha_creacion)|| RIGHT('0'||MONTH(fecha_creacion), 2) as periodo, estado, count(*) as cantidad ");
		query.append("from pubdta.bitcheque " );
		query.append("group by YEAR(fecha_creacion)|| RIGHT('0'||MONTH(fecha_creacion), 2), estado ");
		query.append("order by 1 desc ");
		query.append("fetch first 12 rows only ");

		db2.prepareQuery(query.toString());
		log.debug("Query:" + query.toString());
		//db2.setStatement(1, meses);
		db2.executeQuery();
		List resultado= new ArrayList();
		while (db2.next()){
			EstadisticasTO estadisticas= new EstadisticasTO();
			int mes= db2.getInt("periodo");
			int cantidad= db2.getInt("cantidad");
			String estado= db2.getString("estado");
			estadisticas.setMes(mes);
			estadisticas.setCantidad(cantidad);
			estadisticas.setEstado(estado);
			resultado.add(estadisticas);
		}
		return resultado;
	}
	
	//Query para todas las consultas por mes, fecha u oficina
	public Object selectCount(EstadisticasTO param, String filtro, String order) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select " + filtro + " as filtro, count(*) as cantidad ");
		query.append("from pubdta.bitcheque " );
		query.append("where  estado= ? ");
		if(param.getMes()!=0){
			query.append("and YEAR(fecha_creacion)||RIGHT('0'||MONTH(fecha_creacion), 2)= ? " );
		}
		if(param.getFecha()!=null){
			query.append("and  fechacrea = '" + param.getFecha() + "' ");
		}
		if(param.getOficina()!=null){
			query.append("and oficina= ? " );
		}
		query.append("group by " + filtro + ", estado ");
		if(filtro.equals("oficina") || filtro .equals("usuario")){
			query.append("order by cantidad desc " );
		}else{
			query.append("order by 1 "  + order );
		}

		db2.prepareQuery(query.toString());
		log.debug("Query:" + query.toString());
		db2.setStatement(1, param.getEstado());
		int index=1;
		db2.setStatement(index, param.getEstado());
		index++;
		if(param.getMes()!=0){
			db2.setStatement(index, param.getMes());
			index++;
		}
		if(param.getOficina()!=null){
			db2.setStatement(index, param.getOficina());
			index++;
		}
		db2.executeQuery();
		List resultado= new ArrayList();
		while (db2.next()){
			EstadisticasTO estadisticas= new EstadisticasTO();
			int cantidad= db2.getInt("cantidad");
			estadisticas.setCantidad(cantidad);
			if(filtro.equals("fecha_creacion")){
				String fecha= String.valueOf(db2.getDate("filtro"));
				estadisticas.setFecha(fecha);
			}else{
				estadisticas.setFecha(param.getFecha());
			}
			if(filtro.equals("oficina")){
				String oficina= db2.getString("filtro");
				estadisticas.setOficina(oficina);
			}else{
				estadisticas.setOficina(param.getOficina());
			}
			if(filtro.equals("usuario")){
				String usuario= db2.getString("filtro");
				estadisticas.setUsuario(usuario);
			}else{
				estadisticas.setUsuario(param.getUsuario());
			}
			estadisticas.setEstado(param.getEstado());
			estadisticas.setMes(param.getMes());
			resultado.add(estadisticas);
		}
		return resultado;
	}
	
//	Query para obtener detalle de cheuqes según filtro
	public Object selectDetalleCheques(EstadisticasTO param) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select id, area, oficina, usuario, folio, monto, estado, cola, dispositivo, mensaje, ipremota, username, fecha_creacion, hora_creacion, fecha_modificacion, hora_modificacion ");
		query.append("from pubdta.bitcheque " );
		if(param.getFolio()!=0){
			query.append("where  folio = ? ");
		}else{
			query.append("where  estado= ? ");
		}
		if(param.getMes()!=0){
			query.append("and YEAR(fecha_creacion)||RIGHT('0'||MONTH(fecha_creacion), 2)= ? " );
		}
		if(param.getFecha()!=null){
			query.append("and  fechacrea = '" + param.getFecha() + "' ");
		}
		if(param.getOficina()!=null){
			query.append("and oficina= ? " );
		}
		query.append("order by fecha_creacion desc,  hora_creacion desc "  );

		db2.prepareQuery(query.toString());
		log.debug("Query:" + query.toString());
		int index=1;
		if(param.getFolio()!=0){
			db2.setStatement(index, param.getFolio());
		}else{
			db2.setStatement(index, param.getEstado());
		}
		index++;
		if(param.getMes()!=0){
			db2.setStatement(index, param.getMes());
			index++;
		}
		if(param.getOficina()!=null){
			db2.setStatement(index, param.getOficina());
			index++;
		}
		db2.executeQuery();
		List resultado= new ArrayList();
		while (db2.next()){
			EstadisticasTO estadisticas= new EstadisticasTO();
			int id= db2.getInt("id");
			String area= db2.getString("area");
			String oficina= db2.getString("oficina");
			String usuario= db2.getString("usuario");
			int folio= db2.getInt("folio");
			int monto= db2.getInt("monto");
			String estado= db2.getString("estado");
			String cola= db2.getString("cola");
			String dispositivo= db2.getString("dispositivo");
			String username= db2.getString("username");
			String ipremota= db2.getString("ipremota");
			String mensaje= db2.getString("mensaje");
			String fecha= String.valueOf(db2.getDate("fecha_creacion"));
			String hora= String.valueOf(db2.getTime("hora_creacion"));
			String fechamod= String.valueOf(db2.getDate("fecha_modificacion"));
			String horamod= String.valueOf(db2.getTime("hora_modificacion"));
			estadisticas.setId(id);
			estadisticas.setArea(area);
			estadisticas.setOficina(oficina);
			estadisticas.setUsuario(usuario);
			estadisticas.setFolio(folio);
			estadisticas.setMonto(monto);
			estadisticas.setEstado(estado);
			estadisticas.setCola(cola);
			estadisticas.setDispositivo(dispositivo);
			estadisticas.setIpremota(ipremota);
			estadisticas.setUsername(username);
			estadisticas.setMensaje(mensaje);
			estadisticas.setFecha(fecha);
			estadisticas.setHora(hora);
			estadisticas.setFechamod(fechamod);
			estadisticas.setHoramod(horamod);
			resultado.add(estadisticas);
		}
		return resultado;
	}
	
	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		Map param= (HashMap)obj;
		StringBuffer query= new StringBuffer();
		query.append("insert  into pubdta.bitcheque ");
		query.append("( AREA, OFICINA, USUARIO, FOLIO, MONTO, ESTADO, COLA, DISPOSITIVO, IPREMOTA, USERNAME ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
		db2.prepareQuery(query.toString());
		log.debug("Query:" + query.toString());
		db2.setStatement(1, param.get("area").toString());
		db2.setStatement(2, param.get("oficina").toString());
		db2.setStatement(3, param.get("usuario").toString());
		int folio=((BigDecimal)param.get("folio")).intValue();
		db2.setStatement(4, folio);
		int monto=((Integer)param.get("monto")).intValue();
		db2.setStatement(5, monto);
		db2.setStatement(6, "0");
		db2.setStatement(7, param.get("cola").toString());
		db2.setStatement(8, param.get("dispositivo").toString());
		db2.setStatement(9, param.get("ipremota").toString());
		db2.setStatement(10, param.get("username").toString());
		return db2.executeUpdate();
	}


	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	public int update(int folio, String estado, String mensaje) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("update  pubdta.bitcheque ");
		query.append("set ESTADO= ? , FECHAMOD= current_date, HORAMOD= current_time, MENSAJE= ?");
		query.append("where FOLIO = ? " );
		query.append("and  ID = (select max(id) from pubdta.bitcheque where folio= ?) " );
		db2.prepareQuery(query.toString());
		log.debug("Query:" + query.toString());
		db2.setStatement(1, estado);
		db2.setStatement(2, mensaje);
		db2.setStatement(3, folio);
		db2.setStatement(4, folio);
		return db2.executeUpdate();
	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
