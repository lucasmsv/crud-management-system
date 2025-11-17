<h1 align="center">Management System</h1>

###

<p>
  Sistema CRUD desenvolvido em <strong>Java</strong> com integração ao <strong>MySQL</strong>.  
  Permite criar, visualizar, atualizar e excluir usuários de forma prática.
</p>

###

<h2>🚀 Funcionalidades</h2>
<ul>
  <li>Criar usuário</li>
  <li>Exibir todos os usuários</li>
  <li>Exibir usuário específico</li>
  <li>Atualizar dados do usuário</li>
  <li>Deletar usuário</li>
  <li>Deletar todos os registros</li>
</ul>

###

<h2>🛠 Tecnologias Utilizadas</h2>
<ul>
  <li>Java</li>
  <li>MySQL</li>
  <li>JDBC</li>
</ul>

###

<h2>📦 Como executar</h2>
<ol>
  <li>Clone o repositório:<br>
    <code>git clone https://github.com/lucasmsv/crud-management-system.git</code>
  </li>
  <li>Abra o projeto na sua IDE</li>
  <li>Adicione o MySQL Connector/J</li>
  <li>Configure a conexão no arquivo responsável por conectar ao banco</li>
  <li>Execute a classe <code>Main</code></li>
</ol>

###

<h2>📊 Estrutura do Projeto</h2>

<pre>
src/
 ├── service/
 │    └── UsuarioService.java    # Responsável pelas operações no banco (CRUD)
 │
 ├── model/
 │    └── Usuario.java           # Classe que representa o usuário
 │
 ├── conexao/
 │    └── Conectar.java          # Classe de conexão com o MySQL
 │
 └── Main.java                   # Executa o sistema e chama as funções CRUD
</pre>

###

<h2>⚙️ Configuração do Banco de Dados</h2>

<p>Crie a tabela utilizada pelo sistema:</p>

<pre>
create table if not exists users
(
	id int not null auto_increment primary key,
    registration int not null unique,
    name varchar(100) not null,
    cpf varchar(20) not null unique,
    dateBirth date null
);
</pre>

###

<h2>▶️ Como Executar</h2>

<ol>
  <li>Instale o MySQL e configure usuário/senha.</li>
  <li>Baixe o MySQL Connector/J e adicione ao projeto.</li>
  <li>Configure o método <code>Conectar.getConnection()</code> com seus dados do MySQL.</li>
  <li>Execute o arquivo <code>Main.java</code>.</li>
</ol>

###


