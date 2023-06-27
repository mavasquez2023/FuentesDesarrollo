<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

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
							
							<tr><td style="width : 100%; height : 5px; background-image: url(<%=request.getContextPath()%>/img/3x5.gif);"></td></tr>														

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

							<!-- Resumen -->													
							<tr>
								<td>
									<tiles:get name="resumen.body" />
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

