package cl.araucana.cp.presentation.struts.actions.comprobante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.ConveniosActionForm;
import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO;
import cl.araucana.cp.seguridad.PermisoEmpresaConvenio;

import com.bh.talon.User;
/*
* @(#) EditarComprobanteAction.java 1.14 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.14
 */
public class EditarComprobanteAction extends AppAction 
{
	static Logger logger = Logger.getLogger(EditarComprobanteAction.class);

	public EditarComprobanteAction()
	{
		super();
		this.btns.add("filtro");
		this.btns.add("guardar");
		this.btns.add("pagar");
		this.btns.add("cancelar");	
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>inicio</dd>
	 * <dt>subAccion</dt>
	 * <dd>procesos</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>comprobanteEditar</dd>
	 * <dt>codigoBarra</dt>
	 * <dd>El codigo de barras del comprobante que se quiere editar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "Guardar", se guarda el contenido del formulario en la BD.
	 * Si es "Pagar", se redirecciona al pago del comprobante.
	 * Si es "Cancelar", se redireccona a la lista de nominas.
	 * Si es "Busqueda", se actualiza la consulta con el valor de los combos de filtro.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	public ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ConveniosActionForm actForm = (ConveniosActionForm) form;
		logger.info("operacion en edicion de comprobante:" + actForm.getOperacion() + "::");
		String tipoNomina = "";
		ActionMessages am = new ActionMessages(); 
		ActionErrors ae = new ActionErrors(); 

		int convenio = 0;
		int rut = 0;
		long codigoBarra = 0;
		ComprobanteVO cmp = null;

		Session session = null;
		Transaction tx = null;
		try 
		{
			session = HibernateUtil.getSession();

			//Instancia los managers correspondientes
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);

			UsuarioCP usuarioCP = (UsuarioCP) user; 

			if (actForm.getOperacion().equals("")) 
			{
				//Se llamo desde afuera con URL, desplegar
				logger.info("EditarComprobanteAction:\"\"");
				codigoBarra = Long.valueOf(request.getParameter("codigoBarra")).longValue();

				NominaVO nomina = comprobanteMgr.getNomina(codigoBarra);
				if (nomina != null)
				{
					rut = nomina.getRutEmpresa();
					convenio = nomina.getIdConvenio();
					tipoNomina = comprobanteMgr.tipoProcesoNomina(nomina);
				}
			} else 
			{
				codigoBarra = actForm.getCodigoBarra(0l);
				rut = actForm.getRut();
				convenio = actForm.getConvenio();
				tipoNomina = actForm.getTipoNomina();
				
				if (actForm.getOperacion().equals(Constants.TXT_BTNS.getProperty("guardar"))) 
				{
					logger.info("EditarComprobanteAction:Guardar");
					try
					{
						tx = session.beginTransaction();
						codigoBarra = comprobanteMgr.guardaListaEdicionComprobantes(codigoBarra, actForm.getConsulta());
						tx.commit();
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Comprobante", "" + codigoBarra));
						this.saveMessages(request, am);						
						cmp = comprobanteMgr.armaComprobante(codigoBarra);

						request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=comprobanteEditar&codigoBarra=" + codigoBarra);
					} catch (Exception e)
					{
						if (e.getMessage() != null && e.getMessage().equals("error.anulaFolio"))
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
						else
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cmpNoPudoGuardar", "" + codigoBarra));
						this.saveErrors(request, ae);
					}
				} else if (actForm.getOperacion().equals(Constants.TXT_BTNS.getProperty("pagar"))) 
				{
					logger.info("EditarComprobanteAction:Pagar");
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("pagar"));
				    redirect.addParameter("accion", this.accion);
				    redirect.addParameter("subAccion", this.subAccion);
				    redirect.addParameter("subSubAccion", "pagarNomina");
				    redirect.addParameter("codigoBarra", "" + codigoBarra);	
				    request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=comprobanteEditar&codigoBarra=" + codigoBarra);
					
			        return redirect;
				} else if (actForm.getOperacion().equals(Constants.TXT_BTNS.getProperty("cancelar"))) 
				{
					logger.info("EditarComprobanteAction:Cancelar");
				
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("cancel"));
					redirect.addParameter("accion", "inicio");
					redirect.addParameter("subAccion", "procesos");
					//redirect.addParameter("limpiaPath", "");
				
					return redirect;
				} else if (Utils.codificaAcentos(actForm.getOperacion()).equals(Constants.TXT_BTNS.getProperty("filtro")) 
						|| actForm.getOperacion().equals(Constants.TXT_BTNS.getProperty("filtro"))) 
				{
					logger.info("\n\n\nEditarComprobanteAction:Filtrar");
				
					rut = actForm.getRut();
					convenio = actForm.getConvenio();
					tipoNomina = actForm.getTipoNomina();
					cmp = comprobanteMgr.getCmpPorPagar(convenio, tipoNomina, "" + rut);
					if (cmp != null)
						codigoBarra = cmp.getIdCodigoBarra();
					else
						codigoBarra = -1;
				}
			}
			actForm.setCodigoBarra(Long.toString(codigoBarra));

			//Llena el combo de tipos de nominas
			List tiposNomina = new ArrayList();
			TipoNominaVO tnvoTipoNomina;
			for (Iterator it = procesoMgr.getTiposProceso().iterator(); it.hasNext();) 
			{
				tnvoTipoNomina = (TipoNominaVO) it.next();
				tiposNomina.add(new LabelValueBean(tnvoTipoNomina.getDescripcion(), tnvoTipoNomina.getIdTipoNomina()));
			}
			actForm.setTiposNomina(tiposNomina);

			//Setea el permiso de guardado y pagado
			if (usuarioCP.getEmpresasAdmin().contains(new Integer(rut)))//Si el usuario administra la empresa del convenio del comprobante				
				actForm.setPuedeGuardarOPagar(true);
			else if (usuarioCP.getConveniosEditor().contains(new PermisoEmpresaConvenio(rut, convenio)))//Si el usuario tiene permisos de escritura sobre el convenio				
				actForm.setPuedeGuardarOPagar(true);
			else //El usuario tiene permiso de lectura y no puede guardar ni pagar				
				actForm.setPuedeGuardarOPagar(false);

			//Construye toda la consulta
			if (cmp == null)
				cmp = comprobanteMgr.getComprobante(new Long(codigoBarra));
			List consulta = comprobanteMgr.construyeListaEdicionComprobantes(cmp);
			if (consulta == null)
			{
				logger.info("\n\n\nretornando sin datos a edicion de comprobante: cb:" + codigoBarra + "::");
				actForm.setConsulta(null);

				HashMap combos = empresaMgr.getCombosPermisos(true, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rut, convenio);
				if (combos == null)
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
					this.saveMessages(request, am);
					return mapping.findForward("error");
				}
				actForm.setEmpresas((List)combos.get("empresas"));
				actForm.setObjEmpresas((List)combos.get("objEmpresas"));
				EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
				actForm.setNombreEmpresa(empresa.getRazonSocial());
				actForm.setRutFmt(Utils.formatRut(empresa.getIdEmpresa()));

				actForm.setConvenio(convenio);
				actForm.setTipoNomina(tipoNomina);
				actForm.setRut(rut);
				return mapping.findForward("exito");
			}

			HashMap combos = empresaMgr.getCombosPermisos(false, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rut, convenio);
			if (combos == null)
			{
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
				this.saveMessages(request, am);
				return mapping.findForward("error");
			}
			actForm.setEmpresas((List)combos.get("empresas"));
			actForm.setObjEmpresas((List)combos.get("objEmpresas"));
			EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
			actForm.setNombreEmpresa(empresa.getRazonSocial());
			actForm.setRutFmt(Utils.formatRut(empresa.getIdEmpresa()));

			actForm.setConvenio(convenio);
			actForm.setTipoNomina(tipoNomina);
			actForm.setRut(rut);
			//Totaliza y formatea lo entregado
			LineaComprobanteProvisorioVO lineaConsulta, lineaDetalle;
			Set seccionesSinNada = new HashSet();
			int tipoPagoDetDetalle = 0;
			long totalDet=0, totalSec = 0, totalDNP = 0, totalNP = 0;
			for (Iterator it = consulta.iterator(); it.hasNext();) 				//RECORRO SECCIONES afp, isapre, caja, etc
			{
				lineaConsulta = (LineaComprobanteProvisorioVO) it.next();
				lineaConsulta.setGlosa(lineaConsulta.getGlosa().trim());

				totalSec = comprobanteMgr.calcularTotalSeccion(lineaConsulta.getIdTipoSeccion(), cmp.getSecciones());
				for (Iterator itp = lineaConsulta.getDetalle().iterator(); itp.hasNext();) //RECORRO DETALLE SECCION habitat, cuprum, los heroes, etc
				{
					lineaDetalle = (LineaComprobanteProvisorioVO) itp.next();
					lineaDetalle.setGlosa(lineaDetalle.getGlosa().trim());

					totalDet = comprobanteMgr.calcularTotalDetalleSeccion(lineaConsulta.getIdTipoSeccion(), lineaDetalle.getIdTipoDetalle(), cmp.getSecciones());
					
					lineaDetalle.setTotal(totalDet);

					//Si la seccion es no es pagable individual, los detalles heredan su tipo de pago
					if (!lineaConsulta.getPagableIndividual().booleanValue())
						tipoPagoDetDetalle = lineaConsulta.getTipoPago();
					else
						tipoPagoDetDetalle = lineaDetalle.getTipoPago();

					if (tipoPagoDetDetalle == Constants.EST_SECCION_DNP)
						totalDNP += totalDet;
					else if (tipoPagoDetDetalle == Constants.EST_SECCION_NO_PAGO)
						totalNP += totalDet;
				}
				lineaConsulta.setTotal(totalSec);
				if (totalSec == 0 && lineaConsulta.getIdTipoSeccion() != Constants.ID_TIPO_SECCION_CAJA && lineaConsulta.getIdTipoSeccion() != Constants.ID_TIPO_SECCION_INP)
				{
					logger.info("sacando seccion total cero:" + lineaConsulta.getIdTipoSeccion() + "::");
					seccionesSinNada.add(lineaConsulta);
				}
			}
			actForm.setTotalP(cmp.getTotal());
			actForm.setTotalDNP(totalDNP);
			actForm.setTotalNP(totalNP);

			//Borra las lineas de seccion con total 0
			consulta.removeAll(seccionesSinNada);
			actForm.setConsulta(consulta);
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{			
			logger.error("Se produjo una excepcion en EditarComprobanteAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		} 
	}
	
	
}

