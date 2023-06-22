/**
 * 
 */
package pagecode.pages.admin.crud.tipoRenta;

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
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;

/**
 * @author Administrator
 *
 */
public class TRT_Edit extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataTRTSingleRowParameters;
	protected JDBCMediator originDataTRTSingleRowMediator;
	private static final String originDataTRTSingleRow_metadataFileName = "/WEB-INF/wdo/originDataTRTSingleRow.xml";
	protected DataObject originDataTRTSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText nombre1;
	protected HtmlInputText descripcion1;
	protected HtmlCommandExButton originDataTRTSingleRow1;
	protected HtmlCommandExButton originDataTRTSingleRow2;
	protected HtmlMessages originDataTRTSingleRow3messages;
	protected static final String[] originDataTRTSingleRowArgNames = { "requestScope_param_idTipoRenta" };
	protected static final String[] originDataTRTSingleRowArgValues = { "#{requestScope._param_idTipoRenta}" };
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
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
	 * @action id=originDataTRTSingleRow
	 */
	public String doOriginDataTRTSingleRowUpdateAction() {
		try {
			getOriginDataTRTSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataTRTSingleRow()));
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
			if (originDataTRTSingleRowMediator != null) {
				originDataTRTSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @action id=originDataTRTSingleRow
	 */
	public String doOriginDataTRTSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataTRTSingleRow());
			getOriginDataTRTSingleRow().delete();
			getOriginDataTRTSingleRowMediator().applyChanges(root);
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
			if (originDataTRTSingleRowMediator != null) {
				originDataTRTSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataTRTSingleRow
	 */
	public DataObject getOriginDataTRTSingleRowParameters() {
		if (originDataTRTSingleRowParameters == null) {
			try {
				originDataTRTSingleRowParameters = getOriginDataTRTSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataTRTSingleRowParameters;
	}
	protected JDBCMediator getOriginDataTRTSingleRowMediator() {
		if (originDataTRTSingleRowMediator == null) {
			try {
				originDataTRTSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataTRTSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataTRTSingleRow_metadataFileName),
						originDataTRTSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataTRTSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataTRTSingleRowMediator;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataTRTSingleRowMediator().getGraph( getOriginDataTRTSingleRowParameters() );
	 *   originDataTRTSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataTRTSingleRow
	 */
	public String doOriginDataTRTSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataTRTSingleRowParameters(), originDataTRTSingleRowArgNames,
					originDataTRTSingleRowArgValues, "originDataTRTSingleRow_params_cache");
			DataObject graph = getOriginDataTRTSingleRowMediator().getEmptyGraph();
			originDataTRTSingleRow = graph.createDataObject(0);
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
			if (originDataTRTSingleRowMediator != null) {
				originDataTRTSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataTRTSingleRowMediator().getEmptyGraph();
	 *   originDataTRTSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataTRTSingleRow
	 */
	public String doOriginDataTRTSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataTRTSingleRowParameters(), originDataTRTSingleRowArgNames,
					originDataTRTSingleRowArgValues, "originDataTRTSingleRow_params_cache");
			DataObject graph = getOriginDataTRTSingleRowMediator().getGraph(
					getOriginDataTRTSingleRowParameters());
			originDataTRTSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataTRTSingleRowMediator != null) {
				originDataTRTSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRTSingleRow.xml
	 * @methodEntry id=originDataTRTSingleRow/paramBean=originDataTRTSingleRow/action=originDataTRTSingleRow
	 * @action FILL
	 */
	public DataObject getOriginDataTRTSingleRow() {
		if (originDataTRTSingleRow == null) {
			doOriginDataTRTSingleRowFetchAction();
		}
		return originDataTRTSingleRow;
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
	protected HtmlInputText getDescripcion1() {
		if (descripcion1 == null) {
			descripcion1 = (HtmlInputText) findComponentInRoot("descripcion1");
		}
		return descripcion1;
	}
	protected HtmlCommandExButton getOriginDataTRTSingleRow1() {
		if (originDataTRTSingleRow1 == null) {
			originDataTRTSingleRow1 = (HtmlCommandExButton) findComponentInRoot("originDataTRTSingleRow1");
		}
		return originDataTRTSingleRow1;
	}
	protected HtmlCommandExButton getOriginDataTRTSingleRow2() {
		if (originDataTRTSingleRow2 == null) {
			originDataTRTSingleRow2 = (HtmlCommandExButton) findComponentInRoot("originDataTRTSingleRow2");
		}
		return originDataTRTSingleRow2;
	}
	protected HtmlMessages getOriginDataTRTSingleRow3messages() {
		if (originDataTRTSingleRow3messages == null) {
			originDataTRTSingleRow3messages = (HtmlMessages) findComponentInRoot("originDataTRTSingleRow3messages");
		}
		return originDataTRTSingleRow3messages;
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
	protected HtmlPanelFormBox getFormBox1() {
		if (formBox1 == null) {
			formBox1 = (HtmlPanelFormBox) findComponentInRoot("formBox1");
		}
		return formBox1;
	}
	protected HtmlFormItem getFormItem1() {
		if (formItem1 == null) {
			formItem1 = (HtmlFormItem) findComponentInRoot("formItem1");
		}
		return formItem1;
	}
	protected HtmlFormItem getFormItem2() {
		if (formItem2 == null) {
			formItem2 = (HtmlFormItem) findComponentInRoot("formItem2");
		}
		return formItem2;
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
	public String doCancelBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "list";
		// return "cancel";
		return "cancel";
	}

}