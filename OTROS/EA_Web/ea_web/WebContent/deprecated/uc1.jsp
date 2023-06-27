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
<link href="../theme/Master.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F.</title>
</head>
<body>


<table border="0" width="800" cellpadding="0" cellspacing="0">
	<tbody>
	
		<!-- Header -->
		<tr>	
			<td>
				<bean:write name="header" filter="false"/>
			</td>
		</tr>
		
		<!-- Second Row -->
		<tr>
			<td>
				<table style="width : 100%; height : 460px;" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							
							<!-- Left Navigation Bar -->
							<td style="width: 160px; vertical-align: top;">
								<tiles:insert page="/WEB-INF/tiles/common/navLeftHome.jsp" flush="true" />
							</td>
		
							<td style="width: 1px; " class="verticalgray">
								<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
							</td>
								
							<!-- Contents -->
							<td style="width : 640px; vertical-align: top;">
								<table border="0" cellspacing="1" cellpadding="0">
									<tbody>
										<tr style="height : 20px;">
											<td>
												<html:img alt="" height="1" width="4" page="/img/c.gif"/>
											</td>
										</tr>
										
										<tr>
											<td class="nota">
												<bean:write name="mensaje" scope="request" />
											</td>
										</tr>
										<tr><td style="height : 14px;"><html:img alt="" height="1" width="4" page="/img/c.gif"/></td></tr>
										<tr>
											<td>
												<input class="buttonbold" type="button" value="Volver a la página anterior" onclick="history.go(-1); return false;"/>
											</td>
										</tr>
									</tbody>
								</table>								
							</td>
					
							<td style="width: 1px; " class="verticalgray">
								<html:img page="/img/c.gif" alt="" style="width : 1px; height: 1px;" />
							</td>
							
							<!-- Right Nav Bar -->					
							<td style="width : 150px; vertical-align: top;">
								<table>
									<tbody>
										<tr>
											<td>
												<table>
													<tbody>
														<tr>										
															<td><html:img page="/img/actn011.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
															<td style="text-decoration: none; color: #ff0000; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
																<html:link href="/logout.html">																
																	<span style="text-decoration: none; color: #ff0000; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
																		<bean:message key="global.text.salir" />
																	</span>
																</html:link>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										
										<tr>
											<td><html:img style="border : 0; width : 142px; height : 6px;" alt=""
												page="/img/horiz_gray.gif" /></td>										
										</tr>
						
										<tr>
											<td>
												<table>
													<tbody>
														<tr>										
															<td style="text-decoration: none; color: #000000; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal;">
																Notas del Diseño.. (borrador)
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

							</td>	
							
						</tr>
					</tbody>
				</table>						
			</td>
		</tr>		

	
		<!-- Footer -->
		<tr>
			<td>
				<bean:write name="footer" filter="false"/>					
			</td>
		</tr>	
	
			
	</tbody>
</table>



</body>
</html>
