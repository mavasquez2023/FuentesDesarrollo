package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.ConsBenefProgImpl;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;

public class ConsBenefProgDWR {

    public AfiliadoBeneficiosVO obtenerDatosAfiliado(String rut){
        return ConsBenefProgImpl.obtenerDatosAfiliado(rut);
    }

}
