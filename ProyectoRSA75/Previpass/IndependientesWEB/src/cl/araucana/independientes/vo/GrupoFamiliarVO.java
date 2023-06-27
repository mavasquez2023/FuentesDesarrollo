package cl.araucana.independientes.vo;

/* Clase GrupoFamiliarVO.
 * Contiene las variables que serán usadas para transportar la información referente al objeto GrupoFamiliarVO desde el formulario.
 * Las variables usadas son las siguientes:
 * 	.- idGrupoFam: corresponde al id de secuencia usado en el sistema y de caracter unico.
 * 	.- idPersonaAfiliado: corresponde al id de persona afiliado, unico e igual al idpersonaafiliado de la tabla Afiliado.
 * 	.- conyugue: corresponde a si el afiliado posee conyugue.
 * 			.- si el afiliado tiene un conyugue éste toma el valor de 1.
 * 			.- si el afiliado no tiene un conyugue, éste toma el valor de 0.
 * 			.- si la variable no posee informacion (no se ingresó) éste toma un valor de -1.
 * 	.- cantHijos: corresponde a la cantidad de hijos del afiliado.
 */
public class GrupoFamiliarVO {

    /*Declaracion de variables de la clase GrupoFamiliarVO*/
    private long idGrupoFam;
    private long idPersonaAfiliado;
    private int conyugue;
    private int cantHijos;
    private int cantBeneficiarios;

    /*Creación de los Getting and Setting de la clase.*/
    public int getCantBeneficiarios() {
        return cantBeneficiarios;
    }
    public void setCantBeneficiarios(int cantBeneficiarios) {
        this.cantBeneficiarios = cantBeneficiarios;
    }
    public int getCantHijos() {
        return cantHijos;
    }
    public void setCantHijos(int cantHijos) {
        this.cantHijos = cantHijos;
    }
    public int getConyugue() {
        return conyugue;
    }
    public void setConyugue(int conyugue) {
        this.conyugue = conyugue;
    }
    public long getIdGrupoFam() {
        return idGrupoFam;
    }
    public void setIdGrupoFam(long idGrupoFam) {
        this.idGrupoFam = idGrupoFam;
    }
    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }
    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }

}
