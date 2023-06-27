package cl.araucana.independientes.struts.dwr.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.impl.SolBeneficiosImpl;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;
import cl.araucana.independientes.vo.param.Retorno;

public class SolBeneficiosDWR {

    public AfiliadoBeneficiosVO obtenerDatosAfiliado(String rut){

        return SolBeneficiosImpl.obtenerDatosAfiliado(rut);
    }

    public long obtenerDiferenciaEnDias(String fechaIni, String fechaFin){

        long resultado = 0;
        Date dateIni = new Date();
        Date dateFin = new Date();

        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try
        {
            dateIni = sdf.parse(fechaIni);
            dateFin = sdf.parse(fechaFin);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        resultado = Helper.diferenciaDeDias(dateIni, dateFin);

        return resultado;
    }

    public long obtenerNumeroRecurrencia(int id, long idAfi, String fechaSistema){

        return SolBeneficiosImpl.obtenerNumeroRecurrencia(id, idAfi, fechaSistema);
    }

    public long obtenerNumeroCausanteUnico(int id, long idAfi, long rutCausante){

        return SolBeneficiosImpl.obtenerNumeroCausanteUnico(id, idAfi, rutCausante);
    }

    public Retorno registraBeneficios(String cadenaBeneficios){

        return SolBeneficiosImpl.registraBeneficios(cadenaBeneficios);
    }
}
