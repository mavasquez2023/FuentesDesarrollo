package cl.araucana.cp.presentation.struts.actions.cotizante;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;

import com.bh.talon.User;
/*
 * @(#) BuscarAction.java 1.30 16/09/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jlandero
 * 
 * @version 1.00
 */
public class BuscarAction extends AppAction {

	static Logger logger = Logger.getLogger(BuscarAction.class);

	protected ActionForward doTask( User user
								  , ActionMapping mapping
								  , ActionForm form
								  , HttpServletRequest request
								  , HttpServletResponse response)
	throws Exception {

		String rut        = request.getParameter("rut");
		String rutEmpresa = request.getParameter("idEmp");
		String convenio   = request.getParameter("convenio");
		String nomina     = request.getParameter("nomina");

		response.setHeader("Content-Type", "application/xml");
		response.setContentType("application/xml");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",	"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		try {
			Writer writer = response.getWriter();
			StringBuffer respuesta = new StringBuffer("<respuesta>");

			Session session = HibernateUtil.getSession();
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);

			CotizanteVO cotizante = cotizanteMgr.getCotizante( Integer.parseInt(rutEmpresa)
															 , Integer.parseInt(convenio)
															 , nomina.charAt(0)
															 , Utils.desFormatRut(rut));
			if (cotizante == null) {
				respuesta.append("<estado>0</estado>");			//Trabajador no encontrado
			} else {
				CotizacionVO cotizacion = cotizante.getCotizacion();
				
				if (nomina.equals("R") && cotizacion != null) {
					CotizacionREVO cotiz = (CotizacionREVO) cotizacion;
					if (cotiz.isVoluntario()){
						respuesta.append("<estado>2</estado>");	//Trabajador es Voluntario
					} else {
						respuesta.append("<estado>1</estado>");	//Trabajador es Dependiente
					}
				}

				respuesta.append("<nombre>"   + cotizante.getNombre().trim()      + "</nombre>");
				respuesta.append("<paterno>"  + cotizante.getApellidoPat().trim() + "</paterno>");
				respuesta.append("<materno>"  + cotizante.getApellidoMat().trim() + "</materno>");
				respuesta.append("<genero>"   + cotizante.getIdGeneroReal()       + "</genero>");
				respuesta.append("<sucursal>" + cotizante.getIdSucursal().trim()  + "</sucursal>");
				respuesta.append("<afp>"	  + cotizante.getIdEntPensionReal()	  + "</afp>");
			}

			respuesta.append("</respuesta>");
			writer.write(respuesta.toString());
		} catch (Exception e) {
			logger.error("BuscarAction::" + e.getMessage(), e);
		}
		return null;
	}
}