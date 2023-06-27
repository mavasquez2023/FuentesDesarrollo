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
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Entidades</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/MovPersonalListar" styleId="formulario">

<input type="hidden" id="accionInterna" name="accionInterna"/>

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="2">
			<html:errors/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
		<td  colspan="2" valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Tipo Movimiento Personal</strong></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100" class="textos_formularios">
			<strong>Estructura:</strong>
		</td>	
		<td width="150" class="textos_formularios">
			<select id="estructura" class="campos" name="estructura" onchange="cambiaEstructura();">						
      	       		<option value="MP" selected>Tipo Movimiento Personal</option>
      	       		<option value="MPAF">Tipo Movimiento Personal Afiliación Voluntaria</option>
      	       		<option value="ASIG">Tipo Asignaci&oacute;n Familiar</option>
			</select>			
		</td>
	</tr>
	<tr>
		<td  colspan="2">
			&nbsp;
		</td>
	</tr>
					<tr>
				<td align="right" colspan="2">
					<!--  <html:button property="operacion"  styleClass="btn3" value="Crear Estructura" onclick="javascript:addTipo();"/>  -->
				</td>
				</tr>
	
	<tr>
		<td colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="300" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Descripci&oacute;n</td>
					<td width="160" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha</td>
					<td width="100" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<nested:notEmpty property="lista">
					<nested:iterate id="fila" property="lista" indexId="nFila">
					<c:choose>
   			    		<c:when test="${nFila % 2 == 0}">
   			    			<c:set var="estilo" value="textos-formcolor2"/>
   		    			</c:when>
						<c:otherwise>
							<c:set var="estilo" value="textos_formularios"/>
						</c:otherwise>
					</c:choose>
					<nested:define id="id" property="id" />
						<tr>
							<td class="${estilo}" width="300">
								&nbsp;<nested:write property="nombre" />
							</td>
							<td class="${estilo}" width="160">
								&nbsp;
								<nested:notEmpty property="fechaTerminoObligatoria">
									<nested:equal property="fechaTerminoObligatoria" value="0">
										Sin Fecha de T&eacute;rmino
									</nested:equal>
									<nested:equal property="fechaTerminoObligatoria" value="1">
										Con Fecha de T&eacute;rmino
									</nested:equal>
								</nested:notEmpty>
					
								<nested:notEmpty property="fechaInicoObligatoria">
									<nested:equal property="fechaInicoObligatoria" value="0">
										&nbsp;/&nbsp;Sin Fecha de Inicio
									</nested:equal>
									<nested:equal property="fechaInicoObligatoria" value="1">
										&nbsp;/&nbsp;Con Fecha de Inicio
									</nested:equal>
								</nested:notEmpty>
							</td>
							<td width="100" class="${estilo}" align="center">
								<div align="center">
									<a href="<c:url value='/MovPersonalEditar.do?id=${id}&tipoEdicion=ACTUALIZA' />">
										<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
									</a>
								 
								<!-- 	<a href="javascript:delConfirmar('<c:url value='/EdicionEstructuraMovPersonal.do?id=${id}&accionInterna=DEL_TIPO' />');">
										<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="14" alt="Eliminar" title="Eliminar" height="13" border="0" />
									</a> -->
								
								</div>
							</td>
						</tr>
					</nested:iterate>
				</nested:notEmpty>
				<nested:empty property="lista">
					<tr>
						<td class=textos_formularios colspan="8">
							No hay c&oacute;digos definidos para este &iacute;tem
						</td>
					</tr>
				</nested:empty>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
<!--
function addTipo()
{
	formu = document.getElementById("formulario");	
	formu.action = 'MovPersonalEditar.do?tipoEdicion=NUEVO&id=-1';
	formu.submit();
}										
function editTipo(id)
{
	formu = document.getElementById("formulario");	
	formu.action = 'MovPersonalEditar.do?id='+id;
	formu.submit();
}	
function delTipo(nombre,id)
{
	formu = document.getElementById("formulario");	
	formu.action = 'MovPersonalEditar.do?id='+id+'&accionInterna=DEL_TIPO';	
	formu.submit();
	}
function delConfirmar(url)
{
	if (confirm("¿Está seguro de que desea eliminar el tipo de movimiento personal?")){
		document.location = url;
	} 
} 
function cambiaEstructura()
{
	formu = document.getElementById("formulario");	
	for(a=0; a<formu.estructura.length; a++){
			if(formu.estructura[a].selected == true){
				if(formu.estructura[a].value == "MP")
					formu.action = 'MovPersonalListar.do?limpiaPath=';
				if(formu.estructura[a].value == "MPAF")
					formu.action = 'MovPersonalAfListar.do?limpiaPath=';
				if(formu.estructura[a].value == "ASIG")
					formu.action = 'AsigFamiliarListar.do?limpiaPath=';
			}
		}
	formu.submit();		
}
// -->
</script>
</body>
</html:html>

