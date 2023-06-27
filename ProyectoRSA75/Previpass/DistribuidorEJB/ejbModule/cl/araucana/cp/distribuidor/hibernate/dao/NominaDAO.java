package cl.araucana.cp.distribuidor.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionDNPVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionDNPVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class NominaDAO
{
	private static Logger log = Logger.getLogger(NominaDAO.class);

	/*private StatelessSession session;

	public NominaDAO(StatelessSession session) throws DaoException {
		if (session == null)
			throw new DaoException("No se obtuvo session hibernate");
		this.session = session;
	}*/

	private Session session;
	
	public NominaDAO(Session session) {
		this.session = session;
	}
	
	
	public List getInformeErrores(String idEmpresa, String idConvenio, String tipoNomina) throws DaoException {
		try {

			String select = "select cot, cau, tca ";

			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION))){
				select+=" from   CotizacionPendienteRAVO cot, CausaRAVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION))){
				select+=" from   CotizacionPendienteGRVO cot, CausaGRVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION))){
				select+=" from   CotizacionPendienteREVO cot, CausaREVO cau, TipoCausaVO tca " ;
			}
			if (tipoNomina.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO))){
				select+=" from   CotizacionPendienteDCVO cot, CausaDCVO cau, TipoCausaVO tca " ;
			}			

			select +=" where  cot.rutEmpresa   = cau.rutEmpresa " +
					 " and    cot.idConvenio   = cau.idConvenio " +
					 " and    cot.idCotizPendiente  = cau.idCotizPendiente " +
					 " and    cau.idTipoCausa  = tca.id " +
					 " and    tca.error        = " + String.valueOf(Constants.NIVEL_VAL_ERROR) + 
					 " and    cot.rutEmpresa   = " + idEmpresa +
					 " and    cot.idConvenio   = " + idConvenio;


			StringBuffer hqlQuery = new StringBuffer(select);

			Query query = this.session.createQuery(hqlQuery.toString());

			return query.list();
		} catch (Exception ex) {
			log.error("Ha ocurrido la siguiente excepcion en NominaDAO.getInformeCausa:", ex);
			throw new DaoException("NominaDAO.getInformeCausa: ", ex);
		}
	}


	/**
	 * Cargas Familiares por Empresa y Caja 
	 * 
	 * @param rutEmpresa
	 * @param idEntidadCCAF
	 * @return
	 */
	public List getCargasFamiliaresPorEmpresa(int rutEmpresa, int idEntidadCCAF) {
		List listadoCargas = this.session.createCriteria(CargaFamiliarVO.class)
										 .add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa)))
										 .add(Restrictions.eq("idEntidadCCAF", new Integer(idEntidadCCAF)))
										 .list();
		return listadoCargas;
	}
	
	/**
	 * Obtiene las cajas que presentan cargas familiares para la empresa indicada
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public int [] getCajasEnCargasPorEmpresa(int rutEmpresa) throws DaoException {
		try {
			String query = "SELECT DISTINCT CF.idEntidadCCAF " +
							 "FROM cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO AS CF " +
							"WHERE CF.rutEmpresa = " + rutEmpresa;
			Query q = this.session.createQuery(query);
			List listado = q.list();

			if ( listado.size() == 0 ) {
				return null;
			}

			int[] idCajas = new int[listado.size()];
			int idCaja = 0;

			for (int i=0 ; i < listado.size() ; i++) {
				idCaja = ((Integer) listado.get(i)).intValue();
				idCajas[i] = idCaja; 
			}

			return idCajas;
			
		} catch (Exception e) {
			log.error("Ha ocurrido la siguiente excepcion en CargaFamiliarDAO:getCajasEnCargasFamiliares::" + e);
			throw new DaoException("getCajasEnCargasFamiliares: ", e);
		}
	}
	/**
	 * busca en DB2 la nomina asociada al tipo de nomina, empresa y convenio indicado
	 * 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return retorna nomina encontrada, o null si no existe
	 * @throws DaoException
	 */
	public NominaVO getNomina(String tipoNomina, long idEmpresa, int idConvenio) throws DaoException
	{
		NominaVO n = null;
		if (tipoNomina.equals("R") || tipoNomina.equals("r"))
			n = new NominaREVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("A") || tipoNomina.equals("a"))
			n = new NominaRAVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("G") || tipoNomina.equals("g"))
			n = new NominaGRVO(idConvenio, (int) idEmpresa);
		else
			n = new NominaDCVO(idConvenio, (int) idEmpresa);

		try
		{
			Object o = this.session.get(n.getClass(), n);
			if (o != null)
				return (NominaVO) o;
			return null;
		} catch (Exception ex)
		{
			log.error("Ha ocurrido una excepcion en NominaDAO:getNomina:", ex);
			throw new DaoException("No se encontro la nomina: " + tipoNomina + ", rut: " + idEmpresa + ", convenio: " + idConvenio);
		}
	}
	/**
	 * actualiza los tipo pago de la seccion e detalle seccion en 1 (pagado) y elimina los datos de las tablas seccionDNP, detalleSeccionDNP
	 * 
	 * @author gmallea
	 *  
	 * @param long codBarra
	 */
	public void actualizaTipoPagoSecciones(long codBarra){
		
		
		Query queryDetalle = this.session.createQuery("UPDATE " + DetalleSeccionVO.class.getName() + " s SET s.tipoPago = ? "
						+ "WHERE s.idCodigoBarra = ? ");
		queryDetalle.setInteger(0, 1);
		queryDetalle.setLong(1, codBarra);
		queryDetalle.executeUpdate();
		this.session.flush();
		
		Query query = this.session.createQuery("UPDATE " + SeccionVO.class.getName() + " s SET s.tipoPago = ? "
					+ "WHERE s.idCodigoBarra = ? ");
		query.setInteger(0, 1);
		query.setLong(1, codBarra);
		query.executeUpdate();
		this.session.flush();
		
		Query queryDetalleDNP = this.session.createQuery("DELETE FROM " + DetalleSeccionDNPVO.class.getName() + " WHERE idCodigoBarra = ? ");
		queryDetalleDNP.setLong(0, codBarra);
		queryDetalleDNP.executeUpdate();
		this.session.flush();
		
		Query queryDNP = this.session.createQuery("DELETE FROM " + SeccionDNPVO.class.getName() + " WHERE idCodigoBarra = ? ");
		queryDNP.setLong(0, codBarra);
		queryDNP.executeUpdate();
		this.session.flush();
		
		
	}
}