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
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneListbox;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.UISelectItems;
import java.lang.String;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlJspPanel;

/**
 * @author Administrator
 *
 */
public class SP_wizFirstPage extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCEMultiRowParameters;
	protected JDBCMediator originDataCEMultiRowMediator;
	private static final String originDataCEMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCEMultiRow.xml";
	protected static final String[] originDataCEMultiRowArgNames = {};
	protected static final String[] originDataCEMultiRowArgValues = {};
	private static final int originDataCEMultiRowTargetPageSize = -1;
	protected List originDataCEMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText text1;
	protected HtmlForm form1;
	protected HtmlSelectOneListbox listbox1;
	protected HtmlCommandExButton button1;
	protected UISelectItems selectItems1;
	protected Integer _CEValue;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
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
	 * @action id=originDataCEMultiRow
	 */
	public String doOriginDataCEMultiRowUpdateAction() {
		try {
			getOriginDataCEMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataCEMultiRow()));
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
			if (originDataCEMultiRowMediator != null) {
				originDataCEMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataCEMultiRow
	 */
	public DataObject getOriginDataCEMultiRowParameters() {
		if (originDataCEMultiRowParameters == null) {
			try {
				originDataCEMultiRowParameters = getOriginDataCEMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataCEMultiRowParameters;
	}
	protected JDBCMediator getOriginDataCEMultiRowMediator() {
		if (originDataCEMultiRowMediator == null) {
			try {
				originDataCEMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataCEMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataCEMultiRow_metadataFileName),
						originDataCEMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataCEMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataCEMultiRowMediator;
	}
	/** 
	 * @action id=originDataCEMultiRow
	 */
	public String doOriginDataCEMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataCEMultiRowParameters(), originDataCEMultiRowArgNames,
					originDataCEMultiRowArgValues, "originDataCEMultiRow_params_cache");
			DataObject graph = getOriginDataCEMultiRowMediator().getGraph(
					getOriginDataCEMultiRowParameters());
			originDataCEMultiRow = graph.getList(0);
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
			if (originDataCEMultiRowMediator != null) {
				originDataCEMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataCEMultiRow.xml
	 * @methodEntry id=originDataCEMultiRow/paramBean=originDataCEMultiRow/action=originDataCEMultiRow
	 * @action FILL
	 */
	public List getOriginDataCEMultiRow() {
		if (originDataCEMultiRow == null) {
			doOriginDataCEMultiRowFetchAction();
		}
		return originDataCEMultiRow;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlPanelGrid getGrid1() {
		if (grid1 == null) {
			grid1 = (HtmlPanelGrid) findComponentInRoot("grid1");
		}
		return grid1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlSelectOneListbox getListbox1() {
		if (listbox1 == null) {
			listbox1 = (HtmlSelectOneListbox) findComponentInRoot("listbox1");
		}
		return listbox1;
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
	public Integer get_CEValue() {
		if (_CEValue == null) {
			_CEValue = new Integer(0);
		}
		return _CEValue;
	}
	public void set_CEValue(Integer _CEValue) {
		this._CEValue = _CEValue;
	}
	public String doButton1Action() {
		// This is java code that runs when this action method is invoked
		System.out.println("SP_wizFirstPage.doButton1Action()");
		System.out.println(_CEValue);
		getSessionScope().put("_param_idCE", _CEValue);
		return "next";
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

}