package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;

public class VN180 extends Validacion
{
	private static Logger log = Logger.getLogger(VN180.class);

	/*
	 * 1 parametro = VN180: fecha termino mov de personal, requiere: VN160, VN170
	 * 
	 * Mensajes 206: Fecha termino mov. personal invalida
	 * 			207: Mov. personal es 2, f. inicio o f. termino debe existir
	 * 			208: Fecha inicio debe ser menor o igual a Fecha termino
	 * 			211: Fecha termino mov.personal fuera de rango
	 * 			127: Fecha termino mov. personal indicada pero no codigo mov.
	 * 			129: Codigo de Movimiento es 3 por lo que este valor debe existir
	 * 			319: Fecha de termino vacia
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			Date fecha = asignaFecha(c.getValor());
			if (!cotizante.isAfpVoluntario())
			{
				MovtoPersonalVO mp = ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPers(); // Obtiene el movimiento personal en donde esta contenida la fecha de inicio
				if (mp != null)
				{
					if (fecha != null)
						mp.setTermino(new java.sql.Date(fecha.getTime()));
					else if (mp.getTermino() == null)
						mp.setTermino(null);//Constants.FECHA_DEFECTO_SQL);
					return valida(fecha, c.getValor(), mp, false);
				}
				if (this.logear)
					log.info("validacion VN180 OK:fecha termino MovtoPersonal:sin valor, sin movimiento:");
				return this.COD_CUMPLE_VALIDACION;
			}
			log.info("VN180: fecha termino recibida:" + c.getValor() + "::");
			MvtoPersoAFVO mp = ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPersAF(); // Obtiene el movimiento personal en donde esta contenida la fecha de inicio+
			if (mp != null)
			{
				if (c.getValor().trim().equals(""))
				{
					if (this.logear)
						log.info("validacion VN180 ERR:fecha inicio vacia:");
					if (mp.getTermino() == null)
						mp.setTermino(null);//Constants.FECHA_DEFECTO_SQL);
					return 319; // Fecha termino vacia
				}
				if (fecha != null)
					mp.setTermino(new java.sql.Date(fecha.getTime()));
				else if (mp.getTermino() == null)
					mp.setTermino(null);//Constants.FECHA_DEFECTO_SQL);
				return valida(fecha, c.getValor(), mp, false);
			}
			if (this.logear)
				log.info("validacion VN180 OK:fecha termino MovtoPersonal:sin valor, sin movimiento:");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN180::", e);
			if (this.logear)
				log.info("validacion VN180 ERR:fecha termino MovtoPersonal:");
			return 206;
		}
	}

	private int valida(Date fechaFin, String valor, MovtoPersonalVO mp, boolean web)
	{
		if (mp == null && fechaFin != null)
		{
			if (this.logear)
				log.info("validacion VN180 ERR:fecha fin MovtoPersonal:" + valor + ":no se indico idTipoMovto:");
			return 127; // Fecha inicio mov. personal indicada pero no codigo movimiento.
		} else if (mp == null)
		{
			if (this.logear)
				log.info("validacion VN180 OK:fecha fin MovtoPersonal:sin valor, sin movimiento:");
			return this.COD_CUMPLE_VALIDACION;
		}
		TipoMovimientoPersonalVO tipo = null;
		if (web)
		{
			List lista = this.session.createCriteria(TipoMovimientoPersonalVO.class).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoMovimientoPersonalVO tmp = (TipoMovimientoPersonalVO) it.next();
				if (tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
					break;
				}
			}
		} else
		{
			for (Iterator it = this.mapeoTipoMvto.keySet().iterator(); it.hasNext();)
			{
				TipoMovimientoPersonalVO tmp = (TipoMovimientoPersonalVO) this.mapeoTipoMvto.get(String.valueOf(it.next()));
				if (tmp != null && tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
					break;
				}
			}
		}
		if (tipo == null)
		{
			if (this.logear)
				log.info("validacion VN180 ERR:tipoMovtoPersonal no se encontro:" + mp.getIdTipoMovReal() + "::");
			mp.setTermino(new java.sql.Date(fechaFin.getTime()));
			return 125; // Codigo movimiento personal no corresponde a valores posibles
		}
		int diasXMes = new Integer((String) this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		
		String periodo="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		
		//String periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);

		java.sql.Date _fechaInicio = null;;
		if (mp.getInicio() != null) {
			if (mp.getInicio().getTime() != Constants.FECHA_DEFECTO_MILESIMAS)
				_fechaInicio = new java.sql.Date(mp.getInicio().getTime());
		} else {
			_fechaInicio = null;
		}
		if (tipo.getFechaTerminoObligatoria() == 1)
		{
			if (valor.trim().equals(""))
			{
				if (this.logear)
					log.info("validacion VN180 ERR:fecha inicio vacia:");
				mp.setTermino(null);//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
				return 319; // Fecha termino vacia
			}
			if (fechaFin == null)
			{
				if (this.logear)
					log.info("validacion VN180 ERR: Fecha es obligatoria para mto" + mp.getIdMovimiento() + "::");
				if (mp.getTermino() == null)
					mp.setTermino(null);//Constants.FECHA_DEFECTO_SQL);
				return 206;
			}
		} /*else if (tipo.getFechaTerminoObligatoria() == 0 && fechaFin == null && _fechaInicio == null)
		{
			if (this.logear)
				log.info("validacion VN180 ERR:ambos valores fechas son vacios, debe existir uno de los 2 ::");
			if (mp.getTermino() == null)
				mp.setTermino(null);//Constants.FECHA_DEFECTO_SQL);
			return 207;// Mov. personal es 2, f. inicio o f. termino debe existir
		}*/
		
		else if (tipo.getFechaTerminoObligatoria() == 0 && fechaFin != null) {
			if (this.logear)
				log.info("validacion VN180 ERR:fecha de termino no corresponde ::");
			mp.setTermino(null);
			return 907;// Fecha de termino no corresponde
		}

		if (_fechaInicio != null && fechaFin != null && _fechaInicio.after(fechaFin))
		{
			if (this.logear)
				log.info("validacion VN180 ERR:inicio menor a termino:inicio:" + _fechaInicio + ":termino:" + fechaFin + "::");
			mp.setTermino(new java.sql.Date(fechaFin.getTime()));
			return 208;// Fecha inicio debe ser menor o igual a Fecha termino
		}

		if (fechaFin != null && !FechaValidacion.fechaEnMes(fechaFin, diasXMes, periodo))
		{
			if (this.logear)
				log.info("validacion VN180 ERR:fuera de rango:periodo:" + periodo + ":termino:" + mp.getTermino() + "::");
			mp.setTermino(new java.sql.Date(fechaFin.getTime()));
			return 211;// Fecha termino mov. personal fuera de rango
		}

		mp.setTermino((fechaFin != null ? new java.sql.Date(fechaFin.getTime()) : null));// new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));
		if (this.logear)
			log.info("validacion VN180 OK:fecha termino MovtoPersonal:" + fechaFin + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	private int valida(Date fechaFin, String valor, MvtoPersoAFVO mp, boolean web)
	{
		if (mp == null && fechaFin != null)
		{
			if (this.logear)
				log.info("validacion VN180 ERR:fecha fin MovtoPersonal:" + valor + ":no se indico idTipoMovto:");
			return 127; // Fecha inicio mov. personal indicada pero no codigo movimiento.
		} else if (mp == null)
		{
			if (this.logear)
				log.info("validacion VN180 OK:fecha fin MovtoPersonal:sin valor, sin movimiento:");
			return this.COD_CUMPLE_VALIDACION;
		}
		TipoMvtoPersoAFVO tipo = null;
		if (web)
		{
			List lista = this.session.createCriteria(TipoMvtoPersoAFVO.class).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoMvtoPersoAFVO tmp = (TipoMvtoPersoAFVO) it.next();
				if (tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
					break;
				}
			}
		} else
		{
			for (Iterator it = this.mapeoTipoMvtoAf.keySet().iterator(); it.hasNext();)
			{
				TipoMvtoPersoAFVO tmp = (TipoMvtoPersoAFVO) this.mapeoTipoMvtoAf.get(String.valueOf(it.next()));
				if (tmp != null && tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
					break;
				}
			}
		}
		if (tipo == null)
		{
			if (this.logear)
				log.info("validacion VN180 ERR:tipoMovtoPersonal no se encontro:" + mp.getIdTipoMovReal() + "::");
			mp.setTermino(new java.sql.Date(fechaFin.getTime()));
			return 125; // Codigo movimiento personal no corresponde a valores posibles
		}
		int diasXMes = new Integer((String) this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		
		String periodo="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		//String periodo = (String) this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);

		java.sql.Date _fechaInicio;
		if (mp.getInicio().getTime() != Constants.FECHA_DEFECTO_MILESIMAS)
			_fechaInicio = new java.sql.Date(mp.getInicio().getTime());
		else
			_fechaInicio = null;
		if (tipo.getFechaTerminoObligatoria() == 1)
		{
			if (fechaFin == null)
			{
				if (this.logear)
					log.info("validacion VN180 ERR: termino es obligatorio::");
				if (mp.getTermino() == null)
					return 206;// Fecha termino mov. personal invalida
			}
		} else if (tipo.getFechaInicioObligatoria() == 1)
		{
			if (_fechaInicio == null)
			{// TODO deberia retornar causa de fecha inicio
				if (this.logear)
					log.info("validacion VN180 ERR: inicio es obligatorio::");
				return 206;// Fecha termino mov. personal invalida
			}
		}
		if (tipo.getFechaInicioObligatoria() == 0 && tipo.getFechaTerminoObligatoria() == 0)
		{
			if (_fechaInicio != null && fechaFin != null && _fechaInicio.after(fechaFin))
			{
				if (this.logear)
					log.info("validacion VN180 ERR:inicio menor a termino:inicio:" + _fechaInicio + ":termino:" + fechaFin + "::");
				mp.setTermino(new java.sql.Date(fechaFin.getTime()));
				return 208;// Fecha inicio debe ser menor o igual a Fecha termino
			}

			if (fechaFin != null && !FechaValidacion.fechaEnMes(fechaFin, diasXMes, periodo))
			{
				if (this.logear)
					log.info("validacion VN180 ERR:fuera de rango:periodo:" + periodo + ":termino:" + mp.getTermino() + "::");
				mp.setTermino(new java.sql.Date(fechaFin.getTime()));
				return 211;// Fecha termino mov. personal fuera de rango
			}
		}
		mp.setTermino((fechaFin != null ? new java.sql.Date(fechaFin.getTime()) : null));//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));
		if (this.logear)
			log.info("validacion VN180 OK:fecha termino MovtoPersonal:" + fechaFin + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			if (!cotizante.isAfpVoluntario())
			{
				MovtoPersonalVO mp = ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPers(); // Obtiene el movimiento personal en donde esta contenida la fecha de inicio
				return valida(mp.getTermino(), "" + mp.getTermino(), mp, true);
			}
			MvtoPersoAFVO mp = ((CotizacionREVO) cotizante.getCotizacion()).getMovtoPersAF(); // Obtiene el movimiento personal en donde esta contenida la fecha de inicio
			return valida(mp.getTermino(), "" + mp.getTermino(), mp, true);
		} catch (Exception e)
		{
			log.error("error validacion VN180::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN180(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN180(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN180(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
