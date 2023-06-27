
/*
 * @(#) IndexTaskMonitor2.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class IndexTaskMonitor extends Thread {

	private static DocumentController documentController =
			DocumentController.getInstance();
	
	private final Logger logger = new Logger("IndexTaskMonitor");
	
	private DocumentIndexer indexer;
	private int period;
	private boolean replaceMode;
	private String documentBaseDir;
	
	public IndexTaskMonitor(DocumentIndexer indexer, int period,
			boolean replaceMode) {
				
		this.indexer = indexer;
		this.period = period;
		this.replaceMode = replaceMode;
	}
	
	public void run() {
		final int STEP_1 = 1;
		final int STEP_2 = 2;
		
		indexer.start();
		
		try {
			indexer.join();
		} catch (InterruptedException e) {
			logger.log(">< " + e.getMessage());
			indexer.cancel();
		}
		
		if (indexer.getStatus() != DocumentIndexer.ENDED) {
			indexFailed(STEP_1);

			return;
		}
		
		try {
			if (!replaceMode) {
				mergeDocuments();
			}
						
			documentController.saveMap(
					indexer.getMembers(), indexer.getIndexBaseDir());
		} catch (IOException e) {
			logger.log(">< " + e.getMessage());
			indexFailed(STEP_2);
			
			return;
		}
		
		documentController.setMap(
				indexer.getDocumentTypeName(), period, indexer.getMembers());
			
		logIndexOK();	
	}

	private void indexFailed(int step) {
		DirectoryCleaner.renameAndCleanup(indexer.getIndexBaseDir());
		logIndexFailed(step);		
	}
	
	private void logIndexFailed(int step) {
		logIndexStatus(false, step);
	}
	
	private void logIndexOK() {
		logIndexStatus(true, 0);
	}
	
	private void logIndexStatus(boolean ok, int step) {
		String commonText =
				  "*** Finished index "
				+ "'" + indexer.getDocumentTypeName() + "'";
				
		if (ok) {
			logger.log(commonText + " OK.");				
		} else {
			logger.log(commonText + " with FAILURE. [STEP" + step + "]");
		}
	}
	
	private void mergeDocuments() throws IOException {

		final int PHASE_1 = 1;
		final int PHASE_2 = 2;
		final int PHASE_3 = 3;
				
		String documentTypeName = indexer.getDocumentTypeName();
		
		String docTypeMapDirName =
				  documentController.getDocumentBaseDir()
				+ "/"
				+ documentTypeName
				+ "/"
				+ period;
				
		File docTypeMapDir = new File(docTypeMapDirName);
		Map previousMembers =
				documentController.getDocumentTypeMap(docTypeMapDir);
		
		if (previousMembers == null) {
			return;
		}

		String previousDocDirName = docTypeMapDirName;
		String indexBaseDirName = indexer.getIndexBaseDir();
				
		int[] mergeCounters = new int[4];
				
		logger.log(
				  "Merging "
				+ previousMembers.size()
				+ " previous members from "
				+ "'" + docTypeMapDirName + "'.");
		
		Map indexedMembers = indexer.getMembers();
		Set previousMembersSet = previousMembers.keySet();
		Iterator iterator = previousMembersSet.iterator();
		
		while (iterator.hasNext()) {
			Integer previousMemberId = (Integer) iterator.next();

			List previousDocuments =
					(List) previousMembers.get(previousMemberId);
			
			List indexedDocuments = (List) indexedMembers.get(previousMemberId);
			
			if (indexedDocuments == null ) {
				
				/*
				 * Merge #1: Adds all documents from previous old members
				 *           not present in the new index.
				 */
				mergeCounters[0]++;
				
				for (int i = 0; i < previousDocuments.size(); i++) {
					Document document = (Document) previousDocuments.get(i);
					String documentFileName =
							  previousDocDirName
							+ "/"
							+ previousMemberId
							+ "/"
							+ (i + 1) + ".txt";
							
					File documentFile =	new File(documentFileName);

					/* MARK.
					logger.log(
							  "MERGE #1: " 
							+ documentTypeName + "="
							+ document + " -> " + documentFileName);
					*/
					
					indexer.addDocument(document);
					indexer.saveDocument(document, documentFile);
					
					mergeCounters[1]++;
				}
			} else {

				// Merge #2: Adds most updated version for each document.
				int nAddedDocuments = 0;
				
				for (int i = previousDocuments.size() - 1; i >= 0; i--) {
					Document document = (Document) previousDocuments.get(i);
					
					if (!indexedDocuments.contains(document)) {		
						String documentFileName =
								  previousDocDirName
								+ "/"
								+ previousMemberId
								+ "/"
								+ (i + 1) + ".txt";

						File documentFile =	new File(documentFileName);
	
						logger.log(
								  "MERGE #2: add " 
								+ documentTypeName + "="
								+ document + " -> " + documentFileName);
						
						indexer.addDocument(document, 0);
						indexer.saveDocument(document, documentFile);
						
						mergeCounters[2]++;
						nAddedDocuments++;
					} else {
						logger.log(
								  "MERGE #2: update "
								+ documentTypeName + "="
						 		+ document);
						
						mergeCounters[3]++;
					}
				}
				
				if (nAddedDocuments > 0) {
					int nDocuments = indexedDocuments.size();
					int nNewDocuments = nDocuments - nAddedDocuments;

					String prefixDocumentFileName =
							indexBaseDirName + "/" + previousMemberId + "/";					

					String[] fileExtensions = new String [] {
							".txt",
							".csv"
					};
							
					// Phase 1: Renames documents to *.tmp.
					for (int i = 0; i < fileExtensions.length; i++) {
						String fileExtension = fileExtensions[i];
						
						String documentFileName;
						String tmpDocumentFileName;
						String newDocumentFileName;
						
						File documentFile;
						File tmpDocumentFile;
						File newDocumentFile;
						
						for (int j = 1; j <= nDocuments; j++) {
							documentFileName =
									  prefixDocumentFileName
									+ j + fileExtension;
	
							tmpDocumentFileName =
									  prefixDocumentFileName
									+ j + fileExtension + ".tmp";
							
							documentFile = new File(documentFileName);
							tmpDocumentFile = new File(tmpDocumentFileName);
									
							if (!documentFile.renameTo(tmpDocumentFile)) {
								mergeIOException(PHASE_1, documentFileName);
							}
						}

						// Phase 2: Renumbers old documents added.
						int index = 1;

						for (int j = nDocuments; j >= nNewDocuments + 1; j--) {
							documentFileName =
									  prefixDocumentFileName
									+ j + fileExtension + ".tmp";
	
							newDocumentFileName =
									  prefixDocumentFileName
									+ index++ + fileExtension;
							
							documentFile = new File(documentFileName);
							newDocumentFile = new File(newDocumentFileName);
									
							if (!documentFile.renameTo(newDocumentFile)) {
								mergeIOException(PHASE_2, documentFileName);
							}
						}
					
						// Phase 3: Renumbers new documents.
						for (int j = 1; j <= nNewDocuments; j++) {
							tmpDocumentFileName =
									  prefixDocumentFileName
									+ j + fileExtension + ".tmp";
	
							documentFileName =
									  prefixDocumentFileName
									+ (nAddedDocuments + j) + fileExtension;
							
							tmpDocumentFile = new File(tmpDocumentFileName);
									
							documentFile = new File(documentFileName);
									
							if (!tmpDocumentFile.renameTo(documentFile)) {
								mergeIOException(PHASE_3, documentFileName);
							}
						}
					}
					
				   /*  // MARK.
					logger.log(
							  "Final merged documents list for member "
							+ "'" + previousMemberId + "':");
					
					for (int i = 0; i < indexedDocuments.size(); i++) {
						Document document = (Document) indexedDocuments.get(i);
						
						logger.log("    " + document);
					}
					*/
				}
			}
		}

		logger.log(mergeCounters[0] + " members added by MERGE#1.");
		logger.log(mergeCounters[1] + " documents added by MERGE#1.");
		logger.log(mergeCounters[2] + " documents added by MERGE#2.");
		logger.log(mergeCounters[3] + " documents updated by MERGE#2.");
	}
	
	private void mergeIOException(int phase, String documentFileName)
			throws IOException {
				
		throw new IOException(
				  "Merge: Cannot rename "
				+ "'" + documentFileName + "'. "
				+ "[phase=" + phase + "]");		
	}
}
