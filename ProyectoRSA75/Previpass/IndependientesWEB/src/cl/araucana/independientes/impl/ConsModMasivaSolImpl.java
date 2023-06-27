package cl.araucana.independientes.impl;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.independientes.dao.ConsModMasivaSolDAO;
import cl.araucana.independientes.dao.ModSolicitudDAO;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.ConsModMasivaSolVO;
import cl.araucana.independientes.vo.SolicitudVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class ConsModMasivaSolImpl {

    public ConsModMasivaSolVO consultaMasivaSolicitudes(String fechaInicio, String fechaFin, String estado, String usuario, String fechaActual) throws IOException,SQLException{

        ConsModMasivaSolDAO dao = new ConsModMasivaSolDAO();
        ConsModMasivaSolVO resp = new ConsModMasivaSolVO();

        resp.setLisConsModMasivaSol(dao.consultaMasivaSolicitudes(fechaInicio, fechaFin, estado));

        return resp;
    }

    /*funcion que obtiene idSolicitud*/
    public static SolicitudVO retornaIdSolcitud( String folio){

        return ConsModMasivaSolDAO.retornaIdSolcitud(folio);
    }


    /*Update estado de la solicitud*/
    public static int updateMasivaSolcitud(String folio, String tipoEstado, String rut){

        return ModSolicitudDAO.updateEstadoSol(folio, tipoEstado, rut);
    }

    /* Función que obtiene los estados posibles a los que esta asociado un determinado estado actual.
     * Recibe como entrada en estado actual
     * Retorna un arreglo de tipo Parametro con los estados asociados al estado actual.*/
    public static Parametro[] getEstadosDestinoPosibles(String estadoActual){

        int est = Integer.parseInt(estadoActual);

        ListadoParametros Listaparam = ListadoParametros.getInstancia();
        Parametro[] param = Listaparam.getListEstadoSolicitudAfiliacion();
        Parametro[] parametro = new Parametro[2];
        Parametro[] temp; 
        Parametro[] temp1;
        Parametro[] temp2;

        /*Filtro para Ingresada*/
        if(est == 1){
            temp = ConsModMasivaSolDAO.getEstadosDestinoPosibles(estadoActual);
            /*for(int i = 0; i< temp.length; i++){
				System.out.println("temp1[i]: " + temp[i].getGlosa() + "" + "codigo = " + temp[i].getCodigo());
			}*/
            int j = 0;
            for(int i=0; i<temp.length; i++){

                if(temp[i].getCodigo() == 2 || temp[i].getCodigo() == 4){

                    int estadoTemp = 0;
                    Parametro parametroTemp = new Parametro();

                    estadoTemp = temp[i].getCodigo();
                    parametroTemp.setGlosa(Helper.obtenerDescripcion(param, estadoTemp));
                    parametroTemp.setCodigo(estadoTemp);
                    parametro[j] = parametroTemp;
                    j++;
                }
            }
        }

        if(est == 4){
            /*Filtro para Incompleta*/
            temp1 = ConsModMasivaSolDAO.getEstadosDestinoPosibles(estadoActual);

            parametro = new Parametro[1];
            int k = 0;
            for(int i=0; i<temp1.length; i++){

                if(temp1[i].getCodigo() == 7)
                {
                    int estadoTemp = 0;
                    Parametro parametroTemp = new Parametro();

                    estadoTemp = temp1[i].getCodigo();
                    parametroTemp.setGlosa(Helper.obtenerDescripcion(param, estadoTemp));
                    parametroTemp.setCodigo(estadoTemp);
                    parametro[k] = parametroTemp;
                    k++;
                }
            }
        }

        /*Filtro para PreAprobada*/
        if(est == 2){
            /*Filtro para Incompleta*/
            temp2 = ConsModMasivaSolDAO.getEstadosDestinoPosibles(estadoActual);
            parametro = new Parametro[1];
            int k = 0;
            for(int i=0; i<temp2.length; i++)
            {
                if(temp2[i].getCodigo() == 7){
                    int estadoTemp = 0;
                    Parametro parametroTemp = new Parametro();

                    estadoTemp = temp2[i].getCodigo();
                    parametroTemp.setGlosa(Helper.obtenerDescripcion(param, estadoTemp));
                    parametroTemp.setCodigo(estadoTemp);
                    parametro[k] = parametroTemp;
                    k++;
                }
            }
        }

        if(est == 5 || est == 7){

            String estadActual = "3";
            return ConsModMasivaSolDAO.getEstadosDestinoPosibles(estadActual);
        }

        if(est == 3 || est == 6 || est == 8)
        {
            return ConsModMasivaSolDAO.getEstadosDestinoPosibles(estadoActual);				
        }
        else
        {
            return parametro;
        }
    }

    public static String obtenerUltimaDiaMes(String fechaActual){

        return Helper.obtenerUltimoDiaMes(fechaActual);
    }
}
