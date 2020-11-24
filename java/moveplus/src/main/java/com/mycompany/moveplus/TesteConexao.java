/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author enlem
 */
public class TesteConexao {
    
    public static void main(String[] args) {
        
        ConnectionDatabase config = new ConnectionDatabase();

        JdbcTemplate con = new JdbcTemplate(config.getDatasource());
        
        System.out.println(con.queryForList("select * from Monitoracao;"));
        
    }
    
}
