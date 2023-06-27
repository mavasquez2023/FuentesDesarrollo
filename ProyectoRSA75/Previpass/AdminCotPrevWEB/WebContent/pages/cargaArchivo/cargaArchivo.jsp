<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="poneFoco('codigo')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<script language="javascript" type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script language="javascript">

	// pone al día el Formulario 
	$(document).ready(function() {
		
		$(".btn3").click(function(){
			var afp = $('#codigo option:selected').html();
			if(confirm('¿Desea cargar el archivo para la afp '+ $.trim( afp )+'?')){
				$("#form").submit();
			}			
		});
		}
	);
	
</script>
<logic:present name="mensaje"><div class="msgExito" id="noOk"><bean:message key="message.archivo.carga"/></div><br/></logic:present>
<logic:present name="mensaje.error"><div class="msgError" id="noOk"><bean:message key="message.archivo.carga.error"/></div><br/></logic:present>
<logic:present name="error"><div class="msgError"><bean:message key="message.archivo.procesa.error"/></div><br/></logic:present>
<html:form action="/ValidateCargaArchivo" method="post" enctype="multipart/form-data" styleId="form">
	<tiles:insert page="/pages/comunes/comboAfp.jsp"/>
	<html:hidden property="_cmd" value="resultado" />
	<br/>
	<table width="590" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	<tr> 
		<td width="25%"><strong><bean:message key="label.archivo"/></strong></td>
		<td colspan="2">	
			<html:file property="fileContingencia"/>			
		</td>
	</tr>
	</table>
	<br>
	<table width="590" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		<tr> 
			<td colspan="3" class="botonera" align="center">
				<html:button property="" styleClass="btn3"><bean:message key="button.cargar"/></html:button>		
			</td>
		</tr>
	</table>
</html:form>