/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import org.json.JSONObject;
import java.util.List;
import java.text.*;
import java.util.Calendar;
import oshi.util.Util;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author beatriz
 */
public class Monitoracao {

    //Criando uma nova classe de infos do Sistema
    SystemInfo si = new SystemInfo();
    OperatingSystem os = si.getOperatingSystem(); //pegando infos do OS do sistema
    HardwareAbstractionLayer hal = si.getHardware(); //Infos de Hardware do sistema
    List<HWDiskStore> dadosDisco = hal.getDiskStores(); //Uma lista de dados do disco do meu Hardware
    GlobalMemory memoria = hal.getMemory();          //Pego memória do meu hard
    CentralProcessor cpu = hal.getProcessor();      //E as informações da cpu
    long[] oldTricks = cpu.getSystemCpuLoadTicks();

    //Pegando uso de CPU
    public String usoCpu() {

        //Variavel com o valor de uso da CPU
        Double pctCpu = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
        //Convertendo o valor de uso da CPU 
        pctCpu = pctCpu * 100d;
        //Formatando o valor para string e trocando virgula por ponto
        String valorCpu = String.format("%.2f", pctCpu);
        valorCpu = valorCpu.replace(",", ".");
        return valorCpu;
    }
    
    //Pegando dados de datetime
    public String dataHora() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
    
    //Pegando processos
    public void processos() {
        //Aqui só imprimindo o cabeçalho, esses números servem para dizer o espaçamento
        //positivos vão para direita e negativos para esquerda
        System.out.format("%4s %10s %8s %8s %7s", "ID", "Nome", "RAM", "CPU", "Bytes");

        //Coloco num loop para que ele me atualize 10x 
        for (int i = 0; i < 10; i++) {
            //Crio outro loop, iniciando minha variável process, e limitando ela a pegar 
            //somente X processos e ordena-los por MEMÓRIA  

            for (OSProcess process : os.getProcesses(5, OperatingSystem.ProcessSort.MEMORY)) {
                //Aqui vamos criar nossas variáveis de leitura        
                long ramMemory = process.getResidentSetSize() / 1024;             //RAM, no Linux RE
                double cpuUsage = process.getProcessCpuLoadBetweenTicks(process); //CPU    
                int processID = process.getProcessID();                           //ID do processo
                String processName = process.getName();                           //Nome do processo    
                long bytesRead = process.getBytesRead();                          //Bytes de Leitura do Disco    

                //Imprimindo os dados em formatação estilo "matriz"/tabela        
                System.out.format("\n %-8d %-8s %-8d %-8f %-10d", processID, processName, ramMemory, cpuUsage, bytesRead);
            }
            //Dando um delay nas execuções do for     
            Util.sleep(1000);
        }
    }

    //Pegando dados de uso de memória RAM
    public String usoRam() {
        //Código para pegar informações de RAM
        //Pegando o dado de total de RAM e convertendo para double
        long getTotalRam = memoria.getTotal();
        double totalRam = ((double) getTotalRam);

        //Pegando o dado de total disponível de RAM e convertendo para double
        long getAvailableRam = memoria.getAvailable();
        double availableRam = ((double) getAvailableRam);

        //Calculando a quantidade em uso e a porcentagem em uso de Ram
        double usedRam = totalRam - availableRam;
        double pctUsedRam = (usedRam / totalRam) * 100;
        String valorRam = String.format("%.2f", pctUsedRam);
        valorRam = valorRam.replace(",", ".");

        return valorRam;
    }

    //Check se ID fornecido é legítimo
    public String checkId(String id) {

        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        List<String> select = con.query("SELECT idTerminal FROM Terminal "
                + "where idTerminal = " + id + ";",
                new BeanPropertyRowMapper(Terminal.class));

        if (select.size() > 0) {

            //Editando a lista
            String txt = String.format("%s", select);
            String str[] = txt.split(",");
            List<String> lista = new ArrayList();
            lista = Arrays.asList(str);

            String fk = lista.get(0);
            fk = fk.replace("[idTerminal=", "");
            return fk;

        } else {
            System.out.println("ERRO");
            
        }
        return "";
    }
    
    //Checkando se login fornecido é legítimo
    public void checkLogin(String user, String pass) {
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        List<String> select = con.query("SELECT * FROM UsuarioEstacao where "
                + "emailUsuarioEstacao = '" + user + "' and "
                + "senhaUsuarioEstacao = '" + pass + "';", new BeanPropertyRowMapper(UsuarioEstacao.class));

        if (select.size() > 0) {
            System.out.println("OK");
        } else {
            System.out.println("ERRO");
        }
    }

}