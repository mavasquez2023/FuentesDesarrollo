
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sitenav" type="com.ibm.etools.siteedit.sitelib.core.SiteNavBean" scope="request"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<title>vertical-sep</title>
<link rel="stylesheet" type="text/css" href="/AdministrationWeb/theme/vertical-sep.css">
</head>
<body>
<table class="vsep_table" cellpadding="0" cellspacing="4">
	<tbody class="vsep_table_body">
<c:forEach var="item" items="${sitenav.items}" begin="0" step="1" varStatus="status">
<c:if test="${!status.first}">
		<tr>
			<td class="vsep_cell_separator"><img src="j.gif" width="1" alt=""></td>
		</tr>
</c:if>
 <c:choose>
  <c:when test="${item.group}">
   <c:if test="${!status.first}">
		<tr>
			<td class="vsep_cell_separator"><img src="j.gif" width="1" alt=""></td>
		</tr>
   </c:if>
		<tr class="vsep_table_row">
			<td class="vsep_cell_group">
			<span class="vsep_item_group"><c:out value='${item.label}' escapeXml='false'/></span>
			</td>
        </tr>
  </c:when>
  <c:otherwise>
		<tr class="vsep_table_row">
			<td class="vsep_cell_normal">
			<c:out value='<a href="${item.href}" class="vsep_item_normal">${item.label}</a>' escapeXml='false'/>
			</td>
        </tr>
  </c:otherwise>
 </c:choose>
</c:forEach>
	</tbody>
</table>
</body>
</html>
