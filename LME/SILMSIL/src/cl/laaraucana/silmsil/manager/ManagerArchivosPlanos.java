package cl.laaraucana.silmsil.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.ILCLM052DAO;
import cl.laaraucana.silmsil.dao.ILCSIL052DAO;
import cl.laaraucana.silmsil.dao.ILFJA058DAO;
import cl.laaraucana.silmsil.dao.LM_DAO;
import cl.laaraucana.silmsil.dao.PLANOSDAO;
import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.util.ArchivoPlano;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.util.UtilJaspert;
import cl.laaraucana.silmsil.util.UtilProcesar;
import cl.laaraucana.silmsil.util.UtilZip;
import cl.laaraucana.silmsil.vo.EstadisticoSILMSIL_VO;
import cl.laaraucana.silmsil.vo.ILFJA058VO;
import cl.laaraucana.silmsil.vo.ILFLM052VO;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;
import cl.laaraucana.silmsil.vo.RespuestaEscritura_VO;

public class ManagerArchivosPlanos {

	private Logger log = Logger.getLogger(this.getClass());

	UtilProcesar util = new UtilProcesar();

	/**
	 * Método para la generación del archivo plano proceso SIL.
	 * @return
	 */
	public ArrayList<RespuestaEscritura_VO> generarPlanoSIL(ArrayList procLista, String ahno, String usuario) {
		ArrayList<RespuestaEscritura_VO> temp = new ArrayList<RespuestaEscritura_VO>();
		RespuestaEscritura_VO re = new RespuestaEscritura_VO();
		try {
			String fileOut = Configuraciones.getMainConfig("rutaSIL");
			fileOut = fileOut.replaceAll("AAAA", ahno.trim());//cambio 26-03-2015
			String codCAAF = Configuraciones.getMainConfig("codCAAF");
			//			String carpetaDestino = Configuraciones.getMainConfig("directorioSIL");//ya no aplica 26-03-2015
			String periodo = "";
			String mes;
			String meses;
			String msgEscritura = "";
			String nombreArchivo = "";
			//			boolean respIFS=false;//ya no aplica 26-03-2015
			boolean respLocal = false;
			//Obtiene el mes del archivo a crear.
			for (int i = 0; i < procLista.size(); i++) {
				nombreArchivo = "";
				String dato = String.valueOf(procLista.get(i));
				if (!dato.isEmpty()) {
					//Obtención de datos para fecha.
					//mes = util.getMesProceso(dato, 7);
					//mes = util.getMesProceso(dato, 7);					
					mes = util.getMesProceso(dato);
					periodo = ahno + mes;

					meses = util.getMesProcesoMes(dato);

					ILFJA058VO estadosLista = ILFJA058DAO.consultaEstadosValidados("1", periodo);
					if (estadosLista != null) {

						//TODO limpiar talba ILFSIL052
						int n = PLANOSDAO.limpiaIlfsil052();
						log.info("* * [registros eliminados Ilfsil052] " + n);

						//Llamar a programa AS400 para llenado archivo a consultar (LIEXP.ILCSIL052).
						log.info("callILCSIL052");
						boolean cl = ILCSIL052DAO.callILCSIL052(periodo, usuario);
						//Obtiene listado de datos a escribir.
						ArrayList<ILFSIL052VO> silList = PLANOSDAO.obtenerDatosPlanoSIL();
						if (silList.size() != 0) {
							//Llama a método para crear archivo.
							//nombreArchivo=codCAAF+mes+".csv";
							nombreArchivo = codCAAF + meses;
							String fileOutAux = fileOut + nombreArchivo;

							respLocal = ArchivoPlano.writeFileSIL(silList, fileOut, fileOutAux, periodo);

							//Pasar archivo a carpeta compartida AS400. //ya no aplica 26-03-2015
							log.info("fileOutAux: " + fileOutAux);
							//								log.info("carpetaDestino_nombreArchivo: "+carpetaDestino+nombreArchivo); //ya no aplica 26-03-2015
							//								log.info("setCopyByIFS("+fileOutAux+" , "+carpetaDestino+nombreArchivo+")"); //ya no aplica 26-03-2015

							if (respLocal) {
								msgEscritura = "se genero correctamente el archivo.";
								//									respIFS= ArchivoPlano.setCopyByIFS(fileOutAux,carpetaDestino+nombreArchivo); //ya no aplica 26-03-2015
								//									log.info("respIFS: "+respIFS);	
							} else {
								msgEscritura = "ocurrio un problema al crear el archivo";
								respLocal = false;
							}
						} else {
							msgEscritura = "El estado del periodo no presenta datos";
							respLocal = false;
						}
					} else {
						msgEscritura = "El estado del periodo seleccionado no esta procesado";
						respLocal = false;
					}

					//cargamos respuesta escritura
					re = new RespuestaEscritura_VO();
					re.setPeriodo(periodo);
					re.setCarpetaInterna(fileOut);
					re.setEscritoLocal(respLocal);
					//					re.setEscritoIFS(respIFS);		//ya no aplica 26-03-2015
					re.setMsgEscritura(msgEscritura);
					re.setNombreArchivo(nombreArchivo);
					re.setProceso("SIL");
					//					re.setRutaArchivo(carpetaDestino);//ya no aplica 26-03-2015
					temp.add(re);
				}
			}//end for
		} catch (Exception ex) {
			log.error("Error generarPlanoSIL()" + ex.getMessage());
			ex.printStackTrace();
		}
		return temp;
	}

	/**
	 * Método para la generación del archivo plano proceso LM.
	 * @return
	 */
	public ArrayList<RespuestaEscritura_VO> generarPlanoLM(ArrayList procLista, String ahno, String usuario) {
		log.info("* * [generarPlanoLM] * * ");
		ArrayList<RespuestaEscritura_VO> temp = new ArrayList<RespuestaEscritura_VO>();
		RespuestaEscritura_VO re = new RespuestaEscritura_VO();
		try {
			String fileOut = Configuraciones.getMainConfig("rutaLM");
			fileOut = fileOut.replaceAll("AAAA", ahno.trim());//cambio 26-03-2015
			String codCAAF = Configuraciones.getMainConfig("codCAAF");
			//			String carpetaDestino = Configuraciones.getMainConfig("directorioLM");//ya no aplica 26-03-2015
			String periodo = "";
			String mes;
			String meses;
			String msgEscritura = "";
			String nombreArchivo = "";
			//			boolean respIFS=false;//ya no aplica 26-03-2015
			boolean respLocal = false;

			//Obtiene el mes del archivo a crear.
			for (int i = 0; i < procLista.size(); i++) {

				nombreArchivo = "";
				String dato = String.valueOf(procLista.get(i));
				if (!dato.isEmpty()) {
					//Obtención de datos para fecha.
					//mes = util.getMesProceso(dato, 7);
					//mes = util.getMesProceso(dato, 6);					
					mes = util.getMesProceso(dato);
					periodo = ahno + mes;
					//meses = util.getMesProcesoMes(dato, 6);
					meses = util.getMesProcesoMes(dato);

					ILFJA058VO estadosLista = ILFJA058DAO.consultaEstadosValidados("2", periodo);
					if (estadosLista != null) {

						//TODO delete ILFLM052
						int n = PLANOSDAO.limpiaIlflm052();
						log.info("* * [registros eliminados Ilflm052] " + n);

						//Llamar a programa AS400 para llenado archivo a consultar (LIEXP.ILCLM052).
						log.info("callILCLM052");
						boolean cl = ILCLM052DAO.callILCLM052(periodo, usuario);

						//Obtiene listado de datos a escribir.
						log.info("obtenerDatosPlanoLM");
						ArrayList<ILFLM052VO> lmList = PLANOSDAO.obtenerDatosPlanoLM();
						if (lmList.size() != 0) {
							log.info("* * [obtenerDatos] " + lmList.size());

							//Llama a método para crear archivo.
							//nombreArchivo=codCAAF+mes+".csv";
							nombreArchivo = codCAAF + meses;
							String fileOutAux = fileOut + nombreArchivo;
							respLocal = ArchivoPlano.writeFileLM(lmList, fileOut, fileOutAux, periodo);//cambio 26-03-2015							

							//Pasar archivo a carpeta compartida AS400.
							//comentado para test
							log.info("fileOutAux: " + fileOutAux);
							//						log.info("carpetaDestino_nombreArchivo: "+carpetaDestino+nombreArchivo);//ya no aplica 26-03-2015
							//						log.info("setCopyByIFS("+fileOutAux+" , "+carpetaDestino+nombreArchivo+")");//ya no aplica 26-03-2015

							if (respLocal) {
								msgEscritura = "se genero correctamente el archivo.";
								//							respIFS= ArchivoPlano.setCopyByIFS(fileOutAux,carpetaDestino+nombreArchivo);//ya no aplica 26-03-2015
								//							log.info("respIFS: "+respIFS);	//ya no aplica 26-03-2015
							} else {
								msgEscritura = "ocurrio un problema al crear el archivo";
								respLocal = false;
							}
						} else {
							msgEscritura = "El estado del periodo no presenta datos";
							respLocal = false;
						}
					} else {
						msgEscritura = "El estado del periodo seleccionado no esta procesado";
						respLocal = false;
					}
					//cargamos respuesta escritura
					re = new RespuestaEscritura_VO();
					re.setPeriodo(periodo);
					re.setCarpetaInterna(fileOut);
					re.setEscritoLocal(respLocal);
					//					re.setEscritoIFS(respIFS);	//ya no aplica 26-03-2015	
					re.setMsgEscritura(msgEscritura);
					re.setNombreArchivo(nombreArchivo);
					re.setProceso("LM");
					//					re.setRutaArchivo(carpetaDestino);//ya no aplica 26-03-2015
					temp.add(re);
				}

			}//end for	
		} catch (Exception ex) {
			log.error("Error generarPlanoLM()" + ex.getMessage());
			ex.printStackTrace();
		}
		return temp;
	}

	/**
	 * Método para la generación del archivo plano Estadístico.
	 * @return
	 */
	public ArrayList<RespuestaEscritura_VO> generarPlanoEstadistico(ArrayList checkSIL_List, ArrayList checkLM_List, String ahno, String usuario) throws Exception {
		log.info("* * * * * generarPlanoEstadistico * * * * *");
		ArrayList<RespuestaEscritura_VO> temp = new ArrayList<RespuestaEscritura_VO>();
		RespuestaEscritura_VO re;

		//String fileOut = Configuraciones.getMainConfig("rutaEstadistico");//local		
		//String carpetaDestino = Configuraciones.getMainConfig("directorioEstadistico");//remoto

		//		String fileOut = Configuraciones.getMainConfig("directorioEstadistico");//ya no aplica 26-03-2015		
		String carpetaDestino = Configuraciones.getMainConfig("rutaEstadistico");
		carpetaDestino = carpetaDestino.replaceAll("AAAA", ahno.trim());//cambio 26-03-2015

		String dirTemplateCE = Configuraciones.getMainConfig("templateEstadistico");

		String nombreArchivo = "";
		String msgEscritura = "";
		String periodo;
		String mes;
		ArrayList listaPeriodos = new ArrayList();
		ArrayList estadoProcList = new ArrayList();
		ArrayList silCE_List = new ArrayList();
		ArrayList lmCE_List = new ArrayList();
		ArrayList ce_List = new ArrayList();
		int auxLM = 0;
		int auxSIL = 0;
		EstadisticoSILMSIL_VO ceVO;
		EstadisticoSILMSIL_VO ceVOaux = new EstadisticoSILMSIL_VO();
		Iterator it;
		ILFJA058VO ja58;
		boolean keyValidacion = false;
		//		boolean respIFS=false;//ya no aplica 26-03-2015
		boolean respLocal = false;
		//se generara el estadistico
		ArrayList procLista = new ArrayList();
		procLista.addAll(checkSIL_List);
		procLista.addAll(checkLM_List);
		//Obtiene el mes del archivo a crear.

		//extraemos periodos a procesar, deben venir en pares sil y lm
		String mesA = "";
		String mesB = "";
		for (int i = 0; i < checkSIL_List.size(); i++) {
			String datoA = String.valueOf(checkSIL_List.get(i));
			if (!datoA.isEmpty()) {
				//mesA = util.getMesProceso(datoA, 7);
				mesA = util.getMesProceso(datoA);
				for (int j = 0; j < checkLM_List.size(); j++) {
					String datoB = String.valueOf(checkLM_List.get(j));
					if (!datoB.isEmpty()) {
						//mesB = util.getMesProceso(datoB, 7);
						mesB = util.getMesProceso(datoB);
						if (mesA.equalsIgnoreCase(mesB)) {
							periodo = ahno + mesA;
							listaPeriodos.add(periodo);
						}
					}
				}
			}
		}
		log.info("listaPeriodos: " + listaPeriodos.size());

		for (int i = 0; i < listaPeriodos.size(); i++) {
			int count = 0;
			String dato = String.valueOf(listaPeriodos.get(i));
			if (!dato.isEmpty()) {
				//Obtención de datos para fecha.
				//mes = util.getMesProceso(dato, 7);
				//periodo = ahno+mes;
				periodo = dato;
				//validacion estado procesos si existen retornara dos registros, estadoProceso >6 y errores por proceso en CERO
				estadoProcList = ILFJA058DAO.consultaEstadosValidadosCeros(periodo);
				System.out.println("largo de lista consultaEstadosValidadosCeros "+estadoProcList.size());
				//si count 2, entonces ambos registros cumplieron con proceso >6 y errores en cero.
				//entonces consultamos existencia de datos resultado de proceso sil y LM.
				log.info("estadoProcList: " + estadoProcList);
				if (estadoProcList != null && estadoProcList.size() == 2) {
					auxLM = LM_DAO.count_LM(periodo);
					auxSIL = SIL_DAO.count_SIL(periodo);
					ceVO = new EstadisticoSILMSIL_VO();
					ce_List = ceVO.getListaEstadisticoCero(Integer.parseInt(periodo));
					if (auxLM > 0) {
						lmCE_List = PLANOSDAO.obtenerEstadistico_LM(periodo);
						if (lmCE_List.size() == 2) {
							ce_List.set(0, lmCE_List.get(0));
							ce_List.set(1, lmCE_List.get(1));
						} else if (lmCE_List.size() == 1) {
							ceVOaux = (EstadisticoSILMSIL_VO) lmCE_List.get(0);
							ceVOaux.setPeriodo(Integer.parseInt(periodo));
							if (ceVOaux.getClasificacion() == 1) {
								ce_List.set(0, ceVOaux);
							} else {
								ce_List.set(1, lmCE_List.get(1));
							}
						}
					}
					if (auxSIL > 0) {
						silCE_List = PLANOSDAO.obtenerEstadistico_SIL(periodo);
						if (silCE_List.size() == 2) {
							ce_List.set(2, silCE_List.get(0));
							ce_List.set(3, silCE_List.get(1));
						} else if (silCE_List.size() == 1) {
							ceVOaux = (EstadisticoSILMSIL_VO) silCE_List.get(0);
							if (ceVOaux.getClasificacion() == 3) {
								ce_List.set(2, ceVOaux);
							} else {
								ce_List.set(3, silCE_List.get(1));
							}
						}
					}
					//generar jasper
					UtilJaspert uj = new UtilJaspert();
					//escritura local jasper excel
					//nombreArchivo="estadistico_"+periodo+".xls";
					nombreArchivo = "ES" + periodo + ".xls";
					try {
						//crear Directorio
						File file = new File(carpetaDestino);
						file.mkdirs();

						respLocal = uj.writeJasper(carpetaDestino + nombreArchivo, periodo, dirTemplateCE, ce_List);

					} catch (Exception ex) {
						ex.printStackTrace();
						respLocal = false;
					}
					//Pasar archivo a carpeta compartida AS400.					
					//comentado para test.
					//respIFS= ArchivoPlano.setCopyByIFS(carpetaDestino, fileOut);

					//					log.info("* fileOut_nombreArchivo: "+fileOut+nombreArchivo);//ya no aplica 26-03-2015
					log.info("* carpetaDestino_nombreArchivo: " + carpetaDestino + nombreArchivo);
					//					log.info("* setCopyByIFS("+carpetaDestino+nombreArchivo+" , "+fileOut+nombreArchivo+")");//ya no aplica 26-03-2015

					if (respLocal) {
						msgEscritura = "se genero correctamente el cuadro estadistico para este periodo";
						log.info("\n \n");
						//						respIFS= ArchivoPlano.setCopyByIFS(carpetaDestino+nombreArchivo,fileOut+nombreArchivo);//ya no aplica 26-03-2015
						//String fileOutAux = fileOut+nombreArchivo;
						//respIFS= ArchivoPlano.setCopyByIFS(fileOutAux,carpetaDestino+nombreArchivo);
						//						log.info("respIFS: "+respIFS);	//ya no aplica 26-03-2015
					} else {
						msgEscritura = "ocurrio un problema al generar el archivo estadistico";
					}
					re = new RespuestaEscritura_VO();
					re.setPeriodo(periodo);
					//cargamos respuesta escritura
					re.setCarpetaInterna(carpetaDestino);
					re.setEscritoLocal(respLocal);
					//					re.setEscritoIFS(respIFS);	//ya no aplica 26-03-2015	
					re.setMsgEscritura(msgEscritura);
					re.setNombreArchivo(nombreArchivo);
					re.setProceso("Cuadro Estadistico");
					//					re.setRutaArchivo(fileOut);//ya no aplica 26-03-2015
					temp.add(re);
				}
			}
		}//end for		
		return temp;
	}//end generarPlanoEstadistico

	public ArrayList<RespuestaEscritura_VO> generarArchivos(ArrayList checkSIL_List, ArrayList checkLM_List, String ahno, String usuario) {
		ArrayList<RespuestaEscritura_VO> listaDescarga = new ArrayList<RespuestaEscritura_VO>();
		ArrayList<RespuestaEscritura_VO> listaDescargaVista = new ArrayList<RespuestaEscritura_VO>();
		try {
			RespuestaEscritura_VO re = new RespuestaEscritura_VO();
			re.setRutaArchivo("");
			re.setNombreArchivo("ZIP");
			int vc = 0;

			listaDescargaVista.addAll(this.generarPlanoLM(checkLM_List, ahno, usuario));
			listaDescargaVista.addAll(this.generarPlanoSIL(checkSIL_List, ahno, usuario));
			listaDescargaVista.addAll(this.generarPlanoEstadistico(checkSIL_List, checkLM_List, ahno, usuario));

			//verificar los archivos que se escribieron de la lista solicitada.
			for (RespuestaEscritura_VO rer : listaDescargaVista) {
				//log.info("* *nombre: "+rer.getNombreArchivo()+"\n disponible: "+rer.isEscritoLocal());
				if (rer.isEscritoLocal()) {
					listaDescarga.add(listaDescargaVista.get(vc));
				}
				vc++;
			}

			Configuraciones config = new Configuraciones();
			UtilZip uZip = new UtilZip();
			String rutasOrigen[] = new String[listaDescarga.size()];
			String nombresOrigen[] = new String[listaDescarga.size()];
			String nombreCarpetas[] = new String[listaDescarga.size()];
			String rutaDestino = config.getMainConfig("rutaEstadistico");
			rutaDestino = rutaDestino.replaceAll("AAAA", ahno.trim());//cambio 26-03-2015
			String nombreDestino = "SILMSIL_" + ahno;

			//log.info("* * listaDescarga(): "+listaDescarga.size());
			//log.info("* * listaDescargaVista(): "+listaDescargaVista.size());			
			vc = 0;
			if (listaDescarga.size() > 0) {
				for (RespuestaEscritura_VO rer : listaDescarga) {
					log.info("* *");
					log.info("* * getCarpetaInterna(): " + rer.getCarpetaInterna());
					log.info("* * getNombreArchivo(): " + rer.getNombreArchivo());
					log.info("* * isEscritoLocal(): " + rer.isEscritoLocal());
					rutasOrigen[vc] = rer.getCarpetaInterna();
					nombresOrigen[vc] = rer.getNombreArchivo();
					if (rer.getProceso().equalsIgnoreCase("LM") || rer.getProceso().equalsIgnoreCase("SIL")) {
						nombreCarpetas[vc] = rer.getProceso() + "\\";
					} else {
						nombreCarpetas[vc] = "";
					}
					vc++;
				}

				log.info("* * rutaDestinoZIP: " + rutaDestino);
				log.info("* * nombreDestinoZIP: " + nombreDestino);

				boolean respZip = uZip.generarZip(rutasOrigen, nombresOrigen, nombreCarpetas, rutaDestino, nombreDestino);

				if (respZip) {
					re.setRutaArchivo(rutaDestino + nombreDestino + ".zip");
					re.setNombreArchivo(nombreDestino + ".zip");
					re.setEscritoLocal(true);
				} else {
					re.setEscritoLocal(false);
				}
			} else {
				re.setEscritoLocal(false);
			}
			listaDescargaVista.add(re);
			//listaDescarga.add(re);
		} catch (Exception e) {
			log.error("* *[CATCH: generarArchivos]" + e.getMessage());
			e.printStackTrace();
		}
		//return listaDescarga;		
		return listaDescargaVista;
	}//end generarArchivos
}
