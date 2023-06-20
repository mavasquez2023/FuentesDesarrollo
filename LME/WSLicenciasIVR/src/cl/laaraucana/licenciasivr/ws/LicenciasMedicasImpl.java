package cl.laaraucana.licenciasivr.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;
import cl.araucana.core.util.Rut;
import cl.laaraucana.licenciasivr.ibatis.dao.AdministracionDAO;
import cl.laaraucana.licenciasivr.ibatis.dao.BitacoraDAO;
import cl.laaraucana.licenciasivr.ibatis.dao.LicenciaDAO;
import cl.laaraucana.licenciasivr.services.EmpresaService;
import cl.laaraucana.licenciasivr.services.EmpresaServiceImpl;
import cl.laaraucana.licenciasivr.utils.Utils;
import cl.laaraucana.licenciasivr.vo.ConsultaVO;
import cl.laaraucana.licenciasivr.vo.EmpresaVO;
import cl.laaraucana.licenciasivr.vo.LicenciaVO;


/** 
 * @version 	1.0
 * @author Claudio Lillo
 *  
 */

@WebService( portName = "LicenciasMedicasPort",
			serviceName = "LicenciasMedicasService",
			targetNamespace = "http://servicios.laaraucana.cl/licenciasMedicas"
)

public class LicenciasMedicasImpl implements LicenciasMedicas{
	
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private boolean isValidParams(String rutparam, String fecha){
		int rut= Integer.parseInt(rutparam.split("-")[0]);
		String dv= rutparam.split("-")[1];
		Rut rut_compose= new Rut(rut);
		if(rutparam==null || rutparam.equals("") || rutparam.length()>10 || rut_compose.getDV()!=dv.charAt(0)){
			return false;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(fecha));
			log.info("fecha: " + calendar.getTime());
		} catch(ParseException e) {
			return false;
		} 
		return true;
	}
	
	
	private Integer convierteFecha(String fecha) throws Exception {
		String[] fec = fecha.split("-");
		Integer fechaInt = Integer.parseInt(fec[2]+fec[1]+fec[0]);
		return fechaInt;
	}
	
	
	@WebMethod(action="http://servicios.laaraucana.cl/ivr/licenciasMedicas",  operationName="findLicencias")
	public ResponseWS findLicencias(@WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException{
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("En findLicencias IVR");
		ResponseWS responseWS=new ResponseWS();
		String usuario="";
		try {			
			usuario= request.getUsuario();
			String password= request.getPassword();			
			if(usuario==null || usuario.equals("") || password==null || password.equals("")){
				responseWS.setCodigoRespuesta("3");
			}else{
				AdministracionDAO adminDao= new AdministracionDAO();
				Map dataUsuario= adminDao.validaUsuarioWS(request.getUsuario());
				if(dataUsuario==null || !dataUsuario.get("password").toString().equals(password) || (Integer)dataUsuario.get("idCanal")!=3){
					responseWS.setCodigoRespuesta("3");
					log.warn("Usuario o contraseña no válido:" + usuario);
				}else{
					String origenConsulta = dataUsuario.get("nombre").toString();
					log.info("Origen consulta: " + origenConsulta);
					//Validando datos de entrada
					if(isValidParams(request.getRut(), request.getFecha())){
						log.info("Obteniendo licencias para rut:" + request.getRut());
						int rut= Integer.parseInt(request.getRut().split("-")[0]);
						String dv= request.getRut().split("-")[1];
						String fecha = request.getFecha();
						ConsultaVO consulta = new ConsultaVO();
						Integer fechaDesde = convierteFecha(fecha);
						log.info("fecha int: " + fechaDesde.toString());
						consulta.setRut(rut);
						consulta.setFecha(fechaDesde);
						log.info("fecha obtenida: " + request.getFecha());
						LicenciaDAO licDao= new LicenciaDAO();
						List<LicenciaVO> licenciasVO= licDao.getLicencias(consulta);
						log.info("Número licencias encontradas: " + licenciasVO.size());
						if(licenciasVO.size()>0){
							List<LicenciaWS> licenciasWS= new ArrayList<LicenciaWS>();
							List<String> comparator= new ArrayList<String>();
							for (Iterator<LicenciaVO> iterator = licenciasVO.iterator(); iterator
									.hasNext();) {
								LicenciaVO licenciaVO = (LicenciaVO) iterator
										.next();
								LicenciaWS licenciaWS= new LicenciaWS();
								licenciaWS.setDias(licenciaVO.getDias());
								licenciaWS.setEstado(licenciaVO.getEstado());
								licenciaWS.setFechaInicio(Utils.dateToStringAMD(licenciaVO.getFechaInicio()));
								licenciaWS.setFechaPago(licenciaVO.getFechaPago());
								licenciaWS.setFechaTermino(Utils.dateToStringAMD(licenciaVO.getFechaTermino()));
								licenciaWS.setNumeroLicencia(licenciaVO.getNumeroLicencia());
								licenciaWS.setRazonSocial(licenciaVO.getRazonSocial());
								licenciaWS.setTipoSubsidio(licenciaVO.getTipoSubsidio());
								licenciaWS.setMontoSubsidio(licenciaVO.getMontoSubsidio());
								licenciaWS.setRutEmpresa(licenciaVO.getRutEmpresa());
								String fechasit= licenciaWS.getFechaInicio() + licenciaWS.getFechaTermino();
								if(!comparator.contains(fechasit)) {
									licenciasWS.add(licenciaWS);
									comparator.add(fechasit);
								}								
							}
							List<LicenciaWS> licenciasWSEstado = completaEstado(licenciasWS, rut);
							responseWS.setLicencias(licenciasWSEstado);
							responseWS.setCodigoRespuesta("1");
						}else{
							//sin resultados
							responseWS.setCodigoRespuesta("2");
						}
						//Insertando bitácora
						Map<String, String> bita_param= new HashMap<String, String>();
						bita_param.put("rut", String.valueOf(rut));
						bita_param.put("dv", dv );
						bita_param.put("fecha", Utils.fechaSAP());
						bita_param.put("hora", Utils.getHora());
						bita_param.put("OrigenConsulta", origenConsulta);
						log.info("Insertando bitácora");
						BitacoraDAO.insertBitacora(bita_param);
					}else{
						responseWS.setCodigoRespuesta("3");
						log.warn("Rut no válido");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			responseWS.setCodigoRespuesta("3");
		}
		return responseWS;
	}
	

	private List<LicenciaWS> completaEstado(List<LicenciaWS> licenciasIN, int rutAfiliado) throws Exception {
		List<LicenciaWS> licenciasOUT = new ArrayList<LicenciaWS>();
		for(LicenciaWS licencia : licenciasIN) {
			LicenciaWS newLicencia = new LicenciaWS();
			String numeroLicencia = licencia.getNumeroLicencia();
			newLicencia.setNumeroLicencia(numeroLicencia);
			newLicencia.setDias(licencia.getDias());
			newLicencia.setFechaInicio(licencia.getFechaInicio());
			newLicencia.setFechaPago(licencia.getFechaPago());
			newLicencia.setFechaTermino(licencia.getFechaTermino());
			newLicencia.setMontoSubsidio(licencia.getMontoSubsidio());
			newLicencia.setRazonSocial(licencia.getRazonSocial());
			newLicencia.setRutEmpresa(licencia.getRutEmpresa());
			newLicencia.setTipoSubsidio(licencia.getTipoSubsidio());
			licenciasOUT.add(newLicencia);
			String estadoInicial = licencia.getEstado();
			String estadoFinal = "";
			if(estadoInicial.equals("-1")) {
				log.info("Modificando estado licencia: " + numeroLicencia);
				log.info("Rut Afiliado: " + rutAfiliado);
				EmpresaVO datos = new EmpresaVO();
				datos.setAFIRUT(rutAfiliado);
				datos.setLICIMPNUM(numeroLicencia);
				EmpresaService empresa = new EmpresaServiceImpl();
				datos = empresa.consultaEmpresa(datos);
				String estadoLicencia = datos.getLICEST();
				Integer folioPago = datos.getPAGFOL();
				String licObs = datos.getLICOBS1();
				log.info("estadoLicencia (LICEST): " + estadoLicencia);
				log.info("folioPago (PAGFOL): " + folioPago);
				log.info("licObs: " + licObs);
				if(estadoLicencia.equals("1")) {
					if(folioPago == 0) {
						log.info("PAGFOL=0 -> 6");
						estadoFinal = "6";
					} else if(folioPago > 0) {
						log.info("Folio pago > 0");
						Integer folio = empresa.existeILF3500(folioPago);
						log.info("Folio: " + folio);
						if(null!=folio && folio != 0) {
							log.info("Existe en ILF3500");
							String cod = empresa.existeILF4500A(folio);
							if(null != cod) {
								log.info("Existe en ILF4500A -> ESTCOB: " + cod);
								estadoFinal = cod.equals("C")?"9":"2";
							} else {
								log.info("No existe en ILF4500A");
								String cod2 = empresa.existeTE3YA(folio);
								if(null != cod2) {
									log.info("Existe en TE3YA -> TE3YA: " + cod2);
									estadoFinal = cod2.equals("C")?"9":"2";
								} else {
									log.info("No existe en TE3YA");
									estadoFinal = "2";
								}
							} 
						} else {
							log.info("No existe en ILF3500");
							estadoFinal = "6";
						}
					} else {
						log.info("Error, folio de pago negativo");
						estadoFinal = "error";
					}
				} else if(estadoLicencia.equals("2")) {
					estadoFinal = (licObs.equals("91"))?"5":"6"; 
				} else if(estadoLicencia.equals("3")) {
					estadoFinal = (licObs.equals("6"))?"8":"4"; 
				} else {
					log.info("Error, estado de licencia no válido");
					estadoFinal = "error";
				}
			} else {
				estadoFinal = estadoInicial;
			}
			//log.info("Estado : " + estadoFinal);
			newLicencia.setEstado(estadoFinal);
		}
		return licenciasOUT;
	}
	
}