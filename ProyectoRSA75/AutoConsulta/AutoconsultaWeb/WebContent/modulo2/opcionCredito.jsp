<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Seleccione una Opci&oacute;n</title>
<%@ include file = "/modulo2/includes/headhtml.jsp"%>
</head>

<body>
<div class="container_12 altura-contenedor">
	<div class="grid_5">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td width="95%">&nbsp;</td>
        </tr>
	    <tr>
	      <td valign="top"><img src="img/logo_araucana_interior.jpg" width="311" height="36" /></td>
        </tr>
	    <tr>
	      <td align="right"></td>
        </tr>
      </table>
	</div>
  <div class="grid_7">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="top" style="padding-top:60px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="31%" style="font-size:26px; color:#004686"><strong>Bienvenido(a)</strong></td>
              <td width="69%" align="right" valign="top" style="font-size:26px;color:#666;"><bean:write name="afiliado.nombre"/></td>
            </tr>
          </table></td>
        </tr>
      </table>
      
  </div>
    <div class="grid_12 omega">
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" style=" padding-top:75px;font-size:34px; color:#666;">Seleccione una opci&oacute;n</td>
        </tr>
        <tr>
          <td align="center" style="padding-bottom:50px;">
              <a href="javascript:doOpcion('cert');"><img src="img/opcion_certificados.jpg" width="256" height="100" hspace="10" vspace="20" border="0" /></a>
              <a href="javascript:doOpcion('cns');"><img src="img/opcion_consultas.jpg" width="256" height="100" hspace="10" vspace="20" border="0" /></a></td>
        </tr>
        <tr>
          <td height="304" align="center" valign="top" style="background:url(img/fdo_preaprobado.jpg) center top no-repeat;"><br />
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" class="credito">TIENES UN CR&Eacute;DITO PRE-APROBADO POR</td>
              </tr>
              <tr>
                <td align="center" class="monto"><bean:write name="montoPreAprobado"/></td>
              </tr>
              <tr height="70">
                  <td>&nbsp;</td>
              </tr>
              <tr height="30">
                  <td>&nbsp;</td>
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
  
</div>
<form name="fOpc" id="fOpc" method="post" action="">
      <input type="hidden" name="md2_opcion" id="md2_opcion" value=""/>
      <input type="hidden" name="md2_opcionMnu" id="md2_opcionMnu" />
</form>
</body>
</html>
