package service;

import conexao.Conectar;
import model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private static final List<Usuario> listaUsuarios = new ArrayList<>();

    /*
        Exibe todos os usuários maiores de 18 anos
    */
    public void exibirTodosDados() {
        String sql = "select * from users where timestampdiff(year, dateBirth, curdate()) >= 18";
        Connection conn;
        PreparedStatement stmt;
        ResultSet rset;
        try {
            conn = Conectar.getConnection();
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();
            while (rset.next()) {
                Usuario u = new Usuario();
                u.setMatricula(rset.getInt("registration"));
                u.setNome(rset.getString("name"));
                u.setCpf(rset.getString("cpf"));
                u.setDataNascimento(LocalDate.parse(rset.getString("dateBirth")));
                listaUsuarios.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao exibir dados: " + e.getMessage());
        }

        if (listaUsuarios.isEmpty()) {
            System.out.println("❌ Nenhum dado registrado.");
            return;
        }
        System.out.println("==== USUÁRIOS CADASTRADOS ====");
        for (Usuario u : listaUsuarios) {
            System.out.println("> Matrícula: " + u.getMatricula());
            System.out.println("  Nome: " + u.getNome());
            System.out.println("  CPF: " + u.getCpf());
            System.out.println("  Data de nascimento: " + u.getDataNascimento());
        }
    }

    public void salvar(Usuario u) {
        String sql = "insert into users (registration, name, cpf, dateBirth) " + "values (?, ?, ?, ?)";

        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (!verificarCpfExiste(u.getCpf())) {
                    stmt.setInt(1, u.getMatricula());
                    stmt.setString(2, u.getNome());
                    stmt.setString(3, u.getCpf());
                    if (u.getDataNascimento() != null) {
                        stmt.setDate(4, java.sql.Date.valueOf(u.getDataNascimento()));
                    } else {
                        stmt.setNull(4, java.sql.Types.NULL);
                    }
                    stmt.executeUpdate();
                    System.out.println("✅ Usuário salvo com sucesso!");
                } else {
                    throw new RuntimeException("❌ CPF já cadastrado em nossa base de dados.");
                }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public boolean verificarCpfExiste(String _cpf) {
        String sql = "select 1 from users where cpf = ? limit 1";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, _cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Erro ao verificar CPF: " + e.getMessage());
        }
    }

    public void buscarMatricula(int _registration) {
        String sql = "select * from users where registration = ?";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, _registration);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Registro encontrado no banco!");
                System.out.println("Matrícula: " + rs.getInt("registration"));
                System.out.println("Nome: " + rs.getString("name"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Data de nascimento: " + rs.getDate("dateBirth"));
            } else {
                System.out.println("❌ Nenhum registro retornado pelo banco.");
            }
        } catch (Exception e) {
            throw new RuntimeException("❌ Matrícula \"" + _registration + "\" não localizada na base de dados.");
        }
    }

    public void deletarUsuario(int _matricula) {
        String sql = "delete from users where registration = ?";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, _matricula);

                int effect = stmt.executeUpdate();
                if (effect > 0) {
                    System.out.println("✅ Matrícula \"" + _matricula + "\" deletada da base de dados.");
                } else {
                    System.out.println("❌ Matrícula \"" + _matricula + "\" não encontrada na base de dados.");
                }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Erro ao deletar registro do banco: " + e.getMessage());
        }
    }

    public void alterarDados(String newNome, String newCpf, String newDateBirth, int _matricula) {
        String sql = "update users set name = ?, cpf = ?, dateBirth = ? where registration = ?";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newNome);
            stmt.setString(2, newCpf);
            stmt.setDate(3, java.sql.Date.valueOf(converterData(newDateBirth)));
            stmt.setInt(4, _matricula);
            int effect = stmt.executeUpdate();
            if (effect > 0) {
                System.out.println("✅ Dados atualizados com sucesso.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Erro ao atualizar dados.");
        }
    }

    private LocalDate converterData(String _dataTexto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(_dataTexto, formatter);
    }

    public void deletarTodosDados() {
        String sql = "TRUNCATE TABLE users";

        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
                System.out.println("✅ Todos os dados foram apagados.");
        } catch (SQLException e) {
            throw new RuntimeException("❌ Erro ao deletar dados: " + e.getMessage());
        }
    }

}
