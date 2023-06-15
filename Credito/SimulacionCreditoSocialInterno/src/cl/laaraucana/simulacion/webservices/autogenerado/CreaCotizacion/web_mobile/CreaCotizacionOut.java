
/**
 * CreaCotizacionOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile;
            

            /**
            *  CreaCotizacionOut bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class CreaCotizacionOut
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = CreaCotizacionOut
                Namespace URI = http://lautaro.com/xi/CRM/WEB-Mobile
                Namespace Prefix = ns2
                */
            

                        /**
                        * field for BUSINESS_PARTNER
                        */

                        
                                    protected java.lang.String localBUSINESS_PARTNER ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBUSINESS_PARTNER(){
                               return localBUSINESS_PARTNER;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BUSINESS_PARTNER
                               */
                               public void setBUSINESS_PARTNER(java.lang.String param){
                            
                                            this.localBUSINESS_PARTNER=param;
                                    

                               }
                            

                        /**
                        * field for NUMERO_COTIZACION
                        */

                        
                                    protected java.lang.String localNUMERO_COTIZACION ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNUMERO_COTIZACIONTracker = false ;

                           public boolean isNUMERO_COTIZACIONSpecified(){
                               return localNUMERO_COTIZACIONTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNUMERO_COTIZACION(){
                               return localNUMERO_COTIZACION;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NUMERO_COTIZACION
                               */
                               public void setNUMERO_COTIZACION(java.lang.String param){
                            localNUMERO_COTIZACIONTracker = param != null;
                                   
                                            this.localNUMERO_COTIZACION=param;
                                    

                               }
                            

                        /**
                        * field for TASA_INT_MENSUAL
                        */

                        
                                    protected java.lang.String localTASA_INT_MENSUAL ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTASA_INT_MENSUAL(){
                               return localTASA_INT_MENSUAL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TASA_INT_MENSUAL
                               */
                               public void setTASA_INT_MENSUAL(java.lang.String param){
                            
                                            this.localTASA_INT_MENSUAL=param;
                                    

                               }
                            

                        /**
                        * field for TASA_INT_ANUAL
                        */

                        
                                    protected java.lang.String localTASA_INT_ANUAL ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTASA_INT_ANUAL(){
                               return localTASA_INT_ANUAL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TASA_INT_ANUAL
                               */
                               public void setTASA_INT_ANUAL(java.lang.String param){
                            
                                            this.localTASA_INT_ANUAL=param;
                                    

                               }
                            

                        /**
                        * field for SEG_DESGRAVAMEN
                        */

                        
                                    protected java.lang.String localSEG_DESGRAVAMEN ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSEG_DESGRAVAMEN(){
                               return localSEG_DESGRAVAMEN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SEG_DESGRAVAMEN
                               */
                               public void setSEG_DESGRAVAMEN(java.lang.String param){
                            
                                            this.localSEG_DESGRAVAMEN=param;
                                    

                               }
                            

                        /**
                        * field for SEG_CESANTIA
                        */

                        
                                    protected java.lang.String localSEG_CESANTIA ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSEG_CESANTIA(){
                               return localSEG_CESANTIA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SEG_CESANTIA
                               */
                               public void setSEG_CESANTIA(java.lang.String param){
                            
                                            this.localSEG_CESANTIA=param;
                                    

                               }
                            

                        /**
                        * field for LTE
                        */

                        
                                    protected java.lang.String localLTE ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLTE(){
                               return localLTE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LTE
                               */
                               public void setLTE(java.lang.String param){
                            
                                            this.localLTE=param;
                                    

                               }
                            

                        /**
                        * field for FACTOR_LTE
                        */

                        
                                    protected java.lang.String localFACTOR_LTE ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFACTOR_LTE(){
                               return localFACTOR_LTE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FACTOR_LTE
                               */
                               public void setFACTOR_LTE(java.lang.String param){
                            
                                            this.localFACTOR_LTE=param;
                                    

                               }
                            

                        /**
                        * field for GASTOS_NOTARIALES
                        */

                        
                                    protected java.lang.String localGASTOS_NOTARIALES ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getGASTOS_NOTARIALES(){
                               return localGASTOS_NOTARIALES;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param GASTOS_NOTARIALES
                               */
                               public void setGASTOS_NOTARIALES(java.lang.String param){
                            
                                            this.localGASTOS_NOTARIALES=param;
                                    

                               }
                            

                        /**
                        * field for MONTO_MAXIMO
                        */

                        
                                    protected java.lang.String localMONTO_MAXIMO ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMONTO_MAXIMO(){
                               return localMONTO_MAXIMO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MONTO_MAXIMO
                               */
                               public void setMONTO_MAXIMO(java.lang.String param){
                            
                                            this.localMONTO_MAXIMO=param;
                                    

                               }
                            

                        /**
                        * field for MONTO_CUOTA_MAX
                        */

                        
                                    protected java.lang.String localMONTO_CUOTA_MAX ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMONTO_CUOTA_MAX(){
                               return localMONTO_CUOTA_MAX;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MONTO_CUOTA_MAX
                               */
                               public void setMONTO_CUOTA_MAX(java.lang.String param){
                            
                                            this.localMONTO_CUOTA_MAX=param;
                                    

                               }
                            

                        /**
                        * field for CUMPLE_CONDICIONES
                        */

                        
                                    protected java.lang.String localCUMPLE_CONDICIONES ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCUMPLE_CONDICIONES(){
                               return localCUMPLE_CONDICIONES;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CUMPLE_CONDICIONES
                               */
                               public void setCUMPLE_CONDICIONES(java.lang.String param){
                            
                                            this.localCUMPLE_CONDICIONES=param;
                                    

                               }
                            

                        /**
                        * field for TasaInteres
                        * This was an Array!
                        */

                        
                                    protected cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[] localTasaInteres ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTasaInteresTracker = false ;

                           public boolean isTasaInteresSpecified(){
                               return localTasaInteresTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.lautaro.xi.crm.web_mobile.TasaInteres[]
                           */
                           public  cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[] getTasaInteres(){
                               return localTasaInteres;
                           }

                           
                        


                               
                              /**
                               * validate the array for TasaInteres
                               */
                              protected void validateTasaInteres(cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param TasaInteres
                              */
                              public void setTasaInteres(cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[] param){
                              
                                   validateTasaInteres(param);

                               localTasaInteresTracker = param != null;
                                      
                                      this.localTasaInteres=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param com.lautaro.xi.crm.web_mobile.TasaInteres
                             */
                             public void addTasaInteres(cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres param){
                                   if (localTasaInteres == null){
                                   localTasaInteres = new cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[]{};
                                   }

                            
                                 //update the setting tracker
                                localTasaInteresTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTasaInteres);
                               list.add(param);
                               this.localTasaInteres =
                             (cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[])list.toArray(
                            new cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[list.size()]);

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
                           namespacePrefix+":CreaCotizacionOut",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "CreaCotizacionOut",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "";
                                    writeStartElement(null, namespace, "BUSINESS_PARTNER", xmlWriter);
                             

                                          if (localBUSINESS_PARTNER==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BUSINESS_PARTNER cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBUSINESS_PARTNER);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localNUMERO_COTIZACIONTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "NUMERO_COTIZACION", xmlWriter);
                             

                                          if (localNUMERO_COTIZACION==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("NUMERO_COTIZACION cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNUMERO_COTIZACION);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "";
                                    writeStartElement(null, namespace, "TASA_INT_MENSUAL", xmlWriter);
                             

                                          if (localTASA_INT_MENSUAL==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TASA_INT_MENSUAL cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTASA_INT_MENSUAL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "TASA_INT_ANUAL", xmlWriter);
                             

                                          if (localTASA_INT_ANUAL==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TASA_INT_ANUAL cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTASA_INT_ANUAL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "SEG_DESGRAVAMEN", xmlWriter);
                             

                                          if (localSEG_DESGRAVAMEN==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SEG_DESGRAVAMEN cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSEG_DESGRAVAMEN);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "SEG_CESANTIA", xmlWriter);
                             

                                          if (localSEG_CESANTIA==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SEG_CESANTIA cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSEG_CESANTIA);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "LTE", xmlWriter);
                             

                                          if (localLTE==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("LTE cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLTE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "FACTOR_LTE", xmlWriter);
                             

                                          if (localFACTOR_LTE==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("FACTOR_LTE cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFACTOR_LTE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "GASTOS_NOTARIALES", xmlWriter);
                             

                                          if (localGASTOS_NOTARIALES==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("GASTOS_NOTARIALES cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localGASTOS_NOTARIALES);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "MONTO_MAXIMO", xmlWriter);
                             

                                          if (localMONTO_MAXIMO==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("MONTO_MAXIMO cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMONTO_MAXIMO);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "MONTO_CUOTA_MAX", xmlWriter);
                             

                                          if (localMONTO_CUOTA_MAX==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("MONTO_CUOTA_MAX cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMONTO_CUOTA_MAX);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    writeStartElement(null, namespace, "CUMPLE_CONDICIONES", xmlWriter);
                             

                                          if (localCUMPLE_CONDICIONES==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CUMPLE_CONDICIONES cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCUMPLE_CONDICIONES);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localTasaInteresTracker){
                                       if (localTasaInteres!=null){
                                            for (int i = 0;i < localTasaInteres.length;i++){
                                                if (localTasaInteres[i] != null){
                                                 localTasaInteres[i].serialize(new javax.xml.namespace.QName("","TasaInteres"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("TasaInteres cannot be null!!");
                                        
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
                                                                      "BUSINESS_PARTNER"));
                                 
                                        if (localBUSINESS_PARTNER != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBUSINESS_PARTNER));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BUSINESS_PARTNER cannot be null!!");
                                        }
                                     if (localNUMERO_COTIZACIONTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "NUMERO_COTIZACION"));
                                 
                                        if (localNUMERO_COTIZACION != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNUMERO_COTIZACION));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("NUMERO_COTIZACION cannot be null!!");
                                        }
                                    }
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TASA_INT_MENSUAL"));
                                 
                                        if (localTASA_INT_MENSUAL != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTASA_INT_MENSUAL));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TASA_INT_MENSUAL cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TASA_INT_ANUAL"));
                                 
                                        if (localTASA_INT_ANUAL != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTASA_INT_ANUAL));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TASA_INT_ANUAL cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SEG_DESGRAVAMEN"));
                                 
                                        if (localSEG_DESGRAVAMEN != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSEG_DESGRAVAMEN));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SEG_DESGRAVAMEN cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SEG_CESANTIA"));
                                 
                                        if (localSEG_CESANTIA != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSEG_CESANTIA));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SEG_CESANTIA cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "LTE"));
                                 
                                        if (localLTE != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLTE));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("LTE cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "FACTOR_LTE"));
                                 
                                        if (localFACTOR_LTE != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFACTOR_LTE));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("FACTOR_LTE cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "GASTOS_NOTARIALES"));
                                 
                                        if (localGASTOS_NOTARIALES != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGASTOS_NOTARIALES));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("GASTOS_NOTARIALES cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "MONTO_MAXIMO"));
                                 
                                        if (localMONTO_MAXIMO != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMONTO_MAXIMO));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("MONTO_MAXIMO cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "MONTO_CUOTA_MAX"));
                                 
                                        if (localMONTO_CUOTA_MAX != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMONTO_CUOTA_MAX));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("MONTO_CUOTA_MAX cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CUMPLE_CONDICIONES"));
                                 
                                        if (localCUMPLE_CONDICIONES != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCUMPLE_CONDICIONES));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CUMPLE_CONDICIONES cannot be null!!");
                                        }
                                     if (localTasaInteresTracker){
                             if (localTasaInteres!=null) {
                                 for (int i = 0;i < localTasaInteres.length;i++){

                                    if (localTasaInteres[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "TasaInteres"));
                                         elementList.add(localTasaInteres[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("TasaInteres cannot be null!!");
                                    
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
        public static CreaCotizacionOut parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            CreaCotizacionOut object =
                new CreaCotizacionOut();

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
                    
                            if (!"CreaCotizacionOut".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (CreaCotizacionOut)cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list13 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BUSINESS_PARTNER").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"BUSINESS_PARTNER" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBUSINESS_PARTNER(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","NUMERO_COTIZACION").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"NUMERO_COTIZACION" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNUMERO_COTIZACION(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TASA_INT_MENSUAL").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"TASA_INT_MENSUAL" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTASA_INT_MENSUAL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TASA_INT_ANUAL").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"TASA_INT_ANUAL" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTASA_INT_ANUAL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SEG_DESGRAVAMEN").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"SEG_DESGRAVAMEN" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSEG_DESGRAVAMEN(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SEG_CESANTIA").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"SEG_CESANTIA" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSEG_CESANTIA(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","LTE").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"LTE" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLTE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","FACTOR_LTE").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"FACTOR_LTE" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFACTOR_LTE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","GASTOS_NOTARIALES").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"GASTOS_NOTARIALES" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setGASTOS_NOTARIALES(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MONTO_MAXIMO").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"MONTO_MAXIMO" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMONTO_MAXIMO(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MONTO_CUOTA_MAX").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"MONTO_CUOTA_MAX" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMONTO_CUOTA_MAX(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CUMPLE_CONDICIONES").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"CUMPLE_CONDICIONES" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCUMPLE_CONDICIONES(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TasaInteres").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list13.add(cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone13 = false;
                                                        while(!loopDone13){
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
                                                                loopDone13 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","TasaInteres").equals(reader.getName())){
                                                                    list13.add(cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone13 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTasaInteres((cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.TasaInteres.class,
                                                                list13));
                                                            
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
           
    