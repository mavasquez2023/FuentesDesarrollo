package cl.laaraucana.simulacion.webservices.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.simulacion.utils.CompletaUtil;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.utils.RutUtil;
import cl.laaraucana.simulacion.utils.Utils;
import cl.laaraucana.simulacion.webservices.ServiciosConst;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.ClienteCampañaAP;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.EntradaCampañaVO;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.SalidaCampañaVO;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.ClienteInfoAfiliado;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.VO.EntradaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.VO.SalidaAfiliadoVO;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.OficinaGastosSingleton;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaVO;
import cl.laaraucana.simulacion.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.simulacion.webservices.model.UsuarioAfiliadoVO;


public class DataServiceUtil {

	/**
	 * 
	 * @param rut
	 * @return UsuarioAfiliadoVO con los datos de Sap y As400
	 * @throws ParseException
	 */
	public static SalidaAfiliadoVO obtenerInfoAfiliado(String rut) {
		EntradaAfiliadoVO entrada = new EntradaAfiliadoVO();
		entrada.setRut(rut);
		
		SalidaAfiliadoVO salidaSAP= new SalidaAfiliadoVO();
		try {

			// se llaman a los servicios enviado por parametro el rut
			//SalidaListaConsultaDatosAfiliacionAs400 salidaAs400 = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400(rut);
			ClienteInfoAfiliado cliente= new ClienteInfoAfiliado();
			
			salidaSAP = (SalidaAfiliadoVO)cliente.call(entrada);
			//se valida que los dos servicios traigan datos.
			

		} catch (Exception e) {
			salidaSAP.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaSAP.setMensaje("Error "+e.getMessage());
			e.printStackTrace();
		}
		return salidaSAP;
	}
	
	/**
	 * 
	 * @param rut
	 * @param contrato
	 * @return SalidaCampañaVO con los datos de Sap
	 * @throws ParseException
	 */
	public static SalidaCampañaVO obtenerCampañasAP(String rut, String contrato) {
		EntradaCampañaVO entrada = new EntradaCampañaVO();
		entrada.setRut(rut);
		entrada.setContrato(contrato);
		
		SalidaCampañaVO salidaSAP= new SalidaCampañaVO();
		try {

			// se llaman a los servicios enviado por parametro el rut
			//SalidaListaConsultaDatosAfiliacionAs400 salidaAs400 = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400(rut);
			ClienteCampañaAP cliente= new ClienteCampañaAP();
			
			salidaSAP = (SalidaCampañaVO)cliente.call(entrada);
			//se valida que los dos servicios traigan datos.
			

		} catch (Exception e) {
			salidaSAP.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaSAP.setMensaje("Error "+e.getMessage());
			e.printStackTrace();
		}
		return salidaSAP;
	}
	
	public static String obtenerTextoCampañasAP(SalidaCampañaVO campaña, String contrato) {
		String salida="";
		try {
			double total= campaña.getCondonacionPagoTotal();
			double convenio= campaña.getCondonacionConvenioPago();
			Date fechaVigencia= campaña.getFechaVigencia();
			//Date hoy= new Date();
			String fechahoy= Utils.getFechaHoySAP();
			Date hoy= Utils.stringToDateSAP(fechahoy);
			
			if(fechaVigencia!=null && fechaVigencia.compareTo(hoy)>=0){
				if(total!=0 && total!=99 && convenio!=0 && convenio!=99){
					salida= "Cliente en Campaña de Acuerdo de Pago, contrato " + contrato + " con condonación de capital hasta el " + total + "% por Pago Total o "+ convenio + "% por Convenio de Pago. Vigencia hasta el "  + Utils.dateToString(fechaVigencia);
				}else if(total==0 && convenio== 0){
					salida= "Cliente en Campaña de Acuerdo de Pago, contrato " + contrato + " sin condonación de capital. Vigencia hasta el "  + Utils.dateToString(fechaVigencia);
				}
			}
			if(salida.equals("")){
				if(total==99 && convenio== 99){
					salida= "Cliente en Campaña de Acuerdo de Pago, contrato " + contrato + " sólo con pago total y sin % de condonación de capital";
				}else if(total!=0 && total!=99 && convenio==99){
					salida= "Cliente en Campaña de Acuerdo de Pago, contrato " + contrato + " sólo con pago total y condonación de capital hasta un " + total + "%";
				}else{
					salida= "Cliente sin oferta de castigos";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	/**
	 * Retorna los tipos de afiliados en la araucana
	 * @return un hashmap de tipos de afiliados
	 * Afiliado, Pensionado e Independiente
	 */
	public static Map<String, String> getTipoAfiliacion(String filtro) {
		Map<String, String> hash = new LinkedHashMap<String, String>();
		if(filtro.contains("TRA")){
			hash.put(ConstantesFormalizar.COD_AFILIADO, ConstantesFormalizar.NOM_AFILIADO);
		}
		if(filtro.contains("PEN")){
			hash.put(ConstantesFormalizar.COD_PENSIONADO, ConstantesFormalizar.NOM_PENSIONADO);
		}
		//hash.put(NOM_INDEPENDIENTE, COD_INDEPENDIENTE); 
		if(filtro.contains("DD")){
			hash.put(ConstantesFormalizar.COD_DEUDOR, ConstantesFormalizar.NOM_DEUDOR);
		}
		return hash;
	}
	
	public static String obtenerTipoAfiliadoSap(String tipoAfiliado){
		String salida = "";
		if(ConstantesFormalizar.COD_AFILIADO.equals(tipoAfiliado.trim())){
			salida = ConstantesFormalizar.NOM_AFILIADO;
		}else if(ConstantesFormalizar.COD_PENSIONADO.equals(tipoAfiliado.trim())){
			salida = ConstantesFormalizar.NOM_PENSIONADO;
		}else if(ConstantesFormalizar.COD_DEUDOR.equals(tipoAfiliado.trim())){
			salida = ConstantesFormalizar.NOM_DEUDOR;
		}
		return salida;
	}
	
	public static List<String> obtenerListaProductosSap(String tipoAfiliado, boolean insolvencia){
		List<String> productos= new ArrayList<String>();
		String[] salida=null;
		String lista="";
		if(ConstantesFormalizar.COD_AFILIADO.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_AFILIADO;
		}else if(ConstantesFormalizar.COD_PENSIONADO.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_PENSIONADO;
		}else if(ConstantesFormalizar.COD_DEUDOR.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_DEUDOR_DIRECTO;
		}
		if(insolvencia){
			lista= ConstantesFormalizar.PRODUCTOS_INSOLVENCIA;
		}
		salida= lista.split(";");
		for (int i = 0; i < salida.length; i++) {
			productos.add(salida[i]);
		}
		return productos;
	}
	
	public static List<Map<String, String>> obtenerMapCabeceraCredito(SalidaCreditoVigenteVO cabeceraVO){
		List<Map<String, String>> conceptos= new ArrayList<Map<String,String>>();
		String[] salida=null;
		
		Map h = new HashMap<String, String>();
		h.put("concepto", "insolvencia");
		h.put("valor", cabeceraVO.getInsolvencia());
		conceptos.add(h);
		
		h = new HashMap<String, String>();
		h.put("concepto", "cesantia");
		h.put("valor", cabeceraVO.getCesantia());
		conceptos.add(h);
		
		h = new HashMap<String, String>();
		h.put("concepto", "desgravamen");
		h.put("valor", cabeceraVO.getDesgravamen());
		conceptos.add(h);
		
		return conceptos;
	}
	
	public static List<Map<String, String>> obtenerMapProductosSap(String tipoAfiliado, boolean insolvencia){
		List<Map<String, String>> productos= new ArrayList<Map<String,String>>();
		String[] salida=null;
		String lista="";
		if(ConstantesFormalizar.COD_AFILIADO.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_AFILIADO;
		}else if(ConstantesFormalizar.COD_PENSIONADO.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_PENSIONADO;
		}else if(ConstantesFormalizar.COD_DEUDOR.equals(tipoAfiliado.trim())){
			lista = ConstantesFormalizar.PRODUCTOS_DEUDOR_DIRECTO;
		}
		if(insolvencia){
			lista =ConstantesFormalizar.PRODUCTOS_INSOLVENCIA;
		}
		salida= lista.split(";");
		Map h = new HashMap<String, String>();
		h.put("codigo", "");
		h.put("nombre", "Seleccione");
		productos.add(h);
		
		for (int i = 0; i < salida.length; i++) {
			h = new HashMap<String, String>();
			h.put("codigo", salida[i]);
			h.put("nombre", salida[i]);
			productos.add(h);
		}
		return productos;
	}
	
	public static List<Map<String, String>> obtenerMapCuotas(String tipoProducto, int edad){
		List<Map<String, String>> cuotas= new ArrayList<Map<String,String>>();
		
		String[]salida = ServiciosConst.RES_SERVICIOS.getString("cuotas.producto."+ tipoProducto).trim().split("-");
		int cuotaInicial= Integer.parseInt(salida[0]);
		int cuotaFinal= Integer.parseInt(salida[1]);
		if(edad>0){
			String edad_restringida= ServiciosConst.RES_SERVICIOS.getString("repro.edad.rango.restringido");
			if(edad>=Integer.parseInt(edad_restringida)){
				int cuota_max_edad= Integer.parseInt(ServiciosConst.RES_SERVICIOS.getString("repro.cuota.max.edad." + edad));
				if(cuota_max_edad< cuotaFinal){
					cuotaFinal= cuota_max_edad;
				}
			}
		}
		Map h = new HashMap<String, String>();
		h.put("codigo", "");
		h.put("nombre", "Seleccione");
		cuotas.add(h);
		for (int i = cuotaInicial; i <= cuotaFinal; i++) {
			h = new HashMap<String, String>();
			h.put("codigo", i);
			h.put("nombre", i);
			cuotas.add(h);
		}
		return cuotas;
	}
	
	public static List<Map<String, String>> obtenerMapMesesGracia(String tipoProducto, String tipoAfiliado){
		List<Map<String, String>> cuotas= new ArrayList<Map<String,String>>();
		
		String[]salida = ServiciosConst.RES_SERVICIOS.getString("meses.gracia."+ tipoProducto + "." + tipoAfiliado).trim().split("-");
		int mesInicial= Integer.parseInt(salida[0]);
		int mesFinal= Integer.parseInt(salida[1]);
		
		for (int i = mesInicial; i <= mesFinal; i++) {
			Map h = new HashMap<String, String>();
			h.put("codigo", i);
			h.put("nombre", i);
			cuotas.add(h);
		}
		return cuotas;
	}
	
	public static List<Map<String, String>> obtenerMapCesantia(String tipoAfiliado){
		List<Map<String, String>> opciones= new ArrayList<Map<String,String>>();

		String salida = ServiciosConst.RES_SERVICIOS.getString("cesantia."+ tipoAfiliado).trim();
		salida= salida.equals("S")?"X":"";
		Map<String, String> h = new HashMap<String, String>();
		h.put("codigo", salida);
		opciones.add(h);
	
		return opciones;
	}
	
	public static List<Map<String, String>> obtenerMapDesgravamen(){
		List<Map<String, String>> opciones= new ArrayList<Map<String,String>>();
		String salida= ServiciosConst.RES_SERVICIOS.getString("desgravamen.default").trim();
		salida= salida.equals("S")?"X":"";
		
		Map<String, String> h = new HashMap<String, String>();
		h.put("codigo", salida);
		opciones.add(h);
		
		return opciones;
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
	 * Retorna la lista de oficinas
	 * @return Hash con la lista de oficinas y código.
	 */
	public static List<OficinaGastosNotarialSalidaVO> getOficinas() {
		List<OficinaGastosNotarialSalidaVO> oficinasGastosList = OficinaGastosSingleton.getInstance().getListaOficinasGastos()
			.getOficinasGastosList();
		return oficinasGastosList;
	}

	
}
