package cl.araucana.ea.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;

/**
 * @version 	1.0
 * @author
 */
public class PreActionFilter implements Filter {
	/**
	* @see java.lang.Object#Object ()
	*/
	public PreActionFilter() {
		super();
	}

	/**
	* @see javax.servlet.Filter#void ()
	*/
	public void destroy() {

	}

	/**
	* @see javax.servlet.Filter#void (javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	public void doFilter(
			ServletRequest req,
			ServletResponse resp,
			FilterChain chain)
		throws ServletException, IOException {

		/*
		 * 	se aplica este filtro a las acciones con seguridad aplicada
		 * 
		 * 	1. AuthenticationFailed -- never should happen 
		 * 	2. InvalidSessionStatus -- fire invalidProfile, must be modified later, find out how to recognize invalid session status   
		 * 	3. InvalidProfile -- fire invalidProfile
		 * 	4. UnauthorizedAccess  -- file Unauthorized access
		 */
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		int status = 0;
		String forward = null;

		// 1. omitted
		//if(condition) {
		//	forward = "/pages/error/authenticationFailed.jsp";
		//	status = 1; 
		//}

		// 2.
		Profile profile = (Profile)session.getAttribute("ea_user_profile");
		if(profile == null) {
			forward = "/pages/error/invalidSessionStatus.jsp";
			status = 2; 
		}

		// 3.
		if((status == 0) && !profile.isValid()) {
			forward = "/pages/error/invalidProfile.jsp";
			status = 3; 			
		}
		
		// 4. 
		//if(condition) {
		//	forward = "/pages/error/unauthorizedAccess.jsp";
		//	status = 4; 
		//}

		//forward = "/pages/error/invalidProfile.jsp";
		//status = 3;
		// execute chained servlet.
		/*
		 * 	get an instance of business delegate
		 */
				
		if(status == 0) {		
			chain.doFilter(req, resp);
		} else {
			request.getRequestDispatcher(forward).forward(req, resp);
		}

	}

	/**
	* Method init.
	* @param config
	* @throws javax.servlet.ServletException
	*/
	public void init(FilterConfig config) throws ServletException {

	}

}
