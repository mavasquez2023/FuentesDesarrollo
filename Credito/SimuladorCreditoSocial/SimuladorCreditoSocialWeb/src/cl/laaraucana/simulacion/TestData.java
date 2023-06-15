package cl.laaraucana.simulacion;

import java.util.ArrayList;

public class TestData {
    private String numeroCuota;
    private String fechaVencimiento;
    private String montoCapital;
    private String montoInteres;
    private String seguroDesgravamen;
    private String seguroCesantia;
    private String valorCuota;

    TestData() {
    }

    private TestData(String numeroCuota, String fechaVencimiento, String montoCapital, String montoInteres, String seguroDesgravamen, String seguroCesantia, String valorCuota) {
        this.numeroCuota = numeroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.montoCapital = montoCapital;
        this.montoInteres = montoInteres;
        this.seguroDesgravamen = seguroDesgravamen;
        this.seguroCesantia = seguroCesantia;
        this.valorCuota = valorCuota;
    }

    ArrayList<TestData> loadData() {
        ArrayList<TestData> data = new ArrayList<TestData>();
        data.add(new TestData("1", "31-01-2018", "110.451", "89.151", "5.151", "15.151", "215.151"));
        data.add(new TestData("2", "31-02-2018", "110.452", "89.152", "5.152", "15.152", "215.152"));
        data.add(new TestData("3", "31-03-2018", "110.453", "89.153", "5.153", "15.153", "215.153"));
        data.add(new TestData("4", "31-04-2018", "110.454", "89.154", "5.154", "15.154", "215.154"));
        data.add(new TestData("5", "31-05-2018", "110.455", "89.155", "5.155", "15.155", "215.155"));
        data.add(new TestData("6", "31-06-2018", "110.456", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("7", "31-07-2018", "110.457", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("8", "31-08-2018", "110.458", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("9", "31-09-2018", "110.459", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("10", "31-10-2018", "110.510", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("11", "31-11-2018", "110.511", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("12", "31-12-2018", "110.512", "89.156", "5.156", "15.156", "215.156"));
        data.add(new TestData("13", "31-01-2019", "110.513", "89.156", "5.156", "15.156", "215.156"));

        return data;
    }

    public String getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(String numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMontoCapital() {
        return montoCapital;
    }

    public void setMontoCapital(String montoCapital) {
        this.montoCapital = montoCapital;
    }

    public String getMontoInteres() {
        return montoInteres;
    }

    public void setMontoInteres(String montoInteres) {
        this.montoInteres = montoInteres;
    }

    public String getSeguroDesgravamen() {
        return seguroDesgravamen;
    }

    public void setSeguroDesgravamen(String seguroDesgravamen) {
        this.seguroDesgravamen = seguroDesgravamen;
    }

    public String getSeguroCesantia() {
        return seguroCesantia;
    }

    public void setSeguroCesantia(String seguroCesantia) {
        this.seguroCesantia = seguroCesantia;
    }

    public String getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(String valorCuota) {
        this.valorCuota = valorCuota;
    }
}
