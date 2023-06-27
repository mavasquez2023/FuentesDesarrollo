<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Seleccione una Opci&oacute;n</title>
<%@ include file = "/mobile/includes/headhtml.jsp"%>
<script>
function doContinua() {
document.form1.submit();
}
</script>
</head>

<body>
<div style="width:500px;margen:5px;">
<form name="form1" action="consultas.do">
<input type="hidden" name="md2_opcion" value="1"/>
<input type="hidden" name="md2_opcionMnu" value=""/>
</form>
<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td id="info"><img src="<%=getPath %>img/logo_certificados.gif" width="345" height="41" vspace="5" align="right" /><br />
  </tr>
</table>
<img src="<%=getPath %>img/separador_certificados.gif" width="100%" height="7" vspace="5" /><br />
<br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<img src="<%=getPath %>img/separador_certificados.gif" width="100%" height="7" vspace="5" /><br />

    <div>
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td height="304" align="center" valign="top" style="background:url(<%=getPath %>img/fdo_preaprobado.jpg) center top no-repeat;"><br />
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" class="credito">TIENES UN CR&Eacute;DITO PRE-APROBADO POR</td>
              </tr>
              <tr>
                <td align="center" class="monto"><bean:write name="montoPreAprobado" formatKey="format.money"/></td>
              </tr>
              <tr height="70">
                  <td>&nbsp;</td>
              </tr>
		        <tr>
		          <td align="center" style="padding-bottom:50px;">
		              <a href="javascript:doContinua()">Continuar</a>
		        </tr>

              <tr>
                  <td><div class="grid_12">
                          <a href="logout.do"><img src="img/btn_salir.jpg" width="152" height="50" align="right" border="0"/></a>
                      </div>
                  </td>
              </tr>

          </table></td>
        </tr>
      </table>
  
</div>

</body>
</html>
