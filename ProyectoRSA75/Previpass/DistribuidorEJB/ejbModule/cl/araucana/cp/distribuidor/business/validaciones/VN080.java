package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN080 extends Validacion
{
	private static Logger log = Logger.getLogger(VN080.class);
	/*
	 * 1 parametro = VN080: Entidad de salud
	 * 
	 * Mensajes
	 * 		114: Codigo Salud invalido no aparece en mapeo
	 * 		113: Codigo Salud invalido  
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'R' && cotizante.isAfpVoluntario())
			{
				this.resultado = "F";
				cotizante.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
				return this.COD_CUMPLE_VALIDACION;
			}
			this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String valor = Utils.transformaCodEnt(c.getValor() != null ? c.getValor().toUpperCase() : null);
			if (valor.equals(""))
			{
        		if (this.logear)
					log.info("validacion VN080 ERR: Codigo salud invalido:" + valor + "::");
        		cotizante.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
    			cotizante.setIdEntSalud(valor);
    			if(c.getObligatorio()==0){
					return this.COD_CUMPLE_VALIDACION;
    			}else{
    				return 113; //Codigo Salud invalido
    			}
			}

			if (!this.mapeoSalud.containsKey(valor))
        	{
        		if (this.logear)
					log.info("validacion VN080 ERR: Codigo salud no aparece en mapeo:"+ valor + "::");
        		cotizante.setIdEntSaludReal(Constants.ID_FONASA);
    			cotizante.setIdEntSalud(valor);
        		return 114; //Codigo Salud invalido no aparece en mapeo
        	}
        	MapeoVO mp = (MapeoVO)this.mapeoSalud.get(valor);

			cotizante.setIdEntSaludReal(mp.getId());
			cotizante.setIdEntSalud(valor);
			this.resultado = "";
			if (mp.getId() == Constants.ID_FONASA) {
				cotizante.setIsapre(false);
				cotizante.setIdEntSaludReal(Constants.ID_FONASA);
				cotizante.setIdEntSalud(valor);
			} else {
				cotizante.setIsapre(true);
			}
			if (this.logear)
				log.info("validacion VN080 OK:IdEntSaludReal:" + mp.getId() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN080::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'R' && cotizante.isAfpVoluntario())
			{
				this.resultado = "F";
				cotizante.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
				return this.COD_CUMPLE_VALIDACION;
			}
			this.resultado = "E";
			this.mensaje = "idEntidadSalud";
			if (this.session.get(EntidadSaludVO.class, new Integer(cotizante.getIdEntSaludReal())) == null)
			{
				log.info("validacion VN080 ERROR getIdEntSaludReal no encontrada:" + cotizante.getIdEntSaludReal() + "::");
			  	return 113;//Codigo Salud invalido
			}
			this.resultado = "";
			if (cotizante.getIdEntSaludReal() == Constants.ID_FONASA)
				cotizante.setIsapre(false);
			else
				cotizante.setIsapre(true);

			if (this.logear)
				log.info("validacion VN080 OK:IdEntSaludReal:" + cotizante.getIdEntSaludReal() + "::");
			this.mensaje = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN080::", e);
			return this.CAIDA_SISTEMA;
		}
		
	}

	public VN080(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN080(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN080(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
