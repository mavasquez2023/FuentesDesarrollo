package cl.laaraucana.convenfsura.dao;

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
	public List<T> findByRut(Class<T> clazz, int rut) {

		return em
				.createQuery("select c from " + clazz.getName() + " c where c.rut = :rutAfi ")
				.setParameter("rutAfi", rut).getResultList();
	}

	
}
