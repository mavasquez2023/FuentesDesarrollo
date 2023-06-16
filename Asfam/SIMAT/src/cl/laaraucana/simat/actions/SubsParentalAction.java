package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.SubsParentalVO;
import cl.laaraucana.simat.forms.SubsParentalForm;
import cl.laaraucana.simat.mannagerDB2.SubsParentalMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 * 
 * clase de tipo action que incorpora los metodos necesarios para buscar, insertar, modificar
 * y actualizar de la tabla SubsParental
 * 
 */
public class SubsParentalAction extends AbstractAction

{

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {
			ArrayList listaSubsParental = new ArrayList();
			SubsParentalMannager subsParentalMannager = new SubsParentalMannager();
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
				listaSubsParental = subsParentalMannager.BuscarListaAvanzar(keyFin);

				if (listaSubsParental == null || listaSubsParental.size() == 0) {
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
				listaSubsParental = subsParentalMannager.BuscarListaRetroceder(keyInicio);
				if (listaSubsParental == null || listaSubsParental.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaSubsParental = subsParentalMannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}

			}

			request.setAttribute("listaSubsParental", listaSubsParental);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT3");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		try {
			SubsParentalMannager subsParentalMannager = new SubsParentalMannager();
			SubsParentalForm subsParentalForm = (SubsParentalForm) form;

			ArrayList listaSubsParental = subsParentalMannager.BuscarByMes(subsParentalForm.getMes_informacion());

			request.setAttribute("listaSubsParental", listaSubsParental);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarSubsParentalByMes");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward buscarByNumDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaSubsParental = new ArrayList();
			SubsParentalMannager mannager = new SubsParentalMannager();
			SubsParentalVO subsParental = new SubsParentalVO();
			String numDoc = (String) request.getParameter("numDoc");
			subsParental.setNum_documento(numDoc);
			listaSubsParental = (ArrayList) mannager.getSubsParentalByNumDoc(subsParental);
			request.setAttribute("listaSubsParental", listaSubsParental);
			forward = mapping.findForward("sucessBuscarByNumDoc");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByNumDoc

	public ActionForward buscarByRutBenef(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaSubsParental = new ArrayList();
			SubsParentalMannager mannager = new SubsParentalMannager();
			SubsParentalVO subsParental = new SubsParentalVO();
			String rutBenef = (String) request.getParameter("rutBenef");
			subsParental.setRun_beneficiario_parental(rutBenef);
			listaSubsParental = (ArrayList) mannager.getSubsParentalByRutBenef(subsParental);
			request.setAttribute("listaSubsParental", listaSubsParental);
			forward = mapping.findForward("sucessBuscarByRutBenef");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByRutBenef

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		try {
			ManejoEspacios ue = new ManejoEspacios();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			SubsParentalForm subsParentalForm = (SubsParentalForm) form;
			SubsParentalVO subsParental = new SubsParentalVO();
			ArrayList listaSubsParental = new ArrayList();
			System.out.println(" ini try insert");

			subsParental.setIdParental(0);

			subsParental.setActividad_laboral_trabajador(ue.getCompletaCeros(subsParentalForm.getActividad_laboral_trabajador(), 2));

			subsParental.setAgencia_entidad(subsParentalForm.getAgencia_entidad());
			subsParental.setCalidad_trabajador(subsParentalForm.getCalidad_trabajador());
			subsParental.setCausal_emision(subsParentalForm.getCausal_emision());

			subsParental.setCod_comuna_beneficiario(ue.getCompletaCeros(subsParentalForm.getCod_comuna_beneficiario(), 5));
			subsParental.setCodigo_banco(ue.getCompletaCeros(subsParentalForm.getCodigo_banco(), 3));

			subsParental.setCodigo_entidad(subsParentalForm.getCodigo_entidad());
			subsParental.setCta_cte(subsParentalForm.getCta_cte());
			subsParental.setEdad(subsParentalForm.getEdad());
			subsParental.setEntidad_previsional(subsParentalForm.getEntidad_previsional());

			System.out.println(" * * * * * * * * fechas");
			//subsParental.setFecha_emision_documento(subsParentalForm.getFecha_emision_documento());
			//System.out.println(" * * * * * * * * FED......: "+subsParentalForm.getFecha_emision_documento());
			//System.out.println(" * * * * * * * * FED parse: "+hfa.getFechaISO_sql(subsParentalForm.getFecha_emision_documento()));
			try {
				subsParental.setFecha_emision_documento(hfa.getFechaISO_sql(subsParentalForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				subsParental.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}
			try {
				subsParental.setFecha_inicio_postnatal_parental(hfa.getFechaISO_sql(subsParentalForm.getFecha_inicio_postnatal_parental()));
			} catch (Exception ex) {
				subsParental.setFecha_inicio_postnatal_parental(hfa.getFechaISO_sql("0001-01-01"));
			}
			try {
				subsParental.setFecha_termino_postnatal_parental(hfa.getFechaISO_sql(subsParentalForm.getFecha_termino_postnatal_parental()));
			} catch (Exception ex) {
				subsParental.setFecha_termino_postnatal_parental(hfa.getFechaISO_sql("0001-01-01"));
			}
			subsParental.setMes_informacion(subsParentalForm.getMes_informacion());
			subsParental.setMes_nomina(subsParentalForm.getMes_nomina());
			subsParental.setMod_pago(subsParentalForm.getMod_pago());
			subsParental.setMonto_cotiz_fu(subsParentalForm.getMonto_cotiz_fu());
			subsParental.setMonto_cotiz_sc(subsParentalForm.getMonto_cotiz_sc());
			subsParental.setMonto_desahucio(subsParentalForm.getMonto_desahucio());
			subsParental.setMonto_documento(subsParentalForm.getMonto_documento());
			//System.out.println(" * * * * * * * * 20");
			subsParental.setMonto_fp(subsParentalForm.getMonto_fp());
			subsParental.setMonto_remun_imponible(subsParentalForm.getMonto_remun_imponible());
			subsParental.setMonto_salud(subsParentalForm.getMonto_salud());
			subsParental.setMonto_salud_ad(subsParentalForm.getMonto_salud_ad());
			subsParental.setMonto_sub_dfl44_1978(subsParentalForm.getMonto_sub_dfl44_1978());
			subsParental.setMonto_sub_pagado(subsParentalForm.getMonto_sub_pagado());
			subsParental.setNombre_beneficiario_parental(subsParentalForm.getNombre_beneficiario_parental());
			subsParental.setNombre_beneficiario_postnatal(subsParentalForm.getNombre_beneficiario_postnatal());
			subsParental.setNombre_empleador(subsParentalForm.getNombre_empleador());
			subsParental.setNro_licencia(subsParentalForm.getNro_licencia());
			// System.out.println(" * * * * * * * * 30");
			subsParental.setNro_resolucion(subsParentalForm.getNro_resolucion());
			subsParental.setNum_dias_cotiz_pagados(subsParentalForm.getNum_dias_cotiz_pagados());
			subsParental.setNum_dias_permiso_pagado(subsParentalForm.getNum_dias_permiso_pagado());
			subsParental.setNum_documento(subsParentalForm.getNum_documento());
			subsParental.setRun_beneficiario_parental(subsParentalForm.getRun_beneficiario_parental());
			subsParental.setRun_beneficiario_postnatal(subsParentalForm.getRun_beneficiario_postnatal());
			subsParental.setRut_empleador(subsParentalForm.getRut_empleador());
			subsParental.setSerie_documento(subsParentalForm.getSerie_documento());
			subsParental.setSexo(subsParentalForm.getSexo());
			subsParental.setSubsidio_iniciado(subsParentalForm.getSubsidio_iniciado());

			subsParental.setVinculo_beneficiario_menor(subsParentalForm.getVinculo_beneficiario_menor());
			subsParental.setTipo_de_pago(subsParentalForm.getTipo_de_pago());
			subsParental.setTipo_emision(subsParentalForm.getTipo_emision());
			subsParental.setTipo_extension_postnatal_parental(subsParentalForm.getTipo_extension_postnatal_parental());
			subsParental.setTraspaso(subsParentalForm.getTraspaso());

			subsParental.setIndicador_convenio(subsParentalForm.getIndicador_convenio());
			//al insertar nuevo registro siempre en cero
			subsParental.setServ_salud(0);

			SubsParentalMannager subsParentalMannager = new SubsParentalMannager();
			subsParentalMannager.Insertar(subsParental);

			keyFin = 0;
			listaSubsParental = subsParentalMannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsParental", listaSubsParental);

			forward = mapping.findForward("sucessInsertarSubsParental");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward eliminarByID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		*/
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		try {

			SubsParentalVO subsParental = new SubsParentalVO();
			SubsParentalMannager subsParentalMannager = new SubsParentalMannager();

			SubsParentalForm subsParentalForm = (SubsParentalForm) form;
			subsParental.setIdParental(subsParentalForm.getId());
			subsParentalMannager.Eliminar(subsParental);

			ArrayList listaSubsParental = new ArrayList();
			keyFin = 0;
			listaSubsParental = subsParentalMannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsParental", listaSubsParental);
			System.out.println(" forw");
			forward = mapping.findForward("sucessEliminarSubsParental");

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);

	}

	//actualizar
	public ActionForward actualizarByID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		try {
			ManejoEspacios ue = new ManejoEspacios();
			SubsParentalForm subsParentalForm = (SubsParentalForm) form;
			SubsParentalMannager subsParentalMannager = new SubsParentalMannager();
			SubsParentalVO subsParental = new SubsParentalVO();

			subsParental.setIdParental(subsParentalForm.getId());

			subsParental.setActividad_laboral_trabajador(ue.getCompletaCeros(subsParentalForm.getActividad_laboral_trabajador(), 2));

			subsParental.setAgencia_entidad(subsParentalForm.getAgencia_entidad());
			subsParental.setCalidad_trabajador(subsParentalForm.getCalidad_trabajador());
			subsParental.setCausal_emision(subsParentalForm.getCausal_emision());

			subsParental.setCod_comuna_beneficiario(ue.getCompletaCeros(subsParentalForm.getCod_comuna_beneficiario(), 5));
			subsParental.setCodigo_banco(ue.getCompletaCeros(subsParentalForm.getCodigo_banco(), 3));

			subsParental.setCodigo_entidad(subsParentalForm.getCodigo_entidad());
			subsParental.setCta_cte(subsParentalForm.getCta_cte());
			subsParental.setEdad(subsParentalForm.getEdad());
			subsParental.setEntidad_previsional(subsParentalForm.getEntidad_previsional());

			ManejoHoraFecha hfa = new ManejoHoraFecha();
			try {
				subsParental.setFecha_emision_documento(hfa.getFechaISO_sql(subsParentalForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				subsParental.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}
			try {
				subsParental.setFecha_inicio_postnatal_parental(hfa.getFechaISO_sql(subsParentalForm.getFecha_inicio_postnatal_parental()));
			} catch (Exception ex) {
				subsParental.setFecha_inicio_postnatal_parental(hfa.getFechaISO_sql("0001-01-01"));
			}
			try {
				subsParental.setFecha_termino_postnatal_parental(hfa.getFechaISO_sql(subsParentalForm.getFecha_termino_postnatal_parental()));
			} catch (Exception ex) {
				subsParental.setFecha_termino_postnatal_parental(hfa.getFechaISO_sql("0001-01-01"));
			}
			subsParental.setMes_informacion(subsParentalForm.getMes_informacion());
			subsParental.setMes_nomina(subsParentalForm.getMes_nomina());
			subsParental.setMod_pago(subsParentalForm.getMod_pago());
			subsParental.setMonto_cotiz_fu(subsParentalForm.getMonto_cotiz_fu());
			subsParental.setMonto_cotiz_sc(subsParentalForm.getMonto_cotiz_sc());
			subsParental.setMonto_desahucio(subsParentalForm.getMonto_desahucio());
			subsParental.setMonto_documento(subsParentalForm.getMonto_documento());
			subsParental.setMonto_fp(subsParentalForm.getMonto_fp());
			subsParental.setMonto_remun_imponible(subsParentalForm.getMonto_remun_imponible());
			subsParental.setMonto_salud(subsParentalForm.getMonto_salud());
			subsParental.setMonto_salud_ad(subsParentalForm.getMonto_salud_ad());
			subsParental.setMonto_sub_dfl44_1978(subsParentalForm.getMonto_sub_dfl44_1978());
			subsParental.setMonto_sub_pagado(subsParentalForm.getMonto_sub_pagado());
			subsParental.setNombre_beneficiario_parental(subsParentalForm.getNombre_beneficiario_parental());
			subsParental.setNombre_beneficiario_postnatal(subsParentalForm.getNombre_beneficiario_postnatal());
			subsParental.setNombre_empleador(subsParentalForm.getNombre_empleador());
			subsParental.setNro_licencia(subsParentalForm.getNro_licencia());
			subsParental.setNro_resolucion(subsParentalForm.getNro_resolucion());
			subsParental.setNum_dias_cotiz_pagados(subsParentalForm.getNum_dias_cotiz_pagados());
			subsParental.setNum_dias_permiso_pagado(subsParentalForm.getNum_dias_permiso_pagado());
			subsParental.setNum_documento(subsParentalForm.getNum_documento());
			subsParental.setRun_beneficiario_parental(subsParentalForm.getRun_beneficiario_parental());
			subsParental.setRun_beneficiario_postnatal(subsParentalForm.getRun_beneficiario_postnatal());
			subsParental.setRut_empleador(subsParentalForm.getRut_empleador());
			subsParental.setSerie_documento(subsParentalForm.getSerie_documento());
			subsParental.setSexo(subsParentalForm.getSexo());
			subsParental.setSubsidio_iniciado(subsParentalForm.getSubsidio_iniciado());
			subsParental.setVinculo_beneficiario_menor(subsParentalForm.getVinculo_beneficiario_menor());
			subsParental.setTipo_de_pago(subsParentalForm.getTipo_de_pago());
			subsParental.setTipo_emision(subsParentalForm.getTipo_emision());
			subsParental.setTipo_extension_postnatal_parental(subsParentalForm.getTipo_extension_postnatal_parental());
			subsParental.setTraspaso(subsParentalForm.getTraspaso());

			subsParental.setIndicador_convenio(subsParentalForm.getIndicador_convenio());

			subsParentalMannager.Actualizar(subsParental);

			ArrayList listaSubsParental = new ArrayList();
			keyFin = 0;
			listaSubsParental = subsParentalMannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsParental", listaSubsParental);
			System.out.println(" forw");
			forward = mapping.findForward("sucessActualizarSubsParental");

		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);

	}

}
