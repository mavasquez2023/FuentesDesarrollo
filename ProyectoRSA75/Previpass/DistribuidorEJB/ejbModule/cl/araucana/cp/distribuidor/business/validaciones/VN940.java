package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarTipoCargaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class VN940 extends Validacion
{	
	private static Logger log = Logger.getLogger(VN940.class);
	/*
	 * VN940: Valida que el n�mero de Cargas Simples coincida con lo cargado por AdminCCAF
	 * 
	 * Mensajes
	 * 		900: PARA LA EMPRESA, TRABAJADOR Y CAJA NO EXISTEN CARGAS
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

			int idCcaf = this.datosConvenio.getIdCcaf();
			
			CargaFamiliarVO carga = (CargaFamiliarVO) this.cargasFamiliares.get(idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante());
			
			if (carga == null) {
				this.resultado = "E";
				if (this.logear)
					log.info("Validacion VN940 ERROR: Para la empresa, trabajador y caja, no existen registros de cargas familiares::");
				return 900;	
			} else {
				List detallesCarga = carga.getTiposCargas();
				CargaFamiliarTipoCargaVO detalleCarga = new CargaFamiliarTipoCargaVO();

				for (Iterator it = detallesCarga.iterator(); it.hasNext();) {
					detalleCarga = (CargaFamiliarTipoCargaVO) it.next();
					if (detalleCarga.getIdTipoCarga() == Constants.TIPO_CARGA_SIMPLE) {
						if (detalleCarga.getNumeroCargas() != numCargas) {
							if (this.logear)
								log.info("Validacion VN940 ERROR: La cantidad de Cargas Simple (" + numCargas + ") no coinciden con las del sistema (" + detalleCarga.getNumeroCargas() +")::");
							return 901; //CARGA SIMPLE NO COINCIDE CON LO REPORTADO EN EL SISTEMA
						}
						break;
					}
				}
			}

			if (this.logear)
				log.info("validacion VN940 OK:NumCargaSimple:" + numCargas + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e) {
			log.error("error validacion VN940::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN940(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio, HashMap cargasFamiliares) {
		super(parametrosNegocio, session, datosConvenio, cargasFamiliares);
	}
}