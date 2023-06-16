package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.forms.DocsRevalReemForm;
import cl.laaraucana.simat.mannagerDB2.DocsRevalReemMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class DocsRevalReemAction extends AbstractAction

{
	/*
	 * 
	 */

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			ArrayList listaDocsRevalReem = new ArrayList();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();

			String msgPaginacion = "";
			String direccion = request.getParameter("keyAvance");
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			int keyInicio = 0;
			int keyFin = 0;

			keyInicio = Integer.parseInt(request.getParameter("keyInicio"));
			keyFin = Integer.parseInt(request.getParameter("keyFin"));

			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			String keyEstadoDoc = null;
			keyEstadoDoc = request.getParameter("keyEstadoDoc");
			docsRevalReem.setEstado_documento_original(Integer.parseInt(keyEstadoDoc));
			docsRevalReem.setKeyFin(keyFin);
			docsRevalReem.setKeyInicio(keyInicio);

			if (direccion.equals("a")) {
				//avanzamos
				//paginacion sin restriccion
				if (keyEstadoDoc.equals("0")) {
					listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);
					if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
						msgPaginacion = "no existen registros posteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyFin));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin + cantidad));
					}
				} else {
					//paginacion por estado documento
					listaDocsRevalReem = mannager.BuscarListaAvanzarEstadoDoc(docsRevalReem);
					if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
						msgPaginacion = "no existen registros posteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyFin));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin + cantidad));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
					}
				}
			} else if (direccion.equals("r")) {
				//retrocedemos
				//paginacion sin restricion
				if (keyEstadoDoc.equals("0")) {
					if (keyInicio > 0) {
						keyFin = keyInicio;
						keyInicio = (keyInicio - cantidad);
					} else {
						keyFin = 0;
						keyInicio = 0;
					}
					listaDocsRevalReem = mannager.BuscarListaRetroceder(keyInicio);
					if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
						msgPaginacion = "no existen registros anteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(0));
						request.setAttribute("keyFinCopy", String.valueOf(cantidad));
						listaDocsRevalReem = mannager.BuscarListaRetroceder(0);
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
					}
				} else {
					//Paginacion por estado documento
					if (keyInicio > 0) {
						keyFin = keyInicio;
						keyInicio = (keyInicio - cantidad);
					} else {
						keyFin = 0;
						keyInicio = 0;
					}

					listaDocsRevalReem = mannager.BuscarListaRetrocederEstadoDoc(docsRevalReem);

					if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
						msgPaginacion = "no existen registros anteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(0));
						request.setAttribute("keyFinCopy", String.valueOf(cantidad));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
						listaDocsRevalReem = mannager.BuscarListaRetroceder(0);
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
					}
				}

			}//end if, paginacion			
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT6");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}//end paginacion

	public ActionForward mostrarPaginacionBusqueda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			ArrayList listaDocsRevalReem = new ArrayList();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			String msgPaginacion = "";
			String direccion = request.getParameter("keyAvance");
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			int keyInicio = 0;
			int keyFin = 0;

			keyInicio = Integer.parseInt(request.getParameter("keyInicio"));
			keyFin = Integer.parseInt(request.getParameter("keyFin"));

			if (direccion.equals("a")) {
				//avanzamos
				listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);

				if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
					msgPaginacion = "no existen registros posteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyFin));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin + cantidad));
				}

			} else if (direccion.equals("r")) {

				//retrocedemos
				if (keyInicio > 0) {
					keyFin = keyInicio;
					keyInicio = (keyInicio - cantidad);
				} else {
					keyFin = 0;
					keyInicio = 0;
				}
				listaDocsRevalReem = mannager.BuscarListaRetroceder(keyInicio);
				if (listaDocsRevalReem == null || listaDocsRevalReem.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaDocsRevalReem = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}
			}
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT6");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}//end paginacion

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ArrayList listaDocsRevalReem = new ArrayList();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;

			String mes_informacion = docsRevalReemForm.getMes_informacion();

			listaDocsRevalReem = (ArrayList) mannager.BuscarByMesInf(mes_informacion);

			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			forward = mapping.findForward("sucessBuscarDocsRevalReemByMes");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// Finish with
		return (forward);
	}

	public ActionForward buscarByEstadoDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			ArrayList listaDocsRevalReem = new ArrayList();
			String estadoDoc = (String) request.getParameter("estadoDoc");
			String keyEstadoDoc = estadoDoc;
			request.setAttribute("keyEstadoDoc", keyEstadoDoc);
			try {
				docsRevalReem.setEstado_documento_original(Integer.parseInt(estadoDoc));
				listaDocsRevalReem = (ArrayList) mannager.getDocsRevalReemByEstadoDoc(docsRevalReem);
			} catch (Exception e) {
				System.out.println("CATCH: " + e);
			}
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			forward = mapping.findForward("sucessBuscarByEstadoDoc");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByNumDoc

	public ActionForward buscarByNumDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			ArrayList listaDocsRevalReem = new ArrayList();
			String numDoc = (String) request.getParameter("numDoc");
			docsRevalReem.setNum_documento_original(numDoc);
			listaDocsRevalReem = (ArrayList) mannager.getDocsRevalReemByNumDoc(docsRevalReem);
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			forward = mapping.findForward("sucessBuscarByNumDoc");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByNumDoc

	/*
	 * 
	 */
	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ManejoEspacios ue = new ManejoEspacios();
			DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;
			DocsRevalReemVO docs = new DocsRevalReemVO();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ArrayList listaDocsRevalReem = new ArrayList();

			docs.setMes_informacion(docsRevalReemForm.getMes_informacion());
			docs.setCodigo_entidad(docsRevalReemForm.getCodigo_entidad());
			docs.setTiposubsidio(docsRevalReemForm.getTiposubsidio());
			docs.setMod_pago_original(docsRevalReemForm.getMod_pago_original());

			//docs.setCodigo_banco_original(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_original(),3));
			if (docsRevalReemForm.getCodigo_banco_original().trim().equalsIgnoreCase("")) {
				docs.setCodigo_banco_original("");
			} else {
				docs.setCodigo_banco_original(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_original(), 3));
			}
			docs.setSerie_documento_original(docsRevalReemForm.getSerie_documento_original());
			docs.setNum_documento_original(docsRevalReemForm.getNum_documento_original());

			//docs.setFecha_emision_documento_original(docsRevalReemForm.getFecha_emision_documento_original());
			try {
				docs.setFecha_emision_documento_original(hfa.getFechaISO_sql(docsRevalReemForm.getFecha_emision_documento_original()));
			} catch (Exception ex) {
				docs.setFecha_emision_documento_original(hfa.getFechaISO_sql("0001-01-01"));
			}

			docs.setMonto_documento_original(docsRevalReemForm.getMonto_documento_original());
			docs.setEstado_documento_original(docsRevalReemForm.getEstado_documento_original());
			docs.setMod_pago_nuevo(docsRevalReemForm.getModo_pago_nuevo());

			//docs.setCodigo_banco_nuevo(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_nuevo(),3));
			if (docsRevalReemForm.getCodigo_banco_nuevo().trim().equalsIgnoreCase("")) {
				docs.setCodigo_banco_nuevo("");
			} else {
				docs.setCodigo_banco_nuevo(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_nuevo(), 3));
			}
			docs.setSerie_documento_nuevo(docsRevalReemForm.getSerie_documento_nuevo());
			docs.setNum_documento_nuevo(docsRevalReemForm.getNum_documento_nuevo());

			//docs.setFecha_emision_documento_nuevo(docsRevalReemForm.getFecha_emision_documento_nuevo());  
			try {
				docs.setFecha_emision_documento_nuevo(hfa.getFechaISO_sql(docsRevalReemForm.getFecha_emision_documento_nuevo()));
			} catch (Exception ex) {
				docs.setFecha_emision_documento_nuevo(hfa.getFechaISO_sql("0001-01-01"));
			}

			docs.setMonto_documento_nuevo(docsRevalReemForm.getMonto_documento_nuevo());

			//Mapeo de nuevo campo 'Indicador de convenio'
			docs.setIndicadorConvenio(1);
			
			mannager.Insertar(docs);

			int keyFin = 0;
			listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);

			forward = mapping.findForward("successInsertarDocsRevalReem");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);
	}

	/*
	 * 
	 */
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;
			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();

			docsRevalReem.setId(docsRevalReemForm.getId());
			mannager.Eliminar(docsRevalReem);

			ArrayList listaDocsRevalReem = new ArrayList();
			int keyFin = 0;
			listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
			forward = mapping.findForward("successEliminarDocsRevalReem");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);
	}

	/*
	 * 
	 */
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ManejoEspacios ue = new ManejoEspacios();
			System.out.println("action START TRY");
			DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;
			System.out.println("OBS:formUPACTION, " + docsRevalReemForm.getMes_informacion());
			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			DocsRevalReemMannager mannager = new DocsRevalReemMannager();
			ArrayList listaDocsRevalReem = new ArrayList();

			docsRevalReem.setId(docsRevalReemForm.getId());
			docsRevalReem.setMes_informacion(docsRevalReemForm.getMes_informacion());
			docsRevalReem.setCodigo_entidad(docsRevalReemForm.getCodigo_entidad());
			docsRevalReem.setTiposubsidio(docsRevalReemForm.getTiposubsidio());

			docsRevalReem.setMod_pago_original(docsRevalReemForm.getMod_pago_original());

			//    			docs.setCodigo_banco_original(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_original(),3));
			if (docsRevalReemForm.getCodigo_banco_original().trim().equalsIgnoreCase("")) {
				docsRevalReem.setCodigo_banco_original("");
			} else {
				docsRevalReem.setCodigo_banco_original(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_original(), 3));
			}
			docsRevalReem.setSerie_documento_original(docsRevalReemForm.getSerie_documento_original());
			docsRevalReem.setNum_documento_original(docsRevalReemForm.getNum_documento_original());

			//docs.setFecha_emision_documento_original(docsRevalReemForm.getFecha_emision_documento_original());
			try {
				docsRevalReem.setFecha_emision_documento_original(hfa.getFechaISO_sql(docsRevalReemForm.getFecha_emision_documento_original()));
			} catch (Exception ex) {
				docsRevalReem.setFecha_emision_documento_original(hfa.getFechaISO_sql("0001-01-01"));
			}

			docsRevalReem.setMonto_documento_original(docsRevalReemForm.getMonto_documento_original());
			docsRevalReem.setEstado_documento_original(docsRevalReemForm.getEstado_documento_original());
			docsRevalReem.setMod_pago_nuevo(docsRevalReemForm.getModo_pago_nuevo());

			//docs.setCodigo_banco_nuevo(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_nuevo(),3));
			if (docsRevalReemForm.getCodigo_banco_nuevo().trim().equalsIgnoreCase("")) {
				docsRevalReem.setCodigo_banco_nuevo("");
			} else {
				docsRevalReem.setCodigo_banco_nuevo(ue.getCompletaCeros(docsRevalReemForm.getCodigo_banco_nuevo(), 3));
			}
			docsRevalReem.setSerie_documento_nuevo(docsRevalReemForm.getSerie_documento_nuevo());
			docsRevalReem.setNum_documento_nuevo(docsRevalReemForm.getNum_documento_nuevo());

			//docs.setFecha_emision_documento_nuevo(docsRevalReemForm.getFecha_emision_documento_nuevo());  
			try {
				docsRevalReem.setFecha_emision_documento_nuevo(hfa.getFechaISO_sql(docsRevalReemForm.getFecha_emision_documento_nuevo()));
			} catch (Exception ex) {
				docsRevalReem.setFecha_emision_documento_nuevo(hfa.getFechaISO_sql("0001-01-01"));
			}

			docsRevalReem.setMonto_documento_nuevo(docsRevalReemForm.getMonto_documento_nuevo());

			mannager.actualizar(docsRevalReem);

			int keyFin = 0;
			listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);

			forward = mapping.findForward("successActualizarDocsRevalReem");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);
	}

}
