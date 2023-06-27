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
	<script src="<c:url value="/js/jquery-1.3.2.js" />"></script>
</head>
<body onLoad="foco();">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<c:set var="opcioneditcrea" value="c"/>
<html:form action="/DetalleGrupoConvenio" styleId="formulario" onsubmit="return onFormSubmit(this);" >
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<logic:equal parameter="subSubAccion" value="grupoEditar">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="grupoEditar" />
</logic:equal>
<logic:equal parameter="subSubAccion" value="grupoCrear">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="grupoCrear" />
</logic:equal>
<input type="hidden" id="existeGrupoConvenio" value="">
<table width="580" border="0" cellspacing="0" cellpadding="0">
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
    	<td align="left" valign="top">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
          		<tr valign="bottom"> 
            		<td height="30" bgcolor="#FFFFFF" class="titulo">
						<logic:equal parameter="subSubAccion" value="grupoEditar">
            				<strong>Edici&oacute;n de Grupo de Convenios</strong>
							<c:set var="opcioneditcrea" value="e"/>
            			</logic:equal>
						<logic:equal parameter="subSubAccion" value="grupoCrear">
            				<strong>Creaci&oacute;n de Grupo de Convenios</strong>
            			</logic:equal>
            		</td>
          		</tr>
	        </table>
	    </td>
	</tr>
	<tr>
		<td>
	        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
				<tr> 
            		<td height="4" colspan="4" bgcolor="#85b4be">
            		</td>
				</tr>
				<logic:equal parameter="subSubAccion" value="grupoEditar">
					<tr> 
						<td width="19%" height="17" class="barratablas">C&oacute;digo *</td>
						<td height="17" class="textos_formularios">
							<logic:equal parameter="subSubAccion" value="grupoEditar">
								<html:hidden property="idGrupoConvenio" styleId="idGrupoConvenio" write="true" />
							</logic:equal>
							<logic:equal parameter="subSubAccion" value="grupoCrear">
								<nested:text property="idGrupoConvenio" styleId="idGrupoConvenio" size="17" styleClass="campos" readonly="false" maxlength="9" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
							</logic:equal>
						</td>
						<td height="17" class="barratablas"><strong>Nombre *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:text property="nombreGrupoConvenio" styleClass="campos" styleId="nombreGrupoConvenio" size="50" maxlength="30" onblur="javascript:soloRazonSocial(this);" onkeyup="javascript:soloRazonSocial(this);"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal parameter="subSubAccion" value="grupoCrear">
					<tr> 
						<td height="17" class="barratablas"><strong>Estado *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:select property="estadoGrupoConvenio" styleId="estadoGrupoConvenio" styleClass="campos">
	                    		<html:option value="1">Habilitado</html:option>
	                    		<html:option value="0">Deshabilitado</html:option>
							</nested:select>
						</td>
						<td height="17" class="barratablas"><strong>Nombre *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:text property="nombreGrupoConvenio" styleClass="campos" styleId="nombreGrupoConvenio" size="50" maxlength="30" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td height="17" class="barratablas">
	                	<strong>Homologaci&oacute;n Previred *</strong>
					</td>
					<td height="17" class="textos_formularios">
						<nested:radio property="homologacionPrevired" value="1"/>S&iacute;
						<nested:radio property="homologacionPrevired" value="0"/>No
					</td>
					<logic:equal parameter="subSubAccion" value="grupoEditar">
						<td height="17" class="barratablas"><strong>Estado *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:select property="estadoGrupoConvenio" styleId="estadoGrupoConvenio" styleClass="campos" onchange="javascript:validaCambio();">
	                    		<html:option value="1">Habilitado</html:option>
	                    		<html:option value="0">Deshabilitado</html:option>
							</nested:select>
						</td>
					</logic:equal>
					<logic:notEqual parameter="subSubAccion" value="grupoEditar">
						<td height="34" colspan="2" class="textos_formularios">&nbsp;</td>
					</logic:notEqual>
				</tr>
				<logic:notEqual parameter="subSubAccion" value="grupoEditar">
					<tr>
						<td height="17" class="barratablas">
							<strong>Configuraci&oacute;n Base</strong>
						</td>
						<td height="17" class="textos_formularios">
							<nested:select property="listaGrupoConvenio" styleId="listaGrupoConvenio" styleClass="campos" onchange="javascript:grupoConvenioBase();">
	      	            		<html:optionsCollection property="configuracionesBase" />
	           	        		<html:option value="-1">OTRO...</html:option>
							</nested:select>
						</td>
						<td height="17" class="barratablas">
							<div id="divGrupoConvenio1" style="display: none;">
								<strong>C&oacute;digo Grupo Convenio</strong>
							</div>
						</td>
						<td height="17" class="textos_formularios">
							<div id="divGrupoConvenio2" style="display: none;">
								<nested:text property="idGrupoConvenioBase" styleClass="campos" styleId="idGrupoConvenioBase" size="12" maxlength="10" onkeyup="javascript:soloNumero(this);"/>
							</div>
						</td>
					</tr>
				</logic:notEqual>
				<tr>
					<td height="17" class="barratablas">
						<strong>Indicador tipo archivo</strong>
					</td>
					<td height="17" class="textos_formularios">
						<nested:select property="listaTipoSeparador" styleId="listaTipoSeparador" styleClass="campos" onchange="javascript:tipoSeparador();">
           	        		<html:option value="1">Largo</html:option>
   		   	        		<html:option value="2">Separador</html:option>	           	        		
						</nested:select>
					</td>
					<td height="17" class="barratablas">
						<div id="divSeparador1" style="display: none;">
							<strong>Caracter separador</strong>
						</div>
					</td>
					<td height="17" class="textos_formularios">
						<div id="divSeparador2" style="display: none;">
							<nested:text property="caracterSeparador" styleClass="campos" styleId="caracterSeparador" maxlength="1" size="1" value="${caracterSeparador}" />
						</div>
					</td>
				</tr>
				<%--/logic:notEqual>
				<logic:equal parameter="subSubAccion" value="grupoEditar">
					<c:set var="tipoSeparador"><%=request.getAttribute("tipoSeparador")%></c:set>
					<c:set var="caracterSeparador"><%=request.getAttribute("caracterSeparador")%></c:set>
					<tr>
						<td height="17" class="barratablas">
							<strong>Indicador tipo archivo</strong>
						</td>
						<td height="17" class="textos_formularios" <c:if test="${tipoSeparador == 1}">colspan="3"</c:if>  >
							<c:choose>
								<c:when test="${tipoSeparador == 1}">Largo</c:when>
								<c:otherwise>Separador</c:otherwise>
							</c:choose>
						</td>
						<c:if test="${tipoSeparador == 2}">
							<td height="17" class="barratablas">
								<strong>Caracter separador</strong>
							</td>
							<td height="17" class="textos_formularios">
								<c:out value="${caracterSeparador}"/>
							</td>
						</c:if>
					</tr>
				</logic:equal--%>
				<tr>
            		<td height="4" colspan="4" bgcolor="#85b4be">
            		</td>
				</tr>
          		<tr>
          			<td height="17" class="barratablas" colspan="4">
          				<strong>Opciones de proceso</strong>
          			</td>
          		<tr>
				<tr>
					<td colspan="4">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
			          		<tr>
								<td width="35%" height="17" class="barratablas">
									Generar planillas INP por sucursal?
								</td>
								<td width="15%" height="17" class="textos_formularios">
									<nested:radio property="genINPPorSucursal" value="1"/>S&iacute;
									<nested:radio property="genINPPorSucursal" value="0"/>No
								</td>
								<td width="35%" height="17" class="barratablas">
									Calcular aporte CCAF?
								</td>
								<td width="15%" height="17" class="textos_formularios">
									<nested:radio property="calcularMontoCCAF" value="1"/>S&iacute;
									<nested:radio property="calcularMontoCCAF" value="0"/>No
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Generar planillas Mutual por sucursal?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="genMutualPorSucursal" value="1"/>S&iacute;
									<nested:radio property="genMutualPorSucursal" value="0"/>No
								</td>
								<td height="17" class="barratablas">
									Calcular monto total Salud?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="calcularMontoTotalSalud" value="1"/>S&iacute;
									<nested:radio property="calcularMontoTotalSalud" value="0"/>No
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Generar planillas CCAF por sucursal?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="genCCAFPorSucursal" value="1"/>S&iacute;
									<nested:radio property="genCCAFPorSucursal" value="0"/>No
								</td>
								<td height="17" class="barratablas">
									Calcular monto total Previsi&oacute;n?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="calcularMontoTotalPrev" value="1"/>S&iacute;
									<nested:radio property="calcularMontoTotalPrev" value="0"/>No
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Calcular monto IPS(INP)?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="calcularMontoINP" value="1"/>S&iacute;
									<nested:radio property="calcularMontoINP" value="0"/>No
								</td>
								<td height="17" class="barratablas">
									Imprimir planillas?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="imprimirPlanillas" value="1"/>S&iacute;
									<nested:radio property="imprimirPlanillas" value="0"/>No
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Calcular aporte Mutual?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="calcularMontoMutual" value="1"/>S&iacute;
									<nested:radio property="calcularMontoMutual" value="0"/>No
								</td>
								<td height="17" class="barratablas">
									Calcular Fonasa?
								</td>
								<td height="17" class="textos_formularios">
									<nested:radio property="calcularFonasa" value="1"/>S&iacute;
									<nested:radio property="calcularFonasa" value="0"/>No
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
			<html:submit property="operacion" value="Guardar" styleClass="btn3" />
			<html:cancel property="operacion" value="Cancelar" styleClass="btn3" />
		</td>
	</tr>
</table>
</html:form>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<div><script language="javaScript"> 
	function validaFormulario(f) 
	{
		var sMsje = "";
		if (!validaReq(document.getElementById("nombreGrupoConvenio")))
			sMsje += "* Debe ingresar el nombre del grupo de convenios\n";
		else if (!validaRazonSocial(document.getElementById("nombreGrupoConvenio").value))
			sMsje += "* Caracteres inválidos en el nombre del grupo de convenios.\n";
		<logic:notEqual parameter="subSubAccion" value="grupoEditar">
			if (document.getElementById("idGrupoConvenioBase").value == "")
				sMsje += "* Debe seleccionar un Grupo Convenio Base\n";
			if (document.getElementById("existeGrupoConvenio").value == "0")
				sMsje += "* El Grupo Convenio ingresado no existe \n";
		</logic:notEqual>
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function validaCambio()
	{
		if ('${GruposConvenioActionForm.puedeDeshabilitar}' != '1' && document.getElementById("estadoGrupoConvenio").value == "0")
		{
			alert("Este Grupo de Convenios no se puede deshabilitar, ya que hay Empresas habilitadas asociadas a él.");
			document.getElementById("estadoGrupoConvenio").value = "1";
		}
	}
	
	function foco()
	{
		foco =  document.getElementById('opcionfoco').value;
		if(foco == 'c')
			 document.getElementById('estadoGrupoConvenio').focus();
		else
			 document.getElementById('nombreGrupoConvenio').focus();
	}

	<logic:notEqual parameter="subSubAccion" value="grupoEditar">
		function grupoConvenioBase() {
			if (document.getElementById("listaGrupoConvenio").value == "-1") {
				document.getElementById("divGrupoConvenio1").style.display = "block";
				document.getElementById("divGrupoConvenio2").style.display = "block";
				document.getElementById("idGrupoConvenioBase").value = "";
			} else {
				document.getElementById("divGrupoConvenio1").style.display = "none";
				document.getElementById("divGrupoConvenio2").style.display = "none";
				document.getElementById("idGrupoConvenioBase").value = document.getElementById("listaGrupoConvenio").value;
			}
			//alert(document.getElementById("idGrupoConvenioBase").value);
		}
		
		//Se llama a la función para que, en el campo oculto, quede selecionado el id del Grupo Convenio Base mostrado por defecto en el combobox
		grupoConvenioBase();
	</logic:notEqual>

	function tipoSeparador() {
		//SEPARADOR
		if (document.getElementById("listaTipoSeparador").value == "2") {
			document.getElementById("divSeparador1").style.display = "block";
			document.getElementById("divSeparador2").style.display = "block";
			document.getElementById("caracterSeparador").value = "<c:out value='${caracterSeparador}'/>";
		//LARGO
		} else {
			document.getElementById("divSeparador1").style.display = "none";
			document.getElementById("divSeparador2").style.display = "none";
			document.getElementById("caracterSeparador").value = "";
		}
		//alert(document.getElementById("tipoSeparador").value);
	}

	tipoSeparador();

	$(document).ready(function(){

		$("#idGrupoConvenioBase").blur(function(){
			var id = $("#idGrupoConvenioBase").val();

			jQuery.ajax({
				type: "POST",
				async: false,
				url: '<c:url value="/BuscaAjaxGrupoConvenio.do" />',
				data: "id="+id,
				dataType: ($.browser.msie) ? "text" : "xml",
				error : function(XMLHttpRequest, textStatus, errorThrown){alert('Error: ' + textStatus + ", " + errorThrown);},
				success: function(data) {
					var xml;
					if (typeof data == "string") {
						xml = new ActiveXObject("Microsoft.XMLDOM");
						xml.async = false;
						xml.loadXML(data);
					} else {
						xml = data;
					}
	
					var existe = $(xml).find("existe").text();
					$("#existeGrupoConvenio").val(existe); 
				}
			});
		});
	});
	<%--/logic:notEqual--%>

// -->
</script></div>
</body>
</html:html>
