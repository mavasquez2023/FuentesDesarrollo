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
	<TITLE>Parametros</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body>
<html:form action="/ListaParametros" styleId="formulario">
<input type="hidden" id="accionInterna" name="accionInterna"/>
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
		<td valign="top" height="30" bgcolor="#FFFFFF" class="titulo">
			<strong>Mantenedor de Par&aacute;metros</strong>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					<td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Tipo Par&aacute;metro</td>
					<td width="180" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					<td width="210" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Valor</td>
					<td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('negocio', 'img1');">
								<img id="img1" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Contraer" title="Contraer" />
							</a>
						</div>
					</td>
					<td colspan="3" align="center" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>&nbsp;Negocio</strong>
					</td>
					<td  nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="<c:url value='/EdicionParametros.do?tipoParametro=NEGOCIO' />">
								<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
							</a>
						</div>
					</td>
				</tr>
				<tr id="negocio" style="display:none">
					<td colspan="5">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:notEmpty property="listaNegocio">
								<nested:iterate id="fila" property="listaNegocio" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<nested:notEqual property="id" value="0">
										<nested:define property="id" id="id" />
											<td class="${estilo}" width="20">
												&nbsp;
											</td>
				                    		<td colspan="2" valign="middle" nowrap="nowrap" class="${estilo}" width="270">
				                    			<nested:write property="descripcion" />&nbsp;
				                    		</td>
				                    		<td colspan="2" valign="middle" class="${estilo}" width="300">
				                    			<div style="overflow:hidden; width:300px; margin-left: 0px; " title="<nested:write property="valor" />" >
				                    				&nbsp;<nested:write property="valor" />
				                    			</div>
				                    		</td>
										</nested:notEqual>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
							<nested:empty property="listaNegocio">
								<tr>
									<td class=textos_formularios colspan="5">
										No hay Par&aacute;metros definidos para este &iacute;tem
									</td>
								</tr>
							</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('sistema', 'img2');">
								<img id="img2" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Contraer" title="Contraer" />
							</a>
						</div>
					</td>
					<td colspan="3" align="center" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>&nbsp;Sistema</strong>
					</td>
					<td  nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="<c:url value='/EdicionParametros.do?tipoParametro=SISTEMA' />">
									<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
							</a>
						</div>
					</td>
				</tr>
				<tr id="sistema" style="display:none">
					<td colspan="5">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:notEmpty property="listaSistema">
								<nested:iterate id="fila" property="listaSistema" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<nested:notEqual property="id" value="0">
										<nested:define property="id" id="id" />
											<td class="${estilo}" width="20">
												&nbsp;
											</td>
				                    		<td colspan="2" valign="middle" nowrap="nowrap" class="${estilo}" width="270">
				                    			<nested:write property="descripcion" />&nbsp;
				                    		</td>
				                    		<td colspan="2" valign="middle" class="${estilo}" width="300">
					                 			<div style="overflow:hidden; width:300px; margin-left: 0px; " title="<nested:write property="valor" />" >
					                    			&nbsp;<nested:write property="valor" />
					                    		</div>
				                    		</td>
										</nested:notEqual>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
							<nested:empty property="listaSistema">
								<tr>
									<td class=textos_formularios colspan="5">
										No hay Par&aacute;metros definidos para este &iacute;tem
									</td>
								</tr>
							</nested:empty>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table><br><br>
</html:form>
<script language="javaScript"> 
<!-- 
function swapAll(id, imgId) 
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
		img.alt = "Expandir";
		img.title = "Expandir";
	} else		
	{
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
		img.alt = "Contraer";
		img.title = "Contraer";
	}
}
// -->
</script>
</body>
</html:html>
