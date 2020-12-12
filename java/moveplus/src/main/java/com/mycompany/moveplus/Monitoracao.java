/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import static java.awt.SystemColor.text;
import java.io.File;
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
import oshi.hardware.NetworkIF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.util.FormatUtil;

/**
 * @author beatriz
 */
public class Monitoracao {

    //Contadores para os logs e alertas do Slack
    Integer contcpu = 0;
    Integer contram = 0;
    Integer contdisco = 0;

    //Constantes para armazenar informações do terminal
    String IDTERMINAL = "";
    String IDCONFIGTERMINAL = "";
    String FKCONFIGTERMINAL = "";

    //Criando uma nova classe de infos do Sistema
    SystemInfo si = new SystemInfo();
    OperatingSystem os = si.getOperatingSystem(); //pegando infos do OS do sistema
    HardwareAbstractionLayer hal = si.getHardware(); //Infos de Hardware do sistema
    List<HWDiskStore> dadosDisco = hal.getDiskStores(); //Uma lista de dados do disco do meu Hardware
    GlobalMemory memoria = hal.getMemory();          //Pego memória do meu hard
    CentralProcessor cpu = hal.getProcessor();      //E as informações da cpu
    long[] oldTricks = cpu.getSystemCpuLoadTicks(); //Uso de CPU
    List<NetworkIF> rede = hal.getNetworkIFs();

    //Pegando dados de uso de memória RAM
    public String usoRam() throws Exception {
        Alertas alert = new Alertas();

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

        //Verificando se a porcentagem de uso da RAM está muito alta
        if (pctUsedRam > 90.0) {
            contram++;
            //Caso esteja muito alta por 1 minuto, gerar log e enviar alerta
            if (contram == 12) {
                alert.ram();
                contram = 0;
            }
        } else { //Caso o uso esteja abaixo de 90%, zerar contador
            contram = 0;
        }

        return valorRam;
    }

    //Pegando uso de CPU
    public String usoCpu() throws Exception {

        Alertas alert = new Alertas();
        //Variavel com o valor de uso da CPU
        Double pctCpu = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
        //Convertendo o valor de uso da CPU 
        pctCpu = pctCpu * 100d;
        //Formatando o valor para string e trocando virgula por ponto
        String valorCpu = String.format("%.2f", pctCpu);
        valorCpu = valorCpu.replace(",", ".");

        //Verificando se o uso é superior a 90%
        if (pctCpu > 90.0) {
            contcpu++;
            //Caso o uso de mantenha acima de 90% durante 1 minuto, gerar log e enviar aleta
            if (contcpu == 12) {
                alert.cpu();
                contcpu = 0;
            }
        } else { //Caso contrário, zere o contador
            contcpu = 0;
        }
        return valorCpu;
    }

    //Pegando uso de Disco
    public String usoDisco() throws Exception {
        Alertas alert = new Alertas();
        File[] roots = File.listRoots();
        for (File root : roots) {
            double free = (double) ((root.getUsableSpace() / 1024) / 1024) / 1024;
            double total = (double) ((root.getTotalSpace() / 1024) / 1024) / 1024;
            double used = total - free;
            double pctdisk = (used * 100) / total;
            String disco = String.format("%.2f", pctdisk);
            disco = disco.replaceAll(",", ".");

            //Verificando se o uso do HD superou 50%
            if (pctdisk > 50.0) {
                contdisco++;
                //Se o uso for superior a 50% durante uma hora, enviar alerta;
                if (contdisco == 720) {
                    alert.disco();
                }

            } else { //Caso uso seja inferior, zerar contador
                contdisco = 0;
            }
            return disco;
        }
        return "";
    }

    //Método para formatar os dados de Disco
    public String catchDiskSize() throws Exception {
        Alertas alert = new Alertas();
        File[] roots = File.listRoots();
        for (File root : roots) {
            //Convertendo o tamanho para GB
            double total = (double) ((root.getTotalSpace() / 1024) / 1024) / 1024;
            //Editando string para envio ao banco
            String a = String.format("%.2f", total);
            a = a.replaceAll(",", ".");
            return a;
        }
        return "";
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

    //Check se ID fornecido é legítimo
    public String checkId(String id) {

        //Chamando conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        //Variável para guardar se o id fornecido contém apenas números ou não
        Boolean onlyNumber;
        String regex = "\\d+";

        //Se contiver algo além de números, onlyNumber = false;
        if (id.matches(regex) == false) {
            onlyNumber = false;
        } else { //se for apenas número, onlyNumber = true;
            onlyNumber = true;
        }

        //Caso o id possua somente numeros
        if (onlyNumber) {

            //Buscando se o ID fornecido existe
            List<String> select = con.query("SELECT * FROM Terminal "
                    + "where idTerminal = " + id + ";",
                    new BeanPropertyRowMapper(Terminal.class));

            //Criando a lista com o resultado da query
            String txt = String.format("%s", select);
            String str[] = txt.split(",");
            List<String> lista = new ArrayList();
            lista = Arrays.asList(str);

            //Salvando o ID
            id = lista.get(0);
            id = id.replace("[idTerminal=", "");
            IDTERMINAL = id;

            //Caso exista...
            if (select.size() > 0) {

                //Salvando o FkConfigTerminal
                String fkConfig = lista.get(5);
                fkConfig = fkConfig.replaceAll("fkConfigTerminal=", "");
                fkConfig = fkConfig.replaceAll("]", "");
                fkConfig = fkConfig.replaceAll(" ", "");
                FKCONFIGTERMINAL = fkConfig;
                System.out.println("FKCONFIGTERMINAL = " + fkConfig);
                return id;

            } else { //Caso não exista, dê erro
                System.out.println("TERMINAL INVÁLIDO");
                System.exit(1);
            }
        } else { //Caso não tenha algo além de números
            System.out.println("TERMINAL INVÁLIDO - DIGITE APENAS O ID");
            System.exit(1);
        }
        return "";
    }

    //Checkando se login fornecido é legítimo
    public void checkLogin(String user, String pass) {

        //Chamando a conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        //Buscando se o login e senha fornecidos existem
        List<String> select = con.query("SELECT * FROM UsuarioEstacao where "
                + "emailUsuarioEstacao = '" + user + "' and "
                + "senhaUsuarioEstacao = '" + pass + "';", new BeanPropertyRowMapper(UsuarioEstacao.class
                ));

        //Caso exista
        if (select.size() > 0) {
            System.out.println("Bem vindo");
        } else { //Caso não exista dê erro e gere um log
            System.out.println("USUÁRIO E/OU SENHA INVÁLIDO(S)");
            System.exit(1);
        }
    }

    //Convertendo dados de hardware do processador
    public String catchCpu() {
        //Convertendo double para String
        String cputxt = String.format("%s", cpu);

        //Criando lista
        String list1 = cputxt;
        String str[] = list1.split(" ");
        List<String> cpulist = new ArrayList<String>();
        cpulist = Arrays.asList(str);

        //Formatando dados do processador para envio ao banco
        String cpux = cpulist.get(0) + cpulist.get(1) + cpulist.get(2);

        return cpux;
    }

    //Convertendo dados de hardware de memória
    public String catchRam() {
        //Convertendo double para String
        String memtxt = String.format("%s", memoria);

        //Criando a lista
        String list1 = memtxt;
        String str[] = list1.split(" ");
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(str);

        //Formatando dados da memória para envio ao banco
        String ramx = list.get(2);
        ramx = ramx.replaceAll("GiB/", "");
        ramx = ramx.replaceAll(",", ".");

        return ramx;
    }

    //Validando configurações da máquina com o banco
    public void checkConfig() throws Exception {

        //Chamando a conexão com o Azure
        ConnectionDatabase config = new ConnectionDatabase();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        //Convertendo para String os valores necessários
        String processador = catchCpu();
        String ram = catchRam();
        String disco = catchDiskSize();
        String so = String.format("%s", os);

        //Buscando se existe uma configuração igual a da máquina já existente
        List<String> select = con.query("SELECT idConfigTerminal FROM ConfigTerminal where "
                + "processadorTerminal = '" + processador + "' and "
                + "memoriaTerminal = " + ram + " and "
                + "discoTerminal = " + disco + " and "
                + "sistemaOperacionalTerminal = '" + so + "';",
                new BeanPropertyRowMapper(ConfigTerminal.class
                ));

        //Criando a lista
        String txt = String.format("%s", select);
        String str[] = txt.split(",");
        List<String> lista = new ArrayList();
        lista = Arrays.asList(str);

        //Formatando valores para serem guarrdados
        String idConfig = lista.get(0);
        idConfig = idConfig.replace("[ConfigTerminal{idConfigTerminal=", "");
        IDCONFIGTERMINAL = idConfig;

        //SE A CONFIGURAÇÃO EXISTIR...
        if (lista.size() > 0) {
            //SE O TERMINAL NÃO POSSUIR CONFIGURAÇÃO, INSERIR CONFIGURAÇÃO
            if (FKCONFIGTERMINAL.equals("null")) {
                System.out.println("TERMINAL SEM CONFIGURAÇÃO");
                String insert = "UPDATE Terminal set fkConfigTerminal = "
                        + IDCONFIGTERMINAL + " where idTerminal = "
                        + IDTERMINAL + ";";
                con.update(insert);
            }
            //Caso o terminal já possua uma configuração, dê erro
            if (!FKCONFIGTERMINAL.equals("null") && !FKCONFIGTERMINAL.equals(IDCONFIGTERMINAL)) {
                System.out.println("TERMINAL FORNECIDO NÃO CORRESPONDE AO HARDWARE ENCONTRADO");
                System.exit(1);
            }

        } else { //SENÃO...
            //Inserir uma nova configuração
            System.out.println("INSERINDO UMA NOVA CONFIGURAÇÃO");
            String insert = "INSERT INTO ConfigTerminal values ('"
                    + processador + "','"
                    + ram + "','"
                    + disco + "','"
                    + so + "');";

            con.update(insert);

            //Buscando se existe uma configuração igual a da máquina já existente
            List<String> select2 = con.query("SELECT idConfigTerminal FROM ConfigTerminal where "
                    + "processadorTerminal = '" + processador + "' and "
                    + "memoriaTerminal = " + ram + " and "
                    + "discoTerminal = " + disco + " and "
                    + "sistemaOperacionalTerminal = '" + so + "';",
                    new BeanPropertyRowMapper(ConfigTerminal.class
                    ));

            //Criando a lista
            String txt2 = String.format("%s", select2);
            String str2[] = txt2.split(",");
            List<String> lista2 = new ArrayList();
            lista2 = Arrays.asList(str2);

            //Formatando valores para serem guarrdados
            idConfig = lista2.get(0);
            idConfig = idConfig.replace("[ConfigTerminal{idConfigTerminal=","");
            IDCONFIGTERMINAL = idConfig;
            System.out.println("IDCONFIGTERMINAL = "+IDCONFIGTERMINAL);

            //Atualizar terminal atual com a nova configuração
            System.out.println("VINCULANDO NOVA CONFIGURAÇÃO AO TERMINAL");
            String insert2 = "UPDATE Terminal set fkConfigTerminal = "
                    + IDCONFIGTERMINAL + " where idTerminal = "
                    + IDTERMINAL + ";";

            con.update(insert2);
        }

    }
}
