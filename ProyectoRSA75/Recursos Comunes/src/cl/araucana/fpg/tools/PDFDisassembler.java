/**
 * 
 */
package cl.araucana.fpg.tools;

/**
 * @author usist24
 *
 */
import cl.araucana.core.util.FileUtils;
import cl.araucana.fpg.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class PDFDisassembler
{
	private static final boolean FORWARD_DIRECTION = true;
    private static final boolean BACKWARD_DIRECTION = false;
    private static final byte BEGIN_TEXT[] = {
        10, 66, 84, 10
    };
    private static final byte END_TEXT[] = {
        10, 69, 84, 10
    };
    private String name;
    private String baseName;
    protected byte content[];
    private boolean debug;
    private int offset;
    private int beginIndex;
    private int endIndex;
    private int startxref;
   // private XRefEntry xRefEntries[];
    //private Trailer trailer;
    
    public PDFDisassembler(String pdfFileName, String templateDir)
        throws IOException
    {
        this(pdfFileName, templateDir, null);
    }

    public PDFDisassembler(String pdfFileName, String templateDir, String templateName)
        throws IOException
    {
        File file;
        offset = 0;
        if(!pdfFileName.endsWith(".pdf") && !pdfFileName.endsWith(".PDF"))
            throw new IOException("Unexpected PDF filename '" + pdfFileName + "'.");
        FileInputStream input = null;
        file = new File(pdfFileName);
        content = new byte[(int)file.length()];
        try
        {
            input = new FileInputStream(file);
            if((long)input.read(content) != file.length())
                throw new IOException("Unexpected mismatch size.");
        }
        finally
        {
            if(input != null)
                try
                {
                    input.close();
                }
                catch(IOException ioexception) { }
        }
        if(templateName == null)
        {
            name = file.getName();
            name = name.substring(0, name.length() - 4);
        } else
        {
            name = templateName;
        }
        if(templateDir==null){
        	templateDir="C:/tmp/PDF/template";
        }
        baseName = templateDir + "/" + name;
        debug = Boolean.getBoolean("pdf.debug");
        log("pdfFileName = " + pdfFileName);
        log("name = " + name);
        log("basename = " + baseName);
        return;
    }

    public void disassemble()
        throws IOException
    {
        createStruct(baseName);
    }

    public void createStruct(String baseName)
        throws IOException
    {
        String dirNames[] = {
            "object", "text", "image", "font", "page", "pages", "catalog", "xobject", "void"
        };
        for(int i = 0; i < dirNames.length; i++)
        {
            File dir = new File(baseName + "/" + dirNames[i]);
            if(dir.mkdirs())
                log("'" + dir + "' directory created.");
            else
                throw new IOException("Cannot create directory '" + dir + "'.");
        }

        log("copying source pdf document ...");
        FileOutputStream output = null;
        try
        {
            output = new FileOutputStream(baseName + "/source.pdf");
            output.write(content);
        }
        finally
        {
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioexception) { }
        }
        writeFile("void/0.txt", "endobj\n");
        return;
    }

    private void readLine(boolean forward)
    {
        if(forward)
        {
            if(offset >= content.length)
            {
                beginIndex = endIndex = -1;
                return;
            }
            int index = offset;
            beginIndex = offset;
            for(; content[index] != 13 && content[index] != 10; index++);
            endIndex = index - 1;
            if(content[index] == 13)
            {
                if(++index < content.length && content[index] == 10)
                    index++;
            } else
            {
                index++;
            }
            offset = index;
        } else
        {
            if(offset <= 0)
            {
                beginIndex = endIndex = -1;
                return;
            }
            int index = offset;
            if(content[index] == 13)
                index--;
            else
            if(--index >= 0 && content[index] == 13)
                index--;
            endIndex = index;
            for(; index >= 0 && content[index] != 13 && content[index] != 10; index--);
            if(index < 0)
            {
                beginIndex = 0;
                offset = 0;
            } else
            {
                beginIndex = index + 1;
                offset = index;
            }
        }
    }

    public void gotoEndOfContent()
    {
        offset = content.length - 1;
    }

    public byte[] getLine()
    {
        return getLine(true);
    }

    public byte[] getLine(boolean forward)
    {
        readLine(forward);
        if(endIndex == beginIndex && endIndex == -1)
        {
            return null;
        } else
        {
            byte line[] = new byte[(endIndex - beginIndex) + 1];
            System.arraycopy(content, beginIndex, line, 0, line.length);
            return line;
        }
    }

    public String getStringLine()
    {
        return getStringLine(true);
    }

    public String getStringLine(boolean forward)
    {
        byte line[] = getLine(forward);
        if(line == null)
            return null;
        else
            return new String(line);
    }

    public void writeFile(String fileName, String text)
        throws IOException
    {
        byte data[] = text.getBytes();
        writeFile(fileName, data, 0, data.length);
    }

    public void writeFile(String fileName, int beginIndex, int endIndex)
        throws IOException
    {
        writeFile(fileName, content, beginIndex, (endIndex - beginIndex) + 1);
    }

    public void writeFile(String fileName, byte data[], int offset, int size)
        throws IOException
    {
        FileOutputStream output = null;
        try
        {
            output = new FileOutputStream(baseName + "/" + fileName);
            output.write(data, offset, size);
        }
        finally
        {
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioexception) { }
        }
        return;
    }

    private boolean getStartXRef(String line)
    {
        try
        {
            startxref = Integer.parseInt(line);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    private boolean isTextStream(byte data[], int length)
    {
        int beginIndex = indexOf(BEGIN_TEXT, data, 0, length);
        if(beginIndex < 0)
            return false;
        return indexOf(END_TEXT, data, beginIndex + BEGIN_TEXT.length, length) > 0;
    }

    protected int indexOf(byte pattern[], byte data[], int begin, int end)
    {
label0:
        for(int i = begin; i < end; i++)
        {
            int k = 0;
            for(int j = 0; j < pattern.length; j++)
                if(data[i + k++] != pattern[j])
                    continue label0;

            return i;
        }

        return -1;
    }

    private void log(String message)
    {
        if(debug)
            System.err.println(message);
    }

    public static void help()
    {
        usage();
    }

    public static void usage()
    {
        System.err.println("dis [-i] <pdfFileName> <docType> <docVersion> <templateDir> [<templateName>]");
    }

    public static void main(String args[])
    {
    	/*
        boolean inflateAlways;
        String pdfFileName;
        String docType;
        String docVersion;
        String templateDir;
        String templateName;
        PDFDisassembler disassembler;
        inflateAlways = false;
        pdfFileName = null;
        docType = null;
        docVersion = null;
        templateDir = null;
        templateName = null;
        disassembler = null;
        if(args.length < 4 || args.length > 6)
        {
            usage();
            return;
        }
        int index;
        index = 0;
        if(args[0].equals("-i"))
        {
            inflateAlways = true;
            index = 1;
        }
        pdfFileName = args[index++];
        docType = args[index++];
        docVersion = args[index++];
        templateDir = args[index++];
        try
        {
            Integer.parseInt(docVersion);
        }
        catch(NumberFormatException e)
        {
            System.err.println("Invalid document version '" + docVersion + "'.");
            return;
        }
        String xRefText;
        int i;
        if(index < args.length)
            templateName = args[index];
        disassembler = new PDFDisassembler(pdfFileName, templateDir, templateName);
        disassembler.remove();
        disassembler.disassemble();
        disassembler.writeFile("docProperties.txt", "type=" + docType + "\nversion=" + docVersion + "\n");
        byte line[];
        while((line = disassembler.getLine(true)) != null && line[0] == 37) 
        {
            String sLine = new String(line);
            disassembler.log("prolog: |" + sLine + "|");
        }
        disassembler.writeFile("prolog.txt", 0, disassembler.offset - line.length - 2);
        disassembler.gotoEndOfContent();
        String textLine = disassembler.getStringLine(false);
        if(textLine == null || !textLine.equals("%%EOF"))
            throw new IOException("Unexpected BOF. [%%EOF was expected]");
        textLine = disassembler.getStringLine(false);
        if(textLine == null || !disassembler.getStartXRef(textLine.trim()))
            throw new IOException("Unexpected BOF. [startxref offset was expected]");
        disassembler.log("startxref = " + disassembler.startxref);
        textLine = disassembler.getStringLine(false);
        if(textLine == null || !textLine.equals("startxref"))
            throw new IOException("Unexpected BOF. [startxref was expected]");
        List trailerLines = new ArrayList();
        do
        {
            textLine = disassembler.getStringLine(false);
            if(textLine == null)
                throw new IOException("Unexpected BOF. [trailer was expected]");
            if(textLine.equals("trailer"))
            {
                disassembler.log("trailer found");
                break;
            }
            if(!textLine.startsWith("%"))
                trailerLines.add(textLine.trim());
        } while(true);
        String trailerText = "";
        for(int i = trailerLines.size() - 1; i >= 0; i--)
            trailerText = trailerText + (String)trailerLines.get(i) + " ";

        disassembler.log("trailer = |" + trailerText + "|?");
        PDFDictionary trailerDict = new PDFDictionary(trailerText);
        disassembler.log("trailer: " + trailerDict);
        Trailer trailer = new Trailer();
        trailer.nObjects = trailerDict.getIntValue("/Size");
        trailer.rootObjID = trailerDict.getObjIDRefValue("/Root");
        trailer.infoObjID = trailerDict.getObjIDRefValue("/Info");
        disassembler.trailer = trailer;
        disassembler.log("trailer ok [" + trailer + "]");
        disassembler.writeFile("trailer.txt", "size=" + trailer.nObjects + "\n" + "root=" + trailer.rootObjID + "\n" + "info=" + trailer.infoObjID + "\n");
        disassembler.log("xref:");
        disassembler.offset = disassembler.startxref;
        textLine = disassembler.getStringLine(true);
        if(!textLine.equals("xref"))
            throw new IOException("Xref not found.");
        textLine = disassembler.getStringLine(true);
        String tokens[] = textLine.trim().split(" ");
        if(tokens.length != 2)
            throw new IOException("Unexpected xref.");
        int xRefSize;
        try
        {
            xRefSize = Integer.parseInt(tokens[1]);
        }
        catch(NumberFormatException e)
        {
            throw new IOException("Invalid xref size '" + tokens[1] + "'");
        }
        if(xRefSize != trailer.nObjects)
            throw new IOException("Mismatched trailer and xref sizes.");
        disassembler.xRefEntries = new XRefEntry[xRefSize];
        for(int i = 0; i < xRefSize; i++)
        {
            textLine = disassembler.getStringLine(true);
            tokens = textLine.trim().split(" ");
            XRefEntry xRefEntry = new XRefEntry();
            xRefEntry.offset = Integer.parseInt(tokens[0]);
            xRefEntry.value = Integer.parseInt(tokens[1]);
            xRefEntry.mark = tokens[2].charAt(0);
            if(xRefEntry.mark == 'f')
            {
                xRefEntry.objID = i;
                xRefEntry.offset = i;
            }
            disassembler.xRefEntries[i] = xRefEntry;
        }

        for(int i = 0; i < disassembler.xRefEntries.length; i++)
        {
            XRefEntry xRefEntry = disassembler.xRefEntries[i];
            if(xRefEntry.mark == 'n')
            {
                disassembler.offset = xRefEntry.offset;
                textLine = disassembler.getStringLine();
                tokens = textLine.trim().split(" ");
                xRefEntry.objID = Integer.parseInt(tokens[0]);
            }
        }

        disassembler.log("ObjID Offset Value Mark");
        xRefText = "# index = ObjID Offset Value Mark Type\n";
        i = 0;
          goto _L1
_L12:
        XRefEntry xRefEntry = disassembler.xRefEntries[i];
        if(xRefEntry.mark != 'n') goto _L3; else goto _L2
_L2:
        int offset;
        String headerText;
        int beginIndex;
        disassembler.offset = xRefEntry.offset;
        String textLine;
        for(textLine = disassembler.getStringLine(); !textLine.equals("endobj"); textLine = disassembler.getStringLine());
        offset = disassembler.offset - 1;
        headerText = "";
        disassembler.offset = xRefEntry.offset;
        do
        {
            textLine = disassembler.getStringLine();
            headerText = headerText + textLine.trim() + "\n";
        } while(!textLine.endsWith("stream") && !textLine.endsWith("endobj"));
        disassembler.log("\n" + xRefEntry.objID + ": header = |" + headerText + "|");
        beginIndex = headerText.indexOf("<<");
        if(xRefEntry.objID == disassembler.trailer.infoObjID) goto _L5; else goto _L4
_L4:
        String baseType;
        PDFDictionary objHeaderDict;
        baseType = "text";
        objHeaderDict = null;
        if(headerText.indexOf("<<") <= 0) goto _L7; else goto _L6
_L6:
        String objType;
        objHeaderDict = new PDFDictionary(headerText);
        objType = objHeaderDict.getValue("/Type");
        if(objType == null) goto _L9; else goto _L8
_L8:
        if(objType.equals("/Page"))
            baseType = "page";
        else
        if(objType.equals("/Pages"))
            baseType = "pages";
        else
        if(objType.equals("/Font"))
            baseType = "font";
        else
        if(objType.equals("/Catalog"))
            baseType = "catalog";
        else
        if(objType.equals("/XObject"))
        {
            String objSubType = objHeaderDict.getValue("/Subtype");
            if(objSubType != null && objSubType.equals("/Image"))
                baseType = "image";
            else
                baseType = "xobject";
        }
          goto _L7
_L9:
        int length;
        length = 0;
        String objFilter = objHeaderDict.getValue("/Filter");
        if(objFilter != null && objFilter.equals("/FlateDecode"))
            length = objHeaderDict.getIntValue("/Length");
        if(length <= 0) goto _L7; else goto _L10
_L10:
        disassembler.offset = xRefEntry.offset;
        String textLine;
        do
            textLine = disassembler.getStringLine();
        while(!textLine.endsWith("stream"));
        int streamOffset = disassembler.offset;
        Inflater inflater = new Inflater();
        inflater.setInput(disassembler.content, streamOffset, length);
        byte decodedData[] = new byte[20 * length];
        try
        {
            int decodedLength = inflater.inflate(decodedData);
            if(inflateAlways || disassembler.isTextStream(decodedData, decodedLength))
                disassembler.writeFile("text/" + xRefEntry.objID + ".txt.inflated", decodedData, 0, decodedLength);
        }
        catch(DataFormatException e)
        {
            System.err.println("WARNING: PDF Object '" + i + "' " + "couldn't be " + "uncompressed. [cause=" + e.getMessage() + "] " + "offset=" + streamOffset + " " + "length=" + length);
        }
        finally
        {
            inflater.end();
        }
_L7:
        xRefEntry.baseType = baseType;
        disassembler.writeFile(baseType + "/" + xRefEntry.objID + ".txt", xRefEntry.offset + beginIndex, offset);
        if(objHeaderDict != null)
            disassembler.log(i + ": " + xRefEntry + " " + objHeaderDict);
        else
            disassembler.log(i + ": " + xRefEntry);
          goto _L3
_L5:
        xRefEntry.baseType = "info";
        disassembler.writeFile("info.txt", xRefEntry.offset + beginIndex, offset);
        disassembler.log(xRefEntry.objID + ": [info]");
_L3:
        xRefText = xRefText + i + "=" + xRefEntry.objID + " " + xRefEntry.offset + " " + xRefEntry.value + " " + xRefEntry.mark + " " + xRefEntry.baseType + "\n";
        i++;
_L1:
        if(i < disassembler.xRefEntries.length) goto _L12; else goto _L11
_L11:
        disassembler.writeFile("xref.txt", xRefText);
        break MISSING_BLOCK_LABEL_2183;
        Exception e;
        e;
        e.printStackTrace();
        if(disassembler != null)
            disassembler.remove();
        */
    }

    private void remove()
    {
        try
        {
            FileUtils.removeDir(baseName);
        }
        catch(Exception exception) { }
    }

    

}
