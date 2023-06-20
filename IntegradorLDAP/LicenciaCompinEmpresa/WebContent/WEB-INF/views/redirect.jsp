<%
	String ruta = request.getContextPath();
     String redirectURL = "/initemp.do";
    
    try {
    	response.sendRedirect(response.encodeURL(ruta + redirectURL));

	} catch (Exception e) {
	}
%>
