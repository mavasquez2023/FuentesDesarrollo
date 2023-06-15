<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<tiles:get name="header" />
<body>
	<tiles:get name="header.layout" />
	<table cellspacing="0" cellpadding="0" width="974" border="0">
		<tbody>
	<tr>
			 
				<td align="center" valign="top">
					<tiles:get name="body" />
				</td>
			</tr>
		</tbody>
	</table>
	<tiles:get name="footer.layout" />
</body>
</html:html>