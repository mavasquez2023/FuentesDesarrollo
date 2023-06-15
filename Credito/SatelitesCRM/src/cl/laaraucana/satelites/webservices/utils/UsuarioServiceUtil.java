package cl.laaraucana.satelites.webservices.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.ServicioListaConsultaDatosAfiliacion;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaListaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.ServicioQueryBPStatus;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;

public class UsuarioServiceUtil {

	/**
	 * 
	 * @param rut
	 * @return UsuarioAfiliadoVO con los datos de Sap y As400
	 * @throws ParseException
	 */
	public static UsuarioAfiliadoVO obtenerAfiliado(String rut) throws ParseException {
		UsuarioAfiliadoVO afiliado = new UsuarioAfiliadoVO();
		List<DetalleEmpresaAfiliado> detalleEmpresaList = new ArrayList<DetalleEmpresaAfiliado>();
		DetalleEmpresaAfiliado detalleEmpresa = null;
		try {

			// se llaman a los servicios enviado por parametro el rut
			SalidaListaConsultaDatosAfiliacionAs400 salidaAs400 = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400(rut);
			SalidaListaAfiliadoVO salidaSAP = ServicioQueryBPStatus.obtenerAfiliadoByRutSap(rut);
			//se valida que los dos servicios traigan datos.
			
			if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
				afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				afiliado.setMensaje(salidaSAP.getMensaje());
				return afiliado;
			}else if(salidaAs400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
				afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				afiliado.setMensaje(salidaAs400.getMensaje());
				return afiliado;
			} else if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO) && salidaAs400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)) {
				afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				afiliado.setMensaje("El usuario no ha sido encontrado");
				return afiliado;
			}else if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS) && salidaAs400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				ArrayList<SalidaAfiliadoVO> listaSap = salidaSAP.getListaAfiliado();
				List<SalidaConsultaDatosAfiliacionAs400> listaAs400 = salidaAs400.getDetalleEmpresa();
				List<SalidaConsultaDatosAfiliacionAs400> listaAs400Existe = new ArrayList<SalidaConsultaDatosAfiliacionAs400>();

				//se recorre el listado que viene desde sap, si existe en sap y as400 se agrega a la lista y se crea una lista de objetos de as400 ya ingresados, 
				//si al terminar la lista no existia en as400 se agrega a la lista el detalle de sap, luego se eliminan de la lista de as400 todas las agregadas
				// y se agregan las que no habian sido agregadas.
				for (SalidaAfiliadoVO detSap : listaSap) {
					String fechaFiniquitoSapWeb = Utils.pasaFechaSAPaWEB(detSap.getFechaAfiliacion());
					String rutEmpresaSap = CompletaUtil.llenaConCeros(Utils.obtenerValorAnteriorA(detSap.getRutEmpresa(), "-"), 9, true);
					boolean existe = false;
					for (SalidaConsultaDatosAfiliacionAs400 detAs400 : listaAs400) {
						String fechaFiniquitoAs400Web = Utils.pasaFechaASaWEB(detAs400.getFechaAfiliacion());

						if ((fechaFiniquitoAs400Web != null && fechaFiniquitoAs400Web.equals(fechaFiniquitoSapWeb)) && (rutEmpresaSap != null && rutEmpresaSap.equals(detAs400.getRutEmpresa()))) {
							existe = true;
							afiliado.setNombreAfiliado(detSap.getNombreCompleto());
							detalleEmpresa = new DetalleEmpresaAfiliado();
							detalleEmpresa.setNombreEmpresa(detSap.getRazonSocial());
							detalleEmpresa.setRutEmpresa(detSap.getRutEmpresa());
							detalleEmpresa.setFechaAfiliacion(fechaFiniquitoSapWeb);
							detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoSap(detSap.getRol()));
							detalleEmpresa.setEstadoAfiliacion(detSap.getEstadoAfiliacion());
							detalleEmpresaList.add(detalleEmpresa);
							listaAs400Existe.add(detAs400);
							break;
						}

					}
					if (!existe) {
						afiliado.setNombreAfiliado(detSap.getNombreCompleto());
						detalleEmpresa = new DetalleEmpresaAfiliado();
						detalleEmpresa.setNombreEmpresa(detSap.getRazonSocial());
						detalleEmpresa.setRutEmpresa(detSap.getRutEmpresa());
						detalleEmpresa.setFechaAfiliacion(fechaFiniquitoSapWeb);
						detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoSap(detSap.getRol()));
						detalleEmpresa.setEstadoAfiliacion(detSap.getEstadoAfiliacion());
						detalleEmpresaList.add(detalleEmpresa);
					}
				}
				listaAs400.removeAll(listaAs400Existe);

				for (SalidaConsultaDatosAfiliacionAs400 detAs400 : salidaAs400.getDetalleEmpresa()) {
					detalleEmpresa = new DetalleEmpresaAfiliado();
					detalleEmpresa.setNombreEmpresa(detAs400.getNombreEmpresa());
					detalleEmpresa.setRutEmpresa(convertirRutAs400AWeb(detAs400.getRutEmpresa()));
					detalleEmpresa.setFechaAfiliacion(Utils.pasaFechaASaWEB(detAs400.getFechaAfiliacion()));
					detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoAs400(detAs400.getTipoAfiliado()) );
					detalleEmpresaList.add(detalleEmpresa);
				}
				afiliado.setDetalleEmpresaList(detalleEmpresaList);
				afiliado.setMensaje("sapyas400");
				afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				//se valida si los contratos de Sap vienen con datos al no entrada en la condicion anterior
			} else if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS) && !salidaAs400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				for (SalidaAfiliadoVO detSap : salidaSAP.getListaAfiliado()) {
					afiliado.setNombreAfiliado(detSap.getNombreCompleto());
					detalleEmpresa = new DetalleEmpresaAfiliado();
					detalleEmpresa.setNombreEmpresa(detSap.getRazonSocial());
					detalleEmpresa.setRutEmpresa(detSap.getRutEmpresa());
					detalleEmpresa.setFechaAfiliacion(Utils.pasaFechaSAPaWEB(detSap.getFechaAfiliacion()));
					detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoSap(detSap.getRol()));
					detalleEmpresa.setEstadoAfiliacion(detSap.getEstadoAfiliacion());
					detalleEmpresaList.add(detalleEmpresa);
					afiliado.setDetalleEmpresaList(detalleEmpresaList);
					afiliado.setMensaje("sap");
					afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				}
			//se valida si los contratos de As400 vienen con datos al no entrada en la condicion anterior
			} else if (salidaAs400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS) && !salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				afiliado.setNombreAfiliado(salidaAs400.getNombre());
				for (SalidaConsultaDatosAfiliacionAs400 detAs400 : salidaAs400.getDetalleEmpresa()) {
					detalleEmpresa = new DetalleEmpresaAfiliado();
					detalleEmpresa.setNombreEmpresa(detAs400.getNombreEmpresa());
					detalleEmpresa.setRutEmpresa(convertirRutAs400AWeb(detAs400.getRutEmpresa()));
					detalleEmpresa.setFechaAfiliacion(Utils.pasaFechaASaWEB(detAs400.getFechaAfiliacion()));
					detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoAs400(detAs400.getTipoAfiliado()));
					detalleEmpresaList.add(detalleEmpresa);
					afiliado.setDetalleEmpresaList(detalleEmpresaList);
					afiliado.setMensaje("as400");
					afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				}
			//si no logra encontrar informacion en los WebServices
			} else {
				afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				afiliado.setMensaje("El sistema no encontro información");
			}

		} catch (Exception e) {
			afiliado.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			afiliado.setMensaje("Error "+e.getMessage());
			e.printStackTrace();
		}
		return afiliado;
	}
	
	
	private static String obtenerTipoAfiliadoAs400(String entrada){
		String salida = "";
		if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.as400.trabajador").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.trabajador").trim();
		}else if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.as400.pensionado").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.pensionado").trim();
		}else if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.as400.independiente").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.independiente").trim();
		}
		return salida;
	}
	
	private static String obtenerTipoAfiliadoSap(String entrada){
		String salida = "";
		if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.sap.trabajador").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.trabajador").trim();
		}else if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.sap.pensionado").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.pensionado").trim();
		}else if(ServiciosConst.RES_SERVICIOS.getString("tipo.afiliado.sap.independiente").trim().equals(entrada.trim())){
			salida = ServiciosConst.RES_SERVICIOS.getString("afiliado.independiente").trim();
		}
		return salida;
	}
	
	private static String convertirRutAs400AWeb(String entrada){
		String salida = "";
		salida = CompletaUtil.quitaCerosIzqString(entrada);
		char  dv = RutUtil.obtenerDigitoVerificador(Integer.parseInt(salida));
		salida = salida + "-" +dv;
		return salida;
	}
	
	/*private boolean buscaRutEnLista(String rutEmpresa){
		boolean salida = false;
		
		return salida;
	}
	*/
	
	/**
	 * 
	 * @param rut
	 * @return obtiene usuario con informacion desde SAP o AS400 enviado un rut por parametro y con la informacion de la primera empresa encontrada.
	 */
	/*
	public static UsuarioVO obtenerDatosAfiliadoGeneric(String rut){
		UsuarioVO usuarioSalida = null;
		
		SalidaListaAfiliadoVO salidaSAP = ServicioQueryBPStatus.obtenerAfiliadoByRutSap(rut);
		if(salidaSAP != null && salidaSAP.getCodigoError().equals("0")){
			
			for (SalidaAfiliadoVO salidaAfiliadoVO : salidaSAP.getListaAfiliado()) {
				usuarioSalida = new UsuarioVO();
				usuarioSalida.setRutEmpresa(salidaAfiliadoVO.getRutEmpresa());
				usuarioSalida.setNombreCompleto(salidaAfiliadoVO.getNombreCompleto());
				usuarioSalida.setFechaAfiliacion(salidaAfiliadoVO.getFechaAfiliacion());
				usuarioSalida.setEstadoAfiliacion(salidaAfiliadoVO.getEstadoAfiliacion());
				usuarioSalida.setRol(salidaAfiliadoVO.getRol());
				usuarioSalida.setNombreEmpresa(salidaAfiliadoVO.getRazonSocial());
				break;
			}
			
		}else{
			SalidaListaConsultaDatosAfiliacionAs400 salidaAs400 = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400(rut);
			if(salidaAs400 != null && salidaAs400.equals("0")){
				usuarioSalida = new UsuarioVO();
				
				usuarioSalida.setNombreCompleto(salidaAs400.getNombre());
				for (SalidaConsultaDatosAfiliacionAs400 detalle : salidaAs400.getDetalleEmpresa()) {
					usuarioSalida.setTipoAfiliado(detalle.getTipoAfiliado());
					usuarioSalida.setFechaAfiliacion(detalle.getFechaAfiliacion());
					usuarioSalida.setOficina(detalle.getOficina());
					usuarioSalida.setRutEmpresa(detalle.getRutEmpresa());
					usuarioSalida.setTipoEmpresa(detalle.getTipoEmpresa());
					usuarioSalida.setNombreEmpresa(detalle.getNombreEmpresa());
					usuarioSalida.setSucursal(detalle.getSucursal());
					break;
				}
				
			}
			
		}
		return usuarioSalida;
				
	}
	
	*//**
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @return retorna informacion del usuario segun el rut y el rut de la empresa.
	 */
	/*
	public static UsuarioVO obtenerDatosAfiliadoGeneric(String rut, String rutEmpresa){
		UsuarioVO usuarioSalida = null;
		
		SalidaListaAfiliadoVO salidaSAP = ServicioQueryBPStatus.obtenerAfiliadoByRutSap(rut);
		if(salidaSAP != null && salidaSAP.getCodigoError().equals("0")){
			
			for (SalidaAfiliadoVO salidaAfiliadoVO : salidaSAP.getListaAfiliado()) {
				if((rutEmpresa != null && salidaAfiliadoVO.getRutEmpresa() != null) && rutEmpresa.equalsIgnoreCase(salidaAfiliadoVO.getRutEmpresa())){
					usuarioSalida = new UsuarioVO();
					usuarioSalida.setRutEmpresa(salidaAfiliadoVO.getRutEmpresa());
					usuarioSalida.setNombreCompleto(salidaAfiliadoVO.getNombreCompleto());
					usuarioSalida.setFechaAfiliacion(salidaAfiliadoVO.getFechaAfiliacion());
					usuarioSalida.setEstadoAfiliacion(salidaAfiliadoVO.getEstadoAfiliacion());
					usuarioSalida.setRol(salidaAfiliadoVO.getRol());
					usuarioSalida.setNombreEmpresa(salidaAfiliadoVO.getRazonSocial());
					break;
				}
			}
			
		}else{
			SalidaListaConsultaDatosAfiliacionAs400 salidaAs400 = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400(rut);
			if(salidaAs400 != null && salidaAs400.equals("0")){
				
				if (rutEmpresa != null) {
					String rutEmpresaChange = CompletaUtil.llenaConCeros(rutEmpresa, 11, true);
					rutEmpresaChange = CortarUtil.obtenerValorAnteriorA(rutEmpresaChange, "-");
					for (SalidaConsultaDatosAfiliacionAs400 detalle : salidaAs400.getDetalleEmpresa()) {
						if (detalle.getRutEmpresa() != null && rutEmpresaChange.equals(detalle.getRutEmpresa())) {
							usuarioSalida = new UsuarioVO();
							usuarioSalida.setNombreCompleto(salidaAs400.getNombre());
							usuarioSalida.setTipoAfiliado(detalle.getTipoAfiliado());
							usuarioSalida.setFechaAfiliacion(detalle.getFechaAfiliacion());
							usuarioSalida.setOficina(detalle.getOficina());
							usuarioSalida.setRutEmpresa(detalle.getRutEmpresa());
							usuarioSalida.setTipoEmpresa(detalle.getTipoEmpresa());
							usuarioSalida.setNombreEmpresa(detalle.getNombreEmpresa());
							usuarioSalida.setSucursal(detalle.getSucursal());
							break;
						}
					}
				}
				
			}
			
		}
		return usuarioSalida;
				
	}*/
	
}
