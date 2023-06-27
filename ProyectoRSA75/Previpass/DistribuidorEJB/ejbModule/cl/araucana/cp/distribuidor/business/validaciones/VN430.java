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
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN430 extends Validacion
{
	private static Logger log = Logger.getLogger(VN430.class);
	/*
	 * 1 parametro = VN430: entidad ahorro APV 1
	 * 
	 * Mensajes
	 * 		1690: Codigo Entidad de Ahorro Previsional Voluntario invalido
	 * 		1700: Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() != 'R' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String codigo = Utils.transformaCodEnt(c.getValor());
//			clillo 03/03/15 si empresa envía varios ceros se considera como uno solo (es decir, sin APV)
			try {
				int validaceros= Integer.parseInt(codigo);
				if(validaceros==0){
					codigo="0";
				}
			} catch (Exception e) {
			}
			
			if (codigo.equals(""))
			{
				if (this.logear) 
					log.info("validacion VN430:codigo recibido vacio, se asigna codigo 'sin APV'::");
				cotizante.addApv(Constants.SIN_APV);
				return this.COD_CUMPLE_VALIDACION;
			}
			
			
        	if (!this.mapeoApv.containsKey(codigo.trim()) && !codigo.trim().equals("0"))
        	{
        		if (this.logear) log.info("validacion VN430 ERR:no se encontro APV1:codigo recibido:" + codigo + "::");
				cotizante.addApv(Constants.APV_INVALIDO);
        		return 1700 + cotizante.getApvList().size() - 1;//Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo        	
        	}
        	if(codigo.equals("0"))
        		cotizante.addApv(Constants.SIN_APV);
        	else
        	{
        		MapeoVO mApv = (MapeoVO)this.mapeoApv.get(codigo.trim());
        		cotizante.addApv(mApv.getId());
        	   	if (this.logear) 
        	   		log.info("validacion VN430 OK: APV1:codigo recibido:" + codigo + ":entidad:" + mApv.getId() + "::");
        	}

        	
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN430::", e);
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
				if (this.logear) log.info("validacion VN430:vacio:no se valida mas::");
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}
			if (this.session.get(EntidadApvVO.class, new Integer(apv.getIdEntidadApv())) == null)
			{
				if (this.logear) log.info("validacion VN430 ERROR getIdEntApv no encontrada:" + apv.getIdEntidadApv() + "::");
			  	return 1690;//Monto APV invalido
			}
			if (this.logear) log.info("validacion VN430 OK:" + apv.getIdEntidadApv() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN430::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN430(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN430(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN430(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
