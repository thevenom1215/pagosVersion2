package com.example.thevenom1215.prueba.tablasDTO;

/**
 * Created by yonda on 12/07/2016.
 */
public class C_STATUS_OBJ {

    private int ID_STATUS;
    private String CVE_OBJETO;
    private String CVE_OPERAC;
    private int STS_OBJETO;
    private String DES_STATUS;

    public int getID_STATUS() {
        return ID_STATUS;
    }

    public void setID_STATUS(int ID_STATUS) {
        this.ID_STATUS = ID_STATUS;
    }

    public String getCVE_OBJETO() {
        return CVE_OBJETO;
    }

    public void setCVE_OBJETO(String CVE_OBJETO) {
        this.CVE_OBJETO = CVE_OBJETO;
    }

    public String getCVE_OPERAC() {
        return CVE_OPERAC;
    }

    public void setCVE_OPERAC(String CVE_OPERAC) {
        this.CVE_OPERAC = CVE_OPERAC;
    }

    public int getSTS_OBJETO() {
        return STS_OBJETO;
    }

    public void setSTS_OBJETO(int STS_OBJETO) {
        this.STS_OBJETO = STS_OBJETO;
    }

    public String getDES_STATUS() {
        return DES_STATUS;
    }

    public void setDES_STATUS(String DES_STATUS) {
        this.DES_STATUS = DES_STATUS;
    }
}
