package com.alexis.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.alexis.model.Role;


@Repository
@Transactional
public interface rolDao extends CrudRepository<Role, Long>{
	
	
	@Modifying
	@Query("DELETE FROM Role r where r.usuario.id = :id") 
    void deleteByUserId(@Param("id") Long id);	
	
	@Modifying
	@Query("DELETE FROM Role r where r.usuario.id = '' or r.usuario.id IS NULL") 
    void deleteByIdNull();	
}
