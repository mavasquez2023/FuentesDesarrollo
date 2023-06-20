package cl.laaraucana.silmsil.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.ILCLM050DAO;
import cl.laaraucana.silmsil.dao.ILCSIL050DAO;
import cl.laaraucana.silmsil.dao.ILFJA057DAO;
import cl.laaraucana.silmsil.dao.ILFJA058DAO;
import cl.laaraucana.silmsil.vo.EstadoSILLMVO;
import cl.laaraucana.silmsil.vo.EstadoVO;
import cl.laaraucana.silmsil.vo.ILFJA058VO;
import cl.laaraucana.silmsil.vo.ListadoPrincipalVO;

/**
 * Carga datos en interfaz gráfica index.jsp.
 * @author usist42
 *
 */
public class ManagerInicio {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * Obtiene listado completo para interfaz gráfica.
	 * SIL y LM.
	 * @param ahno
	 * @return
	 */
	public ArrayList<Trimestre> cargarListadoInterfaz(String ahno){
		ArrayList<Trimestre> trimestre =  new ArrayList<Trimestre>();
		ArrayList<ILFJA058VO> lista = new ArrayList<ILFJA058VO>();
		ArrayList<ILFJA058VO> listaMax6 = new ArrayList<ILFJA058VO>();
		try{
			//Obtiene fecha actual.
			String anno = ahno+"%";
			log.info("Ahno = " + ahno);
			//Consulta proceso-estado.
			lista = ILFJA058DAO.consultaEstados(anno);
			listaMax6 = ILFJA058DAO.consultaEstadosValidadosConError(anno);
			//Recorre la lista y la setea en HashMap por período.
			HashMap<String, EstadoSILLMVO> map = this.fullMap(ahno, lista,listaMax6);
			
			//Carga ArrayList<Trimestre>.
			trimestre = this.getListadoTrimestre(ahno, map);

			return trimestre;
		}
		catch(Exception ex){
			log.error("Error cargarListadoInterfaz() :" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método de llenado de HashMap para "KEY" vacias.
	 * @param ahno
	 * @param map
	 * @return
	 */
	private HashMap<String, EstadoSILLMVO> fullMap(String ahno, ArrayList<ILFJA058VO> lista, ArrayList<ILFJA058VO> listaMax6){
		log.info("Método ManagerInicio.fullMap(String ahno, ArrayList<ILFJA058VO> lista, ArrayList<ILFJA058VO> listaMax6)");
		ILFJA058VO vo58 = new ILFJA058VO();
		EstadoSILLMVO est = null;
		try{
			HashMap<String, EstadoSILLMVO> map = new HashMap<String, EstadoSILLMVO>();
			
			//Listado de meses validos a procesar.
			/*
			String meses[] = {ahno+"01", ahno+"01", ahno+"02", ahno+"03", ahno+"04", ahno+"05", ahno+"06", 
				ahno+"07", ahno+"08", ahno+"09", ahno+"10", ahno+"11", ahno+"12"};
			*/
			//Cargar datos provenientes de listado consulta SQL en HashMap.
			for(ILFJA058VO vo: lista){
				 if(map.get(vo.getPepret()) == null){
					 est = new EstadoSILLMVO();
				 }
				 else{
					 est = map.get(vo.getPepret());
				 }
				 if(vo.getIdproc() == 1){
					 est.setSil(vo);
				 }
				 else{
					 est.setLm(vo);
				 }
				 //Revisar dato por KEY.
				 map.put(vo.getPepret(),  est);
				 
			}
			
			//Cargar datos provenientes de listado consulta SQL en HashMap.
			for(ILFJA058VO vo: listaMax6){
				 if(map.get(vo.getPepret()) == null){
					 est = new EstadoSILLMVO();
				 }else{
					 est = map.get(vo.getPepret());
				 }
				 
				 if(vo.getIdproc() == 1){
					 if(est.getSil().getIdesta()>=6){
						 est.setSil(vo);						 
					 }
				 }else{
					 if(est.getLm().getIdesta()>=6){
						 est.setLm(vo);
					 }					 
				 }
				 //Revisar dato por KEY.
				 map.put(vo.getPepret(),  est);
			}
			
			return map;
		}
		catch(Exception ex){
			log.error("Error fullMap() :" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Método para generar listado de trimestre.
	 * @return
	 */
	private ArrayList<Trimestre> getListadoTrimestre(String ahno, HashMap<String, EstadoSILLMVO> map){
		log.info("Método ManagerInicio.getListadoTrimestre(String ahno, HashMap<String, " +
			"EstadoSILLMVO> map)");
		EstadoSILLMVO est = null;
		ILFJA058VO vo58 = null;
		
		ArrayList<Trimestre> lista0 = new ArrayList<Trimestre>();
		ArrayList<Mes> lista1 = new ArrayList<Mes>();
		ArrayList<Mes> lista2 = new ArrayList<Mes>();
		ArrayList<Mes> lista3 = new ArrayList<Mes>();
		ArrayList<Mes> lista4 = new ArrayList<Mes>();
		try{
			//Lista meses validos a procesar.
			String meses[] = {ahno+"01", ahno+"01", ahno+"02", ahno+"03", ahno+"04", ahno+"05", ahno+"06", 
				ahno+"07", ahno+"08", ahno+"09", ahno+"10", ahno+"11", ahno+"12"};
			
			int idProcSil = 0; int idProcLm = 0; long errSil = 0;
			long errLm = 0; long proceSil = 0; long proceLm = 0;
			
			for(int i = 1; i < meses.length; i++){
				est = map.get(meses[i]);
				
				//Llenar datos key no existentes con VO vacio.
				if(est != null){
					if(est.getSil() == null){
						est.setSil(new ILFJA058VO());
					}
					else if(est.getLm() == null){
						est.setLm(new ILFJA058VO());
					}
				}
				else{
					vo58 = new ILFJA058VO();
					est =  new EstadoSILLMVO(vo58, vo58);
				}
				
				//Carga datos a procesar por mes.
				idProcSil = (est.getSil().getIdproc() != 0?est.getSil().getIdproc():0);
				idProcLm = (est.getLm().getIdproc() != 0?est.getLm().getIdproc():0);
				errSil = (est.getSil().getNepret() != 0?est.getSil().getNepret():0);
				errLm = (est.getLm().getNepret() != 0?est.getLm().getNepret():0);
				proceSil = (est.getSil().getNrpret() != 0?est.getSil().getNrpret():0);
				proceLm = (est.getLm().getNrpret() != 0?est.getLm().getNrpret():0);
				
				
				int opc = Integer.parseInt(meses[i].substring(4));
				
//				System.out.println("SIL: período ["+meses[i]+"] proceSil ["+proceSil+"]");
//				System.out.println("LM: período ["+meses[i]+"] proceLm ["+proceLm+"]");
				
				switch(opc){
				case 1: 
					lista1.add(new Mes("Enero", Integer.parseInt(ahno+"01") , "chk_SIL01", "chk_LM01", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"01","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"01","LM")));
					break;
				case 2:
					lista1.add(new Mes("Febrero", Integer.parseInt(ahno+"02"),  "chk_SIL02", "chk_LM02", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"02","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"02","LM")));
					break;
				case 3: 
					lista1.add(new Mes("Marzo", Integer.parseInt(ahno+"03"),  "chk_SIL03", "chk_LM03", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"03","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"03","LM")));
					break;
				case 4: 
					lista2.add(new Mes("Abril", Integer.parseInt(ahno+"04"),  "chk_SIL04", "chk_LM04", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"04","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"04","LM")));
					break;
				case 5: 
					lista2.add(new Mes("Mayo", Integer.parseInt(ahno+"05"),  "chk_SIL05", "chk_LM05", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"05","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"05","LM")));
					break;
				case 6: 
					lista2.add(new Mes("Junio", Integer.parseInt(ahno+"06"),  "chk_SIL06", "chk_LM06", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"06","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"06","LM")));
					break;
				case 7: 
					lista3.add(new Mes("Julio", Integer.parseInt(ahno+"07"),  "chk_SIL07", "chk_LM07", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"07","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"07","LM")));
					break;
				case 8: 
					lista3.add(new Mes("Agosto", Integer.parseInt(ahno+"08"), "chk_SIL08", "chk_LM08", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"08","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"08","LM")));
					break;
				case 9: 
					lista3.add(new Mes("Septiembre",Integer.parseInt(ahno+"09"),  "chk_SIL09", "chk_LM09", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"09","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"09","LM")));
					break;
				case 10: 
					lista4.add(new Mes("Octubre", Integer.parseInt(ahno+"10"), "chk_SIL10", "chk_LM10", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"10","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"10","LM")));
					break;
				case 11: 
					lista4.add(new Mes("Noviembre",Integer.parseInt(ahno+"11"), "chk_SIL11", "chk_LM11", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"11","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"11","LM")));
					break;
				case 12:
					lista4.add(new Mes("Diciembre",Integer.parseInt(ahno+"12"),  "chk_SIL12", "chk_LM12", 
						getEstado(est.getSil().getIdesta(), errSil, proceSil,ahno+"12","SIL"), 
						getEstado(est.getLm().getIdesta(), errLm, proceLm,ahno+"12","LM")));
					break;
				default:
					System.out.println("Error, mes no válido.");
				}
			}	
			//Carga lista trimestre a retornar.
			lista0.add(new Trimestre("01", lista1));
			lista0.add(new Trimestre("02", lista2));
			lista0.add(new Trimestre("03", lista3));
			lista0.add(new Trimestre("04", lista4));
			
			return lista0;
		}
		catch(Exception ex){
			log.error("Error getListadoTrimestre() :" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Método para generar listado de estado.
	 * @return
	 */
	private ArrayList<EstadoVO> getEstado(int idEstado, long errores, long procesados,String periodo, String proceso){
		try{
			ArrayList<EstadoVO> lista = new ArrayList<EstadoVO>();
			EstadoVO estado =  new EstadoVO();
			switch(idEstado){
				case 1:
					//Iniciado.
					estado.setTexto(this.estadoILFJA057(idEstado)); estado.setBoton("sinBoton");
					lista.add(estado);
					break;
				case 2:
					//Cargando.
					estado.setTexto(this.estadoILFJA057(idEstado)); estado.setBoton("sinBoton");
					lista.add(estado);
					break;
				case 3:
					//Cargado.
					estado.setTexto(this.estadoILFJA057(idEstado)); estado.setBoton("sinBoton");
					lista.add(estado);
					break;
//				case 4:
//					estado.setTexto("Validando"); estado.setBoton("sinBoton");
//					lista.add(estado);
//					break;
				case 5:
					//Validando.
					estado.setTexto(this.estadoILFJA057(idEstado));
					estado.setBoton("sinBoton");
					lista.add(estado);
					break;
				case 6: 
					//Validado.
					estado.setTexto("<p>"+this.estadoILFJA057(idEstado) + "[errores=#"+errores+"],</p> " +
						"<p>procesados=#"+procesados+"].</p>"); 
						if(errores==0){
							estado.setBoton("sinBoton");
						}else{
							estado.setBoton("btnCorregir");
							estado.setProceso_periodo(proceso+"_"+periodo);
						}
					
					lista.add(estado);
					break;
				case 8:
					//Generando.
					estado.setTexto(this.estadoILFJA057(idEstado)); estado.setBoton("sinBoton");
					lista.add(estado);
					break;
				case 9:
					//Generado.
					estado.setTexto("<p>"+this.estadoILFJA057(idEstado)+"</p>"); estado.setBoton("btnDescargar");
					estado.setProceso_periodo(proceso+"_"+periodo);
					lista.add(estado);
					break;
				default:
					estado.setTexto("S/N"); estado.setBoton("sinBoton");
					lista.add(estado);
					break;
			}
			return lista;
		}
		catch(Exception ex){
			log.error("Error getListadoEstadoVO() :" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método para obtener etiqueta de estado a través del número 
	 * identificador de estado.
	 * @param ideEstado
	 * @return
	 */
	private String estadoILFJA057(int ideEstado){
		try{
			String estado = ILFJA057DAO.etiquetaEstados(ideEstado);
			return estado;
		}
		catch(Exception ex){
			log.error("Error,  :" + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Método para generar fecha actual.
	 * @return
	 */
	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String date = sdf.format(new Date());
		return date;
	}
	
	/**
	 * Método para generar año actual.
	 * @return
	 */
	private static String getDateAhno(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(new Date());
		return date;
	}
	
	
//	DE AQUÍ EN ADELANTE CLASES DUMMY DE PRUEBA COMENTADAS.
//
//	public ArrayList getListado(){
//		try{
//			//Cargar listado por período del proceso.
//			ArrayList<Mes> lista1 = new ArrayList<Mes>();
//			lista1.add(new Mes("Enero", "chk_SIL01", "chk_LM01", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista1.add(new Mes("Febrero", "chk_SIL02", "chk_LM02", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista1.add(new Mes("Marzo", "chk_SIL03", "chk_LM03", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			
//			ArrayList<Mes> lista2 = new ArrayList<Mes>();
//			lista2.add(new Mes("Abril", "chk_SIL04", "chk_LM04", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista2.add(new Mes("Mayo", "chk_SIL05", "chk_LM05", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista2.add(new Mes("Junio", "chk_SIL06", "chk_LM06", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			
//			ArrayList<Mes> lista3 = new ArrayList<Mes>();
//			lista3.add(new Mes("Julio", "chk_SIL07", "chk_LM07", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista3.add(new Mes("Agosto", "chk_SIL08", "chk_LM08", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista3.add(new Mes("Septiembre", "chk_SIL09", "chk_LM09", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			
//			ArrayList<Mes> lista4 = new ArrayList<Mes>();
//			lista4.add(new Mes("Octubre", "chk_SIL10", "chk_LM10", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista4.add(new Mes("Noviembre", "chk_SIL11", "chk_LM11", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			lista4.add(new Mes("Diciembre", "chk_SIL12", "chk_LM12", this.getEstadoProcesados("201405", 1), this.getEstadoProcesados("201405", 1)));
//			
//			//Cargar listado a mostrar por pantalla.
//			ArrayList<Trimestre> lista5 = new ArrayList<Trimestre>();
//			lista5.add(new Trimestre("01", lista1));
//			lista5.add(new Trimestre("02", lista2));
//			lista5.add(new Trimestre("03", lista3));
//			lista5.add(new Trimestre("04", lista4));
//			
//			return lista5;
//		}
//		catch(Exception ex){
//			log.error("Error en getListado" + ex.getMessage());
//			ex.printStackTrace();
//		}
//		return null;
//	}
//	
//	
//	public ArrayList getEstadoProcesados(String periodo, int idProc){
//		
//		ILFJA058VO vo = new ILFJA058VO();
//		ArrayList<EstadoVO> listaEstado = new ArrayList<EstadoVO>();
//		EstadoVO estado = new EstadoVO();
//		
//		try{
//			
//			//Consulta por estado proceso en archivo LIEXP.ILFJA058.
//			vo = this.getEstadosDummy(periodo, idProc);
//			
//			
//			//Carga estado proceso.
//			if(vo.getNepret() != 0){
//				log.info("Existen #"+vo.getNepret()+" registros con error en el " +
//					"archivo LIEXP.ILFJA058.");
//				
//				if(vo.getIdesta()==5){
//					estado.setTexto("Validado [errores=#"+vo.getNepret()+", " +
//						"procesados =#"+vo.getNrpret()+"]");
//					estado.setBoton("btnCorregir");
//					
//					listaEstado.add(estado);
//				}
//			}
//			else{
//				log.info("No existen registros con error en el archivo LIEXP.ILFJA058.");
//				
//				switch(vo.getIdesta()){
//					
//					case 6: 
//						estado.setTexto("Generando");
//						estado.setBoton("sinBoton");
//						listaEstado.add(estado);
//						break;
//					case 7:
//						estado.setTexto("Generado");
//						estado.setBoton("btnDescargar");
//						listaEstado.add(estado);
//						break;
//					default:
//						estado.setTexto("Iniciado");
//						estado.setBoton("sinBoton");
//						listaEstado.add(estado);
//						break;
//				}
//			}
//			return listaEstado;
//		}
//		catch(Exception ex){
//			log.error("Error en getProcesados() " + ex.getMessage());
//			ex.printStackTrace();
//		}
//		return null;
//	}
//	
//	
//	public ILFJA058VO getEstadosDummy(String periodo, int idProc){
//		
//		ILFJA058VO vo = null;
//		
//		try{
//			vo = new ILFJA058VO(1, 5, "201405", "201405", "10:10:50", 1000, 2, "PepeJava");
//			
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return vo;
//	}
}
