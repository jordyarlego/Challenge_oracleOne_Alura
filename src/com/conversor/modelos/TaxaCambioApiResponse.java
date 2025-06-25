package com.conversor.modelos;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public record TaxaCambioApiResponse(
    String result,
    String documentation,
    String terms_of_use,
    long time_last_update_unix,
    String time_last_update_utc,
    long time_next_update_unix,
    String time_next_update_utc,
    String base_code,
    Map<String, Double> conversion_rates) {

    public boolean isSuccess() {
        return "success".equals(result());
    }

    public String getErrorMessage() {
        if ("error".equals(result())) {
            return "Erro na requisição da API. Verifique a chave ou a documentação.";
        }
        return null;
    }
}