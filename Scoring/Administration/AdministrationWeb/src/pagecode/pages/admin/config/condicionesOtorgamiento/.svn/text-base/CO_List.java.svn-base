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
import java.util.ArrayList;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;

import pagecode.utils.InnerData;
import pagecode.utils.MyData;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.UIColumnEx;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlPanelBox;

/**
 * @author Administrator
 *
 */
public class CO_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	protected DataObject originDataMCOMultiRowParameters;
	protected JDBCMediator originDataMCOMultiRowMediator;
	private static final String originDataMCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataMCOMultiRow.xml";
	protected static final String[] originDataMCOMultiRowArgNames = {};
	protected static final String[] originDataMCOMultiRowArgValues = {};
	private static final int originDataMCOMultiRowTargetPageSize = -1;
	protected List originDataMCOMultiRow;
	protected DataObject originDataMCOMultiRowSpecialParameters;
	protected JDBCMediator originDataMCOMultiRowSpecialMediator;
	private static final String originDataMCOMultiRowSpecial_metadataFileName = "/WEB-INF/wdo/originDataMCOMultiRowSpecial.xml";
	private static final int originDataMCOMultiRowSpecialTargetPageSize = -1;
	protected List originDataMCOMultiRowSpecial;
	protected ArrayList arrayIDCondiciones;
	protected DataObject originDataTRGMultiRowParameters;
	protected JDBCMediator originDataTRGMultiRowMediator;
	private static final String originDataTRGMultiRow_metadataFileName = "/WEB-INF/wdo/originDataTRGMultiRow.xml";
	protected static final String[] originDataTRGMultiRowArgNames = {};
	protected static final String[] originDataTRGMultiRowArgValues = {};
	private static final int originDataTRGMultiRowTargetPageSize = -1;
	protected List originDataTRGMultiRow;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected static final String[] originDataMCOMultiRowSpecialArgNames = {};
	protected static final String[] originDataMCOMultiRowSpecialArgValues = {};
	protected MyData myDataInstance;
	protected ArrayList myDataListInstance;
	protected HtmlDataTableEx myDataListInstance1;
	protected UIColumnEx innerList1column;
	protected HtmlOutputText innerList1text;
	protected UIColumnEx innerList2column;
	protected HtmlOutputText innerList2text;
	protected HtmlOutputText propOne1text;
	protected HtmlOutputText propTwo1text;
	protected HtmlOutputText propThree1text;
	protected HtmlDataTableEx innerList1;
	protected HtmlOutputText innerList2;
	protected UIColumnEx propOne1column;
	protected HtmlOutputText propOne1;
	protected UIColumnEx propTwo1column;
	protected HtmlOutputText propTwo1;
	protected UIColumnEx propThree1column;
	protected HtmlOutputText propThree1;
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box2;
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
	 * @action id=originDataMCOMultiRow
	 */
	public String doOriginDataMCOMultiRowUpdateAction() {
		try {
			getOriginDataMCOMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataMCOMultiRow()));
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
			if (originDataMCOMultiRowMediator != null) {
				originDataMCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataMCOMultiRow
	 */
	public DataObject getOriginDataMCOMultiRowParameters() {
		if (originDataMCOMultiRowParameters == null) {
			try {
				originDataMCOMultiRowParameters = getOriginDataMCOMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataMCOMultiRowParameters;
	}
	protected JDBCMediator getOriginDataMCOMultiRowMediator() {
		if (originDataMCOMultiRowMediator == null) {
			try {
				originDataMCOMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataMCOMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataMCOMultiRow_metadataFileName),
						originDataMCOMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataMCOMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataMCOMultiRowMediator;
	}
	/** 
	 * @action id=originDataMCOMultiRow
	 */
	public String doOriginDataMCOMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataMCOMultiRowParameters(), originDataMCOMultiRowArgNames,
					originDataMCOMultiRowArgValues, "originDataMCOMultiRow_params_cache");
			DataObject graph = getOriginDataMCOMultiRowMediator().getGraph(
					getOriginDataMCOMultiRowParameters());
			originDataMCOMultiRow = graph.getList(0);
			
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
			if (originDataMCOMultiRowMediator != null) {
				originDataMCOMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOMultiRow.xml
	 * @methodEntry id=originDataMCOMultiRow/paramBean=originDataMCOMultiRow/action=originDataMCOMultiRow
	 * @action FILL
	 */
	public List getOriginDataMCOMultiRow() {
		if (originDataMCOMultiRow == null) {
			doOriginDataMCOMultiRowFetchAction();
		}
		return originDataMCOMultiRow;
	}
	/** 
	 * @action id=originDataMCOMultiRowSpecial
	 */
	public String doOriginDataMCOMultiRowSpecialUpdateAction() {
		try {
			getOriginDataMCOMultiRowSpecialMediator().applyChanges(
					getRootDataObject(getOriginDataMCOMultiRowSpecial()));
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
			if (originDataMCOMultiRowSpecialMediator != null) {
				originDataMCOMultiRowSpecialMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataMCOMultiRowSpecial
	 */
	public DataObject getOriginDataMCOMultiRowSpecialParameters() {
		if (originDataMCOMultiRowSpecialParameters == null) {
			try {
				originDataMCOMultiRowSpecialParameters = getOriginDataMCOMultiRowSpecialMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataMCOMultiRowSpecialParameters;
	}
	protected JDBCMediator getOriginDataMCOMultiRowSpecialMediator() {
		if (originDataMCOMultiRowSpecialMediator == null) {
			try {
				originDataMCOMultiRowSpecialMediator = JDBCMediatorFactory.soleInstance
						.createMediator(
								getResourceInputStream(originDataMCOMultiRowSpecial_metadataFileName),
								getSDOConnectionWrapper());
				initSchema(getRealPath(originDataMCOMultiRowSpecial_metadataFileName),
						originDataMCOMultiRowSpecialMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataMCOMultiRowSpecialMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataMCOMultiRowSpecialMediator;
	}
	/** 
	 * @action id=originDataMCOMultiRowSpecial
	 */
	public String doOriginDataMCOMultiRowSpecialFetchAction() {
		try {
			resolveParams(getOriginDataMCOMultiRowSpecialParameters(),
					originDataMCOMultiRowSpecialArgNames, originDataMCOMultiRowSpecialArgValues,
					"originDataMCOMultiRowSpecial_params_cache");
			DataObject graph = getOriginDataMCOMultiRowSpecialMediator().getGraph(
					getOriginDataMCOMultiRowSpecialParameters());
			originDataMCOMultiRowSpecial = graph.getList(0);
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
			if (originDataMCOMultiRowSpecialMediator != null) {
				originDataMCOMultiRowSpecialMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOMultiRowSpecial.xml
	 * @methodEntry id=originDataMCOMultiRowSpecial/paramBean=originDataMCOMultiRowSpecial/action=originDataMCOMultiRowSpecial
	 * @action FILL
	 */
	public List getOriginDataMCOMultiRowSpecial() {
		if (originDataMCOMultiRowSpecial == null) {
			doOriginDataMCOMultiRowSpecialFetchAction();
		}
		return originDataMCOMultiRowSpecial;
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
	public MyData getMyDataInstance() {
		if (myDataInstance == null) {
			myDataInstance = new MyData();
		}
		return myDataInstance;
	}
	public void setMyDataInstance(MyData myDataInstance) {
		this.myDataInstance = myDataInstance;
	}
	public ArrayList getMyDataListInstance() {
		if (myDataListInstance == null) {
			
			InnerData inner1= new InnerData(new Integer(10), "cat");
			InnerData inner2 = new InnerData(new Integer(20), "mouse");
			InnerData inner3 = new InnerData(new Integer(30), "dog");
			InnerData inner4 = new InnerData(new Integer(30), "fox");
			InnerData inner5 = new InnerData(new Integer(30), "lion");
			InnerData inner6 = new InnerData(new Integer(30), "turtle");
			InnerData inner7 = new InnerData(new Integer(30), "elephant");
			
			List listInner_1 = new ArrayList();
			listInner_1.add(inner2);
			listInner_1.add(inner4);
			listInner_1.add(inner6);
			
			List listInner_2 = new ArrayList();
			listInner_2.add(inner1);
			listInner_2.add(inner3);
			listInner_2.add(inner5);
			
			List listInner_3 = new ArrayList();
			listInner_3.add(inner7);
			listInner_3.add(inner1);
			listInner_3.add(inner4);
			
			myDataListInstance = new ArrayList();
			myDataListInstance.add(new MyData("pato","gato", "trato", listInner_1));
			myDataListInstance.add(new MyData("meta","seta", "teta", listInner_2));
			myDataListInstance.add(new MyData("trozo","oso", "foso", listInner_3));
			
			
			
		}
		return myDataListInstance;
	}
	public void setMyDataListInstance(ArrayList myDataListInstance) {
		this.myDataListInstance = myDataListInstance;
	}
	protected HtmlDataTableEx getMyDataListInstance1() {
		if (myDataListInstance1 == null) {
			myDataListInstance1 = (HtmlDataTableEx) findComponentInRoot("myDataListInstance1");
		}
		return myDataListInstance1;
	}
	protected UIColumnEx getInnerList1column() {
		if (innerList1column == null) {
			innerList1column = (UIColumnEx) findComponentInRoot("innerList1column");
		}
		return innerList1column;
	}
	protected HtmlOutputText getInnerList1text() {
		if (innerList1text == null) {
			innerList1text = (HtmlOutputText) findComponentInRoot("innerList1text");
		}
		return innerList1text;
	}
	protected UIColumnEx getInnerList2column() {
		if (innerList2column == null) {
			innerList2column = (UIColumnEx) findComponentInRoot("innerList2column");
		}
		return innerList2column;
	}
	protected HtmlOutputText getInnerList2text() {
		if (innerList2text == null) {
			innerList2text = (HtmlOutputText) findComponentInRoot("innerList2text");
		}
		return innerList2text;
	}
	protected HtmlOutputText getPropOne1text() {
		if (propOne1text == null) {
			propOne1text = (HtmlOutputText) findComponentInRoot("propOne1text");
		}
		return propOne1text;
	}
	protected HtmlOutputText getPropTwo1text() {
		if (propTwo1text == null) {
			propTwo1text = (HtmlOutputText) findComponentInRoot("propTwo1text");
		}
		return propTwo1text;
	}
	protected HtmlOutputText getPropThree1text() {
		if (propThree1text == null) {
			propThree1text = (HtmlOutputText) findComponentInRoot("propThree1text");
		}
		return propThree1text;
	}
	protected HtmlDataTableEx getInnerList1() {
		if (innerList1 == null) {
			innerList1 = (HtmlDataTableEx) findComponentInRoot("innerList1");
		}
		return innerList1;
	}
	protected HtmlOutputText getInnerList2() {
		if (innerList2 == null) {
			innerList2 = (HtmlOutputText) findComponentInRoot("innerList2");
		}
		return innerList2;
	}
	protected UIColumnEx getPropOne1column() {
		if (propOne1column == null) {
			propOne1column = (UIColumnEx) findComponentInRoot("propOne1column");
		}
		return propOne1column;
	}
	protected HtmlOutputText getPropOne1() {
		if (propOne1 == null) {
			propOne1 = (HtmlOutputText) findComponentInRoot("propOne1");
		}
		return propOne1;
	}
	protected UIColumnEx getPropTwo1column() {
		if (propTwo1column == null) {
			propTwo1column = (UIColumnEx) findComponentInRoot("propTwo1column");
		}
		return propTwo1column;
	}
	protected HtmlOutputText getPropTwo1() {
		if (propTwo1 == null) {
			propTwo1 = (HtmlOutputText) findComponentInRoot("propTwo1");
		}
		return propTwo1;
	}
	protected UIColumnEx getPropThree1column() {
		if (propThree1column == null) {
			propThree1column = (UIColumnEx) findComponentInRoot("propThree1column");
		}
		return propThree1column;
	}
	protected HtmlOutputText getPropThree1() {
		if (propThree1 == null) {
			propThree1 = (HtmlOutputText) findComponentInRoot("propThree1");
		}
		return propThree1;
	}
	protected HtmlPanelBox getBox1() {
		if (box1 == null) {
			box1 = (HtmlPanelBox) findComponentInRoot("box1");
		}
		return box1;
	}
	protected HtmlPanelBox getBox2() {
		if (box2 == null) {
			box2 = (HtmlPanelBox) findComponentInRoot("box2");
		}
		return box2;
	}

}