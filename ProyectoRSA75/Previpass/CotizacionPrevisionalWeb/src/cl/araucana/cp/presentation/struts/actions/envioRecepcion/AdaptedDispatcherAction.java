package cl.araucana.cp.presentation.struts.actions.envioRecepcion;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;

import com.bh.talon.User;


public class AdaptedDispatcherAction extends AppAction {
	
	/*
	 * TODO Tipos de nóminas.
	 * 
	 * Los tipos de nóminas debieran ser obtenidos desde la tabla
	 * TIPO_NOMINA y no en duro.
	 */
	private static final String tipoNominas = "RGDArgda";
	
	static Logger logger = Logger.getLogger(AdaptedDispatcherAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UsuarioCP usuarioCP = (UsuarioCP) user;
		Session session = null;

		// System.out.println("AdaptedDispatcherAction:" + usuarioCP);
		
		/*
		 * JavaScript Code Generation.
		 * 
		 * Sample:
		 * 
		 * 		var tipoNominas = new Array(4);
		 *
	     *      tipoNominas[0] = "R";
	     *      tipoNominas[1] = "G";
	     *      tipoNominas[2] = "D";
	     *      tipoNominas[3] = "A";
	     *      
	     *      var empresas = new Array(5);
		 *
	     *      empresas[0] = "10450366=ACME Corporation";
	     *      empresas[1] = "13059060=Sun microsystems";
	     *      empresas[2] = "9152041=Lan Chile S.A.";
	     *      empresas[3] = "7836778=SERVICORP S.A.";
	     *      empresas[4] = "81943490=Colegio Salesiano de Santiago";
	     * 
	     *      var convenios = new Array(9);
		 *
	     *      convenios[0] = "10450366,1";
	     *      convenios[1] = "10450366,2";
	     *      convenios[2] = "13059060,1";
	     *      convenios[3] = "9152041,1";
	     *      convenios[4] = "9152041,2";
	     *      convenios[5] = "9152041,3";
	     *      convenios[6] = "7836778,1";
	     *      convenios[7] = "7836778,2";
	     *      convenios[8] = "81943490,1";
	     */
		String tipoNominasCode =
				  "var tipoNominas = "
				+ "new Array(" + tipoNominas.length() + ");\n\n";

		String enumTipoNominas = "";
		
		for (int i = 0; i < tipoNominas.length(); i++) {
			char tipoNomina = tipoNominas.charAt(i);
			
			tipoNominasCode +=
					"tipoNominas[" + i + "] = \"" + tipoNomina + "\";\n";
			
			enumTipoNominas += tipoNomina;
			
			if (i < tipoNominas.length() - 2) {
				enumTipoNominas += ", ";
			} else if (i == tipoNominas.length() - 2) {
				enumTipoNominas += " o ";
			}
		}
		
		try {
			session = HibernateUtil.getSession();

			// BUG_SEND_NOMINAS (ADAPTED)
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			List conveniosPermEsc = convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
			List conveniosEmpsAdmins = convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
			Set conveniosTodos = new HashSet(CollectionUtils.union(conveniosPermEsc, conveniosEmpsAdmins));
			
			validaMapaNominas(tipoNominas, convenioMgr, conveniosTodos);
			
			int nConvenios = conveniosTodos.size();
			int i = 0;
			
			String conveniosCode =
					"var convenios = new Array(" + nConvenios + ");\n\n";
					
			SortedMap empresas = new TreeMap();
			Iterator conveniosIterator = conveniosTodos.iterator();
			
			while (conveniosIterator.hasNext()) {
				ConvenioVO convenio = (ConvenioVO) conveniosIterator.next();
				
				int idEmpresa = convenio.getIdEmpresa();
				int idConvenio = convenio.getIdConvenio();
				
				conveniosCode +=
						  "convenios[" + i++ + "] = "
						+ "\"" + idEmpresa + "," + idConvenio + "\";\n";
				
				EmpresaVO empresa = empresaMgr.getEmpresa(idEmpresa);
				//TODO 06/06/2012 GMALLEA Se agrega al if la consulta si las empresas son de tipo EMPRESA para que muestre solo las empresas
				if (empresas.get(empresa.getRazonSocial()) == null && empresa.getTipo().equals(Constants.TIPO_EMPRESA)){
					empresas.put(empresa.getRazonSocial(), empresa);
				}
			}
			
			int nEmpresas = empresas.size();
			
			i = 0;
			
			String empresasCode =
					"var empresas = new Array(" + nEmpresas + ");\n\n";

			Iterator empresasIterator = empresas.values().iterator();
			
			while (empresasIterator.hasNext()) {
				EmpresaVO empresa = (EmpresaVO) empresasIterator.next();
				
				empresasCode +=
					  "empresas[" + i++ + "] = "
					+ "\"" + empresa.getIdEmpresa() + "="
					+ empresa.getRazonSocial().trim() + "\";\n";		
			}
			
			request.setAttribute("tipoNominasCode", tipoNominasCode);
			request.setAttribute("enumTipoNominas", enumTipoNominas);
			request.setAttribute("conveniosCode", conveniosCode);
			request.setAttribute("empresasCode", empresasCode);
			
			/*
			 * Generates empresas select list.
			 */
			String empresasSelect = "";
			
			empresasIterator = empresas.values().iterator();
			
			while (empresasIterator.hasNext()) {
				EmpresaVO empresa = (EmpresaVO) empresasIterator.next();
				empresasSelect += "<option value=\"" + empresa.getIdEmpresa() + "\">"
								+ Utils.formatRut(empresa.getIdEmpresa())
								+ " "
							    + truncate(empresa.getRazonSocial().trim())
								+ "</option>\n";
			}
			
			request.setAttribute("empresasSelect", empresasSelect);
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