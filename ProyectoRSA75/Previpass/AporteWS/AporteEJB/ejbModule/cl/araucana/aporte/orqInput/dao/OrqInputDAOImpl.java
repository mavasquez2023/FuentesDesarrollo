package cl.araucana.aporte.orqInput.dao;

import cl.araucana.aporte.orqInput.bo.AporteCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;
import cl.araucana.aporte.orqInput.bo.ErrorResultBO;
import cl.araucana.aporte.orqInput.bo.AporteResultBO;
import cl.araucana.aporte.orqInput.bo.LeasingResultBO;
import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;

public class OrqInputDAOImpl implements OrqInputDAO {

    public OrqInputResultBO obtenerInfoPago (int rut){
        /**
         * IMPLEMENTA LA OBTENCION DE INFORMACION DE 
         * APORTE, CREDITO Y LEASING PARA LA REALIZACION
         * DE PAGO 
         */
        //System.out.println("OrqInputDAOImpl");
        OrqInputResultBO orqInputBO = new OrqInputResultBO();
        AporteResultBO aporteBO = new AporteResultBO();
        AporteCallBO aporteCallBO = new AporteCallBO();
        String aporteGlsError;
        int aporteCodError;

        CreditoResultBO creditoBO = new CreditoResultBO();
        CreditoCallBO creditoCallBO = new CreditoCallBO();
        String creditoGlsError;
        int creditoCodError;

        LeasingResultBO leasingBO = new LeasingResultBO();
        leasingBO = null;
        ErrorResultBO error = new ErrorResultBO();	
        String glsError;
        /**
         * IMPLEMENTACION APORTE
         */
        try{
            aporteCallBO = AporteDAOImpl.obtenerAporte(rut);
            if (aporteCallBO.getCodError()== 0){
                aporteBO = aporteCallBO.getAporte();
                aporteCodError = aporteCallBO.getCodError();
                aporteGlsError = aporteCallBO.getGlsError();				
            }
            else{		
                aporteBO = null;
                aporteCodError = aporteCallBO.getCodError();
                aporteGlsError = aporteCallBO.getGlsError();			
            }								
        }catch (Exception e){
            aporteCodError = -7;
            if (e.toString().length() < 200){
                aporteGlsError = "Error en la invocación de servicio Aporte: " + e.toString();
            }
            else{
                aporteGlsError = "Error en la invocación de servicio Aporte: " + e.toString().substring(0,200);
            }
            aporteBO = null;
            orqInputBO.setErrorBO(error);
        }		
        /**
         * IMPLEMENTACION CREDITO
         */
        
        CreditoCallBO creditoDummy= CreditoSapDAOImpl.obtenerCredito(rut);
        if(creditoDummy!=null){
        	//Llamada dummy
        	creditoCallBO = creditoDummy;
        	creditoBO = creditoDummy.getCredito();
        	creditoCodError = creditoCallBO.getCodError();
            creditoGlsError = creditoCallBO.getGlsError();
        }else{
        	//Llamada original
            try{
                creditoCallBO = CreditoDAOImpl.obtenerCredito(rut);
                if (creditoCallBO.getCodError() == 0){
                    creditoBO = creditoCallBO.getCredito();
                    creditoCodError = creditoCallBO.getCodError();
                    creditoGlsError = creditoCallBO.getGlsError();
                }
                else{
                    creditoBO = null;
                    creditoCodError = creditoCallBO.getCodError();
                    creditoGlsError = creditoCallBO.getGlsError();
                }			
            }catch (Exception e){
                creditoCodError = -7;
                if (e.toString().length() < 200){
                    creditoGlsError = "Error en la invocación de servicio Credito: " + e.toString();
                }
                else{
                    creditoGlsError = "Error en la invocación de servicio Credito: " + e.toString().substring(0,200);
                }
                aporteBO = null;
                orqInputBO.setErrorBO(error);
            }
        }
			
        //System.out.println("aporteCodError "+aporteCodError +" creditoCodError "+ creditoCodError);
        if (aporteCodError == 0 && creditoCodError == 0){
            error.setCodError(0);
        }
        else if (aporteCodError != 0 && creditoCodError == 0){
            error.setCodError(aporteCodError);
        }
        else if (aporteCodError == 0 && creditoCodError != 0){
            error.setCodError(creditoCodError);
        }
        else if (aporteCodError != 0 && creditoCodError != 0) {
            error.setCodError(-7);
        }

        if (aporteGlsError.length()< 130){
            glsError = aporteGlsError;
        }
        else{
            glsError = aporteGlsError.substring(0, 130);
        }
        glsError = glsError + ". ";
        if (creditoGlsError.length()< 130){
            glsError = glsError + creditoGlsError;
        }
        else{
            glsError = glsError + creditoGlsError.substring(0, 130);
        }
        error.setGlsError(glsError);

        orqInputBO.setErrorBO(error);
        orqInputBO.setCreditoBO(creditoBO);
        orqInputBO.setLeasingBO(leasingBO);
        orqInputBO.setAporteBO(aporteBO);
        return orqInputBO;				
    }
}
