<%-- 
    Document   : afiliacion
    Created on : 10-04-2012, 07:08:30 PM
    Author     : desajee
--%>
<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "/modulo2/includes/headhtml.jsp"%>
<script>
function setDat(data) {
var sRut = document.form1.rutAfiliado.value;
    sRut = sRut.replace("-","");
    if (data=="-" && sRut.length>0) {
        sRut = sRut.substr( 0 , sRut.length-1);
    } else {
      if (sRut.length<12) {
             sRut = sRut + data;
      }
    }
    if (sRut.length > 1) {
       sRut = sRut.substr( 0 , sRut.length-1) + "-" + sRut.charAt(sRut.length-1);
    }
    document.form1.rutAfiliado.value = sRut;
}
function doEmpleado() {
  
    document.fEncargado.rutAfiliado.value = document.form1.rutAfiliado.value;
    
    document.fEncargado.submit();

}
function doHide(){
var p = window.parent.document.getElementById('spIdImprime');
  if (p!=null && p!='undefined') {
     p.style.display = 'none';
  }
}
</script>
<body style="background:url(none)" onload="doHide();">
<div class="container_12">
  <div class="grid_12 omega">
    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td id="info">

<logic:notEmpty name="validation.message"> 
<div style="border:1px solid red; ">
<bean:message key='<%=(String)session.getAttribute("validation.message") %>'/>
<logic:notEmpty name="info"> <bean:message key='<%=(String)session.getAttribute("validation.info") %>'/></logic:notEmpty>
</div>
</logic:notEmpty>

<form id="form1" name="form1" method="post" action="">
<table>
<tr><td valign="top">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td align="center"><h3>Ingrese C&eacute;dula de Identidad del empleado</h3></td>
        </tr>
	    <tr>
	      <td align="center">
	      <input name="rutAfiliado" type="text" class="caja" id="rutAfiliado" value='<bean:write name="afiliado.fullRut"/>' maxlength="12" />
	      </td>
        </tr>
      </table>
</td>
<td>
            <table width="335" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="35">&nbsp;</td>
	            <td width="88" align="center"><img src="img/btn_1.jpg" width="83" height="69" vspace="5" onclick="setDat(1)"/></td>
	            <td width="88" align="center"><img src="img/btn_2.jpg" width="83" height="69" vspace="5" onclick="setDat(2)"/></td>
	            <td width="88" align="center"><img src="img/btn_3.jpg" width="83" height="69" vspace="5" onclick="setDat(3)"/></td>
	            <td width="35">&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="img/btn_4.jpg" width="83" height="69" vspace="5" onclick="setDat(4)"/></td>
	            <td align="center"><img src="img/btn_5.jpg" width="83" height="69" vspace="5" onclick="setDat(5)"/></td>
	            <td align="center"><img src="img/btn_6.jpg" width="83" height="69" vspace="5" onclick="setDat(6)"/></td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="img/btn_7.jpg" width="83" height="69" vspace="5" onclick="setDat(7)"/></td>
	            <td align="center"><img src="img/btn_8.jpg" width="83" height="69" vspace="5" onclick="setDat(8)"/></td>
	            <td align="center"><img src="img/btn_9.jpg" width="83" height="69" vspace="5" onclick="setDat(9)"/></td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="img/btn_0.jpg" width="83" height="69" vspace="5" onclick="setDat(0)"/></td>
	            <td align="center"><img src="img/btn_K.jpg" width="83" height="69" vspace="5" onclick="setDat('K')"/></td>
	            <td align="center">&nbsp;</td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td colspan="5">
	            	  <a href="javascript:doEmpleado()"><img src="img/btn_aceptar.jpg" width="148" height="66" vspace="8" align="left" border="0"/></a>
	            	  <img src="img/btn_borrar.jpg" width="148" height="66" vspace="8" align="right" border="0" onclick="setDat('-')"/>
	            </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
</td></tr></table>

          <br />
          </form></td>
        </tr>
    </table>
    
  </div>
  <div style="clear:left;"></div>
</div>
<% if (session.getAttribute("md2.titulo").equals("Consultas")) { %>
<form name="fEncargado" action="consultas.do" method="post" target="_top">
<%} else { %>
<form name="fEncargado" action="certificados.do" method="post" target="_top">
<%} %>

    <input type="hidden" name="rutAfiliado" value="<bean:write name="afiliado.fullRut"/>"/>
    <input type="hidden" name="md2_opcion"  value='<bean:write name="md2_opcion"/>'/>
    <input type="hidden" name="md2_opcionMnu" value='<bean:write name="md2_opcionMnu"/>'/>
</form>

</body>
</html>
