package cl.araucana.sv.filters;

import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UserCheckFilter
  implements Filter
{
  public void init(FilterConfig config) throws ServletException {}
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
	System.out.println(">> UserCheckFilter.doFilter() ++");
	HttpServletRequest httpRequest = (HttpServletRequest)request;
    Principal userPrincipal = httpRequest.getUserPrincipal();
    String userID = null;
    if (userPrincipal == null || (
      userID = userPrincipal.getName()) == null) {
      RequestDispatcher requestDispatcher = 
        request.getRequestDispatcher("/");
      requestDispatcher.forward(request, response);
      return;
    } 
    HttpSession session = httpRequest.getSession();
    UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
    if (userInfo == null) {
      UserRegistryConnection urConnection = null;
      try {
        urConnection = new UserRegistryConnection();
        userInfo = urConnection.getUserInfo(userID);
        urConnection.registerUserLogin(userID, false);
      } catch (UserRegistryException e) {
        e.printStackTrace();
      } finally {
        if (urConnection != null) {
          try {
            urConnection.close();
          } catch (UserRegistryException userRegistryException) {}
        }
      } 

      if (userInfo != null) {
        if (userInfo.isBlocked()) {
          log(
              "User '" + userID + "' will be logged out. " + 
              "[user is BLOCKED]");
          RequestDispatcher requestDispatcher = 
            request.getRequestDispatcher("/logout.do");
          requestDispatcher.forward(request, response);
          return;
        } 
        if (userInfo.mustChangePassword()) {
          log("User '" + userID + "' need change password.");
          String URL = httpRequest.getRequestURL().toString();
          String URI = httpRequest.getRequestURI();
          String query = httpRequest.getQueryString();
          int index = URL.indexOf(URI);
          String nextPage = 
            String.valueOf(URL.substring(0, index)) + 
            httpRequest.getContextPath() + 
            "/router.do?" + query;
          request.setAttribute("changeMode", "initial");
          request.setAttribute("nextPage", nextPage);
          RequestDispatcher requestDispatcher = 
            request.getRequestDispatcher("/changePassword.jsp");
          requestDispatcher.forward(request, response);
          System.out.println("<< UserCheckFilter.doFilter() ++");
          return;
        } 
      } else {
        userInfo = new UserInfo();
      } 
      session.setAttribute("userInfo", userInfo);
    } 
    chain.doFilter(request, response);
  }

  
  public void destroy() {}

  
  private void log(String message) {
    System.out.println("Sucursal Virtual.UserCheckFilter:" + message);
  }
}
