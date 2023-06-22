/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import pagecode.PageCodeBase;
import java.util.List;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import commonj.sdo.DataObject;
import java.sql.Connection;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneMenu;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.UISelectItems;
import java.lang.String;
import com.ibm.faces.component.html.HtmlPanelLayout;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 *
 */
public class CO_wizSecondPage extends PageCodeBase {

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
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText text1;
	protected HtmlForm form1;
	protected HtmlSelectOneMenu menu1;
	protected HtmlCommandExButton button1;
	protected UISelectItems selectItems1;
	protected Integer _TRT_Value;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlForm form100;
	protected HtmlJspPanel leftJspPanel;
	protected HtmlPanelLayout layout1L;
	protected HtmlPanelBox box2;
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
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
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
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlSelectOneMenu getMenu1() {
		if (menu1 == null) {
			menu1 = (HtmlSelectOneMenu) findComponentInRoot("menu1");
		}
		return menu1;
	}
	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}
	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
	}
	public Integer get_TRT_Value() {
		if (_TRT_Value == null) {
			_TRT_Value = new Integer(0);
		}
		return _TRT_Value;
	}
	public void set_TRT_Value(Integer _TRT_Value) {
		this._TRT_Value = _TRT_Value;
	}
	public String doButton1Action() {
		// This is java code that runs when this action method is invoked
		System.out.println("CO_wizSecondPage.doButton1Action()");
		System.out.println(_TRT_Value);
		getSessionScope().put("_param_idTRT", _TRT_Value);
		// TODO: Return an outcome that corresponds to a navigation rule
		return "next";
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
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
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
	protected HtmlPanelBox getBox7() {
		if (box7 == null) {
			box7 = (HtmlPanelBox) findComponentInRoot("box7");
		}
		return box7;
	}
	protected HtmlCommandExButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = (HtmlCommandExButton) findComponentInRoot("cancelBtn");
		}
		return cancelBtn;
	}

}