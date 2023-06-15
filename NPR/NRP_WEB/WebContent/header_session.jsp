<%@page import="cl.jfactory.planillas.web.SessionUsuario"%>
<%@page import="cl.jfactory.planillas.service.helper.AutenticacionHelper"%>
<%
	String token = "";
	SessionUsuario miSession = null;
	try{
		if(request != null && request.getSession() != null  ){
			miSession = ((SessionUsuario) request.getSession().getAttribute("usuario"));
			if(miSession == null || !AutenticacionHelper.validarToken( miSession.getToken() )){
				System.out.println("session = null");
				%><script>location.href = "login.jsp?e=ti"</script><%
			}
			else{	
				System.out.println("session != null");
				token = ((SessionUsuario) request.getSession().getAttribute("usuario")).getToken() ;
				%><script>var mi_token = "<%=token%>";</script><%
				String requestURL = request.getRequestURL().toString();

				if(
					requestURL.contains("configuracionEntidad.jsp") && 
					!( miSession.tieneElPerfil("administrador") || miSession.tieneElPerfil("supervisor") ) ){
					%><script>location.href = "index.jsp"</script><%
				}
				else if( 
					( 
						requestURL.contains("configurador.jsp") ||  
						requestURL.contains("configurador_new.jsp") ||  
						requestURL.contains("opciones.jsp") ||  
						requestURL.contains("v0419.jsp") ||  
						requestURL.contains("verConfiguracion.jsp") ||  
						requestURL.contains("configuracionEntidad.jsp")  )  
					&& !( miSession.tieneElPerfil("administrador") ) ){
						%><script>location.href = "index.jsp"</script><%
					}
				else if( 
					( 
						requestURL.contains("configurador.jsp") ||  
						requestURL.contains("configurador_new.jsp") ||  
						requestURL.contains("opciones.jsp") ||  
						requestURL.contains("v0419.jsp") ||  
						requestURL.contains("verConfiguracion.jsp") ||  
						requestURL.contains("configuracionEntidad.jsp")  )  
					&& !( miSession.tieneElPerfil("administrador") ) ){					
					%><script>location.href = "index.jsp"</script><%
				} 

			}
		}
		else{
			//No hay session	
			%><script>location.href = "login.jsp?e=ti"</script><%
		}
	}
	catch(Exception e){
		%><script>location.href = "login.jsp?e=ti"</script><%
	}



%>



