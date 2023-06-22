

package cl.araucana.wssiagf.clients;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cl.araucana.wssiagf.Codes;
import cl.araucana.wssiagf.Operations;
import cl.araucana.wssiagf.WebServiceResponse;


public class HTTPWebServicesInterfaceClient extends WebServicesInterfaceClient
		implements Operations, Codes {

	private String interfaceURL;
	private PrintStream out = null;

	public HTTPWebServicesInterfaceClient(String sysID, String interfaceURL) {
		super(sysID);

		this.interfaceURL = interfaceURL;
	}

	public String getInterfaceURL() {
		return interfaceURL;
	}

	public void setDebug(PrintStream out) {
		this.out = out;
	}

	public WebServiceResponse getVersion() throws IOException {

		return sendRequest(OP_GET_VERSION, null);
	}

	public WebServiceResponse ingresoReconocimiento(String parameter)
			throws IOException {

		return sendRequest(OP_INGRESO_RECONOCIMIENTO, parameter);
	}

	public WebServiceResponse consultaCausante(String rutCausante)
			throws IOException {

		return sendRequest(OP_CONSULTA_CAUSANTE, rutCausante);
	}

	public WebServiceResponse extincionReconocimiento(
			String parameter) throws IOException {

		return sendRequest(OP_EXTINCION_RECONOCIMIENTO, parameter);
	}

	public WebServiceResponse actualizarCausante(String parameter)
			throws IOException {

		return sendRequest(OP_ACTUALIZAR_CAUSANTE, parameter);
	}

    public static void main(String args[]) throws IOException {

		if (args.length != 1) {
			System.err.println("usage: <scriptName>");

			System.exit(1);
		}

		runScript(args[0]);
		System.exit(0);
	}

	public static void runScript(String scriptName) throws IOException {

		BufferedReader reader = null;
		HTTPWebServicesInterfaceClient client = null;

		System.out.println("Running script '" + scriptName + "' ...\n");

		try {
			reader = new BufferedReader(new FileReader(scriptName));

			String line;

			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(" ");

				if (!tokens[0].equals("connection")) {
					if (client == null) {
						throw new IOException("connection is closed.");
					}
				}

				if (tokens[0].equals("connection")) {
					if (tokens.length != 4) {
						throw new IOException(
								"unexpected connection command.");
					}

					String sysID = tokens[1];
					String interfaceURL = tokens[2];
					boolean debugMode = tokens[3].equals("on");

					System.out.println(
							  "=> connection "
							+ sysID + " " + interfaceURL + " ...");

					client =
							new HTTPWebServicesInterfaceClient(
									sysID, interfaceURL);

					if (debugMode) {
						client.setDebug(System.err);
					}
				} else if (tokens[0].equals("getVersion")) {
					if (tokens.length != 1) {
						throw new IOException(
								"unexpected getVersion command.");
					}

					System.out.println("=> getVersion ...");
					client.getVersion().dump();
 				} else if (tokens[0].equals("ingresoReconocimiento")) {
					if (tokens.length != 2) {
						throw new IOException(
								"unexpected ingresoReconocimiento command.");
					}

					String fileName = tokens[1];

					System.out.println("=> ingresoReconocimiento ...");
					client.ingresoReconocimiento(getFileText(fileName)).dump();
 				} else if (tokens[0].equals("consultaCausante")) {
					if (tokens.length != 2) {
						throw new IOException(
								"unexpected consultaCausante command.");
					}

					String rutCausante = tokens[1];

					System.out.println("=> consultaCausante ...");
					client.consultaCausante(rutCausante).dump();
 				} else if (tokens[0].equals("extincionReconocimiento")) {
					if (tokens.length != 2) {
						throw new IOException(
								"unexpected extincionReconocimiento command.");
					}

					String fileName = tokens[1];

					System.out.println("=> extincionReconocimiento ...");
					client.extincionReconocimiento(getFileText(fileName)).dump();
 				} else if (tokens[0].equals("actualizarCausante")) {
					if (tokens.length != 2) {
						throw new IOException(
								"unexpected actualizarCausante command.");
					}

					String fileName = tokens[1];

					System.out.println("=> actualizarCausante ...");
					client.actualizarCausante(getFileText(fileName)).dump();
				} else if (line.length() > 0 && line.charAt(0) != '#') {
					System.out.println("bad command '" + line + "'.");
				}
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}
	}

	private WebServiceResponse sendRequest(int opID, String parameter)
			throws IOException {

		URL url = null;
		URLConnection connection = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		WebServiceResponse response = new WebServiceResponse();

		/*
		 * Produce request:
		 *
		 * ------------------------
		 * <sysID>::<reqID>::<opID>
		 * <parameter>
		 * ------------------------
		 *
		 * <parameter> es un documento XML o un string. En caso de ser un
		 * string, su sintaxis debe ser una de las dos siguientes:
		 *
		 * (1)
		 *    <name1>=<value1> newline
		 *          ...
		 *    <nameN>=<valueN> newline
		 *
		 * (2)
		 *    <free text line> newline
		 */
		String headerID = sysID + "::" + newReqID() + "::" + opID;
		String siagfRequest = headerID + "\n";

		if (parameter != null) {
			siagfRequest += parameter + "\n";
		} else {
			parameter = "";
		}

		debug("sendRequest: " + siagfRequest);

		try {
			url = new URL(interfaceURL);

			connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "text/plain");

			connection.setRequestProperty(
					"Content-Length", siagfRequest.length() + "");

			connection.connect();

			writer = new PrintWriter(connection.getOutputStream());

			writer.print(siagfRequest);
			writer.close();

			/*
			 * Consume response:
			 *
			 * ------------------------------------------------------------
			 * <sysID>::<reqID>::<opID>::<code>::<timestamp>::<serviceTime>
			 * <message>
			 * ------------------------------------------------------------
			 *
			 * <message> es un documento XML o un string. En caso de ser un
			 * string, su sintaxis debe ser una de las dos siguientes:
			 *
			 * (1)
			 *    <name1>=<value1> newline
			 *          ...
			 *    <nameN>=<valueN> newline
			 *
			 * (2)
			 *    <free text line> newline
			 */
			reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String answer;
			int lineNo = 0;
			int code = 0;
			String timestamp = "";
			int serviceTime = 0;
			String message = "";

			while ((answer = reader.readLine()) != null) {
				if (lineNo++ == 0) {
					String[] tokens = answer.split("::");

					if (tokens.length != 6) {
						throw new IOException("unexpected answer header.");
					}

					try {
						code = Integer.parseInt(tokens[3]);
						timestamp = tokens[4];
						serviceTime = Integer.parseInt(tokens[5]);
					} catch (NumberFormatException e) {
						throw new IOException("unexpected answer header.");
					}

					debug("++ |" + answer + "|");
				} else {
					message += answer + "\n";

					debug(">> |" + answer + "|");
				}
			}

			if (lineNo == 0) {
				throw new IOException("missed answer header.");
			}

			response.setCode(code);
			response.setMessage(message);

			debug(headerID + " " + timestamp + " " + serviceTime + " ms.");
		} finally {
			if (writer != null) {
				writer.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}

		return response;
	}

	public void debug(String message) {
		if (out != null) {
			out.println(message);
		}
	}

	public static String getFileText(String name) throws IOException {

		File file = new File(name);
		int fileSize = (int) file.length();
		byte[] buffer = new byte[fileSize];
		FileInputStream input = null;

		try {
			input = new FileInputStream(file);

			input.read(buffer);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}

		return new String(buffer);
	}
}
