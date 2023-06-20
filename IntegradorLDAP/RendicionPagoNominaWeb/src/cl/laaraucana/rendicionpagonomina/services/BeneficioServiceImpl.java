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
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficiarioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoBenefVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;

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
public class BeneficioServiceImpl implements BeneficioService {

	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.services.BeneficioService#cargarTablaBeneficios()
	 */
	private static final Logger logger = Logger.getLogger(BeneficioServiceImpl.class);
	
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
	
	BeneficioDao beneficioDAO= new BeneficioDaoImpl();
	
	private ConvenioDao convenioDAO= new ConvenioDaoImpl();
	
	@Override
	public List<ResumenCargaPagoBenefVo> leerTablaBeneficios(String banco) throws Exception {
		logger.info("Buscando lista de beneficios para Map");
		int maximoNomina=9999;
		
		//BENEFICIOS MAP
		List<BeneficioEntity> listaBeneficios= beneficioDAO.consultaBeneficios();
		Map<String , String> beneficios= new HashMap<String, String>();
		for(BeneficioEntity beneficio:listaBeneficios){
			beneficios.put(beneficio.getCodigoBeneficio(), beneficio.getDescripcionBeneficio());
		}
		
		logger.info("Buscando lista de beneficiarios estado 2 y 3 banco " + banco);
		
		List<BeneficiarioEntity> beneficiarios=null;
		if(banco.equals("BCI")){
			beneficiarios= beneficioDAO.consultaBeneficiariosBCI();
			maximoNomina=Integer.parseInt(Configuraciones.getConfig("cantidad.maxima.nomina.bci"));
		}else if(banco.equals("BES")){
			beneficiarios= beneficioDAO.consultaBeneficiariosBES();
		}
		
		logger.info("Total registros encontrados para " + banco + ": " + beneficiarios.size());
		List<DetalleCargaPagoBenefVo> listaNomina= new ArrayList<DetalleCargaPagoBenefVo>();
		List<ResumenCargaPagoBenefVo> listaResumen= new ArrayList<ResumenCargaPagoBenefVo>();
		
		String convenio_old="";
		String producto_old="";
		String convenioBES= Configuraciones.getConfig("bes.convenio.beneficios");
		for (Iterator iterator = beneficiarios.iterator(); iterator.hasNext();) {
			BeneficiarioEntity beneficiarioEntity = (BeneficiarioEntity) iterator
					.next();
			if((!beneficiarioEntity.getCodigoConvenio().trim().equals(convenio_old) || !beneficiarioEntity.getCodigoProducto().trim().equals(producto_old) || listaNomina.size()== maximoNomina) && !convenio_old.equals("")){
				if(listaNomina.size()>0){
					logger.info("Cargado Convenio: " + convenio_old + ", Producto: " + producto_old + ", total registros con mandato: " + listaNomina.size());
					ResumenCargaPagoBenefVo respuesta= new ResumenCargaPagoBenefVo();
					if(banco.equals("BCI")){
						respuesta.setConvenio(convenio_old);
					}else if(banco.equals("BES")){
						respuesta.setConvenio(convenioBES);
					}
					
					respuesta.setProducto(producto_old);
					respuesta.setListaNomina(listaNomina);
					listaResumen.add(respuesta);
					listaNomina= new ArrayList<DetalleCargaPagoBenefVo>();
				}
			}
			
			DetalleCargaPagoBenefVo registro= new DetalleCargaPagoBenefVo();
			registro.setRutAfiliado(beneficiarioEntity.getRutBeneficiario());
			if(beneficios.get(beneficiarioEntity.getCodigoBeneficio().trim())!=null){
				
				registro.setCodbanco(beneficiarioEntity.getIdBanco());
				registro.setIdtipcta(beneficiarioEntity.getIdTipoCuenta());
				registro.setNumcuenta(beneficiarioEntity.getNumCuenta().trim());
				registro.setEmail(beneficiarioEntity.getEmail().trim());
				registro.setFechaPago(Utils.getFechaAS());
				registro.setRutAfiliado(beneficiarioEntity.getRutBeneficiario());
				registro.setDvAfiliado(beneficiarioEntity.getDvBeneficiario());
				registro.setNombreAfiliado(beneficiarioEntity.getNombreAfiliado().trim());
				registro.setDescripcionPago(beneficiarioEntity.getDescripcionPago().trim());
				registro.setMontoPago(beneficiarioEntity.getMonto());
				registro.setCodBeneficio(beneficiarioEntity.getCodigoBeneficio().trim());
				registro.setDescripcionPago( beneficios.get(beneficiarioEntity.getCodigoBeneficio().trim()));
				registro.setReferencia1(beneficiarioEntity.getReferencia1().trim());
				registro.setReferencia2(beneficiarioEntity.getReferencia2().trim());
				listaNomina.add(registro);
			}else{
				logger.warn("Rut beneficiario " + beneficiarioEntity.getRutBeneficiario() + " sin código beneficio válido, no se carga");
			}
			convenio_old= beneficiarioEntity.getCodigoConvenio().trim();
			producto_old= beneficiarioEntity.getCodigoProducto().trim();
			
		}
		//Cargando último lote
		if(listaNomina.size()>0){
			logger.info("Cargado Convenio: " + convenio_old + ", Producto: " + producto_old + ", total registros con mandato: " + listaNomina.size());
			ResumenCargaPagoBenefVo respuesta= new ResumenCargaPagoBenefVo();
			respuesta.setCantidadRegistros(0);
			respuesta.setMontoNomina(0);
			if(banco.equals("BCI")){
				respuesta.setConvenio(convenio_old);
			}else if(banco.equals("BES")){
				respuesta.setConvenio(convenioBES);
			}
			respuesta.setProducto(producto_old);
			respuesta.setMontoNomina(0);
			respuesta.setListaNomina(listaNomina);
			listaResumen.add(respuesta);
		}
		return listaResumen;
	}
	@Override
	public int cargarTablasTEF(List<ResumenCargaPagoBenefVo> listaResumen, String banco) throws Exception {
		logger.info("Cargando datos Cabecera y Detalle TEF");
		
		//Se obtiene mapa de los bancos para poder obtener la descripción
		Map<String, BancoEntity> mapBancos= bancoService.getMapBancos();
		
		int totalCabeceras=0;
		for (Iterator iterator = listaResumen.iterator(); iterator.hasNext();) {
			ResumenCargaPagoBenefVo resumen = (ResumenCargaPagoBenefVo) iterator
					.next();
			
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
				
				//Se graban parámetros para actualizar beneficiario con nuevo estado una vez cargado
				HashMap<String, String> updateBene= new HashMap<String, String>();
				updateBene.put("convenio", cabeceratef.getConvenio().trim());
				updateBene.put("producto", cabeceratef.getProducto().trim());
				
				int cantidadRegistros=0;
				long montoNomina=0;
				for (Iterator iterator2 = resumen.getListaNomina().iterator(); iterator2
						.hasNext();) {
					DetalleCargaPagoBenefVo detalleEntity2 = (DetalleCargaPagoBenefVo) iterator2
							.next();
					
					try {
						updateBene.put("id", detalleEntity2.getReferencia1());
						updateBene.put("estado", "1");
						updateBene.put("idCabecera", String.valueOf(cabeceraTEF.getIdCabecera()));
						int exito= beneficioDAO.updateBeneficiario(updateBene);
						
						if(exito>0){
							DetalleEntity detalleTEF= new DetalleEntity();
							detalleTEF.setIdCabecera(cabeceraTEF.getIdCabecera());
							detalleTEF.setCodigoBeneficio(detalleEntity2.getCodBeneficio());

							detalleTEF.setDescripcionEstadoPago("PENDIENTE_PROCESO");
							detalleTEF.setDescripcionPago(detalleEntity2.getDescripcionPago());
							detalleTEF.setRutAfiliado(detalleEntity2.getRutAfiliado());
							detalleTEF.setDvAfiliado(detalleEntity2.getDvAfiliado());
							detalleTEF.setNombres(detalleEntity2.getNombreAfiliado().replaceAll("ñ", "n").replaceAll("Ñ", "N"));
							detalleTEF.setCodigoBanco(String.valueOf(detalleEntity2.getCodbanco()));
							String descripcionBanco="";
							if(convenio.getCodigoBanco().equals("BCI")){
								descripcionBanco= mapBancos.get(String.valueOf(detalleEntity2.getCodbanco())).getDescripcionBCI();
							}else if(convenio.getCodigoBanco().equals("BES")){
								if(detalleEntity2.getCodbanco()>0){
									descripcionBanco= mapBancos.get(String.valueOf(detalleEntity2.getCodbanco())).getDescripcionBES();
								}
							}
							detalleTEF.setDescripcionBanco(descripcionBanco);
							long numeroCuenta=0L;
							if(!detalleEntity2.getNumcuenta().equals("")){
								numeroCuenta= Long.parseLong(detalleEntity2.getNumcuenta());
							}
							detalleTEF.setNumerocuenta(numeroCuenta);
							detalleTEF.setTipoCuenta(detalleEntity2.getIdtipcta());
							detalleTEF.setEmail(detalleEntity2.getEmail());
							int codforpag=detalleEntity2.getIdtipcta();
							String des_formapago="TRANSFERENCIA";
							detalleTEF.setCodigoFormaPago(String.valueOf(codforpag));
							if(codforpag==99){
								des_formapago="PAGO CASH";
							}
							detalleTEF.setDescripcionFormaPago(des_formapago);
							detalleTEF.setMonto((int)detalleEntity2.getMontoPago());
							detalleTEF.setEstadoPago(Estados.PAGO_PENDIENTE_PROCESO);
							detalleTEF.setDescripcionEstadoPago("");
							detalleTEF.setReferencia1(detalleEntity2.getReferencia1());
							detalleTEF.setReferencia2(Utils.getTimeHHmmss());
							detalleTEF.setFechaCambio(new Date());					
							detalleTEF.setIdReferencia(0);
							detalleService.save(detalleTEF);

							montoNomina += detalleTEF.getMonto();
							cantidadRegistros++;
						}
					} catch (Exception e) {
						logger.warn("Beneficiario " + detalleEntity2.getRutAfiliado() + " se excluye por error al grabar, mensaje: " + e.getMessage());
						e.printStackTrace();
						updateBene.put("estado", "2");
						beneficioDAO.updateBeneficiario(updateBene);
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
					String pathSalida = Configuraciones.getConfig("archivo.salida.beneficio");
					
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
					beneficioDAO.rollbackBeneficiarios((int)cabeceraTEF.getIdCabecera());
					//Borrando detalle TEF
					detalleService.deleteByCodigoNomina(cabeceratef.getCodigoNomina());
					//Borrando cabecera TEF
					cabeceraService.deleteBycodNom(cabeceratef.getCodigoNomina());
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public int updateBeneficiarioById(HashMap<String, String> params)
			throws Exception {
		return beneficioDAO.updateBeneficiarioById(params);
	}
	
	@Override
	public int updateBeneficiarioRendicion(HashMap<String, String> params)
			throws Exception {
		return beneficioDAO.updateBeneficiarioRendicion(params);
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
