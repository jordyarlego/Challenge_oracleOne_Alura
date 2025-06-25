# 🪙 Conversor de Moedas - Challenge ONE (Oracle + Alura)

![Status do Projeto](https://img.shields.io/badge/Status-Concluído-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Gson](https://img.shields.io/badge/Biblioteca-Gson-orange)

## 📖 Sobre o Projeto

Este é um conversor de moedas interativo desenvolvido em **Java puro**, que roda diretamente no **console**.  
O projeto foi desenvolvido como parte do **Challenge Back-End - Java** do programa **ONE (Oracle Next Education)** em parceria com a **Alura Latam**.

O objetivo foi aplicar conceitos fundamentais e avançados de **Orientação a Objetos**, boas práticas de programação e o **consumo de APIs externas** para criar uma aplicação funcional e bem estruturada.  
A aplicação permite ao usuário converter valores entre diversas moedas, utilizando **taxas de câmbio atualizadas em tempo real**.

---

## ✅ Funcionalidades Principais

- 🔄 **Conversão Dinâmica:** O usuário pode escolher qualquer moeda de origem e destino (ex: de Dólar para Iene, de Real para Euro, etc.).
- 🌐 **Taxas de Câmbio em Tempo Real:** Conecta-se à [ExchangeRate-API](https://www.exchangerate-api.com/) para buscar as cotações mais recentes.
- 📃 **Listagem de Moedas:** Oferece uma opção para o usuário listar todos os códigos de moedas disponíveis, facilitando o uso.
- 🧾 **Histórico de Conversões:** Todas as operações bem-sucedidas são registradas em um arquivo `historico.json`, com detalhes da transação e timestamp.
- 🔐 **Configuração Segura:** A chave da API é carregada a partir de um arquivo de configuração externo (`config.properties`), que é ignorado pelo Git para garantir a segurança.
- ⚠️ **Tratamento de Erros:** A aplicação está preparada para lidar com entradas inválidas do usuário e falhas na comunicação com a API, fornecendo feedback claro.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17:** Linguagem principal do projeto.
- **Java HTTP Client:** Para realizar as requisições à API externa.
- **Google Gson:** Biblioteca para interpretar respostas em JSON da API e para gerar o arquivo de histórico.
- **IDE:** Desenvolvido e testado no IntelliJ IDEA.

---

## ⚙️ Como Configurar e Executar

### 1. Clone o Repositório

```bash
git clone https://github.com/jordyarlego/Challenge_oracleOne_Alura.git
cd Challenge_oracleOne_Alura
```

### 2. Obtenha sua Chave de API

- Acesse [www.exchangerate-api.com](https://www.exchangerate-api.com) e crie uma conta gratuita.
- Copie sua chave de API após o cadastro.

### 3. Crie o Arquivo de Configuração

Na pasta raiz do projeto, crie um arquivo chamado `config.properties` e adicione a chave no seguinte formato:

```properties
api.key=SUA_CHAVE_DE_API_VAI_AQUI
```

> **Nota:** Esse arquivo está no `.gitignore` e não será versionado.

### 4. Adicione a Biblioteca Gson

Como este projeto **não utiliza Maven ou Gradle**, você precisa adicionar o `.jar` da Gson manualmente:

1. Baixe o `.jar` da Gson em: [https://github.com/google/gson](https://github.com/google/gson)
2. No IntelliJ:
   - Vá em `File > Project Structure... > Libraries`
   - Clique no **+**, escolha **Java** e selecione o arquivo `.jar` baixado.

### 5. Execute a Aplicação 🚀

- Abra o projeto na sua IDE (como IntelliJ IDEA ou Eclipse).
- Vá até o arquivo `Main.java` localizado em:

```
src/com/conversor/main/Main.java
```

- Clique com o botão direito no arquivo e escolha `Run 'Main.main()'`.

A aplicação será executada no console, pronta para uso!

---

## 📁 Estrutura do Projeto

A estrutura de pacotes foi organizada para seguir os princípios de **SOLID** e **Clean Code**.

```
Challenge_oracleOne_Alura/
├── .gitignore
├── config.properties          # Arquivo de configuração (não versionado)
├── historico.json            # Arquivo de saída com o histórico (não versionado)
├── README.md
└── src/
    └── com/
        └── conversor/
            ├── api/
            │   └── ConsultaApi.java              # Comunicação com a API externa
            ├── main/
            │   ├── Main.java                     # Ponto de entrada da aplicação
            │   └── Menu.java                     # Interface de interação com o usuário
            ├── modelos/
            │   ├── HistoricoConversao.java       # Record para o histórico
            │   └── TaxaCambioApiResponse.java    # Record para a resposta da API
            ├── servicos/
            │   ├── Conversor.java                # Lógica de conversão
            │   └── GeradorDeArquivo.java         # Geração do arquivo de histórico
            └── util/
                └── LocalDateTimeAdapter.java     # Adaptador para o Gson entender LocalDateTime
```

---

## 👨‍💻 Autor

Feito com ❤️ por [jordy.dev](https://github.com/jordyarlego)

---

## 📜 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.
