package cl.araucana.cp.distribuidor.business.validaciones;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN700 extends Validacion
{
	private static Logger log = Logger.getLogger(VN700.class);

	/*
	 * 1 parametro = VN700: fecha inicio indemnizacion
	 * 
	 * Mensajes
	 * 		216: FECHA DE INICIO DE INDEMNIZACION INVALIDA
	 * 		208: FECHA INICIO DEBE SER MENOR O IGUAL A FECHA TERMINO
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			Date fecha = asignaFechaSql(c.getValor());
			if(c.getValor().equals("") && tieneIndemnizacion(cotizante))
				return 216;
			return valida(fecha, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN700::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	//private int valida(Date fecha, String valor, CotizacionDCVO cotizacion) 
	private int valida(Date fecha, String valor, CotizanteVO cotizante)
	{
		if (this.getIndemAporte(cotizante) > 0 || this.getTasaPactada(cotizante) > 0)
		{
			if(fecha == null)
			{
				if (this.logear) log.info("validacion VN700 ERR:fecha inicio indemnizacion invalida:"+valor+":");
				this.setIndemInicio(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
				return 216;
			}
			if(fecha.getTime() == 1)
			{
				if (this.logear) log.info("validacion VN700 ERR:fecha inicio indemnizacion invalida:"+valor+":");
				return 216;
			}
		}
		this.setIndemInicio(fecha == null ? new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS) : new java.sql.Date(fecha.getTime()), cotizante);
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;
			
			//CotizacionDCVO cotizacion = (CotizacionDCVO)cotizante.getCotizacion();
			Date fecha = this.getIndemInicio(cotizante);
			return valida(fecha, fecha.toString(), cotizante);
		
		} catch (Exception e)
		{
			log.error("error validacion VN700::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private void setIndemInicio(Date fecha, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setIndemInicio(fecha);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setIndemInicio(fecha);
		}
	}

	private Date getIndemInicio(CotizanteVO cotizante) {
		Date fecha = new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS);
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			fecha = cotizacion.getIndemInicio();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			fecha = cotizacion.getIndemInicio();
		}
		return fecha;
	}

	private float getTasaPactada(CotizanteVO cotizante){
		float tasa = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			tasa = cotizacion.getTasaPactada();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			tasa = cotizacion.getTasaPactada();
		}
		return tasa;
	}

	private int getIndemAporte(CotizanteVO cotizante) {
		int indemAporte = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			indemAporte = cotizacion.getIndemAporte();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			indemAporte = cotizacion.getIndemAporte();
		}
		return indemAporte;
	}

	private boolean tieneIndemnizacion(CotizanteVO cotizante) {
		boolean respuesta = false;
		int regimen = 0;

		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			regimen = cotizacion.getTipoRegimenPrev();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			regimen = cotizacion.getTipoRegimenPrev();			
		}		
		
		if (regimen == 1 || regimen == 2)
			respuesta = true;
		return respuesta;
	}
	
	public VN700(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN700(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN700(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
