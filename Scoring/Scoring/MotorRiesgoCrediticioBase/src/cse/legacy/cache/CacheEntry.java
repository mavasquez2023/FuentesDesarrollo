package cse.legacy.cache;

public class CacheEntry {

	private Class dataClass;
	private Object dataObject;

	public CacheEntry(Object objectData, Class objectClass) {
		this.dataClass = objectClass;
		this.dataObject = objectData;
	}

	public Class getDataClass() {
		return dataClass;
	}

	public void setDataClass(Class dataClass) {
		this.dataClass = dataClass;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

}
