

/*
 * @(#) FPGCompiler.java    1.0 01-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import cl.araucana.core.util.Padder;
import cl.araucana.core.util.java.CodeTemplate;

import cl.araucana.fpg.CompilationOptions;
import cl.araucana.fpg.PDFGenerator;
import cl.araucana.fpg.PDFObject;
import cl.araucana.fpg.PDFObjectCompiler;
import cl.araucana.fpg.PDFTemplate;
import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.compiled.CompiledPDFObject;
import cl.araucana.fpg.compiled.FPGCode;


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
 *            <TD> 01-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *        
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 23-06-2009 </TD>
 *            <TD align="center"> 1.1 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Agrega al generador de DocumentModel los m&eacute;todos
 *                 <code>docIndex()</code> y <code>pageType()</code>. </TD>
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
public class FPGCompiler {

	private static final int JAVA_CODE_REFERENCE_SIZE = 32 * 1024;
	private static final int ERRORS_REFERENCE_COUNT = 128;

	private boolean verbose;

	private long ti;
	private long tf;

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				  "fpgc <templateDir> <templateName> [-v] "
				+ "[-checkDocModel <docModelClassName>] "
				+ "[-genDocModel [-package <pkgName>] -class <className> "
				+ "[-o <srcdir>]]");
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 2) {
			usage();

			return;
		}

		boolean verbose = false;
		boolean checkDocModel = false;
		String docModelClassName = null;
		boolean generateDocModel = false;
		String packageName = null;
		String className = null;
		String sourceDir = ".";

		String templateDir = args[0];
		String templateName = args[1];

		File dir = new File(templateDir + "/" + templateName);

		if (!dir.isDirectory()
				|| !new File(dir, "source.pdf").exists()) {

			System.err.println("Invalid '" + dir + "' directory.");

			return;
		}

		for (int i = 2; i < args.length; i++) {
			if (args[i].equals("-v")) {
				verbose = true;
			} else if (args[i].equals("-checkDocModel")) {
				if (i + 1 < args.length) {
					checkDocModel = true;
					docModelClassName = args[++i];
				} else {
					usage();

					return;
				}
			} else if (args[i].equals("-genDocModel")) {
				int remaindedOptions = args.length - i - 1;

				if (remaindedOptions % 2 != 0 || remaindedOptions > 6) {
					usage();

					return;
				}

				generateDocModel = true;

				for (int j = i + 1; j < args.length; j++) {
					if (args[j].equals("-package")) {
						packageName = args[++j];
					} else if (args[j].equals("-class")) {
						className = args[++j];
					} else if (args[j].equals("-o")) {
						sourceDir = args[++j];
					} else {
						usage();

						return;
					}
				}

				if (className == null) {
					usage();

					return;
				}

				break;
			} else {
				usage();

				return;
			}
		}

		// Validates packageName.
		if (packageName != null) {
			String[] names = packageName.split("\\.");
			boolean isOK = true;

			if (packageName.endsWith(".")) {
				isOK = false;
			} else if (names.length == 0) {
				isOK = false;
			} else {
				for (int i = 0; i < names.length; i++) {
					if (!FPGCode.isID(names[i])) {
						isOK = false;

						break;
					}
				}
			}

			if (!isOK) {
				System.err.println("Invalid packageName '" + packageName + "'");

				return;
			}
		}

		// Validates className.
		if (className != null) {
			if (!FPGCode.isID(className)) {
				System.err.println("Invalid className '" + className + "'");

				return;
			}
		}

		// Validates docModelClassName.
		Class docModelClass = null;

		if (docModelClassName != null) {
			try {
				docModelClass = Class.forName(docModelClassName);

				if (!PDFGenerator.isValidDocumentModelClass(docModelClass)) {
					throw new Exception();
				}
			} catch(Exception e) {
				System.err.println(
						  "-checkDocModel option requires the classname");

				System.err.println(
						  "of a concrete implementation of "
						+ "'cl.araucana.fpg.DocumentModel' interface.");

				return;
			}
		}

		CompilationOptions options = new CompilationOptions();

		options.verbose = verbose;
		options.checkDocModel = checkDocModel;
		options.docModelClassName = docModelClassName;
		options.docModelClass = docModelClass;
		options.generateDocModel = generateDocModel;
		options.packageName = packageName;
		options.className = className;
		options.sourceDir = sourceDir;

		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();

		FPGCompiler compiler = new FPGCompiler();

		compiler.compile(template, options);
	}

	public void compile(PDFTemplate template, CompilationOptions options)
			throws FPGException, IOException {

		verbose = options.verbose;

		String templateFullName = template.getFullName();

		if (options.checkDocModel || options.generateDocModel) {
			log("Compilation options:");
		}

		if (options.checkDocModel) {
			log("    - Check document model:");

			log(
					  "        docModelClassName = "
					+ options.docModelClassName);

			log();
		}

		if (options.generateDocModel) {
			log("    - Generate document model:");

			if (options.packageName != null) {
				log("        packageName = " + options.packageName);
			}

			log("        className   = " + options.className);
			log("        sourceDir   = " + options.sourceDir);
			log();
		}

		System.err.println("FPG Compiler Version 1.1, 23/06/2009.");

		System.err.println(
				  "Compiling template "
				+ "'" + templateFullName + "' (PDF objects) :");

		ti = System.currentTimeMillis();

		PDFObjectCompiler objectCompiler = new PDFObjectCompiler(options);

		/*
		 *  Gets list of compilable PDF objects from PDF template.
		 */
		List compilableObjects = template.getCompilableObjects();

		/*
		 * Cleanups compiled PDF objects previously.
		 */
		Iterator compilableObjectsIT = compilableObjects.iterator();

		while (compilableObjectsIT.hasNext()) {
			PDFObject object = (PDFObject) compilableObjectsIT.next();
			String objectName = object.getObjectName();

			try {
				template.removeData(
						objectName, PDFTemplate.EXTENSION_COMPILED);
			} catch (IOException e) {}
		}

		/*
		 * Compilation process.
		 */
		int nCompilationErrors = 0;

		compilableObjectsIT = compilableObjects.iterator();

		while (compilableObjectsIT.hasNext()) {
			PDFObject object = (PDFObject) compilableObjectsIT.next();
			String paddedObjectName = Padder.rpad(object.getObjectName(), 15);

			System.err.print("    " + paddedObjectName + " ...");

			try {
				objectCompiler.compile(object);
				System.err.println("\r    " + paddedObjectName + " OK.");
			} catch (FPGException e) {
				System.err.println(
						    "\r    " + paddedObjectName + " FAILED. "
						  + e.getMessage());

				nCompilationErrors++;
			}
		}

		tf = System.currentTimeMillis();

		log();

		/*
		 * Check and generate document model options.
		 */
		if (nCompilationErrors == 0) {
			if (options.checkDocModel || options.generateDocModel) {
				TreeMap[] properties = getProperties(objectCompiler);

				if (options.checkDocModel) {
					System.err.println(
							  "Checking template against document model "
							+ "'" + options.docModelClass + "' ...");

					List errors =
							checkDocModel(options.docModelClass, properties);

					if (errors.size() > 0) {
						nCompilationErrors = errors.size();

						System.err.println("Check FAILED.");

						System.err.println(
								"The following errors were detected:");

						for (int i = 0; i < errors.size(); i++) {
							String error = (String) errors.get(i);

							System.err.println("    " + error);
						}
					} else {
						System.err.println("Check OK.");
					}
				}

				if (options.generateDocModel && nCompilationErrors == 0) {
					byte[] javaCode =
							generateDocModel(
									options.packageName,
									options.className,
									properties);

					String sPackagePath = "";

					if (options.packageName != null) {
						sPackagePath = options.packageName.replace('.', '/');
					}

					File dir = new File(options.sourceDir + "/" + sPackagePath);

					dir.mkdirs();

					File srcFile = new File(dir, options.className + ".java");
					FileOutputStream output = null;

					try {
						output = new FileOutputStream(srcFile);

						output.write(javaCode);
					} catch (IOException e) {
						System.err.println(
								  "Document model class couldn´t be generated. "
								+ "[" + e.getMessage() + "]");
					} finally {
						if (output != null) {
							try {
								output.close();
							} catch (IOException e) {}
						}
					}
				}
			}
		}

		/*
		 * Saves compiled PDF objects when no compilation errors occurred.
		 */
		if (nCompilationErrors == 0) {
			compilableObjectsIT = compilableObjects.iterator();

			while (compilableObjectsIT.hasNext()) {
				PDFObject object = (PDFObject) compilableObjectsIT.next();

				template.saveData(
						object.getObjectName(),
						object.getXData(),
						PDFTemplate.EXTENSION_COMPILED);
			}

			System.err.println(
					  "Template '" + templateFullName + "' "
					+ "was compiled successfully.");
		} else {
			System.err.println(
					  "Template '" + templateFullName + "' "
					+ "couldn't be compiled. Checks compilation errors.");
		}

		log("Compilation Time : " + getCompilationTime() + " ms.");
	}

	public long getCompilationTime() {
		return tf - ti;
	}

	private TreeMap[] getProperties(PDFObjectCompiler compiler) {
		TreeMap[] properties = new TreeMap[FPGCode.NTYPES];

		for (int i = 0; i < properties.length; i++) {
			properties[i] = new TreeMap();
		}

		TreeMap globalProperties = compiler.getProperties();
		Iterator globalPropertiesIT = globalProperties.keySet().iterator();

		while (globalPropertiesIT.hasNext()) {
			String globalPropertyName = (String) globalPropertiesIT.next();

			Integer globalPropertyType =
					(Integer) globalProperties.get(globalPropertyName);

			int propertyType = globalPropertyType.intValue();

			properties[propertyType].put(
					globalPropertyName, globalPropertyType);
		}

		return properties;
	}

	private List checkDocModel(Class docModelClass, TreeMap[] properties) {
		List errors = new ArrayList(ERRORS_REFERENCE_COUNT);

		for (int type = 0; type < properties.length; type++) {
			Iterator propertiesIT = properties[type].keySet().iterator();

			while (propertiesIT.hasNext()) {
				String propName = (String) propertiesIT.next();

				try {
					CompiledPDFObject.checkProperty(
							propName, type, docModelClass);
				} catch (FPGException e) {
					errors.add(e.getMessage());
				}
			}
		}

		return errors;
	}

	private byte[] generateDocModel(String packageName, String className,
			TreeMap[] properties) {

		String author = System.getProperty("author", "AUTHOR");
		String email = System.getProperty("email", "EMAIL");

		ByteArrayOutputStream output =
				new ByteArrayOutputStream(JAVA_CODE_REFERENCE_SIZE);

		PrintStream out = new PrintStream(output);

		out.print(CodeTemplate.getHeaderComment(className));

		if (packageName != null) {
			out.println("\n\npackage " + packageName + ";");
		}

		out.println("\n");

		if (properties[FPGCode.TYPE_IMAGE].size() > 0) {
			out.println("import java.awt.Image;");
		}

		out.println("import cl.araucana.fpg.DocumentModel;");

		out.print(CodeTemplate.getClassDocComment(author, email));

		out.println(
				"public class " + className + " implements DocumentModel {");

		final String[] typeNames = {
			"boolean",
			"Image",
			"int",
			"String"
		};

		final String[] argNames = {
			"b",
			"image",
			"n",
			"s"
		};

		/*
		 *  Generates private variable instance to each property.
		 */
		for (int type = 0; type < properties.length; type++) {
			String typeName = typeNames[type];

			if (properties[type].size() > 0) {
				out.println();
				out.println("    /*");
				out.println("     * " + typeName + " variables.");
				out.println("     */");
			}

			Iterator propertiesIT = properties[type].keySet().iterator();

			while (propertiesIT.hasNext()) {
				String propName = (String) propertiesIT.next();

				out.println("    private " + typeName + " " + propName + ";");
			}
		}

		out.println();

		/*
		 *  Generates set/get methods to each property.
		 */
		for (int type = 0; type < properties.length; type++) {
			String typeName = typeNames[type];
			String argName = argNames[type];
			boolean usePrefix = type != FPGCode.TYPE_BOOLEAN;

			if (properties[type].size() > 0) {
				out.println("    /*");
				out.println("     * " + typeName + " properties.");
				out.println("     */");
			}

			Iterator propertiesIT = properties[type].keySet().iterator();

			while (propertiesIT.hasNext()) {
				String propName = (String) propertiesIT.next();
				String baseName = CompiledPDFObject.normalizeName(propName);

				// set method.
				out.println(
						  "    public void set" + baseName
						+ "(" + typeName + " " + argName + ") {");

				out.println(
						"        " + propName + " = " + argName + ";");

				out.println("    }");
				out.println();

				// get method.
				String methodName = (usePrefix) ? "get" + baseName : propName;

				out.println(
						"    public " + typeName + " " + methodName + "() {");

				out.println("        return " + propName + ";");
				out.println("    }");
				out.println();
			}
		}

		/*
		 *  Implements interface DocumentModel.
		 */
		out.println("    /*");
		out.println("     * Implementation for interface DocumentModel.");
		out.println("     */");

		out.println();

		out.println("    public String docID() {");
		out.println("        return \"?\";");
		out.println("    }");

		out.println();

		out.println("    public String docIndex() {");
		out.println("        return docID();");
		out.println("    }");

		out.println();
		
		out.println("    public boolean hasMorePages() {");
		out.println("        return false;");
		out.println("    }");

		out.println();
	
		out.println("    public int pageType() {");
		out.println("        return 0;");
		out.println("    }");

		out.println("}");

		out.close();

		return output.toByteArray();
	}

	private void log() {
		log("");
	}

	private void log(String message) {
		if (verbose) {
			System.err.println(message);
		}
	}
}
