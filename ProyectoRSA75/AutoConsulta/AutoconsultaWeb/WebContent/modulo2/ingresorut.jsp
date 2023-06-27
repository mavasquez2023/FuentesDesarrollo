<%
session.removeAttribute("datosUsuario");
session.invalidate();

%>
<%@ include file = "/tipoMedio.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "includes/headhtml.jsp"%>

<script>
function setDat(data) {
var sRut = document.form1.RUTUsuario.value;
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
    document.form1.RUTUsuario.value = sRut;
}
</script>
<body>
<div class="container_12 altura-contenedor">
	<div class="grid_6 alpha">
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
	      <td height="70">&nbsp;</td>
        </tr>
        <tr>
	      <td height="70">&nbsp;</td>
        </tr>
	    <tr>
	      <td align="center"><h2>Ingreso por C&eacute;dula de Identidad</h2></td>
        </tr>
	    <tr>
	      <td align="center" style="padding-top:35px;"><form id="form1" name="form1" method="post" action="ingreso_clave.jsp">
	        <table width="335" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="55" colspan="5" align="center" background="img/fdo_campoRUT.jpg"><label for="RUT"></label>
	              <input name="RUTUsuario" type="text" class="caja" id="RUTUsuario" value="" maxlength="12" /></td>
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
	            <td align="center"><img src="img/btn_0.png" width="83" height="69" vspace="5" onclick="setDat(0)"/></td>
	            <td align="center"><img src="img/btn_K.png" width="83" height="69" vspace="5" onclick="setDat('K')"/></td>
	            <td align="center">&nbsp;</td>
	            <td>&nbsp;</td>
              </tr>
	          <tr>
	            <td colspan="5">
                        <a href="javascript:document.form1.submit()"><img src="img/btn_aceptar.jpg" width="148" height="66" vspace="8" align="left" border="0"/></a>
                        <img src="img/btn_borrar.jpg" width="148" height="66" vspace="8" align="right"  border="0" onclick="setDat('-')"/>
                    </td>
              </tr>
            </table>
          </form></td>
        </tr>
      </table>
	</div>
    <div class="grid_6 omega">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="5%" rowspan="7" valign="bottom"><img src="img/linea_separadora.jpg" width="3" height="623" /></td>
          <td width="95%">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="right" class="titulo-ingreso"><h1>Estimado Afiliado</h1>
            Digite su RUT o si lo prefiere inserte<br />
          su C&eacute;dula de Identidad</td>
        </tr>
        <tr>
          <td height="70">&nbsp;</td>
        </tr>
        <tr>
          <td align="center"><h2>Ingreso por C&eacute;dula de Identidad</h2></td>
        </tr>
        <tr>
          <td align="center" style="padding-top:35px;"><object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="430" height="452">
            <param name="movie" value="img/ID.swf" />
            <param name="quality" value="high" />
            <param name="wmode" value="transparent" />
            <param name="swfversion" value="6.0.65.0" />
            <!-- This param tag prompts users with Flash Player 6.0 r65 and higher to download the latest version of Flash Player. Delete it if you don’t want users to see the prompt. -->
            <param name="expressinstall" value="../../Scripts/expressInstall.swf" />
            <!-- Next object tag is for non-IE browsers. So hide it from IE using IECC. -->
            <!--[if !IE]>-->
            <object type="application/x-shockwave-flash" data="img/ID.swf" width="430" height="452">
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
      </table>
    </div>
</div>
</body>
</html>
