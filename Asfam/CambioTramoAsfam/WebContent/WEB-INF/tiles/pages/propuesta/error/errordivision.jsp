<% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Expires","0"); 
response.setDateHeader("Expires",-1); 
%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<table><td valign="top">
<jsp:include  page="/WEB-INF/tiles/common/menudivision.jsp">
  
</jsp:include>
</td>
<td width="730px">
<table cellspacing="0" cellpadding="0" width="100%" height="320" border="0" align="center">
	<tr>
		<td class="text" align="center" >		    
			<b>Glosa :</b> <c:out	value="${mensaje}"/><br>			
		</td>
	</tr>
</table>
</td>
</table>
