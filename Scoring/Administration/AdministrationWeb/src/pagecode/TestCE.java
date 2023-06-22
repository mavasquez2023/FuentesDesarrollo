/**
 * 
 */
package pagecode;

import commonj.sdo.DataObject;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import java.sql.Connection;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import com.ibm.faces.component.html.HtmlJspPanel;

/**
 * @author Administrator
 *
 */
public class TestCE extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCEParameters;
	protected JDBCMediator originDataCEMediator;
	private static final String originDataCE_metadataFileName = "/WEB-INF/wdo/originDataCESingleRow.xml";
	protected static final String[] originDataCEArgNames = { "requestScope_param_idClasificacionEmpresa" };
	protected static final String[] originDataCEArgValues = { "#{param._idClasificacionEmpresa}" };
	protected DataObject originDataCE;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelLayout layout1;
	protected HtmlOutputLabel nombre1label;
	protected HtmlOutputText nombre1labelText;
	protected HtmlInputText nombre1;
	protected HtmlOutputLabel descripcion1label;
	protected HtmlOutputText descripcion1labelText;
	protected HtmlInputText descripcion1;
	protected HtmlForm form1;
	protected HtmlCommandExButton originDataCE1;
	protected HtmlMessages originDataCE2messages;
	protected HtmlJspPanel jspPanel1;
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
	 * @action id=originDataCE
	 */
	public String doOriginDataCEUpdateAction() {
		try {
			getOriginDataCEMediator().applyChanges(getRootDataObject(getOriginDataCE()));
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
			if (originDataCEMediator != null) {
				originDataCEMediator.setConnectionWrapper(null);
			}
		}
		return "list";
	}
	/** 
	 * @action id=originDataCE
	 */
	public String doOriginDataCEDeleteAction() {
		try {
			DataObject root = getRootDataObject(getOriginDataCE());
			getOriginDataCE().delete();
			getOriginDataCEMediator().applyChanges(root);
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
			if (originDataCEMediator != null) {
				originDataCEMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataCE
	 */
	public DataObject getOriginDataCEParameters() {
		if (originDataCEParameters == null) {
			try {
				originDataCEParameters = getOriginDataCEMediator().getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCEParameters;
	}
	protected JDBCMediator getOriginDataCEMediator() {
		if (originDataCEMediator == null) {
			try {
				originDataCEMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCE_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCE_metadataFileName),
						originDataCEMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCEMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCEMediator;
	}
	/** 
	 * This method was created in order to create new data.  To retrieve existing data:
	 *   DataObject graph = getOriginDataCEMediator().getGraph( getOriginDataCEParameters() );
	 *   originDataCE = (DataObject)graph.getList(0).get(0);  
	 *
	 * @action id=originDataCE
	 */
	public String doOriginDataCECreateAction() {
		try {
			resolveParams(getOriginDataCEParameters(), originDataCEArgNames, originDataCEArgValues,
					"originDataCE_params_cache");
			DataObject graph = getOriginDataCEMediator().getEmptyGraph();
			originDataCE = graph.createDataObject(0);
			autoGenerateKey(originDataCE, getOriginDataCEMediator(), originDataCE_metadataFileName);
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
			if (originDataCEMediator != null) {
				originDataCEMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * This method was created in order to retrieve existing data.  To create new data:
	 *   DataObject graph = getOriginDataCEMediator().getEmptyGraph();
	 *   originDataCE = graph.createDataObject(0);  
	 *
	 * @action id=originDataCE
	 */
	public String doOriginDataCEFetchAction() {
		try {
			resolveParams(getOriginDataCEParameters(), originDataCEArgNames, originDataCEArgValues,
					"originDataCE_params_cache");
			DataObject graph = getOriginDataCEMediator().getGraph(getOriginDataCEParameters());
			originDataCE = (DataObject) graph.getList(0).get(0);
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
			if (originDataCEMediator != null) {
				originDataCEMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCESingleRow.xml
	 * @methodEntry id=originDataCE/paramBean=originDataCE/action=originDataCE
	 * @action CREATE
	 */
	public DataObject getOriginDataCE() {
		if (originDataCE == null) {
			doOriginDataCECreateAction();
		}
		return originDataCE;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlPanelLayout getLayout1() {
		if (layout1 == null) {
			layout1 = (HtmlPanelLayout) findComponentInRoot("layout1");
		}
		return layout1;
	}
	protected HtmlOutputLabel getNombre1label() {
		if (nombre1label == null) {
			nombre1label = (HtmlOutputLabel) findComponentInRoot("nombre1label");
		}
		return nombre1label;
	}
	protected HtmlOutputText getNombre1labelText() {
		if (nombre1labelText == null) {
			nombre1labelText = (HtmlOutputText) findComponentInRoot("nombre1labelText");
		}
		return nombre1labelText;
	}
	protected HtmlInputText getNombre1() {
		if (nombre1 == null) {
			nombre1 = (HtmlInputText) findComponentInRoot("nombre1");
		}
		return nombre1;
	}
	protected HtmlOutputLabel getDescripcion1label() {
		if (descripcion1label == null) {
			descripcion1label = (HtmlOutputLabel) findComponentInRoot("descripcion1label");
		}
		return descripcion1label;
	}
	protected HtmlOutputText getDescripcion1labelText() {
		if (descripcion1labelText == null) {
			descripcion1labelText = (HtmlOutputText) findComponentInRoot("descripcion1labelText");
		}
		return descripcion1labelText;
	}
	protected HtmlInputText getDescripcion1() {
		if (descripcion1 == null) {
			descripcion1 = (HtmlInputText) findComponentInRoot("descripcion1");
		}
		return descripcion1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlCommandExButton getOriginDataCE1() {
		if (originDataCE1 == null) {
			originDataCE1 = (HtmlCommandExButton) findComponentInRoot("originDataCE1");
		}
		return originDataCE1;
	}
	protected HtmlMessages getOriginDataCE2messages() {
		if (originDataCE2messages == null) {
			originDataCE2messages = (HtmlMessages) findComponentInRoot("originDataCE2messages");
		}
		return originDataCE2messages;
	}
	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
	}

}