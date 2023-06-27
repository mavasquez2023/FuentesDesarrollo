<%@ include file="/html/comun/taglibs.jsp"%>
<link href='<c:url value="/css/Interna_Araucana.css" />' rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>
<script language="JavaScript1.2">

	/*
	 *  CP global and user variables.
	 */
	var period = "${periodo}";
	var MAX_NOMINAS_SELECCIONABLES = 1;
	
	${tipoNominasCode}
	
	${conveniosCode}
	${empresasCode}

</script>
<script language="JavaScript1.2">
function limpiarForm(formato){
	frm = window.document.forms[0];
	frm.reset();
	frm.formato.value=formato;
}
function actualizaEnvioNom(){
	var frame =window.frames['frmUnicaNom']; 
	frame.location.reload()
}

function updateEmpresas(rutEmpresa){
	document.getElementById("empresa1").options[0] = new Option(rutEmpresa, rutEmpresa);
	window.focus();

}
function loading(){
	if( AR_checkUnicNomForm()){
	$.blockUI({
				message: '<img src="<c:url value="/img/loading.gif"/>" /><br>Recibiendo...',
				css: {
					width:           '180px',
					border:          '3px solid #85B4BE',
					backgroundColor: '#E1EBED'
				},
				overlayCSS: {
	      			backgroundColor: '#FFFFFF',
					opacity:         0.5
				}
	});
	return true;
	}else{
		return false;
	}
}
</script>
<script language="JavaScript1.2" src="js/adapted/nozipped.js"></script>
<HEAD>
<TITLE>Previpass - Envío Nómina</TITLE>
</head>
	<table border="0" width="100%" align="left" class="tabla-nomina">
		<tr>
		    <td align="left">
				<form name="senderForm" action=receiver.ado enctype="multipart/form-data" accept="text/plain" method="post" >
				<body onload="javascript:updateEmpresas(${_rutEmpresa})">  

					<input type="hidden" id="accion"    name="accion"    value="nominas">
					<input type="hidden" id="subAccion" name="subAccion" value="envioUnicaNomina">
					<input type="hidden" id="formato" name="formato" value="txt">
					<input type="hidden" name="sendMethod" value="COMPRESSION_NONE">
					<input type="hidden" name="nFiles" value="8">
					<input type="hidden" id="_cmd"    name="_cmd"    value="unicaNominas">
					<input type="hidden" id="_tipoNomina"    name="_tipoNomina"    value="${_tipoNomina}">
					<input type="hidden" id="_convenio"    name="_convenio"    value="${_convenio}">
					<input type="hidden" id="_rutEmpresa"    name="_rutEmpresa"    value="${_rutEmpresa}">

					<table border="0" width="100%" align="center" cellspacing="0" cellpadding="0">
						<tr>
							<th colspan="2" align="center">Envio de Nómina</th>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="textos_formularios">&nbsp;</td>
							<td class="textos_formularios">&nbsp;</td>
						</tr>
						<tr>
							<td class="textos_formularios">Rut :</td>
							<td class="textos_formularios">${divRutEmpresa}<div id="divRutEmpresa"></div></td>
						</tr>
						<tr>
							<td class="textos_formularios">Razón Social :</td>
							<td class="textos_formularios">${divRazonSocial}<div id="divRazonSocial"></div></td>
						</tr>
						<tr>
							<td class="textos_formularios">Tipo Proceso :</td>
							<td class="textos_formularios">${divTipoProceso}<div id="divTipoProceso"></div></td>
						</tr>
						<tr>
							<td class="textos_formularios">Convenio: </td>
							<td class="textos_formularios">${divConvenio}<div id="divConvenio"></div></td>
						</tr>
						<tr>
							<th colspan="2" align="center" class="barra_titulos">
						    <code></code>N&oacute;mina
							<th>
						</tr>
						<tr>
							<td align="left">
								<input type="hidden" name="idNomina1" id="idNomina1" value="" tabindex="1">
								<select name="empresa1" id="empresa1" tabindex="2" class="campos" style="display:none">
								</select>
							</td>
							<td align="left">
								<code>&nbsp;&nbsp;1:</code> <input type="file" name="nomina1" id="nomina1" tabindex="3" size="15">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br>
								<input type="submit" value="Enviar" onclick="loading();" tabindex="25" class="btn-destacado">
							</td>
						</tr>
					</table>
					</body>
				</form>
		    </td>
	</table>