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

public class VN720 extends Validacion
{
	private static Logger log = Logger.getLogger(VN720.class);

	/*
	 * 1 parametro = VN720: periodos indemnizacion: si no viene, o si es invalido, se calcula si las fechas son validas, si no se lanza error
	 * 
	 * 203: VALOR DE PERIODOS DE INDEMNIZACION INCORRECTO
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
			int numPeriodos = asignaValor(c.getValor());

			//return valida(numPeriodos, c.getValor(), (CotizacionDCVO)cotizante.getCotizacion());
			return valida(numPeriodos, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN720::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	//private int valida(int numPeriodos, String valor, CotizacionDCVO cotizacion) 
	private int valida(int numPeriodos, String valor, CotizanteVO cotizante)
	{
		if (numPeriodos < 1)
		{
			Date fechaInicio = this.getIndemInicio(cotizante);
			Date fechaTermino = this.getIndemTermino(cotizante);
			if (fechaInicio != null && fechaTermino != null)
				numPeriodos = fechaTermino.getMonth() - fechaInicio.getMonth() + 1;
			else //no se puede calcular
			{
				if (fechaInicio == null)
					this.setIndemInicio(new Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
				if (fechaTermino == null)
					this.setIndemTermino(new Date(Constants.FECHA_DEFECTO_MILESIMAS), cotizante);
				if (this.logear)
					log.info("validacion VN720 ERR:num periodos indem:valor recibido:" + valor + "::");
				return 203;// VALOR DE PERIODOS DE INDEMNIZACION INCORRECTO
			}
		}
		if(tieneIndemnizacion(cotizante))
			this.setNumPeriodos(numPeriodos, cotizante);
		if (this.logear)
			log.info("validacion VN720 OK:num periodos indem:" + numPeriodos + "::");
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

			int numPeriodos = this.getNumPeriodos(cotizante);

			//return valida(numPeriodos, "" + numPeriodos, (CotizacionDCVO)cotizante.getCotizacion());
			return valida(numPeriodos, "" + numPeriodos, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN720::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int getNumPeriodos(CotizanteVO cotizante) {
		int numPeriodos = 0;
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO)cotizante.getCotizacion();
			numPeriodos = cotizacion.getNumPeriodos();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			numPeriodos = cotizacion.getNumPeriodos();
		}
		return numPeriodos;
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
	
	private void setNumPeriodos(int numPeriodos, CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setNumPeriodos(numPeriodos);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setNumPeriodos(numPeriodos);
		}
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

	public VN720(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN720(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN720(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
