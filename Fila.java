public class Fila {

    private No primeiro;
    private No ultimo;
    private int tamanho;
    private int capacidade = 5;

    public boolean filaVazia() {
        return primeiro == null;
    }

    public boolean filaCheia() {
        return tamanho >= capacidade;
    }

    public void enfileira(Documento documento) throws Fila.FilaCheiaException {
        if (filaCheia()) {
            throw new Fila.FilaCheiaException("Falha ao enfileirar " + documento.getNomeArquivo() + ": fila cheia (capacidade máxima de " + capacidade + " atingida)");
            
        }
        No novo = new No(documento);
        if (filaVazia()) {
            primeiro = novo;
        } else {
            ultimo.setProximo(novo);
        }
        ultimo = novo;
        tamanho++;
        System.out.println("Documento " + documento.getNomeArquivo() + " enfileirado");
    }

    public class FilaCheiaException extends Exception { 
        public FilaCheiaException(String message) {
            super(message);
        }
    }

    public Documento desenfileira() throws Fila.FilaVaziaException{
        if (filaVazia()) {
            throw new Fila.FilaVaziaException("Falha ao desenfileirar: fila vazia");
        }
        Documento documento = primeiro.getDocumento();
        // documento.setHorarioImpressao(LocalDateTime.now());
        documento.setHorarioImpressao();
        primeiro = primeiro.getProximo();
        if (primeiro == null) {
            ultimo = null;
        }
        tamanho--;
        return documento;
    }

    public class FilaVaziaException extends Exception { 
    public FilaVaziaException(String message) {
        super(message);
    }
}

    public Documento consultaDocumento(String nomeArquivo) {
        No atual = primeiro;
        int posicao = 0;
        while (atual != null) {
            if (atual.getDocumento().getNomeArquivo().equalsIgnoreCase(nomeArquivo)) {
                System.out.println("Documento " + nomeArquivo + " encontrado na posição " + (posicao + 1)
                        + " solicitado em " + atual.getDocumento().getHorarioSolicitacao());
                return atual.getDocumento();
            }
            atual = atual.getProximo();
            posicao++;
        }
        System.out.println("Documento " + nomeArquivo + " não encontrado na fila");
        return null;
    }

    @Override
    public String toString() {
        if (filaVazia()) {
            System.out.println("\n\nFila de impressão:");
            return "Fila vazia";
        }

        StringBuilder s = new StringBuilder("\n\nFila de impressão (" + tamanho + "/" + capacidade + "):\n");
        No runner = primeiro;
        while (runner != null) {
            s.append(runner).append(" -> ");
            runner = runner.getProximo();
        }
        return s.toString();
    }

    public int getTamanho() {
        return tamanho;
    }
}
