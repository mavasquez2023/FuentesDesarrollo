package cl.araucana.cp.presentation.struts.actions.cargasFamiliares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.cargasFamiliares.ConsultaNominasActionForm;

import com.bh.talon.User;

public class CompNomCargasAction extends AppAction
{
	static Logger logger = Logger.getLogger(CompNomCargasAction.class);
	
	static List columnas = new ArrayList(5);
	static List alts = new ArrayList(5);
	static String[] imgsrcs;
	static Map nombreTipoNominas = new HashMap();		// NOMINA_EN_LINEA
	
	static {
		columnas.add("enviarNomina");
		columnas.add("corregirNomina");
		columnas.add("editarNomina");
		columnas.add("comprobanteEditar");
		columnas.add("resumenComprProvi");
		columnas.add("verComprobantes");

		alts.add("Enviar");
		alts.add("Corregir");
		alts.add("Edici&oacute;n N&oacute;mina");
		alts.add("Edici&oacute;n Comprobante");
		alts.add("Pagar");
		alts.add("Ver Comprobantes");
		alts.add("Ver Detalle N&oacute;mina");

		imgsrcs = new String[] {
				"/img/iconosResumen/noEnviada.gif",
				"/img/iconosResumen/verCmpPagado.gif",
				"/img/iconosResumen/corregirNomina.gif",
				"/img/iconosResumen/editarNomina.gif",
				"/img/iconosResumen/editarCmp.gif",
				"/img/iconosResumen/detalleCmp.gif",
				"/img/iconosResumen/pagar.gif",
				"/img/iconosResumen/verTrabajadores.gif",
				"/img/iconosResumen/crearNomina.gif",	// NOMINA_EN_LINEA
				"/img/iconosResumen/verErrores.jpg" 	//Ver Errores
		};

		nombreTipoNominas.put("R", "Remuneraciones");
		nombreTipoNominas.put("G", "Gratificaciones");
		nombreTipoNominas.put("D", "Dep&oacute;sitos Convenios e Indemnizaciones");
		nombreTipoNominas.put("A", "Reliquidaciones");		
	}

	public CompNomCargasAction() {
		super();
		this.btns.add("filtro");
	}


	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ConsultaNominasActionForm form = (ConsultaNominasActionForm) actionForm;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);

			String tipoNomina = form.getTipoNominaAux();

			HashMap result = (HashMap) request.getSession().getAttribute("nominasValidandose");

			boolean tieneAvisoCarga = false;

			int rutEmpresa = -1;
			int idConvenio = -1;

			NominaVO nomina;
			List nominas = new ArrayList();
			
			int flgNominasEnProgreso = 0;
			for (Iterator it = result.keySet().iterator(); it.hasNext();) {

				String valor = String.valueOf(it.next());
				String[] elementos = valor.split(";");
				rutEmpresa = Integer.parseInt(elementos[0]);
				idConvenio = Integer.parseInt(elementos[1]);

				nomina = procesoMgr.getNomina(tipoNomina, rutEmpresa, idConvenio);
				nomina.setIdformateado(Utils.formatRut(nomina.getRutEmpresa()));
				nomina.setMostrarChkBox(false);
				nomina.setAccion1("");
				nomina.setAccion2("");
				nomina.setAccion3("");
				nomina.setAccion4("");
				nomina.setAccion5("");
				nomina.setAccion6("");
				nomina.setAccion7("");
				nomina.setAccion8("");
				nomina.setAccion9("");
				nomina.setAccion10("");

				tieneAvisoCarga = procesoMgr.isNominaConAvisosDeCarga(nomina.getEmpresa().getIdEmpresa(), nomina.getIdConvenio(), tipoNomina);
				//tieneAvisoCarga = ((Boolean) result.get(valor)).booleanValue();

				if (tieneAvisoCarga) {
					if (nomina.getIdEstado() == Constants.EST_NOM_POR_PAGAR)
						nomina.setAccion3("X"); //CON AVISOS
					else
						flgNominasEnProgreso = 1;
				} else if (nomina.getIdEstado() != Constants.EST_NOM_EN_PROCESO  && nomina.getIdEstado() != Constants.EST_NOM_EN_EJB) {//!= Constants.EST_NOM_EN_PROCESO  && nomina.getIdEstado() != Constants.EST_NOM_EN_EJB) {
					nomina.setAccion10("X"); //CON OK
				} else if (nomina.getIdEstado() == Constants.EST_NOM_EN_PROCESO  || nomina.getIdEstado() == Constants.EST_NOM_EN_EJB) {
					flgNominasEnProgreso = 1;
				}

				nominas.add(nomina);
			}

			Collections.sort(nominas);
			form.setImgsrcs(imgsrcs);
			request.setAttribute("tipoNominaRespaldo", tipoNomina);
			request.setAttribute("flgNominasEnProgreso", String.valueOf(flgNominasEnProgreso));

			form.setMostrarTotal(false);
			form.setMostrarValidar(false);
			form.setnumeroFilas(new ArrayList());
			form.setNominas(nominas);

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