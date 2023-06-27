

/*
 * @(#) DocStatsCommand.java    1.0 28-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Reports statistics about published PDF document instances of
 * a document type using different grouping criteria.
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
 *            <b>docstats [-v] [-p] [-s] [-P] [-u={b|k|m|g}]
 *                        &lt;docType&gt; [&lt;scope&gt;]</b>
 *         </td>
 *     </tr>
 * </TABLE> 
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
 *        <td><strong>v</strong></td>
 *        
 *        <td>
 *            Statistics are grouped by document versions.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>p</strong></td>
 *        
 *        <td>
 *            Statistics are grouped by publications.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>s</strong></td>
 *        
 *        <td>
 *            Statistics are grouped by source systems.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>P</strong></td>
 *        
 *        <td>
 *            Statistics are grouped by publisher agents.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
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
 * </TABLE>
 *
 * <BR>
 * <BR>
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
 *            Document type. It's mandatory.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><a href="{@docRoot}/extras/scope.html">scope</a></td>
 *        
 *        <td>
 *            Required documents scope. It's optional.
 *        </td>
 *        
 *        <td>
 *            {@link cl.araucana.pdf.publishers.Scope#ALL}
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
 *            <TD> 28-10-2008 </TD>
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
public class DocStatsCommand extends FPGPublisherSPICommand {

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

	private static final String[] headers = {
		"Version",
		"Publication",
		"System",
		"Publisher"
	};

	private static final int FIXED_CONTENTS = 2;

	private static final int FOC = 0;
	private static final int FMC = 1;

	/**
	 * Constructs a <b>docstats SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public DocStatsCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "docstats [-v] [-p] [-s] [-P] [-u={b|k|m|g}] "
				+ "<docType> [<scope>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		boolean statsByVersion = false;
		boolean statsByPublication = false;
		boolean statsBySystem = false;
		boolean statsByPublisher = false;
		boolean statsFull = true;
		int sizeUnit = UNIT_BYTE;
		String docTypeName = null;
		Scope scope = Scope.ALL;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-v")) {
				statsByVersion = true;
				statsFull = false;
			} else if (args[i].equals("-p")) {
				statsByPublication = true;
				statsFull = false;
			} else if (args[i].equals("-s")) {
				statsBySystem = true;
				statsFull = false;
			} else if (args[i].equals("-P")) {
				statsByPublisher = true;
				statsFull = false;
			} else if (args[i].startsWith("-u=")) {
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
			} else if (!args[i].startsWith("-")) {
				if (docTypeName == null) {
					docTypeName = args[i];
				} else {	// Scope.
					scope = new Scope(args[i]);
				}
			} else {
				help(err);

				return;
			}
		}

		if (docTypeName == null) {
			help(err);

			return;
		}

		if (statsFull) {
			statsByVersion = true;
			statsByPublication = true;
			statsBySystem = true;
			statsByPublisher = true;
		}

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
		} catch (PDFPublisherException e) {
			err.println(e.getMessage());

			return;
		}

		if (statsByVersion) {
			reportStats(
					spi,
					out,
					err,
					docTypeName,
					FPGIntegratedPDFPublisherSPI.CRITERIA_VERSION,
					scope,
					sizeUnit);
		}

		if (statsByPublication) {
			reportStats(
					spi,
					out,
					err,
					docTypeName,
					FPGIntegratedPDFPublisherSPI.CRITERIA_PUBLICATION,
					scope,
					sizeUnit);
		}

		if (statsBySystem) {
			reportStats(
					spi,
					out,
					err,
					docTypeName,
					FPGIntegratedPDFPublisherSPI.CRITERIA_SYSTEM,
					scope,
					sizeUnit);
		}

		if (statsByPublisher) {
			reportStats(
					spi,
					out,
					err,
					docTypeName,
					FPGIntegratedPDFPublisherSPI.CRITERIA_PUBLISHER,
					scope,
					sizeUnit);
		}
	}

	private void reportStats(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String docTypeName,
			int criteria, Scope scope, int sizeUnit) {

		boolean versionCriteria =
				criteria == FPGIntegratedPDFPublisherSPI.CRITERIA_VERSION;

		String versionHeader = (versionCriteria) ? "" : "Version";

		out.println();

		out.println(
				  Padder.rpad(headers[criteria], 11)
				+ " "
				+ Padder.rpad(versionHeader, 8)
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

		out.println(Padder.rpad("", 136, '-'));

		try {
			Map versions = new HashMap();
			long[] logicalFixedSizes = new long[FIXED_CONTENTS];
			long[] physicalFixedSizes = new long[FIXED_CONTENTS];
			DocStat physicalTotalStat = null;
			long totalSize = 0;
			List stats = spi.getStats(docTypeName, criteria, scope);
			Iterator iterator = stats.iterator();

			while (iterator.hasNext()) {
				DocStat stat = (DocStat) iterator.next();
				String key = stat.getKey();
				String versionValue;

				if (key == null) {
					physicalTotalStat = stat;
					key = "";
					versionValue = "Logical ";

					stat.setFOCSize(logicalFixedSizes[FOC]);
					stat.setFMCSize(logicalFixedSizes[FMC]);

					out.println(Padder.rpad("", 136, '-'));
					out.println("Totals:");
				} else {
					if (versionCriteria) {
						versionValue = "        ";
					} else {
						versionValue = Padder.rpad(stat.getVersion(), 8);
					}

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

				totalSize =
						  stat.getVOCSize()
						+ stat.getVMCSize()
						+ stat.getFOCSize()
						+ stat.getFMCSize();

				out.println(
						  Padder.rpad(key, 11)
						+ " "
						+ versionValue
						+ " "
						+ formatNumber(stat.getDocumentCount(), 11)
						+ " "
						+ formatNumber(
								stat.getPartitionedDocumentCount(), 11)
						+ " "
						+ formatNumber(
								stat.getNonPartitionedDocumentCount(), 11)
						+ " "
						+ formatSizes(stat, sizeUnit, totalSize));
			}

			if (physicalTotalStat != null) {
				DocStat stat = physicalTotalStat;

				stat.setFOCSize(physicalFixedSizes[FOC]);
				stat.setFMCSize(physicalFixedSizes[FMC]);

				long logicalTotalSize = totalSize;

				long physicalTotalSize =
						  stat.getVOCSize()
						+ stat.getVMCSize()
						+ stat.getFOCSize()
						+ stat.getFMCSize();

				out.println(
						  Padder.rpad("", 11)
						+ " "
						+ "Physical"
						+ " "
						+ formatNumber(stat.getDocumentCount(), 11)
						+ " "
						+ formatNumber(
								stat.getPartitionedDocumentCount(), 11)
						+ " "
						+ formatNumber(
								stat.getNonPartitionedDocumentCount(), 11)
						+ " "
						+ formatSizes(stat, sizeUnit, physicalTotalSize));

				out.println();

				if (logicalTotalSize > 0) {
					double optimization =
							  100.0
							* (logicalTotalSize - physicalTotalSize)
							/ logicalTotalSize;

					out.println(
							  Padder.rpad("", 12)
							+ "*** Storage Optimization: "
							+ Padder.toString(optimization, 2) + "%");
				}

				out.println(
							  Padder.rpad("", 12)
							+ "*** Storage Size Unit: "
							+ unitNames[sizeUnit]);
			}
		} catch (PDFPublisherException e) {
			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		}
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
