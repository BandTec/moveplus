/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.util.Util;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
/**
 *
 * @author enlem
 */
public class Run {
    
    
    public static void main(String[] args) throws Exception {

        Monitoracao mpo = new Monitoracao();

//        Chamando a conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());
        mpo.checkId("2");
//        mpo.checkLogin("joao.silva@cptm.gov.br", "qwerty123");

        while (true) {
            String cpu = mpo.usoCpu();
            String ram = mpo.usoRam();
            String disco = mpo.usoDisco();
            String datetime = mpo.dataHora();
            String insert = "INSERT INTO Monitoracao (memoriaMonitoracao,"
                    + "cpuMonitoracao,discoMonitoracao,redeMonitoracao,"
                    + "dataHoraMonitoracao, fkTerminal) values (" + ram + ","
                    + cpu + "," + disco + ",00.00,'" + datetime + "',"
                    + mpo.FK + ");";

            System.out.println("------------------------------");
            System.out.println("CPU:        " + cpu);
            System.out.println("RAM:        " + ram);
            System.out.println("DISCO:      " + disco);
            System.out.println("DATETIME:   " + datetime);
            System.out.println("FKESTACAO   " + mpo.FK);
            con.update(insert);
            Util.sleep(5000);
        }
    
        
    }
    
}
