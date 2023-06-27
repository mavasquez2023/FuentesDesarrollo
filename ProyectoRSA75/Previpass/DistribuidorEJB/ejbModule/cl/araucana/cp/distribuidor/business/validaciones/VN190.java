package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;

public class VN190 extends Validacion
{
	private static Logger log = Logger.getLogger(VN190.class);
	/*
	 * 1 parametro = VN190: rut entidad SIL, requiere: VN160
	 * 
	 * Mensajes
	 * 		129: Codigo de Movimiento es 3 por lo que este valor debe existir
	 * 		209: RUT Entidad Pagadora de Subsidio invalido
	 * 		128: RUT Entidad Pagadora de Subsidio no aparece en tabla SIL
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String rutIdPagadora = RutValidacion.transformConSinGuion(c.getValor().trim());
			return valida(rutIdPagadora, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN190::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(String rutIdPagadora, String valor, CotizanteVO cotizante) 
	{
		if(cotizante.getIdEntSil() == -1)
			cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
		CotizacionREVO cotREVO = (CotizacionREVO) cotizante.getCotizacion();
		if(!cotizante.isAfpVoluntario())
		{
			if (this.logear)
				log.info("validando entidad SIL para NO voluntario");
			MovtoPersonalVO mp = cotREVO.getMovtoPers();
			if (mp == null)
				return this.COD_CUMPLE_VALIDACION;

			if (mp.getIdTipoMovReal() == Constants.TIPO_MOVTO_SIL)
			{
				if (rutIdPagadora.equals(""))
				{
		        	if (this.logear)
						log.info("validacion VN190 ERR:idEntidadSil: debe venir, tipo movimiento == 3. recibido:" + valor + "::");
		        	cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
					return 129; //Codigo de Movimiento es 3 por lo que este valor debe existir
				}
				if (!this.mapeoSil.containsKey(String.valueOf(rutIdPagadora)))
				{
					if (this.logear)
						log.info("validacion VN190 ERR: getIdEntSil no encontrada:valor recibido:" + rutIdPagadora + "::");
					cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
					return  128; //RUT Entidad Pagadora de Subsidio no aparece en tabla SIL
				}

				EntidadSilVO entidadSil = (EntidadSilVO)this.mapeoSil.get(String.valueOf(String.valueOf(rutIdPagadora)));
				cotizante.setIdEntSil(entidadSil.getId());
			} 
				
			if (!rutIdPagadora.equals("") && mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_SIL)
			{
				//TODO GMALLEA 20-04-2012 Si el rutIdPagadora tiene el guion, obtenimos el rut sin guion y dv. en el caso si no tiene guion se saca el ultimo valor..
				int guion =  rutIdPagadora.indexOf("-");
				String rut =  rutIdPagadora.substring(0, guion != -1 ? guion : rutIdPagadora.length()-1);
				int rutIdPagadoraNum =  Integer.parseInt(rut);
				//20-04-2012 GMALLEA Si es 0 no biene el dato en la nomina
				if(rutIdPagadoraNum != 0){
					if (this.logear)
							log.info("validacion VN190 ERR: getIdEntSil no corresponde::");
						return  343; //no corresponde entidad SIL
				}
			}
			if (this.logear)
				log.info("validacion VN190 OK:idEntidadSil:" + cotizante.getIdEntSil() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		}

		if (this.logear)
			log.info("validando entidad SIL para voluntario");
		MvtoPersoAFVO mp = cotREVO.getMovtoPersAF();
		if (mp == null)
			return this.COD_CUMPLE_VALIDACION;

		if(mp.getIdTipoMovReal() == Constants.TIPO_MOVTOAF_SIL)
		{
			if (rutIdPagadora.equals(""))
			{
				if (this.logear)
					log.info("validacion VN190 ERR:idEntidadSil: debe venir, tipo movimiento == 2. recibido:" + valor + "::");
	        	cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
				return 342; //Codigo de Movimiento es 2 por lo que este valor debe existir			
			}
			List result = this.session.createCriteria(EntidadSilVO.class).add(Restrictions.eq("idEntPagadora", new Integer(rutIdPagadora))).list();
			if (result == null || result.size() == 0)
			{
				if (this.logear)
					log.info("validacion VN190 ERR: getIdEntSil no encontrada:valor recibido:" + rutIdPagadora + "::");
				cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
				return  128; //RUT Entidad Pagadora de Subsidio no aparece en tabla SIL
			}

			EntidadSilVO entidadSil = (EntidadSilVO)result.get(0);
			log.info("validacion VN190 OK:result:" + result.size() + "::");
			if (this.logear)
				log.info("\n\nvalidacion VN190 OK:asignando idEntidadSil:" + entidadSil.getId() + ":nombre entidad:" + entidadSil.getNombre() + "::");
			cotizante.setIdEntSil(entidadSil.getId());
		}
		if (!rutIdPagadora.equals("") && mp.getIdTipoMovReal() != Constants.TIPO_MOVTOAF_SIL)
		{
        	/*List result = this.session.createCriteria(EntidadSilVO.class).add(Restrictions.eq("idEntPagadora", new Integer(rutIdPagadora))).list();
			if (result == null || result.size() == 0)
			{
				if (this.logear)
					log.info("validacion VN190 ERR: getIdEntSil no encontrada:valor recibido:" + rutIdPagadora + "::");
				cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
				return  128; //RUT Entidad Pagadora de Subsidio no aparece en tabla SIL
			}

			EntidadSilVO entidadSil = (EntidadSilVO)result.get(0);
			cotizante.setIdEntSil(entidadSil.getId());
			if(cotizante.getIdEntSil() != Constants.ENTSIL_FALSO )
			{*/
				if (this.logear)
					log.info("validacion VN190 ERR: getIdEntSil no corresponde::");
				return  343; //no corresponde entidad SIL
			//}
		}
		if (this.logear)
			log.info("validacion VN190 OK:idEntidadSil:" + cotizante.getIdEntSil() + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if(cotizante.getIdEntSil() == -1)
				cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			if(!cotizante.isAfpVoluntario())
			{
				CotizacionREVO cotREVO = (CotizacionREVO) cotizante.getCotizacion();
				MovtoPersonalVO mp = cotREVO.getMovtoPers();
				if (this.logear)
					log.info("validando entidad SIL para NO voluntario");
	
				if (mp.getIdTipoMovReal() == Constants.TIPO_MOVTO_SIL)
				{
					if(String.valueOf(Constants.ENTSIL_FALSO).equals(mp.getIdTipoMov()))
					{
						if (this.logear)
							log.info("validacion VN190 ERR:idEntidadSil: debe venir, tipo movimiento == 3. recibido:" + cotizante.getIdEntSil() + "::");
						cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
						return 129; //Codigo de Movimiento es 3 por lo que este valor debe existir
					}
					if(this.session.get(EntidadSilVO.class, new Integer(String.valueOf(cotizante.getIdEntSil()))) == null)
					{
						if (this.logear)
							log.info("validacion VN190 ERR: getIdEntSil no encontrada:" + mp.getIdTipoMov() + "::");
						cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
						return 128; //RUT Entidad Pagadora de Subsidio no aparece en tabla SIL 
					}
				}
			/*	if(mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_SIL && Constants.ENTSIL_FALSO != cotizante.getIdEntSil())
				{
					if (this.logear)
						log.info("validacion VN190 ERR: getIdEntSil no corresponde:tipoMovto:" + mp.getIdTipoMov() + ":idEnt recibida:" + cotizante.getIdEntSil() + "::");
					return  343; //no corresponde entidad SIL
				}*/
			} else 
			{
				CotizacionREVO cotREVO = (CotizacionREVO) cotizante.getCotizacion();
				MvtoPersoAFVO mp = cotREVO.getMovtoPersAF();
				if (this.logear)
					log.info("validando entidad SIL para NO voluntario");
	
				if (mp.getIdTipoMovReal() == Constants.TIPO_MOVTOAF_SIL)
				{
					if(String.valueOf(Constants.ENTSIL_FALSO).equals(mp.getIdTipoMov()))
					{
						if (this.logear)
							log.info("validacion VN190 ERR:idEntidadSil: debe venir, tipo movimiento == 2. recibido:" + mp.getIdTipoMov() + "::");
			        	cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
						return 342; //Codigo de Movimiento es 2 por lo que este valor debe existir	
					}
					if(this.session.get(EntidadSilVO.class, new Integer(mp.getIdTipoMov())) == null)
					{
						if (this.logear)
							log.info("validacion VN190 ERR: getIdEntSil no encontrada:" + mp.getIdTipoMov() + "::");
						cotizante.setIdEntSil(Constants.ENTSIL_FALSO);
						return 128; //RUT Entidad Pagadora de Subsidio no aparece en tabla SIL 
					}
				}
				/*if(mp.getIdTipoMovReal() != Constants.TIPO_MOVTOAF_SIL && Constants.ENTSIL_FALSO != cotizante.getIdEntSil())
				{
					if (this.logear)
						log.info("validacion VN190 ERR: getIdEntSil no corresponde: tipoMovto:" + mp.getIdTipoMov() + ":idEnt recibida:" + cotizante.getIdEntSil() + "::");
					return  343; //no corresponde entidad SIL
				}*/
			}
			if (this.logear)
				log.info("validacion VN190 OK:idEntidadSil:" + cotizante.getIdEntSil() + "::");
			this.mensaje = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN190::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN190(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN190(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN190(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
