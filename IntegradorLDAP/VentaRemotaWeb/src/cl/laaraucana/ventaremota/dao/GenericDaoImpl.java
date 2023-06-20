package cl.laaraucana.ventaremota.dao;

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
	public T findById(Class<T> clazz, String id) {
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
	public List<T> findAllbyRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rut and c.estado = '01' ")
				.setParameter("rut", rut).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findCCRbyRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rut ")
				.setParameter("rut", rut).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllRechazadoByRut(Class<T> clazz, long rut, long numoferta) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutcliente= :rut and c.IdAprobRech='03' and c.numeroOferta= :numoferta ")
				.setParameter("rut", rut)
				.setParameter("numoferta", numoferta).getResultList();
	}

	@Override
	public void caducar(Class<T> clazz) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("update " + clazz.getName() + " set estado = '04' where estado = '01'");

		q.executeUpdate();
	}

	@Override
	public void updateById(Class<T> clazz, long id, String estado) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("update " + clazz.getName() + " set estado = :estado where id = :id");

		q.setParameter("estado", estado);
		q.setParameter("id", id);
		q.executeUpdate();
	}
	@Override
	public void deleteByOferta(Class<T> clazz, long numoferta) {
		em.createQuery("delete from " + clazz.getName() + " where numeroOferta = :numoferta")
				.setParameter("numoferta", numoferta)
				.executeUpdate();

		em.clear();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllbyEstado(Class<T> clazz, String estado) {
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.estado = :estado ")
				.setParameter("estado", estado).getResultList();
	}

}
