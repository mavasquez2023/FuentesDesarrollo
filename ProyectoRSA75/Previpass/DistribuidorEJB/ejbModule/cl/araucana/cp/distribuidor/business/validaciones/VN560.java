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
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN560 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN560.class);
	/*
	 * 1 parametro = VN560: codigo AFP para afiliado a INP (AFC)
	 * 
	 * Mensajes	 
	 * 		182: Codigo AFC invalido
	 * 		183: Codigo AFC no aparece en mapeo
	 * 		333: Codigo AFC no corresponde
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.parametros == null || this.parametros.size() != 1)
			{
				if(this.logear) log.info("validacion VN560: Sin Conceptos::");
				return this.SIN_CONCEPTOS;
			}				

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String codigo = Utils.transformaCodEnt(c.getValor() != null ? c.getValor().toUpperCase() : null);

			int idAFC = Constants.AFC_FALSO;
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)//solo si es INP, si no, no lo considera
			{
				if (codigo.equals(""))//no hay valor, es INP por lo que deberia informar
				{
					cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
					
					// marco 
					/*if(this.logear) log.info("validacion VN560 ERR: Sin valor. Es INP por lo que tiene que informar AFC::");
					return 182; //Codigo AFC invalido*/
				}

				// marco 
				//if(!this.mapeoPension.containsKey(codigo.trim()) 
				if(!this.mapeoPension.containsKey(codigo.trim()) && !(codigo.equals("")) )
	        	{
					if (this.logear)
						log.info("validacion VN560 ERR:Codigo AFC no aparece en mapeo::");
	        		cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
	    			cotizante.setIdEntAfc(codigo);
	        		return 183;//Codigo AFC no aparece en mapeo
	        	}
				// marco 
				if(this.mapeoPension.containsKey(codigo.trim()) && !(codigo.equals("")) ) 
				{
					MapeoVO mp = (MapeoVO)this.mapeoPension.get(codigo.trim());
					cotizante.setIdEntAfcReal(mp.getId());
					cotizante.setIdEntAfc(codigo);
					idAFC = mp.getId();
				}
			}

			//Se agrega lógica por problema a la hora de calcular Seguro de Cesantía para trabajadores sin fondo de pensión, pero que informan cesantía. 
			else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA && !codigo.equals("")) {
				MapeoVO mp = (MapeoVO)this.mapeoPension.get(codigo.trim());
				cotizante.setIdEntAfcReal(mp.getId());
				cotizante.setIdEntAfc(codigo);
				idAFC = mp.getId();				
			}

			else
			{
				cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
				//Asepulveda
				/*if (!codigo.equals(""))
				{
					if(this.logear) log.info("validacion VN560 ERR: con valor. no debe informar AFC si no es INP::");
					return 333; //Codigo AFC no corresponde
				}*/					
			}
			if (this.logear)
				log.info("validacion VN560 OK:IdEntAfc:" + idAFC + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN560::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.logear)
				log.info("validacion VN560 IdEntAfc recibido WEB:" + cotizante.getIdEntAfcReal() + "::");
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP && this.session.get(EntidadPensionVO.class, new Integer(cotizante.getIdEntAfcReal())) == null)
			{
				if (this.logear) log.info("validacion VN560 ERROR IdEntAfcReal no encontrada:" + cotizante.getIdEntAfcReal() + "::");
			  	//return 183;//Codigo Prevision no aparece en mapeo
			}
			if(cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP)
				//jlandero 26/08/2010
				//cotizante.setIdEntAfcReal(Constants.AFC_FALSO);
			if (this.logear)
				log.info("validacion VN560 OK:IdEntAfc:" + cotizante.getIdEntAfcReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN560::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN560(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN560(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN560(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
