package cl.laaraucana.sinacofi.clientesws.model;


public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
