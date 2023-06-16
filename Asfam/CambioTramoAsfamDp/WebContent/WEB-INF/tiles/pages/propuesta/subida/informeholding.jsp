<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.*;" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
 
 
<%List lista=(List)request.getSession().getAttribute("error"); 
 
  
 
 %>
 <table>
	<td valign="top">
	<jsp:include   page="/WEB-INF/tiles/common/menudivision.jsp"></jsp:include>
	</td>
	<td width="730px">
	<div align="center">
		<b>
			<i>
				<font color="#808080">
					Proceso Actualizaci&#243;n de Tramos
					<br><bean:write name="proceso" property="proceso"/>
				</font>
			</i>
		</b>
		<br>
	</div>
	<br>
	<br>
<body>
 
 

<logic:empty name="lista">
<table width="100%" align="center" border="0">
<tr style="background-color: #D7D7D7">
<td align="center">Archivo <bean:write name="case" property="nombre">
</bean:write> cargado exitosamente</td></tr>
</table>
</logic:empty>
<logic:notEmpty name="lista"  >
<table align="center">
<tr><td height="10px"></td></tr>
<tr><td><span class="blueText"><b>Errores Reportados</b></span></td></tr>
</table>
</logic:notEmpty>
 
 
 

<logic:notEmpty name="lista">
<table border="0" align="center" width="100%">
<tr><td colspan="3" align="left"><span class="blueText"><b>Se han reportado los siguientes errores en el archivo <bean:write name="case" property="nombre" />.</b></span></td></tr>
<tr style="background-color: #C0C0C0"><td>Fila</td><td>Columna(s)</td><td>Rut Trabajador</td><td>Descripci&oacute;n</td></tr>
<logic:iterate id="id" name="lista">
<tr style="background-color: #D7D7D7">
<td align="center"><bean:write name="id" property="numerolinea"></bean:write></td>
<td><bean:write name="id" property="numeroColumna"></bean:write></td>
<td><bean:write name="id" property="ruttrabajador"></bean:write></td>
<td><bean:write name="id" property="descripcionerror"></bean:write></td>
</tr>
 
</logic:iterate>


 
</table>

</logic:notEmpty>
 
 
<table>
<tr><td height="50px;"></td></tr>
</table>
 

</body>
</td>
</table>
 
