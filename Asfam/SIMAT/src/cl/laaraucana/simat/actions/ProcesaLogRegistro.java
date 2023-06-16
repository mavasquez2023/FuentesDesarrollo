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
import cl.laaraucana.simat.entidades.DatosLicCobVO;
import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.entidades.ReintegrosVO;
import cl.laaraucana.simat.entidades.SubsParentalVO;
import cl.laaraucana.simat.entidades.SubsPrePostNMVO;
import cl.laaraucana.simat.forms.RegistroAfectadoForm;
import cl.laaraucana.simat.mannagerDB2.ControlDocuMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicCobMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicResolMannager;
import cl.laaraucana.simat.mannagerDB2.DocsRevalReemMannager;
import cl.laaraucana.simat.mannagerDB2.ReintegrosMannager;
import cl.laaraucana.simat.mannagerDB2.SubsParentalMannager;
import cl.laaraucana.simat.mannagerDB2.SubsPrePostNmMannager;

/**
 * @version 	1.0
 * @author
 */
public class ProcesaLogRegistro extends AbstractAction

{

	public ActionForward editarTabla(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		RegistroAfectadoForm registroAfectadoForm = (RegistroAfectadoForm) form;

		String tabla = null;
		int registro_id = 0;
		String t1 = "SMF01";
		String t2 = "SMF02";
		String t3 = "SMF03";
		String t4 = "SMF04";
		String t5 = "SMF05";
		String t6 = "SMF06";
		String t7 = "SMF07";
		String t8 = "SMF08";
		boolean key = false;

		System.out.println("action editar_tabla: ");

		if (registroAfectadoForm != null) {
			tabla = registroAfectadoForm.getTabla_afectada().trim();
			registro_id = Integer.parseInt(registroAfectadoForm.getRegistro_afectado().trim());
			System.out.println("TABLA: " + tabla);
			System.out.println("REGISTRO: " + registro_id);

			if (tabla.equals(t1)) {
				System.out.println("tabla 1");
				try {
					ArrayList listaReintegros = new ArrayList();
					ReintegrosVO reintegros = new ReintegrosVO();
					reintegros.setId(registro_id);
					ReintegrosMannager mannager = new ReintegrosMannager();
					reintegros = mannager.BuscarByID(reintegros);
					System.out.println("actiont1: " + reintegros.getCodigo_entidad());
					System.out.println("actiont1: " + reintegros.getMes_informacion());

					if (reintegros != null) {
						listaReintegros.add(reintegros);
					} else {
						listaReintegros = new ArrayList();
					}
					request.setAttribute("listaReintegros", listaReintegros);

					forward = mapping.findForward("successEditarTabla_1");

				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t2)) {
				System.out.println("tabla 2");
				try {
					ArrayList listaSubsPrePostNM = new ArrayList();
					SubsPrePostNMVO subs = new SubsPrePostNMVO();
					SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
					subs.setId(registro_id);
					subs = mannager.BuscarByID(subs);
					System.out.println("actiont2: " + subs.getCodigo_entidad());
					System.out.println("actiont2: " + subs.getMes_informacion());

					if (subs != null) {
						listaSubsPrePostNM.add(subs);
					} else {
						listaSubsPrePostNM = new ArrayList();
					}
					request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);

					forward = mapping.findForward("successEditarTabla_2");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t3)) {
				System.out.println("tabla 3");
				try {
					ArrayList listaSubsParental = new ArrayList();
					SubsParentalVO subsParental = new SubsParentalVO();
					SubsParentalMannager subsParentalMannager = new SubsParentalMannager();

					subsParental.setIdParental(registro_id);
					subsParental = subsParentalMannager.BuscarByID(subsParental);
					System.out.println("actiont2: " + subsParental.getCodigo_entidad());
					System.out.println("actiont2: " + subsParental.getMes_informacion());

					if (subsParental != null) {
						listaSubsParental.add(subsParental);
					} else {
						listaSubsParental = new ArrayList();
					}
					request.setAttribute("listaSubsParental", listaSubsParental);

					forward = mapping.findForward("successEditarTabla_3");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t5)) {
				System.out.println("tabla 5");
				try {
					ControlDocuMannager mannager = new ControlDocuMannager();
					ControlDocuVO control = new ControlDocuVO();
					ArrayList listaControlDocu = new ArrayList();
					control.setIdControlDocu(registro_id);
					control = mannager.BuscarById(control);

					if (control != null) {
						listaControlDocu.add(control);
					} else {
						listaControlDocu = new ArrayList();
					}
					request.setAttribute("listaControlDocu", listaControlDocu);

					forward = mapping.findForward("successEditarTabla_5");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t6)) {
				System.out.println("tabla 6");
				try {
					DocsRevalReemVO docs = new DocsRevalReemVO();
					DocsRevalReemMannager mannager = new DocsRevalReemMannager();
					ArrayList listaDocsRevalReem = new ArrayList();
					docs.setId(registro_id);
					docs = mannager.BuscarByID(docs);

					if (docs != null) {
						listaDocsRevalReem.add(docs);
					} else {
						listaDocsRevalReem = new ArrayList();
					}
					request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);

					forward = mapping.findForward("successEditarTabla_6");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t7)) {
				System.out.println("tabla 7");
				try {

					ArrayList listaDatosLicCob = new ArrayList();
					DatosLicCobMannager mannager = new DatosLicCobMannager();
					DatosLicCobVO datos = new DatosLicCobVO();
					datos.setId(registro_id);
					datos = mannager.BuscarByID(datos);

					if (datos != null) {
						listaDatosLicCob.add(datos);
					} else {
						listaDatosLicCob = new ArrayList();
					}
					request.setAttribute("listaDatosLicCob", listaDatosLicCob);

					forward = mapping.findForward("successEditarTabla_7");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
			if (tabla.equals(t8)) {
				System.out.println("tabla 8");
				try {
					DatosLicResolMannager mannager = new DatosLicResolMannager();
					DatosLicResolVO datosLicResol = new DatosLicResolVO();
					ArrayList listaDatosLicResol = new ArrayList();
					datosLicResol.setId(registro_id);

					datosLicResol = mannager.BuscarByID(datosLicResol);

					if (datosLicResol != null) {
						listaDatosLicResol.add(datosLicResol);
					} else {
						listaDatosLicResol = new ArrayList();
					}

					request.setAttribute("listaDatosLicResol", listaDatosLicResol);

					forward = mapping.findForward("successEditarTabla_8");
					System.out.println("fin despues del forward: ");
				} catch (Exception e) {
					// Report the error using the appropriate name and ID.
					errors.add("name", new ActionError("id"));
					key = true;
				}
			}
		} else {
			System.out.println("-> forward null: ");
			key = true;
		}

		if (key) {
			System.out.println("evaluando key para error");
			ArrayList listaProcesos = new ArrayList();
			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("error");
		}

		// Finish with
		return (forward);
	}

}
