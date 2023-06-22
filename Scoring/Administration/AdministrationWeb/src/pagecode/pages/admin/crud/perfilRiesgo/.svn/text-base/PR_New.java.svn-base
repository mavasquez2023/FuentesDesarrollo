/**
 * 
 */
package pagecode.pages.admin.crud.perfilRiesgo;

import pagecode.PageCodeBase;
import pagecode.utils.FacesUtils;
import commonj.sdo.DataObject;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import java.sql.Connection;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;
import com.ibm.faces.component.html.HtmlInputHelperSpinner;

/**
 * @author Administrator
 *
 */
public class PR_New extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataPRSingleRowParameters;
	protected JDBCMediator originDataPRSingleRowMediator;
	private static final String originDataPRSingleRow_metadataFileName = "/WEB-INF/wdo/originDataPRSingleRow.xml";
	protected DataObject originDataPRSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText nombre1;
	protected HtmlInputText desde1;
	protected HtmlInputText hasta1;
	protected HtmlMessages originDataPRSingleRow2messages;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelBox box7;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlFormItem formItem4;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlCommandExButton okBtn;
	protected HtmlInputHelperSpinner spinner1;
	protected HtmlInputHelperSpinner spinner2;
	protected HtmlCommandExButton resetBtn;
	protected static final String[] originDataPRSingleRowArgNames = { "requestScope_param_idPerfilRiesgo" };
	protected static final String[] originDataPRSingleRowArgValues = { "#{param._idPerfilRiesgo}" };
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
	 * @action id=originDataPRSingleRow
	 */
	public String doOriginDataPRSingleRowUpdateAction() {
		try {
			getOriginDataPRSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataPRSingleRow()));
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
			if (originDataPRSingleRowMediator != null) {
				originDataPRSingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @action id=originDataPRSingleRow
	 */
	public String doOriginDataPRSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataPRSingleRow());
			getOriginDataPRSingleRow().delete();
			getOriginDataPRSingleRowMediator().applyChanges(root);
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
			if (originDataPRSingleRowMediator != null) {
				originDataPRSingleRowMediator.setConnectionWrapper(null);
			}
		}		
	}
	/** 
	 * @paramBean id=originDataPRSingleRow
	 */
	public DataObject getOriginDataPRSingleRowParameters() {
		if (originDataPRSingleRowParameters == null) {
			try {
				originDataPRSingleRowParameters = getOriginDataPRSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataPRSingleRowParameters;
	}
	protected JDBCMediator getOriginDataPRSingleRowMediator() {
		if (originDataPRSingleRowMediator == null) {
			try {
				originDataPRSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataPRSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataPRSingleRow_metadataFileName),
						originDataPRSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataPRSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataPRSingleRowMediator;
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataPRSingleRow.xml
	 * @methodEntry id=originDataPRSingleRow/paramBean=originDataPRSingleRow/action=originDataPRSingleRow
	 * @action CREATE
	 */
	public DataObject getOriginDataPRSingleRow() {
		if (originDataPRSingleRow == null) {
			doOriginDataPRSingleRowCreateAction();
		}
		return originDataPRSingleRow;
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
	protected HtmlMessages getOriginDataPRSingleRow2messages() {
		if (originDataPRSingleRow2messages == null) {
			originDataPRSingleRow2messages = (HtmlMessages) findComponentInRoot("originDataPRSingleRow2messages");
		}
		return originDataPRSingleRow2messages;
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
	protected HtmlCommandExButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = (HtmlCommandExButton) findComponentInRoot("cancelBtn");
		}
		return cancelBtn;
	}
	protected HtmlCommandExButton getOkBtn() {
		if (okBtn == null) {
			okBtn = (HtmlCommandExButton) findComponentInRoot("okBtn");
		}
		return okBtn;
	}
	protected HtmlInputHelperSpinner getSpinner1() {
		if (spinner1 == null) {
			spinner1 = (HtmlInputHelperSpinner) findComponentInRoot("spinner1");
		}
		return spinner1;
	}
	protected HtmlInputHelperSpinner getSpinner2() {
		if (spinner2 == null) {
			spinner2 = (HtmlInputHelperSpinner) findComponentInRoot("spinner2");
		}
		return spinner2;
	}
	protected HtmlCommandExButton getResetBtn() {
		if (resetBtn == null) {
			resetBtn = (HtmlCommandExButton) findComponentInRoot("resetBtn");
		}
		return resetBtn;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataPRSingleRowMediator().getGraph( getOriginDataPRSingleRowParameters() );
	 *   originDataPRSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataPRSingleRow
	 */
	public String doOriginDataPRSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataPRSingleRowParameters(), originDataPRSingleRowArgNames,
					originDataPRSingleRowArgValues, "originDataPRSingleRow_params_cache");
			DataObject graph = getOriginDataPRSingleRowMediator().getEmptyGraph();
			originDataPRSingleRow = graph.createDataObject(0);
			autoGenerateKey(originDataPRSingleRow, getOriginDataPRSingleRowMediator(),
					originDataPRSingleRow_metadataFileName);
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
			if (originDataPRSingleRowMediator != null) {
				originDataPRSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataPRSingleRowMediator().getEmptyGraph();
	 *   originDataPRSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataPRSingleRow
	 */
	public String doOriginDataPRSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataPRSingleRowParameters(), originDataPRSingleRowArgNames,
					originDataPRSingleRowArgValues, "originDataPRSingleRow_params_cache");
			DataObject graph = getOriginDataPRSingleRowMediator().getGraph(
					getOriginDataPRSingleRowParameters());
			originDataPRSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataPRSingleRowMediator != null) {
				originDataPRSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	public String doCancelBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// return "list";
		// return "cancel";
		return "cancel";
	}

}