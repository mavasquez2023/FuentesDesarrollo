

/*
 * @(#) PDFTemplate.java    1.0 05-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import java.util.zip.Deflater;

import cl.araucana.core.util.ByteArray;
import cl.araucana.core.util.FileUtils;
import cl.araucana.core.util.Padder;


/**
 * Structured collection of PDF objects persisted in a files
 * hierarchy. A PDF template can be derived from a target PDF document using
 * {@link cl.araucana.fpg.tools.FPGShell} specialized shell.
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
 *            <TD> 05-03-2008 </TD>
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
public class PDFTemplate {

	/**
	 * <u>Normal</u> or original PDF Object file extension.
	 */
	public static final int EXTENSION_NORMAL   = 0;

	/**
	 * <u>Inflated</u> or uncompressed PDF Object file extension.
	 */
	public static final int EXTENSION_INFLATED = 1;

	/**
	 * FPG <u>coded</u> PDF Object file extension.
	 */
	public static final int EXTENSION_CODED    = 2;

	/**
	 * FPG <u>compiled</u> PDF Object file extension.
	 */
	public static final int EXTENSION_COMPILED = 3;

	/**
	 * File extension names for PDF objects.
	 */
	public static final String[] extensionNames = {
		"normal",
		"inflated",
		"coded",
		"compiled"
	};

	/**
	 * File extension names for PDF objects.
	 */
	public static final String[] fileExtensions = {
		"",
		".inflated",
		".coded",
		".compiled"
	};

	private static final int ASSEMBLED_REFERENCE_SIZE = 1024 * 1024;	// 1MB
	private static final int FONTLIST_REFERENCE_SIZE = 16;
	private static final int PAGE_REFERENCE_COUNT = 128;
	private static final int FIXED_OBJECTS_REFERENCE_SIZE = 256 * 1024; // 256KB
	private static final int RESOURCE_BUFFER_SIZE = 4096;			   // 4 KB

	private String templateDir;
	private String name;

	private String docType;
	private int docVersion;
	private String docDescription;

	private int startxref = 0;
	private Trailer trailer;
	private XRefEntry[] xRefEntries;
	private List addedXRefEntries = new LinkedList();

	private byte[] prologData;
	private byte[] infoData;

	private PDFPages pdfPages;
	private List pages = new ArrayList(PAGE_REFERENCE_COUNT);
	private List fonts;

	private boolean debug;

	private boolean freezed = false;

	private byte[] fixedObjectsContent;
	private byte[] fixedXRefContent;

	private int defaultFontObjID;

	/**
	 * Constructs an instance to work with the PDF template <code>name</code>
	 * persisted in the <code>templateDir</code> directory.
	 *
	 * @param templateDir Template directory.
	 *
	 * @param name Template name.
	 */
	public PDFTemplate(String templateDir, String name) {
		this.templateDir = templateDir;
		this.name = name;
	}

	/**
	 * Loads PDF objects from this template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void load() throws IOException {

		Properties docProperties = loadProperties("docProperties");

		docType = docProperties.getProperty("type");

		if (docType == null) {
			throw new IOException("Cannot read document type.");
		}

		String sDocVersion = docProperties.getProperty("version");

		if (sDocVersion == null) {
			throw new IOException("Cannot read document version.");
		}

		try {
			docVersion = Integer.parseInt(sDocVersion);
		} catch (Exception e) {
			throw new IOException(
					"Invalid document version '" + sDocVersion + "'.");
		}

		docDescription = docProperties.getProperty("description");

		if (docDescription == null) {
			docDescription = "First Version";
		}

		prologData = loadData("prolog");
		infoData = loadData("info");

		Properties trailerProperties = loadProperties("trailer");
		Properties xrefProperties = loadProperties("xref");

		trailer = new Trailer();

		trailer.nObjects =
				Integer.parseInt(trailerProperties.getProperty("size"));

		trailer.rootObjID =
				Integer.parseInt(trailerProperties.getProperty("root"));

		trailer.infoObjID =
				Integer.parseInt(trailerProperties.getProperty("info"));

		log("trailer = " + trailer);

		try {
			loadPDFPages();
		} catch (FPGException e) {
			throw new IOException(
					"Cannot load PDFPages [cause=" + e.getMessage() + "]");
		}

		// Loads xreferences.
		xRefEntries = new XRefEntry[trailer.nObjects];

		for (int index = 0; index < xRefEntries.length; index++) {
			String propertyValue = xrefProperties.getProperty(index + "");

			if (propertyValue == null) {
				throw new IOException("Unexpected xref EOF.");
			}

			/*
			 * # index = ObjID Offset Value Mark Type
			 *
			 * 0=2 2 65535 f null
			 * 1=1 312 0 n info
			 *
			 */
			String[] tokens = propertyValue.split(" ");

			XRefEntry xRefEntry = new XRefEntry();

			xRefEntry.objID = Integer.parseInt(tokens[0]);;
			xRefEntry.offset = Integer.parseInt(tokens[1]);
			xRefEntry.value = Integer.parseInt(tokens[2]);
			xRefEntry.mark = tokens[3].charAt(0);
			xRefEntry.baseType = tokens[4];

			xRefEntries[index] = xRefEntry;

			log("idx(" + index + ") = " + xRefEntry);
		}
	}

	/**
	 * Gets document type name for this PDF template.
	 *
	 * @return Document type name.
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * Gets document version for this PDF template.
	 *
	 * @return Document version.
	 */
	public int getDocVersion() {
		return docVersion;
	}

	/**
	 * Sets document description for this PDF template.
	 *
	 * @return Document description.
	 */
	public String getDocDescription() {
		return docDescription;
	}

	/**
	 * Sets PDF PROLOG for this template.
	 *
	 * @param data PROLOG data.
	 *
	 * @see #getProlog()
	 */
	public void setProlog(byte[] data) {
		prologData = data;
	}

	/**
	 * Gets PDF PROLOG for this template.
	 *
	 * @return PROLOG data.
	 *
	 * @see #setProlog(byte[])
	 */
	public byte[] getProlog() {
		return prologData;
	}

	/**
	 * Persists PDF PROLOG for this template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveProlog() throws IOException {
		saveData("prolog", prologData);
	}

	/**
	 * Sets PDF INFO object for this template.
	 *
	 * @param data INFO data.
	 *
	 * @see #getInfo()
	 */
	public void setInfo(byte[] data) {
		infoData = data;
	}

	/**
	 * Gets PDF INFO for this template.
	 *
	 * @return INFO data.
	 *
	 * @see #setInfo(byte[])
	 */
	public byte[] getInfo() {
		return infoData;
	}

	/**
	 * Persists PDF INFO for this template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveInfo() throws IOException {
		saveData("info", infoData);
	}

	private void loadPDFPages() throws IOException, FPGException {

		log("loading PDFPages ...");

		/*
		 * <<
		 * /Type /Catalog
		 * /Pages 21 0 R
		 * >>
         * endobj
         */
        String catalogText =
        		new String(loadData("catalog/" + trailer.rootObjID));

		PDFDictionary catalogDict = new PDFDictionary(catalogText);

		int pagesObjID = catalogDict.getObjIDRefValue("/Pages");

		log("PDFPages objID = " + pagesObjID);

		/*
		 * <<
		 * /Type /Pages
		 * /Resources <<
		 * /ProcSet [/PDF /Text /ImageB ]
		 * /Font <<
		 * /F22 22 0 R
		 *      ...
		 * /F36 36 0 R
		 * >>
		 * /XObject <<
		 * /X219 219 0 R
		 *       ...
		 * /X288 288 0 R
		 * >>
		 * /ColorSpace <<
		 * /CS1 [/Pattern /DeviceRGB ]
		 * >>
		 * >>
		 * /Kids
		 * [268 0 R
		 * 322 0 R
		 * 311 0 R
		 * ]
		 * /Count 3
		 * >>
		 * endobj
		 */
        String pagesText = new String(loadData("pages/" + pagesObjID));
		PDFDictionary pagesDict = new PDFDictionary(pagesText);

		pdfPages = new PDFPages(pagesObjID);

		PDFDictionary resourcesDict = pagesDict.getPDFDictionary("/Resources");

		if (resourcesDict != null) {

			/*
			 * Extracts mapped object references to fonts.
			 */
			PDFDictionary fontDict = resourcesDict.getPDFDictionary("/Font");

			if (fontDict != null) {
				log("Fonts:");

				for (int i = 0; i < fontDict.wordCount(); i++)  {
					String fontName = fontDict.getWord(i);
					int fontObjID = fontDict.getObjIDRefValue(fontName);

					pdfPages.addFontObjRef(
							new MappedObjRef(fontName, fontObjID));
				}

				List objRefs = pdfPages.getFontObjRefs();

				for (int i = 0; i < objRefs.size(); i++) {
					log("    " + objRefs.get(i));
				}
			}

			/*
			 * Extracts mapped object references to xobjects.
			 */
			PDFDictionary xObjectDict =
					resourcesDict.getPDFDictionary("/XObject");

			if (xObjectDict != null) {
				log("XObjects:");

				for (int i = 0; i < xObjectDict.wordCount(); i++)  {
					String xObjectName = xObjectDict.getWord(i);

					int xObjectObjID =
							xObjectDict.getObjIDRefValue(xObjectName);

					pdfPages.addXObjectObjRef(
							new MappedObjRef(xObjectName, xObjectObjID));
				}

				List objRefs = pdfPages.getXObjectObjRefs();

				for (int i = 0; i < objRefs.size(); i++) {
					log("    " + objRefs.get(i));
				}
			}
		}

		/*
		 * Extracts object ids to kids.
		 */
		String sKids = pagesDict.getValue("/Kids");

		if (sKids != null) {
			PDFArray kidsArray = new PDFArray(sKids);

			log("Kids:");

			for (int i = 0; i < kidsArray.size(); i += 3)  {
				int pageObjID = kidsArray.getIntValue(i);

				pdfPages.addKidObjID(pageObjID);
			}

			List objIDs = pdfPages.getKidObjIDs();

			for (int i = 0; i < objIDs.size(); i++) {
				log("    " + objIDs.get(i));
			}
		}

		/*
		 * Extracts count.
		 */
		int count = pagesDict.getIntValue("/Count");

		pdfPages.setCount(count);

		log("Count = " + pdfPages.getCount());

		/*
		 * Loads every PDFPage object.
		 */
		List objIDs = pdfPages.getKidObjIDs();

		for (int i = 0; i < objIDs.size(); i++) {
			int pageObjID = ((Integer) objIDs.get(i)).intValue();
			PDFPage page = new PDFPage(pageObjID);

			/*
			 * <<
			 * /Type /Page
			 * /MediaBox [0 0 612 1008 ]
			 * /Rotate 270
			 * /Parent 21 0 R
			 * /Contents [217 0 R
			 * 218 0 R
			 * 222 0 R
			 * ]
			 * >>
			 * endobj
			 */
        	String pageText = new String(loadData("page/" + pageObjID));
			PDFDictionary pageDict = new PDFDictionary(pageText);

			//Extracts parent object id.
			int parentObjID = pageDict.getObjIDRefValue("/Parent");

			page.setParentObjID(parentObjID);

			log("page[" + i + "]:");
			log("    parent objID = " + page.getParentObjID());

			PDFArray contentsArray =
					new PDFArray(pageDict.getValue("/Contents"));

			for (int j = 0; j < contentsArray.size(); j += 3)  {
				int contentObjID = contentsArray.getIntValue(j);

				page.addContentObjID(contentObjID);
			}

			List contentObjIDs = page.getContentObjIDs();

			for (int j = 0; j < contentObjIDs.size(); j++) {
				log("    content #" + j + " = " + contentObjIDs.get(j));
			}

			pageDict.removeWord("/Parent");
			pageDict.removeWord("/Contents");

			byte[] pageHeader = pageDict.assemble(true, false);

			page.setHeader(pageHeader);

			pages.add(page);
		}

		log("PDFPages loaded.");
	}

	/**
	 * Gets template name.
	 *
	 * @return Template name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets template directory.
	 *
	 * @return Template directory.
	 */
	public String getTemplateDir() {
		return templateDir;
	}

	/**
	 * Gets template path.
	 *
	 * @return Template path.
	 */
	public String getPath() {
		return templateDir + "/" + name;
	}

	/**
	 * Gets full template name.
	 *
	 * @return Full template name.
	 */
	public String getFullName() {
		return templateDir + "::" + name;
	}

	/**
	 * Controls debug mode.
	 *
	 * @param debug Debug mode control flag.
	 */
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}

	/**
	 * Indicates if debug mode is enabled or not.
	 *
	 * @return <code>true</code> if debug mode is enabled,
	 *         otherwise <code>false</code>.
	 */
	public boolean isDebugMode() {
		return debug;
	}

	/**
	 * Gets number of PDF fonts.
	 *
	 * @return Number of PDF fonts.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public int getPDFFontCount() throws IOException {

		if (fonts == null) {
			getPDFFonts();
		}

		return fonts.size();
	}

	/**
	 * Gets the specified PDF font.
	 *
	 * @param name PDF font name.
	 *
	 * @return PDF font with the specified name, or <code>null</code> if
	 *         named font is unknown.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public PDFFont getPDFFont(String name) throws IOException {

		if (fonts == null) {
			getPDFFonts();
		}

		for (int i = 0; i < fonts.size(); i++) {
			PDFFont font = (PDFFont) fonts.get(i);

			if (font.getName().equals(name)) {
				return font;
			}
		}

		return null;
	}

	/**
	 * Gets collection of PDF fonts.
	 *
	 * @return Collection of PDF fonts.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public List getPDFFonts() throws IOException {

		if (fonts != null) {
			return fonts;
		}

		fonts = new ArrayList(FONTLIST_REFERENCE_SIZE);

		for (int i = 0; i < xRefEntries.length; i++) {
			XRefEntry xRefEntry = xRefEntries[i];

			if (xRefEntry.baseType.equals("font")) {

				/*
				 * ...
				 * /Name /F00000016
				 * ...
				 * /CharProcs <<
				 * /41 23 0 R
				 * /43 24 0 R
				 * /45 26 0 R
				 * >>
				 */
				ByteArray fontData =
						new ByteArray(loadData("font/" + xRefEntry.objID));

				int index = fontData.indexOf("/Name");
				String fontName = fontData.getNextToken(index + 5);

				PDFFont pdfFont =
						new PDFFont(fontName, xRefEntry.objID);

				index = fontData.indexOf("/CharProcs", index);

				int beginIndex = fontData.indexOf("<<", index);

				index = beginIndex + 3;

				int endIndex = fontData.indexOf(">>", index);
				String sCharProcs = fontData.getString(index, endIndex);
				String[] tokens = sCharProcs.split("[ \n]");

				for (int j = 0; j < tokens.length; j += 4) {
					pdfFont.addCharProc(
							tokens[j], Integer.parseInt(tokens[j + 1]));
				}

				pdfFont.setHeader(fontData.getBytes(0, beginIndex));

				pdfFont.setTrailer(
						fontData.getBytes(endIndex + 3, fontData.length()));

				fonts.add(pdfFont);
				log("font = " + pdfFont);
			}
		}

		return fonts;
	}

	/**
	 * Gets number of pages.
	 *
	 * @return Number of pages.
	 */
	public int getPageCount() {
		return pages.size();
	}

	/**
	 * Gets a PDF Page by its index. Page index starts from 1.
	 *
	 * @param index PDF Page index.
	 *
	 * @return Required PDF page.
	 */
	public PDFPage getPage(int index) {
		return (PDFPage) pages.get(index);
	}

	/*
	 * PDF Objects methods.
	 */

	/**
	 * Gets number of PDF Objects.
	 *
	 * @return Number of PDF Objects.
	 *
	 * @see #initialObjectsCount()
	 * @see #addedObjectsCount()
	 */
	public int objectsCount() {
		return initialObjectsCount() + addedObjectsCount();
	}

	/**
	 * Gets number of PDF Objects loaded initially.
	 *
	 * @return Number of PDF Objects loaded.
	 *
	 * @see #objectsCount()
	 * @see #addedObjectsCount()
	 */
	public int initialObjectsCount() {
		return xRefEntries.length;
	}

	/**
	 * Gets number of PDF Objects added from last load operation.
	 *
	 * @return number of PDF Objects added.
	 *
	 * @see #objectsCount()
	 * @see #initialObjectsCount()
	 */
	public int addedObjectsCount() {
		return addedXRefEntries.size();
	}

	/**
	 * Removes a PDF object persistently from this PDF template.
	 *
	 * @param objID PDF Object ID.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void deleteObject(int objID) throws IOException {

		XRefEntry xRefEntry = getXRefEntry(objID);

		if (xRefEntry == null) {
			throw new IOException("Object " + objID + "not found");
		}

		removeData(xRefEntry);
		xRefEntry.release();
		log("object " + objID + " was deleted.");
	}

	/**
	 * Releases a PDF object from cross reference.
	 *
	 * @param objID PDF Object ID.
	 */
	public void releaseObject(int objID) {
		XRefEntry xRefEntry = getXRefEntry(objID);

		if (xRefEntry == null) {
			return;
		}

		xRefEntry.release();
		log("object " + objID + " was released.");
	}

	/**
	 * Copies a PDF object from this template to other specified PDF template.
	 * This method will preserve original PDF Object ID when it's possible.
	 *
	 * @param objID PDF Object ID.
	 *
	 * @param destTemplate Destination PDF template.
	 *
	 * @return PDF object ID created on destination PDF template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public int copyObject(int objID, PDFTemplate destTemplate)
			throws IOException {

		XRefEntry xRefEntry = getXRefEntry(objID);

		if (xRefEntry == null) {
			throw new IOException("Object " + objID + "not found");
		}

		/*
		 * Checks if objID is assigned in the destination template.
		 */
		XRefEntry destXRefEntry = destTemplate.getXRefEntry(objID);

		if (destXRefEntry != null) {
			destXRefEntry = destTemplate.allocateXRefEntry(xRefEntry.baseType);
		} else {

			/*
			 * Checks if objID is present but release in xref
			 * of the destination template.
			 */
			destXRefEntry = destTemplate.getXRefEntry(objID, false);

			if (destXRefEntry != null) {
				destXRefEntry.offset = 0;
				destXRefEntry.value = 0;
				destXRefEntry.mark = 'n';
				destXRefEntry.baseType = xRefEntry.baseType;
			} else {
				destXRefEntry =
						destTemplate.allocateXRefEntry(xRefEntry.baseType);
			}
		}

		destTemplate.saveData(destXRefEntry, loadData(xRefEntry));

		log(
				  "object " + objID + " copied to "
				+ destTemplate.getName() + "::"	+ destXRefEntry.objID);

		return destXRefEntry.objID;
	}

	/**
	 * Links a PDF object from this template (source) to other PDF object in
	 * (destination) specified PDF template. {@link #EXTENSION_NORMAL} object
	 * extension is assumed.
	 *
	 * @param objID Source PDF Object ID in this PDF template.
	 *
	 * @param destTemplate Destination PDF template.
	 *
	 * @param destObjID Destination PDF Object ID.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #linkObject(int, PDFTemplate, int, int)
	 */
	public void linkObject(int objID, PDFTemplate destTemplate, int destObjID)
			throws IOException {

		linkObject(objID, destTemplate, destObjID, EXTENSION_NORMAL);
	}

	/**
	 * Links a PDF object from this template (source) to other PDF object in
	 * (destination) specified PDF template.
	 *
	 * @param objID Source PDF Object ID in this PDF template.
	 *
	 * @param destTemplate Destination PDF template.
	 *
	 * @param destObjID Destination PDF Object ID.
	 *
	 * @param extension PDF Object file extension.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #linkObject(int, PDFTemplate, int)
	 */
	public void linkObject(int objID, PDFTemplate destTemplate, int destObjID,
			int extension) throws IOException {

		XRefEntry xRefEntry = getXRefEntryByIndex(objID);
		XRefEntry destXRefEntry = destTemplate.getXRefEntryByIndex(destObjID);

		if (!xRefEntry.baseType.equals(destXRefEntry.baseType)) {
			throw new IOException(
					"Base types must be the same to link object.");
		}

		String srcFileName = getFileName(xRefEntry, extension);

		String destFileName =
				destTemplate.getFileName(destXRefEntry, extension);

		log("linking " + srcFileName + " to " + destFileName);

		String linkedPath = "@" + destFileName;

		FileUtils.saveData(srcFileName, linkedPath.getBytes());
	}

	/**
	 * Removes a PDF font persistently.
	 *
	 * @param font PDF font to be removed.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void removeFont(PDFFont font) throws IOException {

		if (getPDFFont(font.getName()) == null) {
			throw new IOException(
					"Font '" + font.getName() + "' not found.");
		}

		log("Removing font " + font.getName() + " ...");

		List charProcs = font.getCharProcs();

		for (int i = 0; i < charProcs.size(); i++) {
			PDFFont.CharProc charProc = (PDFFont.CharProc) charProcs.get(i);

			deleteObject(charProc.getObjID());
		}

		deleteObject(font.getObjID());
		fonts.remove(font);
		pdfPages.removeFontObjRef(new MappedObjRef("", font.getObjID()));

		log("Font " + font.getName() + " removed.");
	}

	/**
	 * Adds a new PDF font extracted from a source PDF template persistently.
	 *
	 * @param srcTemplate Source PDF template.
	 *
	 * @param srcFont PDF Font.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void addFont(PDFTemplate srcTemplate, PDFFont srcFont)
			throws IOException {

		if (getPDFFont(srcFont.getName()) != null) {
			throw new IOException(
					"Font '" + srcFont.getName() + "' cannot be duplicated.");
		}

		String srcFontName = srcTemplate.getName() + "::" + srcFont.getName();

		log("Adding font " + srcFontName + " ...");

		PDFFont newFont = (PDFFont) srcFont.clone();

		int objID = srcTemplate.copyObject(srcFont.getObjID(), this);

		newFont.setObjID(objID);

		List charProcs = srcFont.getCharProcs();

		for (int i = 0; i < charProcs.size(); i++) {
			PDFFont.CharProc charProc = (PDFFont.CharProc) charProcs.get(i);

			objID =	srcTemplate.copyObject(charProc.getObjID(), this);
			charProc = newFont.getCharProc(charProc.getCode());

			charProc.setObjID(objID);
		}

		fonts.add(newFont);

		String $srcFontName =
				srcTemplate.pdfPages.getFontName(srcFont.getObjID());

		MappedObjRef objRef =
				new MappedObjRef($srcFontName, newFont.getObjID());

		pdfPages.addFontObjRef(objRef);

		log("Font " + srcFontName + " added.");
	}

	/**
	 * Releases a PDF page from this PDF template.
	 *
	 * @param page PDF page to be released.
	 */
	public void releasePage(PDFPage page) {
		int pageObjID = page.getObjID();

		pdfPages.removeKidObjID(pageObjID);
		pdfPages.decrementCount();

		releaseObject(pageObjID);
	}

	/**
	 * Adds a new PDF object with the specified base type and content.
	 *
	 * @param data PDF object content.
	 *
	 * @param baseType PDF object base type.
	 *
	 * @return PDF Font object ID added.
	 */
	public int addObject(byte[] data, String baseType) {
		XRefEntry xRefEntry = addNewXRefEntry(baseType);

		xRefEntry.objectData = data;

		return xRefEntry.objID;
	}

	/**
	 * Adds a new PDF object from a named resource. Resource will be assumed
	 * to have <code>/etc/fpg/</code> as prefix.
	 *
	 * @param resourceName Resource name.
	 *
	 * @return PDF Font object ID added.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public int addObject(String resourceName) throws IOException {

		InputStream input =
				getClass().getResourceAsStream("/etc/fpg/" + resourceName);

		if (input == null) {
			throw new IOException(
					"Resource '" + resourceName + "' not found.");
		}

		ByteArrayOutputStream objectData =
				new ByteArrayOutputStream(RESOURCE_BUFFER_SIZE);

		byte[] buffer = new byte[RESOURCE_BUFFER_SIZE];

		try {
			int size;

			while ((size = input.read(buffer)) > 0) {
				objectData.write(buffer, 0 , size);
			}

			return addObject(objectData.toByteArray(), "resource");
		} finally {
			try {
				input.close();
				objectData.close();
			} catch (IOException e) {}
		}
	}

	/**
	 * Adds a new PDF Font object.
	 *
	 * @param name Font name.
	 *
	 * @param encodingObjID Encoding PDF Object ID.
	 *
	 * @return PDF Font object ID added.
	 */
	public int addFontObject(String name, int encodingObjID) {

		byte[] fontData =
				(  "<<\n"
				  + "/Type/Font/BaseFont/"+ name + "/Subtype/Type1\n"
				  + "/Encoding " + encodingObjID + " 0 R\n"
				  + ">>\n"
				  + "endobj\n").getBytes();

		return addObject(fontData, "internal");
	}

	/**
	 * Sets default PDF Font Object ID.
	 *
	 * @param objID Default PDF Font Object ID.
	 *
	 * @see #getDefaultFontObjID()
	 */
	public void setDefaultFontObjID(int objID) {
		defaultFontObjID = objID;
	}

	/**
	 * Gets default PDF Font Object ID.
	 *
	 * @return Default PDF Font Object ID.
	 *
	 * @see #setDefaultFontObjID(int)
	 */
	public int getDefaultFontObjID() {
		return defaultFontObjID;
	}

	/**
	 * Assembles this PDF template. Default Assembled reference size is used.
	 *
	 * @return Assembled PDF template content.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public byte[] assemble() throws IOException {

		return assemble(ASSEMBLED_REFERENCE_SIZE);
	}

	/**
	 * Assembles this PDF template using the specified assembled reference size.
	 *
	 * @param referenceSize Assembled reference size.
	 *
	 * @return Assembled PDF template content.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public byte[] assemble(int referenceSize) throws IOException {

		ByteArrayOutputStream output = null;
		Deflater deflater =	new Deflater(Deflater.BEST_COMPRESSION);

 		startxref = 0;

		try {
			output = new ByteArrayOutputStream(referenceSize);

			/*
			 * Assembles prolog.
			 */
			output.write(prologData);

			startxref += prologData.length;

			/*
			 * Assembles objects.
			 */
			for (int i = 0; i < xRefEntries.length; i++) {
				for (int j = 0; j < xRefEntries.length; j++) {
					XRefEntry xRefEntry = xRefEntries[j];

					if (xRefEntry.objID == i) {
						if (!xRefEntry.baseType.equals("null")
									&& !xRefEntry.baseType.equals("info")) {

							xRefEntry.offset = startxref;

							String objectName =
									(xRefEntry.baseType.equals("void"))
											? "void/0"
											:    xRefEntry.baseType
												+ "/"
												+ xRefEntry.objID;

							long objectLastModified =
									lastModified(objectName);

							long inflatedObjectLastModified =
									lastModified(
											objectName, EXTENSION_INFLATED);

							byte[] objectData;

							if (inflatedObjectLastModified
									> objectLastModified) {

								log(
										  "Text object "
										+ xRefEntry.objID + " changed");

								objectData =
										loadData(
												objectName, EXTENSION_INFLATED);

								byte[] encodedData =
										new byte[objectData.length];

								deflater.setInput(objectData);
								deflater.finish();

								int encodedDataLength =
										deflater.deflate(encodedData);

								if (encodedDataLength == encodedData.length) {
									encodedData =
											new byte[2 * objectData.length];

									deflater.reset();
									deflater.setInput(objectData);
									deflater.finish();

									encodedDataLength =
											deflater.deflate(encodedData);
								}

								deflater.reset();

								/*
								 *  218 0 obj
								 *  <<
								 *  /Filter /FlateDecode /Length 152 >>
								 *  stream
								 *  <ENCODED_DATA>endstream
								 *  endobj
								 */
								String objectHeader =
										  xRefEntry.objID + " 0 obj\n"
										+ "<<\n"
										+ "/Filter /FlateDecode /Length "
										+ encodedDataLength + " >>\n"
										+ "stream\n";

								String objectTrailer = "endstream\nendobj\n";

								byte[] objectHeaderBytes =
										objectHeader.getBytes();

								byte[] objectTrailerBytes =
										objectTrailer.getBytes();

								output.write(objectHeaderBytes);
								output.write(encodedData, 0, encodedDataLength);
								output.write(objectTrailerBytes);

								startxref +=
										  objectHeaderBytes.length
										+ encodedDataLength
										+ objectTrailerBytes.length;
							} else {
								objectData = loadData(objectName);

								String objectHeader =
										xRefEntry.objID + " 0 obj\n";

								byte[] objectHeaderBytes =
										objectHeader.getBytes();

								output.write(objectHeaderBytes);
								output.write(objectData);

								startxref +=
										  objectHeaderBytes.length
										+ objectData.length;
							}
						}

						break;
					}
				}
			}

			/*
			 * Assembles info object.
			 */
			xRefEntries[trailer.infoObjID].offset = startxref;

			String infoHeader = trailer.infoObjID + " 0 obj\n";
			byte[] infoHeaderBytes = infoHeader.getBytes();

			output.write(infoHeaderBytes);
			output.write(infoData);

			startxref += infoHeaderBytes.length	+ infoData.length;

			/*
			 * Produces cross reference section.
			 *
			 * xref
			 * 0 313 <space>
			 * 0000000002 65535 f <space>
			 * 0000000312 00000 n <space>
			 */
			println(output, "xref");
			println(output, "0 " + xRefEntries.length + " ");

			for (int i = 0; i < xRefEntries.length; i++) {
				XRefEntry xRefEntry = xRefEntries[i];

				println(output,
						  Padder.lpad(xRefEntry.offset, 10, '0')
						+ " "
						+ Padder.lpad(xRefEntry.value, 5, '0')
						+ " "
						+ xRefEntry.mark
						+ " ");
			}

			/*
			 *
			 * Produces trailer section.
			 *
			 * trailer
			 * <<
			 * /Size 313 <space>
			 * /Root 312 0 R
			 * /Info 1 0 R
			 * >>
			 * startxref
			 * 134805 <space>
			 * %%EOF
			 */
			println(output, "trailer");
			println(output, "<<");
			println(output, "/Size " + trailer.nObjects + " ");
			println(output, "/Root " + trailer.rootObjID + " 0 R");
			println(output, "/Info " + trailer.infoObjID + " 0 R");
			println(output, ">>");
			println(output, "startxref");
			println(output, startxref + " ");
			println(output, "%%EOF");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}

			deflater.end();
		}

		return output.toByteArray();
	}

	/**
	 * Saves PDF template content.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void save() throws IOException {

		saveObjects();
		saveXRef();
		saveTrailer();
	}

	/**
	 * Saves PDF objects for this PDF template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveObjects() throws IOException {

		/*
		 *  Saves fonts.
		 */
		List fonts = getPDFFonts();

		for (int i = 0; i < fonts.size(); i++) {
			PDFFont font = (PDFFont) fonts.get(i);

			saveData("font/" + font.getObjID(), font.assemble());
			log("font '" + font.getObjID() + "' was saved.");
		}

		/*
		 *  Saves pages.
		 */
		for (int i = 0; i < pages.size(); i++) {
			PDFPage page = (PDFPage) pages.get(i);

			saveData("page/" + page.getObjID(), page.assemble());
			log("page '" + page.getObjID() + "' was saved.");
		}

		/*
		 *  Saves PDFPages.
		 */
		saveData("pages/" + pdfPages.getObjID(), pdfPages.assemble());
		log("pages '" + pdfPages.getObjID() + "' was saved.");
	}

	/**
	 * Saves PDF Cross reference for this PDF template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveXRef() throws IOException {

		/*
		 *  Saves cross references.
		 */
		String xRefText = "# index = ObjID Offset Value Mark Type\n";
		int objectsCount = objectsCount();

		for (int i = 0; i < objectsCount; i++) {
			XRefEntry xRefEntry = getXRefEntryByIndex(i);

			xRefText +=
					  i
					+ "="
					+ xRefEntry.objID + " "
					+ xRefEntry.offset + " "
					+ xRefEntry.value + " "
					+ xRefEntry.mark + " "
					+ xRefEntry.baseType + "\n";
		}

		saveData("xref", xRefText.getBytes());
		log("xref was saved.");
	}

	/**
	 * Saves PDF Trailer for this PDF template.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveTrailer() throws IOException {

		trailer.nObjects = objectsCount();

		String trailerText =
				  "size=" + trailer.nObjects + "\n"
				+ "root=" + trailer.rootObjID + "\n"
				+ "info=" + trailer.infoObjID + "\n";

		saveData("trailer", trailerText.getBytes());
		log("trailer was saved.");
	}

	/**
	 * Converts PDF trailer to byte array.
	 *
	 * @return PDF trailer byte array.
	 *
	 * see #trailerToByteArray(Trailer, int)
	 */
	public byte[] trailerToByteArray() {
		return trailerToByteArray(trailer, startxref);
	}

	/**
	 * Converts specified PDF trailer to byte array.
	 *
	 * @param trailer PDF trailer.
	 *
	 * @param startxref PDF Cross reference offset.
	 *
	 * @return PDF trailer byte array.
	 *
	 * see #trailerToByteArray()
	 */
	public static byte[] trailerToByteArray(Trailer trailer, int startxref) {
		String s =
				  "trailer\n"
				+ "<<\n"
				+ "/Size " + trailer.nObjects + " \n"
				+ "/Root " + trailer.rootObjID + " 0 R\n"
				+ "/Info " + trailer.infoObjID + " 0 R\n"
				+ ">>\n"
				+ "startxref\n"
				+ startxref + " \n"
				+ "%%EOF\n";

		return s.getBytes();
	}

	/**
	 * Indicates if this PDF template is freezed.
	 *
	 * @return <code>true</code> if this PDF template is freezed,
	 *         otherwise <code>false</code>.
	 *
	 * @see #freeze()
	 * @see #freeze(int)
	 */
	public boolean isFreezed() {
		return freezed;
	}

	/**
	 * Freezes this PDF template. Default fixed object reference size is used.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #freeze(int)
	 */
	public void freeze() throws IOException {

		freeze(FIXED_OBJECTS_REFERENCE_SIZE);
	}

	/**
	 * Freezes this PDF template using specified fixed object reference size.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #freeze()
	 */
	public void freeze(int fixedObjectsReferenceSize) throws IOException {

		if (freezed) {
			return;
		}

		ByteArrayOutputStream fixedObjects = null;
		ByteArrayOutputStream fixedXRef = null;
		Deflater deflater =	new Deflater(Deflater.BEST_COMPRESSION);

 		startxref = 0;

		try {
			fixedObjects = new ByteArrayOutputStream(fixedObjectsReferenceSize);

			fixedXRef =
					new ByteArrayOutputStream(
							XRefEntry.SIZE * xRefEntries.length);

			/*
			 * Assembles prolog.
			 */
			fixedObjects.write(prologData);

			startxref += prologData.length;

			/*
			 * Assembles objects.
			 */
			for (int i = 0; i < xRefEntries.length; i++) {
				XRefEntry xRefEntry = xRefEntries[i];

				// Special allocation for freed entries.
				if (xRefEntry.isAssignable()) {
					xRefEntry.assign("void");
				}

				if (!xRefEntry.baseType.equals("null")
							&& !xRefEntry.baseType.equals("info")) {

					xRefEntry.offset = startxref;

					String objectName =
							(xRefEntry.baseType.equals("void"))
									? "void/0"
									:    xRefEntry.baseType
										+ "/"
										+ xRefEntry.objID;

					long objectLastModified =
							lastModified(objectName);

					long inflatedObjectLastModified =
							lastModified(objectName, EXTENSION_INFLATED);

					byte[] objectData;

					if (inflatedObjectLastModified
								> objectLastModified) {

						log(
								  "Text object "
								+ xRefEntry.objID + " changed");

						objectData = loadData(objectName, EXTENSION_INFLATED);

						byte[] encodedData =
								new byte[objectData.length];

						deflater.setInput(objectData);
						deflater.finish();

						int encodedDataLength =
								deflater.deflate(encodedData);

						if (encodedDataLength == encodedData.length) {
							encodedData =
									new byte[2 * objectData.length];

							deflater.reset();
							deflater.setInput(objectData);
							deflater.finish();

							encodedDataLength =
									deflater.deflate(encodedData);
						}

						deflater.reset();

						/*
						 *  218 0 obj
						 *  <<
						 *  /Filter /FlateDecode /Length 152 >>
						 *  stream
						 *  <ENCODED_DATA>endstream
						 *  endobj
						 */
						String objectHeader =
								  xRefEntry.objID + " 0 obj\n"
								+ "<<\n"
								+ "/Filter /FlateDecode /Length "
								+ encodedDataLength + " >>\n"
								+ "stream\n";

						String objectTrailer = "endstream\nendobj\n";

						byte[] objectHeaderBytes =
								objectHeader.getBytes();

						byte[] objectTrailerBytes =
								objectTrailer.getBytes();

						fixedObjects.write(objectHeaderBytes);
						fixedObjects.write(encodedData, 0, encodedDataLength);
						fixedObjects.write(objectTrailerBytes);

						startxref +=
								  objectHeaderBytes.length
								+ encodedDataLength
								+ objectTrailerBytes.length;
					} else {
						objectData = loadData(objectName);

						String objectHeader =
								xRefEntry.objID + " 0 obj\n";

						byte[] objectHeaderBytes =
								objectHeader.getBytes();

						fixedObjects.write(objectHeaderBytes);
						fixedObjects.write(objectData);

						startxref +=
								  objectHeaderBytes.length
								+ objectData.length;
					}
				}
			}

			for (int i = 0; i < addedXRefEntries.size(); i++) {
				XRefEntry xRefEntry = (XRefEntry) addedXRefEntries.get(i);

				xRefEntry.offset = startxref;

				String objectHeader = xRefEntry.objID + " 0 obj\n";
				byte[] objectHeaderBytes = objectHeader.getBytes();
				byte[] objectData = xRefEntry.objectData;

				fixedObjects.write(objectHeaderBytes);
				fixedObjects.write(xRefEntry.objectData);

				startxref += objectHeaderBytes.length + objectData.length;
			}

			/*
			 * Assembles info object if it is assigned.
			 */
			XRefEntry infoXRefEntry = xRefEntries[trailer.infoObjID];

			if (infoXRefEntry.isAssigned()
						&& infoXRefEntry.baseType.equals("info")) {

				infoXRefEntry.offset = startxref;

				String infoHeader = trailer.infoObjID + " 0 obj\n";
				byte[] infoHeaderBytes = infoHeader.getBytes();

				fixedObjects.write(infoHeaderBytes);
				fixedObjects.write(infoData);

				startxref += infoHeaderBytes.length	+ infoData.length;
			}

			/*
			 * Produces fixed part of cross reference section.
			 *
			 * 0000000002 65535 f <space>
			 * 0000000312 00000 n <space>
			 */
			for (int i = 0; i < xRefEntries.length; i++) {
				XRefEntry xRefEntry = xRefEntries[i];

				println(fixedXRef,
						  Padder.lpad(xRefEntry.offset, 10, '0')
						+ " "
						+ Padder.lpad(xRefEntry.value, 5, '0')
						+ " "
						+ xRefEntry.mark
						+ " ");
			}

			for (int i = 0; i < addedXRefEntries.size(); i++) {
				XRefEntry xRefEntry = (XRefEntry) addedXRefEntries.get(i);

				println(fixedXRef,
						  Padder.lpad(xRefEntry.offset, 10, '0')
						+ " "
						+ Padder.lpad(xRefEntry.value, 5, '0')
						+ " "
						+ xRefEntry.mark
						+ " ");
			}
		} finally {
			if (fixedObjects != null) {
				try {
					fixedObjects.close();
				} catch (IOException e) {}
			}

			if (fixedXRef != null) {
				try {
					fixedXRef.close();
				} catch (IOException e) {}
			}

			deflater.end();
		}

		fixedObjectsContent = fixedObjects.toByteArray();
		fixedXRefContent = fixedXRef.toByteArray();

		/*
		 * Refreshs final fixed objects count.
		 */
		trailer.nObjects = objectsCount();

		freezed = true;
	}

	/**
	 * Gets PDF Trailer.
	 *
	 * @return PDF Trailer.
	 */
	public Trailer getTrailer() {
		return trailer;
	}

	/**
	 * Sets Root PDF Object ID.
	 *
	 * @param objID Root PDF Object ID.
	 */
	public void setRootObjID(int objID) {
		trailer.rootObjID = objID;
	}

	/**
	 * Gets Root PDF Object ID.
	 *
	 * @return Root PDF Object ID.
	 */
	public int getRootObjID() {
		return trailer.rootObjID;
	}

	/**
	 * Sets INFO PDF Object ID.
	 *
	 * @param objID INFO PDF Object ID.
	 */
	public void setInfoObjID(int objID) {
		trailer.infoObjID = objID;
	}

	/**
	 * Gets INFO PDF Object ID.
	 *
	 * @return INFO PDF Object ID.
	 */
	public int getInfoObjID() {
		return trailer.infoObjID;
	}

	/**
	 * Gets number of PDF Objects.
	 *
	 * @return Number of PDF Objects.
	 *
	 * @see #getObjectCount()
	 */
	public int getSize() {
		return trailer.nObjects;
	}

	/**
	 * Gets number of PDF Objects.
	 *
	 * @return Number of PDF Objects.
	 *
	 * @see #getSize()
	 */
	public int getObjectCount() {
		return getSize();
	}

	/**
	 * Gets PDF Pages Object.
	 *
	 * @return PDF Pages Object.
	 */
	public PDFPages getPDFPages() {
		return pdfPages;
	}

	/**
	 * Gets PDF Cross Reference offset.
	 *
	 * @return PDF Cross Reference offset.
	 */
	public int getStartXRef() {
		return startxref;
	}

	/**
	 * Gets the list of Compilable PDF Objects. This
	 * method is equivalent to call
	 * <codegetCompilableObjects(EXTENSION_CODED)</code>.
	 *
	 * @return Compilable PDF Objects.
	 *
	 * @see #getObjects(int)
	 */
	public List getCompilableObjects() {
		return getObjects(EXTENSION_CODED);
	}

	/**
	 * Gets the list of PDF Objects to this PDF
	 * Template with the specified extension.
	 *
	 * @param extension Required PDF Objects extension.
	 *
	 * @return list of PDF Objects.
	 */
	public List getObjects(int extension) {
		List compilableObjects = new LinkedList();
		int nObjects = getObjectCount();

		for (int objID = 1; objID < nObjects; objID++) {
			try {
				PDFObject object = getObject(objID, extension);

				if (object != null) {
					compilableObjects.add(object);
				}
			} catch (IOException e) {}
		}

		return compilableObjects;
	}

	/**
	 * Gets a PDF Object. {@link #EXTENSION_NORMAL} PDF Object extension is
	 * assumed.
	 *
	 * @param objID PDF Object ID.
	 *
	 * @return Required PDF Object or <code>null</code> if PDF Object ID is not
	 *         present.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #getObject(int, int)
	 */
	public PDFObject getObject(int objID) throws IOException {

		return getObject(objID, EXTENSION_NORMAL);
	}

	/**
	 * Gets a PDF Object with the specified extension.
	 *
	 * @param objID PDF Object ID.
	 *
	 * @param extension PDF Object extension.
	 *
	 * @return Required PDF Object or <code>null</code> if PDF Object ID is not
	 *         present.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #getObject(int, int)
	 */
	public PDFObject getObject(int objID, int extension) throws IOException {

		XRefEntry xRefEntry = getXRefEntry(objID);

		if (xRefEntry == null) {
			return null;
		}

		PDFObject object = new PDFObject(this);

		object.setObjID(xRefEntry.objID);
		object.setBaseType(xRefEntry.baseType);
		object.setExtension(extension);

		byte[] data = loadData(xRefEntry, extension);

		object.setData(data);

		return object;
	}

	/**
	 * Gets Fixed PDF Objects content. PDF Template must be freezed or an
	 * {@link java.lang.IllegalStateException} exception will be thrown.
	 *
	 * @return Fixed PDF Objects content.
	 *
	 * @see #getFixedXRefContent()
	 */
	public byte[] getFixedObjectsContent() {
		if (!freezed) {
			throw new IllegalStateException("PDF Template not freezed");
		}

		return fixedObjectsContent;
	}

	/**
	 * Gets Fixed Cross Reference content. PDF Template must be freezed or an
	 * {@link java.lang.IllegalStateException} exception will be thrown.
	 *
	 * @return Fixed Cross Reference content.
	 *
	 * @see #getFixedObjectsContent()
	 */
	public byte[] getFixedXRefContent() {
		if (!freezed) {
			throw new IllegalStateException("PDF Template not freezed");
		}

		return fixedXRefContent;
	}

	private XRefEntry getXRefEntryByIndex(int index) {
		if (index < xRefEntries.length) {
			return xRefEntries[index];
		}

		index -= xRefEntries.length;

		return (XRefEntry) addedXRefEntries.get(index);
	}

	private XRefEntry getXRefEntry(int objID) {
		return getXRefEntry(objID, true);
	}

	private XRefEntry getXRefEntry(int objID, boolean assigned) {
		for (int i = 0; i < xRefEntries.length; i++) {
			XRefEntry xRefEntry = xRefEntries[i];

			if (xRefEntry.objID == objID && xRefEntry.check(assigned)) {
				return xRefEntry;
			}
		}

		for (int i = 0; i < addedXRefEntries.size(); i++) {
			XRefEntry xRefEntry = (XRefEntry) addedXRefEntries.get(i);

			if (xRefEntry.objID == objID && xRefEntry.check(assigned)) {
				return xRefEntry;
			}
		}

		return null;
	}

	private XRefEntry allocateXRefEntry(String baseType) {
		for (int i = 0; i < xRefEntries.length; i++) {
			XRefEntry xRefEntry = xRefEntries[i];

			if (xRefEntry.isAssignable()) {
				return xRefEntry.assign(baseType);
			}
		}

		for (int i = 0; i < addedXRefEntries.size(); i++) {
			XRefEntry xRefEntry = (XRefEntry) addedXRefEntries.get(i);

			if (xRefEntry.isAssignable()) {
				return xRefEntry.assign(baseType);
			}
		}

		return addNewXRefEntry(baseType);
	}

	private XRefEntry addNewXRefEntry(String baseType) {
		XRefEntry xRefEntry = new XRefEntry();

		xRefEntry.objID = objectsCount();

		xRefEntry.assign(baseType);
		addedXRefEntries.add(xRefEntry);

		return xRefEntry;
	}

	private String getFileName(String name) {
		return getFileName(name, EXTENSION_NORMAL);
	}

	private String getFileName(String name, int extension) {
		return
				  templateDir + "/"
				+ this.name + "/"
				+ name + ".txt" + fileExtensions[extension];
	}

	private String getFileName(XRefEntry xRefEntry) {
		return getFileName(xRefEntry, EXTENSION_NORMAL);
	}

	private String getFileName(XRefEntry xRefEntry, int extension) {
		if (!xRefEntry.baseType.equals("info")) {
			return
					getFileName(
							  xRefEntry.baseType
							+ "/"
							+ xRefEntry.objID,
							extension);
		}

		return getFileName("info", extension);
	}

	/**
	 * Loads a PDF Template properties from a named template object file.
	 *
	 * @param name Template object file name.
	 *
	 * @return PDF Template properties.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public Properties loadProperties(String name)
			throws IOException {

		return FileUtils.loadProperties(getFileName(name));
	}

	/**
	 * Loads a PDF object content identified by its cross reference entry.
	 * {@link #EXTENSION_NORMAL} object extension is assumed.
	 *
	 * @param xRefEntry PDF Cross Reference entry.
	 *
	 * @return PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #loadData(XRefEntry, int)
	 */
	public byte[] loadData(XRefEntry xRefEntry) throws IOException {

		return loadData(xRefEntry, EXTENSION_NORMAL);
	}

	/**
	 * Loads a PDF object content identified by its cross reference entry and
	 * using the specified object extension.
	 *
	 * @param xRefEntry PDF Cross Reference entry.
	 *
	 * @param extension PDF Object extension.
	 *
	 * @return PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #loadData(XRefEntry)
	 */
	public byte[] loadData(XRefEntry xRefEntry, int extension)
			throws IOException {

		return _loadData(getFileName(xRefEntry, extension));
	}

	/**
	 * Loads a PDF object content from its object file.
	 * {@link #EXTENSION_NORMAL} object extension is assumed.
	 *
	 * @param name PDF object file name without extension.
	 *
	 * @return PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #loadData(String, int)
	 */
	public byte[] loadData(String name) throws IOException {

		return loadData(name, EXTENSION_NORMAL);
	}

	/**
	 * Loads a PDF object content from its object file and using the
	 * specified object extension.
	 *
	 * @param name PDF object file name without extension.
	 *
	 * @param extension PDF Object extension.
	 *
	 * @return PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #loadData(String)
	 */
	public byte[] loadData(String name, int extension) throws IOException {

		return _loadData(getFileName(name, extension));
	}

	private byte[] _loadData(String name) throws IOException {

		byte[] data = FileUtils.loadData(name);

		if (data.length == 0 || data[0] != '@') {
			return data;
		}

		String linkedPath = new String(data, 1, data.length - 1);

		if (!linkedPath.startsWith("/")) {
			File file = new File(name);

			linkedPath = file.getParent() + "/" + linkedPath;
		}

		return FileUtils.loadData(linkedPath);
	}

	/**
	 * Saves a PDF object content to the specified object file name.
	 *
	 * {@link #EXTENSION_NORMAL} object extension is assumed.
	 *
	 * @param name PDF object file name without extension.
	 *
	 * @param data PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #saveData(String, byte[], int)
	 */
	public void saveData(String name, byte[] data) throws IOException {

		saveData(name, data, EXTENSION_NORMAL);
	}

	/**
	 * Saves a PDF object content to the specified object file name and using
	 * the specified object extension.
	 *
	 * @param name PDF object file name without extension.
	 *
	 * @param data PDF object content.
	 *
	 * @param extension PDF Object extension.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #saveData(String, byte[])
	 */
	public void saveData(String name, byte[] data, int extension)
			throws IOException {

		FileUtils.saveData(getFileName(name, extension), data);
	}

	/**
	 * Saves a PDF object content to object file according to the specified
	 * PDF cross reference entry.
	 *
	 * {@link #EXTENSION_NORMAL} object extension is assumed.
	 *
	 * @param xRefEntry PDF cross reference entry.
	 *
	 * @param data PDF object content.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveData(XRefEntry xRefEntry, byte[] data) throws IOException {

		FileUtils.saveData(getFileName(xRefEntry), data);
	}

	/**
	 * Removes a PDF object file. {@link #EXTENSION_NORMAL} object extension
	 * is assumed.
	 *
	 * @param name PDF object file name.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #removeData(String, int)
	 * @see #removeData(XRefEntry)
	 */
	public void removeData(String name) throws IOException {

		removeData(name, EXTENSION_NORMAL);
	}

	/**
	 * Removes a PDF object file using the specified object extension.
	 *
	 * @param name PDF object file name.
	 *
	 * @param extension PDF Object extension.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #removeData(String)
	 * @see #removeData(XRefEntry)
	 */
	public void removeData(String name, int extension) throws IOException {

		FileUtils.remove(getFileName(name, extension));
	}

	/**
	 * Removes a PDF object file associated to a cross reference entry.
	 *
	 * @param xRefEntry PDF Cross reference.
	 *
	 * @throws IOException If an I/O error occurs.
	 *
	 * @see #removeData(String)
	 * @see #removeData(String, int)
	 */
	public void removeData(XRefEntry xRefEntry) throws IOException {

		FileUtils.remove(getFileName(xRefEntry));
	}

	private long lastModified(String name) {
		return lastModified(name, EXTENSION_NORMAL);
	}

	private long lastModified(String name, int extension) {

		return FileUtils.lastModified(getFileName(name, extension));
	}

	private void println(OutputStream output, String s) throws IOException {

		s += "\n";

		output.write(s.getBytes());
	}

	private void log(String message) {
		if (debug) {
			System.err.println("PDFTemplate[" + name + "]: " + message);
		}
	}
}
