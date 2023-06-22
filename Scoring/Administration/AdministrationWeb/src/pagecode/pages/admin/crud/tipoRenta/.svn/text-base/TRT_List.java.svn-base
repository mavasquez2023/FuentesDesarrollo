/**
 * 
 */
package pagecode.pages.admin.crud.tipoRenta;

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
import javax.faces.component.UIParameter;
import java.util.ArrayList;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlRequestLink;

/**
 * @author Administrator
 *
 */
public class TRT_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataTRTMultiRowParameters;
	protected JDBCMediator originDataTRTMultiRowMediator;
	private static final String originDataTRTMultiRow_metadataFileName = "/WEB-INF/wdo/originDataTRTMultiRow.xml";
	protected static final String[] originDataTRTMultiRowArgNames = {};
	protected static final String[] originDataTRTMultiRowArgValues = {};
	private static final int originDataTRTMultiRowTargetPageSize = -1;
	protected List originDataTRTMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx originDataTRTMultiRow1;
	protected HtmlOutputText nombre1text;
	protected HtmlOutputText descripcion1text;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected UIColumnEx descripcion1column;
	protected HtmlOutputText descripcion1;
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box2;
	protected HtmlInputRowSelect rowSelect1;
	protected UIColumnEx columnEx1;
	protected HtmlCommandExButton doOriginDataTRTMultiRowFetchAction1;
	protected HtmlCommandExButton newBtn;
	protected UIParameter param1;
	protected ArrayList selectedRows;
	protected HtmlCommandExButton button1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
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
	 * @action id=originDataTRTMultiRow
	 */
	public String doOriginDataTRTMultiRowUpdateAction() {
		try {
			getOriginDataTRTMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataTRTMultiRow()));
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
			if (originDataTRTMultiRowMediator != null) {
				originDataTRTMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataTRTMultiRow
	 */
	public DataObject getOriginDataTRTMultiRowParameters() {
		if (originDataTRTMultiRowParameters == null) {
			try {
				originDataTRTMultiRowParameters = getOriginDataTRTMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataTRTMultiRowParameters;
	}
	protected JDBCMediator getOriginDataTRTMultiRowMediator() {
		if (originDataTRTMultiRowMediator == null) {
			try {
				originDataTRTMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataTRTMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataTRTMultiRow_metadataFileName),
						originDataTRTMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataTRTMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataTRTMultiRowMediator;
	}
	/** 
	 * @action id=originDataTRTMultiRow
	 */
	public String doOriginDataTRTMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataTRTMultiRowParameters(), originDataTRTMultiRowArgNames,
					originDataTRTMultiRowArgValues, "originDataTRTMultiRow_params_cache");
			DataObject graph = getOriginDataTRTMultiRowMediator().getGraph(
					getOriginDataTRTMultiRowParameters());
			originDataTRTMultiRow = graph.getList(0);
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
			if (originDataTRTMultiRowMediator != null) {
				originDataTRTMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRTMultiRow.xml
	 * @methodEntry id=originDataTRTMultiRow/paramBean=originDataTRTMultiRow/action=originDataTRTMultiRow
	 * @action FILL
	 */
	public List getOriginDataTRTMultiRow() {
		if (originDataTRTMultiRow == null) {
			doOriginDataTRTMultiRowFetchAction();
		}
		return originDataTRTMultiRow;
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
	protected HtmlDataTableEx getOriginDataTRTMultiRow1() {
		if (originDataTRTMultiRow1 == null) {
			originDataTRTMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataTRTMultiRow1");
		}
		return originDataTRTMultiRow1;
	}
	protected HtmlOutputText getNombre1text() {
		if (nombre1text == null) {
			nombre1text = (HtmlOutputText) findComponentInRoot("nombre1text");
		}
		return nombre1text;
	}
	protected HtmlOutputText getDescripcion1text() {
		if (descripcion1text == null) {
			descripcion1text = (HtmlOutputText) findComponentInRoot("descripcion1text");
		}
		return descripcion1text;
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
	protected HtmlCommandExButton getDoOriginDataTRTMultiRowFetchAction1() {
		if (doOriginDataTRTMultiRowFetchAction1 == null) {
			doOriginDataTRTMultiRowFetchAction1 = (HtmlCommandExButton) findComponentInRoot("doOriginDataTRTMultiRowFetchAction1");
		}
		return doOriginDataTRTMultiRowFetchAction1;
	}
	protected HtmlCommandExButton getNewBtn() {
		if (newBtn == null) {
			newBtn = (HtmlCommandExButton) findComponentInRoot("newBtn");
		}
		return newBtn;
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
	
	public String doDeleteTipoRentaBtnAction() {
		/*
		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
		 * una pareja [key,value] y que el key usado segun el tab parameters del
		 * selectionColumn es "IdClasificacionEmpresa", entonces:
		 */
		try {
			// recuperar los IDs seleccionados
			DataObject myRootDataObject = getRootDataObject(originDataTRTMultiRow);
			debug(originDataTRTMultiRow);
			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
					.hasNext();) {
				Map currentMap = (Map) iteratorSelectorColumn.next();
				String currentIDstring = (String) currentMap.get("idRow");
				int currentIDint = Integer.parseInt(currentIDstring); 
				// encontrar el SDORecord correspondiente y borrarlo
				List indexesToDelete = new ArrayList();
				int i = 0;				
				for (Iterator iteratorSDOList = originDataTRTMultiRow.listIterator(); iteratorSDOList
						.hasNext();i++) {
					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
					if (currentSDORecord.getInt("IdAntiguedadLaboral") == currentIDint) {
						indexesToDelete.add(new Integer(i));
					}
				}
				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
					Integer index = (Integer) iterator.next();
					DataObject dataObjectMarkedForDeletion = (DataObject) originDataTRTMultiRow.get(index.intValue());
					dataObjectMarkedForDeletion.delete();
				}				
			}
			debug(originDataTRTMultiRow);
			getOriginDataTRTMultiRowMediator().applyChanges(myRootDataObject);
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
			if (originDataTRTMultiRowMediator != null) {
				originDataTRTMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
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
		Object idTipoRenta = getRequestParam().get("idTRT");
		getRequestScope().put("_param_idTipoRenta", idTipoRenta);
		// return "edit";
		return "edit";
	
	}
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}
	public String doNewBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// return "edit";
		// return "new";
		return "new";
	}

}