<%@ include file="/html/comun/taglibs.jsp" %>
<script language="JavaScript1.2"> 
		function checkForm() {
			currentPassword = document.forms[0].claveActual.value;
			confirmNewPassword = document.forms[0].confirmacionNuevaClave.value;
			newPassword = document.forms[0].nuevaClave.value;
			
			if (currentPassword == "") {
				alert("Advertencia: Debe ingresar la clave actual!");
				
				document.forms[0].claveActual.focus();
				
				return false;
			}
 
			if (currentPassword == newPassword) {
				alert("Advertencia: La nueva clave debe ser diferente de la actual!");
				
				document.forms[0].nuevaClave.value = "";
				document.forms[0].confirmacionNuevaClave.value = "";
				document.forms[0].nuevaClave.focus();
				
				return false;
			}
 
			if (newPassword != confirmNewPassword) {
				alert("Advertencia: No coincide la confirmación de la nueva clave!");
				
				document.forms[0].confirmacionNuevaClave.value = "";
				document.forms[0].confirmacionNuevaClave.focus();
				
				return false;
			}
			
			if (!isNumber(currentPassword) || currentPassword.length != 4) {
				alert("Advertencia: Clave actual inválida. Recuerde que debe ser numérica!");
				
				document.forms[0].claveActual.value = "";
				document.forms[0].claveActual.focus();
				
				return false;
			}
 
			if (!isNumber(newPassword) || newPassword.length != 4) {
				alert("Advertencia: La nueva clave debe ser numérica!");
				
				document.forms[0].nuevaClave.value = "";
				document.forms[0].confirmacionNuevaClave.value = "";
								
				document.forms[0].nuevaClave.focus();
				
				return false;
			}
			
			return true;
		}
		
		function isNumber(s) {
			nmatches = 0;
			
		    for (i = 0; i < s.length; i++) {   
		        ch = s.charAt(i);
		        
				if ('0' <= ch && ch <= '9') {
					nmatches++;
				} else {
					return false;
				}
		    }
		    
		    return nmatches > 0;
		}		
	</script>
<html:form action="/ChangePassword" styleId="formulario">
<table align="center" width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4">
			<p>Sr. Usuario</p>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<p>Para cambiar su clave, proceda según lo siguiente.</p>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;
		</td>
	</tr>	
	<tr>
		<td colspan="4">
			<p>Ingrese su clave actual y una nueva clave de <b>4 dígitos</b>:</p>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;
		</td>
	</tr>	
	<tr>
		<td>
			&nbsp;
		</td>
		<td>
			<p>Clave Actual:</p>
		</td>
        <td height="17">
        	<nested:password property="claveActual" styleId="claveActual" maxlength="4" size="20" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"></nested:password>
        </td>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
		<td>
			<p>Nueva Clave:</p>
		</td>
        <td height="17">
        	<nested:password property="nuevaClave" styleId="nuevaClave" maxlength="4" size="20" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"></nested:password>
        </td>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
		<td>
			<p>Confirmar Nueva Clave:</p>
		</td>
        <td height="17">
        	<nested:password property="confirmacionNuevaClave" styleId="confirmacionNuevaClave" maxlength="4" size="20" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"></nested:password>
        </td>
		<td>
			&nbsp;
		</td>
	</tr>
   	<tr>
     	<td align="center" colspan="4"><br />
     		<div align="center"><input type="submit" value="Aceptar" name="changePassword" onclick="return checkForm();" class="btn3"> </div>
     	</td>
   	</tr>
   	<tr>
		<td>
			&nbsp;
		</td>
	</tr>   	
</table>
</html:form>