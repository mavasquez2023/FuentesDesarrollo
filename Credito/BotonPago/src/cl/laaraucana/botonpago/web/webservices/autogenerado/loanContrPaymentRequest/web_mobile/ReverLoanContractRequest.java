
/**
 * ReverLoanContractRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile;
            

            /**
            *  ReverLoanContractRequest bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ReverLoanContractRequest
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ReverLoanContractRequest
                Namespace URI = http://lautaro.com/xi/BS/WEB-Mobile
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for MessageHeader
                        */

                        
                                    protected cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader localMessageHeader ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMessageHeaderTracker = false ;

                           public boolean isMessageHeaderSpecified(){
                               return localMessageHeaderTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.web_mobile.MessageHeader
                           */
                           public  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader getMessageHeader(){
                               return localMessageHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MessageHeader
                               */
                               public void setMessageHeader(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader param){
                            localMessageHeaderTracker = param != null;
                                   
                                            this.localMessageHeader=param;
                                    

                               }
                            

                        /**
                        * field for ReverLoanContract
                        */

                        
                                    protected cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment localReverLoanContract ;
                                

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.web_mobile.LoanContrPayment
                           */
                           public  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment getReverLoanContract(){
                               return localReverLoanContract;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReverLoanContract
                               */
                               public void setReverLoanContract(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment param){
                            
                                            this.localReverLoanContract=param;
                                    

                               }
                            

                        /**
                        * field for LtReverLoanContract
                        * This was an Array!
                        */

                        
                                    protected cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[] localLtReverLoanContract ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLtReverLoanContractTracker = false ;

                           public boolean isLtReverLoanContractSpecified(){
                               return localLtReverLoanContractTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.web_mobile.ReverseLoanContract[]
                           */
                           public  cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[] getLtReverLoanContract(){
                               return localLtReverLoanContract;
                           }

                           
                        


                               
                              /**
                               * validate the array for LtReverLoanContract
                               */
                              protected void validateLtReverLoanContract(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param LtReverLoanContract
                              */
                              public void setLtReverLoanContract(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[] param){
                              
                                   validateLtReverLoanContract(param);

                               localLtReverLoanContractTracker = param != null;
                                      
                                      this.localLtReverLoanContract=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param com.lautaro.xi.bs.web_mobile.ReverseLoanContract
                             */
                             public void addLtReverLoanContract(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract param){
                                   if (localLtReverLoanContract == null){
                                   localLtReverLoanContract = new cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[]{};
                                   }

                            
                                 //update the setting tracker
                                localLtReverLoanContractTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localLtReverLoanContract);
                               list.add(param);
                               this.localLtReverLoanContract =
                             (cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[])list.toArray(
                            new cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://lautaro.com/xi/BS/WEB-Mobile");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ReverLoanContractRequest",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ReverLoanContractRequest",
                           xmlWriter);
                   }

               
                   }
                if (localMessageHeaderTracker){
                                            if (localMessageHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                            }
                                           localMessageHeader.serialize(new javax.xml.namespace.QName("","MessageHeader"),
                                               xmlWriter);
                                        }
                                            if (localReverLoanContract==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ReverLoanContract cannot be null!!");
                                            }
                                           localReverLoanContract.serialize(new javax.xml.namespace.QName("","ReverLoanContract"),
                                               xmlWriter);
                                         if (localLtReverLoanContractTracker){
                                       if (localLtReverLoanContract!=null){
                                            for (int i = 0;i < localLtReverLoanContract.length;i++){
                                                if (localLtReverLoanContract[i] != null){
                                                 localLtReverLoanContract[i].serialize(new javax.xml.namespace.QName("","LtReverLoanContract"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("LtReverLoanContract cannot be null!!");
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://lautaro.com/xi/BS/WEB-Mobile")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localMessageHeaderTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "MessageHeader"));
                            
                            
                                    if (localMessageHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                    }
                                    elementList.add(localMessageHeader);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReverLoanContract"));
                            
                            
                                    if (localReverLoanContract==null){
                                         throw new org.apache.axis2.databinding.ADBException("ReverLoanContract cannot be null!!");
                                    }
                                    elementList.add(localReverLoanContract);
                                 if (localLtReverLoanContractTracker){
                             if (localLtReverLoanContract!=null) {
                                 for (int i = 0;i < localLtReverLoanContract.length;i++){

                                    if (localLtReverLoanContract[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "LtReverLoanContract"));
                                         elementList.add(localLtReverLoanContract[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("LtReverLoanContract cannot be null!!");
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ReverLoanContractRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ReverLoanContractRequest object =
                new ReverLoanContractRequest();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ReverLoanContractRequest".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ReverLoanContractRequest)cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MessageHeader").equals(reader.getName())){
                                
                                                object.setMessageHeader(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReverLoanContract").equals(reader.getName())){
                                
                                                object.setReverLoanContract(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","LtReverLoanContract").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list3.add(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone3 = false;
                                                        while(!loopDone3){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone3 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","LtReverLoanContract").equals(reader.getName())){
                                                                    list3.add(cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setLtReverLoanContract((cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.ReverseLoanContract.class,
                                                                list3));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    