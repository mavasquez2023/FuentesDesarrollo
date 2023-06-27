//package com.microsystem.lme.helper;
//
//import java.util.Date;
//
//import org.apache.log4j.Logger;
//
//import com.microsystem.lme.util.Util;
//
//public class LoggerHelper {
//
//	/* Declaracion de variables*/
//	public Logger log = Logger.getLogger(this.getClass());
//	
//	public Date dateLcc = null;
//	public StringBuffer logLcc =  new StringBuffer();
//	
//	/* Setters y Getters de variables*/
//	public Date getDateLcc() {
//		return dateLcc;
//	}
//
//	public void setDateLcc(Date dateLcc) {
//		this.dateLcc = dateLcc;
//	}
//
//	public StringBuffer getLogLcc() {
//		return logLcc;
//	}
//
//	public void setLogLcc(StringBuffer logLcc) {
//		this.logLcc = logLcc;
//	}
//
//	/* Log INFO */
//	public void logInfo(String e) {
//		log.info(e);
//    }
//	
//	public void logLcc(String msg, Object[] paramArrayOfObject){
//		log.info(Util.stringFormat(msg,paramArrayOfObject));
//		logLcc(Util.stringFormat(msg,paramArrayOfObject));
//	}
//	
//	/* Log ERROR */
//	public void logError(String e){
//		log.error(e);
//	}
//	
//	public void logError(String msg, Exception e){
//		log.error(msg);
//		log.error(e.getStackTrace());
//	}
//	
//	
//	public void logError(Throwable e){
//		log.error(e.getClass() + "; "+ e.getMessage());
//	}
//	
//	public void logErrorLcc(Throwable e){
//		log.error(e.getClass() + "; "+ e.getMessage());
//		logLcc("ERROR "+ e.getMessage());
//	}
//	
//	/* Log FATAL */
//	public void logFatal(String e){
//		log.fatal(e);
//	}
//	
//	/* Extras */
//	public void logLcc(String message){
//		if(null!=dateLcc) logLcc.append("<br/>&nbsp;&nbsp;"+message);
//	}
//	
//}
