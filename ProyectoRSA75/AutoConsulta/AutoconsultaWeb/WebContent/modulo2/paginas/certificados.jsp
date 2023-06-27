<%-- 
    Document   : certificados.jsp
    Created on : 10-04-2012, 12:07:53 PM
    Author     : desajee
--%>
<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<%
session.setAttribute("md2.titulo", "Certificados");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "/modulo2/includes/headhtml.jsp"%>
<body style="background:url(img/background_interior.jpg) repeat-x 0px -64px">
<div class="container_12">
<%@ include file = "/modulo2/includes/userid.jsp"%>

<div class="grid_12 omega">
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left"><%@ include file = "/modulo2/includes/opciones.jsp"%></td>
        </tr>
        <tr>
          <td><%@ include file = "/modulo2/includes/menues.jsp"%></td>
        </tr>
      </table>
    </div>
    <div style="clear:left;"></div>
</div>

<logic:present name="pideEmpleado"> 
   <iframe src="pideEmpleado.do" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0"></iframe> 
</logic:present>

<logic:notPresent name="pideEmpleado"> 
<logic:present name="message"> 
<div class="alerta">
<bean:write name="message"/><br/>
<bean:write name="info"/><br/>
</div>
</logic:present>
<logic:present name="validation.message"> 
<div class="alerta">
  <bean:message key='<%=(String)session.getAttribute("validation.message") %>'/>
</div>
</logic:present>

<div class="container_12">
<%
String sUriGo = "getAsignacionFamiliar.do";
if (estaOpcionMnu==0) {
   sUriGo = "getAsignacionFamiliar.do";
} else if (estaOpcionMnu==5) {
   sUriGo = "getAsignacionFamiliar.do";
} else if (estaOpcionMnu==6) {
  sUriGo = "getCertificadoLicenciasMedicas.do";
} else if (estaOpcionMnu==7) {
  sUriGo = "getDeudaVigente.do";
} else if (estaOpcionMnu==8) {
  sUriGo = "getAfiliacion.do";
}
%>
   <iframe src="<%=sUriGo %>" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0"></iframe> 
</logic:notPresent>
</div>
<div class="container_12">
  <div class="grid_12 omega">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%">
           <span id='spIdImprimeOff' style=''><a href="#" onclick="btnPrint(0);vbPrintPage(window.iData);btnPrint(1);"><img src="img/btn_imprimir.jpg" width="152" height="50" align="left" border="0"/></a></span>
           <span id='spIdImprimeOn' style='text-decoration:blink;display:none;'><img src="img/loader1.gif"/> &nbsp;Imprimiendo...</span>
        </td>
        <td align="center"><a href="#" onclick="doBajar()"><img src="img/flecha_down.png" border="0"/></a> &nbsp; &nbsp; &nbsp;<a href="#" onclick="doSubir()"><img src="img/flecha_up.png" border="0"/></a></td>
        <td align="center"><a href="logout.do"><img src="img/btn_salir.jpg" width="152" height="50" align="right" border="0"/></a></td>
      </tr>
    </table>
  </div>
  <div style="clear:left;"></div>
</div>
<OBJECT ID="WB" WIDTH="0" HEIGHT="0" CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>

<SCRIPT LANGUAGE="VBScript">
Sub window_onunload
	On Error Resume Next
	Set WB = nothing
End Sub
Sub vbPrintPage (frame)
	frame.focus()
	On Error Resume Next
	WB.ExecWB 6, 2, 3, 0
End Sub
</SCRIPT>
<form name="fOpc" id="fOpc" method="post" action="<%=opAction%>">
      <input type="hidden" name="md2_opcion" id="md2_opcion" value="<%=estaOpcion%>"/>
      <input type="hidden" name="md2_opcionMnu" id="md2_opcionMnu" />
</form>
<script>
var yCor = 00;
function doBajar() {
	var obj = document.getElementById("iData");
	yCor += 100;
	try {
		obj.contentWindow.scrollTo(0,yCor);
	} catch (ex) {
	   alert(ex);
	}
}

function doSubir() {
	var obj = document.getElementById("iData");
	yCor -= 100;
	try {
		obj.contentWindow.scrollTo(0,yCor);
	} catch (ex) {
	   alert(ex);
	}
}
function btnPrint(opc) {
var obj1 = document.getElementById("spIdImprimeOff");
var obj2 = document.getElementById("spIdImprimeOn");

  if (opc==0) {
    obj1.style.display = "none";
    obj2.style.display = "block";
  } else {
    obj1.style.display = "block";
    obj2.style.display = "none";
  }
}
</script>
</body>
</html>
