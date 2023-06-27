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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body onLoad="foco();">
<html:form action="/EdicionParametros" styleId="formulario" onsubmit="return onFormSubmit(this)">

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
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
					<strong>Mantenedor de Par&aacute;metros</strong>
				</td>
			</tr>
			<tr>
				<td class="titulo" height="30">
					<strong>Tipo de Par&aacute;metro:</strong>
					<nested:write property="tipoParametro" />
					<input type="hidden" id="tipParam"  value='<nested:write property="tipoParametro"/>'/>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<nested:notEmpty property="listaParametros">
						<tr>
							<td width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
								Nombre							
							</td>
							<td width="200" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
								Descripci&oacute;n							
							</td>
							<td width="200" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
								Valor							
							</td>
						<!--
							<td width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
								Tipo Par&aacute;metro							
							</td>
						-->
							<td width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
								Tipo Valor							
							</td>
						</tr>
						<nested:iterate id="fila" property="listaParametros" indexId="nFila">
							<input type="hidden" name="modificar${nFila}" id="modificar${nFila}" value="0"/>
							<input type="hidden" name="id${nFila}" id="id${nFila}" value="<nested:write property="id" />"/>
							<input type="hidden" name="campo${nFila}" id="campo${nFila}" value="<nested:write property="nombre" />"/>
					    	<tr>
	                			<td width="80" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
	                				<nested:write property="nombre" />
	                			</td>
	                    		<td width="200" align="center" valign="middle" nowrap="nowrap" class="textos_formularios"> 
	                      			<input type="text" name="descripcion${nFila}" id="descripcion${nFila}" value="<nested:write property="descripcion"/>" class="campos" maxlength="140" size="30" onchange="javascript:modifica('${nFila}');"/>
	                    		</td>
	                    		<td width="200" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
	                    			<nested:equal property="tipoValor" value="texto">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" value="<nested:write property="valor"/>" class="campos" maxlength="140" size="30" onchange="javascript:modifica('${nFila}');"/>
	                    			</nested:equal>
	                    			<nested:equal property="tipoValor" value="entero">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" onblur="javascript:soloEntero(this);modifica('${nFila}');" onkeyup="javascript:soloEntero(this);modifica('${nFila}');" value="<nested:write property="valor"/>" class="campos" maxlength="10" size="30" />
	                    			</nested:equal>
									<nested:equal property="tipoValor" value="decimal">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" onblur="javascript:soloDecimales(this);modifica('${nFila}');" onkeyup="javascript:soloDecimales(this);modifica('${nFila}');" value="<nested:write property="valor"/>" class="campos" maxlength="15" size="30" />
	                    			</nested:equal>
	                    			<nested:equal property="tipoValor" value="fecha">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" onblur="javascript:soloFecha(this);modifica('${nFila}');" onkeyup="javascript:soloFecha(this);modifica('${nFila}');" value="<nested:write property="valor"/>" class="campos" maxlength="16" size="30" />
	                    			</nested:equal>
	                    			<nested:equal property="tipoValor" value="hora">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" onblur="javascript:soloHora(this);modifica('${nFila}');" onkeyup="javascript:soloHora(this);modifica('${nFila}');" value="<nested:write property="valor"/>" class="campos" maxlength="8" size="30" />
	                    			</nested:equal>
	                    			<nested:equal property="tipoValor" value="fecha/hora">
	                    				<input type="text" name="valor${nFila}" id="valor${nFila}" onblur="javascript:soloHoraFecha(this);modifica('${nFila}');" onkeyup="javascript:soloHoraFecha(this);modifica('${nFila}');" value="<nested:write property="valor"/>" class="campos" maxlength="16" size="30" />
	                    			</nested:equal>
	                    		</td>
							<!-- 
								<td width="80" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
									<nested:write property="tipoParametro" />
	                    		</td>
	                    	-->
	                    		<td width="80" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
									<nested:write property="tipoValor" />
									<input type="hidden" name="tipoValor${nFila}" id="tipoValor${nFila}" value="<nested:write property="tipoValor" />" />
	                    		</td>
	                		</tr>
	               		</nested:iterate>
	               		<input type="hidden" name="largoDatos" id="largoDatos" value="${nFila}" />
	               	</nested:notEmpty>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td valign="top">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	        <tr align="center">
    	     	<td valign="top"><br/>
        	 		<html:button property="operacion" value="Guardar" styleClass="btn4" onclick="save();"/>
     			    <html:button property="operacion" value="Cancelar" styleClass="btn4" onclick="cancelar();"/>
            	</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br /><br />
<script type="text/javascript">
<!--
	function modifica(id)
	{
		document.getElementById('modificar'+id).value = '1';
	}

	function cancelar()
	{
		document.getElementById('formulario').accionInterna.value = 'CANCELAR';
		document.getElementById('formulario').submit();
	}

	function save()
	{
		document.getElementById('formulario').accionInterna.value = 'GUARDAR';
		if(validaFormularioParametros(document.getElementById('formulario')))
			document.getElementById('formulario').submit();
			
	}
	
	function validaFloat(value)
	{
		return (value.match(/^-?[0-9]+,?([0-9]+)*$/))
	}

	function validaEntero(expression)  
	{
		return (expression.match(/^-?[0-9]+([0-9]+)*$/))
	}


function validaInputFloat(oInput)
{
	if (! validaFloat(oInput.value) )	
	{
		oInput.focus();
		oInput.select();
		alert("* "+oInput.value + " no es un número válido");
		return(false);
	}
	return(true);
}

function foco()
{
	largo = -1
	largo = document.getElementById('largoDatos').value
	if(largo > 0)
		document.getElementById('valor0').focus();
}
function validaFormularioParametros(form)
{
	var largo = document.getElementById('largoDatos').value;
	var mensaje = '';
	for( it=0; it<largo; it++ )
	{
		var valor = document.getElementById('valor'+it).value;
		var tipoValor = document.getElementById('tipoValor'+it).value;
		var campo = document.getElementById('campo'+it).value;
		if(tipoValor == 'texto')
		{
			if(!validaDescripcion(valor))
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+'\n';
		}
		if(tipoValor == 'entero')
		{
			if(!validaEntero(valor))
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+'\n';
		}
		if(tipoValor == 'decimal')
		{
			if(!validaDecimal(valor))
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+'\n';
		}
		if(tipoValor == 'fecha')
		{
			if(!validaFecha(valor))
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+' [dd/mm/aaaa]\n';
		}
		if(tipoValor == 'hora')
		{
			if(!validaHora(valor))
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+' [HH:MM]\n';
		}
		if(tipoValor == 'fecha/hora')
		{
			if(valor.length == 16)
			{
				if(!validaFecha(valor.substring(0,10)) || !validaHora(valor.substring(11,16)))
					mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+' \n debe ser de formato [dd/mm/aaaa HH:MM]\n';
			} else
			{
				mensaje += '- El parámetro '+campo+' es incorrecto: valor '+valor+' \n debe ser de formato [dd/mm/aaaa HH:MM]\n';
			}
		}
	}
	if(mensaje != '')
	{
		alert(mensaje);
		return false;
	}
	return true;
	
}


function validaFecha(valor)
{
	try
	{
		var fecha = valor;
		fecha = fecha.split("/");
		var dia = fecha[0];
		var mes = fecha[1];
		var ano = fecha[2];
		var estado = true;
		if ((dia.length == 2) && (mes.length == 2) && (ano.length == 4)) 
		{
			switch (parseInt(mes)) 
			{
				case 1:dmax = 31;break;
				case 2: if (ano % 4 == 0) dmax = 29;
						 else dmax = 28;
					 break;
				case 3:dmax = 31;break;
				case 4:dmax = 30;break;
				case 5:dmax = 31;break;
				case 6:dmax = 30;break;
				case 7:dmax = 31;break;
				case 8:dmax = 31;break;
				case 9:dmax = 30;break;
				case 10:dmax = 31;break;
				case 11:dmax = 30;break;
				case 12:dmax = 31;break;
			}
			dmax!=""?dmax:dmax=-1;
		
			if ((parseInt(dia) >= 1) && (parseInt(dia) <= dmax) && (parseInt(mes) >= 1) && (parseInt(mes) <= 12)) 
			{
				!validaEntero(dia)?estado = false:'';
				!validaEntero(mes)?estado = false:'';
				!validaEntero(ano)?estado = false:'';
			} 
				else estado=false;
		}
		else
			estado = false;
		return estado;
	}
	catch(err)
	{
		return false;
	}
} 

function validaHora(valor)
{
	try{
		hora = valor.split(":");
		var hour = hora[0];
		var minute = hora[1];
		if (hour < 0  || hour > 23) return false;
		if (minute<0 || minute > 59) return false;
		return true;
	} catch(err){
		return false;
	}
} 

function validaDecimal(valor)
{
	var retorno = true;
	if(valor != null){
		var largo = valor.length;
		var acum=0;
		for(a=0;a<largo;a++){
			if(a==0){
				if(valor.substr(0,1) == '.') retorno = false;
			}
			if(valor.substr(a,1) == '.') acum++;
			if(a==largo-1){
				if(valor.substr(largo-1,1) == '.') retorno = false;
			}
		}
		if(acum > 1) retorno = false;		
	}
	return retorno;
}

//-->
</script>
</html:form>
</body>
</html:html>