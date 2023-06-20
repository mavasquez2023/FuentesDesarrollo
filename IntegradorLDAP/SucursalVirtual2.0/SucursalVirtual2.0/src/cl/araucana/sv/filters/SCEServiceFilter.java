package cl.araucana.sv.filters;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import java.util.HashMap;
import java.util.List;




public class SCEServiceFilter
  implements ServiceFilter
{
  public boolean isEnabled(UserRegistryConnection connection) throws UserRegistryException {
    System.out.println("SCEServiceFilter: connection.getUserRoles");
    List userRoles = (List) connection.getUserRoles(connection.getUserID(), "CreditoInternet");
    System.out.println("SCEServiceFilter: connection.getUserRoles: ok");
    return (userRoles.size() > 0);
  }
}
