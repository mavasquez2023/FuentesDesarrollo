
/*
 * @(#) Empresa.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
 
 
package cl.araucana.ea.edocs;



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
public class DocumentType {
	
	public static final int KEYFIELD_OFFSET = 0;
	public static final int KEYFIELD_LENGTH = 1;
	
	private int index;
	private String name;
	private DocumentType linkedTo;
	private String prefix1;
	private String prefix2;
	private String formatFileName;
	private int[] fieldLengths;
	private int[] keyFields;
	private int[][] expandedKeyFields;

	private int recordLength;

	public DocumentType(String name, String prefix1, String prefix2,
			String formatFileName, int[] fieldLengths, int[] keyFields) {
	
		this(name, prefix1, prefix2, formatFileName,
				fieldLengths, keyFields, null);
	}

	public DocumentType(String name,  String prefix1, String prefix2,
			String formatFileName, int[] fieldLengths, int[] keyFields,
			DocumentType linkedTo) {
				
		setName(name);

		setPrefix1(prefix1);
		setPrefix2(prefix2);
		setFormatFileName(formatFileName);
		setFieldLengths(fieldLengths);
		setKeyFields(keyFields);
		
		setLinkedTo(linkedTo);
	}

	public DocumentType() {
	}

	public void setIndex(int index) {
		this.index = index;	
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setLinkedTo(DocumentType linkedTo) {
		this.linkedTo = linkedTo;
	}

	public DocumentType getLinkedTo() {
		return linkedTo;
	}

	public void setPrefix1(String prefix1) {
		this.prefix1 = prefix1;
	}

	public String getPrefix1() {
		return prefix1;
	}

	public void setPrefix2(String prefix2) {
		this.prefix2 = prefix2;
	}
	
	public String getPrefix2() {
		return prefix2;
	}

	public void setFormatFileName(String formatFileName) {
		this.formatFileName = formatFileName;
	}

	public String getFormatFileName() {
		return formatFileName;
	}

	public void setFieldLengths(int[] fieldLengths) {
		this.fieldLengths = fieldLengths;
		
		setRecordLength();
	}

	public int[] getFieldLengths() {
		return fieldLengths;
	}

	public boolean isLinkedTo() {
		return linkedTo != null;
	}

	public void setKeyFields(int[] keyFields) {

		int[][] expandedKeyFields = new int[keyFields.length][2];
		
		// Checks key fields.	
		for (int i = 0; i < keyFields.length; i++) {
			if (keyFields[i] < 0 || keyFields[i] >= fieldLengths.length) {
				throw new IllegalArgumentException("Invalid key fields.");
			}
		
			int offset = 0;
				
			for (int j = 0; j < keyFields[i]; j++) {
				offset += fieldLengths[j];
			}
			
			expandedKeyFields[i][KEYFIELD_OFFSET] = offset;
			expandedKeyFields[i][KEYFIELD_LENGTH] = fieldLengths[keyFields[i]];
		}		
		
		this.keyFields = keyFields;
		this.expandedKeyFields = expandedKeyFields;
	}
	
	public int[] getKeyFields() {
		return keyFields;
	}

	public int[][] getExpandedKeyFields() {
		return expandedKeyFields;
	}

	private void setRecordLength() {
		
		// Computes record length.		
		for (int i = 0; i < fieldLengths.length; i++) {
			recordLength += fieldLengths[i];
		}		
	}
	
	public int getRecordLength() {
		return recordLength;
	}
	
	public String toString() {
		String linkedToDocumentTypeName;
		String fieldLengthsSpec = "";
		String keyFieldsSpec = "";
		String expandedKeyFieldsSpec = "";
				
		if (linkedTo == null) {
			linkedToDocumentTypeName = "<none>";
		} else {
			linkedToDocumentTypeName = linkedTo.getName();
		}
	
		for (int i = 0; i < fieldLengths.length; i++) {
			fieldLengthsSpec += fieldLengths[i];
			
			if (i + 1 < fieldLengths.length) {
				fieldLengthsSpec += " ";
			}
		}
		
		for (int i = 0; i < keyFields.length; i++) {
			keyFieldsSpec += keyFields[i];
			
			if (i + 1 < keyFields.length) {
				keyFieldsSpec += " ";
			}
		}

		for (int i = 0; i < expandedKeyFields.length; i++) {
			expandedKeyFieldsSpec +=
					  expandedKeyFields[i][KEYFIELD_OFFSET]
					+ ":"
					+ expandedKeyFields[i][KEYFIELD_LENGTH];
					
			if (i + 1 < expandedKeyFields.length) {
				expandedKeyFieldsSpec += " ";
			}
		}
		
		return	  "name = " + name + "\n"
				+ "linkedTo = " + linkedToDocumentTypeName + "\n"
				+ "prefix1 = " + prefix1 + "\n"	
				+ "prefix2 = " + prefix2 + "\n"
				+ "formatFileName = " + formatFileName + "\n"
				+ "recordLength = " + recordLength + "\n"
				+ "fieldLengths = " + fieldLengthsSpec + "\n"
				+ "keyFields = " + keyFieldsSpec + "\n"
				+ "expandedKeyFields = " + expandedKeyFieldsSpec + "\n";
	}
}
