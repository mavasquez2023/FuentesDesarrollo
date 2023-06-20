package cl.laaraucana.dsiniestro.dao;

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
	public T findBySucursal(Class<T> clazz, String idsucursal) {

		return (T)em
				.createQuery("select c from " + clazz.getName() + " c where c.codigoSucursal = :idsucursal")
				.setParameter("idsucursal", idsucursal).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c order by c.descripcion").getResultList();
	}

	@Override
	public boolean existRut(Class<T> clazz, String rut) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.rut = :rutAfi")
				.setParameter("rutAfi", rut).getResultList().size() > 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByRut(Class<T> clazz, String rut) {

		return em
				.createQuery("select c from " + clazz.getName() + " c where c.rut = :rutAfi order by ID DESC")
				.setParameter("rutAfi", rut).getResultList();
	}
	
}
