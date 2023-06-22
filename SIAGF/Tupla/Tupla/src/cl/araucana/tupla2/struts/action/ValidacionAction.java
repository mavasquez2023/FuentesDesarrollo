package cl.araucana.tupla2.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.DatosValidacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramoTO;
import cl.araucana.tupla2.struts.bussiness.TO.ValidacionTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;

public class ValidacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {

		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		String mensaje = null;
		List result = new ArrayList();
		List tramos = new ArrayList();
		List tuplas = new ArrayList();
		CamposXmlTO oCampo = new CamposXmlTO();

		Araucanajdbcdao dao = new Araucanajdbcdao();
		SqlParametersTO oSql = new SqlParametersTO();
		DatosValidacionTO oDatos = new DatosValidacionTO();
		DatosValidacionTO oDatosUpdate = new DatosValidacionTO();
		TramoTO oTramo = new TramoTO();
		ValidacionTO oValida = (ValidacionTO) form;

		oSql.setEsquemaorigen(oValida.getEsquemaorigen().trim());
		oSql.setEsquemadestino(oValida.getEsquemadestino().trim());
		oSql.setTablaorigen(oValida.getTablaorigen().trim());
		oSql.setTabladestino(oValida.getTabladestino().trim());
		oSql.setMaxid(oValida.getMaxid().trim());

		try {

			result = dao.getvalidacion(oSql);

			for (int i = 0; i < result.size(); i++) {

				oDatos = (DatosValidacionTO) result.get(i);
				int c = 0;
				String estado = "N";
				if (dao.getEstadoA(Integer.parseInt(oDatos.getAFSIRUBE()), oDatos.getAFSIFEAU(), oDatos.getAFFEEXTC(), oSql) > 0)
					estado = "A";
				else if (dao.getEstadoB(Integer.parseInt(oDatos.getAFSIRUBE()), oDatos.getAFSIFEAU(), oDatos.getAFFEEXTC(), oSql) > 0)
					estado = "B";
				else if (dao.getEstadoC(Integer.parseInt(oDatos.getAFSIRUBE()), oDatos.getAFSIFEAU(), oDatos.getAFFEEXTC(), oSql) > 0)
					estado = "C";
				else if (dao.getEstadoD(Integer.parseInt(oDatos.getAFSIRUBE()), oDatos.getAFSIFEAU(), oDatos.getAFFEEXTC(), oSql) > 0)
					estado = "D";

				oDatosUpdate.setEXISTENCIASIAGF(estado);

				tuplas = dao.getdatosvalida(oSql, oDatos.getAFSIRUBE().trim() + "-" + oDatos.getDVBENE().trim());
				tramos = dao.getTramosRetroactivos(oDatos.getRUTBENEFICIARIO(), oDatos.getAFSIFEAU(), oDatos.getAFFEEXTC());
				oDatosUpdate.setAFSIRUBE(oDatos.getAFSIRUBE());
				oDatosUpdate.setCANTIDADTRAMO(String.valueOf(tramos.size()));

				if (Integer.parseInt(oDatosUpdate.getCANTIDADTRAMO()) == 1) {
					oTramo = (TramoTO) tramos.get(0);
					for (int j = 0; j < tuplas.size(); j++) {

						oCampo = (CamposXmlTO) tuplas.get(j);

						if (oCampo.getCodtipocausante().trim().equalsIgnoreCase(oDatos.getCODCAUSANTE().trim())) {
							oDatosUpdate.setESTADOCAUSANTE("OK");
							c++;
						} else {
							oDatosUpdate.setESTADOCAUSANTE("NOK");
						}
						System.out.println(oCampo.getRutBeneficiario());

						if (oCampo.getRutBeneficiario() == oDatos.getRUTBENEFICIARIO()) {
							oDatosUpdate.setESTADOBENEFICIARIO("OK");
							c++;
						} else {
							oDatosUpdate.setESTADOBENEFICIARIO("NOK");
						}
						if (oCampo.getCodEntidadAdm().trim().equalsIgnoreCase(oDatos.getCODIGOENTIDAD().trim())) {
							oDatosUpdate.setCODIGOENTIDAD("OK");
							c++;
						} else {
							oDatosUpdate.setCODIGOENTIDAD("NOK");
						}

						if (Integer.parseInt(oTramo.getPeriodo()) == Integer.parseInt(oCampo.getPeriodo())) {
							oDatosUpdate.setESTADOTRAMO("OK");
							c++;
						} else {
							oDatosUpdate.setESTADOTRAMO("NOK");
						}
					}
				} else {
					oDatosUpdate.setESTADOTRAMO("NOK");

				}

				if (c == 4) {
					oDatosUpdate.setESTADOFINAL("OK");
				} else {
					oDatosUpdate.setESTADOFINAL("NOK");
				}

				if (!dao.updateOrigen(oSql, oDatosUpdate)) {
					System.out.println("error " + i);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			mensaje = "La sesión expiró o el sistema encontró una excepción";
			errors.add("name", new ActionMessage("id"));
		}

		if (!errors.isEmpty()) {

			request.setAttribute("mensaje", mensaje);
			forward = mapping.findForward("onError");
		} else {

			forward = mapping.findForward("onSuccess");
		}

		return forward;

	}

}
