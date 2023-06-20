package cl.laaraucana.sms.ibatis.model.filter;

import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;

public class EstadoLoteSMSFilter extends EstadoLoteSMS {
    private int maxBulkSize;

    public EstadoLoteSMSFilter() {
    }

    public EstadoLoteSMSFilter(int maxBulkSize) {
        this.maxBulkSize = maxBulkSize;
    }

    public int getMaxBulkSize() {
        return maxBulkSize;
    }

    public void setMaxBulkSize(int maxBulkSize) {
        this.maxBulkSize = maxBulkSize;
    }
}
