package ztest;

import java.util.Date;

import cl.lib.export.txt.impl.GenerarTXT;

public class TestGeneraTXTAux {

	public static void main(String[] args) {
		
		
		GenerarTXT instancia = new GenerarTXT();
		
		String url = null;
		
		//url = instancia.generar("FORMATO_BANCO_BCI", "idcodconv: ;;tipo_nomina:4;;", "txt","", null);
		url = instancia.generar("NOM_HO0016_WAL", "filtro: and OFICINA_CREDITO =700 and rutemp not in (76243813, 77085380, 96867130, 76196870, 76258350, 76098502) ;;group_by: CODIGO_HOLDING ;;;;path_out:D://desarrollo/git/clillo/resources-nrp/nrp/output//202202/HO0016/NEWALSIN.xls", "xls",";", null);
		
		//[14/feb./2022 23:20:26,323][NRP_0] DEBUG - txt:NOM_HO0016_WAL, pars:filtro: and periodo = '202202'  and  CODIGO_HOLDING = 'HO0016'  and OFICINA_CREDITO = 804 and rutemp not in (76243813, 77085380, 96867130, 76196870, 76258350, 76098502);;group_by: CODIGO_HOLDING ;;;;path_out:D://desarrollo/git/clillo/resources-nrp/nrp/output//202202/HO0016/NEWALSHO.xls, formatoSalida:xls,separador:null, archivo:null
		//[14/feb./2022 23:22:13,930][main]  DEBUG - txt:txt:NOM_HO0016_WAL, pars:filtro: and periodo = '202202'  and  CODIGO_HOLDING = 'HO0016'  and OFICINA_CREDITO = 804 and rutemp not in (76243813, 77085380, 96867130, 76196870, 76258350, 76098502);;group_by: CODIGO_HOLDING ;;;;path_out:D://desarrollo/git/clillo/resources-nrp/nrp/output//202202/HO0016/NEWALSHO.xls, formatoSalida:xls,separador:null, archivo:null
		System.out.println("url -> "+ url);
		System.out.println("salida -->"+ instancia.dataTMP);
		
	}
	
}
