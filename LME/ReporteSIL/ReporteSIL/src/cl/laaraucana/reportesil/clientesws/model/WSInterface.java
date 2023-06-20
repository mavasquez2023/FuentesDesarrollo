package cl.laaraucana.reportesil.clientesws.model;


public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
