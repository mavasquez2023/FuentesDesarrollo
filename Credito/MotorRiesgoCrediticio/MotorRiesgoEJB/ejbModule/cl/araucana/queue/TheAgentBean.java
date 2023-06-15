package cl.araucana.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cl.araucana.Processor.MessageProcessor;

/**
 * Bean implementation class for Enterprise Bean: TheAgent
 */
public class TheAgentBean implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

	private static Logger logger = Logger.getLogger(TheAgentBean.class.getName());
	
	private static final long serialVersionUID = -7690141315690971329L;
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	public javax.ejb.MessageDrivenContext getMessageDrivenContext() {
		return fMessageDrivenCtx;
	}

	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx) {
		fMessageDrivenCtx = ctx;
	}

	public void ejbCreate() {
	}

	/**
	 * onMessage
	 */
	public void onMessage(javax.jms.Message msg) {

		UserTransaction myTx = getMessageDrivenContext().getUserTransaction();
		try {
			myTx.begin();
			if (!(msg instanceof TextMessage)) {
				System.out.println("Received message is not a text message!");
				throw new RuntimeException();
			}
			TextMessage textMsg = (TextMessage) msg;
			System.out.println("Received message : " + textMsg.getText());

			MessageProcessor processor = new MessageProcessor();

			if (processor.ProcesaSolicitud(textMsg.getText())) {
				myTx.commit();
			}
			else {
				myTx.rollback();
			}
			logger.log(Level.FINE, "MDB acaba de terminar operacion");

		} catch (NotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
}
