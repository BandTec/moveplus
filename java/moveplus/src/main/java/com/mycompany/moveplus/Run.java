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

        //Chamando a conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());
        Run d = new Run();
        d.slack();
        System.out.println("ID");
        mpo.checkId("1");
        System.out.println("LOGIN");
        mpo.checkLogin("joao.silva@cptm.gov.br", "qwerty123");

        while (true) {
            String insert = "INSERT INTO Monitoracao (memoriaMonitoracao,"
                    + "cpuMonitoracao,discoMonitoracao,redeMonitoracao,"
                    + "dataHoraMonitoracao, fkTerminal) values (" + mpo.usoRam() + ","
                    + mpo.usoCpu() + ",00.00,00.00, '" + mpo.dataHora() + "', "
                    + mpo.checkId("1") + ");";

            System.out.println("------------------------------");
            System.out.println(insert);
            System.out.println("RAM:        " + mpo.usoRam());
            System.out.println("CPU:        " + mpo.usoCpu());
            System.out.println("DATETIME:   " + mpo.dataHora());
            System.out.println("FKESTACAO   " + mpo.checkId("1"));
            con.update(insert);
            Util.sleep(5000);
        }
    
    
    }
    
    //Enviar mensagem para o slack
    public  void slack() throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "Teste");

        slack.sendMessage(message);
    }
    
}
