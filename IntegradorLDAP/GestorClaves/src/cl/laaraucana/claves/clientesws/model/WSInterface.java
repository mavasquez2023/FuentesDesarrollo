package cl.laaraucana.claves.clientesws.model;


public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
