<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/pd4ml" prefix="pd4ml" %>


<%@page import="cl.araucana.gestorBecas.ui.WebUtils"%>

<pd4ml:transform
      pageInsets="15,15,50,15,points"
      screenWidth="800"
      adjustScreenWidth="true"
      fileName="gestorBecas.pdf"
      pageFormat="LETTER"
      inline="true">
      
	<!--  el header -->       
	<pd4ml:header areaHeight="70" 
	    watermarkUrl='<%= WebUtils.getAppUrl(request) + "/images/white.gif" %>'
	    watermarkBounds="0,775,600,16"
	    watermarkOpacity="100">
	    
		<table border="0" width="100%">
		<tr><td align="right"><img src='<%= WebUtils.getAppUrl(request) %>/images/araucana.jpg'/></td></tr>
		</table>
	    
	</pd4ml:header>       
	
	            
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />	
		<title><bean:message key="title.application"/></title>	
	    <style type="text/css">
	        <jsp:include page="/styles${internetPath}/araucana.css"/>
	    </style>		
	</head>

	
	<body>

		<div id="div-body">
        	<center>
				<table width="770" border="0" cellpadding="0" cellspacing="0" class="table_transparente" id="table-body">
					<tr id="tr-body">
				        <td valign="top" align="left" id="td_dos_body">
							<tiles:insert attribute="body" flush="false"/>
				        </td>
					</tr>
				</table>
			</center>
			
		</div>
	</body>
	
	
</html>

</pd4ml:transform>