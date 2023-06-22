/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import java.sql.Connection;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneListbox;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import commonj.sdo.DataObject;
import com.ibm.faces.component.html.HtmlPanelBox;
import com.ibm.faces.component.html.HtmlJspPanel;

/**
 * @author Administrator
 * 
 */
public class CO_wizFirstPage extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject universeParameters;
	protected JDBCMediator universeMediator;
	private static final String universe_metadataFileName = "/WEB-INF/wdo/originDataTRGMultiRow.xml";
	protected static final String[] universeArgNames = {};
	protected static final String[] universeArgValues = {};
	private static final int universeTargetPageSize = -1;
	protected List universe;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlSelectOneListbox listbox1;
	protected UISelectItems selectItems1;
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText text1;
	protected HtmlCommandExButton button1;
	protected Integer _TGR_Value;
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
	 * @action id=universe
	 */
	public String doUniverseUpdateAction() {
		try {
			getUniverseMediator().applyChanges(getRootDataObject(getUniverse()));
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
			if (universeMediator != null) {
				universeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}

	/**
	 * @paramBean id=universe
	 */
	public DataObject getUniverseParameters() {
		if (universeParameters == null) {
			try {
				universeParameters = getUniverseMediator().getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return universeParameters;
	}

	protected JDBCMediator getUniverseMediator() {
		if (universeMediator == null) {
			try {
				universeMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(universe_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(universe_metadataFileName), universeMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			universeMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return universeMediator;
	}

	/**
	 * @action id=universe
	 */
	public String doUniverseFetchAction() {
		try {
			resolveParams(getUniverseParameters(), universeArgNames, universeArgValues,
					"universe_params_cache");
			DataObject graph = getUniverseMediator().getGraph(getUniverseParameters());
			universe = graph.getList(0);
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
			if (universeMediator != null) {
				universeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}

	/**
	 * @mediatorFactory 
	 *                  com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties 
	 *                     metadataFileName=/WEB-INF/wdo/originDataTRGMultiRow.xml
	 * @methodEntry id=universe/paramBean=universe/action=universe
	 * @action FILL
	 */
	public List getUniverse() {
		if (universe == null) {
			doUniverseFetchAction();
		}
		return universe;
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

	
	protected HtmlSelectOneListbox getListbox1() {
		if (listbox1 == null) {
			listbox1 = (HtmlSelectOneListbox) findComponentInRoot("listbox1");
		}
		return listbox1;
	}

	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
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

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	public String doButton1Action() {
		// This is java code that runs when this action method is invoked
		System.out.println("CO_wizFirstPage.doButton1Action()");
		System.out.println(_TGR_Value);
		getSessionScope().put("_param_idTRG", _TGR_Value);
		return "next";
	}

	public Integer get_TGR_Value() {
		if (_TGR_Value == null) {
			_TGR_Value = new Integer(0);
		}
		return _TGR_Value;
	}

	public void set_TGR_Value(Integer _TGR_Value) {
		this._TGR_Value = _TGR_Value;
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