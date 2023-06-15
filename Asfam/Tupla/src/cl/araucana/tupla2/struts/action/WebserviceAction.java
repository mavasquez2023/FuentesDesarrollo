package cl.araucana.tupla2.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import cl.araucana.tupla2.business.ConsultaCausanteProcess;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.WebserviceTO;

public class WebserviceAction extends Action {

	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		WebserviceTO form = (WebserviceTO) arg1;
		SqlParametersTO oSql;
		ConsultaCausanteProcess process = null;
		try {
			oSql = new SqlParametersTO();
			oSql.setEsquemaorigen(form.getEsquemaorigen().trim());
			oSql.setEsquemadestino(form.getEsquemadestino().trim());
			oSql.setTablaorigen(form.getTablaorigen().trim());
			oSql.setTablatupla(form.getTablatuplas().trim());
			oSql.setTablacausante(form.getTablacausante().trim());
			oSql.setTablabeneficiario(form.getTablabeneficiario().trim());
			oSql.setTablatramo(form.getTablatramos().trim());
			oSql.setMaxid(form.getMaxid().trim());
			oSql.setMinid(form.getMinid().trim());
			String useTrhread= form.getUseThread();
			oSql.setUseThread(useTrhread==null?false:true);
			String recXml= form.getRecxml();
			oSql.setRecXml(recXml==null?false:true);
			arg2.getSession().setAttribute("oSql", oSql);

			process = new ConsultaCausanteProcess(oSql);
			process.process();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!errors.isEmpty()) {
			arg2.setAttribute("mensaje", ".....");
			forward = arg0.findForward("onError");
		} else {
			arg2.setAttribute("mensaje", process.getMessage());
			forward = arg0.findForward("onSuccess");
		}
		return forward;
	}

}
