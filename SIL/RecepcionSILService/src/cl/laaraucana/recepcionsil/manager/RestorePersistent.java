package cl.laaraucana.recepcionsil.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.ibm.as400.access.AS400JDBCBlobLocator;
import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCConnectionPoolDataSource;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.recepcionsil.persistencia.DAOFactory;
import cl.laaraucana.recepcionsil.persistencia.dto.Ilfsering;
import cl.laaraucana.recepcionsil.service.RecepcionSILService;
import cl.laaraucana.recepcionsil.service.vo.CamposXmlVO;
import cl.laaraucana.recepcionsil.service.vo.EntradaRecepcionSILVO;
import cl.laaraucana.recepcionsil.service.vo.HijoVO;
import cl.laaraucana.recepcionsil.service.vo.Ilf1100VO;
import cl.laaraucana.recepcionsil.service.vo.Ilf8600VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.IlfeOpeVO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.MixtaValidacionVO;
import cl.laaraucana.recepcionsil.service.vo.PersistVO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionVO;
import cl.laaraucana.recepcionsil.service.vo.RemuneracionesVO;
import cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO;
import cl.laaraucana.util.FechaUtil;
import cl.laaraucana.util.Util;
import cl.laaraucana.util.objectvalidate.ValidaObject;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;

public class RestorePersistent {
	Logger log = Logger.getLogger(this.getClass());
	
		
	public boolean processIlfPersist() {
		
		
			boolean resultado= false;
			//ByteArrayInputStream bis=null;
			ObjectInput in = null;
			try {
				int estado= 0;
				log.info("Cronta, buscando registros en ILFPesist");
				List persistentList= DAOFactory.getDaoFactory().getServIngresoDao().getIlfPersist(estado);
				log.info("Cantidad registros:" + persistentList.size());
				for (Iterator iterator = persistentList.iterator(); iterator.hasNext();) {
					PersistVO licpers = (PersistVO) iterator.next();
					String afirut=  licpers.getAfirut();
					int numimpre=  licpers.getNumimpre().intValue();
					byte[] data=licpers.getData();
					
					InputStream istream= new ByteArrayInputStream(data);
					ObjectInput oin = new ObjectInputStream(istream);
					LicenciaNivel2VO licN2 = (LicenciaNivel2VO)oin.readObject();
					
					
					HashMap param= new HashMap();
					param.put("rutAfiliadoFull", afirut);
					param.put("nroLicencia", String.valueOf(numimpre));
					log.info("Verificando si ya está procesado Servicio 1 en ILFSering");
					log.info("Afirut=" + afirut + ", Nro Licencia=" + numimpre);
					HashMap<String, BigDecimal> seringMap= DAOFactory.getDaoFactory().getServIngresoDao().getIlfSering(param);
					if(seringMap!= null){
						RecepcionSILService recepcion= new RecepcionSILService();
						log.info("Invocando completarLicencia para procesar registro Servicio 2" );
						SalidaRecepcionSILVO salida= recepcion.completarLicencia(licN2);
						if(salida.getRespuesta().getCodigoRespuesta().equals("3")){
							log.info("Proceso exitoso, actualizando ILFPersist");
							DAOFactory.getDaoFactory().getServIngresoDao().updateIlfPersist(param);
						}
					}
				}
				
				
				resultado= true;
				//se guarda objeto 
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Error:" + e.getMessage());
			}
		return resultado;	
	}
	
	
}
