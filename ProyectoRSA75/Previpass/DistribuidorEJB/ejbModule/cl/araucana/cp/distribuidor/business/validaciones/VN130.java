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

public class VN130 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN130.class);
	/*
	 * 1 parametro = VN130: Número cargas discapacidad si tiene problemas registra error, pero sigue con la siguiente validación
	 * 
	 * Mensajes
	 * 		119: Número de Cargas simples invalido             
	 * 		120: Número de Cargas con discapacidad invalido
	 * 		121: Número de Cargas Maternales invalido
	 *      364: NUMERO CARGAS CON DISCAPACIDAD NO CORRESPONDE EMP. PUBLICA
	 * 		903: CARGA CON INVALIDEZ NO COINCIDE CON LO REPORTADO EN EL SISTEMA
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
				numCargas = cotizante.getNumCargaInvalidez();
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
					log.info("validacion VN130 ERR: NumCargaInvalidez invalido:" + numCargas + "::");
        		return 120; //Número de Cargas discapacidad invalido (no es numero ni vacio)
			}
			cotizante.setNumCargaInvalidez(numCargas);

			//Marco. Valida número de cargas empresa pública
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es pública
			if (isPrivada == 1 && numCargas > 0) { //empresa pública
				if (this.logear)
					log.info("validacion VN130 Err informa cantidad de cargas discapacidad y es empresa publica");
				return 364; // Número cargas con discapacidad no corresponde empresa pública
			}

			//TODO Validación Cargas Familiares
			int idCcaf = this.datosConvenio.getIdCcaf();

			//15/02/2012
			//Si la Empresa NO tiene caja, se saltan las validaciones
			try {
				int [] cajasCargadas = (int []) this.cargasFamiliares.get(cotizante.getRutEmpresa() + "");
			} catch (Exception e) {
				// La exception es para el caso en que la empresa no presente ninguna carga en CARGA_FAMILIAR
				if (idCcaf == Constants.ID_CCAF_SINCAJA || idCcaf == Constants.ID_CCAF_LOSANDES) {
					if (this.logear)
						log.info("validacion VN130 OK:NumCargaInvalidez:" + numCargas + "::");
					return this.COD_CUMPLE_VALIDACION;
				}
			}
			
			CargaFamiliarVO carga = (CargaFamiliarVO) this.cargasFamiliares.get(idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante());

			if (carga != null) {
				List detallesCarga = carga.getTiposCargas();
				CargaFamiliarTipoCargaVO detalleCarga = new CargaFamiliarTipoCargaVO();

				for (Iterator it = detallesCarga.iterator(); it.hasNext();) {
					detalleCarga = (CargaFamiliarTipoCargaVO) it.next();
					if (detalleCarga.getIdTipoCarga() == Constants.TIPO_CARGA_INVALIDEZ) {
						if (detalleCarga.getNumeroCargas() != numCargas) {
							if (this.logear)
								log.info("Validacion VN130 ERROR: La cantidad de Cargas de Invalidez (" + numCargas + ") no coinciden con las del sistema (" + detalleCarga.getNumeroCargas() +")::");
							return 903; //CARGA CON INVALIDEZ NO COINCIDE CON LO REPORTADO EN EL SISTEMA
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

			if ((listadoCargas != null || listadoCargas.size() > 0)) {
				for (i = 0 ; i < listadoCargas.size() ; i++) {
					CargaFamiliarVO carga = (CargaFamiliarVO) listadoCargas.get(i);
					CargaFamiliarTipoCargaVO detalleCarga = (CargaFamiliarTipoCargaVO)this.session.createCriteria(CargaFamiliarTipoCargaVO.class)
															 									  .add(Restrictions.eq("idCargaFamiliar", new Integer(carga.getIdCargaFamiliar())))
															 									  .add(Restrictions.eq("idTipoCarga", new Integer(Constants.TIPO_CARGA_INVALIDEZ)))
															 									  .uniqueResult();
					if (detalleCarga.getNumeroCargas() != numCargas) {
						if (this.logear)
							log.info("Validacion VN120 ERROR: La cantidad de Cargas de Invalidez (" + numCargas + ") no coinciden con las del sistema (" + detalleCarga.getNumeroCargas() +")::");
						return 903; //CARGA CON INVALIDEZ NO COINCIDE CON LO REPORTADO EN EL SISTEMA
					}
				}
			}*/

			if (this.logear)
				log.info("validacion VN130 OK:NumCargaInvalidez:" + numCargas + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e) {
			log.error("error validacion VN130::", e);
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
					log.info("validacion VN130 ERR: NumCargaInvalidez invalido:" + c.getValor() + "::");
        		return 120; //Numero de Cargas discapacidad invalido (no es numero ni vacio)
			}
			cotizante.setNumCargaInvalidez(numCargas);
			// marco valida numero de cargas empresa publica  	
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica 
			if (isPrivada == 1 && numCargas > 0) //empresa publica,
			{
				if (this.logear)
					log.info("validacion VN130 Err informa cantidad de cargas discapacidad y es empresa publica");
				return 364; // Numero cargas con discapacidad no corresponde empresa publica			
			}
			
			
			
			if (this.logear)
				log.info("validacion VN130 OK:NumCargaInvalidez:" + numCargas + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN130::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getNumCargaInvalidez() < 0)
			{
				if (this.logear)
					log.info("validacion VN130 ERR NumCargaInvalidez:" + cotizante.getNumCargaInvalidez() + "::");
				return 120;//Numero de Cargas con discapacidad invalido
			}

			// MARCO valida numero de cargas y empresa 
			int isPrivada = ((Integer)this.parametrosNegocio.get("empresaPrivada")).intValue(); //si es publica 
			if (isPrivada == 1  && cotizante.getNumCargaInvalidez() > 0 ) //empresa publica,
			{
				if (this.logear)
					log.info("validacion VN1300 Err informa cantidad de cargas con Discapacidad y es empresa publica");			
				return 364; // Numero cargas con discapacidad no corresponde empresa publica	
			}
						
			if (this.logear)
				log.info("validacion VN130 OK:NumCargaInvalidez:" + cotizante.getNumCargaInvalidez() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e)
		{
			log.error("error validacion VN130::", e);
			return this.CAIDA_SISTEMA;
		}
	}*/

	public VN130(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN130(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio, HashMap cargasFamiliares) {
		super(parametrosNegocio, session, datosConvenio, cargasFamiliares);
	}

	public VN130(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN130(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
	
	public VN130(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones, HashMap cargasFamiliares) {
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones, cargasFamiliares);
	}
}
