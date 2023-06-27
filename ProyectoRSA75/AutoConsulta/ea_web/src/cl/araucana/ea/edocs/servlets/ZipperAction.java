
/*
 * @(#) ZipperAction.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.ea.edocs.servlets;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;

import cl.araucana.ea.edocs.DocTypeFormatter;
import cl.araucana.ea.edocs.DocumentController;
import cl.araucana.ea.edocs.DocumentType;
import cl.araucana.ea.edocs.DocumentTypes;
import cl.araucana.ea.edocs.Empresa;
import cl.araucana.ea.edocs.Encargado;
import cl.araucana.ea.edocs.FileLoader;
import cl.araucana.ea.edocs.logging.Logger;


/**
 * ...
 *
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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
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
public class ZipperAction extends HttpServlet implements Servlet {

	private static final int FORMAT_TXT = 1;
	private static final int FORMAT_CSV = 2;
	private static final int FORMAT_XLS = 3;

	private static final int DEFAULT_INITIAL_ZIP_BUF_SIZE = 131072;	// 128 KB.
	private static final int ZIP_BLOCK_BUF_SIZE = 262144;			// 256 KB.
	
	private static DocumentType[] documentTypes = DocumentTypes.documentTypes;
	
	private ServletContext servletContext;
	private final Logger logger = new Logger("ZipperAction");
	
	private String documentBaseDir;
	private String formatDir;
	private String zippedDocPrefix;
	private int initialZipBufferSize = DEFAULT_INITIAL_ZIP_BUF_SIZE;
	
	private DocumentController documentController;
	private DocTypeFormatter[] xlsFormatters;

	public void init() throws ServletException {
		
		servletContext = getServletContext();
		
		documentBaseDir =
				servletContext.getInitParameter("edocs.documentBaseDir");
				
		formatDir = servletContext.getInitParameter("edocs.formatDir");
		
		zippedDocPrefix =
				servletContext.getInitParameter("edocs.zippedDocPrefix");
		
		String initialZipBufferSizeParam =
				servletContext.getInitParameter("edocs.initialZipBufferSize");
		
		try {
			initialZipBufferSize = Integer.parseInt(initialZipBufferSizeParam);
		} catch (NumberFormatException e) {}
		
		logger.log("init()");
		logger.log("    documentBaseDir      = " + documentBaseDir);
		logger.log("    formatDir            = " + formatDir);
		logger.log("    zippedDocPrefix      = " + zippedDocPrefix);		
		logger.log(
				  "    initialZipBufferSize = "
				+ initialZipBufferSize + " bytes.");

		documentController = DocumentController.getInstance();
		
		DocumentType[] documentTypes = DocumentTypes.documentTypes; 
		
		xlsFormatters = new DocTypeFormatter[documentTypes.length];
		
		for (int i = 0; i < xlsFormatters.length; i++) {
			String docType = documentTypes[i].getName();
			String parameterName =
					"edocs." + docType + ".xlsFormatterClassName";
			
			logger.log("");
			
			logger.log(
					  "    loading xlsFormatter for document type "
					+ "'" + docType + "' ...");
			
			String xlsFormatterClassName =
					servletContext.getInitParameter(parameterName);

			if (xlsFormatterClassName == null) {
				throwException("'" + parameterName + "' parameter not found");
			}
			
			try {
				Class xlsFormatterClass = Class.forName(xlsFormatterClassName);
				
				String baseDir = "/ea/edocs/xls/" + docType;
				FileLoader fileloader = null;
				
				fileloader = new FileLoader(baseDir + "/header.html");
				String header = fileloader.loadText();
				
				fileloader = new FileLoader(baseDir + "/trailer.html");
				String trailer = fileloader.loadText();
				
				Constructor constructor =
						xlsFormatterClass.getConstructor(
								new Class[] { String.class, String.class });
							
				xlsFormatters[i] =
						(DocTypeFormatter) constructor.newInstance(
								new String[] { header, trailer });
			} catch (ClassNotFoundException e) {
				throwException(
						  "xls formatter class name "
						+ "'" + xlsFormatterClassName + "'not found");
			} catch (IOException e) {
				throwException(
						  "cannot load html files for document type "
						+ "'" + docType + "'");
			} catch (Exception e) {
				throwException(
						  "cannot instantiate class name "
						+ "'" + xlsFormatterClassName + "'");
			}
			
			logger.log(
					  "    xlsFormatter for document type "
					+ "'" + docType + "' loaded OK.");
		}
	}

	private void throwException(String message) throws ServletException {

		logger.log("    " + message + ".");
				
		throw new ServletException(message);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.log(e);
			
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"/WEB-INF/edocs/userNotify.jsp");
							
			dispatcher.forward(request, response);
		}
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long initialTime = System.currentTimeMillis();
		Date requestDate = new Date();
		Encargado encargado = null;
		boolean invalidatedSession = false;
		HttpSession session = request.getSession();
		Principal principal = (Principal) session.getAttribute("userPrincipal");

		/*
		 * Checks if user principal is authenticated.
		 */
		if (principal == null || principal.getName() == null) {
			session.invalidate();
	
			invalidatedSession = true;
		} else {
			encargado =	(Encargado) session.getAttribute("edocs_encargado");

			if (encargado == null) {
				Profile profile =
						(Profile) session.getAttribute("ea_user_profile");
						
				if (profile != null) {
					String rut = (String) profile.getId();

					String fullName =
							(String) profile.getAttribute("nombreCompleto");
				
					Collection empresas =
							(Collection) profile.getAttribute("empresas");

					encargado = new Encargado(rut, fullName, empresas);
					session.setAttribute("edocs_encargado", encargado);
				} else {
					session.invalidate();

					invalidatedSession = true;
				}			
			}
		}

		if (invalidatedSession) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/logon.jsp");

			dispatcher.forward(request, response);
			
			return;
		}
		
		RequestedInfo requestedInfo = null;
		
		try {
			requestedInfo = new RequestedInfo(request, encargado.getEmpresas());
		} catch (RuntimeException e) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"/WEB-INF/edocs/nominas_anexos.jsp");

			dispatcher.forward(request, response);
			
			return;
		}
						
		// Generates zipped data.
		int zipBlockCount = (int) (encargado.getEmpresasCount() / 10.0 + 0.5);
		int zipBufferSize =
				  initialZipBufferSize + ZIP_BLOCK_BUF_SIZE * zipBlockCount;
		
		ByteArrayOutputStream bufferedOutput =
				new ByteArrayOutputStream(zipBufferSize);

		ZipOutputStream zipOut = new ZipOutputStream(bufferedOutput);

		zipOut.setLevel(Deflater.BEST_SPEED);
		
		includeDocumentTypeFormats(requestedInfo, zipOut);

		int[] documentCount = new int[documentTypes.length];
				
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			
			documentCount[i] =
					includeDocumentType(documentType, requestedInfo, zipOut);
		}
		
		zipOut.close();

		String requestedPeriod =
				DocumentController.periodToString(requestedInfo.getPeriod());
		
		// Send zipped data.
		String zipFileName =
				  zippedDocPrefix + "_"
				+ requestedPeriod.replace('/', '_')
				+ ".zip";		
		
		response.setContentType("application/zip");
		response.setHeader(
				"Content-Disposition", "inline; filename=" + zipFileName);		

		response.setContentLength(bufferedOutput.size());

		OutputStream output = response.getOutputStream();
		
		bufferedOutput.writeTo(output);
		output.close();
		bufferedOutput.close();

		// Logs current request and stats.
		long finalTime = System.currentTimeMillis();
				
		logRequest(
				requestDate,
				request.getHeader("User-Agent"),				
				encargado.getRut() + "",
				requestedInfo,
				documentCount,
				bufferedOutput.size(),
				finalTime - initialTime);
	}

	public void includeDocumentTypeFormats(RequestedInfo requestedInfo,
			ZipOutputStream zipOut) throws IOException {
			
		boolean[] selectedDocumentTypes = new boolean[documentTypes.length];

		// First step: unlinked document types.			
		for (int i = 0; i < selectedDocumentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
		
			if (!documentType.isLinkedTo()) {	
				selectedDocumentTypes[i] =
						requestedInfo.hasSelectedEnterprises(
								documentType.getName());
			}
		}

		// Second step: unlinked document types.
		for (int i = 0; i < selectedDocumentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
		
			if (documentType.isLinkedTo()) {
				documentType = documentType.getLinkedTo();
				
				int index = DocumentTypes.getDocumentTypeIndex(documentType);
			
				selectedDocumentTypes[i] = selectedDocumentTypes[index];
			}
		}
							
		for (int i = 0; i < selectedDocumentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			
			if (selectedDocumentTypes[i]) {
				writeDocumentTypeFormatToZip(
						zipOut, documentType.getFormatFileName());
			}			
		}
	}

	public int includeDocumentType(DocumentType documentType,
			RequestedInfo requestedInfo, ZipOutputStream zipOut)
					throws IOException {

		int period = requestedInfo.getPeriod();
		String docTypeName = documentType.getName();
		Collection empresas = requestedInfo.getEnterprises();
		Iterator iterator = empresas.iterator();
		String formatName = requestedInfo.getFormatName();
		int format = requestedInfo.getFormat();
		int includedDocumentCount = 0;
		String zipEntryName = null;
		String fileName = null;
		NumberFormat nf3 = new DecimalFormat("000");
		int i = 0;
		boolean thereAreDocumentsToBeIncluded = false;
		
		if (requestedInfo.isSeparatedByEnterprise()) {
			if (requestedInfo.isSeparatedByDocumentType(docTypeName)) {
				while (iterator.hasNext()) {
					Empresa empresa = (Empresa) iterator.next();
					int documentCount =
							empresa.getDocumentCount(docTypeName);
					
					thereAreDocumentsToBeIncluded =
							   documentCount > 0
							&& requestedInfo.hasSelectedEnterprise(
									i, docTypeName);
									
					if (thereAreDocumentsToBeIncluded) {
						for (int j = 1; j <= documentCount; j++) {
							zipEntryName =
									  empresa.getRut() + "/"
									+ documentType.getPrefix1() + "/"
									+ documentType.getPrefix2() + "-"
									+ nf3.format(j) + "." + formatName;
									
							fileName =
									  documentBaseDir + "/"
									+ documentType.getName() + "/"
									+ period + "/"
									+ empresa.getRut() + "/"
									+ j + "." + formatName;
			
							writeFileToZip(
									zipOut,
									zipEntryName,
									fileName,
									documentType,
									format);
						}
						
						includedDocumentCount += documentCount;
					}
			
					i++;
				}
			} else {
				while (iterator.hasNext()) {
					Empresa empresa = (Empresa) iterator.next();
					int documentCount =
							empresa.getDocumentCount(docTypeName);

					thereAreDocumentsToBeIncluded =
							   documentCount > 0
							&& requestedInfo.hasSelectedEnterprise(
									i, docTypeName);
									
					if (thereAreDocumentsToBeIncluded) {
						zipEntryName =
								  empresa.getRut() + "/"
								+ documentType.getPrefix1() + "." + formatName;
						
						ZipEntry ze = new ZipEntry(zipEntryName);
		
						zipOut.putNextEntry(ze);
						writeDocHeader(zipOut, documentType, format);
						
						for (int j = 1; j <= documentCount; j++) {
							fileName =
									documentBaseDir + "/"
								  + documentType.getName() + "/"
								  + period + "/"
								  + empresa.getRut() + "/"
								  + j + "." + formatName;
							
							writeFileToZip(
									zipOut, fileName, documentType, format);
						}

						writeDocTrailer(zipOut, documentType, format);
						zipOut.closeEntry();
						
						includedDocumentCount += documentCount;
					}
			
					i++;
				}
			}
		} else {		// information not separated by enterprise.
			boolean zipEntryCreated = false;

			while (iterator.hasNext()) {
				Empresa empresa = (Empresa) iterator.next();
				int documentCount = empresa.getDocumentCount(docTypeName);

				thereAreDocumentsToBeIncluded =
						   documentCount > 0
						&& requestedInfo.hasSelectedEnterprise(
								i, docTypeName);
									
				if (thereAreDocumentsToBeIncluded) {
					if (!zipEntryCreated) {
						zipEntryName =
								documentType.getPrefix1() + "." + formatName;
								
						ZipEntry ze = new ZipEntry(zipEntryName);
		
						zipOut.putNextEntry(ze);
						writeDocHeader(zipOut, documentType, format);
						
						zipEntryCreated = true;
					}
			
					for (int j = 1; j <= documentCount; j++) {
						fileName =
								documentBaseDir + "/"
							  + documentType.getName() + "/"
							  + period + "/"
							  + empresa.getRut() + "/"
							  + j + "." + formatName;
							
						writeFileToZip(zipOut, fileName, documentType, format);
					}
					
					includedDocumentCount += documentCount;
				}
	
				i++;
			}
			
			if (zipEntryCreated) {
				writeDocTrailer(zipOut, documentType, format);
				zipOut.closeEntry();
			}
		}
		
		return includedDocumentCount;
	}

	public boolean getOption(HttpServletRequest request, String optionName) {
		String optionValue = request.getParameter(optionName);
		
		return optionValue != null && optionValue.equals("ON");
	}

	public boolean getOption(HttpServletRequest request, String optionName,
			int index) {
				
		String optionValue = request.getParameter(optionName + index);
		
		return optionValue != null && optionValue.equals("ALL");
	}

	public void logRequest(Date requestDate, String userAgent, String userName,
			RequestedInfo requestedInfo, int[] documentCount, int dataSize,
			long serviceTime) {
		
		int documentTotalCount = 0;
		
		String separatedOptions = "";
		
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			String documentTypeName = documentType.getName();
			boolean separatedByDocumentType =
					requestedInfo.isSeparatedByDocumentType(documentTypeName);
			
			separatedOptions += b2i(separatedByDocumentType);
			
			if (i + 1 < documentTypes.length) {
				separatedOptions += ",";
			}
		}

		String selectedEnterprisesCounters = "";
		
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			String documentTypeName = documentType.getName();
			int selectedEnterprisesCounterByDocumentType =
					requestedInfo.getSelectedEnterprisesCount(documentTypeName);
			
			selectedEnterprisesCounters +=
					selectedEnterprisesCounterByDocumentType;
			
			if (i + 1 < documentTypes.length) {
				selectedEnterprisesCounters += ",";
			}
		}
	
		String documentCounts = "";
		
		for (int i = 0; i < documentCount.length; i++) {
			documentCounts += documentCount[i];
			
			if (i + 1 < documentCount.length) {
				documentCounts += ",";
			}

			documentTotalCount += documentCount[i];
		}
				
		String request =
				  userName + ","
				+ userAgent.replace(',', ';') + ","
				+ "zipper,"
				+ requestedInfo.getPeriod() + ","
				+ requestedInfo.getFormatName() + ","
				+ b2i(requestedInfo.isSeparatedByEnterprise()) + ","
				+ separatedOptions + ","
				+ requestedInfo.getEnterprises().size() + ","
				+ selectedEnterprisesCounters + ","
				+ documentCounts + ","				
				+ documentTotalCount + ","
				+ dataSize + ","	
				+ serviceTime;

		logger.logRequest(request, requestDate);
	}

	public int b2i(boolean b) {
		return (b) ? 1 : 0;
	}
	
	public void writeDocumentTypeFormatToZip(ZipOutputStream zipOut,
			String documentTypeFormatFileName) throws IOException {

		String zipEntryName = "formatos/" + documentTypeFormatFileName;
		String fileName = formatDir + "/" + documentTypeFormatFileName;

		writeFileToZip(zipOut, zipEntryName, fileName, null, FORMAT_TXT);
	}

	public void writeFileToZip(ZipOutputStream zipOut, String zipEntryName,
			String fileName, DocumentType documentType, int format)
				throws IOException {

		ZipEntry ze = new ZipEntry(zipEntryName);
		
		zipOut.putNextEntry(ze);
		writeDocHeader(zipOut, documentType, format);		
		writeFileToZip(zipOut, fileName, documentType, format);
		writeDocTrailer(zipOut, documentType, format);
		zipOut.closeEntry();
	}

	public void writeFileToZip(ZipOutputStream zipOut, String fileName,
			DocumentType documentType, int format) throws IOException {

		FileLoader fileLoader = null;
		byte[] data = null;

		if (format == FORMAT_XLS) {
			String txtFileName =
					fileName.substring(0, fileName.length() - 4) + ".txt";
			
			fileLoader = new FileLoader(txtFileName);

			Collection records = fileLoader.loadLines();
			int[] fieldLengths = documentType.getFieldLengths();
			int recordLength =
					  4									// <TR>
					+ 4 * fieldLengths.length			// <TD>
					+ documentType.getRecordLength()	// reclen
					+ 1;								// newline
					
			StringBuffer sb =
					new StringBuffer(
							(int) (1.2 * recordLength * records.size()));
					
			Iterator iterator = records.iterator();
			int index = documentType.getIndex();
			DocTypeFormatter formatter = xlsFormatters[index];
			
			while (iterator.hasNext()) {
				String record = (String) iterator.next();
				int offset = 0;
				
				sb.append("<TR>");
				
				for (int i = 0; i < fieldLengths.length; i++) {
					String fieldValue =
							record.substring(offset, offset + fieldLengths[i]);
					 
					sb.append(formatter.formatField(i, fieldValue));
					
					offset += fieldLengths[i];
				}
				
				sb.append('\n');
			}
			
			zipOut.write(sb.toString().getBytes());
		} else {
			fileLoader = new FileLoader(fileName);
			data = fileLoader.loadBytes();
			
			zipOut.write(data);
		}
	}
	
	public void writeDocHeader(ZipOutputStream zipOut,
			DocumentType documentType, int format) throws IOException {
				
		if (format == FORMAT_XLS) {
			int index = documentType.getIndex();
			
			zipOut.write(xlsFormatters[index].getHeader().getBytes());
		}
	}

	public void writeDocTrailer(ZipOutputStream zipOut,
			DocumentType documentType, int format) throws IOException {

		if (format == FORMAT_XLS) {
			int index = documentType.getIndex();
			
			zipOut.write(xlsFormatters[index].getTrailer().getBytes());
		}
	}

	private class RequestedInfo {
		
		private int period;
		private boolean separatedByEnterprise;
		private Map separatedByDocumentType = new HashMap();
		private Collection enterprises;
		private int[][] requiredEnterprisesByDocumentType;
		private int format;
		
		private RequestedInfo(HttpServletRequest request,
				Collection enterprises) {

			String requestedPeriodParam =
					(String) request.getParameter("period");
		
			try {
				period = Integer.parseInt(requestedPeriodParam);
			} catch (NumberFormatException e) {}

			// Checks if period is valid.
			if (!documentController.isAvailablePeriod(period)) {
				throw new IllegalArgumentException(
						"Invalid period '" + period + "'.");
			}
			
			separatedByEnterprise = getOption(request, "SEPARADA_POR_EMPRESA");
			
			if (!separatedByEnterprise) {
				for (int i = 0; i < documentTypes.length; i++) {
					DocumentType documentType = documentTypes[i];
					String documentTypeName = documentType.getName();
					
					separatedByDocumentType.put(
							documentTypeName, new Boolean(false));
				}
			} else {
				for (int i = 0; i < documentTypes.length; i++) {
					DocumentType documentType = documentTypes[i];
					String documentTypeName = documentType.getName();
					
					if (documentType.isLinkedTo()) {
						documentType = documentType.getLinkedTo();
					}

					String separatedOptionName =
							"SEPARADA_POR_" + documentType.getName();

					separatedByDocumentType.put(
							documentTypeName,
							new Boolean(
									getOption(request, separatedOptionName)));
				}
			}
					
			String formatParam = request.getParameter("FORMATO");
		
			if (formatParam == null) {
				formatParam = "txt";
			}
		
			if (formatParam.equals("csv")) {
				format = FORMAT_CSV;
			} else if (formatParam.equals("xls")) {
				format = FORMAT_XLS;
			} else {
				format = FORMAT_TXT;
			}
			
			this.enterprises = enterprises;

			requiredEnterprisesByDocumentType =
					new int[enterprises.size()][documentTypes.length];

			/*
			 * Linked document types support now is included.
			 */
			for (int d = 0; d < documentTypes.length; d++) {
				DocumentType documentType = documentTypes[d];
				
				if (documentType.isLinkedTo()) {
					documentType = documentType.getLinkedTo();
				}
				
				for (int e = 0; e < enterprises.size(); e++) {
					if (getOption(request, documentType.getName(), e + 1)) {
						requiredEnterprisesByDocumentType[e][d] = 1;
					}
				}
			}
		}
		
		public int getPeriod() {
			return period;
		}
		
		public boolean isSeparatedByEnterprise() {
			return separatedByEnterprise;
		}
		
		public boolean isSeparatedByDocumentType(String documentType) {
			Boolean value =
					(Boolean) separatedByDocumentType.get(documentType);

			return value.booleanValue(); 
		}
		
		public int getFormat() {
			return format;
		}

		public String getFormatName() {
			if (format == FORMAT_CSV) {
				return "csv";
			}

			if (format == FORMAT_XLS) {
				return "xls";
			}

			return "txt";
		}
		
		public Collection getEnterprises() {
			return enterprises;
		}

		public int getSelectedEnterprisesCount(String documentTypeName) {
			int count = 0;
			int j = -1;

			for (int i = 0; i < documentTypes.length; i++) {
				if (documentTypes[i].getName().equals(documentTypeName)) {
					j = i;
					
					break;
				}
			}

			if (j == -1) {
				return 0;
			}
			
			for (int i = 0; i < enterprises.size(); i++) {
				count += requiredEnterprisesByDocumentType[i][j];
			}
			
			return count;
		}

		public boolean hasSelectedEnterprises(String documentTypeName) {
			return getSelectedEnterprisesCount(documentTypeName) > 0;
		}

		public boolean hasSelectedEnterprise(int enterpriseIndex,
				String documentTypeName) {

			int j = -1;

			for (int i = 0; i < documentTypes.length; i++) {
				if (documentTypes[i].getName().equals(documentTypeName)) {
					j = i;
			
					break;
				}
			}

			if (j == -1) {
				return false;
			}
	
			return requiredEnterprisesByDocumentType[enterpriseIndex][j] == 1;
		}
	}
}
