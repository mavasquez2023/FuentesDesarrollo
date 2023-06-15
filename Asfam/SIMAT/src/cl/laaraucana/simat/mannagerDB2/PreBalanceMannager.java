package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import cl.laaraucana.simat.documentos.InformeFinanciero.LectorBalanceExcel;
import cl.laaraucana.simat.entidades.BalanceGeneral;
import cl.laaraucana.simat.entidades.CuentaInformeFinanciero;
import cl.laaraucana.simat.entidades.ResultadoPreBalance;
import cl.laaraucana.simat.utiles.LectorPropiedades;

public class PreBalanceMannager {
	
	ResourceBundle ctasResource = ResourceBundle.getBundle("cl.laaraucana.simat.config.CuentasPreBalance");
	
	/**
	 * Obtiene los resultados desde el balance general y la información de los planos para
	 * calcular las diferencias y desplegar el informe financiero (Pre Balance)
	 * @return
	 * @throws Exception
	 */
	public ResultadoPreBalance validarPreBalance() throws Exception{
		ResultadoPreBalance resp = new ResultadoPreBalance();
		resp.setCodigo("3");
		resp.setMensaje("El informe financiero se obtuvo correctamente");
		
		//Leer las cuentas del propertie
		Enumeration<String> llaves = ctasResource.getKeys();
		List<String> listaCuentas = Collections.list(llaves);
        Collections.sort(listaCuentas);
		
		//Obtener resultado de cuentas desde los 2 origenes
		HashMap<String, BalanceGeneral> resBalance = getCuentasBalance();
		InformeFinancieroMannager ifMgr = new InformeFinancieroMannager();
		HashMap<String, Long> resPlanos = ifMgr.getResultadoPlanos();

		List<CuentaInformeFinanciero> resultPreBalance = new ArrayList<CuentaInformeFinanciero>();
		//while (llaves.hasMoreElements()) {
		for (String keyOrig : listaCuentas) {
			String keyAux;
			//Leer las propiedades de cada cuenta
			String[] auxProp = keyOrig.split("-");
			keyAux = auxProp[0];//Nro de cuenta
			
			String[] ctaCompuesta = keyAux.split(",");
			String[] propiedades = auxProp[1].split(";");//Propiedades separadas por ';'
			
			String valorAbsBal = propiedades[0];
			String origResBal = propiedades[1];
			String valorAbsPla = propiedades[2];
			String origResPla = propiedades[3];
			
			long resCtaBalance = 0;
			long resCtaPlanos = 0;
			for (String subcuenta : ctaCompuesta) {
				//Ir a buscar al origen resultado balance
				resCtaBalance+=getValorCuenta(subcuenta, origResBal, valorAbsBal, resBalance, resPlanos);
				resCtaPlanos+=getValorCuenta(subcuenta, origResPla, valorAbsPla, resBalance, resPlanos);
			}
			CuentaInformeFinanciero cuenta = new CuentaInformeFinanciero();
			cuenta.setNombreCuenta(keyAux);
			cuenta.setDescripcion(ctasResource.getString(keyOrig));
			cuenta.setResultadoBalance(resCtaBalance);
			cuenta.setResultadoPlanos(resCtaPlanos);
			resultPreBalance.add(cuenta);
			if(cuenta.getDiferencia()!=0){
				resp.setCodigo("5");
				resp.setMensaje("Existen cuentas sin cuadrar, no es posible generar el pase contable");
			}
		}
		resp.setResultPreBalance(resultPreBalance);
		return resp;
	}
	
	/**
	 * Retorna el valor del resultado de la cuenta segun origen y conversion a valor absoluto
	 * @param cuenta
	 * @param origen
	 * @param valorAbs
	 * @param resBalance
	 * @param resPlanos
	 * @return
	 */
	private long getValorCuenta(String cuenta, String origen, String valorAbs,HashMap<String, BalanceGeneral> resBalance, HashMap<String, Long> resPlanos){
		long resCtaBalance = 0;
		if(origen.toUpperCase().trim().equals("XLS")){
			if(resBalance.get(cuenta)!=null)
				resCtaBalance=Long.parseLong(((BalanceGeneral)resBalance.get(cuenta)).getResultado());
			else
				resCtaBalance=0;
		}else if(origen.toUpperCase().trim().equals("SQL")){
			if(resPlanos.get(cuenta)!=null)
				resCtaBalance=((Long)resPlanos.get(cuenta));
		}
		
		if(valorAbs.equals("1"))
			resCtaBalance=Math.abs(resCtaBalance);
		
		return resCtaBalance;
	}
	
	
	/**
	 * Lee el archivo excel con el resutlado de las cuenta del  balance general
	 * @return
	 * @throws Exception
	 */
	private HashMap<String, BalanceGeneral> getCuentasBalance() throws Exception{
			HashMap<String, BalanceGeneral> resBalance = new HashMap<String, BalanceGeneral>();
			LectorPropiedades lp=new LectorPropiedades();
			String rutaTemporal=lp.getAtributoRepositorio("rutaLocalTemporales");
			String filename=lp.getAtributoRepositorio("nombreArchivoPreBalance");
			try {
			//se cargan valores y cuentas del balance general
			LectorBalanceExcel lexel=new LectorBalanceExcel();
			@SuppressWarnings("rawtypes")
			ArrayList listaBalance=lexel.readWorkbook(filename,rutaTemporal);
			listaBalance.remove(0);
			@SuppressWarnings("unchecked")
			Iterator<BalanceGeneral> itBG = listaBalance.iterator();
			BalanceGeneral bg=new BalanceGeneral();
			//Cargar cuentas en un HashMap
			while(itBG.hasNext()){
				bg = (BalanceGeneral) itBG.next();
				if(!(bg.getCuenta()==null || bg.getCuenta().isEmpty() || bg.getResultado()==null)){
					resBalance.put(bg.getCuenta(), bg);
					//System.out.println("cta: " + bg.getCuenta() + ", DESC: "+ bg.getTexto_breve() + ", RES:" + bg.getResultado());
				}
			}
		} catch (Exception e) {
			throw new Exception("Error al leer la planilla del balance general ("+filename+"), verifique que el archivo existe");
		}
		return resBalance;
	}
}
