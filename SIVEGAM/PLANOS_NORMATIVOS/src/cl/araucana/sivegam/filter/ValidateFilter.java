package cl.araucana.sivegam.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

public class ValidateFilter implements Filter
{
      //private String user = null;
      private String mensaje = "";
      
      @Override
      public void destroy() {
            // TODO Auto-generated method stub
            
      }

      @Override
      public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
      {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Principal userPrincipal = httpRequest.getUserPrincipal();
            //String userID = "11648834-5";
            String userID = null;
            
            HttpSession sesion = ((HttpServletRequest) request).getSession();
            //String sourceURL = String.valueOf(sesion.getAttribute("forwardPage"));
            
            //Verifica si el usuario est� autorizado
            if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
                  
                  RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/error_login.jsp");
                  mensaje = "El usuario no est� autenticado.";
                  request.setAttribute("mensaje", "El usuario no est� autenticado");
                  requestDispatcher.forward(request, response);
                  return;
            }

            HttpSession session = httpRequest.getSession();
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            User user = null; //(User)session.getAttribute("user");
            
            
            //Si usuario no est� autenticado
            if (userInfo == null) {      
                  //Autentica usuario en Ldap
                  UserRegistryConnection urConnection = null;
                  
                  try {
                        urConnection = new UserRegistryConnection();
                        userInfo= new UserInfo();
                        userInfo.setBlocked(false);
                        userInfo.setUserID(userID);
                        userInfo.setMustChangePassword(false);
                        userInfo = urConnection.getUserInfo(userID);
                        user = urConnection.getUser(userID);
                        String name = user.getFullName(true);
                        session.setAttribute("userInfo", userInfo);
                        session.setAttribute("userIBM", name);
                  } catch (UserRegistryException e) {
                        e.printStackTrace();
                             try {
                                   urConnection.close();
                             } catch (Exception a) {}
                        //Obtener mensaje desde archivo properties
                             mensaje = "Error al iniciar sesi�n.";
                        request.setAttribute("mensaje", "Error al iniciar sesi�n");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/error_login.jsp");
                        requestDispatcher.forward(request, response);
                        return;
                  }
            }
            
            String msgListNull = " ";
            
            // Si usuario ya esta autenticado
            if (userInfo.isBlocked()) {
              System.out.println("Usuario se encuentra bloqueado.");
              // Redirecciona al sistema anterior
              mensaje = "El usuario se encuentra bloqueado.";
              request.setAttribute("mensaje", "El usuario se encuentra bloqueado.");
              RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/error_login.jsp");
              requestDispatcher.forward(request, response);
              return;
            } 
            
            System.out.println("USER SERVLSET :" + userID);
            
            sesion.setAttribute("mensaje", mensaje);
            sesion.setAttribute("login", userID);
                  
            chain.doFilter(request, response);       
      }

      @Override
      public void init(FilterConfig arg0) throws ServletException {
            
      }

}