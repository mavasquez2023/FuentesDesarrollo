//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.araucana.wsafiliado.mgr.jaxws;

import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This class was generated by the JAX-WS RI.
 * Generated By: JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
 * Generated source version: 2.2.1
 * 
 */
@XmlRootElement(name = "SQLException", namespace = "http://servicios.laaraucana.cl/estadoAfiliacion")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQLException", namespace = "http://servicios.laaraucana.cl/estadoAfiliacion", propOrder = {
    "SQLState",
    "errorCode",
    "message",
    "nextException"
})
public class SQLExceptionBean {

    private String SQLState;
    private int errorCode;
    private String message;
    private SQLException nextException;

    /**
     * 
     * @return
     *     returns String
     */
    public String getSQLState() {
        return this.SQLState;
    }

    /**
     * 
     * @param SQLState
     *     the value for the SQLState property
     */
    public void setSQLState(String SQLState) {
        this.SQLState = SQLState;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * 
     * @param errorCode
     *     the value for the errorCode property
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 
     * @param message
     *     the value for the message property
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     returns SQLException
     */
    public SQLException getNextException() {
        return this.nextException;
    }

    /**
     * 
     * @param nextException
     *     the value for the nextException property
     */
    public void setNextException(SQLException nextException) {
        this.nextException = nextException;
    }

}
