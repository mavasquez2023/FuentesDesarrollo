package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN10030 extends Validacion
{
	private static Logger log = Logger.getLogger(VN10030.class);
	/*
	 * 1 parametro = VN9080 regimen APV Voluntario
	 * 
	 * Mensajes
	 * 		VN10031 REGIMEN APV1 - INVALIDO
	 * 		VN10032 REGIMEN APV1 - NO INFORMADO
	 * 		VN10033 REGIMEN APV1 - ENTIDAD APV Vol VACION PERO CON REGIMEN
	 */
	public int valida(CotizanteVO cotizante){
		try
		{
			this.resultado = "";
			
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			
			//Si el erregle es de largo 2 significa que trae monto en regimen apv voluntario
			boolean isCotPrevVolun =false;
			if(cotizante.getApvList().size() == 2)
				isCotPrevVolun=true;
			
			String codigo = c.getValor().trim();
			
			ApvVO apvVO= null;
			if(isCotPrevVolun){
				apvVO =(ApvVO) cotizante.getApvList().get(0);
			}else{
				
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
						if (this.logear) 
							log.info("validacion VN10030:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
						//apvVO.setRegimen(codigo.toUpperCase().charAt(0));
						return  10033;//REGIMEN APV Vol - ENTIDAD APV VOL VACION PERO CON REGIMEN
					}else if(codigo.equals("")){
						return this.COD_CUMPLE_VALIDACION;
					}else{
						if (this.logear) 
							log.info("validacion VN10030:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
						//apvVO.setRegimen(Constants.REGIMEN_APV_B);
						return 10033;//REGIMEN APV Vol - ENTIDAD APV1 VACION PERO CON REGIMEN
					}
				}
									
			if(apvVO.getIdEntidadApv() > 0){
				if (codigo.equals("")){
					if (this.logear) 
						log.info("validacion VN10030:Regimen APV recibido vacio, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10032;//REGIMEN APV Vol - NO INFORMADO
				}
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return this.COD_CUMPLE_VALIDACION;
					
				}else{
					if (this.logear) 
						log.info("validacion VN10030:Regimen APV recibido Invalido,  se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10031;//REGIMEN APV Vol - INVALIDO
				}
			}			
			return this.COD_CUMPLE_VALIDACION;
			
		} catch(Exception e){
			log.error("error validacion VN10030::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			ApvVO apv = cotizante.getApv(0);
			if (apv == null || apv.getIdEntidadApv() == Constants.SIN_APV)//no hay valor, no se valida nada mas
			{
				this.resultado = "fin";
				if (this.logear) log.info("validacion VN9080:vacio:no se valida mas::");
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}	

			if(Constants.REGIMEN_APV_B == apv.getRegimen() || Constants.REGIMEN_APV_A == apv.getRegimen()){
			
			}else{
				
				if (this.logear) log.info("validacion VN9080 ERROR REGIMEN APV - INVALIDO:" + apv.getRegimen() + "::");
			  	return 9081;//REGIMEN APV Vol- INVALIDO
			}
					
			if (this.logear) log.info("validacion VN10030 OK:" + apv.getIdEntidadApv() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN10030::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN10030(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN10030(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN10030(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
