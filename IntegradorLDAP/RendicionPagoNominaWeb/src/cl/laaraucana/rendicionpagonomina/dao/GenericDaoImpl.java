package cl.laaraucana.rendicionpagonomina.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.ILF4500AVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByParams(Class<T> clazz, NominaManualVo params) throws Exception {
		StringBuilder str = new StringBuilder();

	/*	str.append("select c from " + clazz.getName() + " c where 1 = 1 ");

		if (!params.getEstado().isEmpty()) {
			str.append(" and c.estado = :estado ");
		}
		if (!params.getConvenio().isEmpty()) {
			str.append(" and c.convenio = :convenio");
		}
		if (!params.getProducto().isEmpty()) {
			str.append(" and c.producto = :producto");
		}
		if (!params.getFechaCarga().isEmpty()) {
			str.append(" and c.fechaCarga = :fecha");
		}
		str.append(" order by  c.fechaCarga ");
		
		Query query = (Query) em.createQuery(str.toString());

		if (!params.getEstado().isEmpty()) {
			query.setParameter("estado", Integer.parseInt(params.getEstado()));
		}
		if (!params.getConvenio().isEmpty()) {
			query.setParameter("convenio", params.getConvenio());
		}
		if (!params.getProducto().isEmpty()) {
			query.setParameter("producto", params.getProducto());
		}
		if (!params.getFechaCarga().isEmpty()) {
			query.setParameter("fecha", Utils.stringToDate(params.getFechaCarga()));
		}*/
		return null;
	}
	
	@Override
	public T findById(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		return em.find(clazz, id);
	}

	@Override
	public void deleteById(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		T entity = findById(clazz, id);
		em.remove(em.merge(entity));
	}

	@Override
	public void save(T Entity) {
		// TODO Auto-generated method stub
		em.persist(Entity);
	}

	@Override
	public T saveOrupdate(T Entity) {
		// TODO Auto-generated method stub
		return em.merge(Entity);

	}

	@Override
	public void remove(T Entity) {
		// TODO Auto-generated method stub
		em.remove(em.merge(Entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> finByNumNonmina(Class<T> clazz, long numnomina) {

		return em.createQuery("select c from " + clazz.getName() + " c where c.CodigoNomina = :codnom")
				.setParameter("codnom", numnomina).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> finByFecha(Class<T> clazz, Date fecha) {

		return em.createQuery("select c from " + clazz.getName() + " c where c.FECHACARGA = :fecha")
				.setParameter("fecha", fecha).getResultList();
	}
	
	@Override
	public void deleteByRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		em.createQuery("delete from " + clazz.getName() + " c where c.RUTAFI = :rut").setParameter("rut", rut);
		em.clear();

	}

	@Override
	public boolean existRut(Class<T> clazz, long rut) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.RUTAFI = :rut").setParameter("rut", rut)
				.getResultList().size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getILF4500A(Class<T> clazz, String rut) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.RUTAFI = :rut").setParameter("rut", rut)
				.getResultList();

	}

	@Override
	public void updateILF4500A(Class<T> clazz, ILF4500AVo vo) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("update " + clazz.getName()
						+ " set FECCOB = :FECCOB, ESTCOB = :ESTCOB, FOLBCO = :FOLBCO where RUTAFI = :rut")
				.setParameter("rut", vo.getRUT()).setParameter("FECCOB", vo.getFECCOB())
				.setParameter("ESTCOB", vo.getESTCOB()).setParameter("FOLBCO", vo.getFOLBCO());
		query.executeUpdate();
		em.clear();

	}

	@Override
	public void deleteBycodNom(Class<T> clazz, String codNom) {

		Query query = em.createQuery("delete from " + clazz.getName() + " where CODNOM = :codNom")
				.setParameter("codNom", codNom);

		query.executeUpdate();
		em.clear();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getNominasDetalle(Class<T> clazz, NominaVo nomina) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();

		str.append("select c from " + clazz.getName() + " c where 1 = 1 ");

		if (!nomina.getRut().isEmpty()) {
			str.append(" and c.RUTAFI = :rut ");

		}
		if (!nomina.getEstado().isEmpty()) {
			str.append(" and c.ESTDET = :estado ");
		}
		if (!nomina.getNomina().isEmpty()) {
			str.append(" and c.CODNOM = :codigoNomina");
		}
		str.append(" order by  c.RUTAFI ");
		
		Query query = (Query) em.createQuery(str.toString());

		if (!nomina.getRut().isEmpty()) {
			query.setParameter("rut", nomina.getRut());
		}
		if (!nomina.getEstado().isEmpty()) {
			query.setParameter("estado", nomina.getEstado());
		}
		if (!nomina.getNomina().isEmpty()) {
			query.setParameter("codigoNomina", Long.parseLong(nomina.getNomina()));
		}

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getNominasCabecera(Class<T> clazz, NominaVo nomina) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();

		str.append("select c from " + clazz.getName() + " c where 1 = 1 ");

		if (!nomina.getEstado().isEmpty()) {
			str.append(" and c.ESTPAGO = :estado ");
		}
		if (!nomina.getNomina().isEmpty()) {
			str.append(" and c.CODNOM = :codigoNomina");
		}

		Query query = (Query) em.createQuery(str.toString());

		if (!nomina.getEstado().isEmpty()) {
			query.setParameter("estado", nomina.getEstado());
		}
		if (!nomina.getNomina().isEmpty()) {
			query.setParameter("codigoNomina", Long.parseLong(nomina.getNomina()));
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getConvenioById(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where c.IDBANC = :ID").setParameter("ID", id)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findConvenioAndNomina(Class<T> clazz, long convenio, long nomina) {
		// TODO Auto-generated method stub
		return em
				.createQuery(
						"select c from " + clazz.getName() + " c where c.CODNOM = :CODNOM and c.IDCONVBAN = :IDCONVBAN")
				.setParameter("CODNOM", nomina).setParameter("IDCONVBAN", convenio).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findConvenioAndProducto(Class<T> clazz, String convenio, String producto) {
		// TODO Auto-generated method stub
		return em
				.createQuery(
						"select c from " + clazz.getName() + " c where c.IdCodConv = :convenio and c.Codprod = :producto")
				.setParameter("convenio", convenio).setParameter("producto", producto).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByIdCabecera(Class<T> clazz, long id) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where idCabecera= :idCabecera")
				.setParameter("idCabecera", id)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findPendientesByIdCabecera(Class<T> clazz, long id) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where idCabecera= :idCabecera and estadoPago= :estadoPago ")
				.setParameter("idCabecera", id)
				.setParameter("estadoPago", 3)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByIdDetalle(Class<T> clazz, long id) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from " + clazz.getName() + " c where idDetalle= :idDetalle")
				.setParameter("idDetalle", id)
				.getResultList();
	}

}
