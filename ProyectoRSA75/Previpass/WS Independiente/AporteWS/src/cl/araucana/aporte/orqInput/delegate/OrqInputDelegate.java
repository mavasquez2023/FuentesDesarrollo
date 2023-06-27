package cl.araucana.aporte.orqInput.delegate;

import cl.araucana.aporte.orqInput.bo.AporteDetalleBO;
import cl.araucana.aporte.orqInput.bo.AporteResultBO;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoDetalleBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;
import cl.araucana.aporte.orqInput.bo.ErrorResultBO;
import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;
import cl.araucana.aporte.orqInput.ejb.OrqInputLocal;
import cl.araucana.aporte.orqInput.ejb.OrqInputRemote;
import cl.araucana.aporte.orqInput.locator.OrqInputLocator;
import cl.araucana.aporte.orqInput.service.vo.AporteDetalleVO;
import cl.araucana.aporte.orqInput.service.vo.AporteResultVO;
import cl.araucana.aporte.orqInput.service.vo.CreditoDetalleVO;
import cl.araucana.aporte.orqInput.service.vo.CreditoResultVO;
import cl.araucana.aporte.orqInput.service.vo.ErrorResultVO;
import cl.araucana.aporte.orqInput.service.vo.LeasingResultVO;
import cl.araucana.aporte.orqInput.service.vo.OrqInputResultVO;
import cl.araucana.core.util.Rut;
import cl.araucana.credito.business.service.CreditoService;
import cl.araucana.credito.business.service.impl.CreditoServiceImpl;

public class OrqInputDelegate {
    public OrqInputResultVO obtenerInfoPago(int rut) {
        //System.out.println("OrqInputDelegate");

        OrqInputResultBO orqInputBO = new OrqInputResultBO();
        OrqInputResultVO orqInputVO = new OrqInputResultVO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();

        AporteResultBO aporteBO = new AporteResultBO();
        AporteResultVO aporteVO = new AporteResultVO();

        CreditoResultBO creditoBO = new CreditoResultBO();
        CreditoResultVO creditoVO = new CreditoResultVO();

        LeasingResultVO leasingVO = new LeasingResultVO();

        AporteDetalleBO[] aporteDetalleBO = null;
        AporteDetalleVO[] aporteDetalleVO = null;
        AporteDetalleBO detalleBO = null;	

        CreditoDetalleBO[] creditoDetalleBO = null;
        CreditoDetalleVO[] creditoDetalleVO = null;
        CreditoDetalleBO detalleBO2 = null;
        int datosSize = 0;

        try{
            OrqInputLocal orqInputLocal = OrqInputLocator.getEjbSample();
            orqInputBO = orqInputLocal.obtenerInfoPago(rut);
            
            //Obteniendo Credito
            CreditoService credito= new CreditoServiceImpl();
            Rut rutBS= new Rut(rut);
            System.out.println(rutBS.toString());
            CreditoCallBO creditoCallBO= (CreditoCallBO)credito.getContratosByRutDeudor(rutBS.getNumber()+"-"+rutBS.getDV(), 1);
            orqInputBO.setCreditoBO(creditoCallBO.getCredito());
            
            int codOrqError= orqInputBO.getErrorBO().getCodError();
            String glosaCredError= creditoCallBO.getGlsError();
            int codCredError= creditoCallBO.getCodError();
            if(codOrqError==0 && codCredError!=0){
            	orqInputBO.getErrorBO().setCodError(codCredError);
            	if(glosaCredError.length()>130){
            		glosaCredError= glosaCredError.substring(0, 130);
            	}
            	orqInputBO.getErrorBO().setGlsError(glosaCredError);
            }else if(codOrqError!=0 && codCredError!=0){
            	orqInputBO.getErrorBO().setCodError(-7);
            	String glosa_total= orqInputBO.getErrorBO().getGlsError() + glosaCredError;
            	if(glosa_total.length()>130){
            		glosa_total= glosa_total.substring(0, 130);
            	}
            	orqInputBO.getErrorBO().setGlsError(glosa_total);
            }
            
        }catch (Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqInputVO.setErrorVO(errorVO);
            return orqInputVO;
        }
        errorBO = orqInputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        aporteBO = orqInputBO.getAporteBO();
        if (aporteBO != null){
            datosSize = aporteBO.getNumRegistros();			
            aporteVO.setMonto(aporteBO.getMonto());			
            aporteVO.setNumRegistros(datosSize);
            aporteDetalleBO = aporteBO.getAporteDetalle();
            aporteDetalleVO = new AporteDetalleVO[datosSize];

            for(int i = 0; i < datosSize; i++){
                detalleBO = aporteDetalleBO[i];
                AporteDetalleVO detalleVO = new AporteDetalleVO();
                detalleVO.setFechaVencimiento(detalleBO.getFechaVencimiento());
                detalleVO.setMonto(detalleBO.getMonto());
                detalleVO.setInteresesReajuste(detalleBO.getInteresesReajuste());
                detalleVO.setRentaPromedio(detalleBO.getRentaPromedio());
                aporteDetalleVO[i] = detalleVO;
            }			
            aporteVO.setAporteDetalle(aporteDetalleVO);
        }
        else{
            aporteVO = new AporteResultVO();
        }

        creditoBO = orqInputBO.getCreditoBO();
        if (creditoBO != null){
            datosSize = creditoBO.getNumRegistros();			
            creditoVO.setMonto(creditoBO.getMonto());			
            creditoVO.setNumRegistros(datosSize);
            creditoDetalleBO = creditoBO.getCreditoDetalle();
            creditoDetalleVO = new CreditoDetalleVO[datosSize];

            for(int i = 0; i < datosSize; i++){
                detalleBO2 = creditoDetalleBO[i];
                CreditoDetalleVO detalleVO2 = new CreditoDetalleVO();
                detalleVO2.setCodigoOficina(detalleBO2.getCodigoOficina());
                detalleVO2.setFolioCredito(detalleBO2.getFolioCredito());
                detalleVO2.setNumCuota(detalleBO2.getNumCuota());
                detalleVO2.setTotalCoutas(detalleBO2.getTotalCoutas());
                detalleVO2.setEstadoCouta(detalleBO2.getEstadoCouta());
                detalleVO2.setFechaVencimiento(detalleBO2.getFechaVencimiento());
                detalleVO2.setLineaCredito(detalleBO2.getLineaCredito());
                detalleVO2.setValorCouta(detalleBO2.getValorCouta());
                detalleVO2.setCapital(detalleBO2.getCapital());
                detalleVO2.setSeguros(detalleBO2.getSeguros());
                detalleVO2.setIntereses(detalleBO2.getIntereses());
                detalleVO2.setGravamenes(detalleBO2.getGravamenes());
                detalleVO2.setMultas(detalleBO2.getMultas());
                detalleVO2.setMontoAbonado(detalleBO2.getMontoAbonado());
                detalleVO2.setMontoDescuento(detalleBO2.getMontoDescuento());
                creditoDetalleVO[i] = detalleVO2;
            }			
            creditoVO.setCreditoDetalle(creditoDetalleVO);
        }
        else{
            creditoVO = new CreditoResultVO();
        }
        orqInputVO.setErrorVO(errorVO);
        orqInputVO.setAporteVO(aporteVO);
        orqInputVO.setCreditoVO(creditoVO);
        orqInputVO.setLeasingVO(leasingVO);
        return orqInputVO;
    }

    public OrqInputResultVO obtenerInfoPagoRemote(int rut) {
        //System.out.println("OrqInputDelegate");

        OrqInputResultBO orqInputBO = new OrqInputResultBO();
        OrqInputResultVO orqInputVO = new OrqInputResultVO();

        ErrorResultBO errorBO = new ErrorResultBO();
        ErrorResultVO errorVO = new ErrorResultVO();

        AporteResultBO aporteBO = new AporteResultBO();
        AporteResultVO aporteVO = new AporteResultVO();

        CreditoResultBO creditoBO = new CreditoResultBO();
        CreditoResultVO creditoVO = new CreditoResultVO();

        LeasingResultVO leasingVO = new LeasingResultVO();

        AporteDetalleBO[] aporteDetalleBO = null;
        AporteDetalleVO[] aporteDetalleVO = null;
        AporteDetalleBO detalleBO = null;	

        CreditoDetalleBO[] creditoDetalleBO = null;
        CreditoDetalleVO[] creditoDetalleVO = null;
        CreditoDetalleBO detalleBO2 = null;
        int datosSize = 0;

        try{
            OrqInputRemote orqInputRemote = OrqInputLocator.getEjbSampleRemote();
            orqInputBO = orqInputRemote.obtenerInfoPago(rut);
        }catch (Exception e){
            e.printStackTrace();
            errorVO.setCodError(-4);
            if (e.toString().length() < 200){
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString());
            }
            else{
                errorVO.setGlsError("Error en despliegue EJB:" + e.toString().substring(0,200));
            }
            orqInputVO.setErrorVO(errorVO);
            return orqInputVO;
        }
        errorBO = orqInputBO.getErrorBO();
        errorVO.setCodError(errorBO.getCodError());
        errorVO.setGlsError(errorBO.getGlsError());

        aporteBO = orqInputBO.getAporteBO();
        if (aporteBO != null){
            datosSize = aporteBO.getNumRegistros();			
            aporteVO.setMonto(aporteBO.getMonto());			
            aporteVO.setNumRegistros(datosSize);
            aporteDetalleBO = aporteBO.getAporteDetalle();
            aporteDetalleVO = new AporteDetalleVO[datosSize];

            for(int i = 0; i < datosSize; i++){
                detalleBO = aporteDetalleBO[i];
                AporteDetalleVO detalleVO = new AporteDetalleVO();
                detalleVO.setFechaVencimiento(detalleBO.getFechaVencimiento());
                detalleVO.setMonto(detalleBO.getMonto());
                detalleVO.setInteresesReajuste(detalleBO.getInteresesReajuste());
                detalleVO.setRentaPromedio(detalleBO.getRentaPromedio());
                aporteDetalleVO[i] = detalleVO;
            }			
            aporteVO.setAporteDetalle(aporteDetalleVO);
        }
        else{
            aporteVO = new AporteResultVO();
        }

        creditoBO = orqInputBO.getCreditoBO();
        if (creditoBO != null){
            datosSize = creditoBO.getNumRegistros();			
            creditoVO.setMonto(creditoBO.getMonto());			
            creditoVO.setNumRegistros(datosSize);
            creditoDetalleBO = creditoBO.getCreditoDetalle();
            creditoDetalleVO = new CreditoDetalleVO[datosSize];

            for(int i = 0; i < datosSize; i++){
                detalleBO2 = creditoDetalleBO[i];
                CreditoDetalleVO detalleVO2 = new CreditoDetalleVO();
                detalleVO2.setCodigoOficina(detalleBO2.getCodigoOficina());
                detalleVO2.setFolioCredito(detalleBO2.getFolioCredito());
                detalleVO2.setNumCuota(detalleBO2.getNumCuota());
                detalleVO2.setTotalCoutas(detalleBO2.getTotalCoutas());
                detalleVO2.setEstadoCouta(detalleBO2.getEstadoCouta());
                detalleVO2.setFechaVencimiento(detalleBO2.getFechaVencimiento());
                detalleVO2.setLineaCredito(detalleBO2.getLineaCredito());
                detalleVO2.setValorCouta(detalleBO2.getValorCouta());
                detalleVO2.setCapital(detalleBO2.getCapital());
                detalleVO2.setSeguros(detalleBO2.getSeguros());
                detalleVO2.setIntereses(detalleBO2.getIntereses());
                detalleVO2.setGravamenes(detalleBO2.getGravamenes());
                detalleVO2.setMultas(detalleBO2.getMultas());
                detalleVO2.setMontoAbonado(detalleBO2.getMontoAbonado());
                detalleVO2.setMontoDescuento(detalleBO2.getMontoDescuento());
                creditoDetalleVO[i] = detalleVO2;
            }			
            creditoVO.setCreditoDetalle(creditoDetalleVO);
        }
        else{
            creditoVO = new CreditoResultVO();
        }
        orqInputVO.setErrorVO(errorVO);
        orqInputVO.setAporteVO(aporteVO);
        orqInputVO.setCreditoVO(creditoVO);
        orqInputVO.setLeasingVO(leasingVO);
        return orqInputVO;
    }
}
