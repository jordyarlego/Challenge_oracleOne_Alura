package com.conversor.api;

import com.google.gson.Gson;
import com.conversor.modelos.TaxaCambioApiResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

public class ConsultaApi {


    public TaxaCambioApiResponse buscaTaxas(String moedaBase) {
        String apiKey = carregarChaveApi();
        URI url = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), TaxaCambioApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter as taxas. Verifique o código da moeda ou sua conexão.");
        }
    }


    private String carregarChaveApi() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            return props.getProperty("api.key");
        } catch (IOException ex) {
            throw new RuntimeException("Erro: Não foi possível ler o arquivo config.properties.", ex);
        }
    }


    public Set<String> buscaCodigosDeMoedas() {
        String moedaBaseParaLista = "USD";
        TaxaCambioApiResponse resposta = buscaTaxas(moedaBaseParaLista);

        if (resposta != null && resposta.conversion_rates() != null) {
            return resposta.conversion_rates().keySet();
        } else {
            return Collections.emptySet();
        }
    }
}