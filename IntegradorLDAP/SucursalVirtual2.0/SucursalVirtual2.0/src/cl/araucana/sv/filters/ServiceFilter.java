package cl.araucana.sv.filters;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

public interface ServiceFilter {
  boolean isEnabled(UserRegistryConnection paramUserRegistryConnection) throws UserRegistryException;
}