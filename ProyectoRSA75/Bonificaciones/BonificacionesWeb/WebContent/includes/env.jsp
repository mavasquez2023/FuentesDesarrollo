<%  String contextRoot = (String)session.getAttribute("struts.application");
    if (contextRoot==null) contextRoot="BonificacionesWeb";
    session.setAttribute("bonificaciones.contextRoot",contextRoot);
    contextRoot = "/" + contextRoot; 
    
	String referer=(String)request.getSession(true).getAttribute("referer");
	String msg=(String)request.getSession(true).getAttribute("msg");
		
 // Objeto de Permisos de la Aplicación
   cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)session.getAttribute("application.userinformation");
   if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
 
	/**  
	 * INICIO NUEVO
	 */	
	Long aux = (Long) session.getAttribute("grupoUsuario");
	long grupoUsuario = aux.longValue(); 

//	if(grupoUsuario != 5)
//		System.out.println("EL USUARIO ES ADMINISTRADOR");
//	if(grupoUsuario == 5)
//		System.out.println("EL USUARIO ES SOCIO");
	/**
	 * FIN NUEVO
	 */ 

%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
