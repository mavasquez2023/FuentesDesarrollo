/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import pagecode.PageCodeBase;
import view.data.CondicionesStruct;
import view.data.CondicionMasterKey;
import view.data.access.DataAccessException;
import view.data.access.DataProvider;

import java.util.Iterator;
import java.util.List;
import com.ibm.websphere.sdo.access.connections.ConnectionManager;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapperFactory;
import com.ibm.websphere.sdo.mediator.jdbc.ConnectionWrapper;
import com.ibm.websphere.sdo.mediator.JDBCMediator;
import com.ibm.websphere.sdo.mediator.exception.MediatorException;
import com.ibm.websphere.sdo.mediator.jdbc.JDBCMediatorFactory;
import commonj.sdo.DataObject;
import java.sql.Connection;
import javax.faces.context.FacesContext;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;
import javax.faces.component.html.HtmlForm;
import javax.faces.model.SelectItem;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.UISelectItems;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlPanelLayout;
import javax.faces.component.html.HtmlPanelGrid;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 * 
 */
public class CO_HandyEdit extends PageCodeBase {

	protected DataProvider provider;
	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	private CondicionesStruct condicionMasterInstance;	
	protected HtmlOutputText strTipoRenta1;
	protected HtmlOutputText strPerfilRiesgo1;
	protected HtmlOutputText strTipoRiesgo1;
	protected HtmlMessages condicionMasterInstance1messages;
	protected HtmlSelectManyCheckbox checkbox1;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlFormItem formItem4;
	protected HtmlForm form1;
	protected HtmlCommandExButton updateBtn;
	protected HtmlPanelBox box1;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlPanelBox box2;
	protected UISelectItems selectItems1;
	protected HtmlOutputText text1;
	protected HtmlJspPanel jspPanel1;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlScriptCollector scriptCollector1;
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
	protected HtmlJspPanel topJspPanel;
	protected HtmlPanelLayout layout1T;
	protected HtmlJspPanel jspPanelCenterUnder1TC;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlPanelBox box4;

	public DataProvider getProvider() {
		if (provider == null) {
			try {
				provider = new DataProvider();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return provider;
	}

	public void setProvider(DataProvider provider) {
		this.provider = provider;
	}

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
		System.out.println("Entering getOriginDataCOMultiRowParameters");
		if (originDataCOMultiRowParameters == null) {
			try {
				originDataCOMultiRowParameters = getOriginDataCOMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		System.out.println("Exiting getOriginDataCOMultiRowParameters");
		return originDataCOMultiRowParameters;
	}

	protected JDBCMediator getOriginDataCOMultiRowMediator() {
		System.out.println("Entering getOriginDataCOMultiRowMediator");
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
		System.out.println("Exiting getOriginDataCOMultiRowMediator");
		return originDataCOMultiRowMediator;
	}

	/**
	 * @action id=originDataCOMultiRow
	 */
	public String doOriginDataCOMultiRowFetchAction() {
		System.out.println("Entering doOriginDataCOMultiRowFetchAction");
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
		System.out.println("Exiting doOriginDataCOMultiRowFetchAction");
		return "";
	}

	/**
	 * @mediatorFactory 
	 *                  com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties 
	 *                     metadataFileName=/WEB-INF/wdo/originDataCOMultiRow.xml
	 * @methodEntry 
	 *              id=originDataCOMultiRow/paramBean=originDataCOMultiRow/action
	 *              =originDataCOMultiRow
	 * @action FILL
	 */
	public List getOriginDataCOMultiRow() {
		System.out.println("Entering getOriginDataCOMultiRow");
		if (originDataCOMultiRow == null) {
			doOriginDataCOMultiRowFetchAction();
		}
		System.out.println("Exiting getOriginDataCOMultiRow");
		return originDataCOMultiRow;		
	}

	public CondicionesStruct getCondicionMasterInstance() {
		System.out.println("Entering getCondicionMasterInstance");
		if (condicionMasterInstance == null) {
			doCondicionMasterInstanceFetchAction();
		}
		System.out.println("Exiting getOriginDataCOMultiRow");
		return condicionMasterInstance;
	}

	public void setCondicionMasterInstance(CondicionesStruct condicionMasterInstance) {
		System.out.println("Entering setCondicionMasterInstance");
		this.condicionMasterInstance = condicionMasterInstance;
		System.out.println("Exiting setCondicionMasterInstance");
	}

	public String doCondicionMasterInstanceFetchAction() {
		System.out.println("Entering doCondicionMasterInstanceFetchAction");
		CondicionMasterKey key = findKeyFromParams();		
		try {
			condicionMasterInstance = getProvider().findSingleCondicionesStruct(key);			
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		System.out.println("Exiting doCondicionMasterInstanceFetchAction");
		return "";
	}

	
	private CondicionMasterKey findKeyFromParams() {
		System.out.println("Entering findKeyFromParams()");
		
		String strTipoRiesgo = (String) getRequestParam().get("idTRG");
		System.out.println("getRequestParam().get(idTRG): "+ strTipoRiesgo);
		if (strTipoRiesgo == null){			
			strTipoRiesgo = (String) getSessionScope().get("_param_idTRG");
			System.out.println("getSessionScope().get(_param_idTRG): "+ strTipoRiesgo );
		}
		
		String strTipoRenta =(String) getRequestParam().get("idTRT");
		System.out.println("getRequestParam().get(idTRT): "+ strTipoRenta);
		if(strTipoRenta == null){
			strTipoRenta = (String) getSessionScope().get("_param_idTRT");
			System.out.println("getSessionScope().get(_param_idTRT): "+ strTipoRenta );
		}				
		
		String strPerfilRiesgo = (String) getRequestParam().get("idPR");
		System.out.println("getRequestParam().get(idPR): "+ strPerfilRiesgo);
		if(strPerfilRiesgo == null)
		{
			strPerfilRiesgo = (String) getSessionScope().get("_param_idPR");
			System.out.println("getSessionScope().get(_param_idPR): "+ strPerfilRiesgo );
		}
				
		int tipoRiesgo=0;
		int tipoRenta=0;
		int perfilRiesgo=0;
		try {
			tipoRiesgo = Integer. parseInt(strTipoRiesgo);
			System.out.println("Integer version " + tipoRiesgo);
			tipoRenta = Integer.parseInt(strTipoRenta);
			System.out.println("Integer version " + tipoRenta);
			perfilRiesgo = Integer.parseInt(strPerfilRiesgo);
			System.out.println("Integer version " + perfilRiesgo);
			System.out.println("Happy feet. Va a retornnar un new MasterKey por la buena");		
		} catch (NumberFormatException e) {
			System.out.println("Malangas. Estoy reventando en NumberFormatException ");
			System.out.println("Va a retornnar un new MasterKey por la mala o sea con key: 0 0 0 " );
			e.printStackTrace();
		}
		System.out.println("Exiting findKeyFromParams()");
		return new CondicionMasterKey(tipoRiesgo, tipoRenta, perfilRiesgo);		
	}

	protected HtmlOutputText getStrTipoRenta1() {
		if (strTipoRenta1 == null) {
			strTipoRenta1 = (HtmlOutputText) findComponentInRoot("strTipoRenta1");
		}
		return strTipoRenta1;
	}

	protected HtmlOutputText getStrPerfilRiesgo1() {
		if (strPerfilRiesgo1 == null) {
			strPerfilRiesgo1 = (HtmlOutputText) findComponentInRoot("strPerfilRiesgo1");
		}
		return strPerfilRiesgo1;
	}

	protected HtmlOutputText getStrTipoRiesgo1() {
		if (strTipoRiesgo1 == null) {
			strTipoRiesgo1 = (HtmlOutputText) findComponentInRoot("strTipoRiesgo1");
		}
		return strTipoRiesgo1;
	}

	protected HtmlMessages getCondicionMasterInstance1messages() {
		if (condicionMasterInstance1messages == null) {
			condicionMasterInstance1messages = (HtmlMessages) findComponentInRoot("condicionMasterInstance1messages");
		}
		return condicionMasterInstance1messages;
	}

	protected HtmlSelectManyCheckbox getCheckbox1() {
		if (checkbox1 == null) {
			checkbox1 = (HtmlSelectManyCheckbox) findComponentInRoot("checkbox1");
		}
		return checkbox1;
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

	protected HtmlForm getForm1() {
		System.out.println("Entering getForm1()");
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		System.out.println("Exiting getForm1()");
		return form1;
	}

	protected HtmlCommandExButton getUpdateBtn() {
		if (updateBtn == null) {
			updateBtn = (HtmlCommandExButton) findComponentInRoot("updateBtn");
		}
		return updateBtn;
	}

	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}

	protected HtmlCommandExButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = (HtmlCommandExButton) findComponentInRoot("cancelBtn");
		}
		return cancelBtn;
	}

	protected HtmlPanelBox getBox2() {
		if (box2 == null) {
			box2 = (HtmlPanelBox) findComponentInRoot("box2");
		}
		return box2;
	}

	public String doUpdateBtnAction() {
		System.out.println("Entering doUpdateBtnAction()");
		//TODO Aqui voy con el update :))|
		// This is java code that runs when this action method is invoked
		List newCondiciones = getCondicionMasterInstance().getCondiciones();
		//borrar los registros con esta key
		CondicionMasterKey key = this.getCondicionMasterInstance().getKey();				
		try {
			getProvider().deleteCondicionesAsociadas(key);
			//iterar sobre la lista y para cada uno 
			//hacer el insert correspondiente
			for (Iterator iterator = newCondiciones.iterator(); iterator.hasNext();) {
				String idCondicion = (String) iterator.next();
				getProvider().insertMatrizCondicionRecord(key.getTipoRiesgo(), key.getTipoRenta(), key.getPerfilRiesgo(), idCondicion);
			}
		
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting doUpdateBtnAction()");
		return "list";
	}

	public String doCancelBtnAction() {
		System.out.println("Entering doCancelBtnAction()");
		setCondicionMasterInstance(null);
//		getSessionScope().remove(key);
//		getSessionScope().remove(key);
//		getSessionScope().remove(key);
		System.out.println("Exiting doCancelBtnAction()");
		return "cancel";
	}

	protected UISelectItems getSelectItems1() {
		System.out.println("Entering getSelectItems1()");
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		System.out.println("Exiting getSelectItems1()");
		return selectItems1;
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

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
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

	protected HtmlPanelBox getBox4() {
		if (box4 == null) {
			box4 = (HtmlPanelBox) findComponentInRoot("box4");
		}
		return box4;
	}

}