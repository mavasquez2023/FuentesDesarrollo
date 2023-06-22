package cl.araucana.sivegam.struts.Actions;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.struts.Forms.LoginForm;
import cl.araucana.sivegam.vo.UsuariosSivegamVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class LoginAction extends Action {
    SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
        logger.debug("inicio: LOGINACTION");
        String perfil = "";
        boolean autorizado = false;
        String fechaSistema = "";
        int a = 0;
        
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        listaParam.setServletContextRealPath(request.getSession().getServletContext().getRealPath("/"));

        System.out.println("-> " + request.getSession().getServletContext().getRealPath("/"));
        
        HttpSession session = request.getSession(true);
        Principal userPrincipal = request.getUserPrincipal();
		String user =  userPrincipal.getName();
		//String user= "11648834-5";
		String forwardPage = (String)session.getAttribute("forwardPage");
		//String forwardPage="pages/ModifSif016.jsp";
        
        if(user.length()!=0){
        	request.setAttribute("resultado", "");
        }else{
        	request.setAttribute("resultado", "Error al autenticar");
            return mapping.findForward("errorLogin");
        }

//TODO DESCOMENTAR antes de enviar a cualquier  lado INI
//        if (autentica(request)) {
//            request.setAttribute("resultado", "");
//        } else {
//            request.setAttribute("resultado", "Error al autenticar");
//            return mapping.findForward("errorLogin");
//        }
//      TODO DESCOMENTAR antes de enviar a cualquier  lado FIN

//      TODO ELIMINAR antes de enviar a cualquiere  lado
/*        request.setAttribute("resultado", "");
        String user = (String) request.getParameter("txt_Usuario");
        session.setAttribute("IDAnalista", user.substring(0, user.length() - 2));*/
// TODO hasta aca
        
        //String usiarioValidado = (String) session.getAttribute("IDAnalista");
        String[] usuarioValidado = user.split("\\-");
        Long userconectado = Long.parseLong(usuarioValidado[0]);
        

        UsuariosSivegamVO[] usuariosBD = ParametrosDAO.obtenerUsuarios();

        for (int i = 0; i < usuariosBD.length; i++) {
            if (usuariosBD[i].getUsuario_sivegam() == userconectado) {
                perfil = usuariosBD[i].getDescripcion_tipo_perfil();
                autorizado = true;
                break;
            }
        }

        fechaSistema = ParametrosDAO.obtenerFechaSistema();
        if (String.valueOf(34404).equals(fechaSistema)) {
            a = 34404;
        }

        if(a == 0){
            if (!autorizado) {
                request.setAttribute("resultado", "No posee los privilegios para acceder a la aplicación.");
                return mapping.findForward("errorLogin");
            }
        }
        
        System.out.println("DATOS :" );
        System.out.println("FechaSistema" + fechaSistema);
        System.out.println("TiposDeProcesos" + listaParam.getListTipoProcesos());

        
        System.out.println("ListPeriodoProcesos" +  listaParam.getListPeriodoProcesos().length);
        
        System.out.println("ListFormatoReportes" + listaParam.getListFormatoReportes().length);
        System.out.println("ListTipoReporte"+ listaParam.getListTipoReportes().length);
        System.out.println("ListValoresConexionAS400"+ listaParam.getListValoresConexionToAS400().length);
        System.out.println("ListTipoOrigen"+ listaParam.getListTipoOrigen().length);
        System.out.println("ListTipoBeneficiario"+ listaParam.getListTipoBeneficiario().length);
        System.out.println("ListTipoCausante"+ listaParam.getListTipoCausante().length);
        System.out.println("ListTipoBeneficio"+ listaParam.getListTipoBeneficio().length);
        System.out.println("ListCodigoArchivo"+ listaParam.getListCodigoArchivo().length);
        System.out.println("ListTipoEmision"+ listaParam.getListTipoEmision().length);
        System.out.println("ListTipoDeclaracion"+ listaParam.getListTipoDeclaracion().length);
        System.out.println("ListTipoReintegro"+ listaParam.getListTipoReintegro().length);
        System.out.println("ListTipoSaldo"+ listaParam.getListTipoSaldo().length);
        System.out.println("ListCausalReliquidacion"+ listaParam.getListCausalReliquidacion().length);
        System.out.println("ListTipoEgreso"+ listaParam.getListTipoEgreso().length);
        System.out.println("ListModalidadPago"+ listaParam.getListModalidadPago().length);
        System.out.println("ListCodigoBanco"+ listaParam.getListCodigoBanco().length);
        System.out.println("ListEstadoDocumento"+ listaParam.getListEstadoDocumento().length);
        System.out.println("ListTipoArchivo"+ listaParam.getListTipoArchivo().length);
        System.out.println("ListCodigoTramo"+ listaParam.getListCodigoTramo().length);
        
        

        session.setAttribute("IDAnalista", usuarioValidado[0]);
        session.setAttribute("FechaSistema", fechaSistema);
        session.setAttribute("TiposDeProcesos", listaParam.getListTipoProcesos());
        session.setAttribute("ListStatusProceso", listaParam.getListStatusProceso());
        session.setAttribute("ListPeriodoProcesos", listaParam.getListPeriodoProcesos());
        session.setAttribute("ListFormatoReportes", listaParam.getListFormatoReportes());
        session.setAttribute("ListTipoReporte", listaParam.getListTipoReportes());
        session.setAttribute("ListValoresConexionAS400", listaParam.getListValoresConexionToAS400());
        session.setAttribute("ListTipoOrigen", listaParam.getListTipoOrigen());
        session.setAttribute("ListTipoBeneficiario", listaParam.getListTipoBeneficiario());
        session.setAttribute("ListTipoCausante", listaParam.getListTipoCausante());
        session.setAttribute("ListTipoBeneficio", listaParam.getListTipoBeneficio());
        session.setAttribute("ListCodigoArchivo", listaParam.getListCodigoArchivo());
        session.setAttribute("ListTipoEmision", listaParam.getListTipoEmision());
        session.setAttribute("ListTipoDeclaracion", listaParam.getListTipoDeclaracion());
        session.setAttribute("ListTipoReintegro", listaParam.getListTipoReintegro());
        session.setAttribute("ListTipoSaldo", listaParam.getListTipoSaldo());
        session.setAttribute("ListCausalReliquidacion", listaParam.getListCausalReliquidacion());
        session.setAttribute("ListTipoEgreso", listaParam.getListTipoEgreso());
        session.setAttribute("ListModalidadPago", listaParam.getListModalidadPago());
        session.setAttribute("ListCodigoBanco", listaParam.getListCodigoBanco());
        session.setAttribute("ListEstadoDocumento", listaParam.getListEstadoDocumento());
        session.setAttribute("ListTipoArchivo", listaParam.getListTipoArchivo());
        session.setAttribute("ListCodigoTramo", listaParam.getListCodigoTramo());
        
        
        if (a == 0) {
        	try {
        		forwardPage = (forwardPage.length()==0?null:forwardPage);
				response.sendRedirect(request.getContextPath()+"/"+forwardPage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //return mapping.findForward("forwardPage");
        } else {
            session.setAttribute("Error","Ha ocurrido un error al conectar con el servidor de " +
            	"Base de Datos. Intentelo nuevamente en unos minutos mas. Si el problema persiste, " +
            	"comuniquese con su administrador del Sistema.");
            return mapping.findForward("login");
        }
        return null;
    }

    /*
     * Función que autentica. Usa sesion de LDAP. recibe como entrada un request
     * retorna los datos consultados de un usuario ldap.
     */
    public boolean autentica(HttpServletRequest request) {

        String user = (String) request.getParameter("txt_Usuario");
        String pass = (String) request.getParameter("txt_Clave");

        boolean resp = false;

        HttpSession session = request.getSession();

        try {

            User usuario = null;
            UserRegistryConnection conn = new UserRegistryConnection(user, pass);

            usuario = conn.getUser(user);

            session.setAttribute("IDAnalista", user.substring(0, user.length() - 2));
            session.setAttribute("NombreAnalista", usuario.getName());
            session.setAttribute("ApePatAnalista", usuario.getFirstName());
            session.setAttribute("ApeMatAnalista", usuario.getLastName());
            session.setAttribute("Error", "");

            resp = true;
        } catch (UserRegistryException e) {

            resp = false;
        }

        return resp;

    }
}
