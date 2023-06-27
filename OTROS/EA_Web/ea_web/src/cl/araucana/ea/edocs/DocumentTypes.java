
/*
 * @(#) Empresa.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;

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
public class DocumentTypes {
	
	public static final int NC = 0;
	public static final int NL = 1;
	public static final int AT = 2;
	public static final int CF = 3;
	
	public static final int NDOCTYPES = 4;
	
	private static final Logger logger = new Logger("DocumentTypes");
	
	public static final DocumentType[] documentTypes =
			new DocumentType[NDOCTYPES];
			
	static {
		
		// Nómina de Crédito.
		documentTypes[NC] =
				new DocumentType(
						"NC",
						"nominas_credito",
						"nomina",
						"nomina_credito.txt",
						new int[] { 3, 9, 1, 3, 11, 9, 1, 36, 2, 2, 6, 2, 3, 7 },
						new int[] { 0, 1, 2, 3 });	

		// Nómina de Leasing.
		documentTypes[NL] =
				new DocumentType(
						"NL",
						"nominas_ahorro",
						"nomina",
						"nomina_ahorro.txt",
						new int[] { 8, 1, 3, 3, 8, 1, 52, 6, 1, 3, 9, 9 },
						new int[] { 0, 1, 2, 3 });	

		// Anexo de Trabajadores.
		documentTypes[AT] =
				new DocumentType(
						"AT",
						"anexos_trabajadores",
						"anexo",
						"anexo_trabajadores.txt",
						new int[] { 9, 1, 3, 3, 3, 9, 1, 15, 15, 20, 6, 1, 3,
							        10, 10, 10 },
						new int[] { 0, 1, 2, 3, 4 });

		// Cargas del Mes.
		documentTypes[CF] =
				new DocumentType(
						"CF",
						"cargas_familiares",
						"cargas",
						"cargas_familiares.txt",
						new int[] { 9, 1, 9, 1, 9, 1, 40, 1, 1, 8, 8, 8, 6, 1,
							        10, 10, 10 },
						new int[] { 0, 1 },
						documentTypes[AT]);	
		
		logger.log("Defined document types:");
		
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			String[] lines = documentType.toString().split("\n");
			
			documentType.setIndex(i);
			
			for (int j = 0; j < lines.length; j++) {
				logger.log("    " + lines[j]);
			}
			
			logger.log("");
		}
	}
	
	private DocumentTypes() {
	}
	
	public static int getDocumentTypeIndex(DocumentType documentType) {
		return getDocumentTypeIndex(documentType.getName());
	}
	
	public static int getDocumentTypeIndex(String documentTypeName) {
		for (int i = 0; i < documentTypes.length; i++) {
			String auxDocumentTypeName = documentTypes[i].getName();
			
			if(auxDocumentTypeName.equals(documentTypeName)) {
				return i;	
			}
		}
		
		throw new IllegalArgumentException(
				"'" + documentTypeName + "' unknown document type.");
	}
	public static DocumentType getDocumentType(String documentTypeName) {
		for (int i = 0; i < documentTypes.length; i++) {
			String auxDocumentTypeName = documentTypes[i].getName();
			
			if(auxDocumentTypeName.equals(documentTypeName)) {
				return documentTypes[i];	
			}
		}
		
		throw new IllegalArgumentException(
				"'" + documentTypeName + "' unknown document type.");
	}
}
