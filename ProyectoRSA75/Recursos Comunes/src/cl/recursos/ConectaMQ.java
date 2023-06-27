/*
 * Creado el 13-09-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
import com.ibm.mq.*;

public class ConectaMQ {

	//variables definidas para la conexión a MQ
	//private static String hostname; // define el nombre del host a conectar
	//private static String qManager; // define nombre del objeto de gestor de colas a conectarse
	//private static String channel; // define nombre del canal con el que  se debe conectar en el gestor de colas
	//private static int port; // Puerta a la que debe conectarse, por defecto es 1414
	// Nota. se asume que MQ Server está escuchando
	private static MQQueueManager qMgr; // define un  objeto queue manager	
	
public ConectaMQ(String hostname, String qManager, int port, String channel){
	try{
		//Se obtienen los parametros de Conexión
		System.out.println("Clase Consulta MQ instanciada, hostname=" + hostname + ", qmanager= " + qManager + ", port= " + port + ", channel= " + channel);
		MQEnvironment.hostname = hostname; //resetea variable de ambiente hostname
		MQEnvironment.channel = channel; //resetea variable de ambiente channel
		MQEnvironment.port = port; //resetea variable de ambiente port
		//MQEnvironment.userID = "assist";
		//MQEnvironment.password = "assist";
		qMgr = new MQQueueManager(qManager);
		System.out.println("Consulta a MQ qManager:" + qManager + " en servidor " + hostname + " OK.");
	}catch (MQException ex) {
		System.out.println(" Un error MQ ha ocurrido : Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);
	}
} 
 /**
   consultaColas ****************************************************************************** consultaColas

   Se resume a continuación acciones realizadas por este método
   1. openOptions:	Se setean opciones para abrir cola solo para consulta
   2. MQQueue:		Se define la cola actual sobre la cual se hara la consulta
   3. MQQueue=MQQueueManager.accessQueue:	Se establece el acceso a la cola que abriremos según
   											openOptions	en el gestor de colas establecido 
   4. MQQueue.close():	Se cierra la cola
 * Creation date: (2/28/01 10:06:43 AM)
 */
public int infoMenu(String cola) {
	int openOptions;
	int actualqsize=0;
	try {
		System.out.println("Abrir Cola MQ= " + cola);
		//Abrir cola solo para consulta
		openOptions = MQC.MQOO_INQUIRE;

		// Se especifica la cola que abriremos y las opciones establecidas anteriormente.
		MQQueue local_queue = qMgr.accessQueue(cola, openOptions);
		System.out.println("Cola Accesada");
		// Ahora analizamos las propiedades de la cola
		//número actual de mensajes en la cola
		actualqsize = local_queue.getCurrentDepth();

		//se cierra la cola		
		local_queue.close();
		System.out.println("Numero de mensajes en la cola " + cola + " =" + actualqsize);
	}
	// Si un error ha ocurrido en el proceso , trata de identificar que evento falló.
	catch (MQException ex) {
		System.out.println(" Un error MQ ha ocurrido : Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);
	}
	return actualqsize;
}

public void closeMQ(){
	try{
		qMgr.close();
	}
	// Si un error ha ocurrido en el proceso , trata de identificar que evento falló.
	catch (MQException ex) {
		System.out.println(" Un error MQ ha ocurrido : Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);
	}
}
public static void main(String[] args) {
	String hostname="";
	String qManager="";
	int port=0;
	String channel="";
	try {
		
		ConectaMQ mq= new ConectaMQ(hostname, qManager, port, channel);
		mq.closeMQ();
	} catch (Exception e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}
}
