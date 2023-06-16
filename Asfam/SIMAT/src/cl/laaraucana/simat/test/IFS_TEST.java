package cl.laaraucana.simat.test;

public class IFS_TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String aux = "entonces aux";
		String var = null;
		String temp = null;

		temp = null == var ? "NO" : aux;

		/*
		if(aux=="SI")System.out.println("es si");
		if(aux=="no")System.out.println("es no");
		if(aux.equals("SI"))System.out.println("es si (equasl)");
		*/

		System.out.println("temp: " + temp);
		/*
		String server;
		String user;
		String pass;
		String rutaOrigen;
		String rutaDestino;
		String nameFile;
		System.out.println("LOGIN AS400");
		//\\146.83.1.5\sinaccaf
		//server="146.83.1.5";
		server="146.83.1.2";
		user="sistemas";
		pass="sistemas";
		
		System.out.println("RUTAS ARCHIVOS");
		rutaOrigen="C:/CLOUD/BalanceGeneral/";
		
		//rutaDestino="qdls/TEMP/clear";
		rutaDestino="/SIMAT/201305/";
		nameFile="BalanceGeneral.xls";
		
		System.out.println("SEND FILE AS400");
		Util_SendFileToAS400 sf4= new Util_SendFileToAS400(server, user, pass);
		sf4.copiarArchivoToAS400(rutaOrigen+nameFile, rutaDestino+nameFile);
		sf4.copiarArchivoToAS400(rutaOrigen+nameFile, rutaDestino+nameFile);
		try {
			//sf4.crearArchivo(rutaDestino, "XD");
			sf4.crearDirectorio(rutaDestino);
			sf4.crearDirectorio(rutaDestino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("END IFS AS400");
		*/
	}//end main

}//end class
