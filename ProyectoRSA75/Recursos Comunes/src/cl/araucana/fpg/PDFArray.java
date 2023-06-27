// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 15:00:41
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFArray.java

package cl.araucana.fpg;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package cl.araucana.fpg:
//            FPGException

public class PDFArray
{

    public PDFArray()
    {
        values = new ArrayList();
    }

    public PDFArray(String text)
        throws FPGException
    {
        values = new ArrayList();
        int beginIndex = text.indexOf("[");
        int endIndex = text.lastIndexOf("]");
        if(beginIndex >= 0 && endIndex >= 0)
            text = text.substring(beginIndex + 1, endIndex - 1);
        int i = 0;
        for(int j = text.length(); i < j;)
        {
            char ch = text.charAt(i);
            if(" \t\r\n".indexOf(ch) >= 0)
            {
                i++;
            } else
            {
                String token;
                for(token = (new StringBuffer(String.valueOf(ch))).toString(); ++i < j; token = token + ch)
                {
                    ch = text.charAt(i);
                    if(" \t\r\n".indexOf(ch) >= 0)
                        break;
                }

                values.add(token);
            }
        }

    }

    public void addValue(String value)
    {
        values.add(value);
    }

    public int size()
    {
        return values.size();
    }

    public String getValue(int index)
    {
        checkIndex(index);
        return (String)values.get(index);
    }

    public int getIntValue(int index)
        throws NumberFormatException
    {
        return Integer.parseInt(getValue(index));
    }

    public String toString()
    {
        String s = "array[";
        for(int i = 0; i < values.size(); i++)
        {
            s = s + values.get(i);
            if(i + 1 < values.size())
                s = s + " ";
        }

        return s + "]";
    }

    public byte[] assemble()
    {
        String aux = "[";
        for(int i = 0; i < values.size(); i++)
        {
            aux = aux + values.get(i);
            if(i + 1 < values.size())
                aux = aux + " ";
        }

        aux = aux + "]\n";
        return aux.getBytes();
    }

    private void checkIndex(int index)
    {
        if(index < 0 || index >= values.size())
            throw new IndexOutOfBoundsException((new StringBuffer(String.valueOf(index))).toString());
        else
            return;
    }

    private static final String WHITE_SPACES = " \t\r\n";
    private List values;
}
