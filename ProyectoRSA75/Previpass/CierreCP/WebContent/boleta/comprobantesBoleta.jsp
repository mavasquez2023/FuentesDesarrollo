<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Comprobantes - Boleta</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script>
var path= "<%=request.getContextPath()%>";
var montoBoleta= ${boleta};
var subTotal=0;
function Guardar(){	
	if(montoBoleta!=subTotal){
		alert("El monto de los Comprobantes debe ser igual a la Boleta");
		return;
	}
	if(confirm("¿Confirma Pagar en Tesorería los comprobantes seleccionados?")){
			form1.submit();
		}
}

function suma(monto, estado){
	if(estado){
		subTotal+= Number(monto);
		document.getElementById("montocp").innerHTML= "&#36;" + getFormatNumber(subTotal);
	}else{
		subTotal-= Number(monto);
		document.getElementById("montocp").innerHTML= "&#36;" + getFormatNumber(subTotal);
	}
	setColorOK();
}
function sumaAll(estado){
	if(estado){
		subTotal= total;
		document.getElementById("montocp").innerHTML= "&#36;" + getFormatNumber(total);
	}else{
		subTotal=0;
		document.getElementById("montocp").innerHTML= "&#36;" + 0;
	}
	setColorOK();
}

function setColorOK(){
	if(subTotal==montoBoleta){
		document.getElementById("montocp").style.color="green";
		form1.guardar.disabled= false;
	}else{
		document.getElementById("montocp").style.color="#DF0101";
		form1.guardar.disabled= true;
	}
}

function Volver(){
	window.location= path + "/VerBoletas.do"
}

</script>
</HEAD>
<BODY topmargin="0">
<form action= "CursarComprobantesBoleta.do" name="form1">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="center" valign="top">
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="3" class="titulos_formularios" align="center">Asociar Comprobantes a Boleta</td></tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="72%" valign="top">
				<TABLE border=0 width="100%">
					<tr><td>&nbsp;</td></tr>
					<tr><td width="30%"></td>
						<td width="150" class="textos-formularios1"><h3>Boleta:</h3></td><td class="textos-formularios1" align="right"><h3><div id="montobol">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${boleta}</fmt:formatNumber></div></h3></td>
						<td width="30%"></td>
					</tr>
					<tr><td width="30%"></td>
						<td width="150" class="textos-formularios1"><h3>Comprobantes:</h3></td><td class="textos-formularios1" align="right"><h3><div id="montocp" style="color: #DF0101">$0</div></h3></td>
						<td width="30%"></td>
					</tr>
					<tr><td width="30%"></td>
						<td class="textos-formularios1" colspan=2 align="center"><INPUT type="button" name="guardar" id="guardar" class="btn2"  value="Pagar" onclick="Guardar();" disabled="disabled"></td>
						<td width="30%"></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr><td colspan="7" height="22" align="center" valign="middle" class="textos_formcolor">Comprobantes A Pagar</td></tr>
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><input type="checkbox" name="idtodas" id="idtodas" onclick="selectAll(form1.idcpago, this.checked);sumaAll(this.checked);"/></td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CODIGO BARRA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RAZON SOCIAL</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CONVENIO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">NOMINA</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MONTO($)</td>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="comprobante" items="${comprobantes}">
					<tr>
						<td align="center" class="textos_formularios"><input type="checkbox" name="idcpago" id="idcpago" value="${comprobante.folioIngreso}" onclick="unSelect(form1.idtodas, this.checked);suma(${comprobante.monto}, this.checked);"/></td>
						<td align="center" class="textos_formularios">${comprobante.codigoBarra}</td>
						<td align="center" class="textos_formularios">${comprobante.rutEmpresa}</td>
						<td align="center" class="textos_formularios">${comprobante.razonSocial}</td>
						<td align="center" class="textos_formularios">${comprobante.convenio}</td>
						<td align="center" class="textos_formularios">${comprobante.tipoNomina}</td>
						<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.monto}</fmt:formatNumber></td>
						<c:set var="totalMonto" value="${totalMonto + comprobante.monto}"></c:set>
					</tr>
					</c:forEach>
					<tr><td colspan=6 align="right" class="textos_formularios">Total:</td>
					<td align="right" class="textos_formcolor"><b>&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalMonto}"/></fmt:formatNumber></b></td>
					<td align="center" class="textos_formularios">&nbsp;</td>
					</tr>
					<tr><td colspan=7>&nbsp;</td></tr>
					<tr><td align="center" colspan=7>
							<INPUT type="button" name="volver" class="btn2"  value="Volver" onclick="Volver();" >
						</td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</table>
	<script>
var total= ${totalMonto};
	</script>
	<input type="hidden" name="idBoleta" value="${idboleta}">
</form>
</BODY>
</html>
