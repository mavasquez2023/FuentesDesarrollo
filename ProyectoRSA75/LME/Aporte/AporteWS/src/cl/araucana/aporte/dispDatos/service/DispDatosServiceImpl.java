package cl.araucana.aporte.dispDatos.service;

import java.rmi.RemoteException;

import cl.araucana.aporte.dispDatos.delegate.DispDatosDelegate;
import cl.araucana.aporte.dispDatos.service.vo.AfiliadoResultVO;
import cl.araucana.aporte.dispDatos.service.vo.DispDatosResultVO;
import cl.araucana.aporte.dispDatos.service.vo.ErrorResultVO;

public class DispDatosServiceImpl {

    public DispDatosResultVO obtenerInfDatos(int rut){
        //System.out.println("DispDatosServiceImpl");
        DispDatosResultVO dispDatosVO = new DispDatosResultVO();
        DispDatosDelegate dispDatosDlg = new DispDatosDelegate ();
        ErrorResultVO errorVO = new ErrorResultVO();
        AfiliadoResultVO afilVO = new AfiliadoResultVO();
        try {
            dispDatosVO = dispDatosDlg.obtenerInfDatos(rut);	
        }
        catch (Exception e) {
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }
            afilVO = null;
            dispDatosVO.setError(errorVO);
            dispDatosVO.setAfiliado(afilVO);
            e.printStackTrace();
            return dispDatosVO;
        }	
        return dispDatosVO;
    }

    public DispDatosResultVO obtenerInfDatosRemote(int rut) throws RemoteException{
        //System.out.println("DispDatosServiceImpl");
        DispDatosResultVO dispDatosVO = new DispDatosResultVO();		
        DispDatosDelegate dispDatosDlg = new DispDatosDelegate ();
        ErrorResultVO errorVO = new ErrorResultVO();
        AfiliadoResultVO afilVO = new AfiliadoResultVO();

        try {
            dispDatosVO = dispDatosDlg.obtenerInfDatosRemote(rut);
        } catch (Exception e) {
            errorVO.setCodError(-7);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error al invocacion de servicio:" + e.toString().substring(0,200));
            }
            afilVO = null;
            dispDatosVO.setError(errorVO);
            dispDatosVO.setAfiliado(afilVO);
            e.printStackTrace();
            return dispDatosVO;
        }		
        return dispDatosVO;

    }

}
