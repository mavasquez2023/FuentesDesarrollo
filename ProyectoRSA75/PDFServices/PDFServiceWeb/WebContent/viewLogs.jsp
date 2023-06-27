<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<HEAD> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<LINK href="css/Interna_Araucana.css" rel="stylesheet"	type="text/css" />
<!-- META HTTP-EQUIV="refresh" content="5;URL=ViewProcess.do" -->
<TITLE>La Araucana C.C.A.F. - Bitacora</TITLE>
</HEAD>

<BODY onload="javascript:scrolling();">
		<%@ include file="header.jspf" %>
	<center>
	
		
		<table width="767" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
					<%@ include file="contextUser.jspf" %>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td class="contextUser" width="5%">
							Contexto:
						</td>
						<td class="contextNav" align="left">
							<a href="<%= cPath %>/LoadConfUser.do" >Sistemas</a> 
							<%@ include file="p.jspf" %>
								<c:choose>
									<c:when test="${sistema.identificador == null}">
										Scripts
									</c:when>
									<c:otherwise>
										<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}" >Scripts</a>
									</c:otherwise>
								</c:choose>
							<%@ include file="p.jspf" %>
							<a href="<%= cPath %>/ViewTasks.do">Tareas</a>
							<%@ include file="p.jspf" %>
							Bitácora
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file="barTop.jspf" %>
			</td>
			
		</tr>
	</table> 
	<FORM name="TaskFormBean" method="get" action="ViewLogs.do">
	<INPUT type="hidden" name="taskID" value="${task.taskSID}">
	<input type="hidden" name="offset" />
		<table width="767" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">

			<tr>
				<td width="100%" colspan="3">
				
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" class="barra_tablas" align="center">
								BITACORA DEL SERVIDOR "${logFileName}"
							</td>
						</tr>
						<logic:present name="logs" scope="request">
							<tr>
								<td width="100%" >
									<TEXTAREA wrap="off" class="textProcess" id="textArea" rows="15" cols="105"  name="taskProcess" readonly >${logs}
									</TEXTAREA>
								</td>
							</tr>
						</logic:present>
						
					</table>
				
				</td>
			</tr>
			<tr>
				<td align="right" class="tabPage" width="30%" >
					 &nbsp;
					 
				</td>
				<td align="center" class="tabPage" width="40%">
					 <a href="ViewLogs.do?offset=begin" style=" font-size: 15pt; ">&lt;&lt;</a>
					 &nbsp; 
					 <a href="ViewLogs.do?offset=preview" style=" font-size: 15pt; ">&lt;</a>
					 &nbsp;
					 <a href="ViewLogs.do?offset=next" style=" font-size: 15pt; ">&gt;</a>
					 &nbsp;
					 <a href="ViewLogs.do?offset=end" style=" font-size: 15pt; "><b>&gt;&gt;</b></a>
				</td>
				<td class="tabPage" width="30%">
					 &nbsp;
				</td>				
			</tr>
		</table>
	</FORM> 
</center>

<script type="text/javascript">
	
	function scrolling(){

	    var element = document.getElementById('textArea');
	    element.scrollLeft = element.scrollWidth;
	    //alert("element.scrollHeight-->"+element.scrollHeight);
	    element.scrollTop = element.scrollHeight;
	   	//alert("element.scrollWidth-->"+element.scrollWidth);

	}
	

</script>
</BODY>
</html:html>
