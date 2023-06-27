<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />	
		<title><bean:message key="title.application"/></title>	

		<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

		<link href="${contextRoot}/styles${internetPath}/araucana.css" rel="stylesheet" type="text/css"/>
		<link href="${contextRoot}/styles${internetPath}/jquery.tools.css" rel="stylesheet" type="text/css"/>
		<script src="${contextRoot}/scripts/utilsbox.js" language="javascript" type="text/javascript"></script>		
		<script src="${contextRoot}/scripts/jquery.tools.full.min.js" language="javascript" type="text/javascript"></script>
		<script src="${contextRoot}/scripts/jquery.Rut.min.js" language="javascript" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			if (!isOldFashionBrowser() )
				 $(".addToolTip").tooltip();	
			$("ul.tabs").tabs("> .pane");	
			$("#cargandoPagina").hide();
		});
		</script>
		
	</head>
	
	<body>	
		<center>
			<div id="div-body"">
				<tiles:insert attribute="header"/>
				<table width="770" border="0" cellpadding="0" cellspacing="0" class="table_transparente">
					<tr>
				        <td width="100%" align="left" valign="top">
							<tiles:insert attribute="body"/>
				        </td>
					</tr>
				</table>
			</div>
		</center>
	</body>
	
</html>