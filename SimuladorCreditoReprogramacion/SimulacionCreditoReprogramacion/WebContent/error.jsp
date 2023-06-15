<%@ include file="layout/headerJsp.jsp"%>
<html>
<head>
<link type="text/css" href="<%=request.getContextPath() %>/css/themes/araucana/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />
<link type="text/css" href="<%=request.getContextPath() %>/css/araucana.css" rel="stylesheet" />
<title>Error</title>
</head>
<body>
	<p class="titulo">No ha sido posible completar su solicitud</p>
	<div class="ui-widget">
		<div style="margin-top: 20px; padding: 0 .7em;" class="ui-state-highlight ui-corner-all">
			<p>
				<span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-info"></span> <strong>${titulo}</strong> 
				${mensaje}
				<a class="right" href="javascript:void(0)" onclick="history.back()"> << Volver </a>
			</p>
		</div>
	</div>
</body>
</html>