
/**
 * QueryContractCashFlowResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile;
            

            /**
            *  QueryContractCashFlowResponse bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class QueryContractCashFlowResponse
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = QueryContractCashFlowResponse
                Namespace URI = http://lautaro.com/xi/BS/WEB-Mobile
                Namespace Prefix = ns2
                */
            

                        /**
                        * field for MessageHeader
                        */

                        
                                    protected cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader localMessageHeader ;
                                

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.treasury.MessageHeader
                           */
                           public  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader getMessageHeader(){
                               return localMessageHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MessageHeader
                               */
                               public void setMessageHeader(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader param){
                            
                                            this.localMessageHeader=param;
                                    

                               }
                            

                        /**
                        * field for NroCuenta
                        */

                        
                                    protected java.lang.String localNroCuenta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNroCuentaTracker = false ;

                           public boolean isNroCuentaSpecified(){
                               return localNroCuentaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNroCuenta(){
                               return localNroCuenta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NroCuenta
                               */
                               public void setNroCuenta(java.lang.String param){
                            localNroCuentaTracker = param != null;
                                   
                                            this.localNroCuenta=param;
                                    

                               }
                            

                        /**
                        * field for LineaComercial
                        */

                        
                                    protected java.lang.String localLineaComercial ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLineaComercialTracker = false ;

                           public boolean isLineaComercialSpecified(){
                               return localLineaComercialTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLineaComercial(){
                               return localLineaComercial;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LineaComercial
                               */
                               public void setLineaComercial(java.lang.String param){
                            localLineaComercialTracker = param != null;
                                   
                                            this.localLineaComercial=param;
                                    

                               }
                            

                        /**
                        * field for DetalleCuotas
                        * This was an Array!
                        */

                        
                                    protected cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[] localDetalleCuotas ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDetalleCuotasTracker = false ;

                           public boolean isDetalleCuotasSpecified(){
                               return localDetalleCuotasTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.web_mobile.DetalleCuotasCF[]
                           */
                           public  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[] getDetalleCuotas(){
                               return localDetalleCuotas;
                           }

                           
                        


                               
                              /**
                               * validate the array for DetalleCuotas
                               */
                              protected void validateDetalleCuotas(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param DetalleCuotas
                              */
                              public void setDetalleCuotas(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[] param){
                              
                                   validateDetalleCuotas(param);

                               localDetalleCuotasTracker = param != null;
                                      
                                      this.localDetalleCuotas=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param com.lautaro.xi.bs.web_mobile.DetalleCuotasCF
                             */
                             public void addDetalleCuotas(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF param){
                                   if (localDetalleCuotas == null){
                                   localDetalleCuotas = new cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[]{};
                                   }

                            
                                 //update the setting tracker
                                localDetalleCuotasTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localDetalleCuotas);
                               list.add(param);
                               this.localDetalleCuotas =
                             (cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[])list.toArray(
                            new cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[list.size()]);

                             }
                             

                        /**
                        * field for E_TOTAL_CUOTAS
                        */

                        
                                    protected java.lang.String localE_TOTAL_CUOTAS ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localE_TOTAL_CUOTASTracker = false ;

                           public boolean isE_TOTAL_CUOTASSpecified(){
                               return localE_TOTAL_CUOTASTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getE_TOTAL_CUOTAS(){
                               return localE_TOTAL_CUOTAS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_TOTAL_CUOTAS
                               */
                               public void setE_TOTAL_CUOTAS(java.lang.String param){
                            localE_TOTAL_CUOTASTracker = param != null;
                                   
                                            this.localE_TOTAL_CUOTAS=param;
                                    

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
                        */

                        
                                    protected cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.Log localLog ;
                                

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.bs.treasury.Log
                           */
                           public  cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.Log getLog(){
                               return localLog;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Log
                               */
                               public void setLog(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.Log param){
                            
                                            this.localLog=param;
                                    

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
                           namespacePrefix+":QueryContractCashFlowResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "QueryContractCashFlowResponse",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localMessageHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                            }
                                           localMessageHeader.serialize(new javax.xml.namespace.QName("","MessageHeader"),
                                               xmlWriter);
                                         if (localNroCuentaTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "NroCuenta", xmlWriter);
                             

                                          if (localNroCuenta==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("NroCuenta cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNroCuenta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLineaComercialTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "LineaComercial", xmlWriter);
                             

                                          if (localLineaComercial==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("LineaComercial cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLineaComercial);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDetalleCuotasTracker){
                                       if (localDetalleCuotas!=null){
                                            for (int i = 0;i < localDetalleCuotas.length;i++){
                                                if (localDetalleCuotas[i] != null){
                                                 localDetalleCuotas[i].serialize(new javax.xml.namespace.QName("","DetalleCuotas"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("DetalleCuotas cannot be null!!");
                                        
                                    }
                                 } if (localE_TOTAL_CUOTASTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "E_TOTAL_CUOTAS", xmlWriter);
                             

                                          if (localE_TOTAL_CUOTAS==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("E_TOTAL_CUOTAS cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localE_TOTAL_CUOTAS);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
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
                             
                                            if (localLog==null){
                                                 throw new org.apache.axis2.databinding.ADBException("Log cannot be null!!");
                                            }
                                           localLog.serialize(new javax.xml.namespace.QName("","Log"),
                                               xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://lautaro.com/xi/BS/WEB-Mobile")){
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
                                 if (localNroCuentaTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "NroCuenta"));
                                 
                                        if (localNroCuenta != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNroCuenta));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("NroCuenta cannot be null!!");
                                        }
                                    } if (localLineaComercialTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "LineaComercial"));
                                 
                                        if (localLineaComercial != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLineaComercial));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("LineaComercial cannot be null!!");
                                        }
                                    } if (localDetalleCuotasTracker){
                             if (localDetalleCuotas!=null) {
                                 for (int i = 0;i < localDetalleCuotas.length;i++){

                                    if (localDetalleCuotas[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "DetalleCuotas"));
                                         elementList.add(localDetalleCuotas[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("DetalleCuotas cannot be null!!");
                                    
                             }

                        } if (localE_TOTAL_CUOTASTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "E_TOTAL_CUOTAS"));
                                 
                                        if (localE_TOTAL_CUOTAS != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localE_TOTAL_CUOTAS));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("E_TOTAL_CUOTAS cannot be null!!");
                                        }
                                    }
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ResultCode"));
                                 
                                        if (localResultCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "Log"));
                            
                            
                                    if (localLog==null){
                                         throw new org.apache.axis2.databinding.ADBException("Log cannot be null!!");
                                    }
                                    elementList.add(localLog);
                                

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
        public static QueryContractCashFlowResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            QueryContractCashFlowResponse object =
                new QueryContractCashFlowResponse();

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
                    
                            if (!"QueryContractCashFlowResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (QueryContractCashFlowResponse)cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list4 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MessageHeader").equals(reader.getName())){
                                
                                                object.setMessageHeader(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.MessageHeader.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","NroCuenta").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"NroCuenta" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNroCuenta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","LineaComercial").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"LineaComercial" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLineaComercial(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","DetalleCuotas").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list4.add(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF.Factory.parse(reader));
                                                                
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
                                                                if (new javax.xml.namespace.QName("","DetalleCuotas").equals(reader.getName())){
                                                                    list4.add(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone4 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setDetalleCuotas((cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.web_mobile.DetalleCuotasCF.class,
                                                                list4));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_TOTAL_CUOTAS").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"E_TOTAL_CUOTAS" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setE_TOTAL_CUOTAS(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
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
                                
                                                object.setLog(cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractCashflowIn.treasury.Log.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
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
           
    