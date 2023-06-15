package cl.laaraucana.simat.documentos.RCP;

import java.util.LinkedList;

import cl.laaraucana.simat.documentos.JasperReport.escritorJasper;
import cl.laaraucana.simat.entidades.RCP_integrado;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/*
 * clase que permite completar, y escribir el cuadro resumen de cotizaciones previsionales.
 * */

public class EscritorRCP {

	public RespuestaEscrituraVO escribirRCP(String keyHF, String periodo) throws Exception {

		String keyRuta = null;

		LectorPropiedades lp = new LectorPropiedades();
		String ruta = null;
		String codigoEntidad = lp.getAtributoRepositorio("codigo_entidad");

		String nameFile = codigoEntidad + "_ANEXO4_" + periodo + ".xls";
		String nameFileView = codigoEntidad + "_ANEXO4_" + periodo;

		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		//leer ruta de property...
		//comprobar ruta	
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		//System.out.print("RUTARETORNADA formada: "+ruta);
		//completar documento
		CompletarResumenCotizPrev completadorRCP = new CompletarResumenCotizPrev();
		RCP_integrado rcp_Integrado = new RCP_integrado();
		System.out.print("bloque: B1P1");
		rcp_Integrado.setRcp_FondoUnico_1_FondosPensiones(completadorRCP.getValores_B1P1());
		System.out.print("bloque: B1P2");
		rcp_Integrado.setRcp_FondoUnico_2_FondosSalud(completadorRCP.getValores_B1P2());
		System.out.print("bloque: B1P3");
		rcp_Integrado.setRcp_FondoUnico_3_CotizDI(completadorRCP.getValores_B1P3());
		System.out.print("bloque: totalB1");
		rcp_Integrado.setRcp_FondoUnico_Total(completadorRCP.getValores_TotalB1(rcp_Integrado.getRcp_FondoUnico_1_FondosPensiones(), rcp_Integrado.getRcp_FondoUnico_2_FondosSalud(), rcp_Integrado
				.getRcp_FondoUnico_3_CotizDI()));

		System.out.print("bloque: B2P1");
		rcp_Integrado.setRcp_CargoBenef_1_FondosPensiones(completadorRCP.getValores_B2P1());
		System.out.print("bloque: B2P2");
		rcp_Integrado.setRcp_CargoBenef_2_FondosSalud(completadorRCP.getValores_B2P2());
		System.out.print("bloque: B2P3");
		rcp_Integrado.setRcp_CargoBenef_3_CotizSC(completadorRCP.getValores_B2P3());
		System.out.print("bloque: TotalB2");
		rcp_Integrado.setRcp_CargoBenef_Total(completadorRCP.getValores_TotalB2(rcp_Integrado.getRcp_CargoBenef_1_FondosPensiones(), rcp_Integrado.getRcp_CargoBenef_2_FondosSalud(), rcp_Integrado
				.getRcp_CargoBenef_3_CotizSC()));

		//añadimos entidad, periodo
		rcp_Integrado.setPeriodo(periodo);
		rcp_Integrado.setEntidad(entidad);
		//añadimos a lista el objeto obtenido 
		LinkedList listageneral = new LinkedList();
		listageneral.add(rcp_Integrado);
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"ResCotizPrevi.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "ResCotizPrevi.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//eas.writerBySamba(carpetaDestino,nameFile, archivoOrigen);
		boolean keyAux = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);

		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(keyAux);
		reVO.setNombreArchivo(nameFileView);
		return reVO;
	}//end escribirRCP
}//end class EscritorRCP
