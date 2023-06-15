
/**
 * ConsultaCreditoPreAprobResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile;
            

            /**
            *  ConsultaCreditoPreAprobResponse bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ConsultaCreditoPreAprobResponse
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ConsultaCreditoPreAprobResponse
                Namespace URI = http://lautaro.com/xi/CRM/WEB-Mobile
                Namespace Prefix = ns2
                */
            

                        /**
                        * field for MessageHeader
                        */

                        
                                    protected cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader localMessageHeader ;
                                

                           /**
                           * Auto generated getter method
                           * @return cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader
                           */
                           public  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader getMessageHeader(){
                               return localMessageHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MessageHeader
                               */
                               public void setMessageHeader(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader param){
                            
                                            this.localMessageHeader=param;
                                    

                               }
                            

                        /**
                        * field for CreditoEsp
                        * This was an Array!
                        */

                        
                                    protected cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[] localCreditoEsp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCreditoEspTracker = false ;

                           public boolean isCreditoEspSpecified(){
                               return localCreditoEspTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[]
                           */
                           public  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[] getCreditoEsp(){
                               return localCreditoEsp;
                           }

                           
                        


                               
                              /**
                               * validate the array for CreditoEsp
                               */
                              protected void validateCreditoEsp(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param CreditoEsp
                              */
                              public void setCreditoEsp(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[] param){
                              
                                   validateCreditoEsp(param);

                               localCreditoEspTracker = param != null;
                                      
                                      this.localCreditoEsp=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial
                             */
                             public void addCreditoEsp(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial param){
                                   if (localCreditoEsp == null){
                                   localCreditoEsp = new cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[]{};
                                   }

                            
                                 //update the setting tracker
                                localCreditoEspTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localCreditoEsp);
                               list.add(param);
                               this.localCreditoEsp =
                             (cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[])list.toArray(
                            new cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[list.size()]);

                             }
                             

                        /**
                        * field for ResultCode
                        */

                        
                                    protected java.lang.String localResultCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getResultCode(){
                               return localResultCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResultCode
                               */
                               public void setResultCode(java.lang.String param){
                            
                                            this.localResultCode=param;
                                    

                               }
                            

                        /**
                        * field for Log
                        * This was an Array!
                        */

                        
                                    protected cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[] localLog ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLogTracker = false ;

                           public boolean isLogSpecified(){
                               return localLogTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[]
                           */
                           public  cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[] getLog(){
                               return localLog;
                           }

                           
                        


                               
                              /**
                               * validate the array for Log
                               */
                              protected void validateLog(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Log
                              */
                              public void setLog(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[] param){
                              
                                   validateLog(param);

                               localLogTracker = param != null;
                                      
                                      this.localLog=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log
                             */
                             public void addLog(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log param){
                                   if (localLog == null){
                                   localLog = new cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[]{};
                                   }

                            
                                 //update the setting tracker
                                localLogTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localLog);
                               list.add(param);
                               this.localLog =
                             (cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[])list.toArray(
                            new cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[list.size()]);

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://lautaro.com/xi/CRM/WEB-Mobile");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ConsultaCreditoPreAprobResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ConsultaCreditoPreAprobResponse",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localMessageHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                            }
                                           localMessageHeader.serialize(new javax.xml.namespace.QName("","MessageHeader"),
                                               xmlWriter);
                                         if (localCreditoEspTracker){
                                       if (localCreditoEsp!=null){
                                            for (int i = 0;i < localCreditoEsp.length;i++){
                                                if (localCreditoEsp[i] != null){
                                                 localCreditoEsp[i].serialize(new javax.xml.namespace.QName("","CreditoEsp"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("CreditoEsp cannot be null!!");
                                        
                                    }
                                 }
                                    namespace = "";
                                    writeStartElement(null, namespace, "ResultCode", xmlWriter);
                             

                                          if (localResultCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localResultCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localLogTracker){
                                       if (localLog!=null){
                                            for (int i = 0;i < localLog.length;i++){
                                                if (localLog[i] != null){
                                                 localLog[i].serialize(new javax.xml.namespace.QName("","Log"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("Log cannot be null!!");
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://lautaro.com/xi/CRM/WEB-Mobile")){
                return "ns2";
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

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "MessageHeader"));
                            
                            
                                    if (localMessageHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                    }
                                    elementList.add(localMessageHeader);
                                 if (localCreditoEspTracker){
                             if (localCreditoEsp!=null) {
                                 for (int i = 0;i < localCreditoEsp.length;i++){

                                    if (localCreditoEsp[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "CreditoEsp"));
                                         elementList.add(localCreditoEsp[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("CreditoEsp cannot be null!!");
                                    
                             }

                        }
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ResultCode"));
                                 
                                        if (localResultCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");
                                        }
                                     if (localLogTracker){
                             if (localLog!=null) {
                                 for (int i = 0;i < localLog.length;i++){

                                    if (localLog[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "Log"));
                                         elementList.add(localLog[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("Log cannot be null!!");
                                    
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
        public static ConsultaCreditoPreAprobResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ConsultaCreditoPreAprobResponse object =
                new ConsultaCreditoPreAprobResponse();

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
                    
                            if (!"ConsultaCreditoPreAprobResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ConsultaCreditoPreAprobResponse)cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list4 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MessageHeader").equals(reader.getName())){
                                
                                                object.setMessageHeader(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CreditoEsp").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list2.add(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
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
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","CreditoEsp").equals(reader.getName())){
                                                                    list2.add(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setCreditoEsp((cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ResultCode").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"ResultCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setResultCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","Log").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list4.add(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone4 = false;
                                                        while(!loopDone4){
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
                                                                loopDone4 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","Log").equals(reader.getName())){
                                                                    list4.add(cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone4 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setLog((cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.Log.class,
                                                                list4));
                                                            
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
           
    