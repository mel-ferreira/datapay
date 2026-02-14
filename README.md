# 📊 API de Transações e Estatísticas

## 1. Introdução

Este projeto consiste na implementação de uma API REST responsável por:

- Receber transações financeiras
- Armazená-las em memória
- Retornar estatísticas baseadas nas transações ocorridas nos últimos 60 segundos

A aplicação foi desenvolvida utilizando Java (ou Kotlin) com Spring Boot.

---

## 2. Restrições Técnicas

O projeto deve obrigatoriamente atender aos seguintes requisitos:

### 📁 Repositório

- Deve estar disponível publicamente no GitHub ou GitLab
- NÃO deve ser fork de nenhum outro projeto
- Deve conter no mínimo 1 commit por endpoint (mínimo de 3 commits)
- Todos os commits devem ser realizados pelo mesmo usuário que criou o projeto
- O histórico de commits deve demonstrar a evolução do projeto

⚠️ Atenção: Caso utilize contas pessoais e profissionais diferentes, certifique-se de usar a mesma conta durante todo o desenvolvimento.

---

### ⚙️ Implementação

- Deve seguir exatamente os endpoints especificados  
  Exemplo: `/transacao` não é a mesma coisa que `/transacoes`
- Deve aceitar e responder com objetos exatamente como descritos  
  Exemplo: `dataHora` não é a mesma coisa que `data-hora` ou `dtTransacao`
- NÃO deve utilizar banco de dados (H2, MySQL, PostgreSQL, etc.)
- NÃO deve utilizar sistemas de cache (Redis, Memcached, Infinispan, etc.)
- Deve armazenar todos os dados em memória
- Deve aceitar e responder apenas com JSON

⚠️ Por motivos de segurança, o projeto não deve ser enviado como arquivo. Ele deve estar publicamente acessível para avaliação. Após a correção, poderá ser tornado privado.

---

## 3. Endpoints da API

### 3.1 Receber Transações  
**POST /transacao**

Este endpoint recebe transações contendo valor e dataHora:

```json
{
  "valor": 123.45,
  "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

#### 📌 Campos

| Campo     | Significado                                                | Obrigatório |
|-----------|------------------------------------------------------------|------------|
| valor     | Valor decimal da transação (ponto flutuante)              | Sim        |
| dataHora  | Data/Hora no padrão ISO 8601                               | Sim        |

💡 Dica: Utilize `OffsetDateTime`, pois o Spring Boot entende ISO 8601 nativamente.

---

### ✅ Regras de Validação

A API só aceitará transações que:

- Possuam os campos `valor` e `dataHora`
- Não tenham valor negativo
- Tenham valor igual ou maior que 0
- Não tenham data futura
- Tenham ocorrido em qualquer momento no passado

---

### 📤 Respostas Esperadas

- **201 Created** (sem corpo)  
  Transação válida e registrada.

- **422 Unprocessable Entity** (sem corpo)  
  Transação inválida (ex: valor negativo ou data futura).

- **400 Bad Request** (sem corpo)  
  JSON inválido ou requisição malformada.

---

### 3.2 Limpar Transações  
**DELETE /transacao**

Apaga todas as transações armazenadas em memória.

#### 📤 Resposta Esperada

- **200 OK** (sem corpo)  
  Todas as informações foram apagadas com sucesso.

---

### 3.3 Calcular Estatísticas  
**GET /estatistica**

Retorna estatísticas das transações ocorridas nos últimos 60 segundos.

```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
```

#### 📌 Campos

| Campo | Significado                                                       | Obrigatório |
|-------|-------------------------------------------------------------------|------------|
| count | Quantidade de transações nos últimos 60 segundos                 | Sim        |
| sum   | Soma total das transações nos últimos 60 segundos                | Sim        |
| avg   | Média das transações nos últimos 60 segundos                     | Sim        |
| min   | Menor valor transacionado nos últimos 60 segundos                | Sim        |
| max   | Maior valor transacionado nos últimos 60 segundos                | Sim        |

💡 Dica: Pode-se utilizar `DoubleSummaryStatistics` (Java 8+) para auxiliar no cálculo.

---

### 📤 Resposta Esperada

- **200 OK**  
  JSON contendo `count`, `sum`, `avg`, `min` e `max`.

⚠️ Caso não existam transações nos últimos 60 segundos, todos os valores devem ser retornados como **0 (zero)**.
