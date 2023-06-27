package cl.araucana.independientes.struts.dwr.actions;

import java.util.ArrayList;

import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.AgrupacionVO;
import cl.araucana.independientes.vo.param.Beneficio;
import cl.araucana.independientes.vo.param.Geografico;
import cl.araucana.independientes.vo.param.ListadoMotivo;
import cl.araucana.independientes.vo.param.MotivoDesaf;
import cl.araucana.independientes.vo.param.ListadoGeografico;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementación de la clase GeográficoDWR. 
 * Contiene las funciones necesarias que permiten cargar los datos geográficos usados en el sietema
 */

public class GeograficoDWR {

    /*Función que permite cargar las provincias en el sistema.
     * Recibe como parámetro el id de la Región.
     * Retorna un arreglo de tipo parametro con las provincias asociadas a esa Región.
     */
    public Parametro[] cargarProvincias(int region){

        ListadoGeografico listaGeo = ListadoGeografico.getInstancia();
        Geografico[] listaCompleta = listaGeo.getListGeografico();	
        ArrayList listaFiltrada = new ArrayList();
        ArrayList listaCodigos = new ArrayList();
        Parametro param = new Parametro();
        Integer codigo = new Integer(-1);

        for(int i=0; i<listaCompleta.length; i++){

            if (listaCompleta[i].getIdRegion() == region){

                codigo = new Integer(listaCompleta[i].getIdProvincia());

                if(!listaCodigos.contains(codigo)){
                    listaCodigos.add(codigo);
                    param = new Parametro();
                    param.setCodigo(listaCompleta[i].getIdProvincia());
                    param.setGlosa(listaCompleta[i].getDesProvincia());
                    listaFiltrada.add(param);
                }

            }

        }

        Parametro[] salida = (Parametro[]) listaFiltrada.toArray(new Parametro[listaFiltrada.size()]);

        return salida;

    }

    /*Función que permite cargar las comunas en el sistema.
     * Recibe como parametro el id de la provincia
     * retorna un arreglo de tipo parametro con las comunas asociadas a esa provincia.
     */
    public Parametro[] cargarComunas(int provincia){

        ListadoGeografico listaGeo = ListadoGeografico.getInstancia();
        Geografico[] listaCompleta = listaGeo.getListGeografico();	
        ArrayList listaFiltrada = new ArrayList();
        ArrayList listaCodigos = new ArrayList();
        Parametro param = new Parametro();
        Integer codigo = new Integer(-1);

        for(int i=0; i<listaCompleta.length; i++){

            if (listaCompleta[i].getIdProvincia() == provincia){

                codigo = new Integer(listaCompleta[i].getIdComuna());

                if(!listaCodigos.contains(codigo)){
                    listaCodigos.add(codigo);
                    param = new Parametro();
                    param.setCodigo(listaCompleta[i].getIdComuna());
                    param.setGlosa(listaCompleta[i].getDesComuna());
                    listaFiltrada.add(param);
                }

            }

        }

        Parametro[] salida = (Parametro[]) listaFiltrada.toArray(new Parametro[listaFiltrada.size()]);

        return salida;

    }

    public Parametro[] obtenerLista (String lista)
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();	

        if(lista.equals(IND_Constants.lst_tipo_doc)){
            return listaParam.getListTipoDocumentosSol();

        }else if(lista.equals(IND_Constants.lst_est_doc)){
            return listaParam.getListEstadoDocumento();

        }else if(lista.equals(IND_Constants.lst_ent)){
            return listaParam.getListEntidades();

        }else if(lista.equals(IND_Constants.lst_tipo_pago)){
            return listaParam.getListTipoPagoAporte();

        }else if(lista.equals(IND_Constants.lst_tipo_pago_ben)){
            return listaParam.getListTipoPagoBeneficio();

        }else if(lista.equals(IND_Constants.lst_tipo_org)){
            return listaParam.getListTipoOrg();

        }else if(lista.equals(IND_Constants.lst_tipo_cargo)){
            return listaParam.getListTipoCargo();

        }else if(lista.equals(IND_Constants.lst_tipo_perf)){
            return listaParam.getListPerfiles();

        }else if(lista.equals(IND_Constants.lst_est)){
            return listaParam.getListEstados();

        }else if(lista.equals(IND_Constants.lst_benef)){
            return listaParam.getListGlosaCortaBeneficio();

        }else if(lista.equals(IND_Constants.lst_docs)){
            return listaParam.getListGlosaCortaDocBeneficio();

        }else if(lista.equals(IND_Constants.lst_tipo_sol)){
            return listaParam.getListGlosaTipoSol();

        }else if(lista.equals(IND_Constants.lst_oficinas)){
            return listaParam.getListOficina();	

        }else{	
            return null;
        }
    }

    public String obtenerTipoPago(){
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        return listaParam.getTxtSelTipoCalculoAporte();
    }

    public AgrupacionVO[] obtenerListaAgrupacion (String lista)
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();	

        if(lista.equals("ListAgrupacionFull")){
            return listaParam.getListAgrupacionFull();
        }else{	
            return null;
        }
    }

    public Beneficio[] obtenerListaBeneficios (String lista)
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        if(lista.equals("ListBeneficioFull")){
            return listaParam.getListBeneficioFull();
        }else{	
            return null;
        }
    }

    public Parametro[] cargarDescMotivo(int motivo){

        ListadoMotivo listaMot = ListadoMotivo.getInstancia();
        MotivoDesaf[] listaCompleta = listaMot.getListDescMotivo();
        ArrayList listaFiltrada = new ArrayList();
        ArrayList listaCodigos = new ArrayList();
        Parametro param = new Parametro();
        Integer codigo = new Integer(-1);

        for(int i=0; i<listaCompleta.length; i++){

            if (listaCompleta[i].getIdMotivo() == motivo){

                codigo = new Integer(listaCompleta[i].getIdDescMotivo());

                if(!listaCodigos.contains(codigo)){
                    listaCodigos.add(codigo);
                    param = new Parametro();
                    param.setCodigo(listaCompleta[i].getIdDescMotivo());
                    param.setGlosa(listaCompleta[i].getDesDescMotivo());
                    listaFiltrada.add(param);
                }				
            }
        }

        Parametro[] salida = (Parametro[]) listaFiltrada.toArray(new Parametro[listaFiltrada.size()]);

        return salida;
    }

}
