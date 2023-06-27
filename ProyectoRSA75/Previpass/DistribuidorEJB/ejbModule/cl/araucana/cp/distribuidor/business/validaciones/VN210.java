package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN210 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN210.class);
	/*
	 * 1 parametro = VN210: asignacion familiar
	 * 
	 * Mensajes
	 * 			231: Monto Asignacion Familiar Invalido
	 *  		232: Monto Asignacion Familiar Incorrecto
	 *  		326: Monto no aplica al ser empresa publica
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			this.resultado = "";
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica no aplica
			if (isPrivada == 1) //empresa publica, no aplica
			{
				((CotizacionREVO)cotizante.getCotizacion()).setAsigFamiliar(0);
				if (this.logear)
					log.info("validacion VN210 no se valida, empresa publica");
			} else
			{
				CotizacionREVO cotizacionRevo = (CotizacionREVO) cotizante.getCotizacion();
				int tramoReal= cotizante.getIdTramoReal();
				//clillo 7/7/15 se elimina ya que afecta Walmart
				/*if (tramoReal == Constants.TRAMO_ASIGFAM_NINGUNO)
				{
					if (this.logear)
						log.info("validacion VN210 sin tramo asignado:AsigFamiliar:" + cotizacionRevo.getAsigFamiliar() + ": setea a 0::");
	    			cotizacionRevo.setAsigFamiliar(0);
	    			return this.COD_CUMPLE_VALIDACION;
				}*/
				String valor = Utils.transforma(c.getValor());
				int monto = 0;

				if (!valor.equals(""))
				{
					Integer integer = NumeroValidacion.valida(valor);
					if (integer != null && integer.intValue() >= 0)
						monto = integer.intValue();
					else
					{
						if (this.logear)
							log.info("validacion VN210 ERR:AsigFamiliar:valor recibido invalido:" + valor + "::");
		        		return 231;//Monto Asignacion Familiar Invalido
					}
				}
				cotizacionRevo.setAsigFamiliar(monto);
				//clillo 7/7/15 se agrega para Walmart (coincide con Domino)
				if (tramoReal == Constants.TRAMO_ASIGFAM_NINGUNO && monto>0)
				{
					if (this.logear)
						log.info("validacion VN210 sin tramo asignado:AsigFamiliar:" + cotizacionRevo.getAsigFamiliar());
	    			return 117;
				}
				//clillo 2/12/14 se agrega error de tramo D con monto
				if (tramoReal == Constants.TRAMO_ASIGFAM_D && monto>0){
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:valor recibido tramo D no válido:" + valor + "::");
	        		return 231;//Monto Asignacion Familiar No válido tramo D
				}
				//csanchez 25/05/2011. Se deja como que "cumple" la validación ya que el trabajador ya tiene un aviso que describe el problema
				if (cotizante.getIdTramoReal() == Constants.TRAMO_ASIGFAM_NO_ENCONTRADO)
					return this.COD_CUMPLE_VALIDACION;

				AsigFamVO tramo = (AsigFamVO)this.session.get(AsigFamVO.class, new Integer(cotizante.getIdTramoReal()));
				if (tramo == null)
				{
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:tramo nulo:" + cotizante.getIdTramoReal() + "::");
					return 3505; // ' TRAMO ASIGNACION FAMILIAR NULO' //this.ERR_VAL_ANTERIOR;
				}
	        	
    			int diasTopeASFAM = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_TOPE_ASIGFAM)).intValue();
	        	int sumaAsignacionFamiliar = (cotizante.getNumCargaSimple() * tramo.getValorCarga()) + 
	        								((cotizante.getNumCargaInvalidez() * tramo.getValorCarga()) * 2) +
	        								((cotizante.getNumCargaMaterna() * tramo.getValorCarga()));	        	
    			if(cotizante.getNumDiasTrabajados() < diasTopeASFAM)
    				sumaAsignacionFamiliar = (int)Math.round(sumaAsignacionFamiliar * cotizante.getNumDiasTrabajados() / 30.0);

    			if (monto != sumaAsignacionFamiliar && cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP)
    			{
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:valor recibido:" + monto + ":valor calculado:" + sumaAsignacionFamiliar + "::");
    				return 232;//Monto Asignacion Familiar Incorrecto
    			}

				if (this.logear)
					log.info("validacion VN210 OK:AsigFamiliar:" + cotizacionRevo.getAsigFamiliar() + "::");
			}
			return this.COD_CUMPLE_VALIDACION;
			//Fin agregado dfuentes
		} catch(Exception e)
		{
			log.error("error validacion VN210::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
		//Agregado dfuentes
			//corregido cchamblas
			int monto = 0;
			this.resultado = "";
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica no aplica
			monto = ((CotizacionREVO)cotizante.getCotizacion()).getAsigFamiliar();
			if (isPrivada == 1) //empresa publica, no aplica
			{
				if(monto  != 0)
				{
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar no corresponde al ser empresa publica:::");
	        		return 326;//Monto Asignacion Familiar Invalido
				}
			} else
			{
				if (monto < 0)
				{
					if (this.logear)
						log.info("validacion VN210 ERR: monto < 0 ::");
	        		return 231;//Monto Asignacion Familiar Invalido
				}
				
				int tramoReal= cotizante.getIdTramoReal();
				//clillo 2/12/14 se agrega error de tramo D con monto
				if (tramoReal == Constants.TRAMO_ASIGFAM_D && monto>0){
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:valor recibido tramo D no válido:" + monto + "::");
	        		return 231;//Monto Asignacion Familiar No válido tramo D
				}
				
				AsigFamVO tramo = (AsigFamVO)this.session.get(AsigFamVO.class, new Integer(cotizante.getIdTramoReal()));
				if (tramo == null)
				{
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:tramo nulo:" + cotizante.getIdTramoReal() + "::");
					return this.ERR_VAL_ANTERIOR;
				}
    			int diasTopeASFAM = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_TOPE_ASIGFAM)).intValue();
	        	int sumaAsignacionFamiliar = (cotizante.getNumCargaSimple() * tramo.getValorCarga()) + 
	        								((cotizante.getNumCargaInvalidez() * tramo.getValorCarga()) * 2) +
	        								((cotizante.getNumCargaMaterna() * tramo.getValorCarga()));
    			if(cotizante.getNumDiasTrabajados() < diasTopeASFAM)
    				sumaAsignacionFamiliar = (int)Math.round(sumaAsignacionFamiliar * cotizante.getNumDiasTrabajados() / 30.0);

    			if (monto != sumaAsignacionFamiliar && cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP)
    			{
					if (this.logear)
						log.info("validacion VN210 ERR:AsigFamiliar:valor recibido:" + monto + ":valor calculado:" + sumaAsignacionFamiliar + "::");
    				return 232;//Monto Asignacion Familiar Incorrecto
    			}
			}
			if (this.logear)
				log.info("validacion VN210 OK:AsigFamiliar:" + monto + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN210::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN210(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN210(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN210(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
