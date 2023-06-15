package cl.araucana.wsempresa.manager;

import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;


public class ServerSOAPHandler  implements SOAPHandler<SOAPMessageContext> {
	 
	/**
	* Is called after constructing the handler and before executing any othe method.
	*/
	@PostConstruct
	public void init() {
		System.out.println("En Init de ServerSOAPHandler");
	}
	
    @Override
    public void close(MessageContext context) { 
    }
    
    @Override
    public boolean handleFault(SOAPMessageContext messagecontext) {
    	try {
    		Object tipo= messagecontext.get("tipo");
    		String tipo_str="";
    		if(tipo!=null){
    			tipo_str= tipo.toString();

    			SOAPMessage message = messagecontext.getMessage();
    			SOAPBody body = message.getSOAPBody();
    			SOAPFault fault = body.getFault();
    			QName faultName = new QName(SOAPConstants.URI_NS_SOAP_ENVELOPE, tipo_str);
    			fault.setFaultCode(faultName);
    			fault.setFaultActor("http://www.laaraucana.cl");
    		}
			//fault.setFaultString("Server not responding");
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true; 
    }
 
    @Override
    public boolean handleMessage(SOAPMessageContext messagecontext) { 
    	String usuario="";
    	String password="";
        Boolean outbound = (Boolean) messagecontext 
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY); 
        if (outbound) { 
            System.out.println("SOAP message departing…"); 
        } else { 
            System.out.println("SOAP message incoming…"); 
        } 
        try { 
        	/*SOAPMessage message = messagecontext.getMessage();
            SOAPHeader header = message.getSOAPPart().getEnvelope().getHeader(); 
            if (header != null) { 
                Iterator i = header.getChildElements();
                for (Iterator iterator = header.getChildElements(); iterator
						.hasNext();) {
					Node type = (Node) iterator.next();	
					if(type.getNodeName().equals("AuthenticationInfo")){
						NodeList subnode= type.getChildNodes();
						for (int j = 0; j < subnode.getLength(); j++) {
							org.w3c.dom.Node nodo= subnode.item(j);
							System.out.println(nodo.getNodeName());
							if(nodo.getNodeName().equals("Username")){
								usuario= nodo.getFirstChild().getNodeValue();
								System.out.println("Username=" + usuario);
							}
							if(nodo.getNodeName().equals("Password")){
								password= nodo.getFirstChild().getNodeValue();
								System.out.println("Password=" + password);
							}
						}
					}
				}
            } 
            SOAPBody body = message.getSOAPBody(); 
            if (body != null) { 
                Iterator i = body.getChildElements(); 
                System.out.println("Number of body elements:  "
                        + countElements(i)); 
            }
            messagecontext.put("userid", usuario);
            messagecontext.put("password", password);
            messagecontext.setScope("userid", MessageContext.Scope.APPLICATION);
            messagecontext.setScope("password", MessageContext.Scope.APPLICATION);*/
        } catch (Exception e) { 
            System.out.println(e); 
            e.printStackTrace(); 
        } 
        return true; 
    }
    
    
    @Override 
    public Set<QName> getHeaders() { 
    	return Collections.EMPTY_SET;
        /*System.out.println("Inside SOAP handler of get Headers"); 
        QName securityUsernameHeader = new QName("urn:com.intertech.secty", 
                "Username"); 
        QName securityPasswordHeader = new QName("urn:com.intertech.secty", 
                "Password"); 
        HashSet<QName> headers = new HashSet<QName>(); 
        headers.add(securityUsernameHeader); 
        headers.add(securityPasswordHeader); 
        System.out.println("got Headers:  " + headers); 
        return headers; */
    }
}   
