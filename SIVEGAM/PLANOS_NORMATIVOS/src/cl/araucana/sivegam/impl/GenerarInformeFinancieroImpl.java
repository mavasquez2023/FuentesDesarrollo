package cl.araucana.sivegam.impl;

import cl.araucana.sivegam.dao.GenerarInformeFinancieroDAO;
import cl.araucana.sivegam.vo.InformeFinancieroVO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarInformeFinancieroImpl {

    public static RespuestaVO insertInformeFinanciero(InformeFinancieroVO informeFinanciero) {

        return GenerarInformeFinancieroDAO.insertInformeFinanciero(informeFinanciero);
    }

    public static InformeFinancieroVO gettInformeFinanciero(String periodoDelInforme) {

        return GenerarInformeFinancieroDAO.gettInformeFinanciero(periodoDelInforme);
    }

    public static RespuestaVO updateInformeFinanciero(InformeFinancieroVO informeFinanciero) {

        return GenerarInformeFinancieroDAO.updateInformeFinanciero(informeFinanciero);
    }
}
