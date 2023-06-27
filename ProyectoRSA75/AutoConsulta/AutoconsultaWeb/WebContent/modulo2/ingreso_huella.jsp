<%
session.setAttribute("internal.huella","yes");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ingreso Huella Digital</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/grid.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="container_12 altura-contenedor">
	<div class="grid_6">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td width="95%">&nbsp;</td>
        </tr>
	    <tr>
	      <td valign="top"><img src="img/logo_araucana.jpg" width="414" height="40" /></td>
        </tr>
	    <tr>
	      <td align="right"></td>
        </tr>
	    <tr>
	      <td>&nbsp;</td>
        </tr>
	    <tr>
	      <td valign="top" class="titulo" style="padding-top:140px;"><h1>Estimado Afiliado</h1>Posicione el dedo se&ntilde;alado en el
	        lector de <br />
	        huella, tal como se muestra 
          en la imagen<br />
          <br /></td>
        </tr>
	    <tr>
	      <td>&nbsp;</td>
        </tr>
      </table>
	</div>
  <div class="grid_6">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" height="700px">
        <tr>
          <td align="center" valign="top" style="padding-top:80px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="220" colspan="2" align="left" valign="top" style="background:url(img/fdo_globohuella.jpg) no-repeat top left; padding:0px 0px 0px 20px;" class="txt-dedo">Dedo pulgar <br />derecho</td>
            </tr>
            <tr>
              <td width="44%" rowspan="3"><img src="img/mano_huella.png" width="200" height="200" /></td>
              <td width="56%" align="left" valign="top"><object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="260" height="250">
                <param name="movie" value="img/huella.swf" />
                <param name="quality" value="high" />
                <param name="wmode" value="transparent" />
                <param name="swfversion" value="6.0.65.0" />
                <!-- This param tag prompts users with Flash Player 6.0 r65 and higher to download the latest version of Flash Player. Delete it if you don’t want users to see the prompt. -->
                <param name="expressinstall" value="../../Scripts/expressInstall.swf" />
                <!-- Next object tag is for non-IE browsers. So hide it from IE using IECC. -->
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="img/huella.swf" width="260" height="250">
                  <!--<![endif]-->
                  <param name="quality" value="high" />
                  <param name="wmode" value="transparent" />
                  <param name="swfversion" value="6.0.65.0" />
                  <param name="expressinstall" value="../../Scripts/expressInstall.swf" />
                  <!-- The browser displays the following alternative content for users with Flash Player 6.0 and older. -->
                  <div>
                    <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
                    <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
                  </div>
                  <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
              </object></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td valign="bottom">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
      
    </div>
  <div class="grid_12"><img src="img/btn_volver.jpg" width="152" height="50" align="left" onclick='history.back()'/>
  <img src="img/btn_salir.jpg" width="152" height="50" align="right" onclick='history.back()' /></div>
</div>
<script type="text/javascript">
try {
  swfobject.registerObject("FlashID");
} catch (ex) {
}
</script>
</body>
</html>
