<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />

<style type="text/css">

	
	div.tableContainer {
		width: 767px;		/* table width will be 99% of this*/
		height: 200px; 	/* must be greater than tbody*/
		overflow: auto;
		margin: 0 auto;
		}
	
	tableFixHead {
		width: 99%;		/*100% of container produces horiz. scroll in Mozilla*/
		border: none;
	
		}
	
	tableFixHead>tbodyFixHead	{  /* child selector syntax which IE6 and older do not support*/
		overflow: auto;
		height: 90px;
		overflow-x: hidden;
		border-color: green;
		border-style: solid;
		}
	
	thead tr	{
		position:relative;
		/*top: expression(offsetParent.scrollTop); /*IE5+ only*/*/
		}
	
	thead td, thead th {
		text-align: center;
		font-size: 14px;
		background-color: #FFFFFF;
		color: steelblue;
		font-weight: bold;
		/*border-top: solid 1px #d8d8d8;*/
		}
	
	td	{
		color: #000;
		padding-right: 2px;
		font-size: 12px;
		/*text-align: left;*/
		/*border-bottom: solid 1px #d8d8d8;*/
		/*border-left: solid 1px #d8d8d8;*/
		}
	
	tableFixHead tfoot tr { /*idea of Renato Cherullo to help IE*/
	      position: relative;
	      overflow-x: hidden;
	      top: expression(parentNode.parentNode.offsetHeight >=
		  offsetParent.offsetHeight ? 0 - parentNode.parentNode.offsetHeight + offsetParent.offsetHeight + offsetParent.scrollTop : 0);
	      }
	
	
	tfoot td	{
		text-align: center;
		font-size: 11px;
		font-weight: bold;
		background-color: #FFFFFF;
		color: steelblue;
		/*border-top: solid 1px slategray;*/
		}
	
	td:last-child {padding-right: 20px;} /*prevent Mozilla scrollbar from hiding cell content*/
	
	</style>
	
	
	
	<!-- print style sheet -->
	<style type="text/css" media="print">
	div.tableContainer {
	  overflow: visible;	
	}
	tableFixHead>tbodyFixHead	{
	  overflow: visible; 
	}
	td {
	   height: 14pt;
	} /*adds control for test purposes*/
	
	thead td	{font-size: 11pt;	}
	tfoot td	{
		text-align: center;
		font-size: 9pt;
		border-bottom: solid 1px slategray;
		}
	
	thead	{display: table-header-group;	}
	tfoot	{display: table-footer-group;	}
	thead th, thead td	{position: static; }
	
	thead tr	{position: static; } /*prevent problem if print after scrolling table*/
	tableFixHead tfoot tr {     position: static;    }
	
	
</style>


<TITLE>La Araucana C.C.A.F. - Lista de tareas</TITLE>
<script type="text/javascript">
	function confirmation() {
		var answer = confirm("¿Está seguro que desea detener la operación?")
		return answer;
	}

	function doAction(actionName){
	
		if('AbortTask.do' == actionName){
		
			if(confirmation()){
				document.TaskFormBean.action = actionName;
				document.TaskFormBean.submit();
			} 
			
		} else {
			document.TaskFormBean.action = actionName;
			document.TaskFormBean.submit();
		
		}
		
	}
 
</script>
</head>
<body >
	<%@ include file="header.jspf" %>
	<center>
	<logic:present name="username" scope="session">
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
						<logic:present name="sistema" scope="session">
						<%@ include file="p.jspf" %>
						<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}" >Scripts</a>
						</logic:present>
						<%@ include file="p.jspf" %>
						<a href="<%= cPath %>/ViewTasks.do">Tareas</a>
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
	
			<form name="TaskFormBean" >
			
				<logic:present name="tasksList" scope="session">
					<table width="767" height="30" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="100%" align="center">
							<table width="222" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="104">
										<input type="button" class="btn2"
										value="Monitorear" onclick="doAction('ViewProcess.do')" />
									</td> 
									<td>&nbsp;</td>
									<td width="110">
										<input type="button" class="btn2" 
										value="Detener" onclick="doAction('AbortTask.do')">
									</td>
									<td>&nbsp;</td>
									<td width="110">
										<input type="button" class="btn2"
										value="Eliminar" onclick="doAction('CleanTasksEnded.do')"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</logic:present>
					<logic:present name="messageTask" scope="request">
						<table class="tdcuerpotabla" width="500">
							<tr>
								<td align="center">
									${messageTask}
								</td>
							</tr>
							<tr>
								<td>
									<FONT color='red'><html:errors /></FONT>  
								</td>
							</tr>
						</table>
					</logic:present>

					<table >
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					
				<div id="containerHead" style="width: 767px; height: 10px; padding-top: 10pt;" align="right">
					<table width="767" border="0" cellpadding="0" cellspacing="0"
											bordercolor="#B4D6D8" class="tableFixHead" align="right">
					<thead>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" bordercolor="#B4D6D8" >
									<tr>
										<td colspan="8" class="headertablas" style="border-bottom: solid; border-bottom-color: white; border-bottom-width: 1pt"> 
											LISTA DE TAREAS
										</td>
									</tr>
									<tr>
										<td width="28" class="headertablas" align="center">&nbsp;</td>
										<td width="17" class="headertablas">ID</td>
										<td width="125" class="headertablas">Nombre Script</td>
										<td width="140" class="headertablas">Fecha|Hora Inicio</td>
										<td width="135" class="headertablas">Fecha|Hora Término</td>
										<td width="65" class="headertablas">Usuario</td>
										<td width="50" class="headertablas">Sistema</td>
										<td width="180" class="headertablas">Estado Ejecuci&oacute;n</td>
									</tr>
								</table>
							</td>
						</tr>
					</thead>
					</table>
				</div>
				<div id="container" style="background-color: #D1E6E7; height: 300%; width: 767px;">
					<div class="tableContainer" >
					<table> 
						<tr>
							<td>
								<table border="1" cellpadding="0" cellspacing="0"
										bordercolor="#B4D6D8" class="tableFixHead" align="left" >
									<tbody class="tbodyFixHead">
											<!--tr>
												<td class="barra_tablas" colspan="8" align="center">LISTA DE TAREAS</td>
											</tr>
					
											<tr>
												<td width="3%" class="barra_tablas" align="center">&nbsp;</td>
												<td width="3%" class="barra_tablas">ID</td>
												<td width="17%" class="barra_tablas">Nombre Script</td>
												<td width="18%" class="barra_tablas">Fecha | Hora Inicio</td>
												<td width="18%" class="barra_tablas">Fecha | Hora Término</td>
												<td width="11%" class="barra_tablas">Responsable</td>
												<td width="8%" class="barra_tablas">Sistema</td>
												<td width="30%" class="barra_tablas">Estado Ejecuci&oacute;n</td>
											</tr-->
										
										<logic:present name="tasksList" scope="session">
										<c:set var="idtask" value="${sessionScope.taskFormBean.taskSID}"/>
										<c:set var="count" value="${1}"/>
										<c:set var="style" value="class='textos-formcolor2'"/>
										<c:set var="checked" value="${null}"/>
											<c:forEach var="taskBean" items="${tasksList}">
												<c:choose>
														<c:when test="${idtask == taskBean.taskSID}">
															<c:set var="checked" value="checked='checked'"/>
														</c:when>
														<c:otherwise>
															<c:if test="${count==1 && idtask == taskBean.taskSID}">
																<c:set var="checked" value="checked='checked'"/>
															</c:if>
															<c:if test="${count==1 && idtask == null}">
																<c:set var="checked" value="checked='checked'"/>
															</c:if>
														</c:otherwise>
													</c:choose>
													
													<c:choose>
														<c:when test="${checked != null }">
															<c:set var="style" value="class='selected'"/>
														</c:when>
														<c:otherwise>
															<c:set var="style" value="class='textos-formcolor2'"/>
														</c:otherwise>
													</c:choose>
												
												<tr>
													<td ${style} width="28">
														<input  name="taskSID" 
																type="radio" 
																value="${taskBean.taskSID}" ${checked} 
																id="${taskBean.taskSID}"
																onclick="setAnchor(this.value);"
																/>
																
													</td>
													<td ${style} width="17" >${taskBean.taskSID}</td>
													<td ${style} width="125" align="left"  >${taskBean.nameScript}</td>
													<td ${style} width="140">${taskBean.dateStart}</td>
													<td ${style} width="135">${taskBean.dateEnded}</td>
													<td ${style} width="65">${taskBean.user}</td> 
													<td ${style} width="50">${taskBean.system}</td>
													
													<%-- SE DEFINEN LOS COLORES DEPENDIENDO DEL ESTADO DEL PROCESO --%>
													<td ${style} width="180">
														<c:if test="${taskBean.state == 'TERMINADA'}">
															<font color="blue">
																<c:out value="${taskBean.state}" />
															</font>
														</c:if>
														<c:if test="${taskBean.state == 'EJECUTANDOSE'}">
															<font color="green">
																<c:out value="${taskBean.state}" />
															</font>
														</c:if>
														<c:if test="${taskBean.state == 'DETENIDA'}">
															<font color="gray">
																<c:out value="${taskBean.state}" />
															</font>
														</c:if>
														<c:if test="${taskBean.state == 'ABORTADA'}">
															<font color="red">
																<c:out value="${taskBean.state}" />
															</font>
														</c:if>
														<c:if test="${taskBean.state == 'TERMINADA CON ERRORES'}">
															<font color="orange">
																<c:out value="${taskBean.state}" />
															</font>
														</c:if>
													</td>
													
													
												</tr>
									             <c:set var="count" value="${2}"/>
									             <c:set var="checked" value="${null}"/>
									             <c:set var="style" value=""/>
											</c:forEach>
										</logic:present>
										<logic:notPresent name="tasksList" scope="session">
											<tr>
												<td width="100%" class="textos-formcolor2" colspan="8">Sin  tareas disponibles</td>
											</tr> 
										</logic:notPresent>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>

	</form>
</logic:present>

<logic:notPresent name="username" scope="session">
	<% response.sendRedirect(request.getContextPath()+"/index.jsp"); %> 
</logic:notPresent>
</center>

<SCRIPT>

	function setAnchor(taskID) {
		
		if(taskID == null || taskID == '' || taskID == 'null'){
			document.location.href='<%=cPath%>/ViewTasks.do#1';
		} else {
			document.location.href='<%=cPath%>/ViewTasks.do#${idtask}';
		}
	}
	
</SCRIPT>
</body>
</html>
