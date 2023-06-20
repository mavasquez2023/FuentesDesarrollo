package cl.laaraucana.sms.domain.exchange;

public class StatusSMSOutput {
    private String username;
    private String id;
    private String rut;
    private String dv;
    private String phone;
    private String status;
    private String dateSend;
    private String dateReceived;
    private String statusCode;
    private String statusDescription;

    public StatusSMSOutput() {
        rut = "";
        dv = "";
        phone = "";
        status = "";
        dateSend = "";
        dateReceived = "";
        statusCode = "";
        statusDescription = "";
    }

    public StatusSMSOutput(String username, String id, String rut, String dv, String phone, String status,
                           String dateSend, String dateReceived, String statusCode, String statusDescription) {
        this.username = username;
        this.id = id;
        this.rut = rut;
        this.dv = dv;
        this.phone = phone;
        this.status = status;
        this.dateSend = dateSend;
        this.dateReceived = dateReceived;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
