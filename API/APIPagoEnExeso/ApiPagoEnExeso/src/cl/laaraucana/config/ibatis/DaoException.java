package cl.laaraucana.config.ibatis;

public class DaoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DaoException(String msg, Exception e){
		super(msg,e);
	}
	
}
