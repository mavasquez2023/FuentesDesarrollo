package cl.laaraucana.simat.documentos.InformeFinanciero;

import java.util.ArrayList;
import java.util.LinkedList;

import cl.laaraucana.simat.documentos.JasperReport.escritorJasper;
import cl.laaraucana.simat.entidades.InformeFinancieroVO;
import cl.laaraucana.simat.entidades.InformeFinanciero_XMLVO;
import cl.laaraucana.simat.entidades.InformeFinanciero_jasperVO;
import cl.laaraucana.simat.entidades.RespuestaEscrituraVO;
import cl.laaraucana.simat.mannagerDB2.InformeFinancieroMannager;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;
import cl.laaraucana.simat.utiles.Utilxml;

public class EscritorInformeFinanciero {
	
	public RespuestaEscrituraVO  escribirInformeFinanciero(String periodo)throws Exception{
			//String keyRuta=null;
		boolean key_a=false;
		boolean key_b=false;
		boolean key_res=false;
		LectorPropiedades lp=new LectorPropiedades();
		//String ruta=null;
		String codigoEntidad=lp.getAtributoRepositorio("codigo_entidad");
		
		String nameFile=codigoEntidad+"_IF_"+periodo+".xls";
		String nameFileView=codigoEntidad+"_IF_"+periodo;
		
		String entidad=lp.getAtributoRepositorio("nombre_entidad");
		
//leer ruta de property...
//comprobar ruta, para crearla en caso que no exista		
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaTemporal")).getPath()+nameFile;
		String rutaTemporal=lp.getAtributoRepositorio("rutaLocalTemporales")+nameFile;
		String carpetaDestino=periodo+"/";
		InformeFinancieroVO IF_Manual=new InformeFinancieroVO(0,entidad, periodo, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
	//obtenemos datos enviados, dispuestos en balance general 
		
		InformeFinancieroMannager mannagerIF=new InformeFinancieroMannager();
		ArrayList listaIF=mannagerIF.getTodoInformeFinanciero();
		
		try{
			IF_Manual=(InformeFinancieroVO) listaIF.get(0);
		}catch(Exception ex){
			IF_Manual=new InformeFinancieroVO(0,entidad, periodo, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		}
		IF_Manual.setPeriodo(periodo);
		IF_Manual.setNombre_entidad(entidad);
		InformeFinanciero_jasperVO IF_jasper = new InformeFinanciero_jasperVO(IF_Manual);
		//InformeFinanciero_XMLVO IF_jasper = new InformeFinanciero_XMLVO(IF_Manual);
		
		LinkedList listageneral=new LinkedList();
		//System.out.println("start: IF_JASPER");
		//System.out.println(IF_jasper);
		//System.out.println("End: IF_JASPER");
		
		//listageneral.add(IF_Manual);
		listageneral.add(IF_jasper);
		
	//escritura XLS
		escritorJasper jreport=new escritorJasper();
		//String template=getClass().getResource(lp.getAtributoRepositorio("rutaTemplateJasper")+"Informe_Financiero.jrxml").getPath();
		String template=lp.getAtributoRepositorio("rutaTemplateJasper")+"Informe_Financiero.jrxml";
		
		jreport.writeJasper(rutaTemporal, template,listageneral);
		EscritorArchivoSimple eas= new EscritorArchivoSimple();
		key_a=eas.setCopyByIFS(carpetaDestino, nameFile,rutaTemporal);
		
	//escritura XML
		key_b=this.completarXML_IF(carpetaDestino,IF_Manual);
				
		//key_a=true;
		//key_b=true;
		
		if(key_a && key_b){
			key_res=true;
		}else{
			key_res=false;
		}			
		RespuestaEscrituraVO reVO=new RespuestaEscrituraVO();
		reVO.setEstado(key_res);
		reVO.setNombreArchivo(nameFileView);
		return reVO;
	}
	
	
	public boolean completarXML_IF(String carpetaDestino,InformeFinancieroVO infoFin)throws Exception{
		boolean key=false;
		
		System.out.println("[* * *]completarXML_IF[* * *]");
		//el vo xml, extrae  algunos valores absoluto que deben ser transferidos al xml informe financiero.
		InformeFinanciero_XMLVO infoFinc = new InformeFinanciero_XMLVO(infoFin);
		
		Utilxml uXml=null;
		String nameFileDestinoXML=null;
		
		ManejoHoraFecha hfa=new ManejoHoraFecha();
		LectorPropiedades lp=new LectorPropiedades();
		String codEntidad=lp.getAtributoRepositorio("codigo_entidad");
		
		
		nameFileDestinoXML=codEntidad+"_IF_"+infoFinc.getPeriodo()+".xml";
			
			uXml=new Utilxml();
			StringBuffer archivo = new StringBuffer();
			//archivo.append("<?xml version='1.0' encoding='UTF-8'?>"+"\n");
			archivo.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+"\n");
			
			//nodo padre
			//archivo.append(uXml.getOpenTag("INFORME"));
			archivo.append(uXml.getOpenTag("INFORME xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""));
			
				archivo.append(uXml.getOpenTag("ZONA_0"));
					archivo.append("<ENTIDAD COD_ENTIDAD=\""+codEntidad+"\" NOMBRE_ENTIDAD=\""+infoFinc.getNombre_entidad()+"\"/>"+"\n");
					archivo.append("<PERIODO ANNO=\""+hfa.getSeparacionPeriodoAño(infoFinc.getPeriodo())+"\" MES=\""+hfa.getSeparacionPeriodoMesNum(infoFinc.getPeriodo())+"\"/>"+"\n");
					
				archivo.append(uXml.getCloseTag("ZONA_0"));
				archivo.append(uXml.getOpenTag("ZONA_A DESCRIPCION=\"INGRESOS\""));
				
					//archivo.append(uXml.getValueTag("A1", Long.toString(infoFinc.getA_1())));
					archivo.append(uXml.getValueTag("A1", infoFinc.getA_1()));
					archivo.append(uXml.getValueTag("A2", infoFinc.getA_2()));
					
					archivo.append(uXml.getOpenTag("A3"));
						archivo.append(uXml.getValueTag("A31", infoFinc.getA_3_1()));
						archivo.append(uXml.getValueTag("A32", infoFinc.getA_3_2()));
						archivo.append(uXml.getValueTag("TOTAL_A3", infoFinc.getA_3()));
					archivo.append(uXml.getCloseTag("A3"));
					
					archivo.append(uXml.getOpenTag("A4"));
						archivo.append(uXml.getValueTag("A41", (infoFinc.getA_4_1())));
						archivo.append(uXml.getValueTag("A42", (infoFinc.getA_4_2())));
						archivo.append(uXml.getValueTag("TOTAL_A4", (infoFinc.getA_4())));
					archivo.append(uXml.getCloseTag("A4"));
					
					archivo.append(uXml.getValueTag("TOTAL_A", infoFinc.getA()));
					
				archivo.append(uXml.getCloseTag("ZONA_A"));
				
				archivo.append(uXml.getOpenTag("ZONA_B DESCRIPCION=\"EGRESOS\""));
					archivo.append(uXml.getValueTag("TOTAL_B", infoFinc.getB()));
				archivo.append(uXml.getCloseTag("ZONA_B"));
				
				archivo.append(uXml.getOpenTag("ZONA_C DESCRIPCION=\"SUBTOTAL GASTO EN SUBSIDIOS\""));
					archivo.append(uXml.getValueTag("C1", infoFinc.getC_1()));
					archivo.append(uXml.getValueTag("C2", infoFinc.getC_2()));
					archivo.append(uXml.getValueTag("C3", infoFinc.getC_3()));
					archivo.append(uXml.getValueTag("C4", infoFinc.getC_4()));
					archivo.append(uXml.getValueTag("C5", infoFinc.getC_5()));

					archivo.append(uXml.getOpenTag("C6"));
						archivo.append(uXml.getValueTag("C61", infoFinc.getC_6_1()));
						archivo.append(uXml.getValueTag("C62", infoFinc.getC_6_2()));
						archivo.append(uXml.getValueTag("C63", infoFinc.getC_6_3()));
						archivo.append(uXml.getValueTag("C64", infoFinc.getC_6_4()));
						archivo.append(uXml.getValueTag("C65", infoFinc.getC_6_5()));
						archivo.append(uXml.getValueTag("TOTAL_C6", infoFinc.getC_6()));
					archivo.append(uXml.getCloseTag("C6"));
					
					archivo.append(uXml.getOpenTag("C7"));
						archivo.append(uXml.getValueTag("C71",infoFinc.getC_7_1()));
						archivo.append(uXml.getValueTag("C72", infoFinc.getC_7_2()));
						archivo.append(uXml.getValueTag("C73", infoFinc.getC_7_3()));
						archivo.append(uXml.getValueTag("C74", infoFinc.getC_7_4()));
						archivo.append(uXml.getValueTag("C75", infoFinc.getC_7_5()));
						archivo.append(uXml.getValueTag("TOTAL_C7",infoFinc.getC_7()));
					archivo.append(uXml.getCloseTag("C7"));
				
					archivo.append(uXml.getOpenTag("C8"));
						archivo.append(uXml.getValueTag("C81", infoFinc.getC_8_1()));
						archivo.append(uXml.getValueTag("C82", infoFinc.getC_8_2()));
						archivo.append(uXml.getValueTag("C83", infoFinc.getC_8_3()));
						archivo.append(uXml.getValueTag("C84", infoFinc.getC_8_4()));
						archivo.append(uXml.getValueTag("C85", infoFinc.getC_8_5()));
						archivo.append(uXml.getValueTag("TOTAL_C8", infoFinc.getC_8()));
					archivo.append(uXml.getCloseTag("C8"));
			
					archivo.append(uXml.getOpenTag("C9"));
						archivo.append(uXml.getValueTag("C91", infoFinc.getC_9_1()));
						archivo.append(uXml.getValueTag("C92", infoFinc.getC_9_2()));
						archivo.append(uXml.getValueTag("C93", infoFinc.getC_9_3()));
						archivo.append(uXml.getValueTag("C94", infoFinc.getC_9_4()));
						archivo.append(uXml.getValueTag("C95", infoFinc.getC_9_5()));
						archivo.append(uXml.getValueTag("TOTAL_C9", infoFinc.getC_9()));
					archivo.append(uXml.getCloseTag("C9"));
		
					archivo.append(uXml.getValueTag("TOTAL_C",infoFinc.getC()));
				archivo.append(uXml.getCloseTag("ZONA_C"));
				
				archivo.append(uXml.getOpenTag("ZONA_D DESCRIPCION=\"SUB TOTAL GASTO EN COTIZACIONES\""));
					archivo.append(uXml.getValueTag("TOTAL_D", infoFinc.getD()));
				archivo.append(uXml.getCloseTag("ZONA_D"));
				
				archivo.append(uXml.getOpenTag("ZONA_E DESCRIPCION=\"COTIZACIONES A FONDO DE PENSIONES\""));
					archivo.append(uXml.getValueTag("E1", infoFinc.getE_1()));
					archivo.append(uXml.getValueTag("E2", infoFinc.getE_2()));
					archivo.append(uXml.getValueTag("E3", infoFinc.getE_3()));
					archivo.append(uXml.getValueTag("E4", infoFinc.getE_4()));
					archivo.append(uXml.getValueTag("E5", infoFinc.getE_5()));
					archivo.append(uXml.getValueTag("TOTAL_E", infoFinc.getE()));
				archivo.append(uXml.getCloseTag("ZONA_E"));
				
				archivo.append(uXml.getOpenTag("ZONA_F DESCRIPCION=\"COTIZACIONES DE SALUD\""));
					archivo.append(uXml.getValueTag("F1", infoFinc.getF_1()));
					archivo.append(uXml.getValueTag("F2", infoFinc.getF_2()));
					archivo.append(uXml.getValueTag("F3", infoFinc.getF_3()));
					archivo.append(uXml.getValueTag("F4", infoFinc.getF_4()));
					archivo.append(uXml.getValueTag("F5", infoFinc.getF_5()));
					archivo.append(uXml.getValueTag("TOTAL_F", infoFinc.getF()));
				archivo.append(uXml.getCloseTag("ZONA_F"));
				
				archivo.append(uXml.getOpenTag("ZONA_G DESCRIPCION=\"COTIZACIONES DESAHUCIO E INDEMNIZACION\""));
					archivo.append(uXml.getValueTag("G1", infoFinc.getG_1()));
					archivo.append(uXml.getValueTag("G2", infoFinc.getG_2()));
					archivo.append(uXml.getValueTag("G3", infoFinc.getG_3()));
					archivo.append(uXml.getValueTag("G4", infoFinc.getG_4()));
					archivo.append(uXml.getValueTag("TOTAL_G", infoFinc.getG()));
				archivo.append(uXml.getCloseTag("ZONA_G"));
				
				archivo.append(uXml.getOpenTag("ZONA_H DESCRIPCION=\"EXCEDENTE o DEFICIT\""));
					archivo.append(uXml.getValueTag("TOTAL_H",infoFinc.getH()));
				archivo.append(uXml.getCloseTag("ZONA_H"));
				
				archivo.append(uXml.getOpenTag("ZONA_Z"));
					archivo.append(uXml.getValueTag("OBSERVACIONES","-"));
				archivo.append(uXml.getCloseTag("ZONA_Z"));
			
			archivo.append(uXml.getCloseTag("INFORME"));
			
			
			String rutaTemporal=lp.getAtributoRepositorio("rutaLocalTemporales")+nameFileDestinoXML;
			EscritorArchivoSimple eas= new EscritorArchivoSimple();				
			eas.writerXml(archivo, rutaTemporal);
			System.out.println("[* * * * ]"+rutaTemporal+"[* * * * *]");
			key=eas.setCopyByIFS(carpetaDestino, nameFileDestinoXML,rutaTemporal);
			
			return key;
	}//end write xml IF
	
}//END class
