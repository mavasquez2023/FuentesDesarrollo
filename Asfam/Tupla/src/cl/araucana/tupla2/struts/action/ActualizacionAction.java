package cl.araucana.tupla2.struts.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
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

import cl.araucana.tupla2.struts.bussiness.TO.ActualizaTuplaVO;
import cl.araucana.tupla2.struts.bussiness.TO.ActualizacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramosTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.paperless.siagf.ws.ActualizarCausantePortTypeProxy;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;

public class ActualizacionAction extends Action {
	Araucanajdbcdao dao = null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		ActualizacionTO form1 = (ActualizacionTO) form;
		if (request.getParameter("isForm") == null)
			return mapping.findForward("showForm");

		String mensaje = null;
		ConstruyeXml construye = new ConstruyeXml();
		//CamposXmlTO oCampo = new CamposXmlTO();
		List rechazos = new ArrayList();
		SqlParametersTO oSql = new SqlParametersTO();
		String xml = null;
		String respuesta = null;
		XmlParse parse = new XmlParse();
		RetornoTO oRetorno = new RetornoTO();

		//Archivo de configuracion
		Properties properties = new LoadPropertiesFile().load("wssiagf.properties");

		AutenticacionPortTypeProxy autentica = new AutenticacionPortTypeProxy();
		autentica.setEndpoint(properties.getProperty("webService.Autenticacion"));
		ActualizarCausantePortTypeProxy actualizar = new ActualizarCausantePortTypeProxy(properties.getProperty("webService.ActualizarCausante"));
		ConsultaCausantePortTypeProxy consulta = new ConsultaCausantePortTypeProxy();
		consulta.setEndpoint(properties.getProperty("webService.ConsultaCausante"));

		
		try {
			dao = new Araucanajdbcdao();

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
			if(form1.getMaxperiodo()==0){
				oSql.setMaxperiodo(300001);
			}
		} catch (Exception e) {
			request.setAttribute("mensaje", "No es posible obtener lista de causantes: " + e.getMessage());
			forward = mapping.findForward("onError");
			return forward;
		}

		int succefull = 0;
		int totalcasos=0;
		String opciones="" ;
		String acciones="";
		String codtcau_Invalidos= ",5,8,11,20,27,";
			try {
				if(oSql.getActualizar()!=null){
					for (int i = 0; i < oSql.getActualizar().length; i++) {
						opciones+= "," + oSql.getActualizar()[i];
					}
				}
				if(oSql.getAccion()!=null){
					for (int i = 0; i < oSql.getAccion().length; i++) {
						acciones+= "," + oSql.getAccion()[i];
					}
				}
				xml = null;

				//Consulta casos
				rechazos= dao.selectedTramosRechazo(oSql, opciones, acciones);
				totalcasos= rechazos.size();
				if(rechazos.size()>0){
					if (acciones.indexOf("SV")>-1){
						dao.insertASF011(oSql);
						dao.insertASF012(oSql);
						//Inserta casos sin marca
						dao.insertASF011SinMarca(oSql);
						dao.insertASF012SinMarca(oSql);
					}
					Map<String, TramosTO> tramos= dao.getTramosAsfam();
					List<String> listasiagf= new ArrayList<String>();
					List<CamposXmlTO> listaRegistros= new ArrayList<CamposXmlTO>();
					CamposXmlTO oCampo=null;
					String token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
					String parsetoken = parse.parsearXMLautentica(token);
					for (Iterator iterator = rechazos.iterator(); iterator.hasNext();) {

						try {
							//El menor numero de tramo ya viene definido en dao
							//En base a este tramo se recupera el valor del tramo
							oCampo = (CamposXmlTO) iterator.next();
							
							oCampo.setValorTramo(String.valueOf(tramos.get(oCampo.getPeriodo()+oCampo.getTramoAsigFam()).getValor()));

							//Se rescata la renta promedio y se valida que esté entre el rango del tramo, sino se retorna el máximo valor del tramo
							int renta= getRentaCorrectaTramo(oCampo, tramos);
							oCampo.setIngPromedio(String.valueOf(renta));

							//Se calcula la diferencia entre el Monto informado y el valor del tramo
							if(opciones.indexOf("M")>-1){
								int diferencia=0;
								if(codtcau_Invalidos.indexOf(","+oCampo.getCodtipocausante()+",")>-1){
									diferencia= Integer.parseInt(oCampo.getMontoUnitarioBeneficio()) - Integer.parseInt(oCampo.getValorTramo())*2;
								}else{
									diferencia= Integer.parseInt(oCampo.getMontoUnitarioBeneficio()) - Integer.parseInt(oCampo.getValorTramo());
								}
								 
								oCampo.setDiferencia(diferencia);
							}
							int codigoExito=0;	
							if(oCampo.isActualizaSIAGF() && acciones.indexOf("SF")>-1){
								if(!listasiagf.contains(oCampo.getRutCausante() + oCampo.getPeriodo())){
									//Autentificacion con WS
									
									//Creacion XML a enviar por WS
									xml = construye.creaXmlActualizacion(oCampo);

									//Actualiza causante con WS y guardar en DB2 la respuesta
									int intentos=0;
									do {
										if(oRetorno.getCodigo().equals("-6")){
											token = autentica.login(new Integer(properties.getProperty("credential.id").trim()), properties.getProperty("credential.userName"), properties.getProperty("credential.password"));
											parsetoken = parse.parsearXMLautentica(token);
											intentos++;
										}
										respuesta = actualizar.actualizarCausante(parsetoken, xml);
										//System.out.println("[Actualizar]" + i + ".-XML de respuesta:" + respuesta);
										oRetorno = parse.parsearXMLRetorno(respuesta);
										oRetorno.setId(oCampo.getId());
										oRetorno.setCodigo(oRetorno.getCodigo());
										oRetorno.setMensaje(oRetorno.getMensaje());
										oRetorno.setCodigoxml(xml);
										oRetorno.setIdtupla(oCampo.getTupla());
									} while (oRetorno.getCodigo().equals("-6") || intentos >3);
										
									codigoExito= Integer.parseInt(oRetorno.getCodigo());
									if (!dao.saveRetornoActualiza(oRetorno, oSql)) {
										errors.add("name", new ActionMessage("id"));
										mensaje = "Error al grabar datos enviados del siagf";
									}
									//System.out.println("Código retorno SIAGF=" + oRetorno.getCodigo());
									listasiagf.add(oCampo.getRutCausante() + oCampo.getPeriodo());
								}else{
									oRetorno = new RetornoTO();
									oRetorno.setId(oCampo.getId());
									oRetorno.setCodigo("02");
									oRetorno.setMensaje("Periodo ya actualizado en SIAGF");
									oRetorno.setIdtupla(oCampo.getTupla());
									dao.saveRetornoActualiza(oRetorno, oSql);
								}
							}else if (!oCampo.isActualizaSIAGF() && acciones.indexOf("SF")>-1){
								oRetorno = new RetornoTO();
								oRetorno.setId(oCampo.getId());
								oRetorno.setCodigo("01");
								oRetorno.setMensaje("No corresponde Actualizar SIAGF");
								oRetorno.setIdtupla(oCampo.getTupla());
								dao.saveRetornoActualiza(oRetorno, oSql);
							}
							if(codigoExito==0){
								//Se actualiza tabla 11 o 12
								if (acciones.indexOf("SV")>-1){
									listaRegistros.add(oCampo);
									//dao.update011_012(oCampo, oSql);
								}
								//Si hay diferencia entre informado y valor tramo se guarda en tabla MarcaRechazo
								//dao.updateDiferencia(oCampo, oSql);
								succefull++;
								if(succefull%1000==0){
									System.out.println("Actualizado " + succefull + " de " + totalcasos);
									if (acciones.indexOf("SV")>-1){
										int res11= dao.update011_012(listaRegistros, oSql, "11");
										System.out.println("registros actualizados en la 11: " + res11);
										int res12= dao.update011_012(listaRegistros, oSql, "12");
										System.out.println("registros actualizados en la 12: " + res12);
									
										//Si hay diferencia entre informado y valor tramo se guarda en tabla MarcaRechazo
										int resdif= dao.updateDiferencia(listaRegistros, oSql);
										System.out.println("registros actualizados con diferecnias: " + resdif);
										listaRegistros.clear();
									}
								}
							}else{
								//se elimina registro de Sivegam
								if (acciones.indexOf("SV")>-1){
									dao.delete011_012(oCampo, oSql);
								}
							}
						} catch (Exception ex) {
							System.out.println("Error en id " + oCampo.getId() + ", mensaje= " + ex.getMessage());
							oRetorno = new RetornoTO();
							oRetorno.setId(oCampo.getId());
							oRetorno.setCodigoxml(xml);
							oRetorno.setMensaje("Exception:" + ex.getMessage());
							oRetorno.setCodigo("-99");
							if (!dao.saveRetornoActualiza(oRetorno, oSql)) {
								errors.add("name", new ActionMessage("id"));
								mensaje = "Error al grabar datos de retorno";
							}
							ex.printStackTrace();
						}
					}
					//Se inserta el resto de los registros
					if (acciones.indexOf("SV")>-1){
						
						System.out.println("Actualizado " + succefull + " de " + totalcasos);
						int res11= dao.update011_012(listaRegistros, oSql, oSql.getTabla011());
						System.out.println("registros actualizados en la 11: " + res11);
						int res12= dao.update011_012(listaRegistros, oSql, oSql.getTabla012());
						System.out.println("registros actualizados en la 12: " + res12);
						//Si hay diferencia entre informado y valor tramo se guarda en tabla MarcaRechazo
						int resdif= dao.updateDiferencia(listaRegistros, oSql);
						System.out.println("registros actualizados con diferecnias: " + resdif);
						listaRegistros.clear();
					}
				}
				
				//xml= leerArchivo("C:/SIAGF/Actualiza/21243322-5.xml");

			} catch (Exception ex) {
				
				errors.add("name", new ActionMessage("id"));
				mensaje = "Error al grabar datos de actualizacion";
				
				ex.printStackTrace();
			}

		request.setAttribute("mensaje", "Registros procesados: " + succefull + " de " + rechazos.size());
		forward = mapping.findForward("onSuccess");
		return forward;
	}
	
	public String leerArchivo(String pathfile) {
		BufferedReader f1;
		String buf = "";
		String texto="";
		try {
			//leyendo archivo, usando BufferedReader
			f1 = new BufferedReader(new FileReader(pathfile));
			while ((buf = f1.readLine()) != null) {
					texto+= buf +"\n";
			}
			f1.close();
		} catch (Exception e) {
			e.printStackTrace();
			//retorno.removeAllElements();
		}
		return texto;
	}
	
	public int getRentaCorrectaTramo(CamposXmlTO oCampo , Map<String, TramosTO> tramos){
		int renta= 0;
		try {
			//int rentaPromedio= dao.getRentaPromedio(Integer.parseInt(oCampo.getRutBeneficiario().split("-")[0]), tramos.get(oCampo.getPeriodo()+oCampo.getTramoAsigFam()).getFechaTramo());
			int rentaPromedio= Integer.parseInt(oCampo.getIngPromedio());
			TramosTO tramo= tramos.get(oCampo.getPeriodo()+oCampo.getTramoAsigFam());
			if(rentaPromedio>=tramo.getRentaMinima() && rentaPromedio<=tramo.getRentaMaxima()){
				renta= rentaPromedio;
			}else{
				renta= tramo.getRentaMaxima();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return renta;
	}
}
