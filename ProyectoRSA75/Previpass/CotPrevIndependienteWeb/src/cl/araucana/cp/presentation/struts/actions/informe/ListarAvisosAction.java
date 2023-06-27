package cl.araucana.cp.presentation.struts.actions.informe;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.presentation.beans.Informe;
import cl.araucana.cp.distribuidor.presentation.beans.ResumenInforme;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.utils.FactoryView;

import com.bh.talon.User;

/*
 * @(#) ListarErrorAvisoAction.java 1.0 23/08/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jlandero
 * 
 * @version 1.0
 */
public class ListarAvisosAction extends AppAction
{
	static Logger logger = Logger.getLogger(ListarAvisosAction.class);

	/**
	 *	Action encargado de mostrar la informacion sobre el listado de errores
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			PersonaMgr personaMgr = new PersonaMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);
			
			String idEmpresa  =  (String)request.getParameter("idEmpresa");
			String tipoNomina =  (String)request.getParameter("tipoNomina");
			String idConvenio =  (String)request.getParameter("idConvenio");
			String aviso =  (String)request.getParameter("aviso");
			
			int origenAvisos = Integer.parseInt(request.getParameter("origenTablaAviso")); 
			
			UsuarioCP usuarioCP = (UsuarioCP) user;
			// Recupera las empresas de las que es encargado el user o es administrador
			List listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());			

			List listadoResumen;
			if (origenAvisos == Constants.AVISOS_TABLA_CAUSAAVISOCXP) { 
				listadoResumen = procesoMgr.getInformeAvisos(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas, null);
			} else {
				FactoryView fact = new FactoryView();
				ConvenioVO convenio = (new ConvenioMgr(session)).getConvenio(new Integer(idEmpresa).intValue(), new Integer(idConvenio).intValue());
				GrupoConvenioVO grupoConvenio = (new ConvenioMgr(session)).getGrupoConvenio(convenio.getIdGrupoConvenio());
				List listaConceptos = conceptoMgr.getListaConceptos("" + tipoNomina);
				List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(tipoNomina.charAt(0)), "" + tipoNomina);
				Properties mapConceptos = new Properties();
				mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
				fact.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);				

				listadoResumen = procesoMgr.getInformeAvisosPendientes(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas, origenAvisos, fact);
			}
				
			NominaVO nomina = procesoMgr.getNomina(tipoNomina, Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio));
			
			EmpresaVO empresa = empresaMgr.getEmpresa(Integer.parseInt(idEmpresa));
			empresa.setIdEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));

			Informe informe = new Informe();
			informe.setEmpresa(empresa);
			informe.setListadoResumen(listadoResumen);
			informe.setTipoProceso(tipoNomina);
			informe.setNomina(nomina);

			List nuevoResumen = new ArrayList();
			List causas = null;
			HashMap _nivelErrorTrab;

			for (int i = 0 ; i < informe.getListadoResumen().size() ; i++){
				_nivelErrorTrab = null;
				ResumenInforme resumen = (ResumenInforme)informe.getListadoResumen().get(i);
				CotizanteVO cot = resumen.getCotizante();

				if (origenAvisos == Constants.AVISOS_TABLA_CAUSAAVISOCXP) { 
					causas = cotizanteMgr.getCausasAvisos(tipoNomina.charAt(0), Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), cot.getIdCotizante());
				} else {
					causas = cotizanteMgr.getCausasAvisosPendientes(tipoNomina.charAt(0), Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), cot.getIdCotizante());
				}

				_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(causas);
				
				if (_nivelErrorTrab.size() == 0)
					_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(causas);				

				resumen.setErrores((List)_nivelErrorTrab.get("errores"));
				resumen.setAvisos((List)_nivelErrorTrab.get("avisos"));
				nuevoResumen.add(resumen);
			}
			informe.setListadoResumen(nuevoResumen);

			request.setAttribute("informe", informe);
			request.setAttribute("fechaHoy", new Date());

			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			if (tx != null)
				tx.rollback();
			logger.error("Ha ocurrido la siguiente excepcion: ", ex);
			return mapping.findForward("error");
		}
	}
}
