package cl.araucana.independientes.vo;

/* Clase GrupoFamiliarVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto GrupoFamiliarVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.-  idSecuenciaTelefono: corresponde al id de secuencia usado en el sistema y de caracter único e igual al id de solicitud de la tabla afiliado.
 * 	.-	tipoLocalidad: corresponde a la diferenciación que se le da en el sistema al telefono particular y comercial.
 * 					 .- Si el tipo de localidad es 2, corresponde a la telefono particular.
 * 					 .- Si el tipo de localidad es 3, corresponde a la telefono comercial.
 * 					 .- Si el tipo de localidad es 1, corresponde a la telefono celular.
 * 	.-	codArea: corresponde al codigo de area de la localidad de donde pertenece el numero de telefono.
 * 	.-	nroTelefono: corresponde al numero de telefono de la persona. Va asociado al tipo de localidad, ya que el numero de telefono puede ser particular o comercial.
 * 	.-*/
public class TelefonoVO{

    /*Declaracion de Variables*/
    private long idSecuenciaTelefono;
    private int tipoLocalidad;
    private String codArea;
    private String nroTelefono;
    private String anexoTelefono;
    private int tipoPrincipalidad;
    private int flagTelefono;

    public int getFlagTelefono() {
        return flagTelefono;
    }
    public void setFlagTelefono(int flagTelefono) {
        this.flagTelefono = flagTelefono;
    }
    /*Creación de los Getting and Setting de la clase.*/
    public String getAnexoTelefono() {
        return anexoTelefono;
    }
    public void setAnexoTelefono(String anexoTelefono) {
        this.anexoTelefono = anexoTelefono;
    }
    public String getCodArea() {
        return codArea;
    }
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }
    public long getIdSecuenciaTelefono() {
        return idSecuenciaTelefono;
    }
    public void setIdSecuenciaTelefono(long idSecuenciaTelefono) {
        this.idSecuenciaTelefono = idSecuenciaTelefono;
    }
    public String getNroTelefono() {
        return nroTelefono;
    }
    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
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
