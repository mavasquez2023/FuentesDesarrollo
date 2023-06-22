package cl.araucana.tupla2.struts.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.tupla2.struts.bussiness.TO.ActualizacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramosTO;
import cl.araucana.tupla2.struts.bussiness.TO.WebserviceTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.IngresoReconocimientoPortTypeProxy;

public class EnvioXmlAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		long total_inicio = System.currentTimeMillis();

		//crea objetos
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		ConstruyeXml construye = new ConstruyeXml();
		Araucanajdbcdao dao = new Araucanajdbcdao();
		
		SqlParametersTO oSql = new SqlParametersTO();
		List datos = new ArrayList();
		String mensaje = null;
		String xml = null;
		String token = null;
		String respuesta = null;
		String parsetoken = null;

		try {
			//Crea objetos
			AutenticacionPortTypeProxy autentica = new AutenticacionPortTypeProxy();
			IngresoReconocimientoPortTypeProxy envio = new IngresoReconocimientoPortTypeProxy();
			XmlParse parse = new XmlParse();
			RetornoTO oRetorno = new RetornoTO();

			//Archivo de configuracion
			Properties properties = new LoadPropertiesFile().load("wssiagf.properties");

			//Set ENDPOINTS 
			autentica.setEndpoint(properties.getProperty("webService.Autenticacion"));
			envio.setEndpoint(properties.getProperty("webService.IngresoReconocimiento"));

			//obtiene datos del formulario WEB
			ActualizacionTO form1 = (ActualizacionTO) form;
			oSql.setEsquemaorigen(form1.getEsquemaorigen().trim());
			oSql.setEsquemadestino(form1.getEsquemadestino().trim());
			oSql.setTablaorigen(form1.getTablaorigen().trim());
			oSql.setTablamarcarechazo(form1.getTablamarcarechazo().trim());
			oSql.setTablatramo(form1.getTablatramo().trim());
			oSql.setTabladestino(form1.getTabladestino().trim());
			oSql.setTabla011(form1.getTabla011().trim());
			oSql.setTabla012(form1.getTabla012().trim());
			oSql.setAccion(form1.getAccion());
			oSql.setActualizar(form1.getActualizar());
			oSql.setMinperiodo(form1.getMinperiodo());
			oSql.setMaxperiodo(form1.getMaxperiodo());
			oSql.setProceso(form1.getProceso());
			if(form1.getMaxperiodo()==0){
				oSql.setMaxperiodo(300000);
			}
			String procesos="";
			if(oSql.getProceso()!=null){
				for (int i = 0; i < oSql.getProceso().length; i++) {
					procesos+= "," + oSql.getProceso()[i];
				}
			}
			
			Map<String, TramosTO> tramos= dao.getTramosAsfam();
			
			//obtencion de datos para generar XML
			dao.insertDatosEmpresa(oSql);
			dao.insertDatosBeneficiario(oSql);
			datos = dao.getDatosXmlIngresoMensual(oSql);
			/*if(procesos.indexOf("M")>-1){
				//Mensual
				datos = dao.getDatosXmlIngresoMensual(oSql);
			}else{
				//Correctivo
				datos = dao.getDatosxmlIngreso(oSql);
			}*/
			//obtiene token de autenticacion
			//token=autentica.login(new Integer(10105),"carga_10105","abc123");
			token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
			parsetoken = parse.parsearXMLautentica(token);

			for (int i = 0; i < datos.size(); i++) {
				long individual_inicio = System.currentTimeMillis();
				int enviook=0;
				int intentos=0;
				CamposXmlTO oCampo = (CamposXmlTO) datos.get(i);
				try {
					
					TramosTO datosTramo=null;
					try {
						datosTramo = tramos.get(oCampo.getPeriodo()+oCampo.getTramoAsigFam());
						oCampo.setValorTramo(String.valueOf(datosTramo.getValor()));
					} catch (Exception e) {
						System.out.println("Error al setear valor tramo de registro ID: " + oCampo.getId());
						throw new Exception("Error al setear valor tramo para periodo: " + oCampo.getPeriodo() + ", tramo: " + oCampo.getTramoAsigFam());
					}
					/*if(procesos.indexOf("M")>-1){
						//Mensual
						oCampo.setValorTramo(String.valueOf(datosTramo.getValor()));
					}else{
						//Correctivo
						oCampo.setValorTramo(oCampo.getMontoUnitarioBeneficio());
					}*/
					//Se setea Ingreso Promedio
					if(Integer.parseInt(oCampo.getIngPromedio())>datosTramo.getRentaMaxima() || Integer.parseInt(oCampo.getIngPromedio())<datosTramo.getRentaMinima()){
						oCampo.setIngPromedio(String.valueOf(datosTramo.getRentaMaxima()));
					}
					boolean vigente=true;
					while (enviook==0 && intentos<2) {
						//genera xml con datos obtenidos
						xml = construye.creaXml(oCampo, vigente);
						System.out.println("EnvioXmlAction:xml " + xml);

						//ejecuta servicio de ingreso
						respuesta = envio.ingresoReconocimiento(parsetoken, xml);
						System.out.println("res " + respuesta);
						oRetorno = parse.parsearXMLRetorno(respuesta);
						oRetorno.setId(oCampo.getId());
						oRetorno.setCodigo(oRetorno.getCodigo());
						oRetorno.setMensaje(oRetorno.getMensaje());
						oRetorno.setCodigoxml(xml);
						enviook=1;
						if (oRetorno.getCodigo().trim().equals("-15")) {
							System.out.println("Error de No Vigente");
							enviook=0;
							intentos++;
							vigente=false;
						}
					}
					//analiza respuesta para detectar error por token invalido (codigo -6)
					if (oRetorno.getCodigo().trim().equals("-6")) {
						System.out.println("Error de Token");
						token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
						parsetoken = parse.parsearXMLautentica(token);
					}

					
				} catch (Exception e) {
					System.out.println("Error al procesar registro ID: " + oCampo.getId() + ", Rut Causante: " + oCampo.getRutCausante());
					oRetorno = new RetornoTO();
					oRetorno.setId(oCampo.getId());
					oRetorno.setCodigo("-1");
					oRetorno.setMensaje(e.getMessage());
				}
				//guarda respuesta del servicio
				if (!dao.saveRetornoEnvio(oRetorno, oSql)) {
					errors.add("name", new ActionMessage("id"));
					mensaje = "Error al grabar datos enviados del siagf";
				}
				
				long individual_fin = System.currentTimeMillis();
				System.out.println("TiempoIndividual;" + (individual_fin - individual_inicio) + ";milisegundos");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			mensaje = "Error en el sistema";
			errors.add("name", new ActionMessage("id"));
		} finally {
			dao.disconect();
		}
		if (!errors.isEmpty()) {
			request.setAttribute("mensaje", mensaje);
			forward = mapping.findForward("onError");
		} else {
			forward = mapping.findForward("onSuccess");
		}
		long total_fin = System.currentTimeMillis();
		System.out.println("TiempoTotal;" + (total_fin - total_inicio) + ";milisegundos");
		return forward;
	}

	private String getPeriodoActual(){
		Calendar today = Calendar.getInstance();
		int year= today.get(Calendar.YEAR);
		int month= today.get(Calendar.MONTH);
		String mes= month<6?"01":"02";
		return year + mes;
	}
}
