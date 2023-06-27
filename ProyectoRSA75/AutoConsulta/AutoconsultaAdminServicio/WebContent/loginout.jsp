<%
response.setHeader ( "Pragma", "no-cache");
response.setDateHeader ( "Expires", 0);
response.setHeader ( "Cache Remove", "no-cache");
response.setHeader ( "Cache Remove", "no-store");
request.getSession().invalidate();
response.reset();
response.sendRedirect("/AutoconsultaAdminServicio");
%>