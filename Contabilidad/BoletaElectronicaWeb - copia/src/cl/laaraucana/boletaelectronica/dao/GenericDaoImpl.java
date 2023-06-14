package cl.laaraucana.boletaelectronica.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
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

	public void deleteById(Class<T> clazz, long id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
	}

	@Override
	public List<T> findByName(Class<T> clazz, String name) {

		return em.createQuery("select c from " + clazz.getName() + " c where c.NOMBRE = '" + name + "'")
				.getResultList();
	}

	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c").getResultList();
	}

	@Override
	public List<T> findByNumber(Class<T> clazz, long folio, int estado) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLDOC = :folio and c.ESTADO = :estado")
				.setParameter("folio", folio).setParameter("estado", estado).getResultList();

	}

	@Override
	public List<T> findByFolioBoleta(Class<T> clazz, long folio) {

		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLDOC = :folio")
				.setParameter("folio", folio).getResultList();
	}

	@Override
	public boolean existNumberAndFol(Class<T> clazz, long folio) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLDOC = :folio")
				.setParameter("folio", folio).getResultList().size() > 0;
	}

	@Override
	public T getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return (T) em.createQuery("select c from " + clazz.getName() + " c ").getSingleResult();
	}

	@Override
	public T getByCode(Class<T> clazz, int code) {

		return (T) em.createQuery("select c from " + clazz.getName() + " c where c.CODIGO = :codigo and c.ESTADO = 1")
				.setParameter("codigo", code).getSingleResult();
	}

	@Override
	public T getLastCode(Class<T> clazz) {
		// TODO Auto-generated method stub
		return (T) em.createQuery("select c from " + clazz.getName() + " c order by C.CODIGO DESC").setMaxResults(1)
				.getSingleResult();
	}

	@Override
	public List<T> findAllByEstate(Class<T> clazz, int estado) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.ESTADO = :estado")
				.setParameter("estado", estado).getResultList();
	}

	@Override
	public List<T> findAllNoEmitidas(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em
				.createQuery(
						"select c from " + clazz.getName() + " c where (c.TIPDOC = 39 or c.TIPDOC = 41) and c.NUMBOL = 0")
				.getResultList();

	}

	@Override
	public List<T> findAllEmitidas(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.ESTADO = 1").getResultList();
	}

	@Override
	public List<T> findByNumber(Class<T> clazz, int folio) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLIO = :folio")
				.setParameter("folio", folio).getResultList();

	}

	@Override
	public List<T> findByNumBolAndTipoDoc(Class<T> clazz, long numboleta, int tipodoc) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from " + clazz.getName() + " c where c.NUMBOL = :numbol and c.TIPDOC = :tipodoc")
				.setParameter("numbol", numboleta).setParameter("tipodoc", tipodoc).getResultList();
	}

	@Override
	public List<T> findByNumBolAndTipoDocOrigen(Class<T> clazz, int folio, int tipodoc) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLIO = :folio and c.TIPDOC = :tipodoc")
				.setParameter("folio", folio).setParameter("tipodoc", tipodoc).getResultList();
	}

	@Override
	public void updateOrigen(Class<T> clazz, long numero, int folio) {
		// TODO Auto-generated method stub
		Query result = em.createQuery("update " + clazz.getName() + " set NUMBOL = :numero where FOLIO = :folio");

		result.setParameter("numero", numero);
		result.setParameter("folio", folio);
		result.executeUpdate();
	}

	@Override
	public List<T> findByNumber(Class<T> clazz, long folio) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.FOLDOC = :folio")
				.setParameter("folio", folio).getResultList();
	}

	@Override
	public List<T> findByFecha(Class<T> clazz, long fechaInicio, long fechaFin, long folio) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();

		str.append("select c from " + clazz.getName() + " c where 1 = 1 ");

		if (folio != 0) {
			str.append(" and  c.FOLDOC = :folio ");
		}
		if (fechaInicio != 0 && fechaFin != 0) {
			str.append(" and c.FECCRE  >= :fecIni  and c.FECCRE <= :fecFin");
		}

		Query q = em.createQuery(str.toString());

		if (folio != 0) {
			q.setParameter("folio", folio);
		}
		if (fechaInicio != 0 && fechaFin != 0) {

			q.setParameter("fecIni", fechaInicio);
			q.setParameter("fecFin", fechaFin);
		}
		return q.getResultList();

	}

}
