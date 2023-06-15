package cl.jfactory.planillas.service.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.helper.EstadisticasHelper;
import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class ValidacionProcesoGeneracion implements ITerminadorHebra{

	int CANTIDAD_ITERACIONES = 2;
	int CANTIDAD_HEBRAS = 10;
	int iteracionEnCurso = 1;
	static String PERIODO = UtilesComunes.reemplazarVariables("sys.YearMonth");
	static ArrayList entidades = new ArrayList();
	
	public static void main(String[] args) {
		new ValidacionProcesoGeneracion().init();
	}
	
	
	public void init(){
		//
		if(true) return;
		ValidarInconsistenciaOficinas validarInconsistencias = new ValidarInconsistenciaOficinas();
		
		UtilLogWorkflow.debug("Comenzando el proceso de validación de Generación");
		UtilLogWorkflow.debug("iteración ["+iteracionEnCurso+"]");
		validarInconsistencias.validar();
		validarEntidadesNoInsertadas();
		
	}
	
	public void validarEntidadesNoInsertadas(){

		ValidarInconsistenciaOficinas validarInconsistencias = new ValidarInconsistenciaOficinas();
		entidades.addAll(validarComandoHoldingNoInsertados());
		entidades.addAll(validarComandosEmpresasHoldingNoInsertados());
		entidades.addAll(validarComandosEmpresasNoInsertadas());
		UtilLogWorkflow.debug("ejecutando generacion...");
		ejecutarEntidades(entidades);
		UtilLogWorkflow.debug("fin generacion...");
		if(iteracionEnCurso <CANTIDAD_ITERACIONES){
			iteracionEnCurso++;
			UtilLogWorkflow.debug("iteración ["+iteracionEnCurso+"]");
			validarInconsistencias.validar();
			validarEntidadesNoInsertadas();
		}
	}
	
	public ArrayList validarComandoHoldingNoInsertados(){
		UtilLogWorkflow.debug("Validando comando holdings");
		ArrayList entidades = new ArrayList();
		String query = "carga_SAP.obtenerHoldingsNoRegistrados";
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		pars.put("PERIODO", PERIODO);
		try {
			entidades = (ArrayList) sqlMap.queryForList(query, pars);
			UtilLogWorkflow.debug("holdings no registrados:"+ entidades);
			UtilLogProcesos.debug("holdings no registrados:"+ entidades);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entidades;
	}
	
	public ArrayList validarComandosEmpresasHoldingNoInsertados(){
		UtilLogWorkflow.debug("Validando comando empresas holdings");
		ArrayList entidades = new ArrayList();
		String query = "carga_SAP.obtenerEmpresasHoldingsNoRegistradas";
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		pars.put("PERIODO", PERIODO);
		try {
			entidades = (ArrayList) sqlMap.queryForList(query, pars);
			UtilLogWorkflow.debug("empresas holdings no registradas :"+ entidades);
			UtilLogProcesos.debug("empresas holdings no registradas :"+ entidades);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entidades;
	}
	
	public ArrayList validarComandosEmpresasNoInsertadas(){
		UtilLogWorkflow.debug("Validando comando empresas");
		ArrayList entidades = new ArrayList();
		String query = "carga_SAP.obtenerEmpresasNoRegistradas";
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		pars.put("PERIODO", PERIODO);
		try {
			entidades = (ArrayList) sqlMap.queryForList(query, pars);
			UtilLogWorkflow.debug("empresas no registradas:"+ entidades);
			UtilLogProcesos.debug("empresas no registradas:"+ entidades);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entidades;
	}
	
	public void ejecutarEntidades(ArrayList entidades){
		UtilLogWorkflow.debug("entidades no registradas "+ entidades);
		EstadisticasHelper.inicializar(false);
		new PoolHebras(CANTIDAD_HEBRAS, "cl.jfactory.planillas.service.util.ValidacionProcesoGeneracion", "procesarEntidad", new Class[0], new Object[0], this);
	}

	
	public synchronized String getEntidad(){
		if(entidades!= null){
			if(entidades.size()>0){
				HashMap entidad = (HashMap)entidades.get(0);
				entidades.remove(0);
				return (String) entidad.get("ENTIDAD").toString();
			}
		}
		return null;
	}
	
	public void procesarEntidad(){
		String entidad = getEntidad();
		UtilLogProcesos.debug("reprocesando entidad: "+ entidad);
		GeneradorNominasHelper.generarTodas("codigo_entidad:"+entidad);
		new PoolHebras(1, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0], new ITerminadorHebra() {
			
			public void notificarCierre(int tipo) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void notificarCierre(int tipo) {
		// TODO Auto-generated method stub

		UtilLogProcesos.debug("fin reproceso ");

		EstadisticasHelper.terminar();
	}
	
}


