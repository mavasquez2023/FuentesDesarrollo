package cl.araucana.estasfam.app.business.enums;

import java.util.HashMap;
import java.util.Map;

public enum EstadisticasEnum {
	ASI5490("ASI5490", "EstadisticaASI5490", ".xls"),
	ASI5491("ASI5491", "EstadisticaASI5491", ".xls"),
	ASI5460("ASI5460", "EstadisticaASI5460", ".xls"),
	ASI4580("ASI4580", "EstadisticaASI4580", ".xls"),
	ASI4560("ASI4560", "EstadisticaASI4560", ".xls"),
	CUADRO8("CUADRO8", "EstadisticaCUADRO8", ".xls"),
	CUADRO10("CUADRO10", "EstadisticaCUADRO10", ".xls") ;
	
 
	private String codigo;
    private String nombreXls;
    private String extencion;
    
 
    private static Map<String, EstadisticasEnum> mapEnum;
 
    private EstadisticasEnum(String codigo, String nombreXls, String extencion) {
    	this.codigo = codigo;
    	this.nombreXls = nombreXls;
    	this.extencion = extencion;
    }
 
    public static EstadisticasEnum getEstadisticasEnum(String cod) {
        if (mapEnum == null) {
            initMapping();
        }
        return mapEnum.get(cod);
    }
    
    private static void initMapping() {
        mapEnum = new HashMap<String, EstadisticasEnum>();
        for (EstadisticasEnum s : values()) {
            mapEnum.put(s.codigo, s);
        }
    }
 
    public String getNombreXls() {
        return nombreXls + extencion;
    }
    
    public String getSoloNombreXls() {
        return nombreXls;
    }
    
    public String getSoloExtencion() {
        return extencion;
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
