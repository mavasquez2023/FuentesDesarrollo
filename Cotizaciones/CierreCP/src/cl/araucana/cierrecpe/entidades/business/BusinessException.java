/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package cl.araucana.cierrecpe.entidades.business;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class BusinessException extends Exception{

    public BusinessException()
    {
    }

    public BusinessException(String message)
    {
        super(message);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
