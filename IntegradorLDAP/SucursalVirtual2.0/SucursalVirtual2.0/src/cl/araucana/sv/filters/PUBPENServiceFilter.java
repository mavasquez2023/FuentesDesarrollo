package cl.araucana.sv.filters;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import java.util.List;


public class PUBPENServiceFilter
  implements ServiceFilter
{
  public boolean isEnabled(UserRegistryConnection connection) throws UserRegistryException {
    List userRoles = (List) connection.getUserRoles(connection.getUserID(), "PlaDesPen");
    return (userRoles.size() > 0);
  }
}
