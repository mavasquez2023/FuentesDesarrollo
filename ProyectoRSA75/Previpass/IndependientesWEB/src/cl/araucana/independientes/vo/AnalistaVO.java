package cl.araucana.independientes.vo;

/*Clase AnalistaVO.
 * Contiene las variables que transportarán la información hacia la tabla Analista.
 * Las variables creadas para esta clase son:
 * 	idAnalista: corresponde al rut del analista.
 * 	apellidoPaterno: corresponde al apellido paterno del analista.
 * 	apellidoMaterno: corresponde al apellido materno del analista.
 * 	nombres: corresponde a los nombres de analista.
 *  oficina: corresponde al id de la oficina desde donde el analista ingreso la solicitud de afiliación.
 *  desOficina: corresponde a la glosa asociada al id de la oficina.*/

public class AnalistaVO {

    /*Declaracion de Variables de la clase AnalistaVO*/
    private long idAnalista; 
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private int oficina;
    private String desOficina;

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
    public String getDesOficina() {
        return desOficina;
    }
    public void setDesOficina(String desOficina) {
        this.desOficina = desOficina;
    }
    public long getIdAnalista() {
        return idAnalista;
    }
    public void setIdAnalista(long idAnalista) {
        this.idAnalista = idAnalista;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public int getOficina() {
        return oficina;
    }
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

}
