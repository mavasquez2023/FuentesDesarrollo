<%
	String nombre;
	nombre = request.getParameter("usuario");
   
	if (nombre == null || "".equals(nombre))
		response.sendRedirect("cotizacionJaas.jsp");
		
%>
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<html>
	<HEAD>
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="Manejador de Documentos de Cotización a través de Content Manager OnDemand">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<title>Cotizaci&oacute;n Previsional Electr&oacute;nica</title>
		<script language="JavaScript" type="text/javascript">
			function showMenu() {
				var frameset = document.getElementById("menuFrame");
				frameset.cols = "190,*,0,0";
			}
		</script>
	</HEAD>
	<frameset rows="157,*,0" frameborder="no">
		<frame src="banner.html" scrolling="no" name="frmPresenta" id="frmPresenta" noresize>
		<frameset cols="0,*,0,0" frameborder="no" id="menuFrame" onload="showMenu()">
			<frame src="PaginaPresentacion?nombre=<%out.print (nombre);%>" name="HITLIST" noresize scrolling="auto">
				<frameset rows="345,*" id="main_frame" frameborder="no">
					<frame src="fblank_Instruc.htm" id="frmFormulario" name="frmFormulario" noresize scrolling="no" >
					<frame src="fblank.htm" id="LIST" name="LIST" noresize scrolling="auto">
				</frameset>
			<frame src="fblank.htm" name="frmDownload" scrolling="no" noresize>
			<frame src="fblank.htm" name="frmPrint" scrolling="no" noresize>
		</frameset>
		<frame src="fblank.htm" name="frmResultado" scrolling="auto">
	</frameset>
	<body bgcolor="#C0C0C0">
	</body>
	</html>
