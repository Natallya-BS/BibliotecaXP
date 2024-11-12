import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Biblioteca {

    // Método para registrar um novo empréstimo
    public void registrarEmprestimo(int usuarioId, int livroId, Date dataEmprestimo, Date dataDevolucao) {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, livroId);
            pstmt.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
            pstmt.setDate(4, new java.sql.Date(dataDevolucao.getTime()));
            pstmt.setString(5, "ativo");
            pstmt.executeUpdate();

            // Atualizar a quantidade disponível do livro
            Livro livro = buscarLivroPorId(livroId);
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
            atualizarLivro(livro);
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    // Método para buscar livro por ID
    public Livro buscarLivroPorId(int id) {
        // Implementação para buscar o livro no banco de dados
        return new Livro(id, "Titulo", "Autor", "Editora", 2020, 5); // Exemplo
    }

    // Método para atualizar livro no banco de dados
    public void atualizarLivro(Livro livro) {
        try (Connection conn = Database.connect()) {
            String sql = "UPDATE livros SET quantidade_disponivel = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, livro.getQuantidadeDisponivel());
            pstmt.setInt(2, livro.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
}
