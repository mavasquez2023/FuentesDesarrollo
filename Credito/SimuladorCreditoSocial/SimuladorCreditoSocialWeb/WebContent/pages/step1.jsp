<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
		<title>Step 1</title>
		<script src="js/test2.js"></script>
	</head>
	<body>
		<html:form styleId="step1" action="/step1">

			<%-- ouput errors --%>
			<html:messages id="error" message="false">
				<bean:write name="error" /> <br />
			</html:messages>

			<%-- input field for properties --%>
			Name: <html:text property="name" /> <br />
			Age: <html:text property="age" /> <br />

			<%-- hidden field which specify the page --%>
			<html:hidden property="step" value="1" />

            <html:hidden property="btnStep1" value="btnStep1" />

			<html:submit property="btnStep1"/>

			<a onclick="test()" href="javascript:void(0);">link</a>


	        Download file <html:link action="/DownloadIt">file</html:link>

		</html:form>
	</body>
</html>