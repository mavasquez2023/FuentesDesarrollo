package cl.araucana.aporte.orqInput.service;

import java.rmi.RemoteException;

import cl.araucana.aporte.orqInput.delegate.OrqInputDelegate;
import cl.araucana.aporte.orqInput.service.vo.AporteResultVO;
import cl.araucana.aporte.orqInput.service.vo.CreditoResultVO;
import cl.araucana.aporte.orqInput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqInput.service.vo.LeasingResultVO;
import cl.araucana.aporte.orqInput.service.vo.OrqInputResultVO;


public class OrqInputServiceImpl {	

    public OrqInputResultVO obtenerInfoPago(int rut) {
        //System.out.println("OrqInputResultVO");
        OrqInputResultVO orqInputVO = new OrqInputResultVO();
        OrqInputDelegate orqInputDlg = new OrqInputDelegate();
        ErrorResultVO errorVO = new ErrorResultVO();
        AporteResultVO aporteVO = new AporteResultVO();
        CreditoResultVO creditoVO = new CreditoResultVO();		
        LeasingResultVO leasingVO = new LeasingResultVO();
        try{
            orqInputVO = orqInputDlg.obtenerInfoPago(rut);
        }catch (Exception e) {
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }
            orqInputVO.setErrorVO(errorVO);
            orqInputVO.setAporteVO(aporteVO);
            orqInputVO.setCreditoVO(creditoVO);
            orqInputVO.setLeasingVO(leasingVO);
            e.printStackTrace();
            return orqInputVO;
        }
        return orqInputVO;
    }

    public OrqInputResultVO obtenerInfoPagoRemote(int rut) throws RemoteException{
        //System.out.println("OrqInputResultVO");
        OrqInputResultVO orqInputVO = new OrqInputResultVO();
        OrqInputDelegate orqInputDlg = new OrqInputDelegate();
        ErrorResultVO errorVO = new ErrorResultVO();
        AporteResultVO aporteVO = new AporteResultVO();
        CreditoResultVO creditoVO = new CreditoResultVO();		
        LeasingResultVO leasingVO = new LeasingResultVO();
        try {
            orqInputVO = orqInputDlg.obtenerInfoPagoRemote(rut);
        } catch (Exception e) {
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }		
            orqInputVO.setErrorVO(errorVO);
            orqInputVO.setAporteVO(aporteVO);
            orqInputVO.setCreditoVO(creditoVO);
            orqInputVO.setLeasingVO(leasingVO);
            e.printStackTrace();
            return orqInputVO;
        }
        return orqInputVO;
    }
}
