<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/html/comun/taglibs.jsp" %>
<script language="javascript" type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<link href="<c:url value="css/adminAraucana.css" />" rel="stylesheet" type="text/css">
<table width="590" border="0" cellpadding="0" cellspacing="0" class="textos-formularios3">
<tr> 
	<td width="25%"><strong><bean:message key="label.afp"/></strong></td>
	<td colspan="2">	
		<select name="codigo" id="codigo">
			<logic:iterate id="afp" name="listadoAfp">
				<option value="<bean:write name='afp' property='codigo'/>" lang="<bean:write name='afp' property='descripcion'/>"><bean:write name="afp" property="descripcion"/></option>
			</logic:iterate>
		</select>			
	</td>
</tr>
</table>
<br>	