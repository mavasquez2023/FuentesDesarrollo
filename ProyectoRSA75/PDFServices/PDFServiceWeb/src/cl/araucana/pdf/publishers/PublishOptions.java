

/*
 * @(#) PublishOptions.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import java.io.Serializable;


/**
 * This class encapsulates the configuration of a {@link PDFPublisher} plus a
 * set of general options to control its operations.
 *
 * <BR>
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 02-06-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class PublishOptions implements Serializable {

	private static final long serialVersionUID = -4550029264998190893L;
	
	/**
	 *  <b>Replace</b> replace mode.
	 */
	public static final int MODE_REPLACE       = 0;
	
	/**
	 * <b>No replace</b> replace mode.
	 */
	public static final int MODE_NO_REPLACE    = 1;
	
	/**
	 * <b>Unpublish all in Publication Scope Before</b> replace mode.
	 */
	public static final int MODE_UNPUBLISH_ALL = 2;

	/**
	 *  Insert publication strategy. 
	 */
	public static final int STRATEGY_INSERT = 0;
	
	/**
	 * Update publication strategy. 
	 */
	public static final int STRATEGY_UPDATE = 1;

	private static final String[] replaceModeNames = {
		"replace",
		"noreplace",
		"unpublishAll"
	};

	private static final String[] strategyNames = {
		"insert",
		"update"
	};

	private Publisher publisher;
	private String logID;

	private boolean batchMode;
	private boolean partitioned;
	private boolean compressed;
	private boolean logged;
	private int replaceMode;
	private int strategy;
	private String remark;
	private boolean readOnly = false;

	/**
	 * Constructs an empty instance.
	 */
	public PublishOptions() {
	}

	/**
	 * Sets publisher´s configuration to be used.
	 * 
	 * @param publisher Publisher´s configuration.
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets publisher´s configuration.
	 * 
	 * @return Publisher´s configuration.
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * Sets an identifier to recognize logged events that will generate this
	 * publisher.
	 *  
	 * @param id Log Identifier.
	 */
	public void setLogID(String id) {
		logID = id;
	}

	/**
	 * Gets log identifier of this publisher.
	 * 
	 * @return Log Identifier.
	 */
	public String getLogID() {
		return logID;
	}

	/**
	 * Sets batch mode to this publisher.
	 * 
	 * @param enabled Batch mode. <code>true</code> enables it,
	 *                <code>false</code> otherwise. 
	 */
	public void setBatchMode(boolean enabled) {
		batchMode = enabled;
	}

	/**
	 * Indicates if this publisher uses batch mode or not.
	 * 
	 * @return <code>true</code> if publisher uses batch mode, 
	 *         <code>false</code> otherwise.
	 */
	public boolean isBatchMode() {
		return batchMode;
	}

	/**
	 * Sets that partitioned PDF documents can be published.
	 * 
	 * @param enabled Partitioned PDF documents publication. <code>true</code>
	 *                enables it, <code>false</code> otherwise.
	 */
	public void setPartitioned(boolean enabled) {
		partitioned = enabled;
	}

	/**
	 * Indicates if this publisher publishes partitioned PDF documents or not.
	 * 
	 * @return <code>true</code> if publisher publishes partitioned PDF
	 *         documents, <code>false</code> otherwise.
	 */
	public boolean isPartitioned() {
		return partitioned;
	}

	/**
	 * Indicates if this publisher compresses nonpartitioned PDF documents or
	 * not.
	 * 
	 * @return <code>true</code> if publisher compresses nonpartitioned PDF
	 *         documents, <code>false</code> otherwise.
	 */	
	public void setCompressed(boolean enabled) {
		compressed = enabled;
	}

	/**
	 * Indicates if this publisher compresses nonpartitioned PDF documents or
	 * not.
	 * 
	 * @return <code>true</code> if publisher compresses nonpartitioned PDF
	 *         documents, <code>false</code> otherwise.
	 */	
	public boolean isCompressed() {
		return compressed;
	}

	/**
	 * Sets control flag to log special events.
	 * 
	 * @param enabled Special logged events flag. <code>true</code> enables it,
	 *                <code>false</code> otherwise.
	 */
	public void setLogged(boolean enabled) {
		logged = enabled;
	}

	/**
	 * Indicates if this publisher logs special events or not.
	 * 
	 * @return <code>true</code> if publisher logs special events,
	 *         <code>false</code> otherwise.
	 */	
	public boolean isLogged() {
		return logged;
	}

	/**
	 * Sets replace mode code to be used when a PDF document to be published
	 * exists previously.
	 * 
	 * @param replaceMode Replace mode code.
	 */
	public void setReplaceMode(int replaceMode) {
		checkReplaceMode(replaceMode);

		this.replaceMode = replaceMode;
	}

	/**
	 * Gets replace mode code.
	 * 
	 * @return Replace mode code.
	 */
	public int getReplaceMode() {
		return replaceMode;
	}

	/**
	 * Sets replace mode to be used when a PDF document to be published exists
	 * previously from the specified <code>name</code>.
	 * 
	 * @param name Replace mode name.
	 */
	public void setReplaceModeName(String name) {
		for (int mode = 0; mode < replaceModeNames.length; mode++) {
			if (replaceModeNames[mode].equals(name)) {
				replaceMode = mode;
			}
		}

		throw new IllegalArgumentException(
				"Unknown replace mode '" + name + "'.");
	}

	/**
	 * Gets replace mode name.
	 * 
	 * @return Replace mode name.
	 */	
	public String getReplaceModeName() {
		return replaceModeNames[replaceMode];
	}

	/**
	 * Sets primary strategy code to be used in publication operations.
	 * 
	 * @param strategy Strategy code.
	 */
	public void setStrategy(int strategy) {
		checkStrategy(strategy);

		this.strategy = strategy;
	}

	/**
	 * Gets primary strategy code.
	 * 
	 * @return Strategy code.
	 */
	public int getStrategy() {
		return strategy;
	}

	/**
	 * Sets primary strategy to be used in publication operations from the
	 * specified <code>name</code>.
	 * 
	 * @param name Strategy name.
	 */	
	public void setStrategyName(String name) {
		for (int strategy = 0; strategy < strategyNames.length;
				strategy++) {

			if (strategyNames[strategy].equals(name)) {
				this.strategy = strategy;
			}
		}

		throw new IllegalArgumentException(
				"Unknown replace mode '" + name + "'.");
	}

	/**
	 * Gets primary strategy name.
	 * 
	 * @return Strategy name.
	 */
	public String getStrategyName() {
		return strategyNames[strategy];
	}

	/**
	 * Sets remark or comment. It can be used like description too.
	 * 
	 * @param remark Remark or comment.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Gets remark.
	 * 
	 * @return remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets if publisher will do retrieval operations only.
	 * 
	 * @param enabled Read only control flag. <code>true</code> enables it,
	 *                <code>false</code> otherwise.
	 */
	public void setReadOnly(boolean enabled) {
		readOnly = enabled;
	}

	/**
	 * Indicates if this publisher will operate in read only mode.
	 * 
	 * @return <code>true</code> if publisher will operate in read only mode,
	 *         <code>false</code> otherwise.
	 */	
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Returns a string representation of the publisher name and options as
	 * multiple lines one per each options indicating its name and value.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return
				  "publisher=" + publisher.getName() + " "
				+ "logID=" + logID + " "
				+ "batchMode=" + batchMode + " "
				+ "partitioned=" + partitioned + " "
				+ "compressed=" + compressed + " "
				+ "logged=" + logged + " "
				+ "replaceMode=" + getReplaceModeName() + " "
				+ "strategy=" + getStrategyName() + " "
				+ "remark=" + remark;
	}

	/**
	 * Gets replace mode name from its <code>replaceMode</code> code.
	 * 
	 * @param replaceMode Replace mode code.
	 * 
	 * @return Replace mode name.
	 * 
	 * @throws IllegalArgumentException If replace mode code is unknown. 
	 */
	public static String getReplaceModeName(int replaceMode) {
		checkReplaceMode(replaceMode);

		return replaceModeNames[replaceMode];
	}

	/**
	 * Gets replace mode code from its <code>modeName</code> name.
	 * 
	 * @param modeName Replace mode name.
	 * 
	 * @return Replace mode code or <code>-1</code> if name is unknown.
	 */
	public static int getReplaceMode(String modeName) {
		for (int i = 0; i < replaceModeNames.length; i++) {
			if (replaceModeNames[i].equals(modeName)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Gets strategy name from its <code>strategy</code> code.
	 * 
	 * @param strategy Strategy code.
	 * 
	 * @return Strategy name.
	 * 
	 * @throws IllegalArgumentException If strategy code is unknown.
	 */
	public static String getStrategyName(int strategy) {
		checkStrategy(strategy);

		return strategyNames[strategy];
	}

	/**
	 * Gets strategy code from its <code>strategyName</code> name.
	 * 
	 * @param strategyName Strategy name.
	 * 
	 * @return Strategy code or <code>-1</code> if name is unknown.
	 */
	public static int getStrategy(String strategyName) {
		for (int i = 0; i < strategyNames.length; i++) {
			if (strategyNames[i].equals(strategyName)) {
				return i;
			}
		}

		return -1;
	}

	private static void checkReplaceMode(int replaceMode) {
		if (replaceMode < MODE_REPLACE || replaceMode > MODE_UNPUBLISH_ALL) {
			throw new IllegalArgumentException(
					"Unknown replace mode '" + replaceMode + "'.");
		}
	}

	private static void checkStrategy(int strategy) {
		if (strategy < STRATEGY_INSERT || strategy > STRATEGY_UPDATE) {
			throw new IllegalArgumentException(
					"Unknown publish strategy '" + strategy + "'.");
		}
	}
}
