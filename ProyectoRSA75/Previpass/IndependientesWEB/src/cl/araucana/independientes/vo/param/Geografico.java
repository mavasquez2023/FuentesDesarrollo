package cl.araucana.independientes.vo.param;

/*Clase Geogr�fico.
 * Contiene las variables que contandr�n la informacion de los datos geograficos usados en el sistema.
 * */
public class Geografico {

    /*Declaraci�n de variables de la clase Geogr�fico.*/
    private int idRegion;
    private String desRegion;
    private int idProvincia;
    private String desProvincia;
    private int idComuna;
    private String desComuna;

    /*Creaci�n de Getting and Setting de clase Geogr�fico.*/
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
