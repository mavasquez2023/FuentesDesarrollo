package cl.araucana.cp.presentation.struts.actions.nomina;

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

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
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
 * @(#) ListarErroresAction.java 1.0 23/08/2010
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
public class ListarErroresAction extends AppAction
{
	static Logger logger = Logger.getLogger(ListarErroresAction.class);

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
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			PersonaMgr personaMgr = new PersonaMgr(session);
			
			String idEmpresa  =  (String)request.getParameter("idEmpresa");
			String tipoNomina =  (String)request.getParameter("tipoNomina");
			String idConvenio =  (String)request.getParameter("idConvenio");
			String aviso =  (String)request.getParameter("aviso");
			
			FactoryView fact = new FactoryView();
			ConvenioVO convenio = (new ConvenioMgr(session)).getConvenio(new Integer(idEmpresa).intValue(), new Integer(idConvenio).intValue());
			GrupoConvenioVO grupoConvenio = (new ConvenioMgr(session)).getGrupoConvenio(convenio.getIdGrupoConvenio());
			fact.setGrupoConvenioVO(grupoConvenio);
			List listaConceptos = conceptoMgr.getListaConceptos("" + tipoNomina);
			List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(tipoNomina.charAt(0)), "" + tipoNomina);
			Properties mapConceptos = new Properties();
			mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
			
			fact.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);

			UsuarioCP usuarioCP = (UsuarioCP) user;
			// Recupera las empresas de las que es encargado el user o es administrador
			List listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());						
			
			List listadoResumen = procesoMgr.getInformeErrores(idEmpresa, idConvenio, tipoNomina, aviso, tipoNomina.charAt(0), fact, listaEmpresas);
						
			NominaVO nomina = procesoMgr.getNomina(tipoNomina, Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio));
			
			EmpresaVO empresa = empresaMgr.getEmpresa(Integer.parseInt(idEmpresa));
			empresa.setIdEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));						
			
			Informe informe = new Informe();
			informe.setEmpresa(empresa);
			informe.setListadoResumen(listadoResumen);
			informe.setTipoProceso(tipoNomina);
			informe.setNomina(nomina);
			
			List nuevoResumen = new ArrayList();
			
			
			for (int i = 0 ; i < informe.getListadoResumen().size() ; i++){
				ResumenInforme resumen = (ResumenInforme)informe.getListadoResumen().get(i);
				Cotizante cot = resumen.getCotizantePendiente();
				
//				clillo 3-12-14 por Reliquidación
				//CotizacionPendienteVO cotPendVO = cotizanteMgr.getCotizPend(Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), tipoNomina.charAt(0), cot.getIdCotizPendiente());
				CotizacionPendienteVO cotPendVO = cotizanteMgr.getCotizPend(Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), tipoNomina.charAt(0), cot.getIdCotizPendiente(), cot.getPeriodo());
//				clillo 3-12-14 por Reliquidación, se agrega idCotizante para poder recuperar Avisos
				cotPendVO.setIdCotizante(cot.getIdCotizante());
				//HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausas(tipoNomina.charAt(0), cotPendVO));
				HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausasVO(cotizanteMgr.getCausasVO(tipoNomina.charAt(0), cotPendVO));

				//nivelErrorTrab.get("errores");
				//nivelErrorTrab.get("avisos");
				//nivelErrorTrab.get("descripcionError");
				resumen.setErrores((List)nivelErrorTrab.get("errores"));
				resumen.setAvisos((List)nivelErrorTrab.get("avisos"));
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
