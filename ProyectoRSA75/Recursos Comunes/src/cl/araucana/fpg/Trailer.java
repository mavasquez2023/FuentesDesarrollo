// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:52:46
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Trailer.java

package cl.araucana.fpg;


public class Trailer
    implements Cloneable
{

    public Trailer()
    {
    }

    public Trailer(Trailer trailer)
    {
        nObjects = trailer.nObjects;
        rootObjID = trailer.rootObjID;
        infoObjID = trailer.infoObjID;
    }

    public Object clone()
    {
        return new Trailer(this);
    }

    public String toString()
    {
        return nObjects + " " + rootObjID + " " + infoObjID;
    }

    public int nObjects;
    public int rootObjID;
    public int infoObjID;
}
