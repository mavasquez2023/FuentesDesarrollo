package cl.laaraucana.ventafullweb.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import cl.laaraucana.servicios.simulaCredito.Detalle;
import cl.laaraucana.ventafullweb.dto.SucursalesDto;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.DetalleCuotasVo;
import cl.laaraucana.ventafullweb.vo.SalidainfoAfiliadoVO;

public class AfiliadoUtils {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public static AfiliadoVo agregaInfoAfiliado(AfiliadoVo afiliado, cl.laaraucana.servicios.validaCliente.Response salida) {
		afiliado.setRutAfiliado(salida.getRutAfiliado());
		afiliado.setNombreAfiliado(salida.getNombreAfiliado());
		int afiliadoVigente = salida.getAfiliadoVigente()!=null?Integer.parseInt(salida.getAfiliadoVigente()):0;
		afiliado.setAfiliadoVigente(afiliadoVigente);
		int afiliadoFallecido = salida.getFallecido()!=null?Integer.parseInt(salida.getFallecido()):0;
		afiliado.setAfiliadoFallecido(afiliadoFallecido);
		afiliado.setRazonSocial(salida.getRazonSocialEmpresa());
		afiliado.setCampagnaVigente(Integer.parseInt(salida.getCampanaVigente()));
		afiliado.setMontoCampagna(Integer.parseInt(salida.getMontoCampana()));
		afiliado.setSegmento(salida.getSegmento());
		afiliado.setRutEmpresa(salida.getRutEmpresaCampana()!=null?salida.getRutEmpresaCampana():afiliado.getRutEmpresa());
		afiliado.setIdCampagna(Integer.parseInt(salida.getIdAnexoCampana()));
		int licenciaActiva = salida.getLicenciaMedica()!=null?Integer.parseInt(salida.getLicenciaMedica()):0;
		afiliado.setLicenciaMedicaActiva(licenciaActiva);
		afiliado.setSucursal(salida.getSucursalSimulacion());
		int creditosVigentes = salida.getCreditosVigentes()!=null?Integer.parseInt(salida.getCreditosVigentes()):0;
		afiliado.setCreditosVigentes(creditosVigentes);
		int insolvencia = salida.getInsolvenciaInterna()!=null?Integer.parseInt(salida.getInsolvenciaInterna()):0;
		afiliado.setInsolvenciaInterna(insolvencia);
		int marcaDesafiliacion = salida.getMarcaDesafiliacion()!=null?Integer.parseInt(salida.getMarcaDesafiliacion()):0;
		afiliado.setMarcaDesafiliacion(marcaDesafiliacion);
		afiliado.setDeudaCRF(Integer.parseInt( salida.getDeudaCRF()));
		int vigente = salida.getAfiliadoVigente()!=null?Integer.parseInt(salida.getAfiliadoVigente()):0;
		afiliado.setFechaVigencia(salida.getFechaVigencia());
		afiliado.setAfiliadoVigente(vigente);
		
		return afiliado;
	}
	
	
	public static AfiliadoVo agregaInfoAfiliado(AfiliadoVo afiliado, SalidainfoAfiliadoVO salida) {
		afiliado.setNombreAfiliado(salida.getNombreCompleto());
		return afiliado;
	}

	
	public static AfiliadoVo agregaInfoCreditoAfiliado(AfiliadoVo afiliado, cl.laaraucana.servicios.validaCredito.Response salida) {
		try {
			/*int codRespuesta = salida.getCodigoRespuesta();
			String rutAfiliado = salida.getRUTAFILIADO();
			String rutEmpresa = salida.getRUTEMPRESA();
			String afiliadoVigente = salida.getAFILIADOVIGENTE();
			String riesgoTotal = Double.toString(salida.getRIESGOTOTAL());
			String vecesRenta = salida.getVECESRENTA();
			String superaRenta = salida.getSUPERAVECESRENTA();
			String maximoEndeudamiento = salida.getMAXIMOENDEUDAMIENTO();
			String porcentajeEndeudamiento = Double.toString(salida.getPORCENTAJEENDEUDAMIENTO());
			String endeudamientoNormativo = salida.getSUPERAMAXENDEUDAMIENTONORMATIVO();
			String fueraRangoEdad = salida.getFUERARANGOEDAD();
			String fueraRangoAntiguedad = salida.getFUERARANGOANTIGUEDAD();
			String afiliadoPep = salida.getAFILIADOPEP();
			String estadoInsolvencia = salida.getESTADOINSOLVENCIA();
			String bloqueoCredito = salida.getAFILBLOQUEOALCREDITO();
			String empresaBloqueoCredito = salida.getEMPBLOQUEOALCREDITO();
			String deudaIntercaja = salida.getDEUDAINTERCAJA();
			String licenciaActiva = salida.getLICENCIAACTIVA();
			String diasLicencia = salida.getDIASLICENCIA();
			String creditosCastigados = salida.getCREDITOSCASTIGASDOS();
			String creditosAfpProvida = salida.getCREDITOSAFPPROVIDA();
			String creditoMorosidad = salida.getCREDITOMOROSIDAD();
			String pensionadoAfp = salida.getPENSIONADOAFP();
			String afiliadoPublico = salida.getAFILIADOPUBLICO15();
			String datosObligatorios = salida.getDATOSOBLIGATORIOS();
			String calificaUChile = salida.getCALIFICA_UCHILE();
			String exitoSinacofi = salida.getEXITOSINACOFI();
			String afiliadoMantenedor = salida.getAFILIADOMANTENEDOR();
			String empresaMantenedor = salida.getEMPRESAMANTENEDOR();
			String sexo = salida.getSEXO();*/
			
			String funcionarioCaja = salida.getFUNCIONARIOCAJA();
			if(funcionarioCaja != null) {
				int val = Integer.parseInt(funcionarioCaja);
				afiliado.setAfiliadoFuncionario(val);
			}
			
			String fechaNacimiento = salida.getFECHANACIMIENTO();
			if(fechaNacimiento != null) {
				afiliado.setFechaNacimiento(fechaNacimiento);
			}else {
				afiliado.setFechaNacimiento("");
			}
			
			String estadoCivil = salida.getESTADOCIVIL();
			if(estadoCivil != null) {
				afiliado.setEstadoCivil(estadoCivil);
			}else {
				afiliado.setEstadoCivil("");
			}
			
			String tipoContrato = salida.getTIPOCONTRATO();
			if(tipoContrato != null) {
				afiliado.setTipoContrato(tipoContrato);
			}else {
				afiliado.setTipoContrato("");
			}
			
			String correoAfiliado = salida.getEMAIL();
			if(correoAfiliado != null) {
				afiliado.setCorreo(correoAfiliado);
			}else {
				afiliado.setCorreo("");
			}
			
			String telefono = salida.getTELEFONO();
			if(telefono != null) {
				afiliado.setTelefono(telefono);
			}else {
				afiliado.setTelefono("");
			}
			
			String direccion = salida.getDIRECCION();
			if(direccion != null) {
				afiliado.setDireccion(direccion);
			}else {
				afiliado.setDireccion("");
			}
			
			String numeroDireccion = salida.getNUMERO();
			if(numeroDireccion != null) {
				afiliado.setNumeroDireccion(numeroDireccion);
			}else {
				afiliado.setNumeroDireccion("");
			}
			
			String depto = salida.getDEPTO();
			if(depto != null) {
				afiliado.setDpto(depto);
			}else {
				afiliado.setDpto("");
			}
			
			String villaPoblacion = salida.getVILLAPOBLACION();
			if(villaPoblacion != null) {
				afiliado.setVillaPoblacion(villaPoblacion);
			}else {
				afiliado.setVillaPoblacion("");
			}
			
			String codigoRegion = salida.getCODIGOREGION();
			if(codigoRegion != null) {
				afiliado.setCodigoRegion(codigoRegion);
			}else {
				afiliado.setCodigoRegion("");
			}
			
			String codigoProvincia = salida.getCODIGOPROVINCIA();
			if(codigoProvincia != null) {
				afiliado.setCodigoProvincia(codigoProvincia);
			}else {
				afiliado.setCodigoProvincia("");
			}
			
			String codigoComuna = salida.getCODIGOCOMUNA();
			if(codigoComuna != null) {
				afiliado.setCodigoComuna(codigoComuna);
			}else {
				afiliado.setCodigoComuna("");
			}
			
			String bancoNumeroCuenta = salida.getNUMEROCUENTA();
			if(bancoNumeroCuenta != null) {
				afiliado.setBancoNumeroCuenta(bancoNumeroCuenta);
			}else {
				afiliado.setBancoNumeroCuenta("");
			}
			
			String bancoTipoCuenta = salida.getTIPOCUENTA();
			if(bancoTipoCuenta != null) {
				afiliado.setBancoTipoCuenta(bancoTipoCuenta);
			}else {
				afiliado.setBancoTipoCuenta("");
			}
			
			String bancoCodigoBanco = salida.getCODIGOBANCO();
			if(bancoCodigoBanco != null) {
				afiliado.setBancoCodigoBanco(bancoCodigoBanco);
			}else {
				afiliado.setBancoCodigoBanco("");
			}
			
			String nacionalidad = salida.getNACIONALIDAD();
			if(nacionalidad != null) {
				afiliado.setNacionalidad(nacionalidad);
			}else {
				afiliado.setNacionalidad("");
			}
			
			String superaVecesRenta = salida.getSUPERAVECESRENTA();
			if(superaVecesRenta != null) {
				afiliado.setSuperaVecesRenta(superaVecesRenta);
			}else {
				afiliado.setSuperaVecesRenta("");
			}
			
			String superaMaxEndeudNorm = salida.getSUPERAMAXENDEUDAMIENTONORMATIVO();
			if(superaMaxEndeudNorm != null) {
				afiliado.setSuperaMaxEndeudNorm(superaMaxEndeudNorm);
			}else {
				afiliado.setSuperaMaxEndeudNorm("");
			}
			
			double rentaMonto1 = salida.getMONTO1();
			double rentaMonto2 = salida.getMONTO2();
			double rentaMonto3 = salida.getMONTO3();
			
			int rentaFinal = 0;
			
			String rentaPromedio = "0";
			
			if(rentaMonto1 == 0.0 || rentaMonto2 == 0.0 || rentaMonto3 == 0.0 ) {
				rentaFinal = 0;
			} else {
				rentaFinal = (int) Math.round((((rentaMonto1 + rentaMonto2 + rentaMonto3)/3)*0.77));
			}
			
			rentaPromedio = String.valueOf(rentaFinal);
			
			afiliado.setRentaPromedio(rentaPromedio);
			
			return afiliado;
		} catch(Exception e) {
			return afiliado;
		}
	}
	
	
	public static AfiliadoVo agregaInfoAfiliado(AfiliadoVo afiliado, cl.laaraucana.servicios.simulaCredito.Response salida) {
		try {
			List<DetalleCuotasVo> detalleCuotas = new ArrayList<DetalleCuotasVo>();
			BigDecimal montoCuota = salida.getMONTO_CUOTA();
			BigDecimal tasaInteresMensual = salida.getTASA_INT_MENSUAL();
			BigDecimal tasaInteresAnual = salida.getTASA_INT_ANUAL();
			BigDecimal cae = salida.getCAE();
			BigDecimal impuesto = salida.getIMPUESTO();
			BigDecimal gastoNotarial = salida.getGASTO_NOTARIAL();
			BigDecimal ctc = salida.getCTC();
			BigDecimal costoMensualDesgravamen = salida.getCOSTO_MENSUAL_DESGRAVAMEN();
			BigDecimal costoTotalDesgravamen = salida.getCOSTO_TOTAL_DESGRAVAMEN();
			BigDecimal costoMensualCesantia = salida.getCOSTO_MENSUAL_CESANTIA();
			BigDecimal costoTotalCesantia = salida.getCOSTOS_TOTAL_CESANTIA();
			for(Detalle detalle: salida.getDETALLE()) {
				DetalleCuotasVo detalleCuota = new DetalleCuotasVo();
				int numCuota = detalle.getNUM_CUOTA();
				String fechaVencimiento = detalle.getFECHA_VENCIMIENTO();
				int montoCuotaDetalle = parseInt(detalle.getMONTO_CUOTA());
				int montoInteres = parseInt(detalle.getMONTO_INTERES());
				int seguroDesgravamen = parseInt(detalle.getSEGURO_DESGRAVAMEN());
				int seguroCesantia = parseInt(detalle.getSEGURO_CESANTIA());
				int saldoCapital = parseInt(detalle.getSALDO_CAPITAL());
				int capitalCuota = parseInt(detalle.getCAPITAL_CUOTA());
				detalleCuota.setNumCuota(Integer.toString(numCuota));
				detalleCuota.setVencimiento(fechaVencimiento);
				detalleCuota.setValorCuota(String.valueOf(montoCuotaDetalle));
				detalleCuota.setMontoInteres(String.valueOf(montoInteres));
				detalleCuota.setSeguroDesgravamen(String.valueOf(seguroDesgravamen));
				detalleCuota.setSeguroCesantia(String.valueOf(seguroCesantia));
				detalleCuota.setSaldoCapital(String.valueOf(saldoCapital));
				detalleCuota.setCapitalCuota(String.valueOf(capitalCuota));
				detalleCuotas.add(detalleCuota);
			}
			afiliado.setMontoCuota(parseInt(montoCuota));
			afiliado.setTasaInteresMensual(tasaInteresMensual.toString());
			afiliado.setCae(cae.toString());
			afiliado.setCostoTotalCredito(parseInt(ctc));
			afiliado.setImpuesto(parseInt(impuesto));
			afiliado.setGastoNotaria(parseInt(gastoNotarial));
			afiliado.setValorMensualSeguroCesantia(parseInt(costoMensualCesantia));
			afiliado.setValorMensualSeguroDesgravamen(parseInt(costoMensualDesgravamen));
			afiliado.setPagoPrimeraCuota(AfiliadoUtils.formateaFecha(salida.getFECHA_PRIMER_VENCIMIENTO()));
			afiliado.setTasaInteresAnual(tasaInteresAnual.toString());
			afiliado.setCostoTotalDesgravamen(parseInt(costoTotalDesgravamen));
			afiliado.setCostoTotalCesantia(parseInt(costoTotalCesantia));
			afiliado.setDetalleCuotas(detalleCuotas);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return afiliado;
	}
	
	public static int parseInt(BigDecimal val) {
		try {
			if(val==null) {
				val=new BigDecimal(0);
			}
			double valor = Double.parseDouble(val.toString());
			return (int) valor;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String formateaVal(String val) {
		try {
			Double valor = Double.parseDouble(val);
			DecimalFormat formatea = new DecimalFormat("###,###");
			return formatea.format(valor);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formateaVal(double val) {
		try {
			DecimalFormat formatea = new DecimalFormat("###,###");
			return formatea.format(val);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String formateaFecha(String fecha) {
		String agno = fecha.substring(0, 4);
		String mes = fecha.substring(4, 6);
		String dia = fecha.substring(6, 8);
		return dia + "-" + mes + "-" + agno;
	}
	
	
	public static String getTipoAfiliado(String segmento) {
		//ZAFPEN: Pensionado / ZAFTRA: Trabajador
		String nulo = null;
		if(segmento != null && segmento.length() != 0) {
			if(segmento.equals("PENSIONADOS")) {
				return "Pensionado";
			} else if(segmento.equals("TRABAJADORES")) {
				return "Trabajador";
			} else {
				return segmento;
			}
		} else {
			return nulo;
		}
	}
	
	
	public static String nombreSucursal(List<SucursalesDto> sucursales, String codigo) {
		for(SucursalesDto suc : sucursales) {
			if(suc.getCodigo().equals(codigo)) {
				return suc.getDescripcion();
			}
		}
		return null;
	}
	
	
	public static String formateaRut(String rut) {
		String[] rutCompleto = rut.split("-");
		return formateaVal(rutCompleto[0]) + "-" + rutCompleto[1];
	}
}
