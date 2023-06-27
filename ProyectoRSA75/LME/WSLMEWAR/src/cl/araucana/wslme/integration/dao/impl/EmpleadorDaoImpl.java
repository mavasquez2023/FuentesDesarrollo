package cl.araucana.wslme.integration.dao.impl;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoRequest;
import com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponse;
import com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa;
import com.lautaro.xi.CRM.WEB_Mobile.SI_ConsultaEmpresaAfiliadoOutBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.SI_ConsultaEmpresaAfiliadoOutServiceLocator;

import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.common.util.RutUtil;
import cl.araucana.wslme.common.util.ValidaTiempoLlamadaUtil;
import cl.araucana.wslme.integration.dao.AbstractDao;
import cl.araucana.wslme.integration.dao.EmpleadorDao;
import cl.araucana.wslme.integration.jaxrpc.ws.Empleador;

public class EmpleadorDaoImpl extends AbstractDao implements EmpleadorDao {
	private Logger log = Logger.getLogger(EmpleadorDaoImpl.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	private final int tiempoEsperaMaximoDB2;
	private final int tiempoIteracionDB2;
	private final int tiempoEsperaMaximoSQL;
	private final int tiempoIteracionSQL;
	private final int tiempoEsperaMaximoCRM;
	
	public EmpleadorDaoImpl() throws WSLMEException {
		tiempoEsperaMaximoDB2= Integer.parseInt(ConfigUtil.getValorRecursosDeAplicacion("tiempo.EsperaMaximo.DB2"));
		tiempoIteracionDB2= Integer.parseInt(ConfigUtil.getValorRecursosDeAplicacion("tiempo.Iteracion.DB2"));
		tiempoEsperaMaximoSQL= Integer.parseInt(ConfigUtil.getValorRecursosDeAplicacion("tiempo.EsperaMaximo.SQL"));
		tiempoIteracionSQL= Integer.parseInt(ConfigUtil.getValorRecursosDeAplicacion("tiempo.Iteracion.SQL"));
		tiempoEsperaMaximoCRM= Integer.parseInt(ConfigUtil.getValorRecursosDeAplicacion("tiempo.EsperaMaximo.CRM"));
		log.info("");
	}

	public List getEmpleadoresAS400(Integer rut, Date fecIni, Date fecFin) throws Exception{
		openConnection();
		List listaEmpleadores = new ArrayList();
		String call = "{ call WSLME.GETEMP(?, ?, ?, ?) }";

			log.info("Conecction a DB2: " + !getConnection().isClosed());
			CallableStatement cstmt = getConnection().prepareCall(call);
			log.info("comando: " + call);
			cstmt.setInt(1, rut.intValue());
			cstmt.setInt(2, 1);
			cstmt.setString(3, sdf.format(fecIni));
			cstmt.setString(4, sdf.format(fecFin));
			log.info("Parámetros consulta Empleador en AS400, RUT:" + rut.intValue() + ", Fijo:1, Fecha inicio: " +  sdf.format(fecIni) + ", Fecha fin: " + sdf.format(fecFin));
			ValidaTiempoLlamadaUtil validator = new ValidaTiempoLlamadaUtil();
			log.info("Tiempo espera máximo:" + tiempoEsperaMaximoDB2);
			validator.validarTiempoLlamada(tiempoEsperaMaximoDB2, tiempoIteracionDB2, cstmt);
			ResultSet rsEmpleadores = ejecutarQueryEmpleadores(cstmt, validator);
			if(rsEmpleadores!= null){
				while(rsEmpleadores.next()){
					Empleador objTemp = new Empleador();

					objTemp.setRutEmpleador(RutUtil.rutWSFormat(rsEmpleadores.getInt(1) + "-" + ((rsEmpleadores.getString(2)!=null)?rsEmpleadores.getString(2):""))); // Rut completo empresa
					objTemp.setNomRazSoc((rsEmpleadores.getString(3)!=null)?rsEmpleadores.getString(3):""); //Razon social empresa
					Integer mesAnoUltCot = new Integer(Integer.parseInt(rsEmpleadores.getString(4)));
					objTemp.setUltimaCotizacion(mesAnoUltCot); // Mes y año ultima cotizacion
					listaEmpleadores.add(objTemp);
				}
				log.info("Se obtubieron " + listaEmpleadores.size() + " empleadores activos para el rut [" + rut + "]");
			}else{
				log.info("Respuesta null");
				listaEmpleadores=null;
			}
		
		
		closeConnection();
		return listaEmpleadores;
	}
	
public ResultSet ejecutarQueryEmpleadores(CallableStatement cstmt, ValidaTiempoLlamadaUtil validator){
	ResultSet rsEmpleadores = null;
	try{
		//Thread.sleep(5000);
		log.info("Ejecutando consulta en DB2");
		rsEmpleadores = cstmt.executeQuery();
		validator.llamadaEjecutada = true;
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	return rsEmpleadores;
}
	
public List getEmpleadoresSQLServer(Integer rut, Date fecIni, Date fecFin) throws Exception{
		openConnectionSQLServer();
		log.debug("Consultando Empleadores en SQLServer.");
		List listaEmpleadores = new ArrayList();
		StringBuffer query=new StringBuffer();
		query.append("SELECT T1.CMNA, T3.CMOA, T3.CMPA, CAST((YEAR(T4.CM17A) * 100 + MONTH(T4.CM17A)) AS VARCHAR(6)) AS CM17A ");
		query.append("FROM dbo.af03lm T1 ");
		query.append("LEFT JOIN dbo.af02lm T2 ON T1.SE5FAJC = T2.SE5FAJC ");
		query.append("LEFT JOIN dbo.cm02lm T3 ON T1.CMNA = T3.CMNA ");
		query.append("INNER JOIN ");
		query.append("(SELECT MAX(TAB1.CM17A) AS CM17A, TAB1.CMBA, TAB1.CMNA, TAB1.CM13A ");
		query.append("FROM dbo.cm03lm TAB1 ");
		query.append("INNER JOIN dbo.af03lm TAB2 ");
		query.append("ON (TAB1.CMBA = TAB2.CMBA ");
		query.append("AND TAB1.CMNA = TAB2.CMNA ");
		query.append("AND TAB1.CM13A = TAB2.CM13A) ");
		query.append("WHERE TAB2.SE5FAJC = ? ");
		query.append("GROUP BY TAB1.CMBA, TAB1.CMNA, TAB1.CM13A) T4 ");
		query.append("ON (T1.CMBA = T4.CMBA AND T1.CMNA = T4.CMNA AND T1.CM13A = T4.CM13A) ");
		query.append("WHERE T1.SE5FAJC = ? ");
		query.append("AND T1.SE5FAR9 = 'A' ");
		query.append("AND T4.CM17A IS NOT NULL ");
		
		query.append("AND CONVERT(char(10), cm17a, 112) BETWEEN ? AND ? ");

		query.append("ORDER BY CM17A DESC ");
		
		log.debug("Query SQLServer:" + query.toString());


		//Prepared Statement SQLServer
		log.info("Conecction a SQLServer: " + !getConnection2().isClosed());
		PreparedStatement prestmt = getConnection2().prepareStatement(query.toString());
		prestmt.setInt(1, rut.intValue());
		prestmt.setInt(2, rut.intValue());
		prestmt.setString(3, sdf2.format(fecIni));
		prestmt.setString(4, sdf2.format(fecFin));
		
		log.info("Parámetros consulta Empleador en SQLServer, RUT: " + rut.intValue() + ", Fecha inicio: " + sdf2.format(fecIni) + ", Fecha fin: " + sdf2.format(fecFin));
		
		ValidaTiempoLlamadaUtil validator = new ValidaTiempoLlamadaUtil();
		validator.validarTiempoLlamada(tiempoEsperaMaximoSQL, tiempoIteracionSQL, prestmt);
		
		ResultSet rsEmpleadores = ejecutarQueryEmpleadores(prestmt, validator);
		if(rsEmpleadores!= null){
			while(rsEmpleadores.next()){
				Empleador objTemp = new Empleador();

				objTemp.setRutEmpleador(RutUtil.rutWSFormat(rsEmpleadores.getInt(1) + "-" + ((rsEmpleadores.getString(2)!=null)?rsEmpleadores.getString(2):""))); // Rut completo empresa
				objTemp.setNomRazSoc((rsEmpleadores.getString(3)!=null)?rsEmpleadores.getString(3):""); //Razon social empresa
				Integer mesAnoUltCot = new Integer(Integer.parseInt(rsEmpleadores.getString(4)));
				objTemp.setUltimaCotizacion(mesAnoUltCot); // Mes y año ultima cotizacion
				log.debug("Empresa: " + objTemp.getRutEmpleador() + ", " +  objTemp.getNomRazSoc());
				listaEmpleadores.add(objTemp);
			}
			log.info("SQLS, se obtubieron " + listaEmpleadores.size() + " empleadores para el rut [" + rut + "]");
		}else{
			listaEmpleadores=null;
		}
		
		closeConnection();
		return listaEmpleadores;
	}

public ResultSet ejecutarQueryEmpleadores(PreparedStatement cstmt, ValidaTiempoLlamadaUtil validator){
	ResultSet rsEmpleadores = null;
	try{
		//Thread.sleep(5000);
		log.info("Ejecutando query SQL Server");
		rsEmpleadores = cstmt.executeQuery();
		validator.llamadaEjecutada = true;
	}catch(Exception e){
		e.printStackTrace();
		log.warn("Error al ejecutar query o expiró tiempo, mensaje: " + e.getMessage());
		return null;
	}
	return rsEmpleadores;
}

public List getEmpleadoresCRM(Integer rut, String endpoint, String username, String password) throws WSLMEException, Exception {
	List listaEmpleadores = new ArrayList();
	DT_ConsultaEmpresaAfiliadoRequest reqCRM= new DT_ConsultaEmpresaAfiliadoRequest(String.valueOf(rut));
	SI_ConsultaEmpresaAfiliadoOutServiceLocator servCRM= new SI_ConsultaEmpresaAfiliadoOutServiceLocator();
	if(endpoint==null || endpoint.equals("")){
		endpoint= servCRM.getHTTP_PortAddress();
	}
	log.info("Consultando en CRM, Endpoint: " + endpoint);
	URL url= new URL(endpoint);
	SI_ConsultaEmpresaAfiliadoOutBindingStub bindingCRM= new SI_ConsultaEmpresaAfiliadoOutBindingStub(url, servCRM);
	//Call call= (Call) servCRM.createCall();
	bindingCRM.setUsername(username);
	bindingCRM.setPassword(password);
	bindingCRM.setTimeout(tiempoEsperaMaximoCRM);

	DT_ConsultaEmpresaAfiliadoResponse respCRM=  bindingCRM.SI_ConsultaEmpresaAfiliadoOut(reqCRM);
	int respuesta= respCRM.getRespuesta().intValue();
	log.info("Estado (1:OK): " + respCRM.getRespuesta());
	if( respuesta== 1){
		DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa[] detalle= respCRM.getDetalle_Empresa();
		log.info("CRM, se obtubieron " + detalle.length + " empleadores para el rut [" + rut + "]");
		if(detalle !=null){
			for (int i = 0; i < detalle.length; i++) {
				Empleador objTemp = new Empleador();
				objTemp.setRutEmpleador(detalle[i].getRut_Empresa());
				objTemp.setNomRazSoc(detalle[i].getRazon_Social());
				String fechaUltCot= detalle[i].getFecha_Ultima_Cotiza();
				if(fechaUltCot==null || fechaUltCot.equals("")){
					fechaUltCot="0";
				}else{
					fechaUltCot= fechaUltCot.substring(0, 6);
				}
				objTemp.setUltimaCotizacion(new Integer(Integer.parseInt(fechaUltCot)));
				listaEmpleadores.add(objTemp);
				log.debug("Empresa: " + detalle[i].getRut_Empresa() + ", " +  detalle[i].getRazon_Social());
			}
		}
	}
	return listaEmpleadores;
}

/*public static void main(String[] args) throws WSLMEException {
	EmpleadorDaoImpl empcrm= new EmpleadorDaoImpl();
	String endpoint= "http://ARAPRCIPIP.laaraucana.local:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=WEB_Mobile&receiverParty=&receiverService=&interface=SI_ConsultaEmpresaAfiliadoOut&interfaceNamespace=http://lautaro.com/xi/CRM/WEB-Mobile";
	String username="PISUPER";
	String password="Config2005";
	try {
		empcrm.getEmpleadoresCRM(new Integer(1010492), endpoint, username, password);
	} catch (WSLMEException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/

}
