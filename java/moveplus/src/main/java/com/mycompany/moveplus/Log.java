/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author adria
 */
public class Log {

    Date dataHora = new Date();
    String dataDia = dataHora.getDate() + "/" + (dataHora.getMonth() + 1) + "/" + (dataHora.getYear() + 1900);
    String hora = dataHora.getHours() + ":" + dataHora.getMinutes() + ":" + dataHora.getSeconds();
    String porc = "%";

    public void altoUsoCpu(String txt) throws IOException, Exception {

        FileWriter arq = new FileWriter("C:\\txt2\\LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\n%s - %s - USO DE CPU SUPERIOR A 50%s"
                + "\nDetalhes:"
                + "\nCPU: %s", dataDia, hora, porc,txt
                + "------------------------\n");
        arq.close();
    }

    public void altoUsoRam(String txtRam) throws IOException, Exception {

        FileWriter arq = new FileWriter("C:\\txt2\\LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("\n%s - %s -USO DE RAM SUPERIOR A 50%s"
                + "\nDetalhes:"
                + "\nRAM: %s", dataDia, hora,porc, txtRam
                + "------------------------\n");
        arq.close();
    }

    public void altoUsoDisco(String txtDisco) throws IOException, Exception {

        FileWriter arq = new FileWriter("C:\\txt2\\LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\n%s - %s - USO DE DISCO SUPERIOR A 50%s"
                + "\nDetalhes:"
                + "\nDISCO: %s", dataDia, hora,porc, txtDisco
                + "------------------------\n");

        arq.close();
    }

}
