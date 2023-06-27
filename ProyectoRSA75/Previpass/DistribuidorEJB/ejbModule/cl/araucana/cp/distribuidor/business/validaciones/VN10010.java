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

public class VN10010 extends Validacion
{
	private static Logger log = Logger.getLogger(VN10010.class);
	/*
	 * 1 parametro = VN10010 regimen APV 4
	 * 
	 * Mensajes
	 * 		10011 REGIMEN APV4 - INVALIDO
	 * 		10012 REGIMEN APV4 - NO INFORMADO
	 * 		10013 REGIMEN APV4 - ENTIDAD APV1 VACION PERO CON REGIMEN
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
 
			ApvVO apvVO = cotizante.getApv(3);
			String codigo = c.getValor().trim();

			if(apvVO.getIdEntidadApv() <= 0){
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					if (this.logear) 
						log.info("validacion VN10010:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return 10013;//REGIMEN APV4 - ENTIDAD APV1 VACION PERO CON REGIMEN
				}else if(apvVO.getIdEntidadApv() == -1 && codigo.equals("")){
					return this.COD_CUMPLE_VALIDACION;
				}else{
					if (this.logear) 
						log.info("validacion VN10010:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10013;//REGIMEN APV4 - ENTIDAD APV1 VACION PERO CON REGIMEN
				}
			}			
			if(apvVO.getIdEntidadApv() > 0){
				if (codigo.equals("")){
					if (this.logear) 
						log.info("validacion VN10010:Regimen APV recibido vacio, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10012;//REGIMEN APV4 - NO INFORMADO
				}
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return this.COD_CUMPLE_VALIDACION;
					
				}else{
					if (this.logear) 
						log.info("validacion VN10010:Regimen APV recibido Invalido,  se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10011;//REGIMEN APV4 - INVALIDO
				}
			}			
			return this.COD_CUMPLE_VALIDACION;
			
		} catch(Exception e){
			log.error("error validacion VN10010::", e);
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
				if (this.logear) log.info("validacion VN10010:vacio:no se valida mas::");
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}	

			if(Constants.REGIMEN_APV_B == apv.getRegimen() || Constants.REGIMEN_APV_A == apv.getRegimen()){
			
			}else{
				
				if (this.logear) log.info("validacion VN10010 ERROR REGIMEN APV - INVALIDO:" + apv.getRegimen() + "::");
			  	return 10011;//REGIMEN APV4 - INVALIDO
			}
					
			if (this.logear) log.info("validacion VN10010 OK:" + apv.getIdEntidadApv() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN10010::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN10010(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN10010(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN10010(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
