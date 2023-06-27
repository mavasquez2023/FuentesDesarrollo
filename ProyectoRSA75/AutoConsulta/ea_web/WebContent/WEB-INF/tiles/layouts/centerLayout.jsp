<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />

<meta name="APPLICATION_ORGANIZATION_NAME" content="<%= software.getOrganizationName() %>" />
<meta name="APPLICATION_NAME" content="<%= software.getName() %>" />
<meta name="APPLICATION_TITLE" content="<%= software.getTitle() %>" />
<meta name="APPLICATION_VERSION" content="<%= software.getVersion() %>" />
<meta name="APPLICATION_RELEASE_DATE" content="<%= software.getReleaseDate() %>" />
<meta name="APPLICATION_COPYRIGHT" content="<%= software.getCopyright() %>" />

<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />

<title><tiles:getAsString name="title" /></title>
</head>

<body>

	<table border="0" width="800" cellpadding="0" cellspacing="0">
		<tbody>
		
			<!-- Header -->
			<tr>
				<td colspan="3"><tiles:get name="header" /></td>
			</tr>

			<!-- Second Row -->
			<tr>
				<td>
					<table style="width : 100%; height : 460px;" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
							
								<!-- Left menu -->
								<td style="width: 160px; vertical-align: top;">
									<tiles:get name="left" />
								</td>

								<td style="width: 1px; " class="verticalgray">
									<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
								</td>

								<!-- Body -->
								<td style="width : 640px; vertical-align: top;">
									<tiles:get name="body" />
								</td>

								<td style="width: 1px; " class="verticalgray">
									<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
								</td>
							
								<!-- Right menu -->					
								<td style="width : 150px; vertical-align: top;">
									<tiles:get name="right" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			<!-- Footer -->
			<tr>
				<td colspan="3"><tiles:get name="footer" /></td>
			</tr>
		</tbody>
	</table>
	
</body>
</html>
