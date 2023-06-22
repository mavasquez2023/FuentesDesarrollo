/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import pagecode.PageCodeBase;

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
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.UISelectItems;
import java.util.ArrayList;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlJspPanel;
import com.ibm.faces.component.html.HtmlPanelFormBox;
import com.ibm.faces.component.html.HtmlFormItem;

/**
 * @author Administrator
 *
 */
public class CO_wizFourthPage extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	protected DataObject originDataMCOSingleRowParameters;
	protected JDBCMediator originDataMCOSingleRowMediator;
	private static final String originDataMCOSingleRow_metadataFileName = "/WEB-INF/wdo/originDataMCOSingleRow.xml";
	protected DataObject originDataMCOSingleRow;
	protected static final String[] originDataMCOSingleRowArgNames = { "sessionScope_param_idPR",
				"sessionScope_param_idTRT", "sessionScope_param_idTRG" };
	protected static final String[] originDataMCOSingleRowArgValues = { "#{sessionScope._param_idPR}",
				"#{sessionScope._param_idTRT}", "#{sessionScope._param_idTRG}" };
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText idTipoRiesgoExterno1;
	protected HtmlInputText idTipoRentaPermitida1;
	protected HtmlInputText idPerfilRiesgo1;
	protected HtmlCommandExButton originDataMCOSingleRow1;
	protected HtmlMessages originDataMCOSingleRow2messages;
	protected HtmlSelectManyCheckbox checkbox1;
	protected UISelectItems selectItems1;
	protected ArrayList arrayIDCondiciones;
	protected HtmlPanelBox box6;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlPanelBox box7;
	protected HtmlCommandExButton cancelBtn;
	protected HtmlPanelFormBox formBox1;
	protected HtmlFormItem formItem1;
	protected HtmlFormItem formItem2;
	protected HtmlFormItem formItem3;
	protected HtmlFormItem formItem4;
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
	/** 
	 * @action id=originDataMCOSingleRow
	 */
	public String doOriginDataMCOSingleRowUpdateAction() {
		try {
			
			//*****-----
			//limpiar el DataGraph y crearlo de nuevo
			//esto es crear tantos DataObjects como elementos en el
			//nuevo ArrayIDCondiciones.Tendran el mismo
			//valor para RiesgoExterno, TipoRenta y Perfil
			//y con el correpondiente IdCondicion
			// ;)
			
			//siempre tiene al menos uno asi que lo sobreescribo con el primero de la nueva seleccion
			Integer primero = (Integer) arrayIDCondiciones.get(0);
			originDataMCOSingleRow.set("IdCondicion", primero);
			
			List listRecords = originDataMCOSingleRow.getDataGraph().getRootObject().getList(0);
			System.out.println("CO_wizFourthPage.doOriginDataMCOSingleRowUpdateAction()");
			debug(listRecords);
			for (int i = 0; i < listRecords.size(); i++) {
				DataObject dataObject = (DataObject)listRecords.get(i);
				dataObject.get("IdCondicion");
//				debug(originDataMCOSingleRow);
			}
			//debug(getMatrizCondicionOtorgamiento());
//			for (Iterator iterator = listRecords.iterator(); iterator.hasNext();) {
//				DataObject object = (DataObject) iterator.next();
//				object.delete();
//			}			
			//getMatrizCondicionOtorgamiento().getDataGraph().getRootObject().delete();
			
			//this.matrizCondicionOtorgamiento = 
		    //getMatrizCondicionOtorgamientoMediator().getEmptyGraph();
//			DataObject newRecord = matrizCondicionOtorgamiento.createDataObject(0);
//			newRecord.set("IdTipoRiesgoExterno", new Integer(1));
//			newRecord.set("IdTipoRentaPermitida",new Integer(2));
//			newRecord.set("IdPerfilRiesgo",new Integer(4));
//			newRecord.set("IdCondicion",new Integer(1));

			//probar getContainer()
//			DataObject graph = getMatrizCondicionOtorgamientoMediator().getEmptyGraph();
//			DataObject newRecord = graph.createDataObject();
//			debug(getMatrizCondicionOtorgamiento());
//			
//			
//			DataGraph dataGraphRoot = (DataGraph) getRootDataObject(getMatrizCondicionOtorgamiento());
//			System.out.println(dataGraphRoot.getClass().getName());			
			
			//iterar sobre el arrayIDCondiciones
//			getArrayIDCondiciones();
//			getMatrizCondicionOtorgamientoMediator().applyChanges(
//					getRootDataObject(getMatrizCondicionOtorgamiento()));
//			
			
			
			
			
			//*****-----
			
//			getOriginDataMCOSingleRowMediator().applyChanges(
//					getRootDataObject(getOriginDataMCOSingleRow()));
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
		return "list";
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
			//-----
			//EXCELENTE!! This is how finally I could solve the CheckBoxes challenge !! 
			List myLista = graph.getList(0);
			for (Iterator iterator = myLista.iterator(); iterator.hasNext();) {
				DataObject currentDataObject = (DataObject) iterator.next();
				Integer idCondition = new Integer(currentDataObject.getInt("IdCondicion"));
				this.getArrayIDCondiciones().add(idCondition);
			}			
			//-----
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
	 * @action FILL
	 */
	public DataObject getOriginDataMCOSingleRow() {
		if (originDataMCOSingleRow == null) {
			doOriginDataMCOSingleRowFetchAction();
		}
		return originDataMCOSingleRow;
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
	protected HtmlInputText getIdTipoRiesgoExterno1() {
		if (idTipoRiesgoExterno1 == null) {
			idTipoRiesgoExterno1 = (HtmlInputText) findComponentInRoot("idTipoRiesgoExterno1");
		}
		return idTipoRiesgoExterno1;
	}
	protected HtmlInputText getIdTipoRentaPermitida1() {
		if (idTipoRentaPermitida1 == null) {
			idTipoRentaPermitida1 = (HtmlInputText) findComponentInRoot("idTipoRentaPermitida1");
		}
		return idTipoRentaPermitida1;
	}
	protected HtmlInputText getIdPerfilRiesgo1() {
		if (idPerfilRiesgo1 == null) {
			idPerfilRiesgo1 = (HtmlInputText) findComponentInRoot("idPerfilRiesgo1");
		}
		return idPerfilRiesgo1;
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
	protected HtmlSelectManyCheckbox getCheckbox1() {
		if (checkbox1 == null) {
			checkbox1 = (HtmlSelectManyCheckbox) findComponentInRoot("checkbox1");
		}
		return checkbox1;
	}
	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
	}
	public ArrayList getArrayIDCondiciones() {
		if (arrayIDCondiciones == null) {
			arrayIDCondiciones = new ArrayList();
		}
		return arrayIDCondiciones;
	}
	public void setArrayIDCondiciones(ArrayList arrayIDCondiciones) {
		this.arrayIDCondiciones = arrayIDCondiciones;
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
	protected HtmlFormItem getFormItem4() {
		if (formItem4 == null) {
			formItem4 = (HtmlFormItem) findComponentInRoot("formItem4");
		}
		return formItem4;
	}
	public String doCancelBtnAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "list";
		return "list";
	}

}