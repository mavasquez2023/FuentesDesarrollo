package cl.araucana.spl.util;

import org.apache.commons.collections.map.LRUMap;

/**
 * Caché de tamaño fijo con expiración de objetos.
 * 
 * @see org.apache.commons.collections.map.LRUMap
 * 
 * WARNING: Al usar, sincronizar la instancia de esta clase.
 * 
 */
public class LRUCache {
	private LRUMap map;
	private int timeoutMillis;

	/**
	 *  
	 * @param cacheSize
	 * @param timeoutMillis
	 */
	public LRUCache(int cacheSize, int timeoutMillis) {
		this.map = new LRUMap(cacheSize);
		this.timeoutMillis = timeoutMillis;
	}
	
	/**
	 * Ademas de hacer lo obvio, elimina lo items expirados.
	 */
	public boolean contains(String key) {
		boolean out = false;
		Long cuandoExpira = (Long) map.get(key);
		if (cuandoExpira != null) {
			long now = System.currentTimeMillis();
			if (now < cuandoExpira.longValue()) {
				// Existe y aun no ha expirado.
				out = true;
			} else {
				// Existe pero esta expirado ==> se elimina.
				map.remove(key);
			}
		}
		return out;
	}
	public void put(String correlativo) {
		Long cuandoExpira = new Long(System.currentTimeMillis() + timeoutMillis);
		map.put(correlativo, cuandoExpira);
	}
}
