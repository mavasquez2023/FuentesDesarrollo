package cl.laaraucana.rendicionpagonomina.utils;

import java.io.PrintWriter;

public class TestFile {
	public static void main(String[] args) {
		String pathArchivo = "C:/tmp/mi.csv";
		
		PrintWriter pw = FileManagmentUtils.getOpenedFileToWrite(pathArchivo);
		
		String registroCSV1 = "luis;iabacache;valdivia";

		String registroCSV2 = "paula;morales;sabando";
		
		
		FileManagmentUtils.putLineFromFileOpened(pw, registroCSV1);

		FileManagmentUtils.putLineFromFileOpened(pw, registroCSV2);
		
		FileManagmentUtils.closeFileToWrite(pw);
		
	}
}
