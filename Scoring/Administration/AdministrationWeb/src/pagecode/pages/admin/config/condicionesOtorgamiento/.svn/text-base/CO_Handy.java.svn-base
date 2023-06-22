/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectManyCheckbox;

import pagecode.PageCodeBase;
import view.data.access.DataAccessException;
import view.data.access.DataProvider;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import commonj.sdo.DataObject;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlPanelRowCategory;

/**
 * @author Administrator
 *
 */
public class CO_Handy extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlDataTableEx condicionMasterRecordList1;
	protected HtmlOutputText strTipoRenta1text;
	protected HtmlOutputText strPerfilRiesgo1text;
	protected HtmlOutputText condiciones1text;
	protected UIColumnEx strTipoRenta1column;
	protected HtmlOutputText strTipoRenta1;
	protected UIColumnEx strPerfilRiesgo1column;
	protected HtmlOutputText strPerfilRiesgo1;
	protected UIColumnEx condiciones1column;
	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	protected HtmlSelectManyCheckbox checkbox1;
	protected UISelectItems selectItems1;
	protected DataProvider provider;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlCommandExButton refreshBtn;
	protected UIColumnEx columnEx1;
	protected HtmlOutputText text1;
	protected HtmlRequestLink link1;
	protected UIParameter param1;
	protected UIParameter param2;
	protected UIParameter param3;
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	protected ArrayList condicionMasterList;
	protected HtmlPanelBox box3;
	protected HtmlPanelBox box4;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel1;
	protected HtmlPanelBox box5;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlPanelGrid grid1;
	protected HtmlPanelGrid grid2;
	protected HtmlForm form100;
	protected HtmlJspPanel leftJspPanel;
	protected HtmlPanelLayout layout1L;
	protected HtmlPanelBox box2;
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlPanelBox box1;
	protected HtmlPanelRowCategory rowCategory1;
	protected HtmlOutputText text3;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText strTipoRiesgo1text;
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

	protected HtmlDataTableEx getCondicionMasterRecordList1() {
		if (condicionMasterRecordList1 == null) {
			condicionMasterRecordList1 = (HtmlDataTableEx) findComponentInRoot("condicionMasterRecordList1");
		}
		return condicionMasterRecordList1;
	}

	protected HtmlOutputText getStrTipoRenta1text() {
		if (strTipoRenta1text == null) {
			strTipoRenta1text = (HtmlOutputText) findComponentInRoot("strTipoRenta1text");
		}
		return strTipoRenta1text;
	}

	protected HtmlOutputText getStrPerfilRiesgo1text() {
		if (strPerfilRiesgo1text == null) {
			strPerfilRiesgo1text = (HtmlOutputText) findComponentInRoot("strPerfilRiesgo1text");
		}
		return strPerfilRiesgo1text;
	}

	protected HtmlOutputText getCondiciones1text() {
		if (condiciones1text == null) {
			condiciones1text = (HtmlOutputText) findComponentInRoot("condiciones1text");
		}
		return condiciones1text;
	}

	protected UIColumnEx getStrTipoRenta1column() {
		if (strTipoRenta1column == null) {
			strTipoRenta1column = (UIColumnEx) findComponentInRoot("strTipoRenta1column");
		}
		return strTipoRenta1column;
	}

	protected HtmlOutputText getStrTipoRenta1() {
		if (strTipoRenta1 == null) {
			strTipoRenta1 = (HtmlOutputText) findComponentInRoot("strTipoRenta1");
		}
		return strTipoRenta1;
	}

	protected UIColumnEx getStrPerfilRiesgo1column() {
		if (strPerfilRiesgo1column == null) {
			strPerfilRiesgo1column = (UIColumnEx) findComponentInRoot("strPerfilRiesgo1column");
		}
		return strPerfilRiesgo1column;
	}

	protected HtmlOutputText getStrPerfilRiesgo1() {
		if (strPerfilRiesgo1 == null) {
			strPerfilRiesgo1 = (HtmlOutputText) findComponentInRoot("strPerfilRiesgo1");
		}
		return strPerfilRiesgo1;
	}

	protected UIColumnEx getCondiciones1column() {
		if (condiciones1column == null) {
			condiciones1column = (UIColumnEx) findComponentInRoot("condiciones1column");
		}
		return condiciones1column;
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
	 * @action id=originDataCOMultiRow
	 */
	public String doOriginDataCOMultiRowUpdateAction() {
		try {
			getOriginDataCOMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataCOMultiRow()));
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
			if (originDataCOMultiRowMediator != null) {
				originDataCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}

	/** 
	 * @paramBean id=originDataCOMultiRow
	 */
	public DataObject getOriginDataCOMultiRowParameters() {
		if (originDataCOMultiRowParameters == null) {
			try {
				originDataCOMultiRowParameters = getOriginDataCOMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCOMultiRowParameters;
	}

	protected JDBCMediator getOriginDataCOMultiRowMediator() {
		if (originDataCOMultiRowMediator == null) {
			try {
				originDataCOMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCOMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCOMultiRow_metadataFileName),
						originDataCOMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCOMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCOMultiRowMediator;
	}

	/** 
	 * @action id=originDataCOMultiRow
	 */
	public String doOriginDataCOMultiRowFetchAction() {
		System.out.println("Entering doOriginDataCOMultiRowFetchAction()");
		try {
			resolveParams(getOriginDataCOMultiRowParameters(), originDataCOMultiRowArgNames,
					originDataCOMultiRowArgValues, "originDataCOMultiRow_params_cache");
			DataObject graph = getOriginDataCOMultiRowMediator().getGraph(
					getOriginDataCOMultiRowParameters());
			originDataCOMultiRow = graph.getList(0);
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
			if (originDataCOMultiRowMediator != null) {
				originDataCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		System.out.println("Exiting doOriginDataCOMultiRowFetchAction()");
		return "";
	}

	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCOMultiRow.xml
	 * @methodEntry id=originDataCOMultiRow/paramBean=originDataCOMultiRow/action=originDataCOMultiRow
	 * @action FILL
	 */
	public List getOriginDataCOMultiRow() {
		System.out.println("Entering getOriginDataCOMultiRow()");
		if (originDataCOMultiRow == null) {
			doOriginDataCOMultiRowFetchAction();
		}
		System.out.println("Exiting getOriginDataCOMultiRow()");
		return originDataCOMultiRow;
	}

	protected HtmlSelectManyCheckbox getCheckbox1() {
		if (checkbox1 == null) {
			checkbox1 = (HtmlSelectManyCheckbox) findComponentInRoot("checkbox1");
		}
		return checkbox1;
	}

	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
	}

	public DataProvider getProvider() {
		if (provider == null) {
			try {
				provider = new DataProvider();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return provider;
	}

	public void setProvider(DataProvider provider) {
		this.provider = provider;
	}

	protected HtmlCommandExButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = (HtmlCommandExButton) findComponentInRoot("cancelBtn");
		}
		return cancelBtn;
	}

	protected HtmlCommandExButton getRefreshBtn() {
		if (refreshBtn == null) {
			refreshBtn = (HtmlCommandExButton) findComponentInRoot("refreshBtn");
		}
		return refreshBtn;
	}

	protected UIColumnEx getColumnEx1() {
		if (columnEx1 == null) {
			columnEx1 = (UIColumnEx) findComponentInRoot("columnEx1");
		}
		return columnEx1;
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

	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}

	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}

	protected UIParameter getParam3() {
		if (param3 == null) {
			param3 = (UIParameter) findComponentInRoot("param3");
		}
		return param3;
	}

	public String doLink1Action1() {
		// Type Java code that runs when the component is clicked		
		Object idPerfil = getRequestParam().get("idPR");
		getRequestScope().put("_param_idPR", idPerfil);
		getSessionScope().put("_param_idPR", idPerfil);
		Object idRenta = getRequestParam().get("idTRT");
		getRequestScope().put("_param_idTRT", idRenta);
		getSessionScope().put("_param_idTRT", idRenta);
		Object idRiesgo = getRequestParam().get("idTRG");
		getRequestScope().put("_param_idTRG", idRiesgo);
		getSessionScope().put("_param_idTRG", idRiesgo);
		return "edit";
	}

	public ArrayList getCondicionMasterList() {
		System.out.println("Entering getCondicionMasterList");
		if (condicionMasterList == null) {
			doCondicionMasterListFetchAction();
		}
		System.out.println("Exiting getCondicionMasterList" );
		return condicionMasterList;
	}

	private void doCondicionMasterListFetchAction() {
		System.out.println("Entering doCondicionMasterListFetchAction");
		try {			
			condicionMasterList = (ArrayList) getProvider().selectAllCondicionesStructs();			
		} catch (DataAccessException e) {
			System.out.println("Failing doCondicionMasterListFetchAction()");
			e.printStackTrace();
		}
		System.out.println("Exiting doCondicionMasterListFetchAction");
	}

	public void setCondicionMasterList(ArrayList condicionMasterList) {
		System.out.println("Entering setCondicionMasterList");
		this.condicionMasterList = condicionMasterList;
		System.out.println("Exiting setCondicionMasterList");
	}

	protected HtmlPanelBox getBox3() {
		if (box3 == null) {
			box3 = (HtmlPanelBox) findComponentInRoot("box3");
		}
		return box3;
	}

	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
	}

	protected HtmlPanelBox getBox5() {
		if (box5 == null) {
			box5 = (HtmlPanelBox) findComponentInRoot("box5");
		}
		return box5;
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

	protected HtmlPanelBox getBox2() {
		if (box2 == null) {
			box2 = (HtmlPanelBox) findComponentInRoot("box2");
		}
		return box2;
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

	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}

	protected HtmlPanelRowCategory getRowCategory1() {
		if (rowCategory1 == null) {
			rowCategory1 = (HtmlPanelRowCategory) findComponentInRoot("rowCategory1");
		}
		return rowCategory1;
	}

	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}

	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}

	protected HtmlOutputText getStrTipoRiesgo1text() {
		if (strTipoRiesgo1text == null) {
			strTipoRiesgo1text = (HtmlOutputText) findComponentInRoot("strTipoRiesgo1text");
		}
		return strTipoRiesgo1text;
	}

}