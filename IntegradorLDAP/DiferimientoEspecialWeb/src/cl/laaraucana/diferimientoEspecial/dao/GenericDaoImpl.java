package cl.laaraucana.diferimientoEspecial.dao;

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
						+ " c where c.rutCliente = :rutCliente  order by c.folioCredito, c.cuotaDiferir")
				.setParameter("rutCliente", rut).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByNotAutorized(Class<T> clazz, String fol, long cuota) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.foliocre = :folioCredito and c.numcuota = :cuotaDiferir and c.autorizado = 'NO'")
				.setParameter("folioCredito", fol).setParameter("cuotaDiferir", cuota).getResultList();
	}

	@Override
	public void update(T Entity) {
		
		em.merge(Entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByAutorized(Class<T> clazz, String fol, long cuota) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.foliocre = :folioCredito and c.numcuota = :cuotaDiferir and c.autorizado = 'SI'")
				.setParameter("folioCredito", fol).setParameter("cuotaDiferir", cuota).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByRutBita(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutAfi = :rutAfiliado and c.autorizado = 'SI'")
				.setParameter("rutAfiliado", rut).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByAutorizedCredito(Class<T> clazz, String fol, long cuota) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.foliocre = :folioCredito and c.numcuota = :cuotaDiferir")
				.setParameter("folioCredito", fol).setParameter("cuotaDiferir", cuota).getResultList();
	}

}
