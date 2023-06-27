package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN550 extends Validacion
{
	private static Logger log = Logger.getLogger(VN550.class);

	/*
	 * 1 parametro = VN550: Cotizacion trabajo pesado
	 * 
	 * Mensajes 
	 * 178: Identificador de Trabajo Pesado debe aparecer 
	 * 179: Cotizacion Trabajo Pesado invalido 
	 * 180: Cotizacion Trabajo Pesado incorrecto 
	 * 181: Cotizacion Trabajo Pesado debe aparecer 
	 * 315: MONTO SUPERA EL TOPE IMPONIBLE ESTANDAR, UF ACTUAL
	 * 336: NO CORRESPONDE ID MONTO TRAB.PESADO AL ESTAR ASOCIADO A INP
	 * 353: TASA DE TRABAJOS PESADOS DEBE APARECER
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());
			String id = getId(cotizante.getTipoProceso(), cotizante.getCotizacion()).trim();
			float tasa = getTasa(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (tasa == -1)
				setTasa(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());

			return valida(cotizante, c.getValor(), monto, id, tasa);
		} catch (Exception e)
		{
			log.error("error validacion VN550::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(CotizanteVO cotizante, String valor, int monto, String id, float tasa)
	{
		if (cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_AFP)
		{
			if (monto > 0)
			{
				if (this.logear)
					log.info("validacion VN550 ERR:valor no corresponde si es INP::");
				return 336;
			}
		} else //es AFP => corresponde TP
		{
			if (monto == 0 && tasa == 0 && id.equals(""))
			{
				setMontoTrabajo(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());
				if (this.logear)
					log.info("validacion VN550 OK: TP, se asigna montoTrabPesado = 0");
				return this.COD_CUMPLE_VALIDACION;
			}
			if (monto < 0)
			{
				if (this.logear)
					log.info("validacion VN550 ERR: Cotizacion Trabajo Pesado invalido:" + valor + "::");
				return 179;// Cotizacion Trabajo Pesado invalido (no es numero ni vacio)
			}
			if (monto > 0 && id.equals(""))
			{
				if (this.logear)
					log.info("validacion VN550 ERR: Cotizacion Trabajo Pesado:" + monto + ":: y deben aparecer el id");
				return 178;// Identificador de Trabajo Pesado debe aparecer 
			}
			if (monto > 0 && tasa == 0) 
			{
				if (this.logear)
					log.info("validacion VN550 ERR: Cotizacion Trabajo Pesado:" + monto + ":: y deben aparecer la tasa");
				return 353;//TASA DE TRABAJOS PESADOS DEBE APARECER
			}
			if (monto == 0 && (tasa != 0 || !id.equals("")))
			{
				if (this.logear)
					log.info("validacion VN550 ERR: Cotizacion Trabajo Pesado:" + monto + ":: y deben aparecer el monto:tasa:" + tasa + ":id:" + id + "::");
				return 181;//Cotizacion Trabajo Pesado debe aparecer 
			}

			setMontoTrabajo(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
			if (cotizante.getTipoProceso() != 'A')
			{
				float ufActual = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
				float topeAFP = new Float((String) this.parametrosNegocio.get("" + Constants.PARAM_TOPE_AFP)).floatValue();
				int renta = this.getRenta(cotizante.getTipoProceso(), cotizante.getCotizacion());
				int calculado = Math.round(tasa * renta / 100);
				int tope = Math.round(ufActual * topeAFP * renta);
				if (monto > tope)
				{
					if (this.logear)
						log.info("validacion VN550 ERR: monto supera el tope de " + tope + ":recibido:" + monto + "::");
					return 315;// MONTO SUPERA EL TOPE IMPONIBLE ESTANDAR, UF ACTUAL
				}
				if (monto != calculado && (tasa > 0 || !id.equals("")))
				{
					if (this.logear)
						log.info("validacion VN550 ERR: monto esperado:" + calculado + ": monto recibido:" + monto + "::");
					return 180;//Cotizacion Trabajo Pesado incorrecto 
				}
			}
		}
		if (this.logear)
			log.info("validacion VN550 OK:MontoTrabajoPesado:" + monto + "::Tasa:" + tasa + ":id:" + id + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private void setMontoTrabajo(char tipoProceso, int monto, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO) cotizacion).setTrabPesado(monto);
		else if (tipoProceso == 'G')
			((CotizacionGRVO) cotizacion).setTrabPesado(monto);
		else if (tipoProceso == 'A')
			((CotizacionRAVO) cotizacion).setTrabPesado(monto);
	}

	private void setTasa(char tipoProceso, float tasa, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO) cotizacion).setTasaTrabPesado(tasa);
		else if (tipoProceso == 'G')
			((CotizacionGRVO) cotizacion).setTasaTrabPesado(tasa);
		else if (tipoProceso == 'A')
			((CotizacionRAVO) cotizacion).setTasaTrabPesado(tasa);
	}

	private float getTasa(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTasaTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTasaTrabPesado();
		return ((CotizacionRAVO) cotizacion).getTasaTrabPesado();
	}

	private String getId(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTipoTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTipoTrabPesado();
		return ((CotizacionRAVO) cotizacion).getTipoTrabPesado();
	}

	private int getMontoTrabPesado(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getTrabPesado();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getTrabPesado();
		return ((CotizacionRAVO) cotizacion).getTrabPesado();
	}

	private int getRenta(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO) cotizacion).getRentaImp();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO) cotizacion).getGratificacion();
		return ((CotizacionRAVO) cotizacion).getReliquidacion();
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;			

			String id = getId(cotizante.getTipoProceso(), cotizante.getCotizacion()).trim();
			float tasa = getTasa(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (tasa == -1)
				setTasa(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());
			int monto = getMontoTrabPesado(cotizante.getTipoProceso(), cotizante.getCotizacion());
			return valida(cotizante, "" + monto, monto, id, tasa);
			
			/*
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
			{
				float tasa = getTasa(cotizante.getTipoProceso(), cotizante.getCotizacion());
				float ufActual = new Float((String) this.parametrosNegocio.get("UFActual")).floatValue();
				int topeAFP = new Integer((String) this.parametrosNegocio.get("topeAFP")).intValue();
				int renta = this.getRenta(cotizante.getTipoProceso(), cotizante.getCotizacion());
				int calculado = Math.round(tasa * renta / 100);
				int tope = Math.round(ufActual * topeAFP * renta);
				if (tasa == 0)
				{
					setMontoTrabajo(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());
					if (this.logear)
						log.info("validacion VN550 OK: sin tasa, se asigna montoTrabPesado = 0");
					return this.COD_CUMPLE_VALIDACION;
				}
				if (tasa != 0 && monto == 0 && cotizante.getTipoProceso() != 'A')
				{
					setMontoTrabajo(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());
					if (this.logear)
						log.info("validacion VN550 ERR: tiene tasa, pero no viene monto");
					return 181;// Cotizacion Trabajo Pesado debe aparecer
				}
				if (tasa != 0 && monto < 0 && cotizante.getTipoProceso() == 'A')
				{
					setMontoTrabajo(cotizante.getTipoProceso(), 0, cotizante.getCotizacion());
					if (this.logear)
						log.info("validacion VN550 ERR: tiene tasa, pero monto debe ser mayor o igual a 0");
					return 181;// Cotizacion Trabajo Pesado debe aparecer
				}
				setMontoTrabajo(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
				setTasa(cotizante.getTipoProceso(), tasa, cotizante.getCotizacion());
				float ufActual = new Float((String) this.parametrosNegocio.get("UFActual")).floatValue();
				int topeAFP = new Integer((String) this.parametrosNegocio.get("topeAFP")).intValue();
				if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G')
				{
					if (monto > (ufActual * topeAFP * renta))
					{
						if (this.logear)
							log.info("validacion VN550 ERR: monto supera el tope de " + (ufActual * topeAFP * renta) + "::");
						return 315;// MONTO SUPERA EL TOPE IMPONIBLE ESTANDAR, UF ACTUAL
					}
				}
				if (monto != Math.round(tasa * renta / 100))
				{
					if (this.logear)
						log.info("validacion VN550 ERR: monto esperado:" + Math.round(tasa * renta / 100) + " monto recibido:" + monto + "::");
					return 180;// Cotizacion Trabajo Pesado incorrecto
				}
				this.resultado = "";
			}
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP)
			{
				if (monto != 0)
				{
					if (this.logear)
						log.info("validacion VN550 ERR:valor no corresponde si no es INP::");
					return 336;
				}
			}
			if (this.logear)
				log.info("validacion VN550 OK:MontoTrabajoPesado:");
			return this.COD_CUMPLE_VALIDACION;*/
		} catch (Exception e)
		{
			log.error("error validacion VN550::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN550(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN550(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN550(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}

}
