package cl.laaraucana.simulacion.webservices.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import cl.laaraucana.simulacion.utils.CompletaUtil;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.utils.RutUtil;
import cl.laaraucana.simulacion.utils.Utils;
import cl.laaraucana.simulacion.webservices.ServiciosConst;
import cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT.ServicioQueryBPStatus;
import cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.simulacion.webservices.model.UsuarioAfiliadoVO;


public class UsuarioServiceUtil {

	/**
	 * 
	 * @param rut
	 * @return UsuarioAfiliadoVO con los datos de Sap
	 * @throws ParseException
	 */
	public static UsuarioAfiliadoVO obtenerAfiliado(String rut) {
		UsuarioAfiliadoVO afiliado = new UsuarioAfiliadoVO();
		afiliado.setRut(rut);
		List<DetalleEmpresaAfiliado> detalleEmpresaList = new ArrayList<DetalleEmpresaAfiliado>();
		DetalleEmpresaAfiliado detalleEmpresa = null;
		try {

			// se llaman a los servicios enviado por parametro el rut
			SalidaListaAfiliadoVO salidaSAP = ServicioQueryBPStatus.obtenerAfiliadoByRutSap(rut);
			//se valida que los dos servicios traigan datos.
			
			if (salidaSAP.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_ERROR)) {
				afiliado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				afiliado.setMensaje(salidaSAP.getMensaje());
				return afiliado;
			}else if (salidaSAP.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)) {
				afiliado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);
				afiliado.setMensaje("El usuario no ha sido encontrado");
				return afiliado;
			}else if (salidaSAP.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS) ) {
				for (SalidaAfiliadoVO detSap : salidaSAP.getListaAfiliado()) {
					afiliado.setNombreAfiliado(detSap.getNombreCompleto());
					detalleEmpresa = new DetalleEmpresaAfiliado();
					detalleEmpresa.setNombreEmpresa(detSap.getRazonSocial());
					detalleEmpresa.setRutEmpresa(detSap.getRutEmpresa());
					detalleEmpresa.setFechaAfiliacion(Utils.pasaFechaSAPaWEB(detSap.getFechaAfiliacion()));
					detalleEmpresa.setTipoAfiliado(obtenerTipoAfiliadoSap(detSap.getRol()));
					detalleEmpresaList.add(detalleEmpresa);
					afiliado.setDetalleEmpresaList(detalleEmpresaList);
					afiliado.setMensaje("sap");
					afiliado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
				}
			//si no logra encontrar informacion en los WebServices
			} else {
				afiliado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);
				afiliado.setMensaje("El sistema no encontro información");
			}

		} catch (Exception e) {
			afiliado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			afiliado.setMensaje("Error "+e.getMessage());
			e.printStackTrace();
		}
		return afiliado;
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
	
	
	public static DetalleEmpresaAfiliado obtenerDetalleEmpresa(String rutEmpresa, UsuarioAfiliadoVO user){
		DetalleEmpresaAfiliado detalle = null;
		if(user.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			for (DetalleEmpresaAfiliado det : user.getDetalleEmpresaList()) {
				if(rutEmpresa.equals(det.getRutEmpresa())){
					detalle = new DetalleEmpresaAfiliado();
					detalle.setFechaAfiliacion(det.getFechaAfiliacion());
					detalle.setNombreEmpresa(det.getNombreEmpresa());
					detalle.setRutEmpresa(det.getRutEmpresa());
					detalle.setTipoAfiliado(detalle.getTipoAfiliado());
					break;
				}
			}
		}
		
		return detalle;
	}
	
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
