

/*
 * @(#) XDocStatsCommand.java    1.0 08-12-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Reports summaried statistics about published PDF document instances of one
 * or more document types.
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Syntax</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>xdocstats [-u={b|k|m|g}] [-s &lt;scope&gt;]
 *               [&lt;docType&gt; ... &lt;docType&gt;]</b>
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <p> The following are supported options:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Option</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Default</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>u=</strong></td>
 *        
 *        <td>
 *            Document size unit to be reported. The available units are:
 *            <BR>
 *            <BR>
 *            
 *            <TABLE ALIGN="CENTER" BORDER="1" WIDTH="40%"
 *                   CELLPADDING="3" CELLSPACING="0">
 *               <tr>
 *                   <th bgcolor="black">
 *                       <font color="white"><strong>Unit</strong></font>
 *                   </th>
 *        
 *                   <th bgcolor="black">
 *                      <font color="white"><strong>Description</strong></font>
 *                   </th>
 *               </tr>
 *               
 *               <tr>
 *                    <td>b</td>
 *                    <td>byte</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>k</td>
 *                    <td>kilobyte</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>m</td>
 *                    <td>megabyte</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>g</td>
 *                    <td>gigabyte</td>
 *               </tr>
 *            </TABLE>
 *            
 *            <BR>
 *            <BR>
 *        </td>
 *        
 *        <td>
 *            byte
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>s</strong></td>
 *        
 *        <td>
 *            Required documents
 *            <a href="{@docRoot}/extras/scope.html">scope</a>.
 *            If more than one document type is specified this scope must be
 *            common to everyone ones.
 *        </td>
 *        
 *        <td>
 *            {@link cl.araucana.pdf.publishers.Scope#ALL}
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <p> The following are supported arguments:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Argument</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Default</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>docType</strong></td>
 *        
 *        <td>
 *            Document type to report. Zero or more document types can be
 *            specified.
 *        </td>
 *        
 *        <td>
 *            All registered document types.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 08-12-2008 </TD>
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
public class XDocStatsCommand extends FPGPublisherSPICommand {

	private static final int UNIT_BYTE = 0;

	private static final double unitFactors[] = {
		1,			// BYTE
		1024,		// KILOBYTE
		1048576,	// MEGABYTE
		1073741824	// GIGABYTE
	};

	private static final String unitNames[] = {
		"byte",
		"KB",
		"MB",
		"GB"
	};

	private static final int FIXED_CONTENTS = 2;

	private static final int FOC = 0;
	private static final int FMC = 1;

	/**
	 * Constructs a <b>xdocstats SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public XDocStatsCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "xdocstats [-u={b|k|m|g}] [-s <scope>] "
				+ "[<docType> ... <docType>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		int sizeUnit = UNIT_BYTE;
		Scope scope = Scope.ALL;
		List docTypeNames = new ArrayList();

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-u=")) {
				if (args[i].length() != 4) {
					help(err);

					return;
				}

				int index = "bkmg".indexOf(args[i].charAt(3));

				if (index == -1) {
					help(err);

					return;
				}

				sizeUnit = index;
			} else if (args[i].equals("-s")) {
				if (i + 1 >= args.length) {
					help(err);
				}

				scope = new Scope(args[++i]);
			} else if (!args[i].startsWith("-")) {
				docTypeNames.add(args[i]);
			} else {
				help(err);

				return;
			}
		}

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();

			if (docTypeNames.size() == 0) {
				DocType[] docTypes = spi.getDocTypes();

				for (int i = 0; i < docTypes.length; i++) {
					docTypeNames.add(docTypes[i].getName());
				}
			}
		} catch (PDFPublisherException e) {
			err.println(e.getMessage());

			return;
		}

		reportStats(
				spi,
				out,
				err,
				docTypeNames,
				scope,
				sizeUnit);
	}

	private void reportStats(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, List docTypeNames,
			Scope scope, int sizeUnit) {

		DocStat[] totalStats = { new DocStat(), new DocStat() };

		out.println();

		out.println(
				  Padder.rpad("Name", 25)
				+ " "
				+ Padder.lpad("#Docs", 11)
				+ " "
				+ Padder.lpad("#PartDocs", 11)
				+ " "
				+ Padder.lpad("#NoPartDocs", 11)
				+ " "
				+ Padder.lpad("VOCSize", 15)
				+ " "
				+ Padder.lpad("VMCSize", 15)
				+ " "
				+ Padder.lpad("FOCSize", 15)
				+ " "
				+ Padder.lpad("FMCSize", 15)
				+ " "
				+ Padder.lpad("TotalSize", 15));

		out.println(Padder.rpad("", 141, '-'));

		for (int i = 0; i < docTypeNames.size(); i++) {
			String docTypeName = (String) docTypeNames.get(i);

			try {
				DocStat[] xStats = getStats(spi, docTypeName, scope);

				if (xStats[0].getDocumentCount() == 0) {
					continue;
				}

				for (int j = 0; j < xStats.length; j++) {
					DocStat stat = xStats[j];
					String label;
					String mark;

					totalStats[j].setDocumentCount(
							  totalStats[j].getDocumentCount()
							+ stat.getDocumentCount());

					totalStats[j].setPartitionedDocumentCount(
							  totalStats[j].getPartitionedDocumentCount()
							+ stat.getPartitionedDocumentCount());

					totalStats[j].setNonPartitionedDocumentCount(
							  totalStats[j].getNonPartitionedDocumentCount()
							+ stat.getNonPartitionedDocumentCount());

					totalStats[j].setVOCSize(
							  totalStats[j].getVOCSize()
							+ stat.getVOCSize());

					totalStats[j].setVMCSize(
							  totalStats[j].getVMCSize()
							+ stat.getVMCSize());

					totalStats[j].setFOCSize(
							  totalStats[j].getFOCSize()
							+ stat.getFOCSize());

					totalStats[j].setFMCSize(
							  totalStats[j].getFMCSize()
							+ stat.getFMCSize());

					if (j == 0) {
						label = docTypeName;
						mark = "L";
					} else {
						label = "";
						mark = "P";
					}

					out.println(
							  Padder.rpad(label, 23)
							+ " "
							+ mark
							+ " "
							+ formatNumber(stat.getDocumentCount(), 11)
							+ " "
							+ formatNumber(
									stat.getPartitionedDocumentCount(), 11)
							+ " "
							+ formatNumber(
									stat.getNonPartitionedDocumentCount(), 11)
							+ " "
							+ formatSizes(stat, sizeUnit, getTotalSize(stat)));
				}

				if (i + 1 < docTypeNames.size()) {
					out.println();
				}
			} catch (PDFPublisherException e) {}
		}

		out.println(Padder.rpad("", 141, '-'));

		for (int i = 0; i < totalStats.length; i++) {
			DocStat stat = totalStats[i];
			String mark = (i == 0) ? "L" : "P";

			out.println(
					  Padder.rpad("", 23)
					+ " "
					+ mark
					+ " "
					+ formatNumber(stat.getDocumentCount(), 11)
					+ " "
					+ formatNumber(
							stat.getPartitionedDocumentCount(), 11)
					+ " "
					+ formatNumber(
							stat.getNonPartitionedDocumentCount(), 11)
					+ " "
					+ formatSizes(stat, sizeUnit, getTotalSize(stat)));
		}

		long logicalTotalSize = getTotalSize(totalStats[0]);
		long physicalTotalSize = getTotalSize(totalStats[1]);

		if (logicalTotalSize > 0) {
			double optimization =
					  100.0
					* (logicalTotalSize - physicalTotalSize)
					/ logicalTotalSize;

			out.println();

			out.println(
					  Padder.rpad("", 24)
					+ "*** Storage Optimization: "
					+ Padder.toString(optimization, 2) + "%");
		}

		out.println(
					  Padder.rpad("", 24)
					+ "*** Storage Size Unit: "
					+ unitNames[sizeUnit]);
	}

	private DocStat[] getStats(FPGIntegratedPDFPublisherSPI spi,
			String docTypeName,	Scope scope) throws PDFPublisherException {

		DocStat[] xStats = new DocStat[2];

		Map versions = new HashMap();
		long[] logicalFixedSizes = new long[FIXED_CONTENTS];
		long[] physicalFixedSizes = new long[FIXED_CONTENTS];

		List stats =
				spi.getStats(
						docTypeName,
						FPGIntegratedPDFPublisherSPI.CRITERIA_SYSTEM,
						scope);

		Iterator iterator = stats.iterator();

		while (iterator.hasNext()) {
			DocStat stat = (DocStat) iterator.next();
			String key = stat.getKey();

			if (key == null) {
				xStats[1] = new DocStat(stat);
				key = "";

				stat.setFOCSize(logicalFixedSizes[FOC]);
				stat.setFMCSize(logicalFixedSizes[FMC]);

				xStats[0] = new DocStat(stat);
			} else {
				Integer _version = new Integer(stat.getVersion());
				Integer version = (Integer) versions.get(_version);

				if (version == null) {
					versions.put(_version, _version);

					physicalFixedSizes[FOC] += stat.getFOCSize();
					physicalFixedSizes[FMC] += stat.getFMCSize();
				}

				long nPartDocs = stat.getPartitionedDocumentCount();

				stat.setFOCSize(nPartDocs * stat.getFOCSize());
				stat.setFMCSize(nPartDocs * stat.getFMCSize());

				logicalFixedSizes[FOC] += stat.getFOCSize();
				logicalFixedSizes[FMC] += stat.getFMCSize();
			}
		}

		return xStats;
	}

	private long getTotalSize(DocStat stat) {
		return
				  stat.getVOCSize()
				+ stat.getVMCSize()
				+ stat.getFOCSize()
				+ stat.getFMCSize();
	}

	private String formatSizes(DocStat stat, int unit, long totalSize) {
		if (unit == UNIT_BYTE) {
			return
					  formatNumber(stat.getVOCSize(), 15)
					+ " "
					+ formatNumber(stat.getVMCSize(), 15)
					+ " "
					+ formatNumber(stat.getFOCSize(), 15)
					+ " "
					+ formatNumber(stat.getFMCSize(), 15)
					+ " "
					+ formatNumber(totalSize, 15);
		}

		return
				  formatSize(stat.getVOCSize(), unit, 15)
				+ " "
				+ formatSize(stat.getVMCSize(), unit, 15)
				+ " "
				+ formatSize(stat.getFOCSize(), unit, 15)
				+ " "
				+ formatSize(stat.getFMCSize(), unit, 15)
				+ " "
				+ formatSize(totalSize, unit, 15);
	}

	private String formatNumber(long value, int width) {
		if (value == 0) {
			return Padder.lpad("", width);
		}

		return Padder.lpad(Padder.padSeparators(value), width);
	}

	private String formatSize(long size, int unit, int width) {
		if (size == 0) {
			return Padder.lpad("", width);
		}

		double value = size / unitFactors[unit];

		String intPart = Padder.padSeparators((long) value);
		String s = Padder.toString(value, 2);
		String decimalPart = s.substring(s.length() - 2);

		return Padder.lpad(intPart + "," + decimalPart, width);
	}
}
