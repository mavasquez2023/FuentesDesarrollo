/**
 * 
 */
package conector.lme.ws.cliente.operador.excepciones;

import conector.excepciones.SysException;

/**
 * 
 * 
 * @author jcea@microsystem.cl
 */
public class ErrorInvocacionOperadorException extends SysException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    public ErrorInvocacionOperadorException(Throwable throwable, String nombreServicio, String parametros) {
//        super(throwable, "Servicio: " + nombreServicio + " parametros: " + parametros + " causa: " + throwable);
        super(throwable, throwable.toString());
    }

    public ErrorInvocacionOperadorException(String nombreServicio, String parametros, int codigo, String glosa) {
//        super(null, "Servicio: " + nombreServicio + " parametros: " + parametros + " codigo: " + codigo + " glosa: " + glosa);
        super(null, glosa);
    }

    /* jcm
    public ErrorInvocacionMedipassException(String nombreServicio, String parametros, java.math.BigInteger codigo, String glosa) {
        super(null, "Servicio: " + nombreServicio + " parametros: " + parametros + " codigo: " + codigo + " glosa: "
                + glosa);
    }
    */
}
