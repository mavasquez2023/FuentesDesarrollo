package cl.laaraucana.sms.domain.exchange;

public class StatusURLOutput {
    private String username;
    private String id;
    private String result;
    private String rut;
    private String dv;
    private String phone;
    private String clicks;
    private String browser;
    private String so;
    private String ip;
    private String date;
    private String statusCode;
    private String statusDescription;

    public StatusURLOutput() {
        result = "";
        rut = "";
        dv = "";
        phone = "";
        clicks = "";
        browser = "";
        so = "";
        ip = "";
        date = "";
        statusCode = "";
        statusDescription = "";
    }

    public StatusURLOutput(String username, String id, String result, String rut, String dv, String phone,
                           String clicks, String browser, String so, String ip, String date, String statusCode, String statusDescription) {
        super();
        this.username = username;
        this.id = id;
        this.result = result;
        this.rut = rut;
        this.dv = dv;
        this.phone = phone;
        this.clicks = clicks;
        this.browser = browser;
        this.so = so;
        this.ip = ip;
        this.date = date;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
