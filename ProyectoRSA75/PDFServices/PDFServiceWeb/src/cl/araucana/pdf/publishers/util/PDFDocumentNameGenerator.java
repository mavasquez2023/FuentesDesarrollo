

/*
 * @(#) PDFDocumentNameGenerator.java    1.0 19-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.util;


import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * This utility class provides a general mechanism to automatize
 * PDF document pathnames generation. It's very useful to save
 * collections of published PDF document instances.
 *
 * <p> The generation is based on a derivable pattern from a list of valid
 * component part names and separators between them. When <b>pattern</b>
 * is specified as <i>String</i> this must complaint the following syntax:
 * </p>
 *
 *<TABLE BORDER="1" WIDTH="40%" CELLPADDING="3" CELLSPACING="0" ALIGN="center">
 *    <TR>
 *        <TD ALIGN="center">
 *            name1<b>{</b>sep1<b>}</b>name2<b>{</b>sep2<b>}</b>
 *            ... nameN<b>{sepN-1}</b>
 *        </TD>
 *    </TR>
 *<TABLE>
 *
 * <BR>
 * 
 * <p>
 * where separators can be empty or any string that not contains characters
 * '<b>{</b>' and '<b>}</b>'.
 * </p>
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
 *            <TD> 19-08-2008 </TD>
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
public class PDFDocumentNameGenerator {

	private static Pattern pattern;

	private int[] components;
	private String[] separators;

	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}

	/**
	 * Constructs a pathname generator from the specified <code>names</code>
	 * and <code>pattern</code>.
	 * 
	 * @param names Names.
	 * 
	 * @param pattern Pathname pattern.
	 * 
	 * @see #PDFDocumentNameGenerator(int[], String[])
	 * @see #PDFDocumentNameGenerator(int[], String)
	 */
	public PDFDocumentNameGenerator(String[] names, String pattern) {
		int[] components = new int[names.length];
		String[] separators = new String[components.length];
		int nNames = 0;
		int offset = 0;
		int startIndex;
		String oPattern = pattern;

		pattern += "{}";

		while ((startIndex = pattern.indexOf('{', offset)) >= 0) {
			String name = pattern.substring(offset, startIndex);
			boolean nameFound = false;

			for (int i = 0; i < names.length; i++) {
				if (names[i].equals(name)) {
					components[nNames++] = i;
					nameFound = true;

					break;
				}
			}

			if (!nameFound) {
				throw new IllegalArgumentException(
						  "Unknown name '" + name + "' "
						+ "in pattern '" + oPattern + "'");
			}

			int endIndex = pattern.indexOf('}', startIndex);

			if (endIndex == -1) {
				throw new IllegalArgumentException(
						  "Name '" + name + "' without separator "
						+ "in pattern '" + oPattern + "'");
			}

			String separator = pattern.substring(startIndex + 1, + endIndex);

			if (endIndex - startIndex > 2) {
				throw new IllegalArgumentException(
						    "Invalid separator '"	+ separator + "' "
						  + "in pattern '" + oPattern + "'");
			}

			separators[nNames - 1] = separator;
			offset = endIndex + 1;
		}

		if (nNames == 0) {
			throw new IllegalArgumentException(
				"No names was found in pattern '" + oPattern + "'");
		}

		this.components = new int[nNames];
		this.separators = new String[nNames - 1];

		System.arraycopy(
				components, 0, this.components, 0, this.components.length);

		System.arraycopy(
				separators, 0, this.separators, 0, this.separators.length);
	}

	/**
	 * Constructs a pathname generator from the specified component
	 * part indexes and using a common component <code>separator</code>.
	 * 
	 * @param components Component part indexes.
	 * 
	 * @param separator Common component separator.
	 * 
	 * @see #PDFDocumentNameGenerator(String[], String)
	 * @see #PDFDocumentNameGenerator(int[], String[])
	 */
	public PDFDocumentNameGenerator(int[] components, String separator) {
		this.components = components;
		separators = new String[components.length - 1];

		for (int i = 0; i < separators.length; i++) {
			separators[i] = separator;
		}
	}

	/**
	 * Constructs a pathname generator from the specified component
	 * part indexes and <code>separators</code>.
	 * 
	 * @param components Component part indexes.
	 * 
	 * @param separators Component separators.
	 * 
	 * @see #PDFDocumentNameGenerator(String[], String)
	 * @see #PDFDocumentNameGenerator(int[], String)
	 */	
	public PDFDocumentNameGenerator(int[] components, String[] separators) {
		if (separators.length != components.length - 1) {
			throw new IllegalArgumentException("Unexpected separators");
		}

		this.components = components;
		this.separators = separators;
	}

	/**
	 * Gets component part indexes.
	 * 
	 * @return Component part indexes.
	 * 
	 * @see #getSeparators()
	 */
	public int[] getComponents() {
		return components;
	}

	/**
	 * Gets component part separators.
	 * 
	 * @return Component part separators.
	 * 
	 * @see #getComponents()
	 */	
	public String[] getSeparators() {
		return separators;
	}

	/**
	 * Generates pathname for a PDF document which document ID
	 * is <code>docID</code>. The pathname ends with <b>.pdf</b>
	 * file extension.
	 * 
	 * @param docID Document ID.
	 * 
	 * @return generated pathname.
	 */
	public String generateName(String docID) {
		String name = "";
		String[] docIDComponents = pattern.split(docID);

		for (int i = 0; i < components.length; i++) {
			int index = components[i];

			if (0 <= index && index < docIDComponents.length) {
				name += docIDComponents[index];
			} else {
				name += "unknown";
			}

			if (i + 1 < components.length) {
				name += separators[i];
			}
		}

		return name + ".pdf";
	}

	/**
	 * Displays usage help to standard error.
	 */
	public static void usage() {
		System.err.println(
				  "Usage: <docID> <nComponents> "
				+ "<component1> ... <componentN> <separators>");

		System.exit(1);
	}

	/**
	 * Class demostration.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			usage();
		}

		String docID = args[0];
		String pattern = args[1];
		String[] names = new String[args.length - 2];

		for (int i = 2; i < args.length; i++) {
			names[i - 2] = args[i];
		}

		System.out.println("docID = " + docID);
		System.out.println("Pattern: |" + pattern + "|");
		System.out.print("Names: ");

		for (int i = 0; i < names.length; i++) {
			System.out.print(names[i] + " ");
		}

		System.out.println();

		PDFDocumentNameGenerator generator =
				new PDFDocumentNameGenerator(names, pattern);

		System.out.println("Name  = " + generator.generateName(docID));
	}

/*
		String docID = args[0];
		int nComponents = Integer.parseInt(args[1]);
		int[] components = new int[nComponents];

		for (int i = 0; i < components.length; i++) {
			components[i] = Integer.parseInt(args[i + 2]);
		}

		int nSeparators = args.length - 2 - components.length;
		String[] separators = new String[nSeparators];

		for (int i = 0; i < separators.length; i++) {
			separators[i] = args[i + 2 + components.length];
		}

		System.out.println("docID = " + docID);

		System.out.print("components =");

		for (int i = 0; i < components.length; i++) {
			System.out.print(" " + components[i]);
		}

		System.out.println();

		System.out.print("separators =");

		for (int i = 0; i < separators.length; i++) {
			System.out.print(" " + separators[i]);
		}

		System.out.println();

		PDFDocumentNameGenerator ng;

		if (separators.length == 1) {
			ng = new PDFDocumentNameGenerator(components, separators[0]);
		} else {
			ng = new PDFDocumentNameGenerator(components, separators);
		}

		String name = ng.generateName(docID);

		System.out.println("Name = " + name);
	}
*/
}
