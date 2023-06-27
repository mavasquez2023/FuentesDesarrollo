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

public class VN220 extends Validacion
{
	private static Logger log = Logger.getLogger(VN220.class);

	/*
	 * 1 parametro= VN220: asignacion familiar retro-activa
	 * 
	 * Mensajes 
	 * 133: ASIGNACION FAMILIAR RETROACTIVA INVALIDA 
	 * 134: ASIGNACION FAMILIAR RETROACTIVA DEBE SER CERO 
	 * 327: MONTO ASIG.FAM.RETRO. NO CORRESPONDE AL SER EMPRESA PUBLICA
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
					log.info("validacion VN220 ERR:AsigFamRetro:valor recibido invalido:" + c.getValor() + "::");
				return 133;// ASIGNACION FAMILIAR RETROACTIVA INVALIDA
			}

			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue(); // si es publica no aplica
			if (isPrivada == 1 && monto > 0) // empresa publica, e informa monto
			{
				((CotizacionREVO) cotizante.getCotizacion()).setAsigFamRetro(monto);
				if (this.logear)
					log.info("validacion VN220 ERR:AsigFamRetro:valor recibido no corresponde, empresa publica:" + monto + "::");
				return 327;// MONTO ASIG.FAM.RETRO. NO CORRESPONDE AL SER EMPRESA PUBLICA
			}

			((CotizacionREVO) cotizante.getCotizacion()).setAsigFamRetro(monto);
			if (this.logear)
				log.info("validacion VN220 OK:AsigFamRetro:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN220::", e);
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
			this.mensaje = "AsignacionFamRetroactiva";
			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue(); // si es publica no aplica
			CotizacionREVO cotizacionREVO = (CotizacionREVO) cotizante.getCotizacion();
			int monto = cotizacionREVO.getAsigFamRetro();
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN220 ERR:AsigFamRetro:valor recibido invalido:" + monto + "::");
				return 133;// ASIGNACION FAMILIAR RETROACTIVA INVALIDA
			}
			if (isPrivada == 1 && monto > 0) // empresa publica, no aplica
			{
				if (this.logear)
					log.info("validacion VN220 ERR:AsigFamRetro:valor recibido no corresponde, empresa publica:" + monto + "::");
				return 327;// MONTO ASIG.FAM.RETRO. NO CORRESPONDE AL SER EMPRESA PUBLICA
			}

			if (this.logear)
				log.info("validacion VN220 OK:AsigFamRetro:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN220::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN220(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN220(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN220(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
