package cl.araucana.sivegam.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import cl.araucana.sivegam.conexion.cobol.bo.ParametrosConexionBO;
import cl.araucana.sivegam.conexion.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;

import com.ibm.as400.access.AS400PackedDecimal;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.ws.exception.PropertyVetoException;
//import com.ibm.xslt4j.bcel.generic.NEW;

public class ConsumidorCobol {

    SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    private static ParametrosLlamadaBO[] parametros;

    public static ParametrosLlamadaBO[] call(ParametrosConexionBO conexion, ParametrosLlamadaBO[] _parametros) {
        SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

        logger.debug("INI     : call programa [" + conexion.getPrograma() + "]");
        
        parametros = _parametros;
        logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - conexion.getClaveConexion()   [" + conexion.getClaveConexion() + "]");
        logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - conexion.getClaveConexion()   [" + conexion.getClaveConexion() + "]");
        logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - conexion.getIpServer()        [" + conexion.getIpServer() + "]");
        logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - conexion.getPrograma()        [" + conexion.getPrograma() + "]");
        logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - conexion.getUsuarioConexion() [" + conexion.getUsuarioConexion() + "]");
        AS400Config.configure(conexion);
        QSYSObjectPathName programName = new QSYSObjectPathName(conexion.getPrograma());
        ProgramParameter[] parametrosCall = null;

        try {

            logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - antes de obtener parametros");
            parametrosCall = obtenerParametros();
            logger.debug("SET     : call programa [" + conexion.getPrograma() + "] - despues de obtener parametros");

        } catch (PropertyVetoException e) {
            logger.debug("ERROR   : call programa [" + conexion.getPrograma() + "] - Error al procesar los parametros...");
        }
        
        long time_start;
        long time_end;
        float tiempoTotal;

        logger.debug("CALL    : call programa [" + conexion.getPrograma() + "] - antes de ProgramCall");
        ProgramCall getSystemStatus = new ProgramCall(AS400Config.getAs400(), programName.getPath(), parametrosCall);
        logger.debug("CALL    : call programa [" + conexion.getPrograma() + "] - despues de ProgramCall");

 
        try {
            logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - antes de getSystemStatus.run()");
            time_start = System.currentTimeMillis();
            if (!getSystemStatus.run()) {
                time_end = System.currentTimeMillis();
                tiempoTotal = (time_end - time_start) / 1000;
                logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - Tiempo Total de Ejecucion " + tiempoTotal + " seg.");
                logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - ha ocurrido un problema al ejecutar el programa " + conexion.getPrograma() + ",\n " + getSystemStatus.getMessageList()[0].getText());
            }

            else {
                time_end = System.currentTimeMillis();
                tiempoTotal = (time_end - time_start) / 1000;
                logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - La ejecucion del programa " + conexion.getPrograma() + ", se realizo exitosamente - Tiempo Total de Ejecucion " + tiempoTotal + " seg.");

                logger.debug("RESULT  : call programa [" + conexion.getPrograma() + "] - ---------- RESULTADO ----------");

                for (int i = 0; i < parametros.length; i++) {

                    if ("OUT".equals(parametros[i].getDireccion())) {

                        try {

                            byte[] rbTextoResultado = getSystemStatus.getParameterList()[i].getOutputData();
                            String result = (String) parametros[i].getTextoAS400().toObject(rbTextoResultado);
                            parametros[i].setValor(result);
                            logger.debug("RESULT  : call programa [" + conexion.getPrograma() + "] - parametro " + i + " = [" + result + "]");

                        }

                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                logger.debug("RESULT  : call programa [" + conexion.getPrograma() + "] - ------------------------------");
            }
            logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - despues de getSystemStatus.run()");

        } catch (AS400SecurityException e) {
            e.printStackTrace();
        } catch (ErrorCompletingRequestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ObjectDoesNotExistException e) {
            e.printStackTrace();
        }

        finally {

            AS400Config.getAs400().disconnectService(AS400Config.getAs400().COMMAND);
            logger.debug("RUN     : call programa [" + conexion.getPrograma() + "] - Conexion cerrada..");
        }

        logger.debug("FIN     : call programa [" + conexion.getPrograma() + "]");
        return parametros;
    }

    private static ProgramParameter[] obtenerParametros() throws PropertyVetoException {
        ProgramParameter[] parametrosCall = new ProgramParameter[parametros.length];

        for (int i = 0; i < parametros.length; i++) {

            if ("INTEGER".equals(parametros[i].getTipo())) {

                BigDecimal valor = new BigDecimal(0);
                parametrosCall[i] = new ProgramParameter(new AS400PackedDecimal(parametros[i].getLargo(), 0).toBytes(valor), parametros[i].getLargo());

            }

            else if ("STRING".equals(parametros[i].getTipo())) {

                parametros[i].setTextoAS400(new AS400Text(parametros[i].getLargo(), AS400Config.getAs400()));
                byte[] bTextoResultado = parametros[i].getTextoAS400().toBytes(parametros[i].getValor());
                parametrosCall[i] = new ProgramParameter(bTextoResultado, parametros[i].getLargo());

            }
        }

        return parametrosCall;
    }
}
