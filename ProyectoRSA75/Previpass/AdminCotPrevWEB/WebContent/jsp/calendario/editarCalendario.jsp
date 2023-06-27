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
<body>
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
	               	<td width="88%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Información</td>
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
		                   			<input type="text" name="informacion" class="campos" value="${filaConsulta.informacion}" size="100">
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
		frm.action="EditarCalendario.do?operacion=Guardar";
			frm.submit();	
	}
// End --> 
</script>
</body>
</html:html>
