<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page errorPage="error.jsp" %>
<html>
<HEAD>
<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath() %>/css/Interna_Araucana.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/web_publica.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/XMLDisplay.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/menu.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/screen.css" rel="stylesheet" type="text/css">
<TITLE>Licencia M&#233;dica Electr&#243;nica</TITLE>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/lme.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/utils.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/XMLDisplay.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/menu.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_setup_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_lang.js"></SCRIPT>
<script>
	function init(){
		var action= "<%= request.getAttribute("action") %>";
		var ultimoEstado= "<%= request.getAttribute("ultimoEstado") %>";
		if(action=="upload"){
			if(ultimoEstado=="72"){
				showExecLcc(true,'execLMEDetLcc72');
			}else if(ultimoEstado==""){
				showExecLcc(true,'execLMEDetLcc');
			}
			$('mensajeUpload').innerHTML = "<%= request.getAttribute("respuesta") %>";
			opeVordel();
		}else{
			schedulerStatus();
		}
	}
</script>
<!-- version 19-07-2013-->
</HEAD>
<BODY onload="init()" bgcolor="white">
<table align="center"><tr><td>
<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg"
	width="767" height="81">

<!--<IMG border="0" src="<%=request.getContextPath() %>/img/[Banner] Proyectyos_.jpg" width="767" height="81">-->
<form name="form1" method="post" action="/lme/lme.do">
<!-- Menu bar. -->
<INPUT type="hidden" name="admin" id="admin" value="<%=request.getParameter("role")%>"/>

<div class="menuBar" style="width:767;">
<a class="menuButton" href="" onclick="return buttonClick(event, 'viewMenu');" onmouseover="buttonMouseover(event, 'viewMenu');">Licencia</a>
<%if("admin".equals(request.getParameter("role")) ){%>
<a class="menuButton" href="" onclick="return buttonClick(event, 'fileMenu');" onmouseover="buttonMouseover(event, 'fileMenu');">Proceso</a>
<a class="menuButton" href="" onclick="return buttonClick(event, 'toolsMenu');" onmouseover="buttonMouseover(event, 'toolsMenu');">Ejecutar</a>
<a class="menuButton" href="" onclick="return buttonClick(event, 'editMenu');" onmouseover="buttonMouseover(event, 'editMenu');">Log</a>
<a class="menuButton" href="" onclick="return buttonClick(event, 'adminLme');" onmouseover="buttonMouseover(event, 'adminLme');">Administrador</a>
<%} %>

<!--<a class="menuButton" href="" onclick="return buttonClick(event, 'optionsMenu');" onmouseover="buttonMouseover(event, 'optionsMenu');">Options</a>-->
<!--<a class="menuButton" href="" onclick="return buttonClick(event, 'helpMenu');" onmouseover="buttonMouseover(event, 'helpMenu');">Help</a>-->
</div>
<!-- Main menus. -->
<div id="fileMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" title="Start" onclick="javascript:start()" href="#">Iniciar</a><!-- exec('start') -->
	<a class="menuItem" title="Stop"  onclick="javascript:exec('stop')" href="#">Detener</a>
</div>
<div id="viewMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" title="" onclick="javascript:displayDetailForm()" href="#">Detalle</a>
</div>
<div id="toolsMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" title="LMEEvenLcc" onclick="javascript:showExecLcc(true,'execLMEEvenLcc')"  href="#">Consumir Estados</a>
	<a class="menuItem" title="LMEDetLcc" onclick="javascript:showExecLcc(true,'execLMEDetLcc')" href="#">Consumir Licencias</a>
	<a class="menuItem" title="LMEDetLcc" onclick="javascript:showExecLcc(true,'execLMEDetLcc72')" href="#">Consumir Estado 72</a>
</div>
<div id="editMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" title="Export File" onclick="javascript:getLogFile()" href="#">Exportar Log</a>
<!--	<a class="menuItem" title="Show Log" onclick="javascript:displayLog()" href="#">Desplegar Log</a>-->
</div>
<div id="loadingmsg" style="display: none; position:absolute; z-index:3; left:240; top:200; width:200; height:100; border-width:1; border-style:ridge; background-color:#E6F0F1">
	<center><br><br>
		<font face="Arial" Size="3"><b>Procesando.....</b></font><br>
		<!-- include gif image if u want --> <br><br>
	</center>
</div>

<div id="adminLme" class="menu" onmouseover="menuMouseover(event)">

	<a class="menuItem" title="lmeCero" onclick="javascript:getLmeCero()" href="#">Lme Est 0</a>
<!--	<a class="menuItem" title="lmeCero" onclick="javascript:consMasiva()" href="#">Lme Consulta y Consumo.</a>  -->
	
	
</div>
	
<!--	<p>Iniciar LME</p>-->

	<div id="divStatus" class="texto"></div>
	  <label>
	  <INPUT type="hidden" name="event"/>
	  </label>	 
	</form>
<!--<hr>-->

<%-- exportLogFile --%>
	<form name="form2" method="post" action="/lme/log.do">
		<INPUT type="hidden" name="event"/>

	</form>

<form action="uploadcsv.do" name="form3" method="post" enctype="multipart/form-data">
<!-- 	LMEDetLcc -->
	<div id="divLcc" class="texto">
		<SPAN id="divLccTitle"></SPAN><BR/>
		&nbsp;&nbsp;Licencia : <input type="text" id="license" name="license" maxlength="10" size="10" style="border: 1px solid #6C7D8D; color: #666666; width: 100px; height: 20px; font-size: 11px;"/>
		Operador: <select id="codOpe" name="codOpe" style="border: 1px solid #6C7D8D; color: #666666; /*width: 240px;*/ height: 20px; font-size: 11px; padding: 0px; z-index:12;"></select>
		<input type="button" name="logFile" id="logFile" value="Buscar" onclick="getLMEDetLcc()" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"/>	
		<span id="spanLcc" style="font: normal normal 1em/130% Arial, Helvetica, sans-serif;font-size: 8pt;"></span>	
	</div>
<!-- 	LMEEvenLcc -->
	<DIV id="divExecLcc" style="display: none;"  class="texto">
		<SPAN id="divExecTitle"></SPAN><BR/>
		<DIV id="divExecEvenLcc">
			&nbsp;&nbsp;Fecha:<input type="text" readonly="readonly" name="dateLcc" id="dateLcc" style="border: 1px solid #6C7D8D; color: #666666; width: 100px; height: 20px; font-size: 11px;"/>
			
			<INPUT type="button" name="cal1" id="cal1" style="background-image: url(/lme/img/calendar.gif);border: 0;width: 25px;height: 20px;"/>
			&nbsp;Hora Desde (HH:mm): <input type="text" name="horaDesde" id="horaDesde" style="border: 1px solid #6C7D8D; color: #666666; width: 100px; height: 20px; font-size: 11px;"/>
			&nbsp;Hora Hasta: <input type="text" name="horaHasta" id="horaHasta" style="border: 1px solid #6C7D8D; color: #666666; width: 100px; height: 20px; font-size: 11px;"/>
			<INPUT type="button" name="execute" value="Enviar" onclick="execLcc()" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"/>			
			
			<INPUT type="hidden" id="executeLcc"/>
			<script type="text/javascript">
			var d = getDateFormat();
			Calendar.setup({
				inputField  : "dateLcc",
				ifFormat    : "%d/%m/%Y",
				button      : "cal1",
				minDate		: "2000-01-01",
				maxDate		: d,
				yearButtons	: false,
				id		: 1
				});		
				document.getElementById('dateLcc').onclick = document.getElementById('cal1').onclick;
			</script>
		</DIV>
		<DIV id="divExecDetLcc">
			&nbsp;&nbsp;Licencia: <input type="text" id="exelicense" name="exelicense" maxlength="10" size="10" style="border: 1px solid #6C7D8D; color: #666666; width: 100px; height: 20px; font-size: 11px;"/>
			Operador: <select id="codOpe_" name="codOpe_" style="border: 1px solid #6C7D8D; color: #666666; /*width: 240px;*/ height: 20px; font-size: 11px; padding: 0px; z-index:12;"></select>
			<input type="hidden" name="ultimoEstado" id="ultimoEstado" value="" /><input type="button" name="buttom" id="buttom" value="Enviar" onclick="execDetLcc()" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"/>
			<br/><br/>
			Archivo CSV: <input  id="file" name="file" type="file" value="" />
			<input type="button" name="buttom" id="buttom" value="Enviar" onclick="execDetLccCSV()" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"/>
			<br/>
			<br/>
			<font size="1" color="#999999">(Formato archivo: operador;licencia;dv-licencia)</font>
			<br/>
			<br/>
			<br/>
			<div id="mensajeUpload" style="color:blue;"></div>
		</DIV>
		<BR/>		
		<SPAN id="divExecReturn"></SPAN>
				
	</DIV>
 
	<div id="principal" class="texto">
	  	   <br/>
	  
  <table id="resultados" width="100%" border="0" style="border:#999 1px solid;" class="texto" style="display: none;">
  <tr>
	<td width="9%"><strong>Ide Operador</strong></td>
	<td width="9%"><strong>N° Licencia LME.</strong></td>
	<td width="3%"><strong>DV.</strong></td>
	<td width="9%"><strong>Rut Afiliado</strong></td>
	<td width="7%"><strong>Estado</strong></td>
	<td width="7%"><strong>Fecha Estado</strong></td>
	<td width="7%"><strong>Hora Estado</strong></td>
	<td width="11%"><strong>Fec. Operación</strong></td>
	<td width="29%"><strong>Mensaje Error</strong></td>
	<td width="9%"><strong>Acción</strong></td>
		</tr>
</table>
		    <div id="contenido" style="overflow:auto;height:290px;">
	 		    </div>
		
	</div>
</form>

</td></tr>
</table>

</body>
</html>
