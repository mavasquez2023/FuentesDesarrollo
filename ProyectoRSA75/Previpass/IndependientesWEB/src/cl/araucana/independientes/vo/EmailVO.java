package cl.araucana.independientes.vo;

/* Clase EmailVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto EmailVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.- idSecuenciaEmail: id de secuencia propio del sistema, unico e igual al id de secuencia de la tabla EmailEntidad.
 * 	.- direccMail: corresponde a la direccion email del afiliado.
 * */

public class EmailVO{

    /*Declaracion de variables  de la clase EmailVO */
    private long idSecuenciaEmail;
    private int tipoLocalidad;
    private String direccMail;
    private int tipoPrincipalidad;
    private int flagEmail;

    public int getFlagEmail() {
        return flagEmail;
    }
    public void setFlagEmail(int flagEmail) {
        this.flagEmail = flagEmail;
    }
    /*Creación de los Getting and Setting de la clase.*/
    public String getDireccMail() {
        return direccMail;
    }
    public void setDireccMail(String direccMail) {
        this.direccMail = direccMail;
    }
    public long getIdSecuenciaEmail() {
        return idSecuenciaEmail;
    }
    public void setIdSecuenciaEmail(long idSecuenciaEmail) {
        this.idSecuenciaEmail = idSecuenciaEmail;
    }
    public int getTipoLocalidad() {
        return tipoLocalidad;
    }
    public void setTipoLocalidad(int tipoLocalidad) {
        this.tipoLocalidad = tipoLocalidad;
    }
    public int getTipoPrincipalidad() {
        return tipoPrincipalidad;
    }
    public void setTipoPrincipalidad(int tipoPrincipalidad) {
        this.tipoPrincipalidad = tipoPrincipalidad;
    }

}
