package cl.laaraucana.rendicionpagonomina.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.vo.ParamRendicionVO;
import cl.laaraucana.rendicionpagonomina.vo.RendicionVO;
import cl.lib.export.txt.impl.FileGenerator;


@Service
public class ProcessBeneficiosServiceImpl implements ProcessBeneficiosService {

	private static final Logger logger = Logger.getLogger(ProcessBeneficiosServiceImpl.class);

	@Autowired
	private CabeceraService cabeceraService;

	@Autowired
	private DetalleService detalleService;

	@Autowired
	private ProcessService procesService;
	
	@Autowired
	private ParametrosService convenioService;
	
	@Autowired
	CabeceraManualService cabeceraManualService;
	
	@Autowired
	private DetalleManualService detalleManualService;
	
	
	private BeneficioDao beneficioDAO= new BeneficioDaoImpl();
	
	public CabeceraEntity setCabecera(CabeceraEntity cabecera, ParamRendicionVO paramRendicion, String codigoNomina, int pendientes, String estadoCabecera, int cantidad, int pagado, int rechazado, int devuelto, long montototal, long totalPagado, long totalRechazado, long totalDevuelto, String crc){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");
		int estadoNomina=0;
		//logger.info("Pendientes en cabecera " + cabecera.getCodigoNomina() + ":" + cabecera.getPendientes() + ", pendientes en nómina: " + pendientes);
		if((cabecera.getPendientes() +  pendientes)>0){
			estadoCabecera="EN_PROCESO";
			estadoNomina= Estados.NOMINA_ENPROCESO;
		}else{
			estadoCabecera="CERRADA";
			estadoNomina= Estados.NOMINA_CERRADA;
		}
		//logger.info("Estado nomina:" + estadoCabecera);
		cabecera.setFechaRendicion(new Date());
		cabecera.setPendientes(cabecera.getPendientes() + pendientes);
		cabecera.setCantidad(cabecera.getCantidad() + cantidad);
		cabecera.setCantidadPagado(cabecera.getCantidadPagado() + pagado);
		cabecera.setCantidadRechazado(cabecera.getCantidadRechazado() +  rechazado);
		cabecera.setCantidadDevuelto(cabecera.getCantidadDevuelto() +  devuelto);
		cabecera.setConvenio(paramRendicion.getConvenio());
		cabecera.setProducto("");
		cabecera.setCodigoNomina(Long.parseLong(codigoNomina));
		cabecera.setCodigoRechazoEnvio(0);
		cabecera.setMonto(cabecera.getMonto() +  montototal);
		cabecera.setTotalPagado(cabecera.getTotalPagado() +  totalPagado);
		cabecera.setTotalRechazado(cabecera.getTotalRechazado()+ totalRechazado);
		cabecera.setTotalDevuelto(cabecera.getTotalDevuelto() + totalDevuelto);
		cabecera.setCodigoRechazoRendicion(0);
		cabecera.setEstadoNomina(estadoNomina);
		cabecera.setFechaEnvio(null);
		cabecera.setGlosaRechazoEnvio("");
		cabecera.setGlosaRechazoRendicion("");
		cabecera.setCrc(crc);
		
		return cabecera;
	}
	
	public void insertCabecerayDetalle( Map<String, RendicionVO> mapRendicion){
		try {
			for (Iterator iterator = mapRendicion.values().iterator(); iterator.hasNext();) {
				RendicionVO rendicionVO = (RendicionVO) iterator.next();
				long codigoNomina=rendicionVO.getCabecera().getCodigoNomina();
				try {
		
					CabeceraEntity cabecera_agrabar= rendicionVO.getCabecera();
					logger.info("Verificando si existe cabecera coódigo nómina: " + rendicionVO.getCabecera().getCodigoNomina());
					CabeceraEntity cabecera_actual= cabeceraService.findByCodigoNomina(rendicionVO.getCabecera().getCodigoNomina());
					long id_cabecera=0;
					if(cabecera_actual != null){
						logger.info("Código Nómina: " + codigoNomina + " borrando detalle.");
						detalleService.deleteByCodigoNomina(cabecera_agrabar.getCodigoNomina());
						
						id_cabecera=cabecera_actual.getIdCabecera(); 
						cabecera_actual.setCantidad(cabecera_agrabar.getCantidad());
						cabecera_actual.setMonto(cabecera_agrabar.getMonto());
						cabecera_actual.setCantidadPagado(cabecera_agrabar.getCantidadPagado());
						cabecera_actual.setTotalPagado(cabecera_agrabar.getTotalPagado());
						cabecera_actual.setCantidadDevuelto(cabecera_agrabar.getCantidadDevuelto());
						cabecera_actual.setTotalDevuelto(cabecera_agrabar.getTotalDevuelto());
						cabecera_actual.setCantidadRechazado(cabecera_agrabar.getCantidadRechazado());
						cabecera_actual.setTotalRechazado(cabecera_agrabar.getTotalRechazado());
						cabecera_actual.setCodigoRechazoRendicion(cabecera_agrabar.getCodigoRechazoRendicion());
						cabecera_actual.setGlosaRechazoRendicion(cabecera_agrabar.getGlosaRechazoRendicion());
						cabecera_actual.setFechaRendicion(new Date());
						cabecera_actual.setEstadoNomina(cabecera_agrabar.getEstadoNomina());
						cabecera_actual.setCrc(cabecera_agrabar.getCrc());
						
						logger.info("Actualizando cabecera " + codigoNomina + " con estado " + cabecera_actual.getEstadoNomina());
						cabeceraService.updateNomina(cabecera_actual);
						
					}else{
						logger.info("Código Nómina: " + codigoNomina + " grabación nueva cabecera, estado " + cabecera_agrabar.getEstadoNomina());
						CabeceraEntity cabecerasave= cabeceraService.save(cabecera_agrabar);
						id_cabecera= cabecerasave.getIdCabecera();
					}					
					
					//Se inicia Detalle
					//codigoNomina= cabecerasave.getCodigoNomina();
					
					logger.info("Código Nómina: " + codigoNomina + " grabación detalle, total registros: " +  rendicionVO.getDetalles().size());
					for (DetalleEntity deta : rendicionVO.getDetalles()) {
						deta.setIdCabecera(id_cabecera);
						detalleService.save(deta);
					}
					
				} catch (Exception e) {
					logger.info("Error al grabar nómina: " + codigoNomina);
					e.printStackTrace();
				}
			}
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public CabeceraManualEntity generarCabeceraManual(ResumenCargaPagoManualVo resumen)
			throws Exception {
		CabeceraManualEntity cabecera= new CabeceraManualEntity();
		cabecera.setConvenio(resumen.getConvenio());
		cabecera.setEstado(Estados.NOMINA_PENDIENTE);
		cabecera.setFechaCarga(new Date());
		cabecera.setFechaPago(null);
		cabecera.setFechaCreacion(new Date());
		//cabecera.setMontoPendiente(resumen.getMontoPendiente());
		//La primera vez el monto pendiente es igual al monto de la nómina
		cabecera.setMontoPendiente(resumen.getMontoNomina());
		cabecera.setProducto(resumen.getProducto());
		cabecera.setTotalMonto(resumen.getMontoNomina());
		//La primera vez la cantidad de pendientse es igual al de la nómina
		//cabecera.setTotalPendientes(resumen.getSinmandato());
		cabecera.setTotalPendientes(resumen.getCantidadRegistros());
		cabecera.setTotalRegistros(resumen.getCantidadRegistros());
		cabecera.setUsuarioCreacion("USRTEF");
		cabecera= cabeceraManualService.save(cabecera);
		return cabecera;
	}
	
	@Override
	public String generaArchivoNomina(ArchivoManualVO archivoManualVO) throws Exception {
		FileGenerator instancia = new FileGenerator();
		String rutasalida=null;
		//se obtiene formato de archivo
		String formato= Configuraciones.getConfig("formato.banco." + archivoManualVO.getIdBanco());
		String tipo_archivo= Configuraciones.getConfig("tipo.archivo.banco." + archivoManualVO.getIdBanco() );
		String separador= Configuraciones.getConfig("separador.archivo.banco." + archivoManualVO.getIdBanco() );

		//generando archivo
		String ruta  = instancia.generar(formato, "idcodconv:" + archivoManualVO.getIdConvenio() + ";;codprod:" + archivoManualVO.getIdProducto() + ";; ", tipo_archivo, separador, null);

		

		
		return ruta;
	}

	/*public String generaArchivoNomina(List<DetalleManualEntity> detalle) throws Exception{
		String filename= "Auditoria Usuarios Fecha " + ".csv";
		File destino = new File(filename);
		//Generando la salida
		logger.info("Nombre archivo:" + filename);
		
		//Generando la salida

		OutputStream out= new FileOutputStream(destino);
		PrintStream flujo= new PrintStream(out);
		GeneratorXLS xls= new GeneratorXLS(flujo);

		//Configurando columnas a desplegar y titulos de estas.
		String[] columnas={"rutEjecutivo", "rutUsuario", "rutEmpresa", "accion", "tipoEjecutivo", "envioSMS", "envioEMAIL", "fecha", "hora"};
		String[] titulos={"RUT Ejecutivo", "RUT Usuario", "RUT Empresa", "Accion", "Tipo Ejecutivo", "envio SMS", "envio EMAIL", "Fecha Creacion", "Hora Creacion"};

		xls.generarCSVfromCollection(detalle, columnas, titulos);
		logger.info("Archivo ha sido generado.");
		//Cerrando salida
		out.flush();
		out.close();
		
		return null;
	}*/
	
}
