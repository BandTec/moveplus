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
public class UsuarioEstacao {
    
    private Integer idUsuarioEstacao;
    private String credencialUsuarioEstacao;
    private String emailUsuarioEstacao;
    private String senhaUsuarioEstacao;
    private Integer fkEstacao;

    @Override
    public String toString() {
        return "idUsuarioEstacao=" + idUsuarioEstacao + ", credencialUsuarioEstacao=" + credencialUsuarioEstacao + ", emailUsuarioEstacao=" + emailUsuarioEstacao + ", senhaUsuarioEstacao=" + senhaUsuarioEstacao + ", fkEstacao=" + fkEstacao + '}';
    }

    public Integer getIdUsuarioEstacao() {
        return idUsuarioEstacao;
    }

    public void setIdUsuarioEstacao(Integer idUsuarioEstacao) {
        this.idUsuarioEstacao = idUsuarioEstacao;
    }

    public String getCredencialUsuarioEstacao() {
        return credencialUsuarioEstacao;
    }

    public void setCredencialUsuarioEstacao(String credencialUsuarioEstacao) {
        this.credencialUsuarioEstacao = credencialUsuarioEstacao;
    }

    public String getEmailUsuarioEstacao() {
        return emailUsuarioEstacao;
    }

    public void setEmailUsuarioEstacao(String emailUsuarioEstacao) {
        this.emailUsuarioEstacao = emailUsuarioEstacao;
    }

    public String getSenhaUsuarioEstacao() {
        return senhaUsuarioEstacao;
    }

    public void setSenhaUsuarioEstacao(String senhaUsuarioEstacao) {
        this.senhaUsuarioEstacao = senhaUsuarioEstacao;
    }

    public Integer getFkEstacao() {
        return fkEstacao;
    }

    public void setFkEstacao(Integer fkEstacao) {
        this.fkEstacao = fkEstacao;
    }
    
    
    
}
