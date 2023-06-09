/**
 * 
 */
package pagecode.pages.admin.crud.tipoRiesgo;

import pagecode.PageCodeBase;
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
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlFormItem;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlInputHelperSpinner;

/**
 * @author Administrator
 *
 */
public class TRG_New extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataTRGSingleRowParameters;
	protected JDBCMediator originDataTRGSingleRowMediator;
	private static final String originDataTRGSingleRow_metadataFileName = "/WEB-INF/wdo/originDataTRGSingleRow.xml";
	protected DataObject originDataTRGSingleRow;
	protected static final String[] originDataTRGSingleRowArgNames = { "requestScope_param_idRiesgoExterno" };
	protected static final String[] originDataTRGSingleRowArgValues = { "#{param._idRiesgoExterno}" };
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlInputText nombre1;
	protected HtmlInputText desde1;
	protected HtmlInputText hasta1;
	protected HtmlCommandExButton okBtn;
	protected HtmlJspPanel jspPanel3;
	protected HtmlMessages originDataTRGSingleRow3messages;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem4;
	protected HtmlFormItem formItem1;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlCommandExButton resetBtn;
	protected HtmlInputHelperSpinner spinner1;
	protected HtmlInputHelperSpinner spinner2;
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
	 * @action id=originDataTRGSingleRow
	 */
	public String doOriginDataTRGSingleRowUpdateAction() {
		try {
			getOriginDataTRGSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataTRGSingleRow()));
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @action id=originDataTRGSingleRow
	 */
	public String doOriginDataTRGSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataTRGSingleRow());
			getOriginDataTRGSingleRow().delete();
			getOriginDataTRGSingleRowMediator().applyChanges(root);
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataTRGSingleRow
	 */
	public DataObject getOriginDataTRGSingleRowParameters() {
		if (originDataTRGSingleRowParameters == null) {
			try {
				originDataTRGSingleRowParameters = getOriginDataTRGSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataTRGSingleRowParameters;
	}
	protected JDBCMediator getOriginDataTRGSingleRowMediator() {
		if (originDataTRGSingleRowMediator == null) {
			try {
				originDataTRGSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataTRGSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataTRGSingleRow_metadataFileName),
						originDataTRGSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataTRGSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataTRGSingleRowMediator;
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRGSingleRow.xml
	 * @methodEntry id=originDataTRGSingleRow/paramBean=originDataTRGSingleRow/action=originDataTRGSingleRow
	 * @action CREATE
	 */
	public DataObject getOriginDataTRGSingleRow() {
		if (originDataTRGSingleRow == null) {
			doOriginDataTRGSingleRowCreateAction();
		}
		return originDataTRGSingleRow;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataTRGSingleRowMediator().getGraph( getOriginDataTRGSingleRowParameters() );
	 *   originDataTRGSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataTRGSingleRow
	 */
	public String doOriginDataTRGSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataTRGSingleRowParameters(), originDataTRGSingleRowArgNames,
					originDataTRGSingleRowArgValues, "originDataTRGSingleRow_params_cache");
			DataObject graph = getOriginDataTRGSingleRowMediator().getEmptyGraph();
			originDataTRGSingleRow = graph.createDataObject(0);
			autoGenerateKey(originDataTRGSingleRow, getOriginDataTRGSingleRowMediator(),
					originDataTRGSingleRow_metadataFileName);
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataTRGSingleRowMediator().getEmptyGraph();
	 *   originDataTRGSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataTRGSingleRow
	 */
	public String doOriginDataTRGSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataTRGSingleRowParameters(), originDataTRGSingleRowArgNames,
					originDataTRGSingleRowArgValues, "originDataTRGSingleRow_params_cache");
			DataObject graph = getOriginDataTRGSingleRowMediator().getGraph(
					getOriginDataTRGSingleRowParameters());
			originDataTRGSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
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
	protected HtmlJspPanel getJspPanel3() {
		if (jspPanel3 == null) {
			jspPanel3 = (HtmlJspPanel) findComponentInRoot("jspPanel3");
		}
		return jspPanel3;
	}
	protected HtmlMessages getOriginDataTRGSingleRow3messages() {
		if (originDataTRGSingleRow3messages == null) {
			originDataTRGSingleRow3messages = (HtmlMessages) findComponentInRoot("originDataTRGSingleRow3messages");
		}
		return originDataTRGSingleRow3messages;
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
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	protected HtmlFormItem getFormItem1() {
		if (formItem1 == null) {
			formItem1 = (HtmlFormItem) findComponentInRoot("formItem1");
		}
		return formItem1;
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
	protected HtmlCommandExButton getResetBtn() {
		if (resetBtn == null) {
			resetBtn = (HtmlCommandExButton) findComponentInRoot("resetBtn");
		}
		return resetBtn;
	}
	public String doCancelBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// return "list";
		// return "cancel";
		return "cancel";
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

}