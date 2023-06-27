<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/comun/taglibs.jsp" %>
<script language="javascript" type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script src="<c:url value='/js/comun.js'/>"></script>
<link href="<c:url value="css/adminAraucana.css" />" rel="stylesheet" type="text/css">
<script language="javascript">
	// pone al día el Formulario 
	$(document).ready(function() {
		
		$(".btn3").click(function(){
			var valido = validaPeriodo();
			if(valido){
				$("#form").submit();
				//setTimeout('hideLoading();',1000);
				$("#noOk").hide();
				$("#ok").show();
			}
		});
		}
	);
	//Retorna verdadero si el string sFecha contiene una fecha válida en formato "yyyymm"
	function validaPeriodo() 
	{
		var sFecha = $("#periodo").val();

		var year = sFecha.substring(0,4);
		var mes = sFecha.substring(4,6);
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
<logic:present name="mensaje"><div class="msgError" id="noOk"><bean:message key="message.noArchivo"/></div><br/></logic:present>
<logic:present name="mensajeOK"><div class="msgExito" id="ok"><bean:message key="message.archivo.carga"/></div><br/></logic:present>
<div class="msgExito" id="ok" style="display: none;"><bean:message key="message.archivo.carga"/><br/></div>
<br>
<html:form action="/ValidateArchivoContigencia" styleId="form">
<a class="titulo">Contingencia AFP </a>
<br></br>
<tiles:insert page="/pages/comunes/comboAfp.jsp"/>
<tiles:insert page="/pages/comunes/periodo.jsp"/>
<html:hidden property="_cmd" value="resultado" />
<table width="590" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	<tr> 
		<td colspan="3" class="botonera" align="center">
			<html:button property="" styleClass="btn3"><bean:message key="button.generar"/></html:button>		
		</td>
	</tr>
</table>
</html:form>