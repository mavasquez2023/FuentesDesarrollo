package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN1100 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1100.class);

	/*
	 * 1 parametro= VN1100: rut trabajador dependiente afiliacion voluntaria
	 * 103	RUT invalido
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "F";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();

			String valor = c.getValor().replace(' ', '-');
			String guion[] = valor.split("-");
			if (guion.length == 2)
			{
				valor = guion[0]+guion[1];
				
				//csanchez. Si RUT es igual a cero, se salta la validación
				try{
					int entero = Integer.parseInt(valor);
					if (entero == 0)
						return this.COD_CUMPLE_VALIDACION;
				} catch(Exception ex) {
					if (this.logear)
						log.info("validacion VN1100 ERR: RUT trabajador invalido :valor recibido:" + c.getValor() + "::");
					return 310; //RUT invalido
				}
				
			} else if (guion.length > 2)
			{
				if (this.logear)
					log.info("validacion VN1100 ERR: RUT trabajador Invalido:" + valor + "::");
				return 310; //RUT invalido			
			} 
						
			if (valor.equals(""))
			{
				if (this.logear)
					log.info("validacion VN1100 ERR: RUT trabajador:valor recibido:" + c.getValor() + "::");
				return 310; //RUT invalido
			}
			if (valor.length() < 8)
			{
				if (this.logear)
					log.info("validacion VN1100 ERR: RUT trabajador menor a xxx.xxx-x :valor recibido:" + c.getValor() + "::");
				return 310; //RUT invalido
			}
			if (guion.length == 2)
			{
				try{
					Integer.parseInt(guion[0]);
				} catch(Exception ex)
				{
					if (this.logear)
						log.info("validacion VN1100 ERR: RUT trabajador invalido :valor recibido:" + c.getValor() + "::");
					return 310; //RUT invalido
				}
			} else if (guion.length == 1)
			{
				try{
					Integer.parseInt(guion[0].substring(0,guion[0].length()-1));
				} catch(Exception ex)
				{
					if (this.logear)
						log.info("validacion VN1100 ERR: RUT trabajador invalido :valor recibido:" + c.getValor() + "::");
					return 310; //RUT invalido
				}
			}
			((CotizacionREVO)cotizante.getCotizacion()).setAfpvRutDpndiente(this.setter.transformaRut(valor));
			if (!RutValidacion.valida(valor))
			{
				if (this.logear)
					log.info("validacion VN1100 ERR: RUT : Digito verificador invalido:" + c.getValor() + "::");
				return 311; //RUT invalido
			}			
			if (this.logear)
				log.info("validacion VN1100 OK: RUT trabajador:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN1100::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			//este rut ya debe venir validado, y sin DV => no hay nada que hacer aca
			this.resultado = "F";
			if (this.logear)
				log.info("validacion VN1100 OK:rutAFPVDependiente:" + ((CotizacionREVO)cotizante.getCotizacion()).getAfpvRutDpndiente() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN1100::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN1100(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1100(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1100(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
