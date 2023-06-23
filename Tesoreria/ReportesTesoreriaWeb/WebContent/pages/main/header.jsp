<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<table width="980" class="table_transparente">
	<tr>
		<td height="95" colspan="3" valign="top"><img
			src="<c:url value="/images/logo.png" />" />
		</td>
	</tr>
	<tr>
		<td height="1px" valign="top" align="center" width="350">&nbsp;</td>
		<td height="1px" valign="top" align="center" width="70"><div
				align="center" id="cargandoPagina">
				<!-- <img src="<c:url value="/images/barra_loading.gif" />" width="100"	height="10" /> -->
			</div>
		</td>
		<td height="1px" valign="top" align="right" width="350"><c:if
				test="${not empty pageContext.request.userPrincipal}">
				<i><a href="javascript:logout();" align="right"><bean:message
							key="label.salir" />
				</a>
				</i>
			</c:if></td>

	</tr>
</table>



