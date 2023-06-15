package cl.araucana.autoconsulta.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schema.util.GeneralException;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		String licenciasDatabase = "LIEXP";
		String command = "SELECT A.LICDESFEC, A.LICDIAS, A.LICEST, A.PAGFEC, A.OFIPGO, " + "A.PAGRESDIA, A.LICMONUP, A.LICCONIND, A.LICOBS1, A.LICOBS2, A.LICOBS3, 1 \"VISADA\", A.LICIMPNUM, "
		+ "A.LICFECING \"FECRECEP\", '' \"RESPONSA\", A.oficod, D.CMDA " + "FROM " + licenciasDatabase + ".ILF1000 A , cmdta.cm01f1 D " 
		+ "WHERE D.CMBA = A.oficod AND A.AFIRUT = ? AND A.LICFECING > ? AND A.LICIMPNUM=? " 
		+ "UNION "
		+ "SELECT C.LICDESFEC, C.DIASMED \"LICDIAS\", C.ESTADO \"LICEST\", "
		// TODO poner VISADA = 2. Cambiada a 1 para test.
		+ "0, -1, 0, 0, 0, 0, 0, 0, 2 \"VISADA\", C.LICIMPNUM, " + "C.FECRECEP, C.RESPONSA, C.oficod, D.CMDA " + "FROM " + licenciasDatabase + ".ILF8600 C, cmdta.cm01f1 D " 
		+ "WHERE C.AFIRUT= ? AND C.FECRECEP > ? "
		+ "AND C.ESTADO <> 4 AND C.LICIMPNUM=? AND D.CMBA = C.oficod" + " ORDER BY 1 DESC";
		
		System.out.println(command);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServicesAutoconsultaDelegate delegate =  new ServicesAutoconsultaDelegate();
			UsuarioVO usuario = new UsuarioVO();
			long rut = 12915160;
			usuario.setRutAfiliado(rut);
			usuario.setRut(rut);
			
			usuario.setRutEmpresa(70016160);
			usuario.setIpConexion("172.16.137.24");
			usuario.setRutusuarioAutenticado(rut);
			
			Collection licencias = delegate.getConsultaLicenciasMedicas(usuario);
			List lic = (List) licencias;
			
			for(int i=0;i<lic.size();i++){
				LicenciaMedicaVO licencia = (LicenciaMedicaVO) lic.get(i);
				
				System.out.println("SERVLET FEC RECEPCION: " + licencia.getFechaRecepcion());
//				
				System.out.println("SERVLET FEC ENVIO COMPIN: " +licencia.getFechaEnvioCompin());
				System.out.println("SERVLET FEC RESOLUCION: " +licencia.getFechaRecepcion());
				//System.out.println("SERVLET FEC RECEPCION: " +licencia.get);
			}
			
			
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
