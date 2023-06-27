// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:36:57
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFTemplate.java

package cl.araucana.fpg;

import cl.araucana.core.util.*;
import java.io.*;
import java.util.*;
import java.util.zip.Deflater;

// Referenced classes of package cl.araucana.fpg:
//            Trailer, FPGException, XRefEntry, PDFDictionary, 
//            PDFPages, MappedObjRef, PDFArray, PDFPage, 
//            PDFFont, PDFObject

public class PDFTemplate
{
	public static final int EXTENSION_NORMAL = 0;
	public static final int EXTENSION_INFLATED = 1;
	public static final int EXTENSION_CODED = 2;
	public static final int EXTENSION_COMPILED = 3;
	public static final String extensionNames[] = {
		"normal", "inflated", "coded", "compiled"
	};
	public static final String fileExtensions[] = {
		"", ".inflated", ".coded", ".compiled"
	};
	private static final int ASSEMBLED_REFERENCE_SIZE = 0x100000;
	private static final int FONTLIST_REFERENCE_SIZE = 16;
	private static final int PAGE_REFERENCE_COUNT = 128;
	private static final int FIXED_OBJECTS_REFERENCE_SIZE = 0x40000;
	private static final int RESOURCE_BUFFER_SIZE = 4096;
	private String templateDir;
	private String name;
	private String docType;
	private int docVersion;
	private String docDescription;
	private int startxref;
	private Trailer trailer;
	private XRefEntry xRefEntries[];
	private List addedXRefEntries;
	private byte prologData[];
	private byte infoData[];
	private PDFPages pdfPages;
	private List pages;
	private List fonts;
	private boolean debug;
	private boolean freezed;
	private byte fixedObjectsContent[];
	private byte fixedXRefContent[];
	private int defaultFontObjID;

    public PDFTemplate(String templateDir, String name)
    {
        startxref = 0;
        addedXRefEntries = new LinkedList();
        pages = new ArrayList(128);
        freezed = false;
        this.templateDir = templateDir;
        this.name = name;
    }

    public void load()
        throws IOException
    {
        Properties docProperties = loadProperties("docProperties");
        docType = docProperties.getProperty("type");
        if(docType == null)
            throw new IOException("Cannot read document type.");
        String sDocVersion = docProperties.getProperty("version");
        if(sDocVersion == null)
            throw new IOException("Cannot read document version.");
        try
        {
            docVersion = Integer.parseInt(sDocVersion);
        }
        catch(Exception e)
        {
            throw new IOException("Invalid document version '" + sDocVersion + "'.");
        }
        docDescription = docProperties.getProperty("description");
        if(docDescription == null)
            docDescription = "First Version";
        prologData = loadData("prolog");
        infoData = loadData("info");
        Properties trailerProperties = loadProperties("trailer");
        Properties xrefProperties = loadProperties("xref");
        trailer = new Trailer();
        trailer.nObjects = Integer.parseInt(trailerProperties.getProperty("size"));
        trailer.rootObjID = Integer.parseInt(trailerProperties.getProperty("root"));
        trailer.infoObjID = Integer.parseInt(trailerProperties.getProperty("info"));
        log("trailer = " + trailer);
        try
        {
            loadPDFPages();
        }
        catch(FPGException e)
        {
            throw new IOException("Cannot load PDFPages [cause=" + e.getMessage() + "]");
        }
        xRefEntries = new XRefEntry[trailer.nObjects];
        for(int index = 0; index < xRefEntries.length; index++)
        {
            String propertyValue = xrefProperties.getProperty((new StringBuffer(String.valueOf(index))).toString());
            if(propertyValue == null)
                throw new IOException("Unexpected xref EOF.");
            String tokens[] = propertyValue.split(" ");
            XRefEntry xRefEntry = new XRefEntry();
            xRefEntry.objID = Integer.parseInt(tokens[0]);
            xRefEntry.offset = Integer.parseInt(tokens[1]);
            xRefEntry.value = Integer.parseInt(tokens[2]);
            xRefEntry.mark = tokens[3].charAt(0);
            xRefEntry.baseType = tokens[4];
            xRefEntries[index] = xRefEntry;
            log("idx(" + index + ") = " + xRefEntry);
        }

    }

    public String getDocType()
    {
        return docType;
    }

    public int getDocVersion()
    {
        return docVersion;
    }

    public String getDocDescription()
    {
        return docDescription;
    }

    public void setProlog(byte data[])
    {
        prologData = data;
    }

    public byte[] getProlog()
    {
        return prologData;
    }

    public void saveProlog()
        throws IOException
    {
        saveData("prolog", prologData);
    }

    public void setInfo(byte data[])
    {
        infoData = data;
    }

    public byte[] getInfo()
    {
        return infoData;
    }

    public void saveInfo()
        throws IOException
    {
        saveData("info", infoData);
    }

    private void loadPDFPages()
        throws IOException, FPGException
    {
        log("loading PDFPages ...");
        String catalogText = new String(loadData("catalog/" + trailer.rootObjID));
        PDFDictionary catalogDict = new PDFDictionary(catalogText);
        int pagesObjID = catalogDict.getObjIDRefValue("/Pages");
        log("PDFPages objID = " + pagesObjID);
        String pagesText = new String(loadData("pages/" + pagesObjID));
        PDFDictionary pagesDict = new PDFDictionary(pagesText);
        pdfPages = new PDFPages(pagesObjID);
        PDFDictionary resourcesDict = pagesDict.getPDFDictionary("/Resources");
        if(resourcesDict != null)
        {
            PDFDictionary fontDict = resourcesDict.getPDFDictionary("/Font");
            if(fontDict != null)
            {
                log("Fonts:");
                for(int i = 0; i < fontDict.wordCount(); i++)
                {
                    String fontName = fontDict.getWord(i);
                    int fontObjID = fontDict.getObjIDRefValue(fontName);
                    pdfPages.addFontObjRef(new MappedObjRef(fontName, fontObjID));
                }

                List objRefs = pdfPages.getFontObjRefs();
                for(int i = 0; i < objRefs.size(); i++)
                    log("    " + objRefs.get(i));

            }
            PDFDictionary xObjectDict = resourcesDict.getPDFDictionary("/XObject");
            if(xObjectDict != null)
            {
                log("XObjects:");
                for(int i = 0; i < xObjectDict.wordCount(); i++)
                {
                    String xObjectName = xObjectDict.getWord(i);
                    int xObjectObjID = xObjectDict.getObjIDRefValue(xObjectName);
                    pdfPages.addXObjectObjRef(new MappedObjRef(xObjectName, xObjectObjID));
                }

                List objRefs = pdfPages.getXObjectObjRefs();
                for(int i = 0; i < objRefs.size(); i++)
                    log("    " + objRefs.get(i));

            }
        }
        String sKids = pagesDict.getValue("/Kids");
        List objIDs;
        if(sKids != null)
        {
            PDFArray kidsArray = new PDFArray(sKids);
            log("Kids:");
            for(int i = 0; i < kidsArray.size(); i += 3)
            {
                int pageObjID = kidsArray.getIntValue(i);
                pdfPages.addKidObjID(pageObjID);
            }

            objIDs = pdfPages.getKidObjIDs();
            for(int i = 0; i < objIDs.size(); i++)
                log("    " + objIDs.get(i));

        }
        int count = pagesDict.getIntValue("/Count");
        pdfPages.setCount(count);
        log("Count = " + pdfPages.getCount());
        objIDs = pdfPages.getKidObjIDs();
        for(int i = 0; i < objIDs.size(); i++)
        {
            int pageObjID = ((Integer)objIDs.get(i)).intValue();
            PDFPage page = new PDFPage(pageObjID);
            String pageText = new String(loadData("page/" + pageObjID));
            PDFDictionary pageDict = new PDFDictionary(pageText);
            int parentObjID = pageDict.getObjIDRefValue("/Parent");
            page.setParentObjID(parentObjID);
            log("page[" + i + "]:");
            log("    parent objID = " + page.getParentObjID());
            PDFArray contentsArray = new PDFArray(pageDict.getValue("/Contents"));
            for(int j = 0; j < contentsArray.size(); j += 3)
            {
                int contentObjID = contentsArray.getIntValue(j);
                page.addContentObjID(contentObjID);
            }

            List contentObjIDs = page.getContentObjIDs();
            for(int j = 0; j < contentObjIDs.size(); j++)
                log("    content #" + j + " = " + contentObjIDs.get(j));

            pageDict.removeWord("/Parent");
            pageDict.removeWord("/Contents");
            byte pageHeader[] = pageDict.assemble(true, false);
            page.setHeader(pageHeader);
            pages.add(page);
        }

        log("PDFPages loaded.");
    }

    public String getName()
    {
        return name;
    }

    public String getTemplateDir()
    {
        return templateDir;
    }

    public String getPath()
    {
        return templateDir + "/" + name;
    }

    public String getFullName()
    {
        return templateDir + "::" + name;
    }

    public void setDebugMode(boolean debug)
    {
        this.debug = debug;
    }

    public boolean isDebugMode()
    {
        return debug;
    }

    public int getPDFFontCount()
        throws IOException
    {
        if(fonts == null)
            getPDFFonts();
        return fonts.size();
    }

    public PDFFont getPDFFont(String name)
        throws IOException
    {
        if(fonts == null)
            getPDFFonts();
        for(int i = 0; i < fonts.size(); i++)
        {
            PDFFont font = (PDFFont)fonts.get(i);
            if(font.getName().equals(name))
                return font;
        }

        return null;
    }

    public List getPDFFonts()
        throws IOException
    {
        if(fonts != null)
            return fonts;
        fonts = new ArrayList(16);
        for(int i = 0; i < xRefEntries.length; i++)
        {
            XRefEntry xRefEntry = xRefEntries[i];
            if(xRefEntry.baseType.equals("font"))
            {
                ByteArray fontData = new ByteArray(loadData("font/" + xRefEntry.objID));
                int index = fontData.indexOf("/Name");
                String fontName = fontData.getNextToken(index + 5);
                PDFFont pdfFont = new PDFFont(fontName, xRefEntry.objID);
                index = fontData.indexOf("/CharProcs", index);
                int beginIndex = fontData.indexOf("<<", index);
                index = beginIndex + 3;
                int endIndex = fontData.indexOf(">>", index);
                String sCharProcs = fontData.getString(index, endIndex);
                String tokens[] = sCharProcs.split("[ \n]");
                for(int j = 0; j < tokens.length; j += 4)
                    pdfFont.addCharProc(tokens[j], Integer.parseInt(tokens[j + 1]));

                pdfFont.setHeader(fontData.getBytes(0, beginIndex));
                pdfFont.setTrailer(fontData.getBytes(endIndex + 3, fontData.length()));
                fonts.add(pdfFont);
                log("font = " + pdfFont);
            }
        }

        return fonts;
    }

    public int getPageCount()
    {
        return pages.size();
    }

    public PDFPage getPage(int index)
    {
        return (PDFPage)pages.get(index);
    }

    public int objectsCount()
    {
        return initialObjectsCount() + addedObjectsCount();
    }

    public int initialObjectsCount()
    {
        return xRefEntries.length;
    }

    public int addedObjectsCount()
    {
        return addedXRefEntries.size();
    }

    public void deleteObject(int objID)
        throws IOException
    {
        XRefEntry xRefEntry = getXRefEntry(objID);
        if(xRefEntry == null)
        {
            throw new IOException("Object " + objID + "not found");
        } else
        {
            removeData(xRefEntry);
            xRefEntry.release();
            log("object " + objID + " was deleted.");
            return;
        }
    }

    public void releaseObject(int objID)
    {
        XRefEntry xRefEntry = getXRefEntry(objID);
        if(xRefEntry == null)
        {
            return;
        } else
        {
            xRefEntry.release();
            log("object " + objID + " was released.");
            return;
        }
    }

    public int copyObject(int objID, PDFTemplate destTemplate)
        throws IOException
    {
        XRefEntry xRefEntry = getXRefEntry(objID);
        if(xRefEntry == null)
            throw new IOException("Object " + objID + "not found");
        XRefEntry destXRefEntry = destTemplate.getXRefEntry(objID);
        if(destXRefEntry != null)
        {
            destXRefEntry = destTemplate.allocateXRefEntry(xRefEntry.baseType);
        } else
        {
            destXRefEntry = destTemplate.getXRefEntry(objID, false);
            if(destXRefEntry != null)
            {
                destXRefEntry.offset = 0;
                destXRefEntry.value = 0;
                destXRefEntry.mark = 'n';
                destXRefEntry.baseType = xRefEntry.baseType;
            } else
            {
                destXRefEntry = destTemplate.allocateXRefEntry(xRefEntry.baseType);
            }
        }
        destTemplate.saveData(destXRefEntry, loadData(xRefEntry));
        log("object " + objID + " copied to " + destTemplate.getName() + "::" + destXRefEntry.objID);
        return destXRefEntry.objID;
    }

    public void linkObject(int objID, PDFTemplate destTemplate, int destObjID)
        throws IOException
    {
        linkObject(objID, destTemplate, destObjID, 0);
    }

    public void linkObject(int objID, PDFTemplate destTemplate, int destObjID, int extension)
        throws IOException
    {
        XRefEntry xRefEntry = getXRefEntryByIndex(objID);
        XRefEntry destXRefEntry = destTemplate.getXRefEntryByIndex(destObjID);
        if(!xRefEntry.baseType.equals(destXRefEntry.baseType))
        {
            throw new IOException("Base types must be the same to link object.");
        } else
        {
            String srcFileName = getFileName(xRefEntry, extension);
            String destFileName = destTemplate.getFileName(destXRefEntry, extension);
            log("linking " + srcFileName + " to " + destFileName);
            String linkedPath = "@" + destFileName;
            FileUtils.saveData(srcFileName, linkedPath.getBytes());
            return;
        }
    }

    public void removeFont(PDFFont font)
        throws IOException
    {
        if(getPDFFont(font.getName()) == null)
            throw new IOException("Font '" + font.getName() + "' not found.");
        log("Removing font " + font.getName() + " ...");
        List charProcs = font.getCharProcs();
        for(int i = 0; i < charProcs.size(); i++)
        {
            PDFFont.CharProc charProc = (PDFFont.CharProc)charProcs.get(i);
            deleteObject(charProc.getObjID());
        }

        deleteObject(font.getObjID());
        fonts.remove(font);
        pdfPages.removeFontObjRef(new MappedObjRef("", font.getObjID()));
        log("Font " + font.getName() + " removed.");
    }

    public void addFont(PDFTemplate srcTemplate, PDFFont srcFont)
        throws IOException
    {
        if(getPDFFont(srcFont.getName()) != null)
            throw new IOException("Font '" + srcFont.getName() + "' cannot be duplicated.");
        String srcFontName = srcTemplate.getName() + "::" + srcFont.getName();
        log("Adding font " + srcFontName + " ...");
        PDFFont newFont = (PDFFont)srcFont.clone();
        int objID = srcTemplate.copyObject(srcFont.getObjID(), this);
        newFont.setObjID(objID);
        List charProcs = srcFont.getCharProcs();
        for(int i = 0; i < charProcs.size(); i++)
        {
            PDFFont.CharProc charProc = (PDFFont.CharProc)charProcs.get(i);
            objID = srcTemplate.copyObject(charProc.getObjID(), this);
            charProc = newFont.getCharProc(charProc.getCode());
            charProc.setObjID(objID);
        }

        fonts.add(newFont);
        String $srcFontName = srcTemplate.pdfPages.getFontName(srcFont.getObjID());
        MappedObjRef objRef = new MappedObjRef($srcFontName, newFont.getObjID());
        pdfPages.addFontObjRef(objRef);
        log("Font " + srcFontName + " added.");
    }

    public void releasePage(PDFPage page)
    {
        int pageObjID = page.getObjID();
        pdfPages.removeKidObjID(pageObjID);
        pdfPages.decrementCount();
        releaseObject(pageObjID);
    }

    public int addObject(byte data[], String baseType)
    {
        XRefEntry xRefEntry = addNewXRefEntry(baseType);
        xRefEntry.objectData = data;
        return xRefEntry.objID;
    }

    public int addObject(String resourceName)
        throws IOException
    {
        InputStream input = getClass().getResourceAsStream("/etc/fpg/" + resourceName);
        if(input == null)
            throw new IOException("Resource '" + resourceName + "' not found.");
        ByteArrayOutputStream objectData = new ByteArrayOutputStream(4096);
        byte buffer[] = new byte[4096];
        int i;
        try
        {
            int size;
            while((size = input.read(buffer)) > 0) 
                objectData.write(buffer, 0, size);
            i = addObject(objectData.toByteArray(), "resource");
        }
        finally
        {
            try
            {
                input.close();
                objectData.close();
            }
            catch(IOException ioexception) { }
        }
        return i;
    }

    public int addFontObject(String name, int encodingObjID)
    {
        byte fontData[] = ("<<\n/Type/Font/BaseFont/" + name + "/Subtype/Type1\n" + "/Encoding " + encodingObjID + " 0 R\n" + ">>\n" + "endobj\n").getBytes();
        return addObject(fontData, "internal");
    }

    public void setDefaultFontObjID(int objID)
    {
        defaultFontObjID = objID;
    }

    public int getDefaultFontObjID()
    {
        return defaultFontObjID;
    }

    public byte[] assemble()
        throws IOException
    {
        return assemble(0x100000);
    }

    public byte[] assemble(int referenceSize)
        throws IOException
    {
        ByteArrayOutputStream output;
        output = null;
        Deflater deflater = new Deflater(9);
        startxref = 0;
        try
        {
            output = new ByteArrayOutputStream(referenceSize);
            output.write(prologData);
            startxref += prologData.length;
            for(int i = 0; i < xRefEntries.length; i++)
            {
                for(int j = 0; j < xRefEntries.length; j++)
                {
                    XRefEntry xRefEntry = xRefEntries[j];
                    if(xRefEntry.objID != i)
                        continue;
                    if(!xRefEntry.baseType.equals("null") && !xRefEntry.baseType.equals("info"))
                    {
                        xRefEntry.offset = startxref;
                        String objectName = xRefEntry.baseType.equals("void") ? "void/0" : xRefEntry.baseType + "/" + xRefEntry.objID;
                        long objectLastModified = lastModified(objectName);
                        long inflatedObjectLastModified = lastModified(objectName, 1);
                        if(inflatedObjectLastModified > objectLastModified)
                        {
                            log("Text object " + xRefEntry.objID + " changed");
                            byte objectData[] = loadData(objectName, 1);
                            byte encodedData[] = new byte[objectData.length];
                            deflater.setInput(objectData);
                            deflater.finish();
                            int encodedDataLength = deflater.deflate(encodedData);
                            if(encodedDataLength == encodedData.length)
                            {
                                encodedData = new byte[2 * objectData.length];
                                deflater.reset();
                                deflater.setInput(objectData);
                                deflater.finish();
                                encodedDataLength = deflater.deflate(encodedData);
                            }
                            deflater.reset();
                            String objectHeader = xRefEntry.objID + " 0 obj\n" + "<<\n" + "/Filter /FlateDecode /Length " + encodedDataLength + " >>\n" + "stream\n";
                            String objectTrailer = "endstream\nendobj\n";
                            byte objectHeaderBytes[] = objectHeader.getBytes();
                            byte objectTrailerBytes[] = objectTrailer.getBytes();
                            output.write(objectHeaderBytes);
                            output.write(encodedData, 0, encodedDataLength);
                            output.write(objectTrailerBytes);
                            startxref += objectHeaderBytes.length + encodedDataLength + objectTrailerBytes.length;
                        } else
                        {
                            byte objectData[] = loadData(objectName);
                            String objectHeader = xRefEntry.objID + " 0 obj\n";
                            byte objectHeaderBytes[] = objectHeader.getBytes();
                            output.write(objectHeaderBytes);
                            output.write(objectData);
                            startxref += objectHeaderBytes.length + objectData.length;
                        }
                    }
                    break;
                }

            }

            xRefEntries[trailer.infoObjID].offset = startxref;
            String infoHeader = trailer.infoObjID + " 0 obj\n";
            byte infoHeaderBytes[] = infoHeader.getBytes();
            output.write(infoHeaderBytes);
            output.write(infoData);
            startxref += infoHeaderBytes.length + infoData.length;
            println(output, "xref");
            println(output, "0 " + xRefEntries.length + " ");
            for(int i = 0; i < xRefEntries.length; i++)
            {
                XRefEntry xRefEntry = xRefEntries[i];
                println(output, Padder.lpad(xRefEntry.offset, 10, '0') + " " + Padder.lpad(xRefEntry.value, 5, '0') + " " + xRefEntry.mark + " ");
            }

            println(output, "trailer");
            println(output, "<<");
            println(output, "/Size " + trailer.nObjects + " ");
            println(output, "/Root " + trailer.rootObjID + " 0 R");
            println(output, "/Info " + trailer.infoObjID + " 0 R");
            println(output, ">>");
            println(output, "startxref");
            println(output, startxref + " ");
            println(output, "%%EOF");
        }
        finally
        {
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioexception) { }
            deflater.end();
        }
        return output.toByteArray();
    }

    public void save()
        throws IOException
    {
        saveObjects();
        saveXRef();
        saveTrailer();
    }

    public void saveObjects()
        throws IOException
    {
        List fonts = getPDFFonts();
        for(int i = 0; i < fonts.size(); i++)
        {
            PDFFont font = (PDFFont)fonts.get(i);
            saveData("font/" + font.getObjID(), font.assemble());
            log("font '" + font.getObjID() + "' was saved.");
        }

        for(int i = 0; i < pages.size(); i++)
        {
            PDFPage page = (PDFPage)pages.get(i);
            saveData("page/" + page.getObjID(), page.assemble());
            log("page '" + page.getObjID() + "' was saved.");
        }

        saveData("pages/" + pdfPages.getObjID(), pdfPages.assemble());
        log("pages '" + pdfPages.getObjID() + "' was saved.");
    }

    public void saveXRef()
        throws IOException
    {
        String xRefText = "# index = ObjID Offset Value Mark Type\n";
        int objectsCount = objectsCount();
        for(int i = 0; i < objectsCount; i++)
        {
            XRefEntry xRefEntry = getXRefEntryByIndex(i);
            xRefText = xRefText + i + "=" + xRefEntry.objID + " " + xRefEntry.offset + " " + xRefEntry.value + " " + xRefEntry.mark + " " + xRefEntry.baseType + "\n";
        }

        saveData("xref", xRefText.getBytes());
        log("xref was saved.");
    }

    public void saveTrailer()
        throws IOException
    {
        trailer.nObjects = objectsCount();
        String trailerText = "size=" + trailer.nObjects + "\n" + "root=" + trailer.rootObjID + "\n" + "info=" + trailer.infoObjID + "\n";
        saveData("trailer", trailerText.getBytes());
        log("trailer was saved.");
    }

    public byte[] trailerToByteArray()
    {
        return trailerToByteArray(trailer, startxref);
    }

    public static byte[] trailerToByteArray(Trailer trailer, int startxref)
    {
        String s = "trailer\n<<\n/Size " + trailer.nObjects + " \n" + "/Root " + trailer.rootObjID + " 0 R\n" + "/Info " + trailer.infoObjID + " 0 R\n" + ">>\n" + "startxref\n" + startxref + " \n" + "%%EOF\n";
        return s.getBytes();
    }

    public boolean isFreezed()
    {
        return freezed;
    }

    public void freeze()
        throws IOException
    {
        freeze(0x40000);
    }

    public void freeze(int fixedObjectsReferenceSize)
        throws IOException
    {
        ByteArrayOutputStream fixedObjects;
        ByteArrayOutputStream fixedXRef;
        if(freezed)
            return;
        fixedObjects = null;
        fixedXRef = null;
        Deflater deflater = new Deflater(9);
        startxref = 0;
        try
        {
            fixedObjects = new ByteArrayOutputStream(fixedObjectsReferenceSize);
            fixedXRef = new ByteArrayOutputStream(20 * xRefEntries.length);
            fixedObjects.write(prologData);
            startxref += prologData.length;
            for(int i = 0; i < xRefEntries.length; i++)
            {
                XRefEntry xRefEntry = xRefEntries[i];
                if(xRefEntry.isAssignable())
                    xRefEntry.assign("void");
                if(!xRefEntry.baseType.equals("null") && !xRefEntry.baseType.equals("info"))
                {
                    xRefEntry.offset = startxref;
                    String objectName = xRefEntry.baseType.equals("void") ? "void/0" : xRefEntry.baseType + "/" + xRefEntry.objID;
                    long objectLastModified = lastModified(objectName);
                    long inflatedObjectLastModified = lastModified(objectName, 1);
                    if(inflatedObjectLastModified > objectLastModified)
                    {
                        log("Text object " + xRefEntry.objID + " changed");
                        byte objectData[] = loadData(objectName, 1);
                        byte encodedData[] = new byte[objectData.length];
                        deflater.setInput(objectData);
                        deflater.finish();
                        int encodedDataLength = deflater.deflate(encodedData);
                        if(encodedDataLength == encodedData.length)
                        {
                            encodedData = new byte[2 * objectData.length];
                            deflater.reset();
                            deflater.setInput(objectData);
                            deflater.finish();
                            encodedDataLength = deflater.deflate(encodedData);
                        }
                        deflater.reset();
                        String objectHeader = xRefEntry.objID + " 0 obj\n" + "<<\n" + "/Filter /FlateDecode /Length " + encodedDataLength + " >>\n" + "stream\n";
                        String objectTrailer = "endstream\nendobj\n";
                        byte objectHeaderBytes[] = objectHeader.getBytes();
                        byte objectTrailerBytes[] = objectTrailer.getBytes();
                        fixedObjects.write(objectHeaderBytes);
                        fixedObjects.write(encodedData, 0, encodedDataLength);
                        fixedObjects.write(objectTrailerBytes);
                        startxref += objectHeaderBytes.length + encodedDataLength + objectTrailerBytes.length;
                    } else
                    {
                        byte objectData[] = loadData(objectName);
                        String objectHeader = xRefEntry.objID + " 0 obj\n";
                        byte objectHeaderBytes[] = objectHeader.getBytes();
                        fixedObjects.write(objectHeaderBytes);
                        fixedObjects.write(objectData);
                        startxref += objectHeaderBytes.length + objectData.length;
                    }
                }
            }

            for(int i = 0; i < addedXRefEntries.size(); i++)
            {
                XRefEntry xRefEntry = (XRefEntry)addedXRefEntries.get(i);
                xRefEntry.offset = startxref;
                String objectHeader = xRefEntry.objID + " 0 obj\n";
                byte objectHeaderBytes[] = objectHeader.getBytes();
                byte objectData[] = xRefEntry.objectData;
                fixedObjects.write(objectHeaderBytes);
                fixedObjects.write(xRefEntry.objectData);
                startxref += objectHeaderBytes.length + objectData.length;
            }

            XRefEntry infoXRefEntry = xRefEntries[trailer.infoObjID];
            if(infoXRefEntry.isAssigned() && infoXRefEntry.baseType.equals("info"))
            {
                infoXRefEntry.offset = startxref;
                String infoHeader = trailer.infoObjID + " 0 obj\n";
                byte infoHeaderBytes[] = infoHeader.getBytes();
                fixedObjects.write(infoHeaderBytes);
                fixedObjects.write(infoData);
                startxref += infoHeaderBytes.length + infoData.length;
            }
            for(int i = 0; i < xRefEntries.length; i++)
            {
                XRefEntry xRefEntry = xRefEntries[i];
                println(fixedXRef, Padder.lpad(xRefEntry.offset, 10, '0') + " " + Padder.lpad(xRefEntry.value, 5, '0') + " " + xRefEntry.mark + " ");
            }

            for(int i = 0; i < addedXRefEntries.size(); i++)
            {
                XRefEntry xRefEntry = (XRefEntry)addedXRefEntries.get(i);
                println(fixedXRef, Padder.lpad(xRefEntry.offset, 10, '0') + " " + Padder.lpad(xRefEntry.value, 5, '0') + " " + xRefEntry.mark + " ");
            }

        }
        finally
        {
            if(fixedObjects != null)
                try
                {
                    fixedObjects.close();
                }
                catch(IOException ioexception) { }
            if(fixedXRef != null)
                try
                {
                    fixedXRef.close();
                }
                catch(IOException ioexception1) { }
            deflater.end();
        }
        fixedObjectsContent = fixedObjects.toByteArray();
        fixedXRefContent = fixedXRef.toByteArray();
        trailer.nObjects = objectsCount();
        freezed = true;
        return;
    }

    public Trailer getTrailer()
    {
        return trailer;
    }

    public void setRootObjID(int objID)
    {
        trailer.rootObjID = objID;
    }

    public int getRootObjID()
    {
        return trailer.rootObjID;
    }

    public void setInfoObjID(int objID)
    {
        trailer.infoObjID = objID;
    }

    public int getInfoObjID()
    {
        return trailer.infoObjID;
    }

    public int getSize()
    {
        return trailer.nObjects;
    }

    public int getObjectCount()
    {
        return getSize();
    }

    public PDFPages getPDFPages()
    {
        return pdfPages;
    }

    public int getStartXRef()
    {
        return startxref;
    }

    public List getCompilableObjects()
    {
        return getObjects(2);
    }

    public List getObjects(int extension)
    {
        List compilableObjects = new LinkedList();
        int nObjects = getObjectCount();
        for(int objID = 1; objID < nObjects; objID++)
            try
            {
                PDFObject object = getObject(objID, extension);
                if(object != null)
                    compilableObjects.add(object);
            }
            catch(IOException ioexception) { }

        return compilableObjects;
    }

    public PDFObject getObject(int objID)
        throws IOException
    {
        return getObject(objID, 0);
    }

    public PDFObject getObject(int objID, int extension)
        throws IOException
    {
        XRefEntry xRefEntry = getXRefEntry(objID);
        if(xRefEntry == null)
        {
            return null;
        } else
        {
            PDFObject object = new PDFObject(this);
            object.setObjID(xRefEntry.objID);
            object.setBaseType(xRefEntry.baseType);
            object.setExtension(extension);
            byte data[] = loadData(xRefEntry, extension);
            object.setData(data);
            return object;
        }
    }

    public byte[] getFixedObjectsContent()
    {
        if(!freezed)
            throw new IllegalStateException("PDF Template not freezed");
        else
            return fixedObjectsContent;
    }

    public byte[] getFixedXRefContent()
    {
        if(!freezed)
            throw new IllegalStateException("PDF Template not freezed");
        else
            return fixedXRefContent;
    }

    private XRefEntry getXRefEntryByIndex(int index)
    {
        if(index < xRefEntries.length)
        {
            return xRefEntries[index];
        } else
        {
            index -= xRefEntries.length;
            return (XRefEntry)addedXRefEntries.get(index);
        }
    }

    private XRefEntry getXRefEntry(int objID)
    {
        return getXRefEntry(objID, true);
    }

    private XRefEntry getXRefEntry(int objID, boolean assigned)
    {
        for(int i = 0; i < xRefEntries.length; i++)
        {
            XRefEntry xRefEntry = xRefEntries[i];
            if(xRefEntry.objID == objID && xRefEntry.check(assigned))
                return xRefEntry;
        }

        for(int i = 0; i < addedXRefEntries.size(); i++)
        {
            XRefEntry xRefEntry = (XRefEntry)addedXRefEntries.get(i);
            if(xRefEntry.objID == objID && xRefEntry.check(assigned))
                return xRefEntry;
        }

        return null;
    }

    private XRefEntry allocateXRefEntry(String baseType)
    {
        for(int i = 0; i < xRefEntries.length; i++)
        {
            XRefEntry xRefEntry = xRefEntries[i];
            if(xRefEntry.isAssignable())
                return xRefEntry.assign(baseType);
        }

        for(int i = 0; i < addedXRefEntries.size(); i++)
        {
            XRefEntry xRefEntry = (XRefEntry)addedXRefEntries.get(i);
            if(xRefEntry.isAssignable())
                return xRefEntry.assign(baseType);
        }

        return addNewXRefEntry(baseType);
    }

    private XRefEntry addNewXRefEntry(String baseType)
    {
        XRefEntry xRefEntry = new XRefEntry();
        xRefEntry.objID = objectsCount();
        xRefEntry.assign(baseType);
        addedXRefEntries.add(xRefEntry);
        return xRefEntry;
    }

    private String getFileName(String name)
    {
        return getFileName(name, 0);
    }

    private String getFileName(String name, int extension)
    {
        return templateDir + "/" + this.name + "/" + name + ".txt" + fileExtensions[extension];
    }

    private String getFileName(XRefEntry xRefEntry)
    {
        return getFileName(xRefEntry, 0);
    }

    private String getFileName(XRefEntry xRefEntry, int extension)
    {
        if(!xRefEntry.baseType.equals("info"))
            return getFileName(xRefEntry.baseType + "/" + xRefEntry.objID, extension);
        else
            return getFileName("info", extension);
    }

    public Properties loadProperties(String name)
        throws IOException
    {
        return FileUtils.loadProperties(getFileName(name));
    }

    public byte[] loadData(XRefEntry xRefEntry)
        throws IOException
    {
        return loadData(xRefEntry, 0);
    }

    public byte[] loadData(XRefEntry xRefEntry, int extension)
        throws IOException
    {
        return _loadData(getFileName(xRefEntry, extension));
    }

    public byte[] loadData(String name)
        throws IOException
    {
        return loadData(name, 0);
    }

    public byte[] loadData(String name, int extension)
        throws IOException
    {
        return _loadData(getFileName(name, extension));
    }

    private byte[] _loadData(String name)
        throws IOException
    {
        byte data[] = FileUtils.loadData(name);
        if(data.length == 0 || data[0] != 64)
            return data;
        String linkedPath = new String(data, 1, data.length - 1);
        if(!linkedPath.startsWith("/"))
        {
            File file = new File(name);
            linkedPath = file.getParent() + "/" + linkedPath;
        }
        return FileUtils.loadData(linkedPath);
    }

    public void saveData(String name, byte data[])
        throws IOException
    {
        saveData(name, data, 0);
    }

    public void saveData(String name, byte data[], int extension)
        throws IOException
    {
        FileUtils.saveData(getFileName(name, extension), data);
    }

    public void saveData(XRefEntry xRefEntry, byte data[])
        throws IOException
    {
        FileUtils.saveData(getFileName(xRefEntry), data);
    }

    public void removeData(String name)
        throws IOException
    {
        removeData(name, 0);
    }

    public void removeData(String name, int extension)
        throws IOException
    {
        FileUtils.remove(getFileName(name, extension));
    }

    public void removeData(XRefEntry xRefEntry)
        throws IOException
    {
        FileUtils.remove(getFileName(xRefEntry));
    }

    private long lastModified(String name)
    {
        return lastModified(name, 0);
    }

    private long lastModified(String name, int extension)
    {
        return FileUtils.lastModified(getFileName(name, extension));
    }

    private void println(OutputStream output, String s)
        throws IOException
    {
        s = s + "\n";
        output.write(s.getBytes());
    }

    private void log(String message)
    {
        if(debug)
            System.err.println("PDFTemplate[" + name + "]: " + message);
    }

   

}
