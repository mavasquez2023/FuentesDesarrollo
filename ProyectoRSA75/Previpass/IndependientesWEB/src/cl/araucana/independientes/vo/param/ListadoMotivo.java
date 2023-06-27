package cl.araucana.independientes.vo.param;

import cl.araucana.independientes.dao.ParametrosDAO;

/* Clase ListadoGeográfico.
 * Contiene los metodos para acceder a los datos geográficos necesarios para el sistema.
 * */
public class ListadoMotivo {

    private static ListadoMotivo LISTA_UNICA = null;

    /*Declaracion de variables*/
    private Parametro[] listMotivo;
    private MotivoDesaf[] listDescMotivo;

    private ListadoMotivo(){
        this.listMotivo = ParametrosDAO.obtenerMotivo();
        this.listDescMotivo = ParametrosDAO.obtenerDescMotivo();
    }

    /*Inicializador*/
    public synchronized static ListadoMotivo getInstancia()
    {
        if (LISTA_UNICA == null){ 
            LISTA_UNICA = new ListadoMotivo();
        }	
        return LISTA_UNICA;
    }

    public MotivoDesaf[] getListDescMotivo() {
        return listDescMotivo;
    }

    public void setListDescMotivo(MotivoDesaf[] listDescMotivo) {
        this.listDescMotivo = listDescMotivo;
    }

    public Parametro[] getListMotivo() {
        return listMotivo;
    }

    public void setListMotivo(Parametro[] listMotivo) {
        this.listMotivo = listMotivo;
    }


}