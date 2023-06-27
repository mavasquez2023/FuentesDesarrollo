package cl.araucana.independientes.vo.param;

/*Clase Geográfico.
 * Contiene las variables que contandrán la informacion de los datos geograficos usados en el sistema.
 * */
public class Geografico {

    /*Declaración de variables de la clase Geográfico.*/
    private int idRegion;
    private String desRegion;
    private int idProvincia;
    private String desProvincia;
    private int idComuna;
    private String desComuna;

    /*Creación de Getting and Setting de clase Geográfico.*/
    public String getDesComuna() {
        return desComuna;
    }
    public void setDesComuna(String desComuna) {
        this.desComuna = desComuna;
    }
    public String getDesProvincia() {
        return desProvincia;
    }
    public void setDesProvincia(String desProvincia) {
        this.desProvincia = desProvincia;
    }
    public String getDesRegion() {
        return desRegion;
    }
    public void setDesRegion(String desRegion) {
        this.desRegion = desRegion;
    }
    public int getIdComuna() {
        return idComuna;
    }
    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }
    public int getIdProvincia() {
        return idProvincia;
    }
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
    public int getIdRegion() {
        return idRegion;
    }
    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

}
