package cl.araucana.aporte.orqOutput.dao;


import cl.araucana.aporte.orqOutput.bo.ErrorResultBO;
import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;

public class OrqOutputDAOImpl implements OrqOutputDAO {
    public OrqOutputResultBO recuperacionPago (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago){
        /**
         * IMPLEMENTA LA RECUPERACION DE INFORMACION DE 
         * APORTE, CREDITO Y LEASING PARA LA REALIZACION
         * DE PAGO 
         * IMPLEMENTA ACTUALIZACION DE INFORMACION
         * DE APORTE Y CREDITO ASOCIADO
         */
        OrqOutputResultBO orqOutputBO = new OrqOutputResultBO();
        ErrorResultBO error = new ErrorResultBO();
        ErrorResultBO errorlog = new ErrorResultBO();
        ErrorResultBO errorApt = new ErrorResultBO();
        ErrorResultBO errorCrd= new ErrorResultBO();
        String periodo = String.valueOf(periodoAporte);
        int intentosAporte = 0;
        int intentosCredito = 0;
        String glsError;

        try{
            if(rut <= 0){
                errorCrd.setCodError(-9);
                errorCrd.setGlsError("El campo RUT es obligatorio");
            }
            else if(montoAporte <= 0){
                errorApt.setCodError(-9);
                errorApt.setGlsError("Monto Aporte a actualizar es menor o igual a 0");
            }
            else if(periodoAporte <= 0 || periodo.length()< 8 || periodo.length() > 8){
                errorApt.setCodError(-9);
                errorApt.setGlsError("Periodo Aporte debe tener el formato AAAAMMDD");
            }
            else if(fechaRecaudacion.length() == 0 || fechaRecaudacion.length() < 8 || fechaRecaudacion.length()> 8){
                errorApt.setCodError(-9);
                errorApt.setGlsError("Fecha de Recaudación debe tener el formato DDMMAAAA");
            }
            else if(horaRecaudacion.length() == 0 || horaRecaudacion.length() < 5 || horaRecaudacion.length()> 5){
                errorApt.setCodError(-9);
                errorApt.setGlsError("Hora de Recaudación debe tener el formato HH:MM");
            }
            /*
			else if (Helper.verificaFecha(fechaRecaudacion) == false){
				error.setCodError(-9);
				error.setGlsError("Fecha de Recaudación debe ser mayor o igual a la fecha actual");
				orqOutputBO.setErrorBO(error);
			}*/
            else{
                intentosAporte = 0;
                do{
                    intentosAporte++;
                    System.out.println("intento nº"+intentosAporte+" invocacion de servicio Aporte");
                    errorApt = AporteDAOImpl.actualizarAporte(rut, periodoAporte, montoAporte, fechaRecaudacion);	
                }while ((errorApt.getCodError() == -1 || errorApt.getCodError() == -2 || errorApt.getCodError() == -3 ) && (intentosAporte < 3));

                if (errorApt.getCodError() == 0){
                    System.out.println("Registra log de actualizacion correcta de Aporte");
                    errorlog = AporteDAOImpl.ingresarLogAporte(0, rut, periodoAporte, montoAporte, fechaRecaudacion, horaRecaudacion, usuario);
                }
                else if (errorApt.getCodError() == -1 || errorApt.getCodError() == -2 || errorApt.getCodError() == -3 ){
                    System.out.println("Registra log de error de ejecucion de Aporte");
                    errorlog = AporteDAOImpl.ingresarLogAporte(2, rut, periodoAporte, montoAporte, fechaRecaudacion, horaRecaudacion, usuario);
                }
                else{
                    System.out.println("Registra log de falta de informacion de Aporte");
                    errorlog = AporteDAOImpl.ingresarLogAporte(1, rut, periodoAporte, montoAporte, fechaRecaudacion, horaRecaudacion, usuario);
                }

                System.out.println(+errorlog.getCodError()+": " + errorlog.getGlsError());
            }	
        }catch (Exception e){
            e.printStackTrace();
            errorApt.setCodError(-7);
            if (e.toString().length() < 200){
                errorApt.setGlsError("Error en la invocación de servicio Aporte: " +  e.toString());		
            }
            else{
                errorApt.setGlsError("Error en la invocación de servicio Aporte: " +  e.toString().substring(0,200));		
            }
        }		

        try{
            if(rut <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("El campo RUT es obligatorio");
            }
            else if(montoCredito <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Monto Crédito a actualizar es menor o igual a 0");
            }			
            else if(documentoPago <= 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Documento de Pago debe ser distinto de 0");
            }
            else if(usuario.length() == 0){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Debe ingresar el campo Usuario");
            }
            else if(fechaRecaudacion.length() == 0 || fechaRecaudacion.length() < 8 || fechaRecaudacion.length()> 8){
                errorCrd.setCodError(-10);
                errorCrd.setGlsError("Fecha de Recaudación debe tener el formato DDMMAAAA");
            }
            else if(horaRecaudacion.length() == 0 || horaRecaudacion.length() < 5 || horaRecaudacion.length()> 5){
                errorApt.setCodError(-10);
                errorApt.setGlsError("Hora de Recaudación debe tener el formato HH:MM");
            }
            else{
                intentosCredito = 0;
                do{
                    intentosCredito++;
                    System.out.println("intento nº"+intentosCredito+" invocacion de servicio Credito");
                    errorCrd = CreditoDAOImpl.actualizarCredito(rut, montoCredito, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
                }while ((errorCrd.getCodError() == -1 || errorCrd.getCodError() == -2 || errorCrd.getCodError() == -3 ) && (intentosCredito < 3));

                if (errorCrd.getCodError() == 0){
                    System.out.println("Registra log de actualizacion correcta de Credito");
                    errorlog = CreditoDAOImpl.ingresarLogCredito(0, rut, montoCredito, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
                }				
                else if (errorCrd.getCodError() == -1 || errorCrd.getCodError() == -2 || errorCrd.getCodError() == -3 ){
                    System.out.println("Registra log de error de ejecucion de Credito");
                    errorlog = CreditoDAOImpl.ingresarLogCredito(4, rut, montoCredito, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
                }
                else{
                    System.out.println("Registra log de falta de informacion de Credito");
                    errorlog = CreditoDAOImpl.ingresarLogCredito(3, rut, montoCredito, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
                }				
                System.out.println(+errorlog.getCodError()+": " + errorlog.getGlsError());
            }	
        }catch (Exception e){
            e.printStackTrace();
            errorCrd.setCodError(-7);
            if (e.toString().length() < 200){
                errorCrd.setGlsError("Error en la invocación de servicio Credito: " +  e.toString());		
            }
            else {
                errorCrd.setGlsError("Error en la invocación de servicio Credito: " +  e.toString().substring(0,200));		
            }
        }

        //System.out.println("aporteCodError "+errorApt.getCodError() +" creditoCodError "+ errorCrd.getCodError());
        if (errorApt.getCodError() == 0 && errorCrd.getCodError() == 0){
            error.setCodError(0);
        }
        else if (errorApt.getCodError() != 0 && errorCrd.getCodError() == 0){
            error.setCodError(errorApt.getCodError());
        }
        else if (errorApt.getCodError() == 0 && errorCrd.getCodError() != 0){
            error.setCodError(errorCrd.getCodError());
        }
        else {
            error.setCodError(-7);
        }

        if (errorApt.getGlsError().length()< 130){
            glsError = errorApt.getGlsError();
        }
        else{
            glsError = errorApt.getGlsError().substring(0, 130);
        }
        glsError = glsError + ". ";
        if (errorCrd.getGlsError().length()< 130){
            glsError = glsError + errorCrd.getGlsError();
        }
        else{
            glsError = glsError + errorCrd.getGlsError().substring(0, 130);
        }

        error.setGlsError(glsError);
        orqOutputBO.setErrorBO(error);
        return orqOutputBO;	
    }

}
