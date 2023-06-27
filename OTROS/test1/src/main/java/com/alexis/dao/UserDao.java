package com.alexis.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.alexis.model.Usuario;

@Repository
@Transactional
public interface UserDao extends CrudRepository<Usuario, Long> {
	
    Usuario findByUsername(String username);
    
      
	@Query("select c FROM Usuario c where c.email = :email") 
    Usuario getByUserEmail(@Param("email") String email);
	
	@Modifying
	@Query("update Usuario c set c.password = :password where c.email = :email") 
    void updatePassword(@Param("email") String email, @Param("password") String password);
    
}


