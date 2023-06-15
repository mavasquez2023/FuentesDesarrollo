package cl.araucana.ctasfam.presentation.struts.actions;

 
import java.io.File;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.ArchivosTO;
import cl.araucana.ctasfam.business.to.CargaArchivoTO;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Utils;

public class PrepareddeclaracionAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		String mensaje=null;
		String nombre=null;
		String nombresp=null;
		Encargado encargado=new Encargado();
		Properties Param = new Properties();
		Utils util=new Utils();
		List lista=new ArrayList();
		Vector v=new Vector();
		//CargaArchivoTO cargarForm=(CargaArchivoTO)form;
		//String rutEmpCarga=cargarForm.getRutEmpresa();
		
		FormFile archivo=(FormFile)request.getSession().getAttribute("file");
		//System.out.println("archivo" + archivo.getFileName());
		//System.out.println(request.getSession().getAttribute("rutestado"));
		
		String rutEmpCarga= (String)request.getSession().getAttribute("rutestado");
		String rol= (String)request.getSession().getAttribute("rol");
		
		File file= new File(archivo.getFileName());
		String ext=util.extencion(file);
		ArchivosTO oarchivos=new ArchivosTO();
		Empresa empresamatch=null;
		try{
			String rutint= (rutEmpCarga.replaceAll("\\.", "").split("-"))[0];
			if(ext.equalsIgnoreCase("zip")){

				lista=(List)request.getSession().getAttribute("listafiles");	 
				for(int i=0;i<lista.size();i++){
					oarchivos=(ArchivosTO)lista.get(i);
					String nombrese=oarchivos.getNombre().substring(0, oarchivos.getNombre().indexOf("."));
					System.out.println(nombrese);
					v.add(nombrese);
				}
			}
			else{
				//nombre=archivo.getFileName();
				//nombresp=nombre.substring(0,nombre.indexOf("."));
				v.add(rutint);
			}

			//System.out.println("archivo" + archivo.getFileName());
			//System.out.println("v " + v.size());

				encargado=(Encargado)request.getSession().getAttribute("edocs_encargado");

				if(!rol.equals("Ejecutivo")){
					for (Iterator iter = encargado.getEmpresas().iterator(); iter.hasNext();) {
						Empresa empresa = (Empresa) iter.next();
						String rutEmpresa = "" + empresa.getRut();

						for(int i=0;i<v.size();i++){
							int temp=Integer.parseInt(v.get(i).toString());
							//System.out.println(rutEmpresa);
							//System.out.println(temp);

							if(Integer.parseInt(rutEmpresa)==temp){
								empresa.setFlag(1);
								empresamatch= empresa;
							}


						}

					}
				}else{
					Empresa empresa= new Empresa();
					empresa.setFlag(1);
					empresa.setRut(rutEmpCarga);
					empresa.setName("");
					empresamatch= empresa;
				}

		}catch(Exception ex)
		{
			ex.printStackTrace();
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
		}

		if(!errors.isEmpty()){

			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
		}else
		{
			request.getSession().setAttribute("empresa", empresamatch);
			//request.getSession().setAttribute("edocs_encargado", encargado);
			//request.getSession().setAttribute("proceso", acepta);
			forward=mapping.findForward("showForm");
		}

		return forward;
	}




}
