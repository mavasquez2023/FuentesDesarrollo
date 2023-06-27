<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="../../theme/Master.css" rel="stylesheet" type="text/css" />
<title>body.jsp</title>
</head>
<body>

	<table border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td style="width: 5px;">
					<html:img page="/img/c.gif" alt="" style="width : 5px; height: 1px;" />
				</td>
				
				
				<td style="width: 100%">
					<table style="width : 100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>

							<tr><td style="height: 4px;"><html:img page="/img/c.gif" style="width : 1px; height : 5px;" /></td></tr>

							<!-- Empresa -->																								
							<tr>
								<td>
									<tiles:get name="empresa.body" />
								</td>			
							</tr>
							
							<tr><td style="width : 100%; height : 5px; background-image: url(img/3x5.gif);"></td></tr>														

							<tr><td style="height: 4px;"><html:img page="/img/c.gif" style="width : 1px; height : 5px;" /></td></tr>

							<!-- Banner -->										
							<tr>
								<td style="text-align: center;">
									<tiles:get name="banner.body" />
								</td>
							</tr>	

							<tr><td style="height: 4px;"><html:img page="/img/c.gif" style="width : 1px; height : 5px;" /></td></tr>
									
									
							<!-- Filter -->													
							<tr>
								<td>
									<tiles:get name="filter.body" />
								</td>
							</tr>
							
							<tr>
								<td style="width: 5px;">
									<html:img page="/img/c.gif" alt="" style="width : 5px; height: 1px;" />
								</td>											
							</tr>


							<!-- Detail Table -->
							<tr>
								<td>
									<tiles:get name="detailTable.body" />
								</td>			
							</tr>						
						</tbody>
					</table>
				</td>
				
				<td style="width: 5px;">
					<html:img page="/img/c.gif" alt="" style="width : 5px; height: 1px;" />
				</td>											
				
			</tr>
		</tbody>
	</table>								

</body>
</html>
