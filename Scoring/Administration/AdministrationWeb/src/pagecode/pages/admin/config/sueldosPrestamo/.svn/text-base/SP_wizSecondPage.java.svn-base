/**
 * 
 */
package pagecode.pages.admin.config.sueldosPrestamo;

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
import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlJspPanel;

/**
 * @author Administrator
 *
 */
public class SP_wizSecondPage extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject subUniverseParameters;
	protected JDBCMediator subUniverseMediator;
	private static final String subUniverse_metadataFileName = "/WEB-INF/wdo/originDataALMultiRow.xml";
	protected static final String[] subUniverseArgNames = {};
	protected static final String[] subUniverseArgValues = {};
	private static final int subUniverseTargetPageSize = -1;
	protected List subUniverse;
	protected Integer _AL_Value;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText text1;
	protected HtmlForm form1;
	protected HtmlSelectOneListbox listbox1;
	protected HtmlCommandExButton button1;
	protected UISelectItems selectItems1;
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
	 * @action id=subUniverse
	 */
	public String doSubUniverseUpdateAction() {
		try {
			getSubUniverseMediator().applyChanges(getRootDataObject(getSubUniverse()));
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
			if (subUniverseMediator != null) {
				subUniverseMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=subUniverse
	 */
	public DataObject getSubUniverseParameters() {
		if (subUniverseParameters == null) {
			try {
				subUniverseParameters = getSubUniverseMediator().getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return subUniverseParameters;
	}
	protected JDBCMediator getSubUniverseMediator() {
		if (subUniverseMediator == null) {
			try {
				subUniverseMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(subUniverse_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(subUniverse_metadataFileName),
						subUniverseMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			subUniverseMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return subUniverseMediator;
	}
	/** 
	 * @action id=subUniverse
	 */
	public String doSubUniverseFetchAction() {
		try {
			resolveParams(getSubUniverseParameters(), subUniverseArgNames, subUniverseArgValues,
					"subUniverse_params_cache");
			DataObject graph = getSubUniverseMediator().getGraph(getSubUniverseParameters());
			subUniverse = graph.getList(0);
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
			if (subUniverseMediator != null) {
				subUniverseMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataALMultiRow.xml
	 * @methodEntry id=subUniverse/paramBean=subUniverse/action=subUniverse
	 * @action FILL
	 */
	public List getSubUniverse() {
		if (subUniverse == null) {
			doSubUniverseFetchAction();
		}
		return subUniverse;
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
	public String doButton1Action() {
		// This is java code that runs when this action method is invoked
		System.out.println("SP_wizSecondPage.doButton1Action()");
		System.out.println(_AL_Value);
		getSessionScope().put("_param_idAL", _AL_Value);
		return "next";
	}
	public Integer get_AL_Value() {
		if (_AL_Value == null) {
			_AL_Value = new Integer(0);
		}
		return _AL_Value;
	}

	public void set_AL_Value(Integer _AL_Value) {
		this._AL_Value = _AL_Value;
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