/**
 * 
 */
package pagecode.pages.admin.crud.antiguedadLaboral;

import java.sql.Connection;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;

import pagecode.PageCodeBase;
import pagecode.utils.FacesUtils;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlFormItem;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlInputHelperSpinner;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlPanelLayout;
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
public class AL_New extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataALSingleRowParameters;
	protected JDBCMediator originDataALSingleRowMediator;
	private static final String originDataALSingleRow_metadataFileName = "/WEB-INF/wdo/originDataALSingleRow.xml";
	protected DataObject originDataALSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText nombre1;
	protected HtmlInputText desde1;
	protected HtmlInputText hasta1;
	protected HtmlCommandExButton okBtn;
	protected HtmlMessages originDataALSingleRow2messages;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlJspPanel jspPanel4;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlFormItem formItem4;
	protected HtmlCommandExButton resetBtn;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlInputHelperSpinner spinner2;
	protected HtmlInputHelperSpinner spinner3;
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
	protected HtmlPanelBox box2;
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlPanelLayout layout1B;
	protected HtmlJspPanel jspPanelUnder1BC;
	protected HtmlOutputText text1;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlJspPanel jspPanel1;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlPanelBox box4;
	protected HtmlPanelBox box1;
	protected static final String[] originDataALSingleRowArgNames = { "requestScope_param_idAntiguedad" };
	protected static final String[] originDataALSingleRowArgValues = { "#{param._idAntiguedad}" };
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
	 * @action id=originDataALSingleRow
	 */
	public String doOriginDataALSingleRowUpdateAction() {
		try {
			getOriginDataALSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataALSingleRow()));
			return "list";
		} catch (MediatorException e) {
			FacesUtils.addErrorMessage("No se pudo efectuar la operación.");
			FacesUtils.addErrorMessage(e.getMessage());
			logException(e);
			return "";
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
			if (originDataALSingleRowMediator != null) {
				originDataALSingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @action id=originDataALSingleRow
	 */
	public String doOriginDataALSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataALSingleRow());
			getOriginDataALSingleRow().delete();
			getOriginDataALSingleRowMediator().applyChanges(root);
			return "list";
		} catch (MediatorException e) {
			FacesUtils.addErrorMessage("No se pudo efectuar la operación.");
			FacesUtils.addErrorMessage(e.getMessage());
			logException(e);
			return "";
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
			if (originDataALSingleRowMediator != null) {
				originDataALSingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @paramBean id=originDataALSingleRow
	 */
	public DataObject getOriginDataALSingleRowParameters() {
		if (originDataALSingleRowParameters == null) {
			try {
				originDataALSingleRowParameters = getOriginDataALSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataALSingleRowParameters;
	}
	protected JDBCMediator getOriginDataALSingleRowMediator() {
		if (originDataALSingleRowMediator == null) {
			try {
				originDataALSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataALSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataALSingleRow_metadataFileName),
						originDataALSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataALSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataALSingleRowMediator;
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataALSingleRow.xml
	 * @methodEntry id=originDataALSingleRow/paramBean=originDataALSingleRow/action=originDataALSingleRow
	 * @action CREATE
	 */
	public DataObject getOriginDataALSingleRow() {
		if (originDataALSingleRow == null) {
			doOriginDataALSingleRowCreateAction();
		}
		return originDataALSingleRow;
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
	protected HtmlInputText getNombre1() {
		if (nombre1 == null) {
			nombre1 = (HtmlInputText) findComponentInRoot("nombre1");
		}
		return nombre1;
	}
	protected HtmlInputText getDesde1() {
		if (desde1 == null) {
			desde1 = (HtmlInputText) findComponentInRoot("desde1");
		}
		return desde1;
	}
	protected HtmlInputText getHasta1() {
		if (hasta1 == null) {
			hasta1 = (HtmlInputText) findComponentInRoot("hasta1");
		}
		return hasta1;
	}
	protected HtmlCommandExButton getOkBtn() {
		if (okBtn == null) {
			okBtn = (HtmlCommandExButton) findComponentInRoot("okBtn");
		}
		return okBtn;
	}
	protected HtmlMessages getOriginDataALSingleRow2messages() {
		if (originDataALSingleRow2messages == null) {
			originDataALSingleRow2messages = (HtmlMessages) findComponentInRoot("originDataALSingleRow2messages");
		}
		return originDataALSingleRow2messages;
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
	protected HtmlJspPanel getJspPanel4() {
		if (jspPanel4 == null) {
			jspPanel4 = (HtmlJspPanel) findComponentInRoot("jspPanel4");
		}
		return jspPanel4;
	}
	protected HtmlPanelFormBox getFormBox1() {
		if (formBox1 == null) {
			formBox1 = (HtmlPanelFormBox) findComponentInRoot("formBox1");
		}
		return formBox1;
	}
	protected HtmlFormItem getFormItem2() {
		if (formItem2 == null) {
			formItem2 = (HtmlFormItem) findComponentInRoot("formItem2");
		}
		return formItem2;
	}
	protected HtmlFormItem getFormItem3() {
		if (formItem3 == null) {
			formItem3 = (HtmlFormItem) findComponentInRoot("formItem3");
		}
		return formItem3;
	}
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	protected HtmlCommandExButton getResetBtn() {
		if (resetBtn == null) {
			resetBtn = (HtmlCommandExButton) findComponentInRoot("resetBtn");
		}
		return resetBtn;
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
	public String doButton2Action() {
		// This is java code that runs when this action method is invoked
	
		// return "cancel";
		return "cancel";
	}
	protected HtmlInputHelperSpinner getSpinner2() {
		if (spinner2 == null) {
			spinner2 = (HtmlInputHelperSpinner) findComponentInRoot("spinner2");
		}
		return spinner2;
	}
	protected HtmlInputHelperSpinner getSpinner3() {
		if (spinner3 == null) {
			spinner3 = (HtmlInputHelperSpinner) findComponentInRoot("spinner3");
		}
		return spinner3;
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
	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
	}protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataALSingleRowMediator().getGraph( getOriginDataALSingleRowParameters() );
	 *   originDataALSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataALSingleRow
	 */
	public String doOriginDataALSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataALSingleRowParameters(), originDataALSingleRowArgNames,
					originDataALSingleRowArgValues, "originDataALSingleRow_params_cache");
			DataObject graph = getOriginDataALSingleRowMediator().getEmptyGraph();
			originDataALSingleRow = graph.createDataObject(0);
			autoGenerateKey(originDataALSingleRow, getOriginDataALSingleRowMediator(),
					originDataALSingleRow_metadataFileName);
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
			if (originDataALSingleRowMediator != null) {
				originDataALSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataALSingleRowMediator().getEmptyGraph();
	 *   originDataALSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataALSingleRow
	 */
	public String doOriginDataALSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataALSingleRowParameters(), originDataALSingleRowArgNames,
					originDataALSingleRowArgValues, "originDataALSingleRow_params_cache");
			DataObject graph = getOriginDataALSingleRowMediator().getGraph(
					getOriginDataALSingleRowParameters());
			originDataALSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataALSingleRowMediator != null) {
				originDataALSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
}