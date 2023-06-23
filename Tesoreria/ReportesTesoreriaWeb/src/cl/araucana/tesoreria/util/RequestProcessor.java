package cl.araucana.tesoreria.util;

import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.tesoreria.dao.ReporteTesoreriaDB2DAOImpl;

public class RequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor {
	public boolean processPreprocess(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(true);
		if (req.getUserPrincipal() != null) {
			try {
				if (req.getUserPrincipal().getName() != null && !req.getUserPrincipal().getName().equals("")) {
					
					String rolUsuario = (String) session.getAttribute("rolUsuario");
					if (rolUsuario == null || rolUsuario.equals("")) {
						try {
							ReporteTesoreriaDB2DAOImpl dao = new ReporteTesoreriaDB2DAOImpl();
							rolUsuario = dao.obtenerRolUsuario(req.getUserPrincipal().getName()
								.substring(0, req.getUserPrincipal().getName().length() - 2));
							if (rolUsuario == null || (!rolUsuario.equals("ADMINISTRADOR") && !rolUsuario.equals("EJECUTIVO"))) {
								WebUtils.setError(req, "message.rol.invalido");
							}
							session.setAttribute("rolUsuario", rolUsuario);
							session.setAttribute("rutUsuario", req.getUserPrincipal().getName());
							//req.setAttribute("rolUsuario", rol);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("Usuario: " + (req.getUserPrincipal() == null ? null : req.getUserPrincipal().getName()));
			Locale strutsLocale = (Locale) req.getSession(true).getAttribute(org.apache.struts.Globals.LOCALE_KEY);
			if (strutsLocale == null || !"es_CL".equalsIgnoreCase(strutsLocale.toString()))
				req.getSession(true).setAttribute(org.apache.struts.Globals.LOCALE_KEY, new Locale("es", "cl"));
			req.getSession(true).setAttribute("contextRoot", req.getContextPath());
			req.getSession(true).setAttribute("now", new Date());
		}
		//session.setAttribute("rolUsuario", "ADMINISTRADOR");
		//session.setAttribute("rutUsuario", "70016160-9");
		return true;
	}
}
