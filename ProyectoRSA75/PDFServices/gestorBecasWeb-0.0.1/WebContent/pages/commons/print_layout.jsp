<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />	
		<meta http-equiv="pragma" content="public" />
		<meta http-equiv="cache-control" content="must-revalidate, post-check=0, pre-check=0" />
		<meta http-equiv="expires" content="0" />

		<title><bean:message key="title.application"/></title>	
		<link href="${contextRoot}/styles${internetPath}/araucana.css" rel="stylesheet" type="text/css" />
		<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>
	</head>
	
	<body onload="javascript:window.print();window.close();">
		<center>	
			<div>
				<table width="770" border="0" cellpadding="0" cellspacing="0" class="table_transparente">
					<tr>
						<td align="left">
							<img src="<c:url value="/images/header_2017.jpg" />" width="770" height="95" />
							<tiles:insert attribute="body"/>
						</td>
					</tr>
				</table>
			</div>
		</center>
	</body>
	
</html>