package cl.araucana.tupla2.struts.action;

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
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;
import cl.paperless.siagf.ws.ExtincionReconocimientoPortTypeProxy;

public class ExtincionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		XmlParse parse = new XmlParse();
		RetornoTO oRetorno = new RetornoTO();
		ExtinsionTO form1 = (ExtinsionTO) form;
		ConstruyeXml oConstruye = new ConstruyeXml();
		if (request.getParameter("isForm") == null)
			return mapping.findForward("showForm");

		String mensaje = null;
		Araucanajdbcdao dao = null;
		List listaCausantes = null;
		SqlParametersTO oSql = new SqlParametersTO();

		//Archivo de configuracion
		Properties properties = new LoadPropertiesFile().load("wssiagf.properties");

		//WebServices utilizados
		AutenticacionPortTypeProxy autentica = new AutenticacionPortTypeProxy();
		autentica.setEndpoint(properties.getProperty("webService.Autenticacion"));
		ExtincionReconocimientoPortTypeProxy extincion = new ExtincionReconocimientoPortTypeProxy();
		extincion.setEndpoint(properties.getProperty("webService.ExtincionReconocimiento"));
		ConsultaCausantePortTypeProxy consulta = new ConsultaCausantePortTypeProxy();
		consulta.setEndpoint(properties.getProperty("webService.ConsultaCausante"));

		try {
			//Coneccion a DB2
			dao = new Araucanajdbcdao();

			//Datos del formulario web
			oSql.setEsquemaorigen(form1.getEsquemaorigen().trim());
			oSql.setEsquemadestino(form1.getEsquemadestino().trim());
			oSql.setTablaorigen(form1.getTablaorigen().trim());
			oSql.setTabladestino(form1.getTabladestino().trim());
			oSql.setMaxid(form1.getMaxid().trim());

			//Obtiene datos de los causantes desde DB2
			listaCausantes = dao.getDatosExtincion(oSql);
			System.out.println("[Extingir]Nro. de Registros por extinguir:" + listaCausantes.size());
		} catch (Exception e) {
			request.setAttribute("mensaje", "No es posible obtener lista de causantes: " + e.getMessage());
			forward = mapping.findForward("onError");
			return forward;
		}

		int succefull = 0;
		//Por cada causante a extinguir....
		DatosExtinsionTO causanteDB2 = null;
		String xmlExtinsion = null;
		for (int i = 0; i < listaCausantes.size(); i++) {
			try {
				xmlExtinsion = null;
				causanteDB2 = (DatosExtinsionTO) listaCausantes.get(i);

				//Autentificacion con WS
				String token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
				String parsetoken = parse.parsearXMLautentica(token);

				//Consulta del causante desde WS
				String respuestaConsulta = consulta.consultaCausante(parsetoken, causanteDB2.getRutCausante());
				List listaTuplas = parse.parseaXmlTupla(respuestaConsulta);
				System.out.println("[Extingir]" + i + ".-Largo de Tupla de consulta causante:" + listaTuplas.size());
				System.out.println("[Extingir] Xml respuesta ConsultaCausante: " + respuestaConsulta);
				
				boolean causanteValido = false;
				System.out.println("Evaluando CodEntidadAdm = 10105, CodEstadoTupla = 3, FecRecCausante(WS) = FechaRec(BD)");
				for (int j = 0; j < listaTuplas.size(); j++) {
					TuplaTO causanteWS = (TuplaTO) listaTuplas.get(j);
					System.out.println("Cod Entidad Adm: " + causanteWS.getCodEntidadAdm() + ", Cod Estado Tupla: " + causanteWS.getCodEstadoTupla());
					System.out.println("FecRecCausante(WS): " + causanteWS.getFecRecCausante() + ", FechaRec (BD): " + causanteDB2.getFechaRec());
					
					if (causanteWS.getCodEntidadAdm().trim().equalsIgnoreCase("10105") && !causanteWS.getCodEstadoTupla().trim().equalsIgnoreCase("3") 
							&& causanteWS.getFecRecCausante().trim().equalsIgnoreCase(causanteDB2.getFechaRec())) {
						causanteValido = true;
						xmlExtinsion = oConstruye.creaXmlExtinsion(causanteDB2, causanteWS);
						System.out.println("[Extingir]" + i + ".-" + j + ".-XML de Extinsion:" + xmlExtinsion);
						String respuestaExtincion = extincion.extincionReconocimiento(parsetoken, xmlExtinsion);

						//Almacena respuesta de la extincion en DB2
						System.out.println("[Extingir]" + i + ".-" + j + ".-XML de respuesta:" + respuestaExtincion);
						oRetorno = parse.parsearXMLRetorno(respuestaExtincion);
						oRetorno.setId(causanteDB2.getId());
						oRetorno.setCodigoxml(xmlExtinsion);
						if (!dao.saveRetornoActualiza(oRetorno, oSql)) {
							errors.add("name", new ActionMessage("id"));
							mensaje = "Error al grabar datos enviados del siagf";
						} else {
							if (oRetorno.getCodigo() != null && oRetorno.getCodigo().equals("0"))
								succefull++;
						}
					}
				}
				if (!causanteValido) {
					oRetorno = new RetornoTO();
					oRetorno.setId(causanteDB2.getId());
					oRetorno.setCodigoxml(xmlExtinsion);
					oRetorno.setMensaje("Causante No Vigente o No pertenece a la entidad 10105");
					oRetorno.setCodigo("-98");
					if (!dao.saveRetornoActualiza(oRetorno, oSql)) {
						errors.add("name", new ActionMessage("id"));
						mensaje = "Error al grabar datos enviados del siagf";
					}
				}
			} catch (Exception ex) {
				oRetorno = new RetornoTO();
				oRetorno.setId(causanteDB2.getId());
				oRetorno.setCodigoxml("");
				oRetorno.setMensaje("Exception:" + ex.getMessage());
				oRetorno.setCodigo("-99");

				if (!dao.saveRetornoActualiza(oRetorno, oSql)) {
					errors.add("name", new ActionMessage("id"));
					mensaje = "Error al grabar datos de retorno";
				}
				ex.printStackTrace();
			}
		}

		request.setAttribute("mensaje", "Causantes extinguidos: " + succefull + " de " + listaCausantes.size());
		forward = mapping.findForward("onSuccess");
		return forward;
	}
}
