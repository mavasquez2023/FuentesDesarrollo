<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
		<title>Step 2</title>
	</head>
	<body>
		<html:form action="/step2">

			<%-- ouput errors --%>
			<html:messages id="error" message="false">
				<bean:write name="error" /> <br />
			</html:messages>

			<%-- input field for properties --%>
			City: <html:text property="city" /> <br />
			Phone: <html:text property="phone" /> <br />

			<%-- hidden field which specify the page --%>
			<html:hidden property="step" value="2" />

			<%-- hidden fields for properties of step1 --%>
			<html:hidden property="name" />
			<html:hidden property="age" />

			<html:submit property="btnStep2"/>
		</html:form>
	</body>
</html>