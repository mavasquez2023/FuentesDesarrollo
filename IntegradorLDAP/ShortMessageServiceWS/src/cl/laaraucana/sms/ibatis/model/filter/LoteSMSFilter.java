package cl.laaraucana.sms.ibatis.model.filter;

import cl.laaraucana.sms.ibatis.model.LoteSMS;

public class LoteSMSFilter extends LoteSMS {
    private int maxBulkSize;
    private String estadoActualizado;
    private String estadoActual;

    public LoteSMSFilter() {
    }

    public LoteSMSFilter(String estadoActualizado) {
        this.estadoActualizado = estadoActualizado;
    }

    public LoteSMSFilter(int maxBulkSize) {
        this.maxBulkSize = maxBulkSize;
    }

    public int getMaxBulkSize() {
        return maxBulkSize;
    }

    public void setMaxBulkSize(int maxBulkSize) {
        this.maxBulkSize = maxBulkSize;
    }

    public String getEstadoActualizado() {
        return estadoActualizado;
    }

    public void setEstadoActualizado(String estadoActualizado) {
        this.estadoActualizado = estadoActualizado;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
}
