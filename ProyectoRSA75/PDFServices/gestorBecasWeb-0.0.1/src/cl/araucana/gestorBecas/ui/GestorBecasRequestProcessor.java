/**
 * 
 */
package cl.araucana.gestorBecas.ui;

/**
 * @author J-Factory
 *
 */
import cl.araucana.gestorBecas.bpro.GestorBecasBusiness;
import java.io.PrintStream;
import java.security.Principal;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.struts.tiles.TilesRequestProcessor;


public class GestorBecasRequestProcessor extends TilesRequestProcessor
{
	private static Logger logger;
    private static String OPCION_SELECCIONADA = "_opcid";

    static 
    {
        logger = Logger.getLogger(GestorBecasRequestProcessor.class);
    }
    
    public GestorBecasRequestProcessor()
    {
    }

    public boolean processPreprocess(HttpServletRequest req, HttpServletResponse resp)
    {
    	logger.info("Ingreso Gestor Becas");
    	logger.info("Requerimiento de Usuario: " + (req.getUserPrincipal() != null ? req.getUserPrincipal().getName() : null));
    	logger.info("Detectando opcion de menu");
    	String opcion = WebUtils.getCleanData(req.getParameter(OPCION_SELECCIONADA));
    	logger.info("Opcion --> " + opcion);
    	if(opcion != null)
    	{
    		WebUtils.setTitle(req, "title." + opcion);
    		req.getSession(true).setAttribute(OPCION_SELECCIONADA, opcion);
    	}
    	String action = req.getServletPath();
    	logger.info("Procesando Action --> " + action);
    	Locale strutsLocale = (Locale)req.getSession(true).getAttribute("org.apache.struts.action.LOCALE");
    	if(strutsLocale == null || !"es_CL".equalsIgnoreCase(strutsLocale.toString()))
    		req.getSession(true).setAttribute("org.apache.struts.action.LOCALE", new Locale("es", "cl"));
    	req.getSession(true).setAttribute("contextRoot", req.getContextPath());
    	req.getSession(true).setAttribute("now", new Date());

        try
        {
            if(req.getUserPrincipal()!=null && req.getUserPrincipal().getName() != null && !req.getUserPrincipal().getName().equals(""))
            {
                String rolUsuario = (String)req.getAttribute("rolUsuario");
                if(rolUsuario == null || rolUsuario.equals(""))
                {
                    String rut = req.getUserPrincipal().getName();
                    try
                    {
                        GestorBecasBusiness gestorBecasBusiness = new GestorBecasBusiness();
                        String rol = gestorBecasBusiness.obtenerRolUsuario(WebUtils.getCuerpoRut(rut).toString());
                        if(rol==null){
                        	rol="EJECUTIVO";
                        }
                        rol = rol.equals("ADMINISTRADOR") ? "administradorRole" : rol.equals("EJECUTIVO") ? "ejecutivoRole" : "";
                        req.setAttribute("rolUsuario", rol);
                        logger.info("ROL=" + rol);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        return true;
    }

    
}

