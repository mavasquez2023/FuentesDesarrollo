<%-- 
    Document   : userid
    Created on : 10-04-2012, 12:03:11 PM
    Author     : desajee
--%>
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
          <td align="center" valign="top" style="padding-top:15px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="40" align="right" valign="top" class="titulo-interior"><%=session.getAttribute("md2.titulo")%></td>
            </tr>
            <tr>
              <td align="right" valign="top" style="font-size:20px;color:#666;"><bean:write name="usuario.nombre"/></td>
            </tr>
          </table></td>
        </tr>
      </table>
  </div>
  <div style="clear:left;"></div>
