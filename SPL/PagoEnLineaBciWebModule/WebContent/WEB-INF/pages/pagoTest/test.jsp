<%
String nombreBanco = null;
String contexto = request.getContextPath();
String banco = request.getParameter("bco");
boolean esBch = false;
boolean esBsa = false;
boolean esBci = false;
boolean esBes = false;

if ("bch".equals(banco)) {
	esBch = true;
	nombreBanco = "Banco de Chile";
	contexto = "/PagoEnLineaBchWebModule/pagobch";
	
} else if ("bsa".equals(banco)) {
	esBsa = true;
	nombreBanco = "Banco Santander";
	contexto = "/PagoEnLineaBsaWebModule/pagobsa";
	
} else if ("bci".equals(banco) || "tba".equals(banco)) {
	esBci = true;
	contexto = "/PagoEnLineaBciWebModule/pagobci";
	
	if ("bci".equals(banco)) 
		nombreBanco = "Banco Crédito e Inversiones";
	else 
		nombreBanco = "Banco TBanc";
	
} else if ("bes".equals(banco)) {
	esBes = true;
	nombreBanco = "Banco Estado";
	contexto = "/PagoEnLineaBesWebModule/pagobes";
}
%>

<html>
  <head>
    <title>Pruebas Transacción</title>
	<script>
	function iniciaPago(form) {
		form.target = "_blank";
        form.submit();	
    }
    	
	function sendNotificacion(form) {
  			url = form.url.value;
  			
  		<% if ("bch".equals(banco)) { %>
			// Estilo Banco de Chile
			params = '<MPOUT>';
			params = params + '<CODRET>'+form.codRet.value+'</CODRET>';
			params = params + '<DESCRET>'+form.descRet.value+'</DESCRET>';
			params = params + '<IDCOM>'+form.idCom.value+'</IDCOM>';
			params = params + '<IDTRX>'+form.idTrx.value+'</IDTRX>';
			params = params + '<TOTAL>'+(form.monto.value)+'</TOTAL>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<FECHATRX>20071127150251</FECHATRX>';
			params = params + '<FECHACONT>20071128</FECHACONT>';
			params = params + '<NUMCOMP>0011223344</NUMCOMP>';
			params = params + '<IDREG>'+form.idReg.value+'</IDREG>';
			params = params + '</MPOUT>';
			form.action = form.url.value;
		<% } else if ("bsa".equals(banco)) {%>
			// Estilo Banco Santander
			params = '<MPOUT>';
			params = params + '<CODRET>'+form.codRet.value+'</CODRET>';
			params = params + '<DESCRET>'+form.descRet.value+'</DESCRET>';
			params = params + '<IDCOM>'+form.idCom.value+'</IDCOM>';
			params = params + '<IDTRX>'+form.idTrx.value+'</IDTRX>';
			params = params + '<TOTAL>'+(form.monto.value)+'</TOTAL>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<FECHATRX>20080107140803</FECHATRX>';
			params = params + '<FECHACONT>20080107</FECHACONT>';
			params = params + '<NUMCOMP>2741</NUMCOMP>';
			params = params + '<IDREG>'+form.idReg.value+'</IDREG>';
			params = params + '</MPOUT>';
			form.action = form.url.value;
		<% } else if ("bci".equals(banco) || "tba".equals(banco)) { %>
			// Banco Cred. e Inversiones
		<% } else if ("bes".equals(banco)) { %>
			// Estilo Banco Estado
			params = '<MPOUT>';
			params = params + '<CODRET>'+form.codRet.value+'</CODRET>';
			params = params + '<DESCRET>'+form.descRet.value+'</DESCRET>';
			params = params + '<IDCOM>'+form.idCom.value+'</IDCOM>';
			params = params + '<IDTRX>'+form.idTrx.value+'</IDTRX>';
			params = params + '<TOTAL>'+(form.monto.value)+'</TOTAL>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<FECHATRX>20080107140803</FECHATRX>';
			params = params + '<FECHACONT>20080107</FECHACONT>';
			params = params + '<NUMCOMP>2741</NUMCOMP>';
			params = params + '<IDREG>'+form.idReg.value+'</IDREG>';
			params = params + '</MPOUT>';
			form.action = form.url.value;			
		<% } %>
		
		form.TX.value = params;
		form.target = "_blank";
		form.submit();
	}
	
	function finalizaPago(form) {
		frmNoti = document.forms['frmNotificacion'];

		<% if (esBch) { %>
			// Estilo Banco de Chile
			params = '<MPFIN>';
			params = params + '<IDTRX>'+frmNoti.idTrx.value+'</IDTRX>';
			params = params + '<CODRET>'+frmNoti.codRet.value+'</CODRET>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<TOTAL>'+(frmNoti.monto.value)+'</TOTAL>';
			params = params + '<INDPAGO>'+form.indPago.value+'</INDPAGO>';
			params = params + '<IDREG>'+frmNoti.idReg.value+'</IDREG>'; 
			params = params + '</MPFIN>';
		<% } else if (esBsa) { %>
			// Estilo Banco Santander
			params = '<MPFIN>';
			params = params + '<IDCOM>'+frmNoti.idCom.value+'</IDCOM>';
			params = params + '<IDTRX>'+frmNoti.idTrx.value+'</IDTRX>';
			params = params + '<TOTAL>'+(frmNoti.monto.value)+'</TOTAL>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<CODRET>'+frmNoti.codRet.value+'</CODRET>';
			params = params + '<INDPAGO>'+form.indPago.value+'</INDPAGO>';
			params = params + '<IDREG>'+frmNoti.idReg.value+'</IDREG>';
			params = params + '</MPFIN>';
		<% } else if (esBci) { %>
			// Banco Cred. e Inversiones
		<% } else if (esBes) { %>
			// Estilo Banco Estado
			params = '<MPFIN>';
			params = params + '<IDCOM>'+frmNoti.idCom.value+'</IDCOM>';
			params = params + '<IDTRX>'+frmNoti.idTrx.value+'</IDTRX>';
			params = params + '<TOTAL>'+(frmNoti.monto.value)+'</TOTAL>';
			params = params + '<NROPAGOS>1</NROPAGOS>';
			params = params + '<CODRET>'+frmNoti.codRet.value+'</CODRET>';
			params = params + '<INDPAGO>'+form.indPago.value+'</INDPAGO>';
			params = params + '<IDREG>'+frmNoti.idReg.value+'</IDREG>';
			params = params + '</MPFIN>';			
		<% } %>
		
		form.TX.value = params;
		form.target = "_blank";
		form.submit();
	}
	</script>
  </head>
  <body>
  
	<h3>Pruebas Pago Electr&oacute;nico <%=nombreBanco%></h3>
    <fieldset><legend><b>Iniciar Pago</b></legend>
    <form name="frmInicio" action="/simulacionClienteXMLWeb/index.jsp" method="POST">
	  <table>
		<tr>
		  <td colspan="2" align="left"><input type="button" value="Iniciar" onClick="javascript:iniciaPago(this.form);"></td>
		</tr>
	  </table>		
	</form>
	</fieldset>

    <fieldset><legend><b>Simular Notificaci&oacute;n</b></legend>
    <%
    if (esBch || esBsa || esBes ) { %>
	<form name="frmNotificacion" action="<%=contexto%>/NotificacionInvoker.do" method="POST">
	  <input type="HIDDEN" name="TX" value="">
	  <input type="hidden" name="banco" value="<%=banco%>">
	  <table>
	    <tr>
		  <td>Monto</td>
		  <td colspan="3">: <input type="text" name="monto" value="100000" size="20"></td>
		</tr>
	    <tr>
		  <td>C&oacute;digo Retorno</td>
		  <td>: <input type="text" name="codRet" value="0000" size="20">
		  	<span style="font-size: 10px; color: blue; font-family: Verdana">(= 0000 :Exito, != 0000 :Fracaso)</span>
		  </td>
		  <td>Desc. Retorno</td>
		  <td>: <input type="text" name="descRet" value="Notificacion OK" size="20">		  	
		  </td>
		</tr>
	    <tr>
		  <td>Id Comercio</td>
		  <td>: <input type="text" name="idCom" value="9906100001" size="20"></td>
		  <td>Id Transacci&oacute;n</td>
		  <td>: <input type="text" name="idTrx" value="9906100000001004" size="20"></td>
		</tr>
	    <tr>
		  <td>Id Registro</td>
		  <td colspan="3">: <input type="text" name="idReg" value="13525" size="20"></td>
		</tr>
	    <tr>
		  <td>P&aacute;gina Notificaci&oacute;n</td>
		  <td colspan="3">:
		  
		   <%--= "http://" + request.getServerName() + ":" + request.getServerPort() + contexto + "/NotificacionPago.do" --%>
		  	<input type="text" name="url" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/NotificacionPago.do" size="60">
		  </td>
		</tr>
		<tr>
		  <td colspan="4" align="left">	
	        <input type="button" value="Enviar" onClick="javascript:sendNotificacion(this.form);">
	      </td>
	    </tr>
	  </table>      
    </form>
    <% } else { %>
    	<%@include file="notificacion.jsp"  %>
    <% } %>
    </fieldset>

    <fieldset><legend><b>Simular Salida</b></legend>
    
    <% if (esBch || esBsa || esBes) { %>
	<form name="frmSalida" method="POST" action="<%=contexto%>/SalidaPago.do">
	  <input type="HIDDEN" name="TX" value="">
  	  <input type="hidden" name="trx" value="">
  	  <input type="hidden" name="estado" value="">
	  <table>
	    <tr>
		  <td>Indicador Pago</td>
		  <td>:
		    <select name="indPago">
		      <option value="S">S</option>
		      <option value="N">N</option>
		    </select>
		  </td>
		</tr>
	    <tr>
		  <td>URL de Reconfirmaci&oacute;n</td>
		  <td>: <input type="text" name="url_reconfirmacion" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=contexto%>/ReconfirmacionReplier.do" size="60"></td>
		</tr>
		<tr>
		  <td colspan="2" align="left">	
	        <input type="button" value="Enviar" onClick="javascript:finalizaPago(this.form);"><br/>
		  </td>
		</tr>
	  </table>
	</form>	
	<% } else { %>		
		<%@include file="simulacionretorno.jsp"  %>
	<% } %>	
	</fieldset>
	
  </body>	
</html>