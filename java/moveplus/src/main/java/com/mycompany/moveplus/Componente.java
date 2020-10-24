/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;
import javax.swing.JLabel;
import java.util.List;
import oshi.SystemInfo;
import static oshi.driver.linux.proc.DiskStats.getDiskStats;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
public class Componente {
    SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        long[] oldTricks = cpu.getSystemCpuLoadTicks();
        TelaComponente teste = new TelaComponente();
        
    public void teste(JLabel teste) {       
        try {
            while (true) {
                Double stats = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
                teste.setText(String.format("CPU Usage - %.2f", (100d * stats)));
                Thread.sleep(1200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
}
