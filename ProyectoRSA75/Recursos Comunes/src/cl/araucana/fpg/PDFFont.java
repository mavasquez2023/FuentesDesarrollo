// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 15:03:36
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFFont.java

package cl.araucana.fpg;

import java.util.ArrayList;
import java.util.List;

public class PDFFont
    implements Cloneable
{
	private static final int REFERENCE_CHARPROCS_COUNT = 32;
    private String name;
    private int objID;
    private byte header[];
    private byte trailer[];
    private List charProcs;


    public PDFFont(String name, int objID)
    {
        this(name, objID, 32);
    }

    public PDFFont(String name, int objID, int initialCharProcsCount)
    {
        this.name = name;
        this.objID = objID;
        charProcs = new ArrayList(initialCharProcsCount);
    }

    public Object clone()
    {
        PDFFont font = new PDFFont(name, objID, charProcs.size());
        font.header = new byte[header.length];
        font.trailer = new byte[trailer.length];
        System.arraycopy(header, 0, font.header, 0, header.length);
        System.arraycopy(trailer, 0, font.trailer, 0, trailer.length);
        for(int i = 0; i < charProcs.size(); i++)
        {
            CharProc charProc = (CharProc)charProcs.get(i);
            font.addCharProc((CharProc)charProc.clone());
        }

        return font;
    }

    public void setHeader(byte header[])
    {
        this.header = header;
    }

    public byte[] getHeader()
    {
        return header;
    }

    public void setTrailer(byte trailer[])
    {
        this.trailer = trailer;
    }

    public byte[] getTrailer()
    {
        return trailer;
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

    public void addCharProc(String code, int objID)
    {
        addCharProc(new CharProc(code, objID));
    }

    public void addCharProc(CharProc charProc)
    {
        charProcs.add(charProc);
    }

    public List getCharProcs()
    {
        return charProcs;
    }

    public String getStringCharProcs()
    {
        String result = "";
        for(int i = 0; i < charProcs.size(); i++)
        {
            result = result + charProcs.get(i);
            if(i + 1 < charProcs.size())
                result = result + " ";
        }

        return result;
    }

    public CharProc getCharProc(String code)
    {
        for(int i = 0; i < charProcs.size(); i++)
        {
            CharProc charProc = (CharProc)charProcs.get(i);
            if(charProc.getCode().equals(code))
                return charProc;
        }

        return null;
    }

    public byte[] assemble()
    {
        String aux = "<<\n";
        for(int i = 0; i < charProcs.size(); i++)
            aux = aux + charProcs.get(i) + " 0 R\n";

        aux = aux + ">>\n";
        byte auxBytes[] = aux.getBytes();
        byte assembled[] = new byte[header.length + auxBytes.length + trailer.length];
        System.arraycopy(header, 0, assembled, 0, header.length);
        System.arraycopy(auxBytes, 0, assembled, header.length, auxBytes.length);
        System.arraycopy(trailer, 0, assembled, header.length + auxBytes.length, trailer.length);
        return assembled;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof PDFFont))
        {
            return false;
        } else
        {
            PDFFont other = (PDFFont)obj;
            return getName().equals(other.getName());
        }
    }

    public String toString()
    {
        return name + " " + objID;
    }
    
    public static class CharProc implements Cloneable
    {
    	private String code;
    	private int objID;
    	public CharProc(String code, int objID)
    	{
    		this.code = code;
    		this.objID = objID;
    	}
    	public Object clone()
    	{
    		return new CharProc(code, objID);
    	}

    	public String getCode()
    	{
    		return code;
    	}

    	public void setObjID(int objID)
    	{
    		this.objID = objID;
    	}

    	public int getObjID()
    	{
    		return objID;
    	}

    	public String toString()
    	{
    		return code + " " + objID;
    	}


    }
}

