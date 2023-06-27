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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN090 extends Validacion
{
	private static Logger log = Logger.getLogger(VN090.class);	
	/*
	 * 1 parametro = VN090: entidad exCaja. requerido solo si VN070 es INP, para cualquier tipo de nomina
	 * 
	 * Mensajes
	 * 		115: Codigo de Entidad Ex-Caja invalido
	 * 		331: Codigo excaja no Corresponde por estar asociado a afp
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
			{
				if(this.logear) log.info("validacion VN090: Sin Conceptos::");
				return this.SIN_CONCEPTOS;
			}

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int idEnt = asignaValor(c.getValor());
			if (idEnt == Constants.EXCAJA_FALSO && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)
			{
				if (this.logear)
					log.info("validacion VN090 ERR:idEntidadExCaja incorrecto:valor recibido:" + idEnt + "::");
				cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
	    		return 115;//Codigo de Entidad Ex-Caja invalido
			}

			return valida(idEnt, cotizante, false);
		} catch(Exception e)
		{
			log.error("error validacion VN090::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int idEnt, CotizanteVO cotizante, boolean web) 
	{		
		if(web)
		{
			List result = this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("id", new Integer(idEnt))).list();
			if (result == null || result.size() == 0)
			{
				if (this.logear)
					log.info("validacion VN090 ERR:idEntidadExCaja incorrecto:valor recibido:" + idEnt + "::");
				cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
	    		return 115; //Codigo de Entidad Ex-Caja invalido
			}
		} else 
		{
			if (!this.mapeoCcaf.containsKey(String.valueOf(idEnt)))
			{
				if (this.logear)
					log.info("validacion VN090 ERR:idEntidadExCaja incorrecto:valor recibido:" + idEnt + "::");
				cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
	    		return 115; //Codigo de Entidad Ex-Caja invalido
			}
		}
		
		this.resultado = "";
		cotizante.setIdEntExCaja(idEnt);
		
		if(cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
		{
			if(idEnt != Constants.EXCAJA_FALSO)
			{
				GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");				
				
				if(!grupoConvenioVO.isPrevired()){
					if (this.logear)
						log.info("validacion VN090 ERR:idEntidadExCaja no corresponde al estar asociado a AFP::");
		    		return 331; //Codigo de Entidad Ex-Caja no corresponde
				}
			}
		}
		if (this.logear)
			log.info("validacion VN090 OK:IdEntExCaja:" + idEnt + "::");
				
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "E";
			int idEnt = cotizante.getIdEntExCaja();
			if(idEnt == -1) idEnt = Constants.EXCAJA_FALSO;
			if (idEnt == Constants.EXCAJA_FALSO && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)
			{
				if (this.logear)
					log.info("validacion VN090 ERR:idEntidadExCaja incorrecto:valor recibido:" + idEnt + "::");
				cotizante.setIdEntExCaja(Constants.EXCAJA_FALSO);
	    		return 115;//Codigo de Entidad Ex-Caja invalido
			}

			return valida(idEnt, cotizante, true);
		} catch(Exception e)
		{
			log.error("error validacion VN090::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN090(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN090(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN090(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
