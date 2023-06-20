package cl.laaraucana.pagoenexceso.manager;

import org.apache.log4j.Logger;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.pagoenexceso.persistencia.DAOFactory;
import cl.laaraucana.pagoenexceso.persistencia.dao.impl.AfiliadoDao;
import cl.laaraucana.pagoenexceso.persistencia.dao.impl.PagoEnExcesoDao;
import cl.laaraucana.pagoenexceso.persistencia.vo.ConsultaPagoEnExcesoOut;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExceso;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExcesoDTO;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExcesoListDTO;
import cl.laaraucana.util.RutUtil;

public class PagoEnExcesoMGR {
	Logger log = Logger.getLogger(this.getClass());
	
	public static void main(String[] args) {
		PagoEnExcesoMGR mgr = new PagoEnExcesoMGR();
		ConsultaPagoEnExcesoOut salida = mgr.consultarPagoEnExceso("61533000-0");
		
		System.out.println("nobmre: " + salida.getPagoEnExceso().getNombreCompleto());
		System.out.println("monto: " + salida.getPagoEnExceso().getMonto());
		System.out.println("fecha: " + salida.getPagoEnExceso().getFechaCreacion());
		System.out.println("tipo: " + salida.getPagoEnExceso().getTipo());
		System.out.println(salida.getMensaje());
	}
	
	public ConsultaPagoEnExcesoOut consultarPagoEnExceso(String rut){
		ConsultaPagoEnExcesoOut salida = new ConsultaPagoEnExcesoOut();
		if(!RutUtil.IsRutValido(rut)){
			salida.setCodRespuesta("5");
			salida.setMensaje("Ingrese un rut valido");
			return salida;
		}
		
		try {
			String rutNumeric = rut.split("-")[0];
			PagoEnExcesoDTO pagoEnExceso = getPagoEnExceso(rutNumeric);
			
			if(pagoEnExceso ==null || pagoEnExceso.getMonto()==0 || pagoEnExceso.getFechaCreacion()==null){
				salida.setCodRespuesta("1");
				salida.setMensaje("Rut ingresado no registra devoluciones de pagos en exceso");
			}else{
				String nombreCompleto = getNombreCompleto(pagoEnExceso.getTipo(), rutNumeric);
				pagoEnExceso.setNombreCompleto(nombreCompleto);
				pagoEnExceso.setRut(rut);
				salida.setCodRespuesta("3");
				salida.setMensaje("Pago en exceso obtenido correctamente");
			}
			
			salida.setPagoEnExceso(pagoEnExceso);
		} catch (Exception e) {
			log.error(e);
			salida.setCodRespuesta("5");
			salida.setMensaje("Error al consultar pago en exceso: " + e.getCause());
		}
		
		return salida;
	}
	
	/**
	 * Consulta por pago en exceso asociado al rut
	 * @param rut
	 * @return
	 * @throws DaoException 
	 */
	public PagoEnExcesoDTO getPagoEnExceso(String rut) throws DaoException{
		PagoEnExcesoDao pagoDao = (PagoEnExcesoDao) DAOFactory.getDaoFactory().getPagoEnExcesoDao();
		PagoEnExcesoListDTO listaPagos = new PagoEnExcesoListDTO();
		PagoEnExcesoDTO pago = null;
		
			//consulta pago en exceso afiliado
			PagoEnExceso pagoVO = pagoDao.obtenerPagoEnExcesoAfiPen(rut);
			if( pagoVO != null){
				if(pagoVO.getFecha() !=null && pagoVO.getMonto()!=null && Integer.parseInt(pagoVO.getMonto())>0){
					pago = new PagoEnExcesoDTO(pagoVO.getFecha(), Integer.parseInt(pagoVO.getMonto()), rut , 1);
					listaPagos.addPago(pago);
				}else{
					if(pago==null){
						pagoVO = pagoDao.obtenerPagoEnExcesoEmpresa(rut);
						if( pagoVO != null){
							if(pagoVO.getFecha() !=null && pagoVO.getMonto()!=null && Integer.parseInt(pagoVO.getMonto())>0){
								pago = new PagoEnExcesoDTO(pagoVO.getFecha(), Integer.parseInt(pagoVO.getMonto()), rut , 2);
								listaPagos.addPago(pago);
							}
						}
					}
					if(pago==null){
						pagoVO = pagoDao.obtenerPagoEnExcesoEntPag(rut);
						if( pagoVO != null){
							if(pagoVO.getFecha() !=null && pagoVO.getMonto()!=null && Integer.parseInt(pagoVO.getMonto())>0){
								pago = new PagoEnExcesoDTO(pagoVO.getFecha(), Integer.parseInt(pagoVO.getMonto()), rut , 3);
								listaPagos.addPago(pago);
							}else{
								return null;
							}
						}else{
							return null;
						}
					}
				}
			}
		return listaPagos.getPagoTotal();
	}
	
	
	/**
	 * Obtiene el nombre del afiliado segun el tipo:
	 * 1: afiliado o pensionado
	 * 2: Empresa
	 * 3: entidad pagadora
	 * @param tipoPago
	 * @param rut
	 * @return
	 * @throws DaoException 
	 */
	public String getNombreCompleto(int tipoPago, String rut) throws DaoException{
		AfiliadoDao afiDao = (AfiliadoDao) DAOFactory.getDaoFactory().getAfiliadoDao();
		String nombre = null;
			switch (tipoPago) {
			case 1:
				nombre = afiDao.obtenerNombreAfiliado(rut);
				if(nombre ==null || nombre.trim().equals(""))
					nombre = afiDao.obtenerNombrePensionado(rut);
				log.debug("Consulta nombre persona");
				break;
			case 2:
				nombre = afiDao.obtenerNombreEmpresa(rut);
				if(nombre ==null || nombre.trim().equals(""))
					nombre = afiDao.obtenerNombreEmpresaCMDTA(rut);
				log.debug("Consulta nombre empresa");
				break;
			case 3:
				nombre = afiDao.obtenerNombreEntidadPagadora(rut);
				log.debug("Consulta nombre entidad pagadora");
			}


		return nombre;
	}
}
