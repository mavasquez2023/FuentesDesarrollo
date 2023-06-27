

/*
 * @(#) PDFServiceShell.java    1.0 24-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cl.araucana.core.util.shell.Shell;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdfservice.command.AbortPDFServiceCommand;
import cl.araucana.pdfservice.command.CleanPDFServiceCommand;
import cl.araucana.pdfservice.command.DocTypesPDFServiceCommand;
import cl.araucana.pdfservice.command.ImplementationsPDFServiceCommand;
import cl.araucana.pdfservice.command.PDFServiceCommand;
import cl.araucana.pdfservice.command.ProcessStatusPDFServiceCommand;
import cl.araucana.pdfservice.command.StartPDFServiceCommand;


/**
 * This is a specialized shell based on CLI (<b>Command Line Interface</b>)
 * {@link cl.araucana.core.util.shell.Shell} to manage different
 * {@link PDFProcess} instances.
 *
 * <p> This shell supports the following collection of 
 * {@link cl.araucana.pdfservice.command.PDFServiceCommand} commands:
 * </p>
 *
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="70%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Command</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.AbortPDFServiceCommand
 *        abort}
 *        </td>
 *        
 *        <td>
 *            Aborts one or more PDF processes.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.CleanPDFServiceCommand
 *        clean}
 *        </td>
 *        
 *        <td>
 *            Cleanups all ended or aborted PDF processes.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.DocTypesPDFServiceCommand
 *        doctypes}
 *        </td>
 *        
 *        <td>
 *            Reports available document types to produce/consume.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.ImplementationsPDFServiceCommand
 *        implementations}
 *        </td>
 *        
 *        <td>
 *            Reports all available document type implementations.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.ProcessStatusPDFServiceCommand
 *        ps}
 *        </td>
 *        
 *        <td>
 *            Reports information about PDF processes.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdfservice.command.StartPDFServiceCommand
 *        start}
 *        </td>
 *        
 *        <td>
 *            Starts a new PDF process to produce/consume a document type.
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
 *            <TD> 24-04-2008 </TD>
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
public class PDFServiceShell extends Shell {

	private static int shellID = 0;

	private int id;

	private PDFServiceCommand abortCommand;
	private PDFServiceCommand psCommand;

	private boolean killed;

	/**
	 * Constructs a shell instance associated to <code>service</code> without
	 * a default init script and using standard input, standard output and
	 * standard error.
	 * 
	 * <p> Processes started with this instance will can to use <b>spawn</b>
	 * option to execute in background mode.
	 * </p>
	 * 
	 * @param service Associated PDF Service instance.
	 * 
	 * @throws PDFPublisherException If cannot construct shell instance.
	 */	
	public PDFServiceShell(PDFService service) {
		this(service, true);
	}

	/**
	 * Constructs a shell instance associated to <code>service</code> without
	 * a default init script and using standard input, standard output and
	 * standard error and <code>spawnable</code> control flag.
	 * 
	 * <p> Processes started with this instance will can to use
	 * <b>spawn</b> option to execute in background mode only when
	 * <code>spawnable</code> control flag is enabled. If this flag is disabled
	 * then option will be ignored.
	 * </p>
	 * 
	 * @param service Associated PDF Service instance.
	 * 
	 * @param spawnable Spawn option control flag.
	 * 
	 * @throws PDFPublisherException If cannot construct shell instance.
	 */		
	public PDFServiceShell(PDFService service, boolean spawnable) {
		this(service, null, System.in, System.out, System.err, spawnable);
	}

	/**
	 * Constructs a shell instance associated to <code>service</code> using
	 * the specified default init script and input and outputs.
	 * 
	 * <p> Processes started with this instance will can to use
	 * <b>spawn</b> option to execute in background mode only when
	 * <code>spawnable</code> control flag is enabled. If this flag is disabled
	 * then option will be ignored.
	 * </p>
	 * 
	 * @param service Associated PDF Service instance.
	 * 
	 * @param defaultScript Default init script.
	 * 
	 * @param in Shell normal input.
	 * 
	 * @param out Shell normal output.
	 * 
	 * @param err Shell error output.
	 * 
	 * @param spawnable Spawn option control flag.
	 * 
	 * @throws PDFPublisherException If cannot construct shell instance.
	 */	
	public PDFServiceShell(PDFService service, String defaultScript,
			InputStream in, PrintStream out, PrintStream err,
			boolean spawnable) {

		super(getParams(defaultScript), in, out, err);

		synchronized (PDFServiceShell.class) {
			id = ++shellID;
		}

		abortCommand = new AbortPDFServiceCommand(service);

		addCommand("abort", abortCommand);
		addCommand("clean", new CleanPDFServiceCommand(service));
		addCommand("doctypes", new DocTypesPDFServiceCommand(service));

		addCommand(
				"implementations",
				new ImplementationsPDFServiceCommand(service));

		psCommand = new ProcessStatusPDFServiceCommand(service);

		addCommand("ps", psCommand);
		addCommand("start", new StartPDFServiceCommand(service, id, spawnable));
	}

	private static Map getParams(String defaultScript) {
		Map params = new HashMap();

		String title =
				  "PDF Service Shell Version 1.0 24/04/2008.\n"
				+ "          La Araucana C.C.A.F.\n\n";

		params.put(Shell.TITLE, title);
		params.put(Shell.PROMPT, "pdfservice> ");

		if (defaultScript == null) {
			defaultScript = System.getProperty("user.home") + "/pdfservice.ini";
		}

		params.put(Shell.DEFAULT_SCRIPT, defaultScript);
		params.put(Shell.DEBUG_SYSTEM_PROPERTY_NAME, "pdf.debug");

		return params;
	}

	/**
	 * Gets unique shell ID.
	 * 
	 * @return Unique shell ID.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Executes <code>cmd</code> shell command line.
	 */	
	public boolean execute(String cmd) throws Exception {

		if (killed) {
			return true;
		}

		String echo = getVariable("echo");

		if (echo.equals("on")) {
			out.println("execute: |" + cmd + "|");
		}

		return super.execute(cmd);
	}

	/**
	 * Executes <code>cmd</code> shell command with <code>args</code> arguments.
	 */		
	public void execute(String cmd, Object[] args) {
		if (killed) {
			return;
		}

		PDFServiceCommand command = (PDFServiceCommand) getCommand(cmd);

		if (command == null) {
			return;
		}

		command.execute((String[]) args, in, out, err);
	}

	/**
	 * Displays usage help to the <code>cmd</code> shell command.
	 */	
	public void help(String cmd) {
		PDFServiceCommand command = (PDFServiceCommand) getCommand(cmd);

		if (command == null) {
			return;
		}

		command.help(err);
	}

	/**
	 * Kills or aborts this shell instance. No more shell commands will can
	 * be executed.
	 */
	public void kill() {
		killed = true;

		ByteArrayOutputStream $out = new ByteArrayOutputStream(8192);
		PrintStream out = new PrintStream($out);

		try {
			psCommand.execute(new String[0], in, out, err);
		} catch (Exception e) {
		} finally {
			try {
				out.close();
				$out.close();
			} catch (IOException e) {}
		}

		/*
		 *  Aborts all processes started from this shell.
		 *
		 *  Uses ps command's output to filter targered processes.
		 */
		String[] abortArgs = { "" };

		BufferedReader reader =
				new BufferedReader(new StringReader($out.toString()));

		String line;

		try {
			while ((line = reader.readLine()) != null) {

				/*
				 *
				 *  Candidate lines:
				 *
				 *   <PID> <SID> ...
				 *
				 *  Example:
				 *
				 *  00001 0002 banmedica.pwf   ...
				 *
				 */
				if (line.length() > 0 && Character.isDigit(line.charAt(0))) {
					String[] tokens = line.split(" ");

					if (tokens.length > 2) {
						int pid = Integer.parseInt(tokens[0]);
						int sid = Integer.parseInt(tokens[1]);

						if (sid == id) {
							abortArgs[0] = Integer.toString(pid);

							abortCommand.execute(abortArgs, in, this.out, err);
						}
					}
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}

		destroy();
	}

	/**
	 * Destroyes or finalizes this shell instance.
	 */
	public void destroy() {
		Set commandsList = getCommandsList();
		Iterator commandsListIT = commandsList.iterator();

		while (commandsListIT.hasNext()) {
			String commandName = (String) commandsListIT.next();

			PDFServiceCommand command =
					(PDFServiceCommand) getCommand(commandName);

			command.destroy();
		}

		removeCommandsList();

		abortCommand = null;
		psCommand = null;
	}

	/**
	 * Shell launcher.
	 * 
	 * @param args Shell arguments.
	 */	
	public static void main(String[] args) {
		PDFService service = null;

		try {
			service = new PDFService();

			/*
			 * Injects authorized systems using fpg.shell.systems
			 * system property.
			 *
			 * Syntax: sysName<1>[:sysName<2>:...:sysName<n>]
			 */
			String systems = System.getProperty("pdfservice.shell.systems");

			if (systems != null) {
				Map authorizedSystems = new TreeMap();
				String[] sysNames = systems.split(":");

				for (int i = 0; i < sysNames.length; i++) {
					authorizedSystems.put(sysNames[i], sysNames[i]);
				}

				service.setAuthorizedSystems(authorizedSystems);
			}
		} catch (PDFServiceException e) {
			System.out.println("PDFService cannot be initialized.");

			e.printStackTrace();

			return;
		}

		PDFServiceShell shell = new PDFServiceShell(service);

		try {
			shell.execute();
		} catch (Exception e) {
			System.err.println(
					"Shell was aborted. [cause=" + e.getMessage() + "]");
		}
	}
}
