package cl.laaraucana.contratocr.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@Autowired
	private EntityManager em;
	@Override
	public T findById(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}
	
	@Override
	public void save(T Entity) {

		em.persist(Entity);

	}

	@Override
	public void update(T Entity) {
		// TODO Auto-generated method stub
		em.merge(Entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findbyRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rut ")
				.setParameter("rut", rut).getResultList();
	}
	

	@Override
	public void updateByRut(Class<T> clazz, long rut, String codigoBarra) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("update " + clazz.getName() + " set codigoBarra = :codigoBarra where rutCliente = :rut");

		q.setParameter("codigoBarra", codigoBarra);
		q.setParameter("rut", rut);
		q.executeUpdate();
	}
	

}
