<%
  String path = request.getContextPath();
  
  String getProtocol=request.getScheme();
  String getDomain=request.getServerName();
  String getPort=Integer.toString(request.getServerPort());
  
  String getPath = getProtocol+"://"+getDomain+":"+getPort+path+"/mobile/";
  
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="<%=getPath %>css/estilo.css" rel="stylesheet" type="text/css" />
<link href="<%=getPath %>css/grid.css" rel="stylesheet" type="text/css" />
<script src="<%=getPath %>js/opciones.js" type="text/javascript"></script>
</head>