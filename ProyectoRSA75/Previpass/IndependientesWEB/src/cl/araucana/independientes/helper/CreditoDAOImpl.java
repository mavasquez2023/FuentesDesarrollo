package cl.araucana.independientes.helper;
/*package cl.araucana.aporte.orqInput.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.aporte.cobol.bo.ParametrosConexionBO;
import cl.araucana.aporte.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.aporte.config.ConfiguracionSqlMap;
import cl.araucana.aporte.config.ConsumidorCobol;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoDetalleBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;
import cl.araucana.aporte.orqInput.bo.DatosParametricosBO;
import cl.araucana.aporte.helper.GlobalProperties;
import cl.araucana.aporte.helper.Helper;

public class CreditoDAOImpl {

	public static CreditoCallBO obtenerCredito (int rut){
		//
		// IMPLEMENTA LA BUSQUEDA DEL RUT PARA RETORNAR
		// DATOS DEL CREDITO DEL AFILIADO INDEPENDIENTE
		//

		ParametrosConexionBO conexion = new ParametrosConexionBO();
		ParametrosLlamadaBO[] llamada = new ParametrosLlamadaBO[3];	
		CreditoCallBO creditoCallBO = new CreditoCallBO();
		CreditoResultBO creditoBO = new CreditoResultBO();
		DatosParametricosBO datosBO = new DatosParametricosBO();
		CreditoDetalleBO [] creditoDetalleBO;
		int numregistro;
		String rutSt = new String();
		String credito = new String();

		try {
			datosBO = obtenerTablasParametricas(37);

		}catch (Exception e){
			e.printStackTrace();
			creditoBO = null;
			creditoCallBO.setCodError(-14);
			if (e.toString().length() < 200){
				creditoCallBO.setGlsError("Error en carga datos de Tabla Parametrica: " + e.toString());
			}
			else{
				creditoCallBO.setGlsError("Error en carga datos de Tabla Parametrica: " + e.toString().substring(0,200));
			}
			creditoCallBO.setCredito(creditoBO);
			return creditoCallBO;
		}

		if (datosBO.getError() == 0 ){		
			rutSt = Helper.retornaString3(datosBO.getEntrada(), String.valueOf(rut));
			ParametrosLlamadaBO par1 = new ParametrosLlamadaBO();
			par1.setTipo("STRING");
			par1.setLargo(datosBO.getEntrada());
			par1.setValor(rutSt);
			par1.setDireccion("IN");
			llamada[0] = par1;

			ParametrosLlamadaBO par2 = new ParametrosLlamadaBO();
			par2.setTipo("STRING");
			par2.setLargo(datosBO.getControl());
			par2.setValor(Helper.retornaString3(datosBO.getControl(), ""));
			par2.setDireccion("OUT");
			llamada[1] = par2;

			ParametrosLlamadaBO par3 = new ParametrosLlamadaBO();
			par3.setTipo("STRING");
			par3.setLargo(datosBO.getSalida());
			par3.setValor(Helper.retornaString(datosBO.getSalida()));
			par3.setDireccion("OUT");
			llamada[2] = par3;			

			conexion.setIpServer(datosBO.getIpServer());
			conexion.setUsuarioConexion(datosBO.getUsuarioConexion());
			conexion.setClaveConexion(datosBO.getClaveConexion());
			conexion.setPrograma(datosBO.getPrograma());

			try{

				ParametrosLlamadaBO[] salida = ConsumidorCobol.call(conexion, llamada);				
				credito = (String)salida[1].getValor();			

				if (Integer.parseInt(credito.substring(0, 1)) == 9){
					creditoBO = null;
					creditoCallBO.setCodError(-2);
					creditoCallBO.setGlsError("Error en Transacción COBOL: Servicio no puede resolver la solicitud");
					creditoCallBO.setCredito(creditoBO);				
				}				
				else if (Integer.parseInt(credito.substring(0, 1)) == 0){
					creditoBO = null;
					creditoCallBO.setCodError(-11);
					creditoCallBO.setGlsError("Cliente no posee deuda");
					creditoCallBO.setCredito(creditoBO);				
				}
				else{
					credito = (String)salida[2].getValor();		
					creditoBO.setNumRegistros(Integer.parseInt(credito.substring(0, 3)));					
					numregistro = creditoBO.getNumRegistros();
					creditoBO.setMonto(Integer.parseInt(credito.substring(3, 14)));
					if (numregistro == 0){
						creditoCallBO.setCodError(-11);
						creditoCallBO.setGlsError("Cliente no posee deuda");
						creditoDetalleBO = null;
					}	
					else{
						creditoCallBO.setCodError(0);
						creditoCallBO.setGlsError("Cliente con deuda");
						creditoDetalleBO = new CreditoDetalleBO [numregistro];
					}
					int j = 14;
					for(int i = 0; i< numregistro; i++){
						CreditoDetalleBO creditoDetalle = new CreditoDetalleBO();
						creditoDetalle.setCodigoOficina(Integer.parseInt(credito.substring(j, j + 3)));
						creditoDetalle.setFolioCredito(Integer.parseInt(credito.substring(j + 3, j + 12)));
						creditoDetalle.setNumCuota(Integer.parseInt(credito.substring(j + 12, j + 15)));
						creditoDetalle.setTotalCoutas(Integer.parseInt(credito.substring(j + 15, j + 18)));
						creditoDetalle.setEstadoCouta(Integer.parseInt(credito.substring(j + 18, j + 20)));
						creditoDetalle.setFechaVencimiento(Integer.parseInt(credito.substring(j + 20, j + 28)));
						creditoDetalle.setLineaCredito(Integer.parseInt(credito.substring(j + 28, j + 31)));
						creditoDetalle.setValorCouta(Integer.parseInt(credito.substring(j + 31, j + 40)));
						creditoDetalle.setCapital(Integer.parseInt(credito.substring(j + 40, j + 49)));
						creditoDetalle.setSeguros(Integer.parseInt(credito.substring(j + 49, j + 58)));
						creditoDetalle.setIntereses(Integer.parseInt(credito.substring(j + 58, j + 67)));
						creditoDetalle.setGravamenes(Integer.parseInt(credito.substring(j + 67, j + 76)));	
						creditoDetalle.setMultas(Integer.parseInt(credito.substring(j + 76, j + 85)));	
						creditoDetalle.setMontoAbonado(Integer.parseInt(credito.substring(j + 85, j + 94)));
						creditoDetalle.setMontoDescuento(Integer.parseInt(credito.substring(j + 94, j + 103)));	
						j = j + 103;
						creditoDetalleBO[i] = creditoDetalle;
					}		
					creditoBO.setCreditoDetalle(creditoDetalleBO);
					creditoCallBO.setCredito(creditoBO);
				}
				return creditoCallBO;

			}catch (Exception e){
				e.printStackTrace();
				creditoBO = null;
				creditoCallBO.setCodError(-1);
				if (e.toString().length() < 200){
					creditoCallBO.setGlsError("Error en conexión a proceso COBOL" + e.toString());
				}
				else{
					creditoCallBO.setGlsError("Error en conexión a proceso COBOL" + e.toString().substring(0,200));
				}
				creditoCallBO.setCredito(creditoBO);
				return creditoCallBO;
			}
		}
		else{
			creditoBO = null;
			creditoCallBO.setCodError(datosBO.getError());
			creditoCallBO.setGlsError(datosBO.getGlserror());
			creditoCallBO.setCredito(creditoBO);
			return creditoCallBO;
		}
	}

	public static DatosParametricosBO obtenerTablasParametricas(int entidad){
		SqlMapClient sqlMap =  null;
		HashMap paramMap = new HashMap();
		String CodErrorResult = new String();
		String entrada = new String();
		String control = new String();
		String salida = new String();
		String ipServer = new String();
		String usuarioConexion = new String();
		String claveConexion = new String();
		String programa = new String();
		int codError = 0;
		DatosParametricosBO datosBO = new DatosParametricosBO();

		GlobalProperties global = GlobalProperties.getInstance();
		ipServer = global.getValorExterno("APO.input.ipServer");
		usuarioConexion = global.getValorExterno("APO.input.user");
		claveConexion = global.getValorExterno("APO.input.password");
		programa = global.getValorExterno("APO.input.programa");

		try {
			paramMap.put("P_ENTIDAD", new Integer(entidad));
			paramMap.put("P_ENTRADA1", new String());
			paramMap.put("P_ENTRADA2", new String());
			paramMap.put("P_ENTRADA3", new String());
			paramMap.put("P_ENTRADA4", new String());
			paramMap.put("P_ENTRADA5", new String());
			paramMap.put("P_ENTRADA6", new String());
			paramMap.put("P_CONTROL", new String());
			paramMap.put("P_SALIDA", new String());
			paramMap.put("P_ERROR", new String());
			try{
				sqlMap = ConfiguracionSqlMap.cargarSqlMap();	
				sqlMap.queryForObject("spOrqInput.SP_DATORQ",paramMap);
			}catch (NullPointerException e){
				datosBO.setError(-1);
				if (e.toString().length() < 200){
					datosBO.setGlserror("Error conexión a Base de Datos: " + e.toString());
				}
				else {
					datosBO.setGlserror("Error conexión a Base de Datos: " + e.toString().substring(0,200));
				}
				return datosBO;
			}

			CodErrorResult = (String)paramMap.get("P_ERROR");
			codError = Integer.parseInt(CodErrorResult.trim());

			if (codError == 0){				
				datosBO.setGlserror("Ingreso log exitoso");
				entrada = (String)paramMap.get("P_ENTRADA1");
				datosBO.setEntrada(Integer.parseInt(entrada.trim()));
				control = (String)paramMap.get("P_CONTROL");
				datosBO.setControl(Integer.parseInt(control.trim()));
				salida = (String)paramMap.get("P_SALIDA");
				datosBO.setSalida(Integer.parseInt(salida.trim()));
				//VARIABLE DE CONEXION
				datosBO.setIpServer(ipServer.trim());
				datosBO.setUsuarioConexion(usuarioConexion.trim());
				datosBO.setClaveConexion(claveConexion.trim());
				datosBO.setPrograma(programa.trim());
			}
			else if (codError == -2){
				datosBO.setGlserror("Error en Transacción SQL");
			}
			else {
				datosBO.setGlserror("Error al ingresar log de información de aporte");
			}			
			datosBO.setError(codError);
			return datosBO;

		}catch (SQLException e) {
				e.printStackTrace();
				datosBO.setError(-2);
				if (e.toString().length() < 200){
					datosBO.setGlserror("Error en Transacción SQL:" + e.toString());
				}
				else {
					datosBO.setGlserror("Error en Transacción SQL:" + e.toString().substring(0,200));
				}
				return datosBO;
		}
		finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				datosBO.setError(-3);	
				if (e.toString().length() < 200){
					datosBO.setGlserror("Error de finalización Transacción SQL:" + e.toString());		
				}
				else{
					datosBO.setGlserror("Error de finalización Transacción SQL:" + e.toString().substring(0,200));		
				}
				return datosBO;
			} 
		}
	}



}*/
