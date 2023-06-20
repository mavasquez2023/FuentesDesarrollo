package cl.laaraucana.rendicionpagonomina.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ftp.exceptions.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;

@Service
public class FTPServicePlussImpl implements FTPServicePluss {

	@Value("${path.temporal.folder}")
	private String carpetaTempral;
	

	private static final Logger logger = Logger.getLogger(FTPServicePlussImpl.class);
	
	private int TIME_TO_LIVE_TMP_FILES = 30 * 60 * 1000;

	@Override
	public FTPClient connectToFTP(FTPConfigurationVO config) throws FTPErrors {
		logger.debug("conectando con FTP ...");
		logger.debug("config:"+config.toString());
		FTPClient ftpconnection = new FTPClient();
		ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;

		try {
			logger.debug("conectando FTP...");
			ftpconnection.connect(config.getHost(), config.getPort());
			logger.debug("conectado OK !");
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1,
					"No fue posible conectarse al FTP a través del host=" + config.getHost());
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		reply = ftpconnection.getReplyCode();
		
		if (!FTPReply.isPositiveCompletion(reply)) {

			try {
				ftpconnection.disconnect();
			} catch (IOException e) {
				ErrorMessage errorMessage = new ErrorMessage(-2,
						"No fue posible conectarse al FTP, el host=" + config.getHost() + " entregó la respuesta=" + reply);
				logger.error(errorMessage.toString());
				throw new FTPErrors(errorMessage);
			}
		}


		try {
			logger.debug("FTP login...");
			ftpconnection.login(config.getUser(), config.getPass());
			logger.debug("FTP login OK");
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-3,
					"El usuario=" + config.getUser() + ", y el pass=**** no fueron válidos para la autenticación.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		try {

			logger.debug("FTP set file type ...");
			ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
			logger.debug("FTP set file type OK");
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}
		logger.debug("FTP set pasive mode...");
		ftpconnection.enterLocalPassiveMode();
		logger.debug("FTP set pasive mode OK");
		if(ftpconnection.isConnected()) {

			logger.debug("FTP return conn valida !");
			return ftpconnection;
		}

		logger.debug("FTP return conn invalida !!!!");
		return null;
	}

	@Override
	public void disconnectFTP(FTPClient conn) throws FTPErrors {
		logger.debug("disconnecting from FTP ");
		if (conn.isConnected()) {
			try {
				logger.debug("disconecting from FTP, logout... ");
				conn.logout();
				logger.debug("disconecting from FTP, disconnect ");
				conn.disconnect();
				logger.debug("disconecting from FTP, disconnected OK ");
			} catch (IOException f) {
				throw new FTPErrors(
						new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
			}
		}
	}

	@Override
	public File getFileFromFTP(FTPConfigurationVO config, String fileName) throws FTPErrors {		
		FTPClient ftpconnection = connectToFTP(config);
		InputStream iStream=null;
		try {
			logger.debug("getFileFromFTP fileName: "+config.getPathToGet() + fileName + " ...");
			String path = carpetaTempral;
			iStream = ftpconnection.retrieveFileStream(config.getPathToGet() + fileName);

			logger.debug("getFileFromFTP get ok...");
			final File fileDownloaded = new File(path + fileName+"__"+Math.random()*100000000);

			logger.debug("getFileFromFTP file tmp generated ["+fileDownloaded.getAbsolutePath()+"]");

			logger.debug("getFileFromFTP copying file...");
			FileUtils.copyInputStreamToFile(iStream, fileDownloaded );

			logger.debug("getFileFromFTP copying file OK, length:"+ fileDownloaded.length());
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						logger.debug("waiting for delete tmp file "+TIME_TO_LIVE_TMP_FILES+"ms.");
						Thread.sleep(TIME_TO_LIVE_TMP_FILES);
						fileDownloaded.delete();
						logger.debug("delete tmp file ");
					} catch (InterruptedException e) {
					}
				}
			}).start();
			
			return fileDownloaded;	
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			throw new FTPErrors(new ErrorMessage(-1, "Exception"));
		}
		finally{
			disconnectFTP(ftpconnection);
		}
	}

	@Override
	public String[] searchFilesFromFTP(FTPConfigurationVO config, final String nameLogic) throws FTPErrors {
		FTPClient ftpconnection = connectToFTP(config);
		
		FTPFileFilter filter = new FTPFileFilter() {
			
			@Override
			public boolean accept(FTPFile arg0) {
				logger.debug("compare:["+arg0.getName()+"]["+nameLogic+"]");
				if( arg0.isFile() && nameLogic.startsWith("equals:") &&  arg0.getName().trim().toLowerCase().equals(nameLogic.split("equals:")[1].toLowerCase())) {
					return true;
				}
				else if( arg0.isFile() && nameLogic.startsWith("startsWith:") &&  arg0.getName().trim().toLowerCase().startsWith(nameLogic.split("startsWith:")[1].toLowerCase())) {
					return true;
				}
				else if( arg0.isFile() && nameLogic.startsWith("contains:") &&  arg0.getName().trim().toLowerCase().contains(nameLogic.split("contains:")[1].toLowerCase())) {
					return true;
				}
				else if( arg0.isFile() && nameLogic.startsWith("regex:") ) {
					
					Pattern pattern = Pattern.compile(nameLogic.split("regex:")[1], Pattern.CASE_INSENSITIVE);
				    Matcher matcher = pattern.matcher( arg0.getName().trim());
				    boolean matchFound = matcher.find();
					
					return matchFound;
				}
				return false;
			}
		};
		String[] archivos = null;
		FTPFile[] files = null;
		try {
			logger.debug("Buscando archivos en ["+config.getPathToGet()+"], name: "+ nameLogic);
			files = ftpconnection.listFiles(config.getPathToGet(), filter);
			if(files != null && files.length > 0) {
				logger.debug("Archivos encontrados "+ files.length);
				archivos = new String[files.length];
				int index = 0;
				for (FTPFile ftpFile : files) {
					archivos[index] = ftpFile.getName();
					index++;
				}
			}
			else {
				logger.debug("Archivos encontrados 0 .");
	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ftpconnection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return archivos;
	}

	@Override
	public boolean existFileInFTP(FTPClient ftpconnection, String path, FTPFileFilter filter) throws FTPErrors {
		
		try {
			FTPFile[] files = ftpconnection.listFiles(path, filter);
			if(files != null && files.length>0) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int putFileToFTP(FTPConfigurationVO config, File file, String fileName, boolean overwrite) throws FTPErrors {
		FTPClient ftpconnection = connectToFTP(config);
		int salida=0;
		try {
			InputStream input = new FileInputStream(file);
			boolean resultado= ftpconnection.storeFile(config.getPathToPut() + fileName, input);
			if(resultado){
				salida=1;
			}
			return salida;
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}
	}
	
	@Override
	public int putFileToFTP(FTPConfigurationVO config, InputStream inputStreamFile, String fileName, boolean overwrite) throws FTPErrors {
		FTPClient ftpconnection = connectToFTP(config);
		int salida=0;
		try {
			boolean resultado= ftpconnection.storeFile(config.getPathToPut() + fileName, inputStreamFile);
			if(resultado){
				salida=1;
			}
			return salida;
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}
	}

	@Override
	public int deleteFileInFTP(FTPClient ftpconnection, String path, String fileName) throws FTPErrors {
		
		
		try {
			logger.debug("deleteFileInFTP from ["+path+fileName+"]");
			boolean result = ftpconnection.deleteFile(path+fileName);
			logger.debug("delete result:"+result);
			if(result)
				return 1;
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1, e.getMessage());
			throw new FTPErrors(errorMessage);
			
		}
		
		return -1;
	}
	
	@Override
	public int deleteFileInFTP(FTPConfigurationVO config, String path, String fileName) throws FTPErrors {
		
		FTPClient ftpconnection = connectToFTP(config);
		try {
			logger.debug("deleteFileInFTP from ["+path+fileName+"]");
			boolean result = ftpconnection.deleteFile(path+fileName);
			logger.debug("delete result:"+result);
			if(result)
				return 1;
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1, e.getMessage());
			throw new FTPErrors(errorMessage);
			
		}
		finally {
			disconnectFTP(ftpconnection);
		}
		return -1;
	}

	@Override
	public int moveFileInFTP(FTPConfigurationVO config, String originFileName, final String destinationFileName, boolean overwrite) throws FTPErrors {
		FTPClient ftpconnection = connectToFTP(config);
		
		try {
			logger.debug("moveFileInFTP from ["+config.getPathToGet()+originFileName+"], to ["+config.getPathToPut()+destinationFileName+"]");
			if(overwrite) {
				if(existFileInFTP(ftpconnection, config.getPathToPut(), new FTPFileFilter() {
					
					@Override
					public boolean accept(FTPFile arg0) {
						logger.debug("compare:["+arg0.getName()+"]["+destinationFileName+"]");
						if(arg0.getName().trim().equals(destinationFileName)) {
							return true;
						}
						return false;
					}
				})) {

					logger.debug("moveFileInFTP delete for overwrite ["+config.getPathToPut()+","+destinationFileName+"]");
					deleteFileInFTP(ftpconnection, config.getPathToPut(), destinationFileName);
				}
			}
			boolean result = ftpconnection.rename(config.getPathToGet()+originFileName, config.getPathToPut()+destinationFileName);
			
			logger.debug("Move result:"+result);
			if(result) {
				
				return 1;
			}
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1, e.getMessage());
			throw new FTPErrors(errorMessage);
			
		}
		finally {
			disconnectFTP(ftpconnection);
		}
		return -1;
	}

}
