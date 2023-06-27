// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 15:30:57
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MappedObjRef.java

package cl.araucana.fpg;


public class MappedObjRef
    implements Cloneable, Comparable
{

    public MappedObjRef(String name, int objID)
    {
        this.name = name;
        this.objID = objID;
    }

    public Object clone()
    {
        return new MappedObjRef(name, objID);
    }

    public String getName()
    {
        return name;
    }

    public void setObjID(int objID)
    {
        this.objID = objID;
    }

    public int getObjID()
    {
        return objID;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof MappedObjRef))
            return false;
        MappedObjRef objRef = (MappedObjRef)obj;
        return name.equals(objRef.name) || objID == objRef.objID;
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof MappedObjRef))
            return 1;
        MappedObjRef objRef = (MappedObjRef)o;
        int id1 = getID();
        int id2 = objRef.getID();
        if(id1 == -1 || id2 == -1)
            return name.compareTo(objRef.name);
        else
            return id1 - id2;
    }

    private int getID()
    {
        try {
			return Integer.parseInt(name.substring(2));
		} catch (NumberFormatException e) {
			return -1;
		}
       
    }

    public String toString()
    {
        return name + " " + objID;
    }

    private String name;
    private int objID;
}
