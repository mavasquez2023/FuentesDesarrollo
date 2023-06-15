package ztest;

import java.util.Date;

import cl.lib.export.txt.impl.GenerarTXT;

public class TestGeneraTXT {

	public static void main(String[] args) {
		
		//System.out.println( new GenerarTXT().generar("NOM_76012833_REN", "filtro:and periodo = '201808'  and  RUT_EMPRESA = '76012833'  ", "txt",";",null));
		
		//System.out.println( new GenerarTXT().generar("NOM_TEST_NRP_INSCR", "filtro:and nro_inscripcion like '%-%'   and id_data_archivo < 16080601", "txt",";", null));
		
		
		/*
		[sysout] Nombre Reporte: NOM_61533000_QA
[sysout] Parametros: filtro: and periodo = '201911' and  RUT_EMPRESA = '61533000'  ;;group_by: RUT_EMPRESA ;;;;path_out:/Users/luisibacache/dev/fuentes/git/clillo/resources-nrp/nrp/output//201911/61533000/0615330001219.txt
[sysout] FormatoSalida: txt
[sysout] Separador: 
[sysout] ArchivoResultado: null
		
		*/
		GenerarTXT instancia = new GenerarTXT();
		
		instancia.generar("NOM_61533000_IPS", "filtro: and periodo = '202003' and codigo_entidad:61533000;;tipo_nomina:4;;agrupar_por:RUTBEN,NUMINS;;SUM:MONDE;;encoding:ISO-8859-1;;;;path_out:/home/desarrollo/git/clillo/resources-nrp/nrp/cfg/export_all/IPS_"+new Date().getTime()+".txt", "txt",";", null);
		System.out.println("salida -->"+ instancia.dataTMP);
		
	}
	
}
