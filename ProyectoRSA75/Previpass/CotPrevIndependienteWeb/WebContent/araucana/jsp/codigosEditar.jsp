<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js"/> "></script>
<html:form action="/EdicionCodigosFicha" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="codigosFicha" />
<html:hidden name="subSubAccion" property="subSubAccion" styleId="subSubAccion" value="codigosEditar" />
<html:hidden property="numCodigos" styleId="numCodigos"/>
<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<html:errors />
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
							<td width="70"  align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
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
	                    			<%--<nested:text property="idCodigoNew" styleId="txt${nFila}" styleClass="campos" maxlength="13" onblur="javascript:soloAlfaNumMinusc(this, '.,abcdefghijklmnopqrstuvwxyz-_');" onkeyup="javascript:soloAlfaNumMinusc(this, '.,abcdefghijklmnopqrstuvwxyz-_');"/>--%>
	                    			<nested:text property="idCodigoNew" styleId="txt${nFila}" styleClass="campos" maxlength="4" onblur="javascript:soloAlfanumerico(this, '');" onkeyup="javascript:soloAlfanumerico(this, '');"/>
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
         		<html:submit property="operacion" value="${guardar}" styleClass="btn4"/>
         		<html:cancel property="operacion" value="${cancelar}" styleClass="btn4"/>
            </td>
		</tr>
	</table>
</html:form>
<input type="hidden" id="cantdemap" value="${nFila}" />

<script language="javaScript"> 
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
		tmp = new Array(txts.length);
		var count = 0;
		var num;
		var txt;
		for (var i = 0; i < txts.length; i++) 
		{
			txt = txts[i];
			if (txt.type == "text") 
			{
				/*
				if (!validaReq(txt)) //Retorna verdadero si el campo está presente.
				{
					num = txt.id.substr(3);
					sMsje = "* Campo " + trim(document.getElementById("div" + num).firstChild.nodeValue) + " requerido\n";
					txt.focus();
					break;
				}
				*/

				<logic:equal name="flgTramo" value="1">
				if (!validaReq(txt)) {
					numtxt = txt.id.substr(3);
					textotxt = trim(document.getElementById("div" + numtxt).firstChild.nodeValue);
					sMsje = "* Código de entidad " + textotxt + " está vacío.\n";
					break;
				}
				</logic:equal>

				for (j = 0; j < count; j++)
				{
					if (trim(txt.value) == trim(tmp[j].value) && trim(txt.value) != "")
					{
						numtxt = txt.id.substr(3);
						numtmp = tmp[j].id.substr(3);
						textotxt = trim(document.getElementById("div" + numtxt).firstChild.nodeValue);
						textotmp = trim(document.getElementById("div" + numtmp).firstChild.nodeValue);
						if (textotmp == textotxt) {
							sMsje = "* Campos de " + textotmp + " tienen el mismo valor\n";
						} else {
							sMsje = "* Campos de " + textotmp + " y de " + textotxt + " contienen el mismo valor.\n";//, filas " + (j + 1) + " y " + (count + 1) + "\n";
						}
						txt.focus();
						break;
					}
				}
				tmp[count] = txt;
				count++;
			}
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function foco()
	{
		val = document.getElementById('cantdemap').value;	
		if(document.getElementById('txt0') && val > 0)
			document.getElementById('txt0').focus();
	}
	foco();
</script>