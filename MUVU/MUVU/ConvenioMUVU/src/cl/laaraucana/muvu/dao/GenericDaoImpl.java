package cl.laaraucana.muvu.dao;

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
	
	public void update(T Entity) {

		em.merge(Entity);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByRut(Class<T> clazz, long rut) {

		return em
				.createQuery("select c from " + clazz.getName() + " c where c.rutAfiliado = :rutAfi ")
				.setParameter("rutAfi", rut).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByEmail(Class<T> class1, String email) {
		return em
				.createQuery("select c from " + class1.getName() + " c where c.email = :email ")
				.setParameter("email", email).getResultList();
	}

	
}
