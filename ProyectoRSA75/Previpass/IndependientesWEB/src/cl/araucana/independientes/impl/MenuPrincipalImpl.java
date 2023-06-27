package cl.araucana.independientes.impl;

import cl.araucana.independientes.dao.MenuPrincipalDAO;

public class MenuPrincipalImpl 
{
    public static boolean verificaOficinaAnalista(String idAnalista)
    {
        int resp = -1;

        resp = MenuPrincipalDAO.verificaOficinaAnalista(idAnalista);

        if(resp == 0 || resp == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    public static boolean guardarOficinaAnalista(String idAnalista, String nombre, String apePat, String apeMat, String oficina)
    {
        int resp = -1;

        resp = MenuPrincipalDAO.guardarOficinaAnalista(idAnalista, nombre, apePat, apeMat, oficina);

        if(resp != 0)
        {
            return false;
        }else{
            return true;
        }
    }
}
