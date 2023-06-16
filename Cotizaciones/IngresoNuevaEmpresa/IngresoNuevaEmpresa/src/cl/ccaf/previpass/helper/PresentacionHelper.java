package cl.ccaf.previpass.helper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

import cl.ccaf.previpass.dao.PrevipassDAO;

public class PresentacionHelper {

	public static void main(String[] args) {
		//System.out.println("resultado: "+ autenticar("15057836-1", "luisibacache@gmail.com", "2105"));
		//System.out.println("resultado: "+ autenticar("9000050-0", "luisibacache@gmail.com", "7098"));
		//System.out.println("resultado: "+ autenticar("9000050-0", "luisibacach1e@gmail.com", "7098"));
		/*HashMap data = new HashMap();
		data.put("rut_empresa", "9000050-0");
		//data.put("email_empresa", "libacache@zbsys.net");
		data.put("email_empresa", "luisibacache@gmail.com");
		System.out.println( PrevipassDAO.obtenerRegistro("custom.validarEmpresaEnCreacion", data) );*/
		
		//System.out.println("resultado: "+ generarClave("9000050-0","libacache@@zbsys.net", 7098));
		
	}
	
	public static HashMap autenticar(HttpServletRequest request, String rutEmpresa,String emailEmpresa, String jcaptcha){
		HashMap resultado = new HashMap();
		
		boolean estadoAutenticacion = false;
		boolean captchaPassed=false;
				
		HashMap data = new HashMap();
		data.put("rut_empresa", rutEmpresa);
		data.put("email_empresa", emailEmpresa);
		
		HashMap resultadoEmpresaEnProceso = PrevipassDAO.obtenerRegistro("custom.validarEmpresaEnCreacion", data);
		if(resultadoEmpresaEnProceso != null && "true".equals(resultadoEmpresaEnProceso.get("RESULTADO").toString().toLowerCase())){
			resultado.put("ERROR", "EMPRESA_REGISTRO_EN_PROCESO");
			resultado.put("ESTADO_AUTENTICACION", estadoAutenticacion+"");
			return resultado;
		}
		
		HashMap dataEmpresa = PrevipassDAO.obtenerRegistro("custom.validarExistenciaEmpresa",rutEmpresa.split("-")[0]);
		if(dataEmpresa!= null && "true".equals(dataEmpresa.get("RESULTADO").toString().toLowerCase())){
			resultado.put("ERROR", "EMPRESA_EXISTE");
			resultado.put("ESTADO_AUTENTICACION", estadoAutenticacion+"");
			return resultado;
		}
		
		try {
			captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, jcaptcha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!captchaPassed){
			resultado.put("ERROR", "CAPTCHA_NO_COINCIDE");
			resultado.put("ESTADO_AUTENTICACION", estadoAutenticacion+"");
			return resultado;
		}
		
		HashMap registro = obtenerRegistroTMP(rutEmpresa, emailEmpresa);
		System.out.println("registro: "+ registro);
		estadoAutenticacion = true;
		if(registro == null ){
			PrevipassDAO.ingresarNuevoRegistro(rutEmpresa, emailEmpresa);
		}
		resultado.put("ESTADO_AUTENTICACION", estadoAutenticacion+"");
		return resultado;
		
	}

	
	public static HashMap generarClave(String rutEmpresa,String emailEmpresa,int clave){
		HashMap resultado = new HashMap();
		HashMap data = new HashMap();
		data.put("rut_empresa", rutEmpresa);
		data.put("email_empresa", emailEmpresa);
		
		HashMap resultadoEmpresaEnProceso = PrevipassDAO.obtenerRegistro("custom.validarEmpresaEnCreacion", data);
		if(resultadoEmpresaEnProceso != null && "true".equals(resultadoEmpresaEnProceso.get("RESULTADO").toString().toLowerCase())){
			resultado.put("ERROR", "EMPRESA_REGISTRO_EN_PROCESO");
			resultado.put("ESTADO_GENERACION", false+"");
			return resultado;
		}
		
		HashMap dataEmpresa = PrevipassDAO.obtenerRegistro("custom.validarExistenciaEmpresa",rutEmpresa.split("-")[0]);
		if(dataEmpresa!= null && "true".equals(dataEmpresa.get("RESULTADO").toString().toLowerCase())){
			resultado.put("ERROR", "EMPRESA_EXISTE");
			resultado.put("ESTADO_GENERACION", false+"");
			return resultado;
		}
		
		//resultado.put("ESTADO_GENERACION", PrevipassDAO.actualizarClave(rutEmpresa, emailEmpresa, clave)+"");
		return resultado;
	}
	
	
	public static HashMap obtenerRegistroTMP(String rutEmpresa, String emailEmpresa){
		
		HashMap parametro = new HashMap();
		parametro.put("rut_empresa", rutEmpresa);
		parametro.put("email_empresa", emailEmpresa);
		System.out.println("ejecutando: "+parametro);
		HashMap registro = PrevipassDAO.obtenerRegistro("custom.obtenerDataTMPPrevipass",parametro);
		
		return registro;
	}
	
	
}
