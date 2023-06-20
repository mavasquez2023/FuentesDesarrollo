/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BecasDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BecasDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BecasEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficiarioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoBecasVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBecasVo;

import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.lib.export.txt.impl.FileGenerator;
import cl.liv.export.comun.util.file.ManejoArchivos;

/**
 * @author J-Factory
 *
 */
@Service
public class BecasServiceImpl implements BecasService {

	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.BeneficioService#cargarTablaBeneficios()
	 */
	private static final Logger logger = Logger.getLogger(BecasServiceImpl.class);
	
	@Autowired
	private CabeceraService cabeceraService;
	
	@Autowired
	private DetalleService detalleService;
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	BancoService bancoService;
	
	BecasDao becasDAO= new BecasDaoImpl();
	
	BeneficioDao beneficioDAO= new BeneficioDaoImpl();
	
	private ConvenioDao convenioDAO= new ConvenioDaoImpl();
	
	@Override
	public List<ResumenCargaPagoBecasVo> leerTablaBecas(String banco) throws Exception {
		int maximoNomina=99999;
		
		logger.info("Buscando lista de becas estado 2 y 4 banco " + banco);
		
		List<BecasEntity> becados=null;
		if(banco.equals("BES")){
			becados= becasDAO.consultaBecadosBES();
		}
		
		logger.info("Total registros encontrados para " + banco + ": " + becados.size());
		List<BecasEntity> listaNomina= new ArrayList<BecasEntity>();
		List<ResumenCargaPagoBecasVo> listaResumen= new ArrayList<ResumenCargaPagoBecasVo>();
		
		String convenio_old="";
		String producto_old="";
		
		for (Iterator iterator = becados.iterator(); iterator.hasNext();) {
			BecasEntity becasEntity = (BecasEntity) iterator
					.next();
			if((!becasEntity.getCodigoConvenio().trim().equals(convenio_old) || !becasEntity.getCodigoProducto().trim().equals(producto_old) || listaNomina.size()== maximoNomina) && !convenio_old.equals("")){
				if(listaNomina.size()>0){
					logger.info("Cargado Convenio: " + convenio_old + ", Producto: " + producto_old + ", total registros nomina: " + listaNomina.size());
					ResumenCargaPagoBecasVo respuesta= new ResumenCargaPagoBecasVo();
					
					respuesta.setConvenio(convenio_old);
					respuesta.setProducto(producto_old);
					respuesta.setListaNomina(listaNomina);
					listaResumen.add(respuesta);
					listaNomina= new ArrayList<BecasEntity>();
				}
			}
			
			listaNomina.add(becasEntity);
			convenio_old= becasEntity.getCodigoConvenio().trim();
			producto_old= becasEntity.getCodigoProducto().trim();
			
		}
		//Cargando último lote
		if(listaNomina.size()>0){
			logger.info("Cargado Convenio: " + convenio_old + ", Producto: " + producto_old + ", total registros: " + listaNomina.size());
			ResumenCargaPagoBecasVo respuesta= new ResumenCargaPagoBecasVo();
			respuesta.setCantidadRegistros(0);
			respuesta.setMontoNomina(0);
			respuesta.setConvenio(convenio_old);
			respuesta.setProducto(producto_old);
			respuesta.setListaNomina(listaNomina);
			listaResumen.add(respuesta);
		}
		return listaResumen;
	}
	@Override
	public void cargarDatosmandato(List<ResumenCargaPagoBecasVo> listaResumen) throws Exception{
		
		for (Iterator iterator = listaResumen.iterator(); iterator.hasNext();) {
			ResumenCargaPagoBecasVo resumenCargaPagoBecasVo = (ResumenCargaPagoBecasVo) iterator
					.next();
			List<BecasEntity> listaDetalle= resumenCargaPagoBecasVo.getListaNomina();
			List<BecasEntity> listaDetalleFinal= new ArrayList<BecasEntity>();
			int count=0;
			for (Iterator iterator2 = listaDetalle.iterator(); iterator2
					.hasNext();) {
				BecasEntity detallePagoBecasVo = (BecasEntity) iterator2
						.next();
				boolean agregar= true;
				if(detallePagoBecasVo.getFormaPago().equals("TRANS")){
					int rutAfiliado= detallePagoBecasVo.getRutAfiliado();
					List<MandatoAS400Vo> listMandatoAfil= mandatoService.consultaMandatos(rutAfiliado);
					if(listMandatoAfil.size()==0){
						detallePagoBecasVo.setIdBanco(0);
						detallePagoBecasVo.setNumCuenta("");
						detallePagoBecasVo.setIdTipoCuenta(99);
						detallePagoBecasVo.setEmail("");
						
						if(detallePagoBecasVo.getFechaEnvio()==null){
							detallePagoBecasVo.setFormaPago("CASH");
						}else if(detallePagoBecasVo.getDiasEnvio()>2){
							detallePagoBecasVo.setFormaPago("CASH");
						}else{
							agregar=false;
						}
						
					}else{
						MandatoAS400Vo mandato= listMandatoAfil.get(0);
						detallePagoBecasVo.setIdBanco(mandato.getCodbanco());
						detallePagoBecasVo.setNumCuenta(mandato.getNumcuenta().trim());
						detallePagoBecasVo.setIdTipoCuenta(mandato.getIdtipcta());
						detallePagoBecasVo.setEmail(mandato.getEmail().trim());
					}
				}else{
					detallePagoBecasVo.setIdBanco(0);
					detallePagoBecasVo.setNumCuenta("");
					detallePagoBecasVo.setIdTipoCuenta(99);
					detallePagoBecasVo.setEmail("");
				}
				count++;
				if(agregar){
					listaDetalleFinal.add(detallePagoBecasVo);
				}
			}
			resumenCargaPagoBecasVo.setListaNomina(listaDetalleFinal);
		}
	}
	
	@Override
	public int cargarTablasTEF(List<ResumenCargaPagoBecasVo> listaResumen, String banco) throws Exception {
		logger.info("Cargando datos Cabecera y Detalle TEF");

		//Se obtiene mapa de los bancos para poder obtener la descripción
		Map<String, BancoEntity> mapBancos= bancoService.getMapBancos();

		int totalCabeceras=0;
		for (Iterator iterator = listaResumen.iterator(); iterator.hasNext();) {
			ResumenCargaPagoBecasVo resumen = (ResumenCargaPagoBecasVo) iterator
					.next();
			if(resumen.getListaNomina().size()>0){
				try {
					//Se genera cabecera y detalle NominasTef
					CabeceraEntity cabeceratef= new CabeceraEntity();
					cabeceratef.setCantidad(resumen.getCantidadRegistros());
					cabeceratef.setCantidadPagado(0);
					cabeceratef.setCantidadRechazado(0);
					cabeceratef.setCantidadDevuelto(0);
					cabeceratef.setCodigoNomina(0);
					cabeceratef.setCodigoRechazoEnvio(0);
					cabeceratef.setCodigoRechazoRendicion(0);
					cabeceratef.setConvenio(resumen.getConvenio());
					cabeceratef.setEstadoNomina(Estados.NOMINA_PENDIENTE);
					cabeceratef.setFechaEnvio(null);
					cabeceratef.setFechaCreacion(new Date());
					cabeceratef.setFechaRendicion(null);
					cabeceratef.setCodigoNomina(0);
					cabeceratef.setGlosaRechazoEnvio("");
					cabeceratef.setGlosaRechazoRendicion("");
					cabeceratef.setMonto(resumen.getMontoNomina());
					cabeceratef.setNombreNomina(resumen.getNombreArchivo());
					cabeceratef.setPendientes(0);
					cabeceratef.setPlano(null);
					cabeceratef.setProducto(resumen.getProducto());
					cabeceratef.setTotalPagado(0);
					cabeceratef.setTotalRechazado(0);
					cabeceratef.setTotalDevuelto(0);
					cabeceratef.setCrc(null);

					//Se guarda cabecera TEF
					CabeceraEntity cabeceraTEF= cabeceraService.save(cabeceratef);
					logger.info("Se ha grabado cabecera id: " + cabeceraTEF.getIdCabecera());

					//Se obtiene el convenio asociado para obtener banco convenio y ejecutivo 
					ConvenioEntity convenio = convenioDAO.getConvenio(Integer.parseInt(cabeceratef.getConvenio() ) );



					int cantidadRegistros=0;
					long montoNomina=0;
					HashMap<String, String> updateBecados= new HashMap<String, String>();

					for (Iterator iterator2 = resumen.getListaNomina().iterator(); iterator2
							.hasNext();) {
						BecasEntity detalleEntity2 = (BecasEntity) iterator2
								.next();

						try {
							//Se graban parámetros para actualizar becado con nuevo estado una vez cargado
							updateBecados.put("idpago", String.valueOf(detalleEntity2.getIdPago()));
							updateBecados.put("estado", String.valueOf(Estados.PAGO_PROCESO_PAGO));
							updateBecados.put("formapago", detalleEntity2.getFormaPago());
							updateBecados.put("codbanco", String.valueOf(detalleEntity2.getIdBanco()));
							updateBecados.put("idCabecera", String.valueOf(cabeceraTEF.getIdCabecera()));
							String cuenta=detalleEntity2.getNumCuenta();
							if(cuenta.equals("")){
								cuenta="0";
							}
							updateBecados.put("cuenta", cuenta);
							updateBecados.put("tipocuenta",  String.valueOf(detalleEntity2.getIdTipoCuenta()));

							int exito= becasDAO.updateBecado(updateBecados);

							if(exito>0){
								DetalleEntity detalleTEF= new DetalleEntity();
								detalleTEF.setIdCabecera(cabeceraTEF.getIdCabecera());
								detalleTEF.setCodigoBeneficio(detalleEntity2.getCodigoBeneficio());

								detalleTEF.setDescripcionEstadoPago("PENDIENTE_PROCESO");
								detalleTEF.setDescripcionPago(detalleEntity2.getDescripcionPago());
								detalleTEF.setRutAfiliado(detalleEntity2.getRutAfiliado());
								detalleTEF.setDvAfiliado(detalleEntity2.getDvAfiliado());
								detalleTEF.setNombres(detalleEntity2.getNombreAfiliado().replaceAll("ñ", "n").replaceAll("Ñ", "N"));
								detalleTEF.setCodigoBanco(String.valueOf(detalleEntity2.getIdBanco()));
								String descripcionBanco="";
								if(convenio.getCodigoBanco().equals("BES")){
									if(detalleEntity2.getIdBanco()>0){
										descripcionBanco= mapBancos.get(String.valueOf(detalleEntity2.getIdBanco())).getDescripcionBES();
									}
								}
								detalleTEF.setDescripcionBanco(descripcionBanco);
								long numeroCuenta=0L;
								if(!detalleEntity2.getNumCuenta().equals("")){
									numeroCuenta= Long.parseLong(detalleEntity2.getNumCuenta());
								}
								detalleTEF.setNumerocuenta(numeroCuenta);
								detalleTEF.setTipoCuenta(detalleEntity2.getIdTipoCuenta());
								detalleTEF.setEmail(detalleEntity2.getEmail());
								int codforpag=detalleEntity2.getIdTipoCuenta();
								String des_formapago="TRANSFERENCIA";
								detalleTEF.setCodigoFormaPago(String.valueOf(codforpag));
								if(codforpag==99){
									des_formapago="PAGO CASH";
								}
								detalleTEF.setDescripcionFormaPago(des_formapago);
								detalleTEF.setMonto((int)detalleEntity2.getMonto());
								detalleTEF.setEstadoPago(Estados.PAGO_PENDIENTE_PROCESO);
								detalleTEF.setDescripcionEstadoPago("");
								detalleTEF.setReferencia1(detalleEntity2.getReferencia1());
								detalleTEF.setReferencia2(detalleEntity2.getReferencia2());
								detalleTEF.setFechaCambio(new Date());					
								detalleTEF.setIdReferencia(detalleEntity2.getIdPago());
								detalleService.save(detalleTEF);

								montoNomina += detalleTEF.getMonto();
								cantidadRegistros++;
							}
						} catch (Exception e) {
							logger.warn("Beneficiario " + detalleEntity2.getRutAfiliado() + " se excluye por error al grabar, mensaje: " + e.getMessage());
							e.printStackTrace();
							updateBecados.put("estado", "2");
							becasDAO.updateBecado(updateBecados);
							logger.info("Rut " + detalleEntity2.getRutAfiliado() + " reversado de tabla Beneficios");
						}

					}
					logger.info("Se ha grabado detalle para cabecera id " + cabeceraTEF.getIdCabecera() + ", total registros:" + cantidadRegistros);

					//Se genera nómina 
					logger.info("Se genera archivo plano para convenio " + resumen.getConvenio() + ", procucto " + resumen.getProducto());
					String ruta= generaArchivoNomina(cabeceraTEF, banco);
					logger.info("Ruta archivo generado: " + ruta);
					if(ruta!=null && !ruta.equals("")){
						String prefijo= resumen.getProducto();
						if(prefijo.length()>6){
							prefijo= prefijo.substring(0, 6);
						}
						String idcabtef= String.valueOf(((long)10000000000.0 + cabeceraTEF.getIdCabecera()));

						String nombreArchvio  = prefijo  + idcabtef.substring(1) + ".txt";
						String pathSalida = Configuraciones.getConfig("archivo.salida.becas");

						pathSalida= pathSalida.replaceAll("#banco#", banco);

						ManejoArchivos.copyFileUsingChannel(new File(ruta), new File(pathSalida + nombreArchvio));

						File file = new File(pathSalida + nombreArchvio);
						byte[] fileInBytes = FileUtils.readFileToByteArray(file);
						cabeceraTEF.setPlano(fileInBytes);
						cabeceraTEF.setNombreNomina(nombreArchvio);
						cabeceraTEF.setMonto(montoNomina);
						cabeceraTEF.setCantidad(cantidadRegistros);
						logger.info("Se graba archivo en cabecera tabla");
						cabeceraService.update(cabeceraTEF);

						//Actualizando tabla origen
						updateBecados.put("nomnomina", nombreArchvio);
						becasDAO.updateNominaBecados(updateBecados);

						//Enviando correo ejecutivo por nómina cargada
						HashMap<String, String> params_mail= new HashMap<String, String>();
						params_mail.put("convenio", cabeceraTEF.getConvenio());
						params_mail.put("producto", cabeceraTEF.getProducto());
						params_mail.put("nombre_nomina", cabeceraTEF.getNombreNomina());
						params_mail.put("monto", String.valueOf(cabeceraTEF.getMonto()));
						params_mail.put("cantidad", String.valueOf(cabeceraTEF.getCantidad()));
						enviarCorreo(params_mail);

						totalCabeceras++;
					}else{
						//Update beneficiarios a estado 2
						becasDAO.rollbackBecados((int)cabeceraTEF.getIdCabecera());
						//Borrando detalle TEF
						detalleService.deleteByCodigoNomina(cabeceratef.getCodigoNomina());
						//Borrando cabecera TEF
						cabeceraService.deleteBycodNom(cabeceratef.getCodigoNomina());

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				logger.info("No hay registros que cumplan condición para convenio: " + resumen.getConvenio() + ", producto: " + resumen.getProducto());
			}
		}

		
		return totalCabeceras;
	}
	
	@Override
	public String generaArchivoNomina(CabeceraEntity cabeceraTEF, String banco) throws Exception {
		FileGenerator instancia = new FileGenerator();
		String rutasalida=null;
		//se obtiene formato de archivo
		String formato= Configuraciones.getConfig("formato.beneficios." + banco );
		String tipo_archivo= Configuraciones.getConfig("tipo.archivo.banco." + banco);
		String separador= Configuraciones.getConfig("separador.archivo.banco." + banco);

		//generando archivo
		String ruta  = instancia.generar(formato, "idCabecera:" + cabeceraTEF.getIdCabecera() + ";; ", tipo_archivo, separador, null);
		return ruta;
	}
	

	
	@Override
	public int updateBecadoRendicion(HashMap<String, String> params)
			throws Exception {
		return becasDAO.updateBecadoRendicion(params);
	}
	
	@Override
	public boolean enviarCorreo(HashMap<String, String> params)
			throws Exception {
		ConvenioEntity convenio = convenioDAO.getConvenio(Integer.parseInt( params.get("convenio")) );
		
		if(convenio.getEmailEjecutivo() != null && convenio.getEmailEjecutivo().length()>5) {
			logger.info("Enviando Mail a ejecutivo ["+convenio.getEmailEjecutivo()+"]");
			String body= Configuraciones.getConfig("envio.mail.body");
			body= body.replaceAll("#nombre_nomina#", params.get("nombre_nomina"));
			body= body.replaceAll("#producto#", convenio.getDescripcionConvenio());
			body= body.replaceAll("#monto#", params.get("monto"));
			body= body.replaceAll("#cantidad#", params.get("cantidad"));
			String subject= Configuraciones.getConfig("envio.mail.subject");
			mailService.sendEmail(convenio.getEmailEjecutivo(), subject, body);
			return true;

		}
		else {
			logger.debug("No existe mail configurado para el convenio ["+params.get("convenio")+"]");
		}
		return false;
	}

}
