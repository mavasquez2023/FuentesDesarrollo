package cl.araucana.sv;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.sv.filters.ACServiceFilter;
import cl.araucana.sv.filters.CCServiceFilter;
import cl.araucana.sv.filters.EPCServiceFilter;
import cl.araucana.sv.filters.SCEServiceFilter;
import cl.araucana.sv.filters.ServiceFilter;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;


public class ServicesFilter
{
  private boolean ok = false;
  
  public ServicesFilter(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    if (principal == null) {
      
      System.err.println("Sucursal Virtual.ServicesFilter: Unknown principal.");
      return;
    } 
    this.enabledServices = new HashMap();
    String userID = principal.getName();
    
    System.out.println(userID);
    
    UserRegistryConnection connection = null;
    
    try {
      connection = new UserRegistryConnection();
      connection.setUserID(userID);
      for (int i = 0; i < serviceNames.length; i++) {
        
        ServiceFilter serviceFilter = (ServiceFilter)filters.get(serviceNames[i]);
        boolean enabled = serviceFilter.isEnabled(connection);
        this.enabledServices.put(serviceNames[i], new Boolean(enabled));
      } 
      
      this.ok = true;
    }
    catch (UserRegistryException e) {
      
      System.err.println("Sucursal Virtual.ServicesFilter: UserRegistryException.");
      e.printStackTrace(System.err);
    }
    finally {
      
      if (connection != null) {
        
        try {
          connection.close();
        }
        catch (UserRegistryException userRegistryException) {}
      }
    } 
  }
  
  public boolean isEnabled(String serviceName) {
    Boolean enabled = (Boolean)this.enabledServices.get(serviceName);
    if (enabled == null) {
      return false;
    }
    return enabled.booleanValue();
  }

  
  public boolean isOK() {
    return this.ok;
  }

  
  public String[] getServiceNames() {
    String[] names = new String[serviceNames.length];
    System.arraycopy(serviceNames, 0, names, 0, names.length);
    return names;
  }







  
  private static HashMap filters = new HashMap(); static {
    filters.put("CC", new CCServiceFilter());
    filters.put("EPC", new EPCServiceFilter());
    filters.put("SCE", new SCEServiceFilter());
    filters.put("AC", new ACServiceFilter());
  } private static String[] serviceNames = new String[filters.size()]; static {
    int i = 0;
    for (Iterator iterator = filters.keySet().iterator(); iterator.hasNext(); ) {
      
      String serviceName = (String) iterator.next();
      serviceNames[i++] = serviceName;
    } 
  }
  
  private HashMap enabledServices;
}