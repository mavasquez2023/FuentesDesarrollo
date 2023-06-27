

/*
 * @(#) DocumentType.java    1.0 22-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


/**
 * Represents the set of required elements to create a {@link PDFProcess}
 * instance executable under {@link PDFService} to produce and to consume
 * PDF documents of a same type.
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
 *            <TD> 22-04-2008 </TD>
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
public class DocumentType {

	private String name;
	private String description;
	private String system = "<unknown>";
	private String implementation;
	private String tag;
	private String templateDir;
	private String templateName;
	private Class documentModelClass;
	private int pdfReferenceSize;

	private Class documentModelProviderClass;
	private Class pdfProducerClass;
	private Class pdfConsumerClass;

	private int producerRequestQueueCapacity;
	private int consumerRequestQueueCapacity;

	/**
	 * Constructs an empty document type instance.
	 */
	public DocumentType() {
	}

	/**
	 * Sets document type name.
	 * 
	 * @param name Document type name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets document type name.
	 * 
	 * @return Document type name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets document type description.
	 * 
	 * @param description Document type description.
	 * 
	 * @see #getDescription()
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets document type description.
	 * 
	 * @return Document type description.
	 * 
	 * @see #setDescription(String)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets document type source system.
	 * 
	 * <p> A document type must be associated to a owner source system
	 * to support access control checks when a user tries to execute a
	 * {@link PDFProcess} with it.
	 * </p>
	 * 
	 * @param system Document type source system.
	 * 
	 * @see #getSystem()
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * Gets document type source system.
	 * 
	 * @return Document type source system.
	 * 
	 * @see #setSystem(String)
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * Sets document type implementation name.
	 * 
	 * <p> Name identifies to one of many possible implementations
	 * to be used by PDF Process's components (Usually <b>DMP</b>).
	 * </p>
	 * 
	 * @param name Document type implementation name.
	 * 
	 * @see #getImplementation()
	 */
	public void setImplementation(String name) {
		implementation = name;
	}

	/**
	 * Gets document type implementation name.
	 * 
	 * @return Document type implementation name.
	 * 
	 * @see #setImplementation(String)
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * Sets document type tag.
	 * 
	 * @param tag Document type tag.
	 * 
	 * @see #getTag()
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets document type tag.
	 * 
	 * @return Document type tag.
	 * 
	 * @see #setTag(String)
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets document type template directory.
	 * 
	 * <p> Defines the directory where is located the associated
	 * {@link cl.araucana.fpg.PDFTemplate} instance to produce PDF documents.
	 * </p>
	 * 
	 * @param templateDir Document type template directory.
	 * 
	 * @see #getTemplateDir()
	 * @see #getTemplateName()
	 */
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	/**
	 * Gets document type template directory.
	 * 
	 * @return Socument type template directory.
	 * 
	 * @see #setTemplateDir(String)
	 * @see #setTemplateName(String)
	 */
	public String getTemplateDir() {
		return templateDir;
	}

	/**
	 * Sets document type template name.
	 * 
	 * <p> Defines the {@link cl.araucana.fpg.PDFTemplate} instance name
	 * to produce PDF documents.
	 * </p>
	 *  
	 * @param templateName Document type template name.
	 * 
	 * @see #getTemplateDir()
	 * @see #getTemplateName()
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Gets document type template name.
	 * 
	 * @return Document type template name.
	 * 
	 * @see #setTemplateDir(String)
	 * @see #setTemplateName(String)
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Sets document model class.
	 * 
	 * @param clazz Document model class.
	 * 
	 * @see #setDocumentModelClass(Class)
	 */
	public void setDocumentModelClass(Class clazz) {
		documentModelClass = clazz;
	}

	/**
	 * Gets document model class.
	 * 
	 * @return Document model class.
	 * 
	 * @see #setDocumentModelClass(Class)
	 */
	public Class getDocumentModelClass() {
		return documentModelClass;
	}

	/**
	 * Sets document model provider class.
	 * 
	 * @param clazz Document model provider class.
	 * 
	 * @see #getDocumentModelProviderClass()
	 */	
	public void setDocumentModelProviderClass(Class clazz) {
		documentModelProviderClass = clazz;
	}

	/**
	 * Gets document model provider class.
	 * 
	 * @return Document model provider class.
	 * 
	 * @see #setDocumentModelClass(Class)
	 */	
	public Class getDocumentModelProviderClass() {
		return documentModelProviderClass;
	}

	/**
	 * Sets PDF producer class.
	 * 
	 * @param clazz PDF producer class.
	 * 
	 * @see #getPdfProducerClass()
	 */	
	public void setPdfProducerClass(Class clazz) {
		pdfProducerClass = clazz;
	}

	/**
	 * Gets PDF producer class.
	 * 
	 * @return PDF producer class.
	 * 
	 * @see #setPdfProducerClass(Class)
	 */
	public Class getPdfProducerClass() {
		return pdfProducerClass;
	}

	/**
	 * Sets PDF consumer class.
	 * 
	 * @param clazz PDF consumer class.
	 * 
	 * @see #getPdfConsumerClass()
	 */	
	public void setPdfConsumerClass(Class clazz) {
		pdfConsumerClass = clazz;
	}

	/**
	 * Gets PDF consumer class.
	 * 
	 * @return PDF consumer class.
	 * 
	 * @see #setPdfConsumerClass(Class)
	 */
	public Class getPdfConsumerClass() {
		return pdfConsumerClass;
	}

	/**
	 * Sets PDF documents reference size in bytes.
	 * 
	 * <p> It's used to optimize memory allocation to produce PDF documents.
	 * Final size for any PDF document can be lower or great than this size
	 * of course.
	 * </p>
	 *  
	 * @param size PDF documents reference size.
	 * 
	 * @see #getPdfReferenceSize()
	 */
	public void setPdfReferenceSize(int size) {
		pdfReferenceSize = size;
	}

	/**
	 * Gets PDF documents reference size in bytes.
	 * 
	 * @return PDF documents reference size.
	 */
	public int getPdfReferenceSize() {
		return pdfReferenceSize;
	}

	/**
	 * Sets PDF producer requests queue's capacity.
	 * 
	 * <p> Define the maximum number of queued production requests.
	 * </p>
	 * 
	 * @param capacity PDF producer queue capacity.
	 * 
	 * @see #getProducerRequestQueueCapacity()
	 */
	public void setProducerRequestQueueCapacity(int capacity) {
		producerRequestQueueCapacity = capacity;
	}

	/**
	 * Gets PDF producer requests queue's capacity.
	 * 
	 * @return PDF producer queue capacity.
	 * 
	 * @see #setProducerRequestQueueCapacity(int)
	 */
	public int getProducerRequestQueueCapacity() {
		return producerRequestQueueCapacity;
	}

	/**
	 * Sets PDF consumer requests queue's capacity.
	 * 
	 * <p> Define the maximum number of queued consume requests.
	 * </p>
	 * 
	 * @param capacity PDF consumer queue capacity.
	 * 
	 * @see #getConsumerRequestQueueCapacity()
	 */	
	public void setConsumerRequestQueueCapacity(int capacity) {
		consumerRequestQueueCapacity = capacity;
	}

	/**
	 * Gets PDF consumer requests queue's capacity.
	 * 
	 * @return PDF consumer queue capacity.
	 * 
	 * @see #setConsumerRequestQueueCapacity(int)
	 */	
	public int getConsumerRequestQueueCapacity() {
		return consumerRequestQueueCapacity;
	}

	/**
	 * Returns a string representation of the index as multiple lines
	 * one per each property indicating its name and value.
	 * 
	 * @return String representation.
	 */		
	public String toString() {
		return
				  "name                            = " + getName() + "\n"
				+ "description                     = " + getDescription() + "\n"
				+ "system                          = " + getSystem() + "\n"
				+ "implementation                  = "
				+ getImplementation() + "\n"
				+ "tag                             = " + getTag() + "\n"
				+ "PDF template dir                = " + getTemplateDir() + "\n"
				+ "PDF template name               = "
				+ getTemplateName() + "\n"
				+ "document model class            = "
				+ getDocumentModelClass().getName()	+ "\n"
				+ "document model provider class   = "
				+ getDocumentModelProviderClass().getName() + "\n"
				+ "PDF producer class              = "
				+ getPdfProducerClass().getName() + "\n"
				+ "PDF consumer class              = "
				+ getPdfConsumerClass().getName() + "\n"
				+ "PDF reference size              = "
				+ getPdfReferenceSize()	+ " KB\n"
				+ "producer request queue capacity = "
				+ getProducerRequestQueueCapacity() + "\n"
				+ "consumer request queue capacity = "
				+ getConsumerRequestQueueCapacity() + "\n";
	}
}
