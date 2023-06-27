package cl.araucana.independientes.config;

import cl.araucana.independientes.intercaja.cobol.bo.ParametrosConexionBO;

import com.ibm.as400.access.AS400;

public class AS400Config 
{
    private static AS400 as400;

    public static AS400 getAs400() 
    {
        return as400;
    }

    public static void setAs400(AS400 _as400) 
    {
        as400 = _as400;
    }

    public static void configure(ParametrosConexionBO parametros)
    {
        as400 = new AS400(parametros.getIpServer(), 
                parametros.getUsuarioConexion(), 
                parametros.getClaveConexion());
    }
}
