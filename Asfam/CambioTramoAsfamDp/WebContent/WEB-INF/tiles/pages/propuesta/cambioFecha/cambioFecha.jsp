<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jquery-ui.css" />
<script src="${pageContext.request.contextPath}/assets/javascript/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/javascript/jquery-ui.js"></script>


<script>
$(function () {
$("#fechaCorteInicio").datepicker({
dateFormat: 'dd/mm/yy',
 changeMonth: true,
 changeYear: true,
firstDay: 1
});

$("#fechaCorteFin").datepicker({
dateFormat: 'dd/mm/yy',
 changeMonth: true,
 changeYear: true,
firstDay: 1
});
});
</script>

<script>
        
        function capturaFechas(fechaInicial,fechaFinal)
        {
            var fechaInicial= document.getElementById('fechaCorteInicio').value;
            var fechaFinal= document.getElementById('fechaCorteFin').value;
            valuesStart=fechaInicial.split("/");
            valuesEnd=fechaFinal.split("/");
 
            // Verificamos que la fecha no sea posterior a la actual
            var dateStart=new Date(valuesStart[2],(valuesStart[1]-1),valuesStart[0]);
            var dateEnd=new Date(valuesEnd[2],(valuesEnd[1]-1),valuesEnd[0]);
            
            if(dateStart != "" || dateEnd != ""){
            
               if(dateStart>=dateEnd)
               {
                   alert("La fecha de inicio no puede ser igual o superior a la fecha de termino");
               }
            
            }
            
            else{
             
                  alert("fecha de inicio y fecha de termino son campos obligatorios");
                   return 0;
            
            }
                return 1;
        }
 
    </script>





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
					<br></br><%=request.getAttribute("procesoTramo")%>
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
				CONFIGURAR FECHAS DE PROCESO
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
				<html:form action="/CambioTramoFecPage" method=POST>
					<div id="tabla" style="width: 476px;">
						<table cellspacing="1" cellpadding="1" border="0">
							<tbody>
								<tr valign="top">
									<th width="722" colspan="2" bgcolor="#050BD1">
										
									</th>
								</tr>
								<tr>
									<td colspan="2" align="left">
										<font style="font-size: x-small;">(*) campos requeridos</font>
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
										<font class="text">Fecha de inicio</font>
									</th>
									<td align="left" width="60%">
									<%if(request.getAttribute("fechaCorteInicio") != null)  { %>
									<input type="text" name="fechaCorteInicio" id="fechaCorteInicio" readonly="false"  value="<%=request.getAttribute("fechaCorteInicio")%>">
									<% } else { %>
									<input type="text" name="fechaCorteInicio" id="fechaCorteInicio" readonly="false">
									<% } %>
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
									
										<font class="text">Fecha de termino&nbsp; </font>
									</th>
									
									<td align="left" width="60%">
									  <%if(request.getAttribute("fechaCorteFin") != null)  { %>
									<input type="text" name="fechaCorteFin" id="fechaCorteFin" readonly="false"  value="<%=request.getAttribute("fechaCorteFin")%>">
									<% } else { %>
									<input type="text" name="fechaCorteFin" id="fechaCorteFin" readonly="false">
									<% } %>
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
											onclick="if (capturaFechas() == 1){submit()};"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Guardar
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

</td>
</table>

<c:if test="${!empty mensaje}" >
	<script>
		alert("${mensaje}");
	</script>
</c:if>