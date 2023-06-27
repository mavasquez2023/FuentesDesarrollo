package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;

public class VN160 extends Validacion
{
	private static Logger log = Logger.getLogger(VN160.class);
	/*
	 * 1 parametro = VN160: codigo de mov de personal, requiere: VN150
	 * 
	 * Mensajes
	 * 		124: Codigo movimiento personal invalido
	 * 		125: Codigo movimiento personal no corresponde a valores posibles
	 * 		314: Debe existir movto personal pues dias trabajados < dias mes
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
						
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String codigo = Utils.transforma(c.getValor());
			if (cotizante.isAfpVoluntario())
				return validaReforma(cotizante, codigo, false);
			return valida(cotizante, codigo, false);

		} catch(Exception e)
		{
			log.error("error validacion VN160::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int valida(CotizanteVO cotizante, String codigo, boolean web)
	{
		int diasTrabajados = cotizante.getNumDiasTrabajados();
		int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
			
		if (this.logear)
			log.info("validacion VN160 valida NO Reforma::codigo:" + codigo + ":diasTrabajados:" + diasTrabajados + "::" + diasXMes + "::");
		/* Claudio 
		 * try
		{
			if (codigo.equals(""))
			{
				if (diasTrabajados < diasXMes)
				{
					if (this.logear)
						log.info("validacion VN160 ERR: diasTrabajados(" + diasTrabajados + ") < diasXMes(" + diasXMes + "), tipoMovtoPersonal vacio, debe existir!:valor recibido:" + codigo + "::");
					return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
				}
				return this.COD_CUMPLE_VALIDACION;
//
			} else if (Integer.parseInt(codigo.trim()) == 0 && diasTrabajados < diasXMes)
			{
				if (this.logear)
					log.info("validacion VN160 ERR: diasTrabajados < diasXMes, tipoMovtoPersonal debe existir::");
				return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
			}
			
		} catch(Exception ex)
		{
			if (this.logear)
				log.info("validacion VN160 ERR: codigo:" + codigo + "::");
			return 124; //MOVTO PERSONAL INVALIDO
		}
		*/
		MapeoVO mtm;
    	       	
		if(web)
		{
			Criteria crit = this.session.createCriteria(MapeoTipoMovtoVO.class);
	    	crit.add(Restrictions.eq("idMapaCod", new Integer(this.datosConvenio.getIdMapaCod())));
	    	List result = crit.add(Restrictions.eq("id", new Integer(codigo))).list();
	    	if (result == null || result.size() == 0)
	    	{
				if (this.logear)
					log.info("validacion VN160 ERR:tipoMovtoPersonal no se encontro:" + codigo + "::");
	    		return 125; //Codigo movimiento personal no corresponde a valores posibles
	    	}
	    	mtm = (MapeoVO) result.get(0);
		} else
		{
	
	    	if (!this.mapeoMvto.containsKey(codigo.trim()) && !(codigo.equals("")) && !(codigo.equals("0")) )
	    	{
				if (this.logear)
					log.info("validacion VN160 ERR:tipoMovtoPersonal no se encontro:" + codigo + "::");
	    		return 125; //Codigo movimiento personal no corresponde a valores posibles
	    	}
	    	
	    	mtm = (MapeoVO)this.mapeoMvto.get(codigo.trim());
		}

		//if (diasTrabajados < diasXMes  && (mtm == null || mtm.getId() == 0) )
		if (diasTrabajados < diasXMes  && mtm == null && !codigo.trim().equals("0") )
		{
			if (this.logear)
				log.info("validacion VN160 ERR: diasTrabajados < diasXMes, tipoMovtoPersonal vacio, debe existir!:valor recibido:" + codigo + "::");
			return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
		}

		if (mtm !=null)
		{		
			cotizante.addMovimientoPersonal(mtm.getId());
		}
		
		if (this.logear)
			log.info("validacion VN160 OK:tipoMovtoPersonal:" + mtm.getId() + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaReforma(CotizanteVO cotizante, String codigo, boolean web)
	{
		int diasTrabajados = cotizante.getNumDiasTrabajados();
		int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		if (this.logear)
			log.info("validacion VN160 valida Reforma::codigo:" + codigo + ":diasTrabajados:" + diasTrabajados + "::" + diasXMes + "::");
		try{
			if (codigo.equals(""))
			{
				if (diasTrabajados < diasXMes)
				{
					if (this.logear)
						log.info("validacion VN160 ERR: diasTrabajados("+diasTrabajados+") < diasXMes("+diasXMes+"), tipoMovtoPersonalAF vacio, debe existir!:valor recibido:" + codigo + "::");
					return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
				}
				return this.COD_CUMPLE_VALIDACION;
			} else if (Integer.parseInt(codigo.trim()) == 0 && diasTrabajados < diasXMes)
			{
				if (this.logear)
					log.info("validacion VN160 ERR: diasTrabajados < diasXMes, tipoMovtoPersonalAF debe existir::");
				return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
			}
		} catch(Exception ex)
		{
			if (this.logear)
				log.info("validacion VN160AF ERR: codigo:"+codigo+"::");
			return 124; //MOVTO PERSONAL INVALIDO
		}
		MapeoVO mtm;
		if(web)
		{
			Criteria crit = this.session.createCriteria(MapeoTipoMovtoAFVO.class);
	    	crit.add(Restrictions.eq("idMapaCod", new Integer(this.datosConvenio.getIdMapaCod())));
	    	List result = crit.add(Restrictions.eq("codigo", codigo)).list();
	    	if (result == null || result.size() == 0)
	    	{
				if (this.logear)
					log.info("validacion VN160 ERR:tipoMovtoPersonal no se encontro:" + codigo + "::");
	    		return 125; //Codigo movimiento personal no corresponde a valores posibles
	    	}
	    	mtm = (MapeoVO) result.get(0);
		} else
		{
	    	if (!this.mapeoMvtoAf.containsKey(codigo.trim()))
	    	{
				if (this.logear)
					log.info("validacion VN160 ERR:tipoMovtoPersonalAf no se encontro:" + codigo + "::");
	    		return 125; //Codigo movimiento personal no corresponde a valores posibles
	    	}
	    	mtm = (MapeoVO)this.mapeoMvtoAf.get(codigo.trim());
		}

		if (diasTrabajados < diasXMes && mtm.getId() == 0)
		{
			if (this.logear)
				log.info("validacion VN160AF ERR: diasTrabajados < diasXMes, tipoMovtoPersonalAF vacio, debe existir!:valor recibido:" + codigo + "::");
			return 314; //DEBE EXISTIR MOVTO PERSONAL PUES DIAS TRABAJADOS < DIAS MES
		}

		cotizante.addMovimientoPersonalAF(mtm.getId());
		if (this.logear)
			log.info("validacion VN160 OK:tipoMovtoPersonalAF:" + mtm.getId() + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}
	
	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			if (cotizante.isAfpVoluntario())
			{
				MvtoPersoAFVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPersAF();
				if (this.session.get(TipoMvtoPersoAFVO.class, new Integer(mp.getIdTipoMovReal())) == null)			
				{
					log.info("validacion VN160 ERROR REFORMA: IdTipoMovto no encontrado:" + mp.getIdTipoMovReal() + "::");
				  	return 125; //Codigo movimiento personal no corresponde a valores posibles
				}
				return validaReforma(cotizante, String.valueOf(mp.getIdTipoMovReal()), true);
			}
			MovtoPersonalVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPers();
			if(mp == null){
				log.info("validacion VN160 ERROR IdTipoMovto no encontrado: mp=null::");
			  	return 125; //Codigo movimiento personal no corresponde a valores posibles
			}
			if (this.session.get(TipoMovimientoPersonalVO.class, new Integer(mp.getIdTipoMovReal())) == null)			
			{
				log.info("validacion VN160 ERROR IdTipoMovto no encontrado:" + mp.getIdTipoMovReal() + "::");
			  	return 125; //Codigo movimiento personal no corresponde a valores posibles
			}
			return valida(cotizante, String.valueOf(mp.getIdTipoMovReal()), true);
		} catch(Exception e)
		{
			log.error("error validacion VN160::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN160(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN160(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN160(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
