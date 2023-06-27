<%
String msg = (String)request.getAttribute("mensaje_inicial");
String url = (String)request.getAttribute("url_cgi");
%>
<html>
  <head></head>
  <body onLoad="document.forms[0].submit();">
    <form method="POST" action="<%=url%>">
      <input type="hidden" name="TX" value="<%=msg%>">
    </form>
  </body>
</html>
