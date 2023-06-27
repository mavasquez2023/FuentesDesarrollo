

/*
 * @(#) PublishedDocument.java    1.0 02-06-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.publishers;


import java.io.IOException;
import java.io.OutputStream;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * metadata and content of a published PDF document instance.
 * 
 * <p> The PDF document content can be organized in one or more partitions.
 * When a document is nonpartitioned will have one and only one partition,
 * otherwise it will be four partitions to fixed and variable contents
 * in the following order:
 * </p>
 * 
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="40%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <td>
 *            <strong>FOC</strong>
 *        </td>
 *        
 *        <td>
 *            Fixed PDF Objects Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>VOC</strong>
 *        </td>
 *        
 *        <td>
 *            Variable PDF Objects Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>FMC</strong>
 *        </td>
 *        
 *        <td>
 *             Fixed PDF Metadata Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>VMC</strong>
 *        </td>
 *        
 *        <td>
 *            Variable PDF Metadata Content.
 *        </td>
 *     </tr>
 * </TABLE>
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 02-06-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class PublishedDocument extends PublishedDocumentInfo {

	private static final long serialVersionUID = 3325783066499051496L;
	
	private byte[][] content;

	/**
	 * Constructs an empty instance. 
	 */		
	public PublishedDocument() {
	}

	/**
	 * Sets document content organized in one or more partitions.
	 * 
	 * @param content Document content.
	 * 
	 * @see #getContent()
	 */
	public void setContent(byte[][] content) {
		this.content = content;
	}

	/**
	 * Gets document content organized in one or more partitions.
	 * 
	 * @return Document content.
	 * 
	 * @see #setContent(byte[][])
	 */
	public byte[][] getContent() {
		return content;
	}

	/**
	 * Writes document content to the specified output stream.
	 * 
	 * @param output Output stream
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public void writeTo(OutputStream output) throws IOException {

		for (int i = 0; i < content.length; i++) {
			output.write(content[i]);
		}
	}

	/**
	 * Gets document content size.
	 * 
	 * @return Document content size.
	 */
	public int getSize() {
		int size = 0;

		for (int i = 0; i < content.length; i++) {
			 size += content[i].length;
		}

		return size;
	}
}
