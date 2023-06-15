<%
	String ruta = request.getContextPath();

     
     String redirectURL = "/HomePage.do";
    
    try {
    
    	//System.out.println("Redireccionando a : " + ruta + redirectURL);

    	response.sendRedirect(response.encodeURL(ruta + redirectURL));

	} catch (Exception e) {
	}
%>