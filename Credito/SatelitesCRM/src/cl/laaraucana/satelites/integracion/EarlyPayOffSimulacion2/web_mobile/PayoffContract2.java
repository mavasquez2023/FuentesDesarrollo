
/**
 * PayoffContract2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile;
            

                import java.util.*;
                import javax.xml.namespace.NamespaceContext;
                import javax.xml.namespace.QName;
                import javax.xml.stream.*;
                import org.apache.axiom.om.OMElement;
                import org.apache.axiom.om.OMFactory;
                import org.apache.axis2.databinding.*;
                import org.apache.axis2.databinding.utils.BeanUtil;
                import org.apache.axis2.databinding.utils.ConverterUtil;
                import org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl;

                // Referenced classes of package cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile:
//                            DetalleCuotasSIMULATION_IN_2, ExtensionMapper

                public class PayoffContract2
                    implements ADBBean
                {
                    public static class Factory
                    {

                        public static PayoffContract2 parse(XMLStreamReader reader)
                            throws Exception
                        {
                            PayoffContract2 object;
                            object = new PayoffContract2();
                            String nillableValue = null;
                            String prefix = "";
                            String namespaceuri = "";
                            Vector handledAttributes;
                            ArrayList list19;
                            String content;
                            boolean loopDone19;
                            try
                            {
                                for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                                if(reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
                                {
                                    String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
                                    if(fullTypeName != null)
                                    {
                                        String nsPrefix = null;
                                        if(fullTypeName.indexOf(":") > -1)
                                            nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                                        nsPrefix = nsPrefix != null ? nsPrefix : "";
                                        String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);
                                        if(!"PayoffContract2".equals(type))
                                        {
                                            String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                            return (PayoffContract2)ExtensionMapper.getTypeObject(nsUri, type, reader);
                                        }
                                    }
                                }
                            }
                            catch(XMLStreamException e)
                            {
                                throw new Exception(e);
                            }
                            handledAttributes = new Vector();
                            reader.next();
                            list19 = new ArrayList();
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CUOTA_HASTA")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CUOTA_HASTA  cannot be null");
                                content = reader.getElementText();
                                object.setCUOTA_HASTA(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "GASTO_COBRANZA")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: GASTO_COBRANZA  cannot be null");
                                content = reader.getElementText();
                                object.setGASTO_COBRANZA(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_CUOTA_INF")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_CUOTA_INF  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_CUOTA_INF(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_DIFERIDO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_DIFERIDO  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_DIFERIDO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "SEGURO_DESGRAV")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: SEGURO_DESGRAV  cannot be null");
                                content = reader.getElementText();
                                object.setSEGURO_DESGRAV(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "SEGURO_CESANT")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: SEGURO_CESANT  cannot be null");
                                content = reader.getElementText();
                                object.setSEGURO_CESANT(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_COM_PREP")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_COM_PREP  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_COM_PREP(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_INT_MOROSO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_INT_MOROSO  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_INT_MOROSO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_INT_DVG")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_INT_DVG  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_INT_DVG(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CUOTAS_MOROSAS")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CUOTAS_MOROSAS  cannot be null");
                                content = reader.getElementText();
                                object.setCUOTAS_MOROSAS(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "TIPO_BP")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: TIPO_BP  cannot be null");
                                content = reader.getElementText();
                                object.setTIPO_BP(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "LINEA_CREDITO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: LINEA_CREDITO  cannot be null");
                                content = reader.getElementText();
                                object.setLINEA_CREDITO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MOROSO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MOROSO  cannot be null");
                                content = reader.getElementText();
                                object.setMOROSO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CUOTA_DESDE")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CUOTA_DESDE  cannot be null");
                                content = reader.getElementText();
                                object.setCUOTA_DESDE(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CUOT_TRANSITO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CUOT_TRANSITO  cannot be null");
                                content = reader.getElementText();
                                object.setCUOT_TRANSITO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "NUM_CUOT_TRANSITO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: NUM_CUOT_TRANSITO  cannot be null");
                                content = reader.getElementText();
                                object.setNUM_CUOT_TRANSITO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CUOTA_INFORM")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CUOTA_INFORM  cannot be null");
                                content = reader.getElementText();
                                object.setCUOTA_INFORM(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTOCUOTA")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTOCUOTA  cannot be null");
                                content = reader.getElementText();
                                object.setMONTOCUOTA(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "HONORARIOS")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: HONORARIOS  cannot be null");
                                content = reader.getElementText();
                                object.setHONORARIOS(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_SEG_DVG")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_SEG_DVG  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_SEG_DVG(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_SEG_MOROSO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_SEG_MOROSO  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_SEG_MOROSO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "DetalleCuotas")).equals(reader.getName()))
                            {
                                list19.add(DetalleCuotasSIMULATION_IN_2.Factory.parse(reader));
                                for(loopDone19 = false; !loopDone19;)
                                {
                                    while(!reader.isEndElement()) 
                                        reader.next();
                                    reader.next();
                                    for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                                    if(reader.isEndElement())
                                        loopDone19 = true;
                                    else
                                    if((new QName("", "DetalleCuotas")).equals(reader.getName()))
                                        list19.add(DetalleCuotasSIMULATION_IN_2.Factory.parse(reader));
                                    else
                                        loopDone19 = true;
                                }

                                object.setDetalleCuotas((DetalleCuotasSIMULATION_IN_2[])ConverterUtil.convertToArray(cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.DetalleCuotasSIMULATION_IN_2.class, list19));
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "ARREARS_AMOUNT")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: ARREARS_AMOUNT  cannot be null");
                                content = reader.getElementText();
                                object.setARREARS_AMOUNT(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "CONTRACT_ID")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: CONTRACT_ID  cannot be null");
                                content = reader.getElementText();
                                object.setCONTRACT_ID(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "MONTO_EPO")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: MONTO_EPO  cannot be null");
                                content = reader.getElementText();
                                object.setMONTO_EPO(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "PAYMENT_DATE")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: PAYMENT_DATE  cannot be null");
                                content = reader.getElementText();
                                object.setPAYMENT_DATE(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "REMAINING_BALANCE")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: REMAINING_BALANCE  cannot be null");
                                content = reader.getElementText();
                                object.setREMAINING_BALANCE(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "UNPAID_CHARGE")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: UNPAID_CHARGE  cannot be null");
                                content = reader.getElementText();
                                object.setUNPAID_CHARGE(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement() && (new QName("", "WAIVER_RATE")).equals(reader.getName()))
                            {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                                if("true".equals(nillableValue) || "1".equals(nillableValue))
                                    throw new ADBException("The element: WAIVER_RATE  cannot be null");
                                content = reader.getElementText();
                                object.setWAIVER_RATE(ConverterUtil.convertToString(content));
                                reader.next();
                            } else
                            {
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            }
                            for(; !reader.isStartElement() && !reader.isEndElement(); reader.next());
                            if(reader.isStartElement())
                                throw new ADBException((new StringBuilder("Unexpected subelement ")).append(reader.getName()).toString());
                            return object;
                        }

                        public Factory()
                        {
                        }
                    }


                    public PayoffContract2()
                    {
                        localDetalleCuotasTracker = false;
                    }

                    public String getCUOTA_HASTA()
                    {
                        return localCUOTA_HASTA;
                    }

                    public void setCUOTA_HASTA(String param)
                    {
                        localCUOTA_HASTA = param;
                    }

                    public String getGASTO_COBRANZA()
                    {
                        return localGASTO_COBRANZA;
                    }

                    public void setGASTO_COBRANZA(String param)
                    {
                        localGASTO_COBRANZA = param;
                    }

                    public String getMONTO_CUOTA_INF()
                    {
                        return localMONTO_CUOTA_INF;
                    }

                    public void setMONTO_CUOTA_INF(String param)
                    {
                        localMONTO_CUOTA_INF = param;
                    }

                    public String getMONTO_DIFERIDO()
                    {
                        return localMONTO_DIFERIDO;
                    }

                    public void setMONTO_DIFERIDO(String param)
                    {
                        localMONTO_DIFERIDO = param;
                    }

                    public String getSEGURO_DESGRAV()
                    {
                        return localSEGURO_DESGRAV;
                    }

                    public void setSEGURO_DESGRAV(String param)
                    {
                        localSEGURO_DESGRAV = param;
                    }

                    public String getSEGURO_CESANT()
                    {
                        return localSEGURO_CESANT;
                    }

                    public void setSEGURO_CESANT(String param)
                    {
                        localSEGURO_CESANT = param;
                    }

                    public String getMONTO_COM_PREP()
                    {
                        return localMONTO_COM_PREP;
                    }

                    public void setMONTO_COM_PREP(String param)
                    {
                        localMONTO_COM_PREP = param;
                    }

                    public String getMONTO_INT_MOROSO()
                    {
                        return localMONTO_INT_MOROSO;
                    }

                    public void setMONTO_INT_MOROSO(String param)
                    {
                        localMONTO_INT_MOROSO = param;
                    }

                    public String getMONTO_INT_DVG()
                    {
                        return localMONTO_INT_DVG;
                    }

                    public void setMONTO_INT_DVG(String param)
                    {
                        localMONTO_INT_DVG = param;
                    }

                    public String getCUOTAS_MOROSAS()
                    {
                        return localCUOTAS_MOROSAS;
                    }

                    public void setCUOTAS_MOROSAS(String param)
                    {
                        localCUOTAS_MOROSAS = param;
                    }

                    public String getTIPO_BP()
                    {
                        return localTIPO_BP;
                    }

                    public void setTIPO_BP(String param)
                    {
                        localTIPO_BP = param;
                    }

                    public String getLINEA_CREDITO()
                    {
                        return localLINEA_CREDITO;
                    }

                    public void setLINEA_CREDITO(String param)
                    {
                        localLINEA_CREDITO = param;
                    }

                    public String getMOROSO()
                    {
                        return localMOROSO;
                    }

                    public void setMOROSO(String param)
                    {
                        localMOROSO = param;
                    }

                    public String getCUOTA_DESDE()
                    {
                        return localCUOTA_DESDE;
                    }

                    public void setCUOTA_DESDE(String param)
                    {
                        localCUOTA_DESDE = param;
                    }

                    public String getCUOT_TRANSITO()
                    {
                        return localCUOT_TRANSITO;
                    }

                    public void setCUOT_TRANSITO(String param)
                    {
                        localCUOT_TRANSITO = param;
                    }

                    public String getNUM_CUOT_TRANSITO()
                    {
                        return localNUM_CUOT_TRANSITO;
                    }

                    public void setNUM_CUOT_TRANSITO(String param)
                    {
                        localNUM_CUOT_TRANSITO = param;
                    }

                    public String getCUOTA_INFORM()
                    {
                        return localCUOTA_INFORM;
                    }

                    public void setCUOTA_INFORM(String param)
                    {
                        localCUOTA_INFORM = param;
                    }

                    public String getMONTOCUOTA()
                    {
                        return localMONTOCUOTA;
                    }

                    public void setMONTOCUOTA(String param)
                    {
                        localMONTOCUOTA = param;
                    }
                    
                    public String getHONORARIOS()
                    {
                        return localHONORARIOS;
                    }

                    public void setHONORARIOS(String param)
                    {
                        localHONORARIOS = param;
                    }
                    public String getMONTO_SEG_DVG()
                    {
                        return localMONTO_SEG_DVG;
                    }

                    public void setMONTO_SEG_DVG(String param)
                    {
                        localMONTO_SEG_DVG = param;
                    }
                    public String getMONTO_SEG_MOROSO()
                    {
                        return localMONTO_SEG_MOROSO;
                    }

                    public void setMONTO_SEG_MOROSO(String param)
                    {
                        localMONTO_SEG_MOROSO = param;
                    }
                    public boolean isDetalleCuotasSpecified()
                    {
                        return localDetalleCuotasTracker;
                    }

                    public DetalleCuotasSIMULATION_IN_2[] getDetalleCuotas()
                    {
                        return localDetalleCuotas;
                    }

                    protected void validateDetalleCuotas(DetalleCuotasSIMULATION_IN_2 adetallecuotassimulation_in_2[])
                    {
                    }

                    public void setDetalleCuotas(DetalleCuotasSIMULATION_IN_2 param[])
                    {
                        validateDetalleCuotas(param);
                        localDetalleCuotasTracker = param != null;
                        localDetalleCuotas = param;
                    }

                    public void addDetalleCuotas(DetalleCuotasSIMULATION_IN_2 param)
                    {
                        if(localDetalleCuotas == null)
                            localDetalleCuotas = new DetalleCuotasSIMULATION_IN_2[0];
                        localDetalleCuotasTracker = true;
                        List list = ConverterUtil.toList(localDetalleCuotas);
                        list.add(param);
                        localDetalleCuotas = (DetalleCuotasSIMULATION_IN_2[])list.toArray(new DetalleCuotasSIMULATION_IN_2[list.size()]);
                    }

                    public String getARREARS_AMOUNT()
                    {
                        return localARREARS_AMOUNT;
                    }

                    public void setARREARS_AMOUNT(String param)
                    {
                        localARREARS_AMOUNT = param;
                    }

                    public String getCONTRACT_ID()
                    {
                        return localCONTRACT_ID;
                    }

                    public void setCONTRACT_ID(String param)
                    {
                        localCONTRACT_ID = param;
                    }

                    public String getMONTO_EPO()
                    {
                        return localMONTO_EPO;
                    }

                    public void setMONTO_EPO(String param)
                    {
                        localMONTO_EPO = param;
                    }

                    public String getPAYMENT_DATE()
                    {
                        return localPAYMENT_DATE;
                    }

                    public void setPAYMENT_DATE(String param)
                    {
                        localPAYMENT_DATE = param;
                    }

                    public String getREMAINING_BALANCE()
                    {
                        return localREMAINING_BALANCE;
                    }

                    public void setREMAINING_BALANCE(String param)
                    {
                        localREMAINING_BALANCE = param;
                    }

                    public String getUNPAID_CHARGE()
                    {
                        return localUNPAID_CHARGE;
                    }

                    public void setUNPAID_CHARGE(String param)
                    {
                        localUNPAID_CHARGE = param;
                    }

                    public String getWAIVER_RATE()
                    {
                        return localWAIVER_RATE;
                    }

                    public void setWAIVER_RATE(String param)
                    {
                        localWAIVER_RATE = param;
                    }

                    public OMElement getOMElement(QName parentQName, OMFactory factory)
                        throws ADBException
                    {
                        org.apache.axiom.om.OMDataSource dataSource = new ADBDataSource(this, parentQName);
                        return factory.createOMElement(dataSource, parentQName);
                    }

                    public void serialize(QName parentQName, XMLStreamWriter xmlWriter)
                        throws XMLStreamException, ADBException
                    {
                        serialize(parentQName, xmlWriter, false);
                    }

                    public void serialize(QName parentQName, XMLStreamWriter xmlWriter, boolean serializeType)
                        throws XMLStreamException, ADBException
                    {
                        String prefix = null;
                        String namespace = null;
                        prefix = parentQName.getPrefix();
                        namespace = parentQName.getNamespaceURI();
                        writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                        if(serializeType)
                        {
                            String namespacePrefix = registerPrefix(xmlWriter, "http://lautaro.com/xi/BS/WEB-Mobile");
                            if(namespacePrefix != null && namespacePrefix.trim().length() > 0)
                                writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", (new StringBuilder(String.valueOf(namespacePrefix))).append(":PayoffContract2").toString(), xmlWriter);
                            else
                                writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "PayoffContract2", xmlWriter);
                        }
                        namespace = "";
                        writeStartElement(null, namespace, "CUOTA_HASTA", xmlWriter);
                        if(localCUOTA_HASTA == null)
                            throw new ADBException("CUOTA_HASTA cannot be null!!");
                        xmlWriter.writeCharacters(localCUOTA_HASTA);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "GASTO_COBRANZA", xmlWriter);
                        if(localGASTO_COBRANZA == null)
                            throw new ADBException("GASTO_COBRANZA cannot be null!!");
                        xmlWriter.writeCharacters(localGASTO_COBRANZA);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_CUOTA_INF", xmlWriter);
                        if(localMONTO_CUOTA_INF == null)
                            throw new ADBException("MONTO_CUOTA_INF cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_CUOTA_INF);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_DIFERIDO", xmlWriter);
                        if(localMONTO_DIFERIDO == null)
                            throw new ADBException("MONTO_DIFERIDO cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_DIFERIDO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "SEGURO_DESGRAV", xmlWriter);
                        if(localSEGURO_DESGRAV == null)
                            throw new ADBException("SEGURO_DESGRAV cannot be null!!");
                        xmlWriter.writeCharacters(localSEGURO_DESGRAV);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "SEGURO_CESANT", xmlWriter);
                        if(localSEGURO_CESANT == null)
                            throw new ADBException("SEGURO_CESANT cannot be null!!");
                        xmlWriter.writeCharacters(localSEGURO_CESANT);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_COM_PREP", xmlWriter);
                        if(localMONTO_COM_PREP == null)
                            throw new ADBException("MONTO_COM_PREP cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_COM_PREP);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_INT_MOROSO", xmlWriter);
                        if(localMONTO_INT_MOROSO == null)
                            throw new ADBException("MONTO_INT_MOROSO cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_INT_MOROSO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_INT_DVG", xmlWriter);
                        if(localMONTO_INT_DVG == null)
                            throw new ADBException("MONTO_INT_DVG cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_INT_DVG);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "CUOTAS_MOROSAS", xmlWriter);
                        if(localCUOTAS_MOROSAS == null)
                            throw new ADBException("CUOTAS_MOROSAS cannot be null!!");
                        xmlWriter.writeCharacters(localCUOTAS_MOROSAS);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "TIPO_BP", xmlWriter);
                        if(localTIPO_BP == null)
                            throw new ADBException("TIPO_BP cannot be null!!");
                        xmlWriter.writeCharacters(localTIPO_BP);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "LINEA_CREDITO", xmlWriter);
                        if(localLINEA_CREDITO == null)
                            throw new ADBException("LINEA_CREDITO cannot be null!!");
                        xmlWriter.writeCharacters(localLINEA_CREDITO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MOROSO", xmlWriter);
                        if(localMOROSO == null)
                            throw new ADBException("MOROSO cannot be null!!");
                        xmlWriter.writeCharacters(localMOROSO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "CUOTA_DESDE", xmlWriter);
                        if(localCUOTA_DESDE == null)
                            throw new ADBException("CUOTA_DESDE cannot be null!!");
                        xmlWriter.writeCharacters(localCUOTA_DESDE);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "CUOT_TRANSITO", xmlWriter);
                        if(localCUOT_TRANSITO == null)
                            throw new ADBException("CUOT_TRANSITO cannot be null!!");
                        xmlWriter.writeCharacters(localCUOT_TRANSITO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "NUM_CUOT_TRANSITO", xmlWriter);
                        if(localNUM_CUOT_TRANSITO == null)
                            throw new ADBException("NUM_CUOT_TRANSITO cannot be null!!");
                        xmlWriter.writeCharacters(localNUM_CUOT_TRANSITO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "CUOTA_INFORM", xmlWriter);
                        if(localCUOTA_INFORM == null)
                            throw new ADBException("CUOTA_INFORM cannot be null!!");
                        xmlWriter.writeCharacters(localCUOTA_INFORM);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTOCUOTA", xmlWriter);
                        if(localMONTOCUOTA == null)
                            throw new ADBException("MONTOCUOTA cannot be null!!");
                        xmlWriter.writeCharacters(localMONTOCUOTA);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "HONORARIOS", xmlWriter);
                        if(localHONORARIOS == null)
                            throw new ADBException("HONORARIOS cannot be null!!");
                        xmlWriter.writeCharacters(localHONORARIOS);
                        xmlWriter.writeEndElement();
                        
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_SEG_DVG", xmlWriter);
                        if(localMONTO_SEG_DVG == null)
                            throw new ADBException("MONTO_SEG_DVG cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_SEG_DVG);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_SEG_MOROSO", xmlWriter);
                        if(localMONTO_SEG_MOROSO == null)
                            throw new ADBException("MONTO_SEG_MOROSO cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_SEG_MOROSO);
                        xmlWriter.writeEndElement();
                        if(localDetalleCuotasTracker)
                            if(localDetalleCuotas != null)
                            {
                                for(int i = 0; i < localDetalleCuotas.length; i++)
                                    if(localDetalleCuotas[i] != null)
                                        localDetalleCuotas[i].serialize(new QName("", "DetalleCuotas"), xmlWriter);

                            } else
                            {
                                throw new ADBException("DetalleCuotas cannot be null!!");
                            }
                        namespace = "";
                        writeStartElement(null, namespace, "ARREARS_AMOUNT", xmlWriter);
                        if(localARREARS_AMOUNT == null)
                            throw new ADBException("ARREARS_AMOUNT cannot be null!!");
                        xmlWriter.writeCharacters(localARREARS_AMOUNT);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "CONTRACT_ID", xmlWriter);
                        if(localCONTRACT_ID == null)
                            throw new ADBException("CONTRACT_ID cannot be null!!");
                        xmlWriter.writeCharacters(localCONTRACT_ID);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "MONTO_EPO", xmlWriter);
                        if(localMONTO_EPO == null)
                            throw new ADBException("MONTO_EPO cannot be null!!");
                        xmlWriter.writeCharacters(localMONTO_EPO);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "PAYMENT_DATE", xmlWriter);
                        if(localPAYMENT_DATE == null)
                            throw new ADBException("PAYMENT_DATE cannot be null!!");
                        xmlWriter.writeCharacters(localPAYMENT_DATE);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "REMAINING_BALANCE", xmlWriter);
                        if(localREMAINING_BALANCE == null)
                            throw new ADBException("REMAINING_BALANCE cannot be null!!");
                        xmlWriter.writeCharacters(localREMAINING_BALANCE);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "UNPAID_CHARGE", xmlWriter);
                        if(localUNPAID_CHARGE == null)
                            throw new ADBException("UNPAID_CHARGE cannot be null!!");
                        xmlWriter.writeCharacters(localUNPAID_CHARGE);
                        xmlWriter.writeEndElement();
                        namespace = "";
                        writeStartElement(null, namespace, "WAIVER_RATE", xmlWriter);
                        if(localWAIVER_RATE == null)
                        {
                            throw new ADBException("WAIVER_RATE cannot be null!!");
                        } else
                        {
                            xmlWriter.writeCharacters(localWAIVER_RATE);
                            xmlWriter.writeEndElement();
                            xmlWriter.writeEndElement();
                            return;
                        }
                    }

                    private static String generatePrefix(String namespace)
                    {
                        if(namespace.equals("http://lautaro.com/xi/BS/WEB-Mobile"))
                            return "ns2";
                        else
                            return BeanUtil.getUniquePrefix();
                    }

                    private void writeStartElement(String prefix, String namespace, String localPart, XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        String writerPrefix = xmlWriter.getPrefix(namespace);
                        if(writerPrefix != null)
                        {
                            xmlWriter.writeStartElement(namespace, localPart);
                        } else
                        {
                            if(namespace.length() == 0)
                                prefix = "";
                            else
                            if(prefix == null)
                                prefix = generatePrefix(namespace);
                            xmlWriter.writeStartElement(prefix, localPart, namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    }

                    private void writeAttribute(String prefix, String namespace, String attName, String attValue, XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        if(xmlWriter.getPrefix(namespace) == null)
                        {
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                        xmlWriter.writeAttribute(namespace, attName, attValue);
                    }

                    private void writeAttribute(String namespace, String attName, String attValue, XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        if(namespace.equals(""))
                        {
                            xmlWriter.writeAttribute(attName, attValue);
                        } else
                        {
                            registerPrefix(xmlWriter, namespace);
                            xmlWriter.writeAttribute(namespace, attName, attValue);
                        }
                    }

                    private void writeQNameAttribute(String namespace, String attName, QName qname, XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        String attributeNamespace = qname.getNamespaceURI();
                        String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                        if(attributePrefix == null)
                            attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                        String attributeValue;
                        if(attributePrefix.trim().length() > 0)
                            attributeValue = (new StringBuilder(String.valueOf(attributePrefix))).append(":").append(qname.getLocalPart()).toString();
                        else
                            attributeValue = qname.getLocalPart();
                        if(namespace.equals(""))
                        {
                            xmlWriter.writeAttribute(attName, attributeValue);
                        } else
                        {
                            registerPrefix(xmlWriter, namespace);
                            xmlWriter.writeAttribute(namespace, attName, attributeValue);
                        }
                    }

                    private void writeQName(QName qname, XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        String namespaceURI = qname.getNamespaceURI();
                        if(namespaceURI != null)
                        {
                            String prefix = xmlWriter.getPrefix(namespaceURI);
                            if(prefix == null)
                            {
                                prefix = generatePrefix(namespaceURI);
                                xmlWriter.writeNamespace(prefix, namespaceURI);
                                xmlWriter.setPrefix(prefix, namespaceURI);
                            }
                            if(prefix.trim().length() > 0)
                                xmlWriter.writeCharacters((new StringBuilder(String.valueOf(prefix))).append(":").append(ConverterUtil.convertToString(qname)).toString());
                            else
                                xmlWriter.writeCharacters(ConverterUtil.convertToString(qname));
                        } else
                        {
                            xmlWriter.writeCharacters(ConverterUtil.convertToString(qname));
                        }
                    }

                    private void writeQNames(QName qnames[], XMLStreamWriter xmlWriter)
                        throws XMLStreamException
                    {
                        if(qnames != null)
                        {
                            StringBuffer stringToWrite = new StringBuffer();
                            String namespaceURI = null;
                            String prefix = null;
                            for(int i = 0; i < qnames.length; i++)
                            {
                                if(i > 0)
                                    stringToWrite.append(" ");
                                namespaceURI = qnames[i].getNamespaceURI();
                                if(namespaceURI != null)
                                {
                                    prefix = xmlWriter.getPrefix(namespaceURI);
                                    if(prefix == null || prefix.length() == 0)
                                    {
                                        prefix = generatePrefix(namespaceURI);
                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                    }
                                    if(prefix.trim().length() > 0)
                                        stringToWrite.append(prefix).append(":").append(ConverterUtil.convertToString(qnames[i]));
                                    else
                                        stringToWrite.append(ConverterUtil.convertToString(qnames[i]));
                                } else
                                {
                                    stringToWrite.append(ConverterUtil.convertToString(qnames[i]));
                                }
                            }

                            xmlWriter.writeCharacters(stringToWrite.toString());
                        }
                    }

                    private String registerPrefix(XMLStreamWriter xmlWriter, String namespace)
                        throws XMLStreamException
                    {
                        String prefix = xmlWriter.getPrefix(namespace);
                        if(prefix == null)
                        {
                            prefix = generatePrefix(namespace);
                            NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                            do
                            {
                                String uri = nsContext.getNamespaceURI(prefix);
                                if(uri == null || uri.length() == 0)
                                    break;
                                prefix = BeanUtil.getUniquePrefix();
                            } while(true);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                        return prefix;
                    }

                    public XMLStreamReader getPullParser(QName qName)
                        throws ADBException
                    {
                        ArrayList elementList = new ArrayList();
                        ArrayList attribList = new ArrayList();
                        elementList.add(new QName("", "CUOTA_HASTA"));
                        if(localCUOTA_HASTA != null)
                            elementList.add(ConverterUtil.convertToString(localCUOTA_HASTA));
                        else
                            throw new ADBException("CUOTA_HASTA cannot be null!!");
                        elementList.add(new QName("", "GASTO_COBRANZA"));
                        if(localGASTO_COBRANZA != null)
                            elementList.add(ConverterUtil.convertToString(localGASTO_COBRANZA));
                        else
                            throw new ADBException("GASTO_COBRANZA cannot be null!!");
                        elementList.add(new QName("", "MONTO_CUOTA_INF"));
                        if(localMONTO_CUOTA_INF != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_CUOTA_INF));
                        else
                            throw new ADBException("MONTO_CUOTA_INF cannot be null!!");
                        elementList.add(new QName("", "MONTO_DIFERIDO"));
                        if(localMONTO_DIFERIDO != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_DIFERIDO));
                        else
                            throw new ADBException("MONTO_DIFERIDO cannot be null!!");
                        elementList.add(new QName("", "SEGURO_DESGRAV"));
                        if(localSEGURO_DESGRAV != null)
                            elementList.add(ConverterUtil.convertToString(localSEGURO_DESGRAV));
                        else
                            throw new ADBException("SEGURO_DESGRAV cannot be null!!");
                        elementList.add(new QName("", "SEGURO_CESANT"));
                        if(localSEGURO_CESANT != null)
                            elementList.add(ConverterUtil.convertToString(localSEGURO_CESANT));
                        else
                            throw new ADBException("SEGURO_CESANT cannot be null!!");
                        elementList.add(new QName("", "MONTO_COM_PREP"));
                        if(localMONTO_COM_PREP != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_COM_PREP));
                        else
                            throw new ADBException("MONTO_COM_PREP cannot be null!!");
                        elementList.add(new QName("", "MONTO_INT_MOROSO"));
                        if(localMONTO_INT_MOROSO != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_INT_MOROSO));
                        else
                            throw new ADBException("MONTO_INT_MOROSO cannot be null!!");
                        elementList.add(new QName("", "MONTO_INT_DVG"));
                        if(localMONTO_INT_DVG != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_INT_DVG));
                        else
                            throw new ADBException("MONTO_INT_DVG cannot be null!!");
                        elementList.add(new QName("", "CUOTAS_MOROSAS"));
                        if(localCUOTAS_MOROSAS != null)
                            elementList.add(ConverterUtil.convertToString(localCUOTAS_MOROSAS));
                        else
                            throw new ADBException("CUOTAS_MOROSAS cannot be null!!");
                        elementList.add(new QName("", "TIPO_BP"));
                        if(localTIPO_BP != null)
                            elementList.add(ConverterUtil.convertToString(localTIPO_BP));
                        else
                            throw new ADBException("TIPO_BP cannot be null!!");
                        elementList.add(new QName("", "LINEA_CREDITO"));
                        if(localLINEA_CREDITO != null)
                            elementList.add(ConverterUtil.convertToString(localLINEA_CREDITO));
                        else
                            throw new ADBException("LINEA_CREDITO cannot be null!!");
                        elementList.add(new QName("", "MOROSO"));
                        if(localMOROSO != null)
                            elementList.add(ConverterUtil.convertToString(localMOROSO));
                        else
                            throw new ADBException("MOROSO cannot be null!!");
                        elementList.add(new QName("", "CUOTA_DESDE"));
                        if(localCUOTA_DESDE != null)
                            elementList.add(ConverterUtil.convertToString(localCUOTA_DESDE));
                        else
                            throw new ADBException("CUOTA_DESDE cannot be null!!");
                        elementList.add(new QName("", "CUOT_TRANSITO"));
                        if(localCUOT_TRANSITO != null)
                            elementList.add(ConverterUtil.convertToString(localCUOT_TRANSITO));
                        else
                            throw new ADBException("CUOT_TRANSITO cannot be null!!");
                        elementList.add(new QName("", "NUM_CUOT_TRANSITO"));
                        if(localNUM_CUOT_TRANSITO != null)
                            elementList.add(ConverterUtil.convertToString(localNUM_CUOT_TRANSITO));
                        else
                            throw new ADBException("NUM_CUOT_TRANSITO cannot be null!!");
                        elementList.add(new QName("", "CUOTA_INFORM"));
                        if(localCUOTA_INFORM != null)
                            elementList.add(ConverterUtil.convertToString(localCUOTA_INFORM));
                        else
                            throw new ADBException("CUOTA_INFORM cannot be null!!");
                        elementList.add(new QName("", "MONTOCUOTA"));
                        if(localMONTOCUOTA != null)
                            elementList.add(ConverterUtil.convertToString(localMONTOCUOTA));
                        else
                            throw new ADBException("MONTOCUOTA cannot be null!!");
                        elementList.add(new QName("", "HONORARIOS"));
                        if(localHONORARIOS != null)
                            elementList.add(ConverterUtil.convertToString(localHONORARIOS));
                        else
                            throw new ADBException("HONORARIOS cannot be null!!");
                        
                        elementList.add(new QName("", "MONTO_SEG_DVG"));
                        if(localMONTO_SEG_DVG != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_SEG_DVG));
                        else
                            throw new ADBException("MONTO_SEG_DVG cannot be null!!");
                        elementList.add(new QName("", "MONTO_SEG_MOROSO"));
                        if(localMONTO_SEG_MOROSO != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_SEG_MOROSO));
                        else
                            throw new ADBException("MONTO_SEG_MOROSO cannot be null!!");
                        if(localDetalleCuotasTracker)
                            if(localDetalleCuotas != null)
                            {
                                for(int i = 0; i < localDetalleCuotas.length; i++)
                                    if(localDetalleCuotas[i] != null)
                                    {
                                        elementList.add(new QName("", "DetalleCuotas"));
                                        elementList.add(localDetalleCuotas[i]);
                                    }

                            } else
                            {
                                throw new ADBException("DetalleCuotas cannot be null!!");
                            }
                        elementList.add(new QName("", "ARREARS_AMOUNT"));
                        if(localARREARS_AMOUNT != null)
                            elementList.add(ConverterUtil.convertToString(localARREARS_AMOUNT));
                        else
                            throw new ADBException("ARREARS_AMOUNT cannot be null!!");
                        elementList.add(new QName("", "CONTRACT_ID"));
                        if(localCONTRACT_ID != null)
                            elementList.add(ConverterUtil.convertToString(localCONTRACT_ID));
                        else
                            throw new ADBException("CONTRACT_ID cannot be null!!");
                        elementList.add(new QName("", "MONTO_EPO"));
                        if(localMONTO_EPO != null)
                            elementList.add(ConverterUtil.convertToString(localMONTO_EPO));
                        else
                            throw new ADBException("MONTO_EPO cannot be null!!");
                        elementList.add(new QName("", "PAYMENT_DATE"));
                        if(localPAYMENT_DATE != null)
                            elementList.add(ConverterUtil.convertToString(localPAYMENT_DATE));
                        else
                            throw new ADBException("PAYMENT_DATE cannot be null!!");
                        elementList.add(new QName("", "REMAINING_BALANCE"));
                        if(localREMAINING_BALANCE != null)
                            elementList.add(ConverterUtil.convertToString(localREMAINING_BALANCE));
                        else
                            throw new ADBException("REMAINING_BALANCE cannot be null!!");
                        elementList.add(new QName("", "UNPAID_CHARGE"));
                        if(localUNPAID_CHARGE != null)
                            elementList.add(ConverterUtil.convertToString(localUNPAID_CHARGE));
                        else
                            throw new ADBException("UNPAID_CHARGE cannot be null!!");
                        elementList.add(new QName("", "WAIVER_RATE"));
                        if(localWAIVER_RATE != null)
                            elementList.add(ConverterUtil.convertToString(localWAIVER_RATE));
                        else
                            throw new ADBException("WAIVER_RATE cannot be null!!");
                        return new ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
                    }

                    protected String localCUOTA_HASTA;
                    protected String localGASTO_COBRANZA;
                    protected String localMONTO_CUOTA_INF;
                    protected String localMONTO_DIFERIDO;
                    protected String localSEGURO_DESGRAV;
                    protected String localSEGURO_CESANT;
                    protected String localMONTO_COM_PREP;
                    protected String localMONTO_INT_MOROSO;
                    protected String localMONTO_INT_DVG;
                    protected String localCUOTAS_MOROSAS;
                    protected String localTIPO_BP;
                    protected String localLINEA_CREDITO;
                    protected String localMOROSO;
                    protected String localCUOTA_DESDE;
                    protected String localCUOT_TRANSITO;
                    protected String localNUM_CUOT_TRANSITO;
                    protected String localCUOTA_INFORM;
                    protected String localMONTOCUOTA;
                    protected String localHONORARIOS;
                    protected String localMONTO_SEG_DVG;
                    protected String localMONTO_SEG_MOROSO;
                    protected DetalleCuotasSIMULATION_IN_2 localDetalleCuotas[];
                    protected boolean localDetalleCuotasTracker;
                    protected String localARREARS_AMOUNT;
                    protected String localCONTRACT_ID;
                    protected String localMONTO_EPO;
                    protected String localPAYMENT_DATE;
                    protected String localREMAINING_BALANCE;
                    protected String localUNPAID_CHARGE;
                    protected String localWAIVER_RATE;
                }