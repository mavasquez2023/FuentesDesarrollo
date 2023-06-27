<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
String sRutUsuario = request.getParameter("RUTUsuario");
session.setAttribute("RUTUsuario", sRutUsuario);
System.out.println("inclave.jsp: sRutUsuario [" + sRutUsuario + "]");
if (sRutUsuario==null) { %>
<jsp:forward page="ingreso.jsp"/>
<% } %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ingreso Clave</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/grid.css" rel="stylesheet" type="text/css" />
</head>
<script>
function setDat(data) {
var sDat = document.form1.j_password.value;
    
    if (data=="-" && sDat.length==0) {
      document.form1.action = "ingreso.jsp";
      document.form1.submit();
    } else if (data=="-" && sDat.length>0) {
        sDat = sDat.substr( 0 , sDat.length-1);
    } else {
      if (sDat.length<4) {
          sDat = sDat + data;
      }

    }
    document.form1.j_password.value = sDat;
}
</script>
<body>
<div class="container_12 altura-contenedor">
	<div class="grid_6">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td width="95%">&nbsp;</td>
        </tr>
	    <tr>
	      <td><img src="img/logo_araucana.png" width="414" height="40" /></td>
        </tr>
	    <tr>
	      <td align="right"></td>
        </tr>
	    <tr>
	      <td>&nbsp;</td>
        </tr>
	    <tr>
	      <td class="titulo" style="padding-top:140px;"><h1>Estimado Afiliado</h1>Por favor ingrese su Clave Secreta</td>
        </tr>
	    <tr>
	      <td class="titulo" style="padding-top:140px;">&nbsp;</td>
        </tr>
      </table>
	</div>
    <div class="grid_6">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" height="700px">
        <tr>
          <td align="center"><form id="form2" name="form1" method="post" action="j_security_check">
          <input type="hidden" name="j_username" value='<%=sRutUsuario %>' />
            <table width="335" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="55" colspan="5" align="center" background="img/fdo_campoRUT.jpg"><label for="RUT5"></label>
                  <input name="j_password" type="password" class="caja" id="j_password" value="" maxlength="4" /></td>
              </tr>
              <tr>
                <td width="35">&nbsp;</td>
                <td width="88" align="center"><img src="img/btn_1.png" width="83" height="69" vspace="5" onclick="setDat(1)"/></td>
                <td width="88" align="center"><img src="img/btn_2.png" width="83" height="69" vspace="5" onclick="setDat(2)"/></td>
                <td width="88" align="center"><img src="img/btn_3.png" width="83" height="69" vspace="5" onclick="setDat(3)"/></td>
                <td width="35">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="center"><img src="img/btn_4.png" width="83" height="69" vspace="5" onclick="setDat(4)"/></td>
                <td align="center"><img src="img/btn_5.png" width="83" height="69" vspace="5" onclick="setDat(5)"/></td>
                <td align="center"><img src="img/btn_6.png" width="83" height="69" vspace="5" onclick="setDat(6)"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="center"><img src="img/btn_7.png" width="83" height="69" vspace="5" onclick="setDat(7)"/></td>
                <td align="center"><img src="img/btn_8.png" width="83" height="69" vspace="5" onclick="setDat(8)"/></td>
                <td align="center"><img src="img/btn_9.png" width="83" height="69" vspace="5" onclick="setDat(9)"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="center">&nbsp;</td>
                <td align="center"><img src="img/btn_0.png" width="83" height="69" vspace="5" onclick="setDat(0)"/></td>
                <td align="center">&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="5">
                    <a href="javascript:document.form1.submit();"><img src="img/btn_aceptar.jpg" width="148" height="66" vspace="8" align="left" border="0"/></a>
                    <img src="img/btn_borrar.jpg" width="148" height="66" vspace="8" align="right" border="0" onclick="setDat('-')"/>
                    <!--a href="ingreso.jsp"><img src="img/btn_cancelar.jpg" width="148" height="66" vspace="8" align="right" border="0" title="cancelar" alt="cancelar"/></a-->
                </td>
              </tr>
            </table>
          </form></td>
        </tr>
      </table>
      
    </div>
    <div class="grid_12"><img src="img/btn_volver.jpg" width="152" height="50" align="left"  onclick='history.back()'/></div>
</div>
</body>
</html>
