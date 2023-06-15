package cl.laaraucana.boletaelectronica.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;
import cl.laaraucana.boletaelectronica.utils.Configuraciones;
import cl.laaraucana.boletaelectronica.vo.ParametrosVo;

@Service
public class MigrateServiceImpl implements MigrateService{
	private static final Logger logger = Logger.getLogger(MigrateServiceImpl.class);
	
	protected int AFECTO = 39;
	protected int EXCENTO = 41;
	
	@Autowired
	private OrigenBoletaService origenBoletaService;

	@Autowired
	private BaseServices baseService;

	@Autowired
	private ParametrosService paramService;

	@Override
	public boolean migrate(String id) throws Exception {
		boolean exito=false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("CL"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd", new Locale("CL"));
		SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm:ss", new Locale("CL"));
		List<OrigenBoleta> origenMain = origenBoletaService.getOrigenByNumber(Integer.parseInt(id));
		logger.info("Cantidad registros con folio " + id + " :" + origenMain.size());
		List<OrigenBoleta> origen = new ArrayList<OrigenBoleta>();
		for (OrigenBoleta origenBoleta : origenMain) {
			logger.info("Se valida que no exista folio " + origenBoleta.getFOLIO() + " ya procesado en Cabecera Boletas.");
			if (!baseService.existNumberAndFol(origenBoleta.getFOLIO())) {
				logger.info("No existe, se procesará");
				origen.add(origenBoleta);
			}
		}
		
		if (origen.size() > 0) {
			List<BoletaDetalle> detalleList = new ArrayList<BoletaDetalle>();
			ParametrosVo param = new ParametrosVo();
			param.setCiudad(paramService.getParamByCode(1007).getVALOR());
			param.setComuna(paramService.getParamByCode(1006).getVALOR());
			param.setDireccion(paramService.getParamByCode(1005).getVALOR());
			param.setGiroNegocio(paramService.getParamByCode(1004).getVALOR());
			param.setIndicServicio(paramService.getParamByCode(1001).getVALOR());
			param.setRazonSocial(paramService.getParamByCode(1003).getVALOR());
			param.setRutEmpresa(paramService.getParamByCode(1002).getVALOR());
			param.setUnidadMedida(paramService.getParamByCode(1008).getVALOR());
			param.setVersion(paramService.getParamByCode(1000).getVALOR());
			BoletaBase boletaBase = new BoletaBase();
			logger.info("Se instancia cabecera para folio " + origen.get(0).getFOLIO());
			boletaBase.setRUTREC(origen.get(0).getRUTREC());
			boletaBase.setNOMREC(origen.get(0).getNOMREC());
			boletaBase.setESTADO(0);
			boletaBase.setCIUORIGEN(param.getCiudad());
			boletaBase.setCOMORIGEN(param.getComuna());
			boletaBase.setNUMBOL(origen.get(0).getNUMBOL());
			boletaBase.setDIRORIGEN(param.getDireccion());
			boletaBase.setEMICONT("");
			boletaBase.setFECCRE(Long.parseLong(sdf2.format(new Date())));
			boletaBase.setFECMOD(sdf.format(new Date()));
			boletaBase.setFOLDOC(origen.get(0).getFOLIO());
			boletaBase.setGIRONEGEM(param.getGiroNegocio());
			boletaBase.setHORACRE(sdfh.format(new Date()));
			boletaBase.setHORAMOD(sdfh.format(new Date()));
			boletaBase.setINDICSERV(Integer.parseInt(param.getIndicServicio()));
			boletaBase.setRAZONSOCEM(param.getRazonSocial());
			boletaBase.setRUTEM(param.getRutEmpresa());
			boletaBase.setTIPDOC(origen.get(0).getTIPDOC());
			boletaBase.setVERSION(param.getVersion());
			boletaBase.setURLACEPTA("");
			String impuesto = Configuraciones.getConfig("impuesto.iva");
			float imp = Long.parseLong(impuesto);
			float iva = imp / 100F;
			logger.info("IVA obtenido: " + iva);
			
			//Valores Totales boleta
			long montoTotal = 0;
			long montoExento = 0;
			long montoNeto = 0;
			int ivaTotal = 0;
			
			for (OrigenBoleta origenBoleta : origen) {
				
				BoletaDetalle detalle = new BoletaDetalle();
				long precioUnitario = origenBoleta.getPREUNI();
				logger.info("Precio Unitario: " + precioUnitario);
				long monto_dscto = Math.abs(origenBoleta.getMTODSC());
				
				logger.info("Se instancia detalle para folio " + origenBoleta.getFOLIO() + ", código item: " + origenBoleta.getCODITE());
				detalle.setCANTIDAD(1);
				detalle.setCODCAJ("");
				detalle.setCODITEM(String.valueOf(origenBoleta.getCODITE()));
				detalle.setNOMITEM(origenBoleta.getNOMITE());
				detalle.setPRECUNIT(precioUnitario);
				detalle.setTIMELECTR("");
				detalle.setFOLDOC(origenBoleta.getFOLIO());
				detalle.setUNIMED(param.getUnidadMedida());
				
				//Se calcula Monto Descuento dependiendo del tipo documento
				if(origenBoleta.getTIPDOC() == AFECTO) {
					logger.info("Registro AFECTO");
					
					float descuentoSinIVA = monto_dscto / (1 + iva);
					monto_dscto = Math.round(descuentoSinIVA);
					logger.info("Descuento sin IVA: " + monto_dscto);
				} else {
					logger.info("Registro EXENTO");
					logger.info("Descuento: " + monto_dscto);
					
				}
				//subtotal Item
				long subtotal = precioUnitario - monto_dscto;
				logger.info("Subtotal item: " + subtotal);
				
				if(origenBoleta.getTIPDOC() == AFECTO) {
					//Sumatoria Monto NETO
					montoNeto = montoNeto + subtotal;
				}else{
					//Sumatoria Monto EXENTO
					montoExento = montoExento + subtotal;
				}
				
				
				//Cálculo de iva por item
				int iva_item=0;
				if(origenBoleta.getTIPDOC() == AFECTO) {
					iva_item= Math.round(subtotal * iva);
					ivaTotal = ivaTotal + iva_item;
				} else {
					ivaTotal = 0;
				}
				logger.info("IVA total item: " + iva_item);
				
				detalle.setMTODSC(Long.parseLong(String.valueOf(monto_dscto)));
				detalle.setVALLINDET(Math.abs(Long.parseLong(String.valueOf(iva_item))));
				detalle.setSUBTOTAL(subtotal);
				detalle.setBoletaBase(boletaBase);
				detalleList.add(detalle);
			}
			//Se guardan los totales boleta
			boletaBase.setMONTONETO(montoNeto);
			boletaBase.setMONTOEX(montoExento);
			boletaBase.setMONTOIVA(ivaTotal);
			logger.info("montoNeto: " + montoNeto + ", montoExento: " + montoExento + ", ivaTotal: " + ivaTotal);
			
			montoTotal = montoNeto + montoExento + ivaTotal;
			boletaBase.setMONTOTAL(montoTotal);
			logger.info("montoTotal: " + montoTotal);
					
			boletaBase.setBoletaDetalle(detalleList);
			if(montoNeto>=0 && montoExento>=0 && ivaTotal>=0){
				logger.info("Se graba cabecera y detalles folio " + origen.get(0).getFOLIO());
				baseService.save(boletaBase);
				exito= true;
			}else{
				logger.warn("Valores negativos en folio: " + origen.get(0).getFOLIO() + ", causa: Precio Unitario menor a Descuento");
			}
		}
		return exito;
	}

}
