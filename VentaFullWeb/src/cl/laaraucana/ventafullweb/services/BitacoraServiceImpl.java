package cl.laaraucana.ventafullweb.services;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.springframework.stereotype.Service;

import cl.laaraucana.ventafullweb.dao.BitacoraDao;
import cl.laaraucana.ventafullweb.dto.BitacoraEvaluadorAISDto;
import cl.laaraucana.ventafullweb.dto.BitacoraGenesysDto;
import cl.laaraucana.ventafullweb.dto.BitacoraSeguimientoDto;
import cl.laaraucana.ventafullweb.dto.BitacoraSimulacionDto;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;


@Service
public class BitacoraServiceImpl implements BitacoraService{

	BitacoraDao dao= new BitacoraDao();
	
	@Override
	public void saveBitacoraSimulacion(AfiliadoVo entity) throws Exception {
		
		BitacoraSimulacionDto bitacora= new BitacoraSimulacionDto();
		String[] rutdvAfiliado= entity.getRutAfiliado().split("-");
		int rutAfiliado= Integer.parseInt(rutdvAfiliado[0]);
		String dvAfiliado= rutdvAfiliado[1];
		bitacora.setCae(entity.getCae());
		bitacora.setCelular(entity.getCelular());
		bitacora.setCostoTotalCredito(entity.getCostoTotalCredito());
		bitacora.setDvAfiliado(dvAfiliado);
		bitacora.setFechaPrimerVencimiento(Utils.stringToDateBD(entity.getPagoPrimeraCuota()));
		bitacora.setGastoNotarial(entity.getGastoNotaria());
		bitacora.setImpuestoLTE(entity.getImpuesto());
		bitacora.setMontoCampana(entity.getMontoCampagna());
		bitacora.setMontoCuota(entity.getMontoCuota());
		bitacora.setMontoMensualSeguroCesantia(entity.getValorMensualSeguroCesantia());
		bitacora.setMontoMensualSeguroDesgravamen(entity.getValorMensualSeguroDesgravamen());
		bitacora.setMontoSolicitado(entity.getMontoSimulacion());
		bitacora.setNombre(entity.getNombreAfiliado());
		bitacora.setNroCuotas(entity.getCuotas());
		bitacora.setRutAfiliado(rutAfiliado);
		bitacora.setTasaInteresMensual(entity.getTasaInteresMensual());
		bitacora.setSucursal(entity.getSucursal());
		dao.insertBitacoraSimulacion(bitacora);
	}
	
	@Override
	public BitacoraGenesysDto saveBitacoraGenesys(AfiliadoVo entity, String fechaSeleccionada) throws Exception {
		BitacoraGenesysDto bitacora= new BitacoraGenesysDto();
		String[] rutdvAfiliado= entity.getRutAfiliado().split("-");
		int rutAfiliado= Integer.parseInt(rutdvAfiliado[0]);
		String dvAfiliado= rutdvAfiliado[1];
		
		//Se genera idEnconde para poder actualizar el registro luego de agendar
		String textEncode= entity.getRutAfiliado() + ":"+fechaSeleccionada;
		String idEncode= Base64.getEncoder().encodeToString(textEncode.getBytes());
		
		bitacora.setRutAfiliado(rutAfiliado);
		bitacora.setDvAfiliado(dvAfiliado);
		bitacora.setCelular(entity.getCelular());
		bitacora.setEstado(0);
		bitacora.setFechaAgenda(Utils.stringToDateTime(fechaSeleccionada));
		bitacora.setFechaCreacion(new Date());
		bitacora.setIdConversation("");
		bitacora.setIdEncode(idEncode);
		bitacora.setAutorizar(entity.getAutorizar());
		dao.insertBitacoraGenesys(bitacora);
		
		return bitacora;
	}
	
	public void updateBitacoraGenesys(BitacoraGenesysDto data) throws Exception{
		dao.updateBitacoraGenesys(data);
	}

	@Override
	public void saveBitacoraEvaluadorAIS(SalidaEvaluadorModeloAISVo entity) throws Exception {
		BitacoraEvaluadorAISDto bitacora= new BitacoraEvaluadorAISDto();
		bitacora.setRutAfiliado(entity.getRutAfiliado());
		bitacora.setRutEmpresa(entity.getRutEmpresa());
		bitacora.setDictamen(entity.getDictamen());
		bitacora.setMontoAprobado(entity.getMontoAprobado());
		bitacora.setPlazoAprobado(entity.getPlazoAprobado());
		bitacora.setOpcionMonto1(entity.getOpcionMonto1());
		bitacora.setOpcionPlazo1(entity.getOpcionPlazo1());
		bitacora.setOpcionMonto2(entity.getOpcionMonto2());
		bitacora.setOpcionPlazo2(entity.getOpcionPlazo2());
		bitacora.setOpcionMonto3(entity.getOpcionMonto3());
		bitacora.setOpcionPlazo3(entity.getOpcionPlazo3());
		bitacora.setEqEstadoCivil(entity.getEqEstadoCivil());
		bitacora.setEqFechaNacimient(entity.getEqFechaNacimient());
		bitacora.setEqNacionalidad(entity.getEqNacionalidad());
		bitacora.setPoliticas(entity.getPoliticas());
		bitacora.setReglasNegocio(entity.getReglasNegocio());
		bitacora.setAlerta(entity.getAlerta());
		bitacora.setaAPerfilRiesEmpr(entity.getAAPerfilRiesEmpr());
		bitacora.setaASegmento(entity.getAASegmento());
		bitacora.setSegmento3(entity.getSegmento3());
		bitacora.setvCPerfilPersona(entity.getVCPerfilPersona());
		bitacora.setPriorizacion(entity.getPriorizacion());
		bitacora.setVecesRenta(entity.getVecesRenta());
		bitacora.setRenta(entity.getRenta());
		bitacora.setMaxMonto(entity.getMaxMonto());
		bitacora.setMontoSimulado(entity.getMontoSimulado());
		bitacora.setPlazoSimulado(entity.getPlazoSimulado());
		bitacora.setCotizacion(entity.getCotizacion());
		bitacora.setMaxPorcDesc(entity.getMaxPorcDesc());
		bitacora.setMonto(entity.getMonto());
		bitacora.setMontoCuotaSol(entity.getMontoCuotaSol());
		bitacora.setMontoCuotaSim(entity.getMontoCuotaSim());
		bitacora.setMaxDescuento12(entity.getMaxDescuento12());
		bitacora.setMaxDescuento24(entity.getMaxDescuento24());
		bitacora.setMaxDescuento36(entity.getMaxDescuento36());
		bitacora.setMaxDescuento48(entity.getMaxDescuento48());
		bitacora.setMaxDescuento60(entity.getMaxDescuento60());
		bitacora.setaAPensionadoPBS(entity.getAAPensionadoPBS());
		bitacora.setaASexo(entity.getAASexo());		
		
		Date myDate = new Date();
		String fechaActual = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(myDate);
		
		bitacora.setFechaEvaluacion(fechaActual);
		bitacora.setCodigoError(entity.getCodigoError());
		
		dao.insertBitacoraAIS(bitacora);
		
	}

	@Override
	public void saveBitacoraSeguimiento(AfiliadoVo afiliado, String Accion, String Servicio, String Resultado) throws Exception {
		BitacoraSeguimientoDto bitacora = new BitacoraSeguimientoDto();
		
		String rutCompletoAfiliado = afiliado.getRutAfiliado();
		String[] rutAfiliadoArray = rutCompletoAfiliado.split("-");
		String rutAfiliado = rutAfiliadoArray[0];
		String dvAfiliado = rutAfiliadoArray[1];
		
		String rutCompletoEmpresa = afiliado.getRutEmpresa();
		String[] rutEmpresaArray = rutCompletoEmpresa.split("-");
		String rutEmpresa = rutEmpresaArray[0];
		String dvEmpresa = rutEmpresaArray[1];
		
		Date myDate = new Date();
		String fechaActual = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(myDate);
		
		bitacora.setRutAfiliado(rutAfiliado);
		bitacora.setdVRutAfiliado(dvAfiliado);
		bitacora.setRutEmpresa(rutEmpresa);
		bitacora.setdVRutEmpresa(dvEmpresa);
		bitacora.setFechaSeguimiento(fechaActual);
		bitacora.setAccion(Accion);
		bitacora.setServicio(Servicio);
		bitacora.setResultado(Resultado);
		
		dao.insertBitacoraSeguimiento(bitacora);
		
	}
	
	
}
