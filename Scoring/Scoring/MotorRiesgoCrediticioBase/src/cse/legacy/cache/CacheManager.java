package cse.legacy.cache;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.system.helper.PropertyLoader;

/**
 * Title: Caching Description: Copyright: Copyright (c) 2001 Company: JavaWorld
 * Filename: CacheManager.java
 * 
 * @author Jonathan Lurie
 * @version 1.0
 */
public class CacheManager {
	
	private static Logger logger = Logger.getLogger(CacheManager.class.getName());
	
	private static String CHECKING_RATE;
	/* This is the HashMap that contains all objects in the cache. */
	private static java.util.HashMap cacheHashMap = new java.util.HashMap();
	/* This object acts as a semaphore, which protects the HashMap */
	/* RESERVED FOR FUTURE USE private static Object lock = new Object(); */
	
	protected static void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("cache.properties");
		// get the property value and print it out
		CHECKING_RATE = prop.getProperty("CHECKING_RATE");		
	}
	
	static {
		loadProperties();
		try {
			final int checkingRate ;
			int miliseconds;
			try {
				miliseconds = Integer.parseInt(CHECKING_RATE);
				logger.log(Level.WARNING, "Usando el valor " + miliseconds +  "ms para checkRate." );
			} catch (NumberFormatException nfe) {
				miliseconds = 1000 * 60 * 10;
				logger.log(Level.WARNING, "Usando el valor por defecto: "+ miliseconds +"ms para checkRate." );
			}
			checkingRate = miliseconds; 
			
			/*
			 * Create background thread, which will be responsible for purging
			 * expired items.
			 */
			Thread threadCleanerUpper = new Thread(new Runnable() {
				/*
				 * The default time the thread should sleep between scans. The
				 * sleep method takes in a millisecond value so 1000 * 60 * 10 = 10 minutes
				 */
				
				int milliSecondSleepTime = checkingRate;

				public void run() {
					try {
						/*
						 * Sets up an infinite loop. The thread will continue
						 * looping forever.
						 */
						while (true) {
							logger.log(Level.FINE,"ThreadCleanerUpper Scanning For Expired Objects...");
							/*
							 * Get the set of all keys that are in cache. These
							 * are the unique identifiers
							 */
							java.util.Set keySet = cacheHashMap.keySet();
							/* An iterator is used to move through the Keyset */
							java.util.Iterator keys = keySet.iterator();
							/*
							 * Sets up a loop that will iterate through each key
							 * in the KeySet
							 */
							while (keys.hasNext()) {
								/*
								 * Get the individual key. We need to hold on to
								 * this key in case it needs to be removed
								 */
								Object key = keys.next();
								/*
								 * Get the cacheable object associated with the
								 * key inside the cache
								 */
								Cacheable value = (Cacheable) cacheHashMap.get(key);
								/* Is the cacheable object expired? */
								if (value.isExpired()) {
									/*
									 * Yes it's expired! Remove it from the
									 * cache
									 */
									cacheHashMap.remove(key);
									logger.log(Level.FINE,"ThreadCleanerUpper Running. Found an Expired Object in the Cache.");
								}
							}
							/*
							 * ***********************************************************************
							 * ****** A LRU (least-recently used) or LFU
							 * (least-frequently used) *********** would best be
							 * inserted here****
							 * *********************************
							 * **************************************
							 */
							/*
							 * Puts the thread to sleep. Don't need to check it
							 * continuously
							 */
							Thread.sleep(this.milliSecondSleepTime);
						}
					} catch (Exception e) {
						logger.log(Level.WARNING, "Problemas con el Thread guardian del cache." + e);
						e.printStackTrace();
					}
					return;
				} /* End run method */
			}); /* End class definition and close new thread definition */
			// Sets the thread's priority to the minimum value.
			threadCleanerUpper.setPriority(Thread.MIN_PRIORITY);
			// Starts the thread.
			threadCleanerUpper.start();
		} catch (Exception e) {
			logger.log(Level.SEVERE, " Problemas con el arranque del CacheManager.Static Block: " + e);
		}
	} /* End static block */
	

	public static void putCache(Cacheable object) {
		// Remember if the HashMap previously contains a mapping for the key,
		// the old value
		// will be replaced. This is valid functioning.
		cacheHashMap.put(object.getIdentifier(), object);
	}

	public static Cacheable getCache(Object identifier) {
		// synchronized (lock) // UNCOMMENT LINE XXX
		// { // UNCOMMENT LINE XXX
		Cacheable object = (Cacheable) cacheHashMap.get(identifier);
		// The code to create the object would be placed here.
		// } // UNCOMMENT LINE XXX
		if (object == null)
			return null;
		if (object.isExpired()) {
			cacheHashMap.remove(identifier);
			return null;
		} else {
			return object;
		}
	}
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}