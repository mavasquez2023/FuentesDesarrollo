

/*
 * @(#) PDFFont.java    1.0 08-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.util.ArrayList;
import java.util.List;


/**
 * Nonembedded PDF font. This font type has associated a collection of
 * {@link PDFFont.CharProc} instances, one for each character to be included
 * in a PDF Template. 
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
 *            <TD> 08-03-2008 </TD>
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
public class PDFFont implements Cloneable {

	private static final int REFERENCE_CHARPROCS_COUNT = 32;

	private String name;
	private int objID;

	private byte[] header;
	private byte[] trailer;

	private List charProcs;

	/**
	 * Constructs a PDF Font instance with the specified name and PDF Object ID.
	 * 
	 * @param name PDF Font name.
	 * 
	 * @param objID PDF Object ID.
	 */
	public PDFFont(String name, int objID) {
		this(name, objID, REFERENCE_CHARPROCS_COUNT);
	}

	/**
	 * Constructs a PDF Font instance with the specified name and PDF Object ID
	 * and initial count of character proc entries.
	 * 
	 * @param name PDF Font name.
	 * @param objID PDF Object ID.
	 * @param initialCharProcsCount Initial count of character proc entries.
	 */
	public PDFFont(String name, int objID, int initialCharProcsCount) {
		this.name = name;
		this.objID = objID;
		charProcs = new ArrayList(initialCharProcsCount);
	}

	/**
	 * Clones a PDF Font.
	 */
	public Object clone() {
		PDFFont font = new PDFFont(name, objID, charProcs.size());

		font.header = new byte[header.length];
		font.trailer = new byte[trailer.length];

		System.arraycopy(header, 0, font.header, 0, header.length);
		System.arraycopy(trailer, 0, font.trailer, 0, trailer.length);

		for (int i = 0; i < charProcs.size(); i++) {
			CharProc charProc = (CharProc) charProcs.get(i);

			font.addCharProc((CharProc) charProc.clone());
		}

		return font;
	}

	/**
	 * Sets PDF Font header content.
	 * 
	 * @param header PDF Font header content.
	 * 
	 * @see #getHeader()
	 */
	public void setHeader(byte[] header) {
		this.header = header;
	}

	/**
	 * Gets PDF Font header content.
	 * 
	 * @return PDF Font header content.
	 * 
	 * @see #setHeader(byte[])
	 */
	public byte[] getHeader() {
		return header;
	}

	/**
	 * Sets PDF Font trailer content.
	 * 
	 * @param trailer PDF Font trailer content.
	 * 
	 * @see #getTrailer()
	 */	
	public void setTrailer(byte[] trailer) {
		this.trailer = trailer;
	}

	/**
	 * Gets PDF Font trailer content.
	 * 
	 * @return PDF Font trailer content.
	 * 
	 * @see #setTrailer(byte[])
	 */	
	public byte[] getTrailer() {
		return trailer;
	}

	/**
	 * Gets PDF Font name.
	 * 
	 * @return PDF Font name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets PDF Font ID.
	 * 
	 * @param objID PDF Font ID.
	 * 
	 * @see #getObjID()
	 */
	public void setObjID(int objID) {
		this.objID = objID;
	}

	/**
	 * Gets PDF Font ID.
	 * 
	 * @return PDF Font ID.
	 * 
	 * @see #setObjID(int)
	 */
	public int getObjID() {
		return objID;
	}

	/**
	 * Adds a PDF CharProc to this PDF Font with the specified values.
	 * 
	 * @param code Character code.
	 * 
	 * @param objID Character PDF Object ID.
	 * 
	 * @see #addCharProc(cl.araucana.fpg.PDFFont.CharProc)
	 */
	public void addCharProc(String code, int objID) {
		addCharProc(new CharProc(code, objID));
	}

	/**
	 * Adds a PDF CharProc to this PDF Font.
	 * 
	 * @param charProc Character Proc.
	 * 
	 * @see #addCharProc(String, int)
	 */
	public void addCharProc(CharProc charProc) {
		charProcs.add(charProc);
	}

	/**
	 * Gets collection of {@link PDFFont.CharProc} instances for this PDF Font.
	 * 
	 * @return Collection of Character Procs.
	 */
	public List getCharProcs() {
		return charProcs;
	}

	/**
	 * Gets a string representation for the collection of Character Procs of
	 * this PDF Font.
	 * 
	 * @return String representation for Character Procs.
	 */
	public String getStringCharProcs() {
		String result = "";

		for (int i = 0; i < charProcs.size(); i++) {
			result += charProcs.get(i);

			if (i + 1 < charProcs.size()) {
				result += " ";
			}
		}

		return result;
	}

	/**
	 * Gets a Character Proc instance from its code.
	 * 
	 * @param code Character Proc code.
	 * 
	 * @return Character Proc or <code>null</code> if it is unknown.
	 */
	public CharProc getCharProc(String code) {
		for (int i = 0; i < charProcs.size(); i++) {
			CharProc charProc = (CharProc) charProcs.get(i);

			if (charProc.getCode().equals(code)) {
				return charProc;
			}
		}

		return null;
	}

	/**
	 * Assembles PDF Page content.
	 * 
	 * @return PDF Page content.
	 */	
	public byte[] assemble() {
		String aux = "<<\n";

		for (int i = 0; i < charProcs.size(); i++) {
			aux += charProcs.get(i) + " 0 R\n";
		}

		aux += ">>\n";

		byte[] auxBytes = aux.getBytes();

		byte[] assembled =
				new byte[header.length + auxBytes.length + trailer.length];

		System.arraycopy(header, 0, assembled, 0, header.length);

		System.arraycopy(
				auxBytes, 0, assembled, header.length, auxBytes.length);

		System.arraycopy(
				trailer,
				0,
				assembled,
				header.length + auxBytes.length,
				trailer.length);

		return assembled;
	}

	/**
	 * Compares two PDF Fonts for equality. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <b>PDFFont</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */	
	public boolean equals(Object obj) {
		if (!(obj instanceof PDFFont)) {
			return false;
		}

		PDFFont other = (PDFFont) obj;

		return getName().equals(other.getName());
	}

	/**
	 * Represents a PDF Font with the following syntax:
	 * <b>&lt;name&gt; &lt;object ID&gt;</b>.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return name + " " + objID;
	}

	/**
	 * PDF Character Proc that belongs to a PDF font.
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
	 *            <TD> 08-03-2008 </TD>
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
	public static class CharProc implements Cloneable {

		private String code;
		private int objID;

		/**
		 * Constructs an instance with the specified values.
		 * 
		 * @param code Code.
		 * 
		 * @param objID PDF Object ID.
		 */
		public CharProc(String code, int objID) {
			this.code = code;
			this.objID = objID;
		}

		/**
		 * Clones this instance.
		 */
		public Object clone() {
			return new CharProc(code, objID);
		}

		/**
		 * Gets Character Proc code.
		 * 
		 * @return Character Proc code. 
		 */
		public String getCode() {
			return code;
		}

		/**
		 * Sets PDF Object ID.
		 * 
		 * @param objID PDF Object ID.
		 * 
		 * @see #getObjID()
		 */		
		public void setObjID(int objID) {
			this.objID = objID;
		}

		/**
		 * Gets PDF Object ID.
		 * 
		 * @return PDF Object ID.
		 * 
		 * @see #setObjID(int)
		 */		
		public int getObjID() {
			return objID;
		}

		/**
		 * Represents a Mapped PDF Object reference with the following syntax:
		 * <b>&lt;code&gt; &lt;object ID&gt;</b>.
		 * 
		 * @return String representation.
		 */
		public String toString() {
			return code + " " + objID;
		}
	}
}
