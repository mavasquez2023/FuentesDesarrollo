 <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<table>
	<td width="730px">

<html:form action="/declaracionjurada.do" styleId="formDeclaracion">
	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
	<div align="center">
		<b>
			<i>
				<font color="#808080">	Proceso Actualizaci&oacute;n de Tramos
					<br>
				 <bean:write name="proceso" property="proceso" />
				 <logic:present name="estado">
			    <input type="hidden" name="etapa" value="<bean:write name="estado" property="etapa"/>">  
				</logic:present>
				<logic:notPresent name="estado">
				 <input type="hidden" name="etapa" value="0">  
				</logic:notPresent>
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
				<font class="blackText">
				<c:choose>
						<c:when test='${rol=="Ejecutivo"}'>
							Encargado Divisi�n Previsional:
						</c:when>
						<c:otherwise>
							Encargado de Empresa:
						</c:otherwise>
				</c:choose>
				</font>
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
	</tbody>
</table>
<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tbody>
		<tr>
			<td width="90%" align="center">
				<font class=blueText5>
				<b>
				ACEPTAR DECLARACI&Oacute;N JURADA
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
		<br>
		<br>
		<tr>
		<td width="100%" valign="top" colspan="3">
	<div id="tabla">
		<table width="100%" cellspacing="1" cellpadding="0" border="0">
			<tbody>
				<tr valign="top">
					<th width="100%" bgcolor="#050BD1">
						<b>
							<font class="text"> Declaraci&#243;n Jurada</font>
						</b>
					</th>
				</tr>
				<tr valign="top">
					<td width="100%">
						<ul type="disc">
							<li>
								<b> 
									<font size="2" face="Arial"> 
										CERTIFICO QUE LOS INGRESOS PROMEDIOS INFORMADOS, FUERON PREVIAMENTE
										VERIFICADOS CON LAS DECLARACIONES JURADAS DE INGRESOS FIRMADAS
										POR LOS TRABAJADORES Y ESTAS SE ENCUENTRAN EN CUSTODIA DE LA
										EMPRESA, DE ACUERDO A LA NORMATIVA LEGAL VIGENTE. 
									</font>
								</b>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<div id="tabla">
		<table width="100%" cellspacing="1" cellpadding="0" border="0">
			<tbody>
				<tr valign="top">
					<th width="16%" bgcolor="#050BD1">
						<b>
							<font class="text"> Rut Empresa </font>
						</b>
					</th>
					<th width="42%" bgcolor="#050BD1">
						<b>
							<font class="text"> Raz�n Social </font>
						</b>
					</th>
					<th width="38%" bgcolor="#050BD1">
						<b>
							<font class="text"> Aceptar Declaraci�n</font>
						</b>
					</th>
				</tr>
						<bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />
									
							 <logic:equal name="empresa" property="flag" value="1">
									<tr>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font>										 
										</td>
										<td width="42%">
											<font class="blueText"><jsp:getProperty name="empresa" property="name" /></font>
										</td>
										<td width="38%">	
						                        <p align="center"><input type="checkbox" name="option" id="option" value="<bean:write name="empresa" property="fullRut"/>=<jsp:getProperty name="empresa" property="name" />" ></p>
										</td>
									</tr>
							</logic:equal>
						                  
							<html:hidden property="rutt" />
			</tbody>
		</table>
	</div>
	<br>
	<font size="1" face="Arial">
		Al seleccionar "Aceptar la declaraci�n jurada", usted declara bajo juramento que los datos consignados son expresi�n fiel de la realidad. 
	</font>
	<br>
	<br>
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tbody>
			<tr valign="top">
				<td width="100%">
					<p align="center">
						<html:submit styleClass="boton"  styleId="btnSubmit" 
							onmouseover="this.className='botonOver'"
							onmouseout="this.className='boton'">
							Aceptar
						</html:submit>
						<script>
							$(function(){
								$("#btnSubmit").click(function(e){
									e.preventDefault();
									
									$(this).attr('disabled','disabled');
									$("#btnCancel").attr('disabled','disabled');
												
									var result = obtenercheckeddescarga();
										
									if(result == false){
										$(this).removeAttr("disabled");
										$("#btnCancel").removeAttr("disabled");
									}else{
										$("#formDeclaracion").submit();
										
										$("#tdLoader").append('<img id="imgLoader" src="assets/img/ajax-loader.gif" /><br />');
										$("#tdLoader").css('display','block');
										$("#imgLoader").css('display','block');
									}
								});
							});
						</script>
						<html:cancel styleClass="boton"
							onclick="goToUrl('/CambioTramoAsfam/HomePage.do'); return false;"
							onmouseover="this.className='botonOver'"
							onmouseout="this.className='boton'" styleId="btnCancel">
							Cancelar
						</html:cancel>
					</p>
				</td>
			</tr>
			
			<tr><td>&nbsp;</td></tr>
			
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

				</td>
		</tr>
	</tbody>
</table>
</html:form>
</td>
</table>