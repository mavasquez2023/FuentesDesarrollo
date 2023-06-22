/*
 * Created on Jun 4, 2012
 *
 */
package cse.legacy.dao.cache.impl;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.legacy.cache.CacheManager;
import cse.legacy.cache.CachedObject;
import cse.legacy.dao.cache.DataBoardAgent;
import cse.model.businessobject.Rut;
import cse.system.helper.PropertyLoader;

public class DataBoardAgentImpl implements DataBoardAgent {

	// private HashMap dateBoard;
	//
	// private HashMap rawDataBoard;
	//
	// private int hoursToLive;
	//
	private boolean cacheEnabled;

	private static String CACHE_MODE;
	private static String MINUTES_TO_LIVE;

	private static DataBoardAgent myInstance;

	private static Logger logger = Logger.getLogger(DataBoardAgentImpl.class.getName());

	public static synchronized DataBoardAgent getInstance() {
		if (myInstance == null) {
			myInstance = new DataBoardAgentImpl();
		}
		return myInstance;
	}

	private DataBoardAgentImpl() {
		// dateBoard = new HashMap();
		// rawDataBoard = new HashMap();
		this.loadProperties();
		cacheEnabled = Boolean.valueOf(CACHE_MODE).booleanValue();
		if (cacheEnabled)
			logger.log(Level.INFO, "Caching is ENABLED");
	}

	public String lookupAS400StringData(Rut rut) {
		String data = null;
		if (cacheEnabled) {
			logger.entering(this.getClass().getName(), "lookupStringData", rut);
			/* Try to retrieve the object from the cache! */
			CachedObject foundObject = (CachedObject) CacheManager.getCache(rut);
			/* Let's see if we got it! */			
			if (foundObject == null) {
				logger.log(Level.FINE, "Object not found in the cache.");
			} else {
				logger.log(Level.FINE, "Object found in the cache: " + foundObject.object);
				Object wrappedObject = foundObject.object;
				if (wrappedObject instanceof String) {
					data = (String) wrappedObject;
				}
			}
			logger.exiting(this.getClass().getName(), "lookupStringData", data);
		} else {
			//do nothing
		}
		return data;
	}

	public void publishAS400StringData(Rut rut, String rawData) {
		if (cacheEnabled) {
			logger.entering(this.getClass().getName(), "publishStringData", new Object[] { rut,
					rawData });
			int ttl = Integer.parseInt(MINUTES_TO_LIVE);
			CachedObject cachedObject = new CachedObject(rawData, rut, ttl);
			/* Place the object into the cache! */
			CacheManager.putCache(cachedObject);
			logger.exiting(this.getClass().getName(), "publishStringData");
		} else {
			// do nothing
		}
	}

	// public String lookupDataOld(Rut rut) {
	// logger.entering(this.getClass().getName(), "lookupData", rut);
	// String dataFound = null;
	// if(cacheEnabled){
	// logger.log(Level.INFO, "Caching is ENABLED");
	// logger.log(Level.INFO, "Searching data for " + rut);
	// Date foundDate = (Date) dateBoard.get(rut);
	// if (foundDate != null) {
	// logger.log(Level.INFO, "Data found from " + foundDate);
	// Date today = new Date();
	// int diffInHours = (int) ((today.getTime() - foundDate.getTime()) / (1000
	// * 60 * 60));
	// logger.log(Level.INFO, "Checking if data is recent enough ");
	// if (diffInHours > hoursToLive) {
	// logger.log(Level.INFO, "Sorry. It's too old");
	// removeData(rut);
	// } else {
	// logger.log(Level.INFO, "Good. It's recent data");
	// dataFound = (String) rawDataBoard.get(rut);
	// }
	// }
	// else {
	// logger.log(Level.INFO, "No data found for "+ rut);
	// }
	// } else {
	// logger.log(Level.INFO, "Caching is DISABLED");
	// //do nothing
	// }
	// logger.exiting(this.getClass().getName(), "lookupData", dataFound);
	// return dataFound;
	// }
	//
	// public void publishDataOld(Rut rut, String data) {
	// logger.entering(this.getClass().getName(), "publishData", new
	// Object[]{rut, data});
	// if (cacheEnabled){
	// logger.log(Level.INFO, "Publishing " + data + " into the data board for "
	// + rut);
	// Date dateOfPublish = new Date();
	// dateBoard.put(rut, dateOfPublish);
	// rawDataBoard.put(rut, data);
	// } else {
	// logger.log(Level.INFO, "Caching is DISABLED");
	// //do nothing
	// }
	// logger.exiting(this.getClass().getName(), "publishData");
	// }

	protected void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("cache.properties");
		// get the property value and print it out
		CACHE_MODE = prop.getProperty("ENABLE_CACHE");
		MINUTES_TO_LIVE = prop.getProperty("AS400_DATA.MINUTES_TO_LIVE");
	}

}
