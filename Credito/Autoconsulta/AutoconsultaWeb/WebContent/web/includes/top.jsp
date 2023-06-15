<!--<table border="0" cellpadding="0" cellspacing="0" width="973">
  <tr valign="top">
    <td></td>
  </tr>
</table>
-->


<header>
		<div class="contenedor">
			<a href="http://www.laaraucana.cl" target="_blank"><img src="/AutoconsultaWeb/web/images/v2/logo-final.png"></a>
		</div>
</header> 
<script>
	function logoff(){

		document.logout.submit(); 
	}

function vuelveAPerfiles(){	 
	document.welcome.submit();  
}      
   
</script> 


<div class="texto">
<form method="get" action="<%=request.getContextPath()%>/web/logout.do" name="logout">
  <input name="aceptar2"  type="button" class="botongris" onclick="this.form.submit()" onmouseover="this.className='botongrisOver'" onmouseout="this.className='botongris'" value="Cerrar Sesión"  />
</form>
<%if(request.getSession(true).getAttribute("nombreEmpresas")!=null) {%>
<form method="post" action="<%=request.getContextPath().toString()%>/web/Welcome.do" name="welcome">
  
  <!--<html:link styleClass="grupoopcion" href="javascript:vuelveAPerfiles()">       
					<font color="yellow">Volver</font>
				</html:link>-->
<input name="aceptar"  type="button" class="botongris" onclick="location.href='logout.do'; return false;" style="float: left;" onmouseover="this.className='botongrisOver'" onmouseout="this.className='botongris'" value="Volver"  />

</form>
<%}%>
</div> 
<div style="clear:left"></div>