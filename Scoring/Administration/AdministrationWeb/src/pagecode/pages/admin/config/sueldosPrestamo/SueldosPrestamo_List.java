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
import com.ibm.faces.component.UIColumnEx;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlJspPanel;
import javax.faces.component.html.HtmlSelectOneMenu;
import com.ibm.faces.component.html.HtmlPanelRowCategory;
import javax.faces.component.UISelectItems;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlPanelLayout;
import com.ibm.faces.component.html.HtmlPanelBox;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlGraphicImageEx;

/**
 * @author Administrator
 *
 */
public class SueldosPrestamo_List extends PageCodeBase {

	private static final String SDOConnection_name = "MotorCreditScoring";
	private ConnectionWrapper SDOConnectionWrapper;
	protected DataObject originDataNSPMultiRowParameters;
	protected JDBCMediator originDataNSPMultiRowMediator;
	private static final String originDataNSPMultiRow_metadataFileName = "/WEB-INF/wdo/originDataNSPMultiRow.xml";
	private static final int originDataNSPMultiRowTargetPageSize = -1;
	protected List originDataNSPMultiRow;
	protected HtmlDataTableEx tableEx2;
	protected HtmlOutputText text2;
	protected HtmlJspPanel jspPanel3;
	protected HtmlOutputText idClasificacionEmpresa1text;
	protected UIColumnEx idClasificacionEmpresa1column;
	protected HtmlOutputText idClasificacionEmpresa1;
	protected HtmlOutputText idAntiguedadLaboral1text;
	protected UIColumnEx idAntiguedadLaboral1column;
	protected HtmlOutputText idAntiguedadLaboral1;
	protected HtmlOutputText idNroSueldos1text;
	protected UIColumnEx idNroSueldos1column;
	protected HtmlSelectOneMenu idNroSueldos1;
	protected HtmlPanelRowCategory rowCategory1;
	protected HtmlOutputText text3;
	protected UIColumnEx columnEx1;
	protected DataObject originDataNSMultiRowParameters;
	protected JDBCMediator originDataNSMultiRowMediator;
	private static final String originDataNSMultiRow_metadataFileName = "/WEB-INF/wdo/originDataNSMultiRow.xml";
	protected static final String[] originDataNSMultiRowArgNames = {};
	protected static final String[] originDataNSMultiRowArgValues = {};
	private static final int originDataNSMultiRowTargetPageSize = -1;
	protected List originDataNSMultiRow;
	protected UISelectItems selectItems1;
	protected HtmlJspPanel centeJjspPanel;
	protected HtmlPanelLayout layout1C;
	protected HtmlJspPanel jspPanelUnder1CC;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlPanelBox box6;
	protected HtmlPanelBox box3;
	protected HtmlPanelGrid grid1;
	protected HtmlPanelBox box5;
	protected HtmlPanelGrid grid2;
	protected HtmlSelectOneMenu menu1;
	protected UIColumnEx idClasificacionEmpresa2column;
	protected HtmlOutputText idClasificacionEmpresa2text;
	protected HtmlOutputText idAntiguedadLaboral2text;
	protected HtmlOutputText idNroSueldos2text;
	protected HtmlDataTableEx originDataNSPMultiRow1;
	protected HtmlOutputText idClasificacionEmpresa2;
	protected UIColumnEx idAntiguedadLaboral2column;
	protected HtmlOutputText idAntiguedadLaboral2;
	protected UIColumnEx idNroSueldos2column;
	protected HtmlOutputText idNroSueldos2;
	protected HtmlForm form1;
	protected DataObject originDataALMultiRowParameters;
	protected JDBCMediator originDataALMultiRowMediator;
	private static final String originDataALMultiRow_metadataFileName = "/WEB-INF/wdo/originDataALMultiRow.xml";
	protected static final String[] originDataALMultiRowArgNames = {};
	protected static final String[] originDataALMultiRowArgValues = {};
	private static final int originDataALMultiRowTargetPageSize = -1;
	protected List originDataALMultiRow;
	protected DataObject originDataCEMultiRowParameters;
	protected JDBCMediator originDataCEMultiRowMediator;
	private static final String originDataCEMultiRow_metadataFileName = "/WEB-INF/wdo/originDataCEMultiRow.xml";
	protected static final String[] originDataCEMultiRowArgNames = {};
	protected static final String[] originDataCEMultiRowArgValues = {};
	private static final int originDataCEMultiRowTargetPageSize = -1;
	protected List originDataCEMultiRow;
	protected static final String[] originDataNSPMultiRowArgNames = {};
	protected static final String[] originDataNSPMultiRowArgValues = {};
	protected HtmlOutputText nombre1text;
	protected UIColumnEx nombre1column;
	protected HtmlOutputText nombre1;
	protected HtmlOutputText nombre2text;
	protected UIColumnEx nombre2column;
	protected HtmlOutputText nombre2;
	protected HtmlCommandExButton doOriginDataNSPMultiRowUpdateAction1;
	protected HtmlPanelBox box1;
	protected HtmlPanelBox box2;
	protected HtmlCommandExButton doOriginDataNSPMultiRowFetchAction1;
	protected HtmlOutputText text1;
	protected HtmlScriptCollector scriptCollector100;
	protected HtmlPanelLayout layout1;
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
	protected HtmlJspPanel jspPanel1;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlJspPanel jspPanel2;
	protected HtmlCommandExButton button1;
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
	 * @action id=originDataNSPMultiRow
	 */
	public String doOriginDataNSPMultiRowUpdateAction() {
		try {
			getOriginDataNSPMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataNSPMultiRow()));
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
			if (originDataNSPMultiRowMediator != null) {
				originDataNSPMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataNSPMultiRow
	 */
	public DataObject getOriginDataNSPMultiRowParameters() {
		if (originDataNSPMultiRowParameters == null) {
			try {
				originDataNSPMultiRowParameters = getOriginDataNSPMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataNSPMultiRowParameters;
	}
	protected JDBCMediator getOriginDataNSPMultiRowMediator() {
		if (originDataNSPMultiRowMediator == null) {
			try {
				originDataNSPMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataNSPMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataNSPMultiRow_metadataFileName),
						originDataNSPMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataNSPMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataNSPMultiRowMediator;
	}
	/** 
	 * @action id=originDataNSPMultiRow
	 */
	public String doOriginDataNSPMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataNSPMultiRowParameters(), originDataNSPMultiRowArgNames,
					originDataNSPMultiRowArgValues, "originDataNSPMultiRow_params_cache");
			DataObject graph = getOriginDataNSPMultiRowMediator().getGraph(
					getOriginDataNSPMultiRowParameters());
			originDataNSPMultiRow = graph.getList(0);
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
			if (originDataNSPMultiRowMediator != null) {
				originDataNSPMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataNSPMultiRow.xml
	 * @methodEntry id=originDataNSPMultiRow/paramBean=originDataNSPMultiRow/action=originDataNSPMultiRow
	 * @action FILL
	 */
	public List getOriginDataNSPMultiRow() {
		if (originDataNSPMultiRow == null) {
			doOriginDataNSPMultiRowFetchAction();
		}
		return originDataNSPMultiRow;
	}
	protected HtmlDataTableEx getTableEx2() {
		if (tableEx2 == null) {
			tableEx2 = (HtmlDataTableEx) findComponentInRoot("tableEx2");
		}
		return tableEx2;
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
	protected HtmlOutputText getIdClasificacionEmpresa1text() {
		if (idClasificacionEmpresa1text == null) {
			idClasificacionEmpresa1text = (HtmlOutputText) findComponentInRoot("idClasificacionEmpresa1text");
		}
		return idClasificacionEmpresa1text;
	}
	protected UIColumnEx getIdClasificacionEmpresa1column() {
		if (idClasificacionEmpresa1column == null) {
			idClasificacionEmpresa1column = (UIColumnEx) findComponentInRoot("idClasificacionEmpresa1column");
		}
		return idClasificacionEmpresa1column;
	}
	protected HtmlOutputText getIdClasificacionEmpresa1() {
		if (idClasificacionEmpresa1 == null) {
			idClasificacionEmpresa1 = (HtmlOutputText) findComponentInRoot("idClasificacionEmpresa1");
		}
		return idClasificacionEmpresa1;
	}
	protected HtmlOutputText getIdAntiguedadLaboral1text() {
		if (idAntiguedadLaboral1text == null) {
			idAntiguedadLaboral1text = (HtmlOutputText) findComponentInRoot("idAntiguedadLaboral1text");
		}
		return idAntiguedadLaboral1text;
	}
	protected UIColumnEx getIdAntiguedadLaboral1column() {
		if (idAntiguedadLaboral1column == null) {
			idAntiguedadLaboral1column = (UIColumnEx) findComponentInRoot("idAntiguedadLaboral1column");
		}
		return idAntiguedadLaboral1column;
	}
	protected HtmlOutputText getIdAntiguedadLaboral1() {
		if (idAntiguedadLaboral1 == null) {
			idAntiguedadLaboral1 = (HtmlOutputText) findComponentInRoot("idAntiguedadLaboral1");
		}
		return idAntiguedadLaboral1;
	}
	protected HtmlOutputText getIdNroSueldos1text() {
		if (idNroSueldos1text == null) {
			idNroSueldos1text = (HtmlOutputText) findComponentInRoot("idNroSueldos1text");
		}
		return idNroSueldos1text;
	}
	protected UIColumnEx getIdNroSueldos1column() {
		if (idNroSueldos1column == null) {
			idNroSueldos1column = (UIColumnEx) findComponentInRoot("idNroSueldos1column");
		}
		return idNroSueldos1column;
	}
	protected HtmlSelectOneMenu getIdNroSueldos1() {
		if (idNroSueldos1 == null) {
			idNroSueldos1 = (HtmlSelectOneMenu) findComponentInRoot("idNroSueldos1");
		}
		return idNroSueldos1;
	}
	protected HtmlPanelRowCategory getRowCategory1() {
		if (rowCategory1 == null) {
			rowCategory1 = (HtmlPanelRowCategory) findComponentInRoot("rowCategory1");
		}
		return rowCategory1;
	}
	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}
	protected UIColumnEx getColumnEx1() {
		if (columnEx1 == null) {
			columnEx1 = (UIColumnEx) findComponentInRoot("columnEx1");
		}
		return columnEx1;
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
	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
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
	protected HtmlPanelBox getBox6() {
		if (box6 == null) {
			box6 = (HtmlPanelBox) findComponentInRoot("box6");
		}
		return box6;
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
	protected HtmlSelectOneMenu getMenu1() {
		if (menu1 == null) {
			menu1 = (HtmlSelectOneMenu) findComponentInRoot("menu1");
		}
		return menu1;
	}
	protected UIColumnEx getIdClasificacionEmpresa2column() {
		if (idClasificacionEmpresa2column == null) {
			idClasificacionEmpresa2column = (UIColumnEx) findComponentInRoot("idClasificacionEmpresa2column");
		}
		return idClasificacionEmpresa2column;
	}
	protected HtmlOutputText getIdClasificacionEmpresa2text() {
		if (idClasificacionEmpresa2text == null) {
			idClasificacionEmpresa2text = (HtmlOutputText) findComponentInRoot("idClasificacionEmpresa2text");
		}
		return idClasificacionEmpresa2text;
	}
	protected HtmlOutputText getIdAntiguedadLaboral2text() {
		if (idAntiguedadLaboral2text == null) {
			idAntiguedadLaboral2text = (HtmlOutputText) findComponentInRoot("idAntiguedadLaboral2text");
		}
		return idAntiguedadLaboral2text;
	}
	protected HtmlOutputText getIdNroSueldos2text() {
		if (idNroSueldos2text == null) {
			idNroSueldos2text = (HtmlOutputText) findComponentInRoot("idNroSueldos2text");
		}
		return idNroSueldos2text;
	}
	protected HtmlDataTableEx getOriginDataNSPMultiRow1() {
		if (originDataNSPMultiRow1 == null) {
			originDataNSPMultiRow1 = (HtmlDataTableEx) findComponentInRoot("originDataNSPMultiRow1");
		}
		return originDataNSPMultiRow1;
	}
	protected HtmlOutputText getIdClasificacionEmpresa2() {
		if (idClasificacionEmpresa2 == null) {
			idClasificacionEmpresa2 = (HtmlOutputText) findComponentInRoot("idClasificacionEmpresa2");
		}
		return idClasificacionEmpresa2;
	}
	protected UIColumnEx getIdAntiguedadLaboral2column() {
		if (idAntiguedadLaboral2column == null) {
			idAntiguedadLaboral2column = (UIColumnEx) findComponentInRoot("idAntiguedadLaboral2column");
		}
		return idAntiguedadLaboral2column;
	}
	protected HtmlOutputText getIdAntiguedadLaboral2() {
		if (idAntiguedadLaboral2 == null) {
			idAntiguedadLaboral2 = (HtmlOutputText) findComponentInRoot("idAntiguedadLaboral2");
		}
		return idAntiguedadLaboral2;
	}
	protected UIColumnEx getIdNroSueldos2column() {
		if (idNroSueldos2column == null) {
			idNroSueldos2column = (UIColumnEx) findComponentInRoot("idNroSueldos2column");
		}
		return idNroSueldos2column;
	}
	protected HtmlOutputText getIdNroSueldos2() {
		if (idNroSueldos2 == null) {
			idNroSueldos2 = (HtmlOutputText) findComponentInRoot("idNroSueldos2");
		}
		return idNroSueldos2;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	/** 
	 * @action id=originDataALMultiRow
	 */
	public String doOriginDataALMultiRowUpdateAction() {
		try {
			getOriginDataALMultiRowMediator().applyChanges(
					getRootDataObject(getOriginDataALMultiRow()));
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
			if (originDataALMultiRowMediator != null) {
				originDataALMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @paramBean id=originDataALMultiRow
	 */
	public DataObject getOriginDataALMultiRowParameters() {
		if (originDataALMultiRowParameters == null) {
			try {
				originDataALMultiRowParameters = getOriginDataALMultiRowMediator()
						.getParameterDataObject();
			} catch (MediatorException e) {
				logException(e);
			}
		}
		return originDataALMultiRowParameters;
	}
	protected JDBCMediator getOriginDataALMultiRowMediator() {
		if (originDataALMultiRowMediator == null) {
			try {
				originDataALMultiRowMediator = JDBCMediatorFactory.soleInstance.createMediator(
						getResourceInputStream(originDataALMultiRow_metadataFileName),
						getSDOConnectionWrapper());
				initSchema(getRealPath(originDataALMultiRow_metadataFileName),
						originDataALMultiRowMediator.getSchema());
			} catch (Throwable e) {
				logException(e);
			}
		} else {
			originDataALMultiRowMediator.setConnectionWrapper(getSDOConnectionWrapper());
		}
		return originDataALMultiRowMediator;
	}
	/** 
	 * @action id=originDataALMultiRow
	 */
	public String doOriginDataALMultiRowFetchAction() {
		try {
			resolveParams(getOriginDataALMultiRowParameters(), originDataALMultiRowArgNames,
					originDataALMultiRowArgValues, "originDataALMultiRow_params_cache");
			DataObject graph = getOriginDataALMultiRowMediator().getGraph(
					getOriginDataALMultiRowParameters());
			originDataALMultiRow = graph.getList(0);
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
			if (originDataALMultiRowMediator != null) {
				originDataALMultiRowMediator.setConnectionWrapper(null);
			}
		}
		return "";
	}
	/** 
	 * @mediatorFactory com.ibm.etools.sdo.rdb.datahandlers.RelationalDataFactory
	 * @mediatorProperties metadataFileName=/WEB-INF/wdo/originDataALMultiRow.xml
	 * @methodEntry id=originDataALMultiRow/paramBean=originDataALMultiRow/action=originDataALMultiRow
	 * @action FILL
	 */
	public List getOriginDataALMultiRow() {
		if (originDataALMultiRow == null) {
			doOriginDataALMultiRowFetchAction();
		}
		return originDataALMultiRow;
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
	protected HtmlOutputText getNombre1text() {
		if (nombre1text == null) {
			nombre1text = (HtmlOutputText) findComponentInRoot("nombre1text");
		}
		return nombre1text;
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
	protected HtmlOutputText getNombre2text() {
		if (nombre2text == null) {
			nombre2text = (HtmlOutputText) findComponentInRoot("nombre2text");
		}
		return nombre2text;
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
	protected HtmlCommandExButton getDoOriginDataNSPMultiRowUpdateAction1() {
		if (doOriginDataNSPMultiRowUpdateAction1 == null) {
			doOriginDataNSPMultiRowUpdateAction1 = (HtmlCommandExButton) findComponentInRoot("doOriginDataNSPMultiRowUpdateAction1");
		}
		return doOriginDataNSPMultiRowUpdateAction1;
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
	protected HtmlCommandExButton getDoOriginDataNSPMultiRowFetchAction1() {
		if (doOriginDataNSPMultiRowFetchAction1 == null) {
			doOriginDataNSPMultiRowFetchAction1 = (HtmlCommandExButton) findComponentInRoot("doOriginDataNSPMultiRowFetchAction1");
		}
		return doOriginDataNSPMultiRowFetchAction1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
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
	protected HtmlJspPanel getJspPanel1() {
		if (jspPanel1 == null) {
			jspPanel1 = (HtmlJspPanel) findComponentInRoot("jspPanel1");
		}
		return jspPanel1;
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
	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

}