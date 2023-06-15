<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
		<title>Finish</title>
	</head>
	<body>
		<b>The values are:</b> <br /><br />
		Name = <bean:write name="exampleForm" property="name" /><br />
		Age = <bean:write name="exampleForm" property="age" /><br />
		City = <bean:write name="exampleForm" property="city" /><br />
		Phone = <bean:write name="exampleForm" property="phone" /><br />
	</body>
</html>