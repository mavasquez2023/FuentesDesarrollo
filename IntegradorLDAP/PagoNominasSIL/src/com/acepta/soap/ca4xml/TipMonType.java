//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.acepta.soap.ca4xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipMonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipMonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="15"/>
 *     &lt;enumeration value="BOLIVAR"/>
 *     &lt;enumeration value="BOLIVIANO"/>
 *     &lt;enumeration value="CHELIN"/>
 *     &lt;enumeration value="CORONA DIN"/>
 *     &lt;enumeration value="CORONA NOR"/>
 *     &lt;enumeration value="CORONA SC"/>
 *     &lt;enumeration value="CRUZEIRO REAL"/>
 *     &lt;enumeration value="DIRHAM"/>
 *     &lt;enumeration value="DOLAR AUST"/>
 *     &lt;enumeration value="DOLAR CAN"/>
 *     &lt;enumeration value="DOLAR HK"/>
 *     &lt;enumeration value="DOLAR NZ"/>
 *     &lt;enumeration value="DOLAR SIN"/>
 *     &lt;enumeration value="DOLAR TAI"/>
 *     &lt;enumeration value="DOLAR USA"/>
 *     &lt;enumeration value="DRACMA"/>
 *     &lt;enumeration value="ESCUDO"/>
 *     &lt;enumeration value="EURO"/>
 *     &lt;enumeration value="FLORIN"/>
 *     &lt;enumeration value="FRANCO BEL"/>
 *     &lt;enumeration value="FRANCO FR"/>
 *     &lt;enumeration value="FRANCO SZ"/>
 *     &lt;enumeration value="GUARANI"/>
 *     &lt;enumeration value="LIBRA EST"/>
 *     &lt;enumeration value="LIRA"/>
 *     &lt;enumeration value="MARCO AL"/>
 *     &lt;enumeration value="MARCO FIN"/>
 *     &lt;enumeration value="NUEVO SOL"/>
 *     &lt;enumeration value="OTRAS MONEDAS"/>
 *     &lt;enumeration value="PESETA"/>
 *     &lt;enumeration value="PESO"/>
 *     &lt;enumeration value="PESO CL"/>
 *     &lt;enumeration value="PESO COL"/>
 *     &lt;enumeration value="PESO MEX"/>
 *     &lt;enumeration value="PESO URUG"/>
 *     &lt;enumeration value="RAND"/>
 *     &lt;enumeration value="RENMINBI"/>
 *     &lt;enumeration value="RUPIA"/>
 *     &lt;enumeration value="SUCRE"/>
 *     &lt;enumeration value="YEN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipMonType")
@XmlEnum
public enum TipMonType {

    BOLIVAR("BOLIVAR"),
    BOLIVIANO("BOLIVIANO"),
    CHELIN("CHELIN"),
    @XmlEnumValue("CORONA DIN")
    CORONA_DIN("CORONA DIN"),
    @XmlEnumValue("CORONA NOR")
    CORONA_NOR("CORONA NOR"),
    @XmlEnumValue("CORONA SC")
    CORONA_SC("CORONA SC"),
    @XmlEnumValue("CRUZEIRO REAL")
    CRUZEIRO_REAL("CRUZEIRO REAL"),
    DIRHAM("DIRHAM"),
    @XmlEnumValue("DOLAR AUST")
    DOLAR_AUST("DOLAR AUST"),
    @XmlEnumValue("DOLAR CAN")
    DOLAR_CAN("DOLAR CAN"),
    @XmlEnumValue("DOLAR HK")
    DOLAR_HK("DOLAR HK"),
    @XmlEnumValue("DOLAR NZ")
    DOLAR_NZ("DOLAR NZ"),
    @XmlEnumValue("DOLAR SIN")
    DOLAR_SIN("DOLAR SIN"),
    @XmlEnumValue("DOLAR TAI")
    DOLAR_TAI("DOLAR TAI"),
    @XmlEnumValue("DOLAR USA")
    DOLAR_USA("DOLAR USA"),
    DRACMA("DRACMA"),
    ESCUDO("ESCUDO"),
    EURO("EURO"),
    FLORIN("FLORIN"),
    @XmlEnumValue("FRANCO BEL")
    FRANCO_BEL("FRANCO BEL"),
    @XmlEnumValue("FRANCO FR")
    FRANCO_FR("FRANCO FR"),
    @XmlEnumValue("FRANCO SZ")
    FRANCO_SZ("FRANCO SZ"),
    GUARANI("GUARANI"),
    @XmlEnumValue("LIBRA EST")
    LIBRA_EST("LIBRA EST"),
    LIRA("LIRA"),
    @XmlEnumValue("MARCO AL")
    MARCO_AL("MARCO AL"),
    @XmlEnumValue("MARCO FIN")
    MARCO_FIN("MARCO FIN"),
    @XmlEnumValue("NUEVO SOL")
    NUEVO_SOL("NUEVO SOL"),
    @XmlEnumValue("OTRAS MONEDAS")
    OTRAS_MONEDAS("OTRAS MONEDAS"),
    PESETA("PESETA"),
    PESO("PESO"),
    @XmlEnumValue("PESO CL")
    PESO_CL("PESO CL"),
    @XmlEnumValue("PESO COL")
    PESO_COL("PESO COL"),
    @XmlEnumValue("PESO MEX")
    PESO_MEX("PESO MEX"),
    @XmlEnumValue("PESO URUG")
    PESO_URUG("PESO URUG"),
    RAND("RAND"),
    RENMINBI("RENMINBI"),
    RUPIA("RUPIA"),
    SUCRE("SUCRE"),
    YEN("YEN");
    private final String value;

    TipMonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipMonType fromValue(String v) {
        for (TipMonType c: TipMonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
