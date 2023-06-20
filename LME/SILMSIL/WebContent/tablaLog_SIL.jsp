<%@ include file="layout/headerJsp.jsp"%>
<!-- 
	<form id="formLoad_SIL">
		<div class="classLoading" id="loadingLog">
			<center>
				<img src='<%=request.getContextPath() %>/img/Loading.gif'  id="imgLoad" name="imgLoad" />
				<br>Espere un momento...<br>
			</center>
		</div>
	</form>
-->
	<div class="msjLog">
		<c:if test="${flag=='0'}">
			<p><center>No se han encontrado registros para el período.</center></p>
		</c:if>
	</div>
	<div id="paginacion" class="bordeLimite">
		<c:choose>
			<c:when test="${Keybusqueda==false}">
				<div class="btn_direccionLeft">
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPaginaLog('SIL')">
					
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPaginaLog('SIL')">
				</div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}
				</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePaginaLog('SIL')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPaginaLog('SIL')">	
				</div>
			</c:when>
		</c:choose>
	</div>
	<div class="datagrid">
	<table class="tablaSinBordes">
		<thead>
			<tr>
				<th>N°</th>
   				<th>Rut Trabajador</th>
   				<th>N° Licencia</th>   				
   				<th>Emisi&oacute;n</th>
   				<th>Descripci&oacute;n Error</th>
				<th>Opci&oacute;n</th>
			</tr>
		</thead>	                         
 		<tbody>
 			<!--Empty y msg-->
			
			<!-- Iterate-->
			<c:forEach items="${silList}" var="silList">
				<tr style="border: #4682B4 1px solid;"> 
					<td>${silList.correlab}</td>
					<td class="var_rut">${silList.ruttrabaj}</td>
					<td class="var_nrofol">${silList.nrofol}</td>
					<td class="var_fecemi">${silList.fecemi}</td>
					<td>
						${silList.descripcion}
						<input type="hidden" class="var_lichasfec" value="${silList.lichasfec}">
						<input type="hidden" class="var_pagfol" value="${silList.pagfol}">
						<input type="hidden" class="var_correlab" value="${silList.correlab}">
						<input type="hidden" class="var_c" value="${silList.correlativ}">
					</td>
					<td>
						<input type="button" class="boton correct_SIL"  value="Corregir Error" />	
					</td> 
				</tr>
			</c:forEach>
		<!--END ITERATE--->
		</tbody>
	</table>
	</div>
	
	<div id="paginacion" class="bordeLimite">
		<c:choose>
			<c:when test="${Keybusqueda==false}">
				<div class="btn_direccionLeft">
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPaginaLog('SIL')">
					
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPaginaLog('SIL')"></div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePaginaLog('SIL')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPaginaLog('SIL')">
				</div>
			</c:when>
		</c:choose>
	</div>
	<br>
	<strong>${mensajeBusqueda}</strong>	
