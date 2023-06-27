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
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;

public class VN070 extends Validacion
{
	private static Logger log = Logger.getLogger(VN070.class);

	/*
	 * 1 parametro = VN070: Entidad previsional
	 * 
	 * Mensajes 111: CODIGO PREVISION INVALIDO
	 *          112: CODIGO PREVISION NO APARECE EN MAPEO
	 *          234: ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
	 *          308: CODIGO ENTIDAD AFPAV NO CORRESPONDE A VALORES POSIBLES
	 *          309: CODIGO ENTIDAD AFPAV DEBE EXISTIR
	 */
	public int valida(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			if (this.parametros == null || this.parametros.size() != 1)
				return this.SIN_CONCEPTOS;

			ConceptoVO c = (ConceptoVO) this.parametros.get(0);
			this.mensaje = c.getNombre();
			// marco cambio clase para soportar el - de rut 
			String codigo = Utils.transformaCodEnt(c.getValor() != null ? c.getValor().toUpperCase() : null);
			if (codigo.equals(""))
			{
				if (cotizante.getTipoProceso() == 'D')
				{
					if (this.logear)
						log.info("validacion VN070 ERR: idEntidadAPV vacio " + codigo + "::");
					((CotizacionDCVO)cotizante.getCotizacion()).setIdEntDep(Constants.APVC_FALSO);
					cotizante.setIdEntidadAPVC(codigo);
				} else
				{
					if (this.logear)
						log.info("validacion VN070 ERR: idEntidadPension vacio::");
					cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
					cotizante.setIdEntPension(codigo);
					if(c.getObligatorio()==0){
						return this.COD_CUMPLE_VALIDACION;
					}else{
						return 111; // CODIGO PREVISION INVALIDO
					}
				}
			}
			return valida(codigo, cotizante);
		} catch (Exception e)
		{
			log.error("error validacion VN070::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	private int valida(String codigo, CotizanteVO cotizante)
	{
		if (cotizante.getTipoProceso() == 'D')
		{
			this.resultado = "";
			Criteria crit = this.session.createCriteria(MapeoAPVVO.class);
			crit.add(Restrictions.eq("idMapaCod", new Integer(this.datosConvenio.getIdMapaCod())));
			List result = crit.add(Restrictions.eq("codigo", codigo)).list();
			CotizacionDCVO depositoConvenido = (CotizacionDCVO)cotizante.getCotizacion();
			if (result == null || result.size() == 0)
			{
				if (this.logear)
					log.info("validacion VN070 ERR: idEntidadAPV no aparece en mapeo: codigo " + codigo + "::");
				depositoConvenido.setIdEntDep(Constants.APVC_FALSO);
				cotizante.setIdEntidadAPVC(codigo);
				return 234; // ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
			}
			MapeoVO mp = (MapeoVO) result.get(0);
			depositoConvenido.setIdEntDep(mp.getId());
			cotizante.setIdEntidadAPVC(codigo);
			if (this.logear)
				log.info("validacion VN070 OK:idEntidadAPV:" + this.resultado + ":" + depositoConvenido.getIdEntDep() + "::");
			return this.COD_CUMPLE_VALIDACION;
		}

		if (cotizante.getTipoProceso() == 'R' && cotizante.isAfpVoluntario())
			return validaReforma(codigo, cotizante);

		if (!this.mapeoPension.containsKey(codigo.trim()))
		{
			if (this.logear)
				log.info("validacion VN070 ERR: codigo prevision no aparece en mapeo: codigo:" + codigo + "::");
			cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
			cotizante.setIdEntPension(codigo);
			return 112;// CODIGO PREVISION NO APARECE EN MAPEO
		}
		MapeoVO mp = (MapeoVO) this.mapeoPension.get(codigo.trim());

		cotizante.setIdEntPensionReal(mp.getId());
		cotizante.setIdEntPension(codigo);
		if (mp.getId() == Constants.ID_INP)
			cotizante.setTipoPrevision(Constants.TIPO_PREVISION_INP);
		else if (mp.getId() != Constants.ID_AFP_NINGUNA)
			cotizante.setTipoPrevision(Constants.TIPO_PREVISION_AFP);
		else
			cotizante.setTipoPrevision(Constants.TIPO_PREVISION_NINGUNA);
		if (this.logear)
			log.info("validacion VN070 OK:idEntidadPension::" + cotizante.getIdEntPensionReal() + "::");
		this.resultado = "";
		return this.COD_CUMPLE_VALIDACION;
	}

	public int validaFromWEB(CotizanteVO cotizante)
	{
		try
		{
			this.resultado = "E";
			if (cotizante.getCotizacion() instanceof CotizacionDCVO)
			{
				CotizacionDCVO depositoConvenido = (CotizacionDCVO)cotizante.getCotizacion();
				if (this.session.get(EntidadApvVO.class, new Integer(depositoConvenido.getIdEntDep())) == null)
				{
					log.info("validacion VN070 ERROR IdEntAPVReal no encontrada:" + depositoConvenido.getIdEntDep() + "::");
					return 234;// ENTIDAD DEP. CONVENIDO NO APARECE EN MAPEO
				}
				this.resultado = "";
			} else
			{
				if (this.session.get(EntidadPensionVO.class, new Integer(cotizante.getIdEntPensionReal())) == null)
				{
					log.info("validacion VN070 ERROR IdEntPensionReal no encontrada:" + cotizante.getIdEntPensionReal() + "::");
					return 112;// CODIGO PREVISION NO APARECE EN MAPEO
				}
				if (cotizante.getIdEntPensionReal() == Constants.ID_INP)
				{
					cotizante.setTipoPrevision(Constants.TIPO_PREVISION_INP);
					if (cotizante.isAfpVoluntario())
					{
						if (this.logear)
							log.info("validacion VN070 ERR REFORMA:idEntidadPensionReforma:no puede ser INP:::");
						return 308; // CODIGO ENTIDAD AFPAV NO CORRESPONDE A VALORES POSIBLES
					}
				}
				else if (cotizante.getIdEntPensionReal() != Constants.ID_AFP_NINGUNA)
					cotizante.setTipoPrevision(Constants.TIPO_PREVISION_AFP);
				else
					cotizante.setTipoPrevision(Constants.TIPO_PREVISION_NINGUNA);
			}
			if (cotizante.isAfpVoluntario())
			{
				if (this.logear)
					log.info("validacion VN070 OK REFORMA:idEntidadPension:" + cotizante.getIdEntPensionReal() + "::");
				this.resultado = "F";
			} else if (this.logear)
				log.info("validacion VN070 OK:idEntidadPension:" + cotizante.getIdEntPensionReal() + "::");
			this.resultado = "";
			return this.COD_CUMPLE_VALIDACION;
		} catch (Exception e)
		{
			log.error("error validacion VN070::", e);
			return this.CAIDA_SISTEMA;
		}
	}

	/*
	 * 308: CODIGO ENTIDAD AFPAV NO CORRESPONDE A VALORES POSIBLES 309: CODIGO ENTIDAD AFPAV DEBE EXISTIR
	 */
	public int validaReforma(String codigo, CotizanteVO cotizante)
	{
		this.resultado = "F";
		Criteria crit = this.session.createCriteria(MapeoPensionVO.class);
		crit.add(Restrictions.eq("idMapaCod", new Integer(this.datosConvenio.getIdMapaCod())));
		List result = crit.add(Restrictions.eq("codigo", codigo)).list();
		if (result == null || result.size() == 0)
		{
			cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
			if (this.logear)
				log.info("validacion VN070 ERR REFORMA:idEntidadPensionReforma:codigo invalido:" + codigo + "::");
			return 309;// CODIGO ENTIDAD AFPAV DEBE EXISTIR
		}
		MapeoVO mp = (MapeoVO) result.get(0);

		cotizante.setIdEntPensionReal(mp.getId());
		cotizante.setIdEntPension(codigo);
		if (mp.getId() == Constants.ID_INP)
		{
			cotizante.setIdEntPensionReal(Constants.ID_AFP_NINGUNA);
			if (this.logear)
				log.info("validacion VN070 ERR REFORMA:idEntidadPensionReforma:no puede ser INP" + cotizante.getIdEntPensionReal() + "::");
			return 308; // CODIGO ENTIDAD AFPAV NO CORRESPONDE A VALORES POSIBLES
		}
		cotizante.setIdEntPensionReal(mp.getId());

		if (this.logear)
			log.info("validacion VN070 OK REFORMA:idEntidadPension:" + cotizante.getIdEntPensionReal() + "::");
		return this.COD_CUMPLE_VALIDACION;
	}

	public VN070(HashMap parametrosNegocio, Session session, ConvenioVO datosConvenio)
	{
		super(parametrosNegocio, session, datosConvenio);
	}

	public VN070(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos);
	}

	public VN070(HashMap parametrosNegocio, Utils setter, Session session, ConvenioVO datosConvenio, List parametros, List listaConceptos, List listaMapeosValidaciones)
	{
		super(parametrosNegocio, setter, session, datosConvenio, parametros, listaConceptos, listaMapeosValidaciones);
	}
}
