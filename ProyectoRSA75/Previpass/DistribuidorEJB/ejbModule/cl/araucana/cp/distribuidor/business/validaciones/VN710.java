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

public class VN710 extends Validacion
{
	private static Logger log = Logger.getLogger(VN710.class);

	/*
	 * 1 parametro= VN710: fecha termino indemnizacion
	 * 		aca se valida la existencia de indemnizacion: si existe algun valor para:
	 * 		 tasa, monto aporte, fecha inicio o termino => todos deben existir
	 * 		(VN680-VN690-VN700-VN710)
	 * 
	 * Mensajes
	 * 		217: FECHA DE TERMINO DE INDEMNIZACION INVALIDA
	 * 		208: FECHA INICIO DEBE SER MENOR O IGUAL A FECHA TERMINO
	 * 		199: VALOR TASA INDEMNIZACION INCORRECTA
	 * 		201: MONTO INDEMNIZACION INCORRECTO 
	 * 		216: FECHA DE INICIO DE INDEMNIZACION INVALIDA
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
			this.mensaje = c.getNombre().trim() + ", val otros campos";
			Date fecha = asignaFechaSql(c.getValor());
			//if(c.getValor().equals("") && tieneIndemnizacion(cotizante))
				//return 216;
			return valida(fecha, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN710::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(Date fecha, CotizanteVO cotizante) 
	{
		if (this.getIndemAporte(cotizante) > 0 || this.getTasaPactada(cotizante) > 0)
		{
			if (fecha == null)
			{
				if (this.logear) log.info("validacion VN710 indemnizacion ERR: fechatermino indemnizacion invalida::");
				this.setIndemTermino(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
				return 217;
			}
			this.setIndemTermino(new java.sql.Date(fecha.getTime()), cotizante);
			if(fecha.getTime() == 1)
			{
				if (this.logear) log.info("validacion VN700 ERR:fecha inicio indemnizacion invalida::");
				return 217;
			}
		}
		this.setIndemTermino(fecha == null ? new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS) : new java.sql.Date(fecha.getTime()), cotizante);
		boolean existe = false;
		if (this.getIndemAporte(cotizante) > 0 || this.getTasaPactada(cotizante) > 0 || 
				this.getIndemInicio(cotizante) != null || this.getIndemTermino(cotizante) != null)
			existe = true;
		if (existe)
		{
			if (this.logear)
				log.info("validacion VN710 indemnizacion:SI se considera indemnizacion para esta cotizacion, validando coherencia de cada valor::");
			if (this.getIndemAporte(cotizante) == 0 && this.getTipoRegimenPrev(cotizante)!= 0)
			{
				if (this.logear)
					log.info("validacion VN710 indemnizacion ERR:monto debe ser > 0:valor:" + this.getIndemAporte(cotizante) + "::");
				return 201; //MONTO INDEMNIZACION INCORRECTO
			} else if (this.getIndemAporte(cotizante) == -1)
			{//en VN690 se agrego 'MONTO INDEMNIZACION INVALIDO', por eso ahora no se agrega nada
				this.setIndemAporte(0, cotizante);
				return this.COD_CUMPLE_VALIDACION;
			} else if (this.getTasaPactada(cotizante) == 0 && this.getTipoRegimenPrev(cotizante)!= 0)
			{
				if (this.logear)
					log.info("validacion VN710 indemnizacion ERR:tasa debe ser > 0:valor:" + this.getTasaPactada(cotizante) + "::");
				return 199; //VALOR TASA INDEMNIZACION INCORRECTA
			}
			if (fecha != null && fecha.before(this.getIndemInicio(cotizante)))
			{
				if (this.logear)
					log.info("validacion VN710 ERR:fecha inicio indem debe ser menor a termino: inicio:" + this.getIndemInicio(cotizante).toString() + ":termino:" + fecha.toString() + "::");
				return 208; //FECHA INICIO DEBE SER MENOR O IGUAL A FECHA TERMINO
			}
			if (this.logear)
				log.info("validacion VN710 indemnizacion OK:todos los valores correctos:IndemAporte:" + this.getIndemAporte(cotizante) + 
						":TasaPactada:"  + this.getTasaPactada(cotizante) + ":IndemInicio:" + this.getIndemInicio(cotizante).toString() + ":IndemTermino:" + this.getIndemTermino(cotizante).toString() + "::");
		} else //no se considera indemnizacion
		{
			this.setIndemInicio(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
			this.setIndemTermino(new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
			if (this.logear)
				log.info("validacion VN710 indemnizacion WARN:no se considera indemnizacion para esta cotizacion, todos los valores vacios::");
		}
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

			Date fecha = this.getIndemTermino(cotizante);

			return valida(fecha, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN710::", e);
			return this.CAIDA_SISTEMA;
		}
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

	private Date getIndemTermino(CotizanteVO cotizante) {
		Date fecha = new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS);
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			fecha = cotizacion.getIndemTermino();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			fecha = cotizacion.getIndemTermino();
		}
		return fecha;
	}
	
	private void setIndemAporte(int monto, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setIndemAporte(monto);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setIndemAporte(monto);
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

	private void setIndemTermino(Date fecha, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setIndemTermino(fecha);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setIndemTermino(fecha);
		}
	}

	private int getTipoRegimenPrev(CotizanteVO cotizante) {
		int tipoReg = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			tipoReg = cotizacion.getTipoRegimenPrev();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			tipoReg = cotizacion.getTipoRegimenPrev();
		}
		return tipoReg;
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
	
	public VN710(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN710(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN710(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
