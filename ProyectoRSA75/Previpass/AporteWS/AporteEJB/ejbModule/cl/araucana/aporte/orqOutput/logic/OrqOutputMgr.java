package cl.araucana.aporte.orqOutput.logic;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;

public interface OrqOutputMgr {

    public OrqOutputResultBO recuperacionPago  (int rut, int periodoAporte, int montoCredito, int montoLeasing, int montoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago);

}
