package cl.araucana.sv.servlets;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.http.Router;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePasswordAction
  extends HttpServlet
  implements Servlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String initialChange = request.getParameter("initialChange");
    String nextPage = request.getParameter("nextPage");
    
    String currentPassword = request.getParameter("currentPassword");
    String newPassword = request.getParameter("newPassword");
    RequestDispatcher requestDispatcher = null;
    
    System.out.println("***ChangePasswordAction:");
    System.out.println("initialChange: " + initialChange);
    System.out.println("nextPage: " + nextPage);
    System.out.println("currentPassword: " + currentPassword);
    System.out.println("newPassword: " + newPassword);
    System.out.println("");
    
    if (currentPassword == null || newPassword == null) {
      System.out.println("password: null, redireccionando a logout");
      requestDispatcher = request.getRequestDispatcher("/logout.do");
    } else {
      Principal userPrincipal = request.getUserPrincipal();
      System.out.println("userPrincipal: " + userPrincipal);
      System.out.println("userPrincipal.getName()" + userPrincipal.getName());
      if (userPrincipal == null || userPrincipal.getName() == null) {
        System.out.println("usuario: null, redireccionando a logout");
        requestDispatcher = request.getRequestDispatcher("/logout.do");
      } else {
        requestDispatcher = 
          request.getRequestDispatcher(
            "/changePasswordResponse.jsp");
        
        UserRegistryConnection urConnection = null;
        String userID = userPrincipal.getName();
        System.out.println("usuario: ok, redireccionando a changePasswordResponse");
        try {
          urConnection = new UserRegistryConnection();
          
          urConnection.changeUserPassword(
              userID, currentPassword, newPassword);
          
          Router.reinject(request, userID, newPassword);
          
          request.setAttribute("changeOK", "true");
          request.setAttribute("initialChange", initialChange);
          request.setAttribute("nextPage", nextPage);
          System.out.println("resultado cambio de contraseña: ok");
        } catch (UserRegistryException e) {
          System.out.println("resultado cambio de contraseña: error");
          e.printStackTrace();
          
          request.setAttribute("changeOK", "false");
        } finally {
          if (urConnection != null) {
            try {
              urConnection.close();
            } catch (UserRegistryException userRegistryException) {}
          }
        } 
      } 
    } 
    
    requestDispatcher.forward((ServletRequest)request, (ServletResponse)response);
  }
}