 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>Cotizaciones Previsionales</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="common/css/estilo_formularios.css" rel="stylesheet" type="text/css" />
		<link href="common/css/collapsible_menu.css" rel="stylesheet" type="text/css" />
		<link href="common/css/grid_960.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="common/js/planillas.js"></script>
		<script language="javascript" src="Includes/FuncionesJava.js"></script>
		<script><!--
	function rut_razon(){
		
	}
	
	function AgregaRut()
	{
	  if (form1.RutTrabajador.value == "") return;
	  var fg = 1;
	  for (var i = 0; i < form1.ListaRut.children.length; i++)
	  {
	    var strCurrentValueId = form1.ListaRut.children[i].id;
	    if (form1.RutTrabajador.value == strCurrentValueId) fg = 0;
	  }
	  if (fg == 1)
	    {
	        var newOpt;
	        newOpt = document.createElement("OPTION");
	        newOpt = new Option(form1.RutTrabajador.value, form1.RutTrabajador.value);
	        newOpt.id = form1.RutTrabajador.value;
	        form1.ListaRut.add(newOpt);
	    }
	  form1.RutTrabajador.value = "";
	  form1.dv2.value = "";
	}
		
	function EliminaRut()
	{
	  for (var i = 0; i < form1.ListaRut.children.length; i++)
	  {
	    if (form1.ListaRut.children[i].selected)
	    {
	        form1.ListaRut.remove(i);
	    }
	  }
	  return;
	}
	
	function ListaRutT()
	{
	  var strRut = "";
	  for (var i = 0; i < form1.ListaRut.children.length; i++)
	  {
	    strRut = strRut + form1.ListaRut.children[i].value + " ";
	  }
	  return strRut;
	}
	
	
	function VeRut(dest,orig){
	if (isNaN(orig.value)) {
				alert("Error:\nEste campo debe tener sólo números.");
			 }
			 else
			 {
			 	document.getElementById(dest).value = JDigitoRut(orig.value);
				//document.all.item(dest).value = JDigitoRut(orig.value);
				if(JTrim(document.getElementById(dest).value) == "0" && orig.value > 1){
					document.getElementById(dest).value = "";
			 }
		
		}
	}
	
	function sendBuscar(){
	var holding= "${holding}";
		if (document.getElementById('cantidadPeriodos').checked == true) {
			document.form1.listaRut.value=ListaRutT();
			document.form1.sucursal.value=form1.Sucursal.value;
			document.form1.holdingA.value=holding.replace(/\./g,',');
			document.form1.aux.value="1";
			document.form1.FechaProceso.value="${periodoant}";
			document.form1._folder.value="Certificado Cotizaciones";
			document.form1._accion.value="Buscar";
			document.form1.target="LIST";
			document.form1.action="listMonth";
			document.form1.submit();
		} else if (document.getElementById('periodoFecha').checked == true) {
		
			if (document.getElementById('FechaProceso').value!="" && document.getElementById('FechaProceso2').value!="") {
				document.form1.listaRut.value=ListaRutT();
				document.form1.sucursal.value=form1.Sucursal.value;
				document.form1.holdingA.value=holding.replace(/\./g,',');
				document.form1.aux.value="";
				document.form1.FechaProceso.value="${periodoant}";
				document.form1._folder.value="Certificado Cotizaciones";
				document.form1._accion.value="Buscar";
				document.form1.target="LIST";
				document.form1.action="listMonth";
				document.form1.submit();
			}
			else
			{
				alert ("Debe ingrfesar Fechas de busqueda");
				return false;
			}
			
		} else {
			alert ("Debe marcar una de las opciones de búsqueda de periodos");
			return false;
		}
	}
	
	function descargarPDF(){
	var holding= "${holding}";
		if (document.getElementById('cantidadPeriodos').checked == true) {
			document.form1.listaRut.value=ListaRutT();
			document.form1.sucursal.value=form1.Sucursal.value;
			document.form1.holdingA.value=holding.replace(/\./g, ",");
			document.form1.aux.value="1";
			document.form1.FechaProceso.value="${periodoant}";
			document.form1._folder.value="Certificado Cotizaciones";
			document.form1._accion.value="Bajar Zip"
			document.form1.target="LIST"
			document.form1.action="DescargarPdf"
			document.form1.submit();
		} else if (document.getElementById('periodoFecha').checked == true) {
			document.form1.listaRut.value=ListaRutT();
			document.form1.sucursal.value=form1.Sucursal.value;
			document.form1.holdingA.value=holding.replace(/\./g,',');
			document.form1.aux.value="";
			document.form1.FechaProceso.value="${periodoant}";
			document.form1._folder.value="Certificado Cotizaciones";
			document.form1._accion.value="Bajar Zip";
			document.form1.target="LIST";
			document.form1.action="DescargarPdf";
			document.form1.submit();
		} else {
			alert ("Debe marcar una de las opciones de búsqueda de periodos");
			return false;
		}
	}
	
	function DejaRut(){
		if(form1.RutTrabajador.value != ""){
			form1.NombreTrabajador.value = "";
			form1.NombreTrabajador.disabled = true;
		}else{
			form1.NombreTrabajador.disabled = false;
			form1.dv2.value = ""
		}
	
	}
		
	function DejaNombre(){
		if(JTrim(form1.NombreTrabajador.value) != ""){
			form1.RutTrabajador.value = "";
			form1.dv2.value = "";
			form1.RutTrabajador.disabled = true;
		}else{
			form1.RutTrabajador.disabled = false;
		}
	}
	
	function Aceptar(){
		window.parent.frames['frmResultado'].document.all.TipoProceso.value = form1.TipoProceso.value;
		window.parent.frames['frmResultado'].document.all.RutEmpresa.value = form1.RutEmpresa.value;
		window.parent.frames['frmResultado'].document.all.RutTrabajador.value = ListaRutT();
	
		if(form1.RutEmpresa.value == "" && form1.RutTrabajador.value == "" && form1.NombreTrabajador.value == ""){
			window.parent.frames['frmResultado'].document.all.NombreTrabajador.value = "%";
		}else{
			window.parent.frames['frmResultado'].document.all.NombreTrabajador.value = form1.NombreTrabajador.value;
		}
	var holding= "${holding}";

	
		window.parent.frames['frmResultado'].document.all.Convenio.value = form1.Convenio.value;
		window.parent.frames['frmResultado'].document.all.Sucursal.value = form1.Sucursal.value;
		window.parent.frames['frmResultado'].document.all.Holding.value = holding.replace(/\./g,' ');;
		window.parent.frames['frmResultado'].document.all.form1.submit();
	}
	
	function BajarPDF(){
		var strCondicion = "";
		var holding= "${holding}";
	        strCondicion = strCondicion + " AND Holding IN (" + holding.replace(/\./g,',') + ")";
		if (form1.TipoProceso.selectedIndex != 0) {
			strCondicion = strCondicion + " AND TipoProceso = ''" + form1.TipoProceso.value + "''";
	    }
	
		if (ListaRutT() != "") {
			strCondicion = strCondicion + " AND RutTrabajador in (" + JTrim(ListaRutT()) + ")";
	    }
	
	        strCondicion = strCondicion + " AND Convenio IN (" + JTrim(form1.Convenio.value) + ")";
	
		if (form1.Sucursal.value != "") {
			strCondicion = strCondicion + " AND Sucursal IN (''" + JTrim(form1.Sucursal.value) + "'')";
	    }
	
		if (form1.RutEmpresa.selectedIndex == 0) {
			aux = form1.RutEmpresa.value;
			largo = aux.length;
			aux = aux.substring(0, largo - 1);
		} else {
			aux = form1.RutEmpresa.value;
		}
		strCondicion = strCondicion + " AND RutEmpresa IN (" + aux + ")";
		
		window.parent.frames['frmDownload'].document.all.inTipo.value = "8";
		window.parent.frames['frmDownload'].document.all.inCondicion.value = strCondicion;
		window.parent.frames['frmDownload'].document.all.form2.submit();
	}
	
	function PrintPDF(){
		var strCondicion = "";
		var holding= "${holding}";
		if (form1.TipoProceso.selectedIndex != 0) {
			strCondicion = strCondicion + " AND TipoProceso = ''" + form1.TipoProceso.value + "''";
	    }
	
		if (ListaRutT() != "") {
			strCondicion = strCondicion + " AND RutTrabajador in (" + JTrim(ListaRutT()) + ")";
	    }
	
		if (form1.Sucursal.value != "") {
			strCondicion = strCondicion + " AND Sucursal IN (''" + JTrim(form1.Sucursal.value) + "'')";
	    }
		strCondicion = strCondicion + " AND Holding IN (" + holding.replace(/\./g,',') + ")";
	
	    strCondicion = strCondicion + " AND Convenio IN (" + JTrim(form1.Convenio.value) + ")";
	
		if (form1.RutEmpresa.selectedIndex == 0) {
			aux = form1.RutEmpresa.value;
			largo = aux.length;
			aux = aux.substring(0, largo - 1);
		} else {
			aux = form1.RutEmpresa.value;
		}
		strCondicion = strCondicion + " AND RutEmpresa IN (" + aux + ")";
	
		window.parent.frames['frmPrint'].document.all.inTipo.value = "8";
		window.parent.frames['frmPrint'].document.all.inCondicion.value = strCondicion;
		window.parent.frames['frmPrint'].document.all.form3.submit();
		}
		function sizeRows() 
		{
			var frameset = parent.document.getElementById("main_frame"); 
			frameset.rows = "270,*"; 
		} 
		
		function borrarTdPeriodo() 
		{
			/*if (form1.cantidadPeriodos.checked) {
				form1.FechaProceso.disabled = true;
				form1.FechaProceso2.disbled = true;
				form1.selectCantidadPeriodos.disabled = false;
			}*/
			if (document.getElementById('cantidadPeriodos').checked) {
				document.getElementById('FechaProceso').value="";
				document.getElementById('FechaProceso2').value="";
				document.getElementById('FechaProceso').readOnly = true;
				document.getElementById('FechaProceso2').readOnly = true;
				document.getElementById('selectCantidadPeriodos').disabled = false;
			}
		}
		function borrarTdRango() 
		{
			if (document.getElementById('periodoFecha').checked) {
				document.getElementById('FechaProceso').readOnly = false;
				document.getElementById('FechaProceso2').readOnly = false;
				document.getElementById('selectCantidadPeriodos').disabled = true;
				
			}
		}
		function limpiar(){
			form1.RutTrabajador.disabled = false;
			form1.NombreTrabajador.disabled = false;
			form1.ListaRut.options.length=0;
		}
		function init(){
			window.open('CertifCotiza_Obj.htm','frmResultado');
			window.open('Download.htm','frmDownload');
			window.open('Print.htm','frmPrint');
			sizeRows();
			borrarTdPeriodo();
		}
	</script>
	</head>
<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3" onload="init();">

		<FORM id="form1" method="post" name="form1">
		<input type="hidden" name="listaRut">
		<input type="hidden" name="sucursal">
		<input type="hidden" name="holdingA">
		<input type="hidden" name="aux">
			<font class="titulo">Solicitud Certificado Cotizaciones</font>
				<br/>
				<br/>
				<font face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br></font>
				<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
					<tr>
						<td width="18%" height="29" align="left" valign="middle">Tipo Proceso :</td>
						<td width="27%" align="left" valign="middle"><strong>	
							<select name="TipoProceso" size="1" id="TipoProceso" class="campos">
								<option value="T">Todos</option>
								<option value="R">Remuneraciones</option>
								<option value="G">Gratificaciones</option>
								<option value="D">Dep. Convenidos</option>
								<option value="L">Reliquidaciones</option>
								<option value="S">SIL</option>
							 </select>
							</strong>
						</td>
						<td align="left" valign="middle" nowrap="nowrap">Rut Empresa :</td>
						<td align="left" valign="middle">
							<select name='RutEmpresa' id='RutEmpresa' size='1' style='font-family:Arial;font-size:7pt;' onChange='rut_razon()'>
								<option value='${allRuts}'>--Todos--</option>
								<c:forEach var="empresa" items="${listEmpresas}">
								<option value='${empresa.rutint}'>${empresa.rut}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="left" valign="middle">Convenio :</td>
						<td align="left" valign="middle">
							<c:choose>
								<c:when test="${allConvenios!=''}">
									<select name='Convenio' id='Convenio' size='1' style='font-family:Arial;font-size:7pt;'>
									<option value='${allConvenios}'>--Todos--</option>
									<c:forEach var="convenios" items="${listConvenios}">
									<option value='${convenios}'>${convenios}</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<input type='text' id='Convenio' size='6' maxlength='6' style='font-family:Arial;font-size:8pt;' onkeypress="JValidaCaracter('Numerico','');">
								</c:otherwise>
							</c:choose>
						</td>
						<td align="left" valign="middle">Sucursal :</td>
						<td align="left" valign="middle">
							<c:choose>
								<c:when test="${allSucursales!=''}">
									<select name='Sucursal' id='Sucursal' size='1' style='font-family:Arial;font-size:7pt;'>
									<option value='${allSucursales}'>--Todas--</option>
									<c:forEach var="sucursal" items="${listSucursales}">
									<option value='${sucursal}'>${sucursal}</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<input type='text' id='Sucursal' size='6' maxlength='6' style='font-family:Arial;font-size:8pt;'>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="left" valign="middle" nowrap="nowrap"><br/>Rut Trabajador : </td>
					  	<td align="left" valign="middle" nowrap="nowrap">
							<input name="RutTrabajador" type="text" id="RutTrabajador" style="font-family:Arial;font-size:8pt;" onblur="DejaRut();" onchange="VeRut('dv2',this);" onkeypress="JValidaCaracter('Numerico','');" size="14" maxlength="9">&nbsp;-&nbsp;
   							<input id="dv2" type="text" size="1" maxlength="1" style="font-family:Arial;font-size:8pt;" disabled>
   							<input type="button" value=" >> " class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="AgregaRut();">
   							<input type="button" value=" Borra " class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="EliminaRut();">
   						</td> 
						<td align="left" valign="middle" nowrap="nowrap">Nombre Trabajador :</td>
						<td>
							<input name="NombreTrabajador" type="text" id="NombreTrabajador" style="font-family:Arial;font-size:8pt;" onblur="DejaNombre();" onkeypress="JValidaCaracter('Texto','');" size="30" maxlength="40">
						</td>
					</tr>
					<tr>
						<td align="left" valign="middle" nowrap="nowrap">
							<input type="radio" id="cantidadPeriodos" onclick='borrarTdPeriodo()' name="periodo" value="cantidadPeriodos" checked="checked"> Cantidad de periodos:
						</td>
						<td align="left" valign="middle">
							<select	id="selectCantidadPeriodos" name="ListaMeses" size="1" class="campos">
								<option value="6" selected="selected">Todos</option>
								<option value="0">&Uacute;ltimo Per&iacute;odo(1 Mes)</option>
								<option value="1">&Uacuteltimos 12 Meses</option>
								<option value="2">&Uacuteltimos 24 Meses</option>
								<option value="3">&Uacuteltimos 36 Meses</option>
								<option value="4">&Uacuteltimos 48 Meses</option>
								<option value="5">&Uacuteltimos 60 Meses</option>
							</select>
						</td> 
   						 <td>
   							  
 						</td>
 					  	<td rowspan="2" align="left" valign="middle">
 					  		
   						  	<P><SELECT name="ListaRut" id="ListaRut" size="8" style="WIDTH:100px; HEIGHT:80px"></SELECT></P>
   					  </td>
   					</tr>
   					<tr>
   						<td align="left" valign="middle" nowrap="nowrap">
   							<input type="radio" id="periodoFecha" name="periodo" onclick='borrarTdRango()' value="periodo"> Período :
   						</td>
	                  	<td align="left" valign="middle" nowrap="nowrap">Desde
	                  		<strong>
								<input id="FechaProceso" type="text" class="campos"  name="fechaProceso" style="font-family:Arial;font-size:8pt;" size="10" maxlength="6" onkeypress="JValidaCaracter('Numerico','');" onblur="ValidaFecha(this); ValidaRango(this, FechaProceso2); ValidaFeinicotiza(this, FechaProceso2)">&nbsp;
					 		</strong>
					 		Hasta
					 		<strong>
								<input id="FechaProceso2" type="text" class="campos"  name="FechaProceso2" style="font-family:Arial;font-size:8pt;" size="8" maxlength="10" onkeypress="JValidaCaracter('Numerico','');" onblur="ValidaFecha(this); ValidaRango(fechaProceso, this); ValidaFeinicotiza(fechaProceso, this)">
								<span class="fecha_cuadro">(aaaamm)</span> 
							</strong>
						</td>
						<td align="left" valign="top">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							
						</td>
					</tr>
					<tr>
					<td colspan="4" align="center">
						<input type="button" value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="sendBuscar();">
						<input type="reset" value="Limpiar" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="limpiar();">
						<input type="button" value="Bajar PDF" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="descargarPDF();">
					</td>
				</tr>
			</table>
			<input type="hidden" value="" name="_folder">
			<input type="hidden" value="" name="FechaProceso">
			<input type="hidden" value="" name="_accion">
		</FORM>
	<script>
	</script>
</body>
</html>
 