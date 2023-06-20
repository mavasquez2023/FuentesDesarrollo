/**
 * 
 */
package cl.laaraucana.mandato.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cl.laaraucana.mandato.ibatis.vo.RechazoVo;
import cl.laaraucana.mandato.ibatis.vo.ResumenCargaRechazoVo;

/**
 * @author J-Factory
 *
 */
@Service
public class ProcessFilesServiceImpl implements ProcessFilesService {
	
	private static final Logger logger = Logger.getLogger(ProcessFilesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.mandato.services.ProcessFilesService#cargaArchivoRechazo(org.springframework.web.multipart.commons.CommonsMultipartFile)
	 */
	@Override
	public ResumenCargaRechazoVo cargaArchivoRechazo(CommonsMultipartFile file)
			throws Exception {
		InputStream input= file.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		int cantidadRegistros=0;
		ResumenCargaRechazoVo respuesta = new ResumenCargaRechazoVo();
		List<RechazoVo> listaRechazo= new ArrayList<RechazoVo>();
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
					RechazoVo registro= new RechazoVo();
					numcolumna=1;
					registro.setRutAfiliado(Integer.parseInt(campos[0]));
					numcolumna=2;
					registro.setDvAfiliado(campos[1]);
					numcolumna=3;
					registro.setCodigoBanco(Integer.parseInt(campos[2]));
					numcolumna=4;
					registro.setNumeroCuenta(String.valueOf(Long.parseLong(campos[3])));
					numcolumna=5;
					registro.setMotivoRechazo(campos[4]);
					numcolumna=6;					
					registro.setSistema(campos[5]);
					numcolumna=7;
					registro.setUsuario(campos[6]);
					numcolumna=8;
					registro.setVia("M");
					registro.setEstado(0);
					listaRechazo.add(registro);
					cantidadRegistros++;
				}catch (NumberFormatException e) {
					logger.warn("Mensaje=" + e.getMessage());
					listaErrores.add(line + ";" + "valor no numérico en columna "  + numcolumna);
				}catch (Exception e) {
					if(numline>1){
						logger.warn("Mensaje=" + e.getMessage());
						listaErrores.add(line + ";" + "error en columna "  + numcolumna);
					}
				}
			}
			numline++;
		}
		respuesta.setCantidadRegistros(cantidadRegistros);
		respuesta.setListaRechazo(listaRechazo);
		respuesta.setListaErrores(listaErrores);
		return respuesta;
	}

}
