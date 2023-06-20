<%@ include file="layout/headerJsp.jsp"%>
	 <!--
	<form id="formLoadMant_LM">
		<div class="classLoading" id="loadMenuTabla_LM">
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
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPagina('LM')">
										
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPagina('LM')">
				</div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}
				</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePagina('LM')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPagina('LM')">
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
   				<th>Fecha inicio reposo</th>
   				<th>Fecha t&eacute;rmino reposo</th>
				<th>Opciones</th>
			</tr>
		</thead>	                         
 		<tbody style="border: solid; color: #4682B4; border-bottom: #4682B4; border-width: 1px;">
 			<!--Empty y msg-->
			<!-- Iterate-->
			<c:forEach items="${lmList}" var="lmList">
				<tr style="border: #4682B4 1px solid;">
					<td class="var_c" >${lmList.correlativ}</td>
					<td class="var_r" >${lmList.ruttrabaj}</td>
					<td class="var_f" >${lmList.folio}</td> 
					<td >${lmList.fecinirepo}</td>
					<td class="var_ftr">${lmList.fecterrepo}</td>
					<td>
						<input type="button" class="boton up_LM"  value="Editar"/>												
						<input type="button" class="boton del_LM"  value="Eliminar"/>
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
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPagina('LM')">
					
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPagina('LM')">
				</div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}				
				</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePagina('LM')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPagina('LM')">
				</div>
			</c:when>
		</c:choose>
	</div>
	<br>
		<strong>${mensajeBusqueda}</strong>	