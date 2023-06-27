/*
 * XML Type:  CTHaberes
 * Namespace: urn:www:lme:gov:cl:lme
 * Java type: wwwLmeGovClLme.CTHaberes
 *
 * Automatically generated - do not modify.
 */
package wwwLmeGovClLme.impl;
/**
 * An XML CTHaberes(@urn:www:lme:gov:cl:lme).
 *
 * This is a complex type.
 */
public class CTHaberesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements wwwLmeGovClLme.CTHaberes
{
    private static final long serialVersionUID = 1L;
    
    public CTHaberesImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DETALLE$0 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "detalle");
    private static final javax.xml.namespace.QName ARCHIVO$2 = 
        new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "archivo");
    
    
    /**
     * Gets array of all "detalle" elements
     */
    public wwwLmeGovClLme.CTDetalleHaber[] getDetalleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DETALLE$0, targetList);
            wwwLmeGovClLme.CTDetalleHaber[] result = new wwwLmeGovClLme.CTDetalleHaber[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "detalle" element
     */
    public wwwLmeGovClLme.CTDetalleHaber getDetalleArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDetalleHaber target = null;
            target = (wwwLmeGovClLme.CTDetalleHaber)get_store().find_element_user(DETALLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "detalle" element
     */
    public int sizeOfDetalleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DETALLE$0);
        }
    }
    
    /**
     * Sets array of all "detalle" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setDetalleArray(wwwLmeGovClLme.CTDetalleHaber[] detalleArray)
    {
        check_orphaned();
        arraySetterHelper(detalleArray, DETALLE$0);
    }
    
    /**
     * Sets ith "detalle" element
     */
    public void setDetalleArray(int i, wwwLmeGovClLme.CTDetalleHaber detalle)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDetalleHaber target = null;
            target = (wwwLmeGovClLme.CTDetalleHaber)get_store().find_element_user(DETALLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(detalle);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "detalle" element
     */
    public wwwLmeGovClLme.CTDetalleHaber insertNewDetalle(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDetalleHaber target = null;
            target = (wwwLmeGovClLme.CTDetalleHaber)get_store().insert_element_user(DETALLE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "detalle" element
     */
    public wwwLmeGovClLme.CTDetalleHaber addNewDetalle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDetalleHaber target = null;
            target = (wwwLmeGovClLme.CTDetalleHaber)get_store().add_element_user(DETALLE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "detalle" element
     */
    public void removeDetalle(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DETALLE$0, i);
        }
    }
    
    /**
     * Gets array of all "archivo" elements
     */
    public wwwLmeGovClLme.CTDireccionArchivo[] getArchivoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ARCHIVO$2, targetList);
            wwwLmeGovClLme.CTDireccionArchivo[] result = new wwwLmeGovClLme.CTDireccionArchivo[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "archivo" element
     */
    public wwwLmeGovClLme.CTDireccionArchivo getArchivoArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccionArchivo target = null;
            target = (wwwLmeGovClLme.CTDireccionArchivo)get_store().find_element_user(ARCHIVO$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "archivo" element
     */
    public int sizeOfArchivoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ARCHIVO$2);
        }
    }
    
    /**
     * Sets array of all "archivo" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setArchivoArray(wwwLmeGovClLme.CTDireccionArchivo[] archivoArray)
    {
        check_orphaned();
        arraySetterHelper(archivoArray, ARCHIVO$2);
    }
    
    /**
     * Sets ith "archivo" element
     */
    public void setArchivoArray(int i, wwwLmeGovClLme.CTDireccionArchivo archivo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccionArchivo target = null;
            target = (wwwLmeGovClLme.CTDireccionArchivo)get_store().find_element_user(ARCHIVO$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(archivo);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "archivo" element
     */
    public wwwLmeGovClLme.CTDireccionArchivo insertNewArchivo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccionArchivo target = null;
            target = (wwwLmeGovClLme.CTDireccionArchivo)get_store().insert_element_user(ARCHIVO$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "archivo" element
     */
    public wwwLmeGovClLme.CTDireccionArchivo addNewArchivo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            wwwLmeGovClLme.CTDireccionArchivo target = null;
            target = (wwwLmeGovClLme.CTDireccionArchivo)get_store().add_element_user(ARCHIVO$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "archivo" element
     */
    public void removeArchivo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ARCHIVO$2, i);
        }
    }
}
