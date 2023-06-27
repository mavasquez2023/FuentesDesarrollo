<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style type="text/css">
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 70.0%; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.buttonbold { font-family: Arial, Helvetica, sans-serif; font-size: 10px; }

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>
<SCRIPT language="JavaScript" type="text/javascript">

	function continuar(){
		var continua = false;
		var rut	= document.getElementById("rut");
		var i;
  	  	for (i=0;i<document.proxyForm.empresaElegida.length;i++){	
	  		if (document.proxyForm.empresaElegida[i].value==rut.value){
	  			document.proxyForm.empresaElegida[i].checked = true;
	  			continua = true;
	  			break;
	  		}	
	  	}
	  	if (continua){
	  		document.proxyForm.submit();
	  	}else{
	  		alert("No existe este Rut en la lista de sus Empresas...");
	  		rut.focus();
	  	}
	}
	
	var nav4 = window.Event ? true : false;
	function acceptNum(evt){	
		// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57	
		var key = nav4 ? evt.which : evt.keyCode;
		if (key != 13){
			return (key < 13 || (key >= 48 && key <= 57));
		}
	}
	
</Script>

<table width="160px" cellspacing="0" cellpadding="0" >
	<tbody>
		<tr>
			<td style="width: 5%"></td>
			
			<td style="width : 90%;">			
				
				<table cellpadding="0" cellspacing="0" border="0" width="164px">
					<tbody>
						<tr>
							<td ><html:img page="/img/c.gif" alt="" style="width: 1px; height: 12px;" /></td>
						</tr>
						<tr>
							<td style="height: 4x; font-size: 8px;">&nbsp;</td>
						</tr>
						<tr>
							<td class="navleftgroup1" style="height: 10x;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rut Empresa</td>
						</tr>
						<tr>
							<td style="height: 8x; font-size: 8px;">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="rut" size="8" maxlength="8" value="" onkeypress="return acceptNum(event)"/></td>
						</tr>
						<tr>
							<td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;Rut sin d&iacute;gito verificador&nbsp;)</td>
						</tr>
						<tr>
							<td style="height: 15x; font-size: 8px;">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				
			</td>
			<td style="width : 5%;"></td>	
		</tr>
	</tbody>
</table>
<table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="150">
	<tr>
		<td style="text-align: center;">
			<input type="button" class="buttonbold" name="continuar" value="Aceptar" onclick="JavaScript:continuar()" />
		</td>
	</tr>
</table>
