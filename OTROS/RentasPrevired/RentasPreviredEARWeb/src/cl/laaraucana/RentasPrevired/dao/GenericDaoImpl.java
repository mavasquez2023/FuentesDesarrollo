package cl.laaraucana.RentasPrevired.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	 
	@Autowired
	private EntityManager em;

	 
	
	public T findById(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}

	public void save(T Entity) {

		em.persist(Entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByName(Class<T> clazz, String name, String campo) {

		return em.createQuery("select c from " + clazz.getName() + " c where c." + campo + " = '" + name + "'")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByError(Class<T> clazz) {

		return em.createQuery("select c FROM " + clazz.getName() + " c where c.STATUS = 0").getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c").getResultList();
	}

}
