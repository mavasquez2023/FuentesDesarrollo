<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
<script>
function verCodigos(ancho, alto) {
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, "
	+ "width=" + ancho + ", height=" + alto + ", "
	+ "top=" + (screen.availHeight - alto)/2  +", left=" + (screen.availWidth - ancho)/2;
	window.open("codigos.html", "CODIGOS", opciones);
}
</script>
</HEAD>
<BODY>
<H1>Result</H1>
<TABLE width="100%">
<TR>
	<TD ALIGN="right" ><a href="#" onclick="verCodigos(800, 600);">Ver códigos respuesta</a></TD>
</TR>
</TABLE>
<jsp:useBean id="sampleid" scope="session" class="cl.laaraucana.integracion.impl.IntegracionDTProxy" />

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
        java.lang.String endpoint_0idTemp  = endpoint_0id;
        sampleid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        cl.laaraucana.integracion.impl.IntegracionDT getIntegracionDT10mtemp = sampleid.getIntegracionDT();
if(getIntegracionDT10mtemp == null){
%>
<%=getIntegracionDT10mtemp %>
<%
}else{
        if(getIntegracionDT10mtemp!= null){
        String tempreturnp11 = getIntegracionDT10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String entrada_1id=  request.getParameter("entrada16");
        java.lang.String entrada_1idTemp  = entrada_1id;
        java.lang.String integracionDT13mtemp = sampleid.integracionDT(entrada_1idTemp);
if(integracionDT13mtemp == null){
%>
<%=integracionDT13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(integracionDT13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
exception: <%= e %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>