<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>

<%
String contexto = request.getContextPath(); %>
<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Sistema Pago en L&iacute;nea</strong> </strong></td>             
            </tr>
            <tr valign="top"> 
              <td height="30" align="left" bgcolor="#FFFFFF"><span class="titulos_formularios">Links de Pruebas:</span> </td>             
            </tr>
          </table></td>
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
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/simulacionClienteXMLWeb/" title="Simular pago en Sistema Cliente">Medios de pago (Sistema Cliente)</a>
	        	</td>
	        </tr>
	        <tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;</td>
	        </tr>	        
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/pago/test.do?bco=bci">Pago BCI</a>
	        	</td>
	        </tr>
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/pago/test.do?bco=tba">Pago TBanc</a>
	        	</td>
	        </tr>
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/pago/test.do?bco=bch">Pago Bco.Chile</a>
	        	</td>
	        </tr>
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/pago/test.do?bco=bsa">Pago Bco.Santander</a>
	        	</td>
	        </tr>
	        <tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;</td>
	        </tr>	        
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;
	        		<a href="<c:url value="/admin/Index.do" />">Administraci&oacute;n SPL</a>
	        	</td>
	        </tr>
	       	<tr> 
	        	<td align="left" class="textos-formularios" bgcolor="#FFFFFF">&nbsp;</td>
	        </tr>	        	        
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
     
</table>