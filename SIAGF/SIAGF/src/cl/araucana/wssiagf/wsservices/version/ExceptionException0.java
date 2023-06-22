/**
 * ExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package cl.araucana.wssiagf.wsservices.version;

public class ExceptionException0 extends java.lang.Exception {
    private cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument faultMessage;

    public ExceptionException0() {
        super("ExceptionException0");
    }

    public ExceptionException0(java.lang.String s) {
        super(s);
    }

    public ExceptionException0(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public void setFaultMessage(
        cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument msg) {
        faultMessage = msg;
    }

    public cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument getFaultMessage() {
        return faultMessage;
    }
}
