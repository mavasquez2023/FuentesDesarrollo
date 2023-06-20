package cl.laaraucana.rendicionpagonomina.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ibatis.dao.MandatoAS400Dao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.MandatoAS400DaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.RechazoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.RechazoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.RechazoVo;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.servicio.mandato.AUTENTICACION;
import cl.laaraucana.servicio.mandato.CLIENTE;
import cl.laaraucana.servicio.mandato.LOG_RESPUESTA;
import cl.laaraucana.servicio.mandato.MandatoServicioImpl;
import cl.laaraucana.servicio.mandato.MandatoServicioPortBindingStub;
import cl.laaraucana.servicio.mandato.REVOCAR;
import cl.laaraucana.servicio.mandato.RespuestaInsertMandato;

@Service
public class MandatoAS400ServiceImpl implements MandatoAS400Service {
	
	private static final Logger logger = Logger.getLogger(MandatoAS400ServiceImpl.class);
	
	private MandatoAS400Dao dao = new MandatoAS400DaoImpl();
	
	private RechazoDao rechazoDAO= new RechazoDaoImpl();
	
	@Override
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaMandatos(rut);
	}

	@Override
	public int revocarMandato(String tipoNomina, String codigoRechazo, int rut,
			int codbanco, long cuenta) throws Exception {
		//1 exito al revocar mandato
		//0 n cumple condición para revocar
		//-1 error al revocar mandato
		int salida=0;
		
		String anulaMandato="";
		String descripcionRechazo="";
		//Se valida si cdigo rechazo debe anular mandato
		logger.info("Validando código rechazo mandato" + codigoRechazo + " tipoNomina: " + tipoNomina);
		HashMap<String, String> anula= null;
		if(tipoNomina.equals("PNOL")){
			anula= rechazoDAO.consultaRechazoBCI_PNOL(codigoRechazo);
		}else if(tipoNomina.equals("24HRS")){
			anula= rechazoDAO.consultaRechazoBCI_24HRS(codigoRechazo);
		}else if(tipoNomina.equals("BES")){
			anula= rechazoDAO.consultaRechazoBES(codigoRechazo);
		}
		if(anula!=null){
			anulaMandato= anula.get("AnulaMandato");
			descripcionRechazo= anula.get("DescripcionRechazo");
		}
		if(anulaMandato.equals("S")){
			//Se bueca mandato asociado al Rut
			logger.info("consultado mandato para Rut:" + rut);
			List<MandatoAS400Vo> mandatos= consultaMandatos(rut);
			if(mandatos!= null && mandatos.size()>0){
				//debe haber un solo mandato vigente
				MandatoAS400Vo mandato= mandatos.get(0);
				
				//se valida que no haya cambiado banco y cuenta en mandato para revocar
				if(mandato.getCodbanco()== codbanco && Long.parseLong(mandato.getNumcuenta().trim())== cuenta){
					RechazoVo rechazo= new RechazoVo();

					rechazo.setRutAfiliado((int)mandato.getRutafi());
					rechazo.setDvAfiliado(mandato.getDvafi());
					rechazo.setCodigoBanco(mandato.getCodbanco());
					rechazo.setNumeroCuenta(mandato.getNumcuenta());
					rechazo.setMotivoRechazo(descripcionRechazo);
					rechazo.setUsuario(tipoNomina);
					rechazo.setEstado(0);

					logger.info("Se llena tabla de Mandato Rechazado, motivo:" + descripcionRechazo);
					try {
						int del= dao.deleteMandatoRechazado(mandato.getRutafi());
						logger.info("se han borrado " + del + " mandato rechazado previo" );
						dao.insertMandatoRechazado(rechazo);
						logger.info("insert Mandato Rechazado OK");
					} catch (Exception e) {
						logger.warn("Error al insertar Rechazo mandato Rut " + mandato.getRutafi() + ", mensaje=" + e.getMessage());
						e.printStackTrace();
					}
					
					/*String ep= Configuraciones.getConfig("ep.mandato");
					String usuario= Configuraciones.getConfig("ep.mandato.usuario");
					String clave= Configuraciones.getConfig("ep.mandato.clave");
					
					logger.info("llamando WS de mandato para Revocar mandato:");
					MandatoServicioPortBindingStub mandatoService= new MandatoServicioPortBindingStub();
					mandatoService._setProperty(MandatoServicioPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
				//	mandatoService.setUsername(usuario);
				//	mandatoService.setPassword(clave);
					AUTENTICACION autenticacion= new AUTENTICACION(usuario, clave);
					REVOCAR cliente= new REVOCAR();
					cliente.setRUT(Integer.parseInt(rut.split("-")[0]));
					cliente.setDV(rut.split("-")[1]);
					cliente.setEMAIL(mandato.getEmail());
					cliente.setNOMBRE(mandato.getNombre());
					cliente.setMENSAJE(descripcionRechazo);
					RespuestaInsertMandato respuesta= mandatoService.revocarMandato(autenticacion, cliente);
					LOG_RESPUESTA log= respuesta.getLOG_RESPUESTA();
					
					logger.info("Respuesta WS Mandato:" + log.getCODIGO());
					
					if(log.getCODIGO()==1){
						salida=1;
					}else{
						salida=-1;
					}
					*/
				}else{
					logger.info("Banco o cuenta no coinciden con mandato, no se revoca");
				}
			}
		}
		return salida;
	}


}
