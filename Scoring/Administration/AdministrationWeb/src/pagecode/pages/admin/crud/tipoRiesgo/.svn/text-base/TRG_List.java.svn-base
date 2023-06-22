/**
 * 
 */
package pagecode.pages.admin.crud.tipoRiesgo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;

import pagecode.PageCodeBase;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
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
import commonj.sdo.DataObject;

/**
 * @author Administrator
 *
 */
public class TRG_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataTRGMultiRowParameters;
	protected JDBCMediator originDataTRGMultiRowMediator;
	private static final String originDataTRGMultiRow_metadataFileName = "/WEB-INF/wdo/originDataTRGMultiRow.xml";
	protected static final String[] originDataTRGMultiRowArgNames = {};
	protected static final String[] originDataTRGMultiRowArgValues = {};
	private static final int originDataTRGMultiRowTargetPageSize = -1;
	protected List originDataTRGMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx originDataTRGMultiRow1;
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
	protected HtmlCommandExButton doOriginDataTRGMultiRowFetchAction1;
	protected ArrayList selectedRows;
	protected UIParameter param1;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlForm form100;
	protected HtmlJspPanel leftJspPanel;
	protected HtmlPanelLayout layout1L;
	protected HtmlCommandExButton button1;
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlPanelLayout layout1B;
	protected HtmlJspPanel jspPanelUnder1BC;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlJspPanel jspPanel1;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlJspPanel jspPanelUnder1LC;
	protected HtmlCommandExButton deleteBtn;
	protected HtmlCommandExButton newBtn;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText text1;
	protected HtmlRequestLink link1;
	protected UIParameter param2;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelBox box3;
	protected HtmlPanelGrid grid1;
	protected HtmlPanelBox box5;
	protected HtmlPanelGrid grid2;
	protected HtmlPanelBox box4;
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
	 * @action id=originDataTRGMultiRow
	 */
	public String doOriginDataTRGMultiRowUpdateAction() {
		try {
			getOriginDataTRGMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataTRGMultiRow()));
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
			if (originDataTRGMultiRowMediator != null) {
				originDataTRGMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataTRGMultiRow
	 */
	public DataObject getOriginDataTRGMultiRowParameters() {
		if (originDataTRGMultiRowParameters == null) {
			try {
				originDataTRGMultiRowParameters = getOriginDataTRGMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataTRGMultiRowParameters;
	}
	protected JDBCMediator getOriginDataTRGMultiRowMediator() {
		if (originDataTRGMultiRowMediator == null) {
			try {
				originDataTRGMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataTRGMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataTRGMultiRow_metadataFileName),
						originDataTRGMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataTRGMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataTRGMultiRowMediator;
	}
	/** 
	 * @action id=originDataTRGMultiRow
	 */
	public String doOriginDataTRGMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataTRGMultiRowParameters(), originDataTRGMultiRowArgNames,
					originDataTRGMultiRowArgValues, "originDataTRGMultiRow_params_cache");
			DataObject graph = getOriginDataTRGMultiRowMediator().getGraph(
					getOriginDataTRGMultiRowParameters());
			originDataTRGMultiRow = graph.getList(0);
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
			if (originDataTRGMultiRowMediator != null) {
				originDataTRGMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRGMultiRow.xml
	 * @methodEntry id=originDataTRGMultiRow/paramBean=originDataTRGMultiRow/action=originDataTRGMultiRow
	 * @action FILL
	 */
	public List getOriginDataTRGMultiRow() {
		if (originDataTRGMultiRow == null) {
			doOriginDataTRGMultiRowFetchAction();
		}
		return originDataTRGMultiRow;
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
	protected HtmlDataTableEx getOriginDataTRGMultiRow1() {
		if (originDataTRGMultiRow1 == null) {
			originDataTRGMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataTRGMultiRow1");
		}
		return originDataTRGMultiRow1;
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
	protected HtmlCommandExButton getDoOriginDataTRGMultiRowFetchAction1() {
		if (doOriginDataTRGMultiRowFetchAction1 == null) {
			doOriginDataTRGMultiRowFetchAction1 = (HtmlCommandExButton) findComponentInRoot("doOriginDataTRGMultiRowFetchAction1");
		}
		return doOriginDataTRGMultiRowFetchAction1;
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
	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
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
	protected HtmlJspPanel getJspPanelUnder1LC() {
		if (jspPanelUnder1LC == null) {
			jspPanelUnder1LC = (HtmlJspPanel) findComponentInRoot("jspPanelUnder1LC");
		}
		return jspPanelUnder1LC;
	}
	protected HtmlCommandExButton getDeleteBtn() {
		if (deleteBtn == null) {
			deleteBtn = (HtmlCommandExButton) findComponentInRoot("deleteBtn");
		}
		return deleteBtn;
	}
	protected HtmlCommandExButton getNewBtn() {
		if (newBtn == null) {
			newBtn = (HtmlCommandExButton) findComponentInRoot("newBtn");
		}
		return newBtn;
	}
	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlRequestLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlRequestLink) findComponentInRoot("link1");
		}
		return link1;
	}
	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		Object idTipoRiesgoExterno = getRequestParam().get("idTRG");
		getRequestScope().put("_param_idRiesgoExterno", idTipoRiesgoExterno);
		// return "edit";
		return "edit";
	}
	public String doNewBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// return "edit";
		// return "new";
		return "new";
	}
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
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
	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
	}
	public String doDeleteBtnAction() {
		/*
		 * Dado que selectedRows es una lista de mapas y cada mapa contiene solo
		 * una pareja [key,value] y que el key usado segun el tab parameters del
		 * selectionColumn es "IdClasificacionEmpresa", entonces:
		 */
		try {
			// recuperar los IDs seleccionados
			DataObject myRootDataObject = getRootDataObject(originDataTRGMultiRow);
			debug(originDataTRGMultiRow);
			for (Iterator iteratorSelectorColumn = selectedRows.iterator(); iteratorSelectorColumn
					.hasNext();) {
				Map currentMap = (Map) iteratorSelectorColumn.next();
				String currentIDstring = (String) currentMap.get("idRow");
				int currentIDint = Integer.parseInt(currentIDstring); 
				// encontrar el SDORecord correspondiente y borrarlo
				List indexesToDelete = new ArrayList();
				int i = 0;				
				for (Iterator iteratorSDOList = originDataTRGMultiRow.listIterator(); iteratorSDOList
						.hasNext();i++) {
					DataObject currentSDORecord = (DataObject) iteratorSDOList.next();
					if (currentSDORecord.getInt("IdClasificacionEmpresa") == currentIDint) {
						indexesToDelete.add(new Integer(i));
					}
				}
				for (Iterator iterator = indexesToDelete.iterator(); iterator.hasNext();) {
					Integer index = (Integer) iterator.next();
					DataObject dataObjectMarkedForDeletion = (DataObject) originDataTRGMultiRow.get(index.intValue());
					dataObjectMarkedForDeletion.delete();
				}				
			}
			debug(originDataTRGMultiRow);
			getOriginDataTRGMultiRowMediator().applyChanges(myRootDataObject);
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
			if (originDataTRGMultiRowMediator != null) {
				originDataTRGMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";		
	}

}