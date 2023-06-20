package cl.laaraucana.rendicionpagonomina.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.lib.export.txt.impl.FileGenerator;



@Service
public class ProcesaManualServiceImpl implements ProcesaManualService {

	private static final Logger logger = Logger.getLogger(ProcesaManualServiceImpl.class);
	

	@Override
	public ResumenCargaPagoManualVo cargaPagoManual(CommonsMultipartFile file, List<String> beneficios)
			throws Exception {
		InputStream input= file.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		int cantidadRegistros=0;
		long montoNomina=0;
		ResumenCargaPagoManualVo respuesta= new ResumenCargaPagoManualVo();
		List<DetalleCargaPagoManualVo> listaNomina= new ArrayList<DetalleCargaPagoManualVo>();
		List<String> listaErrores= new ArrayList<String>();
		int numline=1;
		int numcolumna=0;
		while ((line = reader.readLine()) != null) {
			String rutreg="";
			boolean rutval=true;
			line= line.trim();
			if(!line.equals("")){
				numcolumna=0;
				try {
					if(line.substring(line.length()-1).equals(";")){
						line= line.substring(0, line.length()-1);
					}
					line= line.replaceAll(",", ";");
					line= line.replaceAll("\"", "");
					String[] campos= line.split(";");
					if(numline==1){
						try {
							Integer.parseInt(campos[0]);
						} catch (NumberFormatException e) {
							logger.info("primera linea registro es cabecera");
							throw new Exception("primera linea registro es cabecera" );
						}
					}
					DetalleCargaPagoManualVo registro= new DetalleCargaPagoManualVo();
					numcolumna=1;
					registro.setFechaPago(campos[0]);
					numcolumna=2;
					registro.setRutAfiliado(Integer.parseInt(campos[1]));
					numcolumna=3;
					registro.setDvAfiliado(campos[2]);
					numcolumna=4;
					registro.setNombreAfiliado(campos[3]);
					numcolumna=5;
					registro.setDescripcionPago(campos[4]);
					numcolumna=6;
					int monto= Integer.parseInt(campos[5]);
					
					registro.setMontoPago(monto);
					numcolumna=7;
					String beneficio= campos[6];
					if(beneficios.contains(beneficio)){
						registro.setCodBeneficio(campos[6]);
					}else{
						throw new Exception("codigo beneficio no válido fila" + numline );
					}
					numcolumna=8;
					registro.setReferencia1(campos[7]);
					numcolumna=9;
					registro.setReferencia2(campos[8]);
					listaNomina.add(registro);
					montoNomina += monto;
					cantidadRegistros++;
				}catch (NumberFormatException e) {
					logger.warn("Mensaje=" + e.getMessage());
					listaErrores.add(line + ";" + "valor no numérico en columna "  + numcolumna);
				}catch (Exception e) {
					if(numline>1){
						logger.warn("Mensaje=" + e.getMessage());
						if(numcolumna==7){
							listaErrores.add(line + ";" + "codigo beneficio no válido, columna "  + numcolumna);
						}else{
							listaErrores.add(line + ";" + "error en columna "  + numcolumna);
						}
					}
				}
			}
			numline++;
		}
		respuesta.setCantidadRegistros(cantidadRegistros);
		respuesta.setListaNomina(listaNomina);
		respuesta.setMontoNomina(montoNomina);
		//respuesta.setMontoPendiente(montoNomina);
		respuesta.setListaErrores(listaErrores);
		return respuesta;
	}

	@Override
	public String generaArchivoNomina(ArchivoManualVO archivoManualVO) throws Exception {
		FileGenerator instancia = new FileGenerator();
		String rutasalida=null;
		//se obtiene formato de archivo
		String formato= Configuraciones.getConfig("formato.banco." + archivoManualVO.getIdBanco());
		String tipo_archivo= Configuraciones.getConfig("tipo.archivo.banco." + archivoManualVO.getIdBanco() );
		String separador= Configuraciones.getConfig("separador.archivo.banco." + archivoManualVO.getIdBanco() );

		//generando archivo
		String ruta  = instancia.generar(formato, "idcodconv:" + archivoManualVO.getIdConvenio() + ";;codprod:" + archivoManualVO.getIdProducto() + ";; ", tipo_archivo, separador, null);

		return ruta;
	}

	/*public String generaArchivoNomina(List<DetalleManualEntity> detalle) throws Exception{
		String filename= "Auditoria Usuarios Fecha " + ".csv";
		File destino = new File(filename);
		//Generando la salida
		logger.info("Nombre archivo:" + filename);
		
		//Generando la salida

		OutputStream out= new FileOutputStream(destino);
		PrintStream flujo= new PrintStream(out);
		GeneratorXLS xls= new GeneratorXLS(flujo);

		//Configurando columnas a desplegar y titulos de estas.
		String[] columnas={"rutEjecutivo", "rutUsuario", "rutEmpresa", "accion", "tipoEjecutivo", "envioSMS", "envioEMAIL", "fecha", "hora"};
		String[] titulos={"RUT Ejecutivo", "RUT Usuario", "RUT Empresa", "Accion", "Tipo Ejecutivo", "envio SMS", "envio EMAIL", "Fecha Creacion", "Hora Creacion"};

		xls.generarCSVfromCollection(detalle, columnas, titulos);
		logger.info("Archivo ha sido generado.");
		//Cerrando salida
		out.flush();
		out.close();
		
		return null;
	}*/
	
}
