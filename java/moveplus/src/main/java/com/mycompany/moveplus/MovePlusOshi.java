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
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;

public class MovePlusOshi {

    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();

        //Pro
        HardwareAbstractionLayer hal = si.getHardware();

        //Pega dados da memória
        GlobalMemory memoria = hal.getMemory();

        //Pega dados do sistema operacional
        OperatingSystem os = si.getOperatingSystem();

        //Pega dados do Disco
        List<HWDiskStore> dadosDisco = hal.getDiskStores();
        
        List<OSProcess> process = os.getProcesses();
        
        CentralProcessor cpu = hal.getProcessor();

        //Exibe os dados coletados
        System.out.println("Dados de CPU: " + cpu);
        System.out.println("Sistema Operacional: " + os);
        System.out.println("Memória RAM: " + memoria);
        System.out.println("Disco: " + dadosDisco);
        System.out.println("Tese " + hal.getProcessor());
        System.out.println("\n"+process);
        
         long [] oldTricks = cpu.getSystemCpuLoadTicks();
        
        try{
            while(true){
                Double stats = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
                System.out.println(100d *stats);
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
