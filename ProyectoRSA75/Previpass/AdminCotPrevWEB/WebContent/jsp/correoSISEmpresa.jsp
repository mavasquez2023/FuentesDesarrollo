<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Schema (http://www.Schema.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script language="javascript" type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
	<link href="<c:url value="css/adminAraucana.css" />" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
	// pone al día el Formulario 
	$(document).ready(function() {
		
		$(".btn3").click(function(){
			var valido = validaPeriodo();
			if(valido){
				$("#ok").hide();
				$("#msgNO").hide();
				$("#okArchi").hide();
				$('#_cmd').val('EnviarMail');			
				$("#formulario").submit();
				$("#okMail").show();
			}
		});
		$(".btn4").click(function(){
			var valido = validaPeriodo();
			if(valido){
				$("#ok").hide();
				$("#msgNO").hide();
				$("#okMail").hide();
				$('#_cmd').val('GeneraArchivo');			
				$("#formulario").submit();
				$("#okArchi").show();

			}
		});
		}
	);
	//Retorna verdadero si el string sFecha contiene una fecha válida en formato "yyyymm"
	function validaPeriodo() 
	{
		var sFecha = $("#periodo").val();

		var mes = sFecha.substring(0,2);
		var year = sFecha.substring(2,6);
		var dia = '01';
		if (mes < 1 || mes > 12){
				alert("El período ingresado es incorrecto");
				return false;
			}
		if (year < 1970 || year > 2050){
				alert("El período ingresado es incorrecto");
				return false;
			}
		var dateObj = new Date(year, mes - 1, dia);

		if ((dateObj.getDate() != dia) || (dateObj.getMonth() + 1 != mes) || (dateObj.getFullYear() != year)){
				alert("El período ingresado es incorrecto");
				return false;
			}
		return true;
	}
	
</script>
<body>
<html:form action="/correoSISEmpresa" styleId="formulario">
<logic:present name="mensajeOK"><div class="msgExito" id="ok"><bean:message key="message.correo.sisEmpresa.ok"/></div><br/></logic:present>
<logic:present name="mensajeNO"><div class="msgExito" id="msgNO"><bean:message key="message.correo.sisEmpresa.no"/></div><br/></logic:present>
<div class="msgExito" id="okArchi" style="display: none;"><br/><bean:message key="message.archivo"/></div>
<div class="msgExito" id="okMail" style="display: none;"><br/><bean:message key="message.archivo.correoSIS"/></div>
<html:hidden property="_cmd" styleId="_cmd" value="resultado" />
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>

			<td>
				<tiles:insert page="/pages/comunes/comboAfp.jsp"/>
			</td>
		</tr>
	</table>
	<table width="590" border="0" cellpadding="0" cellspacing="0" class="textos-formularios3">
		<tr> 
			<td width="25%"><strong><bean:message key="label.periodoMM"/></strong></td>
			<td colspan="2">	
		       <input name="periodo" id="periodo" maxlength="6" size="8"/>			
			</td>
		</tr>
	</table>
	<table width="590" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right" colspan="2">
			<html:button property="" styleClass="btn3" styleId="enviarMail" value="Enviar e-Mail"  ></html:button>&nbsp;&nbsp;&nbsp;
			<html:button property="" styleClass="btn4" styleId="Genera Archivo" value="Genera Archivo"></html:button>
			</td>
			
		</tr>
	</table>
</html:form>
</script>
</body>
</html:html>
