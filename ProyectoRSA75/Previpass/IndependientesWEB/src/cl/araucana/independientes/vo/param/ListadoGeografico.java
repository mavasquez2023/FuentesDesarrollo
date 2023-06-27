package cl.araucana.independientes.vo.param;

import cl.araucana.independientes.dao.ParametrosDAO;

/* Clase ListadoGeográfico.
 * Contiene los metodos para acceder a los datos geográficos necesarios para el sistema.
 * */
public class ListadoGeografico {

    private static ListadoGeografico LISTA_UNICA = null;

    /*Declaracion de variables*/
    private Geografico[] listGeografico;
    private Parametro[] listRegiones;

    private ListadoGeografico(){

        this.listGeografico = ParametrosDAO.obtenerGeografico();
        this.listRegiones = ParametrosDAO.obtenerRegiones();
    }

    /*Inicializador*/
    public synchronized static ListadoGeografico getInstancia()
    {
        if (LISTA_UNICA == null){ 
            LISTA_UNICA = new ListadoGeografico();
        }	
        return LISTA_UNICA;
    }

    /*Creación de Getting and Setting de clase ListadoGeografico*/	
    public Geografico[] getListGeografico() {
        return listGeografico;
    }

    public void setListGeografico(Geografico[] listGeografico) {
        this.listGeografico = listGeografico;
    }

    public Parametro[] getListRegiones() {
        return listRegiones;
    }

    public void setListRegiones(Parametro[] listRegiones) {
        this.listRegiones = listRegiones;
    }

}
