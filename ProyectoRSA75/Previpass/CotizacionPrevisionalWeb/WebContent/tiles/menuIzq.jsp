<%@ include file="/html/comun/taglibs.jsp" %>
<!-- <c:set var="menus" value="${SESSION_KEY_MENUS}" />
<c:set var="user" value="${currentUser}"/>  -->
<SCRIPT LANGUAGE="JavaScript">
	var SEND_METHOD_UNZIPPED_ADAPTED = 1;
	var SEND_METHOD_DISPATCHER = 2;

	<%
	   java.io.File markFile = new java.io.File("/.adapted");
		String sendMethod = "SEND_METHOD_DISPATCHER";	   

	   if (markFile.exists()) {
		   sendMethod = "SEND_METHOD_UNZIPPED_ADAPTED";
	   }

	   pageContext.setAttribute("sendMethod", sendMethod);
	%>

	var sendMethod = ${sendMethod};

	var javawsInstalled = 0;
		
	var javawsInstalled = 0;  
	var javaws142Installed=0;
	var javaws150Installed=0;
	var javaws160Installed = 0;
	isIE = "false"; 
	if (navigator.mimeTypes && navigator.mimeTypes.length) { 
	   x = navigator.mimeTypes['application/x-java-jnlp-file']; 
	   if (x) { 
	      javawsInstalled = 1; 
	      javaws142Installed=1;
	      javaws150Installed=1;
	      javaws160Installed = 1; 
	  } 
	} 
	else { 
	   isIE = "true"; 
	} 
</SCRIPT> 
<SCRIPT LANGUAGE="VBScript">
	on error resume next
	If isIE = "true" Then
	  If Not(IsObject(CreateObject("JavaWebStart.isInstalled"))) Then
	     javawsInstalled = 0
	  Else
	     javawsInstalled = 1
	  End If
	  If Not(IsObject(CreateObject("JavaWebStart.isInstalled.1.4.2.0"))) Then
	     javaws142Installed = 0
	  Else
	     javaws142Installed = 1
	  End If 
	  If Not(IsObject(CreateObject("JavaWebStart.isInstalled.1.5.0.0"))) Then
	     javaws150Installed = 0
	  Else
	     javaws150Installed = 1
	  End If  
	  If Not(IsObject(CreateObject("JavaWebStart.isInstalled.1.6.0.0"))) Then
	     javaws160Installed = 0
	  Else
	     javaws160Installed = 1
	  End If  
	End If
</SCRIPT>
<table style="width:170px;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" style="padding-top:25px;">
			<div class="menucp1">
				<ul class="menu_collapsecp" id="menucp1">
	<c:forEach var="modulo" items="${menus.modulos}">
		<c:if test="${fn:contains(modulo.rolesPermitidos, user.rol)}">
							<li>
				       			<a href="<c:url value="${modulo.url}" />" id="<c:out value="${modulo.accion}" />" >
	       				<c:out value="${modulo.nombre}" />
	       			</a>
			<c:if test="${!empty modulo.tabs}">
								<ul>
							<c:forEach var="tab" items="${modulo.tabs}">
								<c:if test="${fn:contains(tab.rolesPermitidos, user.rol)}">
			                   						<c:if test="${tab.subAccion != ''}">
														<c:if test="${tab.visible != 'no'}">
													<li id="sub<c:out value="${modulo.accion}" />">
													<a href="<c:url value="${tab.url}" />" id="<c:out value="${tab.subAccion}" />" onclick="${tab.onclick}">
																<c:out value="${tab.nombre}" />
															</a>
													</li>
															</c:if>
													</c:if>
								</c:if>
							</c:forEach>
								</ul>
			</c:if>
					       	</li>
		</c:if>
	</c:forEach>
				</ul>
			</div>
		</td>
	</tr>
</table>