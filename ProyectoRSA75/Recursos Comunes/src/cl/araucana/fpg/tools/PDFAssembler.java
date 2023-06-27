/**
 * 
 */
package cl.araucana.fpg.tools;

/**
 * @author usist199
 *
 */
import cl.araucana.fpg.PDFTemplate;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PDFAssembler
{

    public PDFAssembler()
    {
    }

    public static void help()
    {
        usage();
    }

    public static void usage()
    {
        System.err.println("ass <templateDir> <templateName> [<outputDir>]");
    }

    public static void main(String args[])
        throws Exception
    {
        if(args.length < 2 || args.length > 3)
        {
            usage();
            return;
        } else
        {
            String templateDir = args[0];
            String templateName = args[1];
            String outputDir = args.length != 3 ? "." : args[2];
            System.err.println("Assembling PDF template '" + templateName + "' ...");
            PDFTemplate template = new PDFTemplate(templateDir, templateName);
            template.setDebugMode(Boolean.getBoolean("pdf.debug"));
            template.load();
            byte content[] = template.assemble();
            String pdfFileName = outputDir + "/" + templateName + ".pdf";
            FileOutputStream output = new FileOutputStream(pdfFileName);
            output.write(content);
            output.close();
            System.err.println("Assembled PDF document was saved as '" + pdfFileName + "'.");
            return;
        }
    }
}

