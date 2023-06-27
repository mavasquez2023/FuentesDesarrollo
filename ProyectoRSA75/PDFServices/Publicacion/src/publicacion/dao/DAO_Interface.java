package publicacion.dao;


public interface DAO_Interface {
    /** @exception java.sql.SQLException */
    public abstract Object select(Object pk) throws java.sql.SQLException;

    /** @exception java.sql.SQLException */
    public abstract int insert(Object obj) throws java.sql.SQLException;

    /** @exception java.sql.SQLException */
    public abstract int update(Object obj) throws java.sql.SQLException;

    /** @exception java.sql.SQLException */
    public abstract void delete(Object pk) throws java.sql.SQLException;
    

}
