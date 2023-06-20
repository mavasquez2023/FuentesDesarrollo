package cl.laaraucana.copagocredito.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@PersistenceContext(unitName = "persistenceUnitw")
	private EntityManager em;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em2;

	@PersistenceContext(unitName = "persistenceUnitAS")
	private EntityManager em3;

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
		return em2.createQuery("select c from " + clazz.getName() + " c order by c.descripcion").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em2
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rutCliente  order by c.nCredito, c.valorCuota")
				.setParameter("rutCliente", rut).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByNotAutorized(Class<T> clazz, long fol, long cuota) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName()
				+ " c where c.nCredito = :folioCredito and c.ncuota = :cuotaCopago and c.autorizado = 'NO'")
				.setParameter("folioCredito", fol).setParameter("cuotaCopago", cuota).getResultList();
	}

	@Override
	public void update(T Entity) {

		em.merge(Entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByAutorized(Class<T> clazz, long fol, long cuota) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName()
				+ " c where c.nCredito = :folioCredito and c.ncuota = :cuotaCopago and c.autorizado = 'SI'")
				.setParameter("folioCredito", fol).setParameter("cuotaCopago", cuota).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByRutBita(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rutAfiliado and c.autorizado = 'SI'")
				.setParameter("rutAfiliado", rut).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByAutorizedCredito(Class<T> clazz, long fol, long cuota) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.nCredito = :folioCredito and c.ncuota = :cuotaCopago")
				.setParameter("folioCredito", fol).setParameter("cuotaCopago", cuota).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMandatoByRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em3.createQuery("select c from " + clazz.getName() + " c where c.RUTAFI = :rutAfiliado")
				.setParameter("rutAfiliado", rut).getResultList();
	}

}
