package cl.araucana.independientes.dao;

//import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;

import cl.araucana.independientes.config.ConfiguracionSqlMap;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.vo.LinMantTabGlobVO;
import cl.araucana.independientes.vo.LinMantTabPerfVO;
import cl.araucana.independientes.vo.LinMantTabPromVO;
import cl.araucana.independientes.vo.LinMantTabTipoDocVO;
import cl.araucana.independientes.vo.LinMantTabBenefVO;
import cl.araucana.independientes.vo.LinMantTabDocsBenefsDinVO;
import cl.araucana.independientes.vo.LinMantTabRelBenefsDinDocsBenefsVO;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.TablaBenDinDocVO;
import cl.araucana.independientes.vo.TablaBeneficiosVO;
import cl.araucana.independientes.vo.TablaDocBenDinVO;
import cl.araucana.independientes.vo.TablasGlobalesVO;
import cl.araucana.independientes.vo.TablaPromotoresVO;
import cl.araucana.independientes.vo.TipoDocumentoVO;
import cl.araucana.independientes.vo.PerfilesVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

import com.ibatis.sqlmap.client.SqlMapClient;



public class MantenedoresDAO {

    //	--------------------------------  AQUI COMIENZAN LOS SELECT -------------------------------------//

    public LinMantTabGlobVO[] consultaMantTabGlob(){

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabGlobVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();
        Parametro[] estados = listParametros.getListEstados();
        Parametro[] tipoPagoAporte = listParametros.getListTipoPagoAporte();


        int codigo=0;
        int codigoTipoPago=0;
        int codigoEstado=0;

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabGlobNS.obtenerTabGlob",parametros);

            result = (LinMantTabGlobVO[])datos.toArray(new LinMantTabGlobVO[datos.size()]);
            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabGlobVO lineaTemp = result[i];

                    String entidad = lineaTemp.getEntidad();
                    codigo = Integer.parseInt(result[i].getEntidad());
                    if(codigo == 30){
                        String glosaTipoPago="";
                        codigoTipoPago = Integer.parseInt(lineaTemp.getGlosa());
                        glosaTipoPago = Helper.obtenerDescripcion(tipoPagoAporte, codigoTipoPago);
                        lineaTemp.setGlosa(glosaTipoPago);
                    }

                    entidad = Helper.obtenerDescripcion(entidades, codigo);
                    lineaTemp.setEntidad(entidad);	

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }
                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    //	--------------------------------  FILTRO POR ENTIDAD -------------------------------------//
    public LinMantTabGlobVO[] filtraMantTabGlob(String entidad){

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabGlobVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();
        Parametro[] estados = listParametros.getListEstados();
        Parametro[] tipoPagoAporte = listParametros.getListTipoPagoAporte();

        int codigo=0;
        int codigoTipoPago=0;
        int codigoEstado=0;

        TablasGlobalesVO tablasGlobalesVO = new TablasGlobalesVO();
        tablasGlobalesVO.setEntidad(Integer.parseInt(entidad));

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablasGlobalesVO);
            datos = sqlMap.queryForList("mantTabGlobNS.filtrarTabGlob",parametros);

            result = (LinMantTabGlobVO[])datos.toArray(new LinMantTabGlobVO[datos.size()]);
            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabGlobVO lineaTemp = result[i];

                    entidad = lineaTemp.getEntidad();
                    codigo = Integer.parseInt(result[i].getEntidad());
                    if(codigo == 30){
                        String glosaTipoPago="";
                        codigoTipoPago = Integer.parseInt(lineaTemp.getGlosa());
                        glosaTipoPago = Helper.obtenerDescripcion(tipoPagoAporte, codigoTipoPago);
                        lineaTemp.setGlosa(glosaTipoPago);
                    }

                    entidad = Helper.obtenerDescripcion(entidades, codigo);
                    lineaTemp.setEntidad(entidad);	

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }
                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabPerfVO[] consultaMantTabPerf() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabPerfVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        Parametro[] perfiles = listParametros.getListPerfiles();
        int codigoEstado=0;
        int codigoPerfil=0;
        String dv = "";

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabPerfNS.obtenerTabPerf",parametros);

            result = (LinMantTabPerfVO[])datos.toArray(new LinMantTabPerfVO[datos.size()]);
            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabPerfVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    String perfil = lineaTemp.getPerfil();
                    codigoPerfil = Integer.parseInt(result[i].getPerfil());
                    perfil = Helper.obtenerDescripcion(perfiles, codigoPerfil);
                    lineaTemp.setPerfil(perfil);

                    String rut = lineaTemp.getRut();
                    dv = Helper.digitoVerificadorRut(rut);
                    rut = Helper.separadorDeMiles(rut)+ " - " + dv;
                    lineaTemp.setRut(rut);

                    result[i]=lineaTemp;
                }
            }

            return result;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabPerfVO[] filtraMantTabPerf(String idDocumento) 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabPerfVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        Parametro[] perfiles = listParametros.getListPerfiles();
        int codigoEstado=0;
        int codigoPerfil=0;
        String dv = "";

        PerfilesVO perfilesVO = new PerfilesVO();
        perfilesVO.setIdDocumento(Integer.parseInt(idDocumento));

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", perfilesVO);

            datos = sqlMap.queryForList("mantTabPerfNS.filtrarTabPerf",parametros);

            result = (LinMantTabPerfVO[])datos.toArray(new LinMantTabPerfVO[datos.size()]);
            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabPerfVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    String perfil = lineaTemp.getPerfil();
                    codigoPerfil = Integer.parseInt(result[i].getPerfil());
                    perfil = Helper.obtenerDescripcion(perfiles, codigoPerfil);
                    lineaTemp.setPerfil(perfil);

                    String rut = lineaTemp.getRut();
                    dv = Helper.digitoVerificadorRut(rut);
                    rut = Helper.separadorDeMiles(rut)+ " - " + dv;
                    lineaTemp.setRut(rut);

                    result[i]=lineaTemp;
                }
            }

            return result;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabPromVO[] consultaMantTabProm() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabPromVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;
        String dv = "";
        String fechaBaja="";
        String fechaIni = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);



        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabPromNS.obtenerTabProm",parametros);

            result = (LinMantTabPromVO[])datos.toArray(new LinMantTabPromVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabPromVO lineaTemp = result[i];
                    String estado = lineaTemp.getEstado();

                    //formateo de fecha Inicio
                    fechaIni = lineaTemp.getFechaInicio();
                    try {
                        date = sdf1.parse(fechaIni);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    fechaIni = sdf2.format(date);
                    lineaTemp.setFechaInicio(sdf2.format(date));

                    //formateo de fecha Baja
                    fechaBaja = lineaTemp.getFechaBaja();

                    if (fechaBaja == null || fechaBaja.equals("01/01/1900")){
                        fechaBaja = "";
                        lineaTemp.setFechaBaja(fechaBaja);
                    }
                    else{
                        try {
                            date = sdf1.parse(fechaBaja);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fechaBaja = sdf2.format(date);
                        lineaTemp.setFechaBaja(sdf2.format(date));
                    }

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    String rut = lineaTemp.getRut();
                    dv = Helper.digitoVerificadorRut(rut);
                    rut = Helper.separadorDeMiles(rut)+ " - " + dv;
                    lineaTemp.setRut(rut);

                    result[i]=lineaTemp;
                }
            }
            return result;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    // FILTRO POR RUT DE LA TABLA PROMOTOR
    public LinMantTabPromVO[] filtraMantTabProm(String idCaptador) 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabPromVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;
        String dv = "";
        String fechaBaja="";

        String fechaIni = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        TablaPromotoresVO promotorVO = new TablaPromotoresVO();
        promotorVO.setIdCaptador(Integer.parseInt(idCaptador));


        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", promotorVO);
            datos = sqlMap.queryForList("mantTabPromNS.filtraTabProm",parametros);

            result = (LinMantTabPromVO[])datos.toArray(new LinMantTabPromVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabPromVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    // formateo de fecha Inicio
                    fechaIni = lineaTemp.getFechaInicio();
                    try {
                        date = sdf1.parse(fechaIni);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    fechaIni = sdf2.format(date);
                    lineaTemp.setFechaInicio(sdf2.format(date));

                    //formateo de fecha Baja
                    fechaBaja = lineaTemp.getFechaBaja();

                    if (fechaBaja == null || fechaBaja.equals("01/01/1900")){
                        fechaBaja = "";
                        lineaTemp.setFechaBaja(fechaBaja);
                    }
                    else{
                        try {
                            date = sdf1.parse(fechaBaja);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fechaBaja = sdf2.format(date);
                        lineaTemp.setFechaBaja(sdf2.format(date));
                    }

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    String rut = lineaTemp.getRut();
                    dv = Helper.digitoVerificadorRut(rut);
                    rut = Helper.separadorDeMiles(rut)+ " - " + dv;
                    lineaTemp.setRut(rut);

                    result[i]=lineaTemp;
                }
            }
            return result;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }




    public LinMantTabTipoDocVO[] consultaMantTabTipoDoc() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabTipoDocVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabTipoDocNS.obtenerTabTipoDoc",parametros);

            result = (LinMantTabTipoDocVO[])datos.toArray(new LinMantTabTipoDocVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabTipoDocVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;

        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabBenefVO[] consultaMantTabBenef() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabBenefVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;

        String fechaBaja="";
        String fechaIni = "";
        Date date = new Date();

        String DATE_FORMAT1 = "MM/dd/yyyy"; // El que se recibe de la BD
        String DATE_FORMAT2 = "dd/MM/yyyy"; // El que se envia a la Vista

        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);


        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabBenefNS.obtenerTabBenef",parametros);

            result = (LinMantTabBenefVO[])datos.toArray(new LinMantTabBenefVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabBenefVO lineaTemp = result[i];

                    //formateo de fecha Inicio
                    fechaIni = lineaTemp.getFechaIniValidez();
                    try {
                        date = sdf1.parse(fechaIni);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    fechaIni = sdf2.format(date);
                    lineaTemp.setFechaIniValidez(sdf2.format(date));

                    //formateo de fecha Baja
                    fechaBaja = lineaTemp.getFechaFinValidez();

                    if (fechaBaja == null || fechaBaja.equals("01/01/1900")){
                        fechaBaja = "";
                        lineaTemp.setFechaFinValidez(fechaBaja);
                    }
                    else{
                        try {
                            date = sdf1.parse(fechaBaja);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fechaBaja = sdf2.format(date);
                        lineaTemp.setFechaFinValidez(sdf2.format(date));
                    }


                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabDocsBenefsDinVO[] consultaMantTabDocsBenefsDin() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabDocsBenefsDinVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;

        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabDocsBenefsDinNS.obtenerTabDocsBenefsDin",parametros);

            result = (LinMantTabDocsBenefsDinVO[])datos.toArray(new LinMantTabDocsBenefsDinVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabDocsBenefsDinVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    public LinMantTabRelBenefsDinDocsBenefsVO[] consultaMantTabRelBenefsDinDocsBenefs() 
    {
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        LinMantTabRelBenefsDinDocsBenefsVO[] result = null;

        ListadoParametros listParametros = ListadoParametros.getInstancia();	
        Parametro[] estados = listParametros.getListEstados();
        int codigoEstado=0;


        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabRelBenefsDinDocsBenefsNS.obtenerTabRelBenefsDinDocsBenefs",parametros);

            result = (LinMantTabRelBenefsDinDocsBenefsVO[])datos.toArray(new LinMantTabRelBenefsDinDocsBenefsVO[datos.size()]);

            if(datos != null && datos.size() > 0){
                for(int i=0; i<result.length; i++){

                    LinMantTabRelBenefsDinDocsBenefsVO lineaTemp = result[i];

                    String estado = lineaTemp.getEstado();

                    if(lineaTemp.getEstado() == null){
                        codigoEstado = 0;
                    }else{
                        codigoEstado = Integer.parseInt(result[i].getEstado());
                    }

                    estado = Helper.obtenerDescripcion(estados, codigoEstado);
                    lineaTemp.setEstado(estado);

                    result[i]=lineaTemp;
                }
            }
            return result;
        }   
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return result;
    }

    //	--------------------------------  TERMINO DE LOS SELECT -------------------------------------//

    //--------------------------------  COMIENZO DE LOS INSERT  -------------------------------------//

    public static RespuestaVO saveDataGlobalTable(String glosa, String entidad){

        List datos = null;
        int resultado = 0;
        String estado = "1";
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablasGlobalesVO tablaGlobalvo = new TablasGlobalesVO();
        tablaGlobalvo.setGlosa(glosa);
        tablaGlobalvo.setEntidad(Integer.parseInt(entidad));
        tablaGlobalvo.setEstado(Integer.parseInt(estado));

        try{
            System.out.println("entidadd-------------------->"+entidad);
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaGlobalvo);
            datos = sqlMap.queryForList("mantTabGlobNS.obtenerMaxID", parametros);

            if(datos != null && datos.size() >= 0 ){

                resultado = Integer.parseInt((String)datos.get(0)) + 1;	
                tablaGlobalvo.setCodigo(resultado);

            }
            else{
                resp.setMsgRespuesta("Ocurrió un error al consultar por el Id Tabla Global.");
                resp.setCodRespuesta(96);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaGlobalvo);
            sqlMap.insert("mantTabGlobNS.insertNewTabGlob", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    public static RespuestaVO saveDataPerfiles(String rut, String perfil){

        String estado = "1";
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        int resultado = 0;

        PerfilesVO perfilesVO = new PerfilesVO();
        perfilesVO.setIdDocumento(Integer.parseInt(rut));
        perfilesVO.setIdPerfil(Integer.parseInt(perfil));
        perfilesVO.setEstado(Integer.parseInt(estado));

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", perfilesVO);
            datos = sqlMap.queryForList("mantTabPerfNS.obtenerCountPerfil", parametros);

            if(datos != null && datos.size() >= 0 ){

                resultado = Integer.parseInt((String)datos.get(0));

            }
            else{
                resp.setMsgRespuesta("Ocurrió un error al consultar por el RUT y Perfil.");
                resp.setCodRespuesta(96);
                return resp;
            }

            if (resultado ==  0) {
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", perfilesVO);
                sqlMap.insert("mantTabPerfNS.insertNewTabPerf", parametros);
                resp.setCodRespuesta(0);
                resp.setMsgRespuesta("Los datos fueron guardados correctamente.");
            }
            else{
                resp.setMsgRespuesta("Ya existe un registro en la tabla con los mismos valores de RUT y Perfil.");
                resp.setCodRespuesta(97);
            }

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al insertar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO savePromotorsTable(String rut, String numVerif, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni){

        int estado = 1;
        long idPromotor = 0;
        List secuenciaPromotor = null;

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        String fecBaja = "01/01/1900";
        List datos = null;
        int resultado = 0;

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", rut);
            datos = sqlMap.queryForList("mantTabPromNS.obtenerCountPromotor", parametros);

            if(datos != null && datos.size() >= 0 ){					
                resultado = Integer.parseInt((String)datos.get(0));
                if (resultado !=  0) {
                    resp.setMsgRespuesta("Ya existe un registro en la tabla con los mismos valores de RUT.");
                    resp.setCodRespuesta(97);
                    return resp;
                }
            }
            else{
                resp.setMsgRespuesta("Ocurrió un error al consultar por el RUT.");
                resp.setCodRespuesta(96);
                return resp;
            }

        }catch (SQLException e) {				
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al consultar por el RUT.");
            e.printStackTrace();
            return resp;
        }
        finally {

            try { 
                sqlMap.endTransaction(); 
            }catch (SQLException e){ 				
                e.printStackTrace();
            }
        }

        try {
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            secuenciaPromotor = sqlMap.queryForList("mantTabPromNS.selectSeqPromotor",parametros);
            idPromotor = Long.parseLong((String)secuenciaPromotor.get(0));
        }
        catch (SQLException e) {			
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al consultar por Id Promotor.");
            e.printStackTrace();
            return resp;
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }

        TablaPromotoresVO tablaPromotorvo = new TablaPromotoresVO();

        tablaPromotorvo.setIdPromotor(idPromotor);
        tablaPromotorvo.setIdCaptador(Integer.parseInt(rut));
        tablaPromotorvo.setDigVerificador(numVerif);
        tablaPromotorvo.setApellidoPaterno(apePat);
        tablaPromotorvo.setApellidoMaterno(apeMat);
        tablaPromotorvo.setNombres(nombres);
        tablaPromotorvo.setTipoOrgan(tipoOrg);
        tablaPromotorvo.setTipoCargo(tipoCargo);
        tablaPromotorvo.setCodArea(codAreaContacto);
        tablaPromotorvo.setNroTelefono(telContacto);
        tablaPromotorvo.setFechaInicio(fecIni);
        tablaPromotorvo.setFechaBaja(fecBaja);
        tablaPromotorvo.setEstPromot(estado);
        tablaPromotorvo.setGlosaCalle(calle);
        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaPromotorvo);
            sqlMap.insert("mantTabPromNS.insertNewPromotor", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron guardados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al insertar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO saveDataTipoDoc(String glosa, String obligatorio, String tipoSol){

        String estado = "1";
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        int resultado = 0;

        TipoDocumentoVO tipoDocumentoVO = new TipoDocumentoVO();
        tipoDocumentoVO.setGlosa(glosa);
        tipoDocumentoVO.setObligatorio(Integer.parseInt(obligatorio));
        tipoDocumentoVO.setEstado(Integer.parseInt(estado));
        tipoDocumentoVO.setTipoSol(Integer.parseInt(tipoSol));

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            datos = sqlMap.queryForList("mantTabTipoDocNS.obtenerMaxID", parametros);

            if(datos != null && datos.size() >= 0 ){

                resultado = Integer.parseInt((String)datos.get(0)) + 1;	
                tipoDocumentoVO.setCodigo(resultado);

            }
            else{
                resp.setMsgRespuesta("Ocurrió un error al consultar por el Id Tipo Documento.");
                resp.setCodRespuesta(96);
                return resp;
            }

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tipoDocumentoVO);
            sqlMap.insert("mantTabTipoDocNS.insertNewTabTipoDoc", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron guardados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al insertar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }



    public static RespuestaVO saveBenefTable(String glosaCorta, String glosa, String codCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante){

        int estado = 1;
        long idBeneficio = 0;
        List secuenciaBeneficio = null;

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();


        // --- obtengo secuencia de la tabla Beneficios en Dinero --- //
        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            secuenciaBeneficio = sqlMap.queryForList("mantTabBenefNS.selectSeqBenef",parametros);
            idBeneficio = Long.parseLong((String)secuenciaBeneficio.get(0));
            idBeneficio = idBeneficio + 1;
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }

        TablaBeneficiosVO tablaBeneficiovo = new TablaBeneficiosVO();

        tablaBeneficiovo.setIdBeneficio(idBeneficio);
        tablaBeneficiovo.setGlosaCorta(glosaCorta);
        tablaBeneficiovo.setGlosa(glosa);
        tablaBeneficiovo.setCodigoContable(codCont);
        tablaBeneficiovo.setTipoPago(tipoPago);
        tablaBeneficiovo.setValorPago(valPago);
        tablaBeneficiovo.setMontoPagarMax(montPagMax);
        tablaBeneficiovo.setCarencia(carencia);
        tablaBeneficiovo.setRecurrencia(recurrencia);
        tablaBeneficiovo.setPlazoCobro(plazoCobro);
        tablaBeneficiovo.setFechaIniValidez(fecIniVal);
        tablaBeneficiovo.setFechaFinValidez(fecFinVal);
        tablaBeneficiovo.setIsCausanteUnico(causante);
        tablaBeneficiovo.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaBeneficiovo);
            sqlMap.insert("mantTabBenefNS.insertNewBenef", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO saveDocBenefDinTable(String glosaCorta, String glosa){

        int estado = 1;
        long idDocBenDin = 0;
        List secuenciaDocBenDin = null;

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();


        // --- obtengo secuencia de la tabla Documentos de Beneficios en Dinero --- //
        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            secuenciaDocBenDin = sqlMap.queryForList("mantTabDocsBenefsDinNS.selectSeqDocBenDin",parametros);
            idDocBenDin = Long.parseLong((String)secuenciaDocBenDin.get(0));

        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }

        TablaDocBenDinVO tablaDocBenDinvo = new TablaDocBenDinVO();

        tablaDocBenDinvo.setIdDocBenDin(idDocBenDin);
        tablaDocBenDinvo.setGlosaCorta(glosaCorta);
        tablaDocBenDinvo.setGlosa(glosa);
        tablaDocBenDinvo.setEstado(estado);


        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaDocBenDinvo);
            sqlMap.insert("mantTabDocsBenefsDinNS.insertNewBenef", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    public static RespuestaVO saveBenDinDocTable(long beneficio, long documento, int obligatorio){

        int estado = 1;
        long idBenDinDoc = 0;
        List secuenciaBenDinDoc = null;

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();


        // --- obtengo secuencia de la tabla Documentos de Beneficios en Dinero --- //
        try {

            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            secuenciaBenDinDoc = sqlMap.queryForList("mantTabRelBenefsDinDocsBenefsNS.selectSeqBenDinDoc",parametros);
            idBenDinDoc = Long.parseLong((String)secuenciaBenDinDoc.get(0));

        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }

        TablaBenDinDocVO tablaBenDinDocVO = new TablaBenDinDocVO();

        tablaBenDinDocVO.setIdRelBeneficioDoc(idBenDinDoc);
        tablaBenDinDocVO.setIdBeneficio(beneficio);
        tablaBenDinDocVO.setIdDocBeneficio(documento);
        tablaBenDinDocVO.setEstado(estado);
        tablaBenDinDocVO.setIsObligatorio(obligatorio);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaBenDinDocVO);
            sqlMap.insert("mantTabRelBenefsDinDocsBenefsNS.insertNewBenDinDoc", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }



    //	--------------------------------  AQUI TERMINAN LOS INSERT  -------------------------------------//


    //--------------------------------  AQUI COMIENZAN LOS UPDATE  -------------------------------------//

    public static RespuestaVO modifDataGlobalTable(String codigo, String glosa, String entidad){

        String estado = "1";
        int entidad_aux;
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();

        entidad_aux = Helper.obtenerCodigo(entidades,entidad);

        TablasGlobalesVO tablaGlobalvo = new TablasGlobalesVO();
        tablaGlobalvo.setCodigo(Integer.parseInt(codigo));
        tablaGlobalvo.setGlosa(glosa);
        tablaGlobalvo.setEntidad(entidad_aux);
        tablaGlobalvo.setEstado(Integer.parseInt(estado));

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaGlobalvo);
            sqlMap.insert("mantTabGlobNS.updateMantTabGlob", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    public static RespuestaVO cambiarEstadoGlobalTable(String codigo, String entidad, int estado){

        int entidad_aux;
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        ListadoParametros listParametros = ListadoParametros.getInstancia();
        Parametro[] entidades = listParametros.getListEntidades();

        entidad_aux = Helper.obtenerCodigo(entidades,entidad);

        TablasGlobalesVO tablaGlobalvo = new TablasGlobalesVO();
        tablaGlobalvo.setCodigo(Integer.parseInt(codigo));
        tablaGlobalvo.setEntidad(entidad_aux);
        tablaGlobalvo.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tablaGlobalvo);
            sqlMap.insert("mantTabGlobNS.cambiarEstadoMantTabGlob", parametros);
            resp.setCodRespuesta(0);

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO modifDataPerfiles(String rut, String perfil, String perfilInicial){

        String estado = "1";
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        List datos = null;
        int resultado = 0;

        PerfilesVO perfilesVO = new PerfilesVO();
        perfilesVO.setIdDocumento(Integer.parseInt(rut));
        perfilesVO.setIdPerfil(Integer.parseInt(perfil));
        perfilesVO.setEstado(Integer.parseInt(estado));

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", perfilesVO);
            datos = sqlMap.queryForList("mantTabPerfNS.obtenerCountPerfil", parametros);

            if(datos != null && datos.size() >= 0 ){

                resultado = Integer.parseInt((String)datos.get(0));

            }
            else{
                resp.setMsgRespuesta("Ocurrió un error al consultar por el RUT y Perfil.");
                resp.setCodRespuesta(96);
                return resp;
            }

            if (resultado ==  0) {	
                parametros = new HashMap();
                parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
                parametros.put("input", perfilesVO);
                parametros.put("perfil", new Integer(perfilInicial));
                sqlMap.insert("mantTabPerfNS.updateMantTabPerf", parametros);
                resp.setCodRespuesta(0);
                resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            }
            else{
                resp.setMsgRespuesta("Ya existe un registro en la tabla con los mismos valors de RUT y Perfil.");
                resp.setCodRespuesta(97);
            }
            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }



    public static RespuestaVO cambiarEstadoPerfiles(String rut, String perfil, int estado){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        PerfilesVO perfilesVO = new PerfilesVO();
        perfilesVO.setIdDocumento(Integer.parseInt(rut));
        perfilesVO.setIdPerfil(Integer.parseInt(perfil));
        perfilesVO.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", perfilesVO);
            sqlMap.insert("mantTabPerfNS.cambiarEstadoMantTabPerf", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Estado cambiado correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO modifDataPromotor(String rut, String apePat, String apeMat, String nombres, String tipoOrg, String tipoCargo, String calle, String codAreaContacto, String telContacto, String fecIni){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaPromotoresVO promotorVO = new TablaPromotoresVO();
        promotorVO.setIdCaptador(Integer.parseInt(rut));
        promotorVO.setApellidoPaterno(apePat);
        promotorVO.setApellidoMaterno(apeMat);
        promotorVO.setNombres(nombres);
        promotorVO.setTipoOrgan(tipoOrg);
        promotorVO.setTipoCargo(tipoCargo);
        promotorVO.setGlosaCalle(calle);
        promotorVO.setCodArea(codAreaContacto);
        promotorVO.setNroTelefono(telContacto);
        promotorVO.setFechaInicio(fecIni);


        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", promotorVO);
            sqlMap.insert("mantTabPromNS.updateMantTabProm", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO cambiarEstadoPromotor(String rut, int estado, String fecha){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaPromotoresVO promotorVO = new TablaPromotoresVO();
        promotorVO.setIdCaptador(Integer.parseInt(rut));
        promotorVO.setEstPromot(estado);
        promotorVO.setFechaBaja(fecha);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", promotorVO);
            if (estado == 0){
                sqlMap.insert("mantTabPromNS.cambiarEstadoMantTabPromBaja", parametros);
                resp.setCodRespuesta(0);
                resp.setMsgRespuesta("Estado cambiado correctamente.");
            }
            else{
                sqlMap.insert("mantTabPromNS.cambiarEstadoMantTabProm", parametros);
                resp.setCodRespuesta(0);
                resp.setMsgRespuesta("Estado cambiado correctamente.");
            }


            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO modifDataTipoDoc(String codigo, String glosa, String obligatorio, String tipoSol){

        String estado = "1";
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TipoDocumentoVO tipoDocumentoVO = new TipoDocumentoVO();
        tipoDocumentoVO.setCodigo(Integer.parseInt(codigo));
        tipoDocumentoVO.setGlosa(glosa);
        tipoDocumentoVO.setObligatorio(Integer.parseInt(obligatorio));
        tipoDocumentoVO.setEstado(Integer.parseInt(estado));
        tipoDocumentoVO.setTipoSol(Integer.parseInt(tipoSol));

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tipoDocumentoVO);
            sqlMap.insert("mantTabTipoDocNS.updateMantTabTipoDoc", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }



    public static RespuestaVO cambiarEstadoTipoDoc(String codigo, int estado){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TipoDocumentoVO tipoDocumentoVO = new TipoDocumentoVO();
        tipoDocumentoVO.setCodigo(Integer.parseInt(codigo));
        tipoDocumentoVO.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", tipoDocumentoVO);
            sqlMap.insert("mantTabTipoDocNS.cambiarEstadoMantTabTipoDoc", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Estado cambiado correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO modifDataBenef(String idBeneficio, String glosaCorta, String glosa, String codCont, String tipoPago, String valPago, String montPagMax, String carencia, String recurrencia, String plazoCobro, String fecIniVal, String fecFinVal, String causante){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaBeneficiosVO beneficioVO = new TablaBeneficiosVO();
        beneficioVO.setIdBeneficio(Integer.parseInt(idBeneficio));
        beneficioVO.setGlosaCorta(glosaCorta);
        beneficioVO.setGlosa(glosa);
        beneficioVO.setCodigoContable(codCont);
        beneficioVO.setTipoPago(tipoPago);
        beneficioVO.setValorPago(valPago);
        beneficioVO.setMontoPagarMax(montPagMax);
        beneficioVO.setCarencia(carencia);
        beneficioVO.setRecurrencia(recurrencia);
        beneficioVO.setPlazoCobro(plazoCobro);
        beneficioVO.setFechaIniValidez(fecIniVal);
        beneficioVO.setFechaFinValidez(fecFinVal);
        beneficioVO.setIsCausanteUnico(causante);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", beneficioVO);
            sqlMap.insert("mantTabBenefNS.updateMantTabBenef", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    public static RespuestaVO cambiarEstadoBenef(String idBeneficio, int estado){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaBeneficiosVO beneficioVO = new TablaBeneficiosVO();
        beneficioVO.setIdBeneficio(Integer.parseInt(idBeneficio));
        beneficioVO.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", beneficioVO);
            sqlMap.insert("mantTabBenefNS.cambiarEstadoMantTabBenef", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Estado cambiado correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO modifDataDocBenDin(String idDocBenDin, String glosaCorta, String glosa){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaDocBenDinVO docBenDinVO = new TablaDocBenDinVO();
        docBenDinVO.setIdDocBenDin(Integer.parseInt(idDocBenDin));
        docBenDinVO.setGlosaCorta(glosaCorta);
        docBenDinVO.setGlosa(glosa);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", docBenDinVO);
            sqlMap.insert("mantTabDocsBenefsDinNS.updateMantTabDocBenDin", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO cambiarEstadoDocBenDin(String idDocBenDin, int estado){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaDocBenDinVO docBenDinVO = new TablaDocBenDinVO();
        docBenDinVO.setIdDocBenDin(Integer.parseInt(idDocBenDin));
        docBenDinVO.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", docBenDinVO);
            sqlMap.insert("mantTabDocsBenefsDinNS.cambiarEstadoMantTabDocBenDin", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Estado cambiado correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    public static RespuestaVO modifDataBenDinDoc(long idBenDinDoc, long beneficio, long documento, int obligatorio){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaBenDinDocVO benDinDocVO = new TablaBenDinDocVO();
        benDinDocVO.setIdRelBeneficioDoc(idBenDinDoc);
        benDinDocVO.setIdBeneficio(beneficio);
        benDinDocVO.setIdDocBeneficio(documento);
        benDinDocVO.setIsObligatorio(obligatorio);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", benDinDocVO);
            sqlMap.insert("mantTabRelBenefsDinDocsBenefsNS.updateMantTabBenDinDoc", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Los datos fueron modificados correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al modificar los datos.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }


    public static RespuestaVO cambiarEstadoBenDinDoc(String idBenDinDoc, int estado){

        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        TablaBenDinDocVO benDinDocVO = new TablaBenDinDocVO();
        benDinDocVO.setIdRelBeneficioDoc(Integer.parseInt(idBenDinDoc));
        benDinDocVO.setEstado(estado);

        try{
            sqlMap.startTransaction(0);
            HashMap parametros = new HashMap();

            parametros = new HashMap();
            parametros.put("IIDTA", Helper.getVarPorAmbiente(IND_Constants.Libreria_IIDTA));
            parametros.put("input", benDinDocVO);
            sqlMap.insert("mantTabRelBenefsDinDocsBenefsNS.cambiarEstadoMantTabBenDinDoc", parametros);
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta("Estado cambiado correctamente.");

            return resp;
        }   
        catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta("Ocurrió un problema al cambiar el estado.");
            e.printStackTrace();
        }
        finally {

            try { sqlMap.endTransaction(); 
            }catch (SQLException e) { 

                e.printStackTrace();
            }
        }
        return resp;
    }

    //	--------------------------------  AQUI TERMINAN LOS UPDATE  -------------------------------------//


}	