/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

/**
 *
 * @author enlem
 */
public class Terminal {

    private String idTerminal;
    private String statusTerminal;
    private String seriesNumberTerminal;
    private String fkEstacao;
    private String fkEmpresaTerminal;
    private String fkConfigTerminal;

    
    
    public String getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(String idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getStatusTerminal() {
        return statusTerminal;
    }

    public void setStatusTerminal(String statusTerminal) {
        this.statusTerminal = statusTerminal;
    }

    public String getSeriesNumberTerminal() {
        return seriesNumberTerminal;
    }

    public void setSeriesNumberTerminal(String seriesNumberTerminal) {
        this.seriesNumberTerminal = seriesNumberTerminal;
    }

    public String getFkEstacao() {
        return fkEstacao;
    }

    public void setFkEstacao(String fkEstacao) {
        this.fkEstacao = fkEstacao;
    }

    public String getFkEmpresaTerminal() {
        return fkEmpresaTerminal;
    }

    public void setFkEmpresaTerminal(String fkEmpresaTerminal) {
        this.fkEmpresaTerminal = fkEmpresaTerminal;
    }

    public String getFkConfigTerminal() {
        return fkConfigTerminal;
    }

    public void setFkConfigTerminal(String fkConfigTerminal) {
        this.fkConfigTerminal = fkConfigTerminal;
    }

    @Override
    public String toString() {
        return "idTerminal=" + idTerminal + ", statusTerminal=" + statusTerminal + ", seriesNumberTerminal=" + seriesNumberTerminal + ", fkEstacao=" + fkEstacao + ", fkEmpresaTerminal=" + fkEmpresaTerminal + ", fkConfigTerminal=" + fkConfigTerminal;
    }
    
    
    
    
    
}