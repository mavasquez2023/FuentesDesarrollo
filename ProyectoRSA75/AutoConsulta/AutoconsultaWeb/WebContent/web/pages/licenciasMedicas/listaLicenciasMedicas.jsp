<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>  
 
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top'>
	 <%@ include file = "/web/includes/opciones.jsp"%>
</td><!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>          
<!-- Begin de la pagina particular -->

<script language="JavaScript">
function imprimir(){
    alert("Recuerde desactivar los encabezados y pies de página, además de ajustar el tamaño del texto, en su Explorador de Internet, para así obtener un mejor resultado en la impresión.");
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open('<%= "/"+fullappname+"/showListaLicenciasMedicas.do"%>',"print",prop);
	ventana.print();
}
</script>
<br>
<br>
<%@ include file = "/web/pages/licenciasMedicas/cuerpoListaLicenciasMedicas.jsp"%>

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

<script type="text/javascript">
   function popUpBitacora(nroLicencia, fechaHasta, fechaPago){
	var fecha;
	if(fechaHasta != null){
		fecha = quitarCaracter(fechaHasta, "/");
		fecha = fecha.substring(4, 8) + fecha.substring(2, 4)+ fecha.substring(0, 2);
	}else{
		fecha ='00000000';
	}
	
	fechaPago = quitarCaracter(fechaPago, "/");
		var popup;
		var opciones= "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=400, height=280, top=85, left=140";
		popup = window.open("pages/licenciasMedicas/cargabitacoraLicencia.jsp?nroLicencia="+nroLicencia+"_"+fecha+"&fechaPago="+fechaPago,"",opciones);
	}
	
	function quitarCaracter(entrada, caracter){
		return entrada.replace(new RegExp(caracter, 'g'),'');
	}
</script>
<%@ include file = "/web/includes/footer.jsp"%>