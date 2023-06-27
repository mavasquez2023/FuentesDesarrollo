package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.poi.util.SystemOutLogger;
import org.apache.struts.action.*;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
//import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.independientes.dao.ParametrosDAO;
import cl.araucana.independientes.helper.GlobalProperties;
import cl.araucana.independientes.helper.Helper;
import cl.araucana.independientes.vo.param.ListadoGeografico;
import cl.araucana.independientes.vo.param.ListadoMotivo;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

/*Implementación de la clase LoginAction*/
public class LoginAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        String perfil =  "";
        String fechaSistema = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();

        
        listaParam.setServletContextRealPath(request.getSession().getServletContext().getRealPath("/"));

       
        ListadoGeografico listaGeo = ListadoGeografico.getInstancia();

        ListadoMotivo listaMot = ListadoMotivo.getInstancia();

        HttpSession session = request.getSession();

        //Inicio REQ 6988 JLGN 11-03-2013
        GlobalProperties global = GlobalProperties.getInstance();
        //Fin REQ 6988 JLGN 11-03-2013

        if(autentica(request)){
            request.setAttribute("resultado", "");
        }else{
        	System.out.println("Entro en el error de autentificación.........");
            request.setAttribute("resultado", "Error al autenticar");
            return mapping.findForward("errorLogin");
        }

        perfil  = (String)session.getAttribute("Perfil");
        System.out.println("Perfil = " + String.valueOf(perfil));
        
        if(perfil.equals("0") || perfil.equals("")){
            request.setAttribute("resultado", "No posee los privilegios para acceder a la aplicación.");
            return mapping.findForward("errorLogin");
        }


        fechaSistema = ParametrosDAO.obtenerFechaSistema();

        System.out.println("Declaracion de variables de sesion.");
        //Declaracion de variables de sesion.
        session.setAttribute("ListadoAfpBox",						listaParam.getListAfp());
        session.setAttribute("ListDocumentoBox",					listaParam.getListDocumento());
        session.setAttribute("ListEstadoCivilBox",					listaParam.getListEstadoCivil());
        session.setAttribute("ListEstadoSolicitudAfiliacionBox",	listaParam.getListEstadoSolicitudAfiliacion());
        session.setAttribute("ListEstadoSolicitudDesafiliacionBox",	listaParam.getListEstadoSolicitudDesafiliacion());
        session.setAttribute("ListEstadoAfiliadoBox",				listaParam.getListEstadoAfiliado());
        session.setAttribute("ListGiroComercialBox",				listaParam.getListGiroComercial());
        session.setAttribute("ListLocalidadBox",					listaParam.getListLocalidad());
        session.setAttribute("ListNacionalidadBox",					listaParam.getListNacionalidad());
        session.setAttribute("ListNivelEducacionalBox",				listaParam.getListNivelEducacional());
        session.setAttribute("ListPersonaBox",						listaParam.getListPersona());
        session.setAttribute("ListProfesionBox",					listaParam.getListProfesion());
        session.setAttribute("ListEstadoAporte",					listaParam.getListEstadoAporte());
        session.setAttribute("ListFormaPagoAporte",					listaParam.getListFormaPagoAporte());
        session.setAttribute("ListRegimenSaludBox",					listaParam.getListRegimenSalud());
        session.setAttribute("ListSexoBox",							listaParam.getListSexo());
        session.setAttribute("ListSolicitudBox",					listaParam.getListSolicitud());
        session.setAttribute("ListEstadoDocumentoBox",              listaParam.getListEstadoDocumento());
        session.setAttribute("ListTipoDocumentoBox",				listaParam.getListTipoDocumentosSol());
        //session.setAttribute("ListTipoDocumentoFull",				listaParam.getListTipoDocumentosFull());
        session.setAttribute("ListMailToNominaSolicitudes", 		listaParam.getListMailToNominaSolicitudes());
        session.setAttribute("ListTipoPagoAporte",                  listaParam.getListTipoPagoAporte());
        session.setAttribute("ListTipoOrg",		                    listaParam.getListTipoOrg());
        session.setAttribute("ListTipoCargo",	                    listaParam.getListTipoCargo());

        session.setAttribute("TxtSelTipoCalculoAporte", 			listaParam.getTxtSelTipoCalculoAporte());
        session.setAttribute("TxtValorCalculoAporte",				listaParam.getTxtValorCalculoAporte());
        session.setAttribute("TxtValorACotizar",					listaParam.getTxtValorACotizar());		

        System.out.println("proceso de intercaja.");
        //proceso de intercaja.
        session.setAttribute("ListMailToSalidaIntercaja",			listaParam.getListMailToSalidaIntercaja());
        session.setAttribute("ListTipoArchivoIntercaja",            listaParam.getListTipoArchivo());
        session.setAttribute("ListStatusProceso",                   listaParam.getListStatusProceso());
        session.setAttribute("ListParametrosConexionCobol",         listaParam.getListParametrosConexionCobol());
        session.setAttribute("ListVigenciaMinima", 					listaParam.getListVigenciaMinima());
        session.setAttribute("ListParametrosConexionFTPIntercaja",  listaParam.getListParametrosConexionFTPIntercaja());
        session.setAttribute("ListRangoIntercaja",                  listaParam.getListRangoIntercaja());
        //fin proceso intercaja

        System.out.println("fin proceso intercaja");
        session.setAttribute("ListRegiones",						listaGeo.getListRegiones());
        session.setAttribute("ListMotivos",							listaMot.getListMotivo());
        session.setAttribute("ListDescMotivos",						listaMot.getListDescMotivo());

        session.setAttribute("ListCajas", 							listaParam.getListCaja());
        session.setAttribute("ListOficinas",						listaParam.getListOficina());		

        session.setAttribute("ListMantenedores",					listaParam.getListMantTabGlob());

        session.setAttribute("ListEntidades",						listaParam.getListEntidades());
        session.setAttribute("ListEstados",							listaParam.getListEstados());
        Parametro[] paramPerfiles = listaParam.getListPerfiles();
        session.setAttribute("ListPerfiles",						paramPerfiles);
        session.setAttribute("ListGlosaDocBeneficio",				listaParam.getListGlosaDocBeneficio());
        session.setAttribute("ListGlosaBeneficio",					listaParam.getListGlosaBeneficio());
        session.setAttribute("ListGlosaCortaDocBeneficio",			listaParam.getListGlosaCortaDocBeneficio());
        session.setAttribute("ListGlosaCortaBeneficio",				listaParam.getListGlosaCortaBeneficio());

        
        session.setAttribute("FechaSistema",						fechaSistema);
        session.setAttribute("FechaVigencia", 						Helper.obtenerFechaVigencia(fechaSistema));

        String[] perfiles = perfil.split("\\|");
        
        String perfilesStr = "Permisos: <br>";
        for (int i = 0; i < perfiles.length; i++) {
			System.out.println("===================== perfil "+perfiles[i]);
			for (int j = 0; j < paramPerfiles.length; j++) {
				if(String.valueOf(paramPerfiles[j].getCodigo()).equals(perfiles[i])){
					perfilesStr +=paramPerfiles[j].getGlosa();
				}
			}
			if(i+1 < perfiles.length){
				perfilesStr +="<br> ";
			}
		}
        
        
        session.setAttribute("perfilesStr",perfilesStr);
        		
        //Inicio REQ 6988 JLGN 11-03-2013
        String montoMaxBeneficio = global.getValorExterno("IND.monto.maximo.beneficio");
        System.out.println("montoMaxBeneficio: " + montoMaxBeneficio + "!");
        session.setAttribute("MontoMaxBeneficio",					montoMaxBeneficio);
        //Fin REQ 6988 JLGN 11-03-2013

//      TODO SToro #parche2
        /*try {
			session.setAttribute("FechaResolucion",Helper.primerDiaMesSiguiente(fechaSistema));			
		} catch (Exception e) {
			System.out.println("Error_LoginAction_FechaResolucion:"+e.getMessage());
		}*/	

        //REQ5348
        session.setAttribute("ListAgrupacionFull",listaParam.getListAgrupacionFull());
        //FIN REQ5348

        session.setAttribute("ListBeneficioFull", 					listaParam.getListBeneficioFull());
        session.setAttribute("ListTipoPagoBeneficio", 				listaParam.getListTipoPagoBeneficio());

        System.out.println("Entrando al Helper....");
        Helper.borrarArchivos((String)session.getAttribute("IDAnalista"));

        
        return mapping.findForward("menuPpal");
    }

    /*Función que autentica. Usa sesion de LDAP.
     * recibe como entrada un request
     * retorna los datos consultados de un usuario ldap.*/
    public boolean autentica(HttpServletRequest request){

        String user = (String)request.getParameter("txt_Usuario");
        String pass = (String)request.getParameter("txt_Clave");

        boolean resp = false;

        HttpSession session = request.getSession();

        try{
            User usuario = null;
            UserRegistryConnection conn = new UserRegistryConnection(user, pass);

            usuario = conn.getUser(user);

            session.setAttribute("IDAnalista",					    user.substring(0, user.length()-2));
            session.setAttribute("NombreAnalista",					usuario.getName());
            session.setAttribute("ApePatAnalista",					usuario.getFirstName());
            session.setAttribute("ApeMatAnalista",					usuario.getLastName());
            //session.setAttribute("Perfil", 							ParametrosDAO.obtenerPerfil(user.substring(0, user.length()-2)));
            session.setAttribute("Perfil", 							ParametrosDAO.obtenerPerfiles(user.substring(0, user.length()-2)));
            session.setAttribute("Error", 							"");

            resp = true;
        }catch(UserRegistryException e){

            resp = false;
        }	

        return resp;
       
    }
    
}
//fechaSistema = ParametrosDAO.obtenerFechaSistema();


