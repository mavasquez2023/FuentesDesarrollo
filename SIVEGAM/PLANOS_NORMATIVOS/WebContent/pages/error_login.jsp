<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ERROR LOGIN</title> 
	<link href="./css/estilos_interna.css" rel="stylesheet" type="text/css" />
	<link href="./css/grid_960.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		function closeSesion(){		
			window.open('', '_self', ''); 
			window.close();
		}
	</script>
	
</head>
<body>

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="anio">
  <input type="hidden" name="mes">
  
  <div id="caja_registro">
	
  </div>
  <table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	  <table width="1020">
		<tr>
			<td align="right"> 
				<a href="#" align="right" onclick="javascript:closeSesion()"><B>Cerrar</B></a> 
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			<table align="center" width="1020">
				<tr>
					<td align="center">
						<strong>
							<p1>
								<font color="#0B3B39">INTERFAZ DE ERROR</font>
							</p1>
						</strong>
						<br/>		
					</td>
				</tr>
			</table>
			<br>
			<table width="1020">
				<tr><td>
					<table width="1020">
						<tr><td>
							<table width="288" bgcolor="#F2F2F2" align="center">
								<tr>
									<th height="38" colspan="2"><font color="#1B2935">Ha ocurrido un error : </font></th>
								</tr>
								<tr>
									<th height="38" colspan="2"><font color="#1B2935">No se ha podido validar de manera correcta las credenciales del usuario.</font></th>
								</tr>
							</table>
						</td></tr>
					</table>
				</td></tr>
			</table>
		   </td>	
		</tr>
		<tr>
			<td> 
				
			</td>
		 </tr>
		<tr>
		<td>
		</td>
		</tr>
		<tr> 
		</tr>		 	 	
	  </table> 
</body>
</html:html> 



