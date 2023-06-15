<div align='center'>

<!-- Begin Opciones del menu -->
<script>
	function logoff(){

		document.logout.submit(); 
	}

</script>    
 
<br>

<table border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td nowrap align="center">
			<form method="post" action="<%=request.getContextPath().toString()%>/web/logout.do" name="logout">
				<!-- <html:link styleClass="grupoopcion" href="javascript:logoff()"><font color="yellow">Salir</font></html:link>-->
			</form>
		</td>
	</tr>
</table>

<!-- End Opciones del menu -->

</div>
