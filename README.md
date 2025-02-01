# 📌 DESAFIOS-ACC

Este repositório contém soluções para os desafios propostos, implementados em **Java**. O projeto abrange duas aplicações:

1. **Gerenciamento de Usuários** - Um sistema web para cadastro, edição e exclusão de usuários
2. **Leitor e Extrator de CSV/TXT** - Uma aplicação backend para importar dados de um arquivo CSV para um banco de dados e exportar os registros para o TXT

---

## 📁 Estrutura do Projeto

```
DESAFIOS-ACC/
├── DESAFIO 01/web-app/             # API de gerenciamento de usuários
├── DESAFIO 02/leitor-extrator/     # Importador e exportador de arquivos CSV/TXT
├── FRONT-END DESAFIO 01/           # Interface web para o gerenciamento de usuários
└── README.md                       # Documentação do projeto
```

---

## 🏗 **Desafio 01 - Gerenciamento de Usuários**
Este desafio consiste em um **CRUD** simples para gestão de usuários via **API REST**, utilizando **Java** e banco de dados **MySQL**.

### **Funcionalidades**
Listar todos os usuários cadastrados
Cadastrar novos usuários  
Editar usuários existentes  
Excluir usuários do sistema  

###  **Tecnologias Utilizadas**
- **Java 21**
- **Maven**
- **JAX-RS (Jersey) + Grizzly Server**
- **MySQL**
- **CORS Filter** para configurar a API

###  **Como Rodar a API**
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/DESAFIOS-ACC.git
   cd DESAFIOS-ACC/DESAFIO\ 01/web-app/
   ```
2. **Configure o banco de dados MySQL**
   - Nome do banco: `desafio01`
   - Tabela `usuario`:
     ```sql
     CREATE TABLE usuario (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nome VARCHAR(100) NOT NULL,
         email VARCHAR(100) UNIQUE NOT NULL,
         telefone VARCHAR(20),
         data_nascimento DATE,
         endereco VARCHAR(255)
     );
     ```
3. **Compile e execute o projeto**

4. **Acesse a API:** `http://localhost:8080/usuarios`

---

## 📂 **Desafio 02 - Leitor e Extrator de CSV/TXT**
Este desafio é um **projeto backend** que importa dados de um arquivo CSV para o banco de dados e exporta os dados cadastrados para um TXT

###  **Funcionalidades**
**Importar CSV** - Lê um arquivo CSV e insere os registros no banco de dados
**Exportar TXT** - Gera um arquivo TXT contendo os dados cadastrados  

### 🔹 **Tecnologias Utilizadas**
- **Java 21**
- **Maven**
- **JDBC para MySQL**
- **Manipulação de arquivos com `BufferedReader` e `FileWriter`**

### 🔹 **Como Rodar o Projeto**
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/DESAFIOS-ACC.git
   cd DESAFIOS-ACC/DESAFIO\ 02/leitor-extrator/
   ```
2. **Compile e execute o projeto**

3. **Importar CSV:** O projeto espera um arquivo `lista.csv` no diretório base. O formato esperado:
   ```csv
   nome;email;telefone
   Rafael;rafael@email.com;11999999999
   Ana;ana@email.com;11988888888
   ```
4. **Exportar TXT:** O projeto gerará um arquivo `saida.txt` com os dados separados por `#`:
   ```txt
   Rafael#rafael@email.com#11999999999
   Ana#ana@email.com#11988888888
   ```

---

## ⚙ **Endpoints da API (Desafio 01)**

| Método | Endpoint          | Descrição |
|---------|------------------|-------------|
| **GET** | `/usuarios`      | Lista todos os usuários |
| **POST** | `/usuarios`      | Cria um novo usuário |
| **PUT** | `/usuarios/{id}` | Atualiza um usuário existente |
| **DELETE** | `/usuarios/{id}` | Remove um usuário |

---

## 🛠 **Possíveis Melhorias**
🔹 Criar uma interface frontend para o **Desafio 02**.  
🔹 Adicionar logs para monitoramento da aplicação.  

---


