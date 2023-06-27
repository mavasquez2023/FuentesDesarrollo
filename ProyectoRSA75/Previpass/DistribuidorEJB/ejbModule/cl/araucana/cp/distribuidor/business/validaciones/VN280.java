package cl.araucana.cp.distribuidor.business.validaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;

public class VN280 extends Validacion
{
	private static Logger log = Logger.getLogger(VN280.class);

	/*
	 * 1 parametro = VN280: cotizacion obligatoria prevision
	 * 
	 * Mensajes 
	 * 144: Monto Cotizacion Obligatoria de Prevision invalido 
	 * 145: Monto Cotiz. Oblig. Prevision AFP es menor a 10% Renta Imp. 
	 * 338: Monto Cotizacion Obligatoria de Prevision incorrecto AFP
	 * 339: Monto Cotizacion Obligatoria de Prevision incorrecto INP
	 * 
	 * si no calcular, si monto valido: asigna si no, asigna 0 si calcular si AFP == ninguna asigna 0 si monto invalido retorna error
	 */
	public int valida(CotizanteVO cotizante){
		return this.valida(cotizante, false);
	}
	
	public int validaFromWEB(CotizanteVO cotizante){
		return this.valida(cotizante, true);
	}

	/**
	 * Este es el método que hace el trabajo...
	 * @param cotizante
	 * @param web
	 * @return
	 */
	private int valida(CotizanteVO cotizante, boolean isWeb)
	{
		try
		{
			//int respuesta = this.COD_CUMPLE_VALIDACION; //La respuesta se cambia solo cuando no se cumple la la validación.
			this.resultado = "";
			String sValor = null;
			int monto = 0;
			
			if (cotizante.getTipoProceso() == 'D')
				return this.COD_CUMPLE_VALIDACION;
			
			if (cotizante.isAfpVoluntario() && ( cotizante.getTipoProceso() == 'G' || cotizante.getTipoProceso() == 'A' ) )
					return this.COD_CUMPLE_VALIDACION;
			
			if(isWeb){
				monto = cotizante.getCotizacion().getPrevisionObligatorio();
				sValor = String.valueOf(monto);
			}else{
				if (this.parametros == null || this.parametros.size() != 1)
					return this.SIN_CONCEPTOS;
				else{
					ConceptoVO c = (ConceptoVO) this.parametros.get(0);	
					this.mensaje = c.getNombre();
					sValor = c.getValor();
					monto = asignaValor(c.getValor());
				}
			}
			
			if (cotizante.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA)// pensionado? generar alerta
			{
				if (this.logear)
					log.info("validacion VN280 cot pension obligatoria WAR: entidad Pension == NINGUNA, asigna 0::");
				cotizante.getCotizacion().setPrevisionObligatorio(0);
				return this.COD_CUMPLE_VALIDACION;
			}
				
			if (monto < 0) {
				if (this.logear)
					log.info("validacion VN280 cot pension obligatoria ERR: valor recibido invalido:" + sValor + "::");
				return 144; //Monto Cotizacion Obligatoria de Prevision invalido
				
			}
				
			if (cotizante.getTipoProceso() == 'R' || cotizante.getTipoProceso() == 'G')
			{
				if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
				{
					int remImponible = cotizante.getCotizacion().getRenta();
					
					EntidadPensionVO entidadPension = null;
					if (isWeb)
						entidadPension = (EntidadPensionVO) this.session.get(EntidadPensionVO.class, new Integer(cotizante.getIdEntPensionReal()));
					else
					{
						if (this.mapeoEntPension.containsKey(String.valueOf(cotizante.getIdEntPensionReal())))
							entidadPension = (EntidadPensionVO) this.mapeoEntPension.get(String.valueOf(cotizante.getIdEntPensionReal()));
					}
					
					if (entidadPension == null) {
						if (this.logear)
							log.info("validacion VN280 cot pension obligatoria ERR: entidad prevision invalida (monto no se valida), asigna valor:" + monto + "::");
					} else {
						
						if (monto != 0 && monto <= Math.round(remImponible / entidadPension.getCotizacionObligatoria() )) {
							cotizante.getCotizacion().setPrevisionObligatorio(monto);
							if (this.logear)
								log.info("validacion VN280 cot pension obligatoria ERR: valor recibido:" + monto + ":menor a 10% remImponible:" + remImponible + "::");
							return 145; //Monto Cotiz. Oblig. Prevision AFP es menor a 10% Renta Imp.
						}
						
						float tasaSis = 0;
						if (!this.seDebeValidarSIS())
							tasaSis = entidadPension.getSis();
																	
						int prevCalculadaNormal = Math.round(remImponible * (entidadPension.getCotizacionObligatoria() + entidadPension.getComision() + tasaSis) / 100);
						
						if (!this.isValidoAplicandoTolerancia(monto, prevCalculadaNormal))
						{
							cotizante.getCotizacion().setPrevisionObligatorio(monto);
							if (this.logear)
								log.info("validacion VN280 cot pension obligatoria ERR: valor recibido:" + monto + ":valor esperado normal:" + prevCalculadaNormal + "::");
							return 338; //Monto Cotizacion Obligatoria de Prevision incorrecto AFP
							
						}
					}									
				}else{
					//INP
					monto = 0;
				}
			}
			//Con 'A' setea el monto y cumple la validación...
			cotizante.getCotizacion().setPrevisionObligatorio(monto);
			
			if (this.logear)
				log.info("validacion VN280 OK:cotizacion obligatoria prevision:" + monto + "::");			
			
			return this.COD_CUMPLE_VALIDACION;

		} catch (Exception e)
		{
			log.error("error validacion VN280::", e);
			return this.CAIDA_SISTEMA;
		}
	}
	
	/**
	 * Valida el monto informado aplicando la tolerancia parametrizada. 
	 * @param valorInformado
	 * @param valorCalculado
	 * @return
	 */
	private boolean isValidoAplicandoTolerancia(int valorInformado, int valorCalculado ){

		boolean respuesta = true;
		int tolerancia = new Integer((String)this.parametrosNegocio.get("" + Constants.PARAM_TOLERANCIA_PESO)).intValue();
						
		if ( (valorInformado > (valorCalculado + tolerancia)) || (valorInformado < (valorCalculado - tolerancia))) 
			respuesta = false;
		
		return respuesta;
	}	
	
	/**
	 * Indica si es necesario validar el monto informado
	 * @param web indica si viene desde web
	 * @return
	 */
	private boolean seDebeValidarSIS() {
		boolean respuesta = false;
		
		String periodoInformado="";
		if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		
		}else if(((String)parametrosNegocio.get("tipoEmpresa")).equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
			 periodoInformado = (String)parametrosNegocio.get("" + Constants.PARAM_PERIODO_INDEPENDIENTE);
		}
		
		//String periodoInformado = (String)this.parametrosNegocio.get("" + Constants.PARAM_PERIODO);
		String iniVigSIS = (String)this.parametrosNegocio.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);
		int minTrabSIS = Integer.parseInt((String)this.parametrosNegocio.get("" + Constants.PARAM_MINIMO_TRABAJADORES_SIS));
		boolean isPrivada = ((Integer) this.parametrosNegocio.get("empresaPrivada")).intValue() == 0 ? true : false;
		
		int cantidadCotizantesEnNomina = ((Integer)this.parametrosNegocio.get("cantidadCotizantes")).intValue();
		boolean informaSisEnNomina = ((Boolean)this.parametrosNegocio.get("informaSisEnNomina")).booleanValue();

		//Validaciones de Negocio
		if(isPrivada) {
			//Empresa Privada
			if(!respuesta && informaSisEnNomina) respuesta = true;
			if(!respuesta && cantidadCotizantesEnNomina > minTrabSIS) respuesta = true;
			if(!respuesta && Integer.parseInt(periodoInformado) >= Integer.parseInt(iniVigSIS) ) respuesta = true;
		}else{
			//Empresa Pública
			respuesta = true;
		}
		
		return respuesta;
	}

	public VN280(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN280(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN280(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
