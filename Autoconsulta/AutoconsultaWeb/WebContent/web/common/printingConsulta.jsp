<%@ include file = "/web/includes/env.jsp"%>
<% backFunction="welcome()"; %>   
<% time = printTime; %>  
  
  
<%@ include file = "/web/includes/headerPrint.jsp"%>

<div align='center'>
<br>
<!--html:img page='/web/images/imprimiendoConsulta.gif' border="0"/-->
<table width="80%" height="50%">
<tr> 
	<td bgcolor="blue">
		<p align="center">
			<font color="white" face="arial" size="+1">
			Se est&aacute; imprimiendo el resultado de la simulaci&oacute;n.
			<br/>
			Cuando finalice, por favor haga clic en el bot&oacute;n cerrar.
			</font>
		</p>
	</td>
</tr>
</table>
<br/>
<br/> 
<input type="button" name="cerrar" value="Cerrar" onclick="javascript:top.close();"/> 
</div>

<script language='javascript'>
top.control.focus();
</script>




