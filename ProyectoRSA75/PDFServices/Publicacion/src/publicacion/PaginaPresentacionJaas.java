package publicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import publicacion.dao.CPDAOFactory;
import publicacion.dao.EntidadesCPDAO;
import publicacion.dao.HibernateDao;

import utilPub.UtilPub;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermConvenio;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermEmpresa;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermGrupoConvenio;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.mgr.PersonaMgr;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.recursos.Today;

public class PaginaPresentacionJaas extends HttpServlet {
    
	private static final long serialVersionUID = 937017357695172836L;
	private Iterator it;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException {
		
		//declaro listas
		List afps = new ArrayList();
		List apvs = new ArrayList();
		List mutuales = new ArrayList();
		List isapres = new ArrayList();
		List cajas = new ArrayList();
		
		CPDAOFactory cpdao= null;
		cpdao= new CPDAOFactory();
		EntidadesCPDAO dao = new EntidadesCPDAO(cpdao.getConnection());
		
		
		try {
			afps = (List) dao.selectEntidadesAFP();
			isapres = (List) dao.selectEntidadesISAPRE();
			apvs = (List) dao.selectEntidadesAPV();
			mutuales = (List) dao.selectEntidadesMUTUAL();
			cajas = (List) dao.selectEntidadesCCAF();
			
		} catch (Exception e) {
			 afps = new ArrayList();
			 apvs = new ArrayList();
			 mutuales = new ArrayList();
			 isapres = new ArrayList();
			 cajas = new ArrayList();
		}
		
		
		Session s = HibernateUtil.getSession();
		PersonaMgr personaMgr = new PersonaMgr(s);

		Principal principal = request.getUserPrincipal();
		String rut = principal.getName().substring(0, principal.getName().length()-2);
		int esAdm = 0;
		try {
			esAdm = dao.esAdmin(rut);
		} catch (Exception e) {
			esAdm = 0;
		}
		
		PersonaVO p = null;
		try {
			p = personaMgr.getPersona(Long.valueOf(rut).intValue());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (DaoException e) {
			e.printStackTrace();			
			throw new IOException(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		
		String miMenu = "";
		String servletPath = this.getServletContext().getRealPath("/index.html");
		HibernateDao hibernate = new HibernateDao();
		HttpSession session = request.getSession();

        try {
			String idDomino = ""; 
			String tipoUsuario ="";
			String dato_completo = "";
            String num_holding = "";
            String nom_usuario = p.getNombres().trim() + " " + p.getApellidoPaterno().trim() + " " + p.getApellidoMaterno().trim();
            String ini_cotiza = "";
            String convenio_completo = "";
            String sucursal_completa = "";

            String rut_empresa = "";
            String razon_social = "";
            String todo_los_rut="";
            String todo_los_conv="";
            String toda_las_sucu="";

            String rut_empresa_final = "";
            String razon_social_final = "";
            String convenio_final = "";
            String sucursal_final = "";

            String cajaMutual = hibernate.setCajaMutual();
            String cajaCajas = hibernate.setCajaCajas();
            String cajaAFP = "";
            String cajaAPV = "";
            String cajaIsapre = hibernate.setCajaIsapre();
            
            
            // TIPO USUARIO EMPRESA -- > menú largo
            
            if (personaMgr.isLectorEmpresaHabilitado(p.getIdPersona().intValue())) {
            	HashMap permisosRolLectorEmpresa = personaMgr.getPermisosRolLectorEmpresa(p.getIdPersona().intValue());
            	tipoUsuario = "empresa";
            	num_holding  = hibernate.cargaParametrosHoldings(permisosRolLectorEmpresa);
            	
            	rut_empresa  = hibernate.cargaComboRut(permisosRolLectorEmpresa);
                razon_social = hibernate.cargaComboRazonSocial(permisosRolLectorEmpresa);
                todo_los_rut = hibernate.construyeStringConTodosLosRuts(permisosRolLectorEmpresa);
                String convenio = "";
                String sucursal = "";
                convenio_completo = convenio_completo + ",";
                sucursal_completa = sucursal_completa + ";";

                convenio += hibernate.cargaComboConvenios(permisosRolLectorEmpresa);
                todo_los_conv += hibernate.construyeStringConTodosLosConvenios(permisosRolLectorEmpresa);

                sucursal += hibernate.cargaComboSucursales(permisosRolLectorEmpresa);
                toda_las_sucu += hibernate.construyeStringConTodosLasSucursales(permisosRolLectorEmpresa);

                // agrega las opciones todos a las combobox
                rut_empresa_final += hibernate.construyeStringDeRutEmpresaFinal(todo_los_rut, rut_empresa);
                razon_social_final += hibernate.construyeStringDeRazonSocialFinal(todo_los_rut, razon_social);
                convenio_final += hibernate.construyeStringConvenioFinal(todo_los_conv, convenio);
                sucursal_final += hibernate.construyeStringSucursalFinal(toda_las_sucu, sucursal);
                // sucursal_final += "<input type='text' id='Sucursal' size='6' maxlength='6' style='font-family:Arial;font-size:8pt;' ;>";

                miMenu += hibernate.componeMenuExtenso(miMenu, idDomino);      
                
                session.setAttribute("rutEmpresaFinal", rut_empresa_final);
                session.setAttribute("razonSocialFinal", razon_social_final);
                session.setAttribute("convenioFinal", convenio_final);
                session.setAttribute("sucursalFinal", sucursal_final);
                session.setAttribute("miMenu", miMenu);
                
            } else if (personaMgr.isLectorEntidadHabilitado(p.getIdPersona().intValue())) {

				LectorEntidadVO  le;
				String optionsMutual = "";
				String optionsCajas  = "";
				String optionsAFP    = "";
				String optionsAPV    = "";
				String optionsIsapre = "";

				tipoUsuario = "entidad";
				
				try {
					le = personaMgr.getLectorEntidad(p.getIdPersona().intValue());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new IOException(e.getMessage());
				} catch (DaoException e) {
					e.printStackTrace();			
					throw new IOException(e.getMessage());
				}

				if (le != null) {
					optionsMutual = hibernate.cargaComboEntidad("MUTUAL", le);
					optionsCajas  = hibernate.cargaComboEntidad("CCAF",   le);
					optionsAFP    = hibernate.cargaComboEntidad("AFP",    le);
					optionsAPV    = hibernate.cargaComboEntidad("APV",    le);
					optionsIsapre = hibernate.cargaComboEntidad("ISAPRE", le);
					
					mutuales = hibernate.cargaComboEntidad2("MUTUAL", le);
					cajas  = hibernate.cargaComboEntidad2("CCAF",   le);
					afps    = hibernate.cargaComboEntidad2("AFP",    le);
					apvs    = hibernate.cargaComboEntidad2("APV",    le);
					isapres = hibernate.cargaComboEntidad2("ISAPRE", le);

					if (!optionsMutual.equals(""))
	                	dato_completo += dato_completo + "MUTUAL,";
	                if (!optionsCajas.equals(""))
	                	dato_completo += dato_completo + "CAJA,";
					if (!optionsAFP.equals(""))
						dato_completo += dato_completo + "AFP,";
					if (!optionsAPV.equals(""))
						dato_completo += dato_completo + "APV,";
					if (!optionsIsapre.equals(""))
						dato_completo += dato_completo + "ISAPRE,";
				}

				cajaMutual  = hibernate.construyeCajaMutual(optionsMutual);
                cajaCajas   = hibernate.construyeCajaCajas(optionsCajas);
                cajaAFP     = hibernate.construyeCajaAFP(optionsAFP);
        		cajaAPV     = hibernate.construyeCajaAPV(optionsAPV);
                cajaIsapre  = hibernate.construyeCajaIsapre(optionsIsapre);
                
                dato_completo = dato_completo + ",";
            	
				miMenu += hibernate.componeMenuChico (miMenu, dato_completo, idDomino); 

                convenio_completo  = "";
                sucursal_completa  = "";
                rut_empresa_final  = hibernate.retornaRutEmpresaFinal();
                razon_social_final = hibernate.retornaRazonSocialFinal();
                convenio_final     = hibernate.retornaConvenioFinal();
                sucursal_final     = hibernate.retornaSucursalFinal();
                
                session.setAttribute("rutEmpresaFinal", rut_empresa_final);
                session.setAttribute("razonSocialFinal", razon_social_final);
                session.setAttribute("convenioFinal", convenio_final);
                session.setAttribute("sucursalFinal", sucursal_final);
                session.setAttribute("miMenu", miMenu);
                
            } else {
            	
            	// Existe el rut pero está totalmente inhabilitado (ni empresa ni entidad)
				String pagina_html="PaginaPresentacion.htm";
				String html = "";
				miMenu = "<font face='Verdana, Arial, Helvetica, sans-serif' style='font-size:10pt;' color='#0066CC'><strong>No existe definición de convenios para el usuario...</strong></font>";
				html = UtilPub.getHTML(pagina_html, servletPath);
				html = html.replaceAll("[*]{2}mensaje[*]{2}", miMenu);
				html = html.replaceAll("[*]{2}script[*]{2}", "");
				response.setContentType("text/html");
				out.println(html);
				return;
            }
            
            /*String html = hibernate.componeHtml (miMenu, rut_empresa_final, razon_social_final, convenio_final, sucursal_final,
            		cajaMutual, cajaCajas, cajaIsapre, cajaAFP, cajaAPV,
					num_holding, nom_usuario,
					ini_cotiza,
					servletPath);
            response.setContentType("text/html");            
            out.println(html);
            
            session.setAttribute("html", html);*/
            
            Collection empresas = new ArrayList();
            EmpresasAutorizadasTO empresasTO;
            HashMap permisosRolLectorEmpresa = personaMgr.getPermisosRolLectorEmpresa(p.getIdPersona().intValue());
            String allRuts = "";
            boolean hayConvenios = false;
            Hashtable table = new Hashtable();
           
            for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
        		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();
        		
        		 
                
    	    	// Get Result set metadata
        		
    			Iterator i = pg.getPermEmpresas().values().iterator();
    			while (i.hasNext()) {
    				empresasTO = new EmpresasAutorizadasTO();
    				PermEmpresa permEmpresa = (PermEmpresa) i.next();
    				empresasTO.setRazonSocial(permEmpresa.getEmpresa().getRazonSocial());
    				empresasTO.setRut(String.valueOf(permEmpresa.getEmpresa().getIdEmpresa()));
    				empresasTO.setRutint(permEmpresa.getEmpresa().getIdEmpresa());
    				allRuts += " " + permEmpresa.getEmpresa().getIdEmpresa();
    				empresas.add(empresasTO);
    				
    				
    				Iterator y = permEmpresa.getPermConvenios().values().iterator();
    				while (y.hasNext()) {
    					hayConvenios = true;
    					PermConvenio prmc = (PermConvenio) y.next();
    					if (!table.containsKey(new Integer (prmc.getConvenio().getIdConvenio()))) {
    						table.put(new Integer (prmc.getConvenio().getIdConvenio()), new Integer (prmc.getConvenio().getIdConvenio()));
    					}
    				}
    			}
        	}
            List listconvenios = new ArrayList();
            String todosLosConvenios = "";
            
            Iterator it = table.values().iterator();
    		while (it.hasNext()) {
    			Integer x = (Integer) it.next();
    			todosLosConvenios += " " + x.intValue();	
    			String convenios =  x.toString();
    			listconvenios.add(convenios);
    		}
    		
    		/* clillo 24-7-14 se deshabilitan convenios en desuso
            if (hayConvenios) {
                todosLosConvenios += " 800 900";
                listconvenios.add("800");
            	listconvenios.add("900");
     
    		}	*/	
            
            String todasLasSucursales = hibernate.construyeStringConTodosLasSucursales(permisosRolLectorEmpresa);
            List sucursal = hibernate.cargaComboSucursales2(permisosRolLectorEmpresa);
            
            if (null == ini_cotiza || ini_cotiza.equals("")){
            	ini_cotiza = "200001";
            }  
            /* clillo 8-10-14 se ordenan Listas de Rut Empresa y Razón Social para que combobox salgan ordenados */
            List razonsocial= new ArrayList(empresas);
            Collections.sort(razonsocial, new CustomComparator());
            
            Collections.sort((List)empresas, new CustomComparatorRut());
            
            session.setAttribute("TipoUsuario", tipoUsuario);
            session.setAttribute("allRuts", allRuts);
            session.setAttribute("listEmpresas", empresas);
            session.setAttribute("listRazonSocial", razonsocial);
            
            session.setAttribute("listConvenios", listconvenios);
			session.setAttribute("allConvenios", todosLosConvenios);
			session.setAttribute("listSucursales", sucursal);
			session.setAttribute("allSucursales", todasLasSucursales);
			
			session.setAttribute("Entidades", "empresa");
			session.setAttribute("holding", num_holding);
			session.setAttribute("usuario", nom_usuario);
			session.setAttribute("inicotiza", ini_cotiza);				
			String periodo= getPeriodo(Today.getAAAAMMDD());
			session.setAttribute("periodo", periodo);
			session.setAttribute("periodoant", getPeriodoAnterior(periodo));
			
			session.setAttribute("esAdm", String.valueOf(esAdm));			
			

			session.setAttribute("AFP", afps);
			session.setAttribute("ISAPRE", isapres);
			session.setAttribute("APV", apvs);
			session.setAttribute("MUTUAL", mutuales);
			session.setAttribute("CCAF", cajas);
			
			request.getRequestDispatcher("menu.jsp").forward(request, response);
			
            
        } catch (Exception e) {
			e.printStackTrace();
        }
	}
	
	public String getPeriodo(String fecha){
		String periodo= fecha.substring(0, 6);
		int dia= Integer.parseInt(fecha.substring(6));
		if(dia>20){
			return periodo;
		}else{
			return getPeriodoAnterior(periodo);
		}
		
	}
	
	public String getPeriodoAnterior(String periodo){
		int year= Integer.parseInt(periodo.substring(0, 4));
		int month= Integer.parseInt(periodo.substring(4));
		int previousMonth= month-1;
		if (previousMonth== 0){
			previousMonth=12;
			year= year-1;
		}
		if(previousMonth<10){
			return year + "0" + previousMonth;
		}else{
			return year + "" + previousMonth;
		}
		
	}
}