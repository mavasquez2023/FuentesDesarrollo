<%@ include file = "/modulo/includes/env.jsp"%>
<% title = "Publicidad"; %>
<%@ include file = "/modulo/includes/header.jsp"%>
<%@ include file = "/modulo/includes/top.jsp"%>
 
<%
cl.araucana.autoconsulta.vo.UsuarioVO usuario = (cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario");

String appnameAux = (String)session.getAttribute("struts.application");
String fullappnameAux =  appnameAux + ((String)session.getAttribute("struts.subapplication")==null ? "" : "/"+(String)session.getAttribute("struts.subapplication"));

String urlPdf;

String path = session.getAttribute("pathPublicidad") != null ? (String) session.getAttribute("pathPublicidad") : "";
String path2 = session.getAttribute("pathPubEditada") != null ? (String) session.getAttribute("pathPubEditada") : "";
String  pathPDFAfiliado = session.getAttribute("pathPDFAfiliado") != null ? (String) session.getAttribute("pathPDFAfiliado") : "";
String  pathPDFPensionado = session.getAttribute("pathPDFPensionado") != null ? (String) session.getAttribute("pathPDFPensionado") : "";

Long monto = (Long) session.getAttribute("montoPreAprobado");
%>
<table width="100%" border="0">
<tr>
<td  align="center">
<html:link page="/Menu.do">
	Continuar
</html:link>
</td>
</tr>
<tr>
<td>
	<%
	String pathFisico = "";
	if(usuario.isEsAfiliadoActivo()){
		 urlPdf = pathPDFAfiliado;
		 
	pathFisico = cl.araucana.autoconsulta.common.ImageProcessing.addTextToImg(path,path2,"Credito_Afiliadosmodulo.jpg",usuario.getNombre(),monto.toString(),session.getId());	 
	%>
	<!--Se agrega area de simulacion de credito - ALFONSO LEIVA 08-02-2010-->		
	<map name = "mapa">				
		<area shape="rect" href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/formulariosweb/formulariosolicitudcredito?SRV=Page" coords="564,361,774,397" target="_blank"/>
	</map>

	<img usemap="#mapa" src="<%=request.getContextPath()+"/web/images/publicidad/"+session.getId()+".jpg"%>" border=0 />
	<!--<img src="<%//="file:///"+pathFisico%>" border=0 />-->
	<%
	}else{
		 pathFisico = cl.araucana.autoconsulta.common.ImageProcessing.addTextToImg(path,path2,"Credito_Pensionados_modulo.jpg",usuario.getNombre(),monto.toString(),session.getId());	 
		 
		 urlPdf = pathPDFPensionado;
	%>
	<!--Se agrega area de simulacion de credito - ALFONSO LEIVA 08-02-2010-->		
	<map name = "mapa">					
			<area shape="rect" href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/formulariosweb/formulariosolicitudcredito?SRV=Page" coords="564,361,774,397" target="_blank"/>
	</map>
	<img usemap="#mapa" src="<%=request.getContextPath()+"/web/images/publicidad/"+session.getId()+".jpg"%>" border=0 />
	<!--<img src="<%="file:///"+pathFisico%>" border=0 />-->
	<%}
	%>
</td>
</tr>
<tr>
<td  align="center">
	<html:link page="/Menu.do">
		Continuar
	</html:link>
</td>
</tr>
</table>
<IFRAME src="/AutoconsultaWeb/modulo/pages/eliminaPubCredito.jsp" name="iframe" height="0" width="0"></IFRAME>
<%@ include file = "/modulo/includes/footer.jsp"%>
