<form name="notificarBCI" method="post" action="">
Url notificaci&oacute;n: <input type="text" name="urlnotificacion" size="100" value="<%= getUrl(request) %>/PagoEnLineaBciWebModule/pagobci/NotificacionPago.do" >
<br/>
Trx: <input type="text" name="trx" value="">
<br/>
<input type="hidden" name="estado" value="">
<input type="button" value="Notificar exito" onclick="exito();">
<input type="button" value="Notificar fracaso" onclick="fracaso();">
</form>

<script type="text/javascript">
<!--
function exito() {
	var f = document.forms['notificarBCI'];
	f.action = f.urlnotificacion.value;
	f.estado.value = '021';
	f.submit();
}
function fracaso() {
	var f = document.forms['notificarBCI'];
	f.action = f.urlnotificacion.value;
	f.estado.value = '023';
	f.submit();
}
//-->
</script>

<%!
public String getUrl(HttpServletRequest  request) {
	String server = request.getServerName();
	int puerto = request.getServerPort(); 
	String protocolo = request.getScheme();
	String servidor = protocolo + "://" +  server + ":" + puerto;
	
	return servidor;
}
%>
