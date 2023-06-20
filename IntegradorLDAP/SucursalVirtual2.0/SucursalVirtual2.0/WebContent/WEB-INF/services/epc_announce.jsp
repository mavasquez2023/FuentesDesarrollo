<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*"
%>

<%
	String nextPage = "";
	String URL = request.getRequestURL().toString();
	String URI = request.getRequestURI();
	int index = URL.indexOf(URI);
			
	if (index > 0) {
		nextPage =
			  URL.substring(0, index)
			+ request.getContextPath() + "/router.do";
	}
%>				

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">    
    
    <jsp:include page="/WEB-INF/meta_application.jsp" />
    
    <link href="/sv/theme/Master.css" rel="stylesheet" type="text/css" />
	
	<title>La Araucana C.C.A.F. - Anuncio General</title>
	
	<script language="JavaScript1.2" src="js/common.js" type="text/javascript">
	</script>
	
	<script language="JavaScript1.2">

		function initForm() {
	        document.forms[0].action.disabled = true;
		}

		function service(name) {
			url =
					"<%= nextPage %>" + "?service=" + name
							+ "&ts=" + <%= request.getParameter("ts") %>;

			window.location = url;
		}
	</script>
	
</head>

<body onload="initForm()">

<center>
<img src="images/EPC.jpg">

	<table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
		  <tr>
		    <td width="100%">
				<jsp:include page="/WEB-INF/services/epc_announce_text.jsp.DISABLED" />
			</td>
		 </tr>
	</table>

    <form method=GET action="javascript:service('SRV005')">
        <input type="submit" name="action" class="buttons" value='Continuar'>
    </form>

	<br>
	<br>
	
<table border="0" width="80%">
  <tr>
    <td width="95%">
      <p align="right"><font face="Times New Roman" size="3">
	  Cualquier consulta sobre esta materia puede efectuarla llamando
	  a nuestro Call Center 600 428100 o bien dirigirla a
      </font></p>
    </td>

    <td width="62" height="35">
        <a href="javascript:openFullNewWindow('http://www.laaraucana.cl/?seccion=162&nom=Regimenes%20Legales', 'CONTACTO')">
           <img border="0" src="images/contact.jpg"><a/></td>
  </tr>
</table>

</center>
	
</body>
</html>


