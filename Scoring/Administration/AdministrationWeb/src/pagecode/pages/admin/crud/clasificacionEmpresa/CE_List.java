/**
 * 
 */
package pagecode.pages.admin.crud.clasificacionEmpresa;

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
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlInputRowSelect;
import java.util.ArrayList;
import javax.faces.component.UIParameter;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 *
 */
public class CE_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCEMultiRowParameters;
	protected JDBCMediator originDataCEMultiRowMediator;
	private static final String originDataCEMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCEMultiRow.xml";
	protected static final String[] originDataCEMultiRowArgNames = {};
	protected static final String[] originDataCEMultiRowArgValues = {};
	private static final int originDataCEMultiRowTargetPageSize = -1;
	protected List originDataCEMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx originDataCEMultiRow1;
	protected HtmlOutputText nombre1text;
	protected HtmlOutputText descripcion1text;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected UIColumnEx descripcion1column;
	protected HtmlOutputText descripcion1;
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box2;
	protected HtmlCommandExButton refreshBtn;
	protected HtmlInputRowSelect rowSelect1;
	protected UIColumnEx columnEx1;
	protected ArrayList selectedRows;
	protected HtmlCommandExButton deleteBtn;
	protected UIParameter param1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlMessages messages1;
	protected HtmlCommandExButton newBtn;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText text3;
	protected HtmlRequestLink link1;
	protected UIParameter param2;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlPanelBox box3;
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
	protected HtmlOutputText text1;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlJspPanel jspPanel1;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
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
	 * @action id=originDataCEMultiRow
	 */
	public String doOriginDataCEMultiRowUpdateAction() {
		try {
			getOriginDataCEMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataCEMultiRow()));
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
			if (originDataCEMultiRowMediator != null) {
				originDataCEMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataCEMultiRow
	 */
	public DataObject getOriginDataCEMultiRowParameters() {
		if (originDataCEMultiRowParameters == null) {
			try {
				originDataCEMultiRowParameters = getOriginDataCEMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCEMultiRowParameters;
	}
	protected JDBCMediator getOriginDataCEMultiRowMediator() {
		if (originDataCEMultiRowMediator == null) {
			try {
				originDataCEMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCEMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCEMultiRow_metadataFileName),
						originDataCEMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCEMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCEMultiRowMediator;
	}
	/** 
	 * @action id=originDataCEMultiRow
	 */
	public String doOriginDataCEMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataCEMultiRowParameters(), originDataCEMultiRowArgNames,
					originDataCEMultiRowArgValues, "originDataCEMultiRow_params_cache");
			DataObject graph = getOriginDataCEMultiRowMediator().getGraph(
					getOriginDataCEMultiRowParameters());
			originDataCEMultiRow = graph.getList(0);
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
			if (originDataCEMultiRowMediator != null) {
				originDataCEMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCEMultiRow.xml
	 * @methodEntry id=originDataCEMultiRow/paramBean=originDataCEMultiRow/action=originDataCEMultiRow
	 * @action FILL
	 */
	public List getOriginDataCEMultiRow() {
		if (originDataCEMultiRow == null) {
			doOriginDataCEMultiRowFetchAction();
		}
		return originDataCEMultiRow;
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
	protected HtmlDataTableEx getOriginDataCEMultiRow1() {
		if (originDataCEMultiRow1 == null) {
			originDataCEMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataCEMultiRow1");
		}
		return originDataCEMultiRow1;
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
	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}
	public String doDeleteBtnAction() {
		/*
		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
		 * una pareja [key,value] y que el key usado segun el tab parameters del
		 * selectionColumn es "IdClasificacionEmpresa", entonces:
		 */
		try {
			// recuperar los IDs seleccionados
			DataObject myRootDataObject = getRootDataObject(originDataCEMultiRow);
			debug(originDataCEMultiRow);
			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
					.hasNext();) {
				Map currentMap = (Map) iteratorSelectorColumn.next();
				String currentIDstring = (String) currentMap.get("idRow");
				int currentIDint = Integer.parseInt(currentIDstring); 
				// encontrar el SDORecord correspondiente y borrarlo
				List indexesToDelete = new ArrayList();
				int i = 0;				
				for (Iterator iteratorSDOList = originDataCEMultiRow.listIterator(); iteratorSDOList
						.hasNext();i++) {
					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
					if (currentSDORecord.getInt("IdClasificacionEmpresa") == currentIDint) {
						indexesToDelete.add(new Integer(i));
					}
				}
				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
					Integer index = (Integer) iterator.next();
					DataObject dataObjectMarkedForDeletion = (DataObject) originDataCEMultiRow.get(index.intValue());
					dataObjectMarkedForDeletion.delete();
				}				
			}
			debug(originDataCEMultiRow);
			getOriginDataCEMultiRowMediator().applyChanges(myRootDataObject);
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
			if (originDataCEMultiRowMediator != null) {
				originDataCEMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
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
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}
	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		Object idClasificacion = getRequestParam().get("idCE");
		getRequestScope().put("_param_idClasificacionEmpresa", idClasificacion);
		// return "new";
		// return "edit";
		return "edit";
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
	protected HtmlPanelBox getBox3() {
		if (box3 == null) {
			box3 = (HtmlPanelBox) findComponentInRoot("box3");
		}
		return box3;
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
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
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
	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
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
	public String doTestNewBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "edit";
		// return "new";
		// return "testNewKeyGen";
		return "testNewKeyGen";
	}

}