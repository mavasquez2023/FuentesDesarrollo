/**
 * 
 */
package pagecode.pages.admin.config.condicionesOtorgamiento;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;

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
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.UIColumnEx;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlRequestLink;
import java.util.ArrayList;
import javax.faces.component.UIParameter;

/**
 * @author Administrator
 *
 */
public class CO_ListComposite extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataMCOMultiRowCompositeParameters;
	protected JDBCMediator originDataMCOMultiRowCompositeMediator;
	private static final String originDataMCOMultiRowComposite_metadataFileName = "/WEB-INF/wdo/originDataMCOMultiRowComposite.xml";
	private static final int originDataMCOMultiRowCompositeTargetPageSize = -1;
	protected List originDataMCOMultiRowComposite;
	protected HtmlDataTableEx originDataMCOMultiRowComposite1;
	protected HtmlOutputText nombre1text;
	protected HtmlOutputText nombre2text;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected UIColumnEx nombre2column;
	protected HtmlOutputText nombre2;
	protected HtmlOutputText descripcion1text;
	protected UIColumnEx descripcion1column;
	protected HtmlOutputText descripcion1;
	protected static final String[] originDataMCOMultiRowCompositeArgNames = {};
	protected static final String[] originDataMCOMultiRowCompositeArgValues = {};
	protected HtmlOutputText text2;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText text3;
	protected HtmlRequestLink link1;
	protected DataObject originDataCOMultiRowParameters;
	protected JDBCMediator originDataCOMultiRowMediator;
	private static final String originDataCOMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCOMultiRow.xml";
	protected static final String[] originDataCOMultiRowArgNames = {};
	protected static final String[] originDataCOMultiRowArgValues = {};
	private static final int originDataCOMultiRowTargetPageSize = -1;
	protected List originDataCOMultiRow;
	protected ArrayList arrayIDCondiciones;
	protected UIParameter param1;
	protected UIParameter param2;
	protected UIParameter param3;
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
	 * @action id=originDataMCOMultiRowComposite
	 */
	public String doOriginDataMCOMultiRowCompositeUpdateAction() {
		try {
			getOriginDataMCOMultiRowCompositeMediator().applyChanges(
					getRootDataObject(getOriginDataMCOMultiRowComposite()));
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
			if (originDataMCOMultiRowCompositeMediator != null) {
				originDataMCOMultiRowCompositeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataMCOMultiRowComposite
	 */
	public DataObject getOriginDataMCOMultiRowCompositeParameters() {
		if (originDataMCOMultiRowCompositeParameters == null) {
			try {
				originDataMCOMultiRowCompositeParameters = getOriginDataMCOMultiRowCompositeMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataMCOMultiRowCompositeParameters;
	}
	protected JDBCMediator getOriginDataMCOMultiRowCompositeMediator() {
		if (originDataMCOMultiRowCompositeMediator == null) {
			try {
				originDataMCOMultiRowCompositeMediator = JDBCMediatorFactory.soleInstance
						.createMediator(
								getResourceInputStream(originDataMCOMultiRowComposite_metadataFileName),
								getSDOConnectionWrapper());
				initSchema(getRealPath(originDataMCOMultiRowComposite_metadataFileName),
						originDataMCOMultiRowCompositeMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataMCOMultiRowCompositeMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataMCOMultiRowCompositeMediator;
	}
	/** 
	 * @action id=originDataMCOMultiRowComposite
	 */
	public String doOriginDataMCOMultiRowCompositeFetchAction() {
		try {
			resolveParams(getOriginDataMCOMultiRowCompositeParameters(),
					originDataMCOMultiRowCompositeArgNames,
					originDataMCOMultiRowCompositeArgValues,
					"originDataMCOMultiRowComposite_params_cache");
			DataObject graph = getOriginDataMCOMultiRowCompositeMediator().getGraph(
					getOriginDataMCOMultiRowCompositeParameters());
			originDataMCOMultiRowComposite = graph.getList(0);
			
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
			if (originDataMCOMultiRowCompositeMediator != null) {
				originDataMCOMultiRowCompositeMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataMCOMultiRowComposite.xml
	 * @methodEntry id=originDataMCOMultiRowComposite/paramBean=originDataMCOMultiRowComposite/action=originDataMCOMultiRowComposite
	 * @action FILL
	 */
	public List getOriginDataMCOMultiRowComposite() {
		if (originDataMCOMultiRowComposite == null) {
			doOriginDataMCOMultiRowCompositeFetchAction();
		}
		return originDataMCOMultiRowComposite;
	}
	protected HtmlDataTableEx getOriginDataMCOMultiRowComposite1() {
		if (originDataMCOMultiRowComposite1 == null) {
			originDataMCOMultiRowComposite1 = (HtmlDataTableEx) findComponentInRoot("originDataMCOMultiRowComposite1");
		}
		return originDataMCOMultiRowComposite1;
	}
	protected HtmlOutputText getNombre1text() {
		if (nombre1text == null) {
			nombre1text = (HtmlOutputText) findComponentInRoot("nombre1text");
		}
		return nombre1text;
	}
	protected HtmlOutputText getNombre2text() {
		if (nombre2text == null) {
			nombre2text = (HtmlOutputText) findComponentInRoot("nombre2text");
		}
		return nombre2text;
	}
	protected UIColumnEx getNombre1column() {
		if (nombre1column == null) {
			nombre1column = (UIColumnEx) findComponentInRoot("nombre1column");
		}
		return nombre1column;
	}
	protected HtmlOutputText getNombre1() {
		if (nombre1 == null) {
			nombre1 = (HtmlOutputText) findComponentInRoot("nombre1");
		}
		return nombre1;
	}
	protected UIColumnEx getNombre2column() {
		if (nombre2column == null) {
			nombre2column = (UIColumnEx) findComponentInRoot("nombre2column");
		}
		return nombre2column;
	}
	protected HtmlOutputText getNombre2() {
		if (nombre2 == null) {
			nombre2 = (HtmlOutputText) findComponentInRoot("nombre2");
		}
		return nombre2;
	}
	protected HtmlOutputText getDescripcion1text() {
		if (descripcion1text == null) {
			descripcion1text = (HtmlOutputText) findComponentInRoot("descripcion1text");
		}
		return descripcion1text;
	}
	protected UIColumnEx getDescripcion1column() {
		if (descripcion1column == null) {
			descripcion1column = (UIColumnEx) findComponentInRoot("descripcion1column");
		}
		return descripcion1column;
	}
	protected HtmlOutputText getDescripcion1() {
		if (descripcion1 == null) {
			descripcion1 = (HtmlOutputText) findComponentInRoot("descripcion1");
		}
		return descripcion1;
	}
	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}
	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}
	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}
	protected HtmlRequestLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlRequestLink) findComponentInRoot("link1");
		}
		return link1;
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
	public ArrayList getArrayIDCondiciones() {
		if (arrayIDCondiciones == null) {
			arrayIDCondiciones = new ArrayList();
		}
		return arrayIDCondiciones;
	}
	public void setArrayIDCondiciones(ArrayList arrayIDCondiciones) {
		this.arrayIDCondiciones = arrayIDCondiciones;
	}
	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		Object idRiesgoExterno = getRequestParam().get("idTRG");		
		getSessionScope().put("_param_idTRG",idRiesgoExterno);
		Object idTipoRenta = getRequestParam().get("idTRT");
		getSessionScope().put("_param_idTRT", idTipoRenta);
		Object idPerfilRiesgo = getRequestParam().get("idPR");
		getSessionScope().put("_param_idPR", idPerfilRiesgo);		
		return "config";
	}
	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}
	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}
	protected UIParameter getParam3() {
		if (param3 == null) {
			param3 = (UIParameter) findComponentInRoot("param3");
		}
		return param3;
	}

}