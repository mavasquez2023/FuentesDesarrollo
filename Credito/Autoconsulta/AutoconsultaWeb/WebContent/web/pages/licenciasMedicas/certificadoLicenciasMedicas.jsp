<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
           
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top' >
<%@ include file = "/web/includes/opciones.jsp"%>
</td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>  
<td valign='top'>  

<!-- Begin de la pagina particular -->
<script language="JavaScript">
function imprimir(){
    alert("Recuerde desactivar los encabezados y pies de p�gina, adem�s de ajustar el tama�o del texto, en su Explorador de Internet, para as� obtener un mejor resultado en la impresi�n.");
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open('<%= "/"+fullappname+"/showCertificadoLicenciasMedicas.do"%>',"print",prop);
	ventana.print();
}
</script>
  
<br>

<br>

<%@ include file = "/web/pages/licenciasMedicas/cuerpoCertificadoLicenciasMedicas.jsp"%>

<!-- End de la pagina particular -->
<div align='center'>
<input type="button"  class="boton" name="dummyProperty" value="Imprimir este resultado"        onclick="imprimir();" 
       onMouseOver="this.className='botonOver'" 
       onMouseOut="this.className='boton'" />	


</div>
</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>