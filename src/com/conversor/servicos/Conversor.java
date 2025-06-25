package com.conversor.servicos;

import com.conversor.modelos.TaxaCambioApiResponse;

import java.util.Map;

public class Conversor {


    public double converte(double valor, String moedaAlvo, TaxaCambioApiResponse taxas) {

        Map<String, Double> mapaDeTaxas = taxas.conversion_rates();


        Double taxa = mapaDeTaxas.get(moedaAlvo.toUpperCase());

        if (taxa == null) {

            throw new IllegalArgumentException("Moeda alvo '" + moedaAlvo + "' não encontrada nas taxas de conversão.");
        }


        return valor * taxa;
    }
}