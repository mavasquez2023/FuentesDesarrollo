package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarTipoCargaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN120 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN120.class);
	/*
	 * 1 parametro = VN120: Número cargas simples si tiene problemas registra error, pero sigue con la siguiente validación
	 * 
	 * Mensajes
	 * 		119: Número de Cargas simples invalido             
	 * 		120: Número de Cargas con discapacidad invalido
	 * 		121: Número de Cargas Maternales invalido
	 *      363: NUMERO CARGAS SIMPLE NO CORRESPONDE EN EMPRESA PUBLICA
	 *      901: CARGA SIMPLE NO COINCIDE CON LO REPORTADO EN EL SISTEMA 
	 */

	public int valida(CotizanteVO cotizante) {
		return this.valida(cotizante, false);
	}

	public int validaFromWEB(CotizanteVO cotizante) {
		return this.valida(cotizante, true);
	}

	public int valida(CotizanteVO cotizante, boolean isWeb) {
		try {
			this.resultado = "";
			int numCargas = 0;
			
			if (isWeb) {
				numCargas = cotizante.getNumCargaSimple();
			} else {

				if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
					return this.COD_CUMPLE_VALIDACION;

				if (this.parametros == null || this.parametros.size() != 1)
					return this.SIN_CONCEPTOS;

				ConceptoVO c = (ConceptoVO)this.parametros.get(0);
				this.mensaje = c.getNombre();
				numCargas = asignaValor(c.getValor());
			}

			if (numCargas < 0) {
				if (this.logear)
					log.info("validacion VN120 ERR: NumCargaSimple invalido:" + numCargas + "::");
        		return 119; //Número de Cargas simples invalido (no es número ni vacío)
			}
			cotizante.setNumCargaSimple(numCargas);

			//Marco. Valida número de cargas empresa pública
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es pública
			if (isPrivada == 1 && numCargas > 0) { //empresa pública
				if (this.logear)
					log.info("validacion VN120 Err informa cantidad de cargas simple y es empresa publica");
				return 363; // Número cargas no corresponde empresa pública
			}

			//TODO Validación Cargas Familiares
			int idCcaf = this.datosConvenio.getIdCcaf();

			//15/02/2012
			//Si la empresa tiene carga (está en CARGA_FAMILIAR), pero NO para la caja del convenio, sino para OTRA, se debe mostrar nuevo aviso
			//22/02/2012
			//Se mueve validación a VN140, ya que el aviso depende de si tiene al menos una carga.
			try {
				int [] cajasCargadas = (int []) this.cargasFamiliares.get(cotizante.getRutEmpresa() + "");
			} catch (Exception e) {
				// La exception es para el caso en que la empresa no presente ninguna carga en CARGA_FAMILIAR
				if (idCcaf == Constants.ID_CCAF_SINCAJA || idCcaf == Constants.ID_CCAF_LOSANDES) {
					if (this.logear)
						log.info("validacion VN140 OK:NumCargaMaterna:" + numCargas + "::");
					return this.COD_CUMPLE_VALIDACION;
				}
			}			
			/*int [] cajasCargadas = (int []) this.cargasFamiliares.get(new Integer(cotizante.getRutEmpresa()));
			if (cajasCargadas != null) {

				boolean cajaEncontrada = false;
				for (int i = 0; i < cajasCargadas.length; i++) {
					if (cajasCargadas[i] == idCcaf) {
						cajaEncontrada = true;
						break;
					}
				}

				if (!cajaEncontrada) {
					this.resultado = "E";
					if (this.logear)
						log.info("validacion VN120 OK:NumCargaSimple:" + numCargas + "::");
					this.mensaje = "LAS CARGAS SE ENCUENTRAN PARA LA CAJA&" + cajasCargadas[0] + "&";
					return 904; // LA EMPRESA PRESENTA CARGA PARA OTRA CAJA
				}
			} else {
				// La exception es para el caso en que la empresa no presente ninguna carga en CARGA_FAMILIAR
				if (idCcaf == Constants.ID_CCAF_SINCAJA || idCcaf == Constants.ID_CCAF_LOSANDES) {
					if (this.logear)
						log.info("validacion VN120 OK:NumCargaSimple:" + numCargas + "::");
					return this.COD_CUMPLE_VALIDACION;
				}
			}*/

			CargaFamiliarVO carga = (CargaFamiliarVO) this.cargasFamiliares.get(idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante());

			//TODO 21/02/2012.
			//Se solicita que este error solo se muestre para aquellos trabajadores que tengan al menos un tipo de carga (simple, maternal o invalidez)
			//Se validará esta condición en VN140, ya que solo en dicho VN puedo obtener la cantidad de cargas informadas en la nómina para el trabajador.
			/*if (carga == null) {
				this.resultado = "E";
				if (this.logear)
					log.info("Validacion VN940 ERROR: Para la empresa, trabajador y caja, no existen registros de cargas familiares::");
				return 900;
			} else {*/
			if (carga != null) {
				List detallesCarga = carga.getTiposCargas();
				CargaFamiliarTipoCargaVO detalleCarga = new CargaFamiliarTipoCargaVO();

				for (Iterator it = detallesCarga.iterator(); it.hasNext();) {
					detalleCarga = (CargaFamiliarTipoCargaVO) it.next();
					if (detalleCarga.getIdTipoCarga() == Constants.TIPO_CARGA_SIMPLE) {
						if (detalleCarga.getNumeroCargas() != numCargas) {
							if (this.logear)
								log.info("Validacion VN120 ERROR: La cantidad de Cargas Simple (" + numCargas + ") no coinciden con las del sistema (" + detalleCarga.getNumeroCargas() +")::");
							return 901; //CARGA SIMPLE NO COINCIDE CON LO REPORTADO EN EL SISTEMA
						}
						break;
					}
				}
			}
			
			/*int idCcaf = this.datosConvenio.getIdCcaf();
			int i;
			List listadoCargas = this.session.createCriteria(CargaFamiliarVO.class)
											 .add(Restrictions.eq("rutEmpresa", new Integer(cotizante.getRutEmpresa())))
											 .add(Restrictions.eq("rutTrabajador", new Integer(cotizante.getIdCotizante())))
											 .add(Restrictions.eq("idEntidadCCAF", new Integer(idCcaf)))
											 .list();

			if (listadoCargas == null || listadoCargas.size() == 0) {
				this.resultado = "E";
				if (this.logear)
					log.info("Validacion VN120 ERROR: Para la empresa, trabajador y caja, no existen registros de cargas familiares::");
				return 900;	
			} else {
				for (i = 0 ; i < listadoCargas.size() ; i++) {
					CargaFamiliarVO carga = (CargaFamiliarVO) listadoCargas.get(i);
					CargaFamiliarTipoCargaVO detalleCarga = (CargaFamiliarTipoCargaVO)this.session.createCriteria(CargaFamiliarTipoCargaVO.class)
															 									  .add(Restrictions.eq("idCargaFamiliar", new Integer(carga.getIdCargaFamiliar())))
															 									  .add(Restrictions.eq("idTipoCarga", new Integer(Constants.TIPO_CARGA_SIMPLE)))
															 									  .uniqueResult();
					if (detalleCarga.getNumeroCargas() != numCargas) {
						if (this.logear)
							log.info("Validacion VN120 ERROR: La cantidad de Cargas Simple (" + numCargas + ") no coinciden con las del sistema (" + detalleCarga.getNumeroCargas() +")::");
						return 901; //CARGA SIMPLE NO COINCIDE CON LO REPORTADO EN EL SISTEMA
					}
				}
			}*/

			if (this.logear)
				log.info("validacion VN120 OK:NumCargaSimple:" + numCargas + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e) {
			log.error("error validacion VN120::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	/*public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getTipoProceso() == 'D' || cotizante.isAfpVoluntario())
				return this.COD_CUMPLE_VALIDACION;

			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO)this.parametros.get(0);
			this.mensaje = c.getNombre();
			int numCargas = asignaValor(c.getValor());
			
			if (numCargas < 0)
			{
				if (this.logear)
					log.info("validacion VN120 ERR: NumCargaSimple invalido:" + c.getValor() + "::");
        		return 119; //Numero de Cargas simples invalido (no es numero ni vacio)
			}
			cotizante.setNumCargaSimple(numCargas);
			
			// marco valida numero de cargas empresa publica  	
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica 
			if (isPrivada == 1 && numCargas >0 ) //empresa publica
			{
				if (this.logear)
					log.info("validacion VN120 Err informa cantidad de cargas simple y es empresa publica");			
				return 363; // Numero cargas no corresponde empresa publica			
			}

			if (this.logear)
				log.info("validacion VN120 OK:NumCargaSimple:" + numCargas + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN120::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getNumCargaSimple() < 0)
			{
				if (this.logear)
					log.info("validacion VN120 ERR: NumCargaSimple invalido:" + cotizante.getNumCargaSimple() + "::");
        		return 119; //Numero de Cargas simples invalido (no es numero ni vacio)
			}
			// MARCO valida numero de cargas y empresa 
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica 
			if (isPrivada == 1 && cotizante.getNumCargaSimple() > 0) //empresa publica,
			{
				if (this.logear)
					log.info("validacion VN120 Err informa cantidad de cargas simple y es empresa publica");			
				return 363; // Numero cargas no corresponde empresa publica			
			}
			
			if (this.logear)
				log.info("validacion VN120 OK:NumCargaSimple:" + cotizante.getNumCargaSimple() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN120::", e);
			return this.CAIDA_SISTEMA;
		}
	}*/

	public VN120(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN120(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio, HashMap cargasFamiliares) {
		super(parametrosNegocio, session, datosConvenio, cargasFamiliares);
	}

	public VN120(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN120(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
	
	public VN120(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones, HashMap cargasFamiliares) {
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones, cargasFamiliares);
	}
}
