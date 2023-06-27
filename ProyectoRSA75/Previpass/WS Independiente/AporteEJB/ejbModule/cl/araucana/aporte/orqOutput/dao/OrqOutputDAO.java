package cl.araucana.aporte.orqOutput.dao;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;

public interface OrqOutputDAO {
    public OrqOutputResultBO recuperacionPago (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago);
}