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
import java.util.List;
import java.util.Map;

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
public class CE_New extends PageCodeBase {

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
	protected HtmlCommandExButton okBtn;
	protected HtmlMessages originDataCESingleRow2messages;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlCommandExButton resetBtn;
	protected static final String[] originDataCESingleRowArgNames = { "requestScope_param_idClasificacionEmpresa" };
	protected static final String[] originDataCESingleRowArgValues = { "#{param._idClasificacionEmpresa}" };
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
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCESingleRow.xml
	 * @methodEntry id=originDataCESingleRow/paramBean=originDataCESingleRow/action=originDataCESingleRow
	 * @action CREATE
	 */
	public DataObject getOriginDataCESingleRow() {
		if (originDataCESingleRow == null) {
			doOriginDataCESingleRowCreateAction();
		}
		return originDataCESingleRow;
	}
	
	
	private Integer getNextId(){		
		Integer nextId = null;
		try {
			DataObject graph = getOriginDataCESingleRowMediator().getGraph(getOriginDataCESingleRowParameters());
			List lista = graph.getList(0);
//			getRootDataObject(cseClasificacionEmpresaNew);			
			int id=0;
			Map rec;
			Integer i;
			for (int x=0;x<lista.size();x++) {
				rec=(Map)lista.get(x);
				i=(Integer)rec.get("IdClasificacionEmpresa");
				if(i.intValue() > id) {
					id=i.intValue();
				}
			}
			nextId = new Integer(id + 1);			
		} catch (Throwable e) {
			logException(e);
			FacesUtils.addErrorMessage(e.getMessage());
		} finally {
			try {
				if (SDOConnectionWrapper != null) {
					SDOConnectionWrapper.getConnection().close();
					SDOConnectionWrapper = null;
				}
			} catch (Throwable e1) {
				logException(e1);
				FacesUtils.addErrorMessage(e1.getMessage());
			}
			
		}
		return nextId;
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
	protected HtmlCommandExButton getOkBtn() {
		if (okBtn == null) {
			okBtn = (HtmlCommandExButton) findComponentInRoot("okBtn");
		}
		return okBtn;
	}
	protected HtmlMessages getOriginDataCESingleRow2messages() {
		if (originDataCESingleRow2messages == null) {
			originDataCESingleRow2messages = (HtmlMessages) findComponentInRoot("originDataCESingleRow2messages");
		}
		return originDataCESingleRow2messages;
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
		// return "cancel";
		return "cancel";
	}
	public String doOkBtnAction() {
		return doOriginDataCESingleRowUpdateAction();		
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
			autoGenerateKey(originDataCESingleRow, getOriginDataCESingleRowMediator(),
					originDataCESingleRow_metadataFileName);
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

}