package cl.araucana.independientes.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.config.ConfiguracionSqlMapCajas;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.AgrupacionVO;
import cl.araucana.independientes.vo.param.Beneficio;
import cl.araucana.independientes.vo.param.DocBeneficio;
import cl.araucana.independientes.vo.param.Documento;
import cl.araucana.independientes.vo.param.Geografico;
import cl.araucana.independientes.vo.param.MotivoDesaf;
import cl.araucana.independientes.vo.param.Parametro;

import com.ibatis.sqlmap.client.SqlMapClient;

/* Implementación de la clase ParametrosDAO.
 * Posee la implementación de la comunicación entre la aplicación y la base de datos, mediante un intermediario xml
 * que contiene el mapeo de todas las consultas.
 * */
public class ParametrosDAO {

    /* Función que obtiene los datos parametricos de la base de datos.
     * Recibe como entrada el tipo de parametro al que se desea acceder
     * retorna un arreglo de parametros con la informacion requerida.*/
    public static Parametro[] obtenerParametroList(String tipo){

    	System.out.println("Entrando a obtenerParametroList");
    	
        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tipo);
            datos = sqlMap.queryForList("parametrosNS.obtenerParametricos", parametros);

            System.out.println("Parámetro tamaño retornado = " + datos.size());
            
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) {
                e.printStackTrace(); }

        }
        System.out.println("Parámetro tamaño retornado = POR DEFECTO");
        return salida;
    }

    public static String obtenerParametroUnico(String tipo)
    {
    	System.out.println("eNTRO EN obtenerParametroUnico");
    	
        String resultado = "";
        Parametro[] lista = obtenerParametroList(tipo);

        resultado = lista[0].getGlosa();

        System.out.println("Retorno = " + String.valueOf(resultado));
        
        return resultado;
    }

    /*Función que obtiene los datos geográficos.
     * Retorna un arreglo de tipo Geográfico con los datos requeridos.*/
    public static Geografico[] obtenerGeografico(){

        List datos = null;
        Geografico[] salida = new Geografico[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los datos geográficos.
            HashMap parametros = new HashMap();
            parametros.put("AFDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_AFDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerGeograficos", parametros);

            return (Geografico[]) datos.toArray(new Geografico[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /* Función que obtiene las regiones.
     * Retorna un arreglo de tipo Parametros con el listado de las regiones.*/
    public static Parametro[] obtenerRegiones(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener las regiones.
            HashMap parametros = new HashMap();
            parametros.put("AFDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_AFDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerRegiones", parametros);

            //retorna el arreglo con las regiones.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /*Función que obtiene las oficinas.
     * Retorna un arreglo de tipo Parametro con los datos de las oficinas. (id, glosa)*/
    public static Parametro[] obtenerOficinas(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener las oficinas.
            HashMap parametros = new HashMap();
            parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerOficinas", parametros);

            //retorna el arreglo con las oficinas.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    //   PARA DESAFILIACION
    public static MotivoDesaf[] obtenerDescMotivo(){

        List datos = null;
        MotivoDesaf[] salida = new MotivoDesaf[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los datos geográficos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerDescMotivos", parametros);
            return (MotivoDesaf[]) datos.toArray(new MotivoDesaf[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;

    }
    //PARA DESAFILIACION
    /* Función que obtiene las regiones.
     * Retorna un arreglo de tipo Parametros con el listado de las regiones.*/
    public static Parametro[] obtenerMotivo(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener las regiones.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerMotivos", parametros);
            //retorna el arreglo con las regiones.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }




    /*Función que obtiene los tipos de MANTENEDORES.
     * Retorna un arreglo de tipo Parametro con los datos de los mantenedores. (codigo, glosa)*/
    public static Parametro[] obtenerMantenedores(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerMantenedores", parametros);

            //retorna el arreglo con las oficinas.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }


    /*Función que obtiene los tipos de ENTIDADES.
     * Retorna un arreglo de tipo Parametro con los datos de los mantenedores. (codigo, glosa)*/
    public static Parametro[] obtenerEntidades(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerEntidades", parametros);

            //retorna el arreglo con las entidades.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /*Función que obtiene los tipos de ENTIDADES.
     * Retorna un arreglo de tipo Parametro con los datos de los mantenedores. (codigo, glosa)*/
    public static Parametro[] obtenerGlosaDocBeneficio(){

        List datos = null;
        Parametro[] salida = new Parametro[0];
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerGlosaDocBeneficio", parametros);
//          Parametro param = (Parametro)datos.get(0);
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida;
    }


    /*Función que obtiene los tipos de ENTIDADES.
     * Retorna un arreglo de tipo Parametro con los datos de los mantenedores. (codigo, glosa)*/
    public static Parametro[] obtenerGlosaBeneficio(){

        List datos = null;
        Parametro[] salida = new Parametro[0];
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerGlosaBeneficio", parametros);
//          Parametro param = (Parametro)datos.get(0);
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida;
    }


    public static Parametro[] obtenerGlosaCortaDocBeneficio(){

        List datos = null;
        Parametro[] salida = new Parametro[0];
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerGlosaCortaDocBeneficio", parametros);
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida;
    }


    /*Función que obtiene los tipos de ENTIDADES.
     * Retorna un arreglo de tipo Parametro con los datos de los mantenedores. (codigo, glosa)*/
    public static Parametro[] obtenerGlosaCortaBeneficio(){

        List datos = null;
        Parametro[] salida = new Parametro[0];
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los mantenedores.
            HashMap parametros = new HashMap();
            /*parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));*/
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerGlosaCortaBeneficio", parametros);
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }
        }
        return salida;
    }


    /*Función que obtiene las cajas.
     * Retorna un arreglo de tipo Parametro con los datos de las cajas.*/
    public static Parametro[] obtenerCajas(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMapCajas.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            // Se hace la consulta para obtener las cajas.
            HashMap parametros = new HashMap();
            parametros.put("CPEDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CPEDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerCajas", parametros);

            //retorna el arreglo con las cajas.
            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /* Función que obtiene la fecha del sistema.
     * Retorna la fecha parseada para ser usada en el sistema.
     */
    public static String obtenerFechaSistema(){

        List datos = null;
        String salida = "";
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //realiza una consulta para obtener la fecha de sistema desde la base de datos.
            datos = sqlMap.queryForList("parametrosNS.obtenerFechaSistema");//No requiere parametrizacion.

            fecha = (String)datos.get(0);
            date = sdf1.parse(fecha);
            salida = sdf2.format(date);
            
            System.out.println("Fecha salida = " + String.valueOf(date));

            //retorna la fecha parseada.
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }


    /* Función que obtiene la hora del sistema.
     */
    public static String obtenerHoraSistema(){

        List datos = null;
        String salida = "";

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //realiza una consulta para obtener la hora de sistema desde la base de datos.
            datos = sqlMap.queryForList("parametrosNS.obtenerHoraSistema");//No requiere parametrizacion.

            salida = (String)datos.get(0);
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /*Función que obtiene los perfiles
     * Recibe como entrada un id.
     * Retorna el perfil asociado al id.*/
    public static String obtenerPerfil(String ID){

        List datos = null;
        String salida = "0";

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener el perfil
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", ID);
            datos = sqlMap.queryForList("parametrosNS.obtenerPerfil", parametros);

            //verifica que la consultra traiga los datos requeridos
            if(datos != null && datos.size() > 0){
                //Se guarda el resultado de la consulta en la variable salida
                salida = ((String)datos.get(0));
            }
            //retorna el perfil asociado al id.
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }



    public static String obtenerPerfiles(String ID){
    	
    	System.out.println("Entro en obtenerPerfiles");
        List datos = null;
        String  salida = "";

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);			
            //Se hace la consulta para obtener el perfil
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", ID);
            datos = sqlMap.queryForList("parametrosNS.obtenerPerfil", parametros);
            //verifica que la consultra traiga los datos requeridos
            
            System.out.println("................................................");    
            System.out.println("Tamaño perfil = " + parametros.size());

            for (int i = 0 ; i < datos.size(); i ++){
                salida = salida + ((String)datos.get(i));
                if (i + 1 < datos.size()){
                    salida = salida + "|" ;
                }
            }
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }		 
        }
        return salida;		
    }

    public static Parametro[] obtenerTipoDocumentoList(){

        List datos = null;
        Parametro[] salida = new Parametro[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerDocumentosParam", parametros);

            return (Parametro[]) datos.toArray(new Parametro[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static Documento[] obtenerTipoDocumentoListFull(){

        List datos = null;
        Documento[] salida = new Documento[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerDocumentosParamFull", parametros);
            return (Documento[]) datos.toArray(new Documento[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static Documento[] obtenerTipoDocumentoListDesafiliacion(){

        List datos = null;
        Documento[] salida = new Documento[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerDocumentosParamDesafiliacion", parametros);
            return (Documento[]) datos.toArray(new Documento[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /**
     * Obtiene los tipos de agrupacion desde la tabla IIDTA.TIPOAGRUPACION
     * @return
     */
    public static AgrupacionVO[] obtenerAgrupacion(){

        List datos = null;
        AgrupacionVO[] salida = new AgrupacionVO[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los paramétricos.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.selectAgrupacion",parametros);
            return (AgrupacionVO[]) datos.toArray(new AgrupacionVO[datos.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    public static Collection mesVigente(String fechaEmision) throws Exception{
        Collection salida=new ArrayList();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {			
            sqlMap.startTransaction(0);			
            HashMap parametros = new HashMap();			
            parametros.put("CMDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_CMDTA));			
            parametros.put("input", fechaEmision);			
            salida=new ArrayList(sqlMap.queryForList("parametrosNS.getMesVigente",parametros));					
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { 
                sqlMap.endTransaction(); 
            } catch (SQLException e) { 
                e.printStackTrace();
            }		 
        }
        return salida;		
    }

    public static Beneficio[] obtenerBeneficioListFull(Parametro[] listBeneficios)
    {
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy";
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        List datos = null;
        Beneficio[] salida = new Beneficio[0];
        DocBeneficio[] documentos = new DocBeneficio[0];

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //Se hace la consulta para obtener los beneficios.
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("parametrosNS.obtenerBeneficiosParamFull", parametros);
            salida =  (Beneficio[]) datos.toArray(new Beneficio[datos.size()]);

            for(int i = 0; i < salida.length ; i++){

                Beneficio benTemp = salida[i];

                //Formateo de fecha
                String fechaTempIni = benTemp.getStrFechaIniValidez();
                date = sdf1.parse(fechaTempIni);
                benTemp.setFechaIniValidez(date);
                benTemp.setStrFechaIniValidez(sdf2.format(date));

                String fechaTempFin = benTemp.getStrFechaFinValidez();
                date = sdf1.parse(fechaTempFin);
                benTemp.setFechaFinValidez(date);
                benTemp.setStrFechaFinValidez(sdf2.format(date));

                //Tipo Beneficio
                benTemp.setStrTipoBeneficio(Helper.obtenerDescripcion(listBeneficios, benTemp.getTipoBeneficio()));

                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", new Integer(benTemp.getIdBeneficio()));
                datos = sqlMap.queryForList("parametrosNS.obtenerDocBeneficiosFull", parametros);
                documentos =  (DocBeneficio[]) datos.toArray(new DocBeneficio[datos.size()]);

                benTemp.setListaDocumentosBeneficio(documentos);

                salida[i] = benTemp;
            }

            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }

    /* JLGN 13-02-2012 
     * Función que obtiene la fecha del mes anterior.
     * Retorna la fecha parseada para ser usada en el sistema.
     */
    public static String obtenerFechaSistemaMesAnterior(){

        List datos = null;
        String salida = "";
        String fecha = "";
        Date date = new Date();

        String DATE_FORMAT1 = "dd/MM/yy";
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            //realiza una consulta para obtener la fecha de sistema desde la base de datos.
            datos = sqlMap.queryForList("parametrosNS.obtenerFechaSistemaMesAnterior");//No requiere parametrizacion.

            fecha = (String)datos.get(0);
            date = sdf1.parse(fecha);
            salida = sdf2.format(date);

            //retorna la fecha parseada.
            return salida;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); } catch (SQLException e) { 
                e.printStackTrace(); }

        }
        return salida;
    }
}
