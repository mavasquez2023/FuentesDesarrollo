package cl.araucana.wslme.integration.jaxrpc.ws;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.EmpleadorService;
import cl.araucana.wslme.business.service.OperadorService;
import cl.araucana.wslme.business.service.ValidacionService;
import cl.araucana.wslme.business.service.impl.EmpleadorServiceImpl;
import cl.araucana.wslme.business.service.impl.OperadorServiceImpl;
import cl.araucana.wslme.business.service.impl.ValidacionServiceImpl;
import cl.araucana.wslme.business.to.Event;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.FechaUtil;
import cl.araucana.wslme.common.util.GenerarMail;
import cl.araucana.wslme.integration.ehcache.LmeCacheWrapper;

public class WSLMEValEmpCCAF {
	private Logger log = Logger.getLogger(WSLMEValEmpCCAF.class);

	public LMEValEmpCCAFResponse LMEValEmpCCAF(LMEValEmpCCAFRequest request) {
		
		Timestamp time = FechaUtil.getTimestamp(); 
		
		log.info("Iniciando WSLMEValEmpCCAF");
		LMEValEmpCCAFResponse resp = new LMEValEmpCCAFResponse();
		
		log.debug("Fecha y hora inicio de llamada" + time.toString());
		// Validaciones generales (no null, no blancos, no rut invalidos, no
		// ccaf invalido)
		log.info("Validando parametros de entrada.");
		ValidacionService valGeneral = new ValidacionServiceImpl();
		GenerarMail mail= new GenerarMail();
		try {
			valGeneral.validacionGeneral(request.getCodigoOperador(), request.getClaveOperador(),
					request.getCodigoCCAF(), request.getRutTrabajadorNum(), request.getRutTrabajadorDig());
		} catch (WSLMEException e1) {

			/*log.info("Registrando evento.");
			Timestamp timeEnd = new Timestamp(new Date().getTime());
			LmeCacheWrapper.getInstance().put(
					time.toString(),
					new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
							new Double(request.getRutTrabajadorNum().doubleValue()),
							request.getRutTrabajadorDig(), new Double(1), "1", null, timeEnd));
			*/
			log.info("Retornando Estado [1], Glosa Estado [1] y cero Empleadores.");
			resp.setEstado(new Short((short) 1));
			resp.setGloEstado("1");
			resp.setListaEmpleadores(new Empleador[0]);
//			clillo 3/3/16 se genera Mail
			mail.generarMail("Error al validar parámetros de entrada." + 
					"<BR>Código Operador: " + request.getCodigoOperador() +
					"<BR>Clave Operador: " + request.getClaveOperador() +
					"<BR>Código CCAF: " + request.getCodigoCCAF() +
					"<BR>Rut Trabajador: " + request.getRutTrabajadorNum() +
					"<BR>DV Rut Trabajador: " + request.getRutTrabajadorDig() +
					"<BR><BR>Mensaje:" + e1.getMensaje());
			return resp;
		}

		// Validacion de usuario y clave
		log.info("Autentificando al operador.");
		OperadorService opeServ = new OperadorServiceImpl();
		try {
			if (!opeServ.validaPermisoOperador(request.getCodigoOperador(), request.getClaveOperador())
					.booleanValue()) {
				/*log.info("Registrando evento.");
				Timestamp timeEnd = new Timestamp(new Date().getTime());
				LmeCacheWrapper.getInstance().put(
						time.toString(),
						new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
								new Double(request.getRutTrabajadorNum().doubleValue()),
								request.getRutTrabajadorDig(), new Double(1), "1", null, timeEnd));
				*/
				log.info("Retornando Estado [1], Glosa Estado [1] y cero Empleadores.");
				resp.setEstado(new Short((short) 1));
				resp.setGloEstado("1");
				resp.setListaEmpleadores(new Empleador[0]);
//				clillo 3/3/16 se genera Mail
				mail.generarMail("Error al validar usuario y clave." + 
						"<BR>Código Operador: " + request.getCodigoOperador() +
						"<BR>Clave Operador: " + request.getClaveOperador() +
						"<BR>Código CCAF: " + request.getCodigoCCAF() +
						"<BR>Rut Trabajador: " + request.getRutTrabajadorNum() +
						"<BR>DV Rut Trabajador: " + request.getRutTrabajadorDig() +
						"<BR><BR>Mensaje Error: Código o Clave Operador no válido.");
				return resp;
			}
		} catch (WSLMEException e) {
			/*log.info("Registrando evento.");
			Timestamp timeEnd = new Timestamp(new Date().getTime());
			LmeCacheWrapper.getInstance().put(
					time.toString(),
					new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
							new Double(request.getRutTrabajadorNum().doubleValue()),
							request.getRutTrabajadorDig(), new Double(1), "1", null, timeEnd));
			*/
			log.info("Retornando Estado [1], Glosa Estado [1] y cero Empleadores.");
			resp.setEstado(new Short((short) 1));
			resp.setGloEstado("1");
			resp.setListaEmpleadores(new Empleador[0]);
//			clillo 3/3/16 se genera Mail
			mail.generarMail("Error al validar usuario y clave." + 
					"<BR>Código Operador: " + request.getCodigoOperador() +
					"<BR>Clave Operador: " + request.getClaveOperador() +
					"<BR>Código CCAF: " + request.getCodigoCCAF() +
					"<BR>Rut Trabajador: " + request.getRutTrabajadorNum() +
					"<BR>DV Rut Trabajador: " + request.getRutTrabajadorDig() +
					"<BR><BR>Mensaje Error:" + e.getMensaje());
			return resp;
		}

		// Obtiene los empleadores de dicho rut
		log.info("Obteniendo los empleadores del rut + [" + request.getRutTrabajadorNum()
				+ "].");
		EmpleadorService servEmpleador = new EmpleadorServiceImpl();
		try {
			List listaEmpleadores = servEmpleador
					.getEmpleadoresByRutAfiliado(request.getRutTrabajadorNum());
			if (listaEmpleadores!=null && listaEmpleadores.size() > 0) {
				Empleador[] arrayEmpl = new Empleador[listaEmpleadores.size()];
				Iterator it = listaEmpleadores.iterator();
				for (int i = 0; it.hasNext(); i++) {
					Empleador empTemp = (Empleador) it.next();
					arrayEmpl[i] = empTemp;
				}
				/*log.info("Registrando evento.");
				Timestamp timeEnd = new Timestamp(new Date().getTime());
				LmeCacheWrapper.getInstance().put(
						time.toString(),
						new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
								new Double(request.getRutTrabajadorNum().doubleValue()),
								request.getRutTrabajadorDig(), new Double(0), "3", listaEmpleadores, timeEnd));
				*/
				log.info("Retornando Estado [0], Glosa Estado [3] y "
						+ listaEmpleadores.size() + " Empleadores.");
				resp.setEstado(new Short((short) 0));
				resp.setGloEstado("3");
				resp.setListaEmpleadores(arrayEmpl);
			} else {
				/*log.info("Registrando evento.");
				Timestamp timeEnd = new Timestamp(new Date().getTime());
				LmeCacheWrapper.getInstance().put(
						time.toString(),
						new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
								new Double(request.getRutTrabajadorNum().doubleValue()),
								request.getRutTrabajadorDig(), new Double(0), "2", null, timeEnd));
				*/
				log.info("Retornando Estado [0], Glosa Estado [2] y cero Empleadores.");
				resp.setEstado(new Short((short) 0));
				resp.setGloEstado("2");
				resp.setListaEmpleadores(new Empleador[0]);
			}

			return resp;
		} catch (WSLMEException e) {
			/*log.info("Registrando evento.");
			//clillo 8/2/16 para diferencia los errores por caida de los por parámetros, se asigan estado 0, 0 (solo en base de datos)
			Timestamp timeEnd = new Timestamp(new Date().getTime());
			LmeCacheWrapper.getInstance().put(
					time.toString(),
					new Event(time, request.getCodigoOperador(), request.getClaveOperador(), request.getCodigoCCAF(),
							new Double(request.getRutTrabajadorNum().doubleValue()),
							request.getRutTrabajadorDig(), new Double(0), "0", null, timeEnd));
			*/
			log.info("Retornando Estado [1], Glosa Estado [1] y cero Empleadores.");
			resp.setEstado(new Short((short) 1));
			resp.setGloEstado("1");
			resp.setListaEmpleadores(new Empleador[0]);
			//clillo 3/3/16 se genera Mail
			mail.generarMail("Error al obtener lista de empleadores." + 
					"<BR>Código Operador: " + request.getCodigoOperador() +
					"<BR>Clave Operador: " + request.getClaveOperador() +
					"<BR>Código CCAF: " + request.getCodigoCCAF() +
					"<BR>Rut Trabajador: " + request.getRutTrabajadorNum() +
					"<BR>DV Rut Trabajador: " + request.getRutTrabajadorDig() +
					"<BR><BR>Mensaje Error:" + e.getMensaje());
			return resp;
		}
	}
}
