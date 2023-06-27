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
		<script language="javascript" src="Includes/FuncionesJava.js"></script>
		<script language="javascript" src="Includes/jquery.js"></script>
		<script language="javascript" src="Includes/jQuery.download.js"></script>
		<script language="javascript" src="common/js/planillas.js"></script>
		<script>
			
	function Aceptar(){
		window.parent.frames['frmResultado'].document.all.TipoProceso.value = form1.TipoProceso.value;
		if (form1.FechaProceso2.value != ""){
			window.parent.frames['frmResultado'].document.all.Periodo.value = form1.FechaProceso.value + " " + form1.FechaProceso2.value;
		}else{
			window.parent.frames['frmResultado'].document.all.Periodo.value = form1.FechaProceso.value;
		}
		if (form1.FechaProceso.value == ""){
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
		window.parent.frames['frmResultado'].document.all.Holding.value = holding.replace(/\./g,' ');
		window.parent.frames['frmResultado'].document.all.form1.submit();
	}
		function sizeRows() 
		{
			var frameset = parent.document.getElementById("main_frame"); 
			frameset.rows = "185,*"; 
		} 
		
			var frameset = parent.document.getElementById("main_frame"); 
			frameset.rows = "120,*"; 
		
		window.parent.frames['main_frame'].rows = "120,*";
	</script>
	<script languaje="javascript">
		$(function(){
			$('#holdingA').val($.trim("${holding}").replace(/\./g,','));
			
			$("#bsearch").click(function(){
				$("#form1").attr("action","/Publicacion/CertificadoAnualData");
				$("#form1").attr("method","post");
				$("#form1").attr("target","LIST");
				$("#form1").submit();
			});
			
			/************************SETEO DE AÑOS************************/
			var f = new Date();
			var years = '';
			
			for(var i=2012;i<=f.getFullYear();i++){
				years += i + ' ';
			}
			
			years = $.trim(years);
			//$("#years").append("<option value='"+years+"'>--Todos--</option>");
			
			for(var i=2012;i<=f.getFullYear();i++){
				$("#years").append("<option value='"+i+"'>"+i+"</option>");
			}
			/**************************************************************/
		});
	</script>
	</head>
<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3" onload="window.open('fblank.htm','LIST');window.open('ComprobantePago_Obj.htm','frmResultado');window.open('Download.htm','frmDownload');">
	
	
		<FORM id="form1">
		<input type="hidden" value="Certificado Anual Sence" name="_folder">
			<font class="titulo">Certificado Anual Sence</font>
			<br/>
			<br/>
			<input type="hidden" name="holdingA" id="holdingA">
			<font face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br></font>
			<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">

				<tr>
					<td align="left" valign="middle" nowrap="nowrap">Ingrese el A&ntilde;o :</td>
					<td align="left" valign="middle">
						<select name="years" id="years" style="font-size: 7pt;font-family: Arial;">
						</select>
					</td>
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
				<tr style="display:none">
					<td align="left" valign="middle" nowrap="nowrap">Número Comprobante :</td>
					<td>
						<input id="NumeroComprobante" type="text" size="16" maxlength="14" style="font-family:Arial;font-size:8pt;" onkeypress="JValidaCaracter('Numerico','');" onblur="validaComprobante();">
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
					<td colspan="4" align="center">
						<input type="button" id="bsearch" value="Buscar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
						<input type="reset" value="Limpiar" class="btn1" style="Cursor:Pointer; Cursor:Hand">
					</td>
				</tr>
				
			</table>
		</FORM>
</body>
</html>
