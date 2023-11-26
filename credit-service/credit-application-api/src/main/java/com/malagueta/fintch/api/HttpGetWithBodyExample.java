package com.malagueta.fintch.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetWithBodyExample {
    public static void main(String[] args) {
        try {
            // URL para a qual você deseja enviar a solicitação GET com corpo
            String url = "http://localhost:8082/teste";

            // Criação do objeto URL a partir da string da URL
            URL obj = new URL(url);

            // Abre a conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Configura o método de solicitação para GET
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-TenantID","tenant_1");
            connection.setRequestProperty("Content-Type","application/json");

            // Habilita a escrita para permitir o envio do corpo
            connection.setDoOutput(true);

            // Adiciona dados ao corpo da solicitação
            String requestBody = "{  \"employID\": 1,  \"name\": \"John Doe\"}";
            System.out.println(requestBody);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtém a resposta do servidor
            int responseCode = connection.getResponseCode();
            System.out.println("Código de Resposta: " + responseCode);

            // (O restante do código para ler a resposta permanece o mesmo)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
