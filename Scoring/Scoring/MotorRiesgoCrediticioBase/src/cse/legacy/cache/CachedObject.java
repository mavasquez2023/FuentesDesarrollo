/**
 * Title:        Caching
 * Description:  A Generic Cache Object wrapper.  Implements the Cacheable
 *               uses a TimeToLive stategy for CacheObject expiration.
 * Copyright:    Copyright (c) 2001
 * Company:  JavaWorld
 * Filename: CacheManagerTestProgram.java
 * @author Jonathan Lurie
 * @version 1.0
 */

package cse.legacy.cache;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CachedObject implements Cacheable {
	
	private static Logger logger = Logger.getLogger(CachedObject.class.getName());
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * This variable will be used to determine if the object is expired.
	 */
	private java.util.Date dateofExpiration = null;
	private Object identifier = null;
	/*
	 * This contains the real "value". This is the object which needs to be
	 * shared.
	 */
	public Object object = null;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public CachedObject(Object obj, Object id, int minutesToLive) {
		this.object = obj;
		this.identifier = id;
		// minutesToLive of 0 means it lives on indefinitely.
		if (minutesToLive != 0) {
			dateofExpiration = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateofExpiration);
			cal.add(Calendar.MINUTE, minutesToLive);
			dateofExpiration = cal.getTime();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public boolean isExpired() {
		// Remember if the minutes to live is zero then it lives forever!
		if (dateofExpiration != null) {
			// date of expiration is compared.
			if (dateofExpiration.before(new java.util.Date())) {
				logger.log(Level.FINE, "Object for " + identifier + " has expired from Cache! EXPIRE TIME: " + dateofExpiration.toString() + " CURRENT TIME: "
						+ (new java.util.Date()).toString());
				return true;
			} else {
				logger.log(Level.FINE, "Object for " + identifier + " is still valid");
				return false;
			}
		} else
			// This means it lives forever!
			return false;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Object getIdentifier() {
		return identifier;
	}
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
