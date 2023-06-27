package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import cl.araucana.cp.hibernate.dao.CreditoDAO;


/*
 * @(#) CotizanteMgr.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.21
 */
public class ProdCajaMgr
{
	private static Logger logger = Logger.getLogger(ProdCajaMgr.class);
	private CreditoDAO creditoDao;
	private Session session;

	public ProdCajaMgr(Session session)
	{
		this.creditoDao = new CreditoDAO(session);
		this.session = session;
	}
	
	
	/**
	 * trae deudores Crédito
	 * 
	 * @param idEmpresa
	 * @param periodo
	 * @return
	 * @throws DaoException
	 */
	public Collection getDeudoresNomina(int idEmpresa, int periodo, int tipo_empresa, String opciones) throws DaoException
	{
		List deudores=new ArrayList();
		List ahorrantes=new ArrayList();
		HashMap lista= new HashMap();
		logger.info("Obteniendo registros empresa:" + idEmpresa + ", periodo:" + periodo);
		if(opciones.indexOf("C")>-1){
			deudores= this.creditoDao.getListaDeudores(idEmpresa, periodo, tipo_empresa);
			logger.info("Total deudores:" + deudores.size());
		}
		if(opciones.indexOf("L")>-1){
			ahorrantes= this.creditoDao.getListaAhorrantes(idEmpresa, periodo, tipo_empresa);
			logger.info("Total ahorrantes:" + ahorrantes.size());
		}
		for (Iterator iter = deudores.iterator(); iter.hasNext();) {
			Object[] deudor = (Object[]) iter.next();
			lista.put(deudor[0], deudor);
		}
		for (Iterator iter = ahorrantes.iterator(); iter.hasNext();) {
			Object[] ahorrante = (Object[]) iter.next();
			Object[] deudor= (Object[])lista.get(ahorrante[0]);
			if(deudor!=null){
				//Se traspasa cuota leasing si ya existía registro
				deudor[3]= ahorrante[3];
			}else{
				lista.put(ahorrante[0], ahorrante);
			}
		}
		logger.info("Total registros: " + lista.size());
		return lista.values();
	}

}