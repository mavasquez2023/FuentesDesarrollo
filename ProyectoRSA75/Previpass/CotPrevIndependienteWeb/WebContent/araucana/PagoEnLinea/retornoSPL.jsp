<%@page import="java.util.Iterator"%>
<jsp:useBean id="messageList" scope="request" class="com.bh.talon.MessageList"/>
Fin Pago en L&iacute;nea
<% 
if (messageList!=null)
{
	for(Iterator i = messageList.iterator();i.hasNext();)
	{ 
    	com.bh.talon.Message message= (com.bh.talon.Message) i.next();%>
		<P><%= message.getMessage() %><BR/>
		<%= message.getHint()%></P>
<%	}
}%>