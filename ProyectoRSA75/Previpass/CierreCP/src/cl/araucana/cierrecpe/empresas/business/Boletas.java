

/*
 * @(#) GenerarPlanillas.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.NamingException;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.empresas.dao.BoletasCPDAO;
import cl.araucana.cierrecpe.empresas.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.cierrecpe.empresas.dao.TesoreriaDAO;
import cl.araucana.cierrecpe.empresas.to.BancoTO;
import cl.araucana.cierrecpe.empresas.to.BoletaTO;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;

/*
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 21-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class Boletas implements Constants{
	private CPDAO cpDAO=null;
	private TesoDAO tesoDAO=null;
	private static Logger logger = LogManager.getLogger();
	protected Properties properties;
	private String ipAS400, usuarioAS400, passwordAS400, programa;
	List no_cursados= null;
	
	public Boletas() throws SQLException, NamingException{
		cpDAO= new CPDAO();
		//cpDAO.setAutoCommit(false);
		loadProperties("/etc/folio.properties");
		this.ipAS400= properties.getProperty("ip");
		this.usuarioAS400= properties.getProperty("usuario");
		this.passwordAS400= properties.getProperty("password");
		this.programa= properties.getProperty("programaBoleta");
	}
	
	public Collection verBoletas(int periodo) {
		try {
			logger.finer("Se solicita Ver Boletas");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return (List)boletaDAO.selectBoletas(periodo);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getBoleta(int idBoleta) {
		try {
			logger.finer("Se solicita resctar Boleta");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.selectBoleta(idBoleta);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public int guardarBoleta(BoletaTO boleta) {
		try {
			logger.finer("Se solicita Guardar Boleta");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.insert(boleta);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	public int estadoBoleta(int idBoleta, String estado) {
		try {
			logger.finer("Se solicita cambiar estado a Boleta");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.updateEstado(idBoleta, estado);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	public Collection verBancos() {
		try {
			logger.finer("Se solicita Ver Bancos");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return (List)boletaDAO.selectBancos();
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public void eliminarBanco(int idBanco) {
		try {
			logger.finer("Se solicita Eliminar Banco");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			boletaDAO.deleteBanco(idBanco);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
		}
	}
	public Object getBanco(int idBanco) {
		try {
			logger.finer("Se solicita resctar Boleta");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.selectBanco(idBanco);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public int guardarBanco(BancoTO banco) {
		try {
			logger.finer("Se solicita Guardar Banco");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.insertBanco(banco);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			if(e.getMessage().indexOf("duplicada")>-1){
				return 0;
			}
			return -1;
			//e.printStackTrace();
			
		}
	}
	public int updateBanco(BancoTO banco) {
		try {
			logger.finer("Se solicita Update Banco");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return boletaDAO.updateBanco(banco);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	public void eliminarBoleta(Integer idBoleta) {
		try {
			logger.finer("Se solicita Eliminar Boleta");
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			boletaDAO.delete(idBoleta);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
		}
	}
	public Collection buscarComprobantes(int rutEmpresa) {
		try {
			logger.finer("Se solicita buscar Comprobantes empresa:" + rutEmpresa);
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			return (List)boletaDAO.selectComprobantes(rutEmpresa);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public int cursarComprobantesBoleta(BoletaTO boletaTO) {
		int count=0;
		
		try {
			cpDAO.setAutoCommit(false);
			tesoDAO= new TesoDAO();
			tesoDAO.setAutoCommit(false);
			logger.finer("Cursar Comprobantes Boleta");
			no_cursados= new ArrayList();
			TesoreriaDAO tesoreriaDAO= new TesoreriaDAO(tesoDAO.getConnection());
			BoletasCPDAO boletaDAO= new BoletasCPDAO(cpDAO.getConnection());
			int totalComprobantes= boletaTO.getFolios().length;
			for (int i = 0; i < totalComprobantes; i++) {
				String folio= boletaTO.getFolios()[i];
				int resultado= tesoreriaDAO.update(Integer.parseInt(folio));
				if (resultado>0){
					resultado= boletaDAO.updateComprobanteCP(Integer.parseInt(folio));
					if(resultado>0){
						count++;
					}else{
						System.out.println("Comprobante NO Cursado Previpass, folio=" + folio);
						logger.severe("Comprobante NO Cursado Previpass, folio=" + folio);
						no_cursados.add("Error al cursar comprobante en Previpass, folio: " + folio + "<br>");
					}
				}else{
					System.out.println("Comprobante NO Cursado Tesoreria, folio=" + folio);
					logger.severe("Comprobante NO Cursado Tesoreria, folio=" + folio);
					no_cursados.add("Error al cursar comprobante en Tesorería, folio: " + folio + "<br>");
				}
			}
			if(totalComprobantes==count){
				cpDAO.commit();
				tesoDAO.commit();
			}else{
				cpDAO.rollback();
				tesoDAO.rollback();
				count=0;
				boletaTO.setNo_cursados(no_cursados);
			}

		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			try {
				cpDAO.rollback();
				tesoDAO.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}
		finally{
			try {
				cpDAO.setAutoCommit(true);
				tesoDAO.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public String guardarLibroBanco(BoletaTO boleta) {
		String resultado="";
		try {
			logger.finer("Se solicita Guardar Boleta");
			TesoreriaDAO tesoDAO= new TesoreriaDAO(cpDAO.getConnection(), this.programa, this.ipAS400, this.usuarioAS400, this.passwordAS400 );
			if(!tesoDAO.existBoletaTesoreria(boleta)){
				resultado= tesoDAO.insertBoletaTesoreria(boleta);
			}
			return resultado;
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public void close(){
		cpDAO.closeConnectionDAO();
		if(tesoDAO!=null){
			tesoDAO.closeConnectionDAO();
		}
	}
	private void loadProperties(String fileproperties){
		PropertiesLoader propertiesloader = new PropertiesLoader();
		try
		{
			properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.GenerarPlanillas.class);
		}
		catch(Exception eproperties)
		{
			logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
			eproperties.printStackTrace();
		}
	}

	public List getNo_cursados() {
		return no_cursados;
	}

	public void setNo_cursados(List no_cursados) {
		this.no_cursados = no_cursados;
	}
	 
}

