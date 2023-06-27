package cl.araucana.aporte.helper;

import java.util.Calendar;

public class Helper {
    public static String digitoVerificadorRut(int strRut)
    {
        int rut = 0;
        int s = 0;
        String l_dv = "";

        rut = strRut;

        for (int i = 2; i< 8; i++)
        {
            s = s + ( rut % 10 ) * i;
            rut = (rut - ( rut % 10 )) / 10;
        }

        s = s + ( rut % 10 ) * 2;
        rut = (rut - ( rut % 10 )) / 10;
        s = s + ( rut % 10 ) * 3;
        rut = (rut - ( rut % 10 )) / 10;
        s = 11 - ( s % 11 );

        if ( s == 10 )
            l_dv = "K";
        else
            if ( s == 11 )
                l_dv = "0";
            else
                l_dv = s + "";

        return( l_dv );
    }

    public static String retornaString(int largo){
        String retorno ="";

        for(int i= 0; i<largo;i++){
            retorno = retorno + " ";
        }

        return retorno;

    }

    public static String retornaString2(int largo, String valor){
        String retorno;
        retorno = valor;
        int largoValor = valor.length();
        for(int i= largoValor; i<largo;i++){
            retorno = " " + retorno;
        }
        return retorno;		
    }

    public static String retornaString3(int largo, String valor){
        String retorno;
        retorno = valor;
        int largoValor = valor.length();
        for(int i= largoValor; i<largo;i++){
            retorno = "0"+ retorno;
        }
        return retorno;		
    }

    public static boolean verificaFecha(String fechaRecaudacion){
        int dia = Integer.parseInt(fechaRecaudacion.substring(0,2));
        int mes = Integer.parseInt(fechaRecaudacion.substring(2,4));
        int anio = Integer.parseInt(fechaRecaudacion.substring(4,8));
        mes--;

        Calendar fecha = Calendar.getInstance();
        fecha.set(anio, mes, dia);
        Calendar fechaActual = Calendar.getInstance();
        if(fecha.before(fechaActual)){
            return false;
        }
        else{
            return true;
        }
    }
}
