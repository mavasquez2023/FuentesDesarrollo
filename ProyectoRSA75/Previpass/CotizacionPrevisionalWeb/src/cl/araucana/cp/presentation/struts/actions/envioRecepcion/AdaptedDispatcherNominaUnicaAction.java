package cl.araucana.cp.presentation.struts.actions.envioRecepcion;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;

import com.bh.talon.User;


public class AdaptedDispatcherNominaUnicaAction extends AppAction {
	
	/*
	 * TODO Tipos de nóminas.
	 * 
	 * Los tipos de nóminas debieran ser obtenidos desde la tabla
	 * TIPO_NOMINA y no en duro.
	 */
	private static final String tipoNominas = "RGDArgda";
	
	static Logger logger = Logger.getLogger(AdaptedDispatcherNominaUnicaAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UsuarioCP usuarioCP = (UsuarioCP) user;
		Session session = null;

		String rutEmpresaParam 		=  request.getParameter("rutEmpresa");		
		String rutEmpresa 			=  request.getParameter("rut");
		String razonSocial 			=  request.getParameter("razonSocial");
		String idConv 				=  request.getParameter("idConvenio");
		String desConv	 			=  request.getParameter("desConvenio");
		String tipoProceso 			=  request.getParameter("tipoProceso");
		
		String tipoNominasCode =
				  "var tipoNominas = "
				+ "new Array(" + tipoNominas.length() + ");\n\n";
				
		for (int i = 0; i < tipoNominas.length(); i++) {
			char tipoNomina = tipoNominas.charAt(i);
			
			tipoNominasCode +=
					"tipoNominas[" + i + "] = \"" + tipoNomina + "\";\n";
		}
		request.setAttribute("tipoNominasCode", tipoNominasCode);
		
		String empresasCode =
			"var empresas = new Array(" + 1 + ");\n\n";
		
		empresasCode +=
			  "empresas[" + 0 + "] = "
			+ "\"" + rutEmpresaParam + "="
			+ razonSocial.trim() + "\";\n";			
		request.setAttribute("empresasCode", empresasCode);
		
		try {
			session = HibernateUtil.getSession();
			
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			List conveniosPermEsc = convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
			List conveniosEmpsAdmins = convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
			Set conveniosTodos = new HashSet(CollectionUtils.union(conveniosPermEsc, conveniosEmpsAdmins));
			
			validaMapaNominas(tipoNominas, convenioMgr, conveniosTodos);
			
			int nConvenios = conveniosTodos.size();
			int i = 0;
			
			String conveniosCode =
					"var convenios = new Array(" + nConvenios + ");\n\n";
					
			Iterator conveniosIterator = conveniosTodos.iterator();
			
			while (conveniosIterator.hasNext()) {
				ConvenioVO convenio = (ConvenioVO) conveniosIterator.next();
				
				int idEmpresa = convenio.getIdEmpresa();
				int idConvenio = convenio.getIdConvenio();
				
				conveniosCode +=
						  "convenios[" + i++ + "] = "
						+ "\"" + idEmpresa + "," + idConvenio + "\";\n";
			}
			
			request.setAttribute("conveniosCode", conveniosCode);
			
			
			if(idConv.length()==1){
				idConv= "0"+idConv;
			}
			
			request.setAttribute("divRutEmpresa", rutEmpresa);
			request.setAttribute("divRazonSocial", razonSocial);
			request.setAttribute("divTipoProceso", tipoProceso);
			request.setAttribute("divConvenio", idConv + "-"+desConv);
			
			request.setAttribute("_tipoNomina", tipoProceso);
			request.setAttribute("_convenio", idConv);
			request.setAttribute("_rutEmpresa", rutEmpresaParam);
			
			
		} catch (Exception e) {
			logger.error("Problemas durante el dispatch", e);
			
			// TODO
			return null;
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
		
		return mapping.findForward("envio_adaptado");
	}
	
	private String truncate(String s) {
		final int EMPRESA_RAZON_SOCIAL_MAX_LENGTH = 26;//36

		s = s.trim();
		
		if (s.length() > EMPRESA_RAZON_SOCIAL_MAX_LENGTH) {
			s = s.substring(0, EMPRESA_RAZON_SOCIAL_MAX_LENGTH);
		}
		
		return s;
	}
	
	// BUG_SEND_NOMINAS (ADAPTED)
	/**
	 * Deja en conveniosTodos solo los convenios habilitados y con sus mapeos de nominas bien configurados
	 * @param idsTiposNominas String con los tipos de nominas a procesa, ej: RGAD
	 * @param convenioMgr
	 * @param conveniosTodos lista de convenios a revisar
	 */
	private void validaMapaNominas(String idsTiposNominas, ConvenioMgr convenioMgr, Set conveniosTodos)
	{
		HashMap validacionGrupos = new HashMap();
		Set conveniosDeshabilitados = new HashSet();
		ConvenioVO convenio;
		for (Iterator it = conveniosTodos.iterator(); it.hasNext();) 
		{
			convenio = (ConvenioVO) it.next();
			if (convenio.getHabilitado() == 0)
			{
				logger.debug("\tconv desabilitado:" + convenio.getIdConvenio() + "::");
				conveniosDeshabilitados.add(convenio);
			}
			if (validacionGrupos.containsKey("" + convenio.getIdGrupoConvenio()))
			{
				Boolean validado = (Boolean)validacionGrupos.get("" + convenio.getIdGrupoConvenio());
				if (!validado.booleanValue())//si la configuracion del grupo es invalido
					conveniosDeshabilitados.add(convenio);
			} else
			{
				boolean result = convenioMgr.validaMapaNomGrupo(convenio.getIdGrupoConvenio(), idsTiposNominas);
				validacionGrupos.put("" + convenio.getIdGrupoConvenio(), new Boolean(result));
				if (!result)//si la configuracion del grupo es invalido
					conveniosDeshabilitados.add(convenio);
			}
		}
		conveniosTodos.removeAll(conveniosDeshabilitados);
	}
}