/**
 * 
 */
package conector.test.manual;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.integracion.as400.InvocadorAS400;
import conector.integracion.as400.excepciones.ConexionAS400SysException;
import conector.integracion.as400.excepciones.EjecucionAS400SysException;
import conector.integracion.as400.vo.Estructura;

/**
 * 
 * 
 * @author amartoq@microsystem.cl
 */
public class Prueba0B {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        estructura();
    }

    static void estructura() throws ConfiguracionException, EjecucionAS400SysException, ConexionAS400SysException {
        Estructura resultado = new Estructura(15);
        resultado.paramSalidaChr("RUT", 12);
        resultado.paramSalidaChr("NOM", 80);

        Estructura estructura = new Estructura();
        estructura.paramEntradaNum("RUT", 9, 9246958);
        estructura.paramEntradaChr("DIG", 1, " ");
        estructura.paramSalidaNum("RET", 1);
        estructura.paramEstructura("lista", resultado);

        InvocadorAS400 as400 = new InvocadorAS400("prueba");
        as400.ejecutar("/QSYS.LIB/MVIELMA.LIB/ILP900.PGM", estructura);

        log("salida: RUT [" + estructura.valorNum("RUT") + "]");
        log("salida: DIG [" + estructura.valorChr("DIG") + "]");
        log("salida: RET [" + estructura.valorNum("RET") + "]");
        log("salida: STR [" + estructura.getEstructura("lista") + "]");
        for (int i = 0; i < resultado.getFilas(); i++) {
            log("   " + i + ": RUT [" + resultado.valorChr(i, "RUT") + "]");
            log("   " + i + ": NOM [" + resultado.valorChr(i, "NOM") + "]");
        }
    }

    static void log(String message) {
        System.out.println(message);
    }
}
