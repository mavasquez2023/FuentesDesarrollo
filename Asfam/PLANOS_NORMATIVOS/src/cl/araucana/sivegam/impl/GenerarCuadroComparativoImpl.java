package cl.araucana.sivegam.impl;

import cl.araucana.sivegam.dao.GenerarCuadroComparativoDAO;
import cl.araucana.sivegam.vo.CuadroComparativoVO;

public class GenerarCuadroComparativoImpl {

    public static CuadroComparativoVO obtenerMontosResumenes(String periodoSelected) {

        return GenerarCuadroComparativoDAO.obtenerMontosResumenes(periodoSelected);
    }
}
