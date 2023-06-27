package cl.araucana.cp.presentation.struts.actions.cargasFamiliares;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.presentation.beans.Informe;
import cl.araucana.cp.distribuidor.presentation.beans.ResumenInforme;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;

import com.bh.talon.User;

public class ListarAvisoCargasAction extends AppAction {
	static Logger logger = Logger.getLogger(ListarAvisoCargasAction.class);

	/**
	 *	Action encargado de mostrar la informacion sobre el listado de errores
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			PersonaMgr personaMgr = new PersonaMgr(session);
			
			String idEmpresa  = (String) request.getParameter("idEmpresa");
			String tipoNomina = (String) request.getParameter("tipoNomina");
			String idConvenio = (String) request.getParameter("idConvenio");
			String aviso      = (String) request.getParameter("aviso");

			UsuarioCP usuarioCP = (UsuarioCP) user;
			// Recupera las empresas de las que es encargado el user o es administrador
			List listaEmpresas = personaMgr.getListaEmpresasIn(usuarioCP.getUnionEmpresasLectura());			

			List listadoResumen = procesoMgr.getInformeAvisos(idEmpresa, idConvenio, tipoNomina, aviso, listaEmpresas, Constants.AVISOS_CARGASFAM);
				
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

			for (int i = 0 ; i < informe.getListadoResumen().size() ; i++) {
				_nivelErrorTrab = null;
				ResumenInforme resumen = (ResumenInforme)informe.getListadoResumen().get(i);
				CotizanteVO cot = resumen.getCotizante();
				
//				clillo 3-12-14 por Reliquidación
				//causas = cotizanteMgr.getCausasAvisosCargas(tipoNomina.charAt(0), Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), cot.getIdCotizante());
				causas = cotizanteMgr.getCausasAvisosCargas(tipoNomina.charAt(0), Integer.parseInt(idEmpresa), Integer.parseInt(idConvenio), cot.getIdCotizante(), cot.getPeriodo());

				_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(causas);
				
				if (_nivelErrorTrab.size() == 0)
					_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(causas);				

				resumen.setErrores(null);
				resumen.setAvisos((List)_nivelErrorTrab.get("avisos"));
				nuevoResumen.add(resumen);
			}
			informe.setListadoResumen(nuevoResumen);

			request.setAttribute("informe", informe);
			request.setAttribute("fechaHoy", new Date());

			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			logger.error("Ha ocurrido la siguiente excepcion: ", ex);
			return mapping.findForward("error");
		}
	}
}