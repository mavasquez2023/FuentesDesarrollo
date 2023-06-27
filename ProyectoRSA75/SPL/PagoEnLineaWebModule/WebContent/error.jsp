<%@ page isErrorPage="true" %>
<%
String ctx = request.getContextPath();
%>
<%!
  void printStackTrace(Throwable exception, java.io.Writer out) throws java.io.IOException{
  	java.io.StringWriter sw = new java.io.StringWriter();
	java.io.PrintWriter pw = new java.io.PrintWriter(sw);
	exception.printStackTrace(pw);
	out.write(sw.toString());
	out.write("\r\n");
  }
%>











<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>

	<link href="<%= ctx %>/css/Interna_Araucana.css" rel="stylesheet" type="text/css">
	<link href="<%= ctx %>/css/web_publica.css" rel="stylesheet" type="text/css">
	<link href="<%= ctx %>/css/calendar-system.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%= ctx %>/js/comunes.js"></script>
	<title>La Araucana</title>
</head>
<body>
<table width="759" height="259" border="0" align="left" cellpadding="0" cellspacing="0">
<tr align="left" valign="top"> 
	<td width="759">
		<!-- INICIO HEADER -->

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr> 
  <td>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td width="493" valign="top"><img src="<%= ctx %>/images/top1.gif" width="493" height="74" /></td>
        <td valign="top"><table border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td align="left" valign="top" background="<%= ctx %>/images/top3.gif"><img src="<%= ctx %>/images/top2.gif" width="151" height="25" /></td>
              <td align="right" valign="top" background="<%= ctx %>/images/top3.gif"><img src="<%= ctx %>/images/top4.gif" width="115" height="25" /></td>
            </tr>
            <tr align="right"> 
              <td colspan="2"><img src="<%= ctx %>/images/top5.gif" width="215" height="49" hspace="10" /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </td>
</tr>
<tr> 
  <td class="Titulos">
      <table width="760" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="36%" height="23" align="left" valign="middle" class="titulos_formularios"> 
            <font color="#999999"><font color="#666666">
            	<strong>&nbsp;&nbsp;&nbsp;&nbsp;</strong>&nbsp;<!-- Aqui la ruta de navegacion --></font></font>
          </td>
          <td width="64%" height="23" align="right" valign="bottom" class="titulos_formularios">
          	<table height="18" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="18" align="right" valign="middle" class="botonera_ppalactivada">&nbsp;</td>
                <td align="right" valign="middle" class="botonera_ppalactivada">
                </td>
                <td width="10" align="right" valign="middle" class="botonera_ppalactivada">&nbsp;</td>                
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td height="22" colspan="2" align="left" class="titulos_formularios"> 
            <div align="center"><img src="<%= ctx %>/images/sombra.jpg" width="739" height="7" /></div></td>
        </tr>
      </table>
  </td>
</tr>        
</table>
		
		<!-- FIN HEADER  -->
	</td>
</tr>
<tr align="center" valign="top"> 
	<td>
		
		<!-- INICIO MAIN -->




<!-- 
<pre>
<%printStackTrace(exception, new java.io.PrintWriter(new java.io.PrintStream(System.out)));%>               
<%printStackTrace(exception, out);%>               
<%while (exception instanceof ServletException) {
				exception = ((ServletException) exception).getRootCause();	
				if (exception!=null){ %>
					<b>ServletException root:</b>
					<%printStackTrace(exception, out);%>
				<%}%>
<%}%>
</pre>
-->







<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Error en el proceso:</strong> </strong></td>             
            </tr>
            <tr valign="top"> 
              <td height="30" align="left" bgcolor="#FFFFFF"><span class="titulos_formularios">&nbsp;</span> </td>
            </tr>
          </table></td>
      </tr>
    </table>
  </td>
</tr>
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr> 
              <td align="center" class="textos_formularios">Estimado Cliente, moment&aacute;neamente no podemos atenderlo, disculpe las molestias.</td>             
            </tr>        
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
     
</table>

		<!-- FIN MAIN -->

	</td>
</tr>
<tr align="center" valign="top"> 
  <td height="32">

	<!--  INICIO TAIL -->
<script language="JavaScript" src="<%= ctx %>/js/piepagina.js" type="text/javascript"> </script>
	<!--  FIN TAIL -->
  </td>
</tr>
</table>
</body>
</html>















