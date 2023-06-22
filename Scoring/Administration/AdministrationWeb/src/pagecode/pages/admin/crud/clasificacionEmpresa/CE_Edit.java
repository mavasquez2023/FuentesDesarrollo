/**
 * 
 */
package pagecode.pages.admin.crud.clasificacionEmpresa;

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

/**
 * @author Administrator
 *
 */
public class CE_Edit extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCESingleRowParameters;
	protected JDBCMediator originDataCESingleRowMediator;
	private static final String originDataCESingleRow_metadataFileName = "/WEB-INF/wdo/originDataCESingleRow.xml";
	protected DataObject originDataCESingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText nombre1;
	protected HtmlInputText descripcion1;
	protected HtmlCommandExButton updateBtn;
	protected HtmlCommandExButton deleteBtn;
	protected HtmlMessages originDataCESingleRow3messages;
	protected static final String[] originDataCESingleRowArgNames = { "requestScope_param_idClasificacionEmpresa" };
	protected static final String[] originDataCESingleRowArgValues = { "#{requestScope._param_idClasificacionEmpresa}" };
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
	 * @action id=originDataCESingleRow
	 */
	public String doOriginDataCESingleRowUpdateAction() {
		try {
			getOriginDataCESingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataCESingleRow()));
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
			if (originDataCESingleRowMediator != null) {
				originDataCESingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @action id=originDataCESingleRow
	 */
	public String doOriginDataCESingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataCESingleRow());
			getOriginDataCESingleRow().delete();
			getOriginDataCESingleRowMediator().applyChanges(root);
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
			if (originDataCESingleRowMediator != null) {
				originDataCESingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @paramBean id=originDataCESingleRow
	 */
	public DataObject getOriginDataCESingleRowParameters() {
		if (originDataCESingleRowParameters == null) {
			try {
				originDataCESingleRowParameters = getOriginDataCESingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCESingleRowParameters;
	}
	protected JDBCMediator getOriginDataCESingleRowMediator() {
		if (originDataCESingleRowMediator == null) {
			try {
				originDataCESingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCESingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCESingleRow_metadataFileName),
						originDataCESingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCESingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCESingleRowMediator;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataCESingleRowMediator().getGraph( getOriginDataCESingleRowParameters() );
	 *   originDataCESingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataCESingleRow
	 */
	public String doOriginDataCESingleRowCreateAction() {
		try {
			resolveParams(getOriginDataCESingleRowParameters(), originDataCESingleRowArgNames,
					originDataCESingleRowArgValues, "originDataCESingleRow_params_cache");
			DataObject graph = getOriginDataCESingleRowMediator().getEmptyGraph();
			originDataCESingleRow = graph.createDataObject(0);
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
			if (originDataCESingleRowMediator != null) {
				originDataCESingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataCESingleRowMediator().getEmptyGraph();
	 *   originDataCESingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataCESingleRow
	 */
	public String doOriginDataCESingleRowFetchAction() {
		try {
			resolveParams(getOriginDataCESingleRowParameters(), originDataCESingleRowArgNames,
					originDataCESingleRowArgValues, "originDataCESingleRow_params_cache");
			DataObject graph = getOriginDataCESingleRowMediator().getGraph(
					getOriginDataCESingleRowParameters());
			originDataCESingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataCESingleRowMediator != null) {
				originDataCESingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCESingleRow.xml
	 * @methodEntry id=originDataCESingleRow/paramBean=originDataCESingleRow/action=originDataCESingleRow
	 * @action FILL
	 */
	public DataObject getOriginDataCESingleRow() {
		if (originDataCESingleRow == null) {
			doOriginDataCESingleRowFetchAction();
		}
		return originDataCESingleRow;
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
	protected HtmlMessages getOriginDataCESingleRow3messages() {
		if (originDataCESingleRow3messages == null) {
			originDataCESingleRow3messages = (HtmlMessages) findComponentInRoot("originDataCESingleRow3messages");
		}
		return originDataCESingleRow3messages;
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
		// return "list";
		return "list";
	}

}