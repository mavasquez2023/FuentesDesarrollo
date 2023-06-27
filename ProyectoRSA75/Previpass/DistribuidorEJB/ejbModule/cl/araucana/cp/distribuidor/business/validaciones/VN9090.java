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

public class VN9090 extends Validacion
{
	private static Logger log = Logger.getLogger(VN9090.class);
	/*
	 * 1 parametro = VN9090 regimen APV 2
	 * 
	 * Mensajes
	 * 		9091 REGIMEN APV2 - INVALIDO
	 * 		9092 REGIMEN APV2 - NO INFORMADO
	 * 		9093 REGIMEN APV2 - ENTIDAD APV1 VACION PERO CON REGIMEN
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
 
			//Si el erregle es de largo 2 significa que los errores los debe mostrar en el apv3
			boolean isCotPrevVolun =false;
			if(cotizante.getApvList().size() == 3)
				isCotPrevVolun=true;
			
			ApvVO apvVO = cotizante.getApv(1);
			String codigo = c.getValor().trim();

			if(apvVO.getRegimen() == Constants.REGIMEN_APV_A || apvVO.getRegimen() == Constants.REGIMEN_APV_B){
				return this.COD_CUMPLE_VALIDACION;
			}
			
			if(apvVO.getIdEntidadApv() <= 0){
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					if (this.logear) 
						log.info("validacion VN9090:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					//return  isCotPrevVolun ? 10003 : 9093;//REGIMEN APV2 - ENTIDAD APV1 VACION PERO CON REGIMEN
					return  9093;//REGIMEN APV2 - ENTIDAD APV1 VACION PERO CON REGIMEN
				}else if(apvVO.getIdEntidadApv() == -1 && codigo.equals("")){
					return this.COD_CUMPLE_VALIDACION;
				}else{
				
					if (this.logear) 
						log.info("validacion VN9090:Entidad APV vacio pero con regimen, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					//return isCotPrevVolun ? 10003 : 9093;//REGIMEN APV2 - ENTIDAD APV1 VACION PERO CON REGIMEN
					return 9093;//REGIMEN APV2 - ENTIDAD APV1 VACION PERO CON REGIMEN
					
				}
			}			
			if(apvVO.getIdEntidadApv() > 0){
				if (codigo.equals("")){
					if (this.logear) 
						log.info("validacion VN9090:Regimen APV recibido vacio, se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					//return isCotPrevVolun ? 10002 : 9092;//REGIMEN APV2 - NO INFORMADO
					return 9092;//REGIMEN APV2 - NO INFORMADO
				}
				if(codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_A) || codigo.equalsIgnoreCase(""+Constants.REGIMEN_APV_B)){
					apvVO.setRegimen(codigo.toUpperCase().charAt(0));
					return this.COD_CUMPLE_VALIDACION;
					
				}else{
					if (this.logear) 
						log.info("validacion VN9090:Regimen APV recibido Invalido,  se asigna sin regimen 'sin Regimen APV'::");
					apvVO.setRegimen(Constants.REGIMEN_APV_B);
					//return isCotPrevVolun ? 10001 : 9091;//REGIMEN APV2 - INVALIDO
					return 9091;//REGIMEN APV2 - INVALIDO
				}
			}			
			return this.COD_CUMPLE_VALIDACION;
			
		} catch(Exception e){
			log.error("error validacion VN9090::", e);
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
				if (this.logear) log.info("validacion VN9090:vacio:no se valida mas::");
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}	

			if(Constants.REGIMEN_APV_B == apv.getRegimen() || Constants.REGIMEN_APV_A == apv.getRegimen()){
			
			}else{
				
				if (this.logear) log.info("validacion VN9090 ERROR REGIMEN APV - INVALIDO:" + apv.getRegimen() + "::");
			  	return 9091;//REGIMEN APV - INVALIDO
			}
					
			if (this.logear) log.info("validacion VN9090 OK:" + apv.getIdEntidadApv() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN9090::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN9090(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN9090(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN9090(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
