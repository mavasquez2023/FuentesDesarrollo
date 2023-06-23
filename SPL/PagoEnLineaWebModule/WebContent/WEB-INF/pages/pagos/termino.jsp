<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page import="cl.araucana.spl.util.ResourceHelper"%>

<% ResourceHelper resources = ResourceHelper.getInstance(); %>

<script type="text/javascript" src="<c:url value="/js/FormCheq.js" />"></script>
<script language="javaScript">
<!--
var t;
var contador = new Number(0);
var URL = new String("../pagobase/ConsultaTerminoPago.do");
var receiveReq = "";

//Entrega XmlHttpRequest
function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest(); //No IE
	} else if(window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP"); //IE
	}
}		

//Llamada
function consultarTermino() {
	//Llamada a XmlHttpRequest
	receiveReq = getXmlHttpRequestObject();
	
	if (contador<= <%= Constants.ESTADO_PAGO_ITERACIONES_DE_ESPERA %> && receiveReq) {
		receiveReq.open("GET", URL+'?cont='+contador, true);					
		receiveReq.onreadystatechange = callBackTermino; 
		receiveReq.send(null);
		contador = contador + 1;
	}
	
	if (contador > <%= Constants.ESTADO_PAGO_ITERACIONES_DE_ESPERA %>) {
		var objDiv = document.getElementById('MENSAJE_USUARIO');
		objDiv.innerHTML = "<%=resources.getProperty("pago.pagado.norespuesta")%>";
	}
}

//Lectura de respuesta
function callBackTermino() {
	if (receiveReq.readyState == 4 && receiveReq.status == 200) {
		var result = stripWhitespace(new String(receiveReq.responseText));		
		var objDiv = document.getElementById('MENSAJE_USUARIO');
		
		if (result=='<%=Constants.PAGO_INICIAL%>') {
			objDiv.innerHTML = "<%=resources.getProperty("pago.pagado.vacio")%>";
			t=setTimeout("consultarTermino()",<%= Constants.ESTADO_PAGO_MILISEGUNDOS_ENTRE_CONSULTAS %>);
		}
		if (result=='<%=Constants.PAGO_PAGADO%>') {
			objDiv.innerHTML = "<%=resources.getProperty("pago.pagado.ok")%>";
			return;
		} 
		if (result=='<%=Constants.PAGO_NO_PAGADO%>') {
			objDiv.innerHTML = "<%=resources.getProperty("pago.pagado.nok")%>";
			return;
		}
	}
}

//-->
</script>

<script>
	consultarTermino();
</script>

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
				<div id="MENSAJE_USUARIO">Esperando confirmación del pago.</div>
			  </td>             
            </tr>        
            <tr> 
              <td align="center" class="textos-formularios" bgcolor="#FFFFFF"><br/>
				<form action="${urlRetorno}" method="post">
					<input type="hidden" name="trx" value="${trx}" />
					<input type="hidden" name="vector" value="${vector}" />
					<input type="button" name="b" value="Volver" class="btn2" onClick="this.form.submit()" />
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