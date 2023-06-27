package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

public class VN660 extends Validacion
{
	private static Logger log = Logger.getLogger(VN660.class);

	/*
	 * 1 parametro= VN660: deposito convenido
	 * 
	 * 
	 * Mensajes
	 * 		194: MONTO DE DEPOSITO CONVENIDO INVALIDO
	 * 		195: MONTO DE DEPOSITO CONVENIDO INCORRECTO
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";

			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			//if (cotizante.getTipoProceso() != 'D' && (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int monto = asignaValor(c.getValor());

			return valida(monto, c.getValor(), cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN660::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(int monto, String valor, CotizanteVO cotizante) {
		
		//if (monto < 0 || (monto == 0 && this.getIdEntDep(cotizante) != -1) ){
		if (monto < 0 || (monto == 0 && this.getIdEntDep(cotizante) > 0) ){ //Esto es: distinto de -1 y 0
			if (this.logear)
				log.info("validacion VN660 deposito convenido ERR:valor recibido:" + valor + "::");
			return 194; //MONTO DE DEPOSITO CONVENIDO INVALIDO
		}

		/*if (monto < 0) {
			if (this.logear)
				log.info("validacion VN660 deposito convenido ERR:valor recibido:" + valor + "::");
			return 194; //MONTO DE DEPOSITO CONVENIDO INVALIDO
		}*/

		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setDepositoConvenido(monto);
			
		}
		if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setDepositoConvenido(monto);
		}

		if (this.logear)
			log.info("validacion VN660 OK:deposito convenido:" + monto + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			int monto = 0;
			if (cotizante.getTipoProceso() == 'D') {
				CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
				monto = cotizacion.getDepositoConvenido();
			} else if (cotizante.getTipoProceso() == 'R') {
				CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
				monto = cotizacion.getDepositoConvenido();
			}

			return valida(monto, "" + monto, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN660::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int getIdEntDep(CotizanteVO cotizante){
		int idEntDep = 0;

		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			idEntDep = cotizacion.getIdEntDep();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			idEntDep = cotizacion.getIdEntDep();
		}
		return idEntDep;
		
	}
	
	public VN660(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN660(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN660(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
