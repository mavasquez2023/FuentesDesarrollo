/**
 * 
 */
package cl.laaraucana.simulacion.actions.simuladorrepro;



import java.util.Date;

import org.apache.log4j.Logger;

import cl.laaraucana.simulacion.VO.BitacoraVo;
import cl.laaraucana.simulacion.VO.ResultadoAcuerdo;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.ibatis.dao.BitacoraDAO;



/**
 * @author J-Factory
 *
 */

public class BitacoraServiceImpl {
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	
	public void insertBitacoraReprogramacion(ResultadoSim resultado) throws Exception {
		
		logger.info("Insertando Bitacora Repro Rut:" + resultado.getParamEntrada().getRutAfiliado());
		BitacoraVo bitaVO= new BitacoraVo();
		String[] rutCliente = resultado.getParamEntrada().getRutAfiliado().split("-");
		String[] rutEjecutivo = resultado.getParamEntrada().getRutEjecutivo().split("-");
		bitaVO.setRutEjecutivo(rutEjecutivo[0]);
		bitaVO.setDvEjecutivo(rutEjecutivo[1]);
		bitaVO.setRutAfiliado(rutCliente[0]);
		bitaVO.setDvAfiliado(rutCliente[1]);
		bitaVO.setContrato(resultado.getParamEntrada().getContrato());
		bitaVO.setTipoAfiliado(resultado.getParamEntrada().getTipoAfiliado());
		bitaVO.setProducto(resultado.getParamEntrada().getProductoReprogramacion());
		bitaVO.setPlazo(resultado.getParamEntrada().getPlazo());
		bitaVO.setMesesGracia(resultado.getParamEntrada().getMesesGracia());
		bitaVO.setRenta(resultado.getParamEntrada().getRenta());
		bitaVO.setMontoAbono(resultado.getParamEntrada().getMontoAbono());
		bitaVO.setConSeguroCesantia(resultado.getParamEntrada().getSeguroCesantia());
		bitaVO.setConSeguroDesgravamen(resultado.getParamEntrada().getSeguroDesgravamen());
		
		bitaVO.setMontoAdeudado(resultado.getMontoAdeudado());
		bitaVO.setMontoCuota(resultado.getMontoCuota());
		bitaVO.setCae(resultado.getCae());
		bitaVO.setTasaMensual(resultado.getTasaMensual());
		bitaVO.setTasaAnual(resultado.getTasaAnual());
		bitaVO.setCostoTotal(resultado.getCostoTotal());
		bitaVO.setMontoGravamenesCondonado(resultado.getGrvCondonado());
		bitaVO.setMontoGastosCobCondonado(resultado.getGcCondonado());
		bitaVO.setMontoInteresesCondonado(resultado.getIntCondonado());
		bitaVO.setFechaPrimerVencimiento(resultado.getFechaPrimerVencimiento());
		bitaVO.setMontoSeguroCesantia(resultado.getSegCes());
		bitaVO.setMontoSeguroDesgravamen(resultado.getSegDesg());
		bitaVO.setPorcentajeMaximoEndeudamiento(resultado.getPorcentajeMaximoEndeudamiento());
		bitaVO.setPorcentajeEndeudamientoSimulado(resultado.getPorcentajeEndeudamientoSimulado());
		bitaVO.setFechaSimulacion(new Date());
		BitacoraDAO.insertaBitacoraReprogramacion(bitaVO);
	}
	
public void insertBitacoraAcuerdo(ResultadoAcuerdo resultado) throws Exception {
		
		logger.info("Insertando Bitacora Acuerdo Rut:" + resultado.getParamEntrada().getRutAfiliado());
		BitacoraVo bitaVO= new BitacoraVo();
		String[] rutCliente = resultado.getParamEntrada().getRutAfiliado().split("-");
		String[] rutEjecutivo = resultado.getParamEntrada().getRutEjecutivo().split("-");
		bitaVO.setRutEjecutivo(rutEjecutivo[0]);
		bitaVO.setDvEjecutivo(rutEjecutivo[1]);
		bitaVO.setRutAfiliado(rutCliente[0]);
		bitaVO.setDvAfiliado(rutCliente[1]);
		bitaVO.setContrato(resultado.getParamEntrada().getContrato());
		bitaVO.setTipoAfiliado(resultado.getParamEntrada().getTipoAfiliado());
		bitaVO.setProducto(resultado.getParamEntrada().getProductoReprogramacion());
		bitaVO.setPlazo(resultado.getParamEntrada().getPlazo());
		bitaVO.setMesesGracia(resultado.getParamEntrada().getMesesGracia());
		bitaVO.setRenta(resultado.getParamEntrada().getRenta());
		bitaVO.setMontoAbono(resultado.getParamEntrada().getMontoAbono());
		bitaVO.setConSeguroCesantia(resultado.getParamEntrada().getSeguroCesantia());
		bitaVO.setConSeguroDesgravamen(resultado.getParamEntrada().getSeguroDesgravamen());
		bitaVO.setPorcentajeCondonacionCapital(resultado.getParamEntrada().getPorcentajeCapital());
		
		bitaVO.setCapitalAdeudado(resultado.getCapitalAdeudado());
		bitaVO.setCuotasxPagar(resultado.getCuotasxPagar());
		bitaVO.setTasaMensual(resultado.getTasaInteresMensual());
		bitaVO.setCapitalComprometido(resultado.getCapitalComprometido());
		bitaVO.setCapitalCondonado(resultado.getCapitalCondonado());
		bitaVO.setMontoCuota(resultado.getMontoCuota());
		bitaVO.setCae(resultado.getCae());
		bitaVO.setCostoTotal(resultado.getCostoTotal());
		bitaVO.setFechaPrimerVencimiento(resultado.getFechaPrimerVencimiento());
		bitaVO.setFechaSimulacion(new Date());

		BitacoraDAO.insertaBitacoraAcuerdo(bitaVO);
	}
}
