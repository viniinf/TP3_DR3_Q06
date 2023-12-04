package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }


}

public class Main {
    public static void main(String[] args) {
        // Nome
        String nome = "Vinícius";

        try {
            // URL com o nome fornecido
            URL url = new URL("https://api.agify.io/?name=" + nome);

            // Abre a conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lê a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Converte a resposta para um objeto JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Obtém os valores do JSON
            String nomeResposta = jsonResponse.getString("name");
            int idadeResposta = jsonResponse.getInt("age");

            // Cria um objeto Pessoa com os valores obtidos
            Pessoa pessoa = new Pessoa(nomeResposta, idadeResposta);

            // Imprime o objeto Pessoa
            System.out.println(pessoa);

            // Fecha a conexão
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}