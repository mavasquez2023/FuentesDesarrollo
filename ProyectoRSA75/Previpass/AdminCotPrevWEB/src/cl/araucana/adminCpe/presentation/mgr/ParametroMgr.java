package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ParametroDAO;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaParametro;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) ParametroMgr.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.7
 */
public class ParametroMgr
{
	private ParametroDAO parametroDAO;

	static Logger log = Logger.getLogger(ParametroMgr.class);

	public ParametroMgr(Session session)
	{
		this.parametroDAO = new ParametroDAO(session);
	}

	/**
	 * lista parametros negocio
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getParametrosNegocio() throws DaoException
	{
		List result = new ArrayList();
		List lista = this.parametroDAO.getParametrosNegocio();

		for (Iterator iter = lista.iterator(); iter.hasNext();)
		{
			LineaParametro lineaParametro = new LineaParametro();
			ParametroVO element = (ParametroVO) iter.next();

			copyParametroVoToLineaParametro(lineaParametro, element);
			String valor = lineaParametro.getValor();
			if (lineaParametro.getTipoValor().equalsIgnoreCase("decimal"))
				lineaParametro.setValor(valor.replace('.', ','));
			result.add(lineaParametro);
		}
		return result;
	}

	/**
	 * lista parametros sistema
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getParametrosSistema() throws DaoException
	{
		List result = new ArrayList();
		List lista = this.parametroDAO.getParametrosSistema();

		for (Iterator iter = lista.iterator(); iter.hasNext();)
		{
			LineaParametro lineaParametro = new LineaParametro();
			ParametroVO element = (ParametroVO) iter.next();

			copyParametroVoToLineaParametro(lineaParametro, element);
			String valor = lineaParametro.getValor();
			if (lineaParametro.getTipoValor().equalsIgnoreCase("decimal"))
				lineaParametro.setValor(valor.replace('.', ','));
			result.add(lineaParametro);
		}
		return result;
	}

	/**
	 * copia parametro voto linea
	 * 
	 * @param lineaParametro
	 * @param element
	 */
	private void copyParametroVoToLineaParametro(LineaParametro lineaParametro, ParametroVO element)
	{
		lineaParametro.setId(element.getId());
		lineaParametro.setNombre(element.getNombre().trim());
		lineaParametro.setDescripcion(element.getDescripcion().trim());
		lineaParametro.setValor(element.getValor().trim());
		lineaParametro.setTipoParametro(element.getValor().trim());
		lineaParametro.setTipoValor(element.getTipoValor().trim());
	}

	/**
	 * lista parametro
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ParametroVO getParametro(int id) throws DaoException
	{
		return this.parametroDAO.getParametro(id);
	}

	/**
	 * actualiza parametro
	 * 
	 * @param parametroVO
	 * @throws DaoException
	 */
	public void update(ParametroVO parametroVO) throws DaoException
	{
		this.parametroDAO.update(parametroVO);
	}

	/**
	 * periodo format
	 * 
	 * @return
	 * @throws DaoException
	 */
	public String getPeriodoFormat() throws DaoException
	{
		String periodo = this.parametroDAO.getParametro(Constants.PARAM_PERIODO).getValor().trim();
		return periodo.substring(periodo.length() - 2) + "/" + periodo.substring(0, 4);
	}

	/**
	 * parametro spl
	 * 
	 * @param listaParams
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		return this.parametroDAO.getParametrosHash(listaParams);
	}

	/**
	 * factor
	 * 
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public HashMap getFactores(List tiposNomina) throws DaoException
	{
		return this.parametroDAO.getFactores(tiposNomina);
	}

	/**
	 * carga constantes
	 * 
	 * @return
	 */
	public boolean cargaConstantes()
	{
		try
		{
			ParametrosHash param = this.parametroDAO.getParametrosHash();
			Constants.TASA_FIJA_MUTUAL = Float.parseFloat(param.get("" + Constants.PARAM_TASA_FIJA));

			Constants.TIPOS_SECCION_CAAF = new HashSet();
			Constants.TIPOS_SECCION_CAAF.add(new Integer(5));
			Constants.TIPOS_SECCION_CAAF.add(new Integer(24));
			Constants.TIPOS_SECCION_CAAF.add(new Integer(44));

			// MOTIVOS DE RECHAZO DE ENVIOS
			Constants.EST_RECH_EXITOSO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_EXITOSO));
			Constants.EST_RECH_DUPLICADA = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_DUPLICADO));
			Constants.EST_RECH_REENVIO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_REENVIO));
			Constants.EST_RECH_DATA_CORRUPTA = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_DATA_CORRUPTA));
			Constants.EST_RECH_RECEPCION = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_RECEPCION));
			Constants.EST_RECH_ID_INVALIDO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_ID_INVALIDO));
			Constants.EST_RECH_RUT_INVALIDO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_RUT_INVALIDO));
			Constants.EST_RECH_TIPO_INVALIDO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_TIPO_INVALIDO));
			Constants.EST_RECH_CONV_INVALIDO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_CONV_INVALIDO));
			Constants.EST_RECH_VACIA = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_VACIA));
			Constants.EST_RECH_SIN_FORMATO = Integer.parseInt(param.get("" + Constants.PARAM_EST_RECH_SIN_FORMATO));

			// TIPO CAUSA
			Constants.TIPO_CAUSA_RUT_REPETIDO = Integer.parseInt(param.get("" + Constants.PARAM_TC_RUT_REPETIDO));
			Constants.TIPO_CAUSA_MOVTO_PERSO_NECESARIO = Integer.parseInt(param.get("" + Constants.PARAM_TC_MOVTO_PERSO_REQUERIDO));

			// IDS ENTIDADES ESPECIALES
			Constants.ID_INP = Integer.parseInt(param.get("" + Constants.PARAM_ID_INP));// 0;
			Constants.ID_AFP_NINGUNA = Integer.parseInt(param.get("" + Constants.PARAM_ID_AFP_NINGUNA));// -100;
			Constants.ID_FONASA = Integer.parseInt(param.get("" + Constants.PARAM_ID_FONASA));// 0;
			Constants.ID_SALUD_NINGUNA = Integer.parseInt(param.get("" + Constants.PARAM_ID_SALUD_NINGUNA));// -1;
			Constants.ID_TIPO_DETALLE_ARAUCANA = Integer.parseInt(param.get("" + Constants.PARAM_ID_TIPO_DET_ARAUCANA));// 1;
			Constants.TRAMO_ASIGFAM_NINGUNO = Integer.parseInt(param.get("" + Constants.PARAM_ID_TRAMO_ASIG_FAM_NINGUNO));// 0;

			// estados de comprobantes
			Constants.EST_CMP_POR_PAGAR = Integer.parseInt(param.get("" + Constants.PARAM_EST_CMP_POR_PAGAR));// 3;
			Constants.EST_CMP_PAGO_PARCIAL = Integer.parseInt(param.get("" + Constants.PARAM_EST_CMP_PARCIAL));// 4;
			Constants.EST_CMP_PAGADO = Integer.parseInt(param.get("" + Constants.PARAM_EST_CMP_PAGADO));// 5;
			Constants.EST_CMP_PRECURSADO = Integer.parseInt(param.get("" + Constants.PARAM_EST_CMP_PRECURSADO));// 8;
			// estados nomina
			Constants.EST_NOM_NO_ENVIADA = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_NO_ENVIADA));// -1;
			Constants.EST_NOM_EN_PROCESO = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_PROCESO));// 1;
			Constants.EST_NOM_CON_ERRORES = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_ERRORES));// 2;
			Constants.EST_NOM_POR_PAGAR = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_POR_PAGAR));// 3;
			Constants.EST_NOM_PAGADO_PARCIALMENTE = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_PARCIAL));// 4;
			Constants.EST_NOM_PAGADO = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_PAGADA));// 5;
			Constants.EST_NOM_PRECURSADA = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_PRECURSADA));// 6;
			Constants.EST_NOM_NO_CARGADA = Integer.parseInt(param.get("" + Constants.PARAM_EST_NOM_NO_CARGADA));// 7; //recibida, pero con errores en procesamiento/validaciones

			// PAGINACION
			Constants.NUM_PAG_CL = Integer.parseInt(param.get("" + Constants.PARAM_NUM_PAG_CLIENTE));// 6;
			Constants.NUM_REG_PAG_CL = Integer.parseInt(param.get("" + Constants.PARAM_NUM_REG_PAG_CLIENTE));// 15;
			Constants.NUM_PAG_ADMIN = Integer.parseInt(param.get("" + Constants.PARAM_NUM_PAG_ADMIN));// 6;
			Constants.NUM_REG_PAG_ADMIN = Integer.parseInt(param.get("" + Constants.PARAM_NUM_REG_PAG_ADMIN));// 15;

			// NIVEL ACCESOS ENCARGADOS
			Constants.NIVEL_ACC_CONV_SUC_NADA = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_NADA));// 0;
			Constants.NIVEL_ACC_CONV_SUC_LECTOR = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_LECTOR));// 1;
			Constants.NIVEL_ACC_CONV_SUC_EDITOR = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_ESCRITOR));// 2;
			
			Constants.URL_WS_MAIL_SIS ="http://"+(param.get(""+Constants.PARAM_URL_WS_MAIL_SIS)).trim() + Constants.URL_WS_MAIL_SIS;
			
			return true;
		} catch (Exception e)
		{
			log.error("carga constantes:", e);
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public List getRelacionTipoCausa() throws DaoException {
		return this.parametroDAO.getRelacionTipoCausa();
	}
	
	public byte[] unzipData(byte[] zippedData) throws Exception {
		return this.parametroDAO.unzipData(zippedData);
	}	
}
