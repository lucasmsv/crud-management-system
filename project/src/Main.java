import conexao.Conectar;
import model.Usuario;
import service.UsuarioService;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
private static final UsuarioService service = new UsuarioService();

    public static void main(String[] args) {
        conexaoDB();
        service.deletarTodosDados();
        System.out.println();
        exibirDadosDB();
        System.out.println();
        novoCadastro(55000, "Nome teste", "000.000.000-00", "01/01/2000");
        System.out.println();
        exibirDadosDB();
        System.out.println();
        buscarMatricula(55000);
        System.out.println();
        alterarDados("Nome atualizado", "123.456.789-01", "10/06/2001", 55000);
        System.out.println();
        deletarMatricula(55001);
        deletarMatricula(55000);
        System.out.println();
    }

    private static void deletarMatricula(int _matricula) {
        service.deletarUsuario(_matricula);
    }

    /*
        Metodo de alterar dados
            Inserir os novos dados para atualização: Nome, CPF, Data de Nascimento
            Matrícula: Apenas confirma que o usuário existe e faz a atualização
    */
    private static void alterarDados(String _newNome, String _newCpf, String _dataNascimento, int _matricula) {
        service.alterarDados(_newNome, _newCpf, _dataNascimento, _matricula);
    }

    private static void buscarMatricula(int _matricula) {
        service.buscarMatricula(_matricula);
    }

    private static void novoCadastro(int _matricula, String _nome, String _cpf, String _dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(_dataNascimento, formatter);
        Usuario usuario = new Usuario(_matricula, _nome, _cpf, dataNascimento);
        service.salvar(usuario);
    }

    private static void exibirDadosDB() {
        service.exibirTodosDados();
    }

    private static void conexaoDB() {
        Connection con = Conectar.getConnection();
        System.out.println("✅ Conexão com banco de dados efetuada.");
    }
}
