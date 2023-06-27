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
<html:form action="/EdicionCodigosFicha" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="codigosFicha" />
<html:hidden property="numCodigos" styleId="numCodigos"/>
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
	<td valign="top">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
          <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
          	<tr>
          		<td width="30%"><strong>Grupo de Convenios:</strong></td>
	            <td>
	            	<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:submit();">
	            		<nested:optionsCollection property="gruposConvenio" />
	            	</nested:select>
               	</td>
          	</tr>
          	<tr>          		
	            <td width="20%"><strong>Concepto:</strong></td>
	            <td width="80%">
	            	<nested:select property="opcTipoEdicion" styleClass="campos" onchange="javascript:submit();">
	            		<nested:optionsCollection property="tiposEdicion"/>
	            	</nested:select>
               	</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="2" bgcolor="#85b4be"></td>
         	</tr>
            </table>
            <c:set var="idEnt" value="" scope="request"/>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Edici&oacute;n de C&oacute;digos de Mapeo</strong></td>
            </tr>
       		</table>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
		                 	<td width="370" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Entidad</td>
		                 	<td width="150" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">C&oacute;digo *</td>
							<td width="70" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
	               		</tr>
               			<c:set var="primero">true</c:set>
	               		<nested:iterate id="fila" property="lista" indexId="nFila">
	               			<nested:notEqual property="idEntidad" value="${idEnt}">       			
		                  		</table>
		                  		</td>
		                  		</tr>
		               			<c:set var="idEnt"><nested:write property="idEntidad" /></c:set>
		               			<c:set var="nombreEntidad"><nested:write property="nombre" /></c:set>
		               			<logic:notEqual name="primero" value="true">
									<tr> 
					            		<td height="2" colspan="3" bgcolor="#85b4be">
					            		</td>
									</tr>
								</logic:notEqual>
		               			<c:set var="primero">false</c:set>
		               			<tr>
		               			<td colspan="3">		               			
		               			<div id="titulo${idEnt}" style="display:none"><nested:write property="nombre" /></div>
		               			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		               			<tbody id="<nested:write property="idEntidad" />Body">
	               			</nested:notEqual>
	               			<tr>
	                    		<td width="370" align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
	                    			&nbsp;<nested:write property="nombre" />
	                    			<div id="div${nFila}" style="display:none">
	                    				${nombreEntidad}
	                    			</div>
	                    		</td>
	                    		<td width="150" align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
	                    			<nested:hidden property="idCodigo" />
	                    			<nested:hidden property="idEntidad" />
	                    			<nested:text property="idCodigoNew" styleId="txt${nFila}" styleClass="campos" maxlength="13" onblur="javascript:soloAlfaNumMinusc(this, '.,abcdefghijklmnopqrstuvwxyz-_');" onkeyup="javascript:soloAlfaNumMinusc(this, '.,abcdefghijklmnopqrstuvwxyz-_');"/>
	                    		</td>
								<td width="70" class="textos_formularios" align="center">
									<nested:empty property="nombre">&nbsp;</nested:empty>
									<nested:notEmpty property="nombre">
									<div align="center">
										<a href="javascript:;" onclick="addCodigo('<nested:write property="idEntidad" />');">
											<img src="<c:url value="/img/ico_mas.gif" />" width="11" alt="Agregar C&oacute;digo" title="Agregar C&oacute;digo" height="11" border="0" />
										</a>
										<a href="javascript:;" onclick="delCodigo('<nested:write property="idEntidad" />');">
											<img id="img1" src="<c:url value="/img/ico_menos.gif" />" width="11" height="11" border="0" alt="Eliminar C&oacute;digo" title="Eliminar C&oacute;digo"/>
										</a>
									</div>
									</nested:notEmpty>
								</td>
	                  		</tr>
                  		</nested:iterate>
                  		</tbody>
			            </table>
			            </td>
			            </tr>
					</table>
					</td>
				</tr>
            </table>
            </td>
        </tr>
        <tr align="center">
         	<td valign="top"><br />
         		<html:submit property="operacion" value="Guardar" styleClass="btn4"/>
         		<html:cancel property="operacion" value="Cancelar" styleClass="btn4"/>
            </td>
		</tr>
	</table>
</html:form>
<script language="javaScript"> 
<!--
/*
function trim(str) 
{
	return str.replace(/^\s+(.*?)\s+$/,'$1');
}
*/
function addCodigo(idEntidad)
{
	var bodyTabla = document.getElementById(idEntidad + "Body");
	var fila = document.createElement("tr");
	fila.setAttribute("id", "tr-" + numCodigos);
	var celda = document.createElement("td");
	var numCodigos = new Number(document.getElementById("numCodigos").value);

	celda.setAttribute("style", "background-color: #F5FAF9;");
	celda.setAttribute("className", "textos_formularios");
	celda.setAttribute("width", "370");
	textNode = document.createTextNode("  ");
	celda.appendChild(textNode);
	fila.appendChild(celda);
	
	celda = document.createElement("td");
	celda.setAttribute("style", "background-color: #F5FAF9;");
	celda.setAttribute("className", "textos_formularios");
	celda.setAttribute("width", "150");
	textNode = document.createElement("input");
	textNode.setAttribute("type", "hidden");
	textNode.setAttribute("value", "");
	textNode.setAttribute("name", "lista[" + numCodigos + "].idCodigo");
	celda.appendChild(textNode);
	textNode = document.createElement("input");
	textNode.setAttribute("type", "hidden");
	textNode.setAttribute("value", idEntidad);
	textNode.setAttribute("name", "lista[" + numCodigos + "].idEntidad");
	celda.appendChild(textNode);
	textNode = document.createElement("input");
	textNode.setAttribute("type", "text");
	textNode.setAttribute("id", "txt" + numCodigos);
	textNode.setAttribute("className", "campos");
	textNode.setAttribute("class", "campos");
	textNode.setAttribute("maxLength", "13");
	textNode.setAttribute("name", "lista[" + numCodigos + "].idCodigoNew");
	textNode.setAttribute("onblur", "javascript:soloAlfanumerico(this, '.,');");
	textNode.setAttribute("onkeyup", "javascript:soloAlfanumerico(this, '.,');");
	celda.appendChild(textNode);

	//Agregado Turko
	var eldiv = document.createElement("div");
	eldiv.style.display = "none";
	eldiv.id = "div" + numCodigos;
	eldiv.appendChild(document.createTextNode(trim(document.getElementById("titulo" + idEntidad).firstChild.nodeValue)));
	celda.appendChild(eldiv);
	//

	fila.appendChild(celda);

	celda = document.createElement("td");
	celda.setAttribute("style", "background-color: #F5FAF9;");
	celda.setAttribute("className", "textos_formularios");
	textNode = document.createTextNode("  ");
	celda.appendChild(textNode);
	fila.appendChild(celda);

 	bodyTabla.appendChild(fila);
 	document.getElementById("numCodigos").value = numCodigos + 1;
}

function delCodigo(idEntidad)
{
	var bodyTabla = document.getElementById(idEntidad + "Body");
	var hijos = bodyTabla.getElementsByTagName("tr");
	if ((hijos.length) > 1)
	{
		var numCodigos = document.getElementById("numCodigos").value;
		bodyTabla.removeChild(hijos[hijos.length - 1]); 
	 	document.getElementById("numCodigos").value = numCodigos - 1;
	 } else
	 	alert("Cada entidad debe tener al menos un Código asociado. Por lo tanto el último no se puede eliminar.");
}

	function validaFormulario(f) 
	{
		var txts = document.getElementsByTagName("input");
		var sMsje = "";
		var tmp = new Array(txts.length);
		var tmpIds = new Array(txts.length);
		var num;
		var txt;
		for (var i = 0; i < txts.length; i++) 
		{
			txt = txts[i];
			if (txt.type == "text") 
			{
				/*
				if (!validaReq(txt)) 
				{
					num = txt.id.substr(3);
					sMsje = "* Campo " + trim(document.getElementById("div" + num).firstChild.nodeValue) + " requerido\n";
					txt.focus();
					break;
				}
				*/
				var flag = "false";
				for (j = 0; j < i; j++)
				{
					if (trim(txt.value) == tmp[j] && trim(txt.value) != "")
					{
						numtxt = txt.id.substr(3);
						numtmp = tmpIds[j];
						textotxt = trim(document.getElementById("div" + numtxt).firstChild.nodeValue);
						textotmp = trim(document.getElementById("div" + numtmp).firstChild.nodeValue);
						if (textotmp == textotxt) 
							sMsje = "* Campos de " + textotmp + " tienen el mismo valor\n";
						else 
							sMsje = "* Campos de " + textotmp + " y de " + textotxt + " contienen el mismo valor.\n";

						txt.focus();
						flag = "true";
						break;
					}
				}
				if (flag == "true")
					break;

				tmp[i] = trim(txt.value);
				tmpIds[i] = txt.id.substr(3);
			}
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function limpiaEspacios(cadena)
	{
		for(i = 0; i < cadena.length; )
		{
			if(cadena.charAt(i) == " ")
				cadena = cadena.substring(i+1, cadena.length);
			else
				break;
		}
		for(i = cadena.length-1; i >= 0; i = cadena.length-1)
		{
			if(cadena.charAt(i) == " ")
				cadena=cadena.substring(0,i);
			else
				break;
		}
		return cadena;
	}
// -->
</script>
</body>
</html:html>
