package cl.araucana.cp.hibernate.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ArchivoCargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class CargaFamiliarDAO {
	private static Logger logger = Logger.getLogger(CargaFamiliarDAO.class);
	private Session session;

	public CargaFamiliarDAO(Session session) {
		this.session = session;
	}

	/**
	 * Obtiene las empresas que presentan Cargas Familiares
	 * 
	 * @param cantidad
	 * @param primerReg
	 * @param filtros
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresas(HashMap filtros, Collection col) throws DaoException {
		try {
			if (col.isEmpty() || col == null)
				return Collections.EMPTY_LIST;

			String filtroRut   = (filtros.containsKey("rutEmpresa")  ? (filtros.get("rutEmpresa")).toString()  : null);
			String filtroRazon = (filtros.containsKey("razonSocial") ? (filtros.get("razonSocial")).toString() : null);

			StringBuffer sb = new StringBuffer("SELECT DISTINCT em, ec ");
			sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO as cf " +
					     ", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as em " +
					     ", cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO as co " +
					     ", cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO as ec " +
					 "WHERE cf.rutEmpresa    = co.idEmpresa" +
					  " AND cf.idEntidadCCAF = co.idCcaf" +
					  " AND em.idEmpresa     = co.idEmpresa" +
					  " AND co.idCcaf        = ec.id "       +
					  " AND ec.id           <> " + Constants.EXCAJA_FALSO);

			if (filtroRut != null) {
				sb.append(" AND em.idEmpresa= " + filtroRut);
			}

			if (filtroRazon != null) {
				sb.append(" AND em.razonSocial LIKE '%" + filtroRazon + "%'");
			}

			sb.append(" AND em.idEmpresa IN (");
			for (Iterator it = col.iterator(); it.hasNext();)
			{
				sb.append(it.next().toString());
				if (it.hasNext())
					sb.append(", ");
			}
			sb.append(") ");
			sb.append("ORDER BY em.idEmpresa, em.razonSocial ASC");

			Query q = this.session.createQuery(sb.toString());

			return q.list();
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getEmpresas::" + ex);
			throw new DaoException("getTrabajadores: ", ex);
		}
	}
	
	/**
	 * Obtiene un listado de trabajadores con cargas familiares dado parámetros de búsqueda
	 *  
	 * @param cantidad
	 * @param primerReg
	 * @param filtros
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getTrabajadores(HashMap filtros, Collection col) throws DaoException {
		try {
			if (col.isEmpty() || col == null)
				return Collections.EMPTY_LIST;

			String filtroRut      = (filtros.containsKey("rutTrabajador") ? (filtros.get("rutTrabajador")).toString() : null);
			String filtroNombre   = (filtros.containsKey("nombre")     ? (filtros.get("nombre")).toString()     : null);
			String filtroEmpresa  = (filtros.containsKey("rutEmpresa") ? (filtros.get("rutEmpresa")).toString() : null);
			String filtroCaja     = (filtros.containsKey("idCaja")     ? (filtros.get("idCaja")).toString()     : null);

			StringBuffer sb = new StringBuffer("SELECT cf, em ");
			sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO as cf " +
					     ", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as em " +
					 "WHERE cf.rutEmpresa = em.idEmpresa "); 

			if (filtroRut != null) {
				sb.append(" AND cf.rutTrabajador = " + filtroRut);
			}

			if (filtroEmpresa != null) {
				sb.append(" AND cf.rutEmpresa = " + filtroEmpresa);
			}

			if (filtroNombre != null) {
				sb.append(" AND cf.nombreTrabajador LIKE '%" + filtroNombre + "%'");
			}

			if (filtroCaja != null) {
				sb.append(" AND cf.idEntidadCCAF = " + filtroCaja);
			}

			sb.append(" AND em.idEmpresa IN (");
			for (Iterator it = col.iterator(); it.hasNext();)
			{
				sb.append(it.next().toString());
				if (it.hasNext())
					sb.append(", ");
			}
			sb.append(") ");
			sb.append("ORDER BY cf.rutTrabajador, cf.nombreTrabajador ASC");

			Query q = this.session.createQuery(sb.toString());

			return q.list();
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getTrabajadores::" + ex);
			throw new DaoException("getTrabajadores: ", ex);
		}
	}

	/**
	 * Obtiene las cargas familiares de un trabajador
	 *  
	 * @param rutTrabajador
	 * @param rutEmpresa
	 * @param idCCAF
	 * @return
	 * @throws DaoException
	 */
	public List getCargasPorTrabajador(int rutTrabajador, int rutEmpresa, int idCCAF) throws DaoException {
		try {
			StringBuffer sb = new StringBuffer("SELECT CF.rutTrabajador, CF.nombreTrabajador, DC.rutCarga, DC.nombreCarga, DC.fecNacCarga, DC.fecIniVigencia, DC.fecFinVigencia, PC.nombre, TC.tipoCarga ");
			sb.append(", EM.idEmpresa, EM.razonSocial, SU.nombre, AF.valorCarga, TC.idTipoCarga, EC.nombre, AF.nombre ");
			/*sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO AS CF INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.DetalleCargaFamiliarVO AS DC WITH CF.idCargaFamiliar = DC.idCargaFamiliar " +
                            																 "INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.TipoCargaVO            AS TC WITH DC.idTipoCarga     = TC.idTipoCarga " +
                            																 "INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.ParentescoCargaVO      AS PC WITH DC.idParentesco    = PC.idParentescoCarga " +
                            																 "INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO 				AS AF WITH CF.idTramoAF       = AF.id " +
                            																 "INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO          AS EC WITH CF.idEntidadCCAF   = EC.id " +
                            																 "INNER JOIN cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO              AS EM WITH CF.rutEmpresa      = EM.idEmpresa " +
                            																 "LEFT  JOIN cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO             AS SU WITH EM.idEmpresa	    = SU.idEmpresa " + 
                                                                   																												"AND EM.idCasaMatriz    = SU.idSucursal ");*/
			sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO AS CF" + 
	                     ", cl.araucana.cp.distribuidor.hibernate.beans.DetalleCargaFamiliarVO AS DC" +
						 ", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO AS EM" +
						 ", cl.araucana.cp.distribuidor.hibernate.beans.TipoCargaVO AS TC" +
						 ", cl.araucana.cp.distribuidor.hibernate.beans.ParentescoCargaVO AS PC " +
						 ", cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO AS SU"+
						 ", cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO AS AF"+
						 ", cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO AS EC");
			 sb.append(" WHERE CF.rutEmpresa = " + String.valueOf(rutEmpresa));

			if (rutTrabajador != 0)
				sb.append(" AND CF.rutTrabajador = " + String.valueOf(rutTrabajador));
			if (idCCAF != 0)
				sb.append(" AND CF.idEntidadCCAF = " + String.valueOf(idCCAF));

			sb.append(" AND CF.rutEmpresa      = EM.idEmpresa" +
					  " AND CF.idCargaFamiliar = DC.idCargaFamiliar" +
					  " AND CF.idTramoAF       = AF.id" +
					  " AND CF.idEntidadCCAF   = EC.id" +
					  " AND DC.idTipoCarga     = TC.idTipoCarga" +
					  " AND DC.idParentesco    = PC.idParentescoCarga" +
					  " AND EM.idEmpresa	   = SU.idEmpresa" +
					  " AND EM.idCasaMatriz    = SU.idSucursal");

			Query q = this.session.createQuery(sb.toString());
			return q.list();
		} catch (Exception e) {
			logger.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getCargasPorTrabajador::" + e);
			throw new DaoException("getTrabajadores: ", e);
		}
	}

	/**
	 * Obtiene un único Cotizante dado su rut y su empresa  
	 * 
	 * @param rutTrabajador
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public CotizanteVO getCotizante(int rutTrabajador, int rutEmpresa) throws DaoException {
		try {
			return (CotizanteVO) this.session.createCriteria(CotizanteVO.class)
											 .add(Restrictions.eq("idCotizante", new Integer(rutTrabajador)))
											 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
											 .setFirstResult(0)
											 .setMaxResults(1)
											 .uniqueResult();
		} catch (Exception e) {
			logger.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getCotizantes::" + e);
			throw new DaoException("getCotizantes: ", e);
		}
	}
	
	/**
	 * Obtiene un único Cotizante dado su rut y su empresa  
	 * 
	 * @param idCaja
	 * @return ArchivoCargaFamiliarVO
	 * @throws DaoException
	 */
	public List getFechaEnvioByCaja(int idCaja) throws DaoException {
		try {
			StringBuffer sb = new StringBuffer("select max(ACF.fecha) ,max(ACF.hora) ,ACF.idCaja " +
					" from cl.araucana.cp.distribuidor.hibernate.beans.ArchivoCargaFamiliarVO ACF " +
					" WHERE ACF.estado in ('TER','TEA') AND ACF.idCaja = " +idCaja+
					" group by ACF.idCaja");
			
			Query q = this.session.createQuery(sb.toString());
			return q.list();
			
		} catch (Exception e) {
			logger.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getFechaEnvioByCaja::" + e);
			throw new DaoException("getFechaEnvioByCaja: ", e);
		}
	}
}