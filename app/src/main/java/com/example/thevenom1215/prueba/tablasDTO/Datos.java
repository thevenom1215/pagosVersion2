package com.example.thevenom1215.prueba.tablasDTO;

/**
 * Created by yonda on 22/07/2016.
 */
public class Datos {
    String Cvecontrato=" ";
    String NO_RECIBO=" ";
    String Nombre=" ";
    String Numerodeparcialidades=" ";
    String AP_PATSOLICIT=" ";
    String AP_MATSOLICIT=" ";
    String DESCRIPCION=" ";
    String NO_PARCIALIDAD=" ";
    String PAGO_PARCIALIDAD=" ";

    public String getTOT_APAG() {
        return TOT_APAG;
    }

    public void setTOT_APAG(String TOT_APAG) {
        this.TOT_APAG = TOT_APAG;
    }

    String TOT_APAG =" ";

    int  statuspago;

    Double SALDO_TOTAL;

    public String getAP_PATSOLICIT() {
        return AP_PATSOLICIT;
    }

    public void setAP_PATSOLICIT(String AP_PATSOLICIT) {
        this.AP_PATSOLICIT = AP_PATSOLICIT;
    }

    public String getAP_MATSOLICIT() {
        return AP_MATSOLICIT;
    }

    public void setAP_MATSOLICIT(String AP_MATSOLICIT) {
        this.AP_MATSOLICIT = AP_MATSOLICIT;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getNO_PARCIALIDAD() {
        return NO_PARCIALIDAD;
    }

    public void setNO_PARCIALIDAD(String NO_PARCIALIDAD) {
        this.NO_PARCIALIDAD = NO_PARCIALIDAD;
    }

    public String getPAGO_PARCIALIDAD() {
        return PAGO_PARCIALIDAD;
    }

    public void setPAGO_PARCIALIDAD(String PAGO_PARCIALIDAD) {
        this.PAGO_PARCIALIDAD = PAGO_PARCIALIDAD;
    }

    public void setStatuspago(int statuspago) {
        this.statuspago = statuspago;
    }

    public Double getSALDO_TOTAL() {
        return SALDO_TOTAL;
    }

    public void setSALDO_TOTAL(Double SALDO_TOTAL) {
        this.SALDO_TOTAL = SALDO_TOTAL;
    }

    public String getCvecontrato() {
        return Cvecontrato;
    }

    public void setCvecontrato(String cvecontrato) {
        Cvecontrato = cvecontrato;
    }

    public String getNO_RECIBO() {
        return NO_RECIBO;
    }

    public void setNO_RECIBO(String NO_RECIBO) {
        this.NO_RECIBO = NO_RECIBO;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNumerodeparcialidades() {
        return Numerodeparcialidades;
    }

    public void setNumerodeparcialidades(String numerodeparcialidades) {
        Numerodeparcialidades = numerodeparcialidades;
    }

    public int getStatuspago() {
        return statuspago;
    }
}
