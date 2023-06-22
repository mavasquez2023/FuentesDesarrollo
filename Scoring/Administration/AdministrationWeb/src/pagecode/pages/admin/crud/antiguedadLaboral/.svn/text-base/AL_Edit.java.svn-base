/**
 * 
 */
package pagecode.pages.admin.crud.antiguedadLaboral;

import pagecode.PageCodeBase;
import pagecode.utils.FacesUtils;
import commonj.sdo.DataObject;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import com.ibm.ws.sdo.mediator.jdbc.queryengine.QueryEngineException;

import java.sql.Connection;
import java.sql.SQLException;

import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import java.lang.Object;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlInputHelperAssist;
import com.ibm.faces.component.html.HtmlInputHelperSpinner;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 *
 */
public class AL_Edit extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataALSingleRowParameters;
	protected JDBCMediator originDataALSingleRowMediator;
	private static final String originDataALSingleRow_metadataFileName = "/WEB-INF/wdo/originDataALSingleRow.xml";
	protected static final String[] originDataALSingleRowArgNames = { "requestScope_param_idAntiguedad" };
	protected static final String[] originDataALSingleRowArgValues = { "#{requestScope._param_idAntiguedad}" };
	protected DataObject originDataALSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem2;
	protected HtmlInputText nombre1;
	protected HtmlInputText desde1;
	protected HtmlInputText hasta1;
	protected HtmlForm form1;
	protected HtmlJspPanel jspPanel4;
	protected HtmlMessages originDataALSingleRow2messages;
	protected HtmlJspPanel jspPanel3;
	protected HtmlFormItem formItem3;
	protected HtmlInputHelperAssist assist1;
	protected HtmlInputHelperSpinner spinner1;
	protected HtmlFormItem formItem4;
	protected HtmlCommandExButton updateBtn;
	protected HtmlCommandExButton deleteBtn;
	protected HtmlCommandExButton cancelBtn;
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
	protected HtmlOutputText text1;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlJspPanel jspPanel1;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlPanelBox box4;
	protected HtmlPanelBox box1;
	protected HtmlInputHelperSpinner spinner2;
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
			if(e.getOriginalException() instanceof QueryEngineException){
				String originalMessage = e.getMessage();
				String message = originalMessage.substring(originalMessage.indexOf("REFERENCE constraint"));
				message = message.substring(20);
				FacesUtils.addErrorMessage("Información del constraint : " + message);				
			}
			logException(e);
			return "";
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
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
				FacesUtils.addErrorMessage(e.getMessage());
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
				FacesUtils.addErrorMessage(e.getMessage());
				logException(e);
			}
		} else {
			originDataALSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataALSingleRowMediator;
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
				logException(e1);
			}
			if (originDataALSingleRowMediator != null) {
				originDataALSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataALSingleRow.xml
	 * @methodEntry id=originDataALSingleRow/paramBean=originDataALSingleRow/action=originDataALSingleRow
	 * @action FILL
	 */
	public DataObject getOriginDataALSingleRow() {
		if (originDataALSingleRow == null) {
			doOriginDataALSingleRowFetchAction();
		}
		return originDataALSingleRow;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	public String doButton1Action() {
		// This is java code that runs when this action method is invoked
		return "cancel";
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
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlJspPanel getJspPanel4() {
		if (jspPanel4 == null) {
			jspPanel4 = (HtmlJspPanel) findComponentInRoot("jspPanel4");
		}
		return jspPanel4;
	}
	protected HtmlMessages getOriginDataALSingleRow2messages() {
		if (originDataALSingleRow2messages == null) {
			originDataALSingleRow2messages = (HtmlMessages) findComponentInRoot("originDataALSingleRow2messages");
		}
		return originDataALSingleRow2messages;
	}
	protected HtmlJspPanel getJspPanel3() {
		if (jspPanel3 == null) {
			jspPanel3 = (HtmlJspPanel) findComponentInRoot("jspPanel3");
		}
		return jspPanel3;
	}
	protected HtmlFormItem getFormItem3() {
		if (formItem3 == null) {
			formItem3 = (HtmlFormItem) findComponentInRoot("formItem3");
		}
		return formItem3;
	}
	protected HtmlInputHelperAssist getAssist1() {
		if (assist1 == null) {
			assist1 = (HtmlInputHelperAssist) findComponentInRoot("assist1");
		}
		return assist1;
	}
	protected HtmlInputHelperSpinner getSpinner1() {
		if (spinner1 == null) {
			spinner1 = (HtmlInputHelperSpinner) findComponentInRoot("spinner1");
		}
		return spinner1;
	}
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	protected HtmlCommandExButton getUpdateBtn() {
		if (updateBtn == null) {
			updateBtn = (HtmlCommandExButton) findComponentInRoot("updateBtn");
		}
		return updateBtn;
	}
	protected HtmlCommandExButton getDeleteBtn() {
		if (deleteBtn == null) {
			deleteBtn = (HtmlCommandExButton) findComponentInRoot("deleteBtn");
		}
		return deleteBtn;
	}
	protected HtmlCommandExButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = (HtmlCommandExButton) findComponentInRoot("cancelBtn");
		}
		return cancelBtn;
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
	}
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	protected HtmlInputHelperSpinner getSpinner2() {
		if (spinner2 == null) {
			spinner2 = (HtmlInputHelperSpinner) findComponentInRoot("spinner2");
		}
		return spinner2;
	}

}