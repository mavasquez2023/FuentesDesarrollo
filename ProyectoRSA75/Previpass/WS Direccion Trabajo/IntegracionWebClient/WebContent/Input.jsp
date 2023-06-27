<%@page contentType="text/html;charset=UTF-8"%>
<HTML>
<HEAD>
<TITLE>Inputs</TITLE>
<script>
var pet_AUT='<peticion llave=""><peticionservicio tipo="AUT"><parametro nombre="usuario" valor="#usu" /><parametro nombre="password" valor="#pass" /></peticionservicio>';
var pet_SUB='<peticionservicio tipo="SUBCONTR"><parametro nombre="rut_empleador" valor="#rutemp" /><parametro nombre="rut_trabajador" valor="#ruttra" /><parametro nombre="periodo" valor="#per" /></peticionservicio>';
var pet_fin='</peticion>';
var usuario_testing="70016160-9";
var password_testing="12345";
var usuario_produccion="UWSDT";
var password_produccion="S2RV3C34";
function VerXML() {
	var xml="";
	var peticion= "";		
	var repetir= Number(document.forms[0].repetir.value);
			if(document.forms[0].ambiente[0].checked){
				xml= pet_AUT.replace("#usu", usuario_testing);
				xml= xml.replace("#pass", password_testing);
				
			}else{
				xml= pet_AUT.replace("#usu", usuario_produccion);
				xml= xml.replace("#pass", password_produccion);
			}
			for (j=0; j<repetir; j++) {
				for (i=0; i<3; i++) {
					if(document.forms[0].rutempleador[i].value!="" && document.forms[0].ruttrabajador[i].value!="" && document.forms[0].periodo[i].value!=""){
						peticion= pet_SUB.replace("#rutemp", document.forms[0].rutempleador[i].value);
						peticion= peticion.replace("#ruttra", document.forms[0].ruttrabajador[i].value);
						peticion= peticion.replace("#per", document.forms[0].periodo[i].value);
						xml+= peticion;
					}
				}
			}
			xml+= pet_fin; 
			document.forms[0].entrada16.value= xml;
}
function verCodigos(ancho, alto) {
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, "
	+ "width=" + ancho + ", height=" + alto + ", "
	+ "top=" + (screen.availHeight - alto)/2  +", left=" + (screen.availWidth - ancho)/2;
	window.open("codigos.html", "CODIGOS", opciones);
}
</script>
</HEAD>
<BODY>
<H1>Inputs</H1>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

boolean valid = true;

if(methodID != -1) methodID = Integer.parseInt(method);
switch (methodID){ 
case 2:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 5:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">endpoint:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="endpoint8" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 10:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 13:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>

<TR>
	<TD COLSPAN="1" ALIGN="LEFT">REGISTRO 1 --&#62;&nbsp; Rut Empleador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="rutempleador" SIZE=14 value='69073600-4'></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Rut trabajador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ruttrabajador" SIZE=14 value='6737398-7'></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Periodo:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="periodo" SIZE=10 value='201411'></TD>
</TR>
<TR>
	<TD COLSPAN="1" ALIGN="LEFT">REGISTRO 2 --&#62;&nbsp; Rut Empleador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="rutempleador" SIZE=14 value=''></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Rut trabajador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ruttrabajador" SIZE=14 value=''></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Periodo:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="periodo" SIZE=10 value=''></TD>
</TR>
<TR>
	<TD COLSPAN="1" ALIGN="LEFT">REGISTRO 3 --&#62;&nbsp; Rut Empleador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="rutempleador" SIZE=14 value=''></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Rut trabajador:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ruttrabajador" SIZE=14 value=''></TD>
	<TD COLSPAN="1" ALIGN="LEFT">Periodo:</TD>
	<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="periodo" SIZE=10 value=''></TD>
</TR>
<TR>
	<TD>&nbsp;</TD>
</TR>
<TR>
	<TD COLSPAN="2" ALIGN="LEFT">Repetir registros &nbsp;<INPUT TYPE="text" NAME="repetir" VALUE="1" size="4" maxlength="4" /> &nbsp;veces.</TD>
</TR>
<TR>
	<TD COLSPAN="2" ALIGN="LEFT">Seleccione:<INPUT TYPE="radio" name="ambiente" "VALUE="T" checked="checked" />Testing &nbsp;<INPUT TYPE="radio" name="ambiente" VALUE="P"/>Producción</TD>
</TR>
<TR>
	<TD><INPUT TYPE="button" VALUE="Armar XML" onclick="VerXML();"></TD>
</TR>
<TR>
	<TD>&nbsp;</TD>
</TR>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">XML entrada:</TD>
<TD COLSPAN="5" ALIGN="left"><INPUT TYPE="TEXT" NAME="entrada16" SIZE=100 value=''></TD>
</TR>
</TABLE>
<BR>

<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 1111111111:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">URLString:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="url1111111111" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 1111111112:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
}
if (valid) {
%>
Select a method to test.
<%
    return;
}
%>

</BODY>
</HTML>
