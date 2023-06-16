<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>

<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>

</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
		
			<%-- <div id="contenedorIframeShow" style="display:none;">
				<div style="margin-top: 15px;"></div>
				<iframe
					src="" frameborder="0"
					style="height: 500px;width: 100%;"
					name="iframeBP" id="iframeBP">
				</iframe>
			</div> --%>
			
			<div id="contenedorIframe" style="display:none;">
				<div style="margin-top: 15px; display: none;"></div>
				<iframe 
					src="${urlDestino}" frameborder="0"
					style="height: 500px;width: 100%;"
					name="iframeBP" id="iframeBP" >
				</iframe>
			</div>	
		</div>
		<br>
	</div>
	
	<div id="loading" style="position:fixed; top:35%; left:55%; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
	</div>
				
<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
</fieldset>
</body><div id="div1" style="display:none;"></div>

<script type="text/javascript">

$(document).ready(function () {
	
	   	$("#loading").hide();
		$('#contenedorIframe').show();   
        
        function cambiaUrl(){
        	$('#iframeBP').attr('src', '');
    		setTimeout(muestraIframe,1200);
        }
        
        function muestraIframe(){
        	$("#loading").hide();
        	$('#contenedorIframe').show();      
        	$('#iframeBP').show();   
        	$('#iframeBP').attr('src', '${urlDestino2}');
        }
        
}); //fin document ready

</script>
</html>