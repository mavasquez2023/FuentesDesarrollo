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

public class VN170 extends Validacion
{
	private static Logger log = Logger.getLogger(VN170.class);
	/*
	 * 1 parametro = VN170: fecha inicio mov personal, requiere: VN160
	 * 
	 * Mensajes
	 * 		205: Fecha inicio mov. personal invalida
	 * 		210: Fecha inicio mov. personal fuera de rango
	 * 		126: Fecha inicio mov. personal indicada pero no codigo mov.
	 * 		318: Fecha de inicio vacia
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() != 'R')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			Date fecha = asignaFecha(c.getValor());
			if (!cotizante.isAfpVoluntario())
			{
				MovtoPersonalVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPers(); //Obtiene el movimiento personal en donde esta contenida la fecha de inicio
				if(mp != null)
				{
					if(fecha != null)
						mp.setInicio(new java.sql.Date(fecha.getTime()));
					else
						mp.setInicio(Constants.FECHA_DEFECTO_SQL);
					return valida(fecha, c.getValor(), mp, false);
				}
				if (this.logear)
	    			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:sin valor, sin movimiento:");
	    		return this.COD_CUMPLE_VALIDACION;
			}
			MvtoPersoAFVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPersAF(); //Obtiene el movimiento personal en donde esta contenida la fecha de inicio+
			if(mp != null)
			{
				if(fecha != null)
					mp.setInicio(new java.sql.Date(fecha.getTime()));
				else
					mp.setInicio(Constants.FECHA_DEFECTO_SQL);
				return valida(fecha, c.getValor(), mp, false);
			}
			if (this.logear)
    			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:sin valor, sin movimiento:");
    		return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{

			log.error("error validacion VN170::", e);
			return 205; // Fecha inicio mov. personal invalida
		}
	}

	private int valida(Date fecha, String valor, MovtoPersonalVO mp, boolean web) 
	{
		if (mp == null && fecha != null)
    	{
    		if (this.logear)
    			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal:" + valor + ":no se indico idTipoMovto:");
    		return 126; //Fecha inicio mov. personal indicada pero no codigo mov.
    	} else if (mp == null)
    	{
    		if (this.logear)
    			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:sin valor, sin movimiento:");
    		return this.COD_CUMPLE_VALIDACION;
    	}
		TipoMovimientoPersonalVO tipo = null;
		if(web)
		{
			List lista = this.session.createCriteria(TipoMovimientoPersonalVO.class).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoMovimientoPersonalVO tmp = (TipoMovimientoPersonalVO)it.next();
				if(tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
				}
			}
		} else
		{
			if(this.mapeoTipoMvto.containsKey(String.valueOf(mp.getIdTipoMovReal())))
				tipo = (TipoMovimientoPersonalVO)this.mapeoTipoMvto.get(String.valueOf(mp.getIdTipoMovReal()));

		}
    	int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
    	
    	String periodo="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
    	//String periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);

    	if(tipo != null)// && tipo.getFechaTerminoObligatoria() == 1)
    	{
    		/*if (mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_CESACION) {
        		if(valor.trim().equals(""))
    			{
    				if (this.logear)
    	    			log.info("validacion VN170 ERR:fecha inicio vacia:");
    				mp.setInicio(null);//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
    				return 318; // Fecha inicio vacia
    			}
    		} else {
    			//clillo 8-5-13 Se agrega condición !="null" ya que llegaba así parámetro cuando no exsitía MP inicio
    			if(!valor.trim().equals("") && !valor.equals("null")) {
    				if (this.logear)
    	    			log.info("validacion VN170 ERR:fecha inicio debe ser vacía:");
    				//clillo 8-5-13 se comenta ya que fecha llegaba como null y se caía.
    				//mp.setInicio(new java.sql.Date(fecha.getTime()));
    				return 380; //Fecha de Inicio no corresponde
    			}else{
    				return this.COD_CUMPLE_VALIDACION;
    			}
    		}
			*/
    		//GMALLEA 28-06-2013 
    		if (tipo.getFechaInicoObligatoria() == 1)
    		{
    			if (valor.trim().equals(""))
    			{
    				if (this.logear)
    					log.info("validacion VN180 ERR:fecha inicio vacia:");
    				mp.setInicio(null);
    				return 318; // FECHA DE INICIO NO CORRESPONDE

    			}
    			mp.setInicio((fecha != null ? new java.sql.Date(fecha.getTime()) : null));//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));
        		if (fecha == null && mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_SINMVTO)
        		{
            		if (this.logear)
            			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal invalida:valor recibido:" + valor + "::" + fecha + "::");
            		mp.setInicio(null);//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
        			return 205; //Fecha inicio mov. personal inválida
        		}
    		}else if (tipo.getFechaInicoObligatoria() == 0){
    			if(!valor.trim().equals("") && !valor.equals("null")){
    				if (this.logear)
    					log.info("validacion VN180 ERR:fecha de termino no corresponde ::");
    				mp.setInicio(null);
    				return 906;// FECHA INICIO MOV. PERSONAL NO CORRESPONDE
    			}
    		} 
    		
    		if (!FechaValidacion.fechaEnMes(fecha, diasXMes, periodo))
    		{
        		if (this.logear)
        			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal fuera de rango del periodo:valor recibido:" + valor + "::");
        		mp.setInicio(null);//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS));
    			return 210; //Fecha inicio mov. personal fuera de rango
    		}    		
    	} else if (tipo != null && tipo.getFechaTerminoObligatoria() == 0)
    	{
    		if (fecha != null && !FechaValidacion.fechaEnMes(fecha, diasXMes, periodo))
    		{
        		if (this.logear)
        			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal fuera de rango del periodo:valor recibido:" + valor + "::");
        		mp.setInicio(new java.sql.Date(fecha.getTime()));
    			return 210; //Fecha inicio mov. personal fuera de rango
    		}
    	}
    	mp.setInicio((fecha != null ? new java.sql.Date(fecha.getTime()) : null));//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));

		if (this.logear)
			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:" + fecha + "::");
		this.resultado = "";

		return this.COD_CUMPLE_VALIDACION;
	}
	
	private int valida(Date fecha, String valor, MvtoPersoAFVO mp, boolean web) 
	{
		if (mp == null && fecha != null)
    	{
    		if (this.logear)
    			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal:" + valor + ":no se indico idTipoMovto:");
    		return 126; //Fecha inicio mov. personal indicada pero no codigo mov.
    	} else if (mp == null)
    	{
    		if (this.logear)
    			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:sin valor, sin movimiento:");
    		return this.COD_CUMPLE_VALIDACION;
    	}
		TipoMvtoPersoAFVO tipo = null;
		if(web)
		{
			List lista = this.session.createCriteria(TipoMvtoPersoAFVO.class).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoMvtoPersoAFVO tmp = (TipoMvtoPersoAFVO)it.next();
				if(tmp.getId() == mp.getIdTipoMovReal())
				{
					tipo = tmp;
				}
			}
		} else
		{
			if(this.mapeoTipoMvtoAf.containsKey(String.valueOf(mp.getIdTipoMovReal())))
				tipo = (TipoMvtoPersoAFVO)this.mapeoTipoMvtoAf.get(String.valueOf(mp.getIdTipoMovReal()));
		}
    	int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
    	
    	String periodo="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
    	//String periodo = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);

    	if(tipo != null && tipo.getFechaInicioObligatoria() == 1)
    	{
    		mp.setInicio((fecha != null ? new java.sql.Date(fecha.getTime()) : null));//new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));
    		if (fecha == null && mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_SINMVTO)
    		{
        		if (this.logear)
        			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal invalida:valor recibido:" + valor + "::" + fecha + "::");
    			return 205; //Fecha inicio mov. personal invalida
    		}
    		if (!FechaValidacion.fechaEnMes(fecha, diasXMes, periodo))
    		{
        		if (this.logear)
        			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal fuera de rango del periodo:valor recibido:" + valor + "::");
    			return 210; //Fecha inicio mov. personal fuera de rango
    		}
    	} else if (tipo != null && tipo.getFechaInicioObligatoria() == 0)
    	{
    		if (fecha != null && !FechaValidacion.fechaEnMes(fecha, diasXMes, periodo))
    		{
        		if (this.logear)
        			log.info("validacion VN170 ERR:fecha inicio MovtoPersonal fuera de rango del periodo:valor recibido:" + valor + "::");
    			return 210; //Fecha inicio mov. personal fuera de rango
    		}
    	}
    	mp.setInicio((fecha != null ? new java.sql.Date(fecha.getTime()) : null)); //new java.sql.Date(Constants.FECHA_DEFECTO_MILESIMAS)));

		if (this.logear)
			log.info("validacion VN170 OK:fecha inicio MovtoPersonal:" + fecha + "::");
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
				MovtoPersonalVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPers(); //Obtiene el movimiento personal en donde está contenida la fecha de inicio
				return valida(mp.getInicio(), "" + mp.getInicio(), mp, true);
			}
			MvtoPersoAFVO mp = ((CotizacionREVO)cotizante.getCotizacion()).getMovtoPersAF(); //Obtiene el movimiento personal en donde está contenida la fecha de inicio
			return valida(mp.getInicio(), "" + mp.getInicio(), mp, true);
		} catch(Exception e)
		{
			log.error("error validacion VN170::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN170(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN170(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN170(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
