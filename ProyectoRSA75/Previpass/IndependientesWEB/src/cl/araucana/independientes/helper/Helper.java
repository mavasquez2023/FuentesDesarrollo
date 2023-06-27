package cl.araucana.independientes.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.dao.ParametrosDAO;
import cl.araucana.independientes.vo.DocumentoVO;
import cl.araucana.independientes.vo.MesVigenteVO;
import cl.araucana.independientes.vo.param.Beneficio;
import cl.araucana.independientes.vo.param.Documento;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementacion de la clase Helper. 
 * Contiene implementaciones de funciones utiles para ser usadas en todo el sistema.*/
public class Helper {

    /*Funcion que obtiene la descripcion de un parametro.
     * Recibe como entrada un arreglo de tipo parametro y un codigo.
     * Retorna la glosa asociada al codigo.*/
    public static String obtenerDescripcion(Parametro[] lista, int codigo)
    {
        for(int i=0; i < lista.length; i++){

            Parametro aux = new Parametro();
            aux = lista[i];

            if(aux.getCodigo() == codigo){

                return aux.getGlosa();
            }
        }
        return "";
    }

    public static int obtenerCodigo(Parametro[] lista, String descripcion)
    {
        for(int i=0; i < lista.length; i++)
        {
            Parametro aux = new Parametro();
            aux = lista[i];

            if((aux.getGlosa().trim()).equals(descripcion.trim()))
            {
                return aux.getCodigo();
            }
        }
        return -1;
    }

    public static String obtenerDatoBeneficio(int codigo, int opcion)
    {
        ListadoParametros listaParam = ListadoParametros.getInstancia();

        Beneficio[] lista = listaParam.getListBeneficioFull();

        for(int i=0; i < lista.length; i++)
        {
            Beneficio aux = new Beneficio();
            aux = lista[i];

            if(aux.getIdBeneficio() == codigo){

                switch(opcion){

                case 1 :	return aux.getGlosaBeneficio();

                case 2 :	return aux.getGlosaCortaBeneficio();

                case 3 :	return aux.getCodigoContable();

                default :	return "";
                }
            }
        }
        return "";
    }

//  TODO DGomez
    /*
	public static String obtenerDescripcionGlosa(Parametro[] lista, int codigo, int tabla){
		// TABLA = 1, ES TABLA LISTA BENEFICIOS
		// TABLA = 2, ES TABLA LISTA DOC_BENEFICIOS
		if(tabla == 1){
			for(int i=0; i < lista.length; i++){

				Parametro aux = new Parametro();
				aux = lista[i];
				if(aux.getIdBeneficio()== codigo){

					return aux.getGlosaCorta();
				}
			}
		}
		if(tabla == 2){
			for(int i=0; i < lista.length; i++){

				Parametro aux = new Parametro();
				aux = lista[i];
				if(aux.getIdDocBeneficio() == codigo){

					return aux.getGlosaCorta();
				}
			}
		}

		return "";
	}*/

    /*Funcion que obtiene la fecha de vigencia.*/
    public static String obtenerFechaVigencia(String fecha){

    	System.out.println("Entro en método obtenerFechaVigencia");
        String fechaRes = "";
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        String DATE_FORMAT = "dd/MM/yyyy"; // Formato en el que se debe recibir la cadena
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try
        {
            date = sdf.parse(fecha);
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 2);
            date = cal.getTime();
            fechaRes = sdf.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println("Retornando obtenerFechaVigencia = " + String.valueOf(fechaRes));

        return fechaRes;
    }

    public static void borrarArchivos(String user){

    	System.out.println("Entro al helper");
    	
        user = user + "_";

        File g = new File("");

        StringBuffer excelFile = new StringBuffer("");
        excelFile.append(g.getAbsolutePath());
        excelFile.append(IND_Constants.DIR_INF);

        System.out.println("Constante = " + String.valueOf(IND_Constants.DIR_INF));
        
        String dir = excelFile.toString();
        
        System.out.println("Dir de excelFile = " + dir);

        File f = new File(dir);

        File[] ficheros = f.listFiles();
        
        System.out.println("Tamaño de ficheros = " + ficheros.length);

        for(int i = 0; i<ficheros.length; i++){

            File temp = ficheros[i];

            if(user.equals(temp.getName().substring(0, user.length()))){

                temp.delete();
            }
        }

    }

    public static String digitoVerificadorRut(String strRut)
    {
        int rut = 0;
        int s = 0;
        String l_dv = "";

        rut = Integer.parseInt(strRut);

        if(strRut.trim() == "0"){
            return "0";
        }

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

    public static String separadorDeMiles(String cadena)
    {
        String resultado = "";
        int count = 0;

        for(int i = cadena.length(); i >= 1 ; i--){
            count++;

            if (count == 3){
                resultado = "." + cadena.charAt(i-1) + resultado;
                count = 0;
            }else{
                resultado = cadena.charAt(i-1) + resultado;
            }

        }

        if(resultado.charAt(0) == '.' && resultado.length() > 0){
            resultado = resultado.substring(1, resultado.length());
        }

        return resultado;
    }

    /*Metodo para obtener una variable parametrizada prio ambiente*/
    public static String getVarPorAmbiente(String var)
    {
        String retorno = "";
        GlobalProperties global = GlobalProperties.getInstance();
        String ambiente = global.getValorExterno("IND.properties.ambiente");

        retorno = global.getValor(ambiente + "." + var);

        return retorno;
    }

    /*Metodo para validar correos electrónicos.*/
    public static boolean isEmail(String correo) {        
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        }else{
            return false;
        }
    }

    public static DocumentoVO[] matchDocumentos(DocumentoVO[] existentes, Documento[] parametrizados){

        DocumentoVO[] resultado = new DocumentoVO[parametrizados.length];

        for(int i = 0; i<parametrizados.length; i++){

            DocumentoVO documentoVO = new DocumentoVO();

            documentoVO.setIdSecuenciaDocumento(0);
            documentoVO.setIdSolicitud(0);
            documentoVO.setEstadoDocumento(0);
            documentoVO.setObservacionesDocumento("");

            documentoVO.setTipoDocumento(parametrizados[i].getTipoDocumento());
            documentoVO.setGlosaTipoDocumento(parametrizados[i].getGlosaTipoDocumento());
            documentoVO.setAlta(parametrizados[i].getAlta());
            documentoVO.setObligatorio(parametrizados[i].getObligatorio());

            resultado[i] = documentoVO;
        }

        for(int j = 0; j<parametrizados.length; j++){

            for(int k = 0; k<existentes.length; k++){

                if(parametrizados[j].getTipoDocumento() == existentes[k].getTipoDocumento()){

                    resultado[j].setIdSecuenciaDocumento(existentes[k].getIdSecuenciaDocumento());
                    resultado[j].setIdSolicitud(existentes[k].getIdSolicitud());
                    resultado[j].setEstadoDocumento(existentes[k].getEstadoDocumento());
                    resultado[j].setObservacionesDocumento(existentes[k].getObservacionesDocumento());
                }
            }
        }

        return resultado;
    }

    /**
     * metodo al que se entregaran datos obtendidos desde BD VO y el estado del ultimo dia (1: habil)
     * @param diaHabil dia al que se debe revisar si es habil o no
     * @param vo mes consultado desde BD
     * @return valor del dia habil
     */
    public static int diaHabil(int diaHabil, MesVigenteVO vo){
        int diaHabil2=0;
        switch(diaHabil){
        case 1:diaHabil2=Integer.valueOf(vo.getDia_1()).intValue();break;
        case 2:diaHabil2=Integer.valueOf(vo.getDia_2()).intValue();break;
        case 3:diaHabil2=Integer.valueOf(vo.getDia_3()).intValue();break;
        case 4:diaHabil2=Integer.valueOf(vo.getDia_4()).intValue();break;
        case 5:diaHabil2=Integer.valueOf(vo.getDia_5()).intValue();break;
        case 6:diaHabil2=Integer.valueOf(vo.getDia_6()).intValue();break;
        case 7:diaHabil2=Integer.valueOf(vo.getDia_7()).intValue();break;
        case 8:diaHabil2=Integer.valueOf(vo.getDia_8()).intValue();break;
        case 9:diaHabil2=Integer.valueOf(vo.getDia_9()).intValue();break;
        case 10:diaHabil2=Integer.valueOf(vo.getDia_10()).intValue();break;
        case 11:diaHabil2=Integer.valueOf(vo.getDia_11()).intValue();break;
        case 12:diaHabil2=Integer.valueOf(vo.getDia_12()).intValue();break;
        case 13:diaHabil2=Integer.valueOf(vo.getDia_13()).intValue();break;
        case 14:diaHabil2=Integer.valueOf(vo.getDia_14()).intValue();break;
        case 15:diaHabil2=Integer.valueOf(vo.getDia_15()).intValue();break;
        case 16:diaHabil2=Integer.valueOf(vo.getDia_16()).intValue();break;
        case 17:diaHabil2=Integer.valueOf(vo.getDia_17()).intValue();break;
        case 18:diaHabil2=Integer.valueOf(vo.getDia_18()).intValue();break;
        case 19:diaHabil2=Integer.valueOf(vo.getDia_19()).intValue();break;
        case 20:diaHabil2=Integer.valueOf(vo.getDia_20()).intValue();break;
        case 21:diaHabil2=Integer.valueOf(vo.getDia_21()).intValue();break;
        case 22:diaHabil2=Integer.valueOf(vo.getDia_22()).intValue();break;		
        case 23:diaHabil2=Integer.valueOf(vo.getDia_23()).intValue();break;
        case 24:diaHabil2=Integer.valueOf(vo.getDia_24()).intValue();break;
        case 25:diaHabil2=Integer.valueOf(vo.getDia_25()).intValue();break;
        case 26:diaHabil2=Integer.valueOf(vo.getDia_26()).intValue();break;
        case 27:diaHabil2=Integer.valueOf(vo.getDia_27()).intValue();break;
        case 28:diaHabil2=Integer.valueOf(vo.getDia_28()).intValue();break;
        case 29:diaHabil2=Integer.valueOf(vo.getDia_29()).intValue();break;
        case 30:diaHabil2=Integer.valueOf(vo.getDia_30()).intValue();break;
        case 31:diaHabil2=Integer.valueOf(vo.getDia_31()).intValue();break;					
        }
        return diaHabil2;
    }		

    /**
     * metodo que obtiene desde base de dato el primer dia del mes siguiente.
     * @param fechaVigencia se entrega mes al que se le debe realizar calculo mencionado
     * @return retorna solo el dia habil
     * @throws Exception
     */
    public static String getPrimerDiaHabilMesSig(String fechaVigencia) throws Exception
    {
        String salida="";
        Collection col = new ArrayList();
        col = ParametrosDAO.mesVigente(fechaVigencia.substring(0,6));//formato fecha yyyyMMdd

        MesVigenteVO vo=new MesVigenteVO();

        if(col!=null){
            Iterator iter =col.iterator();
            while(iter.hasNext()){
                vo=new MesVigenteVO();
                vo=(MesVigenteVO)iter.next();
                if(vo!=null && vo.getDia_1()!=null){
                    break;
                }
            }
        }
        String dia=fechaVigencia.substring(6,8);
        int diaHabil1=Integer.parseInt(dia);
        int diaHabil2=diaHabil(diaHabil1, vo);

        if(diaHabil2==0){
            for(int i=diaHabil1;i<32;i++){
                if(diaHabil(i, vo)!=0){
                    diaHabil2=diaHabil(i, vo);
                    salida=(i)+"";
                    break;
                }
            }
        }else{
            salida=diaHabil1+"";			
        }
        return salida;
    }

    public static String obtenerUltimoDiaMes(String fechaActual){

        String fechaFinalMes = "";
        String DATE_FORMAT = "dd/MM/yyyy";
        Date dateActual = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String resp = "";
        try
        {
            dateActual = sdf.parse(fechaActual);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateActual);
        Date fecha = new Date();

        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        fecha = cal.getTime();
        fechaFinalMes = sdf.format(fecha);
        resp = fechaFinalMes;

        return resp;
    }

    public static String obtenerPrimerDiaMes(String fechaActual){

        String fechaFinalMes = "";
        String DATE_FORMAT = "dd/MM/yyyy";
        Date dateActual = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String resp = "";
        try
        {
            dateActual = sdf.parse(fechaActual);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateActual);
        Date fecha = new Date();

        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        fecha = cal.getTime();
        fechaFinalMes = sdf.format(fecha);
        resp = fechaFinalMes;

        return resp;
    }

    /**
     * metodo utilizado para obtener el primer dia habil del mes siguiente.
     * @param fechaVigente: fecha de vigencia del afiliado
     * @return resolucion de directorio o fecha de resolucion
     * @throws Exception
     */
    public static String primerDiaMesSiguiente(String fechaVigente) throws Exception
    {
        String salida = "";

        if(fechaVigente!=null && !"".equals(fechaVigente.trim()))
        {
            StringTokenizer st=new StringTokenizer(fechaVigente,"/");
            int dia=Integer.parseInt(st.nextToken());//dia 
            int mes=Integer.parseInt(st.nextToken());//indica el mes actual
            int año=Integer.parseInt(st.nextToken());//mes
            Calendar cal= Calendar.getInstance();
            cal.set(año, mes, dia);
            int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            cal.set(año, mes, minDay);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            salida=sdf1.format(cal.getTime());
            salida=getPrimerDiaHabilMesSig(salida);
            cal.set(año, mes, Integer.parseInt(salida));
            salida=sdf2.format(cal.getTime());			
        }
        return salida;
    }

    public static long diferenciaDeDias(Date fechaIni, Date fechaFin)
    {
        Calendar calendarIni = Calendar.getInstance();
        Calendar calendarFin = Calendar.getInstance();

        Calendar calendarFormatIni = Calendar.getInstance();
        Calendar calendarFormatFin = Calendar.getInstance();

        calendarIni.setTime(fechaIni);
        calendarFin.setTime(fechaFin);

        calendarFormatIni.set(calendarIni.get(Calendar.YEAR) , calendarIni.get(Calendar.MONTH), calendarIni.get(Calendar.DAY_OF_MONTH));
        calendarFormatFin.set(calendarFin.get(Calendar.YEAR) , calendarFin.get(Calendar.MONTH), calendarFin.get(Calendar.DAY_OF_MONTH));

        long milliseconds1 = calendarFormatIni.getTimeInMillis();
        long milliseconds2 = calendarFormatFin.getTimeInMillis();

        long diff = milliseconds2 - milliseconds1;

        //long diffSeconds = diff / 1000;
        //long diffMinutes = diff / (60 * 1000);
        //long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    public static long diferenciaDeDiasMesSiguiente(Date fechaIni, Date fechaFin)
    {
        Calendar calendarIni = Calendar.getInstance();
        Calendar calendarFin = Calendar.getInstance();

        Calendar calendarFormatIni = Calendar.getInstance();
        Calendar calendarFormatFin = Calendar.getInstance();

        calendarIni.setTime(fechaIni);
        calendarIni.add(Calendar.MONTH, 1);
        calendarFin.setTime(fechaFin);
        calendarFormatIni.set(calendarIni.get(Calendar.YEAR) , calendarIni.get(Calendar.MONTH), calendarIni.get(Calendar.DAY_OF_MONTH));
        calendarFormatFin.set(calendarFin.get(Calendar.YEAR) , calendarFin.get(Calendar.MONTH), calendarFin.get(Calendar.DAY_OF_MONTH));

        long milliseconds1 = calendarFormatIni.getTimeInMillis();
        long milliseconds2 = calendarFormatFin.getTimeInMillis();

        long diff = milliseconds2 - milliseconds1;

        //long diffSeconds = diff / 1000;
        //long diffMinutes = diff / (60 * 1000);
        //long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    //Se puede modificar para que reciba el JNDI por parámetro
    public static Connection getConnection() 
    {
        Connection connection = null;

        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(IND_Constants.JNDI_Independientes);
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static String paddingString(String s, int n, char c, boolean paddingLeft) {

        if (s == null) 
        {
            char[] ch2 = new char[n];
            Arrays.fill(ch2, c);
            String resp = new String(ch2);
            return resp;	
        }

        int add = n - s.length();
        if(add <= 0){
            return s;
        }

        StringBuffer str = new StringBuffer(s);
        char[] ch = new char[add];
        Arrays.fill(ch, c);
        if(paddingLeft){
            str.insert(0, ch);
        }else{
            str.append(ch);
        }

        return str.toString();
    }

    /*Función que elimina un archivo dada la ruta especifica.*/
    public static int deleteFile(String rutaArchivo)
    {
        File file = new File(rutaArchivo);

        if(file.delete())
        {
            return 1;
        }else
        {
            return 0;
        }
    }

    public static int deleteFilesIntercaja()
    {
        File g = new File("");
        int resp = 0;
        StringBuffer fileIntercaja = new StringBuffer("");
        fileIntercaja.append(g.getAbsolutePath());
        fileIntercaja.append(IND_Constants.DIR_UPLFILE);

        String dir = fileIntercaja.toString();

        File f = new File(dir);

        File[] ficheros = f.listFiles();

        for(int i = 0; i<ficheros.length; i++){

            File temp = ficheros[i];

            if(temp.delete())
            {
                resp = 1;
            }
            else
            {
                resp = 0;
            }
        }

        return resp;
    }
}
