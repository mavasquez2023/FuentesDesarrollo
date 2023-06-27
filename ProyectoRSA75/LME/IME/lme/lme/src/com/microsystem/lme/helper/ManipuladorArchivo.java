package com.microsystem.lme.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManipuladorArchivo {

	public void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		FileInputStream fileToDownload = new FileInputStream(filePath);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setContentLength(fileToDownload.available());

		int c;

		while ((c = fileToDownload.read()) != -1) {
			out.write(c);
		}

		out.flush();
		out.close();

		fileToDownload.close();
	}

	public void upload(HttpServletRequest request, HttpServletResponse response, String filePathEntrada, String fileNameEntrada, String contentType, String fileName, byte[] fileData)
			throws ServletException, IOException {

		//Guardar archivo en el servidor
		if (!fileName.equals("")) {

			File fileToCreate = new File(filePathEntrada, fileName);

			if (!fileToCreate.exists()) {

				FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
				fileOutStream.write(fileData);

				fileOutStream.flush();
				fileOutStream.close();

			}
		}
	}
}
