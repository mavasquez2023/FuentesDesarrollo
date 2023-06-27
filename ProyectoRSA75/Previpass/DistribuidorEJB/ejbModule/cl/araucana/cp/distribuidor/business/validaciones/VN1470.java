package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN1470 extends Validacion
{
	private static Logger log = Logger.getLogger(VN1470.class);
	/*
	 * 1 parametro = VN1470: Entidad Depósito Convenido
	 * 
	 * Mensajes
	 * 		XXX: Código Entidad Depósito Convenido inválido.
	 * 		234: ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
	 */
	
	public int valida(CotizanteVO cotizante){
		return this.valida(cotizante, false);
	}
	
	public int validaFromWEB(CotizanteVO cotizante){
		return this.valida(cotizante, true);
	}
	
	public int valida(CotizanteVO cotizante, boolean isWeb) {
		try {
			
			this.resultado = "";
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");
			if (cotizante.getTipoProceso() == 'A' || cotizante.getTipoProceso() == 'G' || (cotizante.getTipoProceso() == 'R' && grupoConvenioVO.isPrevired()))
				return this.COD_CUMPLE_VALIDACION;

			if(isWeb) {
				if (!existeEntidad(cotizante)) {
					log.info("validacion VN070 ERROR IdEntAPVReal no encontrada:" + this.getIdEntDep(cotizante) + "::");
					return 234;// ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
				}
				return this.COD_CUMPLE_VALIDACION;
			} else {
				
				if (this.parametros == null || this.parametros.size() != 1)
					return this.SIN_CONCEPTOS;

				ConceptoVO c = (ConceptoVO) this.parametros.get(0);
				this.mensaje = c.getNombre();
				
				if (c.getValor().equals(""))
					return this.COD_CUMPLE_VALIDACION;
				
				// marco cambió clase para soportar el - de rut
				String codigo = Utils.transformaCodEnt(c.getValor() != null ? c.getValor().toUpperCase() : null);
				
				if (cotizante.getTipoProceso() == 'D' || (cotizante.getTipoProceso() == 'R' && !grupoConvenioVO.isPrevired())) {

					Criteria crit = this.session.createCriteria(MapeoAPVVO.class);
					crit.add(Restrictions.eq("idMapaCod", new Integer(this.datosConvenio.getIdMapaCod())));
					List result = crit.add(Restrictions.eq("codigo", codigo)).list();
					//CotizacionDCVO depositoConvenido = (CotizacionDCVO)cotizante.getCotizacion();
					if (result == null || result.size() == 0)
					{
						if (this.logear)
							log.info("validacion VN070 ERR: idEntidadAPV no aparece en mapeo: codigo " + codigo + "::");
						this.setIdEntDep(cotizante, Constants.APVC_FALSO); //depositoConvenido.setIdEntDep(Constants.APVC_FALSO);
						cotizante.setIdEntidadAPVC(codigo);
						return 234; // ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
					}
					MapeoVO mp = (MapeoVO) result.get(0);
					this.setIdEntDep(cotizante, mp.getId());
					cotizante.setIdEntidadAPVC(codigo);
					if (this.logear)
						log.info("validacion VN070 OK:idEntidadAPV:" + this.resultado + ":" + this.getIdEntDep(cotizante) + "::");
					return this.COD_CUMPLE_VALIDACION;
				}
			}
			
			return this.COD_CUMPLE_VALIDACION;
		} catch(Exception e) {
			log.error("error validacion VN1470::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private void setIdEntDep(CotizanteVO cotizante, int idEntDep) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			cotizacion.setIdEntDep(idEntDep);
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			cotizacion.setIdEntDep(idEntDep);
		}
	}

	private int getIdEntDep(CotizanteVO cotizante) {
		if (cotizante.getTipoProceso() == 'D') {
			CotizacionDCVO cotizacion = (CotizacionDCVO) cotizante.getCotizacion();
			return cotizacion.getIdEntDep();
		} else if (cotizante.getTipoProceso() == 'R') {
			CotizacionREVO cotizacion = (CotizacionREVO) cotizante.getCotizacion();
			return cotizacion.getIdEntDep();
		}
		return 0;
	}
	
	private boolean existeEntidad(CotizanteVO cotizante) {
		boolean existeEntidad = false;
		
		if (cotizante.getCotizacion() instanceof CotizacionDCVO) {
			CotizacionDCVO cotizacion = (CotizacionDCVO)cotizante.getCotizacion();
			if (this.session.get(EntidadApvVO.class, new Integer(cotizacion.getIdEntDep())) != null)
				existeEntidad = true;
		} else if (cotizante.getCotizacion() instanceof CotizacionREVO) {
			CotizacionREVO cotizacion = (CotizacionREVO)cotizante.getCotizacion();
			if (this.session.get(EntidadApvVO.class, new Integer(cotizacion.getIdEntDep())) != null)
				existeEntidad = true;
		}
		return existeEntidad;
	}
	
	public VN1470(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio) {
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN1470(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos) {
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN1470(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones) {
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}