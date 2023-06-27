<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PreviPass</title>
<link href="<c:url value="/css/estilos_interna.css"/>" rel="stylesheet" type="text/css" />
<!-- <link href="<c:url value="/css/grid_960.css"/>" rel="stylesheet" type="text/css" /> -->

	
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	

	<script type="text/javascript">
	  function redireccionar(){
		  document.location = '<%= urlPortalPublico %>';
	  }	  	  
	</script>

<script type="text/javascript">  

    // crea la cookie
	function newCookie(name,value,days) {  
	 var days = 10;    
	 if (days) {  
	   var date = new Date();  
	   date.setTime(date.getTime()+(days*24*60*60*1000));  
	   var expires = "; expires="+date.toGMTString(); }  
	   else var expires = "";  
	   document.cookie = name+"="+value+expires+"; path=/"; 
	 }  

	// Lee las cookies  
	function readCookie(name) {  
	   var nameSG = name + "=";  
	   var nuller = '';  
	  if (document.cookie.indexOf(nameSG) == -1)  
	    return nuller;  
	  
	   var ca = document.cookie.split(';');  
	  for(var i=0; i<ca.length; i++) {  
	    var c = ca[i];  
	    while (c.charAt(0)==' ') c = c.substring(1,c.length);  
	  if (c.indexOf(nameSG) == 0) return c.substring(nameSG.length,c.length); }  
	    return null; 
	}  

	// elimina una cookie  
	function eraseCookie(name) {  
	  newCookie(name,"",1); 
	}  

	// actualzia cookies
	function toMem() {
		if (document.form1.recordarme.checked) {
	    	newCookie('user_previpass', document.form1.j_username.value);     // add a new cookie as shown at left for every  
	    	newCookie('password_previpass', document.form1.j_password.value);   // field you wish to have the script remember
	    	newCookie('remember_previpass','on');
		} else {
			eraseCookie('user_previpass');
			eraseCookie('password_previpass');
			eraseCookie('remember_previpass');
		}
			  
	}  
	  
	// Actualiza la cookie, si corresponde
	function remCookie() {
		var remember = readCookie("remember_previpass");
		if ( remember != "") { 
			document.form1.j_username.value = readCookie("user_previpass");  
			document.form1.j_password.value = readCookie("password_previpass");
			document.form1.recordarme.checked = true;
		}  
	}  
	  
	// prepara el evento de llenado
	function addLoadEvent(func) {  
	  var oldonload = window.onload;  
	  if (typeof window.onload != 'function') {  
	    window.onload = func;  
	  } else {  
	    window.onload = function() {  
	      if (oldonload) {  
	        oldonload();  
	      }  
	      func();  
	    }  
	  }  
	}  
	  
	addLoadEvent(function() {  
	  remCookie();  
	});  
	  
	</script>

</head>



<%
	String version = "Versi&oacute;n 2.5.1 - 27 de Junio de 2012";
	String useragent = request.getHeader("User-Agent");
	// cuando no es explorer 6.0 
	if (useragent == null || 
		(useragent.toLowerCase().indexOf("msie 6") < 0 &&
		useragent.toLowerCase().indexOf("msie 7") < 0)) {
%>

<body>
<div id="caja">
  <form id="form1" name="form1" method="post" action="j_security_check" onsubmit="javascript:toMem();">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="35" colspan="5" valign="top"><img src="img/titulo_login.gif" width="103px" height="21px" /></td>
      </tr>
      <tr>
        <td height="30" colspan="5" class="txt_caja">Realice todos los pagos en l&iacute;nea de sus cotizaciones y servicios caja.</td>
      </tr>
      <tr>
        <td width="11%" height="50" style="padding-right:20px;"><strong>Usuario</strong></td>
        <td width="30%"><input type="text" name="j_username" id="j_username" value=""/></td>
        <td width="9%" style="padding-right:20px;"><strong>Clave</strong></td>
        <td width="28%"><input type="password" name="j_password" id="j_password" value=""/></td>
        <td width="22%" style="padding-left:10px;"><button type="submit" name="" value="login" style="width:65px; height:20px; background-image:url(img/btn_login.jpg); border:none; font-weight:bold; color:#FFF; font-size:11px;">Entrar</button></td>
      </tr>

		<c:if test="${param.error}">
	      <tr>
	        <td colspan="4" align="center"><font color='red'>El usuario y clave no son v&aacute;lidos</font></td>
	        <td>&nbsp;</td>
	      </tr>
		</c:if>
      
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="4%" height="30"><input type="checkbox" name="recordarme" id="recordarme" /> </td>
            <td width="28%" colspan="3"><strong>Recordarme</strong></td>
          </tr>
          <tr>
          <td colspan="4" align="center"><font size="1"><%= version %></font></td>
          </tr>
        </table></td>
      </tr>
    </table>
  </form>
</div>
</body>

<% } else { %>

<body style="background: url(img/bg.jpg) repeat-x">
<div align="center">



<table width="100%">
	<tr>
		<td colspan="3" align="center"><img src="img/header.gif"/><br/><br/></td>
	</tr>
	<tr>
		<td width="20%">&nbsp;</td>
		<td align="center" valign="top">
				<div id="caja" align="left" style="background-color: white; border-color: #1C73A9; border-style: solid; border-width: 1px;">
				  <form id="form1" name="form1" method="post" action="j_security_check" onsubmit="javascript:toMem();">
				    <table width="100%" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td height="35" colspan="5" valign="top"><img src="img/titulo_login.gif" width="103px" height="21px" /></td>
				      </tr>
				      <tr>
				        <td height="30" colspan="5" class="txt_caja">Realice todos los pagos en l&iacute;nea de sus cotizaciones y servicios caja.</td>
				      </tr>
				      <tr>
				        <td width="11%" height="50" style="padding-right:20px;"><strong>Usuario</strong></td>
				        <td width="30%"><input type="text" name="j_username" id="j_username" value=""/></td>
				        <td width="9%" style="padding-right:20px;"><strong>Clave</strong></td>
				        <td width="28%"><input type="password" name="j_password" id="j_password" value=""/></td>
				        <td width="22%" style="padding-left:10px;"><button type="submit" name="" value="login" style="width:65px; height:20px; background-image:url(img/btn_login.jpg); border:none; font-weight:bold; color:#FFF; font-size:11px;">Entrar</button></td>
				      </tr>
				
						<c:if test="${param.error}">
					      <tr>
					        <td colspan="4" align="center"><font color='red'>El usuario y clave no son v&aacute;lidos</font></td>
					        <td>&nbsp;</td>
					      </tr>
						</c:if>
				      
				      <tr>
				        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td width="4%" height="30"><input type="checkbox" name="recordarme" id="recordarme" /> </td>
				            <td width="28%" colspan="3"><strong>Recordarme</strong></td>
				          </tr>
				          <tr>
				          <td colspan="4" align="center"><font size="1"><%= version %></font></td>
				          </tr>
				          <tr>
				          	<td colspan="4" align="right"><font size="2"><a href="javascript:redireccionar();"><b>Volver</b></a></font></td>
				          </tr>
				          
				        </table></td>
				      </tr>
				    </table>
				  </form>
				</div>
		</td>
		<td width="20%">&nbsp;</td>
	</tr>
</table>


</div>
	
</body>

<% } %>

<div style="display: none;"><%= useragent %></div>

</html>
