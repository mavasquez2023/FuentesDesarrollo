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

public class VN10020 extends Validacion
{
	private static Logger log = Logger.getLogger(VN10020.class);
	/*
	 * 1 parametro = VN10020 regimen APV 5
	 * 
	 * Mensajes
	 * 		10021 REGIMEN APV - INVALIDO
	 * 		10022 REGIMEN APV - NO INFORMADO
	 * 		10023 REGIMEN APV1 - ENTIDAD APV1 VACION PERO CON REGIMEN
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
 
			ApvVO apvVO = cotizante.getApv(4);
			String codigo = c.getValor().trim();

			if(apvVO.getIdEntidadApv() <= 0){
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					if (this.logear) 
						log.info("validacion VN10020:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return 10023;//REGIMEN APV5 - ENTIDAD APV1 VACION PERO CON REGIMEN
				}else if(apvVO.getIdEntidadApv() == -1 && codigo.equals("")){
					return this.COD_CUMPLE_VALIDACION;
				}else{
					if (this.logear) 
						log.info("validacion VN10020:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10023;//REGIMEN APV5 - ENTIDAD APV1 VACION PERO CON REGIMEN
				}
			}			
			if(apvVO.getIdEntidadApv() > 0){
				if (codigo.equals("")){
					if (this.logear) 
						log.info("validacion VN10020:Regimen APV recibido vacio, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10022;//REGIMEN APV5 - NO INFORMADO
				}
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return this.COD_CUMPLE_VALIDACION;
					
				}else{
					if (this.logear) 
						log.info("validacion VN10020:Regimen APV recibido Invalido,  se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					return 10021;//REGIMEN APV5 - INVALIDO
				}
			}			
			return this.COD_CUMPLE_VALIDACION;
			
		} catch(Exception e){
			log.error("error validacion VN10020::", e);
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
				if (this.logear) log.info("validacion VN10020:vacio:no se valida mas::");
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}	

			if(Constants.REGIMEN_APV_B == apv.getRegimen() || Constants.REGIMEN_APV_A == apv.getRegimen()){
			
			}else{
				
				if (this.logear) log.info("validacion VN10020 ERROR REGIMEN APV - INVALIDO:" + apv.getRegimen() + "::");
			  	return 10021;//REGIMEN APV5 - INVALIDO
			}
					
			if (this.logear) log.info("validacion VN10020 OK:" + apv.getIdEntidadApv() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN10020::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN10020(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN10020(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN10020(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
