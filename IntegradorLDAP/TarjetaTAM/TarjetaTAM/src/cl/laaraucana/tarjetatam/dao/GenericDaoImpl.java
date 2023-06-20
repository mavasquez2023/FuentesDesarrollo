package cl.laaraucana.tarjetatam.dao;

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
	public T findByOficina(Class<T> clazz, String idOficina) {

		return (T)em
				.createQuery("select c from " + clazz.getName() + " c where c.codigoOficina = :idOficina")
				.setParameter("idOficina", idOficina).getSingleResult();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName()
						+ " c where c.rutCliente = :rutCliente  order by c.nCredito, c.valorCuota")
				.setParameter("rutCliente", rut).getResultList();
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
	public T findByComunaSuc(Class<T> clazz, String idComuna) {

		return (T)em
				.createQuery("select c from " + clazz.getName() + " c where c.idComuna = :id")
				.setParameter("id", idComuna).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByComunaReg(Class<T> clazz, int idRegion) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.region = :region order by c.descripcion")
				.setParameter("region", idRegion).getResultList();
	}
}
