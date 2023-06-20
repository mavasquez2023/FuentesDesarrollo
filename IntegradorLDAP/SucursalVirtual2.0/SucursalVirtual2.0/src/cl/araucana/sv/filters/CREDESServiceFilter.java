package cl.araucana.sv.filters;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;


public class CREDESServiceFilter
  implements ServiceFilter
{
  public boolean isEnabled(UserRegistryConnection connection) throws UserRegistryException {
    return true;
  }
}
