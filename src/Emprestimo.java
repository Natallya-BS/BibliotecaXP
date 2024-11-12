import java.util.Date;

public class Emprestimo {
    private int id;
    private int usuarioId;
    private int livroId;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status; // "ativo", "atrasado", "devolvido"

    public Emprestimo(int id, int usuarioId, int livroId, Date dataEmprestimo, Date dataDevolucao, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Verifica se o empréstimo está atrasado
    public boolean isAtrasado() {
        return new Date().after(dataDevolucao) && status.equals("ativo");
    }
}
