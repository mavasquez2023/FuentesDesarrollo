/**
 * 
 */
package cl.laaraucana.compromisototal.compTotal.actions;

import cl.laaraucana.compromisototal.VO.PresupuestoVO;
import cl.laaraucana.compromisototal.compTotal.forms.ContratoForm;
import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.utils.Utils;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class DetalleContratoPresupuestoBankingAction extends Action
{

    public DetalleContratoPresupuestoBankingAction()
    {
        logger = Logger.getLogger(getClass());
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ActionForward forward = new ActionForward();
        HttpSession sesion = request.getSession();
        try
        {
            ContratoForm datosForm = (ContratoForm)form;
            String rut = (String)sesion.getAttribute("rut");
            if(rut==null){
				rut= request.getParameter("rut");
			}
            if(rut == null)
            {
                Exception e = new Exception("Error con la sesion rut vacio");
                forward = Utils.returnErrorForward(mapping, e);
                logger.error((new StringBuilder("error detalle as400 ")).append(e.getMessage()).toString());
            } else
            {
                String idContrato = datosForm.getId();
                logger.debug((new StringBuilder("Entro a detalleContratoBanking con rut:")).append(rut).append(", y idContrato: ").append(idContrato).toString());
                HttpClient client = null;
                PostMethod postMethod = null;
                StringRequestEntity requestEntity = null;
                String soapRequest = (new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://lautaro.com/xi/CRM/WEB-Mobile\"> <soapenv:Header/>   <soapenv:Body>      <web:MT_ContPresup_REQ>    <Contrato>")).append(idContrato).append("</Contrato>  </web:MT_ContPresup_REQ>   </soapenv:Body></soapenv:Envelope>").toString();
                client = new HttpClient();
                String ep = Configuraciones.getConfig("ep.SI_ContPresup_OUT");
                postMethod = new PostMethod(ep);
                requestEntity = new StringRequestEntity(soapRequest);
                postMethod.addRequestHeader("Authorization", "Basic dV9pbnRlZ3JhZG9yOlNvcG9ydGUyMDEy");
                postMethod.addRequestHeader("Content-Type", "text/xml");
                postMethod.addRequestHeader("CharSet", "UTF-8");
                postMethod.addRequestHeader("User-Agent", "Jakarta Commons-HttpClient/3.0.1");
                postMethod.addRequestHeader("SOAPAction", "http://sap.com/xi/WebService/soap1.1");
                postMethod.addRequestHeader("Host", "ecc6-server:8000");
                postMethod.setRequestEntity(requestEntity);
                try
                {
                    client.executeMethod(postMethod);
                    String response_ = postMethod.getResponseBodyAsString();
                    System.out.println((new StringBuilder("Response from web service: ")).append(response_).toString());
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    InputSource is = new InputSource(new StringReader(postMethod.getResponseBodyAsString()));
                    factory.setNamespaceAware(true);
                    factory.setIgnoringComments(true);
                    Document doc = builder.parse(is);
                    NodeList nodeList = doc.getElementsByTagName("Presupuesto");
                    ArrayList detalle = new ArrayList();
                    for(int i = 0; i < nodeList.getLength(); i++)
                    {
                        NodeList innerList = nodeList.item(i).getChildNodes();
                        PresupuestoVO presupuesto = new PresupuestoVO();
                        presupuesto.setContrato(innerList.item(0).getTextContent());
                        presupuesto.setNumero(innerList.item(1).getTextContent());
                        presupuesto.setMonto(innerList.item(2).getTextContent());
                        detalle.add(presupuesto);
                    }

                    Date date = new Date();
                    request.setAttribute("detalle", detalle);
                    request.setAttribute("rut", rut);
                    request.setAttribute("idContrato", idContrato);
                    request.setAttribute("fechaEmision", date);
                    request.setAttribute("detalle", detalle);
                    request.setAttribute("error", "ok");
                    request.setAttribute("mensaje", "");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println("WebServiceTest is Failure");
                }
                Date date = new Date();
                forward = mapping.findForward("detBankingOk");
                logger.debug("Salio de detalleContratoBankig ");
            }
        }
        catch(Exception e)
        {
            forward = Utils.returnErrorForward(mapping, e);
            logger.error((new StringBuilder("error detalle banking")).append(e.getMessage()).toString());
        }
        return forward;
    }

    protected Logger logger;
}

