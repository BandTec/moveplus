/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

/**
 * @author beatriz
 */

public class MPOshi
 {        
    SystemInfo si = new SystemInfo();    //Criando uma nova classe de infos do Sistema
    OperatingSystem os = si.getOperatingSystem(); //pegando infos do OS do sistema
    HardwareAbstractionLayer hal = si.getHardware(); //Infos de Hardware do sistema    
    CentralProcessor cpu = hal.getProcessor();
    List<HWDiskStore> dadosDisco = hal.getDiskStores(); //Uma lista de dados do disco do meu Hardware
    GlobalMemory memoria = hal.getMemory();          //Pego memória do meu hard   
    
    void printaInfoHard(){                         
 
        //Exibe os dados coletados
        System.out.println("---------------  Dados de CPU  ---------------");
        System.out.println(cpu);
        System.out.println("---------------  Sistema Operacional  ---------------");
        System.out.println(os.getFamily());
        System.out.println("---------------  Memória RAM  ---------------");
        System.out.println(memoria);
        System.out.println("---------------  Disco  ---------------");
        System.out.println(dadosDisco);
        System.out.println("---------------  Processos  ---------------");
           
//Aqui só imprimindo o cabeçalho, esses números servem para dizer o espaçamento
//positivos vão para direita e negativos para esquerda
        System.out.format("%4s %10s %8s %8s %7s", "ID", "Nome", "RAM", "CPU", "Bytes");
     }
    
    void captaProcessos(){
//Coloco num loop para que ele me atualize 10x 
        for(int i = 0; i < 10; i++)
       {   
//Crio outro loop, iniciando minha variável process, e limitando ela a pegar 
//somente X processos e ordena-los por MEMÓRIA  
     
            for(OSProcess process: os.getProcesses(5, OperatingSystem.ProcessSort.MEMORY))
             {   
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
        
    void usoCPU(){
        cpu.getProcessorIdentifier();
        long[] oldTricks = cpu.getSystemCpuLoadTicks();
        System.out.println("");
        System.out.println("---------------  Uso de CPU  ---------------");
            while (true) {
                Double stats = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
                System.out.println(String.format("CPU Usage - %.2f", (100d * stats)));
                Util.sleep(1200);
            }
        
  //   ** Código para pegar informações de RAM **
  
  // RamInfo info = new RamInfo();
  //  List<HWDiskStore> teste = si.getHardware().getDiskStores();
  //      System.out.println(teste);
  //  GlobalMemory memory = si.getHardware().getMemory();
  //        long maxRam = memory.getTotal();
  //        long availableRam = memory.getAvailable();
  //        long usedRam = maxRam - availableRam;
  //        double percentageUsed = Math.ceil((usedRam / maxRam) * 100);
  //     
  //        System.out.println("MaxRam: " + maxRam + " Avaiable: " + availableRam + "Usada" + usedRam + " Porcentagem:" + percentageUsed);
    }

    @Override
    public String toString() {
        return "{" + cpu +'}';
    }
}