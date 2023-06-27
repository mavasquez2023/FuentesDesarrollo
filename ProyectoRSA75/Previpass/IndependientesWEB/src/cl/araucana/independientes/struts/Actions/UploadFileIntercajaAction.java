package cl.araucana.independientes.struts.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cl.araucana.independientes.helper.IND_Constants;
import cl.araucana.independientes.helper.ManipuladorArchivo;
import cl.araucana.independientes.struts.Forms.UploadFileForm;

public class UploadFileIntercajaAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) //throws Exception
    {
        int opcion;

        HttpSession session = request.getSession();

        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        UploadFileForm uplFileForm = (UploadFileForm) form;

        opcion = Integer.parseInt(uplFileForm.getOpcion());

        switch (opcion){


        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1:

            return mapping.findForward("menuIntercaja");

        case 2:
            UploadFileForm fileForm = uplFileForm;

            try{

                FormFile theFile = fileForm.getTheFile();
                String contentType = theFile.getContentType();
                String fileName = theFile.getFileName();
                byte[] fileData = theFile.getFileData();

                String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

                if(extension.equals(IND_Constants.EXT_texto))
                {
                    String fecha = "";
                    String DATE_FORMAT2 = "dd/MM/yyyy";
                    SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

                    //Asignar fecha de sistema.
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

                    date = cal.getTime();
                    fecha = sdf2.format(date);
                    fecha = fecha.substring(0,2)+ fecha.substring(3,5)+ fecha.substring(6,10);				
                    ManipuladorArchivo subir = new ManipuladorArchivo();

                    File file = new File("");
                    StringBuffer rutaFile = new StringBuffer();
                    StringBuffer nameFile = new StringBuffer();

                    rutaFile.append(file.getAbsolutePath());
                    rutaFile.append(IND_Constants.DIR_UPLFILE);

                    String filePath = rutaFile.toString();//;getServlet().getServletContext().getRealPath("/");

                    //nombre del archivo.
                    nameFile.append(fecha);
                    nameFile.append("_");
                    nameFile.append(IND_Constants.SUF_INF_SalidaIntercaja);
                    nameFile.append(IND_Constants.EXT_texto);

                    String fileNameOutput = nameFile.toString();

                    System.out.println("filePath: " + filePath);
                    System.out.println("fileNameOutput: " + fileNameOutput);
                    System.out.println("contentType: " + contentType);
                    System.out.println("fileName: " + fileName);

                    subir.upload(request, response, filePath, fileNameOutput, contentType, fileName, fileData);

                }else{
                    System.out.println("GENARCHENTINTERCACTION.JAVA, Error en la extensión del archivo: no válida. -->" + extension);
                }


            }catch(IOException e){
                e.printStackTrace();
            }
            catch(ServletException f){
                f.printStackTrace();
            }

            return mapping.findForward("generarArchEntrada");

        default: 	

            return mapping.findForward("menuIntercaja");

        }
    }
}
