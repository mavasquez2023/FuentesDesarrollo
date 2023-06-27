package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN020 extends Validacion
{
	private static Logger log = Logger.getLogger(VN020.class);

	/*
	 * 1 parametro = VN020: RUT trabajador
	 * 
	 * Mensajes: 103: RUT invalido 323: RUT invalido (dígito verificador)
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();

			String valor = c.getValor().replace(' ', '-');
			String guion[] = valor.split("-");
			if (guion.length == 2)
				valor = guion[0] + guion[1];
			else if (guion.length > 2)
			{
				if (this.logear)
					log.info("validacion VN020 ERR: RUT trabajador Invalido:" + valor + "::");
				return 103; // RUT invalido
			}

			if (valor.equals(""))
			{
				if (this.logear)
					log.info("validacion VN020 ERR: RUT trabajador:valor recibido:" + c.getValor() + "::");
				return 103; // RUT invalido
			}
			if (valor.length() < 7)
			{
				if (this.logear)
					log.info("validacion VN020 ERR: RUT trabajador menor a xxx.xxx-x :valor recibido:" + c.getValor() + "::");
				return 103; // RUT invalido
			}
			if (guion.length == 2)
			{
				try
				{
					Integer.parseInt(guion[0]);
				} catch (Exception ex)
				{
					if (this.logear)
						log.info("validacion VN020 ERR: RUT trabajador invalido :valor recibido:" + c.getValor() + "::");
					return 103; // RUT invalido
				}
			} else if (guion.length == 1)
			{
				try
				{
					Integer.parseInt(guion[0].substring(0, guion[0].length() - 1));
				} catch (Exception ex)
				{
					if (this.logear)
						log.info("validacion VN020 ERR: RUT trabajador invalido :valor recibido:" + c.getValor() + "::");
					return 103; // RUT invalido
				}
			}
			cotizante.setIdCotizante(this.setter.transformaRut(valor));
			if (!RutValidacion.valida(valor))
			{
				if (this.logear)
					log.info("validacion VN020 ERR: RUT : Digito verificador invalido:" + c.getValor() + "::");
				return 323; // RUT invalido
			}
			if (this.logear)
				log.info("validacion VN020 OK: RUT trabajador:" + valor + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN020::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			// este rut ya debe venir validado, y sin DV => no hay nada que hacer aca
			this.resultado = "";
			if (this.logear)
				log.info("validacion VN020 OK: RUT trabajador:" + cotizante.getIdCotizante() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN020::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN020(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN020(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN020(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
