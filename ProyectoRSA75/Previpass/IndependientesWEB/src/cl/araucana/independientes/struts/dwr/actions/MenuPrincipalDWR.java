package cl.araucana.independientes.struts.dwr.actions;

import cl.araucana.independientes.impl.MenuPrincipalImpl;

public class MenuPrincipalDWR 
{
    public static boolean verificaOficinaAnalista(String idAnalista)
    {
        return MenuPrincipalImpl.verificaOficinaAnalista(idAnalista);
    }

    public static boolean guardarOficinaAnalista(String idAnalista, String nombre, String apePat, String apeMat, String oficina)
    {
        return MenuPrincipalImpl.guardarOficinaAnalista(idAnalista, nombre, apePat, apeMat, oficina);
    }
}
