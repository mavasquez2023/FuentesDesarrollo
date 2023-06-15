package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class FiniquitoLocalUtil {

	protected  Logger log = Logger.getLogger(this.getClass());
	private double totalSocial = 0;
	private double totalEspecial = 0;
	private double totalEducacion = 0;
	
	public List<SalidaFiniquitoVO> getCreditosVigentesSocialesList(List<SalidaFiniquitoVO> listaCreditos) {
		//totalSocial = 0;
		List<SalidaFiniquitoVO> salidaList = new ArrayList<SalidaFiniquitoVO>();
		for (SalidaFiniquitoVO credito : listaCreditos) {
			if (!credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim())
					&& !credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())) {
				salidaList.add(credito);
				double gastoCobranza = (credito.getSumaGCob() != null && credito.getSumaGCob().length()==0)?0:Double.parseDouble(credito.getSumaGCob());
				//totalSocial += credito.getSaldoCapital()+credito.getGravamenes()+gastoCobranza;
				totalSocial += credito.getMontoTotal();
				System.out.println("==================== el monto total essss "+credito.getMontoTotal());
			}
		}
		return salidaList;
	}
	
	public List<SalidaFiniquitoVO> getCreditosVigentesEspecialesList(List<SalidaFiniquitoVO> listaCreditos) {
		//totalEspecial = 0;
		List<SalidaFiniquitoVO> salidaList = new ArrayList<SalidaFiniquitoVO>();
		for (SalidaFiniquitoVO credito : listaCreditos) {
			if (credito.getTipoCredito()!= null && credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim())) {
				salidaList.add(credito);
				double gastoCobranza = (credito.getSumaGCob() != null && credito.getSumaGCob().length()==0)?0:Double.parseDouble(credito.getSumaGCob());
				//totalEspecial += credito.getSaldoCapital()+credito.getGravamenes()+gastoCobranza;
				totalEspecial += credito.getMontoTotal();
			}
		}
		return salidaList;
	}
	

	public List<SalidaFiniquitoVO> getCreditosVigentesEducacionList(List<SalidaFiniquitoVO> listaCreditos) {
		List<SalidaFiniquitoVO> salidaList = new ArrayList<SalidaFiniquitoVO>();
		for (SalidaFiniquitoVO credito : listaCreditos) {
			if (credito.getTipoCredito() != null && credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())) {
				try {
					//credito.setListaDetalle(ServiciosFiniquitoAs400.obtenerDetalleCreditosVigentesAs400(credito.getFolio()));
					credito.setListaCuotasUltimos(getUltimosEducacionList(credito));
					salidaList.add(credito);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("recorriendo lista");
		for (SalidaFiniquitoVO salida : salidaList) {
			System.out.println("el folio principal "+salida.getFolio());
			for (SalidaDetalleFiniquitoVO sal : salida.getListaCuotasUltimos()) {
				System.out.println("n cuotas "+sal.getCuotaCount());
				System.out.println("folio cuota "+sal.getFolio());
			}
		}
		return salidaList;
	}
	
	public List<SalidaDetalleFiniquitoVO> getUltimosEducacionList(SalidaFiniquitoVO credito) throws Exception {
		// totalEducacion = 0;
		List<SalidaDetalleFiniquitoVO> listaCuotasUltimos = new ArrayList<SalidaDetalleFiniquitoVO>();
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////utilizar
		// los valores

		// int cuotaVigente = Integer.parseInt("0"); //cambiar
		// int cuotaHasta = Integer.parseInt("0"); //cambiar
		int cuotaDesde = Integer.parseInt(credito.getCuotaDesde());
		int cuotaHasta = Integer.parseInt(credito.getCuotaHasta());
		int cantQueda = cuotaHasta - cuotaDesde;
		int cantidadInicial = 1;
		//int contador = 1;
		int repeticiones = 0;
		if (cantQueda >= 6) {
			repeticiones = 6;
			cantidadInicial = cuotaHasta - 6;
		} else {
			repeticiones = cantQueda;
			cantidadInicial = cuotaHasta-cantQueda;
		}
		SalidaDetalleFiniquitoVO detalle = null;
		System.out.println("la cantidad hasta es "+cuotaHasta);
		System.out.println("la cuota desde es "+cuotaDesde);
		System.out.println("la cantidad que queda es "+cantQueda);
		System.out.println("la cantidad inicial es "+cantidadInicial+" hasta "+cuotaHasta);
		for (SalidaDetalleFiniquitoVO salidaDetalleCreditoVigenteVO : credito.getListaDetalle().getListaCuotas()) {
			for (int i = cantidadInicial; i <= cuotaHasta; i++) {
				//if (salidaDetalleCreditoVigenteVO.getnCuota() != null && cuotaVigente <= Integer.parseInt(salidaDetalleCreditoVigenteVO.getnCuota())) {
				detalle = new SalidaDetalleFiniquitoVO();
				detalle.setCuotaCount(i);
				detalle.setFolio(salidaDetalleCreditoVigenteVO.getFolio());
				detalle.setMonto(salidaDetalleCreditoVigenteVO.getMonto());
				detalle.setMontoGravamen(salidaDetalleCreditoVigenteVO.getMontoGravamen());
				//salidaDetalleCreditoVigenteVO.setCuotaCount(i);
				listaCuotasUltimos.add(detalle);
				totalEducacion += detalle.getMonto() + detalle.getMontoGravamen();
					//contador++;
				//}
			}
			break;
		}
		/*for (SalidaDetalleFiniquitoVO salidaDetalleCreditoVigenteVO : credito.getListaDetalle().getListaCuotas()) {
			if(salidaDetalleCreditoVigenteVO.getnCuota() != null && cuotaVigente <= Integer.parseInt(salidaDetalleCreditoVigenteVO.getnCuota())){
			salidaDetalleCreditoVigenteVO.setCuotaCount(contador+1);
				listaCuotasUltimos.add(salidaDetalleCreditoVigenteVO);
				totalEducacion += salidaDetalleCreditoVigenteVO.getMonto()+salidaDetalleCreditoVigenteVO.getMontoGravamen();
				contador++;
			}
			if(contador >= 6){
				break;
			}
		}*/

		return listaCuotasUltimos;
	}
	
	
	public DetalleEmpresaAfiliado obtenerDetalleEmpresa(String rutEmpresa, UsuarioAfiliadoVO user){
		DetalleEmpresaAfiliado detalle = null;
		if(user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			for (DetalleEmpresaAfiliado det : user.getDetalleEmpresaList()) {
				if(rutEmpresa.equals(det.getRutEmpresa())){
					detalle = new DetalleEmpresaAfiliado();
					detalle.setFechaAfiliacion(det.getFechaAfiliacion());
					detalle.setNombreEmpresa(det.getNombreEmpresa());
					detalle.setRutEmpresa(det.getRutEmpresa());
					detalle.setTipoAfiliado(det.getTipoAfiliado());
					break;
				}
			}
		}
		
		return detalle;
	}
	
	public static  boolean isFormatoFechaFiniquitoValida(String fechaFiniquito){
		boolean salida = false;
		try {
			Utils.pasaFechaWEBaSAP(fechaFiniquito);
			salida = true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return salida;
	}
	public static boolean isFechaFiniquitoValida(String fechaFiniquito){
		boolean salida = false;
		
		try {
			Utils.pasaFechaWEBaSAP(fechaFiniquito);
			String hoy = Utils.dateToString(new Date());
			Date dateHoy = Utils.stringToDate(hoy);
			Date dateFechaFiniquito = Utils.stringToDate(fechaFiniquito);
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
			String lastDayOfMonth = Utils.dateToString(cal.getTime());
			Date dateLastDayOfMonth = Utils.stringToDate(lastDayOfMonth);
			
			//System.out.println("el ultimo dia del mes es "+dateLastDayOfMonth);
			//System.out.println("el dia de hoy sin hora ni na "+dateHoy);
			//System.out.println("la fecha de finiquito en date es "+dateFechaFiniquito);
			if((dateFechaFiniquito.before(dateLastDayOfMonth) || dateFechaFiniquito.equals(dateLastDayOfMonth)) && (dateFechaFiniquito.after(dateHoy) || dateFechaFiniquito.equals(dateHoy))){
				salida = true; 
			}
			//Date hoy = Utils.
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		return salida;
	}

	
	public double getTotalSocial() {
		return totalSocial;
	}

	public double getTotalEspecial() {
		return totalEspecial;
	}

	public double getTotalEducacion() {
		return totalEducacion;
	}	
	
	
}//fin clase
