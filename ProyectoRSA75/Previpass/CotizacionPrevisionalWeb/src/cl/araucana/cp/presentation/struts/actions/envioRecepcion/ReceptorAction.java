package cl.araucana.cp.presentation.struts.actions.envioRecepcion;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.mail.ReportaError;
import cl.araucana.cp.mail.data.Mail;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.DistribuidorMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.ReceptorMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.receipt.DesEncrypt;

import com.bh.talon.User;
/*
* @(#) ReceptorAction.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 *
 * @version 1.4
 */

public class ReceptorAction extends AppAction
{
	private static final long serialVersionUID = -2811374591656810485L;

	private static final Logger logger = Logger.getLogger(ReceptorAction.class);
	/**
	 * receptor
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String idPersona = "";
		logger.info("ReceptorAction:doTask");

		// CHANGE_ADAPTED
		ActionForward forward = null;

		try
		{
			boolean envioNormal = true;
			
			if(this.subAccion.equals("envioUnicaNomina")){
				envioNormal= false;
			}
			
			if(envioNormal){
			
						try
						{
							//Obtiene el id del usuario que esta realizando el envio
							logger.info("\n\nPK:" + request.getParameter("pk") + "::");
							idPersona = new DesEncrypt().decodifica(request.getParameter("pk"));
							
							
							
							logger.info("idPersona=" + idPersona + "::");
						} catch (Exception e)
						{
							logger.info("Token expirado");
							PrintWriter out = response.getWriter();
							out.println("<!-- expired -->");
							out.close();
							return null;
						}
						Transaction tx = null;
						int result = -1;
						int idEnvio = -1;
						Session session = null;
						try
						{
							session = HibernateUtil.getSession();
							tx = session.beginTransaction();
			
							ReceptorMgr receptorMgr = new ReceptorMgr(session);
							UsuarioMgr usuarioMgr = new UsuarioMgr(session);
			
							HibernateUtil.setIdUser("", idPersona);
							PersonaVO usuario = usuarioMgr.getPersona(new Integer(idPersona).intValue());
							//Recibe la nomina
							result = receptorMgr.recibirEnvio(request, usuario);
							idEnvio = receptorMgr.getIdEnvio();
							tx.commit();
			
						} catch (DaoException e)
						{
							if (tx != null)
								tx.rollback();
							logger.error("Problemas con el proceso de registro en base de datos", e);
						} catch (Exception e)
						{
							if (tx != null)
								tx.rollback();
							logger.error("Problemas durante la recepcion", e);
						}
			
						// CHANGE_ADAPTED
						Boolean adapted = (Boolean) request.getAttribute("adaptedReceiver");
			
						if (adapted == Boolean.TRUE) {
							request.setAttribute("methodName", "Dispatcher (ADAPTED)");
							request.removeAttribute("returnURL");
			
							System.out.println("ReceptorAction: adapted request was detected.");
						}
			
						try
						{
							if (result == Constants.EST_ENVIO_EXITOSO)
							{
								//Carga el nodo
								DistribuidorMgr distribuidorMgr = new DistribuidorMgr(session, idPersona);
								HashMap asignacionNodos = distribuidorMgr.asignaNodos(idEnvio, idPersona);
			
								if (asignacionNodos != null)
								{
									//Presenta el reporte de exito
									if (adapted == Boolean.TRUE) {				// CHANGE_ADAPTED
										forward = mapping.findForward("showReceiptReport");
									} else {
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
										requestDispatcher.forward(request, response);
									}
			
									//Procesa la nomina
									distribuidorMgr.valida(asignacionNodos);
								} else
								{
									rechazaNominas(idEnvio, true);
									try
					    			{
										ParametroMgr parametroMgr = new ParametroMgr(session);
										ParametrosHash param = parametroMgr.getParamEnvioMail();
						    			Mail mail = getMail(param);
						    			mail.setContenido(new StringBuffer("No se encontro ningun nodo activo para el procesamiento del envio."));
			
						    			mail.setDescCorta("Ha ocurrido un error al recibir envio, no se obtubo ningun nodo para asignar a procesamiento.");
						    			ReportaError.enviar(mail);
					    			} catch (Exception e)
					    			{
					    				logger.error("Problemas durante envio mail error", e);
					    			}
			
									if (adapted == Boolean.TRUE) {					// CHANGE_ADAPTED
										forward = mapping.findForward("showReceiptReport");
									} else {
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
										requestDispatcher.forward(request, response);
									}
								}
							} else
							{
								rechazaNominas(idEnvio, false);
			
								//Presenta el reporte de fallo
								if (adapted == Boolean.TRUE) {	// CHANGE_ADAPTED
										
									forward = mapping.findForward("showReceiptReport");	
								
								} else {
									RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
									requestDispatcher.forward(request, response);
								}
							}
			
			} catch (Exception e)
			{
				logger.error("Problemas durante la recepcion", e);
				rechazaNominas(idEnvio, false);
				try
				{
					StringWriter sw = new StringWriter();
					e.printStackTrace(new PrintWriter(sw));

					ParametroMgr parametroMgr = new ParametroMgr(session);
					ParametrosHash param = parametroMgr.getParamEnvioMail();
	    			Mail mail = getMail(param);
	    			mail.setContenido(new StringBuffer(sw.toString()));

	    			mail.setDescCorta("Ha ocurrido un error al recibir envio, caida de sistema.");
	    			ReportaError.enviar(mail);
				} catch (Exception ee) {}
			}
		}else{
			//Envio nomina en gistion de pago
			
			try
			{
				//Obtiene el id del usuario que esta realizando el envio
				logger.info("\n\nPK:" + request.getParameter("pk") + "::");
				idPersona = new DesEncrypt().decodifica(request.getParameter("pk"));
				
				logger.info("idPersona=" + idPersona + "::");
			} catch (Exception e)
			{
				logger.info("Token expirado");
				PrintWriter out = response.getWriter();
				out.println("<!-- expired -->");
				out.close();
				return null;
			}
			Transaction tx = null;
			int result = -1;
			int idEnvio = -1;
			Session session = null;
			try
			{
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();
				
				ReceptorMgr receptorMgr = new ReceptorMgr(session);
				UsuarioMgr usuarioMgr = new UsuarioMgr(session);

				HibernateUtil.setIdUser("", idPersona);
				PersonaVO usuario = usuarioMgr.getPersona(new Integer(idPersona).intValue());
				//Recibe la nomina
				result = receptorMgr.recibirEnvio(request, usuario);
				idEnvio = receptorMgr.getIdEnvio();
				tx.commit();

			} catch (DaoException e)
			{
				if (tx != null)
					tx.rollback();
				logger.error("Problemas con el proceso de registro en base de datos", e);
			} catch (Exception e)
			{
				if (tx != null)
					tx.rollback();
				logger.error("Problemas durante la recepcion", e);
			}

			// CHANGE_ADAPTED
			Boolean adapted = (Boolean) request.getAttribute("adaptedReceiver");

			if (adapted == Boolean.TRUE) {
				request.setAttribute("methodName", "Dispatcher (ADAPTED)");
				request.removeAttribute("returnURL");

				System.out.println("ReceptorAction: adapted request was detected.");
			}

			try
			{
				if (result == Constants.EST_ENVIO_EXITOSO)
				{
					//Carga el nodo
					DistribuidorMgr distribuidorMgr = new DistribuidorMgr(session, idPersona);
					HashMap asignacionNodos = distribuidorMgr.asignaNodos(idEnvio, idPersona);

					if (asignacionNodos != null)
					{
						//Presenta el reporte de exito
						if (adapted == Boolean.TRUE) {				// CHANGE_ADAPTED
							forward = mapping.findForward("showReceiptReportNomUnica");
						} else {
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
							requestDispatcher.forward(request, response);
						}

						//Procesa la nomina
						distribuidorMgr.valida(asignacionNodos);
					} else
					{
						rechazaNominas(idEnvio, true);
						try
		    			{
							ParametroMgr parametroMgr = new ParametroMgr(session);
							ParametrosHash param = parametroMgr.getParamEnvioMail();
			    			Mail mail = getMail(param);
			    			mail.setContenido(new StringBuffer("No se encontro ningun nodo activo para el procesamiento del envio."));

			    			mail.setDescCorta("Ha ocurrido un error al recibir envio, no se obtubo ningun nodo para asignar a procesamiento.");
			    			ReportaError.enviar(mail);
		    			} catch (Exception e)
		    			{
		    				logger.error("Problemas durante envio mail error", e);
		    			}

						if (adapted == Boolean.TRUE) {					// CHANGE_ADAPTED
							forward = mapping.findForward("showReceiptReportNomUnica");
						} else {
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
							requestDispatcher.forward(request, response);
						}
					}
				} else
				{
					rechazaNominas(idEnvio, false);

					//Presenta el reporte de fallo
					if (adapted == Boolean.TRUE) {	// CHANGE_ADAPTED
							
						forward = mapping.findForward("showReceiptReportNomUnica");
					} else {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/showReceiptReport.jsp");
						requestDispatcher.forward(request, response);
					}
				}

				} catch (Exception e)
				{
					logger.error("Problemas durante la recepcion", e);
					rechazaNominas(idEnvio, false);
					try
					{
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
				
						ParametroMgr parametroMgr = new ParametroMgr(session);
						ParametrosHash param = parametroMgr.getParamEnvioMail();
						Mail mail = getMail(param);
						mail.setContenido(new StringBuffer(sw.toString()));
				
						mail.setDescCorta("Ha ocurrido un error al recibir envio, caida de sistema.");
						ReportaError.enviar(mail);
					} catch (Exception ee) {}
				}
		}
		} finally
		{
			HibernateUtil.closeSession();
		}

		return forward;
	}

	private Mail getMail(ParametrosHash param)
	{
		Mail mail = new Mail(Mail.ERROR, param.get("" + Constants.PARAM_MAIL_TO), param.get("" + Constants.PARAM_MAIL_HOST_TO),
				Integer.parseInt(param.get("" + Constants.PARAM_MAIL_PORT)), param.get("" + Constants.PARAM_MAIL_FROM),
				param.get("" + Constants.PARAM_MAIL_USER), param.get("" + Constants.PARAM_MAIL_PASS));
		mail.setLocalHost(param.get("" + Constants.PARAM_MAIL_HOST_LOCAL));
		return mail;
	}

	private void rechazaNominas(int idEnvio, boolean rechazoPorNodo)
	{
		Transaction tx = null;
		try
		{
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			ReceptorMgr receptorMgr = new ReceptorMgr(session);
			receptorMgr.rechazaNominas(idEnvio, rechazoPorNodo);
			tx.commit();
		} catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
		}
	}
}
