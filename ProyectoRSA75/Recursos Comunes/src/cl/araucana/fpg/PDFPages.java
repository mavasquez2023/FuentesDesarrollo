// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:56:08
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFPages.java

package cl.araucana.fpg;

import java.util.*;

// Referenced classes of package cl.araucana.fpg:
//            MappedObjRef

public class PDFPages
    implements Cloneable
{

    public PDFPages(PDFPages pdfPages)
    {
        objID = pdfPages.objID;
        count = pdfPages.count;
        fontObjRefs = copyMappedObjRefList(pdfPages.fontObjRefs, 16);
        xObjectObjRefs = copyMappedObjRefList(pdfPages.xObjectObjRefs, 8);
        kidObjIDs = new ArrayList(Math.max(pdfPages.kidObjIDs.size(), 8));
        kidObjIDs.addAll(pdfPages.kidObjIDs);
    }

    public PDFPages(int objID)
    {
        this.objID = objID;
        fontObjRefs = new ArrayList(16);
        xObjectObjRefs = new ArrayList(8);
        kidObjIDs = new ArrayList(8);
    }

    public Object clone()
    {
        return new PDFPages(this);
    }

    public void setObjID(int objID)
    {
        this.objID = objID;
    }

    public int getObjID()
    {
        return objID;
    }

    public void addFontObjRef(MappedObjRef objRef)
    {
        if(!fontObjRefs.contains(objRef))
            fontObjRefs.add(objRef);
    }

    public void addXObjectObjRef(MappedObjRef objRef)
    {
        if(!xObjectObjRefs.contains(objRef))
            xObjectObjRefs.add(objRef);
    }

    public void addKidObjID(int objID)
    {
        Integer newObjID = new Integer(objID);
        if(!kidObjIDs.contains(newObjID))
            kidObjIDs.add(newObjID);
    }

    public void removeFontObjRef(MappedObjRef objRef)
    {
        fontObjRefs.remove(objRef);
    }

    public void removeXObjectObjRef(MappedObjRef objRef)
    {
        xObjectObjRefs.remove(objRef);
    }

    public void removeKidObjID(int objID)
    {
        kidObjIDs.remove(new Integer(objID));
    }

    public List getFontObjRefs()
    {
        return fontObjRefs;
    }

    public List getXObjectObjRefs()
    {
        return xObjectObjRefs;
    }

    public List getKidObjIDs()
    {
        return kidObjIDs;
    }

    public String getFontName(int objID)
    {
        return getMappedObjRefName(fontObjRefs, objID);
    }

    public String getXObjectName(int objID)
    {
        return getMappedObjRefName(xObjectObjRefs, objID);
    }

    public int getFontObjID(String name)
    {
        return getMappedObjRefObjID(fontObjRefs, name);
    }

    public int getXObjectObjID(String name)
    {
        return getMappedObjRefObjID(xObjectObjRefs, name);
    }

    public void adjustCount()
    {
        count = kidObjIDs.size();
    }

    public void setCount(int count)
    {
        if(count < 0)
            throw new IllegalArgumentException("count must be non negative number");
        if(count > kidObjIDs.size())
        {
            throw new IllegalArgumentException("count cannot be great than number of kids");
        } else
        {
            this.count = count;
            return;
        }
    }

    public int getCount()
    {
        return count;
    }

    public void incrementCount()
    {
        setCount(count + 1);
    }

    public void decrementCount()
    {
        setCount(count - 1);
    }

    public byte[] assemble()
    {
        Collections.sort(fontObjRefs);
        Collections.sort(xObjectObjRefs);
        String aux = "<<\n/Type /Pages\n/Resources <<\n/ProcSet [/PDF /Text /ImageB ]\n";
        aux = aux + "/Font <<\n";
        for(int i = 0; i < fontObjRefs.size(); i++)
            aux = aux + fontObjRefs.get(i) + " 0 R\n";

        aux = aux + ">>\n";
        aux = aux + "/XObject <<\n";
        for(int i = 0; i < xObjectObjRefs.size(); i++)
            aux = aux + xObjectObjRefs.get(i) + " 0 R\n";

        aux = aux + ">>\n";
        aux = aux + "/ColorSpace <<\n/CS1 [/Pattern /DeviceRGB ]\n>>\n";
        aux = aux + ">>\n";
        aux = aux + "/Kids [";
        for(int i = 0; i < kidObjIDs.size(); i++)
            aux = aux + kidObjIDs.get(i) + " 0 R\n";

        aux = aux + "]\n";
        aux = aux + "/Count " + count + "\n" + ">>\n" + "endobj\n";
        return aux.getBytes();
    }

    public String toString()
    {
        return (new StringBuffer(String.valueOf(objID))).toString();
    }

    private String getMappedObjRefName(List mappedObjRefs, int objID)
    {
        for(int i = 0; i < mappedObjRefs.size(); i++)
        {
            MappedObjRef objRef = (MappedObjRef)mappedObjRefs.get(i);
            if(objRef.getObjID() == objID)
                return objRef.getName();
        }

        return null;
    }

    private int getMappedObjRefObjID(List mappedObjRefs, String name)
    {
        for(int i = 0; i < mappedObjRefs.size(); i++)
        {
            MappedObjRef objRef = (MappedObjRef)mappedObjRefs.get(i);
            if(objRef.getName() == name)
                return objRef.getObjID();
        }

        return -1;
    }

    private ArrayList copyMappedObjRefList(List mappedObjRefList, int referenceSize)
    {
        ArrayList copiedList = new ArrayList(Math.max(mappedObjRefList.size(), referenceSize));
        for(int i = 0; i < mappedObjRefList.size(); i++)
        {
            MappedObjRef mappedObjRef = (MappedObjRef)mappedObjRefList.get(i);
            MappedObjRef _mappedObjRef = (MappedObjRef)mappedObjRef.clone();
            copiedList.add(_mappedObjRef);
        }

        return copiedList;
    }

    private static final int REFERENCE_FONT_COUNT = 16;
    private static final int REFERENCE_XOBJECT_COUNT = 8;
    private static final int REFERENCE_KID_COUNT = 8;
    private int objID;
    private List fontObjRefs;
    private List xObjectObjRefs;
    private List kidObjIDs;
    private int count;
}
