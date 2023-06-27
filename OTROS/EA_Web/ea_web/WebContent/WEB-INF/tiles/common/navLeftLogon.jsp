<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table width="100%" cellspacing="0" cellpadding="0" style="vertical-align: top;">
	<tbody>
		<tr>
			<td style="width: 5%"></td>			
			<td style="width : 90%;">				
				<table cellpadding="0" cellspacing="0" border="0" style="vertical-align: top;">
					<tbody>
						<tr>
							<td colspan="3"><html:img page="/img/c.gif" alt="" style="width: 1px; height: 12px;" /></td>
						</tr>
						<tr>
							<td colspan="3" class="navleftgroup1" style="height: 10x;"></td>
						</tr>
						<tr>
							<td colspan="3" style="height: 4x; font-size: 3px;">&nbsp;</td>
						</tr>

						<tr>
							<td></td>
							<!-- Spliting this sentences into several lines may cause un expected results such as wider line spacing -->
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link href="http://www.cp.cl" styleClass="navleftelem"><bean:message key="navLeft1.elem1" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link forward="home" styleClass="navleftelem"><bean:message key="navLeft1.elem2" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link forward="faq" styleClass="navleftelem"><bean:message key="navLeft1.elem4" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link href="http://www.laaraucana.cl/?seccion=159&nom=" styleClass="navleftelem"><bean:message key="navLeft1.elem5" /></html:link></td>
						</tr>
						
						<tr>
							<td></td>
							<td colspan="2"><html:img page="/img/horiz_gray.gif" alt="" style="border: 0; width: 142px; height: 10px" /></td>
						</tr>
					</tbody>
				</table>				
			</td>
			<td style="width : 5%;"></td>	
		</tr>
	</tbody>
</table>