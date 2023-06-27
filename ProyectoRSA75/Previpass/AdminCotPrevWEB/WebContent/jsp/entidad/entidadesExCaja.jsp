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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/entidad.js'/>"></script>
	<script type="text/javascript">

	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");

</script>
</head>
<body onload="mostrar();">
<html:form action="/EdicionEntidadesExCaja" styleId="formulario" onsubmit="return onFormSubmit(this)">
<input type="hidden" id="guardaFolio" name="guardaReimen"/>
<input type="hidden" id="tipoEdicion" name="tipoEdicion" value="<nested:write property="tipoEdicion" />"/>
<input type="hidden" id="accionInterna" name="accionInterna"/>
<html:hidden property="cantidadRegistros" styleId="cantidadRegistros"/>
<html:hidden property="mostrarBoton" styleId="mostrarBoton"/>

<input type="hidden" id="codigoEntidadAntiguo" name="codigoEntidadAntiguo" value="<nested:write property="codigoEntidadAntiguo" />" />
<input type="hidden" id="listaRegimen" name="listaRegimen" value="<nested:write property="listaRegimen" />" />
<input type="hidden" id="RegimenBorrar" name="RegimenBorrar" />
<input type="hidden" id="entidadPadre" name="entidadPadre" />
<input type="hidden" id="idEntPagadora" name="idEntPagadora" value="<nested:write property="idEntPagadora" />" />
<input type="hidden" id="nuevaEntidad" name="nuevaEntidad" value="<nested:write property="nuevaEntidad" />" />
<input type="hidden" id="idBancoSeleccionado" name="idBancoSeleccionado"/>
<input type="hidden" id="idEntidadSeleccionada" name="idEntidadSeleccionada"/>
<input type="hidden" id="ingresoDirecto" name="ingresoDirecto"/>

<nested:define id="origen" property="origen" />
<input type="hidden" id="origen" name="origen" value="${origen}"/>

<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr valign="middle">
	<td>
		<html:errors/>
	</td>
</tr>
<tr valign="middle">
	<td>
	    <html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
	</td>
</tr>
<tr>
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr height="26" valign="middle">
				<nested:empty property="tiposEdicion">
					<td valign="middle" colspan="3" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Edici&oacute;n de Entidad EXCAJA</strong>
					</td>
				</nested:empty>
				<nested:notEmpty property="tiposEdicion">
					<td valign="middle" colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Creaci&oacute;n de Entidad EXCAJA</strong>
					</td>					
				</nested:notEmpty>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Entidad</strong>					
				</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>RUT Entidad INP</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:equal property="nuevaEntidad" value="false">
						<nested:write property="rutEntidad" />
						<input type="hidden" name="rutEntidad" id="rutEntidad" value="<nested:write property="rutEntidad" />"/>
					</nested:equal>
					<nested:notEqual property="nuevaEntidad" value="false">
						<input type="text" name="rutEntidad" id="rutEntidad" value="<nested:write property="rutPadre" />"  class="campos" maxlength="12" size="14" readonly="readonly" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);" />
					</nested:notEqual>
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>Código Entidad *</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="codigoEntidad" styleId="codigoEntidad" styleClass="campos" maxlength="5" size="8"  onblur="javascript:soloNumero(this,'');" onkeyup="javascript:soloNumero(this,'');"/>
					<nested:hidden property="codigoEntidadInicial"></nested:hidden>
				</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Nombre *</strong>
				</td>	
				<td valign="middle" colspan="3" width="150" class="textos_formularios">
					<nested:text property="nombreEntidad" styleId="nombreEntidad" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);" styleClass="campos" maxlength="50" size="50" />
				</td>				
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr> 
           		<td height="4" colspan="4" bgcolor="#85b4be"></td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" colspan="4" height="20" bgcolor="#FFFFFF" align="left" class="barratablas">
					<strong>Datos Regimen Impositivo</strong>				
				</td>
			</tr>
			<tr id="agregarRegimen"  height="26" valign="middle">
				<td valign="middle" colspan="4" height="20" bgcolor="#FFFFFF" align="right" class="barratablas">
					<html:button property="operacion" styleClass="btn3" value="Nuevo Regimen" onclick="javascript:addRegimen();"/>
				</td>
			</tr>
			<tr height="26" valign="middle" >
				<td valign="middle" colspan="4">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="20%"   align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Codigo</td>
						<td width="63%"   align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
						<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Tasa</td>
						<td width="7%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
					</tr>
					<tr>
						<td colspan="4">
							 <table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:notEmpty property="listaEntidadRegimenImpositivo">
									<nested:iterate id="fila" property="listaEntidadRegimenImpositivo" indexId="nFila">
										<c:choose>
						   		    		<c:when test="${nFila % 2 == 0}">
						   		    			<c:set var="estilo" value="textos-formcolor2"/>
						   		    		</c:when>
		   									<c:otherwise>
		   										<c:set var="estilo" value="textos_formularios"/>
		   									</c:otherwise>
										</c:choose>
										
										<nested:define id="idCodigo" property="idCodigo" />
										<tr>
											<td class="${estilo}" width="20%">
												<input value="<nested:write property="idCodigo" />" size="8" id="codigoIngresado_${nFila}" name="codigoIngresado_${nFila}" class="campos" onblur="javascript:soloNumero(this,'');" onkeyup="javascript:soloNumero(this,'');" /> 
											</td>
								  			<td align="right"  valign="middle" class="${estilo}" width="63%">
				                    			&nbsp;<input value="<nested:write property="nombre" />" size="50" id="nombreIngresado_${nFila}" name="nombreIngresado_${nFila}" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);" class="campos"/>
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idCodigo}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle" class="${estilo}" width="18%">
				                    			<input value="<nested:write property="tasaPension" />" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);" size="8" id="tasaIngresado_${nFila}" name="tasaIngresado_${nFila}" class="campos" /> 
				                    		</td>
											<td width="7%" class="${estilo}" align="center">
												<div align="center">
													<a href="javascript:delConfirmar('${nFila}');">
														<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
													</a>
												</div>
											</td>											
										</tr>
									</nested:iterate>
									</nested:notEmpty>
									<nested:empty property="listaEntidadRegimenImpositivo">
										<tr>
											<td class=textos_formularios>
												No hay c&oacute;digos definidos para este &iacute;tem
											</td>
										</tr>
									</nested:empty>
								</table>
						</td>
					</tr>
					<tr id="registroNuevo0" >
						<td width="20%"  align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Codigo</td>
						<td width="63%"   align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
						<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Tasa</td>
						<td width="7%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas"> &nbsp;	</td>
					</tr>
					<tr id="registroNuevo1">
						<td width="20%" align="center" valign="middle" bordercolor="#FFFFFF" class="textos_formularios">
							<nested:text property="codigoReg" maxlength="10" onblur="javascript:soloNumero(this,'');" onkeyup="javascript:soloNumero(this,'');" size="12" styleId="codigoReg" styleClass="campos" />
						</td>
						<td width="63%" align="center" valign="middle" bordercolor="#FFFFFF" class="textos_formularios">
							<nested:text property="nombreReg" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);" maxlength="50" size="50" styleId="nombreReg" styleClass="campos" />
						</td>
						<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="textos_formularios">
							<nested:text property="tasaReg" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);"  maxlength="10" size="12" styleId="tasaReg" styleClass="campos" />
						</td>
						<td width="7%" class="textos_formularios">&nbsp;</td>
					</tr>
 					 <tr id="registroNuevo2" height="26">
						<td>&nbsp;</td>
						<td valign="middle" align="right"><html:button property="operacion" styleClass="btn3" value="Agregar Registro" onclick="javascript:saveRegistro();"/></td>
						<td valign="middle" align="center"><html:button property="operacion" styleClass="btn3" value="Eliminar Registro" onclick="javascript:noSaveReg();"/></td>
						<td>&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
		<tr>
			<td colspan="4" align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
		</tr>
		<tr height="26" valign="middle">
			<td colspan="4" valign="middle" valign="top">
		    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			        <tr align="center">
		    	     	<td valign="middle" valign="top" height="20">
		    	     		<nested:equal property="nuevaEntidad" value="false">
			        	 		<html:button property="operacion" value="Guardar" styleClass="btn4" onclick="irAction('EDIT');"/>
		   	     			 </nested:equal>
		   	     			<nested:notEqual property="nuevaEntidad" value="false">
		         			   <html:button property="operacion" styleClass="btn3" value="Guardar" onclick="irAction('NEW');"/>
							</nested:notEqual>
		     			    <html:button property="operacion" value="Cancelar" styleClass="btn4" onclick="cancelar();"/>
		            	</td>
					</tr>
				</table>
			</td>
		</tr>
</table>
</td>
</tr>
</table>
<br />
<script type="text/javascript">
	function mostrar()
	{
		if(document.getElementById('mostrarBoton').value!=0)
			noSaveReg();
		else
			addRegimen();
	}

	function addRegimen()
	{
		document.getElementById("registroNuevo0").style.display='';
		document.getElementById("registroNuevo1").style.display='';
		document.getElementById("registroNuevo2").style.display='';
		document.getElementById("agregarRegimen").style.display='none';
	}

	function noSaveReg()
	{
		document.getElementById("registroNuevo0").style.display='none';
		document.getElementById("registroNuevo1").style.display='none';
		document.getElementById("registroNuevo2").style.display='none';
		document.getElementById("agregarRegimen").style.display='';
	
	}

	function limpiaReg()
	{
		document.getElementById("codigoReg").value='';
		document.getElementById("nombreReg").value='';
		document.getElementById("tasaReg").value='';
	}

	function irAction(accion) 
	{
		frm = document.getElementById('formulario');
		sMsje = '';
		if(actualizaRegistros(accion))
		{
			if (accion == 'EDIT')
			{
				 document.getElementById('accionInterna').value = 'GUARDAR';
				 frm.submit();
			} else
			{
				  document.getElementById('accionInterna').value = 'NEW';
				 frm.submit();
				
			}
		}
	}

	function listaRegimen()
	{
		formu = document.getElementById('formulario');
		cod = document.getElementById('codigoEntidadAntiguo').value;
		idEntPagadora = document.getElementById('idEntPagadora').value;
		origen = document.getElementById('origen').value;
		url = "<c:url value='/ListaEntidadesRegImp.do?idEntidad="+cod
						+"&origen="+idEntPagadora
						+"&origenAfp="+origen
						+"' />";
		document.location = url;
	}
	
	function delConfirmar(id)
	{
		formu = document.getElementById('formulario');
		document.getElementById('accionInterna').value = 'DEL';
		document.getElementById('RegimenBorrar').value = id;
		formu.submit();
	}
	
	function saveRegistro()
	{
		document.getElementById('mostrarBoton').value='0';
		var cod = trim(document.getElementById('codigoReg').value);
		var nom = trim(document.getElementById('nombreReg').value);
		var tasa = trim(document.getElementById('tasaReg').value);

		var sMsje="";
		if(cod== "")
			sMsje+='* Debe Ingresar un codigo para el Regimen Impositivo.\n';
		if(nom== "")
			sMsje+='* Debe Ingresar un nombre para el Regimen Impositivo.\n';
		if(tasa== "")
			sMsje+='* Debe Ingresar una tasa para el Regimen Impositivo.\n';
		
		if(sMsje=='')
		{
			frm = document.getElementById('formulario');
			document.getElementById('accionInterna').value = 'ADD';
			//noSaveReg();
			frm.submit();
		} else
			alert(sMsje);
	}

	function actualizaRegistros(accion)
	{
		document.getElementById("codigoReg").value='';
		document.getElementById("nombreReg").value='';
		document.getElementById("tasaReg").value='';
		var cant = document.getElementById('cantidadRegistros').value;
		frm = document.getElementById('formulario');
	 
		var sMsje="";
		 
		if(cant>0 && cant !='')
		{
			for(fila=0; fila < cant; fila++)
			{
				cod = trim(document.getElementById("codigoIngresado_" + fila).value);
				nom = trim(document.getElementById("nombreIngresado_" + fila).value);
				tasa = parseInt(document.getElementById("tasaIngresado_" + fila).value);
	
				if(cod == "" )
					sMsje+='* Debe Ingresar un codigo para el Regimen Impositivo.\n';
				if(nom == "")
					sMsje+='* Debe Ingresar un nombre para el Regimen Impositivo.\n';
				if(tasa.length < 1)
					sMsje+='* Debe Ingresar una tasa para el Regimen Impositivo.\n';
			}
		}
		if(accion != 'EDIT' && document.getElementById('rutEntidad').value == '')
			sMsje+='* Debe Ingresar un Rut para la entidad. \n';
		if(document.getElementById('nombreEntidad').value == '')
			sMsje+='* Debe ingresar un nombre para la entidad. \n';
		if(document.getElementById('codigoEntidad').value == '')
			sMsje+='* Debe ingresar un codigo para la entidad. \n';
		
		/*if(document.getElementById("agregarRegimen").style.display == 'none')
		{
			var cod_n = trim(document.getElementById('codigoReg').value);
			var nom_n = trim(document.getElementById('nombreReg').value);
			var tasa_n = trim(document.getElementById('tasaReg').value);

			if(cod_n == "")
				sMsje += '* Debe Ingresar un codigo para el Regimen Impositivo.\n';
			if(nom_n == "")
				sMsje += '* Debe Ingresar un nombre para el Regimen Impositivo.\n';
			if(tasa_n == "")
				sMsje += '* Debe Ingresar una tasa para el Regimen Impositivo.\n';

			document.getElementById("ingresoDirecto").value='VISIBLE';
		} else
			document.getElementById("ingresoDirecto").value='';
		*/
		if(sMsje == '')
			return true;	  	 
		else 
		{
			alert(sMsje);	
			return false;
		}
	}
</script>
</html:form>
</body>
</html:html>
