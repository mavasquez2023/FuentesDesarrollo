// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 16-12-2021 19:40:32
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFObjectExtractor.java

package cl.araucana.fpg.tools;

import cl.araucana.core.util.ByteArray;
import java.io.*;

// Referenced classes of package cl.araucana.fpg.tools:
//            PDFDisassembler

public class PDFObjectExtractor extends PDFDisassembler
{

    public PDFObjectExtractor(String pdfFileName)
        throws IOException
    {
        super(pdfFileName, null, null);
        content = new ByteArray(super.content);
    }

    public byte[] getObject(int objID)
    {
        byte pattern[] = (objID + " 0 obj").getBytes();
        int beginObjIndex = content.indexOf(pattern);
        if(beginObjIndex == -1)
            return null;
        pattern = "\nendobj".getBytes();
        int endObjIndex = content.indexOf(pattern, beginObjIndex);
        if(endObjIndex == -1)
            return null;
        beginObjIndex += pattern.length;
        if(beginObjIndex + 1 > content.length())
            return null;
        if(content.getByteAt(beginObjIndex) == 32)
            beginObjIndex++;
        else
        if(content.getByteAt(beginObjIndex) == 10)
            beginObjIndex++;
        else
        if(content.getByteAt(beginObjIndex) == 13)
            beginObjIndex += 2;
        endObjIndex += pattern.length;
        if(endObjIndex + 1 > content.length())
            return null;
        if(content.getByteAt(endObjIndex) == 10)
            endObjIndex++;
        else
        if(content.getByteAt(endObjIndex) == 13)
            endObjIndex += 2;
        return content.getBytes(beginObjIndex, endObjIndex);
    }

    public static void help()
    {
        usage();
    }

    public static void usage()
    {
        System.err.println("objextract [-i] <pdfFileName> <objID> <outputFileName>");
    }

    public static void main(String args[])
    {
        String pdfFileName;
        int objID;
        String outputFileName;
        FileOutputStream output;
        int index = 0;
        int expectedArgCount = 3;
        if(args.length > 0 && args[0].equals("-i"))
        {
            index = 1;
            expectedArgCount = 4;
        }
        if(args.length != expectedArgCount)
        {
            usage();
            return;
        }
        pdfFileName = args[index++];
        try
        {
            objID = Integer.parseInt(args[index++]);
        }
        catch(NumberFormatException e)
        {
            System.err.println("Invalid object id '" + args[index - 1] + "'.");
            return;
        }
        System.out.println("Searching PDF object '" + objID + "' ...");
        outputFileName = args[index++];
        output = null;
        byte content[];
        try
        {
        PDFObjectExtractor extractor = new PDFObjectExtractor(pdfFileName);
        content = extractor.getObject(objID);
        if(content == null)
        {
            System.err.println("PDF object '" + objID + "' not found.");
            return;
        }
       
            output = new FileOutputStream(outputFileName);
            output.write(content);
            System.out.println("PDF object '" + objID + "' saved in " + "'" + outputFileName + ".");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally{
        if(output != null)
            try
            {
                output.close();
            }
            catch(IOException ioexception) { }
        }
        return;
    }

    private ByteArray content;
}
