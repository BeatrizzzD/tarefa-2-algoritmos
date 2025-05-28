public class Pilha { 
    private No topo;
    private int tamanho;
    private int capacidade = 5;

    public static class PilhaCheiaException extends Exception {
        public PilhaCheiaException(String message) {
            super(message);
        }
    }
    public static class PilhaVaziaException extends Exception {
        public PilhaVaziaException(String message) {
            super(message);
        }
    }

    public boolean pilhaVazia() {
        return topo == null;
    }

    public boolean pilhaCheia() {
        return tamanho >= capacidade;
    }

    public void push(Documento documento) throws PilhaCheiaException {
        if (pilhaCheia()) {
            throw new PilhaCheiaException("Falha ao empilhar " + documento.getNomeArquivo() + ": pilha cheia (capacidade máxima de " + capacidade + " documentos atingida)");
        }
        No novo = new No(documento); 
        if (!pilhaVazia()) {
            novo.setProximo(topo);
        }
        topo = novo;
        tamanho++;
        System.out.println(documento.getNomeArquivo() + " adicionada à pilha");
    }

    public Documento pop() throws PilhaVaziaException {
        if (pilhaVazia()) {
            throw new PilhaVaziaException("Falha na reimpressão: pilha vazia");
        }
        Documento documento = topo.getDocumento(); 
        documento.setHorarioImpressao(); 
        topo = topo.getProximo(); 
        tamanho--;
        return documento;
    }
    
    public Documento peek() throws PilhaVaziaException {
        if (pilhaVazia()) {
            throw new PilhaVaziaException("Falha na consulta: pilha vazia");
        }
        return topo.getDocumento(); 
    }

    public Documento consultaDocumento(String nomeArquivo) {
        No atual = topo;
        int posicao = 1; 
        while (atual != null) {
            if (atual.getDocumento().getNomeArquivo().equalsIgnoreCase(nomeArquivo)) { 
                System.out.println("Documento '" + nomeArquivo + "' encontrado na posição " + posicao +
                                   " a partir do topo, Solicitado: " + atual.getDocumento().getHorarioSolicitacao().toLocalTime()); 
                return atual.getDocumento();
            }
            atual = atual.getProximo();
            posicao++;
        }
        System.out.println("Documento '" + nomeArquivo + "' não encontrado na pilha");
        return null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nPilha de Reimpressão (" + tamanho + "/" + capacidade + "):\n");
        No runner = topo; 
        int count = 1;
        while (runner != null) {
            s.append(count).append(". ").append(runner.getDocumento().getNomeArquivo()).append("\n"); 
            runner = runner.getProximo();
            count++;
        }
        return s.toString();
    }
}