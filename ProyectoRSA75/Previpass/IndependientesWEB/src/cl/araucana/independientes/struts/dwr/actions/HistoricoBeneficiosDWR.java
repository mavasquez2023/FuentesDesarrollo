package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.impl.HistoricoBeneficiosImpl;
import cl.araucana.independientes.vo.HistoricoBeneficiosVO;
import cl.araucana.independientes.vo.param.Retorno;

public class HistoricoBeneficiosDWR{

    public HistoricoBeneficiosVO consultaHistoricoBeneficios (String rut)
    {
        HistoricoBeneficiosVO ret = new HistoricoBeneficiosVO();

        try
        {
            ret = HistoricoBeneficiosImpl.consultaHistoricoBeneficios(rut);

            return ret;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return ret;
    }

    public Retorno anularBeneficio(long folio)
    {
        return HistoricoBeneficiosImpl.anularBeneficio(folio);
    }

    public Retorno reversarBeneficio(long folio)
    {
        return HistoricoBeneficiosImpl.reversarBeneficio(folio);
    }

    //Inicio REQ 6988 JLGN 11-02-2013
    public Retorno actualizarBeneficio(String rut)
    {
        return HistoricoBeneficiosImpl.actualizarBeneficio(rut);
    }
    //Fin REQ 6988 JLGN 11-02-2013
}



