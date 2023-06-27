<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/PermisosConvSucursal" styleId="formulario">
<nested:hidden property="rutEmpresa" styleId="rutEmpresa"><nested:write property="rutEmpresa"/></nested:hidden>
<nested:hidden property="idConvenio" styleId="idConvenio"><nested:write property="idConvenio"/></nested:hidden>
<nested:hidden property="idSinAcceso" styleId="idSinAcceso"><nested:write property="idSinAcceso"/></nested:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
	         	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
	          		<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		                <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                	<tr> 
		                     	<td>
			                        <table width="100%" border="0" cellpadding="0" cellspacing="1">
			                          	<tr valign="bottom">
			                            	<td height="30" bgcolor="#FFFFFF" class="titulo">
			                            		<strong>Selecci&oacute;n de Permisos por Sucursal</strong>
			                            	</td>
            			              	</tr>
			                        </table>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
							<tr> 
								<td height="4" colspan="6" bgcolor="#85b4be"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
							<tr>
								<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
									C&oacute;digo
								</td>
								<td width="40%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
									Nombre
								</td>
								<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas" colspan="4">
									Acceso
								</td>
							</tr>
							<nested:iterate id="fila" property="consulta" indexId="nFila">
								<c:choose>
									<c:when test="${nFila % 2 == 0}">
										<c:set var="estilo" value="textos_formularios" />
									</c:when>
									<c:otherwise>
										<c:set var="estilo" value="textos-formcolor2" />
									</c:otherwise>
								</c:choose>
								<tr>
									<td nowrap="nowrap" class="${estilo}" align="center">
										<nested:write property="idSucursal" />
									</td>
									<td nowrap="nowrap" class="${estilo}" align="left">
										<nested:write property="nombreSucursal" />
									</td>
									<logic:iterate id="nivelAcceso" name="PermisosConvSucuActionForm" property="nivelesAcceso">
										<td nowrap="nowrap" class="${estilo}" align="center">
											<nested:radio idName="nivelAcceso" property="idPermiso" value="idNivelAcceso" styleId="${fila.idSucursal}*${nivelAcceso.idNivelAcceso}">
												<bean:write name="nivelAcceso" property="nombre"/>
											</nested:radio>
										</td>
									</logic:iterate>
								</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td height="41" valign="top"><br />
		             	<html:button property="operacion" styleClass="btn3" value="Asignar" onclick="javascript:guardarDatos();window.close();"/>
		             	<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:window.close();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 

	cargarDatos();

	function cargarDatos() {
	
		var idSinAcceso = document.getElementById("idSinAcceso").value;
	    var inputs = document.getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) {
	    	if (inputs[i].type == "radio") {
	    		if (inputs[i].value == idSinAcceso)
	    			inputs[i].checked = true;
	    		else
	    			inputs[i].checked = false;
			}
		}

		var rutEmpresa = document.getElementById("rutEmpresa").value;
		var idConvenio = document.getElementById("idConvenio").value;
		var idHidden = "permSuc*" + rutEmpresa + "*" + idConvenio;
		var objHidden = window.opener.document.getElementById(idHidden);
		
		var idNivel;
		var objSelect;
		if (objHidden.value.indexOf("#") != -1) {
			aPermisos = objHidden.value.split("#");
			for (var i = 0; i < aPermisos.length; i++) {
				if (aPermisos[i] == "")
					continue;
				objSelect = document.getElementById(aPermisos[i]);
				objSelect.checked = true;
			}
		}
	}
	
	function guardarDatos() {
		var rutEmpresa = document.getElementById("rutEmpresa").value;
		var idConvenio = document.getElementById("idConvenio").value;
		var idHidden = "permSuc*" + rutEmpresa + "*" + idConvenio;
		var objHidden = window.opener.document.getElementById(idHidden);

		var idSinAcceso = document.getElementById("idSinAcceso").value;
	    var inputs = document.getElementsByTagName("input");
		objHidden.value = "";
	    for (var i = 0; i < inputs.length; i++) {
	    	if (inputs[i].type == "radio") {
	    		if ((inputs[i].checked) )//&& (inputs[i].value != idSinAcceso))
					objHidden.value = objHidden.value + inputs[i].id + "#";
			}
		}
	}
</script>
</body>
</html>