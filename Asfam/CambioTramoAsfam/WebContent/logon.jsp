<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Properties"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<%=request.getContextPath()%>/assets/css/Master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/javascript/core.js"></script>
<title>La Araucana C.C.A.F. - Cambio Tramo Asignacion Familiar</title>
<style>
.salir {
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px; 
	font-weight: bold; 
	color: yellow;
}
</style>
<style type="text/css">
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #005C8B; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 12px; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size:10px; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>

</head>
<body bgcolor="#FFFFFF">
<table style="width 100%; height: 60px" cellSpacing="0" cellPadding="0" border="0">
	<tbody>
		<tr>
			<td align="left">
								<img src="assets/img/BONO.jpg" />
		
			</td>
		</tr>
	</tbody>
</table>
<table border="0" width="974" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td colspan="3">
				<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
					<tr>
						<td>
							<br>
							<br>
							<form action="j_security_check" method="post">
								<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" align="center">
									<tr> 
										<td class="login">
											<table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table" align="center">
												<tbody>
													<tr>
														<th colspan="2" class="column-head" scope="rowgroup">Por favor, ingrese su Rut y clave de acceso&nbsp;&nbsp;</th>
													</tr>
													<tr>
														<td valign=top colspan="2" class="table-text" nowrap>&nbsp;</td>
													</tr>
													<tr>
														<td valign=top width="20%" class="table-text" nowrap>
															<label	for="name">Rut:</label>
														</td>
														<td valign=top class="table-text">
															<input type="text" name="j_username" class="short" id="name" maxlength="12" onKeyPress="keyRut()">
															<font size="1">&nbsp;&nbsp;(incluir guión y dígito verificador)</font>
														</td>
													</tr>
													<tr>
														<td valign=top width="20%" class="table-text" nowrap>
															<label	for="pswd">Contrase&ntilde;a:</label>
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
								</table>			
							</form>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<br>
				<br>
			</td>
		</tr>
	</tbody>
</table>
<br>
<br>
<img src="assets/img/footer.jpg" width="974" height="44">
<script>
	document.forms[0].j_username.focus();
</script>
</body>
</html>