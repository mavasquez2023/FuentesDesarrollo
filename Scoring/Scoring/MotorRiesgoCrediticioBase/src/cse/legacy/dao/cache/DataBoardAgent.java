package cse.legacy.dao.cache;

import cse.model.businessobject.Rut;


public interface DataBoardAgent {
    
    public String lookupAS400StringData(Rut rut);

	public void publishAS400StringData(Rut rut, String rawData);
    
  
}
