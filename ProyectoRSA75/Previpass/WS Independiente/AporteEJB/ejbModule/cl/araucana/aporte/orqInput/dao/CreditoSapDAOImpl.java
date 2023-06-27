package cl.araucana.aporte.orqInput.dao;

import java.util.ResourceBundle;

import sun.security.util.Resources;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoDetalleBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;

public class CreditoSapDAOImpl {

	public static ResourceBundle datosDummy = Resources.getBundle("cl.araucana.aporte.config.DatosDummy");
	
    public static CreditoCallBO obtenerCredito (int rut){
    	CreditoCallBO salida = new CreditoCallBO();
    	try {
    		String rutDummy = datosDummy.getString("rutAfiliado");
			long rutDummyLong = Long.parseLong(rutDummy);
			int total = 0;
			if(rutDummyLong == rut){
				CreditoResultBO credito = new CreditoResultBO();
				int cantRegistros = Integer.parseInt(datosDummy.getString("numRegistros"));
				CreditoDetalleBO[] cuotas = new CreditoDetalleBO[cantRegistros];
				for (int i = 0; i < cantRegistros; i++) {
					CreditoDetalleBO detalleAux= new CreditoDetalleBO();
					detalleAux.setCodigoOficina(Integer.parseInt(datosDummy.getString(i + ".codigoOficina")));
					detalleAux.setFolioCredito(Integer.parseInt(datosDummy.getString("folioCredito")));
					detalleAux.setNumCuota(Integer.parseInt(datosDummy.getString(i + ".nroCuota")));
					detalleAux.setFechaVencimiento(Integer.parseInt(datosDummy.getString(i + ".fechaVencimiento")));
					detalleAux.setCapital(Integer.parseInt(datosDummy.getString(i + ".capital")));
					detalleAux.setIntereses(Integer.parseInt(datosDummy.getString(i + ".interes")));
					detalleAux.setSeguros(Integer.parseInt(datosDummy.getString(i + ".seguro")));
					detalleAux.setValorCouta(Integer.parseInt(datosDummy.getString(i + ".valorCuota")));
					detalleAux.setEstadoCouta(Integer.parseInt(datosDummy.getString(i + ".estadoCouta")));
					detalleAux.setLineaCredito(Integer.parseInt(datosDummy.getString(i + ".lineaCredito")));
					detalleAux.setMontoAbonado(Integer.parseInt(datosDummy.getString(i + ".montoAbonado")));
					detalleAux.setGravamenes(Integer.parseInt(datosDummy.getString(i + ".gravamenes")));
					detalleAux.setMultas(Integer.parseInt(datosDummy.getString(i + ".multas")));
					detalleAux.setMontoDescuento(Integer.parseInt(datosDummy.getString(i + ".montoDescuento")));
					detalleAux.setTotalCoutas(Integer.parseInt(datosDummy.getString(i + ".totalCoutas")));
					
					total += detalleAux.getValorCouta();
					cuotas[i] = detalleAux;
				}
				credito.setCreditoDetalle(cuotas);
				credito.setMonto(total);
				credito.setNumRegistros(cantRegistros);
				
				salida.setCodError(0);
				salida.setGlsError("Crédito obtenido OK");
				salida.setCredito(credito);
				return salida;
			}else{
				return null;
			}
    		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
