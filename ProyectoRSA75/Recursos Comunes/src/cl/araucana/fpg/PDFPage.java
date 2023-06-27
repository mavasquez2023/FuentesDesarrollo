// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:54:54
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFPage.java

package cl.araucana.fpg;

import java.util.ArrayList;
import java.util.List;

public class PDFPage
    implements Cloneable
{

    public PDFPage(int objID)
    {
        contentObjIDs = new ArrayList(8);
        this.objID = objID;
    }

    public void setObjID(int objID)
    {
        this.objID = objID;
    }

    public int getObjID()
    {
        return objID;
    }

    public void setParentObjID(int objID)
    {
        parentObjID = objID;
    }

    public int getParentObjID()
    {
        return parentObjID;
    }

    public void addContentObjIDs(int objIDs[])
    {
        for(int i = 0; i < objIDs.length; i++)
        {
            Integer newObjID = new Integer(objIDs[i]);
            contentObjIDs.add(newObjID);
        }

    }

    public void addContentObjID(int objID)
    {
        Integer newObjID = new Integer(objID);
        if(!contentObjIDs.contains(newObjID))
            contentObjIDs.add(newObjID);
    }

    public void removeAllContentObjIDs()
    {
        contentObjIDs.clear();
    }

    public void removeContentObjID(int objID)
    {
        contentObjIDs.remove(new Integer(objID));
    }

    public List getContentObjIDs()
    {
        return contentObjIDs;
    }

    public void setContentObjID(int index, int objID)
    {
        contentObjIDs.set(index, new Integer(objID));
    }

    public int getContentObjID(int index)
    {
        return ((Integer)contentObjIDs.get(index)).intValue();
    }

    public void setHeader(byte header[])
    {
        this.header = header;
    }

    public byte[] getHeader()
    {
        return header;
    }

    public byte[] assemble()
    {
        String aux = "/Parent " + parentObjID + " 0 R\n" + "/Contents [";
        for(int i = 0; i < contentObjIDs.size(); i++)
            aux = aux + contentObjIDs.get(i) + " 0 R\n";

        aux = aux + "]\n>>\nendobj\n";
        byte auxBytes[] = aux.getBytes();
        byte assembled[] = new byte[header.length + auxBytes.length];
        System.arraycopy(header, 0, assembled, 0, header.length);
        System.arraycopy(auxBytes, 0, assembled, header.length, auxBytes.length);
        return assembled;
    }

    public String toString()
    {
        return (new StringBuffer(String.valueOf(objID))).toString();
    }

    private static final int REFERENCE_CONTENT_COUNT = 8;
    private int objID;
    private byte header[];
    private int parentObjID;
    private List contentObjIDs;
}
