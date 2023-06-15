package cl.jfactory.planillas.service.util;

import java.math.BigDecimal;
import java.util.HashMap;

public class ApoyoGeneracionUtil {

	public String getNumeroInscripcionCompleto(HashMap data){
		String retorno = "";
		if(data.get("NRO_INSCRIPCION") != null){
			String numeroInscripcion = (String)data.get("NRO_INSCRIPCION");
			if(numeroInscripcion.indexOf("-")>0){
				String[] partes = numeroInscripcion.split("-");
				retorno = new BigDecimal(partes[0])+"-"+partes[1];
			}
			else{
				retorno = new BigDecimal(numeroInscripcion)+"";
			}
		}
		return retorno;
	}
}
