package cl.araucana.independientes.vo;

/* Clase AfiliadoVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto DireccionVO desde el formulario.
 * Las variables contienen la siguiente información:
 * 	.- idSecuenciaDireccion:idSecuencia: id de secuencia propio de la tabla, unico e igual al id de secuencia de la tabla direccionEntidad.
 * 	.- tipoLocalidad: corresponde a la diferenciación que se le da en el sistema a la direccion particular y comercial.
 * 					 .- Si el tipo de localidad es 1, corresponde a la direccion particular.
 * 					 .- Si el tipo de localidad es 2, corresponde a la direccion comercial.
 * 	.- glosCalle: Corresponde al nombre de la calle.
 * 	.- numDireccion: corresponde al número de domicilio.
 * 	.- poblacionVilla: corresponde 
 * 	.- dpto: corresponde al numero de departamento.
 * 	.- comuna:Corresponde a la comuna en donde vive y/o trabaja la persona. Esta asociada al tipo de localidad para diferenciar comuna de la direccion particular
 * 			  de la direccion comercial.
 * 	.- ciudad: Corresponde a la ciudad en donde vive y/o trabaja la persona. Esta asociada al tipo de localidad para diferenciar comuna de la direccion particular
 * 			  de la direccion comercial.
 * 	.- region: Corresponde a la región en donde vive y/o trabaja la persona. Esta asociada al tipo de localidad para diferenciar comuna de la direccion particular
 * 			  de la direccion comercial.
 */
public class DireccionVO{

    /* Declaracion de variables de la clase DirecciónVO*/
    private long idSecuenciaDireccion;
    private int tipoLocalidad;
    private String glosCalle;
    private String numDireccion;
    private String poblacionVilla;
    private String dpto;
    private String observaciones;
    private int comuna;
    private int ciudad;
    private int region;
    private int tipoPrincipalidad;
    private int flagCalle;

    public int getCiudad() {
        return ciudad;
    }
    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }
    public int getComuna() {
        return comuna;
    }
    public void setComuna(int comuna) {
        this.comuna = comuna;
    }
    public String getDpto() {
        return dpto;
    }
    public void setDpto(String dpto) {
        this.dpto = dpto;
    }
    public String getGlosCalle() {
        return glosCalle;
    }
    public void setGlosCalle(String glosCalle) {
        this.glosCalle = glosCalle;
    }
    public long getIdSecuenciaDireccion() {
        return idSecuenciaDireccion;
    }
    public void setIdSecuenciaDireccion(long idSecuenciaDireccion) {
        this.idSecuenciaDireccion = idSecuenciaDireccion;
    }
    public String getNumDireccion() {
        return numDireccion;
    }
    public void setNumDireccion(String numDireccion) {
        this.numDireccion = numDireccion;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getPoblacionVilla() {
        return poblacionVilla;
    }
    public void setPoblacionVilla(String poblacionVilla) {
        this.poblacionVilla = poblacionVilla;
    }
    public int getRegion() {
        return region;
    }
    public void setRegion(int region) {
        this.region = region;
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
    public int getFlagCalle() {
        return flagCalle;
    }
    public void setFlagCalle(int flagCalle) {
        this.flagCalle = flagCalle;
    }

}
