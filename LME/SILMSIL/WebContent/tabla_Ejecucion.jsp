<%@ include file="layout/headerJsp.jsp"%>
	
	<div id="loadMenu" class="loadMenu">
		<center><img src='./img/cargaDialog.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
	</div>	
	<c:choose>
	   <c:when test="${KeyEstados==false}">
			<label>${msgEjecucion}</label>							
			<script>
				$(document).ready(function(){
					var buttons = {};
   					buttons["Aceptar"] = function() {refrescar(4);$("#dialog_form_Ejecucion").dialog( "close" );};
   					$("#dialog_form_Ejecucion").dialog({buttons:buttons});
  				});
			</script>
		</c:when>
	    <c:otherwise>
	    	
	    	<input type="hidden" id="op" name="op">	
		    	<div class="datagrid">
		    	<form name="form_ejecucion" id="form_ejecucion">
					<table class="tablaSinBordes">
						<thead>
							<tr>
								<th>Periodo</th>
				   				<th>Proceso</th>
				   				<th>Estado</th>
							</tr>
						</thead>	                         
				 		<tbody>
				 			<!--Empty y msg-->	
				 			<c:choose>
							    <c:when test="${not empty listaEjecucion}">
							        <!-- Iterate-->
									<c:forEach items="${listaEjecucion}" var="ejecucionList">
										<tr style="min-height: 30px; height: 30px"> 			
											<td>${ejecucionList.periodo}</td>
											<td>${ejecucionList.proceso}</td>
											<td>${ejecucionList.ultimoEstado}</td>
										</tr>
										<c:if test="${ejecucionList.keyReProceso==true}">								
											<input class="ejecucionInput" type="hidden" id="${ejecucionList.proceso}_${ejecucionList.periodo}" name="${ejecucionList.periodo}_${ejecucionList.proceso}" value="${ejecucionList.proceso}_${ejecucionList.periodo}">
										</c:if>							
									</c:forEach>
									<!--END ITERATE--->
							    </c:when>
							    <c:otherwise>
							    	<tr style="min-height: 60px; height: 60px; text-align: center;">
							        	<td colspan="3"> Favor, solicitar revisión de los estados de procesos, 
							        	ya que en apariencia no estan en el orden correspondiente 
							        	<br>(iniciado, cargando, cargado, validando, validado, generando, generado)
							        	</td>
							        </tr>
							    </c:otherwise>
							</c:choose>					
						</tbody>
					</table>
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="concat" name="concat">
				</form>
				</div>	
					
			<br>
			<label>* Para actualizar los estados debe presionar "refrescar estados" en la pantalla principal</label>
			<br>			
			<label>* Los estados "proceso iniciado" no serán afectados por reproceso o validación.</label>
			<script>
				$(document).ready(function(){
					var buttons = {};
					buttons["Reprocesar"] = function() {reprocesar();$("#dialog_form_Ejecucion").dialog( "close" );};
					buttons["Revalidar"] = function() {revalidar();$("#dialog_form_Ejecucion").dialog( "close" );};      					
					buttons["Cerrar"] = function() {refrescar(4);$("#dialog_form_Ejecucion").dialog( "close" );};
					$("#dialog_form_Ejecucion").dialog({buttons:buttons});
   				});
			</script>
	    </c:otherwise>
	</c:choose>
	
	