/*package com.microsystem.lme.test;

import java.beans.PropertyVetoException;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import microsystem.configuracion.excepciones.ConfiguracionException;
import microsystem.integracion.as400.InvocadorAS400;
import microsystem.integracion.as400.excepciones.ConexionAS400SysException;
import microsystem.integracion.as400.excepciones.EjecucionAS400SysException;
import microsystem.integracion.as400.vo.Estructura;
import microsystem.lme.ws.cliente.operador.RespuestaDetalleLicencia;
import microsystem.lme.ws.cliente.operador.ServiciosMultiOperador;
import microsystem.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import microsystem.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import wwwLmeGovClLme.CTDetalleHaber;
import wwwLmeGovClLme.CTDireccionArchivo;
import wwwLmeGovClLme.CTEstado;
import wwwLmeGovClLme.CTLugarReposo;
import wwwLmeGovClLme.CTRemuneracion;
import wwwLmeGovClLme.CTResolucion;
import wwwLmeGovClLme.CTTelefono;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.microsystem.lme.svc.IAS400Svc;
import com.microsystem.lme.svc.SvcFactory;

public class TestSvc {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		try {
			readFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        System.getProperties().put("http.proxyHost", "proxy.int.msys.cl");
        System.getProperties().put("http.proxyPort", "3128");
        
//        String rutEmp="70016330";
//		String rutAfi="13938059";
//		String urlStr="http://www.licencia.cl/lme_produccion/intranet/descarga_liquidacion.php?cod=e052e2116d4890ee4e226cd03d8b6eca&amp;id_archivo=319006";
		//String urlStr="http://www.medipass.com/WebApp/webadmin/img.php?id=A8B8BCEC213D2C68F7629C5BA982037C";
		//String urlStr="http://www.medipass.cl/WebApp/webadmin/img.php?id=688B301A76413D347951ADBEE903F339";
		//String urlStr="http://www.medipass.cl/WebApp/webadmin/img.php?id=1D0377FA81B2E8477D455C3C480B1E44";
//		String dir="C:\\LME\\Imagenes\\";
//		try {
//			Util.getUrl(urlStr, dir, rutAfi, rutEmp);
//		} catch (SvcException e) {
//			e.printStackTrace();
//		}
//        
//		log(urlStr);
//		log(urlStr.replaceAll("&amp;","&"));
		//log(java.net.URLDecoder.decode(urlStr, "UTF-8"));
        
//        Object[] params= {new Long(3),"19:36"};
//        String p = MessageFormat.format("{0}%s son las %s",new Object[]{"xxx","19:36"});
//        log(p);
//        
//        String x = "<xml-fragment xmlns:urn=\"urn:www:lme:gov:cl:lme\">";log(x);
//        String carOld = "xmlns=\"" + "urn:www:lme:gov:cl:lme\"";
//        
//        System.out.println(x.replaceAll("urn:www:lme:gov:cl:lme","").replaceAll("xmlns:urn=\"\"",""));
        
        //stringFormat((Object[]){new Long(3),"19:36"});
//       
//        System.out.print(p);
//        
        //consumirDetalleLME();
        //actualizarLmeCCAF();
        //invokerAs400();
        
//        int M=0,S=1,T=Integer.parseInt("5534342"); 
//        for(;T!=0;T/=10)S=(S+T%10*(9-M++%6))%11; 
//        String dv = String.valueOf((char)(S!=0?S+47:75)); 
//
//        System.out.println(dv);
		//System.out.println(dataTruncation("0123456789012345",5));
		//System.out.println(dataTruncation("123456789012345",1));
        
       
		
	}
	
	public static void readFile()
			throws Exception {
//		String upDir = "\\..\\..\\..\\..";
//		String projectDir = request.getSession().getServletContext().getRealPath("/");
//		System.out.println("REAL PATH: "+request.getSession().getServletContext().getRealPath("/"));
		
		String projectDir = "/home/lme/utils.js";
		
		//File file = new File("C:\\Archivos de programa\\IBM\\Rational\\SDP\\6.0\\runtimes\\base_v6\\profiles\\default\\logs\\lme.log");
		File file = new File(projectDir);
		
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;

	    try {
	      fis = new FileInputStream(file);

	      // Here BufferedInputStream is added for fast reading.
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

	      // dis.available() returns 0 if the file does not have more lines.
	      while (dis.available() != 0) {

	      // this statement reads the line from the file and print it to
	      // the console.
	        System.out.println(dis.readLine());
	      }

	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		
	}
	
	private static String dataTruncation(String str, int i) {		
		return str.length()>i?str.substring(0,i):str;
	}
	public static Estructura invokerAs400(){
		Estructura s = new Estructura();
		s.paramEntradaNum("RUTAFI-P",9, 5534342);
		s.paramEntradaChr("DVAFI-P",1, "K");
	
		s.paramSalidaNum("CONTADOR-O",3);
		s.paramSalidaChr("LICIMPNUM-O",7);//ERROR NumberFormatException
		Estructura o = new Estructura(3500);
			o.paramSalidaChr("F-DESDE-O",10);
			o.paramSalidaChr("F-HASTA-O",10);
			o.paramSalidaChr("OFI-PAGO-O",1);
			o.paramSalidaChr("F-PAGO-O",10);
			o.paramSalidaChr("EST-LIC-O",4);
			o.paramSalidaChr("EMPRE-O",1);
			o.paramSalidaChr("F-INGRE-O",10);
			o.paramSalidaChr("OBSERVA-O",20);
		s.paramEstructura("DETALLE-LIC-O",o);
		s.paramSalidaNum("COD-ERROR-O",1);
		s.paramSalidaChr("DES-ERROR-O",50);
		
		try {
			InvocadorAS400 i = new InvocadorAS400("as400.laaraucana");
			i.ejecutar("/QSYS.LIB/TESTSIL.LIB/ILPSIL002.PGM", s);
			System.out.println(s);
		} catch (ConfiguracionException e) {
			e.printStackTrace();
		} catch (EjecucionAS400SysException e) {
			e.printStackTrace();
		} catch (ConexionAS400SysException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public static void actualizarLmeCCAF(){
		String host = "146.83.1.5";
		String user = "msystem2";
		String pass = "msystem2";
		String path = "/QSYS.LIB/TESTSIL.LIB/ILPSIL002.PGM";//"/QSYS.LIB/LIEXP.LIB/ILCLME01.PGM";
		String dataInf = "";
		
		AS400 as400 = new AS400(host, user, pass);
		ProgramCall pgm = new ProgramCall(as400);
		try {
			pgm.setProgram(path);
			if (pgm.run()!=true)
            {
                 AS400Message[] msgList = pgm.getMessageList();

//                datosConsulta.estado = "ERROR";
                for (int i=0; i<msgList.length; i++)
                {
                    System.out.println(msgList[i].getText());
//                    def row = [:]
//                    row.Tipo = "Servidor AS400"
//                    row.Descripcion = msgList[i].getText()                    
                }

            }else{
                //Asigna valores de los parametros de retorno
            	dataInf = "datosConsulta.estado = OK";
              
            }
		} catch (PropertyVetoException e) {
			log(e.getClass() + "; "+ e.getMessage());
		} catch (AS400SecurityException e) {
			log(e.getClass() + "; "+ e.getMessage());
		} catch (ErrorCompletingRequestException e) {
			log(e.getClass() + "; "+ e.getMessage());
		} catch (IOException e) {
			log(e.getClass() + "; "+ e.getMessage());
		} catch (InterruptedException e) {
			log(e.getClass() + "; "+ e.getMessage());
		} catch (ObjectDoesNotExistException e) {
			log(e.getClass() + "; "+ e.getMessage());
		}
	}
	
	static void log(String message) {
		System.out.println(message);
    }
	
	private String stringFormat(String msg, Object[] paramArrayOfObject){		
		return MessageFormat.format(msg,paramArrayOfObject);
	}
	
	
	private static void consumirDetalleLME() {
		int agnoImagen = 1900;
        int mesImagen = 0;
        IAS400Svc svc = SvcFactory.getAS400Svc();
		
		
		try {			
			
			ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","12345","http://200.0.156.60:8080/lme2_fonasa/pronunciamiento/lmes/service"); //IMED
            RespuestaDetalleLicencia respuesta = servicio.consultaDetalleLicencia(new BigInteger("245"), "3");

			if(null!=respuesta && null!=respuesta.getZonaA() && 
					null!=respuesta.getZonaA().getZONAA1() &&
					null!=respuesta.getZonaA().getZONAA1().getTrabajador() &&
					null!=respuesta.getZonaA().getZONAA1().getTrabajador().getRut()){
				CTEstado[] listaEstado = respuesta.getZona0().getZONA01().getEstadoArray();
                
				//busca ultimo estado
				int ultimoEstado = 0;
                Date fechaUtimoEstado = null;
				for (int i = 0; i < listaEstado.length; i++) {
                    if (fechaUtimoEstado == null) {
                        ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
                        fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
                    }
                    if (fechaUtimoEstado.compareTo(listaEstado[i].getFechaEstado().getTime()) < 0) {
                        // actualizo estado
                        ultimoEstado = listaEstado[i].getEstadoLicencia().intValue();
                        fechaUtimoEstado = listaEstado[i].getFechaEstado().getTime();
                    }
				}
				 
				 	//Zona A ---------------------------------------------------------------------------------------
				 	String RUTEMPLE = "";
                    String DIGEMPLE = "";
                    String AFIRUT = respuesta.getZonaA().getZONAA1().getTrabajador().getRut(); //respuesta.getZonaA().getZONAA1().getTrabajador()?.rut ?: '';
                    String AFIRUTDV = "";
                    String APAAFI = respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno();
                    String AMAAFI = respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno();
                    String NOMAFI = respuesta.getZonaA().getZONAA1().getTrabajador().getNombres();
                    String FECEMILI = set(respuesta.getZonaA().getZONAA1().getFechaEmision());//19000101
                    String FECINIRE = set(respuesta.getZonaA().getZONAA1().getFechaInicioReposo()); //19000101
                    String AFIEDAD = respuesta.getZonaA().getZONAA1().getTrabajador().getEdad().toString();// 0
                    String AFISEXO = respuesta.getZonaA().getZONAA1().getTrabajador().getSexo().toString();           
                    String AFIEMAIL = set(respuesta.getZonaA().getZONAAC().getEmailTrabajador());
                    String NUMDIALI = set(respuesta.getZonaA().getZONAA1().getTraNdias());
                    String RUTHIJO = respuesta.getZonaA().getZONAA2().getHijo().getRut();//"0"
                    String DIVHIJO = "0";
                    String APAHIJO = respuesta.getZonaA().getZONAA2().getHijo().getApellidoPaterno();
                    String AMAHIJO = respuesta.getZonaA().getZONAA2().getHijo().getApellidoMaterno();
                    String NOMHIJO = respuesta.getZonaA().getZONAA2().getHijo().getNombres();
                    String FECNACHI = "19000101";
//                    String FECNACHI = set(respuesta.getZonaA().getZONAA2().getHijo().getFechaNacimiento());// 19000101
                    String TIPLICEN = respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia().toString();
                    String RECUPERA = respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad().toString();
                    String INITRAMI = respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv().toString();
                    String FECACCID = set(respuesta.getZonaA().getZONAA3().getFechaAccidente());//19000101
                    String TRAYECTO = set(respuesta.getZonaA().getZONAA3().getCodigoTrayecto());//"0"
                    String FECCONCE = set(respuesta.getZonaA().getZONAA3().getFechaConcepcion()); //19000101
                    String TIPREPOS = set(respuesta.getZonaA().getZONAA4().getCodigoTipoReposo());

                    String JORACUER = "0";
                    String LUGREPOP = "0"; //lugaresReposo[i].codigoLugarReposo ?: ''
                    String JUSSIOTR = "";
                    String DIRREP01 = ""; //lugaresReposo[i].calle ?: ''
                    String CODCOM01 = "0";
                    String GLOCOM01 = ""; //lugaresReposo[i].comuna ?: ''
                    String TELEFO01 = "";//telenfonosReposo[i].telefono ?: ''

                    String LUGREP02 = "0"; //lugaresReposo[i].codigoLugarReposo ?: ''
                    String DIRREP02 = ""; //lugaresReposo[i].calle ?: ''
                    String CODCOM02 = "0";
                    String GLOCOM02 = ""; //lugaresReposo[i].comuna ?: ''
                    String TELEFO02 = ""; //telenfonosReposo[i].telefono ?: ''				 	
				 	
				 	CTLugarReposo[] lugarReposo = respuesta.getZonaA().getZONAA4().getLugarReposoArray();
				 	for (int i = 0; i < lugarReposo.length; i++) {
                        if (i==0){
                            LUGREPOP = lugarReposo[i].getCodigoLugarReposo().toString();// ?: ''
                            JUSSIOTR = set(lugarReposo[i].getJustificaDomicilio());// ?: ''
                            DIRREP01 = lugarReposo[i].getDireccionReposo().getCalle();// ?: ''
                            CODCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString(); //habilitado
                            GLOCOM01 = lugarReposo[i].getDireccionReposo().getComuna().toString();
                        }
                        else{
                            LUGREP02 = lugarReposo[i].getCodigoLugarReposo().toString(); // ?: ''
                            DIRREP02 = lugarReposo[i].getDireccionReposo().getCalle() ; //?: ''
                            CODCOM02 = lugarReposo[i].getDireccionReposo().getComuna().toString(); //habilitado
                        }
                    }
				 	
				 	CTTelefono[] telefono = respuesta.getZonaA().getZONAA4().getTelefonoReposoArray();
                    for (int i = 0; telefono != null && i < telefono.length; i++) {
                        if (i==0){
                            TELEFO01 = telefono[i].getTelefono().toString();
                        }
                        else{
                            TELEFO02 = telefono[i].getTelefono().toString();
                        }
                    }
                    String RUTPROFE = respuesta.getZonaA().getZONAA5().getProfesional().getRut(); //"0"
                    String DIVPROFE = "0";
                    String APAPROFE = respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno();
                    String AMAPROFE = respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno();
                    String NOMPROFE = respuesta.getZonaA().getZONAA5().getProfesional().getNombres();
                    String DIRPROFE = respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle();
                    String CODCOMPR = respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna().toString();// "0"
                    String GLOCOMPR = "";
                    String FONPROFE = set(respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono()); //habilitado
                    String FAXPROFE = respuesta.getZonaA().getZONAA5().getProfFax()==null?"0":set(respuesta.getZonaA().getZONAA5().getProfFax().getTelefono());
                    String EMAPROFE = respuesta.getZonaA().getZONAA5().getProfEmail();
                    String GLOESPEC = respuesta.getZonaA().getZONAA5().getProfEspecialidad();
                    String CODESPEC = respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional().toString(); 
                    String TIPPREST = "0";
                    String NROCOMED = set(respuesta.getZonaA().getZONAA5().getProfRegistroColegio());
                    String CODDIAPR = set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoPrincipal());
                    String GLODIAPR = set(respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal());
                    String CODDIASE = set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoSecundario());
                    String GLODIASE = set(respuesta.getZonaA().getZONAAC().getDiagnosticoSecundario());
                    String CODDIAOT = set(respuesta.getZonaA().getZONAAC().getCoddiagnosticoOtro());
                    String GLODIAOT = set(respuesta.getZonaA().getZONAA6().getDiagnosticoOtro());
                    String ANTECCLI = set(respuesta.getZonaA().getZONAA6().getAntecedentesClinicos());
                    String EXAAPOYO = set(respuesta.getZonaA().getZONAA6().getExamenesApoyo());

                    if (CODDIAOT.length()>12)
                        CODDIAOT = CODDIAOT.substring(0,11);


                    String rut = AFIRUT;
                    AFIRUT = separaNumDigRut(rut, "N");
                    AFIRUTDV = separaNumDigRut(rut, "D");
     
                    rut = RUTHIJO.trim();
                    if (rut == "")
                       rut = "0-0";
                 
                    RUTHIJO = separaNumDigRut(rut, "N");
                    DIVHIJO = separaNumDigRut(rut, "D");

                    rut = RUTPROFE;
                    RUTPROFE = separaNumDigRut(rut, "N");
                    DIVPROFE = separaNumDigRut(rut, "D");
                 
   
				 	
                    
					Hashtable h = new Hashtable();
				 	h.put("ideope", "8");h.put("numLicencia","1");h.put("digLicencia","9");
				 	h.put("ultimoEstado",String.valueOf(ultimoEstado));h.put("AFIRUT",AFIRUT);h.put("AFIRUTDV",AFIRUTDV);
				 	h.put("APAAFI",APAAFI);h.put("AMAAFI",AMAAFI);h.put("NOMAFI",NOMAFI);
				 	h.put("FECEMILI",FECEMILI);h.put("FECINIRE",FECINIRE);h.put("AFIEDAD",AFIEDAD);				 	
				 	h.put("AFISEXO",AFISEXO);h.put("AFIEMAIL",AFIEMAIL);h.put("NUMDIALI",NUMDIALI);				 	
				 	h.put("RUTHIJO",RUTHIJO);h.put("DIVHIJO",DIVHIJO);h.put("APAHIJO",APAHIJO);				 	
				 	h.put("AMAHIJO",AMAHIJO);h.put("NOMHIJO",NOMHIJO);h.put("FECNACHI",FECNACHI);				 	
				 	h.put("TIPLICEN",TIPLICEN);h.put("RECUPERA",RECUPERA);h.put("INITRAMI",INITRAMI);				 	
				 	h.put("FECACCID",FECACCID);h.put("TRAYECTO",TRAYECTO);h.put("FECCONCE",FECCONCE);
				 	h.put("TIPREPOS",TIPREPOS);h.put("JORACUER",JORACUER);h.put("LUGREPOP",LUGREPOP);				 	
				 	h.put("JUSSIOTR",JUSSIOTR);h.put("DIRREP01",DIRREP01);h.put("CODCOM01",CODCOM01);
				 	h.put("GLOCOM01",GLOCOM01);h.put("TELEFO01",TELEFO01);h.put("LUGREP02",LUGREP02);
				 	h.put("DIRREP02",DIRREP02);h.put("CODCOM02",CODCOM02);h.put("GLOCOM02",GLOCOM02);				 	
				 	h.put("TELEFO02",TELEFO02);h.put("RUTPROFE",RUTPROFE);h.put("DIVPROFE",DIVPROFE);
				 	h.put("APAPROFE",APAPROFE);h.put("AMAPROFE",AMAPROFE);h.put("NOMPROFE",NOMPROFE);
				 	h.put("DIRPROFE",DIRPROFE);h.put("CODCOMPR",CODCOMPR);h.put("GLOCOMPR",GLOCOMPR);				 	
				 	h.put("FONPROFE",FONPROFE);h.put("FAXPROFE",FAXPROFE);h.put("EXAAPOYO",EXAAPOYO);
				 	h.put("EMAPROFE",EMAPROFE);h.put("GLOESPEC",GLOESPEC);h.put("CODESPEC",CODESPEC);				 	
				 	h.put("TIPPREST",TIPPREST);h.put("NROCOMED",NROCOMED);h.put("CODDIAPR",CODDIAPR);
				 	h.put("GLODIAPR",GLODIAPR);h.put("CODDIASE",CODDIASE);h.put("GLODIASE",GLODIASE);
				 	h.put("CODDIAOT",CODDIAOT);h.put("GLODIAOT",GLODIAOT);h.put("ANTECCLI",ANTECCLI);				 	
				 	
//				 	try {
//						svc.insertIlfe002R(h);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
				 	//Zona B ---------------------------------------------------------------------
				 	if (null!=respuesta.getZonaB()){
				 		String NRORESOL;
                        String CODCAURE;
                        String DERSUBSI;
                        String CIE10;
                        String JORREPAU;
                        String TIPREPAU;
                        String IDECOMPI;
                        String TIPFALLO;
                        String NOMBRECO;
                        String REGCOLME;
                        String RUTCONTR;
                        String DIASPREV;
                        String FECAUTDE;
                        String FECAUTHA;
                        String FECREPFI;
                        String DIASAUT;
                        String PENDXOUT;
                        String FECREDIC;
                        String FECRESFI;
                        String CODCONTI;
                        String CODESTAB;
                        String CODREDIC;
                        String ENTIDAD;
                        String ESTABLEC;
                        
                        CTResolucion[] resolucions = respuesta.getZonaB().getZONAB1().getResolucionArray();
                        for (int i=0; i < resolucions.length; i++) {
                            NRORESOL = set(resolucions[i].getNResolucion());
                            CODCAURE = set(resolucions[i].getCodigoCausaRechazo());
                            DERSUBSI = resolucions[i].getCodigoDerechoSubsidio().toString();
                            CIE10 = set(resolucions[i].getCodigoDiagnostico());

                            //STJornadaReposo.Enum codigoJornadaReposoAutorizado = resolucions[i].getCodigoJornadaReposoAutorizadoArray(0)
                            JORREPAU = "";

                            TIPREPAU = set(resolucions[i].getCodigoReposoAutorizado());
                            IDECOMPI = set(resolucions[i].getCodigoTipoLicenciaEntidad());
                            TIPFALLO = set(resolucions[i].getCodigoTipoResolucion());
                            NOMBRECO = resolucions[i].getContralorNombre() ;
                            REGCOLME = resolucions[i].getContralorRegistroColegio();

                            //parche medipass
                            
                            if (REGCOLME)
                                if (REGCOLME.size()>10)
                                     REGCOLME = REGCOLME[0..9]
                            

                            RUTCONTR = resolucions[i].getContralorRut() ;
                            DIASPREV = set(resolucions[i].getDiasPrevios()) ;
                            FECAUTDE = set(resolucions[i].getEntidadFechaDesde());
                            FECAUTHA = set(resolucions[i].getEntidadFechaHasta()) ;
                            FECREPFI = set(resolucions[i].getEntidadFechaRecepcion()) ;
                            DIASAUT =  set(resolucions[i].getEntidadNdias()) ;
                            PENDXOUT = resolucions[i].getEntidadPendiente() ;
                            FECREDIC = set(resolucions[i].getFechaRedictamen()) ;
                            FECRESFI = set(resolucions[i].getFechaResolucion());

                            CODCONTI = set(resolucions[i].getCodigoContinuacion()) ;
                            CODESTAB = set(resolucions[i].getCodigoEstablecimiento()) ;
                            CODREDIC = set(resolucions[i].getCodigoRedictamen()) ;
                            ENTIDAD = set(resolucions[i].getEntidad()) ;
                            ESTABLEC = set(resolucions[i].getEstablecimiento()) ;

                         

                            h = new Hashtable();
//                            h.put("ideope", lic.getIdeOpe());
//                            h.put("numLicencia", lic.getNumLicencia());
                            h.put("ultimoEstado", String.valueOf(ultimoEstado));
                            h.put("AFIRUT", AFIRUT);h.put("AFIRUTDV", AFIRUTDV);h.put("DIASAUT", DIASAUT);
                            h.put("FECAUTDE", FECAUTDE);h.put("FECAUTHA", FECAUTHA);h.put("DIASPREV", DIASPREV);
                            h.put("TIPREPAU", TIPREPAU);h.put("DERSUBSI", DERSUBSI);h.put("FECREPFI", FECREPFI);
                            h.put("FECRESFI", FECRESFI);h.put("CODCAURE", CODCAURE);h.put("TIPFALLO", TIPFALLO);
                            h.put("IDECOMPI", IDECOMPI);h.put("NRORESOL", NRORESOL);h.put("PENDXOUT", PENDXOUT);
                            h.put("FECREDIC", FECREDIC);h.put("CIE10", CIE10);h.put("RUTCONTR", RUTCONTR);
                            h.put("NOMBRECO", NOMBRECO);h.put("REGCOLME", REGCOLME);h.put("CODCONTI", CODCONTI);
                            h.put("CODESTAB", CODESTAB);h.put("CODREDIC", CODREDIC);h.put("ENTIDAD", ENTIDAD);
                            h.put("ESTABLEC", ESTABLEC);h.put("JORREPAU", JORREPAU);
                            
//                            svc.insertIlfe003(h);

                        }
				 	}
				 	//Zona C ---------------------------------------------------------------------------------------
				 	if (null!=respuesta.getZonaC()){
                        RUTEMPLE = respuesta.getZonaC().getZONAC1().getEmpRut();// "0-0"
                        if (RUTEMPLE == "0-")RUTEMPLE = "0-0";

                        DIGEMPLE ="";
                        String NOMEMPLE = respuesta.getZonaC().getZONAC1().getEmpNombre();
                        String APAEMPLE = "";
                        String AMAEMPLE = "";
                        String TIPEMPLE = "0";
                        String DIREMPLE = respuesta.getZonaC().getZONAC1().getEmpDireccion().getCalle();
                        String COMEMPLE = String.valueOf(respuesta.getZonaC().getZONAC1().getEmpDireccion().getComuna());
                        String GLOCOMEM = "";
                        String FONOEMPL = set(respuesta.getZonaC().getZONAC1().getEmpTelefono().getTelefono());
                        String EMAIEMPL = "";
                        String FECREPEM = set(respuesta.getZonaC().getZONAC1().getEmpFechaRecepcion());//"0" //habilitado
                        String FECENVEM = "0";
                        String CODCCAF = set(respuesta.getZonaC().getZONACC().getCodigoTramitacionCCAF());// "0" //ERRO LARGO, VALOR: 10105
                        String CODCOMPI = set(respuesta.getZonaC().getZONAC1().getCodigoComunaCompin());// "0"
                        String CODACTLA = set(respuesta.getZonaC().getZONAC1().getCodigoActividadLaboral()) ; // "0"
                        String CODOCUPA = set(respuesta.getZonaC().getZONAC1().getCodigoOcupacion()); // "0"
                        String GLOOTROC = "";
                        String CODREGPR = set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional()); // "0" //trae -1
                        String PENSIONA = "0";
                        String CODINSPR = set(respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional()); // "0"
                        String LETCAJPR = respuesta.getZonaC().getZONAC2().getCodigoLetraCaja();
                        String CODCALTR = set(respuesta.getZonaC().getZONAC2().getCodigoCalidadTrabajador()); // "0"
                        String AFC = set(respuesta.getZonaC().getZONAC2().getCodigoSeguroAfc()); //"0"
                        String NOMAFC = "";
                        String CONINDEF = "0";
                        String CODESTAT = "0";
                        String FECAFILI = set(respuesta.getZonaC().getZONAC2().getFechaAfiliacion()); // "0" //habilitado
                        String FECCONTR = set(respuesta.getZonaC().getZONAC2().getFechaContrato()); // "0" 
                        String PORDESAU = String.valueOf(respuesta.getZonaC().getZONAC3().getPorcenDesahucio()); // "0"
                        String RENIMPON = set(respuesta.getZonaC().getZONAC3().getMontoImponibleMesAnterior()); // "0"
                        String CODTIPSU = String.valueOf(respuesta.getZonaC().getZONAC2().getCodigoEntidadPagadora()); 
                        String MOTRECEM = "";

                        rut = RUTEMPLE;
                      
                        h = new Hashtable();
//                        h.put("ideope", lic.getIdeOpe());h.put("numLicencia", lic.getNumLicencia());h.put("AFIRUT", AFIRUT);
                        h.put("ultimoEstado", String.valueOf(ultimoEstado));h.put("RUTEMPLE", RUTEMPLE);h.put("DIGEMPLE", DIGEMPLE);
                        h.put("NOMEMPLE", NOMEMPLE);h.put("APAEMPLE", APAEMPLE);h.put("AMAEMPLE", AMAEMPLE);
                        h.put("TIPEMPLE", TIPEMPLE);h.put("DIREMPLE", DIREMPLE);h.put("COMEMPLE", COMEMPLE); 
                        h.put("GLOCOMEM", GLOCOMEM);h.put("FONOEMPL", FONOEMPL);h.put("EMAIEMPL", EMAIEMPL);
                        h.put("FECREPEM", FECREPEM);h.put("FECENVEM", FECENVEM);h.put("CODCCAF", CODCCAF);
                        h.put("CODCOMPI", CODCOMPI);h.put("CODACTLA", CODACTLA);h.put("CODOCUPA", CODOCUPA); 
                        h.put("GLOOTROC", GLOOTROC);h.put("CODREGPR", CODREGPR);h.put("PENSIONA", PENSIONA);
                        h.put("CODINSPR", CODINSPR);h.put("LETCAJPR", LETCAJPR);h.put("CODCALTR", CODCALTR);
                        h.put("AFC", AFC);h.put("NOMAFC", NOMAFC);h.put("CONINDEF", CONINDEF);h.put("CODESTAT", CODESTAT); 
                        h.put("FECAFILI", FECAFILI);h.put("FECCONTR", FECCONTR);h.put("PORDESAU", PORDESAU);
                        h.put("RENIMPON", RENIMPON);h.put("CODTIPSU", CODTIPSU);h.put("MOTRECEM", MOTRECEM);
                        
//                        svc.insertIlfe004(h);
                    }
				 	//ZONA C - RENTAS --------------------------------------------------------------------------------
				 	
                    String CODINSPR = "0";
                    String LETCAJPR = "";
                    String PERRENTA = "0";
                    String NUMDIATR = "0";
                    String REMMUNIM = "0";
                    String IMPDESAH = "0";
                    String IMPCESAN = "0";
                    String SUEBASE = "0";
                    String SUBDIAS = "0";
                    String SUBMONTO = "0";
                    TIPLICEN = null==respuesta.getZonaA()|| null==respuesta.getZonaA().getZONAA3()?"0":set(respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
                    LETCAJPR = null==respuesta.getZonaC()|| null==respuesta.getZonaC().getZONAC2()?"": set(respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
                    CODINSPR = "0";
                    if (null!=respuesta.getZonaC() && null!=respuesta.getZonaC().getZONAC3()){
                        CTRemuneracion[] remuneracion = respuesta.getZonaC().getZONAC3().getRemuneracionArray();
                        for (int i = 0; i < remuneracion.length; i++) {
                            PERRENTA = set(remuneracion[i].getAnoMesRemAnt()); // "1900-01"
                            NUMDIATR = set(remuneracion[i].getNdiasRemAnt()); // "0"
                            REMMUNIM = set(remuneracion[i].getMontoImponibleRemAnt()) ; //"0"
                            SUEBASE = set(remuneracion[i].getMontoTotalRemAnt()) ; // "0"
                            SUBDIAS = set(remuneracion[i].getNdiasIncapacidadRemAnt()) ; // "0"
                            SUBMONTO = set(remuneracion[i].getMontoIncapacidadRemAnt()) ; // "0"
                            PERRENTA = PERRENTA.substring(0,6);

//                            cmd = "INSERT INTO ILFE005 (IDEOPE, NUMIMPRE, ESTLICEN, AFIRUT, TIPLICEN, CODINSPR, LETCAJPR, PERRENTA, NUMDIATR, REMMUNIM, IMPDESAH, IMPCESAN, SUEBASE, SUBDIAS, SUBMONTO"
//                            cmd += " ) VALUES ("
//                            cmd += " ${ideope}, ${numLicencia}, ${ultimoEstado}, ${AFIRUT}, ${TIPLICEN}, ${CODINSPR}, '${LETCAJPR}', '${PERRENTA}', ${NUMDIATR}, ${REMMUNIM}, ${IMPDESAH}, ${IMPCESAN}, ${SUEBASE}, ${SUBDIAS}, ${SUBMONTO})"
//                            listaCmd << cmd
                            h = new Hashtable();
//                            h.put("ideope", lic.getIdeOpe());h.put("numLicencia", lic.getNumLicencia());h.put("ultimoEstado", String.valueOf(ultimoEstado));
                            h.put("AFIRUT", AFIRUT);h.put("TIPLICEN", TIPLICEN);h.put("CODINSPR", CODINSPR);
                            h.put("LETCAJPR", LETCAJPR);h.put("PERRENTA", PERRENTA);h.put("NUMDIATR", NUMDIATR);
                            h.put("REMMUNIM", REMMUNIM);h.put("IMPDESAH", IMPDESAH);h.put("IMPCESAN", IMPCESAN);
                            h.put("SUEBASE", SUEBASE);h.put("SUBDIAS", SUBDIAS);h.put("SUBMONTO", SUBMONTO);
                            
//                            svc.insertIlfe005(h);
                        }
                    }
                    //ZONA C HABERES --------------------------------------------------------------------------------
                    String PERIODO = "0";
                    String NOMHABER = "";
                    String MONTO = "0";
                    String URLARCHI = "";
                    String STTIPARC = "";
                    
                    if (null!=respuesta.getZonaC() && respuesta.getZonaC().getZONACC() != null) {
                        //URL imagen
                        String strMesImagen;
                        CTDireccionArchivo[] archivo = respuesta.getZonaC().getZONACC().getHaberes().getArchivoArray();
                        for (int i = 0; i < archivo.length; i++) {
                            //Genera periodo imagen
                            ++mesImagen;
                            if (mesImagen>12){
                                ++agnoImagen;
                                mesImagen = 1;
                            }

                            if (mesImagen<10)
                                strMesImagen = "0"+mesImagen;
                            else
                                strMesImagen = String.valueOf(mesImagen);

                            PERIODO = agnoImagen+"-"+strMesImagen;

                            URLARCHI = archivo[i].getUrlArchivo() ;
                            STTIPARC = set(archivo[i].getTipoArchivo());

//                            cmd = "INSERT INTO ILFE006 (AFIRUT, EMPRUT, PERIODO, URLARCHI, STTIPARC"
//                            cmd += " ) VALUES ("
//                            cmd += " ${AFIRUT}, ${RUTEMPLE}, '${PERIODO}', '${URLARCHI}', '${STTIPARC}')"
//                            listaCmd << cmd
                            h = new Hashtable();                            
                            h.put("AFIRUT", AFIRUT);
                            h.put("RUTEMPLE", RUTEMPLE);
                            h.put("PERIODO", PERIODO);
                            h.put("URLARCHI", URLARCHI);
                            h.put("STTIPARC", STTIPARC);
//                            svc.insertIlfe006(h);
                        }

                        //Detalle Haberes
                        CTDetalleHaber[] detalleHabers  = respuesta.getZonaC().getZONACC().getHaberes().getDetalleArray();
                        for (int i = 0; i < detalleHabers.length; i++) {

                            PERIODO = set(detalleHabers[i].getAnoMesRenta()) ;// "1900-01"
                            NOMHABER = detalleHabers[i].getNombreHaber() ; // "0"
                            MONTO = set(detalleHabers[i].getMontoHaber());  // "0"
//
//                            cmd = "INSERT INTO ILFE007 (AFIRUT, EMPRUT, PERIODO, NOMHABER, MONTO"
//                            cmd += " ) VALUES ("
//                            cmd += " ${AFIRUT}, ${RUTEMPLE}, '${PERIODO}', '${NOMHABER}', ${MONTO})"
//
//                            listaCmd << cmd
                            h = new Hashtable();
                            h.put("AFIRUT", AFIRUT);
                            h.put("RUTEMPLE", RUTEMPLE);
                            h.put("PERIODO", PERIODO);
                            h.put("NOMHABER", NOMHABER);
                            h.put("MONTO", MONTO);
//                            svc.insertIlfe007(h);
                        }                        
                    }
//                  update a la tabla de lista de licencias ILFE000, con ESTADO = “01�? ( procesado-ok )
//                    cmd = "UPDATE ILFE000 SET ESTADO = 1 WHERE NUMLIC = ${numLicencia}";
//                    listaCmd << cmd
                    h = new Hashtable();
                    h.put("ESTADO", Integer.valueOf("1"));
//                    h.put("NUMLIC", lic.getNumLicencia());
//                    svc.updateIlfe000(h);
				
			}else{
				//update a la tabla de lista de licencias ILFE000, con ESTADO = “99�? ( procesado-error <no encontrado>)
//                cmd = "UPDATE ILFE000 SET ESTADO = 99 WHERE NUMLIC = ${numLicencia}";
//                listaCmd << cmd
				 Map h = new Hashtable();
                 h.put("ESTADO", Integer.valueOf("99"));
//                 h.put("NUMLIC", lic.getNumLicencia());
//                 svc.updateIlfe000(h);
			}
				
		
		} catch (ConfiguracionException e) {
			e.printStackTrace();
		} catch (ErrorInvocacionOperadorException e) {
			e.printStackTrace();
		}  catch (ErrorRespuestaOperadorException e) {
			e.printStackTrace();
		} 
	}
	
	private static String set(Calendar c) {
		String d = "19000101";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(null!=c){
			d = sdf.format(c.getTime());
		}
		return d;
	}
	private static String set(String s){
		return null==s?"":s.trim();
	}
	private static String set(BigInteger i){
		return null==i?"0":i.toString();
	}
	private static String separaNumDigRut(String rut, String tipo) {
		String auxRut = "0";
        if (rut != "0"){
            int pos = rut.indexOf("-");
            if (pos > 0){
                String[] arRut = rut.split("-");
                if (tipo.equals("N"))
                    auxRut = arRut[0];
                else
                    auxRut = arRut[1];
            }
        }
        return auxRut;
	}


}
*/