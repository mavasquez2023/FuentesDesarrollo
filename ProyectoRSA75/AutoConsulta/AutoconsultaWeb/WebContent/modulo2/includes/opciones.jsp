<%-- 
    Document   : opciones
    Created on : 10-04-2012, 12:05:20 PM
    Author     : desajee
--%>
<%
String estaOpcion = "cns";
String sOpcionMnu = "";
String opAction = "";

if ((String)request.getParameter("md2_opcion")!=null) {
    estaOpcion = (String)request.getParameter("md2_opcion");
}

if (estaOpcion.equals("")) {
    estaOpcion = "" + (String)session.getAttribute("md2_opcion");
    sOpcionMnu = "cns";
    sOpcionMnu = "1";
}

if (estaOpcion.equals("")) {
    estaOpcion = "cns";
}

if (estaOpcion.equals("cns")) {
    opAction = "consultas.jsp";
} else {
    estaOpcion = "cer";
    opAction = "certificados.jsp";
}
session.setAttribute("md2_opcion", estaOpcion);
%>
<%if (estaOpcion.equals("cns")) { %>
    <div id="menu-1"><a href="javascript:doOpcion('cer');"><img src="img/btn_certificados_off.jpg" width="184" height="61" border="0" align="left"  onmouseover="src='img/btn_certificados_on.jpg'" onmouseout="src='img/btn_certificados_off.jpg'"/></a></div>
    <div id="menu-1"><img src="img/btn_consultas_on.jpg" width="184" height="61" align="left" /></div>
<%} else {%>
    <div id="menu-1"><img src="img/btn_certificados_on.jpg" width="184" height="61" align="left" /></div>
    <div id="menu-1"><a href="javascript:doOpcion('cns');"><img src="img/btn_consultas_off.jpg" width="184" height="61" border="0" align="left"  onmouseover="src='img/btn_consultas_on.jpg'" onmouseout="src='img/btn_consultas_off.jpg'"/></a></div>
<%} %>


