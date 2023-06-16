package cl.araucana.estasfam.app.business.enums;

import java.util.HashMap;
import java.util.Map;

public enum TiposEnum {
	ASI5490_NORMAL("N", "NORMAL"),
	ASI5490_MATERNAL("M", "MATERNAL"),
	ASI5490_INVALIDA("I", "INVALIDA"),
	
	CUADRO8_ACTIVOS("A", "ACTIVOS"),
	CUADRO8_SUBSIDIADOS("S", "SUBSIDIADOS"),
	
	CUADRO10_CONYUGUE("C", "CONYUGUE"),
	CUADRO10_HIJOS("H", "HIJOS"),
	CUADRO10_MATERNAL("M", "MATERNAL"),
	CUADRO10_ASCENDIENTES("A", "ASCENDIENTES"),
	
	ASI5491_CONYUGUE("C", "CONYUGUE"),
	ASI5491_ASCENDIENTES("A", "ASCENDIENTES"),
	ASI5491_HIJOS("H", "HIJOS"),
	ASI5491_MATERNAL("M", "MATERNAL"),
	ASI5491_INVALIDA("I", "INVALIDA")
	;
 
	private String codigo;
    private String descripcion;
    
 
    private static Map<String, TiposEnum> mapEnum;
 
    private TiposEnum(String codigo, String descripcion) {
    	this.codigo = codigo;
    	this.descripcion = descripcion;
    }
 
    public static TiposEnum getTipoCargaEnum(String cod) {
        if (mapEnum == null) {
            initMapping();
        }
        return mapEnum.get(cod);
    }
    
    private static void initMapping() {
        mapEnum = new HashMap<String, TiposEnum>();
        for (TiposEnum s : values()) {
            mapEnum.put(s.codigo, s);
        }
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public String getCodigo() {
        return codigo;
    }  
    
    public Boolean equals(String codigo){
    	if(codigo == null) return false;
    	if(this.codigo.equals(codigo)){
    		return true;
    	}else{
    		return false;
    	}
    }
}
