// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 15:27:20
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFObject.java

package cl.araucana.fpg;


// Referenced classes of package cl.araucana.fpg:
//            PDFTemplate

public class PDFObject
{

    public PDFObject(PDFTemplate parent)
    {
        linkID = -1;
        this.parent = parent;
    }

    public PDFTemplate getParent()
    {
        return parent;
    }

    public void setObjID(int objID)
    {
        this.objID = objID;
    }

    public int getObjID()
    {
        return objID;
    }

    public void setBaseType(String baseType)
    {
        this.baseType = baseType;
    }

    public String getBaseType()
    {
        return baseType;
    }

    public void setExtension(int extension)
    {
        this.extension = extension;
    }

    public int getExtension()
    {
        return extension;
    }

    public void setData(byte data[])
    {
        this.data = data;
    }

    public byte[] getData()
    {
        return data;
    }

    public boolean isLinked()
    {
        return linkID != -1;
    }

    public void setNewObjID(int objID)
    {
        newObjID = objID;
    }

    public int getNewObjID()
    {
        return newObjID;
    }

    public void setXData(byte data[])
    {
        xData = data;
    }

    public byte[] getXData()
    {
        return xData;
    }

    public String getObjectName()
    {
        if(baseType.equals("info"))
            return "info";
        else
            return baseType + "/" + objID;
    }

    protected void setLinkID(int linkID)
    {
        this.linkID = linkID;
    }

    protected int getLinkID()
    {
        return linkID;
    }

    public String toString()
    {
        return "parent=" + parent.getName() + " " + "objID=" + objID + " " + "baseType=" + baseType + " " + "extension=" + PDFTemplate.extensionNames[extension];
    }

    protected static final int UNLINKED_ID = -1;
    private PDFTemplate parent;
    private int objID;
    private String baseType;
    private int extension;
    private byte data[];
    private int linkID;
    private int newObjID;
    private byte xData[];
}
