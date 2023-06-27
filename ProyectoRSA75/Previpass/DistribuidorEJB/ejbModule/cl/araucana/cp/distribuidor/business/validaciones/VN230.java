package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN230 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN230.class);
	/*
	 * 1 parametro = VN230: asignacion familiar reintegro
	 * 
	 * Mensajes
	 * 135: REINTEGROS ASIGNACION FAMILIAR INVALIDO
 	 * 328: MONTO ASIG.FAM.REINT. NO CORRESPONDE AL SER EMPRESA PUBLICA
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN230 ERR:AsigFamReint:valor recibido invalido:" + c.getValor() + "::");
				return 135;// REINTEGROS ASIGNACION FAMILIAR INVALIDO
			}

			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue(); // si es publica no aplica
			if (isPrivada == 1 && monto > 0) // empresa publica, e informa monto
			{
				((CotizacionREVO) cotizante.getCotizacion()).setAsigFamRetro(monto);
				if (this.logear)
					log.info("validacion VN230 ERR:AsigFamReint:valor recibido no corresponde, empresa publica:" + monto + "::");
				return 328; //MONTO ASIG.FAM.REINT. NO CORRESPONDE AL SER EMPRESA PUBLICA
			}

			((CotizacionREVO) cotizante.getCotizacion()).setAsigFamReint(monto);
			if (this.logear)
				log.info("validacion VN230 OK:AsigFamReint:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN230::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			this.mensaje = "AsignacionFamReintegro";
			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue(); // si es publica no aplica
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();
			int monto = cotizacionREVO.getAsigFamRetro();
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN230 ERR:AsigFamReint:valor recibido invalido:" + monto + "::");
				return 135;// ASIGNACION FAMILIAR REInTEGRO INVALIDA
			}
			if (isPrivada == 1 && monto > 0) // empresa publica, no aplica
			{
				if (this.logear)
					log.info("validacion VN230 ERR:AsigFamReint:valor recibido no corresponde, empresa publica:" + monto + "::");
				return 328; //MONTO ASIG.FAM.REINT. NO CORRESPONDE AL SER EMPRESA PUBLICA
			}

			if (this.logear)
				log.info("validacion VN230 OK:AsigFamReint:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN230::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN230(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN230(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN230(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
