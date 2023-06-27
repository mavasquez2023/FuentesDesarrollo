<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<HEAD> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<LINK href="css/Interna_Araucana.css" rel="stylesheet"	type="text/css" />
<TITLE>La Araucana C.C.A.F. - Lista de Procesos</TITLE>
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
									<c:when test="${sistema != null}">
										<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}#${idscript}" >Scripts</a>
									</c:when>
									<c:otherwise>
										Scripts
									</c:otherwise>
							</c:choose>
							<%@ include file="p.jspf" %>
							<a href="ViewTasks.do">Tareas</a>
							<%@ include file="p.jspf" %>
							Monitorear
							<%@ include file="p.jspf" %>
							${taskFormBean.nameScript}
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
	<FORM name="FORM" method="get" action="CleanProcessEnded.do">
	<INPUT type="hidden" name="taskID" value="${taskFormBean.taskSID}">


		<table width="767" cellpadding="0" cellspacing="0"
		bordercolor="#CCCCCC" align="center">

			<tr>
				<td>
					&nbsp;
				</td>
			</tr>				
		
			<tr>
				<td width="767">
					<%@ include file="contextSystem.jspf" %>
				</td>
			</tr>
		</table>
		<br/>
		<table width="767" height="30" border="0" cellpadding="0"
			cellspacing="0" align="center">
			<tr>
				
				<td width="100%" align="center">
					<table width="60%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td width="100%" align="center">
								<input type="button" class="btn2" name="Limpiar" value="Limpiar Procesos" 
								        onclick="javascript:doAction('CleanProcessEnded.do')">
							</td>
						</tr>
						<tr>
							<td width="100%"  class="tdcuerpotabla" align="center">
								${messageCleanded} 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="767" border="1" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8">
			<tr>
				<td width="100%">
				<logic:present name="taskFormBean" scope="session">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" class="barra_tablas" align="center">
								PROCESOS DE TAREA: ${taskFormBean.nameScript} 
							</td>
						</tr>
						<tr>
							<td width="100%" class="barra_tablas" align="center">
								<INPUT class="btn2" type="button" name="Refresh" value="Refrescar" onclick="reload()">	
							</td>
							 
						</tr>
						<logic:present name="messageTask" scope="request">
							<tr>
								<td width="100%" align="center">
									<TEXTAREA  wrap="off" class="textProcess" rows="15" cols="107"  id="textArea" readonly >${messageTask}
									</TEXTAREA>
										
									
								</td>
							</tr>
						</logic:present>
						
					</table>
				</logic:present>
				</td>
			</tr>
		</table>
	</FORM> 
</center>

<script type="text/javascript">
	
	function scrolling(){

	    var obj = document.getElementById('textArea');
	    obj.scrollLeft = obj.scrollWidth;
	    //alert("element.scrollHeight-->"+element.scrollHeight);
	    obj.scrollTop = obj.scrollHeight;
	   	//alert("element.scrollWidth-->"+element.scrollWidth);

	}
	
	function reload(){
		document.location.reload();
	
	}
	function confirmation() {
		var answer = confirm(
							  "ATENCIÓN:\n" +
							  "La limpieza de procesos, se aplica a todas las tareas cuyos procesos estén terminados,\n" +
							  "no importando el estado de la tarea.\n\n " +
							  "Si Ud. además es un Administrador Global, limpiará los procesos terminados de las tareas\n" +
							  "ejecutadas por otros usuarios.\n\n" +
							  "¿Está seguro que desea limpiar todos los procesos terminados?"		                      )
		return answer;
	}

		function reload(){
		document.location.reload();
	
	}


	function doAction(actionName){
	
		if('CleanProcessEnded.do' == actionName){
			if(confirmation()){
				document.forms[0].action = actionName;
				document.forms[0].submit();
			} 
		} 
		
	}
	
</script>


</BODY>
</html:html>
