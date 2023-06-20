package cl.laaraucana.envioFormularioASFAM.dao;

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
	public T findBySucursal(Class<T> clazz, String idSucursal) {
		// TODO Auto-generated method stub
		return (T)em.createQuery("select c from " + clazz.getName() + " c where c.codigo = :idSucursal")
				.setParameter("idSucursal", idSucursal)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAnalistasBySucursal(Class<T> clazz, String idSucursal) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.idSucursal = :idSucursal")
				.setParameter("idSucursal", idSucursal)
				.getResultList();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.rutAfi = :rut order by c.FECCRE desc").setParameter("rut", rut)
				.getResultList();
	}

}
