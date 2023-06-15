<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>   
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE></TITLE>
</HEAD>
<BODY>
	 	
<%
//ESTE JSP crea la publicidad de credito preaprobado para los usuarios que se registren 
//y sean afiliados / pensionados que cuenten con un credito preaprobado (caso 1) o cuando
//un encargado de empresa intenta simular un credito para un AFILIADO (caso 2).
cl.araucana.autoconsulta.vo.UsuarioVO usuario = (cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario");
cl.araucana.autoconsulta.vo.AfiliadoVO afiliado = null;
if(session.getAttribute("pubSimulacionAfiliado") != null)
	afiliado = (cl.araucana.autoconsulta.vo.AfiliadoVO) session.getAttribute("pubSimulacionAfiliado");
 
String appnameAux = (String)session.getAttribute("struts.application");
String fullappnameAux =  appnameAux + ((String)session.getAttribute("struts.subapplication")==null ? "" : "/"+(String)session.getAttribute("struts.subapplication"));

String urlPdf;

String path = session.getAttribute("pathPublicidad") != null ? (String) session.getAttribute("pathPublicidad") : "";
String path2 = session.getAttribute("pathPubEditada") != null ? (String) session.getAttribute("pathPubEditada") : "";
String  pathPDFAfiliado = session.getAttribute("pathPDFAfiliado") != null ? (String) session.getAttribute("pathPDFAfiliado") : "";
String  pathPDFPensionado = session.getAttribute("pathPDFPensionado") != null ? (String) session.getAttribute("pathPDFPensionado") : "";

Long monto = (Long) session.getAttribute("montoPreAprobado");
session.removeAttribute("montoPreAprobado");

String pathFisico = "";

if(usuario.isEsAfiliadoActivo() || (afiliado != null && afiliado.getRut() != 0)){
	urlPdf = pathPDFAfiliado;
	if(afiliado == null){ //si estamos en el caso comun (caso 1)	
		pathFisico = cl.araucana.autoconsulta.common.ImageProcessing.addTextToImg(path,path2,"Credito_Afiliados.jpg",usuario.getNombre(),monto.toString(),session.getId());	 				
	
	}
	else //(caso 2 EMPRESA) SOLO AFILIADOS
		pathFisico = cl.araucana.autoconsulta.common.ImageProcessing.addTextToImg(path,path2,"Credito_Afiliados.jpg",afiliado.getNombre()+" "+afiliado.getApellidoPaterno()+" "+afiliado.getApellidoMaterno(),monto.toString(),session.getId());	 
%>
<map name = "mapa">
	<!--Se saca el area del PDF de afiliados acorde a nueva campanya - ALFONSO LEIVA 08-02-2010-->
	<!--Se agrega el area de solicitud de credito - ALFONSO LEIVA 08-02-2010-->	
	<area shape="rect" href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/formulariosweb/formulariosolicitudcredito?SRV=Page" coords="564,361,774,397" target="_blank"/>
	<!--area shape="rect" href="<%=request.getContextPath()+"/web/PDF/SolicitudesAfiliados.pdf"%>" coords="433,446 677,464" target="_blank"/-->
	<!--<area shape="rect" href="<%//="file:///"+urlPdf%>" coords="433,446 677,464" target="_blank"/>-->
</map>
<img usemap="#mapa" src="<%=request.getContextPath()+"/web/images/publicidad/"+session.getId()+".jpg"%>" border=0 />
<!--<img usemap="#mapa" src="<%//="file:///"/web/images/publicidad/"%>" border=0 />-->
<%
}else{
	 pathFisico = cl.araucana.autoconsulta.common.ImageProcessing.addTextToImg(path,path2,"Credito_Pensionados.jpg",usuario.getNombre(),monto.toString(),session.getId());	 
	 urlPdf = pathPDFPensionado;
%>
<map name = "mapa">
	<!--Se saca el area del PDF de afiliados acorde a nueva campanya - ALFONSO LEIVA 08-02-2010-->
	<!--Se agregan las areas de condiciones y simulacion de credito - ALFONSO LEIVA 08-02-2010-->	
	<area shape="rect" href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/formulariosweb/formulariosolicitudcredito?SRV=Page" coords="564,361,774,397" target="_blank"/>
	<!--area shape="rect" href="<%=request.getContextPath()+"/web/PDF/SolicitudesPensionados.pdf"%>" coords="433,446 677,464" target="_blank"/-->
	<!--<area shape="rect" href="<%//="file:///"+urlPdf%>" coords="433,446 677,464"  target="_blank"/>-->
</map>
<!--<img usemap="#mapa" src="<%//="file:///"+pathFisico%>" border=0 />-->
<img usemap="#mapa" src="<%=request.getContextPath()+"/web/images/publicidad/"+session.getId()+".jpg"%>" border=0 />
<%}

//TRUCO PARA BORRAR LA IMAGEN, Frame oculto
%>
<IFRAME src="/AutoconsultaWeb/web/pages/eliminaPubCredito.jsp" name="iframe" height="0" width="0"></IFRAME>
</BODY>
</HTML>
