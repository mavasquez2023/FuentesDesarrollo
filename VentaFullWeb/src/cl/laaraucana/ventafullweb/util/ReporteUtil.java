package cl.laaraucana.ventafullweb.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cl.laaraucana.ventafullweb.dao.SucursalesDao;
import cl.laaraucana.ventafullweb.dto.SucursalesDto;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.DetalleCuotasVo;
import cl.laaraucana.ventafullweb.vo.ReporteSimulacionVo;
import org.apache.log4j.Logger;

public class ReporteUtil {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public ReporteSimulacionVo seteaDatos(AfiliadoVo afiliado) throws SQLException {
		SucursalesDao sucursalesDao = new SucursalesDao();
		List<SucursalesDto> sucursales = sucursalesDao.getSucursales();
		ReporteSimulacionVo salida = new ReporteSimulacionVo();
		salida.setFechaHora(Utils.fechaHoraActual());
		salida.setRut(AfiliadoUtils.formateaRut(afiliado.getRutAfiliado()));
		salida.setSucursal(AfiliadoUtils.nombreSucursal(sucursales, afiliado.getSucursal()));
		salida.setTipoAfiliado(AfiliadoUtils.getTipoAfiliado(afiliado.getSegmento()));
		salida.setMonto("$" + AfiliadoUtils.formateaVal(afiliado.getMontoSimulacion()));
		salida.setNumCuotas(Integer.toString(afiliado.getCuotas()));
		salida.setSeguroCesantiaAnual("$" + AfiliadoUtils.formateaVal(afiliado.getCostoTotalCesantia()));
		salida.setSeguroCesantiaMensual("$" + AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroCesantia()));
		salida.setSeguroDesgravamenAnual("$" + AfiliadoUtils.formateaVal(afiliado.getCostoTotalDesgravamen()));
		salida.setSeguroDesgravamenMensual("$" + AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroDesgravamen()));
		salida.setImpuesto("$" + AfiliadoUtils.formateaVal(afiliado.getImpuesto()));
		salida.setGastoNotarial("$" + AfiliadoUtils.formateaVal(afiliado.getGastoNotaria()));
		salida.setValorCuota("$" + AfiliadoUtils.formateaVal(afiliado.getMontoCuota()));
		salida.setValorCuotaSinSeguro("$" + AfiliadoUtils.formateaVal(afiliado.getMontoCuota()-afiliado.getValorMensualSeguroDesgravamen()));
		salida.setTasaInteresMensual(afiliado.getTasaInteresMensual() + "%");
		salida.setTasaInteresAnual(afiliado.getTasaInteresAnual() + "%");
		salida.setPagoPrimeraCuota(afiliado.getPagoPrimeraCuota());
		salida.setCostoTotalCredito("$" + AfiliadoUtils.formateaVal(afiliado.getCostoTotalCredito()));
		salida.setCae(afiliado.getCae() + "%");
		salida.setFecha(Utils.fechaHoraActual().substring(0, 10));
		salida.setUnidMonetaria("Pesos");
		salida.setDetalleCuotas(seteaDetalle(afiliado.getDetalleCuotas()));
		salida.setIncluyeSC(afiliado.getSeguroCesantia().equals("X")?"SI":"NO");
		salida.setIncluyeSD(afiliado.getSeguroDesgravamen().equals("X")?"SI":"NO");
		logeaInfo(salida);
		return salida;
	}
	
	private List<DetalleCuotasVo> seteaDetalle(List<DetalleCuotasVo> detalleCuotas) {
		List<DetalleCuotasVo> retorno = new ArrayList<DetalleCuotasVo>();
		for(DetalleCuotasVo entrada : detalleCuotas) {
			DetalleCuotasVo det = new DetalleCuotasVo();
			det.setNumCuota(entrada.getNumCuota());
			det.setVencimiento(formateaFecha(entrada.getVencimiento()));
			det.setSaldoCapital("$" + AfiliadoUtils.formateaVal(entrada.getSaldoCapital()));
			det.setMontoInteres("$" + AfiliadoUtils.formateaVal(entrada.getMontoInteres()));
			det.setSeguroDesgravamen("$" + AfiliadoUtils.formateaVal(entrada.getSeguroDesgravamen()));
			det.setSeguroCesantia("$" + AfiliadoUtils.formateaVal(entrada.getSeguroCesantia()));
			det.setValorCuota("$" + AfiliadoUtils.formateaVal(entrada.getValorCuota()));
			det.setCapitalCuota("$" + AfiliadoUtils.formateaVal(entrada.getCapitalCuota()));
			retorno.add(det);
		}
		return retorno;
	}

	private void logeaInfo(ReporteSimulacionVo rep) {
		logger.info("**** DATOS PARA EL REPORTE ****");
		logger.info("fechaHoraActual: " + rep.getFechaHora());
		logger.info("rut: " + rep.getRut());
		logger.info("sucursal: " + rep.getSucursal());
		logger.info("tipo: " + rep.getTipoAfiliado());
		logger.info("monto: " + rep.getMonto());
		logger.info("numCuotas: " + rep.getNumCuotas());
		logger.info("SeguroCesantiaAnual: " + rep.getSeguroCesantiaAnual());
		logger.info("SeguroCesantiaMensual: " + rep.getSeguroCesantiaMensual());
		logger.info("SeguroDesgravamenAnual: " + rep.getSeguroDesgravamenAnual());
		logger.info("SeguroDesgravamenMensual: " + rep.getSeguroDesgravamenMensual());
		logger.info("Impuesto: " + rep.getImpuesto());
		logger.info("GastoNotarial: " + rep.getGastoNotarial());
		logger.info("ValorCuota: " + rep.getValorCuota());
		logger.info("TasaInteresMensual: " + rep.getTasaInteresMensual());
		logger.info("PagoPrimeraCuota: " + rep.getPagoPrimeraCuota());
		logger.info("CostoTotalCredito: " + rep.getCostoTotalCredito());
		logger.info("CAE: " + rep.getCae());
		logger.info("fecha: " + rep.getFecha());
		logger.info("UnidMonetaria: " + rep.getUnidMonetaria());
		for(DetalleCuotasVo det : rep.getDetalleCuotas()) {
			logger.info("numCuota: " + det.getNumCuota());
			logger.info("vencimiento: " + det.getVencimiento());
			logger.info("saldoCapital: " + det.getSaldoCapital());
			logger.info("montoInteres: " + det.getMontoInteres());
			logger.info("seguroDesgravamen: " + det.getSeguroDesgravamen());
			logger.info("seguroCesantia: " + det.getSeguroCesantia());
			logger.info("valorCuota: " + det.getValorCuota());
			logger.info("capitalCuota: " + det.getCapitalCuota());
		}
	}
	
	private String formateaFecha(String fecha) {
		return fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/" + fecha.substring(0, 4);
	}
}
