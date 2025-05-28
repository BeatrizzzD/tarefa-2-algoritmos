import java.time.LocalDateTime;

public class Documento {
    private String nomeArquivo;
    private String usuario;
    private LocalDateTime horarioSolicitacao;
    private LocalDateTime horarioImpressao;

    public Documento(String nomeArquivo, String usuario) {
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.horarioSolicitacao = LocalDateTime.now();
    }

    public String getNomeArquivo(){ 
        return nomeArquivo; 
    }
    public String getUsuario(){ 
        return usuario; 
    }
    public LocalDateTime getHorarioSolicitacao(){ 
        return horarioSolicitacao; 
    }
    public LocalDateTime getHorarioImpressao() {
        return horarioImpressao; 
    }
    public void setHorarioImpressao() {
        this.horarioImpressao = LocalDateTime.now();
    }
    public long getTempoEsperaSegundos() {
        if (horarioImpressao != null)
            return java.time.Duration.between(horarioSolicitacao, horarioImpressao).getSeconds();
        return -1;
    }

    @Override
    public String toString() {
        return "[" + nomeArquivo + " - " + "Usuário: " + usuario + " - Solicitado: " + horarioSolicitacao + "]";
    }
}
