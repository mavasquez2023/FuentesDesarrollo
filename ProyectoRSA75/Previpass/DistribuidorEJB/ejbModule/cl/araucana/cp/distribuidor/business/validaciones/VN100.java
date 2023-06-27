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
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;

public class VN100 extends Validacion
{
	private static Logger log = Logger.getLogger(VN100.class);

	/*
	 * 1 parametro= VN100: codigo regimen impositivo. requerido solo si VN070 es INP, y VN090 es correcto
	 * 
	 * Mensajes 116: Codigo de Regimen impositivo invalido 332: Codigo de Regimen impositivo no corresponde
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (this.parametros == null || this.parametros.size() != 1)
			{
				if (this.logear)
					log.info("validacion VN100: Sin Conceptos::");
				return this.SIN_CONCEPTOS;
			}

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			String valor = Utils.transforma(c.getValor());

			int idCodRegImp = 0;
//			clillo 13-05-16 No se recibe ningún pago IPS/Fonasa
			idCodRegImp = Constants.CODREGIMP_FALSO;
			
			GrupoConvenioVO grupoConvenioVO = (GrupoConvenioVO)this.parametrosNegocio.get("grupoConvenio");				

			if ( (grupoConvenioVO.isPrevired() && !valor.equals("")) || (!valor.equals("") && cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP) )
			{
				Integer integer = NumeroValidacion.valida(valor);
				if (integer != null && integer.intValue() > 0 && integer.intValue() != Constants.CODREGIMP_FALSO)
					idCodRegImp = integer.intValue();
				else
				{
					if(!grupoConvenioVO.isPrevired()){
						if (this.logear)
							log.info("validacion VN100 ERR:codigo regimen impositivo, valor recibido:" + valor + "::");
						return 116; // Codigo de Regimen impositivo invalido
					}
				}
			}
			
			if(grupoConvenioVO.isPrevired()){
				if(cotizante.getIdEntExCaja() == Constants.EXCAJA_FALSO && idCodRegImp != Constants.CODREGIMP_FALSO){
					cotizante.setIdEntExCaja(idCodRegImp);
					idCodRegImp = 1;
				}
			}
			
			if (!this.mapeoRegimenImpositivo.containsKey(String.valueOf(cotizante.getIdEntExCaja() + "#" + idCodRegImp)))
			{
				if (this.logear)
					log.info("validacion VN100 ERR:codigo regimen impositivo no encontrado, valor recibido:" + idCodRegImp + "::");
				cotizante.setIdRegimenImp(Constants.CODREGIMP_FALSO);
				return 116; // Codigo de Regimen impositivo invalido
			}
			
			if(grupoConvenioVO.isPrevired() && cotizante.getIdEntExCaja() != Constants.EXCAJA_FALSO && idCodRegImp != Constants.CODREGIMP_FALSO){
				cotizante.setTipoPrevision(Constants.TIPO_PREVISION_INP);
				cotizante.setIdEntAfcReal( cotizante.getIdEntPensionReal() );
				cotizante.setIdEntAfc( cotizante.getIdEntPension() );
				
				//Ahora las limpio...
				//cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
				cotizante.setIdEntPensionReal(0); //Seleccione
				//cotizante.setIdEntPension("");
				cotizante.setIdEntPension("0"); //Seleccione
			}
			
			if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_AFP)
			{
				if (idCodRegImp != Constants.CODREGIMP_FALSO)
				{
					if (this.logear)
						log.info("validacion VN100 ERR:codigo regimen impositivo no corresponde al estar asociado a AFP::");
					return 332; // Codigo de Regimen impositivo no corresponde
				}
			}
			cotizante.setIdRegimenImp(idCodRegImp);
			if (this.logear)
				log.info("validacion VN100 OK: idRegimenImp:" + cotizante.getIdRegimenImp() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN100::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "";
			if (cotizante.getIdRegimenImp() == -1)
				cotizante.setIdRegimenImp(Constants.CODREGIMP_FALSO);
			if (cotizante.getTipoPrevision() != Constants.TIPO_PREVISION_INP && cotizante.getIdRegimenImp() != Constants.CODREGIMP_FALSO)
			{
				if (this.logear)
					log.info("validacion VN100 ERR:codigo regimen impositivo no corresponde al estar asociado a AFP::");
				return 332; // Codigo de Regimen impositivo no corresponde
			} else if (cotizante.getTipoPrevision() == Constants.TIPO_PREVISION_INP && cotizante.getIdRegimenImp() == Constants.CODREGIMP_FALSO)
			{
				if (this.logear)
					log.info("validacion VN100 ERR:codigo regimen impositivo DEBE informarse, si es INP::");
				return 116; // Codigo de Regimen impositivo invalido
			}
			if (this.session.get(RegImpositivoVO.class, new RegImpositivoVO(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp())) == null)
			{
				log.info("validacion VN100 ERROR getIdRegimenImp no encontrada:" + cotizante.getIdRegimenImp() + "::");
				return 116; // Codigo de Regimen impositivo invalido
			}
			if (this.logear)
				log.info("validacion VN100 OK: idRegimenImp:" + cotizante.getIdRegimenImp() + ":tipo:" + cotizante.getTipoPrevision() + "::");
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN100::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	public VN100(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN100(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN100(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
