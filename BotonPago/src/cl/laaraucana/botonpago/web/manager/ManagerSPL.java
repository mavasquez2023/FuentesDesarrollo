package cl.laaraucana.botonpago.web.manager;

import java.util.ArrayList;

import cl.laaraucana.botonpago.web.database.dao.SplDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Convenio;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Detpago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Mediopago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Pago;
import cl.laaraucana.botonpago.web.database.vo.SalidaSPL;

public class ManagerSPL {

	public static Pago getInfoPago(String IdPago) throws Exception {
		SplDAO splDao = new SplDAO();
		return splDao.getInfoPago(IdPago);
	}

	public static ArrayList<Detpago> getInfoDetallePago(String IdPago) throws Exception {
		SplDAO splDao = new SplDAO();
		return splDao.getInfoDetallePago(IdPago);
	}

	public static Convenio getConvenioById(String convenioId) throws Exception {
		SplDAO splDao = new SplDAO();
		return splDao.getConvenioById(convenioId);
	}

	public static Mediopago getMedioPagoById(String medioPagoId) throws Exception {
		SplDAO splDao = new SplDAO();
		return splDao.getMedioPagoById(medioPagoId);
	}

	public static SalidaSPL getFlujoSPL(String IdPago) throws Exception {
		SalidaSPL salida = new SalidaSPL();
		Pago pago = getInfoPago(IdPago);
		if(pago!=null){
			ArrayList<Detpago> detPago = getInfoDetallePago(IdPago);
			Convenio convenio = getConvenioById(pago.getIdConvenio());
			Mediopago medioPago = getMedioPagoById(convenio.getIdMediopago());
			salida.setPago(pago);
			salida.setDetPago(detPago);
			salida.setConvenio(convenio);
			salida.setMedioPago(medioPago);
			return salida;
		}else{
			return null;
		}
		
	}

}
