package cl.araucana.independientes.vo;

/* Clase AfiliadoVO.
 * Contiene las variables que serán usadas para transportar la informacion referente al objeto AfiliadoVO desde el formulario.
 * Las variables que interactúan son las siguientes:
 * 	.- idPersonaAfiliado: Corresponde al id del afiliado, que es único e igual al idPersona de la tabla Persona.
 *  .- tipoProfesion: Corresponde al tipo de profesión del afiliado.
 *  .- tipoNivelEducacional: Corresponde al nivel educacional del afiliado.(titulo academico del formulario).
 *  .- FechaMatrimonio: corresponde la fecha de matrimonio del afiliado. (si es que lo está).
 *  .- fechaDefuncion: corresponde a la fecha de defuncion del afiliado.
 *  .- tipoRegSalud: corresponde al regimen de salud del afiliado.
 *  .- tipoAfp: corresponde a la AFP contratada por el afiliado.
 *  .- idSolicitud: Corresponde al id de solicitud del afiliado, que es único e igual al idSolicitud de la tabla Solicitud.
 *  .- fechaInicio: Corresponde a la fecha de inicio de afiliación de la persona al sistema.
 *  .- montoCotizar:Corresponde al monto a cotizar del afiliado.
 *  .- tipoEstadoAfiliado: corresponde al id del tipo de estado del afiliado en el sistema.
 *  .- desTipoEstadoAfiliado: corresponde a la glosa asociada al tipoEstadoAfiliado.
 *  */ 
public class AfiliadoVO {

    /*Declaracion de variables de la clase Afiliado*/
    private long idPersonaAfiliado;
    private int tipoProfesion; //Titulo Academico
    private int tipoNivelEduc;
    private String fechaMatrimonio;
    private String fechaDefuncion;
    private int tipoRegSalud;
    private int tipoAfp;
    private int tipoEstado;
    private long idSolicitud;
    private long idSecuenciaAgrupacion;
    private String fechaInicio;
    private int montoCotizar;
    private int tipoEstadoAfiliado;
    private String desTipoEstadoAfiliado;
    private int procesoDesafiliacion;

    public int getProcesoDesafiliacion() {
        return procesoDesafiliacion;
    }
    public void setProcesoDesafiliacion(int procesoDesafiliacion) {
        this.procesoDesafiliacion = procesoDesafiliacion;
    }
    /*Creación de los Getting and Setting de la clase.*/
    public String getDesTipoEstadoAfiliado() {
        return desTipoEstadoAfiliado;
    }
    public void setDesTipoEstadoAfiliado(String desTipoEstadoAfiliado) {
        this.desTipoEstadoAfiliado = desTipoEstadoAfiliado;
    }
    public String getFechaDefuncion() {
        return fechaDefuncion;
    }
    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaMatrimonio() {
        return fechaMatrimonio;
    }
    public void setFechaMatrimonio(String fechaMatrimonio) {
        this.fechaMatrimonio = fechaMatrimonio;
    }
    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }
    public long getIdSecuenciaAgrupacion() {
        return idSecuenciaAgrupacion;
    }
    public void setIdSecuenciaAgrupacion(long idSecuenciaAgrupacion) {
        this.idSecuenciaAgrupacion = idSecuenciaAgrupacion;
    }
    public long getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public int getMontoCotizar() {
        return montoCotizar;
    }
    public void setMontoCotizar(int montoCotizar) {
        this.montoCotizar = montoCotizar;
    }
    public int getTipoAfp() {
        return tipoAfp;
    }
    public void setTipoAfp(int tipoAfp) {
        this.tipoAfp = tipoAfp;
    }
    public int getTipoEstado() {
        return tipoEstado;
    }
    public void setTipoEstado(int tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
    public int getTipoEstadoAfiliado() {
        return tipoEstadoAfiliado;
    }
    public void setTipoEstadoAfiliado(int tipoEstadoAfiliado) {
        this.tipoEstadoAfiliado = tipoEstadoAfiliado;
    }
    public int getTipoNivelEduc() {
        return tipoNivelEduc;
    }
    public void setTipoNivelEduc(int tipoNivelEduc) {
        this.tipoNivelEduc = tipoNivelEduc;
    }
    public int getTipoProfesion() {
        return tipoProfesion;
    }
    public void setTipoProfesion(int tipoProfesion) {
        this.tipoProfesion = tipoProfesion;
    }
    public int getTipoRegSalud() {
        return tipoRegSalud;
    }
    public void setTipoRegSalud(int tipoRegSalud) {
        this.tipoRegSalud = tipoRegSalud;
    }

}
