package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.core.util.NamesParser;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN1300 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1300.class);

	/*
	 * 1 parametro= VN1300: apellidos trabajador dependiente afiliacion voluntaria
	 * 						valida que todos los otros valores de afiliacion voluntaria sean correctos
	 * 
	 * Mensajes 
	 * 		169: Codigo Entidad de Ahorro Previsional Voluntario invalido
	 * 		170: Codigo Entidad de Ahorro Prev. Volun. no aparece en mapeo
	 * 		310: RUT TRABAJADOR DEPENDIENTE DEBE EXISTIR
	 * 		311: RUT TRABAJADOR DEPENDIENTE INVALIDO
	 * 		312: NOMBRE TRABAJADOR DEPENDIENTE INVALIDO
	 * 		313: Apellidos Trabajador dependiente invalidos
	 * 		347: apellidos no encontrados en apellidos compuestos
	 * 		348: apellidos no separables
	 * 
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (!cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;
			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			Utils utils = new Utils();
			if (utils.procesaTexto(true, c.getValor()) != Constants.TEXTO_OK)
			{
				if (this.logear)
					log.info("validacion VN1300 ERR: Apellidos invalidos:" + c.getValor() + "::");
				cotizacion.setAfpvAplldioPatDpndiente(c.getValor());
				return 313; //APELLIDOS INVALIDOS
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
				cotizacion.setAfpvAplldioPatDpndiente(valor);
				return 347; //apellidos no encontrados en apellidos compuestos
			}
			else if (names.length < 2)
			{
				if (this.logear)
					log.info("validacion VN1300 ERR:  Apellidos:valor recibido:" + valor + "::");
				cotizacion.setAfpvAplldioPatDpndiente(valor);
				return 348; //apellidos no separables
			}
			else 
			{
				if (this.logear)
					log.info("validacion VN1300: ap paterno:" + names[0] + ":apMaterno:" + names[1] + "::");
				cotizacion.setAfpvAplldioPatDpndiente(names[0].substring(0, (Math.min(20, names[0].length()))));
				cotizacion.setAfpvAplldioMatDpndiente(names[1].substring(0, (Math.min(20, names[1].length()))));

				if (names.length == 3 && this.logear)
				{
					log.info("validacion VN1300: Apellidos: name learned '" + names[2] + "'");
					List apellAprendidos = (List)this.parametrosNegocio.get("apellAprendidos");
					apellAprendidos.add(names[2]);
				}
			}

			if (this.logear)
				log.info("validacion VN1300 OK:ApellidoPat:" + cotizacion.getAfpvAplldioPatDpndiente() + " :ApellidoMat:" + cotizacion.getAfpvAplldioMatDpndiente() + "::");

			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN1300::", e);
			return this.CAIDA_SISTEMA;
		}		
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			//este valor ya debe venir validado y asignado => no hay nada que hacer aca
			this.resultado = "";
			if (!cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			if (cotizacion.getAfpvAplldioPatDpndiente().equals("") || cotizacion.getAfpvAplldioMatDpndiente().equals(""))
				return 313; //Apellidos invalidos
			if (this.logear)
				log.info("validacion VN1300 OK:  Apellidos:" + cotizacion.getAfpvAplldioPatDpndiente() + ":" + cotizacion.getAfpvAplldioMatDpndiente() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN1300::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN1300(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1300(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1300(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}	
}
