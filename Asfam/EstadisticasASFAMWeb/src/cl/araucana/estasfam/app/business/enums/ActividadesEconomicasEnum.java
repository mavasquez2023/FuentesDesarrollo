package cl.araucana.estasfam.app.business.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ActividadesEconomicasEnum {
	
	AGRICULTURA_GANADERIA_CAZA_SILVIC(1,1),
	EXPLOTACION_DE_MINAS_Y_CANTERAS(2,2),
	INDUSTRIAS_MANUFACTURERAS(3,3),
	SUMINISTRO_DE_ELECTRICIDAD_GAS_Y_AGUA(4,4),
	CONSTRUCCION(5,5),
	COMERCIO(6,6),
	TRANSPORTE_ALMACENAMIENTO_Y_COMUNICACI(7,7),
	INTERMEDIACION_FINANCIERA(8,8),
	SERVICIOS_SOCIALES_Y_DE_SALUD(9,9),
	PESCA(10,1),
	HOTELES_Y_RESTAURANTES(11,10),
	ACT_INMOBIL_EMPRESARIALES_Y_ALQUILER(12,10),
	ADM_PUB_Y_DEF_PLANES_SEGUR_SOC_AFIL(13,10),
	ENSEÑANZA(14,10),
	OTRAS_ACT_SE_SERV_COMUNI_SOC_Y_PERS(15,9),
	HOGARES_PRIVADOS_CON_SERVICIO_DOMESTICO(16,10),
	ORGANIZ_Y_ORGANOS_EXTRATERRITORALES(17,10);
	
	private Integer codActividadEconomica;
	private Integer contColumna;
	
	private static Map<String, ActividadesEconomicasEnum> mapEnum;
	
	private ActividadesEconomicasEnum(Integer codActividadEconomica, Integer contColumna){
		this.codActividadEconomica = codActividadEconomica;
		this.contColumna = contColumna;
	}
	public Integer getCodActividadEconomica() {
		return codActividadEconomica;
	}
	public void setCodActividadEconomica(Integer codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}
	public Integer getContColumna() {
		return contColumna;
	}
	public void setContColumna(Integer contColumna) {
		this.contColumna = contColumna;
	}
	
	public static ActividadesEconomicasEnum getActividadesEconomicasEnum(String codActividadEconomica) {
        if (mapEnum == null) {
            initMapping();
        }
        return (mapEnum.containsKey(codActividadEconomica))?mapEnum.get(codActividadEconomica):null;
    }
    
    private static void initMapping() {
        mapEnum = new HashMap<String, ActividadesEconomicasEnum>();
        for (ActividadesEconomicasEnum s : values()) {
            mapEnum.put(s.codActividadEconomica.toString(), s);
        }
    }
	
//	public static List<ActividadesEconomicasEnum> listEnums() {
//        List<ActividadesEconomicasEnum> enumsAecEcoValues = new ArrayList<ActividadesEconomicasEnum>();
//        for (ActividadesEconomicasEnum s : values()) {
//            enumsAecEcoValues.add(s);
//        }
//        return enumsAecEcoValues;
//    }
}
