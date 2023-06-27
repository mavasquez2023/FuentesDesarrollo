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

public class VN420 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN420.class);
	/*
	 * 1 parametro = VN420: Aporte de la Empresa al Seguro de Cesantia
	 * 
	 * Mensajes
	 * 		167: Monto de Aporte de la Empresa al Seguro de Cesantia invalido
	 * 		168: Monto Aporte de la Empresa al Seguro de Cesantia incorrecto
	 * 		220: Monto Total Seguro de Cesantia incorrecto
	 * 		221: Monto Total Seguro Cesantia no se puede calcular
	 * 		321: APORTE DE EMPRESA SEGURO DE CESANTIA MAYOR A TOPE
	 *      362: APORTE EMPRESA SEG.CES:NO SE INFORMA REMU IMPONIBLE SEG.CES
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
			log.error("error validacion VN420::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante) 
	{

		// si entidad AFP es ninguna muve 0 el valor 
		//TODO. Prueba por problema de descuadratura (Mantis 0001547).
		/*if (cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA)
		{
			monto = 0;
			//cotizante.getCotizacion().setPrevisionObligatorio(0); 
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN420 OK:aporteEmplSegCes = 0 :" );
			return this.COD_CUMPLE_VALIDACION;
		}*/

		//TODO 25-06-2012 GMALLEA SE AGREGA NUEVO ERROR PARA AFC
		//28-11-2014 CLILLO SE CORRIGE ERROR PARA AFC SIN AFP O CON INP
		if ((cotizante.getIdEntAfcReal() == Constants.AFC_FALSO || cotizante.getIdEntAfcReal() == Constants.ID_AFP_NINGUNA) && (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP ||  cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA)&& monto > 0) {
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN420 OK:aporteEmplSegCes = 0 :" );
			return 3830;//DEBE SELECCIONAR ENTIDAD AFC
		}	
		
		if (cotizante.getIdEntAfcReal() == Constants.AFC_FALSO && cotizante.getIdEntAfc() == null && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP) {
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN420 OK:aporteEmplSegCes = 0 :" );
			return this.COD_CUMPLE_VALIDACION;
		}	
		//TODO 25-06-2012 GMALLEA SE AGREGA NUEVO ERROR PARA AFC
		//28-11-2014 CLILLO SE COMENTA YA QUE SE DEJA UNA SOLA (LA PRIMERA DE ARRIBA)
		/*if (cotizante.getIdEntAfcReal() == Constants.AFC_FALSO && cotizante.getIdEntAfc() == null && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_NINGUNA && monto > 0) {
			monto = 0;
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN420 OK:aporteEmplSegCes = 0 :" );
			return 3830;//DEBE SELECCIONAR ENTIDAD AFC
		}	
		*/
		if (monto < 0)
		{
			if (this.logear)
				log.info("validacion VN410 ERR:aporte empl incorrecto: valor recibido:" + valor + "::");
			return 167;//Monto de Aporte de la Empresa al Seguro de Cesantia invalido (no es numero ni vacio)
		}

		setAporteEmpl(cotizante.getTipoProceso(), monto, cotizante.getCotizacion());
		
		if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G')
		{
			//Asepulveda - Recupera renta imponible
			// marco 
//			int rentaImponible = getRenta(cotizante.isIsapre(), cotizante.getCotizacion()); //Asepulveda
			
			int segCesImp = getSegCesRemImp(cotizante.getTipoProceso(), cotizante.getCotizacion());
			
			int rentaImponible = getRenta(cotizante.getCotizacion()); //marco 
			
			int aporteTrab = getSegCesTrab(cotizante.getTipoProceso(), cotizante.getCotizacion());
			if (!this.parametrosNegocio.containsKey("" + Constants.PARAM_AP_EMP_IND_SEG_CES) || 
					!this.parametrosNegocio.containsKey("" + Constants.PARAM_AP_EMP_PF_SEG_CES))
				return this.SIN_PARAM_NEGOCIO;
			float porcentajeInd = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_EMP_IND_SEG_CES)));
			float porcentajePF = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_EMP_PF_SEG_CES)));
	
			float porcenTrabInd = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_TRAB_IND_SEG_CES)));
			float porcenTrabPF = Float.parseFloat(String.valueOf(this.parametrosNegocio.get("" + Constants.PARAM_AP_TRAB_PF_SEG_CES)));
			int tolerancia = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOLERANCIA_PESO)).intValue();
			// marco 
			int diasTrabajados = cotizante.getNumDiasTrabajados();
			int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		
			//Asepulveda
			if (segCesImp == 0 && monto >0) {

				// marco 
//				segCesImp = rentaImponible;
//				((CotizacionREVO)cotizante.getCotizacion()).setSegCesRemImp( rentaImponible );				
	   		   if (this.logear)
					log.info("validacion VN410 ERR: aporte Empleadot Seguro Cesantía:" + monto + ":pero sin remuneracion imponible seg ces ::");					
				return 362;				
			}			
			
           //marco se incorpora tolerencia en monto empleador      
			
			int total = monto + aporteTrab;
			// si no informa monto trabajador y/o empresa no se valida
			if (total > 0) 
			{
				
				if ( ((monto > Math.round(segCesImp * porcentajeInd)+ tolerancia) || (monto < Math.round(segCesImp * porcentajeInd)- tolerancia) )
					&& 
				    ( (monto > Math.round(segCesImp * porcentajePF) + tolerancia ) || (monto < Math.round(segCesImp * porcentajePF) - tolerancia )) 
				     ) 							
			    {
				 if (this.logear)
				 	log.info("validacion VN420 ERR: aporte Empleador incorrecto:" + monto + ":remunecarion imponible seg ces:" + segCesImp + ":valor calculado indefinido:" + Math.round(segCesImp * porcentajeInd) + ":valor calculado pl fijo:" + Math.round(segCesImp * porcentajePF) + "::");
				    return 168; //Monto Aporte del Trabajador al Seguro de Cesantia incorrecto
			     }
			 
			
			
				if (diasTrabajados >= diasXMes){
				rentaImponible = segCesImp;
			    }
		
			 // marco 
				if ((total > Math.round((segCesImp * porcentajeInd)+(rentaImponible * porcenTrabInd))+tolerancia 
					&& 
					total != Math.round((segCesImp * porcentajePF)  + (rentaImponible * porcenTrabPF) ))
					||						
				(total < Math.round(  (segCesImp * porcentajeInd) + (rentaImponible * porcenTrabInd))-tolerancia      
					&& total != Math.round(segCesImp * (porcentajePF + porcenTrabPF))))				
				{
				  if (this.logear)
					log.info("validacion VN420 ERR: segCesantita total incorrecto: trab:" + aporteTrab + "::" + ":empl:" + monto + ":remuneracion imponible seg ces:" + segCesImp + " con una tolerancia de "+tolerancia+"::");
				    return 220; //Monto Total Seguro de Cesantia incorrecto
				}
			}
			
		} // if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G')
		
	
		this.resultado = "";
		if (this.logear)
			log.info("validacion VN420 OK:aporteEmplSegCes:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	private void setAporteEmpl(char tipoProceso, int monto, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			((CotizacionREVO)cotizacion).setSegCesEmpl(monto);
		else if (tipoProceso == 'G')
			((CotizacionGRVO)cotizacion).setSegCesEmpl(monto);
		else if (tipoProceso == 'A')
			((CotizacionRAVO)cotizacion).setSegCesEmpl(monto);
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

	private int getSegCesEmpl(char tipoProceso, CotizacionVO cotizacion)
	{
		if (tipoProceso == 'R')
			return ((CotizacionREVO)cotizacion).getSegCesEmpl();
		else if (tipoProceso == 'G')
			return ((CotizacionGRVO)cotizacion).getSegCesEmpl();
		return ((CotizacionRAVO)cotizacion).getSegCesEmpl();
	}
	
	//ASepulveda
	//marco 
	private int getRenta(CotizacionVO cotizacion)
	{
		return cotizacion.getRenta();
		
	}	

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{	
			int monto = getSegCesEmpl(cotizante.getTipoProceso(), cotizante.getCotizacion());
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
			log.error("error validacion VN420::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN420(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN420(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN420(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
