<%@ include file="layout/headerJsp.jsp"%>
	<!-- 
	<form id="formLoadMant_SIL">
		<div class="classLoading" id="loadMenuTabla_SIL">
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
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPagina('SIL')">
					
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPagina('SIL')">				
				</div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}
				</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePagina('SIL')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPagina('SIL')">
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
   				<th>Folio de pago</th>
   				<th>Fecha t&eacute;rmino reposo</th>
				<th>Opciones</th>
			</tr>
		</thead>	                         
 		<tbody>
 			<!--Empty y msg-->
			<!-- Iterate-->
			<c:forEach items="${silList}" var="silList">
				<tr style="border: #4682B4 1px solid;"> 

					<td class="var_c">${silList.correlativ}</td>
					<td class="var_r">${silList.ruttrabaj}</td>
					<td class="var_f">${silList.nrofol}</td>
					<td class="var_pf">${silList.pagfol}</td>
					<td class="var_ftr">${silList.lichasfec}</td>
					 
					<td>
						<input type="button" class="boton up_SIL"  value="Editar"/>												
						<input type="button" class="boton del_SIL"  value="Eliminar"/>
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
					<input type="button" class="boton"  value="Primero" onclick="javascript:primeraPagina('SIL')">
					
					<input type="button" class="boton"  value="Anterior" onclick="javascript:retrocesoPagina('SIL')">
				</div>
				<div class="txt_center">
					P&aacute;ginas Totales: ${cantidadPaginas} <br> P&aacute;gina Actual: ${paginaActual}
				</div>
				<div class=".btn_direccionRight">
					<input type="button" class="boton"  value="Siguiente" onclick="javascript:avancePagina('SIL')">
					
					<input type="button" class="boton"  value="Último" onclick="javascript:ultimaPagina('SIL')">
				</div>
			</c:when>
		</c:choose>
	</div>
	<br>
	<strong>${mensajeBusqueda}</strong>	