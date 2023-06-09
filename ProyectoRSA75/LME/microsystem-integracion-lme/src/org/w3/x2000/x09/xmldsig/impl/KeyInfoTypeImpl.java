/*
 * XML Type:  KeyInfoType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.KeyInfoType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML KeyInfoType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class KeyInfoTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.KeyInfoType
{
    private static final long serialVersionUID = 1L;
    
    public KeyInfoTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYNAME$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
    private static final javax.xml.namespace.QName KEYVALUE$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
    private static final javax.xml.namespace.QName RETRIEVALMETHOD$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
    private static final javax.xml.namespace.QName X509DATA$6 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
    private static final javax.xml.namespace.QName PGPDATA$8 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
    private static final javax.xml.namespace.QName SPKIDATA$10 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
    private static final javax.xml.namespace.QName MGMTDATA$12 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
    private static final javax.xml.namespace.QName ID$14 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets array of all "KeyName" elements
     */
    public java.lang.String[] getKeyNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYNAME$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "KeyName" element
     */
    public java.lang.String getKeyNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYNAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "KeyName" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetKeyNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYNAME$0, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "KeyName" element
     */
    public org.apache.xmlbeans.XmlString xgetKeyNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYNAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "KeyName" element
     */
    public int sizeOfKeyNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYNAME$0);
        }
    }
    
    /**
     * Sets array of all "KeyName" element
     */
    public void setKeyNameArray(java.lang.String[] keyNameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(keyNameArray, KEYNAME$0);
        }
    }
    
    /**
     * Sets ith "KeyName" element
     */
    public void setKeyNameArray(int i, java.lang.String keyName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYNAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(keyName);
        }
    }
    
    /**
     * Sets (as xml) array of all "KeyName" element
     */
    public void xsetKeyNameArray(org.apache.xmlbeans.XmlString[]keyNameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(keyNameArray, KEYNAME$0);
        }
    }
    
    /**
     * Sets (as xml) ith "KeyName" element
     */
    public void xsetKeyNameArray(int i, org.apache.xmlbeans.XmlString keyName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYNAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(keyName);
        }
    }
    
    /**
     * Inserts the value as the ith "KeyName" element
     */
    public void insertKeyName(int i, java.lang.String keyName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(KEYNAME$0, i);
            target.setStringValue(keyName);
        }
    }
    
    /**
     * Appends the value as the last "KeyName" element
     */
    public void addKeyName(java.lang.String keyName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KEYNAME$0);
            target.setStringValue(keyName);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyName" element
     */
    public org.apache.xmlbeans.XmlString insertNewKeyName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(KEYNAME$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyName" element
     */
    public org.apache.xmlbeans.XmlString addNewKeyName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(KEYNAME$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "KeyName" element
     */
    public void removeKeyName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYNAME$0, i);
        }
    }
    
    /**
     * Gets array of all "KeyValue" elements
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType[] getKeyValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYVALUE$2, targetList);
            org.w3.x2000.x09.xmldsig.KeyValueType[] result = new org.w3.x2000.x09.xmldsig.KeyValueType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "KeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType getKeyValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().find_element_user(KEYVALUE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "KeyValue" element
     */
    public int sizeOfKeyValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYVALUE$2);
        }
    }
    
    /**
     * Sets array of all "KeyValue" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setKeyValueArray(org.w3.x2000.x09.xmldsig.KeyValueType[] keyValueArray)
    {
        check_orphaned();
        arraySetterHelper(keyValueArray, KEYVALUE$2);
    }
    
    /**
     * Sets ith "KeyValue" element
     */
    public void setKeyValueArray(int i, org.w3.x2000.x09.xmldsig.KeyValueType keyValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().find_element_user(KEYVALUE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(keyValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType insertNewKeyValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().insert_element_user(KEYVALUE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType addNewKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().add_element_user(KEYVALUE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "KeyValue" element
     */
    public void removeKeyValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYVALUE$2, i);
        }
    }
    
    /**
     * Gets array of all "RetrievalMethod" elements
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType[] getRetrievalMethodArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RETRIEVALMETHOD$4, targetList);
            org.w3.x2000.x09.xmldsig.RetrievalMethodType[] result = new org.w3.x2000.x09.xmldsig.RetrievalMethodType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "RetrievalMethod" element
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType getRetrievalMethodArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().find_element_user(RETRIEVALMETHOD$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "RetrievalMethod" element
     */
    public int sizeOfRetrievalMethodArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RETRIEVALMETHOD$4);
        }
    }
    
    /**
     * Sets array of all "RetrievalMethod" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setRetrievalMethodArray(org.w3.x2000.x09.xmldsig.RetrievalMethodType[] retrievalMethodArray)
    {
        check_orphaned();
        arraySetterHelper(retrievalMethodArray, RETRIEVALMETHOD$4);
    }
    
    /**
     * Sets ith "RetrievalMethod" element
     */
    public void setRetrievalMethodArray(int i, org.w3.x2000.x09.xmldsig.RetrievalMethodType retrievalMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().find_element_user(RETRIEVALMETHOD$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(retrievalMethod);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "RetrievalMethod" element
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType insertNewRetrievalMethod(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().insert_element_user(RETRIEVALMETHOD$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "RetrievalMethod" element
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType addNewRetrievalMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().add_element_user(RETRIEVALMETHOD$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "RetrievalMethod" element
     */
    public void removeRetrievalMethod(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RETRIEVALMETHOD$4, i);
        }
    }
    
    /**
     * Gets array of all "X509Data" elements
     */
    public org.w3.x2000.x09.xmldsig.X509DataType[] getX509DataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(X509DATA$6, targetList);
            org.w3.x2000.x09.xmldsig.X509DataType[] result = new org.w3.x2000.x09.xmldsig.X509DataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "X509Data" element
     */
    public org.w3.x2000.x09.xmldsig.X509DataType getX509DataArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().find_element_user(X509DATA$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "X509Data" element
     */
    public int sizeOfX509DataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(X509DATA$6);
        }
    }
    
    /**
     * Sets array of all "X509Data" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setX509DataArray(org.w3.x2000.x09.xmldsig.X509DataType[] x509DataArray)
    {
        check_orphaned();
        arraySetterHelper(x509DataArray, X509DATA$6);
    }
    
    /**
     * Sets ith "X509Data" element
     */
    public void setX509DataArray(int i, org.w3.x2000.x09.xmldsig.X509DataType x509Data)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().find_element_user(X509DATA$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(x509Data);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "X509Data" element
     */
    public org.w3.x2000.x09.xmldsig.X509DataType insertNewX509Data(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().insert_element_user(X509DATA$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "X509Data" element
     */
    public org.w3.x2000.x09.xmldsig.X509DataType addNewX509Data()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().add_element_user(X509DATA$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "X509Data" element
     */
    public void removeX509Data(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(X509DATA$6, i);
        }
    }
    
    /**
     * Gets array of all "PGPData" elements
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType[] getPGPDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(PGPDATA$8, targetList);
            org.w3.x2000.x09.xmldsig.PGPDataType[] result = new org.w3.x2000.x09.xmldsig.PGPDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PGPData" element
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType getPGPDataArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().find_element_user(PGPDATA$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PGPData" element
     */
    public int sizeOfPGPDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PGPDATA$8);
        }
    }
    
    /**
     * Sets array of all "PGPData" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setPGPDataArray(org.w3.x2000.x09.xmldsig.PGPDataType[] pgpDataArray)
    {
        check_orphaned();
        arraySetterHelper(pgpDataArray, PGPDATA$8);
    }
    
    /**
     * Sets ith "PGPData" element
     */
    public void setPGPDataArray(int i, org.w3.x2000.x09.xmldsig.PGPDataType pgpData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().find_element_user(PGPDATA$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(pgpData);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PGPData" element
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType insertNewPGPData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().insert_element_user(PGPDATA$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PGPData" element
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType addNewPGPData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().add_element_user(PGPDATA$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "PGPData" element
     */
    public void removePGPData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PGPDATA$8, i);
        }
    }
    
    /**
     * Gets array of all "SPKIData" elements
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType[] getSPKIDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SPKIDATA$10, targetList);
            org.w3.x2000.x09.xmldsig.SPKIDataType[] result = new org.w3.x2000.x09.xmldsig.SPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "SPKIData" element
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType getSPKIDataArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().find_element_user(SPKIDATA$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "SPKIData" element
     */
    public int sizeOfSPKIDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SPKIDATA$10);
        }
    }
    
    /**
     * Sets array of all "SPKIData" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setSPKIDataArray(org.w3.x2000.x09.xmldsig.SPKIDataType[] spkiDataArray)
    {
        check_orphaned();
        arraySetterHelper(spkiDataArray, SPKIDATA$10);
    }
    
    /**
     * Sets ith "SPKIData" element
     */
    public void setSPKIDataArray(int i, org.w3.x2000.x09.xmldsig.SPKIDataType spkiData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().find_element_user(SPKIDATA$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(spkiData);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SPKIData" element
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType insertNewSPKIData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().insert_element_user(SPKIDATA$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SPKIData" element
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType addNewSPKIData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().add_element_user(SPKIDATA$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "SPKIData" element
     */
    public void removeSPKIData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SPKIDATA$10, i);
        }
    }
    
    /**
     * Gets array of all "MgmtData" elements
     */
    public java.lang.String[] getMgmtDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(MGMTDATA$12, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "MgmtData" element
     */
    public java.lang.String getMgmtDataArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MGMTDATA$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "MgmtData" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetMgmtDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(MGMTDATA$12, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "MgmtData" element
     */
    public org.apache.xmlbeans.XmlString xgetMgmtDataArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MGMTDATA$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "MgmtData" element
     */
    public int sizeOfMgmtDataArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MGMTDATA$12);
        }
    }
    
    /**
     * Sets array of all "MgmtData" element
     */
    public void setMgmtDataArray(java.lang.String[] mgmtDataArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(mgmtDataArray, MGMTDATA$12);
        }
    }
    
    /**
     * Sets ith "MgmtData" element
     */
    public void setMgmtDataArray(int i, java.lang.String mgmtData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MGMTDATA$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(mgmtData);
        }
    }
    
    /**
     * Sets (as xml) array of all "MgmtData" element
     */
    public void xsetMgmtDataArray(org.apache.xmlbeans.XmlString[]mgmtDataArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(mgmtDataArray, MGMTDATA$12);
        }
    }
    
    /**
     * Sets (as xml) ith "MgmtData" element
     */
    public void xsetMgmtDataArray(int i, org.apache.xmlbeans.XmlString mgmtData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MGMTDATA$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(mgmtData);
        }
    }
    
    /**
     * Inserts the value as the ith "MgmtData" element
     */
    public void insertMgmtData(int i, java.lang.String mgmtData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(MGMTDATA$12, i);
            target.setStringValue(mgmtData);
        }
    }
    
    /**
     * Appends the value as the last "MgmtData" element
     */
    public void addMgmtData(java.lang.String mgmtData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MGMTDATA$12);
            target.setStringValue(mgmtData);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "MgmtData" element
     */
    public org.apache.xmlbeans.XmlString insertNewMgmtData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(MGMTDATA$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "MgmtData" element
     */
    public org.apache.xmlbeans.XmlString addNewMgmtData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(MGMTDATA$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "MgmtData" element
     */
    public void removeMgmtData(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MGMTDATA$12, i);
        }
    }
    
    /**
     * Gets the "Id" attribute
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$14);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    public org.apache.xmlbeans.XmlID xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$14);
            return target;
        }
    }
    
    /**
     * True if has "Id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$14) != null;
        }
    }
    
    /**
     * Sets the "Id" attribute
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$14);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$14);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlID id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$14);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$14);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "Id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$14);
        }
    }
}
