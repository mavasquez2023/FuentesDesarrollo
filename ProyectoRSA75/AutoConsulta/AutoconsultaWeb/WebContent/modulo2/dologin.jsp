<%@ include file = "/tipoMedio.jsp"%>
<% boolean bEsQuisko = false; %>

<% 
try {
	Cookie []todasLasQ = request.getCookies();
	for (int i=0; i < todasLasQ.length; i++ ) {
	   if (todasLasQ[i].getName().equals("squery") && todasLasQ[i].getValue().equals("1qx")) {
	   
	     bEsQuisko = true;
	
	   }
	}
} catch (Exception ex) {
   bEsQuisko = false;
}
%>

<% if (!bEsQuisko) {  %>

   <jsp:forward page="/web/index.jsp"></jsp:forward>

<% } else {  
	try {
		session.setAttribute("subapp", "modulo2");
		String chkUser = "" + (String)request.getParameter("chkUser");
		session.setAttribute("chkUser", chkUser);
		session.setAttribute("internal.huella","yes");
		if (session.getAttribute("chkUser")==null || ((String)session.getAttribute("chkUser")).equals("") ) {
		   response.sendRedirect("ingreso.jsp");
		} else {
		    response.sendRedirect(cl.laaraucana.autoconsulta.ui.actions.modulo2.GetHuella.getPrincipal());
		}
	} catch(Exception ex) {
%>
<jsp:forward page="/web/index.jsp"></jsp:forward>
<%
	}
}
%>
