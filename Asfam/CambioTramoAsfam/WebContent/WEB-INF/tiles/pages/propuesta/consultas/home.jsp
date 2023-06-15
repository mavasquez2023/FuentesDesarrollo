<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
	<td valign="top">
	<jsp:include   page="/WEB-INF/tiles/common/menudivision.jsp"></jsp:include>
	</td>
	<td width="730px">
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
				<font class="blackText">Encargado Division Previsional:</font>
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
	</tbody>
</table>
<br>
<html:form action="/ParametrosSistema.do" styleId="formParam">
<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td width="90%" align="center">
					<font class=blueText5>
					<b>
					PARÁMETROS DEL SISTEMA
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
				
					<div id="tabla" style="width: 730px;">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tbody>
								<tr>
									<th width="30%" align="left" >
										Descripción
									</th>
									<th align="center" width="30%" >
										Formato
									</th>
									<tH align=middle width="40%">
									 
										Valor
									</th>
								</tr>									 
								
									<tr>
										<td width="30%">
											<font class="blueText">Período</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">AAAAMM</font>
										</td>
										<td width="40%" align="center">
											<font class="blueText"><input type="text" name="periodoproceso" id="periodoproceso" maxlength="6" value="${parametros.periodoProceso }"></font>
										</td>
									</tr>
									<tr>
										<td width="30%">
											<font class="blueText">Fecha Apertura</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">dd/mm/aaaa</font>
										</td>
										<td width="40%" align="center">
											<font class="blueText"><input type="text" name="fechaapertura" id="fechaapertura" maxlength="10" value="${parametros.fechaApertura }"></font>
										</td>
									</tr>
									<tr>
										<td width="30%">
											<font class="blueText">Fecha Cierre</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">dd/mm/aaaa</font>
										
										</td>
										<td width="40%" align="center">
											<font class="blueText"><input type="text" name="fechacierre" id="fechacierre" maxlength="10" value="${parametros.fechaCierre }"></font>
										</td>
									</tr>
									<tr>
										<td width="30%">
											<font class="blueText">Fecha Envío</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">dd/mm/aaaa</font>
										
										</td>
										<td width="40%" align="center">
											<font class="blueText"><input type="text" name="fechaenvio" id="fechaenvio" maxlength="10" value="${parametros.fechaEnvio }"></font>
										</td>
									</tr>
									<tr>
										<td width="30%">
											<font class="blueText">Tipo Descarga</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">&nbsp;</font>
										
										</td>
										<td width="40%" align="center"> 
											<font class="blueText">
												<input type="radio" name="tipoDescarga"  
													<c:if test="${parametros.tipoDescarga=='PROPUESTA' }">checked="checked"</c:if>
													 
													value="PROPUESTA">PROPUESTA &nbsp;
												<input type="radio" name="tipoDescarga"  
												<c:if test="${parametros.tipoDescarga=='INFORME' }">checked="checked"</c:if>
												value="INFORME">INFORME
											</font>
										</td>
									</tr>
									<tr>
										<td width="30%">
											<font class="blueText">Tabla Copia AFP64</font>										 
										</td>
										<td width="30%" align="center">
											<font class="blueText">AFP64_aaaa</font>
										</td>
										<td width="40%" align="center">
											<font class="blueText"><input type="text" name="copiaAFP64" id="copiaAFP64"  maxlength="10" size="12" value="${parametros.copiaAFP64 }"></font>
											<input class="boton"  type="submit"
											value="Copiar" name="copiar" id="copiar"
											onclick="CopiarTabla();"
											onMouseOver="this.className='botonOver'"
											onMouseOut="this.className='boton'">
										</td>
									</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>

			<tr><td>&nbsp;</td></tr>
			<tr>
								<td id="tdLoader" align="center"  style="display:none;">
									<span class="gif-load">
										Procesando, por favor espere un momento.
									</span>
									<br />
								</td>
							</tr>
			<tr>
				<td width="100%">
					<br>
					<p align="center">
						<input class="boton"  type="submit"
						 
							value="Guardar" name="aceptar" id="button"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">

		<script>
					
			$(function(){
				$("#button").click(function(e){
					e.preventDefault();
					$("#formParam").submit();										
					$("#tdLoader").append('<div id="div_downloading"><img id="imgLoader" src="assets/img/ajax-loader.gif" /><br/></div>');
					$("#tdLoader").css('display','block');
					$("#imgLoader").css('display','block');					
				});
				$("#copiar").click(function(e){
					e.preventDefault();										
					$("#tdLoader").append('<div id="div_downloading"><img id="imgLoader" src="assets/img/ajax-loader.gif" /><br/></div>');
					$("#tdLoader").css('display','block');
					$("#imgLoader").css('display','block');
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
<br>
</td>
</table>