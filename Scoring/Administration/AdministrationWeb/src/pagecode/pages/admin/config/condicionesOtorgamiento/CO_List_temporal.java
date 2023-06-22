/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlOutputText;

import pagecode.PageCodeBase;
import pagecode.utils.FacesUtils;

import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import commonj.sdo.DataObject;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import java.sql.Connection;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.UIColumnEx;

/**
 * @author Administrator
 *
 */
public class CO_List_temporal extends PageCodeBase {

	private ConnectionWrapper SDOConnectionWrapper;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelBox box1;
	protected HtmlOutputText text1;
	protected HtmlJspPanel jspPanel1;
	protected HtmlMessages messages1;
	protected HtmlForm form1;
	protected ArrayList selectedRows;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlPanelGrid grid1;
	protected HtmlPanelBox box5;
	protected HtmlPanelGrid grid2;
	protected HtmlForm form100;
	protected HtmlJspPanel leftJspPanel;
	protected HtmlPanelLayout layout1L;
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlPanelBox box4;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	private static final String SDOConnection_name = "MotorCreditScoring";
	protected DataObject otroHuesazoParameters;
	protected JDBCMediator otroHuesazoMediator;
	private static final String otroHuesazo_metadataFileName = "/WEB-INF/wdo/originDataMCOMultiRow.xml";
	protected static final String[] otroHuesazoArgNames = {};
	protected static final String[] otroHuesazoArgValues = {};
	private static final int otroHuesazoTargetPageSize = -1;
	protected List otroHuesazo;
	protected DataObject originDataMCOMultiRowCompositeParameters;
	protected JDBCMediator originDataMCOMultiRowCompositeMediator;
	private static final String originDataMCOMultiRowComposite_metadataFileName = "/WEB-INF/wdo/originDataMCOMultiRowComposite.xml";
	protected static final String[] originDataMCOMultiRowCompositeArgNames = {};
	protected static final String[] originDataMCOMultiRowCompositeArgValues = {};
	private static final int originDataMCOMultiRowCompositeTargetPageSize = -1;
	protected List originDataMCOMultiRowComposite;
	protected HtmlDataTableEx originDataMCOMultiRowComposite1;
	protected UIColumnEx idTipoRiesgoExterno1column;
	protected HtmlOutputText idTipoRiesgoExterno1text;
	protected HtmlOutputText idTipoRentaPermitida1text;
	protected HtmlOutputText idPerfilRiesgo1text;
	protected HtmlOutputText idCondicion1text;
	protected HtmlOutputText nombre1text;
	protected HtmlOutputText nombre2text;
	protected HtmlOutputText descripcion1text;
	protected HtmlOutputText nombre3text;
	protected HtmlOutputText idTipoRiesgoExterno1;
	protected UIColumnEx idTipoRentaPermitida1column;
	protected HtmlOutputText idTipoRentaPermitida1;
	protected UIColumnEx idPerfilRiesgo1column;
	protected HtmlOutputText idPerfilRiesgo1;
	protected UIColumnEx idCondicion1column;
	protected HtmlOutputText idCondicion1;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected UIColumnEx nombre2column;
	protected HtmlOutputText nombre2;
	protected UIColumnEx descripcion1column;
	protected HtmlOutputText descripcion1;
	protected UIColumnEx nombre3column;
	protected HtmlOutputText nombre3;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
	}
	protected HtmlMessages getMessages1() {
		if (messages1 == null) {
			messages1 = (HtmlMessages) findComponentInRoot("messages1");
		}
		return messages1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	public String doNewBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// return "new";
		return "new";
	}
//	public String doDeleteBtnAction() {
//		/*
//		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
//		 * una pareja [key,value] y que el key usado segun el tab parameters del
//		 * selectionColumn es "IdClasificacionEmpresa", entonces:
//		 */
//		try {
//			// recuperar los IDs seleccionados
//			DataObject myRootDataObject = getRootDataObject(originDataMCOMultiRowSpecial);
//			debug(originDataMCOMultiRowSpecial);
//			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
//					.hasNext();) {
//				Map currentMap = (Map) iteratorSelectorColumn.next();
//				String currentIDstring = (String) currentMap.get("idRow");
//				int currentIDint = Integer.parseInt(currentIDstring); 
//				// encontrar el SDORecord correspondiente y borrarlo
//				List indexesToDelete = new ArrayList();
//				int i = 0;				
//				for (Iterator iteratorSDOList = originDataMCOMultiRowSpecial.listIterator(); iteratorSDOList
//						.hasNext();i++) {
//					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
//					if (currentSDORecord.getInt("IdAntiguedadLaboral") == currentIDint) {
//						indexesToDelete.add(new Integer(i));
//					}
//				}
//				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
//					Integer index = (Integer) iterator.next();
//					DataObject dataObjectMarkedForDeletion = (DataObject) originDataMCOMultiRowSpecial.get(index.intValue());
//					dataObjectMarkedForDeletion.delete();
//				}				
//			}
//			debug(originDataMCOMultiRowSpecial);
//			getOriginDataMCOMultiRowSpecialMediator().applyChanges(myRootDataObject);
//		} catch (Throwable e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//			logException(e);
//		} finally {
//			try {
//				if (SDOConnectionWrapper != null) {
//					SDOConnectionWrapper.getConnection().close();
//					SDOConnectionWrapper = null;
//				}
//			} catch (Throwable e1) {
//				FacesUtils.addErrorMessage(e1.getMessage());
//				logException(e1);
//			}
//			if (originDataMCOMultiRowSpecialMediator != null) {
//				originDataMCOMultiRowSpecialMediator.setConnectionWrapper(null);
//			}
//		}
//		return "";
//	}
	public ArrayList getSelectedRows() {
		if (selectedRows == null) {
			selectedRows = new ArrayList();
		}
		return selectedRows;
	}
	public void setSelectedRows(ArrayList selectedRows) {
		this.selectedRows = selectedRows;
	}
	protected HtmlScriptCollector getScriptCollector100() {
		if (scriptCollector100 == null) {
			scriptCollector100 = (HtmlScriptCollector) findComponentInRoot("scriptCollector100");
		}
		return scriptCollector100;
	}
	protected HtmlPanelLayout getLayout1() {
		if (layout1 == null) {
			layout1 = (HtmlPanelLayout) findComponentInRoot("layout1");
		}
		return layout1;
	}
	protected HtmlJspPanel getCenteJjspPanel() {
		if (centeJjspPanel == null) {
			centeJjspPanel = (HtmlJspPanel) findComponentInRoot("centeJjspPanel");
		}
		return centeJjspPanel;
	}
	protected HtmlPanelLayout getLayout1C() {
		if (layout1C == null) {
			layout1C = (HtmlPanelLayout) findComponentInRoot("layout1C");
		}
		return layout1C;
	}
	protected HtmlJspPanel getJspPanelUnder1CC() {
		if (jspPanelUnder1CC == null) {
			jspPanelUnder1CC = (HtmlJspPanel) findComponentInRoot("jspPanelUnder1CC");
		}
		return jspPanelUnder1CC;
	}
	protected HtmlPanelGrid getGrid1() {
		if (grid1 == null) {
			grid1 = (HtmlPanelGrid) findComponentInRoot("grid1");
		}
		return grid1;
	}
	protected HtmlPanelBox getBox5() {
		if (box5 == null) {
			box5 = (HtmlPanelBox) findComponentInRoot("box5");
		}
		return box5;
	}
	protected HtmlPanelGrid getGrid2() {
		if (grid2 == null) {
			grid2 = (HtmlPanelGrid) findComponentInRoot("grid2");
		}
		return grid2;
	}
	protected HtmlForm getForm100() {
		if (form100 == null) {
			form100 = (HtmlForm) findComponentInRoot("form100");
		}
		return form100;
	}
	protected HtmlJspPanel getLeftJspPanel() {
		if (leftJspPanel == null) {
			leftJspPanel = (HtmlJspPanel) findComponentInRoot("leftJspPanel");
		}
		return leftJspPanel;
	}
	protected HtmlPanelLayout getLayout1L() {
		if (layout1L == null) {
			layout1L = (HtmlPanelLayout) findComponentInRoot("layout1L");
		}
		return layout1L;
	}
	protected HtmlJspPanel getRightJspPanel() {
		if (rightJspPanel == null) {
			rightJspPanel = (HtmlJspPanel) findComponentInRoot("rightJspPanel");
		}
		return rightJspPanel;
	}
	protected HtmlPanelLayout getLayout1R() {
		if (layout1R == null) {
			layout1R = (HtmlPanelLayout) findComponentInRoot("layout1R");
		}
		return layout1R;
	}
	protected HtmlJspPanel getJspPanelUnder1RC() {
		if (jspPanelUnder1RC == null) {
			jspPanelUnder1RC = (HtmlJspPanel) findComponentInRoot("jspPanelUnder1RC");
		}
		return jspPanelUnder1RC;
	}
	protected HtmlJspPanel getBottomJspPanel() {
		if (bottomJspPanel == null) {
			bottomJspPanel = (HtmlJspPanel) findComponentInRoot("bottomJspPanel");
		}
		return bottomJspPanel;
	}
	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
	}
	protected HtmlJspPanel getTopJspPanel() {
		if (topJspPanel == null) {
			topJspPanel = (HtmlJspPanel) findComponentInRoot("topJspPanel");
		}
		return topJspPanel;
	}
	protected HtmlPanelLayout getLayout1T() {
		if (layout1T == null) {
			layout1T = (HtmlPanelLayout) findComponentInRoot("layout1T");
		}
		return layout1T;
	}
	protected HtmlJspPanel getJspPanelCenterUnder1TC() {
		if (jspPanelCenterUnder1TC == null) {
			jspPanelCenterUnder1TC = (HtmlJspPanel) findComponentInRoot("jspPanelCenterUnder1TC");
		}
		return jspPanelCenterUnder1TC;
	}
	protected HtmlGraphicImageEx getImageEx1() {
		if (imageEx1 == null) {
			imageEx1 = (HtmlGraphicImageEx) findComponentInRoot("imageEx1");
		}
		return imageEx1;
	}
	protected HtmlJspPanel getJspPanel2() {
		if (jspPanel2 == null) {
			jspPanel2 = (HtmlJspPanel) findComponentInRoot("jspPanel2");
		}
		return jspPanel2;
	}
	protected ConnectionWrapper getSDOConnectionWrapper() {
		if (SDOConnectionWrapper == null) {
			try {
				Connection con = ConnectionManager.createJDBCConnection(SDOConnection_name);
				SDOConnectionWrapper = ConnectionWrapperFactory.soleInstance
						.createConnectionWrapper(con);
			} catch (Throwable e) {
				logException(e);
			}
		}
		return SDOConnectionWrapper;
	}
	/** 
	 * @action id=otroHuesazo
	 */
	public String doOtroHuesazoUpdateAction() {
		try {
			getOtroHuesazoMediator().applyChanges(getRootDataObject(getOtroHuesazo()));
		} catch (Throwable e) {
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				logException(e1);
			}
			if (otroHuesazoMediator != null) {
				otroHuesazoMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=otroHuesazo
	 */
	public DataObject getOtroHuesazoParameters() {
		if (otroHuesazoParameters == null) {
			try {
				otroHuesazoParameters = getOtroHuesazoMediator().getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return otroHuesazoParameters;
	}
	protected JDBCMediator getOtroHuesazoMediator() {
		if (otroHuesazoMediator == null) {
			try {
				otroHuesazoMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(otroHuesazo_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(otroHuesazo_metadataFileName),
						otroHuesazoMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			otroHuesazoMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return otroHuesazoMediator;
	}
	/** 
	 * @action id=otroHuesazo
	 */
	public String doOtroHuesazoFetchAction() {
		try {
			resolveParams(getOtroHuesazoParameters(), otroHuesazoArgNames, otroHuesazoArgValues,
					"otroHuesazo_params_cache");
			DataObject graph = getOtroHuesazoMediator().getGraph(getOtroHuesazoParameters());
			otroHuesazo = graph.getList(0);
		} catch (Throwable e) {
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				logException(e1);
			}
			if (otroHuesazoMediator != null) {
				otroHuesazoMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOMultiRow.xml
	 * @methodEntry id=otroHuesazo/paramBean=otroHuesazo/action=otroHuesazo
	 * @action FILL
	 */
	public List getOtroHuesazo() {
		if (otroHuesazo == null) {
			doOtroHuesazoFetchAction();
		}
		return otroHuesazo;
	}
	/** 
	 * @action id=originDataMCOMultiRowComposite
	 */
	public String doOriginDataMCOMultiRowCompositeUpdateAction() {
		try {
			getOriginDataMCOMultiRowCompositeMediator().applyChanges(
					getRootDataObject(getOriginDataMCOMultiRowComposite()));
		} catch (Throwable e) {
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				logException(e1);
			}
			if (originDataMCOMultiRowCompositeMediator != null) {
				originDataMCOMultiRowCompositeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataMCOMultiRowComposite
	 */
	public DataObject getOriginDataMCOMultiRowCompositeParameters() {
		if (originDataMCOMultiRowCompositeParameters == null) {
			try {
				originDataMCOMultiRowCompositeParameters = getOriginDataMCOMultiRowCompositeMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataMCOMultiRowCompositeParameters;
	}
	protected JDBCMediator getOriginDataMCOMultiRowCompositeMediator() {
		if (originDataMCOMultiRowCompositeMediator == null) {
			try {
				originDataMCOMultiRowCompositeMediator = JDBCMediatorFactory.soleInstance
						.createMediator(
								getResourceInputStream(originDataMCOMultiRowComposite_metadataFileName),
								getSDOConnectionWrapper());
				initSchema(getRealPath(originDataMCOMultiRowComposite_metadataFileName),
						originDataMCOMultiRowCompositeMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataMCOMultiRowCompositeMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataMCOMultiRowCompositeMediator;
	}
	/** 
	 * @action id=originDataMCOMultiRowComposite
	 */
	public String doOriginDataMCOMultiRowCompositeFetchAction() {
		try {
			resolveParams(getOriginDataMCOMultiRowCompositeParameters(),
					originDataMCOMultiRowCompositeArgNames,
					originDataMCOMultiRowCompositeArgValues,
					"originDataMCOMultiRowComposite_params_cache");
			DataObject graph = getOriginDataMCOMultiRowCompositeMediator().getGraph(
					getOriginDataMCOMultiRowCompositeParameters());
			originDataMCOMultiRowComposite = graph.getList(0);
		} catch (Throwable e) {
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				logException(e1);
			}
			if (originDataMCOMultiRowCompositeMediator != null) {
				originDataMCOMultiRowCompositeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOMultiRowComposite.xml
	 * @methodEntry id=originDataMCOMultiRowComposite/paramBean=originDataMCOMultiRowComposite/action=originDataMCOMultiRowComposite
	 * @action FILL
	 */
	public List getOriginDataMCOMultiRowComposite() {
		if (originDataMCOMultiRowComposite == null) {
			doOriginDataMCOMultiRowCompositeFetchAction();
		}
		return originDataMCOMultiRowComposite;
	}
	protected HtmlDataTableEx getOriginDataMCOMultiRowComposite1() {
		if (originDataMCOMultiRowComposite1 == null) {
			originDataMCOMultiRowComposite1 = (HtmlDataTableEx) findComponentInRoot("originDataMCOMultiRowComposite1");
		}
		return originDataMCOMultiRowComposite1;
	}
	protected UIColumnEx getIdTipoRiesgoExterno1column() {
		if (idTipoRiesgoExterno1column == null) {
			idTipoRiesgoExterno1column = (UIColumnEx) findComponentInRoot("idTipoRiesgoExterno1column");
		}
		return idTipoRiesgoExterno1column;
	}
	protected HtmlOutputText getIdTipoRiesgoExterno1text() {
		if (idTipoRiesgoExterno1text == null) {
			idTipoRiesgoExterno1text = (HtmlOutputText) findComponentInRoot("idTipoRiesgoExterno1text");
		}
		return idTipoRiesgoExterno1text;
	}
	protected HtmlOutputText getIdTipoRentaPermitida1text() {
		if (idTipoRentaPermitida1text == null) {
			idTipoRentaPermitida1text = (HtmlOutputText) findComponentInRoot("idTipoRentaPermitida1text");
		}
		return idTipoRentaPermitida1text;
	}
	protected HtmlOutputText getIdPerfilRiesgo1text() {
		if (idPerfilRiesgo1text == null) {
			idPerfilRiesgo1text = (HtmlOutputText) findComponentInRoot("idPerfilRiesgo1text");
		}
		return idPerfilRiesgo1text;
	}
	protected HtmlOutputText getIdCondicion1text() {
		if (idCondicion1text == null) {
			idCondicion1text = (HtmlOutputText) findComponentInRoot("idCondicion1text");
		}
		return idCondicion1text;
	}
	protected HtmlOutputText getNombre1text() {
		if (nombre1text == null) {
			nombre1text = (HtmlOutputText) findComponentInRoot("nombre1text");
		}
		return nombre1text;
	}
	protected HtmlOutputText getNombre2text() {
		if (nombre2text == null) {
			nombre2text = (HtmlOutputText) findComponentInRoot("nombre2text");
		}
		return nombre2text;
	}
	protected HtmlOutputText getDescripcion1text() {
		if (descripcion1text == null) {
			descripcion1text = (HtmlOutputText) findComponentInRoot("descripcion1text");
		}
		return descripcion1text;
	}
	protected HtmlOutputText getNombre3text() {
		if (nombre3text == null) {
			nombre3text = (HtmlOutputText) findComponentInRoot("nombre3text");
		}
		return nombre3text;
	}
	protected HtmlOutputText getIdTipoRiesgoExterno1() {
		if (idTipoRiesgoExterno1 == null) {
			idTipoRiesgoExterno1 = (HtmlOutputText) findComponentInRoot("idTipoRiesgoExterno1");
		}
		return idTipoRiesgoExterno1;
	}
	protected UIColumnEx getIdTipoRentaPermitida1column() {
		if (idTipoRentaPermitida1column == null) {
			idTipoRentaPermitida1column = (UIColumnEx) findComponentInRoot("idTipoRentaPermitida1column");
		}
		return idTipoRentaPermitida1column;
	}
	protected HtmlOutputText getIdTipoRentaPermitida1() {
		if (idTipoRentaPermitida1 == null) {
			idTipoRentaPermitida1 = (HtmlOutputText) findComponentInRoot("idTipoRentaPermitida1");
		}
		return idTipoRentaPermitida1;
	}
	protected UIColumnEx getIdPerfilRiesgo1column() {
		if (idPerfilRiesgo1column == null) {
			idPerfilRiesgo1column = (UIColumnEx) findComponentInRoot("idPerfilRiesgo1column");
		}
		return idPerfilRiesgo1column;
	}
	protected HtmlOutputText getIdPerfilRiesgo1() {
		if (idPerfilRiesgo1 == null) {
			idPerfilRiesgo1 = (HtmlOutputText) findComponentInRoot("idPerfilRiesgo1");
		}
		return idPerfilRiesgo1;
	}
	protected UIColumnEx getIdCondicion1column() {
		if (idCondicion1column == null) {
			idCondicion1column = (UIColumnEx) findComponentInRoot("idCondicion1column");
		}
		return idCondicion1column;
	}
	protected HtmlOutputText getIdCondicion1() {
		if (idCondicion1 == null) {
			idCondicion1 = (HtmlOutputText) findComponentInRoot("idCondicion1");
		}
		return idCondicion1;
	}
	protected UIColumnEx getNombre1column() {
		if (nombre1column == null) {
			nombre1column = (UIColumnEx) findComponentInRoot("nombre1column");
		}
		return nombre1column;
	}
	protected HtmlOutputText getNombre1() {
		if (nombre1 == null) {
			nombre1 = (HtmlOutputText) findComponentInRoot("nombre1");
		}
		return nombre1;
	}
	protected UIColumnEx getNombre2column() {
		if (nombre2column == null) {
			nombre2column = (UIColumnEx) findComponentInRoot("nombre2column");
		}
		return nombre2column;
	}
	protected HtmlOutputText getNombre2() {
		if (nombre2 == null) {
			nombre2 = (HtmlOutputText) findComponentInRoot("nombre2");
		}
		return nombre2;
	}
	protected UIColumnEx getDescripcion1column() {
		if (descripcion1column == null) {
			descripcion1column = (UIColumnEx) findComponentInRoot("descripcion1column");
		}
		return descripcion1column;
	}
	protected HtmlOutputText getDescripcion1() {
		if (descripcion1 == null) {
			descripcion1 = (HtmlOutputText) findComponentInRoot("descripcion1");
		}
		return descripcion1;
	}
	protected UIColumnEx getNombre3column() {
		if (nombre3column == null) {
			nombre3column = (UIColumnEx) findComponentInRoot("nombre3column");
		}
		return nombre3column;
	}
	protected HtmlOutputText getNombre3() {
		if (nombre3 == null) {
			nombre3 = (HtmlOutputText) findComponentInRoot("nombre3");
		}
		return nombre3;
	}

}