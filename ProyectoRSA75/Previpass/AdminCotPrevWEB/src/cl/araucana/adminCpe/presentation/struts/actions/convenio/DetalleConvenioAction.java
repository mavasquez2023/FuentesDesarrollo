package cl.araucana.adminCpe.presentation.struts.actions.convenio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.struts.forms.convenio.DetalleConvenioActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) DetalleConvenioAction.java 1.8 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.8
 */
public class DetalleConvenioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleConvenioAction.class);
	ConvenioMgr convenioMgr;
	/**
	 * llena combos convenios
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombos(DetalleConvenioActionForm actForm) throws DaoException 
	{
		//Combo convenios
		List listaConvenios = new ArrayList(this.convenioMgr.getConveniosEmpresa(actForm.getRutEmpresa()));
		List convenios = new ArrayList();
		ConvenioVO convenio;
		for (Iterator it = listaConvenios.iterator(); it.hasNext();) {
			convenio = (ConvenioVO) it.next();
			if (convenio.getIdEmpresa() != actForm.getRutEmpresa())
				continue;
			convenios.add(new LabelValueBean(convenio.getDescripcion().trim(), Integer.toString(convenio.getIdConvenio())));
		}
		Collections.sort(convenios, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setConvenios(convenios);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>conveniosFicha</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) del convenio para el que se quiere mostrar la ficha.</dd>
	 * <dt>idConvenio</dt>
	 * <dd>El id del convenio que se quiere mostrar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Editar, se redirecciona a la edicion del convenio.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DetalleConvenioActionForm actForm = (DetalleConvenioActionForm) form;
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			this.convenioMgr = new ConvenioMgr(session);
			MapeosMgr mapeosMgr = new MapeosMgr(session);
			
			int rut = -1;
			int idConvenio = -1;

			if (request.getParameter("operacion") != null) 
			{
				//Lamado desde dentro
				if (request.getParameter("operacion").equals("Editar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("Editar")); 
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", request.getParameter("subSubAccion"));
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					redirect.addParameter("idConvenio", actForm.getOpcConvenio());
					
					tx.commit();
					
					return redirect;
				}
			} else 
			{
				//Llamado desde afuera o desde combo convenio
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));
				if (actForm.getOpcConvenio() == null) 
				{
					idConvenio = Integer.parseInt(request.getParameter("idConvenio"));
					actForm.setOpcConvenio("" + idConvenio);
				} else 
					idConvenio = Integer.parseInt(actForm.getOpcConvenio());
			}

			//Llena los combos para editar los convenios
			llenaCombos(actForm);
			
			EmpresaVO empresa = empresaMgr.getEmpresa(rut);
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());
			actForm.setRutEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			actForm.setRutEmpresa(rut);
			
			ConvenioVO convenio = this.convenioMgr.getConvenio(rut, idConvenio);
			ActividadEconomicaVO actividadEconomica = empresaMgr.getActividadEconomica(convenio.getIdActividad());
			
			actForm.setOpcHabilitado(Integer.toString(convenio.getHabilitado()));
			actForm.setOpcGrupoConvenio(Integer.toString(convenio.getIdGrupoConvenio()));
			actForm.setNombreConvenio(convenio.getDescripcion().trim());
			actForm.setCodigoConvenio(Integer.toString(convenio.getIdConvenio()));
			actForm.setOpcActividadEconomica(Integer.toString(convenio.getIdActividad()));
			actForm.setOpcActividadEconomicaMostrar(Integer.toString(convenio.getIdActividad()));
			actForm.setNombreActividadEconomica(actividadEconomica.getNombre().trim());
			actForm.setOpcCaja(Integer.toString(convenio.getIdCcaf()));
			actForm.setOpcCalculoMovPersonal(convenio.getCalculoMovPersonal());
			
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
			actForm.setNombreGrupoConvenio(grupoConvenio.getNombre().trim());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
			actForm.setFechaCreado(dateFormat.format(convenio.getCreado()));
			actForm.setFechaUltAcceso(dateFormat.format(convenio.getUltimoUso()));
			
			if (convenio.getIdCcaf() == Constants.SIN_CCAF)
				actForm.setNombreCaja("Sin Caja");
			else
				actForm.setNombreCaja(entidadesMgr.getCaja(convenio.getIdCcaf()).getNombre().trim());
			actForm.setNumNominas(Integer.toString(convenio.getNumNominas()));
			actForm.setNumCotiz(Integer.toString(convenio.getNumCotizaciones()));
			
			actForm.setNombreMapeoCodigo(mapeosMgr.getMapaCodigo(grupoConvenio.getIdMapaCod()).getDescripcion().trim());
			actForm.setPermisos(this.convenioMgr.getConvenioEncLector(idConvenio, rut));

			GrupoConvenioVO grupo = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
			String mapeoNoDef = "Mapeo indefinido";
			MapaNominaVO mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRem());
			actForm.setDescripcionR(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomGra());
			actForm.setDescripcionG(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRel());
			actForm.setDescripcionA(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomDep());
			actForm.setDescripcionD(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			
			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en EditarConvenioAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}  
	}

}
