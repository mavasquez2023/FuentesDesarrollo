/*
 * Created on 02-11-2011
 *
 */
package com.microsystem.lme.web;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONValue;

import com.microsystem.lme.ibatis.domain.LmeLogVO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.Util;

/**
 * @author microsystem
 *
 */
public class JobLogAction extends DispatchAction {
	private Logger log = Logger.getLogger(this.getClass());
	private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getLog(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//rango fechas
		//ultimo dia
		//ultima hora
		//id time log/error

		LmeLogVO vo = new LmeLogVO();
		vo.setFechaEvento(Util.getToday());
		//vo.setTipoEvento("ERROR");

		try {
			List l = svc_a.getLog(vo);
			List list = new LinkedList();
			for (Iterator iter = l.iterator(); iter.hasNext();) {
				LmeLogVO o = (LmeLogVO) iter.next();
				Map h = new HashMap();
				//h.put("evento", o.getEvento());
				h.put("tipo", o.getTipoEvento());
				h.put("hora", o.getHoraEvento());
				h.put("fecha", o.getFechaEvento());
				h.put("msg", o.getMsg());
				list.add(h);
			}
			String jsonString = JSONValue.toJSONString(list);
			response.setContentType("text/json");
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (SvcException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		
		InitConexion_LME.closeConexion_LME();
		
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportLogFile_(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String upDir = "\\..\\..\\..\\..";
		String projectDir = request.getSession().getServletContext().getRealPath("/");
		System.out.println("REAL PATH: " + request.getSession().getServletContext().getRealPath("/"));

		projectDir = projectDir + upDir + "\\logs\\lme.log";

		//File file = new File("C:\\Archivos de programa\\IBM\\Rational\\SDP\\6.0\\runtimes\\base_v6\\profiles\\default\\logs\\lme.log");
		File file = new File(projectDir);

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {

				// this statement reads the line from the file and print it to
				// the console.
				//System.out.println(dis.readLine());
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}

		return mapping.findForward("init");
	}

	public void exportLogFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String upDir = "\\..\\..\\..\\..";
		//String projectDir = request.getSession().getServletContext().getRealPath("/");		
		//projectDir = projectDir + upDir + "\\logs\\lme.log";
		String projectDir = System.getProperty("user.dir");
		String logsDir = "logs";
		String logFile = "lme.log";

		File f = new File(projectDir + File.separator + logsDir + File.separator + logFile);
		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = request.getSession().getServletContext();
		String mimetype = context.getMimeType(projectDir);

		//
		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + logFile + "\"");

		//
		//  Stream to the requester.
		//
		byte[] bbuf = new byte[16384];
		DataInputStream in = new DataInputStream(new FileInputStream(f));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();
		//
		//response.getWriter().write(op.toString());
		log("user.dir:" + System.getProperty("user.dir"));
		log(projectDir + File.separator + logsDir + File.separator + logFile);
	}

	static void log(String message) {
		System.out.println(message);
	}

}
