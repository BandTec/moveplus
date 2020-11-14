/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author enlem
 */
public class ConnectionDatabase {
    
    private BasicDataSource datasource;

    public ConnectionDatabase() {
        this.datasource = new BasicDataSource();

        //Driver do banco
        this.datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Url do banco
        this.datasource.setUrl("jdbc:sqlserver://srvdbmoveplus.database.windows.net:1433;database=moveplus-database;user=admindb@srvdbmoveplus;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

        //Usuario        
        this.datasource.setUsername("admindb");

        //Senha
        this.datasource.setPassword("Mastermind2020");
    }

    public BasicDataSource getDatasource() {
        return datasource;
    }
    
}
