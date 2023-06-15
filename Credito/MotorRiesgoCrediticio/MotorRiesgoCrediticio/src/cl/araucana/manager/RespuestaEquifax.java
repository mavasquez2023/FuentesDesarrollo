package cl.araucana.manager;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.equifax.wse.gru01.platinum.PLATINUMOutput;
import cl.equifax.wse.gru01.platinum.output.PLATINUMAntecedentesLaborales;
import cl.equifax.wse.gru01.platinum.output.PLATINUMAntecedentesParticulares;
import cl.equifax.wse.gru01.platinum.output.PLATINUMIdentificacionEmpresa;
import cl.equifax.wse.gru01.platinum.output.PLATINUMIdentificacionPersona;
import cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadorAcreditacion;
import cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadorRiesgoCrediticio;
import cl.equifax.wse.gru01.platinum.output.PLATINUMIndicadoresPrevencionFraude;
import cl.equifax.wse.gru01.platinum.output.PLATINUMJustificacion;
import cl.equifax.wse.gru01.platinum.output.PLATINUMRegistroMorosidadesYProtestos;
import cl.equifax.wse.gru01.platinum.output.PLATINUMResumen;
import cl.equifax.wse.gru01.platinum.output.PLATINUMResumenMorosidadesYProtestosImpagos;
import cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo;
import cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBED;
import cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLAB;
import cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadBOLCOM;
import cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMMorosidadICOM;
import cl.equifax.wse.gru01.platinum.output.sub09.PLATINUMPorMercado;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.RespuestaEquifaxDAO;
import cse.database.dao.helper.RespuestaEquifaxHelper;
import cse.database.objects.respuestaequifax.AntecedentesLaborales;
import cse.database.objects.respuestaequifax.AntecedentesParticulares;
import cse.database.objects.respuestaequifax.CuentaCorriente;
import cse.database.objects.respuestaequifax.DetalleEjecutivos;
import cse.database.objects.respuestaequifax.DetalleGenerico;
import cse.database.objects.respuestaequifax.DetalleONP;
import cse.database.objects.respuestaequifax.EscalaPredictor;
import cse.database.objects.respuestaequifax.IdentificacionEmpresa;
import cse.database.objects.respuestaequifax.IdentificacionPersona;
import cse.database.objects.respuestaequifax.IndicadorAcreditacion;
import cse.database.objects.respuestaequifax.IndicadorRiesgoCrediticio;
import cse.database.objects.respuestaequifax.IndicadoresPrevencionFraude;
import cse.database.objects.respuestaequifax.Justificacion;
import cse.database.objects.respuestaequifax.MorosidadBED;
import cse.database.objects.respuestaequifax.MorosidadBOLAB;
import cse.database.objects.respuestaequifax.MorosidadBOLCOM;
import cse.database.objects.respuestaequifax.MorosidadICOM;
import cse.database.objects.respuestaequifax.MorosidadPorMercado;
import cse.database.objects.respuestaequifax.RegistroMorosidadYProtestos;
import cse.database.objects.respuestaequifax.ResumenEquifax;
import cse.database.objects.respuestaequifax.ResumenMorosidad;
import cse.model.util.Params;
import cse.util.FileUtil;
import cse.util.SerializeObjectXml;

public class RespuestaEquifax {
//	private String DIRECTORIO_RESPALDO_XML;
//	private String DIRECTORIO_XML_PROCESADO;
	private static Logger logger = Logger.getLogger(RespuestaEquifax.class.getName());

	public RespuestaEquifax(){
		//loadProperties();
	}
	
	public void procesarRespuestaEquifax(){
		logger.log(Level.INFO, "Inicio del proceso de archivos XML de Equifax");
		//Buscar listado de archivos no procesados
		try {
			String[] archivos = FileUtil.listarArchivosDir(Params.getInstancia().DIRECTORIO_RESPALDO_XML);
			PLATINUMOutput respEquifax = null;
			String idSolicitud = null;
			for (int i = 0; i < archivos.length; i++) {
			//Convertir el XML a Objeto
				try {
					idSolicitud = archivos[i].replaceAll(".xml", "");
					String xml = FileUtil.leerArchivo(Params.getInstancia().DIRECTORIO_RESPALDO_XML, archivos[i]);
					respEquifax = (PLATINUMOutput) SerializeObjectXml.deserializeAxisObject(PLATINUMOutput.class, xml);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al leer archivo Xml: " + idSolicitud, e);
					return;
				}

			RespuestaEquifaxDAO equifaxDao = DAOFactoryImpl.getInstance().getRespuestaEquifaxDAO();
			
			//Almacenar los datos en la base
				try {
					PLATINUMAntecedentesLaborales antLaborales = respEquifax.getAntecedentesLaborales();
					//Ingresar antecedentes laborales
					if(antLaborales!=null){
						AntecedentesLaborales antLaboralesIn = new AntecedentesLaborales();
						antLaboralesIn.setIdSolicitud(idSolicitud);
						antLaboralesIn.setAntecedentes(antLaborales);
						equifaxDao.insertAntecedentesLaborales(antLaboralesIn);
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar antecedentes laborales: " +idSolicitud, e);
				}
				
				try {
					PLATINUMAntecedentesParticulares antParticulares = respEquifax.getAntecedentesParticulares();
					if(antParticulares!=null){
						AntecedentesParticulares antPartIn = new AntecedentesParticulares();
						antPartIn.setIdSolicitud(idSolicitud);
						//Se almacenarán datos tal como vienen
						//antParticulares.setIndicadorGrupoSocioEconomico(RespuestaEquifaxHelper.traducirIndicadorANumero(antParticulares.getIndicadorGrupoSocioEconomico()));
						//antParticulares.setIndicadorPaginaWeb(RespuestaEquifaxHelper.traducirIndicadorANumero(antParticulares.getIndicadorPaginaWeb()));
						antPartIn.setAntecedentes(antParticulares);
						equifaxDao.insertAntecedentesParticulares(antPartIn);
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar ant. particulares: " + idSolicitud, e);
				}
				
				try {
					PLATINUMIdentificacionEmpresa idEmpresa = respEquifax.getIdentificacionEmpresa();
					
					if(idEmpresa!=null){
						IdentificacionEmpresa idEmpresaIn = new IdentificacionEmpresa();
						idEmpresaIn.setIdSolicitud(idSolicitud);
						idEmpresaIn.setIdEmpresa(idEmpresa);
/*
 						idEmpresa = new PLATINUMIdentificacionEmpresa();
 						idEmpresa.setCodigoActividadEconomica("12312");
						idEmpresa.setDescripcionActividadEconomica("comida");
						idEmpresa.setRazonSocial("sushitumadre");
					
 						PLATINUMDetalleActividadEconomica[] actividades = new PLATINUMDetalleActividadEconomica[3];
						PLATINUMDetalleActividadEconomica actEcon = new PLATINUMDetalleActividadEconomica();
						actEcon.setCodigo("123");
						actEcon.setDescripcion("asdasd");
						actividades[0] = actEcon;
						actividades[1] = actEcon;
						actividades[2] = actEcon;
						idEmpresa.setDetalleActividadesEconomicas(actividades);*/
						
						String id = equifaxDao.insertIdentificacionEmpresa(idEmpresaIn);
						
						//Insertar detalle actividad economica asociada a empresa
						if(idEmpresa.getDetalleActividadesEconomicas()!=null){
							
							for (int j = 0; j < idEmpresa.getDetalleActividadesEconomicas().length; j++) {
								//ImprimeUtil.imprimirCampos(idEmpresa.getDetalleActividadesEconomicas()[j]);
								if(idEmpresa.getDetalleActividadesEconomicas()[j]!=null){
									DetalleGenerico detalle = new DetalleGenerico();
									detalle.setId(id);
									detalle.setDetalle(idEmpresa.getDetalleActividadesEconomicas()[j].getDescripcion());
									equifaxDao.insertDetalleActivEconom(detalle);
								}
							}
						}
/*						PLATINUMDetalleEjecutivo[] ejecutivos = new PLATINUMDetalleEjecutivo[2];
						PLATINUMDetalleEjecutivo ejecu = new PLATINUMDetalleEjecutivo();
						ejecu.setCargo("esclavo");
						ejecu.setNombre(null);
						ejecu.setRutDigito("1");
						ejecu.setRutNumero(null);
						ejecutivos[0] =  ejecu;
						ejecutivos[1] = ejecu;
						
						idEmpresa.setDetalleEjecutivos(ejecutivos);*/
						
						if(idEmpresa.getDetalleEjecutivos()!=null){
							for (int j = 0; j < idEmpresa.getDetalleEjecutivos().length; j++) {
								PLATINUMDetalleEjecutivo detEjeAux = idEmpresa.getDetalleEjecutivos()[j];
								if(detEjeAux!=null){
									//ImprimeUtil.imprimirCampos(idEmpresa.getDetalleEjecutivos()[j]);
									DetalleEjecutivos detEje = new DetalleEjecutivos();
									detEje.setIdIdentificacionEmpresa(id);
									detEje.setNombre(detEjeAux.getNombre());
									detEje.setCargo(detEjeAux.getCargo());
									detEje.setRut(detEjeAux.getRutNumero()+"-"+detEjeAux.getRutDigito());
									equifaxDao.insertDetalleEjecutivos(detEje);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar identificación empresa: " + idSolicitud, e);
				}
				
				try {
					PLATINUMIdentificacionPersona idPersona = respEquifax.getIdentificacionPersona();
					if(idPersona!=null){
						IdentificacionPersona idPersonaAux = new IdentificacionPersona();
						idPersonaAux.setIdSolicitud(idSolicitud);
						idPersonaAux.setIdPersona(idPersona);
						equifaxDao.insertIdentificacionPersona(idPersonaAux);
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar identificacion persona: " +idSolicitud, e);
				}
				
				try {
					PLATINUMIndicadorAcreditacion indAcred = respEquifax.getIndicadorAcreditacion();
					if(indAcred!=null){
						//Validar que la fecha no venga en 0000-00-00
						indAcred.setFechaUltimaVerificacionDomicilio(RespuestaEquifaxHelper.validarFechaVacia(indAcred.getFechaUltimaVerificacionDomicilio()));
						//Quitar decimales a valores en otra moneda
						indAcred.setMontoTotalAvaluoFiscal(RespuestaEquifaxHelper.quitarDecimales(indAcred.getMontoTotalAvaluoFiscal()));
						indAcred.setMontoUltimaExportacion(RespuestaEquifaxHelper.quitarDecimales(indAcred.getMontoUltimaExportacion()));
						indAcred.setMontoUltimaImportacion(RespuestaEquifaxHelper.quitarDecimales(indAcred.getMontoUltimaImportacion()));
						
						
						IndicadorAcreditacion indAcredAux = new IndicadorAcreditacion();
						indAcredAux.setIdSolicitud(idSolicitud);
						indAcredAux.setIndAcred(indAcred);
						String idIndAcred = equifaxDao.insertIndAcreditacion(indAcredAux);
						
/*						PLATINUMNombreBanco[] bancos = new PLATINUMNombreBanco[1];
						PLATINUMNombreBanco banco = new PLATINUMNombreBanco();
						banco.setNombre("SANTANDER");
						bancos[0] = banco;
						indAcred.setNombresBancos(bancos);
						*/
						//Ingresar lista de bancos asociados
						if(indAcred.getNombresBancos()!=null){
							for (int j = 0; j < indAcred.getNombresBancos().length; j++) {
								DetalleGenerico detalle = new DetalleGenerico();
								detalle.setId(idIndAcred);
								detalle.setDetalle(indAcred.getNombresBancos()[j].getNombre());
								equifaxDao.insertBanco(detalle);
							}
						}
						
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar indicador de acreditación: " + idSolicitud, e);
				}
				
				try {
					if(respEquifax.getIndicadorRiesgoCrediticio()!=null){
						PLATINUMIndicadorRiesgoCrediticio indRiesgo = respEquifax.getIndicadorRiesgoCrediticio();
						IndicadorRiesgoCrediticio indRiesgoAux = new IndicadorRiesgoCrediticio();
						indRiesgoAux.setIdSolicitud(idSolicitud);
						indRiesgoAux.setRiesgoCred(indRiesgo);
						
						String idIndRiesgo = equifaxDao.insertIndRiesgoCrediticio(indRiesgoAux);
						
						//Insertar aspectos relevantes y escala predictor
						if(indRiesgo.getAspectosRelevante()!=null){
							for (int j = 0; j < indRiesgo.getAspectosRelevante().length; j++) {
								DetalleGenerico detalle = new DetalleGenerico();
								detalle.setId(idIndRiesgo);
								detalle.setDetalle(indRiesgo.getAspectosRelevante()[j].getDescripcion());
								equifaxDao.insertAspectosRelevantes(detalle);
							}
						}
						if(indRiesgo.getEscalaPredictores()!=null){
							for (int j = 0; j < indRiesgo.getEscalaPredictores().length; j++) {
								EscalaPredictor escala = new EscalaPredictor();
								escala.setId(idIndRiesgo);
								escala.setEscalaPred(indRiesgo.getEscalaPredictores()[j]);
								equifaxDao.insertEscalaPredictor(escala);
							}
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar indicador de riesgo crediticio: " + idSolicitud, e);
				}
				
				
				try {
					PLATINUMIndicadoresPrevencionFraude indPrevFraude = respEquifax.getIndicadoresPrevencionFraude();
					if(indPrevFraude!=null){
						/*indPrevFraude.setFechaDefuncion(RespuestaEquifaxHelper.validarFechaVacia(indPrevFraude.getFechaDefuncion()));
						indPrevFraude.setFechaInterdicto(RespuestaEquifaxHelper.validarFechaVacia(indPrevFraude.getFechaInterdicto()));
						indPrevFraude.setFechaUltimoBoletinLaboral(RespuestaEquifaxHelper.validarFechaVacia(indPrevFraude.getFechaUltimoBoletinLaboral()));
						indPrevFraude.setIndicadorImpedidoAbrirCuentaCorriente(RespuestaEquifaxHelper.convertBoolToInt(indPrevFraude.getIndicadorImpedidoAbrirCuentaCorriente()));
						indPrevFraude.setIndicadorPersonaDifunta(RespuestaEquifaxHelper.convertBoolToInt(indPrevFraude.getIndicadorPersonaDifunta()));
						indPrevFraude.setIndicadorTieneDeudaMorosaVencidaCastigada(RespuestaEquifaxHelper.convertBoolToInt(indPrevFraude.getIndicadorTieneDeudaMorosaVencidaCastigada()));
						indPrevFraude.setIndicadorVerificacionIdentidad(RespuestaEquifaxHelper.convertBoolToInt(indPrevFraude.getIndicadorVerificacionIdentidad()));*/
						
						IndicadoresPrevencionFraude indPrevFraAux = new IndicadoresPrevencionFraude();
						indPrevFraAux.setIdSolicitud(idSolicitud);
						indPrevFraAux.setIndPrevFraude(indPrevFraude);
						String idIndPrevFrau = equifaxDao.insertIndicadoresPrevencionFraude(indPrevFraAux);
						
						//Ingresar cuentas corrientes cerradas y detalle ONP
						if(indPrevFraude.getCuentasCorrientesCerradas()!=null){
							for (int j = 0; j < indPrevFraude.getCuentasCorrientesCerradas().length; j++) {
								CuentaCorriente ctaCte = new CuentaCorriente();
								ctaCte.setId(idIndPrevFrau);
								ctaCte.setCuenta(indPrevFraude.getCuentasCorrientesCerradas()[j]);
								equifaxDao.insertCuentaCorrienteCerrada(ctaCte);
								
							}
						}
						if(indPrevFraude.getDetalleONPs()!=null){
							for (int j = 0; j < indPrevFraude.getDetalleONPs().length; j++) {
								DetalleONP detOnp = new DetalleONP();
								detOnp.setId(idIndPrevFrau);
								detOnp.setDetalleONP(indPrevFraude.getDetalleONPs()[j]);
								equifaxDao.insertDetalleONP(detOnp);
							}
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar indicadores Prev. de fraude: " + idSolicitud, e);
				}
				
				try {
					PLATINUMJustificacion justif = respEquifax.getJustificacion();
					if(justif!=null){
						justif.setFechaJustificacion(RespuestaEquifaxHelper.validarFechaHoraVacia(justif.getFechaJustificacion()));
						//Valida si la fecha esta vacía
						if(justif.getFechaJustificacion()!=null){
							Justificacion justifAux = new Justificacion();
							justifAux.setIdSolicitud(idSolicitud);
							justifAux.setJustif(justif);
							equifaxDao.insertJustificacion(justifAux);
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar justificación: "+ idSolicitud, e);
				}
				
				try {
					PLATINUMResumen resEqui = respEquifax.getResumen();
					if(resEqui!=null){
						/*resEqui.setFechaInformeAlmacenado(RespuestaEquifaxHelper.validarFechaHoraVacia(resEqui.getFechaInformeAlmacenado()));
						resEqui.setFechaMotivoEstadoCedula(RespuestaEquifaxHelper.validarFechaHoraVacia(resEqui.getFechaMotivoEstadoCedula()));
						resEqui.setFechaVencimientoCedulaVigenteAportadaPorSRCel(RespuestaEquifaxHelper.validarFechaHoraVacia(resEqui.getFechaVencimientoCedulaVigenteAportadaPorSRCel()));*/
						resEqui.setMontoDeudaVigente(RespuestaEquifaxHelper.quitarDecimales(resEqui.getMontoDeudaVigente()));
						resEqui.setMontoImpagos(RespuestaEquifaxHelper.quitarDecimales(resEqui.getMontoImpagos()));
						
						//ImprimeUtil.imprimirCampos(resEqui);
						ResumenEquifax resumEquiAux = new ResumenEquifax();
						resumEquiAux.setIdSolicitud(idSolicitud);
						resumEquiAux.setResumen(resEqui);
						equifaxDao.insertResumenEquifax(resumEquiAux);
					}
					
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar resumen: " + idSolicitud, e);
				}
				
				try {
					PLATINUMResumenMorosidadesYProtestosImpagos resMoros = respEquifax.getResumenMorosidadesYProtestosImpagos();
					if(resMoros!=null){
						//resMoros.setFechaVencimientoUltimoImpago(RespuestaEquifaxHelper.validarFechaVacia(resMoros.getFechaVencimientoUltimoImpago()));
						
						resMoros.setMontoDocumentos12A24Meses(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoDocumentos12A24Meses()));
						resMoros.setMontoDocumentos6A12Meses(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoDocumentos6A12Meses()));
						resMoros.setMontoDocumentosMasDe24Meses(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoDocumentosMasDe24Meses()));
						resMoros.setMontoDocumentosUltimos6Meses(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoDocumentosUltimos6Meses()));
						resMoros.setMontoTotalImpagos(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoTotalImpagos()));
						resMoros.setMontoUltimoImpago(RespuestaEquifaxHelper.quitarDecimales(resMoros.getMontoUltimoImpago()));
						
						ResumenMorosidad resMorosAux = new ResumenMorosidad();
						resMorosAux.setIdSolicitud(idSolicitud);
						resMorosAux.setResMoro(resMoros);
						equifaxDao.insertResumenMorosidad(resMorosAux);
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar resumen morosidad y protestos: " + idSolicitud, e);
				}
				
				try {
					PLATINUMRegistroMorosidadesYProtestos regMoros = respEquifax.getRegistroMorosidadesYProtestos();
					if(regMoros!=null){
						RegistroMorosidadYProtestos regMorosAux = new RegistroMorosidadYProtestos();
						regMoros.setMontoTotalImpago(RespuestaEquifaxHelper.quitarDecimales(regMoros.getMontoTotalImpago()));
						
						regMorosAux.setIdSolicitud(idSolicitud);
						regMorosAux.setRegMoros(regMoros);
						String idRegistroMorosidad= equifaxDao.insertRegistroMorosidad(regMorosAux);
						
						if(regMoros.getMorosidadesBED()!=null){
							for (int j = 0; j < regMoros.getMorosidadesBED().length; j++) {
								PLATINUMMorosidadBED regBed = regMoros.getMorosidadesBED()[j];
								//regBed.setFechaIngresoEFX(RespuestaEquifaxHelper.validarFechaVacia(regBed.getFechaIngresoEFX()));
								//regBed.setFechaVencimiento(RespuestaEquifaxHelper.validarFechaVacia(regBed.getFechaVencimiento()));
								//regBed.setMontoImpago(RespuestaEquifaxHelper.quitarDecimales(regBed.getMontoImpago()));
								MorosidadBED moroBED = new MorosidadBED();
								moroBED.setIdRegistroMorosidad(idRegistroMorosidad);
								moroBED.setMorosBED(regBed);
								equifaxDao.insertMorosidadBED(moroBED);
							}
						}
						if(regMoros.getMorosidadesBOLAB()!=null){
							for (int j = 0; j < regMoros.getMorosidadesBOLAB().length; j++) {
								PLATINUMMorosidadBOLAB regBolab = regMoros.getMorosidadesBOLAB()[j];
								//regBolab.setFechaBoletin(RespuestaEquifaxHelper.validarFechaVacia(regBolab.getFechaBoletin()));
								MorosidadBOLAB moroBolab = new MorosidadBOLAB();
								moroBolab.setMorosBolab(regBolab);
								equifaxDao.insertMorosidadBOLAB(moroBolab);
							}							
						}
						if(regMoros.getMorosidadesBOLCOM()!=null){
							for (int j = 0; j < regMoros.getMorosidadesBOLCOM().length; j++) {
								PLATINUMMorosidadBOLCOM regBolcom = regMoros.getMorosidadesBOLCOM()[j];
								//regBolcom.setFechaVencimiento(RespuestaEquifaxHelper.validarFechaVacia(regBolcom.getFechaVencimiento()));
								//regBolcom.setFechaIngreso(RespuestaEquifaxHelper.validarFechaVacia(regBolcom.getFechaIngreso()));
								//regBolcom.setMontoImpago(RespuestaEquifaxHelper.quitarDecimales(regBolcom.getMontoImpago()));
								
								MorosidadBOLCOM moroBolcom = new MorosidadBOLCOM();
								moroBolcom.setIdRegistroMorosidad(idRegistroMorosidad);
								moroBolcom.setMorosBolcom(regBolcom);
								equifaxDao.insertMorosidadBOLCOM(moroBolcom);
							}							
						}
						if(regMoros.getMorosidadesICOM()!=null){
							for (int j = 0; j < regMoros.getMorosidadesICOM().length; j++) {
								PLATINUMMorosidadICOM regIcom = regMoros.getMorosidadesICOM()[j];
								//regIcom.setFechaVencimiento(RespuestaEquifaxHelper.validarFechaVacia(regIcom.getFechaVencimiento()));
								//regIcom.setMontoImpago(RespuestaEquifaxHelper.quitarDecimales(regIcom.getMontoImpago()));
								MorosidadICOM moroIcom = new MorosidadICOM();
								moroIcom.setIdRegistroMorosidad(idRegistroMorosidad);
								moroIcom.setMorosIcom(regIcom);
								equifaxDao.insertMorosidadIcom(moroIcom);
							}							
						}
						if(regMoros.getPorMercados()!=null){
							for (int j = 0; j < regMoros.getPorMercados().length; j++) {
								PLATINUMPorMercado regPorMercado = regMoros.getPorMercados()[j];
								//regPorMercado.setMontoImpagos(RespuestaEquifaxHelper.quitarDecimales(regPorMercado.getMontoImpagos()));
								MorosidadPorMercado moroPorMercado = new MorosidadPorMercado();
								moroPorMercado.setIdRegistroMorosidad(idRegistroMorosidad);
								moroPorMercado.setMoroPorMercado(regPorMercado);
								equifaxDao.insertMorosidadPorMercado(moroPorMercado);
							}							
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Error al ingresar morosidad y protestos: " + idSolicitud, e);
				}
				//Mover archivo a carpeta procesados
				FileUtil.moverArchivo(Params.getInstancia().DIRECTORIO_RESPALDO_XML, Params.getInstancia().DIRECTORIO_XML_PROCESADO, archivos[i]);
			}//Fin for
			logger.log(Level.INFO,archivos.length + " archivos procesados");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error al leer directorio de archivos de entrada", e);
		}
	}
	
/*	protected void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("equifax.properties");
		DIRECTORIO_RESPALDO_XML = prop.getProperty("DIRECTORIO_RESPALDO_XML");
		DIRECTORIO_XML_PROCESADO = prop.getProperty("DIRECTORIO_XML_PROCESADO");
	}*/
	
	public static void main(String[] args) {
		RespuestaEquifax mgr = new RespuestaEquifax();
		mgr.procesarRespuestaEquifax();
	}
}
