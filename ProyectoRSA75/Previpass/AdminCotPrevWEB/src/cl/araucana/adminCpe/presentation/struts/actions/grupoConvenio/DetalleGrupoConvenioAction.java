package cl.araucana.adminCpe.presentation.struts.actions.grupoConvenio;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConceptoMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.grupoConvenio.GruposConvenioActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.OpcionProcVO;

import com.bh.talon.User;
/*
* @(#)DetalleGrupoConvenioAction.java 1.9 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author malvarez
 * 
 * @version 1.9
 */
public class DetalleGrupoConvenioAction extends AppAction
{

	private static Logger logger = Logger.getLogger(DetalleGrupoConvenioAction.class);
	
	private static final int EDITAR = 0;
	private static final int CREAR = 1;
	private static final int FICHA = 2;
	
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
	 * <dd>Si este parametro es grupoEditar, se presentara al usuario la edicion de grupos de convenios.
	 * Si es grupoCrear, se presentara la creacion de grupos de convenios.
	 * Si es grupoFicha, se presentara el detalle del grupo de convenios.</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El id del grupo de convenios (<code>int</code>) que se quiere editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Editar, se redirecciona a la edicion de grupos de convenios.
	 * Si es Guardar, guarda el contenido del formulario en la base de datos. Si es Cancelar, se 
	 * redirecciona a la lista de grupos de convenios.</dd>
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
		GruposConvenioActionForm actForm = (GruposConvenioActionForm) form;
		
		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		boolean bGuardar = false;
		int tipoOperacion;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			//Instancia los managers correspondientes
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			MapeosMgr mapeosMgr = new MapeosMgr(session);
			NominaMgr nominaMgr = new NominaMgr(session);

			logger.info("***DetalleGrupoConvenioAction.doTask*** operacion: " + request.getParameter("operacion"));

			if (request.getParameter("subSubAccion").equals("grupoEditar"))
				tipoOperacion = EDITAR;
			else if (request.getParameter("subSubAccion").equals("grupoCrear"))
				tipoOperacion = CREAR;
			else if (request.getParameter("subSubAccion").equals("grupoDetalle"))
				tipoOperacion = FICHA;
			else
				tipoOperacion = -1;

			int idGrupoConvenio = -1;
			
			if ((request.getParameter("operacion") == null)) 
			{
				if (tipoOperacion != CREAR)
					idGrupoConvenio = Integer.parseInt(request.getParameter("grupoConvenio"));
			} else 
			{
				if (request.getParameter("operacion").equals("Editar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Editar"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "grupoEditar");
					redirect.addParameter("grupoConvenio", request.getParameter("grupoConvenio"));
					
					tx.commit();
					
					return redirect;
				} else if (request.getParameter("operacion").equals("Guardar")) 
				{
					bGuardar = true;
					
					GrupoConvenioVO grupo = new GrupoConvenioVO();
					OpcionProcVO opcProc = new OpcionProcVO();
					if (tipoOperacion == EDITAR) 
					{
						idGrupoConvenio = Integer.parseInt(actForm.getIdGrupoConvenio().trim());
						
						grupo = convenioMgr.getGrupoConvenio(idGrupoConvenio);
						opcProc = convenioMgr.getOpcionProceso(grupo.getIdOpcion());
					}

					grupo.setNombre(actForm.getNombreGrupoConvenio().trim());
					grupo.setHabilitado(actForm.getEstadoGrupoConvenio());
					grupo.setPrevired(actForm.getHomologacionPrevired() == 1 ? true : false);

					opcProc.setCalcCcaf(actForm.getCalcularMontoCCAF() == 1 ? true : false);
					opcProc.setCalInp(actForm.getCalcularMontoINP() == 1 ? true : false);
					opcProc.setCalMutual(actForm.getCalcularMontoMutual() == 1 ? true : false);
					opcProc.setCalcTotPrevision(actForm.getCalcularMontoTotalPrev() == 1 ? true : false);
					opcProc.setCalcTotSalud(actForm.getCalcularMontoTotalSalud() == 1 ? true : false);
					opcProc.setCcafSucursal(actForm.getGenCCAFPorSucursal() == 1 ? true : false);
					opcProc.setInpSucursal(actForm.getGenINPPorSucursal() == 1 ? true : false);
					opcProc.setMutualSucursal(actForm.getGenMutualPorSucursal() == 1 ? true : false);
					opcProc.setImprimirPlantillas(actForm.getImprimirPlanillas() == 1 ? true : false);
					opcProc.setCalFonasa(actForm.getCalcularFonasa() == 1 ? true : false);

					if (tipoOperacion == CREAR)
						//idGrupoConvenio = convenioMgr.crearGrupoConvenio(grupo, opcProc);
						idGrupoConvenio = convenioMgr.crearGrupoConvenio(grupo, opcProc, actForm.getIdGrupoConvenioBase(), actForm.getCaracterSeparador());
					else {
						convenioMgr.guardaOpcionProcesos(opcProc);
						mapeosMgr.actualizaMapeoConcepto('R', grupo.getIdMapaNomRem(), Integer.valueOf(actForm.getListaTipoSeparador()).intValue(), actForm.getCaracterSeparador());
						mapeosMgr.actualizaMapeoConcepto('G', grupo.getIdMapaNomGra(), Integer.valueOf(actForm.getListaTipoSeparador()).intValue(), actForm.getCaracterSeparador());
						mapeosMgr.actualizaMapeoConcepto('A', grupo.getIdMapaNomRel(), Integer.valueOf(actForm.getListaTipoSeparador()).intValue(), actForm.getCaracterSeparador());
						mapeosMgr.actualizaMapeoConcepto('D', grupo.getIdMapaNomDep(), Integer.valueOf(actForm.getListaTipoSeparador()).intValue(), actForm.getCaracterSeparador());
						nominaMgr.borraCRCporGrupoConvenio(idGrupoConvenio);
					}
					
					tx.commit();
					
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "grupoLista");

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Grupo de Convenios", Integer.toString(idGrupoConvenio)));
					this.saveMessages(request.getSession(), am);

					return redirect;
				} else if (request.getParameter("operacion").equals("Cancelar")) 
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "grupoLista");
					
					tx.commit();

					return redirect;
				}
			}

			//Llena los datos
			GrupoConvenioVO grupo = new GrupoConvenioVO();

			//csanchez. No se carga el combobox con los Grupos Convenio Base cuando se visualiza.
			if (tipoOperacion != FICHA) {
				List listaGruposBase = convenioMgr.getGrupoConveniosBase();
				List gruposBase = new ArrayList();
				for (Iterator it = listaGruposBase.iterator(); it.hasNext();)
				{
					grupo = (GrupoConvenioVO) it.next();
					gruposBase.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
				}
				Collections.sort(gruposBase, LabelValueBean.CASE_INSENSITIVE_ORDER);
				actForm.setConfiguracionesBase(gruposBase);
				grupo = new GrupoConvenioVO();
			}

			if ((tipoOperacion == EDITAR) || (tipoOperacion == FICHA)) 
			{
				grupo = convenioMgr.getGrupoConvenio(idGrupoConvenio);

				actForm.setIdGrupoConvenio(Integer.toString(grupo.getIdGrupoConvenio()));
				actForm.setNombreGrupoConvenio(grupo.getNombre().trim());
				actForm.setEstadoGrupoConvenio(grupo.getHabilitado() != 0 ? 1 : 0);
				actForm.setHomologacionPrevired(grupo.isPrevired() ? 1 : 0);
				
				OpcionProcVO opcProc = convenioMgr.getOpcionProceso(grupo.getIdOpcion());
				actForm.setCalcularMontoCCAF(opcProc.getCalcCcaf() ? 1 : 0);
				actForm.setCalcularMontoINP(opcProc.getCalInp() ? 1 : 0);
				actForm.setCalcularMontoMutual(opcProc.getCalMutual() ? 1 : 0);
				actForm.setCalcularMontoTotalPrev(opcProc.getCalcTotPrevision() ? 1 : 0);
				actForm.setCalcularMontoTotalSalud(opcProc.getCalcTotSalud() ? 1 : 0);
				actForm.setGenCCAFPorSucursal(opcProc.getCcafSucursal() ? 1 : 0);
				actForm.setGenINPPorSucursal(opcProc.getInpSucursal() ? 1 : 0);
				actForm.setGenMutualPorSucursal(opcProc.getMutualSucursal() ? 1 : 0);
				actForm.setImprimirPlanillas(opcProc.getImprimirPlantillas() ? 1 : 0);
				actForm.setCalcularFonasa(opcProc.isCalFonasa() ? 1 : 0);

				//csanchez. Se obtiene el tipo de separación de datos de la nómima.
				//Se usa Remuneración, ya que para los demás tipos es el mismo separador y caracter.
				ConceptoMgr conceptoMgr = new ConceptoMgr(session);
				List listaMapeos = conceptoMgr.getListaMapeosConcepto(grupo.getIdMapaNom('R'), "R");

				//Se consulta sólo al primer elemento de la lista de MapeosConcepto por el tipo de separador, ya que es el mismo para toda la lista.
				request.setAttribute("tipoSeparador",     String.valueOf(((MapeoConceptoVO) listaMapeos.get(0)).getTipoSeparador()));
				request.setAttribute("caracterSeparador", ((MapeoConceptoVO) listaMapeos.get(0)).getCaracterSeparador());

				//TODO. Mejorar esto
				actForm.setListaTipoSeparador(String.valueOf(((MapeoConceptoVO) listaMapeos.get(0)).getTipoSeparador()));
				actForm.setCaracterSeparador(((MapeoConceptoVO) listaMapeos.get(0)).getCaracterSeparador());
			}

			//Datos adicionales ficha
			if (tipoOperacion == FICHA) 
			{
				actForm.setFechaCreacion(grupo.getCreado());

				actForm.setIdMapaCod(grupo.getIdMapaCod());
				actForm.setNombreMapeoCodigo(mapeosMgr.getMapaCodigo(grupo.getIdMapaCod()).getDescripcion().trim());

				String mapeoNoDef = "Mapeo indefinido";
				MapaNominaVO mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRem());
				actForm.setDescripcionR(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
				mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomGra());
				actForm.setDescripcionG(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
				mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRel());
				actForm.setDescripcionA(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
				mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomDep());
				actForm.setDescripcionD(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
				
			} else
				actForm.setPuedeDeshabilitar(convenioMgr.getNumEmpsHabilitadas(grupo.getIdGrupoConvenio()) > 0 ? 0 : 1);

			tx.commit();

			if (tipoOperacion == EDITAR) 
			{
				if (bGuardar) 
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Grupo de Convenio", Integer.toString(idGrupoConvenio)));
					this.saveMessages(request.getSession(), am);
				}

				request.setAttribute("cambioParam", "Edición Grupo de Convenios");
				return mapping.findForward("exitoEditar");
			} else if (tipoOperacion == CREAR) 
			{
				if (bGuardar) {
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Grupo de Convenios", Integer.toString(idGrupoConvenio)));
					this.saveMessages(request.getSession(), am);

					ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "grupoLista");

					return redirect;
				}
				request.setAttribute("cambioParam", "Creación Grupo de Convenios");
				return mapping.findForward("exitoEditar");
			} else if (tipoOperacion == FICHA)
				return mapping.findForward("exitoMostrar");
			else
				return null;
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en DetalleGrupoConvenioAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		} 
	}
}
