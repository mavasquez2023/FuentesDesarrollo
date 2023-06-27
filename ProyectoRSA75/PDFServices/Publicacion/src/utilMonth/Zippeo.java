package utilMonth;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zippeo {
	
	public static void gzipFile(String from, String to) throws IOException {
	    FileInputStream in = new FileInputStream(from);
	    GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = in.read(buffer)) != -1)
	      out.write(buffer, 0, bytesRead);
	    in.close();
	    out.close();
	  }

	  /** Zip the contents of the directory, and save it in the zipfile */
	  public static void zipDirectory(String dir, String zipfile)
	      throws IOException, IllegalArgumentException {
	    // Check that the directory is a directory, and get its contents
	    File d = new File(dir);
	    if (!d.isDirectory())
	      throw new IllegalArgumentException("Not a directory:  "
	          + dir);
	    String[] entries = d.list();
	    byte[] buffer = new byte[4096]; // Create a buffer for copying
	    int bytesRead;

	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

	    for (int i = 0; i < entries.length; i++) {
	      File f = new File(d, entries[i]);
	      if (f.isDirectory())
	        continue;//Ignore directory
	      FileInputStream in = new FileInputStream(f); // Stream to read file
	      ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
	      out.putNextEntry(entry); // Store entry
	      while ((bytesRead = in.read(buffer)) != -1)
	        out.write(buffer, 0, bytesRead);
	      in.close(); 
	      f.delete();
	    }
	    out.close();
	 

}   //this is good, this is used
	  public void zipFolder(String inf, String outf){
	  try
	  {
	  File inFolder=new File(inf);
	    File outFolder=new File(outf);
	  ZipOutputStream out = new ZipOutputStream(new 
	 BufferedOutputStream(new FileOutputStream(outFolder)));
	  BufferedInputStream in = null;
	  byte[] data  = new byte[1000];
	  String files[] = inFolder.list();
	  for (int i=0; i<files.length; i++)
	   {
	   in = new BufferedInputStream(new FileInputStream
	 (inFolder.getPath() + "/" + files[i]), 1000);  
	 out.putNextEntry(new ZipEntry(files[i])); 
	   int count;
	   while((count = in.read(data,0,1000)) != -1)
	   {
	  out.write(data, 0, count);
	   }
	   out.closeEntry();
	   }
	   out.flush();
	   out.close();
	  
	  }
	  
	   catch(Exception e)
	  {
	   e.printStackTrace();
	   } 
	  }
}
	 

