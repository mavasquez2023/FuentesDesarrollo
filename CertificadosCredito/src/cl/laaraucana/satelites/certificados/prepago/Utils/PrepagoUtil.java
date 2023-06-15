package cl.laaraucana.satelites.certificados.prepago.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;

public class PrepagoUtil {
	
	private double totalSocial = 0;
	private double totalEspecial = 0;
	private double totalEducacion = 0;
	private double sumaTotal;
	
	
	public List<SalidaCreditoPrepagoVO> getCreditosPrepagoSocialesList (List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		totalSocial = 0;
		List<SalidaCreditoPrepagoVO> salidaList = new ArrayList<SalidaCreditoPrepagoVO>();
		for (SalidaCreditoPrepagoVO credito : listaPrepagosCompleta) {
			if (credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.social").trim())||
					(!credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim())&& 
					!credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim())&&
					!credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap").trim()) &&
					!credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial.sap").trim()))) {
				salidaList.add(credito);
				totalSocial += Double.valueOf(credito.getTotal());
			}
		}
		return salidaList;
	}
	
	public List<SalidaCreditoPrepagoVO> getCreditosPrepagoEspecialList (List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		totalEspecial = 0;
		List<SalidaCreditoPrepagoVO> salidaList = new ArrayList<SalidaCreditoPrepagoVO>();		
		for (SalidaCreditoPrepagoVO credito : listaPrepagosCompleta) {
			if (credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim())||
					credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial.sap").trim())) {
				salidaList.add(credito);
				totalEspecial += Double.valueOf(credito.getTotal());
			}
		}
		return salidaList;
	}
	
	public List<SalidaCreditoPrepagoVO> getCreditosPrepagoCesList (List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		totalEducacion = 0;
		List<SalidaCreditoPrepagoVO> salidaList = new ArrayList<SalidaCreditoPrepagoVO>();
		for (SalidaCreditoPrepagoVO credito : listaPrepagosCompleta) {
			if (credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim()) ||
					credito.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap").trim())) {
				salidaList.add(credito);
				totalEducacion += Double.valueOf(credito.getTotal());
			}
			
		}
		return salidaList;
	}

//	public double getTotalSocial() {
//		return totalSocial;
//	}
//
//	public double getTotalEspecial() {
//		return totalEspecial;
//	}
//
//	public double getTotalEducacion() {
//		return totalEducacion;
//	}

	public double getSumaTotal() {
		double suma = this.totalSocial+this.totalEducacion+this.totalEspecial;
		if (suma!=0) {
			this.sumaTotal=suma;
		}
		return sumaTotal;
	}

//	public void setSumaTotal() {
//		this.sumaTotal = this.totalSocial+this.totalEducacion+this.totalEspecial;
//	}
	
	public static String getUltimoDiaYMes(){
		String ultimoDiaYMes = null;
		Calendar cal = Calendar.getInstance();
		int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		switch(mes){
			case 0:{ultimoDiaYMes=dia+" de Enero";break;}
			case 1:{ultimoDiaYMes=dia+" de Febrero";break;}
			case 2:{ultimoDiaYMes=dia+" de Marzo";break;}
			case 3:{ultimoDiaYMes=dia+" de Abril";break;}
			case 4:{ultimoDiaYMes=dia+" de Mayo";break;}
			case 5:{ultimoDiaYMes=dia+" de Junio";break;}
			case 6:{ultimoDiaYMes=dia+" de Julio";break;}
			case 7:{ultimoDiaYMes=dia+" de Agosto";break;}
			case 8:{ultimoDiaYMes=dia+" deSeptiembre";break;}
			case 9:{ultimoDiaYMes=dia+" de Octubre";break;}
			case 10:{ultimoDiaYMes=dia+" de Noviembre";break;}
			case 11:{ultimoDiaYMes=dia+" de Diciembre";break;}
		}
		return ultimoDiaYMes;
	}
	
	public static String getMesYAno(){
		String mesYAno = null;
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);;
		int mes = cal.get(Calendar.MONTH);
		switch(mes){
			case 0:{mesYAno="Enero del "+ano;break;}
			case 1:{mesYAno="Febrero del "+ano;break;}
			case 2:{mesYAno="Marzo del "+ano;break;}
			case 3:{mesYAno="Abril del "+ano;break;}
			case 4:{mesYAno="Mayo del "+ano;break;}
			case 5:{mesYAno="Junio del "+ano;break;}
			case 6:{mesYAno="Julio del "+ano;break;}
			case 7:{mesYAno="Agosto del "+ano;break;}
			case 8:{mesYAno="Septiembre del "+ano;break;}
			case 9:{mesYAno="Octubre del "+ano;break;}
			case 10:{mesYAno="Noviembre del "+ano;break;}
			case 11:{mesYAno="Diciembre del "+ano;break;}
		}
		return mesYAno;
	}
	
	public static String getTipoCredito (String tipoCreditoRepactar){
		String tipoCredito;
		if(tipoCreditoRepactar.equals("062")){
			tipoCredito = CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior");
		}else if (tipoCreditoRepactar.equals("063")) {
			tipoCredito = CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial");
		}else{
			tipoCredito = CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.social");
		}
		return tipoCredito;
	} 
	
public SalidaListaCreditoPrepagoFoliosVO getCreditoCutonPDF(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		
		//List<SalidaListaCreditoPrepagoFoliosVO> listaSalida = new ArrayList<SalidaListaCreditoPrepagoFoliosVO>();

		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		
		SalidaListaCreditoPrepagoFoliosVO salidaVO = new SalidaListaCreditoPrepagoFoliosVO();
		List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
		
		String dvo0 = "Contrato";
		String dvo1 = "Fecha Otorgamiento";
		String dvo2 = "Tipo de Afiliado";
		String dvo3 = "Estado del Crédito";
		String dvo4 = "Tasa de Interés (%)";
		String dvo5 = "Prima Seguro Desgravamen";
		String dvo6 = "Prima Seguro Cesantía";
		String dvo7 = "Folio de Formulario #24";
		String dvo8 = "Monto Ley Timbre y Estampillas (LTE)";
		String dvo9 = "Monto Otorgado";
		String dvo10 = "Monto Cuota Descuento";
		String dvo11 = "Plazo al Otorgamiento";
		String dvo12 = "Total Cuotas Pagadas";
		String dvo13 = "Total Cuotas Morosas";
		String dvo14 = "Total Cuotas por Vencer";
		String dvo15 = "Saldo Adeudado (1)";
		String dvo16 = "Interés Devengado";
		String dvo17 = "Saldo Capital Cuotas Futuras";
		String dvo18 = "Comisión de Prepago";
		String dvo19 = "Saldo Capital a Condonar(2)";
		String dtotales = "Total a pagar (*)(3)";
		
		int largo = listaPrepagosCompleta.size();

		SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO(dvo0);
		SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO(dvo1);
		SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO(dvo2);
		SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO(dvo3);
		SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO(dvo4);
		SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO(dvo5);
		SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO(dvo6);
		SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO(dvo7);
		SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO(dvo8);
		SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO(dvo9);
		SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO(dvo10);
		SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO(dvo11);
		SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO(dvo12);
		SalidaCreditoPrepagoFoliosVO vo13 = new SalidaCreditoPrepagoFoliosVO(dvo13);
		SalidaCreditoPrepagoFoliosVO vo14 = new SalidaCreditoPrepagoFoliosVO(dvo14);
		SalidaCreditoPrepagoFoliosVO vo15 = new SalidaCreditoPrepagoFoliosVO(dvo15);
		SalidaCreditoPrepagoFoliosVO vo16 = new SalidaCreditoPrepagoFoliosVO(dvo16);
		SalidaCreditoPrepagoFoliosVO vo17 = new SalidaCreditoPrepagoFoliosVO(dvo17);
		SalidaCreditoPrepagoFoliosVO vo18 = new SalidaCreditoPrepagoFoliosVO(dvo18);
		SalidaCreditoPrepagoFoliosVO vo19 = new SalidaCreditoPrepagoFoliosVO(dvo19);
		SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();
		totales.setDetalle(dtotales);


		SalidaCreditoPrepagoVO credito = listaPrepagosCompleta.get(0);
		vo0.setValorFolio1(credito.getFolio());
		vo0.setCabecera(true);
		vo1.setValorFolio1(credito.getFechaOtorgamiento());
		vo2.setValorFolio1(credito.getTipoBp());
		vo3.setValorFolio1(Configuraciones.getConfig("sap.estado.credito." + credito.getEstado()));
		vo4.setValorFolio1(String.valueOf(credito.getTasaInteres()));
		vo5.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
		vo6.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
		vo7.setValorFolio1(credito.getFolioForm24());
		vo8.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
		vo9.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
		vo10.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
		vo11.setValorFolio1(credito.getPlazo());
		vo12.setValorFolio1(credito.getCuotasPagadas());
		vo13.setValorFolio1(credito.getCuotasMorosas());
		vo14.setValorFolio1(credito.getCuotasFuturas());
		vo15.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoAdeudado()));
		vo16.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
		vo17.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapitalFuturo()));
		vo18.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
		vo19.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapitalCondonado()));
		totales.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));



		SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		salidaList.add(espacio);
		salidaList.add(vo0);
		salidaList.add(vo1);
		salidaList.add(vo2);
		salidaList.add(vo3);
		salidaList.add(vo4);
		salidaList.add(vo5);
		salidaList.add(vo6);
		salidaList.add(vo7);
		salidaList.add(vo8);
		salidaList.add(vo9);
		salidaList.add(vo10);
		salidaList.add(vo11);
		salidaList.add(vo12);
		salidaList.add(vo13);
		salidaList.add(vo14);
		salidaList.add(vo15);
		salidaList.add(vo16);
		salidaList.add(vo17);
		salidaList.add(vo18);
		salidaList.add(vo19);
		salidaList.add(totales);

		salidaVO.setSalidaList(salidaList);
		salidaVO.setTotales(totales);

		return salidaVO;
	}

public SalidaListaCreditoPrepagoFoliosVO getCreditoAcuerdoPagoPDF(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta, boolean cumpleAP){
		
		//List<SalidaListaCreditoPrepagoFoliosVO> listaSalida = new ArrayList<SalidaListaCreditoPrepagoFoliosVO>();

		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		
		SalidaListaCreditoPrepagoFoliosVO salidaVO = new SalidaListaCreditoPrepagoFoliosVO();
		List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
		
		String dvo0 = "Contrato";
		String dvo1 = "Fecha Otorgamiento";
		String dvo2 = "Tipo de Afiliado";
		String dvo3 = "Estado del Crédito";
		String dvo4 = "Tasa de Interés (%)";
		String dvo5 = "Prima Seguro Desgravamen";
		String dvo6 = "Prima Seguro Cesantía";
		String dvo7 = "Folio de Formulario #24";
		String dvo8 = "Monto Ley Timbre y Estampillas (LTE)";
		String dvo9 = "Monto Otorgado";
		String dvo10 = "Monto Cuota Descuento";
		String dvo11 = "Plazo al Otorgamiento";
		String dvo12 = "Total Cuotas Pagadas";
		String dvo13 = "Total Cuotas Morosas";
		String dvo14 = "Total Cuotas por Vencer";
		String dvo15 = "Saldo Adeudado (1)";
		String dvo16 = "Interés Devengado";
		String dvo17 = "Saldo Capital Cuotas Futuras";
		String dvo18 = "Comisión de Prepago";
		String dvo19 = "Saldo Capital a Condonar";
		String dtotales = "Total a pagar (*)(3)";
		if(cumpleAP){
			dvo19= dvo19 + " (2)";
		}else{
			dvo17= dvo17 + " (2)";
		}
		
		int largo = listaPrepagosCompleta.size();

		SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO(dvo0);
		SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO(dvo1);
		SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO(dvo2);
		SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO(dvo3);
		SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO(dvo4);
		SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO(dvo5);
		SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO(dvo6);
		SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO(dvo7);
		SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO(dvo8);
		SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO(dvo9);
		SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO(dvo10);
		SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO(dvo11);
		SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO(dvo12);
		SalidaCreditoPrepagoFoliosVO vo13 = new SalidaCreditoPrepagoFoliosVO(dvo13);
		SalidaCreditoPrepagoFoliosVO vo14 = new SalidaCreditoPrepagoFoliosVO(dvo14);
		SalidaCreditoPrepagoFoliosVO vo15 = new SalidaCreditoPrepagoFoliosVO(dvo15);
		SalidaCreditoPrepagoFoliosVO vo16 = new SalidaCreditoPrepagoFoliosVO(dvo16);
		SalidaCreditoPrepagoFoliosVO vo17 = new SalidaCreditoPrepagoFoliosVO(dvo17);
		SalidaCreditoPrepagoFoliosVO vo18 = new SalidaCreditoPrepagoFoliosVO(dvo18);
		SalidaCreditoPrepagoFoliosVO vo19 = new SalidaCreditoPrepagoFoliosVO(dvo19);
		SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();
		totales.setDetalle(dtotales);


		SalidaCreditoPrepagoVO credito = listaPrepagosCompleta.get(0);
		vo0.setValorFolio1(credito.getFolio());
		vo0.setCabecera(true);
		vo1.setValorFolio1(credito.getFechaOtorgamiento());
		vo2.setValorFolio1(credito.getTipoBp());
		vo3.setValorFolio1(Configuraciones.getConfig("sap.estado.credito." + credito.getEstado()));
		vo4.setValorFolio1(String.valueOf(credito.getTasaInteres()));
		vo5.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
		vo6.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
		vo7.setValorFolio1(credito.getFolioForm24());
		vo8.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
		vo9.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
		vo10.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
		vo11.setValorFolio1(credito.getPlazo());
		vo12.setValorFolio1(credito.getCuotasPagadas());
		vo13.setValorFolio1(credito.getCuotasMorosas());
		vo14.setValorFolio1(credito.getCuotasFuturas());
		vo15.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoAdeudado()));
		vo16.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
		vo17.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapitalFuturo()));
		vo18.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
		vo19.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapitalCondonado()));
		totales.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));



		SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		salidaList.add(espacio);
		salidaList.add(vo0);
		salidaList.add(vo1);
		salidaList.add(vo2);
		salidaList.add(vo3);
		salidaList.add(vo4);
		salidaList.add(vo5);
		salidaList.add(vo6);
		salidaList.add(vo7);
		salidaList.add(vo8);
		salidaList.add(vo9);
		salidaList.add(vo10);
		salidaList.add(vo11);
		salidaList.add(vo12);
		salidaList.add(vo13);
		salidaList.add(vo14);
		salidaList.add(vo15);
		salidaList.add(vo16);
		salidaList.add(vo17);
		salidaList.add(vo18);
		salidaList.add(vo19);
		salidaList.add(totales);

		salidaVO.setSalidaList(salidaList);
		salidaVO.setTotales(totales);

		return salidaVO;
	}

	public SalidaListaCreditoPrepagoFoliosVO getCreditoPrepagoPDF(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		
		//List<SalidaListaCreditoPrepagoFoliosVO> listaSalida = new ArrayList<SalidaListaCreditoPrepagoFoliosVO>();

		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		
		SalidaListaCreditoPrepagoFoliosVO salidaVO = new SalidaListaCreditoPrepagoFoliosVO();
		List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
		SalidaCreditoPrepagoVO credito = listaPrepagosCompleta.get(0);
		
		String dvo0 = "Contrato";
		String dvo1 = "Fecha Otorgamiento";
		String dvo2 = "Tipo de Afiliado";
		String dvo3 = "Estado del Crédito";
		String dvo4 = "Tasa de Interés (%)";
		String dvo5 = "Prima Seguro Desgravamen";
		String dvo6 = "Prima Seguro Cesantía";
		String dvo7 = "Folio de Formulario #24";
		String dvo8 = "Monto Ley Timbre y Estampillas (LTE)";
		String dvo9 = "Monto Otorgado";
		String dvo10 = "Monto cuota descuento";
		String dvo11 = "Plazo al Otorgamiento";
		String dvo12 = "Total Cuotas Pagadas";
		String dvo13 = "Total Cuotas Morosas";
		String dvo14 = "Total Cuotas por Vencer";
		String dvo15 = "N° Cuota(s) en Tránsito (1)";
		String dvo16 = "Total Cuotas en Tránsito (1)";
		//definición de moroso
		String dvo17 = "Saldo Adeudado (1)";
		String dvo18 = "Interés Devengado";
		String dvo19 = "Saldo Capital Cuotas Futuras";
		String dvo20 = "Comisión de Prepago";
		String dvo21 = "Monto Final Adeudado";
		//definición de moroso
		String dtotales = "Total a pagar (*)(2)";
		if(credito.getEstado().equals(Constants.CREDITO_VIGENTE)){
			//15 y 16 no se agregarán a la lista 
			dvo17 = "Saldo Adeudado (2)";
			dtotales = "Total a pagar (*)(3)";
		}
		
		int largo = listaPrepagosCompleta.size();

		SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO(dvo0);
		SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO(dvo1);
		SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO(dvo2);
		SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO(dvo3);
		SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO(dvo4);
		SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO(dvo5);
		SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO(dvo6);
		SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO(dvo7);
		SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO(dvo8);
		SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO(dvo9);
		SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO(dvo10);
		SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO(dvo11);
		SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO(dvo12);
		SalidaCreditoPrepagoFoliosVO vo13 = new SalidaCreditoPrepagoFoliosVO(dvo13);
		SalidaCreditoPrepagoFoliosVO vo14 = new SalidaCreditoPrepagoFoliosVO(dvo14);
		SalidaCreditoPrepagoFoliosVO vo15 = new SalidaCreditoPrepagoFoliosVO(dvo15);
		SalidaCreditoPrepagoFoliosVO vo16 = new SalidaCreditoPrepagoFoliosVO(dvo16);
		SalidaCreditoPrepagoFoliosVO vo17 = new SalidaCreditoPrepagoFoliosVO(dvo17);
		SalidaCreditoPrepagoFoliosVO vo18 = new SalidaCreditoPrepagoFoliosVO(dvo18);
		SalidaCreditoPrepagoFoliosVO vo19 = new SalidaCreditoPrepagoFoliosVO(dvo19);
		SalidaCreditoPrepagoFoliosVO vo20 = new SalidaCreditoPrepagoFoliosVO(dvo20);
		SalidaCreditoPrepagoFoliosVO vo21 = new SalidaCreditoPrepagoFoliosVO(dvo21);
		SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();
		totales.setDetalle(dtotales);


		
		vo0.setValorFolio1(credito.getFolio());
		vo0.setCabecera(true);
		vo1.setValorFolio1(credito.getFechaOtorgamiento());
		vo2.setValorFolio1(credito.getTipoBp());
		vo3.setValorFolio1(Configuraciones.getConfig("sap.estado.credito." + credito.getEstado()));
		vo4.setValorFolio1(String.valueOf(credito.getTasaInteres()));
		vo5.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
		vo6.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
		vo7.setValorFolio1(credito.getFolioForm24());
		vo8.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
		vo9.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
		vo10.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
		vo11.setValorFolio1(credito.getPlazo());
		vo12.setValorFolio1(credito.getCuotasPagadas());
		vo13.setValorFolio1(credito.getCuotasMorosas());
		vo14.setValorFolio1(credito.getCuotasFuturas());
		vo15.setValorFolio1(credito.getNumCuotasEnTransito());
		vo16.setValorFolio1("$ " + Utils.formateaDobleSinDecimal(credito.getMontoCuotasEntransito()));
		vo17.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoAdeudado()));
		vo18.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
		vo19.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapitalFuturo()));
		vo20.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
		vo21.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoFinalAdeudado()));
		totales.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));



		SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		salidaList.add(espacio);
		salidaList.add(vo0);
		salidaList.add(vo1);
		salidaList.add(vo2);
		salidaList.add(vo3);
		salidaList.add(vo4);
		salidaList.add(vo5);
		salidaList.add(vo6);
		salidaList.add(vo7);
		salidaList.add(vo8);
		salidaList.add(vo9);
		salidaList.add(vo10);
		salidaList.add(vo11);
		salidaList.add(vo12);
		salidaList.add(vo13);
		salidaList.add(vo14);
		if(credito.getEstado().equals(Constants.CREDITO_VIGENTE)){
			salidaList.add(vo15);
			salidaList.add(vo16);
		}
		salidaList.add(vo17);
		salidaList.add(vo18);
		salidaList.add(vo19);
		salidaList.add(vo20);
		salidaList.add(vo21);
		salidaList.add(totales);

		salidaVO.setSalidaList(salidaList);
		salidaVO.setTotales(totales);

		return salidaVO;
	}
	
	public SalidaListaCreditoPrepagoFoliosVO getCreditosConFolios(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta, boolean esPrepago){
		
		//List<SalidaListaCreditoPrepagoFoliosVO> listaSalida = new ArrayList<SalidaListaCreditoPrepagoFoliosVO>();

		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		
		SalidaListaCreditoPrepagoFoliosVO salidaVO = new SalidaListaCreditoPrepagoFoliosVO();
		List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
		
		String dvo0 = "Detalle";
		String dvo1 = "Fecha Otorgamiento";
		String dvo2 = "Tipo de afiliado";
		String dvo3 = "Línea de crédito";
		String dvo4 = "Folio de Formulario #24";
		String dvo4_1 = "Titular / Aval";
		String dvo4_2 = "Rol Pagador";
		String dvo5 = "Monto otorgado";
		String dvo6 = "Monto impuesto LTE";
		String dvo7 = "Prima Mensual Seg. Desgravamen";
		String dvo8 = "Prima Mensual Seg. Cesantía";
		String dvo9 = "Monto cuota descuento";
		String dvo10 = "Desde cuota vigente";
		String dvo11 = "Hasta cuota vigente";
		String dvo12 = "Total cuotas morosas";
		String dvo13 = "N° cuota en tránsito";
		//String dvo14 = "N° de cuota en tránsito";
		String dvo14 = "Gravámenes";
		String dvo15 = "Gastos de Cobranza";
		String dvo16 = "Honorarios";
		String dvo17 = "Interés devengado (*)";
		String dvo18 = "Saldo Capital";
		String dvo18_1 = "Saldo Intereses";
		String dvo18_2 = "Saldo Seguros";
		String dvo19 = "Comisión de Prepago";
		String dvo20 = "Total Diferido";
		String dvo21 = "Total Cuotas informadas (tránsito)";
		String dtotales = "Total a pagar (*)";
		
		int largo = listaPrepagosCompleta.size();
		if(largo == 0){
			SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO();
			vo0.setDetalle(dvo0);
			SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO();
			vo1.setDetalle(dvo1);
			SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO();
			vo2.setDetalle(dvo2);
			SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO();
			vo3.setDetalle(dvo3);
			SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO();
			vo4.setDetalle(dvo4);
			SalidaCreditoPrepagoFoliosVO vo4_1 = new SalidaCreditoPrepagoFoliosVO();
			vo4_1.setDetalle(dvo4_1);
			SalidaCreditoPrepagoFoliosVO vo4_2 = new SalidaCreditoPrepagoFoliosVO();
			vo4_2.setDetalle(dvo4_2);
			SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO();
			vo5.setDetalle(dvo5);
			SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO();
			vo6.setDetalle(dvo6);
			SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO();
			vo7.setDetalle(dvo7);
			SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO();
			vo8.setDetalle(dvo8);
			SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO();
			vo9.setDetalle(dvo9);
			SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO();
			vo10.setDetalle(dvo10);
			SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO();
			vo11.setDetalle(dvo11);
			SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO();
			vo12.setDetalle(dvo12);
			SalidaCreditoPrepagoFoliosVO vo13 = new SalidaCreditoPrepagoFoliosVO();
			vo13.setDetalle(dvo13);
			SalidaCreditoPrepagoFoliosVO vo14 = new SalidaCreditoPrepagoFoliosVO();
			vo14.setDetalle(dvo14);
			SalidaCreditoPrepagoFoliosVO vo15 = new SalidaCreditoPrepagoFoliosVO();
			vo15.setDetalle(dvo15);
			SalidaCreditoPrepagoFoliosVO vo16 = new SalidaCreditoPrepagoFoliosVO();
			vo16.setDetalle(dvo16);
			SalidaCreditoPrepagoFoliosVO vo17 = new SalidaCreditoPrepagoFoliosVO();
			vo17.setDetalle(dvo17);
			SalidaCreditoPrepagoFoliosVO vo18 = new SalidaCreditoPrepagoFoliosVO();
			vo18.setDetalle(dvo18);
			SalidaCreditoPrepagoFoliosVO vo18_1 = new SalidaCreditoPrepagoFoliosVO();
			vo18_1.setDetalle(dvo18_1);
			SalidaCreditoPrepagoFoliosVO vo18_2 = new SalidaCreditoPrepagoFoliosVO();
			vo18_2.setDetalle(dvo18_2);
			SalidaCreditoPrepagoFoliosVO vo19 = new SalidaCreditoPrepagoFoliosVO();
			vo19.setDetalle(dvo19);
			SalidaCreditoPrepagoFoliosVO vo20 = new SalidaCreditoPrepagoFoliosVO();
			vo20.setDetalle(dvo20);
			SalidaCreditoPrepagoFoliosVO vo21 = new SalidaCreditoPrepagoFoliosVO();
			vo21.setDetalle(dvo21);
			SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();
			totales.setDetalle(dtotales);
			
			
			salidaList.add(null);
			salidaList.add(vo0);
			salidaList.add(vo1);
			if(esPrepago){
				salidaList.add(vo2);
			}
			salidaList.add(vo3);
			salidaList.add(vo4);
			salidaList.add(vo4_1);
			salidaList.add(vo4_2);
			salidaList.add(vo5);
			salidaList.add(vo6);
			salidaList.add(vo7);
			salidaList.add(vo8);
			salidaList.add(vo9);
			salidaList.add(vo10);
			salidaList.add(vo11);
			salidaList.add(vo12);
			if(esPrepago){
				salidaList.add(vo13);
			}
			salidaList.add(vo14);
			salidaList.add(vo15);
			salidaList.add(vo16);
			salidaList.add(vo17);
			salidaList.add(vo18);
			salidaList.add(vo18_1);
			salidaList.add(vo18_2);
			if(esPrepago){
				salidaList.add(vo19);
				salidaList.add(vo20);
				salidaList.add(vo21);
			}
			salidaList.add(totales);
			
			salidaVO.setSalidaList(salidaList);
			salidaVO.setTotales(totales);
			
			return salidaVO;
	
		}
		
		for(int i = 0; i < largo; i=i+6){
			
		
			SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO();
			vo0.setDetalle(dvo0);
			SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO();
			vo1.setDetalle(dvo1);
			SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO();
			vo2.setDetalle(dvo2);
			SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO();
			vo3.setDetalle(dvo3);
			SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO();
			vo4.setDetalle(dvo4);
			SalidaCreditoPrepagoFoliosVO vo4_1 = new SalidaCreditoPrepagoFoliosVO();
			vo4_1.setDetalle(dvo4_1);
			SalidaCreditoPrepagoFoliosVO vo4_2 = new SalidaCreditoPrepagoFoliosVO();
			vo4_2.setDetalle(dvo4_2);
			SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO();
			vo5.setDetalle(dvo5);
			SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO();
			vo6.setDetalle(dvo6);
			SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO();
			vo7.setDetalle(dvo7);
			SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO();
			vo8.setDetalle(dvo8);
			SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO();
			vo9.setDetalle(dvo9);
			SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO();
			vo10.setDetalle(dvo10);
			SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO();
			vo11.setDetalle(dvo11);
			SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO();
			vo12.setDetalle(dvo12);
			SalidaCreditoPrepagoFoliosVO vo13 = new SalidaCreditoPrepagoFoliosVO();
			vo13.setDetalle(dvo13);
			SalidaCreditoPrepagoFoliosVO vo14 = new SalidaCreditoPrepagoFoliosVO();
			vo14.setDetalle(dvo14);
			SalidaCreditoPrepagoFoliosVO vo15 = new SalidaCreditoPrepagoFoliosVO();
			vo15.setDetalle(dvo15);
			SalidaCreditoPrepagoFoliosVO vo16 = new SalidaCreditoPrepagoFoliosVO();
			vo16.setDetalle(dvo16);
			SalidaCreditoPrepagoFoliosVO vo17 = new SalidaCreditoPrepagoFoliosVO();
			vo17.setDetalle(dvo17);
			SalidaCreditoPrepagoFoliosVO vo18 = new SalidaCreditoPrepagoFoliosVO();
			vo18.setDetalle(dvo18);
			SalidaCreditoPrepagoFoliosVO vo18_1 = new SalidaCreditoPrepagoFoliosVO();
			vo18_1.setDetalle(dvo18_1);
			SalidaCreditoPrepagoFoliosVO vo18_2 = new SalidaCreditoPrepagoFoliosVO();
			vo18_2.setDetalle(dvo18_2);
			SalidaCreditoPrepagoFoliosVO vo19 = new SalidaCreditoPrepagoFoliosVO();
			vo19.setDetalle(dvo19);
			SalidaCreditoPrepagoFoliosVO vo20 = new SalidaCreditoPrepagoFoliosVO();
			vo20.setDetalle(dvo20);
			SalidaCreditoPrepagoFoliosVO vo21 = new SalidaCreditoPrepagoFoliosVO();
			vo21.setDetalle(dvo21);
			SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();
			totales.setDetalle(dtotales);
		
		
		
		
		SalidaCreditoPrepagoVO credito = null;
		if(largo >= i+1){
			credito = listaPrepagosCompleta.get(i);
			vo0.setValorFolio1(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio1(credito.getFechaOtorgamiento());
			vo2.setValorFolio1(credito.getTipoBp());
			vo3.setValorFolio1(credito.getLineaCredito());
			vo4.setValorFolio1(credito.getFolioForm24());
			vo4_1.setValorFolio1(credito.getRol());
			vo4_2.setValorFolio1(credito.getPagador());
			vo5.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio1(credito.getDesdeCuota());
			vo11.setValorFolio1(credito.getHastaCuota());
			vo12.setValorFolio1(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio1(credito.getNumCuotasEnTransito());
			vo14.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
		if(largo >= i+2){
			credito = listaPrepagosCompleta.get(i+1);
			vo0.setValorFolio2(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio2(credito.getFechaOtorgamiento());
			vo2.setValorFolio2(credito.getTipoBp());
			vo3.setValorFolio2(credito.getLineaCredito());
			vo4.setValorFolio2(credito.getFolioForm24());
			vo4_1.setValorFolio2(credito.getRol());
			vo4_2.setValorFolio2(credito.getPagador());
			vo5.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio2(credito.getDesdeCuota());
			vo11.setValorFolio2(credito.getHastaCuota());
			vo12.setValorFolio2(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio2(credito.getNumCuotasEnTransito());
			vo14.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
		if(largo >= i+3){
			credito = listaPrepagosCompleta.get(i+2);
			vo0.setValorFolio3(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio3(credito.getFechaOtorgamiento());
			vo2.setValorFolio3(credito.getTipoBp());
			vo3.setValorFolio3(credito.getLineaCredito());
			vo4.setValorFolio3(credito.getFolioForm24());
			vo4_1.setValorFolio3(credito.getRol());
			vo4_2.setValorFolio3(credito.getPagador());
			vo5.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio3(credito.getDesdeCuota());
			vo11.setValorFolio3(credito.getHastaCuota());
			vo12.setValorFolio3(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio3(credito.getNumCuotasEnTransito());
			vo14.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
				  
		if(largo >= i+4){
			credito = listaPrepagosCompleta.get(i+3);				  
			vo0.setValorFolio4(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio4(credito.getFechaOtorgamiento());
			vo2.setValorFolio4(credito.getTipoBp());
			vo3.setValorFolio4(credito.getLineaCredito());
			vo4.setValorFolio4(credito.getFolioForm24());
			vo4_1.setValorFolio4(credito.getRol());
			vo4_2.setValorFolio4(credito.getPagador());
			vo5.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio4(credito.getDesdeCuota());
			vo11.setValorFolio4(credito.getHastaCuota());
			vo12.setValorFolio4(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio4(credito.getNumCuotasEnTransito());
			vo14.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
					
		if(largo >= i+5){
		    credito = listaPrepagosCompleta.get(i+4);
		    vo0.setValorFolio5(credito.getFolio());
		    vo0.setCabecera(true);
		    vo1.setValorFolio5(credito.getFechaOtorgamiento());
			vo2.setValorFolio5(credito.getTipoBp());
			vo3.setValorFolio5(credito.getLineaCredito());
			vo4.setValorFolio5(credito.getFolioForm24());
			vo4_1.setValorFolio5(credito.getRol());
			vo4_2.setValorFolio5(credito.getPagador());
			vo5.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio5(credito.getDesdeCuota());
			vo11.setValorFolio5(credito.getHastaCuota());
			vo12.setValorFolio5(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio5(credito.getNumCuotasEnTransito());
			vo14.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
					
		if(largo >= i+6){
		    credito = listaPrepagosCompleta.get(i+5);
		    vo0.setValorFolio6(credito.getFolio());
		    vo0.setCabecera(true);
		    vo1.setValorFolio6(credito.getFechaOtorgamiento());
			vo2.setValorFolio6(credito.getTipoBp());
			vo3.setValorFolio6(credito.getLineaCredito());
			vo4.setValorFolio6(credito.getFolioForm24());
			vo4_1.setValorFolio6(credito.getRol());
			vo4_2.setValorFolio6(credito.getPagador());
			vo5.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoImpuestoLTE()));
			vo7.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegDesgravamen()));
			vo8.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getPrimaSegCesantia()));
			vo9.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo10.setValorFolio6(credito.getDesdeCuota());
			vo11.setValorFolio6(credito.getHastaCuota());
			vo12.setValorFolio6(Utils.formateaDobleSinDecimal(credito.getCantidadCuotasMorosas()));
			vo13.setValorFolio6(credito.getNumCuotasEnTransito());
			vo14.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
			vo15.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getGastoCobranza()));
			vo16.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getHonorarios()));
			vo17.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
			vo18.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			vo18_1.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresMoroso()));
			vo18_2.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoSeguros()));
			vo19.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoComisionPrepagos()));
			vo20.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getTotalDiferido()));
			vo21.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getTotalCuotasInformadas()));
			totales.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getTotal()));
		}
		
		SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		salidaList.add(espacio);
		salidaList.add(vo0);
		salidaList.add(vo1);
		if(esPrepago){
			salidaList.add(vo2);
		}
		salidaList.add(vo3);
		salidaList.add(vo4);
		salidaList.add(vo4_1);
		salidaList.add(vo4_2);
		salidaList.add(vo5);
		salidaList.add(vo6);
		salidaList.add(vo7);
		salidaList.add(vo8);
		salidaList.add(vo9);
		salidaList.add(vo10);
		salidaList.add(vo11);
		salidaList.add(vo12);
		if(esPrepago){
			salidaList.add(vo13);
		}
		salidaList.add(vo14);
		salidaList.add(vo15);
		salidaList.add(vo16);
		salidaList.add(vo17);
		salidaList.add(vo18);
		salidaList.add(vo18_1);
		salidaList.add(vo18_2);
		if(esPrepago){
			salidaList.add(vo19);
			salidaList.add(vo20);
			salidaList.add(vo21);
		}
		salidaList.add(totales);
		
		//Agregar espacio separador tabla
		//SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		//salidaList.add(espacio);
		
		salidaVO.setSalidaList(salidaList);
		salidaVO.setTotales(totales);
		
		//listaSalida.add(salidaVO);
		}//Fin for
		
		return salidaVO;
	}
	
public SalidaListaCreditoPrepagoFoliosVO getCreditosDeudaConFolios(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta, boolean esPrepago){
		
		//List<SalidaListaCreditoPrepagoFoliosVO> listaSalida = new ArrayList<SalidaListaCreditoPrepagoFoliosVO>();

		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		//listaPrepagosCompleta.addAll(listaPrepagosCompleta);
		
		SalidaListaCreditoPrepagoFoliosVO salidaVO = new SalidaListaCreditoPrepagoFoliosVO();
		List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
		SalidaCreditoPrepagoVO prepago= null;
		if(listaPrepagosCompleta.size()>0){
			prepago= listaPrepagosCompleta.get(0);
		}
		String dvo0 = "Número de operación";
		String dvo1 = "Fecha Otorgamiento Crédito";
		String dvo2 = "Tipo de afiliado";
		String dvo3 = "Línea de crédito";
		String dvo4 = "Tipo de Operación";
		String dvo5 = "Monto otorgado";
		String dvo6 = "Monto cuota descuento";
		String dvo7 = "Plazo al otorgamiento";
		String dvo8 = "Desde cuota";
		String dvo9 = "Hasta cuota";
		String dvo10 = "Gravámenes (interés moratorio)";
		String dvo11 = "Interés devengado a la fecha de admisibilidad";
		String dvo12 = "Saldo Capital";
		String dtotalverif;
		if(prepago!=null && prepago.isLiquidacion()){
			dtotalverif = "Total a verificar (capital)";
		}else{
			dtotalverif = "Total a verificar (capital + gravámenes + interés devengado)";
		}
		
		int largo = listaPrepagosCompleta.size();
		if(largo == 0){
			SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO();
			vo0.setDetalle(dvo0);
			SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO();
			vo1.setDetalle(dvo1);
			SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO();
			vo2.setDetalle(dvo2);
			SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO();
			vo3.setDetalle(dvo3);
			SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO();
			vo4.setDetalle(dvo4);
			SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO();
			vo5.setDetalle(dvo5);
			SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO();
			vo6.setDetalle(dvo6);
			SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO();
			vo7.setDetalle(dvo7);
			SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO();
			vo8.setDetalle(dvo8);
			SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO();
			vo9.setDetalle(dvo9);
			SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO();
			vo10.setDetalle(dvo10);
			SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO();
			vo11.setDetalle(dvo11);
			SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO();
			vo12.setDetalle(dvo12);
			SalidaCreditoPrepagoFoliosVO totalverif = new SalidaCreditoPrepagoFoliosVO();
			totalverif.setDetalle(dtotalverif);
			
			
			salidaList.add(null);
			salidaList.add(vo0);
			salidaList.add(vo1);
			salidaList.add(vo2);
			salidaList.add(vo3);
			salidaList.add(vo4);
			salidaList.add(vo5);
			salidaList.add(vo6);
			salidaList.add(vo7);
			salidaList.add(vo8);
			salidaList.add(vo9);
			if(prepago==null || !prepago.isLiquidacion()){
				salidaList.add(vo10);
				salidaList.add(vo11);
			}
			salidaList.add(vo12);
			salidaList.add(totalverif);
			
			salidaVO.setSalidaList(salidaList);
			salidaVO.setTotales(totalverif);
			
			return salidaVO;
	
		}
		
		for(int i = 0; i < largo; i=i+6){
			
		
			SalidaCreditoPrepagoFoliosVO vo0 = new SalidaCreditoPrepagoFoliosVO();
			vo0.setDetalle(dvo0);
			SalidaCreditoPrepagoFoliosVO vo1 = new SalidaCreditoPrepagoFoliosVO();
			vo1.setDetalle(dvo1);
			SalidaCreditoPrepagoFoliosVO vo2 = new SalidaCreditoPrepagoFoliosVO();
			vo2.setDetalle(dvo2);
			SalidaCreditoPrepagoFoliosVO vo3 = new SalidaCreditoPrepagoFoliosVO();
			vo3.setDetalle(dvo3);
			SalidaCreditoPrepagoFoliosVO vo4 = new SalidaCreditoPrepagoFoliosVO();
			vo4.setDetalle(dvo4);
			SalidaCreditoPrepagoFoliosVO vo5 = new SalidaCreditoPrepagoFoliosVO();
			vo5.setDetalle(dvo5);
			SalidaCreditoPrepagoFoliosVO vo6 = new SalidaCreditoPrepagoFoliosVO();
			vo6.setDetalle(dvo6);
			SalidaCreditoPrepagoFoliosVO vo7 = new SalidaCreditoPrepagoFoliosVO();
			vo7.setDetalle(dvo7);
			SalidaCreditoPrepagoFoliosVO vo8 = new SalidaCreditoPrepagoFoliosVO();
			vo8.setDetalle(dvo8);
			SalidaCreditoPrepagoFoliosVO vo9 = new SalidaCreditoPrepagoFoliosVO();
			vo9.setDetalle(dvo9);
			SalidaCreditoPrepagoFoliosVO vo10 = new SalidaCreditoPrepagoFoliosVO();
			vo10.setDetalle(dvo10);
			SalidaCreditoPrepagoFoliosVO vo11 = new SalidaCreditoPrepagoFoliosVO();
			vo11.setDetalle(dvo11);
			SalidaCreditoPrepagoFoliosVO vo12 = new SalidaCreditoPrepagoFoliosVO();
			vo12.setDetalle(dvo12);
			SalidaCreditoPrepagoFoliosVO totalverif = new SalidaCreditoPrepagoFoliosVO();
			totalverif.setDetalle(dtotalverif);
		
		
		
		
		SalidaCreditoPrepagoVO credito = null;
		if(largo >= i+1){
			credito = listaPrepagosCompleta.get(i);
			vo0.setValorFolio1(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio1(credito.getFechaOtorgamiento());
			vo2.setValorFolio1(credito.getTipoBp());
			vo3.setValorFolio1(credito.getLineaCredito());
			vo4.setValorFolio1("Crédito Social");
			vo5.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio1(credito.getPlazo());
			vo8.setValorFolio1(credito.getDesdeCuota());
			vo9.setValorFolio1(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			}
			vo12.setValorFolio1("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
		
		if(largo >= i+2){
			credito = listaPrepagosCompleta.get(i+1);
			vo0.setValorFolio2(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio2(credito.getFechaOtorgamiento());
			vo2.setValorFolio2(credito.getTipoBp());
			vo3.setValorFolio2(credito.getLineaCredito());
			vo4.setValorFolio1("Crédito Social");
			vo5.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio2(credito.getPlazo());
			vo8.setValorFolio2(credito.getDesdeCuota());
			vo9.setValorFolio2(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			}
			vo12.setValorFolio2("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
		if(largo >= i+3){
			credito = listaPrepagosCompleta.get(i+2);
			vo0.setValorFolio3(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio3(credito.getFechaOtorgamiento());
			vo2.setValorFolio3(credito.getTipoBp());
			vo3.setValorFolio3(credito.getLineaCredito());
			vo4.setValorFolio1("Crédito Social");
			vo5.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio3(credito.getPlazo());
			vo8.setValorFolio3(credito.getDesdeCuota());
			vo9.setValorFolio3(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() ));
			}
			vo12.setValorFolio3("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
				  
		if(largo >= i+4){
			credito = listaPrepagosCompleta.get(i+3);				  
			vo0.setValorFolio4(credito.getFolio());
			vo0.setCabecera(true);
			vo1.setValorFolio4(credito.getFechaOtorgamiento());
			vo2.setValorFolio4(credito.getTipoBp());
			vo3.setValorFolio4(credito.getLineaCredito());
			vo4.setValorFolio1("Crédito Social");
			vo5.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio4(credito.getPlazo());
			vo8.setValorFolio4(credito.getDesdeCuota());
			vo9.setValorFolio4(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			}
			vo12.setValorFolio4("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
					
		if(largo >= i+5){
		    credito = listaPrepagosCompleta.get(i+4);
		    vo0.setValorFolio5(credito.getFolio());
		    vo0.setCabecera(true);
		    vo1.setValorFolio5(credito.getFechaOtorgamiento());
			vo2.setValorFolio5(credito.getTipoBp());
			vo3.setValorFolio5(credito.getLineaCredito());
			vo4.setValorFolio5("Crédito Social");
			vo5.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio5(credito.getPlazo());
			vo8.setValorFolio5(credito.getDesdeCuota());
			vo9.setValorFolio5(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			}
			vo12.setValorFolio5("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
					
		if(largo >= i+6){
		    credito = listaPrepagosCompleta.get(i+5);
		    vo0.setValorFolio6(credito.getFolio());
		    vo0.setCabecera(true);
		    vo1.setValorFolio6(credito.getFechaOtorgamiento());
			vo2.setValorFolio6(credito.getTipoBp());
			vo3.setValorFolio6(credito.getLineaCredito());
			vo4.setValorFolio6("Crédito Social");
			vo5.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoOtorgado()));
			vo6.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoCuota()));
			vo7.setValorFolio6(credito.getPlazo());
			vo8.setValorFolio6(credito.getDesdeCuota());
			vo9.setValorFolio6(credito.getHastaCuota());
			if(!prepago.isLiquidacion()){
				vo10.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getGravamenes()));
				vo11.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getMontoInteresDevengado()));
				totalverif.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital() + credito.getGravamenes() + credito.getMontoInteresDevengado()));
			}else{
				totalverif.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			}
			vo12.setValorFolio6("$ "+Utils.formateaDobleSinDecimal(credito.getSaldoCapital()));
			
		}
		
		SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		salidaList.add(espacio);
		salidaList.add(vo0);
		salidaList.add(vo1);
		salidaList.add(vo2);
		salidaList.add(vo3);
		salidaList.add(vo4);
		salidaList.add(vo5);
		salidaList.add(vo6);
		salidaList.add(vo7);
		salidaList.add(vo8);
		salidaList.add(vo9);
		if(!prepago.isLiquidacion()){
			salidaList.add(vo10);
			salidaList.add(vo11);
		}
		salidaList.add(vo12);
		salidaList.add(totalverif);
		//Agregar espacio separador tabla
		//SalidaCreditoPrepagoFoliosVO espacio = new SalidaCreditoPrepagoFoliosVO("","","","","","","","");
		//salidaList.add(espacio);
		
		salidaVO.setSalidaList(salidaList);
		salidaVO.setTotales(totalverif);
		
		//listaSalida.add(salidaVO);
		}//Fin for
		
		return salidaVO;
	}
	
	public List<SalidaDetalleCuotasEarlyPayOff2> getDetalleCuotasTrabajadorIndependiente(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta, boolean isTrabajador){
		
		List<SalidaDetalleCuotasEarlyPayOff2> listadoFechas = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		
		List<SalidaCreditoPrepagoVO> listaPagos = new ArrayList<SalidaCreditoPrepagoVO>();
		
		for (SalidaCreditoPrepagoVO credito : listaPrepagosCompleta) {
			if(isTrabajador){
				if(!"Pensionado".equalsIgnoreCase(credito.getTipoBp())){
					listaPagos.add(credito);
				}
			}else {
				if("Pensionado".equalsIgnoreCase(credito.getTipoBp())){
					listaPagos.add(credito);
				}
			}
			
		}
		
		listadoFechas = getSumatoriaFechasPago(listaPagos);
		
		return listadoFechas;
		
	}
	
public List<SalidaDetalleCuotasEarlyPayOff2> getDetalleCuotasTrabajador(List<SalidaCreditoPrepagoVO> listaPrepagosCompleta){
		
		List<SalidaDetalleCuotasEarlyPayOff2> listadoFechas = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		
		List<SalidaCreditoPrepagoVO> listaPagos = new ArrayList<SalidaCreditoPrepagoVO>();
		
		for (SalidaCreditoPrepagoVO credito : listaPrepagosCompleta) {
			//clillo 27-09-2017 Se elimina opciones de pago por trabajador y pensionado
			listaPagos.add(credito);
		}
		
		listadoFechas = getSumatoriaFechasPago(listaPagos);
		
		return listadoFechas;
		
	}
	
public List<SalidaDetalleCuotasEarlyPayOff2> getSumatoriaFechasPago(List<SalidaCreditoPrepagoVO> listaCreditos){
	if(listaCreditos==null || listaCreditos.size()==0) return new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();

	Map<String, SalidaDetalleCuotasEarlyPayOff2> map = new TreeMap<String, SalidaDetalleCuotasEarlyPayOff2>();

	for (SalidaCreditoPrepagoVO salidaCreditoPrepagoVO : listaCreditos) {
		for (SalidaDetalleCuotasEarlyPayOff2 detalle : salidaCreditoPrepagoVO.getListaFechaPagar()) {
			SalidaDetalleCuotasEarlyPayOff2 objeto = map.get(detalle.getFechaDePago());
			SalidaDetalleCuotasEarlyPayOff2 objeto2 = new SalidaDetalleCuotasEarlyPayOff2();
			if(objeto == null){
				map.put(detalle.getFechaDePago(), detalle);

			}else{
				objeto2.setFechaDePago(objeto.getFechaDePago());
				objeto2.setTotalAPagar(objeto.getTotalAPagar());
				double suma = objeto2.getTotalAPagar()+detalle.getTotalAPagar();
				objeto2.setTotalAPagar(suma);
				map.put(detalle.getFechaDePago(), objeto2);
			}
		}
	}


	List<SalidaDetalleCuotasEarlyPayOff2> listado = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>(map.values());

	return listado;


}
/*public List<SalidaDetalleCuotasEarlyPayOff2> getSumatoriaFechasPrePago(List<SalidaCreditoPrepagoVO> listaCreditos){
	if(listaCreditos==null || listaCreditos.size()==0) return new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();

	boolean isTrabajador= true;

	Map<String, SalidaDetalleCuotasEarlyPayOff2> map = new TreeMap<String, SalidaDetalleCuotasEarlyPayOff2>();

	for (SalidaCreditoPrepagoVO salidaCreditoPrepagoVO : listaCreditos) {
		for (SalidaDetalleCuotasEarlyPayOff2 detalle : salidaCreditoPrepagoVO.getListaFechaPagar()) {
			String tipoAfiliado= salidaCreditoPrepagoVO.getTipoBp();
			//clillo 2-10-2017 Se determina si es DD para mostrar la tabla de fechas de pago completa o solo un registro si es Afiliado
			if(tipoAfiliado.equals("Deudor Directo")){
				isTrabajador= false;
			}
			SalidaDetalleCuotasEarlyPayOff2 objeto = map.get(detalle.getFechaDePago());
			SalidaDetalleCuotasEarlyPayOff2 objeto2 = new SalidaDetalleCuotasEarlyPayOff2();
			if(objeto == null){
				map.put(detalle.getFechaDePago(), detalle);

			}else{
				objeto2.setFechaDePago(objeto.getFechaDePago());
				objeto2.setTotalAPagar(objeto.getTotalAPagar());
				double suma = objeto2.getTotalAPagar()+detalle.getTotalAPagar();
				objeto2.setTotalAPagar(suma);
				map.put(detalle.getFechaDePago(), objeto2);
			}
			if(isTrabajador){
				break;
			}
		}
	}


	List<SalidaDetalleCuotasEarlyPayOff2> listado = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>(map.values());

	return listado;


}*/


	
	
}
