<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<head>
<meta name="APPLICATION_ORGANIZATION_NAME" content="La Araucana C.C.A.F." />
<meta name="APPLICATION_NAME" content="PDFServiceWeb" />
<meta name="APPLICATION_TITLE" content="Producci&oacute;n y Consumo de Documentos PDF" />
<meta name="APPLICATION_VERSION" content="1.0" />
<meta name="APPLICATION_RELEASE_DATE" content="05/11/2008" />
<meta name="APPLICATION_COPYRIGHT" content="(c) Copyright La Araucana C.C.A.F. 2000-2008. Reservados todos los derechos." />

<title>La Araucana C.C.A.F. - Acceso al sistema</title>
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />

</head>

<body onload="setFocus()">
<script>
function setFocus(){

	document.forms[0].j_username.focus();
}

function validateFields(){
	var msg1 = "Estimado Usuario:<br/>";
	var msg2 = "";
	
	if(document.forms[0].j_username.value.length < 1 ){
		msg2 = "Favor ingrese un usuario v&aacute;lido";
		msg.className = 'selected';
		msg.innerHTML = msg1 + msg2;
		document.forms[0].j_username.focus();
		return false;
	}  
	if(document.forms[0].j_password.value.length < 1){
		msg2 = "Favor ingrese una contraseña v&aacute;lida";
		msg.className = 'selected';
		msg.innerHTML = msg1 + msg2;
		document.forms[0].j_password.focus();
		return false;
	}
	
	return true; 
	

}


</script>
	<center>

	<%@ include file="header.jspf" %>
	<form name="identificacionUsuarioForm" action="j_security_check" method="post" onsubmit="javascript:return validateFields();">
		<table width="767" border="0" cellpadding="0" cellspacing="0">

			<tr>
				<td>
					<table width="300" border="0" cellpadding="0" cellspacing="0" align="center" >
						<tr>
						  <td>&nbsp;</td>
						</tr>
						<tr>
							<td rowspan="32" align="center">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						 			<tr>
						    			<td class="barra_tablas">Acceso al Sistema</td>
						  			</tr>
								</table>
								<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="barratablas" align="center">Usuario</td>
													<td class="textos-formularios1">
														<input type="text" name="j_username" size="20" maxlength="20"/>
													</td>
												</tr>
												<tr>
													<td class="barratablas">Contraseña</td>
													<td class="textos-formularios1" >
														<input type="password" name="j_password"  size="20" maxlength="20"/>
													</td>
												</tr>
												
												<tr>
													
													<td colspan="2">
													
														<input name="ok" type="submit" class="btn2" value="Ingresar"  />
													
													</td>
												</tr>
											</table>
										</td>
									</tr>
									
									<tr>
									    <td>
									    	<div id="msg"></div>
									    </td>
									</tr>
								</table>
								
							</td>
						</tr> 
					</table>
				</td>
	 		</tr>
		</table>
		</form>
	</center>
</body>
</html>
