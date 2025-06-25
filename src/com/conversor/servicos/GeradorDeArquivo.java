package com.conversor.servicos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.conversor.util.LocalDateTimeAdapter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class GeradorDeArquivo {

    public void salvaJson(List<?> lista) throws IOException {


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        FileWriter escrita = new FileWriter("historico.json");
        escrita.write(gson.toJson(lista));
        escrita.close();
    }
}