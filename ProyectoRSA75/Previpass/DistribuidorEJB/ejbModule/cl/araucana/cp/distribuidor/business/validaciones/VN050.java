package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN050 extends Validacion 
{
	private static Logger log = Logger.getLogger(VN050.class);
	/*
	 * 1 parametro = VN050: Genero trabajador
	 * 
	 * Mensajes
	 *		108: Genero no valido
	 *      317: Genero no aparece en mapeo
	 * 
	 */
	public int valida(CotizanteVO cotizante) 
	{
		try
		{
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			String valor = Utils.transforma(c.getValor() != null ? c.getValor().toUpperCase() : null);
			if (valor.equals(""))
			{
				if (this.logear)
					log.info("validacion VN050 ERR: Genero: valor recibido invalido:" + valor + "::");
				//cotizante.setIdGeneroReal(Constants.GENERO_INVALIDO); //GENERO_FALSO);
				return 108; //Genero no valido
			}

			if (!this.mapeoGeneros.containsKey(valor))
			{
				if (this.logear)
					log.info("validacion VN050 ERR: Genero:valor recibido:" + valor + "::");
				//cotizante.setIdGeneroReal(Constants.GENERO_INVALIDO); //GENERO_FALSO);
				return 317; //Genero no aparece en mapeo
			}
			
			MapeoVO mg = (MapeoVO)this.mapeoGeneros.get(valor);
			cotizante.setIdGeneroReal(mg.getId());
			if (this.logear)
				log.info("validacion VN050 OK: Genero:" + cotizante.getIdGeneroReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN050::", e);
			return this.CAIDA_SISTEMA;
		}		
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.session.get(GeneroVO.class, new Integer(cotizante.getIdGeneroReal())) == null) 
			{
				if (this.logear)
					log.info("validacion VN050 ERR: Genero:valor recibido:" + cotizante.getIdGeneroReal() + "::");
				//cotizante.setIdGeneroReal(Constants.GENERO_INVALIDO); //GENERO_FALSO);
				return 317; //Genero no aparece en mapeovalido
			} 
			if (this.logear)
				log.info("validacion VN050 OK: Genero:" + cotizante.getIdGeneroReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN050::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN050(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN050(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN050(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
