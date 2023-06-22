<%-- jsf:pagecode language="java" location="/src/pagecode/pages/admin/config/sueldosPrestamo/SueldosPrestamo_List.java" --%><%-- /jsf:pagecode --%><%-- tpl:insert page="/theme/myTemplate.jtpl" --%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
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
<title>SueldosPrestamo_List</title>
<link rel="stylesheet" type="text/css" title="Style" href="/AdministrationWeb/theme/stylesheet.css">
<link rel="stylesheet" type="text/css" href="/AdministrationWeb/theme/horizontal-trail__.css">
<link rel="stylesheet" type="text/css" href="/AdministrationWeb/theme/vertical-sep.css">
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
				<hx:panelBox styleClass="panelBox" id="box6" layout="pageDirection">



													<h:outputText styleClass="myTitle" id="text2"
														value="#{label.SP_List_title}"></h:outputText>
													<hx:jspPanel id="jspPanel3">
						<hr>
					</hx:jspPanel><hx:panelBox styleClass="panelBox" id="box1">
														<hx:commandExButton styleClass="commandExButton"
															type="submit" value="#{label.refresh_btn}"
															id="doOriginDataNSPMultiRowFetchAction1"
															action="#{pc_SueldosPrestamo_List.doOriginDataNSPMultiRowFetchAction}">
														</hx:commandExButton>
													</hx:panelBox>



				<hx:dataTableEx id="originDataNSPMultiRow1" value="#{pc_SueldosPrestamo_List.originDataNSPMultiRow}" var="varoriginDataNSPMultiRow" styleClass="dataTableEx" headerClass="headerClass" footerClass="footerClass" rowClasses="rowClass1, rowClass2" columnClasses="columnClass1" border="0" cellpadding="2" cellspacing="0">
						<hx:columnEx id="columnEx1">
							<hx:panelRowCategory styleClass="panelRowCategory" id="rowCategory1" value="#{varoriginDataNSPMultiRow.IdClasificacionEmpresa}">
								<h:outputText styleClass="outputText" id="text1" value="#{varoriginDataNSPMultiRow.NumeroSueldosPrestamo_ClasificacionEmpresa.Descripcion}"></h:outputText>
							</hx:panelRowCategory>
							<f:facet name="header"></f:facet>
						</hx:columnEx>
						<hx:columnEx id="nombre2column">
							<f:facet name="header">
																<h:outputText styleClass="outputText"
																	value="Clasificacion Empresa" id="nombre2text"></h:outputText>
															</f:facet>
							

							<h:outputText styleClass="outputText" id="nombre2" value="#{varoriginDataNSPMultiRow.NumeroSueldosPrestamo_ClasificacionEmpresa.Nombre}">
							</h:outputText>
						</hx:columnEx>
						<hx:columnEx id="nombre1column">
							<f:facet name="header">
																<h:outputText styleClass="outputText"
																	value="Antiguedad Laboral" id="nombre1text"></h:outputText>
															</f:facet>
							<h:outputText styleClass="outputText" id="nombre1" value="#{varoriginDataNSPMultiRow.NumeroSueldosPrestamo_AntiguedadLaboral.Nombre}">
							</h:outputText>
						</hx:columnEx>
						<hx:columnEx id="idNroSueldos2column">
							<f:facet name="header">
																<h:outputText styleClass="outputText"
																	value="Nro de Sueldos" id="idNroSueldos2text"></h:outputText>
															</f:facet>
							<h:selectOneMenu styleClass="selectOneMenu" id="menu1" value="#{varoriginDataNSPMultiRow.IdNroSueldos}">
								<f:selectItems value="#{selectitems.pc_SueldosPrestamo_List.originDataNSMultiRow.Nombre.IdNroSueldo.toArray}" id="selectItems1" />
							</h:selectOneMenu>
						</hx:columnEx>
						
					</hx:dataTableEx><hx:panelBox styleClass="panelBox" id="box2">
														
														<hx:commandExButton styleClass="commandExButton"
															type="submit" value="#{label.update_btn}"
															id="doOriginDataNSPMultiRowUpdateAction1"
															action="#{pc_SueldosPrestamo_List.doOriginDataNSPMultiRowUpdateAction}">
														</hx:commandExButton>
														<hx:commandExButton type="submit"
															value="#{label.cancel_btn}" styleClass="commandExButton"
															id="button1"></hx:commandExButton>
													</hx:panelBox></hx:panelBox>
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