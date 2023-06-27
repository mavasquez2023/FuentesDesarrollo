package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.core.util.NamesParser;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RutEspecialesVO;

public class VN040 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN040.class);
	/*
	 * 1 parametro = VN040: Apellidos del trabajador
	 * 
	 * Mensajes
	 * 		105: Apellidos no separables
	 * 		106: Apellidos no encontrados en apellidos compuestos
	 * 		107: Apellidos Invalidos
	 * 		233: Datos no coinciden con RUT Especial
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			Utils utils = new Utils();
			if (utils.procesaTexto(true, c.getValor()) != Constants.TEXTO_OK)
			{
				if (this.logear)
					log.info("validacion VN040 ERR: Apellidos invalidos:" + c.getValor() + "::");
				cotizante.setApellidoPat(c.getValor());
				return 107; //APELLIDOS INVALIDOS
			}
			String valor = utils.getValor();

			NamesParser parser = (NamesParser)this.parametrosNegocio.get("NamesParser");		
			String[] names = parser.parse(valor);
			if (names != null && names.length == 3)
				parser.add(names[2]);
			if (names == null)
			{
				if (this.logear)
					log.info("validacion VN040 ERR:  Apellidos:valor recibido:" + valor + "::");
				cotizante.setApellidoPat(valor);
				return 106; //apellidos no encontrados en apellidos compuestos
			}
			//else if (names.length < 2)
			else if (names.length == 1)
			{
				if (this.logear)
					log.info("validacion VN040:  Apellido paterno :valor recibido:" + valor + "::");
				//cotizante.setApellidoPat(valor);
				cotizante.setApellidoPat(names[0].substring(0, (Math.min(20, names[0].length()))));
				//return 105; //apellidos no separables
			}
			else 
			{
				if (this.logear)
					log.info("validacion VN040: ap paterno:" + names[0] + ":apMaterno:" + names[1] + "::");
				cotizante.setApellidoPat(names[0].substring(0, (Math.min(20, names[0].length()))));
				cotizante.setApellidoMat(names[1].substring(0, (Math.min(20, names[1].length()))));

				if (names.length == 3 && this.logear)
				{
					log.info("validacion VN040: Apellidos: name learned '" + names[2] + "'");
					List apellAprendidos = (List)this.parametrosNegocio.get("apellAprendidos");
					apellAprendidos.add(names[2]);
				}
			}

			int respuesta = validaRutEspeciales(cotizante);			
			if (this.logear && respuesta != 0)
				log.info("validacion VN040: Rut Especiales ERR:IdCotizante:" + cotizante.getIdCotizante() + ":Nombre:" + cotizante.getNombre() + " :ApellidoPat:" + cotizante.getApellidoPat() + " :ApellidoMat:" + cotizante.getApellidoMat() + "::");
			else if (this.logear)
				log.info("validacion VN040 OK:ApellidoPat:" + cotizante.getApellidoPat() + " :ApellidoMat:" + cotizante.getApellidoMat() + "::");

			return respuesta;
		} catch(Exception e)
		{
			log.error("error validacion VN040::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int validaRutEspeciales(CotizanteVO cotizante) 
	{
		List rutEspeciales = (List) this.parametrosNegocio.get("rutEspeciales");
		for (Iterator it = rutEspeciales.iterator(); it.hasNext();)
		{
			RutEspecialesVO rut = (RutEspecialesVO)it.next();
			if (cotizante.getIdCotizante() == rut.getRutEspecial())
			{
				if (!cotizante.getNombre().equals(rut.getNombre().trim()))
				{
					if (this.logear)
						log.info("validacion VN040: Rut Especiales ERR:Nombre no coincide:" + cotizante.getNombre() + "::");
					return 233; //DATOS NO COINCIDEN CON RUT ESPECIAL
				}
				if (!cotizante.getApellidoPat().equals(rut.getApellidoPaterno().trim()))
				{
					if (this.logear)
						log.info("validacion VN040: Rut Especiales ERR:Apellido Parterno no coincide:" + cotizante.getApellidoPat() + "::");
					return 233; //DATOS NO COINCIDEN CON RUT ESPECIAL
				}
				if (!cotizante.getApellidoMat().equals(rut.getApellidoMaterno().trim()))
				{
					if (this.logear)
						log.info("validacion VN040: Rut Especiales ERR:Apellido Materno no coincide:" + cotizante.getApellidoMat() + "::");
					return 233; //DATOS NO COINCIDEN CON RUT ESPECIAL
				}
				return this.COD_CUMPLE_VALIDACION;
			}
		}
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			//este valor ya debe venir validado y asignado => no hay nada que hacer aca
			this.resultado = "";
			if (cotizante.getApellidoPat().equals(""))// || cotizante.getApellidoMat().equals(""))
				return 107; //Apellidos inválidos
			if (this.logear)
				log.info("validacion VN040 OK:  Apellidos:" + cotizante.getApellidoPat() + ":" + cotizante.getApellidoMat() + "::");
			int respuesta = validaRutEspeciales(cotizante);			
			if (this.logear && respuesta != 0)
				log.info("validacion VN040: Rut Especiales ERR:IdCotizante:" + cotizante.getIdCotizante() + ":Nombre:" + cotizante.getNombre() + ":ApellidoPat:" + cotizante.getApellidoPat() + ":ApellidoMat:" + cotizante.getApellidoMat() + "::");
			return respuesta;
		} catch(Exception e)
		{
			log.error("error validacion VN040::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN040(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN040(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN040(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
