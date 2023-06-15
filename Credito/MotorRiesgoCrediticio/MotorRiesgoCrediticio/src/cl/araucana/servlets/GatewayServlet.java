package cl.araucana.servlets;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GatewayServlet
 */
public class GatewayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String JMSCF_JNDI_NAME = "java:comp/env/CreditScoringConnFact";
	private final static String JMSQ_JNDI_NAME = "java:comp/env/CreditScoringCola";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GatewayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void myService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			InitialContext initCtx = new InitialContext();
			// Finding the WAS QueueConnectionFactory
			javax.jms.ConnectionFactory qcf = (javax.jms.ConnectionFactory) initCtx
					.lookup(JMSCF_JNDI_NAME);
			// Finding the Queue Destination
			Destination q = (Destination) initCtx.lookup(JMSQ_JNDI_NAME);
			// Create JMS Connection
			Connection connection = qcf.createConnection();
			// Create JMS Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Create MessageProducer and TextMessage
			MessageProducer queueSender = session.createProducer(q);
			TextMessage outMessage = session.createTextMessage();
			
			String id = request.getParameter("id");
			String as400id = request.getParameter("as400id");
			String payload = request.getParameter("payload");
			
			if (as400id == null || as400id.length() < 100) {
				as400id = "nulo";
			}
			outMessage.setText(id+"&"+as400id+"&"+payload);
			
			// Set type and destination and send
			outMessage.setJMSType("package_received");
			outMessage.setJMSDestination(q);
			queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			queueSender.send(outMessage);
			connection.close();
			System.out.println("Send completed");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		myService(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		myService(request, response);

	}

}
