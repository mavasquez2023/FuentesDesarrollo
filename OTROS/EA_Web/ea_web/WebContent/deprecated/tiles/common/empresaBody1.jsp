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
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="../../theme/Master.css" rel="stylesheet" type="text/css" />
<title>empresa.jsp</title>
</head>
<body>

<table style="vertical-align: top;" border="0" cellspacing="0" cellpadding="0">					
	<tbody>
		<tr>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_1.gif" style="text-align : center; border : 0px;" /></td>
			<td style="width: 340px; height : 4px; background-image : url(img/box_top.gif)"></td>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_2.gif" style="text-align : center; border : 0px;" /></td>
		</tr>
		
		<tr>
			<td style="width : 4px; background-image : url(img/box_left.gif)"></td>
			<td style="width : 340px; background-color: #f8f8f8;">
				<table cellspacing="0" cellpadding="0">
					<tbody>								
						<tr>
							<td style="width : 10px;"></td>
							<td>
								<table>
									<tbody>
										<tr>
											<td style="height : 4px;" colspan="2" style="font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #006666">
												<bean:message key="global.text.empresa" />
											</td>												
										</tr>
										<tr>
											<bean:define id="rut" name="empresa" property="rut" scope="request" />
											<td style="width : 100px; font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #000000">
												<bean:write name="rut" property="formattedRut" scope="page" />-
												<bean:write name="rut" property="dv" scope="page"/>
											</td>												
											<td style="font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #000000">
												<bean:write name="empresa" property="nombre" scope="request"/>
											</td>												
										</tr>																														
									</tbody>
								</table>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>											
			</td>
			<td style="width : 4px; background-image : url(img/box_right.gif)"></td>
		</tr>
		
		<tr>
			<td style="width : 4px; height : 4px"><html:img page="/img/box_3.gif" style="text-align : center; border : 0px;" /></td>
			<td style="width: 340px; height : 4px; background-image : url(img/box_bottom.gif)"></td>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_4.gif" style="text-align : center; border : 0px;" /></td>
		</tr>
	</tbody>
</table>

</body>
</html>
