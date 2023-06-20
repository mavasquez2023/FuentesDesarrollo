package cl.laaraucana.sms.domain.exchange;

public class MessageOutput {
    private String phone;
    private String result;
    private String idSMS;
    private String idURL;
    private String statusCode;
    private String statusDescription;

    public MessageOutput() {
        phone = "";
        result = "";
        idSMS = "";
        idURL = "";
        statusCode = "";
        statusDescription = "";
    }

    public MessageOutput(String phone, String result, String idSMS, String idURL, String statusCode, String statusDescription) {
        this.phone = phone;
        this.result = result;
        this.idSMS = idSMS;
        this.idURL = idURL;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getIdSMS() {
        return idSMS;
    }

    public void setIdSMS(String idSMS) {
        this.idSMS = idSMS;
    }

    public String getIdURL() {
        return idURL;
    }

    public void setIdURL(String idURL) {
        this.idURL = idURL;
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
