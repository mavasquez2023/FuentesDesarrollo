<%@ include file="../layout/headerJsp.jsp"%>
<html>
<head>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<link type="text/css" href="<%=request.getContextPath()%>/css/simulacion.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/autoNumeric.js"></script>
	<title></title>
</head>
<body>
	<div id="contactoDiv">
		<p class="titulo">Solicitud de contacto</p>
		<form action="<%=request.getContextPath()%>/enviarCotizacion.do" method="post" class="form" id="formContact">
			<fieldset class="form-fieldset fieldset-med">
				<div class="padding-m">
					<h2>Ingrese datos de contacto</h2>
					<div class="hr"></div>
					
					<div class="field li">
						<label class="span2-8">Nombre: </label>						
						<input type="text" name="nombre" id="nombre" class="text" value="${resultado.nombreAfiliado}" disabled="disabled">
						<%-- <html:errors property="nombre" /> --%>
					</div>
					<div class="field li">
						<label class="span2-8">RUT</label>
						<input type="text" name="rut" id="rut" class="text" value="${resultado.rut}" disabled="disabled"/>
						<%-- <html:errors property="rut" /> --%>
					</div>
					<div class="field li">
						<label class="span2-8">T&eacute;lefono (*)</label>
						<input type="text" name="extension" id="extension" class="text-min">
						<span id="espacio-extension" class="espacio-min"><html:errors property="extension" />&nbsp;</span>
						<input type="text" name="fono" id="fono" class="text-med"/>
						<html:errors property="fono" />
						<label class="span2-8 left-clear"></label>
						<span class="ejemplo left">Ej: 2</span>
					</div>
									
					<div class="field li">
						<label class="span2-8">Celular (*)</label>
						<input type="text" name="preMovil" id="preMovil" class="text-min">
						<span id="espacio-preMovil" class="espacio-min"><html:errors property="preMovil" />&nbsp;</span>
						<input type="text" name="celular" id="celular" class="text-med"/>
						<html:errors property="celular" />	
						<label class="span2-8 left-clear"></label>
						<span class="ejemplo left">Ej: 9</span>						
					</div>
					
					<div class="field li">
						<label class="span2-8">E-mail (*)</label>
						<input type="text" name="email" class="text"/>
						<html:errors property="email" />
					</div>
					<div class="field li">
						<label class="span2-8">Calle (*)</label>
						<input type="text" name="calle" class="text"/>
						<html:errors property="calle" />
					</div>
					<div class="field li">
						<label class="span2-8">N° (*)</label>
						<input type="text" name="calleNro" id="calleNro" class="text"/>
						<html:errors property="calleNro" />
					</div>
					<div class="field li">
						<label class="span2-8">N° Depto</label>
						<input type="text" name="nroDpto" id="nroDpto" class="text" />
						<html:errors property="nroDpto" />
					</div>
					<div class="field li">
						<label class="span2-8">Comuna (*)</label>
						<select name=comunasList id="comunasList">
							<option value="">Seleccione comuna</option>
							<c:forEach items="${comunas}" var="comuna"><option value="${comuna.key};${comuna.value.idRegion}" >${comuna.value.nombreComuna}</option></c:forEach>
						</select>
						<input type="hidden" name="comuna" id="comuna">
						<html:errors property="comuna" />
					</div>
					<div class="field li">
						<label class="span2-8">Región</label>
						<select name="regiones" id="regiones" disabled="disabled">
							<option value=""></option>
							<c:forEach items="${regiones}" var="region"><option value="${region.key}">${region.value}</option></c:forEach>
						</select>
						<input type="hidden" id="region" name="region">
					</div>
					
					<!-- <div class="field li">
						<label class="span2-8">Solicitar contacto</label>
						<div class="radio-horiz">
							Sí
							<input type="radio" name="contacto" class="contacto" value="true" checked="checked">
							No
							<input type="radio" name="contacto" class="contacto" value="false"> 
						</div>	
					</div> -->
					<input type="hidden" value="true" name="contacto">
					
					
					<div class="espacio-small"></div>
					<div class="right">
						<input type="button" class="boton" value="<< Volver" onclick="history.back()"/>
						<input type="submit" class="boton" value="Confirmar" />
					</div>
					<div class="espacio-small"></div>
				</div>
			</fieldset>
		</form>
		<div class="padding-xs">
			<p class="li">(*) Campos requeridos</p>
		</div>
	</div>
	<div id="loading" style="position:absolute; top:60%; left:40%; display:none; z-index: auto">
    	<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
    </div>
	<script type="text/javascript">
		$(document).ready(function() {
			//Validaciones del formulario
			$("#formContact").validate({
				rules : {
					extension : {required: true, minlength: 1, min: 1},
					fono : {required: true, minlength: 7},
					preMovil: {required: true, minlength: 1, min: 1},
					celular : {required: true, minlength: 7},
					email : {
						required: true,
						email: true
					},
					calle: "required",
					calleNro: {required: true, maxlength: 5},
					nroDpto: {maxlength: 5},
					comunasList: "required",
					comuna : "required"
				},
				messages : {
					extension: "*",
					preMovil: "*",
					email: {
						email: "Ingrese email valido"
					}
				},
				errorPlacement: function(error, element) {
					
					if (element.attr("name") == "extension"){
						error.appendTo("#espacio-extension");
				        error.addClass("error-min");
					}else if(element.attr("name") == "preMovil"){
						error.appendTo("#espacio-preMovil");
				        error.addClass("error-min");
					}else{
						error.insertAfter( element ); 
					}
					
		         }
			}); //fin validate
			
			
			$(window).bind('beforeunload', function () {
                $('#loading').show();
            });
			
			
			jQuery(function($) {
				$('#celular, #fono').autoNumeric('init', {
					aSep : '',
					aDec : ',',
					mDec : '0',
					vMax: '9999999'
				});
							
				$('#extension').autoNumeric('init', {
					aSep : '',
					aDec : ',',
					mDec : '0',
					vMax: '99'
				});
				
				$('#preMovil').autoNumeric('init', {
					aSep : '',
					aDec : ',',
					mDec : '0',
					vMax: '9'
				});
				
				$('#calleNro, #nroDpto').autoNumeric('init', {
					aSep : '',
					aDec : ',',
					mDec : '0',
					vMax: '99999'
				});
				
			});
			
			$("#comunasList").change(function(){
				var str = $("#comunasList").val();
				/* if(str.length == 0){
					$("#regiones").val("");
				}else{ */
					var split = str.split(";");
					var idComuna = split[0];
					var idRegion = split[1];
					$("#regiones option[value="+idRegion+"]").attr("selected",true);
					$("#comuna").val(idComuna);
					$("#region").val(idRegion);
					$("#regiones").val(idRegion);
				//}
			});//fin de comunas list
			
		});//fin document ready
				
		/* jQuery(function($) {
			$('#telfijo').autoNumeric('init', {
				aSep : '',
				aDec : ',',
				mDec : '0',
				vMax: '99999999'
			});
		}); */
		
	</script>
</body>
</html>