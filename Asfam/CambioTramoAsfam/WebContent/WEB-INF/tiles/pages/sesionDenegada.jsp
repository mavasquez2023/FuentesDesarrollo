<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/src/pagecode/SesionDenegada.java" --%><%-- /jsf:pagecode --%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Properties"%>
<% 
	request.getSession().invalidate();
	Properties Config = new Properties();
	Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="/assets/css/Master.css" rel="stylesheet" type="text/css" />
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
						<table cellSpacing="0" cellPadding="0">
							<tbody>
								<tr>
									<td valign="top" rowSpan="2">
										<img src="../../../assets/img/BONO.jpg" />
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<table border="0" width="974" style="font-family: Arial, Helvetica, sans-serif">
			<tbody>
				<tr>
					<td colspan="3" align="center">
						<font color="#808080">
							<b>
								Proceso Actualizaci&oacute;n de Tramos<br />
								<%out.println(Config.getProperty("PROCESO"));%>
							</b>
						</font>
					</td>
				</tr>
				
				<tr><td colspan="3">&nbsp;</td></tr>
				<tr><td colspan="3">&nbsp;</td></tr>
			
				<tr>
					<td colspan="3" align="center" style="text-decoration: underline;">
						<span>
							<b>En este momento el servidor se encuentra ocupado, por favor intente conectarse más tarde</b>
						</span>
						<br />
						<span>
							<a href="<%out.println(Config.getProperty("URLVOLVER"));%>">Volver</a>
						</span>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<br>
		<img src="../../../assets/img/footer.jpg" width="974" height="44">
	</body>
</html>

