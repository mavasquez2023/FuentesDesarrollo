/**
 * 
 */
package cl.araucana.fpg.tools;

/**
 * @author usist24
 *
 */
//Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 26-08-2015 13:49:55
//Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   PDFImage.java


import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.Image;
import java.io.*;

//Referenced classes of package cl.araucana.fpg.tools:
//         PDFObjectExtractor

public class PDFImage
{

 public PDFImage(String fileName, int width, int height)
     throws IOException
 {
     checkImageFile(fileName);
     log("image size: width=" + width + " height=" + height);
     int pageWidth = (int)PageSize.LETTER.width();
     int pageHeight = (int)PageSize.LETTER.height();
     log("page size: width=" + pageWidth + " height=" + pageHeight);
     Rectangle pageSize;
     int x0;
     int y0;
     if(width < pageWidth && height < pageHeight)
     {
         pageSize = PageSize.LETTER;
         x0 = (pageWidth - width) / 2;
         y0 = (pageHeight - height) / 2;
     } else
     if(width < pageHeight && height < pageWidth)
     {
         pageSize = PageSize.LETTER.rotate();
         x0 = (pageHeight - width) / 2;
         y0 = (pageWidth - height) / 2;
     } else
     {
         throw new IOException("Image too big to letter page size.");
     }
     Document document = new Document(pageSize, 36F, 36F, 36F, 36F);
     File tmpPDFFile = File.createTempFile("$$$", ".pdf");
     log("Temporal PDF file: " + tmpPDFFile);
     FileOutputStream output = null;
     PdfWriter writer = null;
     boolean pdfGenerated = false;
     try
     {
         output = new FileOutputStream(tmpPDFFile);
         writer = PdfWriter.getInstance(document, output);
         document.open();
         PdfContentByte cb = writer.getDirectContent();
         Graphics2D g = cb.createGraphics(pageWidth, pageHeight);
         g.drawImage(image, x0, y0, null);
         g.dispose();
         document.close();
         pdfGenerated = true;
     }
     catch(Exception e)
     {
         throw new IOException("Cannot load image file '" + fileName + "'. [cause=" + e.getMessage() + "]");
     }
     finally
     {
         if(output != null)
             try
             {
                 output.close();
             }
             catch(IOException ioexception) { }
         if(pdfGenerated)
         {
             PDFObjectExtractor extractor = new PDFObjectExtractor(tmpPDFFile.getPath());
             content = extractor.getObject(1);
         }
         if(!debug)
             tmpPDFFile.delete();
     }
     return;
 }

 public byte[] getContent()
 {
     return content;
 }

 private void checkImageFile(String fileName)
     throws IOException
 {
     String _fileName = fileName.toLowerCase();
     if(!_fileName.endsWith(".jpg") && !_fileName.endsWith(".gif"))
         throw new IOException("Only '.jpg' and '.gif' file extensions are supported.");
     FileInputStream input = null;
     try
     {
         input = new FileInputStream(fileName);
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
     Toolkit toolkit = Toolkit.getDefaultToolkit();
     image = toolkit.getImage(fileName);
     return;
 }

 public static void help()
 {
     usage();
 }

 public static void usage()
 {
     System.err.println("image2pdf <imageFileName> <width> <height> <pdfObjectFileName>");
 }

 public static void main(String args[])
 {
     if(args.length != 4)
     {
         usage();
         return;
     }
     PDFImage pdfImage = null;
     String imageFileName = args[0];
     int width = Integer.parseInt(args[1]);
     int height = Integer.parseInt(args[2]);
     String pdfObjectFileName = args[3];
     try
     {
         pdfImage = new PDFImage(imageFileName, width, height);
     }
     catch(IOException e)
     {
         System.err.println("ERROR: " + e.getMessage());
         return;
     }
     byte content[] = pdfImage.getContent();
     if(content == null)
     {
         System.err.println("Image's PDF object not found.");
         return;
     }
     FileOutputStream output = null;
     try
     {
         output = new FileOutputStream(pdfObjectFileName);
         output.write(content);
     }
     catch(IOException e)
     {
         System.err.println("ERROR: " + e.getMessage());
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

 private static void log(String message)
 {
     if(debug)
         System.out.println("PDFImage: " + message);
 }

 public static boolean debug = Boolean.getBoolean("fpg.image.debug");
 private Image image;
 private byte content[];

}

