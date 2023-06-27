<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<h1 align="center" >
<bean:message key='errors.system'/>
<br><br> 
<font size='2' color='red'>    
<%   
    String umsg=(String)request.getAttribute("error.usermessage");
    if (umsg!=null) { %>
    	<bean:write name="error.usermessage"/>
	<%}%>
<br><br>
</font>
<% String info=(String)request.getAttribute("error.info"); %>
<font size='1' color='black'><%= info!=null ? info : "" %></font>
<BR><BR>
<input type="submit" value="Volver" onclick="window.location='<html:rewrite page="" />/home.do';" />
<BR>
</h1>