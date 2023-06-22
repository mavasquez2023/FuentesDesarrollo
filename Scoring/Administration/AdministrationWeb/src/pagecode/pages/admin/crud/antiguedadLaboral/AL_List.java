/**
 * 
 */
package pagecode.pages.admin.crud.antiguedadLaboral;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;

import pagecode.PageCodeBase;
import pagecode.utils.FacesUtils;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlInputRowSelect;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlPanelLayout;
import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import com.ibm.ws.sdo.mediator.jdbc.queryengine.QueryEngineException;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 *
 */
public class AL_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataALMultiRowParameters;
	protected JDBCMediator originDataALMultiRowMediator;
	private static final String originDataALMultiRow_metadataFileName = "/WEB-INF/wdo/originDataALMultiRow.xml";
	protected static final String[] originDataALMultiRowArgNames = {};
	protected static final String[] originDataALMultiRowArgValues = {};
	private static final int originDataALMultiRowTargetPageSize = -1;
	protected List originDataALMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx originDataALMultiRow1;
	protected HtmlOutputText nombre1text;
	protected HtmlOutputText desde1text;
	protected HtmlOutputText hasta1text;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected UIColumnEx desde1column;
	protected HtmlOutputText desde1;
	protected UIColumnEx hasta1column;
	protected HtmlOutputText hasta1;
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box2;
	protected HtmlCommandExButton refreshBtn;
	protected HtmlInputRowSelect rowSelect1;
	protected UIColumnEx columnEx1;
	protected UIParameter param1;
	protected ArrayList selectedRows;
	protected HtmlCommandExButton deleteBtn;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlJspPanel leftJspPanel;
	protected HtmlPanelLayout layout1L;
	protected HtmlJspPanel jspPanelUnder1LC;
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlPanelLayout layout1B;
	protected HtmlJspPanel jspPanelUnder1BC;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText text1;
	protected HtmlMessages messages1;
	protected HtmlCommandExButton newBtn;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText text3;
	protected HtmlRequestLink link1;
	protected HtmlJspPanel jspPanel3;
	protected UIParameter param2;
	protected ConnectionWrapper getSDOConnectionWrapper() {
		if (SDOConnectionWrapper == null) {
			try {
				Connection con = ConnectionManager.createJDBCConnection(SDOConnection_name);
				SDOConnectionWrapper = ConnectionWrapperFactory.soleInstance
						.createConnectionWrapper(con);
			} catch (Throwable e) {
				FacesUtils.addErrorMessage(e.getMessage());
				logException(e);
			}
		}
		return SDOConnectionWrapper;
	}
	/** 
	 * @action id=originDataALMultiRow
	 */
	public String doOriginDataALMultiRowUpdateAction() {
		try {
			getOriginDataALMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataALMultiRow()));
		} catch (Throwable e) {
			FacesUtils.addErrorMessage(e.getMessage());
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
			if (originDataALMultiRowMediator != null) {
				originDataALMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataALMultiRow
	 */
	public DataObject getOriginDataALMultiRowParameters() {
		if (originDataALMultiRowParameters == null) {
			try {
				originDataALMultiRowParameters = getOriginDataALMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataALMultiRowParameters;
	}
	protected JDBCMediator getOriginDataALMultiRowMediator() {
		if (originDataALMultiRowMediator == null) {
			try {
				originDataALMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataALMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataALMultiRow_metadataFileName),
						originDataALMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataALMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataALMultiRowMediator;
	}
	/** 
	 * @action id=originDataALMultiRow
	 */
	public String doOriginDataALMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataALMultiRowParameters(), originDataALMultiRowArgNames,
					originDataALMultiRowArgValues, "originDataALMultiRow_params_cache");
			DataObject graph = getOriginDataALMultiRowMediator().getGraph(
					getOriginDataALMultiRowParameters());
			originDataALMultiRow = graph.getList(0);
		} catch (Throwable e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				FacesUtils.addErrorMessage(e1.getMessage());
				logException(e1);
			}
			if (originDataALMultiRowMediator != null) {
				originDataALMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataALMultiRow.xml
	 * @methodEntry id=originDataALMultiRow/paramBean=originDataALMultiRow/action=originDataALMultiRow
	 * @action FILL
	 */
	public List getOriginDataALMultiRow() {
		if (originDataALMultiRow == null) {
			doOriginDataALMultiRowFetchAction();
		}
		return originDataALMultiRow;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlDataTableEx getOriginDataALMultiRow1() {
		if (originDataALMultiRow1 == null) {
			originDataALMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataALMultiRow1");
		}
		return originDataALMultiRow1;
	}
	protected HtmlOutputText getNombre1text() {
		if (nombre1text == null) {
			nombre1text = (HtmlOutputText) findComponentInRoot("nombre1text");
		}
		return nombre1text;
	}
	protected HtmlOutputText getDesde1text() {
		if (desde1text == null) {
			desde1text = (HtmlOutputText) findComponentInRoot("desde1text");
		}
		return desde1text;
	}
	protected HtmlOutputText getHasta1text() {
		if (hasta1text == null) {
			hasta1text = (HtmlOutputText) findComponentInRoot("hasta1text");
		}
		return hasta1text;
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
	protected UIColumnEx getDesde1column() {
		if (desde1column == null) {
			desde1column = (UIColumnEx) findComponentInRoot("desde1column");
		}
		return desde1column;
	}
	protected HtmlOutputText getDesde1() {
		if (desde1 == null) {
			desde1 = (HtmlOutputText) findComponentInRoot("desde1");
		}
		return desde1;
	}
	protected UIColumnEx getHasta1column() {
		if (hasta1column == null) {
			hasta1column = (UIColumnEx) findComponentInRoot("hasta1column");
		}
		return hasta1column;
	}
	protected HtmlOutputText getHasta1() {
		if (hasta1 == null) {
			hasta1 = (HtmlOutputText) findComponentInRoot("hasta1");
		}
		return hasta1;
	}
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	protected HtmlPanelBox getBox2() {
		if (box2 == null) {
			box2 = (HtmlPanelBox) findComponentInRoot("box2");
		}
		return box2;
	}
	protected HtmlCommandExButton getRefreshBtn() {
		if (refreshBtn == null) {
			refreshBtn = (HtmlCommandExButton) findComponentInRoot("refreshBtn");
		}
		return refreshBtn;
	}
	protected HtmlInputRowSelect getRowSelect1() {
		if (rowSelect1 == null) {
			rowSelect1 = (HtmlInputRowSelect) findComponentInRoot("rowSelect1");
		}
		return rowSelect1;
	}
	protected UIColumnEx getColumnEx1() {
		if (columnEx1 == null) {
			columnEx1 = (UIColumnEx) findComponentInRoot("columnEx1");
		}
		return columnEx1;
	}
	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}
	public ArrayList getSelectedRows() {
		if (selectedRows == null) {
			selectedRows = new ArrayList();
		}
		return selectedRows;
	}
	public void setSelectedRows(ArrayList selectedRows) {
		this.selectedRows = selectedRows;
	}
	protected HtmlCommandExButton getDeleteBtn() {
		if (deleteBtn == null) {
			deleteBtn = (HtmlCommandExButton) findComponentInRoot("deleteBtn");
		}
		return deleteBtn;
	}
	public String doDeleteAntiguedadLaboralBtnAction() {
		/*
		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
		 * una pareja [key,value] y que el key usado segun el tab parameters del
		 * selectionColumn es "IdClasificacionEmpresa", entonces:
		 */
		try {
			// recuperar los IDs seleccionados
			DataObject myRootDataObject = getRootDataObject(originDataALMultiRow);
			debug(originDataALMultiRow);
			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
					.hasNext();) {
				Map currentMap = (Map) iteratorSelectorColumn.next();
				String currentIDstring = (String) currentMap.get("idRow");
				int currentIDint = Integer.parseInt(currentIDstring); 
				// encontrar el SDORecord correspondiente y borrarlo
				List indexesToDelete = new ArrayList();
				int i = 0;				
				for (Iterator iteratorSDOList = originDataALMultiRow.listIterator(); iteratorSDOList
						.hasNext();i++) {
					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
					if (currentSDORecord.getInt("IdAntiguedadLaboral") == currentIDint) {
						indexesToDelete.add(new Integer(i));
					}
				}
				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
					Integer index = (Integer) iterator.next();
					DataObject dataObjectMarkedForDeletion = (DataObject) originDataALMultiRow.get(index.intValue());
					dataObjectMarkedForDeletion.delete();
				}				
			}
			debug(originDataALMultiRow);
			getOriginDataALMultiRowMediator().applyChanges(myRootDataObject);
		} catch (MediatorException e) {
			FacesUtils.addErrorMessage("No se pudo efectuar la operación.");
			if(e.getOriginalException() instanceof QueryEngineException){
				String originalMessage = e.getMessage();
				String message = originalMessage.substring(originalMessage.indexOf("REFERENCE constraint"));
				message = message.substring(20);
				FacesUtils.addErrorMessage("Información del constraint : " + message);				
			}
			logException(e);
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				FacesUtils.addErrorMessage(e1.getMessage());
				logException(e1);
			}
			if (originDataALMultiRowMediator != null) {
				originDataALMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
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
	protected HtmlJspPanel getJspPanelUnder1LC() {
		if (jspPanelUnder1LC == null) {
			jspPanelUnder1LC = (HtmlJspPanel) findComponentInRoot("jspPanelUnder1LC");
		}
		return jspPanelUnder1LC;
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
	protected HtmlPanelLayout getLayout1B() {
		if (layout1B == null) {
			layout1B = (HtmlPanelLayout) findComponentInRoot("layout1B");
		}
		return layout1B;
	}
	protected HtmlJspPanel getJspPanelUnder1BC() {
		if (jspPanelUnder1BC == null) {
			jspPanelUnder1BC = (HtmlJspPanel) findComponentInRoot("jspPanelUnder1BC");
		}
		return jspPanelUnder1BC;
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
	protected HtmlPanelGrid getGrid1() {
		if (grid1 == null) {
			grid1 = (HtmlPanelGrid) findComponentInRoot("grid1");
		}
		return grid1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlMessages getMessages1() {
		if (messages1 == null) {
			messages1 = (HtmlMessages) findComponentInRoot("messages1");
		}
		return messages1;
	}
	protected HtmlCommandExButton getNewBtn() {
		if (newBtn == null) {
			newBtn = (HtmlCommandExButton) findComponentInRoot("newBtn");
		}
		return newBtn;
	}
	public String doNewBtnAction() {
		// This is java code that runs when this action method is invoked	
		// return "new";
		// return "edit";
		return "new";
	}
	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}
	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}
	protected HtmlRequestLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlRequestLink) findComponentInRoot("link1");
		}
		return link1;
	}
	protected HtmlJspPanel getJspPanel3() {
		if (jspPanel3 == null) {
			jspPanel3 = (HtmlJspPanel) findComponentInRoot("jspPanel3");
		}
		return jspPanel3;
	}
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}
	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		Object idAntiguedad = getRequestParam().get("idAL");
		getRequestScope().put("_param_idAntiguedad", idAntiguedad);
		return "edit";
	}

}