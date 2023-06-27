package cl.araucana.independientes.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.independientes.dao.*;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.AfiliadoBeneficiosVO;
import cl.araucana.independientes.vo.param.ListadoParametros;

public class ConsBenefProgImpl {

    public static AfiliadoBeneficiosVO obtenerDatosAfiliado(String rut)
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        AfiliadoBeneficiosVO salida = new AfiliadoBeneficiosVO();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        Date date = new Date();

        int estadoAporte = -1;

        salida = SolBeneficiosDAO.obtenerDatosAfiliado(rut);

        salida.setGlosaTipoestadoAfiliado(Helper.obtenerDescripcion(listaParam.getListEstadoAfiliado(), salida.getTipoEstadoAfiliado()));

        //JLGN 13-02-2012
        //salida.setEstadoAporte(SolBeneficiosDAO.obtenerEstadoPagoAporte(rut, Helper.obtenerUltimoDiaMes(ParametrosDAO.obtenerFechaSistema())));
        salida.setEstadoAporte(SolBeneficiosDAO.obtenerEstadoPagoAporte(rut, Helper.obtenerUltimoDiaMes(ParametrosDAO.obtenerFechaSistema()), Helper.obtenerUltimoDiaMes(ParametrosDAO.obtenerFechaSistemaMesAnterior())));

        /*
		switch (salida.getEstadoAporte())
		{
			case 0:	estadoAporte = 2; break; //Moroso
			case 1:	estadoAporte = 1; break; //Cancelado
		}

		salida.setGlosaEstadoAporte(Helper.obtenerDescripcion(listaParam.getListEstadoAporte(), estadoAporte));
         */	
        if (salida.getFechaVigencia() != null){
            try{				
                date = sdf1.parse(salida.getFechaVigencia());
                salida.setFechaVigencia(sdf2.format(date));
                salida.setTotalDias(Helper.diferenciaDeDias(date, sdf2.parse(ParametrosDAO.obtenerFechaSistema())));

            }catch (ParseException e) {
                e.printStackTrace();
            }		
        }		

        //20120720 - ANA - MODIFICACION DE OBTENCION DE ESTADO DE APORTE
        estadoAporte = salida.getEstadoAporte();
        if (salida.getTotalDias() > 0){	
            try {
                long totalDiasMesSiguiente = Helper.diferenciaDeDiasMesSiguiente(date, sdf2.parse(ParametrosDAO.obtenerFechaSistema()));
                if (totalDiasMesSiguiente >= 0){
                    salida.setGlosaEstadoAporte(Helper.obtenerDescripcion(listaParam.getListEstadoAporte(), estadoAporte));
                }
                else{
                    salida.setGlosaEstadoAporte(Helper.obtenerDescripcion(listaParam.getListEstadoAporte(), 3));
                    salida.setEstadoAporte(3);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{	
            salida.setGlosaEstadoAporte(Helper.obtenerDescripcion(listaParam.getListEstadoAporte(), 3));
            salida.setEstadoAporte(3);
        }

        return salida;
    }
}
