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

public class VN450 extends Validacion
{
	private static Logger log = Logger.getLogger(VN450.class);
	/*
	 * 1 parametro = VN450: entidad ahorro APV 2
	 * 
	 * Mensajes
	 * 		1691: Codigo Entidad de Ahorro Previsional Voluntario invalido
	 * 		1701: Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo

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
			//clillo 03/03/15 si empresa envía varios ceros se considera como uno solo (es decir, sin APV)
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
					log.info("validacion VN450:codigo recibido vacio, se asigna codigo 'sin APV'::");
				cotizante.addApv(Constants.SIN_APV);
				return this.COD_CUMPLE_VALIDACION;
			}
			
			//Si vienen solo números, los convierto a int
			int codigoTemp=-1000; //por defecto...
			try{
				codigoTemp = Integer.parseInt(codigo.trim());
			}catch (Exception e) {
				// TODO: handle exception
			}
    		
			if(codigoTemp==0) //Si el valor resultante es cero, entonces se lo seteo al código
				codigo = String.valueOf( 0 );			
			
			if (!this.mapeoApv.containsKey(codigo.trim()) && !codigo.trim().equals("0"))
        	{
        		if (this.logear) log.info("validacion VN450 ERR:no se encontro APV1:codigo recibido:" + codigo + "::");
				cotizante.addApv(Constants.APV_INVALIDO);
        		return 1700 + cotizante.getApvList().size() - 1;//Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo        	
        	}
        	if(codigo.equals("0"))
        		cotizante.addApv(Constants.SIN_APV);
        	else
        	{
        		MapeoVO mApv = (MapeoVO)this.mapeoApv.get(codigo.trim());
        		cotizante.addApv(mApv.getId());
        	   	if (this.logear) log.info("validacion VN450 OK: APV1:codigo recibido:" + codigo + ":entidad:" + mApv.getId() + "::");
        	}
        	       	
        	
        	return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN450::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			ApvVO apv = cotizante.getApv(1);
			if (apv == null)//no hay valor, no se valida nada mas
			{
				this.resultado = "fin";
				if (this.logear) log.info("validacion VN450 : no se valida mas::");
				//cotizante.addApv(Constants.SIN_APV);
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}
			if (this.session.get(EntidadApvVO.class, new Integer(apv.getIdEntidadApv())) == null)
			{
				if (this.logear) log.info("validacion VN450 ERROR getIdEntApv no encontrada:" + apv.getIdEntidadApv() + "::");
			  	return 169;//Monto APV invalido
			}
			if (apv.getIdEntidadApv() == Constants.SIN_APV)//no hay valor, no se valida nada mas
			{
				this.resultado = "fin";
				if (this.logear) log.info("validacion VN450 : codigo:" + apv.getIdEntidadApv() + ": no se valida mas::");
				//cotizante.addApv(Constants.SIN_APV);
				cotizante.setApvList(Utils.limpiaListaApv(cotizante.getApvList()));
				return this.COD_CUMPLE_VALIDACION;				
			}
			this.resultado = "";
			if (this.logear) log.info("validacion VN450 OK::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN450::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN450(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN450(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN450(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
