/**
 * 
 */
package pagecode.pages.admin.config.sueldosPrestamo;

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
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.UISelectItems;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;

/**
 * @author Administrator
 *
 */
public class SP_wizThirdPage extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataNSMultiRowParameters;
	protected JDBCMediator originDataNSMultiRowMediator;
	private static final String originDataNSMultiRow_metadataFileName = "/WEB-INF/wdo/originDataNSMultiRow.xml";
	protected static final String[] originDataNSMultiRowArgNames = {};
	protected static final String[] originDataNSMultiRowArgValues = {};
	private static final int originDataNSMultiRowTargetPageSize = -1;
	protected List originDataNSMultiRow;
	protected DataObject originDataNSPSingleRowParameters;
	protected JDBCMediator originDataNSPSingleRowMediator;
	private static final String originDataNSPSingleRow_metadataFileName = "/WEB-INF/wdo/originDataNSPSingleRow.xml";
	protected static final String[] originDataNSPSingleRowArgNames = { "sessionScope_param_idCE",
				"sessionScope_param_idAL" };
	protected static final String[] originDataNSPSingleRowArgValues = {
				"#{sessionScope._param_idCE}", "#{sessionScope._param_idAL}" };
	protected DataObject originDataNSPSingleRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlInputText idClasificacionEmpresa1;
	protected HtmlInputText idAntiguedadLaboral1;
	protected HtmlSelectOneMenu idNroSueldos1;
	protected HtmlForm form1;
	protected HtmlCommandExButton originDataNSPSingleRow1;
	protected HtmlMessages originDataNSPSingleRow2messages;
	protected UISelectItems selectItems1;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
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
	 * @action id=originDataNSMultiRow
	 */
	public String doOriginDataNSMultiRowUpdateAction() {
		try {
			getOriginDataNSMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataNSMultiRow()));
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
			if (originDataNSMultiRowMediator != null) {
				originDataNSMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataNSMultiRow
	 */
	public DataObject getOriginDataNSMultiRowParameters() {
		if (originDataNSMultiRowParameters == null) {
			try {
				originDataNSMultiRowParameters = getOriginDataNSMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataNSMultiRowParameters;
	}
	protected JDBCMediator getOriginDataNSMultiRowMediator() {
		if (originDataNSMultiRowMediator == null) {
			try {
				originDataNSMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataNSMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataNSMultiRow_metadataFileName),
						originDataNSMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataNSMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataNSMultiRowMediator;
	}
	/** 
	 * @action id=originDataNSMultiRow
	 */
	public String doOriginDataNSMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataNSMultiRowParameters(), originDataNSMultiRowArgNames,
					originDataNSMultiRowArgValues, "originDataNSMultiRow_params_cache");
			DataObject graph = getOriginDataNSMultiRowMediator().getGraph(
					getOriginDataNSMultiRowParameters());
			originDataNSMultiRow = graph.getList(0);
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
			if (originDataNSMultiRowMediator != null) {
				originDataNSMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataNSMultiRow.xml
	 * @methodEntry id=originDataNSMultiRow/paramBean=originDataNSMultiRow/action=originDataNSMultiRow
	 * @action FILL
	 */
	public List getOriginDataNSMultiRow() {
		if (originDataNSMultiRow == null) {
			doOriginDataNSMultiRowFetchAction();
		}
		return originDataNSMultiRow;
	}
	/** 
	 * @action id=originDataNSPSingleRow
	 */
	public String doOriginDataNSPSingleRowUpdateAction() {
		try {
			getOriginDataNSPSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataNSPSingleRow()));
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
			if (originDataNSPSingleRowMediator != null) {
				originDataNSPSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @action id=originDataNSPSingleRow
	 */
	public String doOriginDataNSPSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataNSPSingleRow());
			getOriginDataNSPSingleRow().delete();
			getOriginDataNSPSingleRowMediator().applyChanges(root);
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
			if (originDataNSPSingleRowMediator != null) {
				originDataNSPSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataNSPSingleRow
	 */
	public DataObject getOriginDataNSPSingleRowParameters() {
		if (originDataNSPSingleRowParameters == null) {
			try {
				originDataNSPSingleRowParameters = getOriginDataNSPSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataNSPSingleRowParameters;
	}
	protected JDBCMediator getOriginDataNSPSingleRowMediator() {
		if (originDataNSPSingleRowMediator == null) {
			try {
				originDataNSPSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataNSPSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataNSPSingleRow_metadataFileName),
						originDataNSPSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataNSPSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataNSPSingleRowMediator;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataNSPSingleRowMediator().getGraph( getOriginDataNSPSingleRowParameters() );
	 *   originDataNSPSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataNSPSingleRow
	 */
	public String doOriginDataNSPSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataNSPSingleRowParameters(), originDataNSPSingleRowArgNames,
					originDataNSPSingleRowArgValues, "originDataNSPSingleRow_params_cache");
			DataObject graph = getOriginDataNSPSingleRowMediator().getEmptyGraph();
			originDataNSPSingleRow = graph.createDataObject(0);
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
			if (originDataNSPSingleRowMediator != null) {
				originDataNSPSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataNSPSingleRowMediator().getEmptyGraph();
	 *   originDataNSPSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataNSPSingleRow
	 */
	public String doOriginDataNSPSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataNSPSingleRowParameters(), originDataNSPSingleRowArgNames,
					originDataNSPSingleRowArgValues, "originDataNSPSingleRow_params_cache");
			DataObject graph = getOriginDataNSPSingleRowMediator().getGraph(
					getOriginDataNSPSingleRowParameters());
			originDataNSPSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataNSPSingleRowMediator != null) {
				originDataNSPSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataNSPSingleRow.xml
	 * @methodEntry id=originDataNSPSingleRow/paramBean=originDataNSPSingleRow/action=originDataNSPSingleRow
	 * @action FILL
	 */
	public DataObject getOriginDataNSPSingleRow() {
		if (originDataNSPSingleRow == null) {
			doOriginDataNSPSingleRowFetchAction();
		}
		return originDataNSPSingleRow;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlInputText getIdClasificacionEmpresa1() {
		if (idClasificacionEmpresa1 == null) {
			idClasificacionEmpresa1 = (HtmlInputText) findComponentInRoot("idClasificacionEmpresa1");
		}
		return idClasificacionEmpresa1;
	}
	protected HtmlInputText getIdAntiguedadLaboral1() {
		if (idAntiguedadLaboral1 == null) {
			idAntiguedadLaboral1 = (HtmlInputText) findComponentInRoot("idAntiguedadLaboral1");
		}
		return idAntiguedadLaboral1;
	}
	protected HtmlSelectOneMenu getIdNroSueldos1() {
		if (idNroSueldos1 == null) {
			idNroSueldos1 = (HtmlSelectOneMenu) findComponentInRoot("idNroSueldos1");
		}
		return idNroSueldos1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlCommandExButton getOriginDataNSPSingleRow1() {
		if (originDataNSPSingleRow1 == null) {
			originDataNSPSingleRow1 = (HtmlCommandExButton) findComponentInRoot("originDataNSPSingleRow1");
		}
		return originDataNSPSingleRow1;
	}
	protected HtmlMessages getOriginDataNSPSingleRow2messages() {
		if (originDataNSPSingleRow2messages == null) {
			originDataNSPSingleRow2messages = (HtmlMessages) findComponentInRoot("originDataNSPSingleRow2messages");
		}
		return originDataNSPSingleRow2messages;
	}
	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
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
	protected HtmlFormItem getFormItem3() {
		if (formItem3 == null) {
			formItem3 = (HtmlFormItem) findComponentInRoot("formItem3");
		}
		return formItem3;
	}

}