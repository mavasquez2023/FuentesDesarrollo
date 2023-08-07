/**
 * 
 */
package cl.araucana.wsrest.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;


import cl.araucana.wsatento.business.service.LicenciaRestService;
import cl.araucana.wsatento.business.service.SeguridadService;
import cl.araucana.wsatento.business.service.impl.LicenciaRestServiceImpl;
import cl.araucana.wsatento.business.service.impl.SeguridadServiceImpl;
import cl.araucana.wsatento.business.to.LicenciaRestTO;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;
import cl.araucana.wsatento.integration.jaxrpc.bean.Licencia;
import cl.araucana.wsatento.integration.jaxrpc.ws.WSLicencia;
import cl.araucana.wsrest.vo.LicenciaVO;
import cl.araucana.wsrest.vo.RequestVO;
import cl.araucana.wsrest.vo.ResponseVO;



@Path("/getLicencias")
public class LicenciasRest {
	private Logger logger = Logger.getLogger(LicenciasRest.class);
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public ResponseVO getLicencias(RequestVO credenciales) {
		List<LicenciaVO> licenciasList=new ArrayList<LicenciaVO>();
		ResponseVO respuesta= new ResponseVO();
		try {
			String usuario= credenciales.getUsuario();
			String clave=credenciales.getClave();
			String rut= credenciales.getRut();
			
			//Validando Parámetros
			SeguridadService seguridad = new SeguridadServiceImpl();
	        seguridad.login(usuario, clave, "WSLICENCIA");
	        if(rut == null || rut.trim().equals(""))
	            throw new WSAtentoException("0201", "Rut en blanco.");
	        if(!RutUtil.validaRut(rut.trim()))
	            throw new WSAtentoException("0231", "Rut invalido.");

	        //Invocando Servicio
			logger.info("Consultamdo licencias RUT:" + rut);
			LicenciaRestService service= new LicenciaRestServiceImpl();
			List<LicenciaRestTO> licencias= service.getLicenicas(rut);

			for (Iterator iterator = licencias.iterator(); iterator.hasNext();) {
				LicenciaRestTO licenciaRestTO = (LicenciaRestTO) iterator
						.next();
				if(licenciaRestTO.getCompin()==1){
					LicenciaVO licenciaVO= new LicenciaVO();
					licenciaVO.setCodigoSucursalPago(licenciaRestTO.getCodSucPago());
					licenciaVO.setCompin(licenciaRestTO.getCompin());
					licenciaVO.setDias(licenciaRestTO.getDias());
					logger.info("Fecha Desde: " + licenciaRestTO.getFechaDesde() );
					licenciaVO.setFechaDesde(licenciaRestTO.getFechaDesde());
					logger.info("Fecha Pago: " + licenciaRestTO.getFechaPago() );
					licenciaVO.setFechaPago(licenciaRestTO.getFechaPago());
					licenciaVO.setTipo(licenciaRestTO.getTipo());

					licenciasList.add(licenciaVO);
				}
			}
			
		
			if(licenciasList.size()>0){
				respuesta.setLicencias(licenciasList);
				respuesta.setCodigo("0000");
				respuesta.setMensaje("Licencias encontradas");
			}else{
				respuesta.setCodigo("0010");
				respuesta.setMensaje("No existen licencias asociadas al Rut");
				logger.info("No existen licencias asociadas al Rut:" + rut);
			}
		} catch (WSAtentoException e) {
			respuesta.setCodigo(e.getCodigo());
			respuesta.setMensaje(e.getMensaje());
			logger.error("mensaje:" + e.getMensaje());
		}
		
        return respuesta;
    }
	
	public static String date2String(Date date){
		return formatoSAP.format(date);
	}
}
