/**
 * 
 */
package com.microsystem.lme.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import com.ibm.jvm.io.FileInputStream;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.LMEInfLiquid;
import conector.lme.ws.cliente.operador.LMEInfLiquidResponse;
import conector.lme.ws.cliente.operador.WsLMEInetSOAPStub;
import conector.lme.ws.cliente.operador.WsLMEInet_PortType;
import conector.lme.ws.cliente.operador.WsLMEInet_ServiceLocator;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.utils.ConstantesTimeOut;

/**
 * @author usist199
 *
 */
public class testLiqui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testLiqui test= new testLiqui();
		testLiqui.LiquidarLicencia();

	}
	
	protected static void LiquidarLicencia(){
		String line, xml="";
		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:/LME/datos.txt"));
			while ((line = bf.readLine())!=null) {
				   System.out.println(line);
				   xml+=line;
			}
			bf.close();
			String nombreServicio="LIQUIDACION";
			int idLicencia=1639940;
			String dvLicencia="K";
			String url= "http://10.11.87.27:8080/LME/IMInfLiquid";
			int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().LIQUIDACION);
			
			 byte[] datosLiquidacion = xml.getBytes();
			 
			 String salida = Base64.encode(datosLiquidacion);
			 System.out.println("encodedBytes:" + salida);
			/* 
			 
			 Calendar fecProceso = Calendar.getInstance();
			 fecProceso.setTime(new Date());
			
			 String parametros = "idLicencia: " + idLicencia + " dvLicencia: " + dvLicencia + " fechaProceso: "
			 + new Date() + " zonaD: " + datosLiquidacion;
			 
			 WsLMEInet_PortType cliente = obtenerCliente("LMEInfLiquid", parametros, timeOut, url);
			 
			  LMEInfLiquid LMEInfLiquid = new LMEInfLiquid("3", "C", "10105", "petrohue",
			          biginteger(idLicencia), dvLicencia, fecProceso, biginteger(1), datosLiquidacion);
			  LMEInfLiquidResponse respuesta = cliente.LMEInfLiquid(LMEInfLiquid);
			  if (respuesta == null || respuesta.getEstado() == null) {
			      throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
			              "Servicio no entrega respuesta.");
			  }
			  */
			  
		}catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	protected static BigInteger biginteger(long l) {
        return BigInteger.valueOf(l);
    }
	
	protected static WsLMEInet_PortType obtenerCliente(String nombreServicio, String parametros, int timeOut, String url)
    throws ConfiguracionException, ErrorInvocacionOperadorException {
try {
    WsLMEInet_ServiceLocator lmeLocator = new WsLMEInet_ServiceLocator();
    // workaround: deshabilitamos multirefs :/
    lmeLocator.getEngine().setOption(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
    //URL urlServicio = getConfig().getURL(URL_SERVICIO);
    URL urlServicio = null;
	try {
		urlServicio = new URL(url);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
    WsLMEInet_PortType cliente = lmeLocator.getWsLMEInetSOAP(urlServicio);
    WsLMEInetSOAPStub stub = (WsLMEInetSOAPStub) cliente;
    stub.setTimeout(timeOut);
    return cliente;
} catch (ServiceException e) {
    throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
}
}
}
