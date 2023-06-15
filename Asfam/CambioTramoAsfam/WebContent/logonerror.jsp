<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Expires","0"); 
response.setDateHeader("Expires",-1); 
%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>La Araucana C.C.A.F. - Cambio de Tramo</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cambiotramo.css" type="text/css">
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
<body bgcolor="#ffffff"><div class="main">
<center>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr> 
    <td colspan="7" scope="col"><img src="${pageContext.request.contextPath}/assets/img/BONO.jpg" border="0" /></td>
  </tr>
  <tr> 
    <td colspan="7" align="center" valign="middle" scope="row"></td>    
  </tr>
     <tr> 
    <th align="left" valign="middle" scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td width="178">&nbsp;</td>
    <td width="287" align="center" valign="top" class="titulos">&nbsp;</td>
    <td width="16">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="18">&nbsp;</td>
  </tr>
  <tr> 
        
    <td  colspan="7" align="center" valign="middle" class="titulos_tabla"></td>    
  </tr>
  <tr> 
        
    <td colspan="7" align="left" valign="top" class="titulo_login"><div align="center"></div></td>    
  </tr>
   <tr> 
    <td colspan="7" align="center" valign="middle" height="10"></td>    
  </tr>
  <tr>     
    <td colspan="7" align="center" valign="top"><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td align="center" class="subtitulos" height="75">
           			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tbody><tr> 
					<td class="login">
					<table class="noframe-framing-table" summary="Login Table" align="center" border="0" cellpadding="5" cellspacing="0" width="400">
						<tbody>
							<tr>
								<th colspan="2" class="column-head" scope="rowgroup">Error de autenticación&nbsp;&nbsp;</th>
							</tr>
							<tr>
								<td colspan="2" class="table-text" valign="top" nowrap="nowrap">&nbsp;</td>
							</tr>
							<tr>
								<td class="table-text" valign="middle" width="20%" nowrap="nowrap" align="center">
									<label for="name">El Rut y/o clave ingresados no son válidos.</label>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="table-text" valign="top" nowrap="nowrap">&nbsp;</td>
							</tr>	
							<tr>
						
								<td colspan="2" class="login-button-section" align="center" valign="top" nowrap="nowrap">
								   <a href="${pageContext.request.contextPath}/index.jsp"><input class="buttons" type="button" onclick="${pageContext.request.contextPath}/index.jsp" name="btn_volver" value="Volver"></a></td> 
							</tr>						
						</tbody>
					</table>
					</td>
				</tr>
			</tbody></table>
          </td>
        </tr>             
      </table>
      </td>    
  </tr>
  <tr> 
    <td colspan="7" align="center" valign="middle" height="20"></td>    
  </tr>
  <tr>
  	<td colspan="7" width="100%">
  		<table width="50" border="0" align="center" cellpadding="0" cellspacing="0">

      </table> 
     </td>  
  </tr>
</table>
</center>
</div>
</body>
</html>