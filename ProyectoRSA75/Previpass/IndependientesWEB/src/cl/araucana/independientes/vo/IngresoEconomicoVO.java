package cl.araucana.independientes.vo;

/* Clase GrupoFamiliarVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto GrupoFamiliarVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.- idIngEconom: corresponde al id de secuencia usado en el sistema y de caracter unico.
 * 	.- idPersonaAfiliado: corresponde al id de persona afiliado, unico e igual al idpersonaafiliado de la tabla Afiliado.
 * 	.- actEconom: Corresponde a la actividad economica del afiliado.
 * 	.- rentaImponible: corresponde a la cantidad imponible del afiliado.
 * 	.- rentaCotizada: corresponde a la renta cotizada del afiliado.
 * 	.- honorario: corresponde a si el afiliado posee honorarios.
 * 			.- si el afiliado tiene honorarios éste toma el valor de 1.
 * 			.- si el afiliado no tiene  honorarios, éste toma el valor de 0.
 */
public class IngresoEconomicoVO {

    /*Declaracion de Variables*/
    private long idIngEconom;
    private long idPersonaAfiliado;
    private String actEconom;
    private long rentaImponible;
    private long rentaCotizada;
    private long montoUltimaCotizacion;	
    private int honorario;
    private String fecUltCotizacion;

    /*Creación de los Getting and Setting de la clase.*/
    public String getActEconom() {
        return actEconom;
    }
    public void setActEconom(String actEconom) {
        this.actEconom = actEconom;
    }
    public int getHonorario() {
        return honorario;
    }
    public void setHonorario(int honorario) {
        this.honorario = honorario;
    }
    public long getIdIngEconom() {
        return idIngEconom;
    }
    public void setIdIngEconom(long idIngEconom) {
        this.idIngEconom = idIngEconom;
    }
    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }
    public long getRentaCotizada() {
        return rentaCotizada;
    }
    public void setRentaCotizada(long rentaCotizada) {
        this.rentaCotizada = rentaCotizada;
    }
    public long getRentaImponible() {
        return rentaImponible;
    }
    public void setRentaImponible(long rentaImponible) {
        this.rentaImponible = rentaImponible;
    }
    public long getMontoUltimaCotizacion() {
        return montoUltimaCotizacion;
    }
    public void setMontoUltimaCotizacion(long montoUltimaCotizacion) {
        this.montoUltimaCotizacion = montoUltimaCotizacion;
    }

    public String getFecUltCotizacion() {
        return fecUltCotizacion;
    }
    public void setFecUltCotizacion(String fecUltCotizacion) {
        this.fecUltCotizacion = fecUltCotizacion;
    }
}
