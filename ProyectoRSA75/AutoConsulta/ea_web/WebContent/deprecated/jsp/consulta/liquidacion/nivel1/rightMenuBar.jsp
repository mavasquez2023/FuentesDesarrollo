<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="../../../../theme/Master.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
function onto(i) {
	var o = eval(i);
	var winX = document.body.offsetWidth;
	var winY = document.body.offsetHeight;
	var x = (document.layers)? e.pageX : event.x+document.body.scrollLeft;
	var y = (document.layers)? e.pageY : event.y+document.body.scrollTop;

	posx = 0;
	posy = 0;
	
	posx = (winX - x < 50) ? x - 50 : x + 50;
	posy = (y < 50) ? y + 10 : y - 50;	

	o.style.left = posx;
	o.style.top = posy;

	var obj = eval("note");
	o.style.left = 650;
	o.style.top = 130;
	
	o.style.visibility = "visible";
}

function out(i) {
	var o = eval(i);
	
	o.style.visibility = "hidden";
}
</script>
<title>filtro.jsp</title>
</head>
<body>

<table>
	<tbody>
		<tr>
			<td>
				<table>
					<tbody>
					</tbody>
				</table>
			</td>
		</tr>
		
		<tr>
			<td><html:img page="/img/horiz_gray.gif" style="border : 0; width : 142px; height : 6px;" alt=""/></td>										
		</tr>

		<tr>
			<td>
				<table>
					<tbody>
						<tr>										
							<td id="note">
								<logic:notEmpty name="detalles" scope="request">
								<logic:iterate id="detalle" name="detalles" scope="request">						
									<div id='i<bean:write name="detalle" property="concepto.codigo" />'; style="position: absolute; visibility: hidden; width: 70px;" >
									<logic:notEmpty name="detalle" property="concepto.descripcion" scope="page">
										<table style="border-color: #ffff00;" border="1px" cellspacing="0" cellpadding="1" width="200px">
											<tbody>
												<tr>												
													<td style="font-family: verdana, ariel, serif; font-size: 10px; background-color: #ffffcc; text-align: justify;">
														<span style="font-family: verdana, ariel, serif; font-size: 10px; background-color: #ffffcc;">
														corresponde a:</span><br />
														<bean:write name="detalle" property="concepto.descripcion" />
													</td>
												</tr>
											</tbody>
										</table>
									</logic:notEmpty>
									</div>
								</logic:iterate>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</body>
</html>
