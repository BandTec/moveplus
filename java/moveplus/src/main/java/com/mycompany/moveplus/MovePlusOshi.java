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
import oshi.software.os.OperatingSystem;

public class MovePlusOshi {

    public static void main(String[] args) {

        //Pega dados de CPU
        CentralProcessor cpu = new SystemInfo().getHardware().getProcessor();

        //Instância o objeto para pegar outras informações sobre o Hardware e Sistema
        SystemInfo si = new SystemInfo();

        //Pro
        HardwareAbstractionLayer hal = si.getHardware();

        //Pega dados da memória
        GlobalMemory memoria = hal.getMemory();

        //Pega dados do sistema operacional
        OperatingSystem os = si.getOperatingSystem();

        //Pega dados do Disco
        List<HWDiskStore> dadosDisco = hal.getDiskStores();

        //Exibe os dados coletados
        System.out.println("Dados de CPU: " + cpu);
        System.out.println("Sistema Operacional: " + os);
        System.out.println("Memória RAM: " + memoria);
        System.out.println("Disco: " + dadosDisco);
        System.out.println("Tese " + hal.getProcessor());
    }
}
