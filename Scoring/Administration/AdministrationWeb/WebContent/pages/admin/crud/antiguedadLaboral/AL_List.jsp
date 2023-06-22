<%-- jsf:pagecode language="java" location="/src/pagecode/pages/admin/crud/antiguedadLaboral/AL_List.java" --%><%-- /jsf:pagecode --%><%-- tpl:insert page="/theme/myTemplate.jtpl" --%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib prefix="siteedit" uri="http://www.ibm.com/siteedit/sitelib"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- tpl:put name="headarea" --%>
<title>AL_List</title>
<link rel="stylesheet" type="text/css" title="Style" href="../../../../theme/stylesheet.css">
<%-- /tpl:put --%>
<link rel="stylesheet" type="text/css" title="Style"
	href="/AdministrationWeb/theme/stylesheet.css">
<link rel="stylesheet" type="text/css"
	href="/AdministrationWeb/theme/horizontal-trail__.css">
<link rel="stylesheet" type="text/css"
	href="/AdministrationWeb/theme/vertical-sep.css">
<f:loadBundle var="label" basename="labels" />
</head>
<f:view>
	<body>
		<hx:scriptCollector id="scriptCollector100">

			<hx:panelLayout styleClass="panelLayout" id="layout1" width="100%"
				height="100%" bgcolor="#efefef">
				<f:facet name="body">
					<hx:jspPanel id="centeJjspPanel">
						<hx:panelLayout styleClass="panelLayout" id="layout1C"
							width="100%" height="100%" align="left">
							<f:facet name="body">
								<hx:jspPanel id="jspPanelUnder1CC">
									<%-- tpl:put name="bodyCenter" --%>
		<hx:scriptCollector id="scriptCollector1">

			<h:form styleClass="form" id="form1">

												<h:panelGrid styleClass="panelGrid" id="grid1" columns="1">
													<h:outputText styleClass="myTitle" id="text1"
														value="#{label.AL_List_title}"></h:outputText>
													<hx:jspPanel id="jspPanel3"><hr></hx:jspPanel>

													<h:messages styleClass="messages" id="messages1"></h:messages><hx:panelBox styleClass="panelBox" id="box1">
														<hx:commandExButton styleClass="commandExButton"
															type="submit" value="#{label.refresh_btn}"
															id="refreshBtn"
															action="#{pc_AL_List.doOriginDataALMultiRowFetchAction}">
														</hx:commandExButton>
													</hx:panelBox><hx:dataTableEx id="originDataALMultiRow1" value="#{pc_AL_List.originDataALMultiRow}" var="varoriginDataALMultiRow" styleClass="dataTableEx" headerClass="headerClass" footerClass="footerClass" rowClasses="rowClass1, rowClass2" border="0" cellpadding="2" cellspacing="0" width="100%" columnClasses="columnClass1">
													<hx:columnEx id="columnEx1">
														<hx:inputRowSelect styleClass="inputRowSelect" id="rowSelect1" value="#{pc_AL_List.selectedRows}">
															<f:param value="#{varoriginDataALMultiRow.IdAntiguedadLaboral}" name="idRow" id="param1"></f:param>
														</hx:inputRowSelect>
														<f:facet name="header"></f:facet>
													</hx:columnEx>
													<hx:columnEx id="nombre1column">
														<f:facet name="header">
															<h:outputText styleClass="outputText" value="Nombre" id="nombre1text"></h:outputText>
														</f:facet>
														<h:outputText styleClass="outputText" id="nombre1" value="#{varoriginDataALMultiRow.Nombre}">
														</h:outputText>
													</hx:columnEx>
													<hx:columnEx id="desde1column">
														<f:facet name="header">
															<h:outputText styleClass="outputText" value="Desde" id="desde1text"></h:outputText>
														</f:facet>
														<h:outputText styleClass="outputText" id="desde1" value="#{varoriginDataALMultiRow.Desde}">
															<hx:convertNumber />
														</h:outputText>
													</hx:columnEx>
													<hx:columnEx id="hasta1column">
														<f:facet name="header">
															<h:outputText styleClass="outputText" value="Hasta" id="hasta1text"></h:outputText>
														</f:facet>
														<h:outputText styleClass="outputText" id="hasta1" value="#{varoriginDataALMultiRow.Hasta}">
															<hx:convertNumber />
														</h:outputText>
													</hx:columnEx>
														<hx:columnEx id="columnEx2">
															<hx:requestLink styleClass="requestLink" id="link1" action="#{pc_AL_List.doLink1Action}">
																<h:outputText id="text3" styleClass="outputText" value="Editar"></h:outputText>
																<f:param
																	value="#{varoriginDataALMultiRow.IdAntiguedadLaboral}"
																	name="idAL" id="param2"></f:param>
															</hx:requestLink>
															<f:facet name="header">
															</f:facet>
														</hx:columnEx>
													</hx:dataTableEx><hx:panelBox styleClass="panelBox" id="box2">
														<hx:commandExButton type="submit" value="#{label.new_btn}"
															styleClass="commandExButton" id="newBtn"
															action="#{pc_AL_List.doNewBtnAction}"></hx:commandExButton>
														<hx:commandExButton type="submit"
															value="#{label.delete_btn}" styleClass="commandExButton"
															id="deleteBtn"
															action="#{pc_AL_List.doDeleteAntiguedadLaboralBtnAction}"></hx:commandExButton>

													</hx:panelBox>
													</h:panelGrid>
												

												</h:form>
		</hx:scriptCollector>
	<%-- /tpl:put --%>
								</hx:jspPanel>
							</f:facet>
							<f:facet name="left">
								<hx:panelBox styleClass="panelBox" id="box3" width="100">
								<div id="Layer0"
			style="height: 100%; position: static; width: 100%; z-index: auto; display: none"></div>		
								</hx:panelBox>
							</f:facet>
							<f:facet name="right">
								<h:panelGrid styleClass="panelGrid" id="grid1" width="100">
								<div id="Layer1"
			style="height: 100%; position: static; width: 100%; z-index: auto; display: none"></div>
								</h:panelGrid>
							</f:facet>
							<f:facet name="bottom">
							</f:facet>
							<f:facet name="top">
								<hx:panelBox styleClass="panelBox" id="box5" height="100%"
									width="100%">
									<div id="Layer2"
			style="height: 100%; position: static; width: 100%; z-index: 1; left: 50px; top: 50px">
										<h:panelGrid styleClass="panelGrid" id="grid2" width="100%">
										<div id="Layer3"
			style="position: absolute; z-index: 1; width: 50px; height: 50px; top: 50px; left: 50px"></div>
										</h:panelGrid>
									</div>
									</hx:panelBox>
							</f:facet>
						</hx:panelLayout>
					</hx:jspPanel>
				</f:facet>
				<f:facet name="left">
					<h:form styleClass="form" id="form100">
						<hx:jspPanel id="leftJspPanel">
							<hx:panelLayout styleClass="panelLayout" id="layout1L"
								width="100%" bgcolor="#efefef" height="100%">
								<f:facet name="body">
									<hx:panelBox styleClass="panelBox" id="box2"
										layout="pageDirection" valign="top">
										<siteedit:navbar
											target="home,children,parent,topchildren,self"
											targetlevel="1-3"
											spec="/AdministrationWeb/theme/vertical-sep.jsp" />


									</hx:panelBox>

								</f:facet>
								<f:facet name="left"></f:facet>
								<f:facet name="right"></f:facet>
								<f:facet name="bottom"></f:facet>
								<f:facet name="top"></f:facet>
							</hx:panelLayout>

						</hx:jspPanel>
					</h:form>
				</f:facet>
				<f:facet name="right">
					<hx:jspPanel id="rightJspPanel">
						<hx:panelLayout styleClass="panelLayout" id="layout1R"
							width="100%" bgcolor="#efefef" height="100%">
							<f:facet name="body">
								<hx:jspPanel id="jspPanelUnder1RC"></hx:jspPanel></f:facet>
							<f:facet name="right"></f:facet>
							<f:facet name="bottom"></f:facet>
							<f:facet name="top"></f:facet>
						</hx:panelLayout>
					</hx:jspPanel>
				</f:facet>
				<f:facet name="bottom">
					<hx:jspPanel id="bottomJspPanel">
						<hr/>
						<hr/>
						<hx:panelBox styleClass="panelBox" id="box4" width="100%"
							height="100%" layout="pageDirection" align="right"
							bgcolor="#efefef" valign="top">
							<h:outputText id="text1" value="Powered by MetricArts" styleClass="signature"></h:outputText>
						</hx:panelBox>
					</hx:jspPanel>
				</f:facet>
				<f:facet name="top">
					<hx:jspPanel id="topJspPanel">
						<hx:panelLayout styleClass="panelLayout" id="layout1T"
							width="100%" bgcolor="#efefef">
							<f:facet name="body">
								<hx:jspPanel id="jspPanelCenterUnder1TC">
								</hx:jspPanel>
							</f:facet>
							<f:facet name="left">
								<hx:jspPanel id="jspPanel1">
									<hx:graphicImageEx styleClass="graphicImageEx" id="imageEx1"
										value="/images/araucana.gif"></hx:graphicImageEx>
								</hx:jspPanel>
							</f:facet>
							<f:facet name="right"></f:facet>
							<f:facet name="bottom">
								<hx:jspPanel id="jspPanel2">
									<hr>
									<hx:panelBox styleClass="panelBox" id="box1" width="100%"
										bgcolor="teal" align="right">
										<siteedit:navtrail target="home,parent,ancestor,self"
											start="[" separator="&gt;" end="]"
											spec="/AdministrationWeb/theme/horizontal-trail_.jsp" />
									</hx:panelBox>
								</hx:jspPanel>
							</f:facet>
							<f:facet name="top"></f:facet>
						</hx:panelLayout>
					</hx:jspPanel>
				</f:facet>
			</hx:panelLayout>
		</hx:scriptCollector>




		
	</body>
</f:view>
</html><%-- /tpl:insert --%>