<script language="JavaScript"> 
function changeClave(){
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes"; 
	ventana=window.open('http://araucanatest/personas/evalatc2.nsf/ccontra?OpenForm',"",prop);
}
</script>  
<table width="756" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="780">
    <html:img page="/images/logoArriba1.gif" width="756" height="41" border="0" usemap="#Map2"/></td>
  </tr>
</table>
<table width="756" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="29"><html:img page="/images/logoArriba2.gif" width="200" height="29"/></td>
    <td bgcolor="#FFFFFF"><html:img page="/images/logoArriba3.gif" width="556" height="29" border="0"/></td>
  </tr>
</table> 
<map>
	<map name="Map2">
	<area shape="rect" coords="736,5,754,25" href="/AutoconsultaPublicWeb/ibm_security_logout?logout=Logout&logoutExitPage=/login.jsp">
</map>

	<tr valign="top" align="center">
		<td class="textobold" colspan="2" >
				<font size='3'><B>
						<bean:message key="mensaje.publicitario"/><BR>
						</B>
						<BR>
				</font>
		</td>  
	</tr> 
	<BR>
	<TABLE border="1"  width="756" align="center" cellpadding="0" cellspacing="0">
	<logic:notEmpty name="publicidad" > 
		<logic:iterate id="publicidad" name="publicidad" >
			<tr>			
				<td class="texto" align="left" nowrap="nowrap"><font size="2"><B> <bean:message key="texto.publicitario"/></B></font></td>
			</tr>
			<tr>							
				<td class="textobold" align="left" wrap><font size="2"><bean:write name="publicidad" property="mensaje" /></font><BR></td>			
			</tr>		
	</logic:iterate>
		</logic:notEmpty>
	</TABLE>
	<BR>
	<font class="certificado">
	<html:form action='/ManagePublicAction'>
	<html:hidden property="accion" value="put"/>
	<table border="1" width="756" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="texto" align="left">
        		<bean:message key="texto.publicitario.nuevo"/>
	        </td>
    	</tr>
    	<tr>
        	<td class="textobold">
        		<html:text property="texto"  size="100" maxlength="255" styleClass="field" />
			</td>
		</tr>
		<tr>
			<td class="texto" colspan="2" align="center">
			<html:image page='/images/aceptar.gif'/>
			</td>
		</tr>
	</table>
	<table width="756" border="0" align="center" cellpadding="0" cellspacing="0">
	 
	 <tr>
	<td  class="texto"><html:link href='javascript:changeClave();'><bean:message key="texto.cambio.clave"/></html:link>
	  </td>
	</tr>
	</table>

	</html:form>
</font>

