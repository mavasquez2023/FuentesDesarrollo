<jsp:useBean id="renderer" scope="application" class="cl.araucana.adminCpe.utils.Renderer"/>
<jsp:useBean id="messageList" scope="request" class="com.bh.talon.MessageList"/>
<% 
if (messageList!=null)
{
	for(java.util.Iterator i = messageList.iterator();i.hasNext();)
	{ 
    	com.bh.talon.Message message= (com.bh.talon.Message) i.next();%>
		<P><%= renderer.formatString(message.getMessage()) %><BR/>
		<%= renderer.formatString(message.getHint())%></P>
<%	}
}%>