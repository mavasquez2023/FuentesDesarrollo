package cl.araucana.aporte.orqOutput.delegate;

import cl.araucana.aporte.orqOutput.bo.ErrorResultBO;
import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote;
import cl.araucana.aporte.orqOutput.locator.OrqOutputLocator;
import cl.araucana.aporte.orqOutput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqOutput.service.vo.OrqOutputResultVO;


public class OrqOutputDelegate {
    public OrqOutputResultVO recuperacionPago(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputResultBO orqOutputBO = new OrqOutputResultBO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();

        try{
            OrqOutputLocal orqOutputLocal = OrqOutputLocator.getEjbSample();
            orqOutputBO = orqOutputLocal.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        }catch(Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);
            return orqOutputVO;
        }

        errorBO = orqOutputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        orqOutputVO.setErrorVO(errorVO);
        return orqOutputVO;
    }

    public OrqOutputResultVO recuperacionPagoRemote (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){
        OrqOutputResultVO orqOutputVO = new OrqOutputResultVO();
        OrqOutputResultBO orqOutputBO = new OrqOutputResultBO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();

        try{
            OrqOutputRemote orqOutputRemote = OrqOutputLocator.getEjbSampleRemote();
            orqOutputBO = orqOutputRemote.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
        }catch(Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqOutputVO.setErrorVO(errorVO);
            return orqOutputVO;
        }

        errorBO = orqOutputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        orqOutputVO.setErrorVO(errorVO);
        return orqOutputVO;
    }
}
