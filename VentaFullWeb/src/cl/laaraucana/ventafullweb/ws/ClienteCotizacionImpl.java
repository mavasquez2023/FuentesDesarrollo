package cl.laaraucana.ventafullweb.ws;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lautaro.xi.BS.WEB_Mobile.DT_Cotizacion_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_Cotizacion_RES;
import com.lautaro.xi.BS.WEB_Mobile.SI_Cotizacion_OUTBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;

public class ClienteCotizacionImpl implements ClienteCotizacion {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public int getRespuestaCotizacion() {
		String ep = Configuraciones.getConfig("ep.WSCotizacion");
		String user = Configuraciones.getConfig("servicios.sap.username");
		String pass = Configuraciones.getConfig("servicios.sap.pass");
		
		SimpleDateFormat formatearFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActual = new Date();
		String fechaFormateada = formatearFecha.format(fechaActual);		
		
		String tipoCotizacion = Configuraciones.getConfig("cotizacion.uf");
		
		Double UF_Actual;
		int UF_Formateada = 0;
		
		try {
			SI_Cotizacion_OUTBindingStub stub = new SI_Cotizacion_OUTBindingStub();
			stub._setProperty(SI_Cotizacion_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			stub._setProperty(SI_Cotizacion_OUTBindingStub.USERNAME_PROPERTY, user);
			stub._setProperty(SI_Cotizacion_OUTBindingStub.PASSWORD_PROPERTY, pass);
			DT_Cotizacion_REQ req = new DT_Cotizacion_REQ();
			req.setMONEDA(tipoCotizacion);
			req.setFECHA(fechaFormateada);
			
			DT_Cotizacion_RES salida = stub.SI_Cotizacion_OUT(req);
			
			if(salida == null ) {
				logger.error("Error al ejecutar WS WSCotizaciones. Respuesta null");
			} else {
				logger.info("Se ha ejecutado correctamente el WSCotizaciones. Respuesta: " + salida.getCOTIZACION());
			}
			
			UF_Actual = Double.parseDouble(salida.getCOTIZACION());
			UF_Formateada = (int) Math.round(UF_Actual);
			
		} catch(Exception e) {
			logger.error("Error al ejecutar WSCotizaciones. " + e);
			
		}
		
		return UF_Formateada;
	}
}
