

<%@ page import="java.util.List" %>
 

<html>
<head>


<title>test</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
<script type="text/javascript">
function generarDocumentoPDF(valor){
document.formpdf.action="GenerarDocumentoPDF";
document.formpdf.valori.value=valor;
document.formpdf._folder.value="Certificado Cotizaciones";
document.formpdf._accion.value="Descargar";
document.formpdf.FechaProceso.value="${periodoant}";  
document.formpdf.submit();
 }
</script>
</head>
<body>
<%
HttpSession sesion=request.getSession();
List param= (List)sesion.getAttribute("parametros");
String cantidad= (String)sesion.getAttribute("cantidad");
 
if(param.size()>2199)
out.print("<table width='700px'><tr><td align='center'><span style='font-size: 16px;'>CERTIFICADO DE COTIZACIONES</span></td></tr>"
+ "<tr><td height='10px'></td></tr>"
 + "<tr><td align='center'><span style='font-size: 14px;'>Solo se han mostrado los primeros 200 resultados</span></td></tr></table>");
else
out.print("<table width='700px'><tr><td align='center'><span style='font-size: 16px;'>CERTIFICADO DE COTIZACIONES</span></td></tr>"
+ "<tr><td height='10px'></td></tr>"
 + "</table>");

%>

<form name="formpdf"  id="formpdf" method="post">
<input type="hidden" name="valori" >
<input type="hidden" name="_folder" >
<input type="hidden" name="_accion" >
<input type="hidden" value="" name="FechaProceso">
<table  style="border: 1px solid darkblue;border-width: 1px 1px 0 0" cellpadding="0" cellspacing="0" width="700px">
<tbody><tr bgcolor="darkblue" style="color: white;"><td width="60px"><span style="font-size: 12;" ><b>Tipo Proceso</b></span></td><td><span style="font-size: 12;" ><b>Rut Empresa</b></span></td><td><span style="font-size: 12;" ><b>Nombre Afiliado</b></span></td><td><span style="font-size: 12;" ><b>Rut Afiliado</b></span></td><td width="60px"><span style="font-size: 12;" ><b>Convenio</b></span></td><td><span style="font-size: 12;" ><b>Sucursal</b></span></td><td align="center"><span style="font-size: 12;" ><b>PDF</b></span></td></tr></tbody>
<% if("1" == param.get(13)) {%>
<% 
for(int i=0;i<param.size();i+=14) {
if((i % 28)==0){
%> 
<tbody>
<tr bgcolor="#ffffff" onMouseOver="this.style.backgroundColor = 'darkblue'; this.style.color='white'" onMouseOut="this.style.backgroundColor = '#ffffff';this.style.color='black'"></tr></tbody>
<%} else {%><tbody><tr bgcolor="#ffffff" onMouseOver="this.style.backgroundColor = 'darkblue';this.style.color='white'" onMouseOut="this.style.backgroundColor = '#ffffff';this.style.color='black'">
<%}%>
<td align="center" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;" ><%=param.get(i+10).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i).toString() + " - " + param.get(i+6).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+1).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+2).toString()+ " - " + param.get(i+5).toString() %></span></td>
<td align="center" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12"><%=param.get(i+7).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+3).toString() %></span></td>
<td width="80px" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><input type="button" value="Descargar" onclick="generarDocumentoPDF(<%=i%>);" style="width: 70px;height: 20px;font-size: 12"> </td>
<%} %>
</tr>
</tbody>
<%} else { %>
<%
for(int i=0;i<param.size();i+=15) {
if((i % 30)==0){
%> 
<tbody>
<tr bgcolor="#ffffff" onMouseOver="this.style.backgroundColor = 'darkblue'; this.style.color='white'" onMouseOut="this.style.backgroundColor = '#ffffff';this.style.color='black'"></tr></tbody>
<%} else {%><tbody><tr bgcolor="#ffffff" onMouseOver="this.style.backgroundColor = 'darkblue';this.style.color='white'" onMouseOut="this.style.backgroundColor = '#ffffff';this.style.color='black'">
<%}%>
<td align="center" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;" ><%=param.get(i+10).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i).toString() + " - " + param.get(i+6).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+1).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+2).toString()+ " - " + param.get(i+5).toString() %></span></td>
<td align="center" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12"><%=param.get(i+7).toString() %></span></td>
<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span style="font-size: 12;margin-left: 5px;"><%=param.get(i+3).toString() %></span></td>
<td width="80px" style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><input type="button" value="Descargar" onclick="generarDocumentoPDF(<%=i%>);" style="width: 70px;height: 20px;font-size: 12"> </td>
<%} %>
</tr>
</tbody>
<%} %>
</table>
</form>
</body>
</html>
