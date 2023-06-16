<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<table>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

<script>
	function printpage()
	{
	  window.print()
	}
</script>

<table>
	<tr>
		<td valign="top">
			<jsp:include   page="/WEB-INF/tiles/common/menu.jsp"></jsp:include>
		</td>
		
		<td width="730px">
			<br><br><br>
			<br />
			<center>
				<span style="color:red;text-size:16px">
					Su propuesta esta siendo cargada y su estado puede ser verificado en la opcion "Estado de procesamiento"<br />
					<b>
						<a href="ProcesaEstadoBash.do">Ir a "Estado de procesamiento"</a>
					</b>
				</span>
			</center>
		</td>
	</tr>
</table>