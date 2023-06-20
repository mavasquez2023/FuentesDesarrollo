<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Cookie[] cookies = request.getCookies();
	if (cookies == null)
		cookies = new Cookie[0];
	for (int i = 0; i < cookies.length; i++) {
		cookies[i].setMaxAge(0);
		cookies[i].setValue(null);
		response.addCookie(cookies[i]);
		//out.println(cookies[i].getName() + ":\t" + cookies[i].getValue() + "<BR>");
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/meta_application.jsp" />
<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body class="corp">
<div id="content" class="container_12">
  <div class="grid_12">
  <a href="http://www.laaraucana.cl"><img src="fondo_04.jpg"></a>
  </div>
</div>
</body>
</html>
