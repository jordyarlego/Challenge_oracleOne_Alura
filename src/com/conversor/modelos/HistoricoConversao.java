package com.conversor.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public record HistoricoConversao(
        String moedaBase,
        String moedaAlvo,
        double valorOriginal,
        double valorConvertido,
        LocalDateTime timestamp
) {

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format(
                "[%s] Conversão: %.2f %s => %.2f %s",
                timestamp.format(formatter),
                valorOriginal,
                moedaBase,
                valorConvertido,
                moedaAlvo
        );
    }
}