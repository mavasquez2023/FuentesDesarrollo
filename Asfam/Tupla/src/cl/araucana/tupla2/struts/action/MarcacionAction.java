package cl.araucana.tupla2.struts.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.tupla2.struts.bussiness.TO.ActualizaTuplaVO;
import cl.araucana.tupla2.struts.bussiness.TO.ActualizacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.MarcacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramosTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ConstruyeXml;
import cl.araucana.tupla2.struts.utils.XmlParse;
import cl.araucana.util.LoadPropertiesFile;
import cl.paperless.siagf.ws.ActualizarCausantePortTypeProxy;
import cl.paperless.siagf.ws.AutenticacionPortTypeProxy;
import cl.paperless.siagf.ws.ConsultaCausantePortTypeProxy;

public class MarcacionAction extends Action {
	Araucanajdbcdao dao = null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		MarcacionTO form1 = (MarcacionTO) form;
		if (request.getParameter("isForm") == null)
			return mapping.findForward("showForm");

		String mensaje = null;
		ConstruyeXml construye = new ConstruyeXml();
		//CamposXmlTO oCampo = new CamposXmlTO();
		List rechazos = new ArrayList();
		SqlParametersTO oSql = new SqlParametersTO();
		String xml = null;
		String respuesta = null;
		XmlParse parse = new XmlParse();
		RetornoTO oRetorno = new RetornoTO();
		String codtcau_Invalidos= ",5,8,11,20,27,";
		//Archivo de configuracion
		Properties properties = new LoadPropertiesFile().load("wssiagf.properties");
		
		try {
			dao = new Araucanajdbcdao();

			oSql.setEsquemaorigen(form1.getEsquemaorigen().trim());
			oSql.setEsquemadestino(form1.getEsquemadestino().trim());
			oSql.setTablaorigen(form1.getTablaorigen().trim());
			oSql.setTablamarcarechazo(form1.getTablamarcarechazo().trim());
			oSql.setTablatramo(form1.getTablatramo().trim());
			oSql.setTabladestino(form1.getTabladestino().trim());
			oSql.setActualizar(form1.getActualizar());
			oSql.setMaxid(form1.getMaxid());

		} catch (Exception e) {
			request.setAttribute("mensaje", "No es posible obtener lista de causantes: " + e.getMessage());
			forward = mapping.findForward("onError");
			return forward;
		}

		int succefull = 0;
		int totalcasos=0;
		String opciones="" ;
			try {
				if(oSql.getActualizar()!=null){
					for (int i = 0; i < oSql.getActualizar().length; i++) {
						opciones+= "," + oSql.getActualizar()[i];
					}
				}
				xml = null;

				rechazos= dao.consultaRechazoTuplas(oSql, opciones);
				for (Iterator iterator = rechazos.iterator(); iterator.hasNext();) {
					ActualizaTuplaVO rechazo = (ActualizaTuplaVO) iterator.next();
					if(rechazo.getIdTupla()==null){
						rechazo.setTupla(true);
						rechazo.setIdTupla("0");
					}else{
						if(!rechazo.getCodigoEntidad().trim().equals(rechazo.getCodigoEntidadRechazo().trim())){
							rechazo.setEntidad(true);
						}else{
							String codigoCausanteValido= rechazo.getCodigoCausanteRechazo();
							if( Integer.parseInt(rechazo.getEstadoTupla())== 3){
								codigoCausanteValido= rechazo.getCodigoCausante();
							}
							if(opciones.indexOf("P")>-1 && rechazo.getPeriodoTramo()== null){
								rechazo.setPeriodo(true);
							}else if(opciones.indexOf("T")>-1 && rechazo.getPeriodoTramo().trim().equals(rechazo.getPeriodoRechazo().trim()) && !rechazo.getTramoTupla().trim().equals(rechazo.getTramoRechazo().trim())){
								rechazo.setTramo(true);
							}else if(opciones.indexOf("M")>-1 && rechazo.getPeriodoTramo().trim().equals(rechazo.getPeriodoRechazo().trim()) && !rechazo.getMontoTramo().trim().equals(rechazo.getMontoTramoRechazo().trim()) && codtcau_Invalidos.indexOf("," + codigoCausanteValido + ",")==-1){
								rechazo.setMonto(true);
							}else if(opciones.indexOf("M")>-1 && rechazo.getPeriodoTramo().trim().equals(rechazo.getPeriodoRechazo().trim()) && Integer.parseInt(rechazo.getMontoTramo())*2 != Integer.parseInt(rechazo.getMontoTramoRechazo()) && codtcau_Invalidos.indexOf("," + codigoCausanteValido + ",")>-1){
								rechazo.setMonto(true);
							}
							if(opciones.indexOf("C")>-1 && !rechazo.getCodigoCausante().trim().equals(rechazo.getCodigoCausanteRechazo().trim())){
								rechazo.setCausante(true);
							}
							if(opciones.indexOf("B")>-1 && !rechazo.getRutBeneficiario().trim().equals(rechazo.getRutBeneficiarioRechazo().trim())){
								rechazo.setBeneficiario(true);
							}
							if(opciones.indexOf("E")>-1 && !rechazo.getRutEmpleador().trim().equals(rechazo.getRutEmpleadorRechazo().trim())){
								rechazo.setEmpleador(true);
							}
						}
					}
					boolean exito= dao.guardarCausaRechazo(rechazo, oSql);
					if(exito){
						succefull++;
					}
				}

			} catch (Exception ex) {
				
				errors.add("name", new ActionMessage("id"));
				mensaje = "Error al grabar datos de actualizacion";
				
				ex.printStackTrace();
			}

		request.setAttribute("mensaje", "Registros procesados: " + succefull + " de " + rechazos.size());
		forward = mapping.findForward("onSuccess");
		return forward;
	}
	
}
