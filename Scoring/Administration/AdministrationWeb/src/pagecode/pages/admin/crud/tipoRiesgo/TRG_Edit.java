/**
 * 
 */
package pagecode.pages.admin.crud.tipoRiesgo;

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
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlInputHelperSpinner;

/**
 * @author Administrator
 *
 */
public class TRG_Edit extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataTRGSingleRowParameters;
	protected JDBCMediator originDataTRGSingleRowMediator;
	private static final String originDataTRGSingleRow_metadataFileName = "/WEB-INF/wdo/originDataTRGSingleRow.xml";
	protected static final String[] originDataTRGSingleRowArgNames = { "requestScope_param_idRiesgoExterno" };
	protected static final String[] originDataTRGSingleRowArgValues = { "#{requestScope._param_idRiesgoExterno}" };
	protected DataObject originDataTRGSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText nombre1;
	protected HtmlInputText desde1;
	protected HtmlInputText hasta1;
	protected HtmlCommandExButton originDataTRGSingleRow1;
	protected HtmlCommandExButton originDataTRGSingleRow2;
	protected HtmlMessages originDataTRGSingleRow3messages;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
	protected HtmlPanelBox box7;
	protected HtmlFormItem formItem4;
	protected HtmlJspPanel jspPanel3;
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
	}
	/** 
	 * @action id=originDataTRGSingleRow
	 */
	public String doOriginDataTRGSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataTRGSingleRow());
			getOriginDataTRGSingleRow().delete();
			getOriginDataTRGSingleRowMediator().applyChanges(root);
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
			if (originDataTRGSingleRowMediator != null) {
				originDataTRGSingleRowMediator.setConnectionWrapper(null);
			}
		}
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
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRGSingleRow.xml
	 * @methodEntry id=originDataTRGSingleRow/paramBean=originDataTRGSingleRow/action=originDataTRGSingleRow
	 * @action FILL
	 */
	public DataObject getOriginDataTRGSingleRow() {
		if (originDataTRGSingleRow == null) {
			doOriginDataTRGSingleRowFetchAction();
		}
		return originDataTRGSingleRow;
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
	protected HtmlCommandExButton getOriginDataTRGSingleRow1() {
		if (originDataTRGSingleRow1 == null) {
			originDataTRGSingleRow1 = (HtmlCommandExButton) findComponentInRoot("originDataTRGSingleRow1");
		}
		return originDataTRGSingleRow1;
	}
	protected HtmlCommandExButton getOriginDataTRGSingleRow2() {
		if (originDataTRGSingleRow2 == null) {
			originDataTRGSingleRow2 = (HtmlCommandExButton) findComponentInRoot("originDataTRGSingleRow2");
		}
		return originDataTRGSingleRow2;
	}
	protected HtmlMessages getOriginDataTRGSingleRow3messages() {
		if (originDataTRGSingleRow3messages == null) {
			originDataTRGSingleRow3messages = (HtmlMessages) findComponentInRoot("originDataTRGSingleRow3messages");
		}
		return originDataTRGSingleRow3messages;
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
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	protected HtmlJspPanel getJspPanel3() {
		if (jspPanel3 == null) {
			jspPanel3 = (HtmlJspPanel) findComponentInRoot("jspPanel3");
		}
		return jspPanel3;
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