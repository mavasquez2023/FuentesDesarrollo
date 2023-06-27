package cl.araucana.independientes.vo;

import java.util.Date;

/* Clase GrupoFamiliarVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto GrupoFamiliarVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.- idPersona: corresponde al id de secuencia usado en el sistema y de caracter unico.
 * 	.- tipoDocumento: corresponde al tipo de documento de la persona (rut, pasaporte, etc).
 * 	.- idDocumento: Corresponde al rut de la persona.
 * 	.- digVerificador: corresponde al dígito verificador del rut de la persona.
 * 	.- apellidoPaterno: corresponde al apellido paterno de la persona.
 * 	.- apellidoMaterno: corresponde al apellido materno de la persona.
 * 	.- nombres: corresponde a los nombres de la persona.
 * 	.- fechaNacimiento: corresponde a la fecha de nacimiento de la persona.
 * 	.- tipoSexo: corresponde al tipo de sexo de la persona. Este puede ser masculino o femenino.
 * 	.- tipoNacionalidad: corresponde a la nacionalidad de la persona. Este puede ser extranjera o chilena.
 */
public class PersonaVO {

    /*Declaracion de variables*/
    private long idPersona;
    private int tipoDocumento;
    private long idDocumento;
    private String digVerificador;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String fechaNacimiento;
    private Date fechaNacimientoDate;//Se usa para recuperar la fecha desde la base de datos.
    private int tipoSexo;
    private int tipoPersona;
    private int tipoNacionalidad;

    /*Creación de los Getting and Setting de la clase.*/
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getDigVerificador() {
        return digVerificador;
    }
    public void setDigVerificador(String digVerificador) {
        this.digVerificador = digVerificador;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Date getFechaNacimientoDate() {
        return fechaNacimientoDate;
    }
    public void setFechaNacimientoDate(Date fechaNacimientoDate) {
        this.fechaNacimientoDate = fechaNacimientoDate;
    }
    public long getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }
    public long getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public int getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public int getTipoNacionalidad() {
        return tipoNacionalidad;
    }
    public void setTipoNacionalidad(int tipoNacionalidad) {
        this.tipoNacionalidad = tipoNacionalidad;
    }
    public int getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public int getTipoSexo() {
        return tipoSexo;
    }
    public void setTipoSexo(int tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

}	
