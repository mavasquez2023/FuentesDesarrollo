<%@ include file="layout/headerJsp.jsp"%>
		
		<c:choose>
		   	<c:when test="${keyOperacion==false}">
					<label>${msg}</label>	
					
					<form action="" name="form_Mentenedor_Usuario" id="form_Mentenedor_Usuario">
						<div id="loadGif" class="loadGif">
							<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
						</div>
		    			<input type="hidden" id="op" name="op">
		    			<input type="hidden" id="id_user" name="id_user" value="">
		    		</form>
		    		
		    		<script>
						$(document).ready(function(){							
							var buttons = {};
        					buttons["Cerrar"] = function() {        						 
        						$("#dialog_form_Usuario").dialog("close");
        						cargarListado_Usuarios();
        					};
        					
        					$("#dialog_form_Usuario").dialog({buttons:buttons});
        				});
					</script>
									
			</c:when>
		 <c:otherwise>	
		    <form action="" name="form_Mentenedor_Usuario" id="form_Mentenedor_Usuario">
		    	<input type="hidden" id="op" name="op">
		    	<input type="hidden" id="id_user" name="id_user" value="${userRegistro.id_user}">
			    <br>
		        <div class="visorForm">
					<div class="campoForm">
						<label class="lbl_numero" >( 1)</label>
						<label class="lbl_nombre" >Rut</label>
						<input type="text"  maxlength="11" class="txt_campo" id="rut_user" name="rut_user" value="${userRegistro.rut_user}">
						<label class="lbl_Error" id="rut_user_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 2)</label>
						<label class="lbl_nombre" >Nombre Usuario</label>
						<input type="text"  maxlength="200" class="txt_campo" id="nombre_user" name="nombre_user" value="${userRegistro.nombre_user}">
						<label class="lbl_Error" id="nombre_user_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 3)</label>
						<label class="lbl_nombre" >Apellido Paterno</label>
						<input type="text"  maxlength="100" class="txt_campo" id="ape_paterno" name="ape_paterno" value="${userRegistro.ape_paterno}">
						<label class="lbl_Error" id="ape_paterno_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 4)</label>
						<label class="lbl_nombre" >Apellido Materno</label>
						<input type="text" maxlength="100" class="txt_campo" id="ape_materno" name="ape_materno" value="${userRegistro.ape_materno}">
						<label class="lbl_Error" id="ape_materno_error"></label>
					</div>
					<div class="campoForm">
						<label class="lbl_numero" >( 5)</label>
						<label class="lbl_nombre" >E-Mail</label>
						<input type="text"  maxlength="200" class="txt_campo" id="email_user" name="email_user" value="${userRegistro.email_user}">
						<label class="lbl_Error" id="email_user_error"></label>
					</div>
				</div>
			</form>
		    </c:otherwise>
		</c:choose>
		
		
		