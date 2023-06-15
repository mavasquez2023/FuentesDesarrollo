<%@ include file="./comun/headerJsp.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<jsp:include page="./comun/header.jsp" flush="true" />
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
<body>
	<br />
	<br />
	<br />
	<br />
	<br />
	<div class="container">
		<div class="alert alert-danger" style="margin-left: 20%;margin-right: 5%">${mensaje}</div>
		
	</div>
</body>
</html>