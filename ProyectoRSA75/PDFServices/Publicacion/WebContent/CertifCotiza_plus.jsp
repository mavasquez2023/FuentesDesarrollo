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
	<style>
	#filtrobasico    {
	VISIBILITY: visible;
	POSITION: absolute;
	TOP: 83px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-color: #BEBEBE;
	border-bottom-color: #BFBFBF;
	border-left-color: #BEBEBE;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	width: 242px;
	padding-left: 0px;
	padding-top: 0px;
	padding-right: 0px;
	padding-bottom: 15px;
	font-size: 12px;
	left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	}
	#filtroava {
	LEFT: 0px;
	VISIBILITY: hidden;
	POSITION: absolute;
	TOP: 83px;
	border-right-color: #C0C0C0;
	border-bottom-color: #C0C0C0;
	border-left-color: #C0C0C0;
	width: 242px;
	font-size: 12px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	padding: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	}
</style>
		
	</head>
<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3" onload="window.open('CertifCotiza_Obj.htm','frmResultado');window.open('Download.htm','frmDownload');window.open('Print.htm','frmPrint');sizeRows()">
	<script>
	function rut_razon(){
		
	}
	function filtro(estado){

		if(estado == "on"){
			filtroava.style.visibility= "visible";
			filtrobasico.style.visibility= "hidden";
			form1.filtroavanzado.value="on";
			window.parent.frames['frmFormulario'].rows="450,*";
		}else{
			filtroava.style.visibility= "hidden";
			filtrobasico.style.visibility= "visible";
			form1.filtroavanzado.value="off";
			window.parent.frames['frmFormulario'].rows="250,*";
			limpiarFiltros();
		}
	}
	function limpiarFiltros(){

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
		document.all.item(dest).value = JDigitoRut(orig.value);
		if(JTrim(document.all.item(dest).value) == "0" && orig.value > 1){
			document.all.item(dest).value = "";
		}
	}
	
	function sendBuscar(){
	document.form1.listaRut.value=ListaRutT();
	document.form1.sucursal.value=form1.Sucursal.value;
	document.form1.holdingA.value=window.parent.frames['frmPresenta'].document.all.ocuHolding.value;
	document.form1.target="LIST"
	document.form1.action="listMonth"
	document.form1.submit();
	}
	
	function descargarPDF(){
	document.form1.listaRut.value=ListaRutT();
	document.form1.sucursal.value=form1.Sucursal.value;
	document.form1.holdingA.value=window.parent.frames['frmPresenta'].document.all.ocuHolding.value;
	document.form1.target="LIST"
	document.form1.action="DescargarPdf"
	document.form1.submit();
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
		alert('rutEmpresa:'+form1.RutEmpresa.value);		
		window.parent.frames['frmResultado'].document.all.RutTrabajador.value = ListaRutT();
	
		if(form1.RutEmpresa.value == "" && form1.RutTrabajador.value == "" && form1.NombreTrabajador.value == ""){
			window.parent.frames['frmResultado'].document.all.NombreTrabajador.value = "%";
		}else{
			window.parent.frames['frmResultado'].document.all.NombreTrabajador.value = form1.NombreTrabajador.value;
		}
	
		window.parent.frames['frmResultado'].document.all.Convenio.value = form1.Convenio.value;
		alert('Convenio:'+form1.Convenio.value);	
		
		window.parent.frames['frmResultado'].document.all.Sucursal.value = form1.Sucursal.value;
		window.parent.frames['frmResultado'].document.all.Holding.value = window.parent.frames['frmPresenta'].document.all.ocuHolding.value;
		window.parent.frames['frmResultado'].document.all.form1.submit();
	}
	
	function BajarPDF(){
		var strCondicion = "";
	        strCondicion = strCondicion + " AND Holding IN (" + window.parent.frames['frmPresenta'].document.all.ocuHolding.value.replace(" ", ",") + ")";
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
		if (form1.TipoProceso.selectedIndex != 0) {
			strCondicion = strCondicion + " AND TipoProceso = ''" + form1.TipoProceso.value + "''";
	    }
	
		if (ListaRutT() != "") {
			strCondicion = strCondicion + " AND RutTrabajador in (" + JTrim(ListaRutT()) + ")";
	    }
	
		if (form1.Sucursal.value != "") {
			strCondicion = strCondicion + " AND Sucursal IN (''" + JTrim(form1.Sucursal.value) + "'')";
	    }
		strCondicion = strCondicion + " AND Holding IN (" + window.parent.frames['frmPresenta'].document.all.ocuHolding.value.replace(" ", ",") + ")";
	
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
	</script>
	<script language="javascript" src="Includes/FuncionesJava.js"></script>
		<FORM id="form1" method="post" name="form1">
		<input type="hidden" name="listaRut">
		<input type="hidden" value="Certificado Cotizaciones Holding: Certificado Cotizaciones" name="_folder">
		<input type="hidden" name="sucursal">
		<input type="hidden" name="holdingA">
			<font class="titulo">Solicitud Certificado Cotizaciones</font>
				<br/>
				<br/>
				<font face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br></font>
				
				<table width="652" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
					<tr>
						<td align="left" valign="middle" nowrap="nowrap">Rut Empresa :</td>
						<td align="left" valign="middle">
							<select name='RutEmpresa' id='RutEmpresa' size='1' style='font-family:Arial;font-size:7pt;' onChange='rut_razon()'>
							<option value='${allRuts}'>--Todos--</option>
							<c:forEach var="empresa" items="${listEmpresas}">
							<option value='${empresa.rutint}'>${empresa.rut}</option>
							</c:forEach>
							</select>
						</td>
						<td align="left" valign="middle" nowrap="nowrap"><br/>Rut Trabajador : </td>
					  	<td align="left" valign="middle" nowrap="nowrap">
						  <input name="RutTrabajador" type="text" id="RutTrabajador" style="font-family:Arial;font-size:8pt;" onblur="DejaRut();" onchange="VeRut('dv2',this);" onkeypress="JValidaCaracter('Numerico','');" size="14" maxlength="9">&nbsp;-&nbsp;
   						  <input id="dv2" type="text" size="1" maxlength="1" style="font-family:Arial;font-size:8pt;" disabled>
   						</td>
					</tr>
				</table>
			<div id="filtrobasico">
				<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
					<tr>
					<td colspan="3" align="center">
						<input type="button" value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="sendBuscar();">
						<input type="reset" value="Limpiar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
						<input type="button" value="Bajar PDF" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="descargarPDF();">
					</td>
					<td align="right"><a href="javascript:filtro('on')"><IMG src="Images/mas.gif"
						border="0" align="middle">Ver filtro avanzado</a>
					</td>
					</tr>
				</table>
			</div>
			<div id="filtroava">
				<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
						
					<tr>
						<td align="left" valign="middle">Convenio :
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
								<input type='text' id='Sucursal' size='6' maxlength='6' style='font-family:Arial;font-size:8pt;'>
							</c:otherwise>
							</c:choose>
						</td>
						<td align="left" valign="middle">Sucursal :
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
						 <td>
   							  <input type="button" value=" >> " class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="AgregaRut();">
 						</td>
 					  	<td rowspan="2" align="left" valign="middle">
 					  		
   						  	<P><SELECT name="ListaRut" id="ListaRut" size="8" style="WIDTH:100px; HEIGHT:80px"></SELECT></P>
   					  </td>
					</tr>
					<tr>
						<td width="25%" height="29" align="left" valign="middle">Tipo Proceso :</td>
						<td width="27%" align="left" valign="middle"><strong>	
							<select name="TipoProceso" size="1" id="TipoProceso" class="campos">
								<option value="T">Todos</option>
								<option value="R">Remuneraciones</option>
								<option value="G">Gratificaciones</option>
								<option value="D">Dep. Convenidos</option>
								<option value="L">Reliquidaciones</option>
							 </select>
							</strong>
						</td>
						
						<td align="left" valign="top">
	   						  <input type="button" value=" Borra " class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="EliminaRut();">
						</td>
					</tr>
					<tr>
						<td align="left" valign="middle" nowrap="nowrap">Cantidad de periodos:</td>
				
						<td align="left" valign="middle">
							<select	name="ListaMeses" size="1" class="campos">
								<option value="6" selected="selected">Todos</option>
								<option value="0">&Uacute;ltimo Per&iacute;odo(1 Mes)</option>
								<option value="1">&Uacuteltimos 12 Meses</option>
								<option value="2">&Uacuteltimos 24 Meses</option>
								<option value="3">&Uacuteltimos 36 Meses</option>
								<option value="4">&Uacuteltimos 48 Meses</option>
								<option value="5">&Uacuteltimos 60 Meses</option>
							</select>
						</td>
	
						<td align="left" valign="middle" nowrap="nowrap">Nombre Trabajador :</td>
						<td>
							<input name="NombreTrabajador" type="text" id="NombreTrabajador" style="font-family:Arial;font-size:8pt;" onblur="DejaNombre();" onkeypress="JValidaCaracter('Texto','');" size="37" maxlength="40">
						</td>
					</tr>
					<tr>
					<td colspan="3" align="center">
						<input type="button" value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="sendBuscar();">
						<input type="reset" value="Limpiar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
						<input type="button" value="Bajar PDF" class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="descargarPDF();">
					</td>
					<td align="right"><a href="javascript:filtro('off')"><IMG src="Images/menos.gif"
						border="0" align="middle">Ocultar filtro avanzado</a>
					</td>
					</tr>					
   						
				</table>
			</div>
			<input type="hidden" id="filtroavanzado" />
		</FORM>
	<script>
	//document.all.divConvenio.innerHTML = window.parent.frames['frmPresenta'].document.all.ocuConvenio.value;
	//document.all.divRut.innerHTML = window.parent.frames['frmPresenta'].document.all.ocuRut.value;
	//document.all.divSucursal.innerHTML = window.parent.frames['frmPresenta'].document.all.ocuSucursal.value;
	</script>
</body>
</html>
 