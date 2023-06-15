package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.AfiliadosErrorTO;
import cl.araucana.ctasfam.business.to.HoldingTO;
import cl.araucana.ctasfam.business.to.VerificadorTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Utils;

public class HoldingAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		
		ActionForward forward=new ActionForward();
		ActionErrors errors=new ActionErrors();
		List listcomun=new ArrayList();
		List listaerrorColum=new ArrayList();
		String mensaje=null;
		FormFile archivo=null;
		String nombrearchivo=null;
		Utils util=new Utils();
		AraucanaJdbcDao dao=new AraucanaJdbcDao();
		String extension=null; 
		List listaerrors=new ArrayList();
		List error=new ArrayList();
		List listaerror=new ArrayList();
		AfiliadosErrorTO oerror=new AfiliadosErrorTO();
		String descripcion=null;
		String tipo=null;
		VerificadorTO ocase=new VerificadorTO();
		Encargado enc1 =new Encargado();
		enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");

		HoldingTO frm=(HoldingTO)form;
		archivo=frm.getArchivoholding();
		nombrearchivo=archivo.getFileName();
		tipo=frm.getTipo();

		AceptaPropuestaForm acepta=new AceptaPropuestaForm();

		Properties Carpetas = new Properties();
		Carpetas.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String rutaArchivos=Carpetas.getProperty("RESPALDO");
		String periodo=Carpetas.getProperty("PERIODO");
		String proceso=Carpetas.getProperty("PROCESO");




		System.out.println(">>tipo " + tipo);

		Date hoy=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
		SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss", new Locale("cl"));
		String hora=sdf2.format(hoy);
		String fecha=sdf.format(hoy);
		String ext=fecha+hora;
		File file= new File(archivo.getFileName());
		ocase.setNombre(archivo.getFileName());

		try{



			if(archivo.getFileSize()==0)
			{
				mensaje=dao.getValores("003","ERROR")[0];
				errors.add("name", new ActionMessage("id"));
			}

			acepta.setProceso(proceso);
			extension=util.extencion(file);

			String carpetas=util.creaCarpetas("  ", rutaArchivos, ext,extension);
			String temp[]=carpetas.split("-");
			String carpetaRes=temp[0];


			if(tipo.equals("empresa"))
			{
				listcomun=util.procesaArchivos(extension, archivo,rutaArchivos,ext,tipo,request); 
				
				for (int i = 0; i < listcomun.size(); i++) {
					
					if ((i%2) == 0) {
						listaerror.add(listcomun.get(i));	
					}
					
				}
				
		       for (int i = 0; i < listcomun.size(); i++) {
					
					if ((i%2) != 0) {
						listaerrorColum.add(listcomun.get(i));	
					}
					
				 }
				
				if(listaerror==null){
					request.setAttribute("mensaje", "Error al procesar el archivo. Verifique que el formato corresponda al tipo indicado");

					return mapping.findForward("onError");
				}

				for(int i=0;i<listaerror.size()-1;i++){
					listaerrors.add(listaerror.get(i) + "/" + archivo.getFileName() + "/" + "9");
				}
			}
			else
			{
				request.getSession().setAttribute("edocs_encargado", enc1);
				
				listcomun=util.procesaArchivosHolding(extension, archivo, rutaArchivos,ext, request); 
				
                 for (int i = 0; i < listcomun.size(); i++) {
					
					if ((i%2) == 0) {
						listaerror.add(listcomun.get(i));	
					}
					
				 }
				
		       for (int i = 0; i < listcomun.size(); i++) {
					
					if ((i%2) != 0) {
						listaerrorColum.add(listcomun.get(i));	
					}
					
				 }

				if(listaerror==null){
					request.setAttribute("mensaje", "Error al procesar el archivo. Verifique que el formato corresponda al tipo indicado");

					return mapping.findForward("onError");
				}

				for(int i=0;i<listaerror.size()-1;i++)
				{
					listaerrors.add(listaerror.get(i) + "/" + archivo.getFileName() + "/" + "9");
				}

			}

			for(int j=0;j<listaerrors.size();j++)
			{
				System.out.println(listaerrors.get(j).toString());
			}

			String d[]=new String[11];

			d[0]=dao.getValores("004","ERROR")[0];
			d[1]=dao.getValores("005","ERROR")[0];
			d[2]=dao.getValores("006","ERROR")[0];
			d[3]=dao.getValores("007","ERROR")[0];
			d[4]=dao.getValores("008","ERROR")[0];
			d[5]=dao.getValores("009","ERROR")[0];
			d[6]=dao.getValores("010","ERROR")[0];
			d[7]=dao.getValores("011","ERROR")[0];
			d[8]=dao.getValores("012","ERROR")[0];
			d[9]=dao.getValores("013","ERROR")[0];
			d[10]=dao.getValores("014","ERROR")[0];

			for(int i=0;i<listaerrors.size();i++){
				System.out.println(listaerrors.get(i).toString());
			}


			if(listaerrors.size()>0){



				for(int i=0;i<listaerrors.size();i++){




					if(!listaerrors.get(i).toString().split("/")[0].equalsIgnoreCase("error")){

						String temp1[]=listaerrors.get(i).toString().split(";");


						String param[]=temp1[1].split("/");

						// for(int k=0;k<param.length;k++)
						//	arc[k]=param[4];
						int errores=0;
						String temp2[]=temp1[0].split("/");
						int largo=temp2.length;



						for(int j=0;j<largo;j++){
							if(temp2[j].length()==0||temp2[j].trim().equals(""))
							{
								errores=0;
							}
							else{

								errores=Integer.parseInt(temp2[j]);
							}
							switch(errores){
							case 4: descripcion=d[0];break;
							case 5: descripcion=d[1];break;
							case 6: descripcion=d[2];break;
							case 7: descripcion=d[3];break;
							case 8: descripcion=d[4];break;
							case 9: descripcion=d[5];break;
							case 10:descripcion=d[6];break;
							case 11:descripcion=d[7];break;
							case 12:descripcion=d[8];break;
							case 13:descripcion=d[9];break;
							case 14:descripcion=d[10];break;
							}


							if(largo>1&&param[4].equals("0")&&j>0)
							{ param[4]="1";
							}
							if(i>0&&!extension.equalsIgnoreCase("zip"))
							{
								param[4]="1";
							}


							oerror=new AfiliadosErrorTO();
							oerror.setDescripcionerror(descripcion);
							if(errores==13)
							{
								oerror.setRuttrabajador("");
								oerror.setNumerolinea("0");
								oerror.setNumeroColumna("0");

							}else
							{
								String columnas = listaerrorColum.get(i).toString().replace("[", "");	
								columnas = columnas.replace("]", "");
								oerror.setRuttrabajador(Integer.parseInt(param[0])+"-" + param[1]);
								oerror.setNumerolinea(param[2]);
								oerror.setNumeroColumna(columnas.split(",")[j]);
							}

							oerror.setNombrearchivo(param[3]);
							oerror.setPar(param[4]); 
							oerror.setCodigoerror(String.valueOf(errores));
							error.add(oerror);


						}
					}
				}
			}


		}catch(Exception ex)
		{
			ex.printStackTrace();
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
		}

		if(!errors.isEmpty()){

			request.setAttribute("mensaje", mensaje);

			forward = mapping.findForward("onError");
		}
		else
		{
			request.getSession().setAttribute("lista",error);
			request.setAttribute("case",ocase);
			request.setAttribute("proceso",acepta);
			forward=mapping.findForward("informeholding");
		}

		return forward;
	}

}
