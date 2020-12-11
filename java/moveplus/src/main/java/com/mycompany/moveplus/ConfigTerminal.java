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
public class ConfigTerminal {
    
    private Integer idConfigTerminal;
    private String processadorTerminal;
    private String memoriaTerminal;
    private String discoTerminal;
    private String sistemaOperacionalTerminal;

    @Override
    public String toString() {
        return "ConfigTerminal{" + "idConfigTerminal=" + idConfigTerminal + ", processadorTerminal=" + processadorTerminal + ", memoriaTerminal=" + memoriaTerminal + ", discoTerminal=" + discoTerminal + ", sistemaOperacionalTerminal=" + sistemaOperacionalTerminal + '}';
    }

    public Integer getIdConfigTerminal() {
        return idConfigTerminal;
    }

    public void setIdConfigTerminal(Integer idConfigTerminal) {
        this.idConfigTerminal = idConfigTerminal;
    }

    public String getProcessadorTerminal() {
        return processadorTerminal;
    }

    public void setProcessadorTerminal(String processadorTerminal) {
        this.processadorTerminal = processadorTerminal;
    }

    public String getMemoriaTerminal() {
        return memoriaTerminal;
    }

    public void setMemoriaTerminal(String memoriaTerminal) {
        this.memoriaTerminal = memoriaTerminal;
    }

    public String getDiscoTerminal() {
        return discoTerminal;
    }

    public void setDiscoTerminal(String discoTerminal) {
        this.discoTerminal = discoTerminal;
    }

    public String getSistemaOperacionalTerminal() {
        return sistemaOperacionalTerminal;
    }

    public void setSistemaOperacionalTerminal(String sistemaOperacionalTerminal) {
        this.sistemaOperacionalTerminal = sistemaOperacionalTerminal;
    }
    
    
    
    
}
