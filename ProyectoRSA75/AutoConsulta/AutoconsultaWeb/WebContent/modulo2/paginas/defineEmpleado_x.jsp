<%--
    Document   : consultas.jsp
    Created on : 10-04-2012, 11:58:29 AM
    Author     : desajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cl.laaraucana.modulo2.utils.MenuMod2Utils"%>
<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<%
session.setAttribute("md2.titulo", "Consultas");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file = "/modulo2/includes/headhtml.jsp"%>

<body class="columna">
<%@ include file = "/modulo2/includes/userid.jsp"%>

<%@ include file = "/modulo2/includes/opciones.jsp"%>

<div id="container">
<%@ include file = "/modulo2/includes/menues.jsp"%>

<div id="contenidos">


<div class="container_12 altura-contenedor">
	<div class="grid_6">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td align="center"><h3>Ingreso por Cédula de Identidad del empleado</h3></td>
        </tr>
	    <tr>
	      <td align="center" style="padding-top:35px;"><form id="form1" name="form1" method="post" action="">
	        <table width="335" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="55" colspan="5" align="center" background="../../WEB-INF/paginas/img/fdo_campoRUT.jpg"><label for="RUT"></label>
	              <input name="RUT" type="text" class="caja" id="RutAfiliado" value='<bean:write name="afiliado.rut"/>' maxlength="12" /></td>
              </tr>
	          <tr>
	            <td width="35">&nbsp;</td>
	            <td width="88" align="center"><img src="../../WEB-INF/paginas/img/btn_1.jpg" width="83" height="69" vspace="5" /></td>
	            <td width="88" align="center"><img src="../../WEB-INF/paginas/img/btn_2.jpg" width="83" height="69" vspace="5" /></td>
	            <td width="88" align="center"><img src="../../WEB-INF/paginas/img/btn_3.jpg" width="83" height="69" vspace="5" /></td>
	            <td width="35">&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_4.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_5.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_6.jpg" width="83" height="69" vspace="5" /></td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_7.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_8.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_9.jpg" width="83" height="69" vspace="5" /></td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_0.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center"><img src="../../WEB-INF/paginas/img/btn_K.jpg" width="83" height="69" vspace="5" /></td>
	            <td align="center">&nbsp;</td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td colspan="5">
	            	  <a href="javascript:doEncargado()"><img src="../../WEB-INF/paginas/img/btn_aceptar.jpg" width="148" height="66" vspace="8" align="left" border="0"/></a>
	            	  <img src="../../WEB-INF/paginas/img/btn_borrar.jpg" width="148" height="66" vspace="8" align="right" />
	            </td>
              </tr>
            </table>
          </form></td>
        </tr>
      </table>
</div>
</div>
</div>
</div>
<%@ include file = "/modulo2/includes/formEncagado.jsp"%>

</body>
</html>
