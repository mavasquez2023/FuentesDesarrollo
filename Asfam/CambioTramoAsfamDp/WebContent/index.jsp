 <%
	String ruta = request.getContextPath();

     
     String redirectURL = "/DivisionPrevisionalPage.do?step=homeDivisionPrevisional";
    
    try {
    
    	System.out.println("Redireccionando a : " + ruta + redirectURL);

    	response.sendRedirect(response.encodeURL(ruta + redirectURL));

	} catch (Exception e) {
	}
%>