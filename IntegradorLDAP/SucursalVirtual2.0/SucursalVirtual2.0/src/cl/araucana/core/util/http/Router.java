/**
 * 
 */
package cl.araucana.core.util.http;

import cl.araucana.core.util.UserPrincipal;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Router extends HttpServlet
  implements Servlet
{
  public static final String userPrincipalAttributeName = "userPrincipal";
  private static HashMap credentials = new HashMap();
  private String localRedirector;
  private String appName;
  private Properties services;

  public static void inject(HttpServletRequest request, UserPrincipal userPrincipal)
  {
    HttpSession session = request.getSession();
    String sessionID = session.getId();

    credentials.put(sessionID, userPrincipal);
  }

  public static void reinject(HttpServletRequest request, String userID, String newPassword)
  {
    UserPrincipal userPrincipal = new UserPrincipal(userID, newPassword);

    inject(request, userPrincipal);
  }

  public static void drop(HttpSession session) {
    String sessionID = session.getId();

    UserPrincipal userPrincipal = (UserPrincipal)credentials.get(sessionID);

    if (userPrincipal != null)
    {
      credentials.remove(sessionID);
    }
  }

  public void init(ServletConfig servletConfig) {
    ServletContext servletContext = servletConfig.getServletContext();

    this.appName = servletContext.getServletContextName();

    log(">> init");

    this.services = new Properties();

    InputStream is = 
      getClass().getResourceAsStream("/etc/services.properties");
    try
    {
      if (is == null) {
        throw new IOException("resource not found");
      }

      this.services.load(is);
    } catch (IOException e) {
      log(
        "    cannot load '/etc/services.properties' file [" + 
        e.getMessage() + "]");

      if (is != null)
        try {
          is.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
    finally
    {
      if (is != null)
        try {
          is.close();
        }
        catch (IOException localIOException2) {
        }
    }
    if (this.services.isEmpty()) {
      log("    Error, no services configured");
    } else {
      log("    Configured Services");
      log("    -------------------");
      log("");

      Enumeration servicesEnumeration = this.services.keys();

      while (servicesEnumeration.hasMoreElements()) {
        String service = (String)servicesEnumeration.nextElement();
        String link = this.services.getProperty(service);

        log("    " + service + " -> " + link);
      }
    }

    this.localRedirector = servletContext.getInitParameter("localRedirector");

    if (this.localRedirector != null) {
      this.localRedirector = this.localRedirector.trim();
    }

    if (!this.localRedirector.equals("")) {
      log("");
      log("    localRedirector = " + this.localRedirector);
    } else {
      log("");
      log("    localRedirector = [reflect]");

      this.localRedirector = null;
    }

    log("");
    log("<< init");
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    HttpSession session = request.getSession();

    UserPrincipal userPrincipal = 
      (UserPrincipal)credentials.get(session.getId());

    if (userPrincipal == null) {
      log("missing user principal.");
      response.sendRedirect("/sv/logout.do");
      return;
    }

    if (this.localRedirector == null) {
      String svURL = request.getRequestURL().toString();
      int index = svURL.indexOf("/sv");

      this.localRedirector = svURL.substring(0, index + 3);
    }

    String service = request.getParameter("service");
    String serviceURL = null;

    if (service == null) {
      log("missing service.");
      serviceURL = request.getParameter("URL");
      if (serviceURL == null) {
        log("unknown service '" + service + "'");
        return;
      }
    } else {
      serviceURL = getServiceURL(service);
      if (serviceURL == null) {
        log("unknown service '" + service + "'");
        return;
      }
      log("service = " + service);
    }

    int sourceIndex = serviceURL.indexOf("source=");

    if (sourceIndex > 0) {
      char separator = serviceURL.charAt(sourceIndex - 1);

      if ((separator == '&') || (separator == '?')) {
        String sourceService = serviceURL.substring(sourceIndex + 7);

        if (getServiceURL(sourceService) != null) {
          serviceURL = 
            serviceURL.substring(0, sourceIndex) + 
            "source=" + 
            this.localRedirector + "/router.do" + 
            "%3Fservice=" + 
            sourceService;
        } else {
          log(
            "invalid source service '" + sourceService + "' " + 
            "in URL '" + serviceURL + "'.");

          return;
        }
      }
    }

    log("redirect to " + serviceURL);

    if (serviceURL.startsWith("/")) {
      response.setHeader("Location", "router.do");

      RequestDispatcher dispatcher = 
        request.getRequestDispatcher(
        "/WEB-INF/services" + serviceURL);

      dispatcher.forward(request, response);

      return;
    }

    String encodedUserCredentials = userPrincipal.encode();
    int index = serviceURL.indexOf("?");

    serviceURL = serviceURL + (
      index > 0 ? "&" : "?") + "uc=" + encodedUserCredentials;

    response.sendRedirect(serviceURL);
  }

  private String getServiceURL(String service) {
    return this.services.getProperty(service);
  }

  public void log(String message) {
    System.out.println(this.appName + ".Router: " + message);
  }
}