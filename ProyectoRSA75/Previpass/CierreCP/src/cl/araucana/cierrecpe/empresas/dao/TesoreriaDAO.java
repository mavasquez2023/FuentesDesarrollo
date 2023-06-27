/*
 * Creado el 24-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.sql.Date;

import javax.sql.DataSource;

import com.ibm.as400.access.*;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.BoletaTO;
import cl.recursos.*;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TesoreriaDAO implements DAO_Interface{
	private ConectaDB2 db2teso;
	private String programa;
	private AS400 as400;
	private String usuario;

	public TesoreriaDAO(String programa, String ipTesoreria, String usuario, String password){
		this.programa= programa;
		this.as400 = new AS400(ipTesoreria, usuario, password);
		this.usuario= usuario;
	}
	public TesoreriaDAO(ConectaDB2 db2, String programa, String ipTesoreria, String usuario, String password)throws SQLException{
		this.db2teso= db2;
		this.programa= programa;
		this.as400 = new AS400(ipTesoreria, usuario, password);
		this.usuario= usuario;
	}
	public TesoreriaDAO(ConectaDB2 db2) throws SQLException{
		this.db2teso= db2;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#update(java.lang.Object)
	 */
	public boolean existBoletaTesoreria(Object obj){
		String query="";
		BoletaTO detalleTO= (BoletaTO) obj;

		try {
			query= "select * from tedta.te11f1 where te6xa= ? and obf002= ? and sajkm94= ?";
			String date= Today.getAAAAMMDD('-');
			db2teso.prepareQuery(query);
			db2teso.setStatement(1, detalleTO.getMonto());
			db2teso.setStatement(2, Date.valueOf(date));
			db2teso.setStatement(3, usuario.toUpperCase());
			db2teso.executeQuery();
			if(db2teso.next()){
				return true;
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return false;
	}

	public String insertBoletaTesoreria(Object obj) {
		BoletaTO detalleTO= (BoletaTO) obj;
		int ErrorCode;
		String ErrorMsg;
		String flags = "";

		//System.out.println("SPL, insertBoletaTesoreria parametros , codbanco: " + detalleTO.getCodBanco() + ", numCuenta:" + detalleTO.getCuenta() + ", monMovi :" + detalleTO.getMonto() + ", tipoCargo:" + detalleTO.getTipoAbono() + ", codInt:" + detalleTO.getCodOperacion() );
		int fecVenci= Integer.parseInt(Today.getAAAAMMDD());

		BigDecimal packedin = new BigDecimal(3D);
		BigDecimal packedReturn = new BigDecimal(1D);

		ProgramParameter pList[] = new ProgramParameter[9];

		//System.out.println("El valor de cB es: " +	cB);
		pList[0] = new ProgramParameter(new AS400ZonedDecimal(3, 0).toBytes(detalleTO.getIdBanco()), 0);

		AS400Text ctaCte = new AS400Text(15, as400);
		byte[] cC = ctaCte.toBytes(detalleTO.getIdCuenta());
		pList[1] = new ProgramParameter(cC);

		pList[2] = new ProgramParameter(new AS400ZonedDecimal(8, 0).toBytes(fecVenci),	0);

		pList[3] = new ProgramParameter(new AS400ZonedDecimal(13, 0).toBytes(detalleTO.getMonto()),	0);

		AS400Text tipoCA = new AS400Text(1, as400);
		byte[] tCA = tipoCA.toBytes(detalleTO.getTipoAbono());
		pList[4] = new ProgramParameter(tCA);

		//pList[5] = new ProgramParameter(new AS400ZonedDecimal(11, 0).toBytes(numPrestamo),	0);
		pList[5] = new ProgramParameter(new AS400ZonedDecimal(11, 0).toBytes(0),	0);

		pList[6] = new ProgramParameter(new AS400ZonedDecimal(5, 0).toBytes(detalleTO.getCodOperacion()),	0);

		//Nuevo parámetro para pasar el N° de Préstamo a otra tabla
		AS400Text nCred = new AS400Text(78, as400);
		byte[] nC = nCred.toBytes("0");
		pList[7] = new ProgramParameter(nC);

		AS400Text blanco = new AS400Text(1, as400);
		byte[] b = blanco.toBytes(flags);
		pList[8] = new ProgramParameter(b,1);
		System.out.println("insertBoletaTesoreria, se han definido los parametros de CL");
		QSYSObjectPathName programName = new QSYSObjectPathName(this.programa);
		//System.out.println("Invocando Program  call as400: " + programName.getPath());

		ProgramCall inv0011 =
			new ProgramCall(as400, programName.getPath(), pList);
		try {
			if (!inv0011.run()) {
				StringBuffer msg = new StringBuffer();
				AS400Message msgList[] = inv0011.getMessageList();
				for (int i = 0; i < msgList.length; i++)
					msg.append(msgList[i].getText().toString());

				ErrorCode = 2002;
				ErrorMsg = "Retorno Rutina de Traspaso.\n" + msg.toString();
				System.out.println(ErrorMsg);
				//as400.disconnectAllServices();
				detalleTO.setEstado("E");
				detalleTO.setDescripcionEstado("Error en retorno Rutina de Traspaso Libro de Bancos, mensaje:" + msg.toString());
				return null;
			} else {
				byte[] rbFlags = pList[8].getOutputData();
				String IDflags = (String) blanco.toObject(rbFlags);

				System.out.println("IDflags = |" + IDflags + "|");

				if (rbFlags == null){
					System.out.println("Retorno de función vacío");
				}
				as400.disconnectAllServices();
				//System.out.println("packedReturn=" + packedReturn.toString());
				detalleTO.setEstado("S");
				return packedReturn.toString();
			}
		} catch (ObjectDoesNotExistException dnf) {
			System.out.println("TesoreriaDAO, Error en la ejecución de Programa "+ this.programa + " para ID_Boleta= " + detalleTO.getIdBoleta() + ", mensaje:" + dnf.getMessage());
			ErrorCode = dnf.getReturnCode();
			ErrorMsg = dnf.getMessage();
			dnf.printStackTrace();
			detalleTO.setEstado("E");
			detalleTO.setDescripcionEstado("Error en la ejecución de Programa "+ this.programa + " para ID_Boleta= " + detalleTO.getIdBoleta() + ", mensaje:" + dnf.getMessage());
			return null;
		} catch (Exception error) {
			System.out.println("TesoreriaDAO, Error en la ejecución de Programa "+ this.programa + " para ID_Boleta= " + detalleTO.getIdBoleta() + ", mensaje:" + error.getMessage());
			ErrorCode = -1;
			ErrorMsg =
				"Error al ejecutar la Rutina de Traspaso.'n"
				+ error.getMessage();
			error.printStackTrace();
			detalleTO.setEstado("E");
			detalleTO.setDescripcionEstado("Error en la ejecución de Programa "+ this.programa + " para ID_Boleta= " + detalleTO.getIdBoleta() + ", mensaje:" + error.getMessage());
			return null;
		}
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(int folio){
	
		String queryUpdate= "update tedta.te07f1  set  TE3YA = 'C', TE40A= ?, TE1TA= ?, TE41A='P', OBF005= ?, OBF006= ?, SAJKM92= 'usermq' where  TE3YA = 'I' and TE3WA= ?  ";
		String date= Today.getAAAAMMDD('-');
			String time= Today.getHHMMSS(':');
			int resultado;
			try {
				db2teso.prepareQuery(queryUpdate);
				db2teso.setStatement(1, Date.valueOf(date));
				db2teso.setStatement(2, Time.valueOf(time));
				db2teso.setStatement(3, Date.valueOf(date));
				db2teso.setStatement(4, Time.valueOf(time));
				db2teso.setStatement(5, folio);
				resultado = db2teso.executeUpdate();
			} catch (SQLException e) {
				resultado=0;
				System.out.println("TesoreriaDAO, Error en la ejecución SQL en Tesoreria para folio= " + folio + ", mensaje:" + e.getMessage());
				e.printStackTrace();
			}
		//detalleTO.setFoliosImpresos(no_cursados);
		
		return resultado;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}


	public void closeConnections() throws SQLException{
		if(db2teso!=null){
			db2teso.desconectaDB2();
		}
		if(as400!= null){
			as400.disconnectAllServices();
		}
	}
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
