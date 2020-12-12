/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import java.util.Scanner;
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
        //Chamando a conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());
        
        //Scanner de teclado
        Scanner input = new Scanner(System.in);
        
        //Instanciando a classe de Monitoracao
        Monitoracao mpo = new Monitoracao();
        
        mpo.catchCpu();

        //Recebendo informações de login
        System.out.println("Digite seu email");
        String user = input.nextLine();
        System.out.println("Digite sua senha");
        String pass = input.nextLine();
        
        //Enviando informações de login
        mpo.checkLogin(user,pass);
        
        //Recebendo ID da máquina
        System.out.println("Digite o ID da máquina");
        String id = input.nextLine();
        
        //Enviando ID
        mpo.checkId(id);
        
        //Verificando configuração do terminal
        mpo.checkConfig();

        //Loop de inserts da Monitoracao
        while (true) {
            String cpu = mpo.usoCpu();
            String ram = mpo.usoRam();
            String disco = mpo.usoDisco();
            String datetime = mpo.dataHora();
            String insert = "INSERT INTO Monitoracao (memoriaMonitoracao,"
                    + "cpuMonitoracao,discoMonitoracao,dataHoraMonitoracao,"
                    + "fkTerminal) values (" + ram + ","
                    + cpu + "," + disco + ",'" + datetime + "',"
                    + mpo.IDTERMINAL + ");";

            System.out.println("------------------------------");
            System.out.println("CPU:        " + cpu);
            System.out.println("RAM:        " + ram);
            System.out.println("DISCO:      " + disco);
            System.out.println("DATETIME:   " + datetime);
            System.out.println("FKTERMINAL: " + mpo.IDTERMINAL);
            con.update(insert);
            Util.sleep(5000);
        }
    }

}
