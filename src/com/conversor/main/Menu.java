package com.conversor.main;

import com.conversor.api.ConsultaApi;
import com.conversor.modelos.HistoricoConversao;
import com.conversor.modelos.TaxaCambioApiResponse;
import com.conversor.servicos.Conversor;
import com.conversor.servicos.GeradorDeArquivo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsultaApi consultaApi = new ConsultaApi();
    private final Conversor conversor = new Conversor();
    private final List<HistoricoConversao> historico = new ArrayList<>();

    public void exibir() {
        System.out.println("*************************************************");
        System.out.println("Bem vindo(a) ao Conversor de Moedas Dinâmico!");
        System.out.println("*************************************************");

        while (true) {
            try {
                System.out.println("\n--- MENU ---");
                System.out.print("Digite o código da moeda de ORIGEM (ex: USD), 'LISTAR' para ver as opções, ou 'SAIR': ");
                String moedaBase = leitura.nextLine().toUpperCase();

                if (moedaBase.equalsIgnoreCase("SAIR")) {
                    break;
                }


                if (moedaBase.equalsIgnoreCase("LISTAR")) {
                    exibirCodigosDisponiveis();
                    continue;
                }

                System.out.print("Digite o código da moeda de DESTINO (ex: EUR): ");
                String moedaAlvo = leitura.nextLine().toUpperCase();

                System.out.print("Digite o valor a ser convertido: ");
                double valor = Double.parseDouble(leitura.nextLine());

                TaxaCambioApiResponse taxas = consultaApi.buscaTaxas(moedaBase);
                double valorConvertido = conversor.converte(valor, moedaAlvo, taxas);

                System.out.printf(">> RESULTADO: %.2f %s equivalem a %.2f %s\n", valor, moedaBase, valorConvertido, moedaAlvo);

                HistoricoConversao novaConversao = new HistoricoConversao(moedaBase, moedaAlvo, valor, valorConvertido, LocalDateTime.now());
                historico.add(novaConversao);

            } catch (NumberFormatException e) {
                System.err.println("Erro: Valor inválido. Por favor, digite um número.");
            } catch (RuntimeException e) {
                System.err.println("Erro na conversão: " + e.getMessage());
            }
        }

        salvarHistorico();
        System.out.println("\nPrograma encerrado. Histórico de conversões salvo.");
        leitura.close();
    }


    private void exibirCodigosDisponiveis() {
        System.out.println("\n--- Carregando códigos de moedas disponíveis... ---");
        try {
            Set<String> codigos = consultaApi.buscaCodigosDeMoedas();
            System.out.println("Códigos de Moeda Suportados:");


            List<String> listaOrdenada = new ArrayList<>(codigos);
            listaOrdenada.sort(null);


            int colunas = 8;
            for (int i = 0; i < listaOrdenada.size(); i++) {
                System.out.printf("%-10s", listaOrdenada.get(i));
                if ((i + 1) % colunas == 0) {
                    System.out.println();
                }
            }
            System.out.println("\n-------------------------------------------------");
        } catch (RuntimeException e) {
            System.err.println("Não foi possível carregar a lista de moedas: " + e.getMessage());
        }
    }

    private void salvarHistorico() {
        if (historico.isEmpty()) {
            return;
        }
        try {
            GeradorDeArquivo gerador = new GeradorDeArquivo();
            gerador.salvaJson(historico);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo de histórico: " + e.getMessage());
        }
    }
}