package cl.liv.comun.utiles;

import java.util.HashMap;

public class MiHashMap extends HashMap{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1853716113772371311L;

	public Object get(Object key) {
		// TODO Auto-generated method stub
		
		Object data = null; 
		data = super.get(key);
		if(data != null){
			return data;
		}
		data = super.get(key.toString().toLowerCase()); 
		if(data != null){
			return data;
		}
		data = super.get(key.toString().toUpperCase()); 
		if(data != null){
			return data;
		}
		
		return null;
	}
	
	public MiHashMap toMiHashMap(HashMap m){
		this.putAll(m);
		return this;
	}
	
}
