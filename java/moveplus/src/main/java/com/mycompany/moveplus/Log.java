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
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author adria
 */
public class Log {

    Date dataHora = new Date();
    String dataDia = dataHora.getDate() + "/" + (dataHora.getMonth() + 1) + "/" + (dataHora.getYear() + 1900);
    String hora = dataHora.getHours() + ":" + dataHora.getMinutes() + ":" + dataHora.getSeconds();
    String porc = "%";

    Monitoracao mpo = new Monitoracao();

    //Chamando a conexão com o Azure
    ConnectionDatabase config = new ConnectionDatabase();
    JdbcTemplate con = new JdbcTemplate(config.getDatasource());

    public void altoUsoRam(String txtRam, String fkTerminal) throws IOException, Exception {

        String insert = "INSERT INTO TerminalAlerta values ('"
                + mpo.dataHora() + "'," + fkTerminal + ", 1);";

        con.update(insert);

        FileWriter arq = new FileWriter("./LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("\n%s - %s -USO DE RAM SUPERIOR A 90%s"
                + "\nDetalhes:"
                + "\nRAM: %s", dataDia, hora, porc, txtRam
                + "------------------------\n");
        arq.close();
    }

    public void altoUsoCpu(String txt, String fkTerminal) throws IOException, Exception {

        String insert = "INSERT INTO TerminalAlerta values ('"
                + mpo.dataHora() + "'," + fkTerminal + ", 2);";

        con.update(insert);

        FileWriter arq = new FileWriter("./LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\n%s - %s - USO DE CPU SUPERIOR A 90%s"
                + "\nDetalhes:"
                + "\nCPU: %s", dataDia, hora, porc, txt
                + "------------------------\n");
        arq.close();

    }

    public void altoUsoDisco(String txtDisco, String fkTerminal) throws IOException, Exception {
        String insert = "INSERT INTO TerminalAlerta values ('"
                + mpo.dataHora() + "'," + fkTerminal + ", 2);";

        con.update(insert);

        FileWriter arq = new FileWriter("./LOG.txt", true);

        //Criação do objeto para gravar no arquivo
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\n%s - %s - USO DE DISCO SUPERIOR A 50%s"
                + "\nDetalhes:"
                + "\nDISCO: %s", dataDia, hora, porc, txtDisco
                + "------------------------\n");

        arq.close();
    }

}
