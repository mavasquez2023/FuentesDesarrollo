package cl.laaraucana.simat.documentos.CuadrosEstadisticos;

import java.util.LinkedList;

import cl.laaraucana.simat.documentos.JasperReport.escritorJasper;
import cl.laaraucana.simat.entidades.CuadroEstadistico_1_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_1_jasperVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_2_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_2_jasperVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_3_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_3_jasperVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_4_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_4_jasperVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_5_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_5_jasperVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_6_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_6_jasperVO;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;
import cl.laaraucana.simat.utiles.Utilxml;

/*
 * clase encargada de completar los datos de cuadros estadisticos y de escribirlos en el repositorio.
 * 
 * */

public class EscritorCuadrosEstadisticos {

	public EscritorCuadrosEstadisticos() {
	}

	//metodos que completaran y crearan el formato excel para el cuadro estadistico.	

	public RespuestaEscrituraVO escribirCuadro_1(String keyHF, String periodo) throws Exception {
		//Licencias médicas tramitadas según tipo de licencia médica.
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		String keyRuta = null;
		//String ruta=null;
		LectorPropiedades lp = new LectorPropiedades();
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String nameFile = codEntidad + "_IE1_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE1_" + periodo;
		//leer ruta de property

		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_1_VO ce1 = new CuadroEstadistico_1_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce1 = completarCE.obtenerDatosCuadro1();
		ce1.setPeriodo(periodo);
		ce1.setEntidad(entidad);
		ce1.setCodEntidad(codEntidad);
		CuadroEstadistico_1_jasperVO ce1_j = new CuadroEstadistico_1_jasperVO(ce1);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce1_j);

		//escritura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_1.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_1.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//eas.writerBySamba(carpetaDestino,nameFile, archivoOrigen);
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
		//escritura XML
		key_b = this.completarXML_CE1(keyHF, ce1);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}

		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;

	}//end escribirCuadro_1

	public RespuestaEscrituraVO escribirCuadro_2(String keyHF, String periodo) throws Exception {
		//numero subsidios iniciados segun tipo de subsidio
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		String keyRuta = null;
		//String ruta=null;
		//String nameFile="cuadro_IE2_"+periodo+"_"+keyHF+".xls";
		LectorPropiedades lp = new LectorPropiedades();
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String nameFile = codEntidad + "_IE2_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE2_" + periodo;
		//leer ruta de property...
		//			String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_2_VO ce2 = new CuadroEstadistico_2_VO("", "", "", 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce2 = completarCE.obtenerDatosCuadro2();
		ce2.setPeriodo(periodo);
		ce2.setEntidad(entidad);
		ce2.setCodEntidad(codEntidad);
		CuadroEstadistico_2_jasperVO ce2_j = new CuadroEstadistico_2_jasperVO(ce2);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce2_j);
		//escritura en xls
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_2.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_2.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);

		//escritura en xml
		key_b = this.completarXML_CE2(keyHF, ce2);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;
	}

	public RespuestaEscrituraVO escribirCuadro_3(String keyHF, String periodo) throws Exception {
		//		numero subsidios iniciados segun tipo de subsidio
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		String keyRuta = null;
		//String ruta=null;
		//String nameFile="cuadro_IE3_"+periodo+"_"+keyHF+".xls";
		LectorPropiedades lp = new LectorPropiedades();
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String nameFile = codEntidad + "_IE3_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE3_" + periodo;
		//leer ruta de property...

		//			String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_3_VO ce3 = new CuadroEstadistico_3_VO("", "", "", 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce3 = completarCE.obtenerDatosCuadro3();
		ce3.setPeriodo(periodo);
		ce3.setEntidad(entidad);
		ce3.setCodEntidad(codEntidad);

		CuadroEstadistico_3_jasperVO ce3_j = new CuadroEstadistico_3_jasperVO(ce3);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce3_j);
		//escrtitura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_3.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_3.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);

		//escritura XML
		key_b = this.completarXML_CE3(keyHF, ce3);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;

	}

	public RespuestaEscrituraVO escribirCuadro_4(String keyHF, String periodo) throws Exception {
		String keyRuta = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		//String ruta=null;
		//String nameFile="cuadro_IE4_"+periodo+"_"+keyHF+".xls";
		LectorPropiedades lp = new LectorPropiedades();
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String nameFile = codEntidad + "_IE4_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE4_" + periodo;
		//leer ruta de property...
		//			String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_4_VO ce4 = new CuadroEstadistico_4_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce4 = completarCE.obtenerDatosCuadro4();
		ce4.setPeriodo(periodo);
		ce4.setEntidad(entidad);
		ce4.setCodEntidad(codEntidad);
		CuadroEstadistico_4_jasperVO ce4_j = new CuadroEstadistico_4_jasperVO(ce4);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce4_j);
		//escritura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_4.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_4.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
		//escritura XML
		key_b = this.completarXML_CE4(keyHF, ce4);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;

	}

	public RespuestaEscrituraVO escribirCuadro_5(String keyHF, String periodo) throws Exception {
		String keyRuta = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		//String ruta=null;
		//String nameFile="cuadro_IE5_"+periodo+"_"+keyHF+".xls";
		LectorPropiedades lp = new LectorPropiedades();
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String nameFile = codEntidad + "_IE5_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE5_" + periodo;
		//leer ruta de property...
		//			String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_5_VO ce5 = new CuadroEstadistico_5_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce5 = completarCE.obtenerDatosCuadro5();
		ce5.setPeriodo(periodo);
		ce5.setEntidad(entidad);
		ce5.setCodEntidad(codEntidad);
		CuadroEstadistico_5_jasperVO ce5_j = new CuadroEstadistico_5_jasperVO(ce5);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce5_j);
		//escritura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_5.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_5.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);

		//escritura XML
		key_b = this.completarXML_CE5(keyHF, ce5);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;

	}

	public RespuestaEscrituraVO escribirCuadro_6(String keyHF, String periodo) throws Exception {
		String keyRuta = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		//String ruta=null;
		//String nameFile="cuadro_IE6_"+periodo+"_"+keyHF+".xls";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		String entidad = lp.getAtributoRepositorio("nombre_entidad");
		String nameFile = codEntidad + "_IE6_" + periodo + ".xls";
		String nameFileView = codEntidad + "_IE6_" + periodo;
		//leer ruta de property...
		//			String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");

		CuadroEstadistico_6_VO ce6 = new CuadroEstadistico_6_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0);
		CompletarCuadrosEstadisticos completarCE = new CompletarCuadrosEstadisticos();
		ce6 = completarCE.obtenerDatosCuadro6();
		ce6.setPeriodo(periodo);
		ce6.setEntidad(entidad);
		ce6.setCodEntidad(codEntidad);
		CuadroEstadistico_6_jasperVO ce6_j = new CuadroEstadistico_6_jasperVO(ce6);
		LinkedList listageneral = new LinkedList();

		listageneral.add(ce6_j);
		//escritura XLS
		escritorJasper jreport = new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"cuadroEstadistico_6.jrxml").getPath();
		String template = lp.getAtributoRepositorio("rutaTemplateJasper") + "cuadroEstadistico_6.jrxml";
		jreport.writeJasper(rutaTemporal, template, listageneral);

		//File archivoOrigen=new File(rutaTemporal);
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		key_a = eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
		//escritura en xml
		key_b = this.completarXML_CE6(keyHF, ce6);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		RespuestaEscrituraVO reVO = new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;
	}

	//generadores de formato XML
	public boolean completarXML_CE1(String keyHF, CuadroEstadistico_1_VO ce1) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE1_" + ce1.getPeriodo() + ".xml";

		//instanciamos cuadro estadistico a escribir,
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();

		//archivo.append("<?xml version='1.0' encoding='UTF-8'?>"+"\n");
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));
		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce1.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce1.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce1.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce1.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"1\" TITULO=\"Licencias medicas tramitadas segun tipo de licencia medica\"/>" + "\n");
		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));

		archivo.append(uXml.getOpenTag("ReposoPrenatal"));
		archivo.append(uXml.getOpenTag("LicenciasTramitadas"));
		archivo.append(uXml.getValueTag("AutSinModificacion", Integer.toString(ce1.getReposoPrenatal_Lic_SinModificacion())));
		archivo.append(uXml.getValueTag("AutModificadas", Integer.toString(ce1.getReposoPrenatal_Lic_Modificadas())));
		archivo.append(uXml.getValueTag("AutReconsideradas", Integer.toString(ce1.getReposoPrenatal_Lic_Reconsideradas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce1.getReposoPrenatal_Lic_Rechazadas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce1.getReposoPrenatal_Lic_total())));
		archivo.append(uXml.getCloseTag("LicenciasTramitadas"));
		archivo.append(uXml.getOpenTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getValueTag("Autorizados", Integer.toString(ce1.getReposoPrenatal_Dias_Autorizados())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce1.getReposoPrenatal_Dias_Reconsiderados())));
		archivo.append(uXml.getValueTag("Rechazados", Integer.toString(ce1.getReposoPrenatal_Dias_Rechazados())));
		archivo.append(uXml.getCloseTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getCloseTag("ReposoPrenatal"));

		archivo.append(uXml.getOpenTag("ReposoPostnatal"));
		archivo.append(uXml.getOpenTag("LicenciasTramitadas"));
		archivo.append(uXml.getValueTag("AutSinModificacion", Integer.toString(ce1.getReposoPostnatal_Lic_SinModificacion())));
		archivo.append(uXml.getValueTag("AutModificadas", Integer.toString(ce1.getReposoPostnatal_Lic_Modificadas())));
		archivo.append(uXml.getValueTag("AutReconsideradas", Integer.toString(ce1.getReposoPostnatal_Lic_Reconsideradas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce1.getReposoPostnatal_Lic_Rechazadas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce1.getReposoPostnatal_Lic_total())));
		archivo.append(uXml.getCloseTag("LicenciasTramitadas"));
		archivo.append(uXml.getOpenTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getValueTag("Autorizados", Integer.toString(ce1.getReposoPostnatal_Dias_Autorizados())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce1.getReposoPostnatal_Dias_Reconsiderados())));
		archivo.append(uXml.getValueTag("Rechazados", Integer.toString(ce1.getReposoPostnatal_Dias_Rechazados())));
		archivo.append(uXml.getCloseTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getCloseTag("ReposoPostnatal"));

		archivo.append(uXml.getOpenTag("PermisoEGNM"));
		archivo.append(uXml.getOpenTag("LicenciasTramitadas"));
		archivo.append(uXml.getValueTag("AutSinModificacion", Integer.toString(ce1.getEnferGravNiñoMenor1_Lic_SinModificacion())));
		archivo.append(uXml.getValueTag("AutModificadas", Integer.toString(ce1.getEnferGravNiñoMenor1_Lic_Modificadas())));
		archivo.append(uXml.getValueTag("AutReconsideradas", Integer.toString(ce1.getEnferGravNiñoMenor1_Lic_Reconsideradas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce1.getEnferGravNiñoMenor1_Lic_Rechazadas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce1.getEnferGravNiñoMenor1_Lic_total())));
		archivo.append(uXml.getCloseTag("LicenciasTramitadas"));
		archivo.append(uXml.getOpenTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getValueTag("Autorizados", Integer.toString(ce1.getEnferGravNiñoMenor1_Dias_Autorizados())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce1.getEnferGravNiñoMenor1_Dias_Reconsiderados())));
		archivo.append(uXml.getValueTag("Rechazados", Integer.toString(ce1.getEnferGravNiñoMenor1_Dias_Reconsiderados())));
		archivo.append(uXml.getCloseTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getCloseTag("PermisoEGNM"));

		archivo.append(uXml.getOpenTag("Totales"));
		archivo.append(uXml.getOpenTag("LicenciasTramitadas"));
		archivo.append(uXml.getValueTag("AutSinModificacion", Long.toString(ce1.getTotales_Lic_SinModificacion())));
		archivo.append(uXml.getValueTag("AutModificadas", Long.toString(ce1.getTotales_Lic_Modificadas())));
		archivo.append(uXml.getValueTag("AutReconsideradas", Long.toString(ce1.getTotales_Lic_Reconsideradas())));
		archivo.append(uXml.getValueTag("Rechazadas", Long.toString(ce1.getTotales_Lic_Rechazadas())));
		archivo.append(uXml.getValueTag("Total", Long.toString(ce1.getTotales_Lic_total())));
		archivo.append(uXml.getCloseTag("LicenciasTramitadas"));
		archivo.append(uXml.getOpenTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getValueTag("Autorizados", Long.toString(ce1.getTotales_Dias_Autorizados())));
		archivo.append(uXml.getValueTag("Reconsideradas", Long.toString(ce1.getTotales_Dias_Reconsiderados())));
		archivo.append(uXml.getValueTag("Rechazados", Long.toString(ce1.getTotales_Dias_Rechazados())));
		archivo.append(uXml.getCloseTag("DiasLicenciaTramitados"));
		archivo.append(uXml.getCloseTag("Totales"));

		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce1.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		//File archivoOrigen=new File(rutaTemporal);
		//eas.writerBySamba(carpetaDestino,nameFileDestinoXML, archivoOrigen);
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);
	}

	public boolean completarXML_CE2(String keyHF, CuadroEstadistico_2_VO ce2) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		//nameFileDestinoXML="cuadro_IE2_"+ce2.getPeriodo()+"_"+keyHF+".xml";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE2_" + ce2.getPeriodo() + ".xml";

		//instanciamos cuadro estadistico a escribir,
		//CuadroEstadistico_2_VO ce2= new CuadroEstadistico_2_VO("", "", 0,0, 0, 0, 0);
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));
		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce2.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce2.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce2.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce2.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"02\" TITULO=\"Numero de subsidios iniciados segun tipo de subsidio\"/>" + "\n");
		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));
		archivo.append(uXml.getValueTag("SubReposoPrenatal", Integer.toString(ce2.getSub_Reposo_Prenatal())));
		archivo.append(uXml.getValueTag("SubReposoPostnatal", Integer.toString(ce2.getSub_Reposo_Postnatal())));
		archivo.append(uXml.getValueTag("SubPermisoParental", Integer.toString(ce2.getSub_Permiso_Postnatal_Parental())));
		archivo.append(uXml.getValueTag("SubEGNM", Integer.toString(ce2.getSub_Permiso_enferm_Grave_niño_menor())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce2.getTotales_NumSubs_iniciados())));
		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce2.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);

	}

	public boolean completarXML_CE3(String keyHF, CuadroEstadistico_3_VO ce3) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		//nameFileDestinoXML="cuadro_IE3_"+ce3.getPeriodo()+"_"+keyHF+".xml";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE3_" + ce3.getPeriodo() + ".xml";

		//instanciamos cuadro estadistico a escribir,
		//CuadroEstadistico_3_VO ce3= new CuadroEstadistico_3_VO("", "", 0,0, 0, 0, 0);
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));
		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce3.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce3.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce3.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce3.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"3\" TITULO=\"Numero de dias de subsidio pagados segun tipo de subsidio\"/>" + "\n");
		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));
		archivo.append(uXml.getValueTag("SubReposoPrenatal", Integer.toString(ce3.getSub_Reposo_Prenatal())));
		archivo.append(uXml.getValueTag("SubReposoPostnatal", Integer.toString(ce3.getSub_Reposo_Postnatal())));
		archivo.append(uXml.getValueTag("SubPermisoParental", Integer.toString(ce3.getSub_Permiso_Postnatal_Parental())));
		archivo.append(uXml.getValueTag("SubEGNM", Integer.toString(ce3.getSub_Permiso_enferm_Grave_niño_menor())));
		archivo.append(uXml.getValueTag("Total", Long.toString(ce3.getTotal_Dias_Subsidios_Pagados())));
		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce3.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);

	}

	public boolean completarXML_CE4(String keyHF, CuadroEstadistico_4_VO ce4) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		//nameFileDestinoXML="cuadro_IE4_"+ce4.getPeriodo()+"_"+keyHF+".xml";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE4_" + ce4.getPeriodo() + ".xml";

		//instanciamos cuadro estadistico a escribir,
		//CuadroEstadistico_4_VO ce4= new CuadroEstadistico_4_VO("", "", 0,0, 0, 0, 0, 0, 0, 0, 0);
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));
		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce4.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce4.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce4.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce4.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"4\" TITULO=\"Subsidios por permiso postnatal parental segun extension del permiso\"/>" + "\n");
		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));

		archivo.append(uXml.getOpenTag("SubPermisoParentalIniciados"));
		archivo.append(uXml.getValueTag("JornadaCompleta", Integer.toString(ce4.getSub_PostPar_Iniciados_Jornada_Completa())));
		archivo.append(uXml.getValueTag("JornadaParcial", Integer.toString(ce4.getSub_PostPar_Iniciados_Jornada_Parcial())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce4.getSub_PostPar_Iniciados_Total())));
		archivo.append(uXml.getCloseTag("SubPermisoParentalIniciados"));

		archivo.append(uXml.getOpenTag("SubPermisoParentalTraspasados"));
		archivo.append(uXml.getValueTag("JornadaCompleta", Integer.toString(ce4.getSub_PostPar_Traspasados_Jornada_Completa())));
		archivo.append(uXml.getValueTag("JornadaParcial", Integer.toString(ce4.getSub_PostPar_Traspasados_Jornada_Parcial())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce4.getSub_PostPar_Traspasados_Total())));
		archivo.append(uXml.getCloseTag("SubPermisoParentalTraspasados"));

		archivo.append(uXml.getOpenTag("SubPermisoParentalPagados"));
		archivo.append(uXml.getValueTag("JornadaCompleta", Integer.toString(ce4.getSub_PostPar_Pagados_Jornada_Completa())));
		archivo.append(uXml.getValueTag("JornadaParcial", Integer.toString(ce4.getSub_PostPar_Pagados_Jornada_Parcial())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce4.getSub_PostPar_Pagados_Total())));
		archivo.append(uXml.getCloseTag("SubPermisoParentalPagados"));

		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce4.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);
	}

	public boolean completarXML_CE5(String keyHF, CuadroEstadistico_5_VO ce5) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		//nameFileDestinoXML="cuadro_IE5_"+ce5.getPeriodo()+"_"+keyHF+".xml";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE5_" + ce5.getPeriodo() + ".xml";
		//instanciamos cuadro estadistico a escribir,
		//CuadroEstadistico_5_VO ce5= new CuadroEstadistico_5_VO("", "", 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));
		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce5.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce5.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce5.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce5.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"5\" TITULO=\"Licencias medicas tramitadas por subsidios a mujeres sin contrato vigente segun tipo de licencia medica\"/>" + "\n");
		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));

		archivo.append(uXml.getOpenTag("SubReposoPrenatal"));
		archivo.append(uXml.getValueTag("Autorizadas", Integer.toString(ce5.getSub_Reposo_Prenatal_Autorizadas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce5.getSub_Reposo_Prenatal_Rechazadas())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce5.getSub_Reposo_Prenatal_Autorizadas_Reconsideradas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce5.getTotal_Licencias_Reposo_Prenatal())));
		archivo.append(uXml.getCloseTag("SubReposoPrenatal"));

		archivo.append(uXml.getOpenTag("SubReposoPostnatal"));
		archivo.append(uXml.getValueTag("Autorizadas", Integer.toString(ce5.getSub_Reposo_Postnatal_Autorizadas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce5.getSub_Reposo_Postnatal_Rechazadas())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce5.getSub_Reposo_Postnatal_Autorizadas_Reconsideradas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce5.getTotal_Licencias_Reposo_Postnatal())));
		archivo.append(uXml.getCloseTag("SubReposoPostnatal"));

		archivo.append(uXml.getOpenTag("Totales"));
		archivo.append(uXml.getValueTag("Autorizadas", Integer.toString(ce5.getTotales_Autorizadas_Reconsideradas())));
		archivo.append(uXml.getValueTag("Rechazadas", Integer.toString(ce5.getTotales_Rechazadas())));
		archivo.append(uXml.getValueTag("Reconsideradas", Integer.toString(ce5.getTotales_Autorizadas_Reconsideradas())));
		archivo.append(uXml.getValueTag("Total", Integer.toString(ce5.getTotales_Total())));
		archivo.append(uXml.getCloseTag("Totales"));

		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce5.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("[* * * * ]" + rutaTemporal + "[* * * * *]");
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);
	}

	public boolean completarXML_CE6(String keyHF, CuadroEstadistico_6_VO ce6) throws Exception {
		Utilxml uXml = null;
		String nameFileDestinoXML = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		//nameFileDestinoXML="cuadro_IE6_"+ce6.getPeriodo()+"_"+keyHF+".xml";
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		nameFileDestinoXML = codEntidad + "_IE6_" + ce6.getPeriodo() + ".xml";
		//instanciamos cuadro estadistico a escribir,
		//CuadroEstadistico_6_VO ce6= new CuadroEstadistico_6_VO("", "", 0,0, 0, 0, 0, 0, 0, 0);
		//obtenemos valores de completacion CE1, 
		//test en cero...
		//creamos archivo
		uXml = new Utilxml();
		StringBuffer archivo = new StringBuffer();
		archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n");
		//nodo padre
		archivo.append(uXml.getOpenTag("INFORMACION_ESTADISTICA xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));

		archivo.append(uXml.getOpenTag("ZONA_0"));

		archivo.append("\n" + "<ENTIDAD COD_ENTIDAD=\"" + ce6.getCodEntidad() + "\" NOMBRE_ENTIDAD=\"" + ce6.getEntidad() + "\"/>" + "\n");
		archivo.append("<PERIODO ANNO=\"" + hfa.getSeparacionPeriodoAño(ce6.getPeriodo()) + "\" MES=\"" + hfa.getSeparacionPeriodoMes(ce6.getPeriodo()) + "\"/>" + "\n");
		archivo.append("<CUADRO NUMERO=\"6\" TITULO=\"Subsidios a mujeres sin contrato vigente\"/>" + "\n");

		archivo.append(uXml.getCloseTag("ZONA_0"));
		archivo.append(uXml.getOpenTag("DATOS"));

		archivo.append(uXml.getOpenTag("SubsidiosIniciados"));
		archivo.append(uXml.getValueTag("SubReposoPrenatal", Integer.toString(ce6.getNum_sub_Iniciados_Prenatal())));
		archivo.append(uXml.getValueTag("SubReposoPrenatalComplementario", Integer.toString(ce6.getNum_sub_Iniciados_Prenatal_Complementario())));
		archivo.append(uXml.getValueTag("SubReposoPostnatal", Integer.toString(ce6.getNum_sub_Iniciados_Postnatal())));
		archivo.append(uXml.getValueTag("SubPermisoParental", Integer.toString(ce6.getNum_sub_Iniciados_Permiso_Postnatal_Parental())));
		archivo.append(uXml.getCloseTag("SubsidiosIniciados"));

		archivo.append(uXml.getOpenTag("SubsidiosPagados"));
		archivo.append(uXml.getValueTag("SubReposoPrenatal", Integer.toString(ce6.getNum_sub_Pagados_Prenatal())));
		archivo.append(uXml.getValueTag("SubReposoPrenatalComplementario", Integer.toString(ce6.getNum_sub_Pagados_Prenatal_Complementario())));
		archivo.append(uXml.getValueTag("SubReposoPostnatal", Integer.toString(ce6.getNum_sub_Pagados_Postnatal())));
		archivo.append(uXml.getValueTag("SubPermisoParental", Integer.toString(ce6.getNum_sub_Pagados_Permiso_Postnatal_Parental())));
		archivo.append(uXml.getCloseTag("SubsidiosPagados"));

		archivo.append(uXml.getCloseTag("DATOS"));
		archivo.append(uXml.getCloseTag("INFORMACION_ESTADISTICA"));

		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//comprabador de ruta
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFileDestinoXML;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFileDestinoXML;
		String carpetaDestino = ce6.getPeriodo() + "/";
		eas.writerXml(archivo, rutaTemporal);

		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		return eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML, rutaTemporal);
	}

}
