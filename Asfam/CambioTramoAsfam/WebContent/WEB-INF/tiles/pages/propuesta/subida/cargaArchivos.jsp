<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<table>
<tr>
<td width="730px">
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
				<font class="blackText">Encargado de Empresa:</font>
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
				ENV&#205;O DECLARACION DE RENTA
				<br/>
				</b>
				</font>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<br>
				<br>
			</td>
		</tr>
		<tr>
			<td width="100%" align="center" valign="top">
				<html:form action="Cargar" method="post" enctype="multipart/form-data" styleId="formCarga">
					<div id="tabla" style="width: 520px;">
						<table cellspacing="1" cellpadding="1" border="0">
							<tbody>
								<tr valign="top">
									<th width="722" bgcolor="#050BD1">
										<font class="text">Haga click en el bot&#243;n 'Seleccionar archivo' para buscar el archivo que desea enviar</font>
									</th>
								</tr>
								<tr valign="top">
									<td width="722" valign="middle" height="50">
										<font size="2" face="Arial">Ubicaci&#243;n Archivo:</font>
										<html:file property="archivo" onkeypress="return false" styleId="archivo" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<br>
					
					 <input type="hidden" name="rutEmpresa" value="<%=request.getAttribute("rutestado")%>">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tbody>
							<tr valign="top">
								<td width="100%">
									<p align="center">
										<html:submit styleId="button" styleClass="boton" 
											value="Enviar Archivo" onmouseover="botonover();" onmouseout="botondown();">
											Enviar Archivo
										</html:submit>
										<script>
										$(function(){
											$("#button").click(function(e){
												e.preventDefault();

												$(this).attr('disabled','disabled');
												
												var result = validar('Empresa');
												
												if(result == false){
													$(this).removeAttr("disabled");
												}else{
													$("#formCarga").submit();
													
													$("#tdLoader").append('<img id="imgLoader" src="assets/img/ajax-loader.gif" /><br />');
													$("#tdLoader").css('display','block');
													$("#imgLoader").css('display','block');
												}
											});
										});
										</script>

										<html:button styleClass="boton"
											onclick="window.close();"
											property="close"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Cancelar
										</html:button>
									</p>
								</td>
							</tr>
							
							<tr><td>&nbsp;</td></tr>
							<c:if test="${estado=='F'}">
							<tr>
								<td colspan="3">
									<p align="center">
									<font  style="font-weight: bold;color:red">
									&nbsp; Usted ya realiz&oacute; la declaraci&oacute;n para esta empresa, si vuelve a enviar reemplazará toda la información.
									</font>
									</p>
								</td>
							</tr>
							</c:if>
							<tr>
								<td id="tdLoader" align="center" style="display:none;" >
									<span class="gif-load">
										Procesando archivo, por favor espere un momento.
									</span>
									<br />
								</td>
							</tr>
							
						</tbody>
					</table>
					<br>
					<div id="mensaje" align="left">
						<p>
							<font class="redBackGris">
							<u>Recuerde que</u>:
							<br/><br/>
							<li>
								No debe realizar modificaciones al formato del archivo (Ejemplo: agregar nuevas columnas, utilizar fórmulas, 
								números con decimales, entre otros).
							</li>
							<br/><br/>
							<li>
								El nombre del archivo puede ser cualquiera pero  
								debe poseer extensi&#243;n <b>.csv</b>
							</li>
							</br></br>
							<li>
								En caso que su empresa tenga trabajadores que hayan efectuado reconocimiento de causantes por primera vez durante el mes de Junio o Julio <%=request.getAttribute("periodo_anterior")%>, debe agregar en la n&#243;mina a estos trabajadores. 
							</li>
							</font>
							</p>
					</div>	
					<br>
				</html:form>
			</td>
		</tr>
	</tbody>
</table>
</td>
</tr>
</table>