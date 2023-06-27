<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="../theme/Master.css" rel="stylesheet" type="text/css" />
<title>header.jsp</title>
</head>
<body>

	<bean:include id="mainLink" page="/deprecated/include/mainLink.jsp"/>
	
	<table border="0" style="width : 100%; height: 60px;" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>
					<table style="width : 160px; background-color: #99ccff;" cellpadding="0" cellspacing="0">
						<tr>
							<td rowspan="2" style="width: 70px; height: 60px; text-align: center;">
								<html:img page="/img/Logomovi.gif" width="40" height="32" />
							</td>
							<td style="width: 90px; height: 20px">
							</td>
						</tr>
						<tr>
							<td style="height: 40px; font-size: 14px; font-weight: bold">
								C.C.A.F<br />La Araucana
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="height: 40px; width: 640px; color : #333300; background-color: #99ccff; font-family: Verdana, Arial, sans-serif; font-size : 18px; font-weight : 900; text-align : center;">Empresas Adherentes</td>
						</tr>
						<tr>
							<td style="width: 640px; height: 20px; vertical-align: bottom;">
								<!-- Main Link -->
								<bean:write name="mainLink" filter="false"/>																								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
