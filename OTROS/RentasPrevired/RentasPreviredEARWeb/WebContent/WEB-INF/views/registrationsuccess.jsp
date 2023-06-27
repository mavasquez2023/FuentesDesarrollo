<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration Confirmation Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="generic-container">
		 
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
	<!-- 	<c:if test="${tipo !='anonymousUser'}">
		<span class="well floatRight">
			Go to <a href="<c:url value='/list' />">Users List</a>
		</span>
		</c:if>
		<c:if test="${tipo =='anonymousUser'}">
		<span class="well floatRight">
		 Go to <a href="<c:url value='/login' />">login</a>
		</span>
		</c:if>-->
	</div>
</body>

</html>