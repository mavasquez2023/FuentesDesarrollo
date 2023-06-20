package cl.laaraucana.imed.clientews.model;


public interface WSInterface {
	public AbstractSalidaVO callAlta(AbstractEntradaVO entrada) throws Exception;
	public AbstractSalidaVO callBaja(AbstractEntradaVO entrada) throws Exception;
}
