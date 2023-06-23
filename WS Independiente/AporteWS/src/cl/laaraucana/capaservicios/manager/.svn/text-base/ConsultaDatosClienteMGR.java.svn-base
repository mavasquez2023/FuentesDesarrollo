package cl.laaraucana.capaservicios.manager;

import org.apache.log4j.Logger;
import cl.laaraucana.capaservicios.database.dao.DatosClienteDAO;
import cl.laaraucana.capaservicios.database.vo.DatosClienteVO;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.ConstantesWS;
import cl.laaraucana.capaservicios.util.RutUtil;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.util.ValidarUtil;
import cl.laaraucana.capaservicios.webservices.vo.Log;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActDatosClienteVO;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActualizarDatosClienteOut;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteIn;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteOut;

public class ConsultaDatosClienteMGR {
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Obtiene datos personales y de contactabilidad del cliente. 
	 * Permite especificar la lógica a utilizar para la obtención de datos
	 * 
	 * cod 1: Obtiene los datos del modelo corporativo
	 * cod 2: Si existen datos en el modelo CESP lo obtiene desde ahí, caso contrario, obtiene los
	 * datos del modelo corporativo.
	 * Origen de datos en DB2/AS400.
	 * @param entrada
	 * @return
	 */
	public ConsultaDatosClienteOut obtenerDatosCliente(ConsultaDatosClienteIn entrada){
		ConsultaDatosClienteOut salida = new ConsultaDatosClienteOut();
		Log log = null;
	
	if(!RutUtil.IsRutValido(entrada.getRutCliente())){
		log = new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese rut válido");
		salida.setLog(log);
		return salida;
	}
	
	try {
		// Consultar datos personales
		DatosClienteDAO dao = new DatosClienteDAO();
		DatosClienteVO datosVO = null;
		
		//Obtencion de datos desde modelo corporativo
		if(entrada.getCodEjecucion() == null){
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Código de ejecución no válido");
			salida.setLog(log);
			return salida;
		}
		
		if (entrada.getCodEjecucion().equals("1")) {
			datosVO = dao.getDatosClienteCorp(entrada.getRutCliente());
		} else if(entrada.getCodEjecucion().equals("2")){
			//lógica de obtención de datos para actualización
			datosVO = dao.getDatosCliente(entrada.getRutCliente());
			if(datosVO.getCliente() == null){
				datosVO = dao.getDatosClienteCorp(entrada.getRutCliente());
			}
		}else{
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Código de ejecución no válido");
			salida.setLog(log);
			return salida;
		}
		
		if (datosVO.getCliente() == null)
			log = new Log(Constantes.COD_RESPUESTA_VACIO, "Consulte con su empresa si no tiene activado el servicio de crédito especial");
		else{
				if (String.valueOf(datosVO.getCliente().getNroCelular()).length() !=7
						|| !ValidarUtil.isEmail(datosVO.getCliente().getEmail())) {
					logger.debug("::ConsultaDatosCliente: Nro de celular con largo distinto a 7 o email no valido.");
					log = new Log(ConstantesWS.DATOS_CLIENTE_COD_FALTAN_DATOS,
							"Faltan antecedentes necesarios para ingresar al sistema de Crédito Especial");
				}else{
					log = new Log(Constantes.COD_RESPUESTA_SUCCESS, "Datos obtenidos correctamente");
				}
		}

		salida.setDatosCliente(datosVO.getCliente());
		} catch (Exception e) {
			logger.error("::ConsultaDatosCliente: Error al obtener datos de cliente: ", e);
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Error al consultar datos de afiliado: " + e.getMessage());
		}
		salida.setLog(log);
		return salida;
	}
	
	/**
	 * Actualiza de contactabilidad del cliente en modelo de aplicación crédito especial.
	 * Si existe en la base de datos, actualiza el registro, si no, inserta el registro. 
	 * @param entrada
	 * @return
	 */
	public ActualizarDatosClienteOut actualizarDatosCliente(ActDatosClienteVO entrada){
		ActualizarDatosClienteOut salida = new ActualizarDatosClienteOut();
		Log log = validarActDatos(entrada);
		
		//Validar datos de entrada
		if(log != null){
			salida.setLog(log);
			return salida;
		}
		
		//Ejecución de actualización de datos
		DatosClienteDAO dao = new DatosClienteDAO();
		DatosClienteVO datosVO = new DatosClienteVO();
		
		try {
			DatosClienteVO datosCorp = new DatosClienteVO();
			datosCorp = dao.getDatosClienteCorp(entrada.getRutCliente());
			
			if (datosCorp.getCliente() == null){
				salida.setActualizado(false);
				salida.setLog(new Log(Constantes.COD_RESPUESTA_VACIO, "Rut de cliente no encontrado"));
				return salida;
			}
			
			//Mapear datos nomodificables modelo corporativo desde tabla y datos modificables desde servicio
			datosCorp.getCliente().setDireccion(entrada.getDireccion());
			datosCorp.getCliente().setNroDpto(entrada.getNroDpto());
			datosCorp.getCliente().setCodComuna(entrada.getCodComuna());
			
			datosCorp.getCliente().setCodAreaTelFijo(entrada.getCodAreaTelFijo());
			datosCorp.getCliente().setTelFijo(entrada.getTelFijo());
			datosCorp.getCliente().setPrefijoCelular(entrada.getPrefijoCelular());
			datosCorp.getCliente().setNroCelular(entrada.getNroCelular());
			
			//Cambiar formato de fecha a AS400
			datosCorp.getCliente().setFechaNac(Utils.pasaFechaWEBaAs400(datosCorp.getCliente().getFechaNac()));
			
			//Verificar si el rut existe
			datosVO.setRutAfi(RutUtil.getLongRut(entrada.getRutCliente()));
			datosVO.setDvRutAfi(RutUtil.getDv(entrada.getRutCliente()));
			datosVO = dao.getDatosCliente(entrada.getRutCliente());
			
			if(datosVO.getCliente() == null){
				//Si no existe, insertar el registro
				datosVO.setCliente(datosCorp.getCliente());
				dao.ingresarDatosCliente(datosVO);
			}else{//Si existe, actualizar
				datosVO.setCliente(datosCorp.getCliente());
				dao.actualizarDatosCliente(datosVO);
			}
			salida.setActualizado(true);
			log = new Log(Constantes.COD_RESPUESTA_SUCCESS, "Datos actualizados correctamente");
		} catch (Exception e) {
			logger.error("::ConsultaDatosCliente: Error al actualizar datos de afiliado",e);
			log = new Log(Constantes.COD_RESPUESTA_ERROR, "Error al actualizar datos de afiliado: " + e.getMessage());
		}
		salida.setLog(log);
		return salida;
	}
	
	/**
	 * Valida datos de entrada del método actualizar datos del servicio
	 * @param entrada
	 * @return
	 */
	private Log validarActDatos(ActDatosClienteVO entrada){
		entrada.setTelFijo(entrada.getTelFijo().trim());
		
		if(!RutUtil.IsRutValido(entrada.getRutCliente()))
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese rut válido");
		
		if(!entrada.getCodComuna().matches("\\d{0,5}"))
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese cod. de comuna válido");
		
		if(entrada.getCodAreaTelFijo() == 0 || String.valueOf(entrada.getCodAreaTelFijo()).length()>10)
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese cod. de área válido");
		
		if(!ValidarUtil.isNumeric(entrada.getTelFijo()) || entrada.getTelFijo().length()>14)
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese teléfono fijo válido");
		
		if(!(entrada.getDireccion() != null && entrada.getDireccion().length()<41))
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese dirección válida");
		
		if(!(entrada.getNroDpto() != null && entrada.getNroDpto().length()<21))
			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese nro. depto. válido");
		
//		if(entrada.getPrefijoCelular() == 0 || String.valueOf(entrada.getPrefijoCelular()).length()>1)
//			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese prefijo celular válido");
//		
//		if(entrada.getNroCelular() == 0 || String.valueOf(entrada.getNroCelular()).length()>14)
//			return new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese nro. de celular válido");
		return null;
	}
	
	/**
	 * Mapea los datos de entrada para actualización de datos
	 * @param entrada
	 * @return
	 * @throws ParseException 

	private ClienteVO mapearActDatosCliente(ActDatosClienteVO entrada) throws ParseException{
		ClienteVO clienteVO = new ClienteVO();
		//clienteVO.setRutCliente(entrada.getRutCliente());
		//clienteVO.setSexo(entrada.getSexo().toUpperCase());
		//clienteVO.setEstadoCivil(entrada.getEstadoCivil().toUpperCase());
		
		clienteVO.setDireccion(entrada.getDireccion());
		clienteVO.setNroDpto(entrada.getNroDpto());
		clienteVO.setCodComuna(entrada.getCodComuna());
		
		clienteVO.setCodAreaTelFijo(entrada.getCodAreaTelFijo());
		clienteVO.setTelFijo(entrada.getTelFijo());
		clienteVO.setPrefijoCelular(entrada.getPrefijoCelular());
		clienteVO.setNroCelular(entrada.getNroCelular());
		
		//clienteVO.setEmail(entrada.getEmail());
		clienteVO.setFechaNac(Utils.pasaWebToAs400(entrada.getFechaNac()));//Convertir fecha a numeric(8)
		
		return clienteVO;
	}	
	
	 		if(!entrada.getSexo().matches("\\w{0,1}")){
			salida.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese sexo válido"));
			return salida;
		}
		
		if(!entrada.getEstadoCivil().matches("\\w{0,1}")){
			salida.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese est. civil válido"));
			return salida;
		}
		
		if(!entrada.getFechaNac().matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")){
			salida.setLog(new Log(Constantes.COD_RESPUESTA_ERROR, "Ingrese fec. nacimiento válida (dd-mm-yyyy)"));
			return salida;
		}
	 */
}
