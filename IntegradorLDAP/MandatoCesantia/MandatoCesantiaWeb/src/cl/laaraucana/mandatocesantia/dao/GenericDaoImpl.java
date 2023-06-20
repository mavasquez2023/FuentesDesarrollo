package cl.laaraucana.mandatocesantia.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;


	public T findById(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}

	public void save(T Entity) {

		em.persist(Entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c order by c.descripcion").getResultList();
	}


	@Override
	public void update(T Entity) {

		em.merge(Entity);

	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByRutBita(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rutAfiliado")
				.setParameter("rutAfiliado", rut).getResultList();
	}




}
