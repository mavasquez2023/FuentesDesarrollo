package cl.araucana.spl.actions.admin.rendicion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import cl.araucana.spl.actions.admin.rendicion.bbv.UtilsRendicionBBV;
import cl.araucana.spl.actions.admin.rendicion.bch.UtilsRendicionBCH;
import cl.araucana.spl.actions.admin.rendicion.bci.UtilsRendicionBCI;
import cl.araucana.spl.actions.admin.rendicion.bes.UtilsRendicionBES;
import cl.araucana.spl.actions.admin.rendicion.bit.UtilsRendicionBIT;
import cl.araucana.spl.actions.admin.rendicion.bsa.UtilsRendicionBSA;
import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.RendicionException;
import cl.araucana.spl.forms.admin.rendicion.ImportarRendicionForm;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.RendicionManager;
import cl.araucana.spl.util.ActionTools;

import com.bh.talon.User;

/**
 * Action que muestra un preview de las transacciones a importar.
 * @author malvarez
 * @since 1.0 / 28-03-2008
 *
 */

public class ImportarRendicionPreviewAction extends AppAction {

	private static final Logger logger = Logger.getLogger(ImportarRendicionPreviewAction.class);	
	private String target = "preview";	
	private static ResourceBundle resourceRendicion = null;
	
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			DaoConfig.startTransaction();
			
			ImportarRendicionForm frm = (ImportarRendicionForm) form;
			RendicionManager mgrRendicion = new RendicionManager();
			PagoManager mgrPago = new PagoManager();
			MedioPagoManager mgrMedio = new MedioPagoManager();
			HttpSession session = request.getSession();

			//Gets form
			FormFile rendicionFile = frm.getRendicion();
			BigDecimal idMedioPago = frm.getMedioPago().getId();
			target = "preview";
			
			//Buscar medio pago
			MedioPago medioPago = mgrMedio.getMedioPagoById(idMedioPago);
			String codBanco = medioPago!=null?medioPago.getCodigo():"";
						
			//Valido el tamagno
			if ((rendicionFile != null) && (rendicionFile.getFileSize() > 0)) {
				//Subir archivo
				String archivoNombre = rendicionFile.getFileName();
				logger.debug("Leyendo archivo: " + archivoNombre);				
				
				Map mapaParametros = new HashMap();
				mapaParametros.put(UtilsRendicionBCI.NOMBRE_ARCHIVO, archivoNombre);
				mapaParametros.put(UtilsRendicionBCI.FORM_FILE, rendicionFile);
				mapaParametros.put(UtilsRendicionBCI.MEDIO_PAGO, medioPago);
				mapaParametros.put(UtilsRendicionBCI.MGR_RENDICION, mgrRendicion);
				mapaParametros.put(UtilsRendicionBCI.MGR_PAGO, mgrPago);
				mapaParametros.put(UtilsRendicionBCI.FORM_IMPORTAR_RENDICION, frm);
				
				if (Constants.MEDIO_CODIGO_BCI.equalsIgnoreCase(codBanco) 
					|| Constants.MEDIO_CODIGO_TBC.equalsIgnoreCase(codBanco)) {
					UtilsRendicionBCI utilsRendicionBCI = new UtilsRendicionBCI();
					resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBCI");
					
					target = utilsRendicionBCI.initProcesarRendicion(mapaParametros, request, session);
					
				} else if (Constants.MEDIO_CODIGO_BCH.equalsIgnoreCase(codBanco)) {
					if (Constants.TESTING) {
						UtilsRendicionBCH utilsRendicionBCH = new UtilsRendicionBCH();
						resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBCH");
						
						target = utilsRendicionBCH.initProcesarRendicion(mapaParametros, request, session);
					} else {
						ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.noHabilitada"));
						target = "inicio";
					}

				} else if (Constants.MEDIO_CODIGO_BSA.equalsIgnoreCase(codBanco)) {
					if (Constants.TESTING) {
						UtilsRendicionBSA utilsRendicionBSA = new UtilsRendicionBSA();
						target = utilsRendicionBSA.initProcesarRendicion(mapaParametros, request, session);
					} else {
						ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.noHabilitada"));
						target = "inicio";
					}
					
				}else if (Constants.MEDIO_CODIGO_BES.equalsIgnoreCase(codBanco)) {
					UtilsRendicionBES utilsRendicionBES = new UtilsRendicionBES();
						
						resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBES");
						target = utilsRendicionBES.initProcesarRendicion(mapaParametros, request, session);
				
				}else if (Constants.MEDIO_CODIGO_BIT.equalsIgnoreCase(codBanco)) {
					UtilsRendicionBIT utilsRendicionBIT = new UtilsRendicionBIT();
						
						target = utilsRendicionBIT.initProcesarRendicion(mapaParametros, request, session);
					
				}else if (Constants.MEDIO_CODIGO_BBV.equalsIgnoreCase(codBanco)) {
					UtilsRendicionBBV utilsRendicionBBV = new UtilsRendicionBBV();
					
					target = utilsRendicionBBV.initProcesarRendicion(mapaParametros, request, session);
				}
				
				else {					
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.noHabilitada"));
					target = "inicio";
				}

			} else {
				logger.debug("ARCHIVO VACIO");
				ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.vacio"));
				target = "inicio";
			}
			
			DaoConfig.commitTransaction();
			
		} catch (Exception e) {
			target = "inicio";
			if (e instanceof RendicionException) {				
				ActionTools.addMessage(request, getMensajeRendicion(e.getMessage()));
			} else {
				ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacionNOK"));
			}
			logger.error("Error: ", e);
		} finally {
			DaoConfig.endTransaction();
		}
		
		logger.debug("target action: " + target);
		return mapping.findForward(target);
	}
	
	private ActionMessage getMensajeRendicion(String message) {
		ActionMessage actionMessage = new ActionMessage("");
		
		if ("".equals(message)) {
			actionMessage = new ActionMessage("rendicion.archivo.importacionNOK");
		} else if ("rendicion.archivo.linea.tamagnoMenor".equals(message)) {
			actionMessage = new ActionMessage(message, resourceRendicion.getString("REND_NRO_CARACTERES_LINEA"));
		} else {
			actionMessage = new ActionMessage(message);
		}
		
		return actionMessage;
	}
}