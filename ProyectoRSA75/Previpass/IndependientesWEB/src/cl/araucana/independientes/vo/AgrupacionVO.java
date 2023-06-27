package cl.araucana.independientes.vo;

/* Clase AgrupacionVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto AgrupaciónVO desde el formulario.
 */
public class AgrupacionVO {

    /*Declaracion de variables*/
    private long idSecuencia;
    private int tipoDocumento;
    private long idDocumento;
    private String digVerDocumento;
    private String razonSocial;
    private String tipoGiroComercial;
    private String fechaInicio;
    private int tipoActEconomica;
    private int numIntegrantes;
    private int tipoAgrupacion;
    private String representanteLegal;

    /*Creación de los Getting and Setting de la clase.*/
    public String getDigVerDocumento() {
        return digVerDocumento;
    }
    public void setDigVerDocumento(String digVerDocumento) {
        this.digVerDocumento = digVerDocumento;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public long getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }
    public long getIdSecuencia() {
        return idSecuencia;
    }
    public void setIdSecuencia(long idSecuencia) {
        this.idSecuencia = idSecuencia;
    }
    public int getNumIntegrantes() {
        return numIntegrantes;
    }
    public void setNumIntegrantes(int numIntegrantes) {
        this.numIntegrantes = numIntegrantes;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public int getTipoActEconomica() {
        return tipoActEconomica;
    }
    public void setTipoActEconomica(int tipoActEconomica) {
        this.tipoActEconomica = tipoActEconomica;
    }
    public int getTipoAgrupacion() {
        return tipoAgrupacion;
    }
    public void setTipoAgrupacion(int tipoAgrupacion) {
        this.tipoAgrupacion = tipoAgrupacion;
    }
    public int getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getTipoGiroComercial() {
        return tipoGiroComercial;
    }
    public void setTipoGiroComercial(String tipoGiroComercial) {
        this.tipoGiroComercial = tipoGiroComercial;
    }	
    public String getRepresentanteLegal() {
        return representanteLegal;
    }
    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }	
}
