package cl.araucana.aporte.orqOutput.service;

import java.rmi.RemoteException;

import cl.araucana.aporte.orqOutput.delegate.OrqOutputDelegate;
import cl.araucana.aporte.orqOutput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqOutput.service.vo.OrqOutputResultVO;

public class OrqOutputServiceImpl {
    public OrqOutputResultVO recuperacionPago (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago, String ofi_folio){
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputDelegate orqOutputDlg = new OrqOutputDelegate();
        ErrorResultVO errorVO = new ErrorResultVO();
        try{
            orqOutputVO = orqOutputDlg.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago, ofi_folio);
        }catch (Exception e) {
            e.printStackTrace();
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);			
            return orqOutputVO;
        }
        return orqOutputVO;
    }

    public OrqOutputResultVO recuperacionPagoRemote (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago, String ofi_folio)throws RemoteException{
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputDelegate orqOutputDlg = new OrqOutputDelegate();
        ErrorResultVO errorVO = new ErrorResultVO();
        try {
            orqOutputVO = orqOutputDlg.recuperacionPagoRemote(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        } catch (Exception e) {
            e.printStackTrace();
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);			
            return orqOutputVO;
        }		
        return orqOutputVO;
    }

}
