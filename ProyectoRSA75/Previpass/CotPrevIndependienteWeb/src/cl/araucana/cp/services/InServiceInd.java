package cl.araucana.cp.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.services.vo.ErrorResultVO;

public class InServiceInd{

	
	private static Logger log = Logger.getLogger(InServiceInd.class);
	
		public ErrorResultVO creaIndependiente(int rut,String dv,String apellidoPaterno, String apellidoMaterno, String nombres, int genero,
						int codigoActividadEconomica,String tipoDireccion,String direccion, String numero,String dpto,
						int idComuna, String email, String telefono, String fax, String celular){
			
			ErrorResultVO errorResultVO = new ErrorResultVO(); 
			String respuesta ="";
			try{
				Session session = HibernateUtil.getSession();
				
				log.info("Web Services Crear Independiente a  :"+
						"Rut: "+rut+"\n"+
						"Dv: "+dv+"\n"+
						"ApellidoPaterno: "+apellidoPaterno.toUpperCase()+"\n"+
						"ApellidoMaterno: "+apellidoMaterno.toUpperCase()+"\n"+
						"Nombres: "+nombres.toUpperCase()+"\n"+
						"Numero: "+numero+"\n"+
						"Direccion: "+direccion+"\n"+
						"Depto: "+dpto+"\n"+
						"Telefono: "+telefono+"\n"+
						"Fax: "+fax+"\n"+
						"Celular: "+Integer.parseInt(celular)+"\n"+
						"IdComuna: "+idComuna+"\n"+
						"Email: "+email+"\n");
				
				PersonaVO personaVO = new PersonaVO();
				
				personaVO.setRut(""+rut);
				personaVO.setApellidoPaterno(apellidoPaterno.toUpperCase());
				personaVO.setApellidoMaterno(apellidoMaterno.toUpperCase());
				personaVO.setNombres(nombres.toUpperCase());
				personaVO.setNumero(numero);
				personaVO.setDireccion(direccion);
				personaVO.setDpto(dpto);
				personaVO.setTelefono(telefono);
				personaVO.setFax(fax);
				personaVO.setCelular(Integer.parseInt(celular));
				personaVO.setIdComuna(new Integer(idComuna));
				personaVO.setEmail(email);
				
				ProcesoMgr procesoMgr = new ProcesoMgr(session);
				
				respuesta = procesoMgr.creaIndependiente(personaVO, genero, codigoActividadEconomica, tipoDireccion,session);
				
				errorResultVO.setCodigo(0);
				errorResultVO.setDescripcion(respuesta);
				
			}catch (Exception e) {
				log.error("Se produjo una excepcion en InputServiceIndependiente.creaIndependiente", e);
				errorResultVO.setCodigo(1);
				errorResultVO.setDescripcion("Se produjo en error al crear el Independiente " +e.getMessage());
			}
			
			return errorResultVO;
			}
	}
