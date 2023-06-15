package cl.araucana.tupla2.struts.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.araucana.tupla2.exception.Tupla2Exception;
import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.DatosExtinsionTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramoTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;

public class ConstruyeXml {
	public static void main(String arg[]) {
		ConstruyeXml myObject = new ConstruyeXml();

		System.out.println(myObject.creaXmlActualizacion(new CamposXmlTO(true)));

	}

	/**
	 * Genera el XML que debe ser enviado al WS de SIAGF 
	 * @param oCampo Informacion de un causante
	 * @return String con el XML generado
	 * @throws Tupla2Exception 
	 */
	public String creaXmlActualizacion(CamposXmlTO oCampo) {
		List tramos = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha= sdf.format(new Date());
		String content = null;
		content = "<?xml version='1.0' encoding='ISO-8859-1' ?>" + "<ActualizacionCausante version='1.0'>" + "<FechaEmision>" + fecha + "</FechaEmision>";

		//TAG Tupla
		content += "<Tupla>" + "<RutCausante>" + oCampo.getRutCausante().trim() + "</RutCausante>" + "<CodTipoCausante>" + oCampo.getCodtipocausante().trim() + "</CodTipoCausante>" + "<CodEntidadAdm>" + oCampo.getCodEntidadAdm().trim() + "</CodEntidadAdm>" + "<FecRecCausante>"
				+ oCampo.getFecRecCausante().substring(0, 4) + "-" + oCampo.getFecRecCausante().substring(4, 6) + "-" + oCampo.getFecRecCausante().substring(6, 8) + "</FecRecCausante>" + "<RutBeneficiario>" + oCampo.getRutBeneficiario().trim() + "</RutBeneficiario>" + "<CodTipoBeneficio>" + oCampo.getIdTipoBeneficio().trim() + "</CodTipoBeneficio>" + "</Tupla>";

		//TAG Modificar
		content += "<Modificar>";

		//Tramos

			content += "<Tramos>";
		
				content = content + "<Tramo>" + 
						"<Periodo>" + oCampo.getPeriodo() + "</Periodo>" + 
						"<NumTramo>" + oCampo.getTramoAsigFam() + "</NumTramo>" + 
						"<IngPromedio>" + oCampo.getIngPromedio().trim() + "</IngPromedio>" + 
						"<MontoUnitarioBeneficio>" + oCampo.getValorTramo().trim() + "</MontoUnitarioBeneficio>" + 
						"</Tramo>";
			content = content + "</Tramos>";
	
		/*TODOS LOS CAMPOS PUEDEN SER NULOS....*/
/*	
		if (oCampo.getIdTipoBeneficio() != null && oCampo.getIdTipoBeneficio().trim().length() > 0)
			content += "<TipoBeneficiario>" + oCampo.getTipoBeneficiario().trim() + "</TipoBeneficiario>";

		if (oCampo.getNomBeneficiario() != null && oCampo.getNomBeneficiario().trim().length() > 0)
			content += "<NombreBeneficiario>" + oCampo.getNomBeneficiario().trim() + "</NombreBeneficiario>";
		//"<RegionBeneficiario>NO_DEFINIDO</RegionBeneficiario>"+
		//"<ComunaBeneficiario>NO_DEFINIDO</ComunaBeneficiario>"+
		//"<RegionCausante>NO_DEFINIDO</RegionCausante>"+
		//"<ComunaCausante>NO_DEFINIDO</ComunaCausante>"+

		if (oCampo.getRutEmpleador() != null && oCampo.getRutEmpleador().trim().length() > 0)
			content += "<RutEmpleador>" + oCampo.getRutEmpleador().trim() + "</RutEmpleador>";

		if (oCampo.getNomEmpleador() != null && oCampo.getNomEmpleador().trim().length() > 0)
			content += "<NomEmpleador>" + oCampo.getNomEmpleador().trim() + "</NomEmpleador>";

		if (oCampo.getActeco() != null && oCampo.getActeco().trim().length() > 0)
			content += "<Acteco>" + oCampo.getActeco().trim() + "</Acteco>";

		if (oCampo.getRegionEmpleador() != null && oCampo.getRegionEmpleador().trim().length() > 0)
			content += "<RegionEmpleador>" + oCampo.getRegionEmpleador().trim() + "</RegionEmpleador>";

		if (oCampo.getComunaEmpleador() != null && oCampo.getComunaEmpleador().trim().length() > 0)
			content += "<ComunaEmpleador>" + oCampo.getComunaEmpleador().trim() + "</ComunaEmpleador>";
*/
		content += "</Modificar>" + "</ActualizacionCausante>";

		//System.out.println(content);
		return content;
	}

	public String creaXml(CamposXmlTO oCampo) throws Exception {

		List tramos = new ArrayList();
		TramoTO oTramoActual = null;

		Araucanajdbcdao dao = new Araucanajdbcdao();
		System.out.println("INGRESO RECONICIMIENTO SIAGF");
		System.out.println(oCampo.getRutBeneficiario().split("-")[0]);
		System.out.println(oCampo.getFecRecCausante());
		System.out.println(oCampo.getFecExtCausante());

		//Logica para agregar los campos del tramo actual
		//oTramoActual = dao.getTramoActual(oCampo.getRutBeneficiario().split("-")[0], oCampo.getFecRecCausante(), oCampo.getFecExtCausante());
		//tramos = dao.getTramosRetroactivos(oCampo.getRutBeneficiario().split("-")[0], oCampo.getFecRecCausante(), oCampo.getFecExtCausante());

		//System.out.println(oTramoActual.getNumTramo());
		//System.out.println(tramos.size());

		StringBuffer content = new StringBuffer();

		content.append("<?xml version='1.0' encoding='ISO-8859-1' ?>");
		content.append("<CargaCausante version='1.0'>" + "<Documento>" + "<FechaEmision>" + oCampo.getFechaEmision().trim() + "</FechaEmision>");
		content.append("<Causante>" + "<TipoCausante>" + oCampo.getTipoCausante().trim() + "</TipoCausante>");
		content.append("<SexoCausante>" + oCampo.getSexoCausante().trim() + "</SexoCausante>");
		content.append("<RutCausante>" + oCampo.getRutCausante().trim() + "</RutCausante>");
		content.append("<NomCausante>" + oCampo.getNomCausante().trim() + "</NomCausante>");

		if (!oCampo.getfNacCausante().trim().equalsIgnoreCase("")){
			content.append("<FecNacCausante>" + oCampo.getfNacCausante().trim() + "</FecNacCausante>");
		}
					//+"<RegionCausante>" + oCampo.getRegionBeneficiario() + "</RegionCausante>" + "<ComunaCausante>" + oCampo.getComunaBeneficiario() + "</ComunaCausante>";
		
		content.append("</Causante>");
		content.append("<Beneficiario>" + "<TipoBeneficiario>" + oCampo.getTipoBeneficiario().trim() + "</TipoBeneficiario>");
		content.append("<RutBeneficiario>" + oCampo.getRutBeneficiario().trim() + "</RutBeneficiario>");
		content.append("<NomBeneficiario>" + oCampo.getNomBeneficiario().trim() + "</NomBeneficiario>");
		if(oCampo.getRegionBeneficiario()!=null){
			content.append("<RegionBeneficiario>" + oCampo.getRegionBeneficiario() + "</RegionBeneficiario>");
		}
		if(oCampo.getComunaBeneficiario()!=null){
			content.append("<ComunaBeneficiario>" + oCampo.getComunaBeneficiario() + "</ComunaBeneficiario>");
		}
		content.append("<IngPromedio>" + oCampo.getIngPromedio().trim() + "</IngPromedio>");

		content.append("<Empleador>" + "<RutEmpleador>" + oCampo.getRutEmpleador().trim() + "</RutEmpleador>");
		content.append("<NomEmpleador>" + oCampo.getNomEmpleador().trim() + "</NomEmpleador>");
		if(oCampo.getActeco()!=null && !oCampo.getActeco().trim().equals("0")){
			content.append("<Acteco>" + oCampo.getActeco().trim() + "</Acteco>");
		}
		if(oCampo.getRegionEmpleador()!=null){
			content.append("<RegionEmpleador>" + oCampo.getRegionEmpleador().trim()	+ "</RegionEmpleador>");
		}
		if(oCampo.getComunaEmpleador()!=null){
			content.append("<ComunaEmpleador>" + oCampo.getComunaEmpleador().trim() + "</ComunaEmpleador>");
		}
		content.append("</Empleador>");
		content.append("</Beneficiario>");
		content.append("<EntidadAdm>" + "<CodEntidadAdm>" + oCampo.getCodEntidadAdm().trim() + "</CodEntidadAdm>");
		content.append("<Beneficio>" + "<IdTipoBeneficio>" + oCampo.getIdTipoBeneficio().trim() + "</IdTipoBeneficio>");
		content.append("<FecRecCausante>" + oCampo.getFecRecCausante().trim() + "</FecRecCausante>");
		content.append("<FecPagoBeneficio>" + oCampo.getFecPagoBeneficio().trim() + "</FecPagoBeneficio>");


		
		if(getPeriodoActual().equals(oCampo.getPeriodo())){
			content.append("<MontoUnitarioBeneficio>" + oCampo.getValorTramo().trim() + "</MontoUnitarioBeneficio>");
			content.append("<TramoAsigFam>" + oCampo.getTramoAsigFam().trim() + "</TramoAsigFam>");
			//content.append("<IngPromedio>" + oCampo.getIngPromedio().trim() + "</IngPromedio>"); 
		}
		
		if (!oCampo.getFecExtCausante().trim().equalsIgnoreCase("") && !oCampo.getFecExtCausante().trim().equals("3001-01-01")) {
			content.append("<FecExtCausante>"+oCampo.getFecExtCausante().trim()+"</FecExtCausante>");		
			content.append("<CausaExtCausante>11</CausaExtCausante>");
		}
		if(!getPeriodoActual().equals(oCampo.getPeriodo())){

			content.append("<Tramos>");
			content.append("<Tramo>"); 
			content.append("<Periodo>" + oCampo.getPeriodo() + "</Periodo>"); 
			content.append("<NumTramo>" + oCampo.getTramoAsigFam() + "</NumTramo>"); 
			content.append("<IngPromedio>" + oCampo.getIngPromedio().trim() + "</IngPromedio>"); 
			content.append("<MontoUnitarioBeneficio>" + oCampo.getValorTramo().trim() + "</MontoUnitarioBeneficio>"); 
			content.append("</Tramo>");
			content.append("</Tramos>");
		}

		content.append("</Beneficio>");
		content.append("</EntidadAdm>");
		content.append("</Documento>");
		content.append("</CargaCausante>");

		return content.toString();
	}

	public String creaXmlExtinsion(DatosExtinsionTO fromDB2, TuplaTO fromWS) {
		String content = null;

		content = "<?xml version='1.0' encoding='ISO-8859-1' ?>" + " <ExtincionReconocimiento>" + " <Documento version='1.0'>" + " <FechaEmision>" + fromDB2.getFechaEmision() + "</FechaEmision>" + " <FechaExtCausante>" + fromDB2.getFechaExtincion() + "</FechaExtCausante>" + " <CausaExtCausante>"
				+ fromDB2.getCodigoExtincion() + "</CausaExtCausante> " + "<Tupla>" + " <RutCausante>" + fromWS.getRutCausante() + "</RutCausante>" + " <TipoCausante>" + fromWS.getCodTipoCausante() + "</TipoCausante>"
				+
				//" <TipoCausante>22</TipoCausante>" + 
				" <CodEntidadAdm>" + fromWS.getCodEntidadAdm() + "</CodEntidadAdm>" + " <FecRecCausante>" + fromWS.getFecRecCausante() + "</FecRecCausante> " + " <RutBeneficiario>" + fromWS.getRutBeneficiario() + "</RutBeneficiario> " + " <IdTipoBeneficio>" + fromWS.getCodTipoBeneficio()
				+ "</IdTipoBeneficio> " +
				//" <IdTipoBeneficio>2</IdTipoBeneficio> " +
				" </Tupla>" + " </Documento> " + " </ExtincionReconocimiento>";

		return content;
	}
	
	private String getPeriodoActual(){
		Calendar today = Calendar.getInstance();
		int year= today.get(Calendar.YEAR);
		int month= today.get(Calendar.MONTH);
		String mes= month<6?"01":"02";
		return year + mes;
	}
}
