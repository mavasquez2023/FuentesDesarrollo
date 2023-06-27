/*
 * Created on 21-11-2011
 *
 */
package com.microsystem.lme.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONValue;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;
import com.microsystem.lme.helper.Helper;
import com.microsystem.lme.ibatis.domain.Ilfe002VO;
import com.microsystem.lme.ibatis.domain.Ilfe082VO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.IAS400Svc_SIL;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.InitConexion_SIL;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.SvcFactory_SIL;
import com.microsystem.lme.util.Constants;

/**
 * @author microsystem
 *
 */
public class LmeAdmin extends DispatchAction {

	private IAS400Svc_SIL svc_b = null;
	private IAS400Svc_LME svc_a = null;
	private static final String SVC_NAME = "DETALLE";

	public void getLmeCero(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		svc_b = SvcFactory_SIL.getAS400Svc_SIL();

		List l = svc_b.getLmeCero();

		List list = new LinkedList();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			Ilfe002VO vo = (Ilfe002VO) iter.next();
			Map h = new HashMap();
			h.put("ideOpe", vo.getIdeOpe());
			h.put("numimpre", vo.getNumImpre());
			h.put("dvimpre", vo.getDvImpre());
			h.put("estLicen", vo.getEstadoLicencia());
			h.put("afiRut", String.valueOf(vo.getAfiRut()));
			h.put("fechaOpr", vo.getFechaOpr());
			h.put("msgErr", vo.getMsgErr());
			h.put("numimprela", vo.getNumimprela());
			h.put("fechaEstado", vo.getFechaEstado());
			h.put("horaEstado", vo.getHoraEstado());
			list.add(h);
		}
		String jsonString = JSONValue.toJSONString(list);
		response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(jsonString);

		InitConexion_SIL.closeConexion_SIL();
	}

	public void getOperador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		svc_b = SvcFactory_SIL.getAS400Svc_SIL();

		List l = svc_b.getLmeCero();

		List list = new LinkedList();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			Ilfe002VO vo = (Ilfe002VO) iter.next();
			Map h = new HashMap();
			h.put("ideOpe", vo.getIdeOpe());
			h.put("numimpre", vo.getNumImpre());
			h.put("estLicen", vo.getEstadoLicencia());
			h.put("afiRut", String.valueOf(vo.getAfiRut()));
			h.put("fechaOpr", vo.getFechaOpr());
			h.put("msgErr", vo.getMsgErr());
			list.add(h);
		}
		String jsonString = JSONValue.toJSONString(list);
		response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(jsonString);

		InitConexion_SIL.closeConexion_SIL();
	}

	public void updateLmeCero(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		svc_b = SvcFactory_SIL.getAS400Svc_SIL();
		HashMap map = new HashMap();

		map.put("afiRut", Long.valueOf(request.getParameter("afiRut")));
		map.put("estLicen", Integer.valueOf(request.getParameter("estLicen")));
		map.put("ideOpe", Integer.valueOf(request.getParameter("ideOpe")));
		map.put("numimpre", Integer.valueOf(request.getParameter("numimpre")));

		map.put("_ideOpe", Integer.valueOf(request.getParameter("_ideOpeNew")));
		map.put("_numimpre", Integer.valueOf(request.getParameter("_numimpreNew")));
		map.put("_dv", String.valueOf(Helper.dv(request.getParameter("_numimpreNew"))));

		boolean respuesta = svc_b.updateLMECero(map);

		if (respuesta) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("-1");
		}

		InitConexion_SIL.closeConexion_SIL();
	}

	//modif pto

	public void obtieneNumimprela(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		ResourceBundle properties = ResourceBundle.getBundle("lme.resources.ApplicationResources");
		String host = properties.getString("host");
		String user = properties.getString("user");
		String pass = properties.getString("pass");
		String cl_numimprela = properties.getString("cl_numimprela");

		Long afirut = Long.valueOf(request.getParameter("afiRut"));
		Integer estLicen = Integer.valueOf(request.getParameter("estLicen"));
		Integer ideOpe = Integer.valueOf(request.getParameter("ideOpe"));
		Integer numimpre = Integer.valueOf(request.getParameter("numimpre"));
		Integer numimprelaOld = Integer.valueOf(request.getParameter("numimprela"));

		String _ideOpeNew = Integer.valueOf(request.getParameter("_ideOpeNew")).toString();
		Integer _numimpreNew = Integer.valueOf(request.getParameter("_numimpreNew"));
		String _dv = String.valueOf(Helper.dv(request.getParameter("_numimpreNew")));

		svc_b = SvcFactory_SIL.getAS400Svc_SIL();
		HashMap map2R = new HashMap();
		HashMap map8600 = new HashMap();

		boolean respuesta = false;

		AS400 as400 = new AS400(host, user, pass);
		ProgramParameter[] parametro = new ProgramParameter[3];

		AS400Text ideope = new AS400Text(1);
		AS400Text numimprela = new AS400Text(7);
		AS400Text exit = new AS400Text(1);

		parametro[0] = new ProgramParameter(ideope.toBytes(_ideOpeNew), 1);
		parametro[1] = new ProgramParameter(numimprela.toBytes("0000000"), 7);
		parametro[2] = new ProgramParameter(exit.toBytes("0"), 1);

		QSYSObjectPathName programName = new QSYSObjectPathName(cl_numimprela);
		ProgramCall pgm = new ProgramCall(as400, programName.getPath(), parametro);

		try {

			if (!pgm.run()) {
				System.out.println("Error en cobol");
				AS400Message[] msgList = pgm.getMessageList();

				for (int i = 0; i < msgList.length; i++) {
					System.out.println(msgList[i].getText());
				}

			} else {
				//System.out.println("----->Exito en Cobol");                
				byte[] ReciveNumimprela = parametro[1].getOutputData();

				String numimprel = (String) numimprela.toObject(ReciveNumimprela);
				//System.out.println("----->el numimprela es: " + numimprel);

				map2R.put("afiRut", afirut);
				map2R.put("estLicen", estLicen);
				map2R.put("ideOpe", ideOpe);
				map2R.put("numimpre", numimpre);
				map2R.put("_ideOpe", _ideOpeNew);
				map2R.put("_numimpre", _numimpreNew);
				map2R.put("_dv", _dv);
				map2R.put("_numimprela", numimprel);

				map8600.put("afiRut", afirut);
				map8600.put("numimprela", numimprelaOld);
				map8600.put("_numimprela", numimprel);
				map8600.put("_numimpre", _numimpreNew);

				if (!numimprel.equals("0000000")) {
					boolean respuestaUpdate = svc_b.updateLMECeroNumImprela(map2R);

					if (respuestaUpdate) {
						System.out.println("Actualiza TABLA ILFE002R");

						boolean respuestaUpdate8600 = svc_b.updateLMECeroNumImprela8600(map8600);
						if (respuestaUpdate8600) {
							System.out.println("actualizada tabla ILF8600");
							respuesta = true;
						} else {
							//System.out.println("No actualizada tabla ILF8600");
						}

					} else {
						//System.out.println("No Actualiza TABLA ILFE002R");
					}
				}
			}
			if (respuesta) {
				response.getWriter().write("1");
			} else {
				response.getWriter().write("-1");
			}
		} catch (AS400SecurityException e) {
			response.getWriter().write(e.getMessage());
		} catch (ErrorCompletingRequestException e) {
			response.getWriter().write(e.getMessage());
		} catch (IOException e) {
			response.getWriter().write(e.getMessage());
		} catch (InterruptedException e) {
			response.getWriter().write(e.getMessage());
		} catch (ObjectDoesNotExistException e) {
			response.getWriter().write(e.getMessage());
		}
		
		InitConexion_SIL.closeConexion_SIL();
	}

	// fin modif pto

	public void DeleteLmeCero(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		//MessageResources m = getResources(request);
		svc_a = SvcFactory_LME.getAS400Svc_LME();
		//ConsumoOperadorService service = new ConsumoOperadorService(m);
		Ilfe082VO ilfe82Vo = new Ilfe082VO();
		ilfe82Vo.setAfiRut(Long.parseLong(request.getParameter("afiRut")));
		ilfe82Vo.setIdeOpe(Integer.valueOf(request.getParameter("ideOpe")));
		ilfe82Vo.setLicImpNum(Integer.valueOf(request.getParameter("licimpnum")));
		ilfe82Vo.setNumImpre(Integer.valueOf(request.getParameter("numimpre")));
		
		boolean res = svc_a.insertIlfe082(ilfe82Vo);
		
		if(res){
			response.getWriter().write("Se agregó la licencia '"+ilfe82Vo.getLicImpNum()+"' a la cola para eliminar");
		}else{
			response.getWriter().write("Error al ingresar licencia '"+ilfe82Vo.getLicImpNum()+"' a la cola para ser eliminada");
		}
		InitConexion_LME.closeConexion_LME();
	}

	private boolean empty(List l) {
		return null == l || l.size() == 0;
	}

	public void getIdeOpe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		svc_a = SvcFactory_LME.getAS400Svc_LME();
		UrlBorderVO vo = new UrlBorderVO();
		vo.setNombreServicio(SVC_NAME);
		List l = svc_a.getIdeOpe(vo);

		List list = new LinkedList();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			UrlBorderVO o = (UrlBorderVO) iter.next();
			Map h = new HashMap();
			h.put("ideOpe", o.getIdeOpe());
			h.put("nomOpe", o.getNombreServicio().trim());

			list.add(h);
		}
		String jsonString = JSONValue.toJSONString(list);
		response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(jsonString);

		InitConexion_LME.closeConexion_LME();
	}
}
