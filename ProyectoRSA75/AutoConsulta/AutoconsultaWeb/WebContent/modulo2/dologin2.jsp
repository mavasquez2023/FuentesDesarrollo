<%
String chkUser = (String)request.getParameter("chkUser");
chkUser = "10104927-2";
session.setAttribute("chkUser", chkUser);
session.setAttribute("internal.huella","yes");
if (session.getAttribute("chkUser")==null || ((String)session.getAttribute("chkUser")).equals("") ) {
   response.sendRedirect("ingreso.jsp");
} else {
%>
<jsp:forward page="/modulo2/j_security_check">
   <jsp:param name="j_password" value="1234" />
   <jsp:param name="j_username" value="70016160-9" />
</jsp:forward>
<%
}
%>
