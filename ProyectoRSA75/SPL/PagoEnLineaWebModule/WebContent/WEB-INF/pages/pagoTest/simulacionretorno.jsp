<table border="0">
<tr>
  <td>
	<form name="terminoBCI" method="post" action="">
	Url T&eacute;rmino: <input type="text" name="urlTermino" size="100" value="<%= getUrl2(request) %>/PagoEnLineaBciWebModule/pagobci/TerminoPago.do">
	<br>
	Trx: <input type="text" name="trx" value="">
	<br>
	<input type="submit" value="Volver" onclick="irAction();">
	</form>
  </td>
</tr>
</table>


<script type="text/javascript">
<!--
function irAction() {
	var f = document.forms['terminoBCI'];
	f.action = f.urlTermino.value;
	f.submit();
}
//-->
</script>

<%!
public String getUrl2(HttpServletRequest  request) {
	String server = request.getServerName();
	int puerto = request.getServerPort(); 
	String protocolo = request.getScheme();
	String servidor = protocolo + "://" +  server + ":" + puerto;
	
	return servidor;
}
%>

