package cl.araucana.ctasfam.batch.dao.as400.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cl.araucana.ctasfam.batch.common.dto.ArchivoPropuestaDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ArchivoPropuestaUtil;
import cl.araucana.ctasfam.batch.dao.AbstractAs400Dao;
import cl.araucana.ctasfam.batch.dao.ArchivoPropuestaDao;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSJavaFile;

public class ArchivoPropuestaDaoImpl extends AbstractAs400Dao implements ArchivoPropuestaDao{
	public ArchivoPropuestaDto getArchivoPropuesta(String pathAs400) throws TechnicalException {
		try {
			AS400 connection = getConnection();
			
			IFSFileInputStream isAs400 = new IFSFileInputStream(connection, pathAs400);
			ByteArrayOutputStream baosContenidoProp = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 64];
			int bytesRead = isAs400.read(buffer);
			while (bytesRead > 0) {

				baosContenidoProp.write(buffer);
				bytesRead = isAs400.read(buffer);
			}

			isAs400.close();
			baosContenidoProp.close();
			closeConnection();
			
			String tipo = ArchivoPropuestaUtil.getTipoArchivo(pathAs400);
			ArchivoPropuestaDto archivoPropResult = new ArchivoPropuestaDto(tipo, baosContenidoProp.toByteArray());
			return archivoPropResult;
		} catch (AS400SecurityException e) {
			throw new TechnicalException("0501","Ocurrio un error al obtener el archivo desde el sistema de archivos", e);
		} catch (IOException e) {
			throw new TechnicalException("0502","Ocurrio un error al obtener el archivo desde el sistema de archivos", e);
		} 

	}
	
	public static void main(String [] args){
		
		try {
//			ArchivoPropuestaDaoImpl dao = new ArchivoPropuestaDaoImpl();
//			ByteArrayOutputStream baos = dao.getArchivoPropuesta("/files/Respaldo/tempcrivera/70016160_20160520090000.xlsx");
//			System.out.println(baos.size());
//			AS400 connection = dao.getConnection();
			
			AS400 connection = new AS400("146.83.1.2", "schema", "schema");
			
			
			String as400Path = "/files/Respaldo/tempcrivera/";
			String localPath = "C:\\dev\\temp\\propuestas\\csv_empresas\\";
			String partFileName1 = "8153760";
			String partFileName2 = "_20160530170400.CSV";
			
			IFSJavaFile file2 = new IFSJavaFile(connection, as400Path);
			if (!file2.exists()) {
				file2.mkdir();
			}
			for(int i = 0; i < 4 ;i++){
				IFSJavaFile file = new IFSJavaFile(connection, 
						as400Path + partFileName1 + i + partFileName2);
				if (file.exists()) {
					Boolean a = file.delete();
					System.out.println(a);
				}
				
				IFSFileOutputStream osAs400 = new IFSFileOutputStream(connection, 
						as400Path + partFileName1 + i + partFileName2, 
						IFSFileOutputStream.SHARE_NONE, false);
				
				FileInputStream fis = new FileInputStream(new File(localPath + partFileName1 + i + partFileName2));
				
				byte[] buffer = new byte[1024 * 64];
				int bytesRead = fis.read(buffer);
				while (bytesRead > 0) {
					osAs400.write(buffer, 0, bytesRead);
					bytesRead = fis.read(buffer);
				}
				
				fis.close();
				osAs400.close();
			}
			
			connection.disconnectAllServices();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
}
