<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				<font class="blackText">Encargado División Previsional:</font>
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
				CONSULTA DE ESTADO DE PROPUESTAS
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
				<html:form action="/Consulta1" >
					<div id="tabla" style="width: 476px;">
						<table cellspacing="1" cellpadding="1" border="0">
							<tbody>
								<tr valign="top">
									<th width="722" colspan="2" bgcolor="#050BD1">
										<font class="text">Ingrese parámetros para la búsqueda</font>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="left">
										<font style="font-size: x-small;">(*) Campos requeridos</font>
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
										<font class="text">Código Oficina:</font>
									</th>
									<td align="left" width="60%">
										<html:text name="divicionPrevicionalForm" property="oficina" />
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
										<font class="text">Rut Empresa:&nbsp;<span style="color: red;">*</span> </font>
									</th>
									<td align="left" width="60%">
										<html:text name="divicionPrevicionalForm" property="empresa" onkeypress="return rut_validkey(event);" maxlength="10"/>
									  <font style="font-size: x-small;"> (Ejemplo 12345678-9)</font>
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
										<font class="text">Código Sucursal:</font>
									</th>
									<td align="left" width="60%">
										<html:text name="divicionPrevicionalForm" property="sucursal" />
									</td>
								</tr>								
							</tbody>
						</table>
					</div>
					<br>
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tbody>
							<tr valign="top">
								<td width="100%">
									<p align="center">
										<html:submit styleClass="boton"
											onclick="return onSubmitConsulta1();"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Buscar
										</html:submit>
										<html:cancel styleClass="boton"
											onclick="goToUrl('/CambioTramoAsfam/DivisionPrevisionalPage.do?step=homeDivisionPrevisional'); return false;"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Cancelar
										</html:cancel>
									</p>
								</td>
							</tr>
						</tbody>
					</table>
				</html:form>
			</td>
		</tr>
	</tbody>
</table>


<logic:equal name="divicionPrevicionalForm" property="afiliados_propuesta_propuesta" value="0">
	<h3>No se encontro informacion relacionada a los datos ingresados.</h3>
</logic:equal>
 <logic:notEqual name="divicionPrevicionalForm" property="afiliados_propuesta_propuesta" value="0" >


	<h2>Resultado:</h2>
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tr>
			<td>Numero de Trabajadores Propuesta:</td>
			<td>
				<bean:write name="divicionPrevicionalForm" property="afiliados_propuesta_propuesta" />
			</td>
		</tr>
		<tr>
			<td>Numero de Trabajadores Informados:</td>
			<td>
				 <c:out value="${informados}"></c:out>
			</td>
		</tr>
			 
		<tr>
			<td>Numero de Trabajadores ingresados:</td>
			<td>
				 <c:out value="${insertados}"></c:out>
			</td>
		</tr>
	</table>
	</logic:notEqual>
	<logic:present name="divicionPrevicionalForm" property="afiliados_propuesta">
	<br><br>
	<table width="100%"><tr><td><span style="font-size: 18;"><b>Listado de Trabajadores Informados</b></span></td>
	 
	<td align="right"><span style="font-size: 16;color: black"><html:link action="excel.do">Descargar Excel</html:link></span> 
	</td> 
	</tr></table>
     <br><br>
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td width="100%">
					<div id="tabla">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tbody>
								<tr>
									<th align="center" width="13%" bgcolor="#050BD1">
										<font class="text">Rut</font>
									</th>
									<th align="center" width="35%">
										<font class="text">Nombre</font>
									</th>
									<th align="center" width="20%">
										<font class="text">Ingreso Promedio Mensual</font>
									</th>
									<th align="center" width="10%">
										<font class="text">Tramo</font>
									</th>
									<th align="center" width="10%">
										<font class="text">Valor</font>
									</th>
									<th align="center" width="12%">
										<font class="text">Via de Recepcion</font>
									</th>
								</tr>
								<logic:iterate name="divicionPrevicionalForm" property="afiliados_propuesta" id="afiliado" indexId="index"> 
									<tr>
										<td align="left">
											<font class="blueText">
												&nbsp;<bean:write name="afiliado" property="formatedRutAfiliado" />
											</font>
										</td>
										<td align="left">
											<font class="blueText">
												<bean:write name="afiliado" property="nombreAfiliado" />
											</font>
										</td>
										<td align="right">
											<font class="blueText">
												$<bean:write name="afiliado" property="ingresoPromedio" format="#,###,###" />
											</font>
										</td>
										<td align="center">
											<font class="blueText">
												<bean:write name="afiliado" property="tramo" />
											</font>
										</td>
										<td align="center">
											<font class="blueText">
												<bean:write name="afiliado" property="valorTramo" />
											</font>
										</td>
										<td align="center">
											<font class="blueText">
												<bean:write name="afiliado" property="fullOrigen" />
											</font>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</logic:present>
</td>
</table>