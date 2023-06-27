<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!-- 
<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
 -->

<html:html>
<head >
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="foco();">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/EditarCalendario" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors/>
		</td>
	</tr>
	<tr>
		<td>
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
	<td>
               <table width="100%" border="0" cellpadding="0" cellspacing="1">
                 	<tr valign="bottom">
                   	<td height="30" bgcolor="#FFFFFF" class="titulo">
                   		<strong>
                    			Edici&oacute;n de Calendario
                   		</strong>
                   	</td>
                 	</tr>
               </table>
    </td>
    </tr>
  	<tr> 
  		<tr> 
    	<td align="left" valign="top">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
            		<tr class="subtitulos_tablas">
               		<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Mes</td>
	               	<td width="18%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha 1</td>
	               	<td width="18%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha 2</td>
	               	<td width="18%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha 3</td>
	               	<td width="34%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha Cierre</td>
              		</tr>
              		<c:set var="contador" value="0"></c:set>
              		<logic:notEmpty property="consulta" name="EdicionCalendarioActionForm">
						<logic:iterate id="filaConsulta" property="consulta" indexId="nFila" name="EdicionCalendarioActionForm">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
		               		<tr>
		                   		<td class="textos_formularios">
		                   			<c:out value="${filaConsulta.mes}"></c:out>
		                   			<input type="hidden" name="mes" id="mes_${nFila}" value="${filaConsulta.mes}"/>
		                   			<input type="hidden" name="idCal" id="idCal_${nFila}" value="${filaConsulta.idCal}"/>
		                   		</td>
		                   		<td class="textos_formularios">
		                   			<input type="text" name="fecha1" class="campos" id="fecha1_${nFila}" value="${filaConsulta.fecha1}" maxlength="10" size="12" onblur="javascript:soloFechaG(this);" onkeyup="javascript:soloFechaG(this);">
		                   		</td>
		                   		<td class="textos_formularios">
		                   			<input type="text" name="fecha2" class="campos" id="fecha2_${nFila}" value="${filaConsulta.fecha2}"maxlength="10" size="12" onblur="javascript:soloFechaG(this);" onkeyup="javascript:soloFechaG(this);">
		                   		</td>
		                   		<td class="textos_formularios">
		                   			<input type="text" name="fecha3" class="campos" id="fecha3_${nFila}" value="${filaConsulta.fecha3}"maxlength="10" size="12" onblur="javascript:soloFechaG(this);" onkeyup="javascript:soloFechaG(this);">
		                   		</td>
		                   		<td class="textos_formularios">
		                   			<input type="text" name="fecha4" class="campos" id="fecha4_${nFila}" value="${filaConsulta.fecha4}"maxlength="10" size="12" onblur="javascript:soloFechaG(this);" onkeyup="javascript:soloFechaG(this);">&nbsp;&nbsp;
		                   			<input type="text" name="hora" class="campos" id="hora_${nFila}" value="${filaConsulta.hora}"maxlength="2" size="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);">&nbsp;<b>:</b> 
		                   			<input type="text" name="min" class="campos" id="min_${nFila}" value="${filaConsulta.min}"maxlength="2" size="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);">&nbsp;hh:mm
		                   		</td>
		               		</tr>
		               		<c:set var="contador">${nFila+1}</c:set>
	             		</logic:iterate>
	             </logic:notEmpty>
           		</table>
      </td>
    </tr>
    <tr align="right">
             <td height="41" valign="top"><br />
             	<html:button property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:guardar();"/>
             </td>
           </tr>
  </table>
  <input type="hidden" name="largoDatos" id="largoDatos" value="${nFila}" />
</html:form>
<script language="javaScript">
<!-- 
	function guardar() 
	{ 
		frm = document.forms['EdicionCalendarioActionForm'];
		if(validaCampos(frm))
		{
			frm.action="EditarCalendario.do?operacion=Guardar";
			frm.submit();	
		}
	}

	function validaCampos(frm) 
	{
		var sMsje = "";
		var elementos = <c:out value="${contador}"></c:out>;
		
		for(i = 0; i < elementos; i++)
		{
			var f1 = document.getElementById('fecha1_'+i).value;
			var f2 = document.getElementById('fecha2_'+i).value;
			var f3 = document.getElementById('fecha3_'+i).value;
			var f4 = document.getElementById('fecha4_'+i).value;
			var mes = (document.getElementById('mes_'+i).value).replace(/^\s*|\s*$/g,"")
			var hora = document.getElementById('hora_'+i).value;
			var min = document.getElementById('min_'+i).value;
			if(!validaFechaG(f1))
				sMsje += "* La fecha 1 : " + f1 + " del mes " + mes +" no tiene un formato válido\n";
			if(!validaFechaG(f2))
				sMsje += "* La fecha 2 : " + f2 + " del mes " + mes +" no tiene un formato válido\n";
			if(!validaFechaG(f3))
				sMsje += "* La fecha 3 : " + f3 + " del mes " + mes +" no tiene un formato válido\n";
			if(!validaFechaG(f4))
				sMsje += "* La fecha cierre : " + f4 + " del mes " + mes +" no tiene un formato válido\n";
			if(hora < 0 || hora > 23)
				sMsje += "* La hora de cierre : " + hora + " del mes " + mes +" no es válida\n";
			if(min < 0 || min > 59)
				sMsje += "* El minuto de cierre : " + min + " del mes " + mes +" no es válido\n";
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}	

	function validaFechaG(sFecha) 
	{
		var regex = /^\d{2}\-\d{2}\-\d{4}$/
		if (!regex.test(sFecha))
			return false;

		var dia  = sFecha.split("-")[0];
		var mes  = sFecha.split("-")[1];
		var year = sFecha.split("-")[2];
		var anno = new Number(year);
		if (dia < 1 || dia > 31)
			return false;
		if (mes < 1 || mes > 12)
			return false;
		if (year < 1970 || year > 2050)
			return false;

		var dateObj = new Date(year, mes - 1, dia);

		if ((dateObj.getDate() != dia) || (dateObj.getMonth() + 1 != mes) || (dateObj.getFullYear() != year))
			return false;
		return true;
	}

	function foco()
	{	
		dt = document.getElementById('largoDatos').value;	
		if(dt != "")
			document.getElementById('fecha1_0').focus();
	}
// End --> 
</script>
</body>
</html:html>
