# Gerenciador de Pedidos

## 📌 Propósito
O **Gerenciador de Pedidos** é uma aplicação Java com Spring Boot que permite gerenciar categorias, produtos, pedidos e fornecedores. O projeto demonstra o uso de JPA (Java Persistence API) para mapeamento objeto-relacional, incluindo os principais tipos de relacionamentos:

- `@OneToMany` e `@ManyToOne` entre Categoria e Produto
- `@ManyToMany` entre Produto e Pedido
- `@ManyToOne` entre Produto e Fornecedor

## 🚀 Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL Database (para testes)
- Maven

## 🛠️ Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gerenciador-pedidos.git
   cd gerenciador-pedidos
   ```

## Estrutura das Entidades
- **Categoria**: possui uma lista de produtos *@OneToMany*
- **Produto**: pertence a uma categoria e a um fornecedor *@ManyToOne*
- **Pedido**: possui uma lista de produtos *@ManyToMany*
- **Fornecedor**: fornece produtos *@OneToMany* unidirecional via Produto

## 💡 Exemplo de Uso
Na inicialização da aplicação (CommandLineRunner), são criadas categorias, produtos, fornecedores e pedidos com seus respectivos relacionamentos. Os dados são persistidos automaticamente no banco de dados PostGreSql.

## 📂 Pacotes Principais
- **model**: contém as entidades JPA
- **repository**: interfaces que estendem JpaRepository
- **main**: lógica de inicialização e testes de persistência

## ✅ Funcionalidades Demonstradas
- Criação e persistência de entidades
- Relacionamentos entre entidades com JPA
- Uso de `CascadeType.ALL` para salvar entidades relacionadas
- Consulta e exibição de dados com Spring Data JPA