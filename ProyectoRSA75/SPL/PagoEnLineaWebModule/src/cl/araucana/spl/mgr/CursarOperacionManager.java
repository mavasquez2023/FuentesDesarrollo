package cl.araucana.spl.mgr;

import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.ComprobanteTesoreria;
import cl.araucana.spl.beans.DetallePago;
import cl.araucana.spl.beans.EntradaLibroBanco;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.dao.LibroBancoDAO;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.TesoreriaDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.util.Constantes;
import cl.araucana.spl.util.FechaUtil;
import cl.araucana.spl.util.MailSender;

import com.ibatis.dao.client.DaoManager;

public class CursarOperacionManager {
	private static final Logger logger = Logger.getLogger(CursarOperacionManager.class);
	private TesoreriaDAO tesDao;
	private LibroBancoDAO libroBcoDao;
	private PagoDAO pagoDao;
	
//	public static void main(String[] args) throws Exception {
//		boolean pagado = true;
//		if(pagado){
//			PagoBbvManager mgr = new PagoBbvManager();
//			TransaccionBbv trxBD = mgr.getTransaccionByCodigoIdTrx("620");
//			
//			System.out.println("Respuesta de la consulta: " + trxBD);
//			
//			CursarOperacionManager curse = new CursarOperacionManager();
//			curse.cursarPago(trxBD.getPago(), Constants.MEDIO_CODIGO_BBV);
//		}else{
//			logger.warn("El resultado del pago es NOK");
//		}
//	}
	
	/**
	 * Cursa comprobantes de tesoreria asociados al pago que se está notificando
	 * Utiliza el codigo de medio de pago para determinar si el banco trabaja de forma online o batch
	 * y realizar el registro en el libro de banco en el caso que corresponda (online)
	 * @param trx
	 * @throws Exception 
	 */
	public void cursarPago(Pago pago, String medioPago) throws Exception{
		DaoManager mgr = DaoConfig.getDaoManager();
		int estCurseCompTes = 0;
		int estRegistroLibroBco = 0;
		
		//Obtener comprobantes de tesoreria asociados al numero de pago
		pagoDao = (PagoDAO) mgr.getDao(PagoDAO.class);
		List detallePago = pagoDao.findFoliosByPago(pago.getId());
		
		//Actualizar los comprobantes asociados al pago
		tesDao = (TesoreriaDAO)mgr.getDao(TesoreriaDAO.class);
		ComprobanteTesoreria entradaTes = new ComprobanteTesoreria();
		entradaTes.setTipoPago(Constantes.getInstancia().TIPO_PAGO_TES);
		entradaTes.setUsuario(Constantes.getInstancia().USUARIO_TES);
		entradaTes.setEstMovimiento(Constantes.getInstancia().EST_MOV_TES);
		int cant=0;
		for (int i = 0; i < detallePago.size(); i++) {
			DetallePago registro = (DetallePago) detallePago.get(i);
			entradaTes.setFolioTes(registro.getFolio().toString());
			try {
				if(tesDao.actualizarComprobanteTesoreria(entradaTes)) cant++;
			} catch (Exception e) {
				String m= "Se produjo una excepción al actualizar comprobante de tesorería ("+entradaTes.getFolioTes()+")";
				logger.error(m,e);
				MailSender.sendError(m,e);
			}
		}
		if(cant==detallePago.size()){
			estCurseCompTes = 1;
			logger.info("Se cursó correctamente los folios de tesorería para el pago: " + pago.getId() +  "("+cant+" folios)");
		}else{
			logger.debug("Error al cursar comprobantes de tesorería (pago "+ pago.getId() +"): se cursó " + cant + " de " + detallePago.size() + " comprobantes");
		}
		
		
		MedioPago banco = (MedioPago) Constantes.getInstancia().MEDIO_PAGO.get(medioPago);
		
		if(banco.getTipoNotificacion().equals(Constantes.getInstancia().COD_TIPO_BCO_ONLINE)){
			EntradaLibroBanco bcoEntrada = new EntradaLibroBanco();
			bcoEntrada.setDescripcion(Constantes.getInstancia().DESC_PAGO_MEDIO_ONLINE);
			bcoEntrada.setNroCuentaCorriente(pago.getConvenio().getCtaCorriente());
			bcoEntrada.setBanco(String.valueOf(banco.getCodigoBanco()));
			bcoEntrada.setFechaMovimiento(FechaUtil.getFechaHoyAs400());
			bcoEntrada.setMonto(pago.getMonto().toString());
			bcoEntrada.setNroDeposito(pago.getId().toString());
			
			boolean resBco = false;
			try {
				resBco = registrarPagoLibroBanco(bcoEntrada);
			} catch (Exception e) {
				String m = "Error al realizar registro en libro de banco para pago: " + pago.getId();
				logger.error(m,e);
				resBco = false;
				MailSender.sendError(m,e);
			}
			estRegistroLibroBco = (resBco) ? 1 : 0;
			logger.debug("Estado de registro en libro de banco para pago '" + pago.getId() + "': " + resBco);
		}
		
		//Actualizar el estado del registro
		try {
			pagoDao = (PagoDAO) mgr.getDao(PagoDAO.class);
			pago.setEstCurseCompTes(estCurseCompTes);
			pago.setEstRegistroLibroBco(estRegistroLibroBco);
			cant = pagoDao.updatePagoTrxCursada(pago);
			logger.debug("Estado de pago actualizado correctamente "+cant);
		} catch (Exception e) {
			String m = "Se produjo un error al actualizar el estado del pago ("+pago.getId()+") con el curse de tesorería y registro en libro de banco";
			logger.error(m,e);
			MailSender.sendError(m,e);
		}
	}
	
	/**
	 * Registro en libro de banco
	 * @param bcoEntrada
	 * @return
	 * @throws Exception 
	 */
	public boolean registrarPagoLibroBanco(EntradaLibroBanco bcoEntrada) throws Exception{
		boolean resBco = false;
		//Ingreso en libro de banco por el monto total del pago}
		DaoManager mgr = DaoConfig.getDaoManager();
		libroBcoDao = (LibroBancoDAO)mgr.getDao(LibroBancoDAO.class);
		bcoEntrada.setCodOperacionInterna(Constantes.getInstancia().COD_OP_INTERNA);
		bcoEntrada.setTipoAbono(Constantes.getInstancia().TIPO_ABONO);
		resBco = libroBcoDao.registrarEnLibroBanco(bcoEntrada);
		return resBco;
	}
}
