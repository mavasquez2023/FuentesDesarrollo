<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Actualiza tus datos</h1>
			<p class="texto" align="center">A continuación, puedes actualizar
				tus datos de contacto.</p>
			<br>
			<div class="centraTabla">
				<div class="msg">
					<c:if test="${not empty tipo}">
						<div class="${tipo}">
							<strong>Mensaje:&nbsp;</strong>${mensaje}
						</div>
					</c:if>
				</div>
				<form id="formulario" action="actualizaDatos.do?op=updateInfo"
					method="post">
					<table class="formH">
						<tr>
							<td colspan="2"><label for="email">E-mail</label><br> <input
								class="inputLargo" type="text" name="email"
								value="${datosDeudor.email}" size="60" maxlength="40"></td>
							<td><label for="recibirECVM">Recibir&nbsp;estado&nbsp;de&nbsp;cuenta&nbsp;vía&nbsp;email</label><br>
								<c:if test="${empty datosDeudor.envecta}">
									<input type="checkbox" value="S" name="recibirECVM">
								</c:if> <c:if test="${not empty datosDeudor.envecta}">
									<input type="checkbox" value="S" checked="checked"
										name="recibirECVM">
								</c:if></td>
						</tr>
						<tr>
							<td><label for="fono1">Fono 1</label><br> <input
								class="inputCorto fonosClass" type="text" name="fono1"
								id="fono1" value="${datosDeudor.fono1}" maxlength="10">
							</td>
							<td><label for="fono2">Fono 2</label><br> <input
								class="inputCorto fonosClass" type="text" name="fono2"
								id="fono2" value="${datosDeudor.fono2}" maxlength="10">
							</td>
							<td><label for="fono3">Fono 3</label><br> <input
								class="inputCorto fonosClass" type="text" name="fono3"
								id="fono3" value="${datosDeudor.fono3}" maxlength="10">
							</td>
						</tr>
						<tr>
							<td><label>Estado civil</label><br> <select
								name="estadoCivil" class="inputCorto" id="estadoCivil">
									<option value="">seleccione estado civil</option>
									<option value="S">Soltero/a</option>
									<option value="C">Casado/a</option>
									<option value="V">Viudo/a</option>
									<option value="D">Divorciado/a</option>
							</select>
							</td>
							<td colspan="2"><label>Dirección</label><br> <input
								class="inputLargo" type="text" name="direccion"
								value="${datosDeudor.direccion}" maxlength="60"></td>
						</tr>
						<tr>
							<td><label for="">Comuna</label><br> <select
								id="comunasList" class="inputCorto">
									<option id=":" value="">seleccione comuna</option>
									<c:forEach items="${comunas}" var="comuna">
										<option id="${comuna.key}" value="${comuna.value}">${comuna.value}</option>
									</c:forEach>
							</select> <input type="hidden" name="comuna" id="comuna"></td>
							<td><label for="">Provincia</label><br> <select
								id="provinciasList" disabled="disabled" class="inputCorto">
									<option id="" value="">primero seleccione comuna</option>
									<c:forEach items="${provincias}" var="provincia">
										<option id="${provincia.key}" value="${provincia.value}">${provincia.value}</option>
									</c:forEach>
							</select> <input type="hidden" name="provincia" id="provincia"></td>
							<td><label>Región</label><br> <select id="regionesList"
								disabled="disabled" class="inputCorto">
									<option id="" value="">primero seleccione comuna</option>
									<c:forEach items="${regiones}" var="region">
										<option id="${region.key}" value="${region.value}">${region.value}</option>
									</c:forEach>
							</select> <input type="hidden" name="region" id="region"></td>
						</tr>
						<tr>
							<td align="right" colspan="3"><input type="submit"
								class="boton" value="Guardar"></td>
						</tr>
					</table>
				</form>
				<div id="mensajesErrorAct" >
					<html:errors property="email" />
					<html:errors property="fono1" />
					<html:errors property="estadoCivil" />
					<html:errors property="direccion" />
					<html:errors property="comuna" />
				</div>
			</div>
			<div align="right"></div>
		</div>
		<br>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var com = '<c:out value="${datosDeudor.comuna}"/>';
						var ciu = '<c:out value="${datosDeudor.provincia}"/>';
						var reg = '<c:out value="${datosDeudor.region}"/>';
						var estCiv = '<c:out value="${datosDeudor.estcivil}"/>';

						$("#comunasList option:contains(" + com + ")").attr(
								"selected", true);
						$("#provincias option:contains(" + ciu + ")").attr(
								"selected", true);
						$("#regiones option:contains(" + reg + ")").attr(
								"selected", true);
						$("#estadoCivil option[value=" + estCiv + "]").attr(
								"selected", true);

						asd();

						function asd() {
							var str = $('#comunasList').find('option:selected')
									.attr('id');

							var cod = str.split(":");
							var idRegion = cod[0];
							var idProvincia = cod[1];

							$("#regionesList option[id=" + idRegion + "]")
									.attr("selected", true);
							$("#provinciasList option[id=" + idProvincia + "]")
									.attr("selected", true);
							$("#comuna").val($("#comunasList").val());
							$("#region").val($("#regionesList").val());
							$("#provincia").val($("#provinciasList").val());
						}

						$("#comunasList").change(function() {
							asd();
						});//fin de comunas list

						$("#formulario")
								.validate(
										{
											ignore : [],
											rules : {
												email : {
													required : true,
													email : true
												},
												direccion : 'required',
												estadoCivil : 'required',
												comuna : 'required',
												fono1 : {
													required : true,
													minlength : 7
												},
												fono2 : {
													minlength : 7
												},
												fono3 : {
													minlength : 7
												}
											},
											errorLabelContainer : '#mensajesErrorAct',
											messages : {
												fono1 : {
													required : "* El fono1 es requerido.",
													minlength : "El fono1 de tener mínimo 7 caracteres."
												},
												fono2 : "El fono2 de tener mínimo 7 caracteres.",
												fono3 : "El fono3 de tener mínimo 7 caracteres.",
												direccion : "* La dirección es requerida.",
												comuna : "* La comuna es requerida.",
												estadoCivil : "* Estado civil es requerido.",
												email : {
													required : "* El correo electrónico es requerido.",
													email : "* El correo electrónico ingresado no es valido."
												}
											}
										}); //fin jquery validate
						jQuery(function($) {
							$(".fonosClass").autoNumeric("init", {
								aSep : '',
								aDec : ',',
								mDec : '0',
								vMax : '9999999999'
							});
						});
						$("#formulario").submit(function() {
							$(".msg").empty();
						});

					});
</script>
</html>