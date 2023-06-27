package cl.araucana.wslme.integration.jaxrpc.ws;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PrefixHandler extends GenericHandler {

	protected HandlerInfo info = null;

	public boolean handleRequest(MessageContext context) {
		return true;
	}

	public boolean handleResponse(MessageContext context) {
		SOAPMessageContext smc = (SOAPMessageContext) context;
        SOAPMessage sm = smc.getMessage();
        try {
			SOAPBody body = sm.getSOAPBody();
			
			changePrefix(body.getChildNodes());
			changeText(body.getChildNodes());
	        System.out.println(body.getPrefix());
			
			//body.setValue(body.toString().replaceAll("pqqq", "urn"));
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void changeText(NodeList list) {
		// TODO Apéndice de método generado automáticamente
		if(list==null){
			return;
		}else{
			for(int i=0;i<list.getLength();i++){
				Node n = list.item(i);

				try {
					n = n.getAttributes().removeNamedItem("xmlns:p361");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private void changePrefix(NodeList list){
		if(list==null){
			return;
		}else{
			for(int i=0;i<list.getLength();i++){
				Node n = list.item(i);
				System.out.println(n.getLocalName()+"-"+n.getNamespaceURI());
				if("p361".equals(n.getPrefix()))
					n.setPrefix("urn");
				changePrefix(n.getChildNodes());
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see javax.xml.rpc.handler.Handler#getHeaders()
	 */
	public QName[] getHeaders() {
		return info.getHeaders();
	}

	public void init(HandlerInfo arg) {
		info = arg;
	}
	
	public void destroy() {
	}		

}
