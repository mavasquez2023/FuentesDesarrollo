/**
 * 
 */
package cl.araucana.mapeo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cl.recursos.ConectaDB2;

/**
 * @author usist24
 *
 */
public class CertificadoDAO {
	private static ConectaDB2 db2=null;
	private String sistema="jdbc:as400://146.83.1.96/INTRANET";
	private String usuario="usercierre";
	private String password="usercierre";
	
	//Parametros Testing
	/*private String sistema="jdbc:as400://146.83.1.32/B10A9262";
	private String usuario="qondadm";
	private String password="qondadm";
	*/
	
	private static List log= new ArrayList(); 
	
	public CertificadoDAO() throws Exception, SQLException{
		this.db2= new ConectaDB2(sistema, usuario, password);
		this.db2.getConnection().setAutoCommit(false);
	}
	public String[] getDatosEmpresa(int rutEmp){
		StringBuffer query=new StringBuffer();
		String[] salida= new String[3];
			//CotizanteTO cotizante= (CotizanteTO)obj;
			try {
				//llenaDerecha(rutemp, Len);
				query.append("select max(pwcccdhol), min(pwccconve), max(pwccsucur)");
				query.append("from pwdtad.pwf6000 ");
				query.append("where pwccrutem= ? ");
				db2.prepareQuery(query.toString());
				db2.setStatement(1, rutEmp);
				salida[0]= "";
				salida[1]= "1";
				salida[2]= "CASMAT";
				db2.executeQuery();
				if (db2.next()) {
					salida[0]= db2.getInt(1) + "";
					salida[1]= db2.getInt(2) + "";
					salida[2]= db2.getString(3) + "";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					db2.closeStatement();
				} catch (SQLException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
			return salida;
	}
	
	public boolean isCotizanteVigente(String tipoNomina, int rutEmp, int convenio, int rutCotizante, int periodonew){
		StringBuffer query=new StringBuffer();
		String tabla="";
		boolean vigente=true;
			//CotizanteTO cotizante= (CotizanteTO)obj;
			try {
				if(tipoNomina.equals("G")){
					tabla="pwf6001d";
				}else{
					tabla="pwf6000d";
				}
				//llenaDerecha(rutemp, Len);
				query.append("SELECT pwcccopro FROM pwdtad." + tabla);
				query.append(" WHERE pwccrutem= ? ");
				query.append(" AND pwccconve= ? ");
				query.append(" AND pwccrutaf= ? ");
				db2.prepareQuery(query.toString());
				db2.setStatement(1, rutEmp);
				db2.setStatement(2, convenio);
				db2.setStatement(3, rutCotizante);
				db2.executeQuery();
				if (db2.next()) {
					int periodo= db2.getInt(1);
					if(periodo<periodonew){
						db2.closeStatement();
						query= new StringBuffer();
						query.append("UPDATE pwdtad." + tabla);
						query.append(" SET pwcccopro= ? , ");
						query.append(" pwccfecem= ? ");
						query.append(" WHERE pwccrutem= ? ");
						query.append(" AND pwccconve= ? ");
						query.append(" AND pwccrutaf= ? ");
						//System.out.println("UPDATE periodo: " + query.toString());
						db2.prepareQuery(query.toString());
						db2.setStatement(1, periodonew);
						db2.setStatement(2, getFechaEmision(periodonew));
						db2.setStatement(3, rutEmp);
						db2.setStatement(4, convenio);
						db2.setStatement(5, rutCotizante);
						db2.executeUpdate();
					}
				}else{
					vigente= false;
					//cotizante.setActivo(false);
				}
				
			} catch (SQLException e) {
				System.out.println("Error al consultar trabajador " + rutEmp + "/" + convenio + "/" + rutCotizante );
				System.out.println("Mensaje:" + e.getMessage());
			}
			finally{
				try {
					db2.closeStatement();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return vigente;
	}
	
	public String getFechaEmision(int periodo){
			String per= String.valueOf(periodo);
			String anio= per.substring(0, 4);
			String mes= per.substring(4, 6);
			final String dia= "10";
			if(mes.equals("12")){
				return (Integer.parseInt(anio) + 1) + "01" + dia;
			}else{
				if(Integer.parseInt(mes)<9){
					return anio + "0" + (Integer.parseInt(mes) + 1) + dia;
				}else{
					return anio + (Integer.parseInt(mes) + 1) + dia;
				}
			}
	}
	
	public boolean insertArchivoDetalle(List registros){
		try{
			log.clear();
			int i=0;
			int j=0;
			for (Iterator iter = registros.iterator(); iter.hasNext();) {
				DetalleCertificadoTO registro = (DetalleCertificadoTO) iter.next();
				String query="";
				boolean exito= false;
				int intentos=1;
				do{
					query= registro.toString();
					try {
						if(!query.equals("")){
							db2.executeUpdate(query);
							i++;
						}
						exito= true;
					} catch (SQLException e) {
						String mensaje= e.getMessage();
						if(mensaje.indexOf("clave duplicada")>-1){
							registro.setConvenio(registro.getConvenio()+1);
							intentos++;
						}else{
							e.printStackTrace();
							intentos=4;
						}
					}
				}while(!exito && intentos<4);
				j++;
				/* db2.addBatch(query);
				i++;
				if(i%100==0){
					try {
						db2.executeBatch();
					} catch (SQLException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
					db2.clearBatch();
				} */
				if(i%1000==0){
					System.out.println("Avance: "  + i + " registros detalle insertados");
				}
			}
			//db2.executeBatch();
			//db2.clearBatch();
			System.out.println("Total: "  + i + " registros detalle insertados");
			log.add("Registros Detalle insertados: " + i + "/" + registros.size());
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en InsertDetalle()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}
	
	public boolean insertArchivoCabecera(List registros){
		int i=0;
		int j=0;
		log.clear();
		String query="";
		try{
			for (Iterator iter = registros.iterator(); iter.hasNext();) {
				j++;
				CabeceraCertificadoTO registro = (CabeceraCertificadoTO) iter.next();
				String[] datos= getDatosEmpresa(registro.getRutemp());
				registro.setHolding(Integer.parseInt(datos[0]));
				registro.setConvenio(Integer.parseInt(datos[1]));
				registro.setSucursal(datos[2]);
				boolean existe= isCotizanteVigente(registro.getTipoNomina(), registro.getRutemp(), registro.getConvenio(), registro.getRutafi(), registro.getPeriodo());
				if(!existe && registro.getRutemp()>0){
					query= registro.toString();
					if(!query.equals("")){
						try {
							db2.executeUpdate(query);
							i++;
						} catch (Exception e) {
							String mensaje= e.getMessage();
							if(mensaje.indexOf("clave duplicada")>-1){
								
							}else{
								e.printStackTrace();
								throw new Exception(e);
							}
						}
					}
					/*i++;
					if(i%100==0){
						db2.executeBatch();
						db2.clearBatch();
					}*/
					if(i%1000==0){
						System.out.println("Avance: "  + i + " registros cabecera insertados");
					}
				}
				
			}
			//db2.executeBatch();
			//db2.clearBatch();
			System.out.println("Total : "  + i + " registros cabecera insertados");
			log.add("Registros Cabecera insertados: " + i + "/" + registros.size());
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en InsertCabecera()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}
//	Realiza desconexión al DB2
	public void desconectaDB2(){
		try {
			db2.desconectaDB2();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
			
	}
	
//	Realiza commit al DB2
	public void commit(){
		try {
			db2.getConnection().commit();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
			
	}
	
//	Realiza commit al DB2
	public void rollback(){
		try {
			db2.getConnection().rollback();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
			
	}
	/**
	 * @return el log
	 */
	public static List getLog() {
		return log;
	}
	/**
	 * @param log el log a establecer
	 */
	public static void setLog(List log) {
		CertificadoDAO.log = log;
	}
}
