<%@ include file="layout/headerJsp.jsp"%>
	<div id="dialogoFormulario" title="Atención" style="display: none;">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin: 0 7px 20px 0;"></span>Si elimina este ítem, se eliminaran todos los sub-ítems.
¿Realmente desea continuar?
			</p>
		</div>
		<c:choose>
		   	<c:when test="${KeyOperacion==false}">
					<label>${msg}</label>
					<form action="" name="form_Mentenedor_Menu" id="form_Mentenedor_Menu">
		    			<input type="hidden" id="op" name="op">
		    			<input type="hidden" id="codMenu" name="codMenu" value="">
		    		</form>
		    		
		    		<script>
						$(document).ready(function(){							
							var buttons = {};
        					buttons["Cerrar"] = function() {        						 
        						$("#dialog_form_Menu").dialog("close");
        						cargarListado_Menu();
        					};
        					
        					$("#dialog_form_Menu").dialog({buttons:buttons});
        				});
					</script>
		    							
			</c:when>
		 <c:otherwise>	
		    <form action="" name="form_Mentenedor_Menu" id="form_Mentenedor_Menu">
		    	<input type="hidden" id="op" name="op">
		    	<input type="hidden" id="codMenu" name="codMenu" value="${menuRegistro.codMenu}">
			    <br>
		        <div class="visorForm">
					<div class="campoForm">
						<label class="lbl_numero" >(1)</label>
						<label class="lbl_nombre" >Etiqueta</label>
						<input type="text"  maxlength="100" class="txt_campo" id="etiqueta" name="etiqueta" 
							value="${menuRegistro.etiqueta}" />
						<label class="lbl_Error" id="etiqueta_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(2)</label>
						<label class="lbl_nombre" >URL(enlace)</label>
						<input type="text"  maxlength="250" class="txt_campo" id="enlace" name="enlace" 
							value="${menuRegistro.enlace}">
						<label class="lbl_Error" id="enlace_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(3)</label>
						<label class="lbl_nombre" >Nodo Padre</label>
						<input type="text"  maxlength="3" class="txt_campo" id="nodoPadre" name="nodoPadre" 
							value="${menuRegistro.nodoPadre}" />
						<label class="lbl_help">0 corresponde al nodo raíz</label>
						<label class="lbl_Error" id="nodoPadre_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(4)</label>
						<label class="lbl_nombre" >Nivel</label>
						<input type="text" maxlength="3" class="txt_campo" id="nivel" name="nivel" 
							value="${menuRegistro.nivel}">
						<label class="lbl_Error" id="nivel_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(5)</label>
						<label class="lbl_nombre" >Hoja</label>
						<c:choose>
							<c:when test="${empty menuRegistro.flgHoja}">
								<select id="flgHoja" name="flgHoja" class="txt_campo">
									<option value="0">NO</option>
									<option value="1">SI</option>
								</select>
							</c:when>
							<c:otherwise>
								<select id="flgHoja" name="flgHoja" class="txt_campo">
									<c:if test="${menuRegistro.flgHoja=='0'}">
										<option value="0">NO</option>
										<option value="1">SI</option>
									</c:if>
									<c:if test="${menuRegistro.flgHoja=='1'}">
										<option value="1">SI</option>
										<option value="0">NO</option>
									</c:if>
								</select>
							</c:otherwise>
						</c:choose>
						<!-- <label class="lbl_help">1: SI / 0: NO</label> -->
						<label class="lbl_Error" id="flgHoja_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(6)</label>
						<label class="lbl_nombre" >Tiene seguridad</label>
						<c:choose>
							<c:when test="${empty menuRegistro.seguridad}">
								<select id="seguridad" name="seguridad" class="txt_campo">
									<option value="0">NO</option>
									<option value="1">SI</option>
								</select>
							</c:when>
							<c:otherwise>
								<select id="seguridad" name="seguridad" class="txt_campo">
									<c:if test="${menuRegistro.seguridad=='0'}">
										<option value="0">NO</option>
										<option value="1">SI</option>
									</c:if>
									<c:if test="${menuRegistro.seguridad=='1'}">
										<option value="1">SI</option>
										<option value="0">NO</option>
									</c:if>
								</select>
							</c:otherwise>
						</c:choose>
					<!-- 	<label class="lbl_help">1: SI / 0: NO</label> -->
						<label class="lbl_Error" id="seguridad_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(7)</label>
						<label class="lbl_nombre" >Link interno</label>
						<c:choose>
							<c:when test="${empty menuRegistro.linkInterno}">
								<select id="linkInterno" name="linkInterno" class="txt_campo">
									<option value="0">NO</option>
									<option value="1">SI</option>
								</select>
							</c:when>
							<c:otherwise>
								<select id="linkInterno" name="linkInterno" class="txt_campo">
									<c:if test="${menuRegistro.linkInterno=='0'}">
										<option value="0" selected>NO</option>
										<option value="1">SI</option>
									</c:if>
									<c:if test="${menuRegistro.linkInterno=='1'}"> 
										<option value="0">NO</option>
										<option value="1" selected>SI</option>
									</c:if>
								</select>
							</c:otherwise>
						</c:choose>
						<!-- <label class="lbl_help">1: SI / 0: NO</label> -->
						<label class="lbl_Error" id="linkInterno_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >(8)</label>
						<label class="lbl_nombre" >Descripci&oacute;n</label>
						<input type="text"  maxlength="200" class="txt_campo" id="descripcion" name="descripcion" 
							value="${menuRegistro.descripcion}">
						<label class="lbl_Error" id="descripcion_error"></label>
					</div>
				</div>
			</form>
		    </c:otherwise>
		</c:choose>
		
		
		