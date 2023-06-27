<%@ include file="/html/comun/taglibs.jsp" %>
<html>
<head>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="renderer" scope="application" class="cl.araucana.adminCpe.utils.Renderer"/>
<jsp:useBean id="messageList" scope="request" class="com.bh.talon.MessageList"/>
<br />
<div class="titulo"><strong>Ha ocurrido un error, por favor int&eacute;ntelo nuevamente.</strong></div>
<br />
<% 
if (messageList!=null)
{
	for(Iterator i = messageList.iterator();i.hasNext();)
	{ 
    	com.bh.talon.Message message= (com.bh.talon.Message) i.next();%>
		<p>
			<div class="titulo"><%= renderer.formatString(message.getMessage()) %><br /><%= renderer.formatString(message.getHint())%></div>
		</p>
<%	}
}%>
<form action="<c:url value="/ListarUsuarios.do" />">
	<input type="submit" class="btn3" value="Volver"/>
</form>
</body>
</html>