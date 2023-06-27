<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<SCRIPT language="Javascript">
function noImplementado(){
	//alert("Opción no habilitada");
}
function cerrarSesion(){
	//redirecciona a login.jsp, el que cierra la sesion

	document.forms.logoForm.action = "/BonificacionesWeb/login.jsp";
	document.forms.logoForm.submit();
	
} 

</SCRIPT>

	<table width="756" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="780">
	    	<form name="logoForm">
	    	<html:img page="/images/logoArriba1.gif" width="756" height="41" border="0" usemap="#Map2"/></td>
			</form>
	  </tr>
	</table>
	<table width="756" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="29"><html:img page="/images/logoArriba2.gif" width="200" height="29"/></td>
	    <td bgcolor="#FFFFFF"><html:img page="/images/logoArriba3.gif" width="556" height="29" border="0" usemap="#Map"/></td>
	  </tr>
	</table> 
<map name="Map">
<area shape="rect" coords="232,2,298,17" href="javascript:noImplementado();" >
<area shape="rect" coords="325,1,368,17" href="javascript:noImplementado();">
<area shape="rect" coords="402,0,442,17" href="javascript:noImplementado();">
<area shape="rect" coords="484,1,534,17" href="javascript:noImplementado();">
</map>
<map name="Map2">
<area shape="rect" coords="688,4,708,25" href="javascript:noImplementado();">
<area shape="rect" coords="712,5,732,25" href="javascript:noImplementado();">
<area shape="rect" coords="736,5,754,25" href="javascript:cerrarSesion();">
</map>
