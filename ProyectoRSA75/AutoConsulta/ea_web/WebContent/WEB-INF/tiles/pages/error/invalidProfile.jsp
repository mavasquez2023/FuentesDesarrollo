<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F</title>

<script>
function goto(url) {
	location.href="<%=request.getContextPath()%>" + url;
}
</script>
</head>
<body>
<table>
	<tbody>
		<tr>
			<td>
				<table border="0" cellspacing="1" cellpadding="0">
					<tbody>
						<tr style="height : 20px;"><td><img alt="" height="1" width="4" src="./img/c.gif"/></td></tr>
						<tr>
							<td style="font-family: verdana; font-size: medium;">
								<p>Estimado(a) Cliente.</p>
							</td>			
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>			
						</tr>
						<tr>
							<td>
								Su sessión ha expirado.
							</td>			
						</tr>
						<tr>
							<td>
								Ingrese nuevamente.
							</td>			
						</tr>
						<tr>
							<td>
								Gracias
							</td>			
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>			
						</tr>
						<tr style="height : 10px;"><td><img alt="" height="1" width="4" src="./img/c.gif"/></td></tr>
						<tr>
							<td>
								<!-- Form -->
								<form action="j_security_check" method="post">
									<table width="300px">
										<tbody>
											<tr>
												<td class="labelbold" style="width : 100px;">Identificador:</td>
												<td><input size="20" type="text" name="j_username" maxlength="25" /></td>
											</tr>
											<tr>
												<td class="labelbold">Contraseña:</td>
												<td><input size="20" type="password" name="j_password" maxlength="25" /></td>
											</tr>
											<tr>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td style="text-align: center;" colspan="2">
													<input class="buttonbold" type="submit" name="action" value="Ingresar" />&nbsp;
													<!--
													<input class="buttonbold"type="reset" name="reset" value="Limpiar" />
													-->
												</td>
											</tr>
										</tbody>
									</table>
								</form>											
							
							</td>
						</tr>								
					</tbody>
				</table>
			</td>			
		</tr>
	</tbody>
</table>
</body>
</html>
