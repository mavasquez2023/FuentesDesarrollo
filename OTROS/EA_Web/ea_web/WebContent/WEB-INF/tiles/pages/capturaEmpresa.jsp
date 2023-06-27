<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<script language="jscript" type="text/javascript">

function ingresar(){

	if (document.forms[0].rut.value==""){
		alert("Digite el rut de la Empresa...");
		document.forms[0].rut.focus();
		return false;
	}else{
		return true;
	}
}

var nav4 = window.Event ? true : false;
function acceptNum(evt){	
	// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57	
	var key = nav4 ? evt.which : evt.keyCode;
	if (key != 13){
		return (key < 13 || (key >= 48 && key <= 57));
	}
}

</script>

<table width="100%">
	<tbody>
		<tr>
			<td style="width : 5%">
				<html:img alt="" height="1" width="4" page="/img/c.gif" />
			</td>

			<td style="width : 90%;">
				<html:form action="/capturaEmpresa" method="post" scope="request" onsubmit="return ingresar()">
				<table border="0" cellspacing="1" cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td colspan="2">
								&nbsp;
							<td>
						</tr>
						<tr>
							<td style="font-family: verdana; font-size: medium; font-weight: bold;" colspan="2" align="center" width="100%">
								Consulta por Empresa
							<td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							<td>
						</tr>
						<logic:present name="errorEmpresa" scope="session">
							<tr>
								<td style="font-weight: bold; color: #ff0000; font-family: Verdana, Arial, sans-serif; font-size: 12px;"><bean:write name="errorEmpresa" scope="session"/></td>
							</tr>
							<tr>
							<td colspan="2">
								&nbsp;
							<td>
						</tr>
						</logic:present>
						<tr>
							<td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; background-color: #F7F7F7; background-image: none; vertical-align: top;">&nbsp;Rut de la Empresa:&nbsp;
								<html:text property="rut" size="10" maxlength="8" value="" onkeypress="return acceptNum(event)"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;Rut sin d&iacute;gito verificador&nbsp;)</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							<td>
						</tr>
						<tr>
							<td valign=top colspan="2" nowrap align="center" style="padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size:10px; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;">
								<html:submit value="Aceptar"></html:submit>
							<td>
						</tr>
					</tbody>
				</table>
				<html:hidden property="status" value="" />
				</html:form>
			</td>
			<td style="width : 5%">
				<img alt="" height="1" width="4" src="./img/c.gif"/>
			</td>
		</tr>
	</tbody>
</table>
