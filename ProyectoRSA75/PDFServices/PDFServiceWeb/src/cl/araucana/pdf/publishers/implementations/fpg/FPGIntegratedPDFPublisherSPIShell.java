

/*
 * @(#) FPGIntegratedPDFPublisherSPIShell.java    1.0 16-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import java.io.InputStream;
import java.io.PrintStream;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.JDBCUtils;
import cl.araucana.core.util.Property;
import cl.araucana.core.util.shell.Shell;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;

import cl.araucana.pdf.publishers.implementations.fpg.command.*;


/**
 * This is a specialized shell based on CLI (<b>Command Line Interface</b>)
 * {@link cl.araucana.core.util.shell.Shell} to manage different
 * {@link FPGIntegratedPDFPublisherSPI} instances.
 * 
 * <p> This shell supports the following collection of 
 * {@link
 * cl.araucana.pdf.publishers.implementations.fpg.command.FPGPublisherSPICommand
 * }
 * commands:
 * </p>
 *
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
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
 *        cl.araucana.pdf.publishers.implementations.fpg.command.CleanupCommand
 *        cleanup}
 *        </td>
 *        
 *        <td>
 *            Cleanups all empty publications of a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.DocStatsCommand
 *        docstats}
 *        </td>
 *        
 *        <td>
 *            Reports statistics about published PDF document instances of
 *            a document type using different grouping criteria.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.DocTypesCommand
 *        doctypes}
 *        </td>
 *        
 *        <td>
 *            Lists registered document type names and its metadata.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.ProvidersCommand
 *        providers}
 *        </td>
 *        
 *        <td>
 *            Lists all configured <i>FPG Integrated PDF Publisher SPI</i>.
 *            Addtionally it permits to set a provider as default one to be
 *            used by the remainding commands.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.PublicationsCommand
 *        publications}
 *        </td>
 *        
 *        <td>
 *            Reports registered publications of a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.PublishCommand
 *        publish}
 *        </td>
 *        
 *        <td>
 *            Publishes a set of PDF files in a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.PublishersCommand
 *        publishers}
 *        </td>
 *        
 *        <td>
 *            Lists all registered publisher agents. Additionally it permits to
 *            set a publisher agent as default one to be used in
 *            publication operations.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.RegisterCommand
 *        register}
 *        </td>
 *        
 *        <td>
 *            Registers a new source system, publisher agent or document type. 
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.SaveCommand
 *        save}
 *        </td>
 *        
 *        <td>
 *            Retrieves and saves published PDF document instances of a
 *            document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.SelectCommand
 *        select}
 *        </td>
 *        
 *        <td>
 *            Retrieves published PDF documents metadata of a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.SystemsCommand
 *        systems}
 *        </td>
 *        
 *        <td>
 *            Lists all registered source systems. Additionally it permits to
 *            set a source system as default one to be used in publication
 *            operations.
 *        </td>
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.UnpublishCommand
 *        unpublish}
 *        </td>
 *        
 *        <td>
 *            Unpublishes published PDF document instances of a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.UnregisterCommand
 *        unregister}
 *        </td>
 *        
 *        <td>
 *            Unregisters a source system, publisher agent or document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.VersionsCommand
 *        versions}
 *        </td>
 *        
 *        <td>
 *            Lists registered versions of a document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link
 *        cl.araucana.pdf.publishers.implementations.fpg.command.XDocStatsCommand
 *        xdocstats}
 *        </td>
 *        
 *        <td>
 *            Reports summaried statistics about published PDF document
 *            instances of one or more document types.
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * <BR>
 * 
 * <p> The <b>showTime</b> shell variable can be set <b>on/off</b> to enable
 * or to disable the response time report of each executed command shell.
 * </p>
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
 *            <TD> 16-07-2008 </TD>
 *            <TD align="center">  1.0 </TD>
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
public class FPGIntegratedPDFPublisherSPIShell extends Shell {

	private PublisherManager manager;
	private String[] providerNames;
	private Publisher defaultProvider;

	private FPGIntegratedPDFPublisherSPI spi;

	/**
	 * Constructs a shell instance without a default init script and using
	 * standard input, standard output and standard error.
	 * 
	 * @throws PDFPublisherException If cannot construct shell instance.
	 */
	public FPGIntegratedPDFPublisherSPIShell() throws PDFPublisherException {

		this(null, System.in, System.out, System.err);
	}

	/**
	 * Constructs a shell instance using the specified default init script and
	 * input and outputs.
	 * 
	 * @param defaultScript Default init script.
	 * 
	 * @param in Shell normal input.
	 * 
	 * @param out Shell normal output.
	 * 
	 * @param err Shell error output.
	 * 
	 * @throws PDFPublisherException If cannot construct shell instance.
	 */
	public FPGIntegratedPDFPublisherSPIShell(
			String defaultScript, InputStream in, PrintStream out,
			PrintStream err) throws PDFPublisherException {

		super(getParams(defaultScript), in, out, err);

		manager = PublisherManager.getInstance();

		/*
		 *  Identifies collection of providers from publishers.
		 */
		String[] publisherNames = manager.getPublisherNames();
		int nProviders = 0;

		for (int i = 0; i < publisherNames.length; i++) {
			String publisherName = publisherNames[i];
			Publisher publisher = manager.getPublisher(publisherName);

			if (publisher.getType() == FPGIntegratedPDFPublisher.class) {
				nProviders++;
			}
		}

		/*
		 *  Must exist at least one provider.
		 */
		if (nProviders == 0) {
			throw new PDFPublisherException("No providers were found");
		}

		providerNames = new String[nProviders];
		nProviders = 0;

		for (int i = 0; i < publisherNames.length; i++) {
			String publisherName = publisherNames[i];
			Publisher publisher = manager.getPublisher(publisherName);

			if (publisher.getType() == FPGIntegratedPDFPublisher.class) {
				providerNames[nProviders++] = publisherName;
			}
		}

		/*
		 * Determines default provider.
		 */
		Publisher defaultPublisher = manager.getDefaultPublisher();

		for (int i = 0; i < providerNames.length; i++) {
			String providerName = providerNames[i];

			if (providerName.equals(defaultPublisher.getName())) {
				defaultProvider = defaultPublisher;

				break;
			}
		}

		if (defaultProvider == null) {
			defaultProvider = manager.getPublisher(providerNames[0]);
		}

		addCommand("cleanup",      new CleanupCommand(this));
		addCommand("docstats",     new DocStatsCommand(this));
		addCommand("doctypes",     new DocTypesCommand(this));
		addCommand("providers",    new ProvidersCommand(this));
		addCommand("publications", new PublicationsCommand(this));
		addCommand("publish",      new PublishCommand(this));
		addCommand("publishers",   new PublishersCommand(this));
		addCommand("register",     new RegisterCommand(this));
		addCommand("save",         new SaveCommand(this));
		addCommand("select",       new SelectCommand(this));
		addCommand("systems",      new SystemsCommand(this));
		addCommand("unpublish",    new UnpublishCommand(this));
		addCommand("unregister",   new UnregisterCommand(this));
		addCommand("versions",     new VersionsCommand(this));
		addCommand("xdocstats",    new XDocStatsCommand(this));
	}

	private static Map getParams(String defaultScript) {
		Map params = new HashMap();

		String title =
				  "FPGIntegratedPDFPublisherSPI Shell Version 1.0 20/11/2008.\n"
				+ "                La Araucana C.C.A.F.\n\n";

		params.put(Shell.TITLE, title);
		params.put(Shell.PROMPT, "fpgpubspi> ");

		if (defaultScript == null) {
			defaultScript = System.getProperty("user.home") + "/fpgpub.ini";
		}

		params.put(Shell.DEFAULT_SCRIPT, defaultScript);
		params.put(Shell.DEBUG_SYSTEM_PROPERTY_NAME, "fpgpubspi.debug");

		return params;
	}

	/**
	 * Gets set of available providers.
	 * 
	 * @return Set of available providers.
	 */
	public String[] getProviderNames() {
		return providerNames;
	}

	/**
	 * Gets a provider from its <code>name</code>.
	 * 
	 * @param name Provider name.
	 * 
	 * @return provider or <code>null</code> when provider <code>name</code>
	 *         is unknown.
	 */
	public Publisher getProvider(String name) {
		return manager.getPublisher(name);
	}

	/**
	 * Sets default provider.
	 * 
	 * @param name Provider name.
	 * 
	 * @throws PDFPublisherException If cannot set default provider name.
	 */
	public void setDefaultProviderName(String name)
			throws PDFPublisherException {

		Publisher defaultProvider = manager.getPublisher(name);

		if (defaultProvider == null) {
			throw new PDFPublisherException(
					"Unknown provider '" + name + "'.");
		}

		this.defaultProvider = defaultProvider;

		if (spi != null) {
			System.out.println("disconnecting previous spi ...");

			spi.disconnect();

			spi = null;
		}
	}

	/**
	 * Gets default provider name.
	 * 
	 * @return Default provider name.
	 */
	public String getDefaultProviderName() {
		return defaultProvider.getName();
	}

	/**
	 * Gets default provider.
	 * 
	 * @return Default provider.
	 */
	public Publisher getDefaultProvider() {
		return defaultProvider;
	}

	/**
	 * Gets associated <b>spi</b> instance.
	 * 
	 * @return Associated <b>spi</b> instance.
	 * 
	 * @throws PDFPublisherException If cannot get associated <b>spi</b>.
	 */
	public FPGIntegratedPDFPublisherSPI getSPI() throws PDFPublisherException {

		if (spi == null) {
			String dataSourceName =	getPropertyValue("dataSourceName");

			if (dataSourceName == null) {
				throw new PDFPublisherException(
						"dataSourceName property is mandatory.");
			}

			String schemaName = getPropertyValue("schemaName");

			if (schemaName == null) {
				throw new PDFPublisherException(
						"schemaName property is mandatory.");
			}

			DataSource dataSource;

			try {
				dataSource = JDBCUtils.getDataSource(dataSourceName);
			} catch(SQLException e) {
				throw new PDFPublisherException(
						"Cannot get SPI '" + getDefaultProviderName() + "'.",
						e);
			}

			spi = new FPGIntegratedPDFPublisherSPI(dataSource, schemaName);
		}

		return spi;
	}

	/**
	 * Executes <code>cmd</code> shell command with <code>args</code> arguments.
	 * Additionally reports executed command response time if shell variable
	 * <b>showTime</b> is <b>on</b>.
	 */
	public void execute(String cmd, Object[] args) {
		FPGPublisherSPICommand command =
				(FPGPublisherSPICommand) getCommand(cmd);

		if (command == null) {
			return;
		}

		boolean showTime = getVariable("showTime").equals("on");
		long ti = 0L;
		long tf = 0L;

		if (showTime) {
			ti = System.currentTimeMillis();
		}

		command.execute((String[]) args, in, out, err);

		if (showTime) {
			tf = System.currentTimeMillis();

			err.println("*** time: " + (tf - ti) + " ms.");
		}
	}

	/**
	 * Displays usage help to the <code>cmd</code> shell command.
	 */
	public void help(String cmd) {
		FPGPublisherSPICommand command =
				(FPGPublisherSPICommand) getCommand(cmd);

		if (command == null) {
			return;
		}

		command.help(err);
	}

	/**
	 * Closes connection to the associated <b>spi</b> instance.
	 */
	public void close() {
		if (spi != null) {
			System.out.println("Disconnecting from current spi ...");

			spi.disconnect();
		}
	}

	/**
	 * Shell launcher.
	 * 
	 * @param args Shell arguments.
	 */
	public static void main(String[] args) {
		FPGIntegratedPDFPublisherSPIShell shell = null;

		try {
			shell =	new FPGIntegratedPDFPublisherSPIShell();

			shell.execute();
		} catch (Exception e) {
			System.err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		} finally {
			if (shell != null) {
				shell.close();
			}
		}
	}

	private String getPropertyValue(String name) {
		Property property = defaultProvider.getProperty(name);

		if (property == null) {
			return null;
		}

		return property.getValue();
	}
}
