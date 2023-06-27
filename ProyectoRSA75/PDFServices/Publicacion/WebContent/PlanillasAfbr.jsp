
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Solicitud Planillas AFBR</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="common/css/estilo_formularios.css" rel="stylesheet"
	type="text/css" />
<link href="common/css/collapsible_menu.css" rel="stylesheet"
	type="text/css" />
<link href="common/css/grid_960.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="common/js/planillas.js"></script>
<script language="javascript" src="Includes/FuncionesJava.js"></script>
<script type="text/javascript">
	
	function limpiar(){
	
     form1.action="LimpiarGrilla";
     form1.target="LIST";
     form1.submit();
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
	 
		function sizeRows() 
		{
			var frameset = parent.document.getElementById("main_frame"); 
			frameset.rows = "270,*"; 
		} 
		
	 
		 
	
 
	function sendBuscar(){
	var holding= "${holding}";
		  

			document.form1.holdingA.value=holding.replace(/\./g,',');
			document.form1.aux.value="1";
			document.form1.FechaProceso.value="${periodoant}";
			document.form1._folder.value="Certificado Cotizaciones";
			document.form1._accion.value="Buscar";
			document.form1.target="LIST";
			document.form1.action="ListaEmpresasAfbr";
			document.form1.submit();
	  
	}
	
	function descargarPDF(){
	var holding= "${holding}";
		 
			
			document.form1.holdingA.value=holding.replace(/\./g,',');
			document.form1.aux.value="";
			document.form1.FechaProceso.value="${periodoant}";
			document.form1._folder.value="Certificado Cotizaciones";
			document.form1._accion.value="Bajar Zip";
			document.form1.target="LIST";
			document.form1.action="DescargarZip";
			document.form1.submit();
		 
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
	
	//permite key presses de solo números.
     


     function isKeyNum(event){
     if (event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==8 || event.keyCode==46 || event.charCode>=48 && event.charCode<=57)
     return true;
      
     else return false;
     }
	
	function rutEmpresa(){
	
	var id_Rut= document.getElementById('RutEmpresa').value;
	if(id_Rut=='${allRuts}')
	{ 
	document.getElementById('RazonSocial').value=0;
	}
	else
	{
	document.getElementById('RazonSocial').value=id_Rut;
	}
	}
	
	function rutRazon(){
	var id_Razon = document.getElementById('RazonSocial').value; 
	if(id_Razon==0){
	    document.getElementById('RutEmpresa').value='${allRuts}';
	 }
	 else
	 {
	 document.getElementById('RutEmpresa').value=id_Razon;
	 }
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
		
		function init(){
			window.open('CertifCotiza_Obj.htm','frmResultado');
			window.open('Download.htm','frmDownload');
			window.open('Print.htm','frmPrint');
			sizeRows();
			borrarTdPeriodo();
		}
	</script>
</head>

<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3" onload="init()">

<FORM id="form1" method="post" name="form1"><input type="hidden"
	name="listaRut"> <input type="hidden" name="FechaProceso">
<input type="hidden" name="sucursal"> <input type="hidden"
	name="holdingA"> <input type="hidden" name="aux"> <input
	type="hidden" name="holding" value="${holding}"> <font
	class="titulo">Solicitud Planillas AFBR</font> <font
	face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br>
</font> <br />
<table width="685" border="0" cellspacing="5" cellpadding="0"
	id="tabla_cont">
	<tr>
		<td width="18%" height="29" align="left" valign="middle">Tipo
		Proceso :</td>
		<td width="36%" align="left" valign="middle"><strong> <select
			name="TipoProceso" size="1" id="TipoProceso" class="campos">
			<option value="T">Todos</option>
			<option value="R">Remuneraciones</option>
			<option value="G">Gratificaciones</option>
			<option value="D">Dep. Convenidos</option>
			<option value="L">Reliquidaciones</option>
			<option value="S">SIL</option>
		</select> </strong></td>
		<td align="left" valign="middle" nowrap="nowrap" width="70">Periodo:</td>
		<td align="left" valign="middle" nowrap="nowrap">Desde <strong>
		<input id="FechaProceso" type="text" class="campos"
			name="fechaProceso" style="font-family:Arial;font-size:8pt;"
			value="${periodoant}" size="10" maxlength="6"
			onkeypress="return isKeyNum(event)"
			onblur="ValidaFecha(this);ValidaFeinicotiza(this, FechaProceso2)">&nbsp;
		</strong> Hasta <strong> <input id="FechaProceso2" type="text"
			value="${periodo}" class="campos" name="FechaProceso2"
			style="font-family:Arial;font-size:8pt;" size="8" maxlength="10"
			onkeypress="return isKeyNum(event)"
			onblur="ValidaFecha(this); ValidaRango(fechaProceso, this); ValidaFeinicotiza(fechaProceso, this)">
		<span class="fecha_cuadro">(aaaamm)</span> </strong></td>

	</tr>

	<tr>
		<td align="left" valign="middle" nowrap="nowrap">Rut Empresa:</td>
		<td align="left" valign="middle"><select name='RutEmpresa'
			id='RutEmpresa' size='1' style='font-family:Arial;font-size:7pt;'
			onChange='rutEmpresa()'>
			<option value='${allRuts}'>--Todos--</option>
			<c:forEach var="empresa" items="${listEmpresas}">
				<option value='${empresa.rutint}'>${empresa.rut}</option>
			</c:forEach>
		</select></td>
		<td align="left" valign="middle" nowrap="nowrap" width="70">Raz&oacute;n
		Social:</td>
		<td width="231"><select name='RazonSocial' id="RazonSocial"
			size='1' style='font-family:Arial;font-size:7pt;width:220px'
			onclick="rutRazon()">
			<option value='0'>--Todos--</option>
			<c:forEach var="empresa" items="${listEmpresas}">
				<option value='${empresa.rutint}'>${empresa.razonSocial}</option>
			</c:forEach>
		</select></td>

	</tr>
	<tr>
		<td align="left" valign="middle">Convenio :</td>
		<td align="left" valign="middle"><c:choose>
			<c:when test="${allConvenios!=''}">
				<select name='Convenio' id='Convenio' size='1'
					style='font-family:Arial;font-size:7pt;'>
					<option value='${allConvenios}'>--Todos--</option>
					<c:forEach var="convenios" items="${listConvenios}">
						<option value='${convenios}'>${convenios}</option>
					</c:forEach>
				</select>
			</c:when>
			<c:otherwise>
				<input type='text' id='Convenio' size='6' maxlength='6'
					style='font-family:Arial;font-size:8pt;'
					onkeypress="JValidaCaracter('Numerico','');">
			</c:otherwise>
		</c:choose></td>
		<td align="left" valign="middle" width="70"></td>
		<td align="left" valign="middle" width="231"></td>
	</tr>
	<tr>
		<td align="left" valign="middle" nowrap="nowrap"></td>
		<td align="left" valign="middle"></td>
		<td width="70"></td>
		<td rowspan="2" align="left" valign="middle" width="231"></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="134"><input type="button"
			value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand"
			onclick="sendBuscar();"> <input type="reset" value="Limpiar"
			class="btn1" style="Cursor:Pointer; Cursor:Hand" onclick="limpiar()">
		<input type="button" value="Bajar ZIP" class="btn1"
			style="Cursor:Hand" onclick="descargarPDF();"></td>
	</tr>
</table>
<input type="hidden" value="" name="_folder"> <input
	type="hidden" value="" name="_accion"></FORM>


</body>
</html>
