<%
session.setAttribute("subapp", "modulo2");
String chkUser = (String)request.getParameter("chkUser");
session.setAttribute("chkUser", chkUser);
session.setAttribute("internal.huella","yes");
if (session.getAttribute("chkUser")==null || ((String)session.getAttribute("chkUser")).equals("") ) {
   response.sendRedirect("ingreso.jsp");
} else {
  response.sendRedirect("j_security_check?j_username=70016160-9&j_password=1234");
}
%>
