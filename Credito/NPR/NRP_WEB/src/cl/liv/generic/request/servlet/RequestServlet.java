package cl.liv.generic.request.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import cl.liv.core.request.impl.RequestImpl;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposSalida;

public class RequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061711754376149030L;

	Logger log = Logger.getLogger(RequestServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response, TiposMetodoHttp.GET);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response, TiposMetodoHttp.POST);
	}
	public void procesarPeticion(HttpServletRequest request, HttpServletResponse response, int metodoEntrada) {
	
		String idReq = request.getParameter("id_req");
		String formatoSalida = request.getParameter("formato_salida");
		String formatoEntrada = request.getParameter("formato_entrada");

		log.info("idReq: " + idReq);
		log.info("formatoSalida: " + formatoSalida);

		int tipoSalida = TiposSalida.SALIDA_JSON;
		int tipoEntrada = TiposEntrada.ENTRADA_ESTANDAR;

		if (formatoSalida != null && formatoSalida.equals("xml"))
			tipoSalida = TiposSalida.SALIDA_XML;
		else if (formatoSalida != null && formatoSalida.equals("json"))
			tipoSalida = TiposSalida.SALIDA_JSON;
		else if (formatoSalida != null && formatoSalida.equals("data"))
			tipoSalida = TiposSalida.SALIDA_SOLO_DATA;

		if (formatoEntrada != null && formatoEntrada.equals("json"))
			tipoEntrada = TiposEntrada.ENTRADA_JSON;
		if (formatoEntrada != null && formatoEntrada.equals("string"))
			tipoEntrada = TiposEntrada.ENTRADA_STRING;

		Object salida = null;
		try {
			salida = RequestImpl.procesarPeticion(request, idReq, tipoSalida, tipoEntrada, metodoEntrada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			procesarOutputError(tipoSalida, e.getMessage(), response);
			return;

		}

		if (tipoSalida == TiposSalida.SALIDA_JSON) {
			registrarSalida(response, (JSONObject) salida);
		} else if (tipoSalida == TiposSalida.SALIDA_XML) {
			registrarSalidaXML(response, (String) salida);
		} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
			registrarSalidaTexto(response, (String) salida);
		}

	}

	public void procesarOutputError(int tipoSalida, String message, HttpServletResponse response) {
		if (tipoSalida == TiposSalida.SALIDA_XML) {
			registrarSalidaXML(response, "<root><error>" + message + "</error></root>");
		} else if (tipoSalida == TiposSalida.SALIDA_JSON) {
			JSONObject json = new JSONObject();
			try {
				json.put("error", message);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			registrarSalida(response, json);
		} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
			registrarSalidaTexto(response, "Error: " + message);
		}
	}

	public void registrarSalidaTexto(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registrarSalidaXML(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registrarSalida(HttpServletResponse response, JSONObject result) {
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		// response.addHeader("Access-Control-Allow-Credentials", "true");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(result != null){
				if(result.toString().length() > 512)
					log.info("[output cortado]: " + result.toString().substring(0,512));
				else
					log.info("[output]: " + result.toString());
			}
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
