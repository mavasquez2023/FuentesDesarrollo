package cl.araucana.cp.presentation.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoEnvioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RechazoDescriptorVO;
import cl.araucana.cp.hibernate.dao.EstadosDAO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.logger.InitAuditLogger;
import cl.araucana.cp.web.menu.MenusConfig;
/*
* @(#) ParametroMgr.java 1.19 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.19
 */
public class ServletConfiguration extends HttpServlet 
{
	static final long serialVersionUID = 5985030137399567905L;
	private static final Logger logger = Logger.getLogger(ServletConfiguration.class);
	/**
	 * init
	 */
	public void init() throws ServletException
	{
		super.init();		

		logger.debug("Configurando menus visibles");
		configurarMenus();
		logger.debug("Menus visibles configurados.");
		logger.debug("Cargar estados envio/rechazo");
		cargaEstados();
		logger.debug("estados envio/rechazo cargados");
		cargaMXTotales();
		logger.debug("mxTotales cargados");

		try
		{
			logger.debug("Cargar textos botones");
			cargaTextoBotones();
			logger.debug("textos botones cargados");
		} catch (IOException ioe)
		{
			logger.error("problemas cargando texto botoneso", ioe);
			throw new ServletException("Problemas cargando textos botones! no se puede levantar la aplicacion, revisar configuracion: /files/textoBotones.properties");
		}
		cargaParametros();
		InitAuditLogger.process(HibernateUtil.getSession());
		HibernateUtil.closeSession();
	}
	private void cargaMXTotales()
	{//menos 1 ya que los arreglos parten de cero, no de uno
		HashMap tmp = new HashMap();
		tmp.put("1", "" + 10);
		tmp.put("2", "" + 3);
		tmp.put("3", "" + 9);
		tmp.put("4", "" + 2);
		tmp.put("5", "" + 7);
		tmp.put("6", "" + 2);
		tmp.put("7", "" + 2);
		tmp.put("8", "" + 0);
		tmp.put("20", "" + 5);
		tmp.put("21", "" + 1);
		tmp.put("22", "" + 3);
		tmp.put("23", "" + 1);
		tmp.put("24", "" + 0);
		tmp.put("25", "" + 0);
		tmp.put("40", "" + 5);
		tmp.put("41", "" + 1);
		tmp.put("42", "" + 3);
		tmp.put("43", "" + 1);
		tmp.put("44", "" + 0);
		tmp.put("60", "" + 0 + "#" + 1);
		Constants.TOTAL_MX_SECCION = tmp;
	}

	/**
	 * configurar menus
	 * @throws ServletException
	 */
	public void configurarMenus() throws ServletException 
	{
		String path = findPath("path-config-menus");
		try 
		{
			MenusConfig config = new MenusConfig(path);
			logger.debug("Cantidad de modulos encontrados: " + config.getModulos().size());
			logger.debug("Se almacena bean de configuracion como atributo " + MenusConfig.KEY_MENUS_CONFIG + " en servletcontext");
			getServletContext().setAttribute(MenusConfig.KEY_MENUS_CONFIG, config);
		} catch (Exception e) 
		{
			throw new ServletException(e);
		}
	}
	/**
	 * find path
	 * @param pathName
	 * @return
	 */
	private String findPath(String pathName) 
	{
		String path = getInitParameter(pathName);
		logger.debug("Path relativo de xml configuracion: "  + path);
		path = getServletContext().getRealPath(path);
		logger.debug("Path absoluto de xml configuracion: "  + path);
		return path;
	}
	/**
	 * carga estados
	 *
	 */
	private void cargaEstados()
	{
		logger.info("ServletConfiguration:cargaEstados");
		Transaction tx = null;

		try
		{
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			EstadosDAO estadosDao = new EstadosDAO(session);
			Constants.ESTADO_ENVIO = estadosDao.cargaEstados(EstadoEnvioVO.class);
			Constants.ESTADO_RECHAZO = estadosDao.cargaEstados(RechazoDescriptorVO.class);
			Constants.ESTADO_NOMINA = estadosDao.cargaEstados(EstadoNominaVO.class);

			tx.commit();
		} catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			logger.error("problemas cargando estados", e);
		}
	}
	/**
	 * carga parametros
	 *
	 */
	private void cargaParametros() 
	{
		logger.info("ServletConfiguration:cargaParametros");
		try
		{
			Session session = HibernateUtil.getSession();
			ParametrosDAO parametrosDao = new ParametrosDAO(session); 

			ParametrosHash param = parametrosDao.getParametrosHash();
			logger.info("ServletConfiguration:cargaParametros:" + param.getValores().size() + "::");
			Constants.TASA_FIJA_MUTUAL = Float.parseFloat(param.get("" + Constants.PARAM_TASA_FIJA));
			Constants.TIPOS_SECCION_CAAF = new HashSet();
			Constants.TIPOS_SECCION_CAAF.add(new Integer(5));
			Constants.TIPOS_SECCION_CAAF.add(new Integer(24));
			Constants.TIPOS_SECCION_CAAF.add(new Integer(44));

			//MOTIVOS DE RECHAZO DE ENVIOS
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

			//TIPO CAUSA
			Constants.TIPO_CAUSA_RUT_REPETIDO = Integer.parseInt(param.get("" + Constants.PARAM_TC_RUT_REPETIDO));
			Constants.TIPO_CAUSA_MOVTO_PERSO_NECESARIO = Integer.parseInt(param.get("" + Constants.PARAM_TC_MOVTO_PERSO_REQUERIDO));
	
			//IDS ENTIDADES ESPECIALES
			Constants.ID_INP = Integer.parseInt(param.get("" + Constants.PARAM_ID_INP));// 0;
			Constants.ID_AFP_NINGUNA = Integer.parseInt(param.get("" + Constants.PARAM_ID_AFP_NINGUNA));// -100;
			Constants.ID_FONASA = Integer.parseInt(param.get("" + Constants.PARAM_ID_FONASA));// 0;
			Constants.ID_SALUD_NINGUNA = Integer.parseInt(param.get("" + Constants.PARAM_ID_SALUD_NINGUNA));// -1;
			Constants.ID_TIPO_DETALLE_ARAUCANA = Integer.parseInt(param.get("" + Constants.PARAM_ID_TIPO_DET_ARAUCANA));// 1;
			Constants.TRAMO_ASIGFAM_NINGUNO = Integer.parseInt(param.get("" + Constants.PARAM_ID_TRAMO_ASIG_FAM_NINGUNO));// 0;

			//estados de comprobantes
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

			//PAGINACION
			Constants.NUM_PAG_CL = Integer.parseInt(param.get("" + Constants.PARAM_NUM_PAG_CLIENTE));// 6;
			Constants.NUM_REG_PAG_CL = Integer.parseInt(param.get("" + Constants.PARAM_NUM_REG_PAG_CLIENTE));// 15;
			Constants.NUM_PAG_ADMIN = Integer.parseInt(param.get("" + Constants.PARAM_NUM_PAG_ADMIN));// 6;
			Constants.NUM_REG_PAG_ADMIN = Integer.parseInt(param.get("" + Constants.PARAM_NUM_REG_PAG_ADMIN));// 15;

			// NIVEL ACCESOS ENCARGADOS
			Constants.NIVEL_ACC_CONV_SUC_NADA = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_NADA));// 0;
			Constants.NIVEL_ACC_CONV_SUC_LECTOR = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_LECTOR));// 1;
			Constants.NIVEL_ACC_CONV_SUC_EDITOR = Integer.parseInt(param.get("" + Constants.PARAM_NIVEL_ACC_ESCRITOR));// 2;
			
		} catch (Exception e)
		{
			logger.error("problemas cargando parametros", e);
		}
	}
	/**
	 * carga texto botones
	 * @throws IOException
	 */
	private void cargaTextoBotones() throws IOException
	{
		Constants.TXT_BTNS.load(getClass().getResourceAsStream("/files/textoBotones.properties"));
	}
}
