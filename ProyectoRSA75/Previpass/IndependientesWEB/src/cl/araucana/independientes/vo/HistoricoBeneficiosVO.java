package cl.araucana.independientes.vo;

import cl.araucana.independientes.vo.param.Retorno;

public class HistoricoBeneficiosVO {

    private String estadoAfiliado;
    private String nombreAfiliado;
    private long idPersonaAfiliado;
    private String cantReembolsos;
    private String montoReembolsado;

    private BeneficioVO[] listaBeneficios;

    private Retorno retorno;

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }

    public String getCantReembolsos() {
        return cantReembolsos;
    }

    public void setCantReembolsos(String cantReembolsos) {
        this.cantReembolsos = cantReembolsos;
    }

    public String getEstadoAfiliado() {
        return estadoAfiliado;
    }

    public void setEstadoAfiliado(String estadoAfiliado) {
        this.estadoAfiliado = estadoAfiliado;
    }

    public BeneficioVO[] getListaBeneficios() {
        return listaBeneficios;
    }

    public void setListaBeneficios(BeneficioVO[] listaBeneficios) {
        this.listaBeneficios = listaBeneficios;
    }

    public String getMontoReembolsado() {
        return montoReembolsado;
    }

    public void setMontoReembolsado(String montoReembolsado) {
        this.montoReembolsado = montoReembolsado;
    }

    public String getNombreAfiliado() {
        return nombreAfiliado;
    }

    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }

    public long getIdPersonaAfiliado() {
        return idPersonaAfiliado;
    }

    public void setIdPersonaAfiliado(long idPersonaAfiliado) {
        this.idPersonaAfiliado = idPersonaAfiliado;
    }

}
