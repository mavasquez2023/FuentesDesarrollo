<%@ include file="../comun/headerJsp.jsp" %>
<html>
	<head>
		<jsp:include flush="true" page="../comun/headerCRM.jsp"></jsp:include>
		<title>Certificados de Cr&eacute;dito - La Araucana C.C.A.F.</title>
	</head>
	<body>
	<header>
		<div class="contenedor">
			<a href="http://www.laaraucana.cl" target="_blank"><img src="<%=request.getContextPath() %>/img/logo-final.png"></a>
		</div>
	</header>
	
	<div class="content">
		<div id="barra">
			<div class="texto">
				<b>Emisor:</b> ${user.toUpperCase()}
			</div>
			<div class="boton-gris margin-10" onclick="window.location='<%=request.getContextPath() %>/main/logout.do'">
				Cerrar sesión
			</div>
		</div>
		
		<div id="side-menu">
		<br>
			<div class="menu_div">
				<ul>
				<li>
					<strong class="titulo_certificados">CERTIFICADOS TRABAJADOR</strong>
				</li>
				</ul>
			</div>
			<form action="<%=request.getContextPath() %>/main/changeBpRut.do" method="post">
				<div class="margin-10">
					<strong>Rut Trabajador:</strong>
				</div>
				<div class="field">
					<input type="text" class="textRut" maxlength="12" id="rut" name="rut" value="${rutCRM}" onKeyPress="keyRut();">&nbsp;
					<input type="submit" class="boton" value="consultar">				
				</div>
			</form>
			<div class="error" >
				<html:errors property="rut"/>
			</div>
			<c:if test="${CerAfili=='X' || perfilamiento=='false'}">
			<br>
			<div class="titulo_menu">
					<strong><span class="fas fa-angle-down"></span>&nbsp;Afiliación</strong>
			</div>
			<div class="menu_div">
			        <ul>
			        
			       	<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/afiliacion/generaCertificado.do">Certificado de Afiliaci&oacute;n</a></li>
					</ul>
			</div>
			</c:if>
			<br>
			<div class="titulo_menu">
					<strong><span class="fas fa-angle-down"></span>&nbsp;Crédito</strong>
			</div>
			<div class="menu_div">
			        <ul>
					<c:if test="${CerCreVigen=='X' || perfilamiento=='false'}">
					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/creditosvigentes/listarCreditosVigentes.do">Cr&eacute;ditos Vigentes</a></li>
					</c:if> 
					<c:if test="${CerCreCance=='X' || perfilamiento=='false'}">
					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/creditoscancelados/listarCreditosCancelados.do">Cr&eacute;ditos Cancelados</a></li>
					</c:if> 
					<c:if test="${CerCreFiniq=='X' || perfilamiento=='false'}">			
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/finiquito/finiquitoInicioView.do">Finiquito</a></li>
 					</c:if>  
 					<c:if test="${CerCrePrepa=='X' || perfilamiento=='false'}">
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/prepago/getCreditosPrepago.do">Prepago</a></li>
 					</c:if>
 					<c:if test="${CerCreDeuda=='X' || perfilamiento=='false'}">
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/deuda/deudaInicioView.do">Deuda Ley 20.720 Renegociación SIR</a></li>
					</c:if>
					<c:if test="${CerCreDeuda=='X' || perfilamiento=='false'}">
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/deuda/getCreditosDeuda.do">Deuda Ley 20.720 Liquidación</a></li>
					</c:if>
					<c:if test="${CerCreCons=='X' || perfilamiento=='false'}">
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/bitacora/consultaBitacora.do">Consultas por RUT</a></li>
					</c:if>
					<c:if test="${CerCreAdm=='X'}">
 					<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=certificados/perfilarMenu.do">Perfilamiento Menú</a></li>
					</c:if>
			        </ul>
			</div>
			<br><br>
			<div class="menu_div">
				<ul>
				<li>
					<strong class="titulo_certificados">CERTIFICADOS EMPRESA</strong>
				</li>
				</ul>
			</div>
			
			<form action="<%=request.getContextPath() %>/main/changeBpRut.do" method="post">
				<div class="margin-10">
					<strong>Rut Empresa:</strong>
				</div>
				<div class="field">
					<input type="text" class="textRut" maxlength="12" id="rutemp" name="rutemp" value="${rutEmpresa}" onKeyPress="keyRut();">&nbsp;
					<input type="submit" class="boton" value="consultar">				
				</div>
			</form>
			<div class="error" >
				<html:errors property="rutemp"/>
			</div>
			<br>
			<div class="titulo_menu">
					<strong><span class="fas fa-angle-down"></span>&nbsp;Cotizaciones</strong>
			</div>
			<div class="menu_div">
			        <ul>
			       	<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=${cotizaciones_pagadas}&tipo=empresa">Cotizaciones Pagadas</a></li>
			       	<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=${cotizaciones_trabajador}&tipo=empresa">Cotizaciones por Trabajador</a></li>
			       	<li><a href="<%=request.getContextPath() %>/main/localRouter.do?url=${deuda_previsional}&tipo=empresa">Deuda Previsional</a></li>
			        </ul>
			        <br>
			</div>
		</div>
		<div id="contenedorIframe">
			<iframe src="${url}" id="mainIframe" name="mainIframe" frameborder="0"></iframe>
		</div>
	</div>
	<div id="loading" style="position:absolute; top:50%; left:47%; display:none; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
	</div>
	<script>
	$(document).ready(function() {
		$('a').click(function() {
			var url = $(this).attr('rel');
			$('#mainIframe').attr('src', url);
	
		});
		//comienda el show
		$(window).bind('beforeunload', function(){
			$('#loading').show();
		});
		$("#rut").Rut({
			format_on : 'keyup'
		});
		$("#rutemp").Rut({
			format_on : 'keyup'
		});
		$('#rut').keyup(function(){
        	$(this).val($(this).val().toUpperCase());
    	});
    	$('#rutemp').keyup(function(){
        	$(this).val($(this).val().toUpperCase());
    	});
    	
	});
	
</script>
</body>
</html>