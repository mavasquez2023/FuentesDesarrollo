package publicacion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.recursos.Today;

import publicacion.dao.AutorizacionesDominoDAO;
import publicacion.dao.CPDAOFactory;
import publicacion.dao.EntidadesCPDAO;
import publicacion.dao.PubDAOFactory;
import publicacion.dao.ServDAOFactory;

public class PaginaPresentacion extends HttpServlet {
    
	private static final long serialVersionUID = 937048357695172836L;
	
	public void init(){
		CPDAOFactory cpdao= null;
		try {
			cpdao= new CPDAOFactory();
			System.out.println(">>Entrando a EntidadesCPESingleton");
			EntidadesCPDAO entdao= new EntidadesCPDAO(cpdao.getConnection());
			List afps= (List)entdao.selectEntidadesAFP();
			List isapres=(List)entdao.selectEntidadesISAPRE();
			List apvs=(List)entdao.selectEntidadesAPV();
			List mutuales=(List)entdao.selectEntidadesMUTUAL();
			List cajas=(List)entdao.selectEntidadesCCAF();
			ServletContext sctx= this.getServletContext();
			sctx.setAttribute("AFP", afps);
			sctx.setAttribute("ISAPRE", isapres);
			sctx.setAttribute("APV", apvs);
			sctx.setAttribute("MUTUAL", mutuales);
			sctx.setAttribute("CCAF", cajas);
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		finally{
			if(cpdao!= null){
				cpdao.closeConnectionDAO();
			}
		}
		
		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException {
		
		ServDAOFactory servdao= null;
		//Se define listas para guardar los nombres de entidades
		List afps= new ArrayList();
		List isapres= new ArrayList();
		List apvs= new ArrayList();
		List mutuales= new ArrayList();
		List cajas= new ArrayList();
        try {
        	System.out.println("En  PaginaPresentacion sin jaas");
			HttpSession sesion= request.getSession();
			//Se instancia DAO de Publicación
			servdao= new ServDAOFactory();
			
			//Se lee id de usuario desde request
			String docid = request.getParameter("nombre"); //ESTE DATO VIENE DE COTIZACION.JSP
			
			//Se busca en DAO la información asociada al docid 
			AutorizacionesDominoDAO autorizacion= new AutorizacionesDominoDAO(servdao.getConnection());
			//Se llena objeto AtributosUsuarioTO con toda la información asociada al usuario conectado
			AtributosUsuarioTO atributos= (AtributosUsuarioTO)autorizacion.selectEmpresas(docid);
			// fin DAO
			
			//Si no encuentra resultados o no hay convenios autorizados se retorna
			if(atributos==null || atributos.getConvenios().isEmpty()){
				sesion.setAttribute("TipoUsuario", "");
				sesion.setAttribute("Entidades", "");
			}else{

				//Se cargan los atributos en sesion
				sesion.setAttribute("listEmpresas", atributos.getEmpresas());
				sesion.setAttribute("listRazonSocial", atributos.getSortEmpresasByRazon());
				sesion.setAttribute("allRuts", atributos.getListaEmpresas());
				sesion.setAttribute("listConvenios", atributos.getConvenios());
				sesion.setAttribute("allConvenios", atributos.getListaConvenios());
				sesion.setAttribute("listSucursales", atributos.getSucursales());
				sesion.setAttribute("allSucursales", atributos.getListaSucursales());
				sesion.setAttribute("TipoUsuario", atributos.getTipoUsuario());
				sesion.setAttribute("Entidades", atributos.getTipoEntidad());
				sesion.setAttribute("holding", atributos.getHolding());
				sesion.setAttribute("usuario", atributos.getUsuario());
				sesion.setAttribute("inicotiza", atributos.getIniCotiza());	
				String periodo= getPeriodo(Today.getAAAAMMDD());
				sesion.setAttribute("periodo", periodo);
				sesion.setAttribute("periodoant", getPeriodoAnterior(periodo));
				sesion.setAttribute("esAdm", "0");
				
				//Si usuario es una entidad, se busca el nombre de la entidad para asignar en el combox de la entidad asociada
				//en caso de una empresa, se rescata todas las entidades desde Singleton que extrae entidades de CPE
				if (atributos.getTipoUsuario().equals("entidad")) {
					String entidad= atributos.getTipoEntidad();
					Collection nomentidad=atributos.getEmpresas();
					String nombreEntidad="";
					for (Iterator iter = nomentidad.iterator(); iter.hasNext();) {
						EmpresasAutorizadasTO element = (EmpresasAutorizadasTO) iter.next();
						nombreEntidad= element.getRazonSocial();
					}
					if (entidad.equals("MUTUAL")){
						mutuales.add(nombreEntidad);
					}
					if (entidad.equals("CAJA")){
						cajas.add(nombreEntidad);
					}
					if (entidad.equals("ISAPRE")){
						EntidadTO isaent= new EntidadTO();
						String nombre= nombreEntidad;
						String value=nombreEntidad;
						if(value.length()>40){
							value= value.substring(0, 40);
						}
						isaent.setNombre(nombre);
						isaent.setValue(value);
						
						isapres.add(isaent);
					}
					if (entidad.equals("AFP")){
						afps.add(nombreEntidad);
					}
					if (entidad.equals("APV")){
						EntidadTO apvent= new EntidadTO();
						String nombre= nombreEntidad;
						String value=nombreEntidad;
						if(value.length()>40){
							value= value.substring(0, 40);
						}
						apvent.setNombre(nombre);
						apvent.setValue(value);
						apvs.add(apvent);
					}
					//se guardan las entidades en sesion.
					sesion.setAttribute("AFP", afps);
					sesion.setAttribute("ISAPRE", isapres);
					sesion.setAttribute("APV", apvs);
					sesion.setAttribute("MUTUAL", mutuales);
					sesion.setAttribute("CCAF", cajas);
				}
				
			}
			request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
        }
        finally{
        	if(servdao!=null){
        		servdao.closeConnectionDAO();
        	}
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