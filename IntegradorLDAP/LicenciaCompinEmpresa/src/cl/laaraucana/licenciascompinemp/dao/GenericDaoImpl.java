package cl.laaraucana.licenciascompinemp.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.laaraucana.licenciascompinemp.entities.Comuna;

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
	public List<T> findByComunaSuc(Class<T> clazz, int idcomuna) {

		return em
				.createQuery("select c from " + clazz.getName() + " c where c.idcomuna = :id")
				.setParameter("id", idcomuna).getResultList();
	}

	 
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c order by c.descripcion").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByComunaReg(Class<T> clazz, int idRegion) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.region = :region order by c.descripcion")
				.setParameter("region", idRegion).getResultList();
	}

	@Override
	public boolean existfolio(Class<T> clazz, String folio) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.folioLicencia = :folioLicencia")
				.setParameter("folioLicencia", folio).getResultList().size() > 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByFolio(Class<T> clazz, String folio, String rut) {

		return em
				.createQuery("select c from " + clazz.getName() + " c where c.folioLicencia = :folioLicencia and c.rut = :rutAfi order by ID DESC")
				.setParameter("folioLicencia", folio)
				.setParameter("rutAfi", rut).getResultList();
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
	public List<T> getCorreoEjecutivo(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c order by balanceo").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllSucursales(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c order by c.nombreSucursal").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getSucursalByCodigo(Class<T> clazz, String codSucursal) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.codigoSucursal= :codSucursal ")
				.setParameter("codSucursal", codSucursal).getResultList();
	}
}
