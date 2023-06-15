package ztest;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.jfactory.planillas.service.util.ITerminadorHebra;
import cl.jfactory.planillas.service.util.PoolHebras;
import cl.jfactory.planillas.service.util.UtilLogThread;

public class TestGeneracion {

	public static void main(String[] args) {
		
		/*HashMap parametros = new HashMap();
		parametros.put("codigo_entidad","");

		try {
			new ConfiguradorHelper().generarNominas(new HashMap(), parametros);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//if(true) return;
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:61607900");
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:94637000");
		//TRABAJADOR
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:2383038");
		//PENSIONADO
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:96933030");
		//NOM_61004000_GEN
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:60910000;;agrupar_por:RUT_PAGADOR;;SUM:IMPORTE_CUOTA_MONEDA_LOCAL");
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:76012833");
		//new PoolHebras(1, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0]);
		
		
		/*
		String sql = "SELECT YEAR(CURRENT_DATE) ANIO, MONTH(CURRENT_DATE) MES, SUM(IMPORTE_CUOTA_MONEDA_LOCAL) TOTAL_CUOTAS, SUM(DISTINCT MONTO_total_NOMINA ) TOTAL_NOMINAS, SUM(IMPORTE_CUOTA_MONEDA_LOCAL + IMPORTE_GRAVE_MONEDA_LOCAL) TOTAL_CUOTAS_GRAVAMEN, COUNT(1) CANTIDAD_REGISTROS  #count_agrupado#  FROM  NRPDTA.NRP15F1 where 1 = 1  and periodo = '201808'  and  RUT_EMPRESA = '61606404'  and (fecha_colocacion <'20180509') or RUT_EMPRESA =61606404 and (fecha_colocacion >'20180508' and producto <> 'SAP') ";
		
		
		String sqlAgrupado = sql.substring(sql.indexOf("FROM"))  ;
		
		sqlAgrupado = ", (select count(1) from ( select count(1) " + sqlAgrupado + " group by RUT_PAGADOR,NRO_INSCRIPCION ) a ) CANTIDAD_REGISTROS";
		
		System.out.println(sqlAgrupado);
		*/
		
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:90743000");
		//60805010
		
		//61.979.440-0.
		//UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201901");
		//UtilesComunes.variablesEstaticas.put("sys.year4", "2019");
		//UtilesComunes.variablesEstaticas.put("sys.month2", "01");
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:61607900");
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:61533000");
		
		/*new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					new NominaResumenIPS().obtenerResumenesIPS(new HashMap());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();*/
		
		//IPS
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:61533000");
		
		
		//Dipreca
		//new GeneradorNominasHelper().generarTodas( "codigo_entidad:61513000");
		
		//Empresa sin nomina
		//new GeneradorNominasHelper().generarTodas("codigo_entidad:1655081");

		//Empresa huachipato
		//new GeneradorNominasHelper().generarTodas("codigo_entidad:94637000");
		
		//Empresa magallanes
		//new GeneradorNominasHelper().generarTodas("codigo_entidad:61607900");
		
		//new EstadisticasHelper().inicializarTabla();
		//UtilesComunes.variablesEstaticas.put("sys.YearMonth", "202004");
		//String codigoEntidad = "70000850";
		String codigoEntidad = "60805010";
		codigoEntidad =  "HO0016";
		//String codigoEntidad = "61513000";
		//ConfiguradorHelper.eliminarCMDFilesPorEntidad(codigoEntidad);
		//new GeneradorNominasHelper().generarTodas("codigo_entidad:"+codigoEntidad);
		new GeneradorNominasHelper().generarTodas("codigo_entidad:"+codigoEntidad);
		new PoolHebras(1, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0], new ITerminadorHebra() {
			
			public void notificarCierre(int tipo) {
				// TODO Auto-generated method stub

				System.out.println("notificar el cierre Generacion");
				UtilLogThread.debug("notificar cierre Generacion");
			}
		});
		
	}
}
