package cl.laaraucana.simat.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ControlDocuVO;
import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.entidades.TablaHomologacionVO;
import cl.laaraucana.simat.entidades.UsuariosVO;
import cl.laaraucana.simat.forms.ControlDocuForm;
import cl.laaraucana.simat.forms.DatosLicResolForm;
import cl.laaraucana.simat.forms.DocsRevalReemForm;
import cl.laaraucana.simat.forms.UsuariosForm;
import cl.laaraucana.simat.mannagerDB2.ControlDocuMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicCobMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicResolMannager;
import cl.laaraucana.simat.mannagerDB2.DocsRevalReemMannager;
import cl.laaraucana.simat.mannagerDB2.LogProcesosMannager;
import cl.laaraucana.simat.mannagerDB2.ReintegrosMannager;
import cl.laaraucana.simat.mannagerDB2.SubsParentalMannager;
import cl.laaraucana.simat.mannagerDB2.SubsPrePostNmMannager;
import cl.laaraucana.simat.mannagerDB2.TablaHomologacionMannager;
import cl.laaraucana.simat.mannagerDB2.UsuariosMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class ProcesaMenuAction extends AbstractAction {

	public ActionForward mostrarPag1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//t1 reintegros		
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value		
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaReintegros = new ArrayList();
				ReintegrosMannager mannager = new ReintegrosMannager();
				listaReintegros = mannager.BuscarListaAvanzar(keyFin);
				request.setAttribute("listaReintegros", listaReintegros);
				String keyInicioCopy = String.valueOf(keyInicio);
				String keyFinCopy = String.valueOf(listaReintegros.size());
				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);
				forward = mapping.findForward("mostrarPag1");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}//end mostrarPag1

	public ActionForward mostrarPag2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//metodo para ir a la pagina plano 2 subprepostNM
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaSubsPrePostNM = new ArrayList();
				SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
				listaSubsPrePostNM = mannager.BuscarListaAvanzar(keyFin);
				request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
				String keyInicioCopy = String.valueOf(keyInicio);
				String keyFinCopy = String.valueOf(listaSubsPrePostNM.size());
				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);
				forward = mapping.findForward("mostrarPag2");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}//end 

	public ActionForward mostrarPag3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaSubsParental = new ArrayList();
				//respaldar indices mostrados.							
				//cargar 10 primeros
				SubsParentalMannager subsParentalMannager = new SubsParentalMannager();
				//listaSubsParental=subsParentalMannager.BuscarLista(keyInicio,keyFin);
				listaSubsParental = subsParentalMannager.BuscarListaAvanzar(keyFin);
				request.setAttribute("listaSubsParental", listaSubsParental);
				String keyInicioCopy = String.valueOf(keyInicio);
				String keyFinCopy = String.valueOf(listaSubsParental.size());
				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);
				forward = mapping.findForward("mostrarPag3");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag4(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//plano 4 no se utiliza 
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		//SubsPrePostNMForm SubsPrePostNMForm = (SubsPrePostNMForm) form;
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaSubsTscVig = new ArrayList();
				request.setAttribute("listaSubsTscVig", listaSubsTscVig);
				forward = mapping.findForward("mostrarPag4");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag5(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//mantenedor tabla 5 controlDocu
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		ControlDocuForm controlDocuForm = (ControlDocuForm) form;

		int keyInicio = 0;
		int keyFin = 0;//para usar fetch

		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaControlDocu = new ArrayList();
				ControlDocuVO control = new ControlDocuVO();

				ControlDocuMannager mannager = new ControlDocuMannager();
				listaControlDocu = mannager.BuscarListaAvanzar(keyFin);

				request.setAttribute("listaControlDocu", listaControlDocu);

				String keyInicioCopy = String.valueOf(keyInicio);

				String keyFinCopy = String.valueOf(listaControlDocu.size());

				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);

				forward = mapping.findForward("mostrarPag5");
			}
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}

		return forward;
	}

	public ActionForward mostrarPag6(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		//plano 6 dosrevalreem
		ActionForward forward = new ActionForward(); // return value
		DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;

		int keyInicio = 0;
		int keyFin = 0;//para usar fetch

		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaDocsRevalReem = new ArrayList();
				DocsRevalReemVO docs = new DocsRevalReemVO();
				DocsRevalReemMannager mannager = new DocsRevalReemMannager();
				listaDocsRevalReem = mannager.BuscarListaAvanzar(keyFin);
				request.setAttribute("listaDocsRevalReem", listaDocsRevalReem);
				String keyInicioCopy = String.valueOf(keyInicio);
				String keyFinCopy = String.valueOf(listaDocsRevalReem.size());
				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);
				forward = mapping.findForward("mostrarPag6");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag7(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		//plano 7 DatosLicCob
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaDatosLicCob = new ArrayList();

				DatosLicCobMannager mannager = new DatosLicCobMannager();
				listaDatosLicCob = mannager.BuscarListaAvanzar(keyFin);

				request.setAttribute("listaDatosLicCob", listaDatosLicCob);

				String keyInicioCopy = String.valueOf(keyInicio);

				String keyFinCopy = String.valueOf(listaDatosLicCob.size());

				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);

				forward = mapping.findForward("mostrarPag7");
			}
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag8(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//plano 8 DatosLicResol
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaDatosLicResol = new ArrayList();
				DatosLicResolVO datos = new DatosLicResolVO();

				DatosLicResolMannager mannager = new DatosLicResolMannager();
				listaDatosLicResol = mannager.BuscarListaAvanzar(keyFin);

				request.setAttribute("listaDatosLicResol", listaDatosLicResol);

				String keyInicioCopy = String.valueOf(keyInicio);

				String keyFinCopy = String.valueOf(listaDatosLicResol.size());

				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);

				forward = mapping.findForward("mostrarPag8");
			}
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag9(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//mostrarpagina t9 usuarios
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		UsuariosForm usuarioForm = (UsuariosForm) form;

		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {

				UsuariosVO usuarios = new UsuariosVO();
				UsuariosMannager mannager = new UsuariosMannager();

				ArrayList listaUsuarios = new ArrayList();
				ArrayList listaAux = new ArrayList();
				listaAux = mannager.BuscarTodo();
				UsuariosVO uvo = new UsuariosVO();
				String test = "";
				ManejoHoraFecha mhf = new ManejoHoraFecha();
				Iterator it = listaAux.iterator();
				while (it.hasNext()) {
					uvo = (UsuariosVO) it.next();
					test = uvo.getUltima_coneccion();
					boolean j = mhf.checkFechaDefault(test);
					if (j) {
						uvo.setUltima_coneccion("-");
					}
					listaUsuarios.add(uvo);
				}

				request.setAttribute("listaUsuarios", listaUsuarios);
				forward = mapping.findForward("mostrarPag9");
			}
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

	public ActionForward mostrarPag10(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//mostrarpagina t10 log procesos
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		int keyInicio = 0;
		int keyFin = 0;//para usar fetch
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaProcesos = new ArrayList();
				LogProcesosMannager mannager = new LogProcesosMannager();
				listaProcesos = mannager.BuscarListaAvanzar(keyFin);

				request.setAttribute("listaProcesos", listaProcesos);

				String keyInicioCopy = String.valueOf(keyInicio);
				String keyFinCopy = String.valueOf(listaProcesos.size());

				request.setAttribute("keyInicioCopy", keyInicioCopy);
				request.setAttribute("keyFinCopy", keyFinCopy);

				forward = mapping.findForward("mostrarPag10");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		// Finish with
		return forward;
	}

	public ActionForward mostrarPag11(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//		mostrarpagina t11 homologacion
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaHomologacion = new ArrayList();
				TablaHomologacionVO homologacion = new TablaHomologacionVO();
				TablaHomologacionMannager thmngr = new TablaHomologacionMannager();

				listaHomologacion = thmngr.buscarTodoHomologacion();
				request.setAttribute("listaHomologacion", listaHomologacion);
				forward = mapping.findForward("mostrarPag11");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		// Finish with		
		return forward;
	}

	public ActionForward mostrarPag12(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value		
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				ArrayList listaInforme = new ArrayList();
				request.setAttribute("listaInforme", listaInforme);
				forward = mapping.findForward("mostrarPag12");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		// Finish with
		return forward;
	}

	public ActionForward openHistorico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesionActual = null;
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				//instanciar clase que hace apertura de carpeta.			
				String ruta = null;
				Runtime r = Runtime.getRuntime();
				Process p = null;

				try {
					ruta = "C:" + File.separator + "CLOUD" + File.separator + "RepositorioPeriodos" + File.separator;
					p = r.exec("explorer.exe " + ruta);
				} catch (Exception e) {
					System.out.println("Error al ejecutar");

				}
				forward = mapping.findForward("mostrarPag1");
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}

}//end class

