
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sitenav" type="com.ibm.etools.siteedit.sitelib.core.SiteNavBean" scope="request"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>trail_horizontal</title>
<link rel="stylesheet" type="text/css" href="/AdministrationWeb/theme/horizontal-trail__.css">
</head>
<body>
<div>
<table class="htrail2_table">
	<tbody class="htrail2_table_body">
		<tr class="htrail2_table_row">
		<td>
		<c:out value='${sitenav.start}' escapeXml='false'/>
		</td>
<c:forEach var="item" items="${sitenav.items}" begin="0" step="1" varStatus="status">
<c:if test="${!status.first}">
			<td class="htrail2_cell_separator"><c:out value='${sitenav.separator}' escapeXml='false'/></td>
</c:if>
			<td class="htrail2_cell_normal">
			<c:out value='<a href="${item.href}" class="htrail2_item_normal">${item.label}</a>' escapeXml='false'/>
			</td>
</c:forEach>
<td><c:out value='${sitenav.end}' escapeXml='false'/></td>
		</tr>
	</tbody>
</table>

</div>
</body>
</html>
