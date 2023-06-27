<%
String msg = (String)request.getAttribute("mensaje_inicial");
String url_notificacion= "/PagoEnLineaBesWebModule/pagobes/NotificacionPago.do";
String url_respuesta= "/PagoEnLineaBesWebModule/pagobes/TerminoPago.do?retorno=720";

String url = url_notificacion;
%>
<html>
  <head></head>
  <body onLoad="document.forms[0].submit();">
      <form method="POST" action="<%=url%>">
      <input type="hidden" name="xml" value="<%=msg%>">
    </form>
  </body>
</html>
