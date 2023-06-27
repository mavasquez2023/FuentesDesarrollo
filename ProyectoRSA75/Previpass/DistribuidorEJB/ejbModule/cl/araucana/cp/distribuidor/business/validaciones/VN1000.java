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

public class VN1000 extends Validacion
{
	private static Logger log = Logger.getLogger(VN680.class);

	/*
	 * 1 parametro = VN1000: id entidad AFP afiliacion voluntaria
	 * 
	 * Mensajes
	 * 		169: Codigo Entidad de Ahorro Previsional Voluntario invalido
	 * 		170: Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo

	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String codigo = Utils.transformaCodEnt(c.getValor());

			if (codigo.equals(""))//no hay valor, no se valida nada mas
				cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
			else
			{
	        	if (!this.mapeoPension.containsKey(codigo.trim()))
	        		cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
	        		//return 170;//	Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo
	        	else
	        	{
		        	MapeoVO mp = (MapeoVO)this.mapeoPension.get(codigo.trim());
					cotizante.setIdEntidadAFPVReal(mp.getId());
					cotizante.setIdEntidadAFPV(codigo);
	        	}
			}
			this.resultado = "F";
			if (this.logear)
				log.info("validacion VN1000 OK:IdEntidadAFPVReal:" + cotizante.getIdEntPensionReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN1000::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";

			if (cotizante.getIdEntidadAFPVReal() == -1)
				cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
			else if(this.session.get(EntidadPensionVO.class, new Integer(cotizante.getIdEntidadAFPVReal())) == null)
				cotizante.setIdEntidadAFPVReal(Constants.ID_AFP_NINGUNA);
				//return 169;//	Codigo Entidad de Ahorro Previsional Voluntario invalido
			this.resultado = "F";
			if (this.logear)
				log.info("validacion VN1000 OK:IdEntidadAFPVReal:" + cotizante.getIdEntidadAFPVReal() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN1000::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN1000(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1000(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1000(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
