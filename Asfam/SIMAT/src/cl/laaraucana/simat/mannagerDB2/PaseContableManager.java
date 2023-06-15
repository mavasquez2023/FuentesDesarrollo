package cl.laaraucana.simat.mannagerDB2;

import java.util.List;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.PaseContableDAO;
import cl.laaraucana.simat.entidades.CuentaPaseContableVO;
import cl.laaraucana.simat.entidades.ResultadoPaseContable;
import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class PaseContableManager {
	
	ManejoFormatoNumeros utilNumeros = new ManejoFormatoNumeros();
	/**
	 * Realiza sumatoria y validación sobre importes totales y permite generar pase contable en el caso que sea permitido.
	 * @return
	 */
	public ResultadoPaseContable obtenerPaseContable(String periodo){
		ResultadoPaseContable res = new ResultadoPaseContable();
		
		if(!periodo.matches("\\d{6}")){
			res.setCodigo("5");
			res.setMensaje("Debe enviar un periodo válido");
			return res;
		}
		try {
			PaseContableDAO paseDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getPaseContableDAO();
			List<CuentaPaseContableVO> ctasPaseContable = paseDao.getCuentasPaseContable();
			long totalDebito = 0;
			long totalCredito = 0;
			
			if(ctasPaseContable==null || ctasPaseContable.size()>0){
				for (CuentaPaseContableVO cuentaPaseContableVO : ctasPaseContable) {
					if(cuentaPaseContableVO.getTipoImporte().toUpperCase().equals(CuentaPaseContableVO.TIPO_IMPORTE_CREDITO))
						totalCredito+=cuentaPaseContableVO.getMonto();
					else
						totalDebito+=cuentaPaseContableVO.getMonto();
				}
//				res.setCodigo("3");
//				res.setMensaje("Pase contable obtenido correctamente");
				if(totalDebito==totalCredito){
					res.setCodigo("3");
					res.setMensaje("Pase contable obtenido correctamente");
				}else{
					res.setCodigo("5");
					res.setMensaje("Los montos totales del importe no cuadran. Total debito: $ +"+utilNumeros.getFormatoAbsoluto(totalDebito) + ", Total crédito: $"+utilNumeros.getFormatoAbsoluto(totalCredito));
				}
			}else{
				res.setCodigo("5");
				res.setMensaje("Se produjo un error al generar el pase contable, por favor intente nuevamente");
			}
			res.setCuentasPaseContable(ctasPaseContable);
			res.setTotalCredito(totalCredito);
			res.setTotalDebito(totalDebito);
		} catch (Exception e) {
			res.setCodigo("5");
			res.setMensaje("Se produjo un error al generar el pase contable, causa: " + e.getCause());
		}
		return res;
	}
	
	/**
	 * Ingresa registros de pase contable en SIMATDTA.SMF11
	 * Llama a programa AS400 que se encarga de procesar pase contable
	 * @param periodo
	 * @return True si existia un pase contable generado para el periodo seleccionado, por lo tanto, fue borrado y reingresado
	 * @throws Exception 
	 */
	public boolean procesarPaseContable(String periodo, List<CuentaPaseContableVO> ctasPaseContable) throws Exception{
		boolean res = false;
		PaseContableDAO paseDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getPaseContableDAO();
		//List<CuentaPaseContableVO> ctasPaseContable = paseDao.getCuentasPaseContable();
			//Ingresar registros a la base de datos e invocar al programa AS400
		int cant = paseDao.borrarPaseContable(periodo);
		paseDao.ingresarPaseContable(ctasPaseContable, periodo);
		paseDao.crearPaseContableAs400(periodo);
		
		if(cant>0){
			res = true;
		}
		return res;
	}
}
