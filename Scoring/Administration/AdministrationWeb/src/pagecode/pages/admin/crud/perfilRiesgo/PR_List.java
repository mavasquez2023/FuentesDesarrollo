/**
 * 
 */
package pagecode.pages.admin.crud.perfilRiesgo;

import pagecode.PageCodeBase;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import commonj.sdo.DataObject;
import java.sql.Connection;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.UIColumnEx;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlInputRowSelect;
import com.ibm.faces.component.html.HtmlCommandExButton;
import java.util.ArrayList;
import javax.faces.component.UIParameter;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlRequestLink;

/**
 * @author Administrator
 *
 */
public class PR_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataPRMultiRowParameters;
	protected JDBCMediator originDataPRMultiRowMediator;
	private static final String originDataPRMultiRow_metadataFileName = "/WEB-INF/wdo/originDataPRMultiRow.xml";
	protected static final String[] originDataPRMultiRowArgNames = {};
	protected static final String[] originDataPRMultiRowArgValues = {};
	private static final int originDataPRMultiRowTargetPageSize = -1;
	protected List originDataPRMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx originDataPRMultiRow1;
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
	protected HtmlInputRowSelect rowSelect1;
	protected UIColumnEx columnEx1;
	protected HtmlCommandExButton refreshBtn;
	protected ArrayList selectedRows;
	protected UIParameter param1;
	protected HtmlCommandExButton deleteBtn;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlMessages messages1;
	protected HtmlCommandExButton newBtn;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText text3;
	protected HtmlRequestLink link1;
	protected UIParameter param2;
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
	 * @action id=originDataPRMultiRow
	 */
	public String doOriginDataPRMultiRowUpdateAction() {
		try {
			getOriginDataPRMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataPRMultiRow()));
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
			if (originDataPRMultiRowMediator != null) {
				originDataPRMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataPRMultiRow
	 */
	public DataObject getOriginDataPRMultiRowParameters() {
		if (originDataPRMultiRowParameters == null) {
			try {
				originDataPRMultiRowParameters = getOriginDataPRMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataPRMultiRowParameters;
	}
	protected JDBCMediator getOriginDataPRMultiRowMediator() {
		if (originDataPRMultiRowMediator == null) {
			try {
				originDataPRMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataPRMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataPRMultiRow_metadataFileName),
						originDataPRMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataPRMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataPRMultiRowMediator;
	}
	/** 
	 * @action id=originDataPRMultiRow
	 */
	public String doOriginDataPRMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataPRMultiRowParameters(), originDataPRMultiRowArgNames,
					originDataPRMultiRowArgValues, "originDataPRMultiRow_params_cache");
			DataObject graph = getOriginDataPRMultiRowMediator().getGraph(
					getOriginDataPRMultiRowParameters());
			originDataPRMultiRow = graph.getList(0);
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
			if (originDataPRMultiRowMediator != null) {
				originDataPRMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataPRMultiRow.xml
	 * @methodEntry id=originDataPRMultiRow/paramBean=originDataPRMultiRow/action=originDataPRMultiRow
	 * @action FILL
	 */
	public List getOriginDataPRMultiRow() {
		if (originDataPRMultiRow == null) {
			doOriginDataPRMultiRowFetchAction();
		}
		return originDataPRMultiRow;
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
	protected HtmlDataTableEx getOriginDataPRMultiRow1() {
		if (originDataPRMultiRow1 == null) {
			originDataPRMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataPRMultiRow1");
		}
		return originDataPRMultiRow1;
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
	protected HtmlCommandExButton getRefreshBtn() {
		if (refreshBtn == null) {
			refreshBtn = (HtmlCommandExButton) findComponentInRoot("refreshBtn");
		}
		return refreshBtn;
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
	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}
	
	public String doDeletePerfilRiesgoBtnAction() {
		/*
		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
		 * una pareja [key,value] y que el key usado segun el tab parameters del
		 * selectionColumn es "IdClasificacionEmpresa", entonces:
		 */
		try {
			// recuperar los IDs seleccionados
			DataObject myRootDataObject = getRootDataObject(originDataPRMultiRow);
			debug(originDataPRMultiRow);
			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
					.hasNext();) {
				Map currentMap = (Map) iteratorSelectorColumn.next();
				String currentIDstring = (String) currentMap.get("idRow");
				int currentIDint = Integer.parseInt(currentIDstring); 
				// encontrar el SDORecord correspondiente y borrarlo
				List indexesToDelete = new ArrayList();
				int i = 0;				
				for (Iterator iteratorSDOList = originDataPRMultiRow.listIterator(); iteratorSDOList
						.hasNext();i++) {
					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
					if (currentSDORecord.getInt("IdAntiguedadLaboral") == currentIDint) {
						indexesToDelete.add(new Integer(i));
					}
				}
				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
					Integer index = (Integer) iterator.next();
					DataObject dataObjectMarkedForDeletion = (DataObject) originDataPRMultiRow.get(index.intValue());
					dataObjectMarkedForDeletion.delete();
				}				
			}
			debug(originDataPRMultiRow);
			getOriginDataPRMultiRowMediator().applyChanges(myRootDataObject);
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
			if (originDataPRMultiRowMediator != null) {
				originDataPRMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	protected HtmlCommandExButton getDeleteBtn() {
		if (deleteBtn == null) {
			deleteBtn = (HtmlCommandExButton) findComponentInRoot("deleteBtn");
		}
		return deleteBtn;
	}
	protected HtmlPanelBox getBox6() {
		if (box6 == null) {
			box6 = (HtmlPanelBox) findComponentInRoot("box6");
		}
		return box6;
	}
	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}
	protected HtmlJspPanel getJspPanel3() {
		if (jspPanel3 == null) {
			jspPanel3 = (HtmlJspPanel) findComponentInRoot("jspPanel3");
		}
		return jspPanel3;
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
	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		// return "new";
		// return "edit";
		Object idPerfilRiesgo = getRequestParam().get("idPR");
		getRequestScope().put("_param_idPerfilRiesgo",idPerfilRiesgo);	
		return "edit";
	}
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}

}