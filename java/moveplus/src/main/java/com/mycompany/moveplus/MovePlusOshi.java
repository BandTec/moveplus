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
import java.util.List;
import oshi.SystemInfo;
import static oshi.driver.linux.proc.DiskStats.getDiskStats;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import javax.swing.JLabel;


public class MovePlusOshi {

    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<HWDiskStore> dadosDisco = hal.getDiskStores();
        GlobalMemory memoria = hal.getMemory();
        CentralProcessor cpu = hal.getProcessor();
        OperatingSystem os = si.getOperatingSystem();
        List<OSProcess> procs = os.getProcesses();

        long[] oldTricks = cpu.getSystemCpuLoadTicks();

        
        //Exibe os dados coletados
        System.out.println("---------------  Dados de CPU  ---------------");
        System.out.println(cpu);
        System.out.println("---------------  Sistema Operacional  ---------------");
        System.out.println(os);
        System.out.println("---------------  Memória RAM  ---------------");
        System.out.println(memoria);
        System.out.println("---------------  Disco  ---------------");
        System.out.println(dadosDisco);
        System.out.println("---------------  Processos  ---------------");

        System.out.format("%4s %10s %8s %8s %7s", "ID", "Nome", "RAM", "CPU", "Bytes");
    
        //Coloco num loop para que ele me atualize 10x 

        for(int i = 0; i < 10; i++)
       {   
        //Crio outro loop, iniciando minha variável process, e limitando ela a pegar 
        //somente X processos e ordena-los por MEMÓRIA  
     
            for(OSProcess process: os.getProcesses(2, OperatingSystem.ProcessSort.MEMORY))
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
        }
        System.out.println("");
        System.out.println("---------------  Uso de CPU  ---------------");
        try {
            while (true) {
                Double stats = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
                System.out.println(String.format("CPU Usage - %.2f", (100d * stats)));
                Thread.sleep(1200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
