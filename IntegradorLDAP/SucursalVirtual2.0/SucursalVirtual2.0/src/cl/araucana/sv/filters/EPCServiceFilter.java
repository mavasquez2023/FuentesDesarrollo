 package cl.araucana.sv.filters;
 
 import cl.araucana.core.registry.UserRegistryConnection;
 import cl.araucana.core.registry.exception.UserRegistryException;
 import java.util.Collection;
 

 public class EPCServiceFilter
   implements ServiceFilter
 {
   public boolean isEnabled(UserRegistryConnection connection) throws UserRegistryException {
     Collection enterprises = 
       connection.getEnterprises("PorEmpAdhe", "PorEmpAdheEnc");
     
     return (enterprises.size() > 0);
   }
 }
