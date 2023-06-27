

/*
 * @(#) ProcessStatusPDFServiceCommand.java    1.0 25-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import java.text.DecimalFormat;

import cl.araucana.pdfservice.PDFService;
import cl.araucana.pdfservice.PDFProcess;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.Array;
import cl.araucana.core.util.Padder;


/**
 * Reports information about PDF processes.
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
 *        <td><b>ps [ [-l | -t] [&lt;pid&gt; ...] | -s &lt;sid&gt; ]</b></td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * 
 * <p> The following are supported options:
 * </p>
 * 
 * <BR>
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
 *        <td><strong>l</strong></td>
 *        
 *        <td>
 *            Detailed report format is used.
 *        </td>
 *        
 *        <td>
 *            Summary report format is used.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>t</strong></td>
 *        
 *        <td>
 *            Reports uncaught process error or exception.
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
 *            Filters PDF processes that belong to the specified session.
 *        </td>
 *        
 *        <td>
 *            Sessions aren't filtered.
 *        </td>
 *     </tr>
 * </TABLE>
 *
 * <BR>
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
 *        <td><strong>pid</strong></td>
 *        
 *        <td>
 *            Process ID to the process to be aborted. It can be specified
 *            zero or more times.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 * </TABLE>
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
 *            <TD> 25-04-2008 </TD>
 *            <TD align="center">  2.0 </TD>
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
public class ProcessStatusPDFServiceCommand extends PDFServiceCommand {

	private String summariedHeader;
	private String summariedLine;

	private DecimalFormat workPercentDF = new DecimalFormat("##0.00");
	private DecimalFormat rateDF = new DecimalFormat("0.00");

	/**
	 * Constructs a <b>Process Status PDFService</b> command associated to the
	 * <code>service</code>.
	 * 
	 * @param service Associated <code>service</code> instance.
	 */
	public ProcessStatusPDFServiceCommand(PDFService service) {
		super(service);

		summariedHeader =
				  Padder.rpad("PID", 5, ' ') + " "
				+ Padder.rpad("SID", 4, ' ') + " "
				+ Padder.rpad("DOCTYPE", 24, ' ') + " "
				+ Padder.rpad("OWNER", 10, ' ') + " "
				+ Padder.rpad("STATE", 7, ' ') + " "
				+ Padder.rpad("START TIME", 19, ' ') + " "
				+ Padder.rpad("TIME (ms)", 10, ' ') + " "
				+ Padder.rpad("PWUNITS", 7, ' ') + " "
				+ Padder.rpad("WUNITS", 7, ' ') + " "
				+ Padder.rpad("%WORK", 6, ' ') + " "
				+ Padder.rpad("END TIME", 19, ' ') + " "
				+ "RATE (docs/s)";

		summariedLine = Padder.rpad("", 142, '-');
	}

	public void help(PrintStream err) {
		err.println("ps [ [-l | -t] [<pid> ...] | -s <sid> ]");
	}

	public void execute(String[] args, InputStream in, PrintStream out,
			PrintStream err) {

		boolean longReport = (args.length >= 1) && args[0].equals("-l");
		boolean reportThrowable = (args.length >= 1) && args[0].equals("-t");
		boolean filteredBySID = (args.length >= 1) && args[0].equals("-s");
		int sid = 0;

		if (longReport && reportThrowable) {
			help(err);

			return;
		}

		if ((longReport || reportThrowable) && filteredBySID) {
			help(err);

			return;
		}

		if (filteredBySID) {
			try {
				if (args.length != 2) {
					throw new Exception();
				}

				sid = Integer.parseInt(args[1]);
				args = new String[0];
			} catch (Exception e) {
				help(err);

				return;
			}
		}

		int nPids =
				(longReport || reportThrowable) ? args.length - 1 : args.length;

		if (reportThrowable) {
			exceptionReport(nPids, args, out, err);
		} else {
			normalReport(longReport, sid, nPids, args, out, err);
		}
	}

	private void normalReport(boolean longReport, int sid, int nPids,
			String[] args, PrintStream out, PrintStream err) {

		int nEndedProcesses = 0;
		long totalTime = 0L;
		int totalProcessedWorkUnits = 0;
		int totalWorkUnits = 0;

		if (nPids > 0) {
			if (!longReport) {
				out.println(summariedHeader);
				out.println(summariedLine);
			}

			for (int i = (longReport) ? 1 : 0; i < args.length; i++) {
				int pid;

				try {
					pid = Integer.parseInt(args[i]);
				} catch(NumberFormatException e) {
					out.println("Invalid process id '" + args[i] + "'.");

					continue;
				}

				PDFProcess.Info info = service.getInfo(pid);

				if (info != null) {
					if (longReport) {
						out.println(detailedInfo(info));
					} else {
						out.println(summariedInfo(info));

						if (info.state == PDFProcess.ENDED) {
							nEndedProcesses++;

							totalTime += info.elapsedTime;
							totalProcessedWorkUnits += info.processedWorkUnits;
							totalWorkUnits += info.workUnits;
						}
					}
				} else {
					out.println("Invalid process " + pid + ".");
				}
			}

			if (!longReport && nEndedProcesses > 0) {
				totalReport(
						nEndedProcesses,
						totalTime,
						totalProcessedWorkUnits,
						totalWorkUnits, out);
			}

			return;
		}

		PDFProcess.Info[] infos = service.getAllProcessesInfo();

		if (infos.length > 0) {
			if (!longReport) {
				out.println(summariedHeader);
				out.println(summariedLine);
			}

			for (int i = 0; i < infos.length; i++) {
				if (longReport) {
					out.println(detailedInfo(infos[i]));
				} else if (sid == 0 || infos[i].sessionID == sid) {
					out.println(summariedInfo(infos[i]));

					if (infos[i].state == PDFProcess.ENDED) {
						nEndedProcesses++;

						totalTime += infos[i].elapsedTime;
						totalProcessedWorkUnits += infos[i].processedWorkUnits;
						totalWorkUnits += infos[i].workUnits;
					}
				}
			}

			if (!longReport && nEndedProcesses > 0) {
				totalReport(
						nEndedProcesses,
						totalTime,
						totalProcessedWorkUnits,
						totalWorkUnits, out);
			}
		}
	}

	private void totalReport(int nEndedProcesses, long totalTime,
			int totalProcessedWorkUnits, int totalWorkUnits, PrintStream out) {

		String sPercent =
				getWorkPercent(totalProcessedWorkUnits, totalWorkUnits);

		out.println(summariedLine);

		String sRate = getRate(totalProcessedWorkUnits, totalTime);

		out.println(
				  Padder.rpad("", 74, ' ') + " "
				+ Padder.rpad(totalTime, 10, ' ') + " "
				+ Padder.rpad(totalProcessedWorkUnits, 7, ' ') + " "
				+ Padder.rpad(totalWorkUnits, 7, ' ') + " "
				+ Padder.lpad(sPercent, 6, ' ') + " "
				+ Padder.rpad("", 19, ' ') + " "
				+ Padder.lpad(sRate, 6, ' '));
	}

	private void exceptionReport(int nPids, String[] args, PrintStream out,
			PrintStream err) {

		if (nPids > 0) {
			for (int i = 1; i < args.length; i++) {
				int pid;

				try {
					pid = Integer.parseInt(args[i]);
				} catch(NumberFormatException e) {
					err.println("Invalid process id '" + args[i] + "'.");

					continue;
				}

				PDFProcess.Info info = service.getInfo(pid);

				if (info == null) {
					err.println("Invalid process " + pid + ".");

					continue;
				}

				if (info.throwable != null) {
					err.println(
							"Process " + Padder.lpad(info.pid, 5, '0') + ":");

					info.throwable.printStackTrace(err);
				}
			}

			return;
		}

		PDFProcess.Info[] infos = service.getAllProcessesInfo();

		if (infos.length > 0) {
			for (int i = 0; i < infos.length; i++) {
				PDFProcess.Info info = infos[i];

				if (info.throwable != null) {
					err.println(
							"Process " + Padder.lpad(info.pid, 5, '0') + ":");

					info.throwable.printStackTrace(err);
				}
			}
		}
	}

	private String getWorkPercent(PDFProcess.Info info) {
		return getWorkPercent(
				info.processedWorkUnits, info.workUnits, info.isEnded());
	}

	private String getWorkPercent(int processedWorkUnits, int workUnits) {
		return getWorkPercent(processedWorkUnits, workUnits, true);
	}

	private String getWorkPercent(int processedWorkUnits, int workUnits,
			boolean ended) {

		if (workUnits == 0) {
			return (ended) ? "100.00" : "undef";
		}

		double percent = 100.0 * processedWorkUnits / workUnits;

		return workPercentDF.format(percent);
	}

	private String getRate(PDFProcess.Info info) {
		return getRate(info.processedWorkUnits, info.elapsedTime);
	}

	private String getRate(int processedWorkUnits, long elapsedTime) {
		if (elapsedTime == 0) {
			return "undef";
		}

		double rate = 1000.0 * processedWorkUnits / elapsedTime;

		return rateDF.format(rate);
	}

	private String summariedInfo(PDFProcess.Info info) {
		String endTime =
				(info.isEnded())
						? new AbsoluteDateTime(info.endTime).toString()
						: Padder.rpad("", 19, ' ');

		return
				  Padder.lpad(info.pid, 5, '0') + " "
				+ Padder.lpad(info.sessionID, 4, '0') + " "
				+ Padder.rpad(info.docTypeName, 24, ' ') + " "
				+ Padder.rpad(info.owner, 10, ' ') + " "
				+ Padder.rpad(PDFProcess.stateNames[info.state], 7, ' ') + " "
				+ new AbsoluteDateTime(info.beginTime) + " "
				+ Padder.rpad(info.elapsedTime, 10, ' ') + " "
				+ Padder.rpad(info.processedWorkUnits, 7, ' ') + " "
				+ Padder.rpad(info.workUnits, 7, ' ') + " "
				+ Padder.lpad(getWorkPercent(info), 6, ' ') + " "
				+ endTime + " "
				+ Padder.lpad(getRate(info), 6, ' ');
	}

	private String detailedInfo(PDFProcess.Info info) {
		String endTime =
				(info.isEnded())
						? new AbsoluteDateTime(info.endTime).toString()
						: "NO ENDED";

		String abortMessage = "";

		if (info.state == PDFProcess.ABEND) {
			abortMessage =
					  "abort message                   = \""
					+ info.abortMessage + "\"\n";
		}

		return
				  "pid                             = " + info.pid + "\n"
				+ "session id                      = " + info.sessionID + "\n"
				+ "doctype                         = " + info.docTypeName + "\n"
				+ "owner                           = " + info.owner + "\n"
				+ "state                           = "
				+ PDFProcess.stateNames[info.state] + "\n"
				+ "begin time                      = "
				+ new AbsoluteDateTime(info.beginTime) + "\n"
				+ "elapsed time                    = "
				+ info.elapsedTime + " ms\n"
				+ "processed work units            = "
				+ info.processedWorkUnits + "\n"
				+ "work units                      = "
				+ info.workUnits + "\n"
				+ "%work                           = "
				+ getWorkPercent(info) + "\n"
				+ "rate                            = "
				+ getRate(info) + " docs/s\n"
				+ "producer request queue size     = "
				+ info.producerRequestQueueSize + "\n"
				+ "producer request queue capacity = "
				+ info.producerRequestQueueCapacity + "\n"
				+ "consumer request queue size     = "
				+ info.consumerRequestQueueSize + "\n"
				+ "consumer request queue capacity = "
				+ info.consumerRequestQueueCapacity + "\n"
				+ "provider options                = ["
				+ Array.toString(info.providerOptions) + "]\n"
				+ "producer options                = ["
				+ Array.toString(info.producerOptions)+ "]\n"
				+ "consumer options                = ["
				+ Array.toString(info.consumerOptions) + "]\n"
				+ "end time                        = " + endTime + "\n"
				+ abortMessage;
	}
}
