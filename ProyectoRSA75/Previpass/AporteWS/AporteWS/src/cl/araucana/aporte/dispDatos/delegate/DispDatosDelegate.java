package cl.araucana.aporte.dispDatos.delegate;

import cl.araucana.aporte.core.RemoteEJBLocatorException;
import cl.araucana.aporte.dispDatos.bo.AfiliadoResultBO;
import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;
import cl.araucana.aporte.dispDatos.bo.ErrorResultBO;
import cl.araucana.aporte.dispDatos.ejb.DispDatosLocal;
import cl.araucana.aporte.dispDatos.ejb.DispDatosRemote;
import cl.araucana.aporte.dispDatos.locator.DispDatosLocator;
import cl.araucana.aporte.dispDatos.service.vo.AfiliadoResultVO;
import cl.araucana.aporte.dispDatos.service.vo.DispDatosResultVO;
import cl.araucana.aporte.dispDatos.service.vo.ErrorResultVO;

public class DispDatosDelegate {

    public DispDatosResultVO obtenerInfDatos(int rut){
        //System.out.println("DispDatosDelegate");

        DispDatosResultBO dispDatosBO = new DispDatosResultBO ();
        DispDatosResultVO dispDatosVO = new DispDatosResultVO ();

        ErrorResultVO errorVO = new ErrorResultVO ();
        ErrorResultBO errorBO = new ErrorResultBO ();

        AfiliadoResultVO afilVO = new AfiliadoResultVO();
        AfiliadoResultBO afilBO = new AfiliadoResultBO();

        try {
            DispDatosLocal dispDatosLocal = DispDatosLocator.getEjbSample();
            dispDatosBO = dispDatosLocal.obtenerInfoDatos(rut);
        }catch (Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            afilVO = null;
            dispDatosVO.setError(errorVO);
            dispDatosVO.setAfiliado(afilVO);
            return dispDatosVO;
        }

        errorBO = dispDatosBO.getError();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        afilBO = dispDatosBO.getAfiliado();	
        if (afilBO != null){				
            afilVO.setRut(afilBO.getRut());
            afilVO.setDlgVerificador(afilBO.getDlgVerificador());
            if (afilBO.getApellidoPaterno().length() > 40){
                afilVO.setApellidoPaterno(afilBO.getApellidoPaterno().substring(0, 40));
            }
            else{
                afilVO.setApellidoPaterno(afilBO.getApellidoPaterno());
            }
            if (afilBO.getApellidoMaterno().length() > 40){
                afilVO.setApellidoMaterno(afilBO.getApellidoMaterno().substring(0,40));
            }
            else{
                afilVO.setApellidoMaterno(afilBO.getApellidoMaterno());
            }
            if(afilBO.getNombres().length() > 40){
                afilVO.setNombres(afilBO.getNombres().substring(0,40));
            }
            else{
                afilVO.setNombres(afilBO.getNombres());
            }
            afilVO.setGenero(afilBO.getGenero());
            afilVO.setActEconomica(afilBO.getActEconomica());
            afilVO.setTipoDireccion(afilBO.getTipoDireccion());
            afilVO.setDireccion(afilBO.getDireccion());
            afilVO.setNumero(afilBO.getNumero());
            afilVO.setDepartamento(afilBO.getDepartamento());
            afilVO.setComuna(afilBO.getComuna());
            afilVO.setMail(afilBO.getMail());
            afilVO.setTelefono(afilBO.getTelefono());
            afilVO.setFax(afilBO.getFax());
            afilVO.setCelular(afilBO.getCelular());		
        }

        dispDatosVO.setError(errorVO);
        dispDatosVO.setAfiliado(afilVO);
        return dispDatosVO;
    }


    public DispDatosResultVO obtenerInfDatosRemote(int rut)throws RemoteEJBLocatorException{
        //System.out.println("DispDatosDelegate");

        DispDatosResultBO dispDatosBO = new DispDatosResultBO ();
        DispDatosResultVO dispDatosVO = new DispDatosResultVO ();

        ErrorResultVO errorVO = new ErrorResultVO ();
        ErrorResultBO errorBO = new ErrorResultBO ();

        AfiliadoResultVO afilVO = new AfiliadoResultVO();
        AfiliadoResultBO afilBO = new AfiliadoResultBO();

        try {
            DispDatosRemote dispDatosRemote = DispDatosLocator.getEjbSampleRemote();
            dispDatosBO = dispDatosRemote.obtenerInfoDatos(rut);
        }catch (Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            afilVO = null;
            dispDatosVO.setError(errorVO);
            dispDatosVO.setAfiliado(afilVO);
            return dispDatosVO;
        }

        errorBO = dispDatosBO.getError();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        afilBO = dispDatosBO.getAfiliado();	
        if (afilBO != null){				
            afilVO.setRut(afilBO.getRut());
            afilVO.setDlgVerificador(afilBO.getDlgVerificador());
            if (afilBO.getApellidoPaterno().length() > 40){
                afilVO.setApellidoPaterno(afilBO.getApellidoPaterno().substring(0, 40));
            }
            else{
                afilVO.setApellidoPaterno(afilBO.getApellidoPaterno());
            }
            if (afilBO.getApellidoMaterno().length() > 40){
                afilVO.setApellidoMaterno(afilBO.getApellidoMaterno().substring(0,40));
            }
            else{
                afilVO.setApellidoMaterno(afilBO.getApellidoMaterno());
            }
            if(afilBO.getNombres().length() > 40){
                afilVO.setNombres(afilBO.getNombres().substring(0,40));
            }
            else{
                afilVO.setNombres(afilBO.getNombres());
            }
            afilVO.setGenero(afilBO.getGenero());
            afilVO.setActEconomica(afilBO.getActEconomica());
            afilVO.setTipoDireccion(afilBO.getTipoDireccion());
            afilVO.setDireccion(afilBO.getDireccion());
            afilVO.setNumero(afilBO.getNumero());
            afilVO.setDepartamento(afilBO.getDepartamento());
            afilVO.setComuna(afilBO.getComuna());
            afilVO.setMail(afilBO.getMail());
            afilVO.setTelefono(afilBO.getTelefono());
            afilVO.setFax(afilBO.getFax());
            afilVO.setCelular(afilBO.getCelular());		
        }

        dispDatosVO.setError(errorVO);
        dispDatosVO.setAfiliado(afilVO);
        return dispDatosVO;
    }
}
