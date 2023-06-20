<%@ include file="./comun/headerJsp.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">
 <div class="alert alert-success">
  ${mensaje}
</div>
<div style="text-align: right;">
   <a type="submit" class="btn btn-primary" href="<c:url value = 'init.do'/>">volver</a>
  </div>
</div>
</body>
</html>