package com.example.thevenom1215.prueba.tablasDTO;

/**
 * Created by yonda on 12/07/2016.
 */
public class C_PAQUETE {

    private int ID_PAQUETE;
    private String NOMBRE;
    private String DESCRIPCION;
    private int COSTO;

    public int getID_PAQUETE() {
        return ID_PAQUETE;
    }

    public void setID_PAQUETE(int ID_PAQUETE) {
        this.ID_PAQUETE = ID_PAQUETE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(int COSTO) {
        this.COSTO = COSTO;
    }
}
