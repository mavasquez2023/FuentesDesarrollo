package cl.araucana.sivegam.struts.dwr.actions;

import cl.araucana.sivegam.impl.GenerarCuadroComparativoImpl;
import cl.araucana.sivegam.vo.CuadroComparativoVO;

public class GenerarCuadroComparativoDWR {

    public CuadroComparativoVO obtenerMontosResumenes(String periodoSelected) {

        return GenerarCuadroComparativoImpl.obtenerMontosResumenes(periodoSelected);
    }
}
