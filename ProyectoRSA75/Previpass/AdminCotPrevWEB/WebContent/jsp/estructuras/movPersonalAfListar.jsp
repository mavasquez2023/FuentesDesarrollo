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
<html:form action="/MovPersonalAfListar" styleId="formulario">

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
		<td valign="top" colspan="2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
						</table>
					</td>
				</tr>
	<tr>
		<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mantenedor tipo Movimiento Personal Afiliación Voluntaria</strong></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100" class="textos_formularios">
			<strong>Estructura:</strong>
		</td>	
		<td width="300" class="textos_formularios">
			<select id="estructura" name="estructura" class="campos" onchange="cambiaEstructura();">						
      	       		<option value="MP">Tipo Movimiento Personal</option>
      	       		<option selected="selected" value="MPAF">Tipo Movimiento Personal Afiliación Voluntaria</option>
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
		<td colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="300" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Descripci&oacute;n</td>
					<td width="100" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha inicio</td>
					<td width="100" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fecha t&eacute;rmino</td>
					<td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
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
							<td class="${estilo}"  width="300">
								&nbsp;<nested:write property="nombre" /><input type="hidden" value="<nested:write property="nombre" />" id="nom_${id}"/>
							</td>
							<td class="${estilo}" width="100">
								&nbsp;<nested:notEmpty property="fechaInicioObligatoria">
									<nested:equal property="fechaInicioObligatoria" value="0">
										Sin Fecha
									</nested:equal>
									<nested:equal property="fechaInicioObligatoria" value="1">
										Con Fecha
									</nested:equal>
								</nested:notEmpty>
							</td>
							<td class="${estilo}" width="100">
								&nbsp;<nested:notEmpty property="fechaTerminoObligatoria">
									<nested:equal property="fechaTerminoObligatoria" value="0">
										Sin Fecha
									</nested:equal>
									<nested:equal property="fechaTerminoObligatoria" value="1">
										Con Fecha
									</nested:equal>
								</nested:notEmpty>
							</td>
							<td width="100" class="${estilo}" align="center">
								<div align="center">
									<a href="<c:url value='/MovPersonalAfEditar.do?id=${id}&tipoEdicion=ACTUALIZA' />">
										<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
									</a>
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
	formu.action = 'MovPersonalAfEditar.do?tipoEdicion=NUEVO&id=-1';
	formu.submit();
}										
function editTipo(id)
{
	formu = document.getElementById("formulario");	
	formu.action = 'MovPersonalAfEditar.do?id='+id;
	formu.submit();
}	
function delTipo(nombre,id)
{
	formu = document.getElementById("formulario");	
	formu.action = 'MovPersonalAfEditar.do?id='+id+'&accionInterna=DEL_TIPO';	
	formu.submit();
	}
function delConfirmar(url,desc)
{
	ms=document.getElementById('nom_'+desc);
	if (confirm("¿Está seguro de que desea eliminar el tipo de movimiento personal \n "+ms.value+" ?")){
		document.location = url;
	} 
} 
function cambiaEstructura()
{
	formu = document.getElementById("formulario");	
	for(a=0; a<formu.estructura.length; a++){
			if(formu.estructura[a].selected == true){
				if(formu.estructura[a].value == "MP"){
					formu.action = 'MovPersonalListar.do?limpiaPath=';
				}
				if(formu.estructura[a].value == "MPAF"){
					formu.action = 'MovPersonalAfListar.do?limpiaPath=';
				}
				if(formu.estructura[a].value == "ASIG"){
					formu.action = 'AsigFamiliarListar.do?limpiaPath=';
				}	
			}
		}
	formu.submit();		
}
// -->
</script>
</body>
</html:html>
