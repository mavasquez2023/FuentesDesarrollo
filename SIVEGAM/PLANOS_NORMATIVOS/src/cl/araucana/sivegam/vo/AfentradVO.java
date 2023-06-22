package cl.araucana.sivegam.vo;

import java.util.Date;

public class AfentradVO {
    private long IDESTCARGA;
    private Date FECHACARGA;
    private int ESTADOCRGA;
    private long PERIODOCRG;
    private String TIPARCHCRG;
    private long USUARIOCRG;

    public int getESTADOCRGA() {
        return ESTADOCRGA;
    }

    public void setESTADOCRGA(int estadocrga) {
        ESTADOCRGA = estadocrga;
    }

    public Date getFECHACARGA() {
        return FECHACARGA;
    }

    public void setFECHACARGA(Date fechacarga) {
        FECHACARGA = fechacarga;
    }

    public long getIDESTCARGA() {
        return IDESTCARGA;
    }

    public void setIDESTCARGA(long idestcarga) {
        IDESTCARGA = idestcarga;
    }

    public long getPERIODOCRG() {
        return PERIODOCRG;
    }

    public void setPERIODOCRG(long periodocrg) {
        PERIODOCRG = periodocrg;
    }

    public String getTIPARCHCRG() {
        return TIPARCHCRG;
    }

    public void setTIPARCHCRG(String tiparchcrg) {
        TIPARCHCRG = tiparchcrg;
    }

    public long getUSUARIOCRG() {
        return USUARIOCRG;
    }

    public void setUSUARIOCRG(long usuariocrg) {
        USUARIOCRG = usuariocrg;
    }

}
