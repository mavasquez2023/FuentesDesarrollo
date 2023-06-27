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
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
<!--
	var bCancel = false;		
//-->
</script>
</head>
<body>
<html:form action="/PermisosRolLector" styleId="formulario">

<input type="hidden" name="largoFila" id="largoFila" value="<nested:write property="largoFila" />" />
<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="<nested:write property="rutEmpresa" />" />
<input type="hidden" name="idGrupoConvenio" id="idGrupoConvenio" value="<nested:write property="idGrupoConvenio" />" />
<input type="hidden" name="idConvenio" id="idConvenio" value="<nested:write property="idConvenio" />" />
<input type="hidden" name="idLectorEmpresa" id="idLectorEmpresa" value="<nested:write property="idLectorEmpresa" />" />

<br />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
	         	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
	          		<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">	          			
			    		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
					       	<tr> 
					          	<td width="30%"><strong>RUT Independiente:</strong></td>
					          	<td width="30%"><nested:write property="rutEmpresaFmt" /></td>
					            <td><strong>Nombre:</strong></td>
					          	<td><nested:write property="nombreEmpresa" /></td>
					       	</tr>
					      	<tr> 
					         	<td><strong>Convenio:</strong></td>
					         	<td colspan="3"><nested:write property="idConvenio" /></td>
					      	</tr>
					      	<tr> 
					        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
					     	</tr>
					    </table>
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
								<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
									C&oacute;digo
								</td>
								<td width="55%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
									Descripci&oacute;n
								</td>
								<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
									<input type="checkbox" id="todasSucCheck" name="todasSucCheck" onclick="javascript:cambiaCheck()"> Asignar
								</td>
							</tr>
							<nested:notEmpty property="consulta">
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
										<td nowrap="nowrap" class="${estilo}" align="left">
											<nested:write property="codigo" />
										</td>									
										<td nowrap="nowrap" class="${estilo}" align="left">
											<nested:write property="descripcion" />
										</td>								
										<td nowrap="nowrap" class="${estilo}">
											<div align="center">
												<input type="checkbox" id="check_${nFila}" name="check_${nFila}" value="<nested:write property="codigo" />">
												<input type="hidden" name="value_${nFila}" id="value_${nFila}" value="<nested:write property="codigo" />">												
											</div>
										</td>
									</tr>
								</nested:iterate>
							</nested:notEmpty>			
						</table>
					</td>
				</tr>
				<tr align="center">
					<td height="41" valign="top"><br />
						<nested:notEmpty property="consulta">
			             	<html:button property="operacion" styleClass="btn3" value="Asignar" onclick="javascript:guardarDatos();window.close();"/>
			             </nested:notEmpty>
		             	<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:window.close();"/>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
<!--
	function guardarDatos()
	{//idSuc-${empresa.idEmpresa}-${perm.idConvenio}		
		var objHidden = window.opener.document.getElementById("idSuc-${PermisosRolLectorActionForm.rutEmpresa}-${PermisosRolLectorActionForm.idConvenio}");
		var valor = ""; //objHidden.value;

		var inputs = document.getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) 
	    {
	    	if (inputs[i].type == "checkbox" && inputs[i].checked == true && inputs[i].name.lastIndexOf("todasSucCheck") == -1)
				valor += inputs[i].value + "#";
		}
		objHidden.value = valor;
	}


	cargarDatos();

	function cargarDatos() 
	{
		var objHidden = window.opener.document.getElementById("idSuc-${PermisosRolLectorActionForm.rutEmpresa}-${PermisosRolLectorActionForm.idConvenio}");
		var valores = objHidden.value.split("#");
	    var inputs = document.getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) 
	    {
	    	if (inputs[i].type == "checkbox" && inputs[i].id != "todasSucCheck")
	    	{
		    	for (var j = 0; j < valores.length; j++)
		    	{
		    		if (valores[j] == inputs[i].value)
		    		{
		    			inputs[i].checked = true;
		    			break;
		    		}
		    	}
		    }
		}
	}

	function cambiaCheck()
	{
		var inputs = document.forms[0].getElementsByTagName("input");
		var estado = document.getElementById("todasSucCheck").checked;
		for (var i = 0; i < inputs.length; i++) 
		{
	        if ((inputs[i].type == undefined) || (inputs[i].type != "checkbox"))
	        	continue;
	        inputs[i].checked = estado;
		}
	}
// -->
</script>
</body>
</html>
