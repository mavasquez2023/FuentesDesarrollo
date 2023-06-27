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
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/collapsible_menu.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/estilo_formularios.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/grid_960.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/estilos_interna.css" />" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script language="JavaScript" type="text/javascript" src="<c:url value='/Scripts/jquery.js'/>"></script>
	<script language="JavaScript" type="text/javascript" src="<c:url value='/Scripts/collapsible_menu.js'/>"></script>
	<script type="text/javascript">
	function swapAll(id, imgId)
	{
		obj = document.getElementById(id);
		img = document.getElementById(imgId);
	    if ( obj.style.display == '' )
	    {
			obj.style.display='none';
			img.src   = '../img/ico_mas.gif';
			img.alt   = "Expandir";
			img.title = "Expandir";
		} else
		{
			obj.style.display = '';
			img.src   = '../img/ico_menos.gif';
			img.alt   = "Contraer";
			img.title = "Contraer";
		}
	}
	</script>

<script language="javaScript">
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

 function esIE() {
  var val = navigator.userAgent.toLowerCase();
  if (val.indexOf("msie") > -1) {
		return true;
  }
  else {
    return false;
    
  }
	
 }

function initMenu(accion, subAccion)
{
	despliega("sub" + accion, subAccion);
	resalta(accion)
}

function despliega(target, subAccion)
{
    for (i = 0; i < listaSubMenu.length; i++)
    {
		/* if (target == listaSubMenu[i])
		{
	    	if(document.getElementById(target).style.display == "none" || document.getElementById(target).style.display == "")
      			document.getElementById(target).style.display = "block";
    	    else
      			document.getElementById(target).style.display = "block";
   	}
		else{
	    	document.getElementById(listaSubMenu[i]).style.display = "block";
   		}*/
	document.getElementById(listaSubMenu[i]).style.display = "block";
   	}
   	if (subAccion != ""){
    	resalta2(subAccion);
    }else{
		// $('#'+listaMenu[i]).addClass('collapsed').removeClass('expanded').parent().find('> ul').slideUp(1);
	}
}

function resalta(target)
{
    for (i = 0; i < listaMenu.length; i++)
    {
		if (target == listaMenu[i]){
	        document.getElementById(target).style.color = "#1C74A9";
	        if (!esIE()) {
	      	   	$('#'+target).addClass('expanded').parent().find('> ul');
	      	}
	      	else{
	      	   	$('#'+target).addClass('expanded').parent().find('> ul');
	      	}
	  	}
		else{
	 	 	document.getElementById(listaMenu[i]).style.color = "#000000";
      	   	// $('#'+listaMenu[i]).addClass('colapsed').removeClass('expanded').parent().find('> ul').slideUp(1);
	        if (!esIE()) {
	      	   	$('#'+target).addClass('expanded').parent().find('> ul');
	      	}
	      	else{
	      	   	$('#'+target).addClass('expanded').parent().find('> ul');
	      	}
	 	}
    }
}

function resalta2(target)
{
    for (i = 0; i < listaLinks.length; i++)
    {
		if (target == listaLinks[i])
		    document.getElementById(target).style.color = "#1C74A9";
		    
		else if (listaLinks[0])
	    	document.getElementById(listaLinks[0]).style.color = "#1C74A9";
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

function excelPC(url, rutEmpresa, idConvenio)
{	
	var opciones="";
	if(document.getElementById("opcionC").checked){
		opciones+="C";
	}
	if(document.getElementById("opcionL").checked){
		opciones+="L";
	}
	if(opciones=="") return false;
	url = url+"?accion=excel&idConvenio="+idConvenio+"&rutEmpresa="+rutEmpresa+"&tipoProceso="+document.getElementById("tipoProceso").value + "&opciones=" + opciones;
	window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
}

function procesaCSV(url, rutEmpresa, idConvenio)
{
	var opciones="";
	if(document.getElementById("opcionC").checked){
		opciones+="C";
	}
	if(document.getElementById("opcionL").checked){
		opciones+="L";
	}
	if(opciones=="") return false;
	url = url+"?accion=procesa&idConvenio="+idConvenio+"&rutEmpresa="+rutEmpresa+"&tipoProceso="+document.getElementById("tipoProceso").value + "&opciones=" + opciones;
	window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=600,height=400");
}

function excelFull(url, listaConvenios)
{	
	var sels=0;
	var lista="";
	nrosel= listaConvenios.length;
	if(isNaN(nrosel)){
		if(listaConvenios.checked){
			sels++;
			lista+= listaConvenios.value + ",";
		}
	}else{
		for (var i = 0; i < nrosel; i++) {
			if (listaConvenios[i].checked==true){
				sels++;
				lista+= listaConvenios[i].value + ",";
			}
		}
	}
	if(sels==0){
		alert("Debe seleccionar al menos una Empresa.");
		return false;
	}
	var opciones="";
	if(document.getElementById("opcionC").checked){
		opciones+="C";
	}
	if(document.getElementById("opcionL").checked){
		opciones+="L";
	}
	if(opciones=="") return false;
	lista= lista.substr(0, lista.length - 1);
	url = url+"?accion=excel&listaConvenios="+lista+"&tipoProceso="+document.getElementById("tipoProceso").value + "&opciones=" + opciones;
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
</script>
</head>
<!--  body onLoad="initMenu('<%= request.getAttribute("accion") %>','<%= request.getAttribute("activar") %>');" -->
<body>
<iframe id='ifrmPr' src='#' style="width:0px; height:0px; border: none; background:transparent"></iframe>
<div id="div-body">
<table width="940" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td class="menuSuperior" colspan="2" valign="top" height="20">
    		<a href="javascript:muestraCalendario();" class="menuSuperior"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" /></a>&nbsp;<a href="javascript:muestraCalendario();" class="menuSuperior">Calendario</a>
    		&nbsp;&nbsp;
    	</td>
   	</tr>
	<tr>
		<td align="left" valign="top" width="185">
			<tiles:insert attribute="left_menu"/>
		</td>
        <td align="center" valign="top" style="padding-top:25px;">
        	<table width="100%" border="0" cellpadding="1" cellspacing="0">
	        	<tr>
	        		<td valign="top">
	        			<div style="width: 590px;">
				       		<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
					           	<tr>
					             	<td width="75%" height="20" align="left" valign="top">
					             		<c:set var="totalpath" value="0"></c:set>
						             	<c:forEach var="path" items="${lista}">
						             		<c:set var="totalpath" value="${totalpath + 1}"></c:set>
						             	</c:forEach>
						             	<c:if test="${totalpath > 1}">
						             	<c:forEach var="path" items="${lista}">
						             		<c:choose>
							             			<c:when test="${path.actual}"><a href="#"><span class="links"><tiles:useAttribute name="nombre" scope="session"/><c:out value="${nombre}"/></span></a></c:when>
							             			<c:otherwise><a href="<c:out value="${path.url}" />" class="links"><c:out value="${path.nombre}" /></a>&nbsp;&gt;</c:otherwise>
						             		</c:choose>
						             	</c:forEach>
							             	<br/>
						             	</c:if>
						             	<span class="titulo"><tiles:useAttribute name="nombre" scope="session"/><c:out value="${nombre}"/></span>
									</td>
					             	<td width="55%" align="right" class="tit-usuario">
					             		<div style="font-size: 14px;font-weight: bolder;">
					             			Per&iacute;odo: ${periodo}
					             		</div>
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