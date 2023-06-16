package cl.laaraucana.simat.documentos.ILF4501;

import java.util.LinkedList;

import cl.laaraucana.simat.documentos.JasperReport.escritorJasper;
import cl.laaraucana.simat.entidades.CuadroILF4501VO;
import cl.laaraucana.simat.entidades.CuadroILF4501_jasperVO;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/*
 * clase que permite completar el formato ilf4501 y escribirlo
 * */

public class EscritorILF4501 {

	public RespuestaEscrituraVO escribirILF4501(String keyHF, String periodo) throws Exception {
		String keyRuta = null;

		String ruta = null;
		LectorPropiedades lp = new LectorPropiedades();
		String codigoEntidad = lp.getAtributoRepositorio("codigo_entidad");
		//String nameFile="ILF4501_"+periodo+"_"+keyHF+".xls";
		String nameFile = codigoEntidad + "_ILF4501_" + periodo + ".xls";
		String nameFileView = codigoEntidad + "_ILF4501_" + periodo;

		String entidad = lp.getAtributoRepositorio("nombre_entidad");

		//leer ruta de property...
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		CuadroILF4501VO ilf4501 = new CuadroILF4501VO(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		CompletarILF4501 cILF = new CompletarILF4501();
		ilf4501 = cILF.completadorILF4501(ilf4501);

		ilf4501.setPeriodo(periodo);
		ilf4501.setEntidad(entidad);
		LinkedList listageneral = new LinkedList();
		//para separacion de decimales
		CuadroILF4501_jasperVO ilf4501_j = new CuadroILF4501_jasperVO(ilf4501);
		listageneral.add(ilf4501_j);
		//listageneral.add(ilf4501);
		//escritura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"ILF4501.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "ILF4501.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//eas.writerBySamba(carpetaDestino,nameFile, archivoOrigen);
		boolean keyAux = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);

		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(keyAux);
		reVO.setNombreArchivo(nameFileView);
		return reVO;
	}//end escribirILF4501

}//end EscritorILF4501
