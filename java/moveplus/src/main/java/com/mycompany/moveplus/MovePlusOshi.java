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
        System.out.println("Dados de CPU: " + cpu);
        System.out.println("Sistema Operacional: " + os);
        System.out.println("Memória RAM: " + memoria);
        System.out.println("Disco: " + dadosDisco);
        System.out.println("Teste " + hal.getProcessor());
        System.out.println("Processo" + procs);

        GlobalMemory memory = si.getHardware().getMemory();
        long maxRam = memory.getTotal();
        long availableRam = memory.getAvailable();
        long usedRam = maxRam - availableRam;
        double percentageUsed = Math.ceil((usedRam / maxRam) * 100);

        System.out.println("MaxRam: " + maxRam + " Avaiable: " + availableRam + "Usada" + usedRam + " Porcentagem:" + percentageUsed);

        for (OSProcess process : os.getProcesses(20, OperatingSystem.ProcessSort.MEMORY)) {
            long ramMemory = process.getResidentSetSize() / 1024;
            double cpuUsage = process.getProcessCpuLoadBetweenTicks(process);
            long diskUsage = process.getBytesRead() / process.getBytesWritten();

            System.out.println("   ---   ID   --- Nome         RAM             CPU");
            System.out.println(process.getProcessID() + " - " + process.getName() + " - " + ramMemory + " - " + cpuUsage + " - " + process.getBytesRead());
        }

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
