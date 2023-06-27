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

public class VN410 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN410.class);
	/*
	 * 1 parametro = VN410: aporte trab seguro cesantia
	 * 
	 * Mensajes
	 * 		165: Monto Aporte del Trabajador al Seguro de Cesantia invalido
	 * 		166: Monto Aporte del Trabajador al Seguro de Cesantia incorrecto
	 * 		320: APORTE DEL TRAB. SEGURO DE CESANTIA MAYOR A TOPE
	 *      361: APORTE TRAB. SEG. CES: NO SE INFORMA REMU IMPONIBLE SEG.CES
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";
			//Validacion aplicada a R, G, A
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN410::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante) 
	{
		//TODO. Prueba por problema de descuadratura (Mantis 0001547).
		/*if (cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA)
		{
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN410 OK:aporte trab seguro cesantia = 0 :");
			return this.COD_CUMPLE_VALIDACION;
		}*/


		if ((cotizante.getIdEntAfcReal() == Constants.AFC_FALSO || cotizante.getIdEntAfcReal() == Constants.ID_AFP_NINGUNA) && (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP || cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA) && monto > 0) {
			
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN3830 OK:aporte trab seguro cesantia = 0 :" );
			return 3830;//DEBE SELECCIONAR ENTIDAD AFC
		}	
		
		if (cotizante.getIdEntAfcReal() == Constants.AFC_FALSO && cotizante.getIdEntAfc() == null && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP) {
			
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN410 OK:aporte trab seguro cesantia = 0 :" );
			return this.COD_CUMPLE_VALIDACION;
		}	
		
		//TODO 25-06-2012 GMALLEA SE AGREGA NUEVO ERROR PARA AFC
//		28-11-2014 CLILLO SE COMENTA YA QUE SE DEJA UNA SOLA (LA PRIMERA DE ARRIBA)
		/*if (cotizante.getIdEntAfcReal() == Constants.AFC_FALSO && cotizante.getIdEntAfc() == null && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA && monto > 0) {
			
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN3830 OK:aporte trab seguro cesantia = 0 :" );
			return 3830;//DEBE SELECCIONAR ENTIDAD AFC
		}	
		*/
	
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN410 ERR: aporte trabajador incorrecto: valor recibido:" + valor + "::");
			return 165;//Monto Aporte del Trabajador al Seguro de Cesantia invalido (no es numero ni vacio)
		}
		
			
		if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G')
		{
			//Asepulveda - Recupera renta imponible
			//marco 
			int rentaImponible = getRenta(cotizante.getCotizacion()); //Asepulveda
		
			// marco 
			int diasTrabajados = cotizante.getNumDiasTrabajados();
			int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
			
			setAporteTrab(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
			
			int segCesImp = getSegCesRemImp(cotizante.getTipoProceso(), cotizante.getCotizacion());
			float porcentajeInd = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_TRAB_IND_SEG_CES)));
			float porcentajePF = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_TRAB_PF_SEG_CES)));
			
			//Asepulveda - Comenta este codigo a petición de La Araucana
//			int topeAPV = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOPE_APV)).intValue();
//			float UFActual = new Float((String)this.parametrosNegocio.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
//			if (monto > topeAPV * UFActual)
//			{
//				if (this.logear)
//					log.info("validacion VN410 ERR: aporte trabajador MAYOR A TOPE::");					
//				return 320; //APORTE DEL TRAB. SEGURO DE CESANTIA MAYOR A TOPE
//			}
			
			//Asepulveda
			if (segCesImp == 0 && monto >0) {
			// marco 
			// enviar mensaje de error biene aporte sin renta imponible seguro 
				
//				segCesImp = rentaImponible;
//				((CotizacionREVO)cotizante.getCotizacion()).setSegCesRemImp( rentaImponible );				
				if (this.logear)
					log.info("validacion VN410 ERR: aporte Trabajador Seguro Cesantía de :" + monto + ":pero sin remuneracion imponible seg ces ::");					
				return 361;	
			}
			
			// marco si dias trabajados es menor a 30 (según parametro) se toma la renta imponible para validar el monto 
			// de cotización del trabajador.
			if (diasTrabajados < diasXMes && cotizante.getTipoProceso() == 'R'){
				segCesImp = rentaImponible;
			}
					
			if (monto != Math.round(segCesImp * porcentajeInd) && monto != Math.round(segCesImp * porcentajePF))
			{
				if (this.logear)
					log.info("validacion VN410 ERR: aporte trabajador incorrecto:" + monto + ":remuneracion imponible seg ces:" + segCesImp + ":valor calculado indef:" + Math.round(segCesImp * porcentajeInd) + ":valor calculado pl fijo:" + Math.round(segCesImp * porcentajePF) + "::");					
				return 166; //Monto Aporte del Trabajador al Seguro de Cesantia incorrecto
			}
			
		}

		this.resultado = "";
		if (this.logear)
			log.info("validacion VN410 OK:aporteTrabSegCes:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private void setAporteTrab(char tipoProceso, int monto, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO)cotizacion).setSegCesTrab(monto);
		else if (tipoProceso == 'G')
			((CotizacionGRVO)cotizacion).setSegCesTrab(monto);
		else if (tipoProceso == 'A')
			((CotizacionRAVO)cotizacion).setSegCesTrab(monto);
	}

	private int getSegCesRemImp(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO)cotizacion).getSegCesRemImp();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO)cotizacion).getSegCesRemImp();
		return ((CotizacionRAVO)cotizacion).getSegCesRemImp();
	}

	private int getSegCesTrab(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO)cotizacion).getSegCesTrab();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO)cotizacion).getSegCesTrab();
		return ((CotizacionRAVO)cotizacion).getSegCesTrab();
	}
	
	//ASepulveda Marco
	private int getRenta(CotizacionVO cotizacion)
	{
			return cotizacion.getRenta();
	}	

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{	
			int monto = getSegCesTrab(cotizante.getTipoProceso(), cotizante.getCotizacion());
			//28-11-2014 CLILLO SE AGREGA ERROR SIN ENTIDAD AFC 
			String idafc= cotizante.getIdEntAfc();
			int idafcreal= cotizante.getIdEntAfcReal();
			if ((cotizante.getIdEntAfcReal() == Constants.AFC_FALSO || cotizante.getIdEntAfcReal() == Constants.ID_AFP_NINGUNA) && (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP ||  cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA)&& monto > 0) {
				this.resultado = "";
				if (this.logear)
					log.info("validacion VN420 OK:aporteEmplSegCes = 0 :" );
				return 3830;//DEBE SELECCIONAR ENTIDAD AFC
			}
			int segCesImp = getSegCesRemImp(cotizante.getTipoProceso(), cotizante.getCotizacion());
			//28-11-2014 CLILLO SE AGREGA ERROR SIN REM. AFC 
			if (segCesImp == 0 && monto >0) {			
				if (this.logear)
					log.info("validacion VN410 ERR: aporte Empleadot Seguro Cesantía:" + monto + ":pero sin remuneracion imponible seg ces ::");					
				return 362;				
			}

			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			this.resultado = "";

			return valida(monto, "" + monto, cotizante);
		} catch(Exception e)
		{
			log.error("error validacion VN410::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN410(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN410(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN410(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
