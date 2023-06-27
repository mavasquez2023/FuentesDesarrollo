<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page import="cl.araucana.spl.util.ResourceHelper"%>

<% ResourceHelper resources = ResourceHelper.getInstance(); %>

<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Proceso de pago</strong></strong></td>             
            </tr>
            <tr valign="top"> 
              <td height="30" align="left" bgcolor="#FFFFFF"><span class="titulos_formularios">Estimado cliente:</span></td>             
            </tr>
          	</table>
        </td>
      </tr>
    </table>
  </td>
</tr>
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr> 
              <td align="center" class="textos-formularios" bgcolor="#FFFFFF">
				<div id="MENSAJE_USUARIO"><%=resources.getProperty("pago.pagado.noprecompra")%></div>
			  </td>             
            </tr>        
            <tr> 
              <td align="center" class="textos-formularios" bgcolor="#FFFFFF"><br/>
				<form>
					
					<input type="button" name="b" value="Cerrar" class="btn2" onClick="window.close();" />
				</form>
			  </td>
			</tr>
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
</table>