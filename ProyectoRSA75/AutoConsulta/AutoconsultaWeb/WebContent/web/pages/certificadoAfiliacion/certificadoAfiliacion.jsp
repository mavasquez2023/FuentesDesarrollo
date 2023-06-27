<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>        
         
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top'>
<!-- Cargando menú -->
<%@ include file = "/web/includes/opciones.jsp"%>

</td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'> 
     
<!-- Begin de la pagina particular -->

<script language="JavaScript"> 
function imprimir(){
    alert("Recuerde desactivar los encabezados y pies de página, además de ajustar el tamaño del texto, en su Explorador de Internet, para así obtener un mejor resultado en la impresión.");
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open('<%= "/"+fullappname+"/showCertificadoAFILI.do"%>',"print",prop);
	ventana.print();
}
</script>

<%@ include file = "/web/pages/certificadoAfiliacion/cuerpoCertificadoAfiliacion.jsp"%>

<!-- End de la pagina particular -->
<div align='center'>
<input type="button"  class="boton" name="dummyProperty" value="Imprimir este resultado"        onclick="imprimir();" 
       onMouseOver="this.className='botonOver'" 
       onMouseOut="this.className='boton'" />	
<span style="font-size:10px">&nbsp; &nbsp; &nbsp;</span>
<input type="submit"  class="boton" name="dummyProperty" value="Cerrar"        onclick="window.location='Menu.do'" 
       onMouseOver="this.className='botonOver'" 
       onMouseOut="this.className='boton'" />	


</div>
</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>