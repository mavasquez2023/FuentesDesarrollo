package cl.araucana.tupla2.struts.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.tupla2.struts.bussiness.TO.DatosExtinsionTO;
import cl.araucana.tupla2.struts.bussiness.TO.ExtinsionTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;
import cl.paperless.siagf.ws.ExtincionReconocimientoPortTypeProxy;

public class ExtincionActionOld extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		String mensaje = null;
		List lista = new ArrayList();
		TuplaTO oTupla = new TuplaTO();

		try {
			//Archivo de configuracion
			Properties properties = new LoadPropertiesFile().load("wssiagf.properties");

			ExtinsionTO oExtinsion = (ExtinsionTO) form;
			SqlParametersTO oSql = new SqlParametersTO();
			ConstruyeXml oConstruye = new ConstruyeXml();
			oSql.setEsquemaorigen(oExtinsion.getEsquemaorigen());
			oSql.setTablaorigen(oExtinsion.getTablaorigen());
			DatosExtinsionTO oDatos = new DatosExtinsionTO();

			XmlParse parse = new XmlParse();
			ExtincionReconocimientoPortTypeProxy extincion = new ExtincionReconocimientoPortTypeProxy();
			extincion.setEndpoint(properties.getProperty("webService.ExtincionReconocimiento"));

			//Autenticacion contra WS
			AutenticacionPortTypeProxy autentica = new AutenticacionPortTypeProxy();
			autentica.setEndpoint(properties.getProperty("webService.Autenticacion"));
			String respuesta = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
			String token = parse.parsearXMLautentica(respuesta);
			String rutCausante = oExtinsion.getRutcausante();

			//Obtiene informacion del causante a extinguir desde DB2

			Araucanajdbcdao dao = new Araucanajdbcdao();
			oDatos = dao.getdatosExtinsion(oSql, rutCausante);

			//Consulta del causante desde WS
			ConsultaCausantePortTypeProxy consulta = new ConsultaCausantePortTypeProxy();
			consulta.setEndpoint(properties.getProperty("webService.ConsultaCausante"));
			String xml = consulta.consultaCausante(token, rutCausante);
			lista = parse.parseaXmlTupla(xml);
			System.out.println("Largo de Tupla de consulta causante:" + lista.size());

			for (int i = 0; i < lista.size(); i++) {
				oTupla = (TuplaTO) lista.get(i);
				if (/*oDatos.getFechaReconocimiento().trim().equalsIgnoreCase(oTupla.getFecRecCausante().trim())&&*/
				oTupla.getCodEntidadAdm().trim().equalsIgnoreCase("10105") && oTupla.getCodEstadoTupla().trim().equalsIgnoreCase("vigente")) {
					String envioExtinsion = oConstruye.creaXmlExtinsion(oDatos, oTupla);
					System.out.println(i + ".-XML de Extinsion:" + envioExtinsion);
					//extincion.extincionReconocimiento(token, envioExtinsion);
				}
			}
		} catch (Exception e) {
			mensaje = e.getMessage();
			errors.add("name", new ActionMessage(e.getMessage()));
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
