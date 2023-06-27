<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<style type="text/css">
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 70.0%; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size: 10px;  font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.error-text {text-align:left; color:blue; font-family: Arial, Helvetica, sans-serif; font-size: 70.0%;}
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>

<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr>
		<td>&nbsp;
		<td>
	</tr>
	<tr>
		<td width="10%" style="vertical-align: top;">
			<html:img page="/img/exclaim.gif" alt="" width="40" height="40" />
		</td>
		<td class="error-text">Error en la validaci&oacute;n<br />Intente de nuevo.</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">
			<form action="j_security_check" method="post">
			<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" align="center">
				<tr>
					<td class="login">
					<table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table">
						<tbody>
							<tr>
								<th colspan="2" class="column-head" scope="rowgroup">Ingresar&nbsp;&nbsp;</th>
							</tr>
							<tr>
								<td valign=top colspan="2" class="table-text" nowrap>&nbsp;</td>
							</tr>
							<tr>
								<td valign=top width="33%" class="table-text" nowrap>
									<label	for="name">Identificador:</label>
								</td>
								<td valign=top class="table-text">
									<input type="text" name="j_username" class="short" id="name">
								</td>
							</tr>
							<tr>
								<td valign=top width="33%" class="table-text" nowrap>
									<label	for="pswd">Clave:</label>
								</td>
								<td valign=top class="table-text">
									<input type="password" name="j_password" class="short" id="pswd">
								</td>
							</tr>
							<tr>
								<td valign=top colspan="2" class="login-button-section" nowrap align="center">
									<input type="submit" name="action" class="buttons" value='Aceptar'>
								</td>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td>
						<table>
							<tr>
								<td class="nota" style="width: 35px;"><b>NOTA</b> </td>
								<td class="nota">
									Este servicio se encuentra en etapa de implementaci&oacute;n, 
									por lo que su acceso mediante la creaci&oacute;n y asignaci&oacute;n de clave, 
									esta siendo coordinado mediante un proceso de Solicitudes, 
									por lo que lo invitamos a tomar contacto envi&aacute;ndonos su solicitud 
									a la direcci&oacute;n electr&oacute;nica <a href="mailto:ctorres@laaraucana.cl">ctorres@laaraucana.cl</a>.
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
</table>

<script>
	document.forms[0].j_username.focus();	
</script>