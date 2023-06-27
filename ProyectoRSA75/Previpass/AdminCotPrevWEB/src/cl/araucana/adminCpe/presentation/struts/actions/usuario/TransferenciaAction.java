package cl.araucana.adminCpe.presentation.struts.actions.usuario;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.usuario.TransferenciaActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

import com.bh.talon.User;
/*
* @(#) TransferenciaAction.java 1.9 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author malvarez
 * 
 * @version 1.9
 */
public class TransferenciaAction extends AppAction 
{
	private static Logger logger = Logger.getLogger(TransferenciaAction.class);

	/**
	 * transferencia
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		TransferenciaActionForm form = (TransferenciaActionForm) formulario;
		
		Session session = null;
		Transaction tx = null;
		ActionMessages am = new ActionMessages(); 
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String operacion = (form.getOperacion() == null ? "" : form.getOperacion());
			String retorno = "";
			logger.info("TransferenciaAction:operacion:" + operacion + "::");
			logger.info("TransferenciaAction:RutInicio:" + form.getRutInicioTrans() + "::");
			logger.info("TransferenciaAction:Volver:" + form.getVolver() + "::");
			if (operacion.equals("")) //muestra interfaz vacia
				return mapping.findForward("exito");
			else if (operacion.equals("Buscar"))
			{
				PersonaMgr personaMgr = new PersonaMgr(session);
				PersonaVO personaInicio = personaMgr.getPersona(Utils.desFormatRut(form.getRutInicio()));
				if (personaInicio != null)
				{
					Usuario usuario = new Usuario(personaInicio.getIdPersona().intValue(), Utils.formatRut(personaInicio.getIdPersona().intValue()));
					usuario.setNombre(personaInicio.getNombres().trim());
					usuario.setApellidos(personaInicio.getApellidoPaterno().trim() + " " + personaInicio.getApellidoMaterno().trim());
					form.setUsuarioInicio(usuario);
				} else
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transf.noEncuentra", "de Origen"));
				PersonaVO personaDestino = personaMgr.getPersona(Utils.desFormatRut(form.getRutDestino()));
				if (personaDestino != null)
				{
					Usuario usuario = new Usuario(personaDestino.getIdPersona().intValue(), Utils.formatRut(personaDestino.getIdPersona().intValue()));
					usuario.setNombre(personaDestino.getNombres().trim());
					usuario.setApellidos(personaDestino.getApellidoPaterno().trim() + " " + personaDestino.getApellidoMaterno().trim());
					form.setUsuarioDestino(usuario);
				} else
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transf.noEncuentra", "de Destino"));
					form.setUsuarioInicio(null);
				}
				if (personaInicio != null && personaDestino != null)
				{
					List permEncLector = (List)personaMgr.getPermEncargadoLector(personaInicio.getIdPersona().intValue(), Constants.RUT_EMPRESA_FALSA, "", Constants.ID_GRUPO_FALSO, "").get(0);
					List permAdmin = personaMgr.getEmpresasEsAdmin(personaInicio.getIdPersona().intValue());
					if (permEncLector != null)
					{
						for (Iterator it = permEncLector.iterator(); it.hasNext();)
						{
							GrupoConvenio gc = (GrupoConvenio)it.next();
							for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
							{
								Empresa emp = (Empresa)it2.next();
								for (Iterator it3 = emp.getConvenios().iterator(); it3.hasNext();)
								{
									PermEncargadoLector perm = (PermEncargadoLector)it3.next();
									List sucursales = personaMgr.getSucursalesPermLector(personaInicio.getIdPersona().intValue(), emp.getIdEmpresa(), perm.getIdConvenio());
									StringBuffer sb = new StringBuffer();
									for (Iterator it4 = sucursales.iterator(); it4.hasNext();)
										sb.append(((SucursalVO)it4.next()).getIdSucursal().trim() + "<br />");
									if (sb.toString().length() > 0)
										perm.setSucursalLector(sb.toString().substring(0, sb.toString().length() - 6));
									else
										perm.setSucursalLector(" ");
								}
							}
						}
					}
					
					form.setPermisosEncLector(permEncLector);
					form.setPermisosAdmin(permAdmin);
					if (form.getPermisosEncLector().size() == 0 && form.getPermisosAdmin().size() == 0)
					{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transf.sinPermisos", Utils.formatRut(personaInicio.getIdPersona().intValue())));
						form.setUsuarioInicio(null);
					}
					logger.debug("\n\n\nnAdmin:" + form.getPermisosAdmin().size() + ":nEnc:" + form.getPermisosEncLector().size() + "::");
				}
				retorno = "buscar";
			} else if (operacion.equals("Transferir"))
			{
				PersonaMgr personaMgr = new PersonaMgr(session);
				personaMgr.transferirPermisos(form.getRutInicioTrans(), form.getRutDestinoTrans());
				form.setRutInicio("");
				form.setRutDestino("");

				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transf.ok", Utils.formatRut(form.getRutInicioTrans()), Utils.formatRut(form.getRutDestinoTrans())));
				if (form.getVolver() != null && !form.getVolver().trim().equals(""))
				{
					tx.commit();
					logger.info("transferencia exitosa: desde:" + Utils.formatRut(form.getRutInicioTrans()) + ":hacia:" + Utils.formatRut(form.getRutDestinoTrans()) + "::");
					List lista = (List)request.getSession().getAttribute("listaPath");
					if (lista != null)
					{
						String enlace = (String)lista.get(0);
						enlace = enlace.substring(enlace.indexOf("\"") + 1, enlace.indexOf("\"", enlace.indexOf("\"") + 1));
					 
						ActionRedirect redirect = new ActionRedirect(enlace);					
						this.saveMessages(request.getSession(), am);
						
						return redirect;
					}
				}
				form.setUsuarioInicio(null);
				retorno = "transferir";
			}
			tx.commit();
			this.saveMessages(request, am);
			return mapping.findForward(retorno);
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListarUsuariosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		} 
	}

}
