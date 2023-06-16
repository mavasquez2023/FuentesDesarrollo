<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
List lista=(List)request.getSession().getAttribute("lista");
 %>
 
<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<table><tr><td valign="top">
<jsp:include  page="/WEB-INF/tiles/common/menudivision.jsp">
  
</jsp:include>
</td>
<td width="730px">
<html:form action="DescargaPropuestasZip" styleId="formDescarga">
	<html:hidden property="serviceName" />
	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
	<div align="center">
		<b>
			<i>
				<font color="#808080">
					Proceso Actualizaci&#243;n de Tramos
					<br><bean:write name="proceso" property="proceso"/>
				</font>
			</i>
		</b>
		<br>
	</div>
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td width="5%" height="21"></td>
				<td width="80%" height="21"></td>
				<td width="10%" height="21"></td>
				<td width="5%" height="21"></td>
			</tr>
			<tr>
				<td width="5%" height="18"></td>
				<td align="left" width="20%" height="18">
					<font class="blackText">Encargado Divisi�n Previsional:</font>

					<br />
					<font class="blueText"> 
						<jsp:getProperty name="edocs_encargado" property="fullName" />
					</font>
				</td>
				<td align="left" width="70%" height="18">
					<font class="blackText">Rut Encargado:</font>
					<br />
					<font class="blueText">
						<jsp:getProperty name="edocs_encargado" property="formattedRut" />
					</font>
				</td>
				<td width="5%" height=18></td>
			</tr>
			<tr>
				<td width="5%" height=21></td>
				<td width="80%" height=21></td>
				<td width="10%" height=21></td>
				<td width="5%" height=21></td>
			</tr>
			<tr>
				<td width="5%" height=21></td>
				<td width="80%" height=21></td>
				<td width="10%" height=21></td>
				<td width="5%" height=21></td>
			</tr>
		</tbody>
	</table>
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td width="90%" align="center">
					<font class=blueText5>
					<b>
					DESCARGA DE PROPUESTAS
					</b>
					</font>
				</td>
			</tr>
			<tr>
			<tr>
				<td width="100%" colspan="3">
					<br>
				</td>
			</tr>
		
			<tr>
				<td width="100%">
				<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
				<td width="40%" align="right">
					<font class=text>Ingrese Rut Empresa:&nbsp;</font>
				</td>
				<td width="30%">
					<input type="text" name="rutempdown" id="rutempdown" value="" maxlength="12" onKeyPress="keyRut()">
				</td>
				<td width="30%">
					&nbsp;
				</td>
				</tr>	
				</table>
				</td>
				
			</tr>
	
			<html:hidden property="rutt" />
			<tr><td>&nbsp;</td></tr>
			<tr>
								<td id="tdLoader" align="center"  style="display:none;">
									<span class="gif-load">
										Procesando archivo, por favor espere un momento.
									</span>
									<br />
								</td>
							</tr>
			<tr>
				<td width="100%">
					<br>
					<p align="center">

						<input class="boton"  type="button"
						 
							onclick="return descargaPropuesta();"
							value="Descargar Propuesta" name="aceptar" id="button"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">
						

					
		<script>
					
			$(function(){
				$("#button").click(function(e){
					e.preventDefault();
					if($("#rutempdown").val()!=""){
						$("#formDescarga").submit();										
						$("#tdLoader").append('<div id="div_downloading"><img id="imgLoader" src="assets/img/ajax-loader.gif" /><br/></div>');
						$("#tdLoader").css('display','block');
						$("#imgLoader").css('display','block');
					setTimeout('$("#div_downloading").remove();$("#tdLoader").css("display","none")', 3000);
					}
				});
			});
			
			
	</script>
					</p>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td width="100%" colspan="3">
					<center>
						<span style="color:red;font-size:16px;font-weight: bold">
						</span>
					</center>
				</td>
			</tr>
			
		</tbody>
	</table>
	
</html:form>
</td>
</tr>
</table>