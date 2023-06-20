<%@ include file="layout/headerJsp.jsp"%>
   	<input type="hidden" id="op" name="op">	
    	<div class="datagrid">
    	<form name="form_descarga" id="form_descarga" action="descargar.do" >
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
					    <c:when test="${not empty listaDescarga}">
				 			<!--START ITERATE--->	
				 			<c:forEach items="${listaDescarga}" var="ld">				 			
				 				<tr style="min-height: 30px; height: 30px"> 			
									<td>${ld.periodo}</td>
									<td>${ld.proceso}</td>
									<td>${ld.msgEscritura}</td>
								</tr>
								<br>
				 			</c:forEach>
							<!--END ITERATE--->					
						</c:when>
					    <c:otherwise>
					    	<tr style="min-height: 60px; height: 60px; text-align: center;">
					        	<td colspan="3"> no se ha podido generar la descarga de los archivos generados.
					        	<br>Favor de solicitar revision de proceso "generación" 
					        	</td>
					        </tr>
					    </c:otherwise>
					</c:choose>	
				</tbody>
			</table>
		<input type="hidden" id="op" name="op">
		<input type="hidden" id="nombreZip" name="nombreZip" value="${nombreZip}">
		<input type="hidden" id="rutaOrigenZip" name="rutaOrigenZip" value="${rutaOrigenZip}">
		
		</form>
		</div>	
		<div id="loadMenu" class="loadMenu">
			<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
		</div>		
	<br>	
	
	<label>* Se descargar&aacute; un archivo Zip que contiene todos los documentos con estado "Generado"</label>
	<br>
	<div id="errorDescarga" style="text-align: right;">
		
	</div>
	<script>
		$(document).ready(function(){
			
			var buttons = {};
			if($("#form_descarga").find("input[name='nombreZip']").val()=="ZIP"){
				//alert("Los archivos solicitados no se pueden descargar.");
				 $("#errorDescarga").append("<label class='loadEtiqueta'> Los archivos solicitados no se puede descargar</label>");
			}else{
				
				buttons["Descargar Todo"] = function() {descargar();$("#dialog_form_Descarga").dialog("close");};
			}						      					
			buttons["Cancelar"] = function() {$("#dialog_form_Descarga").dialog( "close" );};
			$("#dialog_form_Descarga").dialog({buttons:buttons});
 		});
	</script>
	
	
	