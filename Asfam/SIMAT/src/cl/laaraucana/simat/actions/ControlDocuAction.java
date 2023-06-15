package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ControlDocuVO;
import cl.laaraucana.simat.forms.ControlDocuForm;
import cl.laaraucana.simat.mannagerDB2.ControlDocuMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class ControlDocuAction extends AbstractAction {

	/*
	 * clase para realizar operaciones sobre mantenedor de tabla SMF05, controlDocu.
	 */

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			ControlDocuMannager mannager = new ControlDocuMannager();
			ArrayList listaControlDocu = new ArrayList();
			String msgPaginacion = "";
			String direccion = request.getParameter("keyAvance");
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			int keyInicio = 0;
			int keyFin = 0;

			keyInicio = Integer.parseInt(request.getParameter("keyInicio"));
			keyFin = Integer.parseInt(request.getParameter("keyFin"));

			ControlDocuVO controlDocu = new ControlDocuVO();
			String keyEstadoDoc = null;
			keyEstadoDoc = request.getParameter("keyEstadoDoc");
			controlDocu.setEstado_documento(Integer.parseInt(keyEstadoDoc));
			controlDocu.setKeyFin(keyFin);
			controlDocu.setKeyInicio(keyInicio);

			if (direccion.equals("a")) {
				//avanzamos
				//	paginacion sin restriccion
				if (keyEstadoDoc.equals("0")) {
					listaControlDocu = mannager.BuscarListaAvanzar(keyFin);

					if (listaControlDocu == null || listaControlDocu.size() == 0) {
						msgPaginacion = "no existen registros posteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyFin));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin + cantidad));
					}
				} else {
					//					paginacion por estado documento
					listaControlDocu = mannager.BuscarListaAvanzarEstadoDoc_SMF05(controlDocu);
					if (listaControlDocu == null || listaControlDocu.size() == 0) {
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
					listaControlDocu = mannager.BuscarListaRetroceder(keyInicio);
					if (listaControlDocu == null || listaControlDocu.size() == 0) {
						msgPaginacion = "no existen registros anteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(0));
						request.setAttribute("keyFinCopy", String.valueOf(cantidad));
						listaControlDocu = mannager.BuscarListaRetroceder(0);
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
					listaControlDocu = mannager.BuscarListaRetrocederEstadoDoc_SMF05(controlDocu);
					if (listaControlDocu == null || listaControlDocu.size() == 0) {
						msgPaginacion = "no existen registros anteriores";
						request.setAttribute("msgPaginacion", msgPaginacion);
						request.setAttribute("keyInicioCopy", String.valueOf(0));
						request.setAttribute("keyFinCopy", String.valueOf(cantidad));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
						listaControlDocu = mannager.BuscarListaRetrocederEstadoDoc_SMF05(controlDocu);
					} else {
						request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
						request.setAttribute("keyFinCopy", String.valueOf(keyFin));
						request.setAttribute("keyEstadoDoc", keyEstadoDoc);
					}
				}

			}

			request.setAttribute("listaControlDocu", listaControlDocu);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT5");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println("Antes try");

		try {
			ArrayList listaControlDocu = new ArrayList();
			ControlDocuForm controlDocuForm = (ControlDocuForm) form;

			String mes_informacion = controlDocuForm.getMes_informacion();
			System.out.println("MES_INFORMACIÓN = " + mes_informacion);

			ControlDocuMannager mannager = new ControlDocuMannager();
			listaControlDocu = (ArrayList) mannager.BuscarByMesInf(mes_informacion);

			System.out.println("Lista devolución " + listaControlDocu.size());

			request.setAttribute("listaControlDocu", listaControlDocu);
			forward = mapping.findForward("sucessBuscarControlDocuByMes");
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
			ArrayList listaControlDocu = new ArrayList();
			ControlDocuMannager mannager = new ControlDocuMannager();
			ControlDocuVO control = new ControlDocuVO();
			String estadoDoc = (String) request.getParameter("estadoDoc");
			String keyEstadoDoc = estadoDoc;
			request.setAttribute("keyEstadoDoc", keyEstadoDoc);
			try {
				control.setEstado_documento(Integer.parseInt(estadoDoc));
				listaControlDocu = (ArrayList) mannager.getControlDocuByEstadoDoc(control);
			} catch (Exception e) {
				System.out.println("CATCH: " + e);
			}
			request.setAttribute("listaControlDocu", listaControlDocu);
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
			ArrayList listaControlDocu = new ArrayList();
			ControlDocuMannager mannager = new ControlDocuMannager();
			ControlDocuVO control = new ControlDocuVO();
			String numDoc = (String) request.getParameter("numDoc");
			control.setNum_documento(numDoc);
			listaControlDocu = (ArrayList) mannager.getControlDocuByNumDoc(control);
			request.setAttribute("listaControlDocu", listaControlDocu);
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
		System.out.println("llego antye try");
		try {
			ManejoEspacios ue = new ManejoEspacios();
			System.out.println("llego action");
			ControlDocuMannager mannager = new ControlDocuMannager();
			ControlDocuVO control = new ControlDocuVO();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ControlDocuForm controlForm = (ControlDocuForm) form;
			System.out.println("inicio get form: 1");
			control.setIdControlDocu(0);
			control.setMes_informacion(controlForm.getMes_informacion());
			control.setCodigo_entidad(controlForm.getCodigo_entidad());
			control.setTipo_subsidio(controlForm.getTipo_subsidio());
			control.setRut_empleador(controlForm.getRut_empleador());
			control.setNombre_empleador(controlForm.getNombre_empleador());
			control.setRun_beneficiario(controlForm.getRun_beneficiario());
			control.setNombre_beneficiario(controlForm.getNombre_beneficiario());
			control.setMod_pago(controlForm.getMod_pago());
			control.setSerie_documento(controlForm.getSerie_documento());
			control.setNum_documento(controlForm.getNum_documento());
			System.out.println("inicio get form: 2");
			//control.setFecha_emision_documento(controlForm.getFecha_emision_documento());
			try {
				control.setFecha_emision_documento(hfa.getFechaISO_sql(controlForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				control.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}
			System.out.println("inicio get form: 3");
			control.setMonto_documento(controlForm.getMonto_documento());

			if (controlForm.getCodigo_banco().trim().equalsIgnoreCase("")) {
				control.setCodigo_banco("");
			} else {
				control.setCodigo_banco(ue.getCompletaCeros(controlForm.getCodigo_banco(), 3));
			}
			control.setEstado_documento(controlForm.getEstado_documento());
			System.out.println("inicio get form: 4");
			//control.setFecha_cambio_estado(controlForm.getFecha_cambio_estado());
			try {
				control.setFecha_cambio_estado(hfa.getFechaISO_sql(controlForm.getFecha_cambio_estado()));
			} catch (Exception ex) {
				control.setFecha_cambio_estado(hfa.getFechaISO_sql("0001-01-01"));
			}
			System.out.println("inicio get form: 5");
			mannager.Insertar(control);
			System.out.println("inicio get form: 6");

			ArrayList listaControlDocu = new ArrayList();
			int keyFin = 0;
			listaControlDocu = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaControlDocu", listaControlDocu);
			forward = mapping.findForward("successInsertarControlDocu");
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
		System.out.println("llego action,");
		try {
			ManejoEspacios ue = new ManejoEspacios();
			ControlDocuVO control = new ControlDocuVO();
			ControlDocuMannager mannager = new ControlDocuMannager();
			ArrayList listaControlDocu = new ArrayList();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ControlDocuForm controlForm = (ControlDocuForm) form;

			control.setIdControlDocu(controlForm.getId());
			control.setMes_informacion(controlForm.getMes_informacion());
			control.setCodigo_entidad(controlForm.getCodigo_entidad());
			control.setTipo_subsidio(controlForm.getTipo_subsidio());
			control.setRut_empleador(controlForm.getRut_empleador());
			control.setNombre_empleador(controlForm.getNombre_empleador());
			control.setRun_beneficiario(controlForm.getRun_beneficiario());
			control.setNombre_beneficiario(controlForm.getNombre_beneficiario());
			control.setMod_pago(controlForm.getMod_pago());
			control.setSerie_documento(controlForm.getSerie_documento());
			control.setNum_documento(controlForm.getNum_documento());
			//control.setFecha_emision_documento(controlForm.getFecha_emision_documento());
			try {
				control.setFecha_emision_documento(hfa.getFechaISO_sql(controlForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				control.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}
			control.setMonto_documento(controlForm.getMonto_documento());

			if (controlForm.getCodigo_banco().trim().equalsIgnoreCase("")) {
				control.setCodigo_banco("");
			} else {
				control.setCodigo_banco(ue.getCompletaCeros(controlForm.getCodigo_banco(), 3));
			}
			control.setEstado_documento(controlForm.getEstado_documento());

			//control.setFecha_cambio_estado(controlForm.getFecha_cambio_estado());
			try {
				control.setFecha_cambio_estado(hfa.getFechaISO_sql(controlForm.getFecha_cambio_estado()));
			} catch (Exception ex) {
				control.setFecha_cambio_estado(hfa.getFechaISO_sql("0001-01-01"));
			}

			mannager.Actualizar(control);
			int keyFin = 0;
			listaControlDocu = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaControlDocu", listaControlDocu);

			forward = mapping.findForward("successActualizarControlDocu");
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

			ControlDocuMannager mannager = new ControlDocuMannager();
			ArrayList listaControlDocu = new ArrayList();
			ControlDocuVO controlDocu = new ControlDocuVO();
			ControlDocuForm controlForm = (ControlDocuForm) form;

			controlDocu.setIdControlDocu(controlForm.getId());

			System.out.println("id borrar: " + controlForm.getId());
			mannager.Eliminar(controlDocu);

			int keyFin = 0;
			listaControlDocu = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaControlDocu", listaControlDocu);
			forward = mapping.findForward("successEliminarControlDocu");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);

	}

}
