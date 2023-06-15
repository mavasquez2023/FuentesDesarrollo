<!DOCTYPE html>
<%
	session=request.getSession(true);
	String nombre="";
	String fechaPeriodo="";
	
	//para respuesta de generacion de documentos
	String msgEP="";
	
	//para estado de procesos
	String keyProcesoCarga="";
	String keyProcesoValidacion="";
	
	if(session==null || session.isNew()){
		response.sendRedirect("errorLogin.jsp");
	}else{
		nombre=(String)session.getAttribute("nombre");
		if(nombre==null){
			response.sendRedirect("errorLogin.jsp");
		}else{
				fechaPeriodo=(String)request.getAttribute("fechaPeriodo");
				keyProcesoCarga=(String)request.getAttribute("keyProcesoCarga");
				keyProcesoValidacion=(String)request.getAttribute("keyProcesoValidacion");
				msgEP=(String)request.getAttribute("msgEscrituraPlanos");
				
				if(msgEP==null){msgEP="";}
				if(keyProcesoCarga==null){keyProcesoCarga="";}
				if(keyProcesoValidacion==null){keyProcesoValidacion="";}
				
		}
	}
 %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10" />
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM Software Development Platform">
	<META http-equiv="Content-Style-Type" content="text/css">
	<title>SIMAT</title>
	
	<script language="JavaScript" src="jqSimat/menuOpen/menu.js"></script>
	<link rel="stylesheet" href="css/menu.css" type="text/css">

	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/simular.css">
	<link rel="stylesheet" href='./css/main.css' type="text/css" />
	<link rel="stylesheet" href='./css/screen.css' type="text/css" />
	<link rel="stylesheet" href='./css/interior.css' type="text/css" />
	
	<link rel="stylesheet" href='cssSimat/estructura.css' type="text/css" />	
			
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	
	<script src="jqSimat/menuOpen/ajustesMenu.js"></script>
	
</head>

<body bgcolor="white">

<center>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">		
		<div id="workSpaceOpciones" name="workSpaceOpciones">
				<div class="menuBar">
					<a class="menuButton" href="#" onclick="javascript:cambiarPeriodo()">Cambiar Periodo</a>
					
					<a class="menuButton" href="" onclick="return buttonClick(event, 'adminProcesos');">Procesos</a>											
					<a class="menuButton" href="" onclick="return buttonClick(event, 'mantMenu');" onmouseover="buttonMouseover(event, 'mantMenu');">Ajustes</a>
					<a class="menuButton" href="#" onclick="return buttonClick(event, 'generarDocumentos');"> Generar Documentos</a>
					<a class="menuButton" href="" onclick="return buttonClick(event, 'docInformeFinanciero');" onmouseover="buttonMouseover(event, 'docInformeFinanciero');">Informe Financiero</a>
					<a class="menuButton" href="" onclick="return buttonClick(event, 'adminMenu');" onmouseover="buttonMouseover(event, 'adminMenu');">Administraci&oacute;n</a>
					<a class="menuButton" href="" onclick="return buttonClick(event, 'paseContable');" onmouseover="buttonMouseover(event, 'paseContable');">Pase Contable</a>
					<a class="menuButton" href="" onclick="return buttonClick(event, 'VisorLOG');" onmouseover="buttonMouseover(event, 'VisorLOG');">Visor de LOG</a>
					<a class="menuButton" href="#" onclick="javascript:closeSesion()">Salir</a>
				</div>			
					
				<div id="adminProcesos" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:ProcesoGenerarArchivos()" href="#">Generar BD SIMAT</a>
					<a class="menuItem" title="" onclick="javascript:ProcedimientoValidar()" href="#">Validaci&oacute;n BD SIMAT</a>
				</div>
					
				<div id="generarDocumentos" class="menu" onmouseover="menuMouseover(event)">
						<a class="menuItem" title="" onclick="javascript:generarPlano_1()" href="#"> Plano 1</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_2()" href="#"> Plano 2</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_3()" href="#"> Plano 3</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_4()" href="#"> Plano 4</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_5()" href="#"> Plano 5</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_6()" href="#"> Plano 6</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_7()" href="#"> Plano 7</a>
						<a class="menuItem" title="" onclick="javascript:generarPlano_8()" href="#"> Plano 8</a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_1()" href="#"> Cuadro Estadistico 1</a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_2()" href="#"> Cuadro Estadistico 2 </a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_3()" href="#"> Cuadro Estadistico 3 </a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_4()" href="#"> Cuadro Estadistico 4 </a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_5()" href="#"> Cuadro Estadistico 5 </a>
						<a class="menuItem" title="" onclick="javascript:generarCuadro_6()" href="#"> Cuadro Estadistico 6 </a>
						<a class="menuItem" title="" onclick="javascript:generarRCP()" href="#"> Resumen Cotizaciones <br>Previsionales</a>
						<a class="menuItem" title="" onclick="javascript:generarILF4501()" href="#">ILF4501</a>
				</div>
				
				<div id="paseContable" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:generarPlanoPaseContable()" href="#">Pase contable</a>
				</div>
					
				<div id="mantMenu" class="menu" onmouseover="menuMouseover(event)">
						<a class="menuItem" title="" onclick="javascript:goPag1()" href="#">REINTEGROS</a>
						<a class="menuItem" title="" onclick="javascript:goPag2()" href="#">SUBSPREPOSTNM</a>
						<a class="menuItem" title="" onclick="javascript:goPag3()" href="#">SUBSPARENTAL</a>
						<a class="menuItem" title="" onclick="javascript:goPag5()" href="#">CONTROLDOCU</a>
						<a class="menuItem" title="" onclick="javascript:goPag6()" href="#">DOCSREVALREEM</a>
						<a class="menuItem" title="" onclick="javascript:goPag7()" href="#">DATOSLICCOB</a>
						<a class="menuItem" title="" onclick="javascript:goPag8()" href="#">DATOSLICRESOL</a>
				</div>
						 			 
				<div id="adminMenu" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:goPag9()" href="#">Mantenedor Usuarios</a>
					<a class="menuItem" title="" onclick="javascript:goPag11()" href="#">Mantenedor C&oacute;digos de Dominio</a>
				</div>
					
				<div id="docInformeFinanciero" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:visorIF()"href="#">Ver Informe Financiero</a>
				</div>
					
				<div id="VisorLOG" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:goPag10()" href="#">Ver Log.</a>
				</div>
					
				<div id="VisorHistorico" class="menu" onmouseover="menuMouseover(event)">
					<a class="menuItem" title="" onclick="javascript:openHistorico()" href="#">Abrir Repositorio</a>				
				</div>					
			</div>
							
			<div id="workSpacePeriodo" name="workSpacePeriodo">	
				<fieldset class="form-fieldset">
					<div class="field">						
							<%
							out.println("<strong>"+"Periodo seleccionado: "+fechaPeriodo+"</strong><br><br>");
							out.println("<strong>"+keyProcesoCarga+"</strong><br><br>");
							out.println("<strong>"+keyProcesoValidacion+"</strong><br><br>");					
							%>
					<input class="boton" type="button" value='Consultar Estado Procesos' onclick="javascript:getEstadosProcesos()"/>									
					</div>
					<br>
				</fieldset>
			</div>	
			
			<div id="loadMSG" name="loadMSG">
				<center>
					<img id="wait" name="wait" src='./imgSimat/Loading.gif'><br>Espere un momento...<br>
				</center>					
			</div>
			
			<fieldset class="form-fieldset">
			<div id="workSpaceMensajes" name="workSpaceMensajes">
				
					<div id="RespuestaEjecucion">
						<%
							out.println("<label class='label'>"+msgEP+"</label><br>");
						%>	
					</div>
					<div id="loadMenu" name="loadMenu" >
						<center>
							<img id="wait" name="wait" src='./imgSimat/Loading.gif'><br>Espere un momento...<br>
						</center>					
					</div>
			</div>
		</fieldset>
		</div><!-- fin workSpace -->
	</div><!-- fin wrapper -->
</center>


</body>
</html>
