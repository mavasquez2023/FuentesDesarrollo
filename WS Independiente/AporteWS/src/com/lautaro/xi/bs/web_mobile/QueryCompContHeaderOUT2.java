//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.lautaro.xi.bs.web_mobile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(name = "QueryCompContHeaderOUT2", targetNamespace = "http://lautaro.com/xi/BS/WEB-Mobile")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.lautaro.xi.bs.treasury.ObjectFactory.class,
    com.lautaro.xi.bs.web_mobile.ObjectFactory.class
})
public interface QueryCompContHeaderOUT2 {


    /**
     * 
     * @param queryCompactContractHeaderRequestOut
     * @return
     *     returns com.lautaro.xi.bs.web_mobile.QueryCompactContractHeaderResponse2
     */
    @WebMethod(operationName = "QueryCompContrHeader", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "QueryCompactContractHeaderResponseOut2", targetNamespace = "http://lautaro.com/xi/BS/WEB-Mobile", partName = "QueryCompactContractHeaderResponseOut2")
    public QueryCompactContractHeaderResponse2 queryCompContrHeader(
        @WebParam(name = "QueryCompactContractHeaderRequestOut", targetNamespace = "http://lautaro.com/xi/BS/WEB-Mobile", partName = "QueryCompactContractHeaderRequestOut")
        QueryCompactContractHeaderRequest queryCompactContractHeaderRequestOut);

}
