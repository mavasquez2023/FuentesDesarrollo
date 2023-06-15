package cl.laaraucana.compromisototal.utils;

import java.util.ArrayList;
import java.util.ResourceBundle;

import cl.laaraucana.compromisototal.VO.ContratoVO;

public class MapeoCodigosBanking {
	// busca en el archivo properties el nombre equivalente
	public String replaceEstadoCredito(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertBanking.estadoCredito");
		String campo;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}

	public String replaceLineaComercial(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertBanking.lineaComercial");
		String campo;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}

	
	public String replaceRolAsociado(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertBanking.rolAsociado");
		String campo;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}

	public String replaceTipoAfiliado(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertBanking.tipoAfiliado");
		String campo;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}

	// realiza el reemplazo de los campos en la lista
	public ArrayList<ContratoVO> replaceCampos(ArrayList<ContratoVO> lista) {
		ArrayList<ContratoVO> listaNueva = new 	ArrayList<ContratoVO>();
		for (int i = 0; i < lista.size(); i++) {
			ContratoVO contrato = lista.get(i);
			contrato.setEstadoCredito(this.replaceEstadoCredito(contrato.getEstadoCredito()));
			contrato.setLineaComercial(this.replaceLineaComercial(contrato.getLineaComercial()));
			contrato.setTitular(this.replaceRolAsociado(contrato.getTitular()));
			contrato.setTipoAfiliado(this.replaceTipoAfiliado(contrato.getTipoAfiliado()));

			listaNueva.add(contrato);
		}

		return listaNueva;
	}

}
