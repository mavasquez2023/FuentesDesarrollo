package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public abstract class Validacion
{
	protected int COD_CUMPLE_VALIDACION = 0;
	protected int SIN_PARAM_NEGOCIO = 1;
	protected int SIN_CONCEPTOS = 2; // lista de parametros vacia
	protected int CONCEPTOS_SIN_VALOR = 3; // valor obligatorio vacio
	protected int ERR_VAL_ANTERIOR = 4; // algun valor que se supone deberia estar ya configurado no esta (asignacion valores en validaciones anteriores)
	protected int CAIDA_SISTEMA = 5;

	protected boolean logear = true;

	protected Vector parametros = new Vector();
	protected HashMap parametrosNegocio;
	protected Utils setter;
	protected Session session;
	protected ConvenioVO datosConvenio;

	protected String resultado = "";
	protected String mensaje = "";

	protected HashMap mapeoGeneros;
	protected HashMap mapeoSucursales;
	protected HashMap mapeoSalud;
	protected HashMap mapeoCcaf;
	protected HashMap mapeoPension;
	protected HashMap mapeoTramoAsigFam;
	protected HashMap mapeoMvto;
	protected HashMap mapeoMvtoAf;
	protected HashMap mapeoTipoMvto;
	protected HashMap mapeoTipoMvtoAf;
	protected HashMap mapeoSil;
	protected HashMap mapeoApv;
	protected HashMap mapeoEntPension;
	protected HashMap mapeoAsigFam;
	protected HashMap mapeoEntSalud;
	protected HashMap mapeoRegimenImpositivo;

	protected HashMap cargasFamiliares;

	public Validacion(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio, HashMap cargasFamiliares) {
		super();
		this.parametrosNegocio = parametrosNegocio;
		this.session = session;
		this.datosConvenio = datosConvenio;
		this.cargasFamiliares = cargasFamiliares;
		this.logear = (((String) parametrosNegocio.get("" + Constants.PARAM_LOGGEAR_VALIDACIONES)).toLowerCase().equals("si") ? true : false);
	}	
	
	public Validacion(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super();
		this.parametrosNegocio = parametrosNegocio;
		this.session = session;
		this.datosConvenio = datosConvenio;
		this.logear = (((String) parametrosNegocio.get("" + Constants.PARAM_LOGGEAR_VALIDACIONES)).toLowerCase().equals("si") ? true : false);
	}

	public Validacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super();
		this.parametrosNegocio = parametrosNegocio;
		this.setter = setter;
		this.session = session;
		this.datosConvenio = datosConvenio;
		setParametros(parametros, listaConceptos);
		this.logear = (((String) parametrosNegocio.get("" + Constants.PARAM_LOGGEAR_VALIDACIONES)).toLowerCase().equals("si") ? true : false);
	}

	public Validacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeos)
	{
		super();
		this.parametrosNegocio = parametrosNegocio;
		this.setter = setter;
		this.session = session;
		this.datosConvenio = datosConvenio;
		setParametros(parametros, listaConceptos);
		this.logear = (((String) parametrosNegocio.get("" + Constants.PARAM_LOGGEAR_VALIDACIONES)).toLowerCase().equals("si") ? true : false);
		if (listaMapeos != null)
		{
			this.mapeoGeneros = (HashMap) listaMapeos.get(0);
			this.mapeoSucursales = (HashMap) listaMapeos.get(1);
			this.mapeoSalud = (HashMap) listaMapeos.get(2);
			this.mapeoCcaf = (HashMap) listaMapeos.get(3);
			this.mapeoPension = (HashMap) listaMapeos.get(4);
			this.mapeoTramoAsigFam = (HashMap) listaMapeos.get(5);
			this.mapeoMvto = (HashMap) listaMapeos.get(6);
			this.mapeoMvtoAf = (HashMap) listaMapeos.get(7);
			this.mapeoTipoMvto = (HashMap) listaMapeos.get(8);
			this.mapeoTipoMvtoAf = (HashMap) listaMapeos.get(9);
			this.mapeoSil = (HashMap) listaMapeos.get(10);
			this.mapeoApv = (HashMap) listaMapeos.get(11);
			this.mapeoEntPension = (HashMap) listaMapeos.get(12);
			this.mapeoAsigFam = (HashMap) listaMapeos.get(13);
			this.mapeoEntSalud = (HashMap) listaMapeos.get(14);
			this.mapeoRegimenImpositivo = (HashMap) listaMapeos.get(15);
		}
	}

	public Validacion(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeos, HashMap cargasFamiliares)
	{
		super();
		this.parametrosNegocio = parametrosNegocio;
		this.setter = setter;
		this.session = session;
		this.datosConvenio = datosConvenio;
		setParametros(parametros, listaConceptos);
		this.logear = (((String) parametrosNegocio.get("" + Constants.PARAM_LOGGEAR_VALIDACIONES)).toLowerCase().equals("si") ? true : false);
		if (listaMapeos != null)
		{
			this.mapeoGeneros = (HashMap) listaMapeos.get(0);
			this.mapeoSucursales = (HashMap) listaMapeos.get(1);
			this.mapeoSalud = (HashMap) listaMapeos.get(2);
			this.mapeoCcaf = (HashMap) listaMapeos.get(3);
			this.mapeoPension = (HashMap) listaMapeos.get(4);
			this.mapeoTramoAsigFam = (HashMap) listaMapeos.get(5);
			this.mapeoMvto = (HashMap) listaMapeos.get(6);
			this.mapeoMvtoAf = (HashMap) listaMapeos.get(7);
			this.mapeoTipoMvto = (HashMap) listaMapeos.get(8);
			this.mapeoTipoMvtoAf = (HashMap) listaMapeos.get(9);
			this.mapeoSil = (HashMap) listaMapeos.get(10);
			this.mapeoApv = (HashMap) listaMapeos.get(11);
			this.mapeoEntPension = (HashMap) listaMapeos.get(12);
			this.mapeoAsigFam = (HashMap) listaMapeos.get(13);
			this.mapeoEntSalud = (HashMap) listaMapeos.get(14);
			this.mapeoRegimenImpositivo = (HashMap) listaMapeos.get(15);
		}
		this.cargasFamiliares = cargasFamiliares;
	}	
	
	
	public abstract int valida(CotizanteVO cotizante);

	public abstract int validaFromWEB(CotizanteVO cotizante);

	public void setSession(Session arg0)
	{
		this.session = arg0;
	}

	public void setDatosConvenio(ConvenioVO arg1)
	{
		this.datosConvenio = arg1;
	}

	public String getResultado()
	{
		return this.resultado.trim();
	}

	public String getMensaje()
	{
		return this.mensaje.trim();
	}

	public void setParametros(List parametros, List listaConceptos)
	{
		this.mensaje = "";
		for (Iterator it = parametros.iterator(); it.hasNext();)
		{
			ConceptoValidacionVO c = (ConceptoValidacionVO) it.next();
			for (Iterator it2 = listaConceptos.iterator(); it2.hasNext();)
			{
				ConceptoVO campo = (ConceptoVO) it2.next();
				
				if (c.getIdConcepto() == campo.getId())
				{
					if (this.mensaje.equals("")) // solo la primera vez
						this.mensaje = campo.getNombre();
					this.parametros.add(campo);
				}
			}
		}
	}

	/*
	 * valida si monto recibido es valido, si es numerico, mayor o = a 0 lo retorna, si no, retorna -1 (cualquier cosa invalida)
	 */
	public int asignaValor(String valor)
	{
		String valorTmp = Utils.transforma(valor);
		if (!valorTmp.equals(""))
		{
			Integer integer = NumeroValidacion.valida(valorTmp);
			if (integer != null && integer.intValue() >= 0)
				return integer.intValue();
			return -1;
		}
		return 0;
	}

	public float asignaValorF(String valor)
	{
		String valorTmp = Utils.transforma(valor);
		if (!valorTmp.equals(""))
		{
			valorTmp = valor.replaceAll(",", ".");
			Float fl = NumeroValidacion.validaF(valorTmp);
			if (fl != null && fl.floatValue() >= 0)
				return fl.floatValue();
			return -1;
		}
		return 0;
	}

	public Date asignaFecha(String valor)
	{
		String valorTmp = Utils.transforma(valor);
		valorTmp = valor.replaceAll(" ", "_");
		if (!valorTmp.equals(""))
			return FechaValidacion.validaFormato(valorTmp);
		return null;
	}

	public java.sql.Date asignaFechaSql(String valor)
	{
		if (!valor.equals(""))
		{
			Date fechaTmp = FechaValidacion.validaFormato(valor);
			if (fechaTmp != null)
				return new java.sql.Date(fechaTmp.getTime());
			return null;
		}
		return null;
	}
	
	public String getValor(){
		String valor;
		ConceptoVO c = (ConceptoVO) this.parametros.get(0);
		valor = c.getValor();
		return valor;
	}
}
