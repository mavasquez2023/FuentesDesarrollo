

package cl.araucana.fpg.test;


/*
 * @(#) QueueTester.java    1.0 21-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import cl.araucana.core.util.Queue;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 21-04-2008 </TD>
 *            <TD align="center">  2.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class QueueTester {

	public static void main(String[] args) throws Exception {

		Queue requestQueue = new Queue(10);

		for (int i = 0; i < 8; i++) {
			requestQueue.put(new Integer(i));
		}

		System.out.println("Queue capacity: " + requestQueue.capacity());
		System.out.println("Queue size: " + requestQueue.size());

		while (requestQueue.size() > 0) {
			System.out.println("Element :" + requestQueue.get());
		}

		requestQueue = new Queue(10);

		ProducerThread producerThread = new ProducerThread(requestQueue);
		ConsumerThread consumerThread = new ConsumerThread(requestQueue);

		producerThread.start();
		consumerThread.start();

		try {
			long time = 1000;

			if (args.length > 0) {
				try {
					time = Long.parseLong(args[0]);
				} catch (NumberFormatException e) {}
			}

			System.out.println("main: sleeping " + time + " ms ...");
			Thread.sleep(time);
		} catch (InterruptedException e) {};

		producerThread.interrupt();
		consumerThread.interrupt();
	}

	private static class ProducerThread extends Thread {

		private Queue requestQueue;

		public ProducerThread(Queue requestQueue) {
			this.requestQueue = requestQueue;

			setDaemon(true);
		}

		public void run() {
			try {
				while (true) {
					int value = (int) (100 * Math.random());

					requestQueue.put(new Integer(value));
					System.out.println("producer: " + value);
				}
			} catch (InterruptedException e) {
				System.out.println("producer: interrupted.");
			}
		}
	}

	private static class ConsumerThread extends Thread {

		private Queue requestQueue;

		public ConsumerThread(Queue requestQueue) {
			this.requestQueue = requestQueue;

			setDaemon(true);
		}

		public void run() {
			try {
				while (true) {
					int value = ((Integer) requestQueue.get()).intValue();

					System.out.println("consumer: " + value);

					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("consumer: interrupted.");
			}
		}
	}
}
