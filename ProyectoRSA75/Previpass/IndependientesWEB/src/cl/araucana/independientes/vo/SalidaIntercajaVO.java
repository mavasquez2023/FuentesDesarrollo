package cl.araucana.independientes.vo;

public class SalidaIntercajaVO {

    /*Declaracion de variables de la clase*/
    private String resultado;
    private int codResultado;

    private String fechaConPimerDiaMes;
    private String fechaConUltimoDiaMes;
    private String afiliacionesNuevas;
    private String desafiliados;
    private String cambioCCAF;
    private String fallecidos;
    private String desafiliadosTipo1;
    private String desafiliadosTipo2;
    private String contadores;
    private String nombreArchivo;
    private String archivoSalidaIntercaja;
    private LinSalidaIntercajaVO[] lisSalidaIntercaja;

    /*nuevas variables para manejo de rangos de intercaja*/
    private String rangoUno;
    private String rangoDos;
    private String rangoTres;

    /*Generacion de get and set*/
    public String getRangoDos() {
        return rangoDos;
    }
    public void setRangoDos(String rangoDos) {
        this.rangoDos = rangoDos;
    }
    public String getRangoTres() {
        return rangoTres;
    }
    public void setRangoTres(String rangoTres) {
        this.rangoTres = rangoTres;
    }
    public String getRangoUno() {
        return rangoUno;
    }
    public void setRangoUno(String rangoUno) {
        this.rangoUno = rangoUno;
    }

    public String getArchivoSalidaIntercaja() {
        return archivoSalidaIntercaja;
    }
    public void setArchivoSalidaIntercaja(String archivoSalidaIntercaja) {
        this.archivoSalidaIntercaja = archivoSalidaIntercaja;
    }
    public String getContadores() {
        return contadores;
    }
    public void setContadores(String contadores) {
        this.contadores = contadores;
    }	
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public LinSalidaIntercajaVO[] getLisSalidaIntercaja() {
        return lisSalidaIntercaja;
    }
    public void setLisSalidaIntercaja(LinSalidaIntercajaVO[] lisSalidaIntercaja) {
        this.lisSalidaIntercaja = lisSalidaIntercaja;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getAfiliacionesNuevas() {
        return afiliacionesNuevas;
    }
    public void setAfiliacionesNuevas(String afiliacionesNuevas) {
        this.afiliacionesNuevas = afiliacionesNuevas;
    }
    public String getCambioCCAF() {
        return cambioCCAF;
    }
    public void setCambioCCAF(String cambioCCAF) {
        this.cambioCCAF = cambioCCAF;
    }
    public String getDesafiliados() {
        return desafiliados;
    }
    public void setDesafiliados(String desafiliados) {
        this.desafiliados = desafiliados;
    }
    public String getFallecidos() {
        return fallecidos;
    }
    public void setFallecidos(String fallecidos) {
        this.fallecidos = fallecidos;
    }
    public String getFechaConPimerDiaMes() {
        return fechaConPimerDiaMes;
    }
    public void setFechaConPimerDiaMes(String fechaConPimerDiaMes) {
        this.fechaConPimerDiaMes = fechaConPimerDiaMes;
    }
    public String getFechaConUltimoDiaMes() {
        return fechaConUltimoDiaMes;
    }
    public void setFechaConUltimoDiaMes(String fechaConUltimoDiaMes) {
        this.fechaConUltimoDiaMes = fechaConUltimoDiaMes;
    }
    public String getDesafiliadosTipo1() {
        return desafiliadosTipo1;
    }
    public void setDesafiliadosTipo1(String desafiliadosTipo1) {
        this.desafiliadosTipo1 = desafiliadosTipo1;
    }
    public String getDesafiliadosTipo2() {
        return desafiliadosTipo2;
    }
    public void setDesafiliadosTipo2(String desafiliadosTipo2) {
        this.desafiliadosTipo2 = desafiliadosTipo2;
    }

}
