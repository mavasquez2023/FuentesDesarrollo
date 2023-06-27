// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:53:38
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XRefEntry.java

package cl.araucana.fpg;


public class XRefEntry
    implements Comparable
{

    public XRefEntry()
    {
    }

    public int compareTo(Object o)
    {
        XRefEntry other = (XRefEntry)o;
        return offset - other.offset;
    }

    public boolean isAssigned()
    {
        return mark == 'n';
    }

    public boolean isReleased()
    {
        return mark == 'f';
    }

    public boolean isAssignable()
    {
        return isReleased() && value != 65535;
    }

    public boolean check(boolean assigned)
    {
        if(assigned)
            return isAssigned();
        else
            return isAssignable();
    }

    public void release()
    {
        offset = objID;
        value = 1;
        mark = 'f';
        baseType = null;
    }

    public XRefEntry assign(String baseType)
    {
        offset = 0;
        value = 0;
        mark = 'n';
        this.baseType = baseType;
        return this;
    }

    public XRefEntry assign(int objID, String baseType)
    {
        this.objID = objID;
        return assign(baseType);
    }

    public String toString()
    {
        return objID + " " + offset + " " + value + " " + mark + " " + baseType;
    }

    public static final int SIZE = 20;
    public static final int RESERVED = 65535;
    public int objID;
    public int offset;
    public int value;
    public char mark;
    public String baseType;
    public byte objectData[];
}
