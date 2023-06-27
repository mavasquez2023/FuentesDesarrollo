package cl.araucana.independientes.helper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ManipuladorArchivo {

    public void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName)throws 
    ServletException, IOException{

        PrintWriter out = response.getWriter();

        FileInputStream fileToDownload = new FileInputStream(filePath);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment; filename=" + fileName);
        response.setContentLength(fileToDownload.available());

        int c;

        while((c=fileToDownload.read()) != -1){
            out.write(c);
        }

        out.flush();
        out.close();

        fileToDownload.close();
    }

    public void upload(HttpServletRequest request, HttpServletResponse response, String filePathEntrada, String fileNameEntrada, String contentType, String fileName, byte[] fileData)throws 
    ServletException, IOException{

        //Guardar archivo en el servidor
        if(!fileName.equals("")){

            File fileToCreate = new File(filePathEntrada, fileName);

            if(!fileToCreate.exists()){

                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                fileOutStream.write(fileData);

                fileOutStream.flush();
                fileOutStream.close();

            }
        }
    }
}
