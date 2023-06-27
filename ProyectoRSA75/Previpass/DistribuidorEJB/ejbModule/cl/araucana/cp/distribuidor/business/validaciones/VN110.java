package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CargaFamiliarVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN110 extends Validacion
{
	private static Logger log = Logger.getLogger(VN110.class);

	/*
	 * 1 parametro = VN110: Tramo asignacion familiar
	 * 
	 * Mensajes 
	 * 117: Tramo Asignacion Familiar invalido 
	 * 118: Tramo Asignacion Familiar incorrecto
	 * 360: DEBE INFORMAR TRAMO SI INFORMA CARGAS
	 * 366: TRAMO ASIGNACION FAMILIAR NO CORRESPONDE EN EMPRESA PUBLICA
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			// MARCO 
//			this.resultado = "E";
			this.resultado = "";
			
			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			System.out.println("Valor tramo=" + c.getValor() + ".");
			String codigo = Utils.transforma(c.getValor() != null ? c.getValor().toUpperCase() : null);

			/*if (codigo.equals("")){
				//codigo = "0";
				return this.COD_CUMPLE_VALIDACION;
			}*/
			
// marco valida si es empresa publica
//			if (isPrivada == 1 && this.datosConvenio.getIdCcaf() != Constants.SIN_CCAF && cotizante.getTipoProceso() == 'R')
//		{
//				if (this.logear)
//				log.info("validacion VN110 OK: Empresa es CAJA-FISCAL: Tramo 0::");			
//				cotizante.setIdTramoReal(Constants.TRAMO_ASIGFAM_NINGUNO);
//				return this.COD_CUMPLE_VALIDACION;
//			}
			System.out.println(">>>codigo tramo:" + codigo + ".");
			if (!this.mapeoTramoAsigFam.containsKey(codigo.trim()) && (!codigo.equals("") ))
			{
				if (this.logear)
					log.info("validacion VN110: tramo asig familiar no encontrado:" + codigo + "::");
				cotizante.setIdTramoReal(Constants.TRAMO_ASIGFAM_NO_ENCONTRADO);
				return 117; // Tramo Asignacion Familiar invalido
			}	

			MapeoVO mp = (MapeoVO) this.mapeoTramoAsigFam.get(codigo.trim());
			
			
			// marco valida si es empresa publica e informa tramo
			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue();	
			if (isPrivada == 1 && mp!= null && mp.getId() != Constants.TRAMO_ASIGFAM_NINGUNO )		
			{
				if (this.logear)
					log.info("validacion VN110 Err: Informa tramo y empresa es publica:");			
				return 	366;//  TRAMO ASIGNACION FAMILIAR NO CORRESPONDE EN EMPRESA PUBLICA		
			}
			
			//clillo 5-12-2014 Se modifica para que si Tramo sea vacío o ninguno entre a validar si informó cargas.
			if (mp== null || mp.getId() == Constants.TRAMO_ASIGFAM_NINGUNO)
			{
				if (this.logear)
					log.info("validacion VN110 tramo asig familiar asignado: 'NINGUNO', revisa que todas las cargas sean cero");
				this.resultado = "";
				if (cotizante.getNumCargaInvalidez() > 0 || cotizante.getNumCargaMaterna() > 0 || cotizante.getNumCargaSimple() > 0)
				{
					if (this.logear)
						log.info("validacion VN110 ERR: informa tramo 'NINGUNO', pero informa cargas");
					return 360; //DEBE INFORMAR TRAMO SI INFORMA CARGAS
				}
	
			}
			
			if (codigo.equals("")){
				return this.COD_CUMPLE_VALIDACION;
			}
			
			cotizante.setIdTramo(codigo);
			cotizante.setIdTramoReal(mp.getId());
			
			if (cotizante.getNumCargaInvalidez() + cotizante.getNumCargaMaterna() + cotizante.getNumCargaSimple() == 0)
			{
				if (this.logear)
					log.info("validacion VN110 tramo asig familiar asignado: 'NINGUNO', todas las cargas eran cero");
				this.resultado = "";
				return this.COD_CUMPLE_VALIDACION;				
			}

			boolean validado=false;
			int j=0;
			//TODO. Cargas Familiares
			//if (this.cargasFamiliares != null) {
				int idCcaf = this.datosConvenio.getIdCcaf();
				CargaFamiliarVO carga = (CargaFamiliarVO) this.cargasFamiliares.get(idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante());
				if (carga != null) {
					if (carga.getIdTramoAF() != cotizante.getIdTramoReal()) {
						if (this.logear)
							log.info("Validacion VN110 ERROR: Para la empresa, trabajador y caja, no coincide el tramo de cargas familiares::");
						return 905; //TRAMO NO COINCIDE CON LO REPORTADO EN EL SISTEMA
					}else{
						validado=true;
					}
				}
			//}
			
				if (cotizante.getTipoProceso() == 'R' && !validado)
				{
					CotizacionREVO cotREVO = (CotizacionREVO) cotizante.getCotizacion();
					int rentaImponible = cotREVO.getRentaImp();
					//	 marco Valida el tramo segun enta si dias trabajados >=30 
					
					int diasTrabajados = cotizante.getNumDiasTrabajados();
					int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();		
					//	---				
					AsigFamVO asigFamVo = (AsigFamVO) this.mapeoAsigFam.get(String.valueOf(mp.getId()));

					// marco valida tramo trabaja todo el mes
					if (diasTrabajados >= diasXMes){
						if (rentaImponible > asigFamVo.getRentaMaxima() || rentaImponible < asigFamVo.getRentaMinima())
						{
							if (this.logear)
								log.info("validacion VN110 tramo asig familiar no concuerda con rangos:tramo real recibido:" + cotizante.getIdTramoReal() + ":IdTramo recibido:" + codigo + ":renta:"
										+ rentaImponible + ":renta min tramo:" + asigFamVo.getRentaMinima() + ":renta max tramo:" + asigFamVo.getRentaMaxima() + "::");
							return 118; // Tramo Asignacion Familiar incorrecto
						}
					}		
					
				}

			this.resultado = "";
			if (this.logear)
				log.info("validacion VN110 OK:IdTramoReal:" + mp.getId() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN110::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			int isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue();
			// marco se elimina para validar el tramo si es empresa publica
//			if (isPrivada == 1 && this.datosConvenio.getIdCcaf() != Constants.SIN_CCAF && cotizante.getTipoProceso() == 'R')
//			{
//				if (this.logear)
//					log.info("validacion VN110 OK: Empresa es CAJA-FISCAL: Tramo 0::");
//				return this.COD_CUMPLE_VALIDACION;
//			}

			// marco 
//			this.resultado = "E";
			this.resultado = "";
			
			AsigFamVO tramoAsigFam = null;
			if ((tramoAsigFam = (AsigFamVO) this.session.get(AsigFamVO.class, new Integer(cotizante.getIdTramoReal()))) == null)
			{
				log.info("validacion VN110 ERROR getIdTramoReal no encontrada:" + cotizante.getIdTramoReal() + "::");
				return 117; // Tramo Asignacion Familiar invalido
			}

// marco valida si es empresa publica e informa tramo
			if (isPrivada == 1 && cotizante.getIdTramoReal()!= Constants.TRAMO_ASIGFAM_NINGUNO )		
			{
				if (this.logear)
					log.info("validacion VN110 Err: Informa tramo y empresa es publica:");			
				return 	366;//  TRAMO ASIGNACION FAMILIAR NO CORRESPONDE EN EMPRESA PUBLICA		
			}
			
			if (cotizante.getIdTramoReal() == Constants.TRAMO_ASIGFAM_NINGUNO)
			{
				if (this.logear)
					log.info("validacion VN110 tramo asig familiar asignado: 'NINGUNO', revisa que todas las cargas sean cero");
				this.resultado = "";
				if (cotizante.getNumCargaInvalidez() > 0 || cotizante.getNumCargaMaterna() > 0 || cotizante.getNumCargaSimple() > 0)
				{
					if (this.logear)
						log.info("validacion VN110 ERR: informa tramo 'NINGUNO', pero informa cargas");
					return 360; //DEBE INFORMAR TRAMO SI INFORMA CARGAS
				}
				return this.COD_CUMPLE_VALIDACION;
			}
			//clillo 7/7/15 si informa Tramo debe venir cargas
			if (cotizante.getIdTramoReal() != Constants.TRAMO_ASIGFAM_NINGUNO && (cotizante.getNumCargaInvalidez() + cotizante.getNumCargaMaterna() + cotizante.getNumCargaSimple()) == 0)
			{
				if (this.logear)
					log.info("validacion VN110 tramo asig familiar asignado pero todas las cargas eran cero");
				return 132;				
			}
			//clillo 7/7/15 se comenta ya que no sirve que borre Tramo (caso Walmart)
			/*if (cotizante.getNumCargaInvalidez() + cotizante.getNumCargaMaterna() + cotizante.getNumCargaSimple() == 0)
			{
				if (this.logear)
					log.info("validacion VN110 tramo asig familiar asignado: 'NINGUNO', todas las cargas eran cero");
				this.resultado = "";
				cotizante.setIdTramoReal(Constants.TRAMO_ASIGFAM_NINGUNO);
				return this.COD_CUMPLE_VALIDACION;				
			}*/
			boolean validado=false;
			//TODO. Cargas Familiares
			int idCcaf = this.datosConvenio.getIdCcaf();
			CargaFamiliarVO carga = (CargaFamiliarVO) this.cargasFamiliares.get(idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante());
			if (carga != null) {
				//log.info("Validacion VN110, filtro: " + idCcaf + ";" + cotizante.getRutEmpresa() + ";" + cotizante.getIdCotizante() + ", tramo Repositorio=" + carga.getIdTramoAF() + ", tramoInformado=" + cotizante.getIdTramoReal());
				if (carga.getIdTramoAF() != cotizante.getIdTramoReal()) {
					if (this.logear)
						log.info("Validacion VN110 ERROR: Para la empresa, trabajador y caja, no coincide el tramo de cargas familiares::");
					return 905; //TRAMO NO COINCIDE CON LO REPORTADO EN EL SISTEMA
				}else{
					validado=true;
				}
			}
			
			if (cotizante.getTipoProceso() == 'R' && !validado)
			{
				CotizacionREVO cotREVO = (CotizacionREVO) cotizante.getCotizacion();
				int rentaImponible = cotREVO.getRentaImp();
// marco Valida el tramo segun enta si dias trabajados >=30 	
				int diasTrabajados = cotizante.getNumDiasTrabajados();
				int diasXMes = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_DIAS_X_MES)).intValue();		
//---				
				// marco valida tramo trabaja todo el mes
				if (diasTrabajados >= diasXMes){
					if (rentaImponible > tramoAsigFam.getRentaMaxima() || rentaImponible < tramoAsigFam.getRentaMinima())
					{
						if (this.logear)
							log.info("validacion VN110 tramo asig familiar no concuerda con rangos:tramo:" + cotizante.getIdTramoReal() + ":renta:" + rentaImponible + ":minTramo:"
									+ tramoAsigFam.getRentaMinima() + ":maxTramo:" + tramoAsigFam.getRentaMaxima() + "::");
						return 118; // Tramo Asignacion Familiar incorrecto
					}
				}			
				
			}

			

			if (this.logear)
				log.info("validacion VN110 OK:IdTramoReal:" + cotizante.getIdTramoReal() + "::");
			this.mensaje = "";
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN110::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN110(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN110(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio, HashMap cargasFamiliares) {
		super(parametrosNegocio, session, datosConvenio, cargasFamiliares);
	}

	public VN110(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN110(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
	
	public VN110(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones, HashMap cargasFamiliares) {
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones, cargasFamiliares);
	}
}