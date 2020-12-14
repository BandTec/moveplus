/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import org.json.JSONObject;
import oshi.util.Util;

/**
 *
 * @author enlem
 */
public class Alertas {

    private Integer cpu = 0;

    public void cpu() throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "CPU ACIMA DE 90% POR 1 MINUTO");

        slack.sendMessage(message);

    }

    public void ram() throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "RAM ACIMA DE 90% POR 1 MINUTO");

        slack.sendMessage(message);

    }

    public void disco() throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "USO DE DISCO ACIMA DE 50%");

        slack.sendMessage(message);

    }

    public void iniciouManutencao(String id) throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "TERMINAL DE ID " + id + " ESTA EM MANUTENCAO");

        slack.sendMessage(message);

    }

    public void retornouOperacao(String id) throws Exception {
        Slack slack = new Slack();

        JSONObject message = new JSONObject();
        message.put("text", "TERMINAL DE ID " + id + " ESTA OPERANTE");

        slack.sendMessage(message);
    }
}
