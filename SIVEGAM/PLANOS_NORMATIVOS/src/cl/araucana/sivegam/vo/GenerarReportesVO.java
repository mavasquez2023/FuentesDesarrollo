package cl.araucana.sivegam.vo;

public class GenerarReportesVO {

    /* variables de la clase. */
    private int codResultado;
    private String resultado;
    private long idMaestroSivegam;

    /* generacion de get and set */
    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public long getIdMaestroSivegam() {
        return idMaestroSivegam;
    }

    public void setIdMaestroSivegam(long idMaestroSivegam) {
        this.idMaestroSivegam = idMaestroSivegam;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
