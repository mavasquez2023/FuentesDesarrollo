package cl.araucana.cp.hibernate.dao;

public class ObjectParameter {
	
	 int tipo;
	    int modo;
	    int indice;
	    String nombre;
	    Object valor;
	    private int oracle_jdbc_OracleTypes_CURSOR = -10;
	    
	    //newTipo = VARCHAR; NUMBER; INTEGER; DOUBLE; DATE; TIME; TIMESTAMP; CURSOR
	    //newModo = IN; OUT; INOUT
	    /**
	     * @param newIndice
	     * @param newNombre
	     * @param newValor
	     * @param newTipo
	     * @param newModo
	     */
	    public ObjectParameter(int newIndice, String newNombre, Object newValor, String newTipo, String newModo) {
	        setIndice(newIndice);
	        setNombre(newNombre);
	        setValor(newValor);
	        setTipo(newTipo);
	        setModo(newModo);
	    }

	    /**
	     * @param newIndice
	     */
	    public void setIndice(int newIndice) {
	        this.indice = newIndice;
	    }
	    /**
	     * @param newTipo
	     */
	    public void setTipo(String newTipo) {
	        if (newTipo.equalsIgnoreCase("VARCHAR"))
	            this.tipo = java.sql.Types.VARCHAR;
	        if (newTipo.equalsIgnoreCase("NUMBER"))
	            this.tipo = java.sql.Types.LONGVARCHAR;
	        if (newTipo.equalsIgnoreCase("INTEGER"))
	            this.tipo = java.sql.Types.INTEGER;
	        if (newTipo.equalsIgnoreCase("DOUBLE"))
	            this.tipo = java.sql.Types.DOUBLE;
	        if (newTipo.equalsIgnoreCase("DATE"))
	            this.tipo = java.sql.Types.DATE;
	        if (newTipo.equalsIgnoreCase("TIME"))
	            this.tipo = java.sql.Types.TIME;
	        if (newTipo.equalsIgnoreCase("TIMESTAMP"))
	            this.tipo = java.sql.Types.TIMESTAMP;
	        if (newTipo.equalsIgnoreCase("CURSOR"))
	            this.tipo = oracle_jdbc_OracleTypes_CURSOR;
	        if (newTipo.equalsIgnoreCase("BLOB"))
	            this.tipo = java.sql.Types.BLOB;
	    }
	    /**
	     * @param newModo
	     */
	    public void setModo(String newModo) {
	        if (newModo.equalsIgnoreCase("IN"))
	            this.modo = java.sql.DatabaseMetaData.procedureColumnIn;
	        if (newModo.equalsIgnoreCase("OUT"))
	            this.modo = java.sql.DatabaseMetaData.procedureColumnOut;
	        if (newModo.equalsIgnoreCase("INOUT"))
	            this.modo = java.sql.DatabaseMetaData.procedureColumnInOut;
	    }
	    /**
	     * @param newNombre
	     */
	    public void setNombre(String newNombre) {
	        this.nombre = newNombre;
	    }
	    /**
	     * @param newValor
	     */
	    public void setValor(Object newValor) {
	        this.valor = newValor;
	    }
	    /**
	     * @return indice
	     */
	    public int getIndice() {
	        return indice;
	    }
	    /**
	     * @return tipo
	     */
	    public int getTipo() {
	        return tipo;
	    }
	    /**
	     * @return modo
	     */
	    public int getModo() {
	        return modo;
	    }
	    /**
	     * @return nombre
	     */
	    public String getNombre() {
	        return nombre;
	    }
	    /**
	     * @return valor
	     */
	    public Object getValor() {
	        return valor;
	    }

}
