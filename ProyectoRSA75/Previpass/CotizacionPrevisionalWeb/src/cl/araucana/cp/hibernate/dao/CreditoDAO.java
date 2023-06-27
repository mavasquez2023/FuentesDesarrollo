package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.AF02F1EEPPVO;
import cl.araucana.cp.hibernate.beans.AF02F1VO;
import cl.araucana.cp.hibernate.beans.RCF300VO;
import cl.araucana.cp.hibernate.beans.RCF310VO;
import cl.araucana.cp.hibernate.beans.T0031VO;
import cl.araucana.cp.hibernate.beans.T0032VO;

/*
 * @(#) CotizanteDao.java 1.16 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.16
 */
public class CreditoDAO
{
	private static Logger log = Logger.getLogger(CreditoDAO.class);
	private Session session;

	public CreditoDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param perodo
	 * @return List
	 * @throws DaoException
	 */
	public List getListaDeudores(int idEmpresa, int periodo, int tipo_empresa) throws DaoException
	{
		try
		{
			log.info("CreditoDAO:getListaDeudores:idEmpresa" + idEmpresa + ":periodo:" + periodo + "::");
			StringBuffer sb = new StringBuffer();
			//CREDITO
			sb.append("SELECT d.rutDeudor as RUT, d.dvRutDeudor as DV, ");
			sb.append("sum(d.valorCuota +  d.nomtoGravamenes) AS VALOR_CUOTA_CREDITO, 0 AS VALOR_CUOTA_LEASING, ");
			sb.append("coalesce(afi.nombreAfiliado, '') as NOMBRE, ");
			sb.append("coalesce(trim(afi.apellidoAfiliado), '') || ' ' || coalesce(trim(afi.apellidoMaterno), '') as APELLIDOS, ");
			sb.append("coalesce(afi.sexoAfiliado, 'M') as SEXO ");
			sb.append("FROM " +  RCF300VO.class.getName() + " as c, ");
			sb.append( RCF310VO.class.getName() + " as d, ");
			if(tipo_empresa==1){
				//publica
				sb.append( AF02F1EEPPVO.class.getName() + " as afi ");
			}else{
				//privada
				sb.append( AF02F1VO.class.getName() + " as afi ");
			}
			sb.append("WHERE c.fechaVencimiento = :fecha ");
			sb.append("AND c.rutEmpresa= :rut " );
			sb.append("AND c.folioNomina = d.folioNomina ");
			sb.append("AND afi.rutAfiliado = d.rutDeudor ");
			sb.append("GROUP BY d.rutDeudor, d.dvRutDeudor, ");
			sb.append("afi.nombreAfiliado, afi.apellidoAfiliado, afi.apellidoMaterno, afi.sexoAfiliado ");			
			sb.append("ORDER  BY 1, 3 ");
			
			log.info("query:" + sb);
			//List params = new ArrayList();
			//params.add("fecha", periodo + "30");
			//params.add(new Integer(idEmpresa));
			
			//System.out.println("params size:" + params.size());
			Query q = this.session.createQuery(sb.toString());
			q.setParameter("fecha", periodo + getUltimoDia(periodo));
			log.info("parametro1:" + periodo + getUltimoDia(periodo));
			q.setParameter("rut", new Integer(idEmpresa));
			log.info("parametro2:" + idEmpresa);
			//int par = 0;
			//for (Iterator iter = params.iterator(); iter.hasNext();)
			//	q.setParameter(par++, iter.next());

			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CreditoDAO:getListaDeudores", ex);
			ex.printStackTrace();
			throw new DaoException("CreditoDAO:getListaDeudores idEmpresa: " + idEmpresa + ", periodo: " + periodo , ex);
		}
	}
	
	/**
	 * 
	 * @param idEmpresa
	 * @param perodo
	 * @return List
	 * @throws DaoException
	 */
	public List getListaAhorrantes(int idEmpresa, int periodo, int tipo_empresa) throws DaoException
	{
		try
		{
			log.info("CreditoDAO:getListaAhorrantes:idEmpresa" + idEmpresa + ":periodo:" + periodo + "::");
			StringBuffer sb = new StringBuffer();
			
			//LEASING
			sb.append("SELECT d.rutAhorrante AS RUT, afi.dvRutAfiliado as DV, ");
			sb.append("0 as VALOR_CUOTA_CREDITO, sum(d.valorCuota) AS VALOR_CUOTA_LEASING, ");
			sb.append("coalesce(afi.nombreAfiliado, '') as NOMBRE, ");
			sb.append("coalesce(trim(afi.apellidoAfiliado), '') || ' ' || coalesce(trim(afi.apellidoMaterno), '') as APELLIDOS, ");
			sb.append("coalesce(afi.sexoAfiliado, 'M') as SEXO ");
			sb.append("FROM " +  T0031VO.class.getName() + " as c, ");
			sb.append( T0032VO.class.getName() + " as d, ");
			if(tipo_empresa==1){
				//publica
				sb.append( AF02F1EEPPVO.class.getName() + " as afi ");
			}else{
				//privada
				sb.append( AF02F1VO.class.getName() + " as afi ");
			}
			sb.append("WHERE c.fechaVencimiento = :fecha ");
			sb.append("AND c.rutEmpresa= :rut " );
			sb.append("AND c.folioNomina = d.folioNomina ");
			sb.append("AND afi.rutAfiliado = d.rutAhorrante ");
			sb.append("GROUP BY d.rutAhorrante, afi.dvRutAfiliado, ");
			sb.append("afi.nombreAfiliado, afi.apellidoAfiliado, afi.apellidoMaterno, afi.sexoAfiliado ");
			sb.append("ORDER  BY 1, 3 ");
			
			log.info("query:" + sb);
			//List params = new ArrayList();
			//params.add("fecha", periodo + "30");
			//params.add(new Integer(idEmpresa));
			
			//System.out.println("params size:" + params.size());
			Query q = this.session.createQuery(sb.toString());
			q.setParameter("fecha", periodo + getUltimoDia(periodo));
			log.info("parametro1:" + periodo + getUltimoDia(periodo));
			q.setParameter("rut", new Integer(idEmpresa));
			log.info("parametro2:" + idEmpresa);
			//int par = 0;
			//for (Iterator iter = params.iterator(); iter.hasNext();)
			//	q.setParameter(par++, iter.next());

			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CreditoDAO:getListaDeudores", ex);
			ex.printStackTrace();
			throw new DaoException("CreditoDAO:getListaDeudores idEmpresa: " + idEmpresa + ", periodo: " + periodo , ex);
		}
	}
	
	/**
	 * entra mmyyyy y devuelve una fecha equivalente al último día del mes (mm) del año yyyy
	 * @param mmyyyy
	 * @return
	 */
	private String getUltimoDia(int yyyymm){
		
		String dia="00";
		try{
			int mes = Integer.parseInt(String.valueOf(yyyymm).substring(4, 6));
			int year= Integer.parseInt(String.valueOf(yyyymm).substring(0, 4));
			switch (mes) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dia= "31";
				break;
			case 2:
				dia= "28";
				if(isBisiestoYear(year)) dia= "29";					
				break;
			default:
				dia="30";
			break;
			}
			
		}catch (Exception e) {
			System.out.println("Error getUltimoDia, periodo:" + yyyymm);
		}
		return dia;
	}
	
	public static boolean isBisiestoYear(double anio){
		try{
			double r = (anio/4) - Math.round(anio/4);
			if(r == 0)
				return true;
			else
				return false;
		}catch( Exception e) {
			System.out.println("CAI EN VALIDAR AÑO BISIESTO");        	
			e.printStackTrace();
			return false;
		}
	}
}