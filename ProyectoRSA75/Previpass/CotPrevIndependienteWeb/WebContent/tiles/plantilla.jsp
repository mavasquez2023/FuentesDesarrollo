<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<c:set var="menus" value="${SESSION_KEY_MENUS}" />
<c:set var="lista" value="${SESSION_PATH_NAVEGACION}" />
<c:set var="usuario" value="${currentUser}" />
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>

<script language="javaScript">
<!--
var listaMenu = new Array();
var listaSubMenu = new Array();
var listaLinks = new Array();
//"corregirNomina", "envio", "buscaTrabajador", "codigos", "remu", "grati", "reli", "dep");
<c:forEach var="modulo" items="${menus.modulos}">
	<c:if test="${fn:contains(modulo.rolesPermitidos, usuario.rol)}">
		listaMenu.push("<c:out value="${modulo.accion}" />");
		<c:if test="${!empty modulo.tabs}">
			listaSubMenu.push("sub<c:out value="${modulo.accion}" />");
			<c:forEach var="tab" items="${modulo.tabs}">
				<c:if test="${fn:contains(tab.rolesPermitidos, usuario.rol)}">
					listaLinks.push("<c:out value="${tab.subAccion}" />");
				</c:if>
			</c:forEach>
		</c:if>
	</c:if>
</c:forEach>


function initMenu(accion, subAccion)
{
	despliega("sub" + accion, subAccion);
	resalta(accion)
}

function despliega(target, subAccion)
{
    for (i = 0; i < listaSubMenu.length; i++)
    {
		if (target == listaSubMenu[i])
		{
	    	if(document.getElementById(target).style.display == "none" || document.getElementById(target).style.display == "")
      			document.getElementById(target).style.display = "block";
    	    else
      			document.getElementById(target).style.display = "none";
		} else
	    	document.getElementById(listaSubMenu[i]).style.display = "none";
   	}
   	if (subAccion != "")
    	resalta2(subAccion);
}

function resalta(target)
{
    for (i = 0; i < listaMenu.length; i++)
    {
		if (target == listaMenu[i])
	      	    document.getElementById(target).style.color = "#ff8a00";
		else
		    document.getElementById(listaMenu[i]).style.color = "#FFFFFF";
    }
}

function resalta2(target)
{
    for (i = 0; i < listaLinks.length; i++)
    {
		if (target == listaLinks[i])
		    document.getElementById(target).style.color = "#ff8a00";
		else if (listaLinks[i] != "" && document.getElementById(listaLinks[i]))
	    	document.getElementById(listaLinks[i]).style.color = "#000000";
    }
}

function validaSender(enlace) {
	if (sendMethod == SEND_METHOD_DISPATCHER) {
		return validaJWS(enlace);
	} else if (sendMethod == SEND_METHOD_UNZIPPED_ADAPTED) {
		return validaUnzippedAdaptedSender(enlace);
	}
	return false;
}

function esJWSValido() 
{	
	return javawsInstalled;
}

function validaJWS(enlace)
{
	var link = "";
	//Valida que el envio este dentro del plazo.
	if(<%=request.getAttribute("bloqueoEdicionNom")%> == 0)
	{
		if (!esJWSValido()) 
		{
			if (confirm('Debe instalar Java para el envio de Nóminas. ¿Instalar ahora?.'))
			{
				if(navigator.appVersion.indexOf("Linux") !=-1) 
				{
					enlace.target = "_blank";
					enlace.href = '${descLinux}';
					return true;
				} else
				{
					enlace.target = "_blank";
					enlace.href = '${descWin}';
					return true;
				} 
				/*if (confirm('Se abrirá una nueva ventana, a la que deberá contestar \'Yes\'.\n'
					+ 'Una vez finalizada la descarga, deberá contestar nuevamente \'Yes\'.\n'
					+ 'Finalizada la instalación, deberá cerrar la sesión e iniciarla para poder subir la(s) nómina(s).'))
				{
					enlace.target = "_blank";
					enlace.href = "http://jdl.sun.com/webapps/getjava/BrowserRedirect?locale=en&host=www.java.com:80";
					return true;
				} else
					return false;*/
			} else
				return false;
		} else
		{
			var date = new Date();
			enlace.href = "dispatcher" + date.getTime() + ".jnlp";
			enlace.target = "_self";
			return true;
		}
	} else
	{
		alert('\n El plazo válido para enviar Nominas ha finalizado');
		return false;
	}
}

function validaUnzippedAdaptedSender(enlace) {

	//Valida que el envio este dentro del plazo.
	if (<%=request.getAttribute("bloqueoEdicionNom")%> == 0) {
		enlace.href = "adispatcher.do?accion=nominas&subAccion=envio&limpiaPath=";		
		enlace.target = "_self";
		return true;
	}

	alert('\n El plazo válido para enviar nóminas ha finalizado');

	return false;
}

function abrirDocImpresion(url) 
{
	target = "_blank";
	window.open(url, target, "height=600,width=650,toolbar=no,directories=no,"
		+ "scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
}

function excel(url, rutEmpresa, idConvenio)
{
	url = url+"?accion=excel&idConvenio="+idConvenio+"&rutEmpresa="+rutEmpresa+"&tipoProceso="+document.getElementById("tipoProceso").value;
	window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
}

function muestraAviso()
{
	if (document.getElementById("pop-aviso").style.visibility == 'hidden')
		document.getElementById("pop-aviso").style.visibility = 'visible';
	else
		document.getElementById("pop-aviso").style.visibility = 'hidden';
}
function muestraCalendario()
{
	if (document.getElementById("pop-calendario").style.visibility == 'hidden' || document.getElementById("pop-calendario").style.visibility == '')
	{
		document.getElementById("pop-calendario").style.display = 'block';
		document.getElementById("pop-calendario").style.visibility = 'visible';
	} else
	{
		document.getElementById("pop-calendario").style.display = 'none';
		document.getElementById("pop-calendario").style.visibility = 'hidden';
	}
}

function doLogout() 
{
	document.getElementById("logoutForm").submit();
}

/*
 * Funciones generales de validacion
 */
	//Esta funcion debe ponerse en el evento onSubmit del form: onsubmit="return onFormSubmit(this)"
	//Retorna el valor de verdad de la funcion validaFormulario(form), que debe estar definida en cada
	//formulario. Se le pasa como parametro el form que gatillo el evento.
	//validaFormulario(form) debe retornar true si no hubo errores de validacion y false en caso contrario. Ademas
	//debe mostrar los alert correspondientes al usuario.
	function onFormSubmit(formulario) 
	{

		if (bCancel == true)
			return true;
			
		return validaFormulario(formulario);
	}
	
	//Declaración de variable para poder diferenciar correctamente html submit de html cancel
	var bCancel = false;
	
	//Retorna verdadero si el campo está presente.
	function validaReq(field) 
	{
	 if (field!=null)
	 {
		with (field) 
		{
			if ((value == null) || (trim(value) == ""))
				return false;
			else
				return true;
		}
	  }
	  return false;
	}

	function trim(cadena)
	{
		for(i=0; i<cadena.length; )
		{
			if(cadena.charAt(i)==" ")
				cadena=cadena.substring(i+1, cadena.length);
			else
				break;
		}
		for(i=cadena.length-1; i>=0; i=cadena.length-1)
		{
			if(cadena.charAt(i)==" ")
				cadena=cadena.substring(0,i);
			else
				break;
		}
		return cadena;
	}

	//Retorna verdadero si el string sFecha contiene una fecha válida en formato "dd/mm/yyyy"
	function validaFecha(sFecha) 
	{
		var regex = /^\d{2}\/\d{2}\/\d{4}$/
		if (!regex.test(sFecha))
			return false;

		var dia  = sFecha.split("/")[0];
		var mes  = sFecha.split("/")[1];
		var year = sFecha.split("/")[2];
		if (dia < 1 || dia > 31)
			return false;
		if (mes < 1 || mes > 12)
			return false;
		if (year < 1970 || year > 2050)
			return false;
		var dateObj = new Date(year, mes - 1, dia);

		if ((dateObj.getDate() != dia) || (dateObj.getMonth() + 1 != mes) || (dateObj.getFullYear() != year))
			return false;
		return true;
	}
	
	//Valida que el string sRut contenga un rut valido segun el formato: 99[.]999[.]999[-]9|k|K o rut sin formato
	function validaRut(sRut) 
	{
		if(sRut.length < 10) sRut = '0' + sRut;
		var regex = /^\d{1,3}\.?\d{3}\.?\d{3}-?[\dkK]$/
		return regex.test(sRut);
	}
	
	//Valida el digito verificador del rut en el string sRut
	function validaDV(sRut) 
	{
		var rut = sRut.replace(/\./g, "");
		if (rut.indexOf("-") != -1) 
		{
			//Rut con guion		
			var num = rut.split("-")[0];
			var dig = rut.split("-")[1].toUpperCase();
		} else 
		{
			//Rut sin guion
			var num = rut.substr(0, rut.length - 1);
			var dig = rut.substr(rut.length - 1).toUpperCase();
		}
		var s = 0;
		var m = 2;
		for (var i = num.length - 1; i >= 0; i--) 
		{
			s = s + m++*num.charAt(i);
			if (m == 8) m = 2;
		}

		var d = 11 - (s % 11);
		if (d == 10)
			d = "K";
		else if (d == 11)
			d = "0";

		if (dig != d)
			return false;

		return true;
	}

	//Valida que el string sTxt contenga solo caracteres validos para la base de datos de Araucana
	function validaChrs(sTxt) 
	{
		var regex = /[^A-Za-z0-9 ]/
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}
	
	//Valida que el string sTxt contenga solo letras
	function validaSoloTexto(sTxt) 
	{
		var regex = /[^A-Za-zÑñ ]/
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}

	//Valida que el string sTxt contenga solo letras
	function validaSoloNombre(sTxt) 
	{
		var regex = /[^A-Za-zÑñ\.\ ]/
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}
	
	//Valida que el string sTxt no sea una secuencia de espacios
	function validaNoEspacios(sTxt) 
	{
		var regex = /^ +$/
		return !regex.test(sTxt);
	}

	//Valida que el string sTxt pueda ser parseado como un entero sin signo
	function validaUInt(sTxt) 
	{
		var regex = /^\d+$/
		return regex.test(sTxt);
	}
	
	//Valida que el string pueda ser parseado como un flotante sin signo
	function validaUFloat(sTxt) 
	{
		var regex = /^(\d*\,)?\d+$/
		return regex.test(sTxt);
	}
	
	//Valida que el string sea un email válido
	function validaEmail(sTxt) 
	{
		var regex = /^[\w\.\-]+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
		return regex.test(sTxt);
	}

//probar!
	function validarEmail(valor) 
	{
		var filtro = /^[A-Za-z][A-Za-z0-9_\.]*@[A-Za-z0-9]+(\.[A-Za-z]{2,3}){1,2}$/;
		return (filtro.test(valor));
	}

function cargaConvenios(idConvenio)
{
	var rutEmpresa = document.getElementById("comboEmpresas").value;
	document.getElementById("nombreEmp").innerHTML = document.getElementById("nomEmp#" + rutEmpresa).value;
	//limpia lista
	for (i = document.getElementById("comboConvenios").childNodes.length - 1; i >= 0; i--)
		document.getElementById("comboConvenios").removeChild(document.getElementById("comboConvenios").childNodes.item(i));
	//llena lista
	var i = 0;
	var hidd = document.getElementById("idConvEmp#" + rutEmpresa + "#" + i);
	while (hidd)
	{
		theOption = document.createElement("OPTION");
		theOption.appendChild(document.createTextNode(document.getElementById("descConvEmp#" + rutEmpresa + "#" + i).value));
		theOption.setAttribute("value", hidd.value);
		if (idConvenio == hidd.value)
			theOption.selected = true;
		document.getElementById("comboConvenios").appendChild(theOption);

		i++;
		hidd = document.getElementById("idConvEmp#" + rutEmpresa + "#" + i);		
	}
}

function ClickHereToPrint() 
{
	try 
	{
		var ifrm = document.getElementById('ifrmPr');
		var content = document.getElementById('pop-calendario').innerHTML;

		var printDoc = (ifrm.contentWindow || ifrm.contentDocument);
		if (printDoc.document)
			printDoc = printDoc.document;

		printDoc.write("<html><head><title>title</title>");
		printDoc.write("</head><body onload='this.focus(); this.print();'>");
		printDoc.write(content + "</body></html>");
		printDoc.close();
	} catch(e) 
	{
		self.print();
	}
}
// -->
</script>
</head>
<body onload="initMenu('<%= request.getAttribute("accion") %>', '<%= request.getAttribute("activar") %>');">
<iframe id='ifrmPr' src='#' style="width:0px; height:0px; border: none; background:transparent"></iframe>
<div id="div-body">
<table width="770" height="100%" border="0" cellpadding="0" cellspacing="0">
	<!-- <tr>
		<td colspan="2" valign="top"><img src="<c:url value="/img/logo.jpg" />" width="770" /></td>
	</tr> -->
   	<tr>
    	<td class="menuSuperior" colspan="2" valign="top" height="20" width="770">
	    	
    		<a href="javascript:muestraCalendario();" class="menuSuperior"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" /></a>&nbsp;<a href="javascript:muestraCalendario();" class="menuSuperior">Calendario</a>
    		&nbsp;&nbsp;
			<%--a href="${urlMisPlanillas}" class="menuSuperior">
				<img src='<c:url value="/img/ico_pdf.gif" />' width="12" height="14" border="0" />
				&nbsp;Mis Planillas
			</a--%>
    		<a href="PrepareChangePassword.do" class="menuSuperior">
    			<img src="<c:url value="/img/ico_padlock.gif" />" width="11" height="11" border="0" />
    		</a>
    		<a href="PrepareChangePassword.do" class="menuSuperior">
    			Cambiar Clave
    		</a>
    		&nbsp;&nbsp;
		<a href="javascript:doLogout();" class="menuSuperior"><img src="<c:url value="/img/ico_cerrar.gif" />" width="9" height="9" border="0" /></a>&nbsp;<a href="javascript:doLogout();" class="menuSuperior">Cerrar Sesi&oacute;n</a>
    	</td>
   	</tr>
	<tr>
		<td colspan="2" height="20" valign="top" align="center" width="770">
			<img src="<c:url value="/img/sombra.jpg" />" width="739" height="7" />
		</td>
	</tr>
	<tr>
		<td width="180" align="left" valign="top">
			<tiles:insert attribute="left_menu"/>
		</td>
        <td align="left" valign="top" width="590">
        	<table border="0" cellpadding="0" cellspacing="0">
	        	<tr>
	        		<td valign="top">
	        			<div style="width: 590;">
				       		<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
					           	<tr>
					             	<td width="45%" height="20" align="left" class="imprimir" valign="top">
						             	<!--<c:forEach var="path" items="${lista}">
						             		<c:choose>
						             			<c:when test="${path.actual}"><span class="botonera_ppalactivada"><tiles:useAttribute name="nombre" scope="session"/><c:out value="${nombre}"/></span></c:when>
						             			<c:otherwise><a href="<c:out value="${path.url}" />" class="links"><c:out value="${path.nombre}" /></a>&nbsp;&gt;&nbsp;</c:otherwise>
						             		</c:choose>
						             	</c:forEach>
						             	-->
									</td>
					             	<td width="55%" align="right" class="tit-usuario">
					             		<div style="font-size: 14px;font-weight: bolder;">
					             			Per&iacute;odo: ${periodo}
					             		</div>
<!--  					             	
					             		Usuarioxxx, ${usuarioActivo}		             
 -->					           
 					             		${usuarioActivo}
					             	</td>
					           	</tr>
				         	</table>
				         	<br>
			         	</div>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>
	        			<tiles:insert attribute="main"/>
	        		</td>
	        	</tr>
        	</table>
        </td>
	</tr>
</table>
</div>
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/Tiles.do">
</form>
<div id="pop-calendario" STYLE="position:absolute; top:100px; left:150px; width:701px; height:506px; z-index:6; visibility: hidden; background-color: #D1E6E7; border: 1px solid black; opacity: 1;">
	<!--[if lte IE 6.5]><iframe></iframe><![endif]-->
	<div STYLE="background-color: #D1E6E7;">
		<div align="right"><a onclick="javascript:ClickHereToPrint();" href="#" class="links">Imprimir</a>&nbsp;&nbsp;<a href="javascript:muestraCalendario();" class="links">Cerrar Calendario</a>&nbsp;&nbsp;&nbsp;</div>
		<div align="center"><b><i>Calendario de Transmisi&oacute;n y Pagos</i></b></div><br>
		<b><font size="2">Actualizaci&oacute;n de Par&aacute;metros:</font></b><font size="2">A partir del d&iacute;a </font><b><font size="2" color="#FF8100">24 de cada mes</font></b><font size="2"> o día h&aacute;bil siguiente.</font><br>
		<br>
	</div>
<table border="1">
<tr valign="top"><td class="barra_tablas" width="97" rowspan="2"><div align="center"><b><font size="2" face="Verdana">Mes de Proceso</font></b></div></td><td class="barra_tablas" width="664"><div align="center"><b><font size="2" face="Verdana">Informaci&oacute;n</font></b><br>
</div></td></tr>

<tr valign="top"><td class="barra_tablas" width="664">&nbsp;</td></tr>
<logic:iterate id="cal" name="calendario">
	<tr valign="top"><td class="barra_tablas" width="97"><b>${cal.mes}</b></td><td class="fechas_calendario"><bean:write name="cal" property="informacion"/>&nbsp;</td></tr>
</logic:iterate>
</table>
</div>
</body>
</html>