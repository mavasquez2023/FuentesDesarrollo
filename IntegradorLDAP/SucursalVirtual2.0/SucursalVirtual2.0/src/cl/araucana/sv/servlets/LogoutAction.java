package cl.araucana.sv.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutAction
  extends HttpServlet
  implements Servlet
{
  private String homePage;
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    
    session.invalidate();
    
    if (this.homePage == null) {
      ServletContext servletContext = getServletContext();
      
      this.homePage = servletContext.getInitParameter("homePage");
    } 
    
    if (this.homePage != null) {
      response.sendRedirect("ibm_security_logout?logoutExitPage=" + this.homePage);
    } else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/");
      
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
}