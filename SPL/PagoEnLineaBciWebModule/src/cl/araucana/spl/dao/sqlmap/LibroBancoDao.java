package cl.araucana.spl.dao.sqlmap;

import java.util.HashMap;
import java.util.List;

import cl.araucana.spl.beans.EntradaLibroBanco;
import cl.araucana.spl.dao.LibroBancoDAO;
import cl.araucana.spl.util.CompletaUtil;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;


public class LibroBancoDao extends SqlMapDaoTemplate implements LibroBancoDAO {
	
	public LibroBancoDao(DaoManager daoManager) {
		super(daoManager);
	}

	/**
	 * Realiza registro en libro de banco
	 */
	public boolean registrarEnLibroBanco(EntradaLibroBanco entrada)
			throws Exception {
		boolean salida = false;
		HashMap entradaMap = mapearEntradaLibro(entrada);
		queryForObject("registrarEnLibroBanco", entradaMap);
		String resp = (String) entradaMap.get("DATO9");
		if (resp.trim().equals("0")) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;
	}
	
	/**
	 * Registro masivo de libro de banco
	 */
	public boolean registrarEnLibroBancoMasivo(List entrada) throws Exception {
		boolean resp = true;
		for (int i = 0; i < entrada.size(); i++) {
			EntradaLibroBanco registro = (EntradaLibroBanco) entrada.get(i);
			HashMap entradaMap = mapearEntradaLibro(registro);
			queryForObject("registrarEnLibroBanco", entradaMap);
			String salida = (String) entradaMap.get("DATO9");
			if(salida.trim().equals("1")){
				resp = false;
			}
		}
		return resp;
	}
	
	private HashMap mapearEntradaLibro(EntradaLibroBanco entrada) throws Exception{
		HashMap entradaMap = new HashMap();
		entradaMap.put("DATO1", CompletaUtil.rellenarCampos(entrada.getBanco(), 3,"0"));
		entradaMap.put("DATO2", CompletaUtil.rellenarCampos(entrada.getNroCuentaCorriente(),15, " "));
		entradaMap.put("DATO3", CompletaUtil.rellenarCampos(entrada.getFechaMovimiento(), 8, "0"));
		entradaMap.put("DATO4", CompletaUtil.rellenarCampos(entrada.getMonto(), 13, "0"));
		entradaMap.put("DATO5", CompletaUtil.rellenarCampos(entrada.getTipoAbono(), 1, " "));
		entradaMap.put("DATO6", CompletaUtil.rellenarCampos(entrada.getNroDeposito(),11, "0"));
		entradaMap.put("DATO7", CompletaUtil.rellenarCampos(entrada.getCodOperacionInterna(), 5, "0"));
		entradaMap.put("DATO8", CompletaUtil.rellenarCampos(entrada.getDescripcion(),78, " "));
		entradaMap.put("DATO9", "0");
		return entradaMap;
	}

}
