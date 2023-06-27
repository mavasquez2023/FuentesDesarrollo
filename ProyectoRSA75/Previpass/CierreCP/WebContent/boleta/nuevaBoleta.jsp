<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="cl.araucana.cierrecpe.business.Parametros"%>
<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Boleta PAC - CierreCP</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";
var cuentas= ['' 
<c:forEach var="banco" items="${bancos}">
, '${banco.idCuenta}'
</c:forEach>
];

function Guardar(){	
	var monto= form1.monto.value;
	if(form1.rutempresa.value==""){
		alert('Ingrese Rut Empresa.');
		return;
	}else if(form1.banco.selectedIndex==0){
		alert('Seleccione un Banco.');
		return;
	}else if(monto<=0 || monto==""){
		alert('Ingrese un Monto válido para la Boleta.');
		return;
	}
	
	if(confirm("¿Confirma la generación de la boleta por $" + monto + " ?")){
			form1.cuenta.disabled=false;
			form1.rutempresa.value= quitaFormato(form1.rutempresa.value, '.');
			form1.monto.value= quitaFormato(form1.monto.value, '.-');
			form1.submit();
		}
}

function OptionBanco(indice){
	var cuenta= cuentas[indice];
	form1.cuenta.value=cuenta;
}

function VerBancos(){
	window.location= path + "/VerBancos.do"
}

</script>
</HEAD>
<BODY topmargin="0" >
<form action= "GenerarBoleta.do" name="form1">
				<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
					<tr><td colspan=4 class="titulos_formularios" align="center">Generar Nueva Boleta</td></tr>
					<tr><td width="180">&nbsp;</td></tr>
					<tr><td class="textos-formularios1" width="180">Período : <B><%=Parametros.getInstance().getParam().getPeriodoSistema()%></B>
					</td>
					<td colspan=3 class="text-11" align="right" ><a href="#" onclick="VerBancos();return false;">Bancos&nbsp;<img src="icons/bancos.gif" border="0" align="middle" alt="Lista Bancos" ></a></td>
					</tr>
					<tr><td width="180">&nbsp;</td></tr>
					<tr>
							<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=4>Ingrese Datos Boleta</td>
					</tr>
					<tr>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="180">RUT EMPRESA</td>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="210">BANCO</td>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="140">CUENTA</td>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="167">MONTO$</td>
					</tr>
					<tr>
							<td class="textos_formularios" align="center" width="180"><INPUT type="text" name="rutempresa" id="rutempresa" value="" onKeyPress="keyRut()" onKeyUp="formateaRut(this)"/></td>
							<td class="textos_formularios" align="center" width="210">
								<select name="banco" class="text-11" onchange="OptionBanco(this.selectedIndex);">
									<option value="" selected="selected">Seleccionar</option>
									<c:forEach var="banco" items="${bancos}">
										<option value="${banco.idBanco}">${banco.nombre}</option>
									</c:forEach>
								</select>
							</td>
							<td class="textos_formularios" align="center" colspan=1 width="140"><INPUT type="text" name="cuenta" id="cuenta" value="" disabled=disabled/></td>
							<td class="textos_formularios" align="center" colspan=1 width="167">$<INPUT type="text" name="monto" id="monto" value="" onKeyPress="keyNum()" onKeyUp="formateaNumber(this)"/></td>
					</tr>
					<tr><td colspan=4>&nbsp;</td></tr>
					<tr><td align="center" colspan=4>
						<INPUT type="button" name="gurdar" class="btn2"  value="Guardar" onclick="Guardar();" >
					</td></tr>
				</TABLE>
</form>
</BODY>
</html>
