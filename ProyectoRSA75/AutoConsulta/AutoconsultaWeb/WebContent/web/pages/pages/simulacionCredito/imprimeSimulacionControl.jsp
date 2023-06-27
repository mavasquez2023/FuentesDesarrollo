<%@ include file = "/web/includes/env.jsp"%>
<% isPrinting = true; %>   
<%@ include file = "/web/includes/headerPrint.jsp"%>
                
<div align='left'>      
<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>     
		<td align="left">  
			<%@ include file = "/web/pages/simulacionCredito/cuerpoSimulacionPrint.jsp"%>
			<br> 
		</td>
	<tr>   
		<td class="certificado" align="right">
			<bean:write name="fechaHoy" formatKey="format.fullDate"/>
		</td>
	</tr>
</table>         
</div>
