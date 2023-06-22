/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import pagecode.PageCodeBase;
import commonj.sdo.DataObject;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import java.sql.Connection;
import java.util.List;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;
import javax.faces.component.UISelectItems;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 *
 */
public class CO_New_temporal extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataMCOSingleRowParameters;
	protected JDBCMediator originDataMCOSingleRowMediator;
	private static final String originDataMCOSingleRow_metadataFileName = "/WEB-INF/wdo/originDataMCOSingleRow.xml";
	protected static final String[] originDataMCOSingleRowArgNames = { "sessionScope_param_idPR",
				"sessionScope_param_idTRT", "sessionScope_param_idTRG" };
	protected static final String[] originDataMCOSingleRowArgValues = { "#{param._idPR}",
				"#{param._idTRT}", "#{param._idTRG}" };
	protected DataObject originDataMCOSingleRow;
	protected DataObject originDataTRTMultiRowParameters;
	protected JDBCMediator originDataTRTMultiRowMediator;
	private static final String originDataTRTMultiRow_metadataFileName = "/WEB-INF/wdo/originDataTRTMultiRow.xml";
	protected static final String[] originDataTRTMultiRowArgNames = {};
	protected static final String[] originDataTRTMultiRowArgValues = {};
	private static final int originDataTRTMultiRowTargetPageSize = -1;
	protected List originDataTRTMultiRow;
	protected DataObject originDataTRGMultiRowParameters;
	protected JDBCMediator originDataTRGMultiRowMediator;
	private static final String originDataTRGMultiRow_metadataFileName = "/WEB-INF/wdo/originDataTRGMultiRow.xml";
	protected static final String[] originDataTRGMultiRowArgNames = {};
	protected static final String[] originDataTRGMultiRowArgValues = {};
	private static final int originDataTRGMultiRowTargetPageSize = -1;
	protected List originDataTRGMultiRow;
	protected DataObject originDataPRMultiRowParameters;
	protected JDBCMediator originDataPRMultiRowMediator;
	private static final String originDataPRMultiRow_metadataFileName = "/WEB-INF/wdo/originDataPRMultiRow.xml";
	protected static final String[] originDataPRMultiRowArgNames = {};
	protected static final String[] originDataPRMultiRowArgValues = {};
	private static final int originDataPRMultiRowTargetPageSize = -1;
	protected List originDataPRMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelBox box1;
	protected HtmlOutputText text1;
	protected HtmlJspPanel jspPanel1;
	protected HtmlSelectOneMenu idTipoRiesgoExterno1;
	protected HtmlSelectOneMenu idTipoRentaPermitida1;
	protected HtmlSelectOneMenu idPerfilRiesgo1;
	protected HtmlSelectOneMenu idCondicion1;
	protected HtmlForm form1;
	protected HtmlCommandExButton originDataMCOSingleRow1;
	protected HtmlMessages originDataMCOSingleRow2messages;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlFormItem formItem4;
	protected HtmlPanelBox box2;
	protected HtmlCommandExButton button1;
	protected UISelectItems selectItems1;
	protected UISelectItems selectItems2;
	protected UISelectItems selectItems3;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	protected UISelectItems selectItems4;
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
	protected HtmlJspPanel rightJspPanel;
	protected HtmlPanelLayout layout1R;
	protected HtmlJspPanel jspPanelUnder1RC;
	protected HtmlJspPanel bottomJspPanel;
	protected HtmlPanelBox box4;
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
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
	 * @action id=originDataMCOSingleRow
	 */
	public String doOriginDataMCOSingleRowUpdateAction() {
		try {
			getOriginDataMCOSingleRowMediator().applyChanges(
					getRootDataObject(getOriginDataMCOSingleRow()));
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
			if (originDataMCOSingleRowMediator != null) {
				originDataMCOSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @action id=originDataMCOSingleRow
	 */
	public String doOriginDataMCOSingleRowDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataMCOSingleRow());
			getOriginDataMCOSingleRow().delete();
			getOriginDataMCOSingleRowMediator().applyChanges(root);
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
			if (originDataMCOSingleRowMediator != null) {
				originDataMCOSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataMCOSingleRow
	 */
	public DataObject getOriginDataMCOSingleRowParameters() {
		if (originDataMCOSingleRowParameters == null) {
			try {
				originDataMCOSingleRowParameters = getOriginDataMCOSingleRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataMCOSingleRowParameters;
	}
	protected JDBCMediator getOriginDataMCOSingleRowMediator() {
		if (originDataMCOSingleRowMediator == null) {
			try {
				originDataMCOSingleRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataMCOSingleRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataMCOSingleRow_metadataFileName),
						originDataMCOSingleRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataMCOSingleRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataMCOSingleRowMediator;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataMCOSingleRowMediator().getGraph( getOriginDataMCOSingleRowParameters() );
	 *   originDataMCOSingleRow = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataMCOSingleRow
	 */
	public String doOriginDataMCOSingleRowCreateAction() {
		try {
			resolveParams(getOriginDataMCOSingleRowParameters(), originDataMCOSingleRowArgNames,
					originDataMCOSingleRowArgValues, "originDataMCOSingleRow_params_cache");
			DataObject graph = getOriginDataMCOSingleRowMediator().getEmptyGraph();
			originDataMCOSingleRow = graph.createDataObject(0);
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
			if (originDataMCOSingleRowMediator != null) {
				originDataMCOSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataMCOSingleRowMediator().getEmptyGraph();
	 *   originDataMCOSingleRow = graph.createDataObject(0);  
	 *
	 * @action id=originDataMCOSingleRow
	 */
	public String doOriginDataMCOSingleRowFetchAction() {
		try {
			resolveParams(getOriginDataMCOSingleRowParameters(), originDataMCOSingleRowArgNames,
					originDataMCOSingleRowArgValues, "originDataMCOSingleRow_params_cache");
			DataObject graph = getOriginDataMCOSingleRowMediator().getGraph(
					getOriginDataMCOSingleRowParameters());
			originDataMCOSingleRow = (DataObject) graph.getList(0).get(0);
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
			if (originDataMCOSingleRowMediator != null) {
				originDataMCOSingleRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOSingleRow.xml
	 * @methodEntry id=originDataMCOSingleRow/paramBean=originDataMCOSingleRow/action=originDataMCOSingleRow
	 * @action CREATE
	 */
	public DataObject getOriginDataMCOSingleRow() {
		if (originDataMCOSingleRow == null) {
			doOriginDataMCOSingleRowCreateAction();
		}
		return originDataMCOSingleRow;
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
	/** 
	 * @action id=originDataTRGMultiRow
	 */
	public String doOriginDataTRGMultiRowUpdateAction() {
		try {
			getOriginDataTRGMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataTRGMultiRow()));
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
			if (originDataTRGMultiRowMediator != null) {
				originDataTRGMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataTRGMultiRow
	 */
	public DataObject getOriginDataTRGMultiRowParameters() {
		if (originDataTRGMultiRowParameters == null) {
			try {
				originDataTRGMultiRowParameters = getOriginDataTRGMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataTRGMultiRowParameters;
	}
	protected JDBCMediator getOriginDataTRGMultiRowMediator() {
		if (originDataTRGMultiRowMediator == null) {
			try {
				originDataTRGMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataTRGMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataTRGMultiRow_metadataFileName),
						originDataTRGMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataTRGMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataTRGMultiRowMediator;
	}
	/** 
	 * @action id=originDataTRGMultiRow
	 */
	public String doOriginDataTRGMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataTRGMultiRowParameters(), originDataTRGMultiRowArgNames,
					originDataTRGMultiRowArgValues, "originDataTRGMultiRow_params_cache");
			DataObject graph = getOriginDataTRGMultiRowMediator().getGraph(
					getOriginDataTRGMultiRowParameters());
			originDataTRGMultiRow = graph.getList(0);
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
			if (originDataTRGMultiRowMediator != null) {
				originDataTRGMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataTRGMultiRow.xml
	 * @methodEntry id=originDataTRGMultiRow/paramBean=originDataTRGMultiRow/action=originDataTRGMultiRow
	 * @action FILL
	 */
	public List getOriginDataTRGMultiRow() {
		if (originDataTRGMultiRow == null) {
			doOriginDataTRGMultiRowFetchAction();
		}
		return originDataTRGMultiRow;
	}
	/** 
	 * @action id=originDataPRMultiRow
	 */
	public String doOriginDataPRMultiRowUpdateAction() {
		try {
			getOriginDataPRMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataPRMultiRow()));
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
			if (originDataPRMultiRowMediator != null) {
				originDataPRMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataPRMultiRow
	 */
	public DataObject getOriginDataPRMultiRowParameters() {
		if (originDataPRMultiRowParameters == null) {
			try {
				originDataPRMultiRowParameters = getOriginDataPRMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataPRMultiRowParameters;
	}
	protected JDBCMediator getOriginDataPRMultiRowMediator() {
		if (originDataPRMultiRowMediator == null) {
			try {
				originDataPRMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataPRMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataPRMultiRow_metadataFileName),
						originDataPRMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataPRMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataPRMultiRowMediator;
	}
	/** 
	 * @action id=originDataPRMultiRow
	 */
	public String doOriginDataPRMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataPRMultiRowParameters(), originDataPRMultiRowArgNames,
					originDataPRMultiRowArgValues, "originDataPRMultiRow_params_cache");
			DataObject graph = getOriginDataPRMultiRowMediator().getGraph(
					getOriginDataPRMultiRowParameters());
			originDataPRMultiRow = graph.getList(0);
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
			if (originDataPRMultiRowMediator != null) {
				originDataPRMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataPRMultiRow.xml
	 * @methodEntry id=originDataPRMultiRow/paramBean=originDataPRMultiRow/action=originDataPRMultiRow
	 * @action FILL
	 */
	public List getOriginDataPRMultiRow() {
		if (originDataPRMultiRow == null) {
			doOriginDataPRMultiRowFetchAction();
		}
		return originDataPRMultiRow;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
	}
	protected HtmlSelectOneMenu getIdTipoRiesgoExterno1() {
		if (idTipoRiesgoExterno1 == null) {
			idTipoRiesgoExterno1 = (HtmlSelectOneMenu) findComponentInRoot("idTipoRiesgoExterno1");
		}
		return idTipoRiesgoExterno1;
	}
	protected HtmlSelectOneMenu getIdTipoRentaPermitida1() {
		if (idTipoRentaPermitida1 == null) {
			idTipoRentaPermitida1 = (HtmlSelectOneMenu) findComponentInRoot("idTipoRentaPermitida1");
		}
		return idTipoRentaPermitida1;
	}
	protected HtmlSelectOneMenu getIdPerfilRiesgo1() {
		if (idPerfilRiesgo1 == null) {
			idPerfilRiesgo1 = (HtmlSelectOneMenu) findComponentInRoot("idPerfilRiesgo1");
		}
		return idPerfilRiesgo1;
	}
	protected HtmlSelectOneMenu getIdCondicion1() {
		if (idCondicion1 == null) {
			idCondicion1 = (HtmlSelectOneMenu) findComponentInRoot("idCondicion1");
		}
		return idCondicion1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlCommandExButton getOriginDataMCOSingleRow1() {
		if (originDataMCOSingleRow1 == null) {
			originDataMCOSingleRow1 = (HtmlCommandExButton) findComponentInRoot("originDataMCOSingleRow1");
		}
		return originDataMCOSingleRow1;
	}
	protected HtmlMessages getOriginDataMCOSingleRow2messages() {
		if (originDataMCOSingleRow2messages == null) {
			originDataMCOSingleRow2messages = (HtmlMessages) findComponentInRoot("originDataMCOSingleRow2messages");
		}
		return originDataMCOSingleRow2messages;
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
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	protected HtmlPanelBox getBox2() {
		if (box2 == null) {
			box2 = (HtmlPanelBox) findComponentInRoot("box2");
		}
		return box2;
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
	protected UISelectItems getSelectItems2() {
		if (selectItems2 == null) {
			selectItems2 = (UISelectItems) findComponentInRoot("selectItems2");
		}
		return selectItems2;
	}
	protected UISelectItems getSelectItems3() {
		if (selectItems3 == null) {
			selectItems3 = (UISelectItems) findComponentInRoot("selectItems3");
		}
		return selectItems3;
	}
	/** 
	 * @action id=originDataCOMultiRow
	 */
	public String doOriginDataCOMultiRowUpdateAction() {
		try {
			getOriginDataCOMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataCOMultiRow()));
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
			if (originDataCOMultiRowMediator != null) {
				originDataCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataCOMultiRow
	 */
	public DataObject getOriginDataCOMultiRowParameters() {
		if (originDataCOMultiRowParameters == null) {
			try {
				originDataCOMultiRowParameters = getOriginDataCOMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCOMultiRowParameters;
	}
	protected JDBCMediator getOriginDataCOMultiRowMediator() {
		if (originDataCOMultiRowMediator == null) {
			try {
				originDataCOMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCOMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCOMultiRow_metadataFileName),
						originDataCOMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCOMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCOMultiRowMediator;
	}
	/** 
	 * @action id=originDataCOMultiRow
	 */
	public String doOriginDataCOMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataCOMultiRowParameters(), originDataCOMultiRowArgNames,
					originDataCOMultiRowArgValues, "originDataCOMultiRow_params_cache");
			DataObject graph = getOriginDataCOMultiRowMediator().getGraph(
					getOriginDataCOMultiRowParameters());
			originDataCOMultiRow = graph.getList(0);
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
			if (originDataCOMultiRowMediator != null) {
				originDataCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCOMultiRow.xml
	 * @methodEntry id=originDataCOMultiRow/paramBean=originDataCOMultiRow/action=originDataCOMultiRow
	 * @action FILL
	 */
	public List getOriginDataCOMultiRow() {
		if (originDataCOMultiRow == null) {
			doOriginDataCOMultiRowFetchAction();
		}
		return originDataCOMultiRow;
	}
	protected UISelectItems getSelectItems4() {
		if (selectItems4 == null) {
			selectItems4 = (UISelectItems) findComponentInRoot("selectItems4");
		}
		return selectItems4;
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
	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
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

}