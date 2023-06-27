package conector.lme.ws.cliente.operador.excepciones;

import conector.excepciones.SysException;

public class ErrorRespuestaOperadorException extends SysException {
   

    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;
    
    public ErrorRespuestaOperadorException(Throwable throwable, String mensaje) {
        super(throwable, mensaje);        
    }
    
    public ErrorRespuestaOperadorException(String nombreServicio, String parametros, int codigo, String glosa) {
//        super(null, "Servicio: " + nombreServicio + " parametros: " + parametros + " codigo: " + codigo + " glosa: " + glosa);
        super(null, glosa);
    }

}
