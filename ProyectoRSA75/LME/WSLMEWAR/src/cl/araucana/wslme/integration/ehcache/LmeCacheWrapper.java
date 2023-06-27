package cl.araucana.wslme.integration.ehcache;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class LmeCacheWrapper {

	private static String CACHE_NAME = "efxDefaultCache";
	private static final String EHCACHE_DEFAULT_CONFIG_XML = "/ehcache.xml";
	private static LmeCacheWrapper instance;
	private CacheManager cacheManager;

	private LmeCacheWrapper(String cacheName) throws CacheException {
		URL ehCacheConfig = this.getClass().getResource(EHCACHE_DEFAULT_CONFIG_XML);
		this.cacheManager = new CacheManager(ehCacheConfig);
		CACHE_NAME = cacheName;
		if (!this.cacheManager.cacheExists(CACHE_NAME)) {
			this.cacheManager.addCache(CACHE_NAME);
		}
	}

	private LmeCacheWrapper(String cacheName, URL configUrl)
			throws CacheException {
		this.cacheManager = new CacheManager(configUrl);
		CACHE_NAME = cacheName;
		if (!this.cacheManager.cacheExists(CACHE_NAME)) {
			this.cacheManager.addCache(CACHE_NAME);
		}
	}

	private Ehcache getCache() {
		return this.cacheManager.getEhcache(CACHE_NAME);
	}

	public static LmeCacheWrapper getInstance() {
		if (instance == null) {
			instance = new LmeCacheWrapper(CACHE_NAME);
		}
		return instance;
	}

	public static LmeCacheWrapper getInstance(String cacheName) {
		if (instance == null) {
			instance = new LmeCacheWrapper(cacheName);
		}
		return instance;
	}

	public static LmeCacheWrapper getInstance(String cacheName, URL configXml) {
		if (instance == null) {
			instance = new LmeCacheWrapper(cacheName, configXml);
		}
		return instance;
	}

	public void put(String key, Object value) {
		getCache().put(new Element(key, value));
	}

	public Object get(String key) {
		Element element = getCache().get(key);
		if (element != null) {
			return element.getValue();
		}
		return null;
	}

	public List getAllAndRemove() {
		List keys = getCache().getKeys();
		if (keys != null) {
			List result = new ArrayList(keys.size());
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				Element e = getCache().get(key);
				if (e != null)
					result.add(e.getValue());
				getCache().remove(key);
			}
			return result;
		}
		return null;
	}
}