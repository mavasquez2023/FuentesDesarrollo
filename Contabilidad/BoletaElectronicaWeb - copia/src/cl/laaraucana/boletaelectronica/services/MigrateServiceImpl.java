package cl.laaraucana.boletaelectronica.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;
import cl.laaraucana.boletaelectronica.vo.ParametrosVo;

@Service
public class MigrateServiceImpl implements MigrateService{
	
	@Autowired
	private OrigenBoletaService origenBoletaService;

	@Autowired
	private BaseServices baseService;

	@Autowired
	private ParametrosService paramService;

	@Override
	public void migrate(String id) throws Exception {
	 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("CL"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd", new Locale("CL"));
		SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm:ss", new Locale("CL"));

		List<OrigenBoleta> origenMain = origenBoletaService.getOrigenByNumber(Integer.parseInt(id));

		List<OrigenBoleta> afecta = origenBoletaService.findByNumBolAndTipoDocOrigen(Integer.parseInt(id), 39);

		List<OrigenBoleta> exenta = origenBoletaService.findByNumBolAndTipoDocOrigen(Integer.parseInt(id), 41);

		List<OrigenBoleta> origen = new ArrayList<OrigenBoleta>();

		long montoTotal = 0;

	 
		for (OrigenBoleta origenBoleta : origenMain) {

			if (!baseService.existNumberAndFol(origenBoleta.getFOLIO())) {

				origen.add(origenBoleta);
			}
		}

		long montoExento = 0;
		long montoNeto = 0;
		long montoIva = 0;

		if (exenta.size() > 0) {

			for (OrigenBoleta boletaBase : exenta) {

				montoExento += boletaBase.getPREUNI();
			}
			
			montoTotal = montoExento;
		}

		if (afecta.size() > 0) {

			for (OrigenBoleta boletaBase : afecta) {

				montoNeto += boletaBase.getPREUNI();
			}

			for (OrigenBoleta boletaBase : afecta) {

				montoIva += boletaBase.getVALLIN();
			}
			montoTotal = montoNeto + montoIva;
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
			boletaBase.setMONTOEX(montoExento);
			boletaBase.setMONTOTAL(montoTotal);
			boletaBase.setRAZONSOCEM(param.getRazonSocial());
			boletaBase.setRUTEM(param.getRutEmpresa());
			boletaBase.setMONTOIVA(montoIva);
			boletaBase.setMONTONETO(montoNeto);
			boletaBase.setTIPDOC(origen.get(0).getTIPDOC());
			boletaBase.setVERSION(param.getVersion());
			boletaBase.setURLACEPTA("");
		

			for (OrigenBoleta origenBoleta : origen) {

				BoletaDetalle detalle = new BoletaDetalle();

				detalle.setCANTIDAD(1);
				detalle.setCODCAJ("");
				detalle.setCODITEM(String.valueOf(origenBoleta.getCODITE()));
				detalle.setNOMITEM(origenBoleta.getNOMITE());
				detalle.setPRECUNIT(origenBoleta.getPREUNI());
				detalle.setTIMELECTR("");
				detalle.setFOLDOC(origenBoleta.getFOLIO());
				detalle.setUNIMED(param.getUnidadMedida());
				detalle.setVALLINDET(origenBoleta.getVALLIN());
				detalle.setBoletaBase(boletaBase);

				detalleList.add(detalle);

			}
			boletaBase.setBoletaDetalle(detalleList);

			baseService.save(boletaBase);
		}
	}

}
