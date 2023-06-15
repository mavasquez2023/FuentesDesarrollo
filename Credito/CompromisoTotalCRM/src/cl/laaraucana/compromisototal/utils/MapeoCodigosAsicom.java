package cl.laaraucana.compromisototal.utils;

import java.util.ArrayList;
import java.util.ResourceBundle;

import cl.laaraucana.compromisototal.webservice.client.asicom.VO.SalidaAsicomVO;

public class MapeoCodigosAsicom {

	// busca en el archivo properties el nombre equivalente
	public String replaceEstadoCredito(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertAsicom.estadoCredito");
		String campo;
		if (key != null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
			//			campo = mainCfg.getString("default");
		}
		return campo;
	}

	public String replaceRolAsociado(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertAsicom.rolAsociado");
		String campo;
		if (key != null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = mainCfg.getString("default");
		}
		return campo;
	}

	public String replaceRolPagador(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertAsicom.rolPagador");
		String campo;
		if (mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = mainCfg.getString("default");
		}
		return campo;
	}

	// realiza el reemplazo de los campos en la lista
	public ArrayList<SalidaAsicomVO> replaceCampos(ArrayList<SalidaAsicomVO> lista) {

		ArrayList<SalidaAsicomVO> listaNueva = new ArrayList<SalidaAsicomVO>();

		for (int i = 0; i < lista.size(); i++) {
			SalidaAsicomVO contrato = lista.get(i);
			contrato.setEstado_credito(this.replaceEstadoCredito(contrato.getEstado_credito()));
			contrato.setRol_asociado_relacion(this.replaceRolAsociado(contrato.getRol_asociado_relacion()));
			contrato.setRol_pagador(this.replaceRolPagador(contrato.getRol_pagador()));

			listaNueva.add(contrato);
		}

		return listaNueva;
	}

}
