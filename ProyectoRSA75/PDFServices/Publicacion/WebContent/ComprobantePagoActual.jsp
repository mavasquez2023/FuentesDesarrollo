<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<title>Comprobantes de Pago</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="common/css/estilo_formularios.css" rel="stylesheet" type="text/css" />
		<link href="common/css/collapsible_menu.css" rel="stylesheet" type="text/css" />
		<link href="common/css/grid_960.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="Includes/jquery.js"></script>
		<script language="javascript" src="Includes/jquery.numeric.js"></script>
		<script language="javascript" src="Includes/FuncionesJava.js"></script>
		<script language="javascript" src="common/js/planillas.js"></script>
		<script>
	
	function Aceptar(){
		window.parent.frames['frmResultado'].document.all.TipoProceso.value = form1.TipoProceso.value;
		if (form1.FechaProceso2.value != ""){
			window.parent.frames['frmResultado'].document.all.FechaProceso.value = form1.FechaProceso.value + " " + form1.FechaProceso2.value;
		}else if(form1.FechaProceso.value != ""){
			window.parent.frames['frmResultado'].document.all.FechaProceso.value = form1.FechaProceso.value + " " + "205908";
		}else{
			if ("${inicotiza}" != "") {
			    window.parent.frames['frmResultado'].document.all.FechaProceso.value= "${inicotiza}" + " " + "205908";
			}else{
				alert("Ingrese un período para la búsqueda");
				return false;
			}
		}
		var holding= "${holding}";
		window.parent.frames['frmResultado'].document.all.NumeroComprobante.value = form1.NumeroComprobante.value;
		window.parent.frames['frmResultado'].document.all.RutEmpresa.value = form1.RutEmpresa.value;
		window.parent.frames['frmResultado'].document.all.Convenio.value = form1.Convenio.value;
		window.parent.frames['frmResultado'].document.all.Holding.value = holding.replace(/\./g, " ");
		window.parent.frames['frmResultado'].document.all.form1.submit();
	}
	function BajarPDF(){
		var strCondicion = "";
		var holding= "${holding}";
	    strCondicion = strCondicion + " AND Holding IN (" + holding.replace(/\./g, ",") + ")";
		if (form1.TipoProceso.selectedIndex != 0) {
			strCondicion = strCondicion + " AND TipoProceso = ''" + form1.TipoProceso.value + "''";
	    }
		if (form1.FechaProceso.value != "") {
			strCondicion = strCondicion + " AND Periodo BETWEEN " + (cantidaddias(form1.FechaProceso.value)-5);
			if (form1.FechaProceso2.value == ""){
				strCondicion = strCondicion + " AND " + (cantidaddias(form1.FechaProceso.value)+5);
			}else{
				strCondicion = strCondicion + " AND " + (cantidaddias(form1.FechaProceso2.value)+5);
			}
		}
		if (form1.NumeroComprobante.value != "") {
			strCondicion = strCondicion + " AND NumeroComprobante = ''" + form1.NumeroComprobante.value + "''";
	    }
	    strCondicion = strCondicion + " AND Convenio IN (" + JTrim(form1.Convenio.value) + ")";
	
		if (form1.RutEmpresa.selectedIndex == 0) {
			aux = form1.RutEmpresa.value;
			largo = aux.length;
			aux = aux.substring(0, largo - 1);
		} else {
			aux = form1.RutEmpresa.value;
		}
		strCondicion = strCondicion + " AND RutEmpresa IN (" + aux + ")";
	
		window.parent.frames['frmDownload'].document.all.inTipo.value = "7";
		window.parent.frames['frmDownload'].document.all.inCondicion.value = strCondicion;
		window.parent.frames['frmDownload'].document.all.form2.submit();
		}
		function sizeRows() 
		{
			var frameset = parent.document.getElementById("main_frame"); 
			frameset.rows = "185,*"; 
		} 
	</script>
	<script languaje="javascript">
		$(function(){
			var tpdf=0;
			$('#holdingA').val($.trim("${holding}").replace(/\./g,','));

		
			$("#bsearch").click(function(){
				if($.trim($("#FechaProceso").val()).length > 0 && $.trim($("#FechaProceso2").val()).length == 0){
					$("#FechaProceso2").val($("#FechaProceso").val());
				}
			
				if($.trim($("#FechaProceso").val()).length == 0){
					alert("Debe ingresar al menos la fecha de inicio del periodo");
					return false;
				}
				
				$("#form1").attr("action","/Publicacion/ComprobantePagoData");
				$("#form1").attr("method","post");
				$("#form1").attr("target","LIST");
				$("#form1").submit();
				tpdf=1;
			});
			
			var pdf=0;
			$("#bpdf").click(function(){
				if(tpdf==1){
					var FechaProceso = $.trim($("#FechaProceso").val());
					var FechaProceso2 = $.trim($("#FechaProceso2").val());
					var RutEmpresa = $.trim($("#RutEmpresa").val()).replace(/\s/g,",");
					var NumeroComprobante = $.trim($("#NumeroComprobante").val());
					var Convenio = $.trim($("#Convenio").val()).replace(/\s/g,",");
					var holding = $.trim($("#holdingA").val()).replace(/\s/g,",");
					var TipoProceso = $.trim($("#TipoProceso").val()).replace(/\s/g,",");

					window.open("/Publicacion/pdfLoaderPagoData.jsp?FechaProceso="+FechaProceso.toString()+"&FechaProceso2="+FechaProceso2.toString()+"&RutEmpresa="+RutEmpresa.toString()+"&NumeroComprobante="+NumeroComprobante.toString()+"&Convenio="+Convenio.toString()+"&holding="+holding.toString()+"&TipoProceso="+TipoProceso.toString(),'PDFdata'+pdf.toString(),'width=800,height=630,left=0,top=100,screenX=0,screenY=100');
					pdf++;
				}else{
					alert("Debe hacer una búsqueda para generar la tabla de comprobantes");
				}
			});
			
			$("#FechaProceso,#FechaProceso2").numeric();
			
			$("#FechaProceso").keypress(function(){
				JValidaCaracter('Numerico','');
			}).blur(function(e){
				if($(this).val() < 201101 && $.trim($(this).val()).length > 0){
					alert("La fecha de inicio debe ser mayor o igual a 201101, Enero del 2011");
					$(this).val("");
				}
				if($.trim($(this).val()).length > 6){
					alert("Formato inválido, el formato es aaaamm (año y mes)");
					$(this).val("");
				}
			});
			
			$("#FechaProceso2").keypress(function(){
				JValidaCaracter('Numerico','');
			}).blur(function(e){
				if($.trim($(this).val()).length > 0 && $(this).val() < $("#FechaProceso").val()){
					alert("La fecha Final debe ser mayor que la fecha de inicio");
					$(this).val("");
				}
				if($.trim($(this).val()).length > 6){
					alert("Formato inválido, el formato es aaaamm (año y mes)");
					$(this).val("");
				}
			});
		});
	</script>
	</head>
<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3" onload="window.open('ComprobantePago_Obj.htm','frmResultado');window.open('Download.htm','frmDownload');sizeRows();">
		<FORM id="form1">
			<input type="hidden" value="Solicitud Comprobantes Pago" name="_folder">
			<input id="holdingA" type="hidden" name="holding" value="" />
			<font class="titulo">Solicitud Comprobantes Pago <span style="color:#ccc">(Enero de 2011 hacia delante)</span></font>
				<br/>
				<br/>
				<font face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br></font>
				<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
					<tr>
						<td width="18%" height="29" align="left" valign="middle">Tipo Proceso :</td>
						<td width="27%" align="left" valign="middle"><strong>	
								<select name="TipoProceso" size="1" id="TipoProceso" class="campos">
									<option value="">Todos</option>
									<option value="R">Remuneraciones</option>
									<option value="G">Gratificaciones</option>
									<option value="D">Dep. Convenidos</option>
									<option value="A">Reliquidaci&oacute;n</option>														
					  			</select>
							</strong>
						</td>
						<td align="left" valign="middle" nowrap="nowrap">Período</td>
			        	<td align="left" valign="middle" nowrap="nowrap">Desde<strong>
								<input type="text" id="FechaProceso" name="FechaProceso" value="${periodoant}" style="font-family:Arial;font-size:8pt;" size="8" maxlength="6" />&nbsp;
									</strong>Hasta<strong>
								<input type="text" id="FechaProceso2" name="FechaProceso2" value="${periodo}" style="font-family:Arial;font-size:8pt;" size="8" maxlength="6" />&nbsp;
									<span class="fecha_cuadro">(aaaamm)</span> </strong>
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle" nowrap="nowrap">Rut Empresa :</td>
					<td align="left" valign="middle">
						<c:choose>
								<c:when test="${TipoUsuario=='empresa'}">
									<select name='RutEmpresa' id='RutEmpresa' size='1' style='font-family:Arial;font-size:7pt;' onChange='rut_razon()'>
										<option value='${allRuts}'>--Todos--</option>
										<c:forEach var="empresa" items="${listEmpresas}">
											<option value='${empresa.rutint}'>${empresa.rut}</option>
										</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<input type='text' id='RutEmpresa' size='14' maxlength='12' style='font-family:Arial;font-size:8pt;' onkeypress="JValidaCaracter('Numerico','');" onchange="VeRut('dv1',this);" onblur="DejaRut();">
									&nbsp;-&nbsp;<input id='dv1' type='text' size='1' maxlength='1' style='font-family:Arial;font-size:9pt;' onkeypress="JValidaCaracter('Numerico','');" disabled>
								</c:otherwise>
						</c:choose>
					</td>
					<td align="left" valign="middle" nowrap="nowrap">Raz&oacute;n Social :</td>
					<td align="left" valign="middle">
						<c:choose>
							<c:when test="${TipoUsuario=='empresa'}">
								<select name='RazonSocial' id='RazonSocial' size='1' style='font-family:Arial;font-size:7pt;' onChange='razon_rut()'>
									<option value='${allRuts}'>--Todos--</option>
									<c:forEach var="empresa" items="${listRazonSocial}">
										<option value='${empresa.rutint}'>${empresa.razonSocial}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<input type='text' id='RazonSocial' size='28' maxlength='40' style='font-family:Arial;font-size:8pt;' onkeypress=JValidaCaracter('Texto',''); onblur=DejaRazon();>
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle" nowrap="nowrap">Número Comprobante :</td>
					<td>
						<input name="NumeroComprobante" id="NumeroComprobante" type="text" size="16" maxlength="14" style="font-family:Arial;font-size:8pt;" onkeypress="JValidaCaracter('Numerico','');" onblur="validaComprobante();">
					</td>
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
				</tr>
				<tr>
					<td colspan="3" align="right">
						<input id="bsearch" type="button" value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
						<input type="reset" value="Limpiar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
						<input id="bpdf" type="button" value="Bajar PDF" class="btn1" style="Cursor:Pointer; Cursor:Hand">
					</td>
					<td align="right">
						<span style="margin-left:100px">
							<a onclick="javascript:window.parent.frames['frmFormulario'].location='ComprobantePago.jsp';window.parent.frames['LIST'].location='fblank.htm'" style="cursor:pointer;color:blue;font-weight:normal;text-decoration: underline;">
								> Ver histórico
							</a>
						</span>					
					</td>
				</tr>
			</table>
		</FORM>
</body>
</html>
