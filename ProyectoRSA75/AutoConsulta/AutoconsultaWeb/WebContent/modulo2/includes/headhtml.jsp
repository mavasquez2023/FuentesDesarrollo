<%
  String path = request.getContextPath();
  
  String getProtocol=request.getScheme();
  String getDomain=request.getServerName();
  String getPort=Integer.toString(request.getServerPort());
  
  String getPath = getProtocol+"://"+getDomain+":"+getPort+path+"/";
  
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<base href="<%=getPath %>"/>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/grid.css" rel="stylesheet" type="text/css" />
<script src="js/opciones.js" type="text/javascript"></script>
</head>