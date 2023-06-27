<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>      
         
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>   
<tr>
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
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
<div align='center'>
	<html:link href='javascript:imprimir();'>
	<html:img page="/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
	</html:link>
	<html:link page="/Menu.do">
	<html:img page="/images/volver.gif" border="0" alt="Volver"/>
	</html:link>
</div>
<br>  
<br>
 
<%@ include file = "/web/pages/licenciasMedicas/cuerpoListaLicenciasMedicas.jsp"%>

<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>

