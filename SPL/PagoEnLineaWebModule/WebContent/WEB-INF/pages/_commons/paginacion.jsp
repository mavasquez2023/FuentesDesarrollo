<%@ page import="com.bh.paginacion.*" %>
<%
HttpPageParameters params = (HttpPageParameters) request.getAttribute("com.bh.paginacion.HttpPageParameters");
String jsPaginar = params.getJavascriptFuncionName();

DataPage p = (DataPage) request.getAttribute("com.bh.paginacion.DataPage");
%>
<input type="hidden" name="<%= params.getOffsetName() %>" value="" />
<table>
<tr>
	<td>
			<% if (p.getHasPreviousPage()) { %>
				<input type="button" value="Retroceder" onclick="<%= jsPaginar%>(this.form,<%= p.getPreviousOffset() %>);">
			<% } else { %>
				<input type="button" value="Retroceder" disabled>
			<% } %>
			
			<% if (p.getHasNextPage()) { %>
				<input type="button" value="Avanzar" onclick="<%= jsPaginar %>(this.form,<%= p.getNextOffset() %>);">
			<% } else { %>
				<input type="button" value="Avanzar" disabled>
			<% } %>
	</td>
</tr>
</table>




