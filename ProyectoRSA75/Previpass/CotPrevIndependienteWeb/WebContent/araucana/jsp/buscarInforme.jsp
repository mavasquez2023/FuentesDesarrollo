<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<script type="text/javascript">
	function validaFormulario()  {
		//Valida caracteres válidos en caja de búsqueda
		var sMsje = "";
		if (validaReq(document.getElementById("rutEmpresa")) && !validaRut(document.getElementById("rutEmpresa").value))
			sMsje += "* Formato de RUT inválido para el Independiente a buscar\n";

		if (sMsje == "") {
			if (validaReq(document.getElementById("rutEmpresa")) &&	!validaDV(document.getElementById("rutEmpresa").value))
				sMsje += "* Dígito verificador inválido para el RUT del Independiente a buscar\n";
		}

		if (sMsje != "") {
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}

		document.forms[0].submit();
	}

	//Retorna verdadero si el campo está presente.
	function validaReq(field) {
		if (field != null) {
			with (field) {
				if (value == null || trim(value) == "")
					return false;
				else
					return true;
			}
		}
		return false;
	}

	//Valida que el string sRut contenga un rut válido según el formato: 99[.]999[.]999[-]9|k|K o rut sin formato
	function validaRut(sRut) {
		if(sRut.length < 10) sRut = '0' + sRut;
		var regex = /^\d{1,3}\.?\d{3}\.?\d{3}-?[\dkK]$/
		return regex.test(sRut);
	}
	
	//Valida el digito verificador del rut en el string sRut
	function validaDV(sRut) {
		var rut = sRut.replace(/\./g, "");
		if (rut.indexOf("-") != -1) {
			//Rut con guión		
			var num = rut.split("-")[0];
			var dig = rut.split("-")[1].toUpperCase();
		} else {
			//Rut sin guión
			var num = rut.substr(0, rut.length - 1);
			var dig = rut.substr(rut.length - 1).toUpperCase();
		}
		var s = 0;
		var m = 2;
		for (var i = num.length - 1; i >= 0; i--) {
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
	
	function openPopUp(url) {
		target = "_blank";
		//window.open(url, target, "height=600,width=650,toolbar=no,directories=no,scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
		window.open(url, target, "toolbar=no,directories=no,scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
	}
</script>

<html:form action="/ResultadoInforme" styleId="formulario">
<html:hidden styleId="accion"    property="accion"    name="accion"    value="reportes" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="errorAviso" />

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
  <tr>
    <td><strong>RUT Independiente:</strong></td>
    <td><html:text property="rutEmpresa" maxlength="12" size="25" styleClass="campos" styleId="rutEmpresa" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
    <td><strong>Nombre: </strong></td>
    <td><html:text property="razonSocial" styleId="razonSocial" styleClass="campos"/></td>
  </tr>
  <tr style="display:none">
    <td><strong>Tipo Proceso: </strong></td>
    <c:set var="tipoNominaSel">${tipoNomina}</c:set>
    <td colspan="3">

    	<select name="tipoProceso" id="tipoProceso" size="1" Class="campos">
				<option value="R" selected="selected">R</option>
		</select>
    	
    	
    	<!-- <html:select property="tipoProceso" styleId="tipoProceso" size="1" styleClass="campos">
        	<logic:iterate id="tipoNomina" name="tiposDeNominas">
        		<bean:define id="nomina" name="tipoNomina" type="cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO"></bean:define>
        		<c:set var="tipoNominaCmb"><bean:write name='nomina' property='idTipoNomina' /></c:set>
        		<option value="<bean:write name='nomina' property='idTipoNomina' />" <c:if test='${tipoNominaCmb eq tipoNominaSel}'>selected="selected"</c:if>><bean:write name="nomina" property="descripcion"/></option>
        	</logic:iterate>
		</html:select> -->
	</td>
  </tr>
  <tr>
    <td colspan="4"><div align="center"><input type="button" value="Buscar" onclick="javascript:validaFormulario();" class="btn3" id="btnBuscar"/></div></td>
								         
  </tr>
  <tr> 
  	<td height="4" colspan="4" bgcolor="#85b4be"></td>
  </tr>  
</table>
</html:form>
