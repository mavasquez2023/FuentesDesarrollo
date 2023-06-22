/**
 * 
 */
package cl.laaraucana.cuotasdup.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.dao.VO.MapeoArchivoVO;
import cl.laaraucana.cuotasdup.dao.VO.ResultadoCuotasVO;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class MapeoArchivo {
	private final static int EXITO= 0;
	private final static int ERROR_FORMATO= 1;
	private final static int ERROR_CONCEPTO= 2;
	private final static int ERROR_ARCHIVO= 3;
	public static ResultadoCuotasVO leerArchivo(String pathfile, HashMap<String, MapeoArchivoVO> mapeos){
		ResultadoCuotasVO resultado= new ResultadoCuotasVO();
		resultado.setCodigoResultado(EXITO);
		List<CuotaVO> listaCuotas= new ArrayList<CuotaVO>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathfile));
			String line = null;
			int linea=0;
			while ((line = reader.readLine()) != null) {
				int columna=1;
				try {
					linea++;
					line= line.trim();
					if(!line.equals("")){
						String[] lista_campo= line.split(";");
						if(lista_campo.length==15 || lista_campo.length==16){
							String campo="";
							CuotaVO cuota= new CuotaVO();
							//RUT Empresa
							MapeoArchivoVO mapeo= mapeos.get("CMNA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							//campo= line.substring(mapeo.getPosicion(), mapeo.getPosicion() + mapeo.getLargo());
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setRutEmpresa(Integer.parseInt(campo));
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("RUT Empresa");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//DV RUT Empresa
							columna++;
							mapeo= mapeos.get("CMOA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()== mapeo.getLargo()){
								cuota.setDvRutEmpresa(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("DV RUT Empresa");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Nombre Empresa
							columna++;
							mapeo= mapeos.get("CMPA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()<= mapeo.getLargo()){
								cuota.setRazonSocial(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Razón Social");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Código Oficina
							columna++;
							mapeo= mapeos.get("CMBA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setOficina(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Código Oficina");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Nombre Oficina
							columna++;
							mapeo= mapeos.get("CMCA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()<= mapeo.getLargo()){
								cuota.setNombreOficina(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Nombre Oficina");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Código Sucursal
							columna++;
							mapeo= mapeos.get("CM13A");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							campo= campo.substring(1);
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setSucursal(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Código Sucursal");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//RUT Afiliado
							columna++;
							mapeo= mapeos.get("AFIRUT");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setRutAfiliado(Integer.parseInt(campo));
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("RUT Deudor");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//DV RUT Afiliado
							columna++;
							mapeo= mapeos.get("SE5FBH3");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()== mapeo.getLargo()){
								cuota.setDvRutAfiliado(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("DV RUT Deudor");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Nombre Afiliado
							columna++;
							mapeo= mapeos.get("AFHCA");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()<= mapeo.getLargo()){
								cuota.setNombreAfiliado(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Nombre Afiliado");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Monto Cuota
							columna++;
							mapeo= mapeos.get("CUOMONPAG");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1].trim();
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setMontoCuota(Integer.parseInt(campo));
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Monto Cuota");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Número Cuota
							columna++;
							mapeo= mapeos.get("RCUONUM");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1].trim();
							if(campo.equals("")){
								campo="0";
							}
							if(Utils.isNumeric(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setNumeroCuota(Integer.parseInt(campo));
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Número Cuota");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Fecha Pago Cuota
							columna++;
							mapeo= mapeos.get("CUOPAGFEC");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()== mapeo.getLargo()){
								cuota.setFechaPagoCuota(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Fecha Pago Cuota");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Fecha Creación
							columna++;
							mapeo= mapeos.get("OBF002");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()== mapeo.getLargo()){
								cuota.setFechaCreacion(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Fecha Creación");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Hora Creación
							columna++;
							mapeo= mapeos.get("OBF003");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(Utils.isNumeric(campo) && campo.length()== mapeo.getLargo()){
								cuota.setHoraCreacion(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Hora Creación");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Usuario Crreación
							columna++;
							mapeo= mapeos.get("SAJKM94");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							campo= lista_campo[mapeo.getPosicion()-1];
							if(campo.length()<= mapeo.getLargo()){
								cuota.setUsuarioCreacion(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Usuario Creación");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							//Correo Electrónico
							columna++;
							mapeo= mapeos.get("CORELEC");
							if(mapeo==null){
								resultado.setCodigoResultado(ERROR_CONCEPTO);
								break;
							}
							try {
								campo= lista_campo[mapeo.getPosicion()-1];
							} catch (Exception e) {
								campo="";
							}
							if(campo.equals("") || Utils.isValidEmail(campo) && campo.length()<= mapeo.getLargo()){
								cuota.setCorreo(campo);
							}else{
								resultado.setLineaError(linea);
								resultado.setColumnaError("Correo Deudor");
								resultado.setCodigoResultado(ERROR_FORMATO);
								break;
							}
							listaCuotas.add(cuota);
						}else{
							resultado.setLineaError(linea);
							resultado.setColumnaError("" + lista_campo.length);
							resultado.setCodigoResultado(ERROR_FORMATO);
							break;
						}
					}
				}catch(Exception e){
					resultado.setLineaError(linea);
					resultado.setColumnaError(String.valueOf(columna));
					resultado.setCodigoResultado(ERROR_FORMATO);
					break;
				}
			}
			resultado.setListaCuotas(listaCuotas);
		} catch (FileNotFoundException e) {
			resultado.setLineaError(0);
			resultado.setColumnaError("Archivo No Encontrado, ruta:" + pathfile);
			resultado.setCodigoResultado(ERROR_ARCHIVO);
			e.printStackTrace();
		} catch (IOException e) {
			resultado.setLineaError(0);
			resultado.setColumnaError("Archivo no pudo ser leído, ruta:" + pathfile);
			resultado.setCodigoResultado(ERROR_ARCHIVO);
			e.printStackTrace();
		}
		return resultado;
}
}
