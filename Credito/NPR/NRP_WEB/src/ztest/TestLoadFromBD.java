package ztest;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.liv.cargas.masivas.service.ICargaMasivaService;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class TestLoadFromBD {

	public static void main(String[] args) throws Exception {
		
		/*String origen = "carga_sap_config";
		String archivo = "select * from dummy_persona";
		String clase = "cl.liv.cargas.masivas.impl.CargaMasivaImplFromBD";
		String idCarga = "carga_sinacaff";
		idCarga = "carga_sinacaff";
		String idProperties = "planillas";
		HashMap config = new HashMap();
		config.put("origen", origen);
		config.put("archivo", archivo);
		config.put("clase_implementacion", clase);
		config.put("id_carga", idCarga);
		config.put("id_properties", idProperties);
		CargaMasivaFromBDImpl carga = new CargaMasivaFromBDImpl();
		boolean validate = carga.validate(config);
		System.out.println("validate: "+ validate);
		*/
		/*
		String nombre = UtilesComunes.reemplazarVariables("sys.YearMonth") + (int)(Math.random()* 1000);
		System.out.println(nombre);
		
		String archivo = "/home/clillo/nrp/input/SSMagallanes0718.xlsx";
		File excel = new File(archivo);
		System.out.println("archivo: "+ archivo);

		
		Workbook workbook = new XSSFWorkbook(excel);
		
		
		try {
			jxl.Workbook.getWorkbook(excel);
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(true) return;
		*/
		
		/*
		WorkFlowHelper.validarUltimosRegistrosMagallanes();
		
		if(true) return;
		System.out.println(UtilesComunes.getMD5("liv.cargas_masivas.planillas.carga_mag"));
		*/
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap("sqlMap_config");
		try {
			ArrayList procesos = new ArrayList();
			ArrayList origenes = (ArrayList) sqlMap.queryForList("planillas.obtenerProcesosCarga");
			System.out.println("obtener origenes configurados: "+ origenes);
			if(origenes != null && origenes.size()>0){
				for(int i=0; i<origenes.size(); i++){
					if( ((MiHashMap) origenes.get(i)).get("estado").toString().equals("1") ){
						ICargaMasivaService carga = (ICargaMasivaService) UtilReflectionImpl.getInstance( ((((MiHashMap) origenes.get(i)).get("clase_implementacion")).toString()) );
						
						boolean estado = carga.validate( (MiHashMap) origenes.get(i));
						System.out.println("estado proceso ["+ ((MiHashMap) origenes.get(i)).get("codigo") +"]:"+estado);
						JSONObject proceso = new JSONObject();
						proceso.put("codigo",((MiHashMap) origenes.get(i)).get("codigo"));
						if(estado){
							if(carga.execute((MiHashMap) origenes.get(i))==1){
								estado = true;
							}
							else{
								estado = false;
							}
						}
						proceso.put("estado",estado);
						procesos.add(proceso);
						
					}
				}
			}
			
			System.out.println("estado procesos: "+ procesos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
}
