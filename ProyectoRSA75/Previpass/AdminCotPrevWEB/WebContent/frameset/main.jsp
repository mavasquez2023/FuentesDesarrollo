<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/frameset/adminAraucana.css" />" rel="stylesheet" type="text/css">
</HEAD>
	<FRAMESET rows="150, *" border="0" framespacing="0" frameborder="no">
	     <NOFRAMES>
	         <BODY>
	             Su visualizador no soporta frames. Pulse 
	             <A HREF="<c:url value="/frameset/body.jsp" />">aqui </A> para volver.
	         </BODY>
	    </NOFRAMES>
	    <FRAME SRC="<c:url value="/frameset/titulo.jsp" />" name="titulo" id="titulo" scrolling="no"  noresize="noresize"/>
      	<FRAMESET COLS="180, *" border="0">
        	<FRAME SRC="<c:url value="/frameset/menu.jsp" />" name="menu" id="menu" noresize="noresize" scrolling="no" style="overflow-x:hidden; overflow-y:auto;"/> 
	      	<FRAMESET ROWS="50, *" border="0">
	        	<FRAME SRC="<c:url value="/frameset/user.jsp" />" name="user" id="user" noresize="noresize"/>
	         	<FRAME SRC="<c:url value="/ListarUsuarios.do?limpiaPath=" />" NAME="BODY" id="BODY" noresize="noresize"/>  
	      	</FRAMESET>  
      	</FRAMESET>
	</FRAMESET>
</HTML>
